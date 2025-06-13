package com.silverlake.mleb.ccmcb.module;



import javax.xml.ws.WebServiceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.silverlake.mleb.ccmcb.constant.WSConstant;
import com.silverlake.mleb.ccmcb.util.PropertiesManager;
import com.silverlake.mleb.ccmcb.util.SecurityUtil;
import com.silverlake.mleb.ccmcb.util.UserLoginSession;
import com.silverlake.mleb.mcb.bean.ObAuthenticationRequest;
import com.silverlake.mleb.mcb.bean.ObHeaderResponse;
import com.silverlake.mleb.mcb.bean.ObRequest;
import com.silverlake.mleb.mcb.bean.ObResponse;
import com.silverlake.mleb.mcb.constant.MiBConstant;



public class PerformDeviceUnBindOpen extends MiBServices
{

	
	
	public PerformDeviceUnBindOpen(WebServiceContext session) {
		super(session);
		// TODO Auto-generated constructor stub
	}


	public static PropertiesManager property = new PropertiesManager();
	UserLoginSession userloginsession = new UserLoginSession();
	SecurityUtil secUtil = new SecurityUtil();
	private static Logger log = LogManager.getLogger(PerformDeviceUnBindOpen.class);
	ObAuthenticationRequest obRequest;
 
	
	@Override
	public ObRequest getBDObject() {
		func_id = MiBConstant.FUNC_MCB_DVUNBIND_OPEN;
		return obRequest;
	}
	
	
	
	public void processData(String pString,String cString, String random_number, String approvalUnbindFlag, String passwordDataBlock)
	{
		 
		obRequest =  new ObAuthenticationRequest();
		if( pString == null || cString==null || random_number==null)
		{
			obResponse = new ObResponse();
			obResponse.setObHeader(new ObHeaderResponse());
			obResponse.getObHeader().setStatusCode(WSConstant.CCWS_INVALID_INPUT_ERROR);
		}
		else
		{
			obRequest.setpString(pString);
			obRequest.setcString(cString);
			obRequest.setRandomNumber(random_number);
			obRequest.setApprovalUnbindFlag(approvalUnbindFlag);
			obRequest.setPasswordDataBlock(passwordDataBlock);
		}
		
		
		
		obRequest.setUserContext(userloginsession.getSessionKey(httpSession));
		obRequest.setObUser(userloginsession.getLoginSessionKeyData(httpSession));
	 
	}



	@Override
	public void processResponse() {
		
		if(obResponse.getObHeader().getStatusCode().equalsIgnoreCase(MiBConstant.MIB_COMMON_SUCCESS))
		{
			httpSession.removeAttribute(WSConstant.MIB_HTTP_SESSION_DEVICE_PROFILE_LISTING);
		}
	}
	
}
