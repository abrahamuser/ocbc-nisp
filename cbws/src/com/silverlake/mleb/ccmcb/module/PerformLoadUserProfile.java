package com.silverlake.mleb.ccmcb.module;

import javax.xml.ws.WebServiceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.silverlake.mleb.ccmcb.util.PropertiesManager;
import com.silverlake.mleb.ccmcb.util.SecurityUtil;
import com.silverlake.mleb.ccmcb.util.UserLoginSession;
import com.silverlake.mleb.mcb.bean.ObLoadUserProfileRequest;
import com.silverlake.mleb.mcb.bean.ObRequest;
import com.silverlake.mleb.mcb.constant.MiBConstant;

public class PerformLoadUserProfile extends MiBServices
{

	public PerformLoadUserProfile(WebServiceContext session)
	{
		super(session);
		// TODO Auto-generated constructor stub
	}

	public static PropertiesManager property = new PropertiesManager();
	UserLoginSession userloginsession = new UserLoginSession();
	SecurityUtil secUtil = new SecurityUtil();
	private static Logger log = LogManager.getLogger(PerformLoadUserProfile.class);
	ObLoadUserProfileRequest obRequest;

	@Override
	public ObRequest getBDObject()
	{
		func_id = MiBConstant.FUNC_UPM_LOAD_USER_PROFILE;
		return obRequest;
	}

	public void processData()
	{
		// set request to bd
		obRequest = new ObLoadUserProfileRequest();
		obRequest.setUserContext(userloginsession.getSessionKey(httpSession));
		obRequest.setObUser(userloginsession.getLoginSessionKeyData(httpSession));
	}

	@Override
	public void processResponse()
	{
	}
}
