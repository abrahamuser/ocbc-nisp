package com.silverlake.mleb.mcb.module.func.token;

import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.ObTokenRequest;
import com.silverlake.mleb.mcb.bean.ObTokenResponse;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.module.common.SessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.token.TokenRequestData;
import com.silverlake.mleb.mcb.module.vc.token.TokenResponseData;

/**
 * Purpose: used to delete software token
 * Omni Web Services:
 * token/sw/delete
 * 
 * @author AdamZalil
 *
 */
@Service
public class SoftwareTokenDelete extends SessionFuncServices<ObTokenRequest, ObTokenResponse>{
	
	@Override
	public void processInternal(String locale, String sessionId, String trxId, ObTokenRequest requestBean, ObTokenResponse responseBean, VCGenericService vcService) throws Exception {
		TokenRequestData requestData = new TokenRequestData();
		
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setDevice_id(requestBean.getObDevice().getDeviceId());
		requestData.setDevice_finger_print(requestBean.getDevice_finger_print());
		
		VCResponse<TokenResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.TOKEN_DELETE, 
				requestData, 
				TokenResponseData.class, 
				true);
		if(processVCResponseError(vcResponse, responseBean)){
			return;
		}
		TokenResponseData responseData = vcResponse.getData();
	}
}

