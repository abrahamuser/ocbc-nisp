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
 * Purpose: Verify challenge response
 * Omni Web Services:
 * token/cr/verify
 * 
 * @author Alex Loo
 *
 */
@Service
public class VerifyChallengeResponse extends SessionFuncServices<ObAuthenticationRequest, ObAuthenticationResponse>{
	
	@Override
	public void processInternal(String locale, String sessionId, String trxId, ObAuthenticationRequest requestBean, ObAuthenticationResponse responseBean, VCGenericService vcService) throws Exception {
		
	}
}
