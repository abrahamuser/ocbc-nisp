package com.silverlake.mleb.ccmcb.module;



import javax.xml.ws.WebServiceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.silverlake.mleb.ccmcb.util.PropertiesManager;
import com.silverlake.mleb.ccmcb.util.SecurityUtil;
import com.silverlake.mleb.ccmcb.util.UserLoginSession;
import com.silverlake.mleb.mcb.bean.ObAuthenticationRequest;
import com.silverlake.mleb.mcb.bean.ObRequest;
import com.silverlake.mleb.mcb.bean.ObUserContext;
import com.silverlake.mleb.mcb.bean.ObUserDetail;
import com.silverlake.mleb.mcb.constant.MiBConstant;



public class PerformUpdateTNC extends MiBServices
{

	
	
	public PerformUpdateTNC(WebServiceContext session) {
		super(session);
		// TODO Auto-generated constructor stub
	}


	public static PropertiesManager property = new PropertiesManager();
	UserLoginSession userloginsession = new UserLoginSession();
	SecurityUtil secUtil = new SecurityUtil();
	private static Logger log = LogManager.getLogger(PerformUpdateTNC.class);
	ObAuthenticationRequest obRequest;
	private String loginID;
	
	@Override
	public ObRequest getBDObject() {
		func_id = MiBConstant.FUNC_MCB_UPDATE_TNC;
		return obRequest;
	}
	
	
	
	public void processData(String tncAction)
	{
		obRequest = new ObAuthenticationRequest();
		ObUserContext userContext = userloginsession.getSessionKey(httpSession);
		userContext = userContext == null ? new ObUserContext(): userContext;
		obRequest.setUserContext(userContext);
		obRequest.setObUser(userloginsession.getLoginSessionKeyData(httpSession));
		obRequest.setTncAction(tncAction);
	}



	@Override
	public void processResponse() {
		
		if(obResponse.getObHeader().getStatusCode().equalsIgnoreCase(MiBConstant.MIB_COMMON_SUCCESS))
		{
			ObUserDetail obuser = userloginsession.getLoginSessionKeyData(httpSession);
			obuser.setTncFlag(obResponse.getObUser().isTncFlag());
		}
	}
	
}
