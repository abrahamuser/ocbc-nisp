package com.silverlake.mleb.ccmcb.module;



import javax.xml.ws.WebServiceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.silverlake.mleb.ccmcb.constant.WSConstant;
import com.silverlake.mleb.ccmcb.util.PropertiesManager;
import com.silverlake.mleb.ccmcb.util.SecurityUtil;
import com.silverlake.mleb.ccmcb.util.UserLoginSession;
import com.silverlake.mleb.mcb.bean.ObAuthenticationRequest;
import com.silverlake.mleb.mcb.bean.ObDeviceBindingRequest;
import com.silverlake.mleb.mcb.bean.ObRequest;
import com.silverlake.mleb.mcb.constant.MiBConstant;



public class RetrieveDeviceProfiles extends MiBServices
{

	
	
	public RetrieveDeviceProfiles(WebServiceContext session) {
		super(session);
		// TODO Auto-generated constructor stub
	}


	public static PropertiesManager property = new PropertiesManager();
	UserLoginSession userloginsession = new UserLoginSession();
	SecurityUtil secUtil = new SecurityUtil();
	private static Logger log = LogManager.getLogger(RetrieveDeviceProfiles.class);
	ObDeviceBindingRequest obRequest;
	private String loginID;
	
	@Override
	public ObRequest getBDObject() {
		func_id = MiBConstant.FUNC_MCB_DVENQUIRY;
		return obRequest;
	}
	
	
	
	public void processData()
	{
	
		
		obRequest = new ObDeviceBindingRequest();
		obRequest.setUserContext(userloginsession.getSessionKey(httpSession));
		obRequest.setObUser(userloginsession.getLoginSessionKeyData(httpSession));
 

	}



	@Override
	public void processResponse() {
		
		
		if(obResponse.getObHeader().getStatusCode().equalsIgnoreCase(MiBConstant.MIB_COMMON_SUCCESS))
		{
			obResponse.getObHeader().setId(getMlebTransactionID());
			httpSession.setAttribute(WSConstant.MIB_HTTP_SESSION_DEVICE_PROFILE_LISTING, obResponse);
		}
	}
	
}
