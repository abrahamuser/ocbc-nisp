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
import com.silverlake.mleb.mcb.entity.GeneralCode;
import com.silverlake.mleb.mcb.entity.dao.CustomerDAO;
import com.silverlake.mleb.mcb.entity.dao.GeneralCodeDAO;
import com.silverlake.mleb.mcb.module.common.FuncServices;
import com.silverlake.mleb.mcb.module.vc.authorization.AuthPayment;
import com.silverlake.mleb.mcb.module.vc.authorization.AuthRequestData;
import com.silverlake.mleb.mcb.module.vc.authorization.AuthResponseData;
import com.silverlake.mleb.mcb.module.vc.authorization.Backdate;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.common.VCService;
import com.silverlake.mleb.mcb.module.vc.transaction.Transaction;

/*Authorization (Approve Part 1)*/

@Service
public class PreAuthorizeTransaction extends FuncServices  
{
	private static Logger log = LogManager.getLogger(PreAuthorizeTransaction.class);

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
			
			AuthRequestData vcAuthTransactionRequest = new AuthRequestData();
			VCService usrService = new VCService(appContext);
			vcAuthTransactionRequest.setOrg_cd(orgId);
			vcAuthTransactionRequest.setUsr_cd(usrId);
			
			//Map trxList to paymentList
			if (requestData.getList_trx()!=null)
			{
				vcAuthTransactionRequest.setList_payment(new ArrayList());
				for(Transaction temp:requestData.getList_trx())
				{
					AuthPayment authPayment = new AuthPayment();
					BeanUtils.copyProperties(temp, authPayment);
					authPayment.setAction_cd(requestData.getAction_cd());
					vcAuthTransactionRequest.getList_payment().add(authPayment);
				}
			}
			
			//Call RES OMNI Auth 
			AuthResponseData vcAuthResponseData = new AuthResponseData();
			VCResponse<AuthResponseData> vcAuthResponse = null;
			vcAuthResponse = usrService.callOmniVC(vcAuthTransactionRequest.method_auth_pre_authorize,vcAuthTransactionRequest, vcAuthResponseData, requestData.getUserContext().getSessionId(), arg0.getTranxID(), ipAddress);
			
			if(vcAuthResponse.getResponse_code().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS))
			{
				vcAuthResponseData = vcAuthResponse.getData();
				List<GeneralCode> productCodeList = new ArrayList();
				if(arg0.getLocaleCode().equalsIgnoreCase(MiBConstant.LOCALE_IN))
				{
					productCodeList = gnDao.getAllTaskTranxProductCode_IN();
				}
				else if(arg0 != null && arg0.getLocaleCode() != null && arg0.getLocaleCode().toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase())) //FE is zh which is CN.
				{
					productCodeList = gnDao.getAllTaskTranxProductCode_CN();
				}
				else
				{
					productCodeList = gnDao.getAllTaskTranxProductCode();
				}
				obResponse.setList_backdated_info(new ArrayList());
				if (vcAuthResponseData.getList_backdated_info()==null || vcAuthResponseData.getList_backdated_info().isEmpty())
				{
					obResponse.getObHeader().setStatusCode(MiBConstant.MCB_BACKDATEDLIST_EMPTY);
				}
				else 
				{
					for(Backdate temp:vcAuthResponseData.getList_backdated_info())
					{
						for(GeneralCode gnTmp:productCodeList)
						{
							if(null!=temp.getProd_cd() && temp.getProd_cd().equalsIgnoreCase(gnTmp.getGnCode()))
							{
								temp.setProd_desc(gnTmp.getGnCodeDescription());
								break;
							}
						}
						
						
						Backdate backdate = new Backdate();
						BeanUtils.copyProperties(temp, backdate);
						obResponse.getList_backdated_info().add(backdate);
					}
					obResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
				}
				
				obResponse.setList_payment(new ArrayList());
				//map product description
				for(AuthPayment temp:vcAuthTransactionRequest.getList_payment())
				{
					for(GeneralCode gnTmp:productCodeList)
					{
						if(null!=temp.getProd_cd() && temp.getProd_cd().equalsIgnoreCase(gnTmp.getGnCode()))
						{
							temp.setProd_desc(gnTmp.getGnCodeDescription());
							break;
						}
					}
					
					AuthPayment transaction = new AuthPayment();
					BeanUtils.copyProperties(temp, transaction);
					obResponse.getList_payment().add(transaction);
				}
				
				obResponse.setIdTransaction(requestData.getIdTransaction());
				
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
