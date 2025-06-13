package com.silverlake.mleb.mcb.module.func.authorization;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.mcb.bean.ObAuthorizationRequest;
import com.silverlake.mleb.mcb.bean.ObAuthorizationResponse;
import com.silverlake.mleb.mcb.bean.ObHeaderResponse;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.entity.GeneralCode;
import com.silverlake.mleb.mcb.entity.dao.CustomerDAO;
import com.silverlake.mleb.mcb.entity.dao.GeneralCodeDAO;
import com.silverlake.mleb.mcb.module.common.FuncServices;
import com.silverlake.mleb.mcb.module.vc.authorization.AuthRequestData;
import com.silverlake.mleb.mcb.module.vc.authorization.AuthResponseData;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.common.VCService;
import com.silverlake.mleb.mcb.module.vc.transaction.Transaction;
import com.silverlake.mleb.mcb.module.vc.user.RequestData;
import com.silverlake.mleb.mcb.module.vc.user.ResponseData;

/*Authorization (Transaction List)*/

@Service
public class RetrieveAuthorizeTransaction extends FuncServices  
{
	private static Logger log = LogManager.getLogger(RetrieveAuthorizeTransaction.class);

	@Autowired
	CustomerDAO dao;

	@Autowired
	GeneralCodeDAO gnDao;

	@Autowired ApplicationContext appContext;

	public MICBResponseBean process(MICBRequestBean arg0) {
		
		MICBResponseBean response = new MICBResponseBean();
		ObAuthorizationResponse obResponse = new ObAuthorizationResponse();
		obResponse.setObHeader(new ObHeaderResponse());

		try {
			ObAuthorizationRequest requestData = (ObAuthorizationRequest) arg0.getBDObject();
			String orgId = requestData.getObUser().getOrgId();
			String usrId = requestData.getObUser().getLoginId();
			String ipAddress = requestData.getObHeader() == null ? null : requestData.getObHeader().getIp();
			List<String> source_trx = requestData.getSource_trx();
			
			for(String indSourceTrx:source_trx){
				//When source transaction is NB (Non-bulk), payment master id must contain value
				if(indSourceTrx.equalsIgnoreCase("NB")){
					if(requestData.getPymtMasterId() == null || requestData.getPymtMasterId().isEmpty()){
						log.info("Payment master id must not be empty for non-bulk source transaction");
						obResponse.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
					}
					break;
				}
			}
			
			if(obResponse.getObHeader().getStatusCode() != null){
				response.setBDObject(obResponse);
				return response;
			}
			
			if (requestData.getTrx_status()!=null) {
				if(requestData.getTrx_status().equalsIgnoreCase("") || requestData.getTrx_status().isEmpty()) {
					requestData.setTrx_status("004");
				}
			}else {
				requestData.setTrx_status("004");
			}
			
			String trx_status = requestData.getTrx_status();
			String value_date_from = requestData.getValue_date_from();
			String value_date_to = requestData.getValue_date_to();
			
			String pageSize = requestData.getPage_size()==null?"10":requestData.getPage_size();
			pageSize = pageSize.trim().length()==0?"10":pageSize;
			
			String pageNo = (String) (requestData.getPage_no()==null?"1":requestData.getPage_no());
			pageNo = pageNo.trim().length()==0?"1":pageNo;
			
			
			
			
			//Call REQ OMNI Auth
			VCService usrService = new VCService(appContext);
			AuthRequestData vcAuthTransactionRequest = new AuthRequestData();

			vcAuthTransactionRequest.setOrg_cd(orgId);
			vcAuthTransactionRequest.setUsr_cd(usrId);
			vcAuthTransactionRequest.setPage_size(pageSize);
			vcAuthTransactionRequest.setPage_no(pageNo);
			vcAuthTransactionRequest.setSource_trx(source_trx);
			vcAuthTransactionRequest.setTrx_status(trx_status);
			vcAuthTransactionRequest.setValue_date_from(value_date_from);
			vcAuthTransactionRequest.setValue_date_to(value_date_to);
			vcAuthTransactionRequest.setPymt_master_id(requestData.getPymtMasterId());
			if(requestData.getUpload_date_from() != null && !requestData.getUpload_date_from().isEmpty()) {
				vcAuthTransactionRequest.setUpload_date_from(requestData.getUpload_date_from());
			}
			if(requestData.getUpload_date_to() != null && !requestData.getUpload_date_to().isEmpty()) {
				vcAuthTransactionRequest.setUpload_date_to(requestData.getUpload_date_to());
			}
			if(requestData.getProd_cd() != null && !requestData.getProd_cd().isEmpty()) {
				vcAuthTransactionRequest.setProd_cd(requestData.getProd_cd());
			}
			
			
			//Call REQ OMNI ActionList
			RequestData vcActionListRequest = new RequestData();
			vcActionListRequest.setOrg_cd(orgId);
			vcActionListRequest.setUsr_cd(usrId);
			vcActionListRequest.setMn_itm_id("10217");  //hardcoded 22July2019
			
			//Call RES OMNI Auth 
			AuthResponseData vcAuthResponseData = new AuthResponseData();
			VCResponse vcAuthResponse = usrService.callOmniVC(vcAuthTransactionRequest.method_auth_List_pending_trx,vcAuthTransactionRequest, vcAuthResponseData, requestData.getUserContext().getSessionId(), arg0.getTranxID(), ipAddress);
			
			//Call RES OMNI ActionList 
			ResponseData vcListActionResponseData = new ResponseData();
			VCResponse vcActionListResponse = usrService.callOmniVC(VCMethodConstant.REST_METHODS.USER_ACCESS_RESTRICTION,vcActionListRequest, vcListActionResponseData, requestData.getUserContext().getSessionId(), arg0.getTranxID(), ipAddress);
			
			if(vcAuthResponse.getResponse_code().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS))
			{
				vcAuthResponseData = (AuthResponseData) vcAuthResponse.getData();
				List<GeneralCode> productCodeList = new ArrayList();
				
				if(arg0.getLocaleCode().equalsIgnoreCase(MiBConstant.LOCALE_IN))
				{
					productCodeList = gnDao.getAllTaskTranxProductCode_IN();
				}
				else if(arg0 != null && arg0.getLocaleCode() != null && arg0.getLocaleCode().toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase()))
				{
					productCodeList = gnDao.getAllTaskTranxProductCode_CN();
				}
				else
				{
					productCodeList = gnDao.getAllTaskTranxProductCode();
				}
				
				//map upload format description
				List<GeneralCode> uploadFormat = new ArrayList(); 
				if(arg0.getLocaleCode().equalsIgnoreCase(MiBConstant.LOCALE_IN)) {
					uploadFormat = gnDao.findByGnCodeType(MiBConstant.TRANXUPLOADFORMAT, MiBConstant.LOCALE_IN);
				}else if(arg0 != null && arg0.getLocaleCode() != null && arg0.getLocaleCode().toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase())){
					uploadFormat = gnDao.findByGnCodeType(MiBConstant.TRANXUPLOADFORMAT, MiBConstant.LOCALE_CN);
				} else {
					uploadFormat = gnDao.findByGnCodeType(MiBConstant.TRANXUPLOADFORMAT, MiBConstant.LOCALE_EN);
				}
				
				
				if(vcAuthResponseData.getList_trx()!=null)
				{
					obResponse.setList_trx(new ArrayList());
					//add index through looping below
					int index = 1 + ((Integer.parseInt(pageNo)-1)*Integer.parseInt(pageSize));
					for(Transaction temp:vcAuthResponseData.getList_trx())
					{
						for(GeneralCode gnTmp:productCodeList)
						{
							if(null!=temp.getProd_cd() && temp.getProd_cd().equalsIgnoreCase(gnTmp.getGnCode()))
							{
								temp.setProd_desc(gnTmp.getGnCodeDescription());
								break;
							}
						}
						
						
						for(GeneralCode gnTmp:uploadFormat)
						{
							if(null!=temp.getUpload_format() && temp.getUpload_format().equalsIgnoreCase(gnTmp.getGnCode()))
							{
								temp.setUpload_format_desc(gnTmp.getGnCodeDescription());
								break;
							}
						}
						
						
						Transaction transaction = new Transaction();
						BeanUtils.copyProperties(temp, transaction);
						transaction.setIndex(String.valueOf(index));
						obResponse.getList_trx().add(transaction);
						index++;
					}
					obResponse.setTotal_rows(String.valueOf(vcAuthResponseData.getTotal_rows()));
					
					
					if(vcActionListResponse.getResponse_code().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS))
					{
						vcListActionResponseData = (ResponseData) vcActionListResponse.getData();
						if(vcListActionResponseData.getList_restriction()!=null)
						{
							obResponse.setList_restriction(new ArrayList());
							for(String actionTemp:vcListActionResponseData.getList_restriction())
							{
								obResponse.getList_restriction().add(actionTemp);
							}
						}
						else
						{	
							obResponse.getObHeader().setStatusCode(MiBConstant.MIB_GENERAL_ERROR);
						}
					}
					else
					{
						obResponse.getObHeader().setStatusCode(MiBConstant.MIB_OMNI_PREFIX+vcAuthResponse.getResponse_code() );
						obResponse.getObHeader().setStatusMessage(vcAuthResponse.getResponse_desc());
					}
					
				}
				else
				{
					obResponse.getObHeader().setStatusCode(MiBConstant.MIB_GENERAL_ERROR);
				}
				//VELO2UAT-307 - should return prod list to FE & add prod code field
				List<GeneralCode> taskMapProductCodeList = gnDao.getAllTASKMapProdCode();
				List<Transaction> taskproductList = new ArrayList();
				for(String selectedSource:source_trx) {
					for(GeneralCode tmp : taskMapProductCodeList) {
						if(selectedSource.equalsIgnoreCase(tmp.getGnCode())) {
							String split[] = tmp.getGnCodeDescription().split(",");
							for(GeneralCode tmpx: productCodeList) {
								for(String tmpy:split) {
									if(tmpy.equalsIgnoreCase(tmpx.getGnCode())) {
										Transaction trx = new Transaction();
										trx.setProd_cd(tmpx.getGnCode());
										trx.setProd_desc(tmpx.getGnCodeDescription());
										trx.setTrx_source(tmp.getGnCode());
										taskproductList.add(trx);
									}
								}
							}
							break;
						}
					}
				}
				obResponse.setProductList(taskproductList);
				//Ignore any error but returning success always to FE
				obResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
			}
			else
			{
				obResponse.getObHeader().setStatusCode(MiBConstant.MIB_OMNI_PREFIX+vcAuthResponse.getResponse_code() );
				obResponse.getObHeader().setStatusMessage(vcAuthResponse.getResponse_desc());
			}

		} catch (Exception e) {

			log.info(this.getClass().toString(), e);
			obResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			obResponse.getObHeader().setStatusMessage(e.getMessage());
		}

		response.setBDObject(obResponse);

		return response;
	}
}
