package com.silverlake.mleb.mcb.module.func.task;



import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.mcb.bean.ObHeaderResponse;
import com.silverlake.mleb.mcb.bean.ObTaskListRequest;
import com.silverlake.mleb.mcb.bean.ObTaskListResponse;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.entity.GeneralCode;
import com.silverlake.mleb.mcb.entity.dao.GeneralCodeDAO;
import com.silverlake.mleb.mcb.module.common.FuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.common.VCService;
import com.silverlake.mleb.mcb.module.vc.task_list.TaskListRequestData;
import com.silverlake.mleb.mcb.module.vc.task_list.TaskListResponseData;
import com.silverlake.mleb.mcb.module.vc.transaction.Transaction;
import com.silverlake.mleb.mcb.module.vc.user.ResponseData;






@Service
public class RetrieveTaskTransaction extends FuncServices  
{

	private static Logger log = LogManager.getLogger(RetrieveTaskTransaction.class);
	
	@Autowired
	GeneralCodeDAO dao;
	
	 
	@Autowired ApplicationContext appContext;
	
	
	public MICBResponseBean process(MICBRequestBean arg0) {
		// TODO Auto-generated method stub
		
		
	
		MICBResponseBean response = new MICBResponseBean();
		ObTaskListResponse obTaskListResponse = new ObTaskListResponse();
		obTaskListResponse.setObHeader(new ObHeaderResponse());
	
		
		
		try {


			ObTaskListRequest requestData = (ObTaskListRequest) arg0.getBDObject();			
			String orgId = requestData.getObUser().getOrgId();
			String usrId = requestData.getObUser().getLoginId();
			String ipAddress = requestData.getObHeader() == null ? null : requestData.getObHeader().getIp();
			VCService userAsyncService = new VCService(appContext); 
			
			TaskListRequestData requestReq = new TaskListRequestData();
			requestReq.setOrg_cd(orgId);
			requestReq.setUsr_cd(usrId);
			requestReq.setSource(requestData.getTranxSourcelist());
			requestReq.setStatus(requestData.getStatus());
			requestReq.setList_prod_cd(getProdCdList(requestData.getProdCodeList()));
			
			String pageSize = requestData.getPageSize()==null?"10":requestData.getPageSize();
			pageSize = pageSize.trim().length()==0?"10":pageSize;
			
			
			String pageNo = requestData.getPageNo()==null?"1":requestData.getPageNo();
			pageNo = pageNo.trim().length()==0?"1":pageNo;
			
			
			requestReq.setPage_size(pageSize);
			requestReq.setPage_no(pageNo);
			//If transactional records, put optional value from and to date as request parameters
			if(requestData.getTranxSourcelist()==null || !requestData.getTranxSourcelist().equalsIgnoreCase("NT")){
				requestReq.setValue_date_from(requestData.getValueDateFrom());
				requestReq.setValue_date_to(requestData.getValueDateTo());
			}

			requestReq.setSource_file_name(requestData.getSourceFileName());
			requestReq.setUpload_date_from(requestData.getUploadDateFrom());
			requestReq.setUpload_date_to(requestData.getUploadDateTo());
			
			TaskListResponseData responseDataTmp = new TaskListResponseData();
			VCResponse vcResponse = new VCResponse();
			if(requestData.getTranxSourcelist()!=null && requestData.getTranxSourcelist().equalsIgnoreCase("NT"))
			{
			
				vcResponse = userAsyncService.callOmniVC(VCMethodConstant.REST_METHODS.TASK_LIST_NON_TXN.getUri(), requestReq, responseDataTmp, requestData.getUserContext().getSessionId(),  arg0.getTranxID(), ipAddress);
			
			}
			else
			{
				String omniPath = VCMethodConstant.REST_METHODS.TASK_LIST_TXN.getUri();
				if(requestData.getModule() != null && requestData.getModule().equalsIgnoreCase("SUMMARY")){
					omniPath =  VCMethodConstant.REST_METHODS.TRANSACTION_TXN_SUMMARY.getUri();
				}
				vcResponse = userAsyncService.callOmniVC(omniPath, requestReq, responseDataTmp, requestData.getUserContext().getSessionId(),  arg0.getTranxID(), ipAddress);
			}
			
			
			if(vcResponse.getResponse_code().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS))
			{
				
				responseDataTmp = (TaskListResponseData) vcResponse.getData();
				//map product description
				List<GeneralCode> productCodeList = new ArrayList();
				if(arg0.getLocaleCode().equalsIgnoreCase(MiBConstant.LOCALE_IN)) {
					productCodeList = dao.getAllTaskTranxProductCode_IN();
				} else if(arg0 != null && arg0.getLocaleCode() != null && arg0.getLocaleCode().toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase())){
					productCodeList = dao.getAllTaskTranxProductCode_CN();
				} else {
					productCodeList = dao.getAllTaskTranxProductCode();
				}
				//map status description
				List<GeneralCode> gnTranxStatusCode = new ArrayList();
				if(arg0.getLocaleCode().equalsIgnoreCase(MiBConstant.LOCALE_IN)){
					gnTranxStatusCode = dao.getAllTaskTranxStatusCode_IN();
				} else if(arg0 != null && arg0.getLocaleCode() != null && arg0.getLocaleCode().toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase())){
					gnTranxStatusCode = dao.getAllTaskTranxStatusCode_CN();
				} else {
					gnTranxStatusCode = dao.getAllTaskTranxStatusCode();
				}
				//map rollover type description
				List<GeneralCode> rolloverTypeList = dao.findByGnCodeType(MiBConstant.ROLLOVERTYPE, arg0.getLocaleCode());
				//map upload format description 
				List<GeneralCode> uploadFormat = new ArrayList();
				if(arg0.getLocaleCode().equalsIgnoreCase(MiBConstant.LOCALE_IN)) {
					uploadFormat = dao.findByGnCodeType(MiBConstant.TRANXUPLOADFORMAT, MiBConstant.LOCALE_IN);
				} else if(arg0 != null && arg0.getLocaleCode() != null && arg0.getLocaleCode().toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase())){
					uploadFormat = dao.findByGnCodeType(MiBConstant.TRANXUPLOADFORMAT, MiBConstant.LOCALE_CN);
				} else {
					uploadFormat = dao.findByGnCodeType(MiBConstant.TRANXUPLOADFORMAT, MiBConstant.LOCALE_EN);
				}
				
				int index = 1 + ((Integer.parseInt(pageNo)-1)*Integer.parseInt(pageSize));
				for(Transaction taskTranx:responseDataTmp.getList_trx()){
					//Map product description
					if(null!=taskTranx.getProd_cd()){
						for(GeneralCode gnTmp:productCodeList){
							if(taskTranx.getProd_cd().equalsIgnoreCase(gnTmp.getGnCode())){
								taskTranx.setProd_desc(gnTmp.getGnCodeDescription());
								break;
							}
						}
					}
					//Map status description
					if(null!=taskTranx.getTrx_status()){
						for(GeneralCode gnTmp:gnTranxStatusCode){
							if(taskTranx.getTrx_status().equalsIgnoreCase(gnTmp.getGnCode())){
								taskTranx.setTrx_status_desc(gnTmp.getGnCodeDescription());
								break;
							}
						}
					}
					//Map rollover type description
					if(null!=taskTranx.getRollover_type()){
						for(GeneralCode gnTmp:rolloverTypeList){
							if(taskTranx.getRollover_type().equalsIgnoreCase(gnTmp.getGnCode())){
								taskTranx.setRollover_type_desc(gnTmp.getGnCodeDescription());
								break;
							}
						}
					}
					//Map rollover type new description
					if(null!=taskTranx.getRollover_type_new()){
						for(GeneralCode gnTmp:rolloverTypeList){
							if(taskTranx.getRollover_type_new().equalsIgnoreCase(gnTmp.getGnCode())){
								taskTranx.setRollover_type_new_desc(gnTmp.getGnCodeDescription());
								break;
							}
						}
					}
					//Map upload format description 
					if(null!=taskTranx.getUpload_format()){
						for(GeneralCode gnTmp:uploadFormat){
							if(taskTranx.getUpload_format().equalsIgnoreCase(gnTmp.getGnCode())){
								taskTranx.setUpload_format_desc(gnTmp.getGnCodeDescription());
								break;
							}
						}
					}
					
					//setting remittance_no to blank if it is null
					if(null!=taskTranx.getRemittance_no()){
					
					}else{
						taskTranx.setRemittance_no("");
					}
					
					
					//Add index in order to be used at performAuthConfirmation
					taskTranx.setIndex(String.valueOf(index));
					//Set the source of transaction into response, if requested source is single only.
					if(requestData.getTranxSourcelist().indexOf(",")<0){
						taskTranx.setTrx_source(requestData.getTranxSourcelist());
					}
					index++;
				}
				
				obTaskListResponse.setTaskTransactionList(responseDataTmp.getList_trx());
				obTaskListResponse.setTotal_rows(String.valueOf(responseDataTmp.getTotal_rows()));

				
				List<GeneralCode> taskMapProductCodeList = dao.getAllTASKMapProdCode();
				
				
				List<Transaction> taskproductList = new ArrayList();
				
				for(GeneralCode tmp : taskMapProductCodeList)
				{
					if(requestData.getTranxSourcelist().equalsIgnoreCase(tmp.getGnCode()))
					{
						
						String split[] = tmp.getGnCodeDescription().split(",");
						for(GeneralCode tmpx: productCodeList)
						{
							for(String tmpy:split)
							{
								if(tmpy.equalsIgnoreCase(tmpx.getGnCode()))
								{
									Transaction trx = new Transaction();
									trx.setProd_cd(tmpx.getGnCode());
									trx.setProd_desc(tmpx.getGnCodeDescription());
									taskproductList.add(trx);
								}
								
							}
							
							
							
						}
						
						
						break;
					}
				}
				obTaskListResponse.setProductList(taskproductList);
				

				
				obTaskListResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
			}
			else
			{
				
				obTaskListResponse.getObHeader().setStatusCode(MiBConstant.MIB_OMNI_PREFIX+vcResponse.getResponse_code() );
				obTaskListResponse.getObHeader().setStatusMessage(vcResponse.getResponse_desc());
			}
			
			
			
			
			
		
		} catch (Exception e) {
			
			
		
			log.info(this.getClass().toString(), e);
			obTaskListResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			obTaskListResponse.getObHeader().setStatusMessage(e.getMessage());
	
		}
		
		response.setBDObject(obTaskListResponse);
		
		return response;
	}
	
	public List<String> getProdCdList(String prdCdList)
	{
		if(prdCdList!=null && prdCdList.trim().length()>0)
		{
		
			List<String> rs = new ArrayList();
			
			
			String split[] = prdCdList.split(",");
			
			for(String tmp:split)
			{
				rs.add(tmp);
			}
			
			return rs;
		}
		
		return null;
	}
 

	
	
}