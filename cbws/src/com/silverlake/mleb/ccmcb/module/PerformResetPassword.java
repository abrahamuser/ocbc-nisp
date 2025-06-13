package com.silverlake.mleb.ccmcb.module;

import javax.xml.ws.WebServiceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.silverlake.mleb.ccmcb.util.PropertiesManager;
import com.silverlake.mleb.ccmcb.util.SecurityUtil;
import com.silverlake.mleb.ccmcb.util.UserLoginSession;
import com.silverlake.mleb.mcb.bean.ObAuthenticationRequest;
import com.silverlake.mleb.mcb.bean.ObRequest;
import com.silverlake.mleb.mcb.bean.ObUserDetail;
import com.silverlake.mleb.mcb.constant.MiBConstant;

public class PerformResetPassword extends MiBServices
{

	public PerformResetPassword(WebServiceContext session)
	{
		super(session);
		// TODO Auto-generated constructor stub
	}

	public static PropertiesManager property = new PropertiesManager();
	UserLoginSession userloginsession = new UserLoginSession();
	SecurityUtil secUtil = new SecurityUtil();
	private static Logger log = LogManager.getLogger(PerformResetPassword.class);
	ObAuthenticationRequest obRequest;

	@Override
	public ObRequest getBDObject()
	{
		func_id = MiBConstant.FUNC_MCB_RESET_PASSWORD;
		return obRequest;
	}

	public void processData(String org_cd,String usr_cd,String email, String phone)
	{
		ObUserDetail resetUser = new ObUserDetail();
		resetUser.setUserId(usr_cd);
		resetUser.setOrgId(org_cd);
		// set request to bd
		obRequest = new ObAuthenticationRequest();
		obRequest.setUserContext(userloginsession.getSessionKey(httpSession));
		obRequest.setObUser(resetUser);
		obRequest.setEmail(email);
		obRequest.setPhone(phone);
 
	}

	@Override
	public void processResponse()
	{
	}
}
