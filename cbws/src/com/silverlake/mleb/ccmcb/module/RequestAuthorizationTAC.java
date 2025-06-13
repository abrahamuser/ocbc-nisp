package com.silverlake.mleb.ccmcb.module;



import javax.xml.ws.WebServiceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.silverlake.mleb.ccmcb.constant.WSConstant;
import com.silverlake.mleb.ccmcb.util.PropertiesManager;
import com.silverlake.mleb.ccmcb.util.SecurityUtil;
import com.silverlake.mleb.ccmcb.util.UserLoginSession;
import com.silverlake.mleb.mcb.bean.ObAuthorizationRequest;
import com.silverlake.mleb.mcb.bean.ObAuthorizationResponse;
import com.silverlake.mleb.mcb.bean.ObRequest;
import com.silverlake.mleb.mcb.bean.ObUserDetail;
import com.silverlake.mleb.mcb.constant.MiBConstant;



public class RequestAuthorizationTAC extends MiBServices
{

	
	
	public RequestAuthorizationTAC(WebServiceContext session) {
		super(session);
		// TODO Auto-generated constructor stub
	}


	public static PropertiesManager property = new PropertiesManager();
	UserLoginSession userloginsession = new UserLoginSession();
	SecurityUtil secUtil = new SecurityUtil();
	private static Logger log = LogManager.getLogger(RequestAuthorizationTAC.class);
	ObAuthorizationRequest obRequest;
	private String loginID;
	
	@Override
	public ObRequest getBDObject() {
		func_id = MiBConstant.FUNC_MCB_AUTHORIZATIONTAC;
		return obRequest;
	}
	
	
	
	public void processData(String request_type)
	{
		//get resend tag count from session 		
		ObAuthorizationResponse authResponse =  (ObAuthorizationResponse) httpSession.getAttribute(WSConstant.MIB_RESEND_TAG_AUTH_TAC);
		
		obRequest = new ObAuthorizationRequest();
		obRequest.setUserContext(userloginsession.getSessionKey(httpSession));
		obRequest.setObUser(userloginsession.getLoginSessionKeyData(httpSession));
		obRequest.setRequest_type(request_type);
		obRequest.setResendTagCount(authResponse==null?0:authResponse.getResendTagCount()); 
		obRequest.setResendTagLastDate(authResponse==null?null:authResponse.getResendTagDateTime());
	}



	@Override
	public void processResponse() {
		
		if(obResponse.getObHeader().getStatusCode().equalsIgnoreCase(MiBConstant.MIB_COMMON_SUCCESS))
		{
			String tacAccessID = obResponse.getObUser().getAccessId();
			ObUserDetail obUserData = userloginsession.getLoginSessionKeyData(httpSession);
			
			obUserData.setAccessId(tacAccessID);
			userloginsession.addSessionKeyData(httpSession, obUserData);
			
			httpSession.setAttribute(WSConstant.MIB_RESEND_TAG_AUTH_TAC, obResponse);
		}
	}
	
}
