package com.silverlake.mleb.ccmcb.module;

import javax.xml.ws.WebServiceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.silverlake.mleb.ccmcb.constant.WSConstant;
import com.silverlake.mleb.ccmcb.util.PropertiesManager;
import com.silverlake.mleb.ccmcb.util.SecurityUtil;
import com.silverlake.mleb.ccmcb.util.UserLoginSession;
import com.silverlake.mleb.mcb.bean.ObRequest;
import com.silverlake.mleb.mcb.bean.ObTokenRequest;
import com.silverlake.mleb.mcb.constant.MiBConstant;

public class SwitchDefaultActiveTokenRequest extends MiBServices
{
	public SwitchDefaultActiveTokenRequest(WebServiceContext session)
	{
		super(session);
		// TODO Auto-generated constructor stub
	}

	public static PropertiesManager property = new PropertiesManager();
	UserLoginSession userloginsession = new UserLoginSession();
	SecurityUtil secUtil = new SecurityUtil();
	private static Logger log = LogManager.getLogger(RetrieveHolidayList.class);
	ObTokenRequest obRequest;

	@Override
	public ObRequest getBDObject()
	{
		func_id = MiBConstant.FUNC_MCB_SWITCH_DEFAULT_TOKEN;
		return obRequest;
	}

	public void processData(String token_type_old, String token_type_new)
	{
		// set request to bd
		obRequest = new ObTokenRequest();
		obRequest.setUserContext(userloginsession.getSessionKey(httpSession));
		obRequest.setObUser(userloginsession.getLoginSessionKeyData(httpSession));
		
		obRequest.setToken_type_new(token_type_new);
		obRequest.setToken_type_old(token_type_old);
 
	}

	@Override
	public void processResponse()
	{
		// set attribute for account detail use index get object
		if (obResponse.getObHeader().getStatusCode()
				.equalsIgnoreCase(MiBConstant.MIB_COMMON_SUCCESS))
		{
			httpSession.setAttribute(WSConstant.MIB_SWITCH_DEF_ACTIVE_TOKEN, obResponse);
		}
	}

	
}
