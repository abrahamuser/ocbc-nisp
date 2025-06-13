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

/**
 * Purpose: used to reset user's password
 * Omni Web Services:
 * user/reset_password
 * 
 * @author AdamZalil
 *
 */

@Service
public class PerformResetPassword extends FuncServices  
{
	private static Logger log = LogManager.getLogger(PerformResetPassword.class);

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
			String ipAddress = requestData.getObHeader() == null ? null : requestData.getObHeader().getIp();
			VCService usrService = new VCService(appContext); 
			ResponseData vcResponseData = new ResponseData();
			/*if (requestData.getPhone()==null || requestData.getPhone().trim().length()==0) {
				requestData.setPhone(requestData.getObUser().getMobileNumber());
			}*/

			RequestData vcResetPasswordRequest = new RequestData();
			vcResetPasswordRequest.setOrg_cd(requestData.getObUser().getOrgId());
			vcResetPasswordRequest.setUsr_cd(requestData.getObUser().getUserId());
			vcResetPasswordRequest.setEmail(requestData.getEmail());
			vcResetPasswordRequest.setPhone(requestData.getPhone());
			if(arg0.getDeviceBean() != null) {
				vcResetPasswordRequest.setDevice_type(arg0.getDeviceBean().getModel());
				vcResetPasswordRequest.setDevice_os(arg0.getDeviceBean().getType()+" "+arg0.getDeviceBean().getOs());
			}

			VCResponse vcResponse = usrService.callOmniVC(VCMethodConstant.REST_METHODS.USER_RESET_PASSWORD, vcResetPasswordRequest, vcResponseData, requestData.getUserContext().getSessionId(), arg0.getTranxID(), ipAddress);

			if(vcResponse.getResponse_code().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS)){

				vcResponseData = (ResponseData) vcResponse.getData();
				responseBean.setOrgId(vcResponseData.getOrg_cd());
				responseBean.setUserId(vcResponseData.getUsr_cd());
				responseBean.setUsr_status(vcResponseData.getUser_status());
				responseBean.setUsr_state(vcResponseData.getUser_state());
				responseBean.setActivation_link(vcResponseData.getActivation_link());
				responseBean.setUserSecStatus(vcResponseData.getUser_sec_status());
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
			}
			else
			{
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_OMNI_PREFIX+vcResponse.getResponse_code());
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
