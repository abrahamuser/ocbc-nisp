package com.silverlake.mleb.ccmcb.module;

import javax.xml.ws.WebServiceContext;

import com.silverlake.mleb.ccmcb.constant.WSConstant;
import com.silverlake.mleb.ccmcb.util.UserLoginSession;
import com.silverlake.mleb.mcb.bean.ObRequest;
import com.silverlake.mleb.mcb.bean.ObTokenRequest;
import com.silverlake.mleb.mcb.bean.ObTokenResponse;
import com.silverlake.mleb.mcb.bean.ObUserDetail;
import com.silverlake.mleb.mcb.constant.MiBConstant;

public class OTPVerify extends MiBServices
{
	public OTPVerify(WebServiceContext session) {
		super(session);
		// TODO Auto-generated constructor stub
	}

	UserLoginSession userloginsession = new UserLoginSession();
	ObTokenRequest obRequest;
	
	@Override
	public ObRequest getBDObject() {
		func_id = MiBConstant.FUNC_MCB_OTP_VERIFY;
		return obRequest;
	}
	
	public void processData(String otp, String requestIdStats)
	{
		//get request_id from session 		
		ObTokenResponse otpResponse =  (ObTokenResponse) httpSession.getAttribute(WSConstant.MIB_OTP_REQ);
		
		obRequest = new ObTokenRequest();
		obRequest.setUserContext(userloginsession.getSessionKey(httpSession));
		obRequest.setObUser(userloginsession.getLoginSessionKeyData(httpSession));
		obRequest.setRequest_id(otpResponse==null?null:otpResponse.getRequest_id());
		obRequest.setOtp(otp);
		if (requestIdStats==null || requestIdStats.isEmpty() ) {
			requestIdStats="true";
		}
		obRequest.setRequestId_stats(requestIdStats);
	}


	@Override
	public void processResponse() {
		
		if(obResponse.getObHeader().getStatusCode().equalsIgnoreCase(MiBConstant.MIB_COMMON_SUCCESS))
		{
			/*String tacAccessID = obResponse.getObUser().getAccessId();*/
			ObUserDetail obUserData = userloginsession.getLoginSessionKeyData(httpSession);
			
			/*obUserData.setAccessId(tacAccessID);*/
			userloginsession.addSessionKeyData(httpSession, obUserData);
			
			httpSession.setAttribute(WSConstant.MIB_OTP_VERIFY, obResponse);
			httpSession.removeAttribute(WSConstant.MIB_OTP_REQ);//Clear the cache once verification successful
		}
	}
	
}
