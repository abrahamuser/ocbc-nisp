package com.silverlake.mleb.mcb.module.func.others;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.mcb.bean.ObAuthenticationResponse;
import com.silverlake.mleb.mcb.bean.ObHeaderResponse;
import com.silverlake.mleb.mcb.bean.ObSendFeedbackFaqRequest;
import com.silverlake.mleb.mcb.bean.ObUserContext;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.entity.dao.CustomerDAO;
import com.silverlake.mleb.mcb.entity.dao.MLEBMIBDAO;
import com.silverlake.mleb.mcb.module.common.FuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.common.VCService;



@Service
public class SendFeedbackFaq extends FuncServices  
{

	private static Logger log = LogManager.getLogger(SendFeedbackFaq.class);
	
	@Autowired
	MLEBMIBDAO dao;
	
	@Autowired
	CustomerDAO custDao;
	 
	@Autowired ApplicationContext appContext;
	
	public MICBResponseBean process(MICBRequestBean arg0) {
		// TODO Auto-generated method stub
	
		MICBResponseBean response = new MICBResponseBean();
		ObAuthenticationResponse oBResponse = new ObAuthenticationResponse();
		oBResponse.setObHeader(new ObHeaderResponse());
		
		try {

			ObSendFeedbackFaqRequest requestData = (ObSendFeedbackFaqRequest) arg0.getBDObject();	
			String faq_id = requestData.getFaq_id();
			String feedback = requestData.getFeedback();
			String org_cd = requestData.getOrg_cd();
			String usr_cd = requestData.getUsr_cd();
			String deviceId = arg0.getDeviceBean()==null?null:arg0.getDeviceBean().getDeviceId();
			String deviceOs = arg0.getDeviceBean()==null?null:arg0.getDeviceBean().getOs();
			String deviceType = arg0.getDeviceBean()==null?null:arg0.getDeviceBean().getType();
			String ipAddress = requestData.getObHeader() == null ? null : requestData.getObHeader().getIp();
			
			//Call REQ OMNI SendFeedbackFaq
			VCService usrService = new VCService(appContext); 
			com.silverlake.mleb.mcb.module.vc.others.RequestData vcFeedbackFaqRequest = new com.silverlake.mleb.mcb.module.vc.others.RequestData();
			vcFeedbackFaqRequest.setFaq_id(faq_id);
			vcFeedbackFaqRequest.setFeedback(feedback);
			vcFeedbackFaqRequest.setOrg_cd(org_cd);
			vcFeedbackFaqRequest.setUsr_cd(usr_cd);
			vcFeedbackFaqRequest.setDevice_id(deviceId);
			vcFeedbackFaqRequest.setDevice_type(deviceType);
			vcFeedbackFaqRequest.setDevice_os(deviceOs);
			
			//Call RES OMNI SendFeedbackFaq
			com.silverlake.mleb.mcb.module.vc.others.ResponseData vcFeedbackFaqResponseData = new com.silverlake.mleb.mcb.module.vc.others.ResponseData();
			VCResponse vcResponse = usrService.callOmniVC(VCMethodConstant.REST_METHODS.FAQ_FEEDBACK,vcFeedbackFaqRequest, vcFeedbackFaqResponseData, null, arg0.getTranxID(), ipAddress);
			
			if(vcResponse.getResponse_code().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS))
			{
				oBResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
			}
			else
			{
				oBResponse.getObHeader().setStatusCode(MiBConstant.MIB_OMNI_PREFIX+vcResponse.getResponse_code() );
				oBResponse.getObHeader().setStatusMessage(vcResponse.getResponse_desc());
			}
		
		} catch (Exception e) {
			 
			log.info(this.getClass().toString()+" ERROR Logout ", e);
			oBResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			oBResponse.getObHeader().setStatusMessage(e.getMessage());
		}
		
		response.setBDObject(oBResponse);
		
		return response;
	}

}
