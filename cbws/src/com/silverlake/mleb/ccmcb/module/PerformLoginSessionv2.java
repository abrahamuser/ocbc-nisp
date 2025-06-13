package com.silverlake.mleb.ccmcb.module;



import java.util.Date;

import javax.xml.ws.WebServiceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.silverlake.mleb.ccmcb.constant.WSConstant;
import com.silverlake.mleb.ccmcb.util.PropertiesManager;
import com.silverlake.mleb.ccmcb.util.SecurityUtil;
import com.silverlake.mleb.ccmcb.util.UserLoginSession;
import com.silverlake.mleb.mcb.bean.ObAuthenticationRequest;
import com.silverlake.mleb.mcb.bean.ObAuthenticationResponse;
import com.silverlake.mleb.mcb.bean.ObHeaderResponse;
import com.silverlake.mleb.mcb.bean.ObRequest;
import com.silverlake.mleb.mcb.bean.ObResponse;
import com.silverlake.mleb.mcb.bean.ObUserContext;
import com.silverlake.mleb.mcb.bean.ObUserDetail;
import com.silverlake.mleb.mcb.constant.MiBConstant;



public class PerformLoginSessionv2 extends MiBServices
{

	

	public PerformLoginSessionv2(WebServiceContext session) {
		super(session);
		// TODO Auto-generated constructor stub
	}


	public static PropertiesManager property = new PropertiesManager();
	UserLoginSession userloginsession = new UserLoginSession();
	SecurityUtil secUtil = new SecurityUtil();
	private static Logger log = LogManager.getLogger(PerformLoginSessionv2.class);
	ObAuthenticationRequest obRequest;
	private String loginID;
	
	@Override
	public ObRequest getBDObject() {
		func_id = MiBConstant.FUNC_MCB_LOGIN_V2;
		return obRequest;
	}
	
	
	
	public void processData(String orgId, String ursId, String pString, String cString, String random_number, String authType, String passwordDataBlock, String ip)
	{
		 
			obRequest = new ObAuthenticationRequest();
		
			
			ObAuthenticationResponse ssResponse = (ObAuthenticationResponse) httpSession.getAttribute(WSConstant.MIB_HTTP_SESSION_CUST_PROFILE);
		
			log.info("SSRESPONSE FROM SESSION TOKEN :: " + ssResponse);
			//if( ssResponse !=null && ssResponse.getNonce()!=null)
			if( ssResponse !=null )
			{
				obRequest.setUserContext(new ObUserContext());
				obRequest.setNonce(ssResponse.getNonce());
				obRequest.setLoginType(authType);
				obRequest.setpString(pString);
				obRequest.setcString(cString);
				obRequest.setRandomNumber(random_number);
				obRequest.setPasswordDataBlock(passwordDataBlock);
				obRequest.setObUser(new ObUserDetail());
				obRequest.getObUser().setUserId(ursId.trim());
				obRequest.getObUser().setLoginId(ursId.trim());
				obRequest.getObUser().setOrgId(orgId.trim());
				obRequest.setIp(ip);
			}
			else
			{
				obResponse = new ObResponse();
				obResponse.setObHeader(new ObHeaderResponse());
				obResponse.getObHeader().setStatusCode(WSConstant.CCWS_INVALID_PRE_SESSION);
			}
			
			
	}


	
	

	@Override
	public void processResponse() {
		
		
		
		
		if(obResponse.getObHeader().getStatusCode().equalsIgnoreCase(MiBConstant.MIB_COMMON_SUCCESS) || 
				obResponse.getObHeader().getStatusCode().equalsIgnoreCase(MiBConstant.MCB_UNTAGGED_DEVICE) || 
				obResponse.getObHeader().getStatusCode().equalsIgnoreCase(MiBConstant.MCB_UNTAGGED_DEVICE_AND_EXCEED_LIMIT) )
		{
			ObAuthenticationResponse authenticationResponse = (ObAuthenticationResponse) obResponse;
			
			if(obResponse.getObHeader().getStatusCode().equalsIgnoreCase(MiBConstant.MCB_UNTAGGED_DEVICE) ||
					obResponse.getObHeader().getStatusCode().equalsIgnoreCase(MiBConstant.MCB_UNTAGGED_DEVICE_AND_EXCEED_LIMIT))
			{
				httpSession.setAttribute(SecurityUtil.PRELOGIN_SESSION_TAG, obResponse.getUserContext().getLoginId());
			}
			
			//log.info("Login Session ID ::::::::i::::::::::::::::::::: "+obResponse.getUserContext().getLoginId());
			if(null!=authenticationResponse.getDescriptions())
			{
				httpSession.setAttribute(SecurityUtil.PRETNC_SESSION_TAG, obResponse.getUserContext().getLoginId());
			}
			
			httpSession.setAttribute(SecurityUtil.USER_SUCCESSFUL_LOGIN_TIME, new Date());
			
	
			String forceLogin = property.getProperty("security.force.login");
			ObUserContext sObUserCtx = new ObUserContext();
			sObUserCtx.setLoginId(obResponse.getObUser().getOrgId()+obResponse.getUserContext().getLoginId());
			sObUserCtx.setSessionId(obResponse.getUserContext().getSessionId());
			sObUserCtx.setSecToken(obResponse.getUserContext().getSecToken());
			
			ObUserDetail obuser = new ObUserDetail();
			obuser.setCifNumber( obResponse.getObUser().getCifNumber());
			obuser.setLoginId( obResponse.getObUser().getLoginId());
			obuser.setFullName(obResponse.getObUser().getFullName());
			obuser.setOrgId(obResponse.getObUser().getOrgId());
			obuser.setUserId(obResponse.getObUser().getUserId());
			obuser.setTncFlag(obResponse.getObUser().isTncFlag());
			obuser.setAccessId(obResponse.getObUser().getAccessId());
			obuser.setUsr_status(obResponse.getObUser().getUsr_status());
			obuser.setUsrName(obResponse.getObUser().getUsrName());
			obuser.setOrgName(obResponse.getObUser().getOrgName());
			obuser.setAddress1(obResponse.getObUser().getAddress1());
			obuser.setAddress2(obResponse.getObUser().getAddress2());
			obuser.setAddress3(obResponse.getObUser().getAddress3());
			obuser.setMobileNumber(obResponse.getObUser().getMobileNumber());
			obuser.setEmail(obResponse.getObUser().getEmail());
			obuser.setIsEligiblePurchaseForeignCcy(obResponse.getObUser().getIsEligiblePurchaseForeignCcy());
			
			boolean addUserSession = userloginsession.addSessionUser(httpSession, sObUserCtx, obuser);
			
			
			if(null!=forceLogin && forceLogin.equalsIgnoreCase("true") && !addUserSession)
			{
				
				addUserSession = userloginsession.addSessionUser_replace(httpSession,sObUserCtx,obuser);
				
				
				if(!addUserSession)
				{
					obResponse.getObHeader().setStatusCode(SecurityUtil.SECURITY_ERROR_INVALID_LOGIN_ACTION);
					log.info("Login Session Failed :- ["+loginID+"] - ["+obResponse.getObHeader().getStatusCode());
				}
				
				
			}
			else if(!addUserSession)
			{
				obResponse.getObHeader().setStatusCode(SecurityUtil.SECURITY_ERROR_SESSION_USER_EXISTED);
				log.info("Login Session Failed : ["+loginID+"] - ["+obResponse.getObHeader().getStatusCode());
			}
			
			obResponse.getUserContext().setSessionId("");
			obResponse.getUserContext().setSecToken("");
			obResponse.getUserContext().setLoginId("");
			obResponse.getObUser().setCifNumber("");
			obResponse.getObUser().setAccessId("");
			//obResponse.getObUser().setLoginId("");
			
			httpSession.setMaxInactiveInterval(authenticationResponse.getSessionDuration());
			ObUserContext test = userloginsession.getSessionKey(httpSession);
			//log.info("Login Session ID ::::::::x::::::::::::::::::::: "+test.getLoginId());
			
		}
	}
	
}
