package com.silverlake.mleb.ccmcb.module;

import javax.xml.ws.WebServiceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.silverlake.mleb.ccmcb.util.PropertiesManager;
import com.silverlake.mleb.ccmcb.util.SecurityUtil;
import com.silverlake.mleb.ccmcb.util.UserLoginSession;
import com.silverlake.mleb.mcb.bean.ObAuthenticationRequest;
import com.silverlake.mleb.mcb.bean.ObChangePasswordRequest;
import com.silverlake.mleb.mcb.bean.ObRequest;
import com.silverlake.mleb.mcb.constant.MiBConstant;

public class PerformChangePassword extends MiBServices
{

	public PerformChangePassword(WebServiceContext session)
	{
		super(session);
		// TODO Auto-generated constructor stub
	}

	public static PropertiesManager property = new PropertiesManager();
	UserLoginSession userloginsession = new UserLoginSession();
	SecurityUtil secUtil = new SecurityUtil();
	private static Logger log = LogManager.getLogger(PerformChangePassword.class);
	ObAuthenticationRequest obRequest;

	@Override
	public ObRequest getBDObject()
	{
		func_id = MiBConstant.FUNC_MCB_CHANGE_PASSWORD;
		return obRequest;
	}

	public void processData(String randomID, String pString, String cString, String passwordDataBlock)
	{
		// set request to bd
		obRequest = new ObAuthenticationRequest();
		obRequest.setUserContext(userloginsession.getSessionKey(httpSession));
		obRequest.setObUser(userloginsession.getLoginSessionKeyData(httpSession));
		obRequest.setRandomNumber(randomID);
		obRequest.setpString(pString);
		obRequest.setcString(cString);
		obRequest.setPasswordDataBlock(passwordDataBlock);
	}

	@Override
	public void processResponse()
	{
	}
}
