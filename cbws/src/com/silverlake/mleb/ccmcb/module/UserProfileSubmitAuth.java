package com.silverlake.mleb.ccmcb.module;

import javax.xml.ws.WebServiceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.silverlake.mleb.ccmcb.constant.WSConstant;
import com.silverlake.mleb.ccmcb.util.PropertiesManager;
import com.silverlake.mleb.ccmcb.util.SecurityUtil;
import com.silverlake.mleb.ccmcb.util.UserLoginSession;
import com.silverlake.mleb.mcb.bean.ObProfileMaintRequest;
import com.silverlake.mleb.mcb.bean.ObProfileMaintResponse;
import com.silverlake.mleb.mcb.bean.ObRequest;
import com.silverlake.mleb.mcb.constant.MiBConstant;

public class UserProfileSubmitAuth extends MiBServices
{

	public UserProfileSubmitAuth(WebServiceContext session)
	{
		super(session);
		// TODO Auto-generated constructor stub
	}

	public static PropertiesManager property = new PropertiesManager();
	UserLoginSession userloginsession = new UserLoginSession();
	SecurityUtil secUtil = new SecurityUtil();
	private static Logger log = LogManager.getLogger(UserProfileSubmitAuth.class);
	ObProfileMaintRequest obRequest;

	@Override
	public ObRequest getBDObject()
	{
		func_id = MiBConstant.FUNC_MCB_USRPRF_AUTH_SUBMIT;//MiBConstant.FUNC_MCB_TIMEDPS_ACCTDETAILS;
		return obRequest;
	}

	public void processData(String action,String recordIdList)
	{
		
		obRequest = new ObProfileMaintRequest();
		 
		obRequest.setRecordIds(recordIdList);
		obRequest.setAction_cd(action);
		
		ObProfileMaintResponse objSession = (ObProfileMaintResponse) httpSession.getAttribute(WSConstant.MIB_HTTP_SESSION_SUBSCRIBER_AUTH);
		if(null!=objSession)
		{
			obRequest.setUser_profile_list(objSession.getUser_profile_list());
		}
		
		
		obRequest.setUserContext(userloginsession.getSessionKey(httpSession));
		obRequest.setObUser(userloginsession.getLoginSessionKeyData(httpSession));
		return;
		
	
 
	}

	@Override
	public void processResponse()
	{	
		
		 
	}

}
