package com.silverlake.mleb.mcb.module.func;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.ObAuthenticationRequest;
import com.silverlake.mleb.mcb.bean.ObAuthenticationResponse;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.module.common.SessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.user.RequestData;
import com.silverlake.mleb.mcb.module.vc.user.ResponseData;

/**
 * Purpose: Verify the password
 * 
 * Omni Web Services:
 * user/verify_pwd
 * 
 * @author Alex Loo
 */
@Service
public class PerformVerifyPassword extends SessionFuncServices<ObAuthenticationRequest, ObAuthenticationResponse>{
	private static Logger log = LogManager.getLogger(PerformVerifyPassword.class);
	
	@Override
	public void processInternal(String locale, String sessionId, String trxId, ObAuthenticationRequest requestBean, ObAuthenticationResponse responseBean, VCGenericService vcService) throws Exception {
		RequestData requestData = new RequestData();
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setParam_c(requestBean.getcString());
		requestData.setParam_p(requestBean.getpString());
		requestData.setRandom_number(requestBean.getRandomNumber());
		requestData.setFailctr("N");//VELO2UAT-475 - hardcode for biometric verify password
		requestData.setPassword_data_block(requestBean.getPasswordDataBlock());
		
		VCResponse<com.silverlake.mleb.mcb.module.vc.user.ResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.USER_VERIFY_PWD,
				requestData,
				ResponseData.class,
				true);
		
		processVCResponseError(vcResponse, responseBean, false);
	}
}
