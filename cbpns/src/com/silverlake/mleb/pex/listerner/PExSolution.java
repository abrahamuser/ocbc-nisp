package com.silverlake.mleb.pex.listerner;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silverlake.hlb.mib.bean.ObHeaderResponse;
import com.silverlake.hlb.mib.bean.ObResponse;
import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.pex.constant.PExConstant;
import com.silverlake.mleb.pex.entity.dao.MLEBPExDAO;
import com.silverlake.mleb.pex.module.func.CancelPex;
import com.silverlake.mleb.pex.module.func.ConfirmCreationPex;
import com.silverlake.mleb.pex.module.func.ConfirmCreationPex_TAC;
import com.silverlake.mleb.pex.module.func.ConfirmUpdatePexDirectAccount;
import com.silverlake.mleb.pex.module.func.CreationCheckDirectPex;
import com.silverlake.mleb.pex.module.func.CreationPex;
import com.silverlake.mleb.pex.module.func.PerformCreationPex;
import com.silverlake.mleb.pex.module.func.PerformUpdatePexDirectAccount;
import com.silverlake.mleb.pex.module.func.RetrievePexDirectAccount;
import com.silverlake.mleb.pex.module.func.RetrievePexTransaction;
import com.silverlake.mleb.pns.constant.PNSConstant;
import com.silverlake.mleb.pns.module.PushNotificationService;
import com.silverlake.mleb.pns.module.RetrievePromotionNotificationHistory;

@Service("PExSolution")
public class PExSolution {
	
	
	private static Logger log = LogManager.getLogger(PExSolution.class);
	
	@Autowired
	MLEBPExDAO dao;
	
	@Autowired PerformCreationPex performCreationPex;
	@Autowired CreationPex creationPex;
	@Autowired ConfirmCreationPex confirmCreationPex;
	@Autowired CancelPex cancelPex;
	@Autowired RetrievePexDirectAccount retrievePExAcc;
	@Autowired PerformUpdatePexDirectAccount updatePexAcc;
	@Autowired CreationCheckDirectPex createCheckDirectPex;
	@Autowired RetrievePexTransaction retrievePexTransaction;
	@Autowired ConfirmUpdatePexDirectAccount confirmUpdatePexAcc;
	@Autowired ConfirmCreationPex_TAC confirmResendTACPex;
	
	@Autowired PushNotificationService pushNotificationService;
	@Autowired RetrievePromotionNotificationHistory retrievePromotionNotificationHistory;

	public MICBResponseBean onCall(MICBRequestBean arg0) {
		// TODO Auto-generated method stub
		MICBResponseBean responseBean = new MICBResponseBean();
		try
		{
			MICBRequestBean micbRequestBean = arg0;
			
			
			//MICBRequestBean micbRequestBean = (MICBRequestBean) arg0.getMessage().getPayload();
			
			log.info("HLB PEX Mule Listerner onCall :: [ "+micbRequestBean.getHeaderBean().getServiceID()+" ]");
			
		
			if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(PExConstant.FUNC_CREATION_PEX))
			{
				
				responseBean = creationPex.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(PExConstant.FUNC_CONFIRM_CREATION_PEX))
			{
				
				responseBean = confirmCreationPex.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(PExConstant.FUNC_PERFORM_CREATION_PEX))
			{
				
				responseBean = performCreationPex.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(PExConstant.FUNC_CANCEL_PEX))
			{
				
				responseBean = cancelPex.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(PExConstant.FUNC_RETRIEVE_PEX_ACC))
			{	
				responseBean = retrievePExAcc.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(PExConstant.FUNC_CONFIRM_PEX_ACC))
			{
				responseBean = confirmUpdatePexAcc.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(PExConstant.FUNC_UPDATE_PEX_ACC))
			{
				responseBean = updatePexAcc.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(PExConstant.FUNC_CREATION_CHECK_DIRECT_PEX))
			{
				
				responseBean = createCheckDirectPex.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(PExConstant.FUNC_RETRIEVE_PEX_TRANSACTION_HIS))
			{
				
				responseBean = retrievePexTransaction.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(PExConstant.FUNC_CREATION_RESEND_TAC_PEX))
			{
				
				responseBean = confirmResendTACPex.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(PExConstant.FUNC_PEX_PUSH_NOTIFICATION))
			{
				responseBean = pushNotificationService.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(PNSConstant.FUNC_PNB_RETRIEVE_PUSH_NOTIF_HIST))
			{
				responseBean = retrievePromotionNotificationHistory.acceptProcess(micbRequestBean);
			}
			else
			{
				ObResponse obResponse = new ObResponse();
				obResponse.setObHeader(new ObHeaderResponse());
				obResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_INVALID_FUNC_ERROR);
				responseBean.setBDObject(obResponse);
			}

			
			
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.error(this.getClass().toString(), e);
			
			ObResponse obResponse = new ObResponse();
			obResponse.setObHeader(new ObHeaderResponse());
			obResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COMMON_ERROR);
			responseBean.setBDObject(obResponse);
		}
		
		
		return responseBean;
	}	
	


}
