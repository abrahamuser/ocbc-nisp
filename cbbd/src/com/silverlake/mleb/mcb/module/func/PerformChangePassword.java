package com.silverlake.mleb.mcb.module.func;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.mcb.bean.ObAuthenticationRequest;
import com.silverlake.mleb.mcb.bean.ObAuthenticationResponse;
import com.silverlake.mleb.mcb.bean.ObHeaderResponse;
import com.silverlake.mleb.mcb.bean.ObUserContext;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.entity.dao.CustomerDAO;
import com.silverlake.mleb.mcb.entity.dao.MLEBMIBDAO;
import com.silverlake.mleb.mcb.module.common.FuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.common.VCService;
import com.silverlake.mleb.mcb.module.vc.user.RequestData;
import com.silverlake.mleb.mcb.module.vc.user.ResponseData;



@Service
public class PerformChangePassword extends FuncServices  
{

	private static Logger log = LogManager.getLogger(PerformChangePassword.class);
	
	
	@Autowired
	MLEBMIBDAO dao;
	
	@Autowired
	CustomerDAO custDao;
	
	 
	@Autowired ApplicationContext appContext;
	
	
	public MICBResponseBean process(MICBRequestBean arg0) {
		// TODO Auto-generated method stub
		
		
	
		MICBResponseBean response = new MICBResponseBean();
		ObAuthenticationResponse responseBean = new ObAuthenticationResponse();
		responseBean.setObHeader(new ObHeaderResponse());
		responseBean.setUserContext(new ObUserContext());
		
		try {

			ObAuthenticationRequest requestData = (ObAuthenticationRequest) arg0.getBDObject();			
			String deviceOS = arg0.getDeviceBean() == null ? null : arg0.getDeviceBean().getType()+" "+arg0.getDeviceBean().getOs();
			String deviceType = arg0.getDeviceBean() == null ? null : arg0.getDeviceBean().getModel();
			String ipAddress = requestData.getObHeader() == null ? null : requestData.getObHeader().getIp();
			
			VCService usrService = new VCService(appContext); 
			ResponseData vcResponseData = new ResponseData();
			
			 
			
			
				RequestData vcChangePasswordRequest = new RequestData();
				vcChangePasswordRequest.setOrg_cd(requestData.getObUser().getOrgId());
				vcChangePasswordRequest.setUsr_cd(requestData.getObUser().getUserId());
				vcChangePasswordRequest.setParam_c(requestData.getcString());
				vcChangePasswordRequest.setParam_p(requestData.getpString());
				vcChangePasswordRequest.setRandom_number(requestData.getRandomNumber());
				vcChangePasswordRequest.setDevice_type(deviceType);
				vcChangePasswordRequest.setDevice_os(deviceOS);
				vcChangePasswordRequest.setPassword_data_block(requestData.getPasswordDataBlock());
			 
				VCResponse vcResponse = usrService.callOmniVC(VCMethodConstant.REST_METHODS.USER_CHANGE_PASSWORD, vcChangePasswordRequest, vcResponseData, requestData.getUserContext().getSessionId(), arg0.getTranxID(), ipAddress);
				
				if(!vcResponse.getResponse_code().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS)){
					
					responseBean.getObHeader().setStatusCode(MiBConstant.MIB_OMNI_PREFIX+vcResponse.getResponse_code());
					responseBean.getObHeader().setStatusMessage(vcResponse.getResponse_desc());
					response.setBDObject(responseBean);
					 
				}
				else
				{
					vcResponseData = (ResponseData) vcResponse.getData();
					responseBean.setUserSecStatus(vcResponseData.getUser_sec_status());
					responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
				
			
				}
			
			
			
		
		} catch (Exception e) {
			 
			log.info(this.getClass().toString()+" ERROR Logout ", e);
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			responseBean.getObHeader().setStatusMessage(e.getMessage());
	
		}
		
		response.setBDObject(responseBean);
		
		return response;
	}






	
	
}
