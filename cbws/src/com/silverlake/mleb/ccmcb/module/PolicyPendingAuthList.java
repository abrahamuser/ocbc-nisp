package com.silverlake.mleb.ccmcb.module;


import javax.xml.ws.WebServiceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.silverlake.mleb.ccmcb.constant.WSConstant;
import com.silverlake.mleb.ccmcb.util.PropertiesManager;
import com.silverlake.mleb.ccmcb.util.SecurityUtil;
import com.silverlake.mleb.ccmcb.util.UserLoginSession;
import com.silverlake.mleb.mcb.bean.ObProfileMaintRequest;
import com.silverlake.mleb.mcb.bean.ObRequest;
import com.silverlake.mleb.mcb.constant.MiBConstant;

public class PolicyPendingAuthList extends MiBServices
{

	public PolicyPendingAuthList(WebServiceContext session)
	{
		super(session);
		// TODO Auto-generated constructor stub
	}

	public static PropertiesManager property = new PropertiesManager();
	UserLoginSession userloginsession = new UserLoginSession();
	SecurityUtil secUtil = new SecurityUtil();
	private static Logger log = LogManager.getLogger(PolicyPendingAuthList.class);
	ObProfileMaintRequest obRequest;

	@Override
	public ObRequest getBDObject()
	{
		func_id = MiBConstant.FUNC_MCB_POLICY_AUTH_LIST;//MiBConstant.FUNC_MCB_TIMEDPS_ACCTDETAILS;
		return obRequest;
	}

	public void processData(String pageNo, String pageSize)
	{
		
		obRequest = new ObProfileMaintRequest();
		 
		
		obRequest.setPage_no(pageNo);
		obRequest.setPage_size(pageSize);
		
		obRequest.setUserContext(userloginsession.getSessionKey(httpSession));
		obRequest.setObUser(userloginsession.getLoginSessionKeyData(httpSession));
		return;
		
	
 
	}

	@Override
	public void processResponse()
	{	
		
		if (obResponse.getObHeader().getStatusCode()
				.equalsIgnoreCase(MiBConstant.MIB_COMMON_SUCCESS))
		{
	 
			httpSession.setAttribute(WSConstant.MIB_HTTP_SESSION_SUBSCRIBER_AUTH, obResponse);
		}
	}

}
