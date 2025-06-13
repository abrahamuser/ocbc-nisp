package com.silverlake.mleb.mcb.module.func.token;

import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.ObAuthenticationRequest;
import com.silverlake.mleb.mcb.bean.ObAuthenticationResponse;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.module.common.SessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.token.TokenRequestData;
import com.silverlake.mleb.mcb.module.vc.token.TokenResponseData;

/**
 * Purpose: Request response code to customer which will use to do CR verify later.  
 * Omni Web Services:
 * token/cr/request
 * 
 * @author Alex Loo
 *
 */
@Service
public class RequestChallengeResponse extends SessionFuncServices<ObAuthenticationRequest, ObAuthenticationResponse>{
	
	@Override
	public void processInternal(String locale, String sessionId, String trxId, ObAuthenticationRequest requestBean, ObAuthenticationResponse responseBean, VCGenericService vcService) throws Exception {
		TokenRequestData requestData = new TokenRequestData();
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setDevice_id(requestBean.getObDevice().getDeviceId());
		requestData.setRequest_type(requestBean.getIdType());
		VCResponse<TokenResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.TOKEN_CR_REQUEST, 
				requestData, 
				TokenResponseData.class, 
				true);
		if(processVCResponseError(vcResponse, responseBean)){
			return;
		}
		TokenResponseData responseData = vcResponse.getData();
		responseBean.setTokenReqId(responseData.getRequest_id());
		responseBean.setChallengeCode(responseData.getChallenge_code());
	}
}
