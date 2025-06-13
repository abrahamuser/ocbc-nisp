package com.silverlake.mleb.ccmcb.module;



import javax.xml.ws.WebServiceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.silverlake.mleb.ccmcb.constant.WSConstant;
import com.silverlake.mleb.ccmcb.util.PropertiesManager;
import com.silverlake.mleb.ccmcb.util.SecurityUtil;
import com.silverlake.mleb.ccmcb.util.UserLoginSession;
import com.silverlake.mleb.mcb.bean.ObAuthenticationRequest;
import com.silverlake.mleb.mcb.bean.ObAuthenticationResponse;
import com.silverlake.mleb.mcb.bean.ObRequest;
import com.silverlake.mleb.mcb.bean.ObUserContext;
import com.silverlake.mleb.mcb.constant.MiBConstant;


public class RetrieveSecToken extends MiBServices
{


	public RetrieveSecToken(WebServiceContext session) {
		super(session);
		// TODO Auto-generated constructor stub
	}


	public static PropertiesManager property = new PropertiesManager();
	UserLoginSession userloginsession = new UserLoginSession();
	SecurityUtil secUtil = new SecurityUtil();
	private static Logger log = LogManager.getLogger(RetrieveSecToken.class);
	ObAuthenticationRequest obRequest;
	private String loginID;
	
	@Override
	public ObRequest getBDObject() {
		func_id = MiBConstant.FUNC_MCB_SECTOKEN;
		return obRequest;
	}
 
	
	
	
	public void processData(String primaryActionFlag)
	{
		
		httpSession.removeAttribute(WSConstant.MIB_HTTP_SESSION_CUST_PROFILE);
		
		this.loginID = loginID;
		obRequest = new ObAuthenticationRequest();
		obRequest.setUserContext(new ObUserContext());
		obRequest.setPrimaryActionFlag(primaryActionFlag);

	}



	@Override
	public void processResponse() {
		

		if(obResponse.getObHeader().getStatusCode().equalsIgnoreCase(MiBConstant.MIB_COMMON_SUCCESS))
		{
			httpSession.setAttribute(WSConstant.MIB_HTTP_SESSION_CUST_PROFILE, obResponse);
			//Encrypt the sensitive data by using device id as key
			if(obResponse != null && obResponse instanceof ObAuthenticationResponse && deviceBean != null && deviceBean.getDeviceId() != null && !deviceBean.getDeviceId().isEmpty()){
				String key = deviceBean.getDeviceId().substring(deviceBean.getDeviceId().length()-8, deviceBean.getDeviceId().length());
				ObAuthenticationResponse responseBean = (ObAuthenticationResponse) obResponse;
				responseBean.setOrgId(encryptString(key, responseBean.getOrgId()));
				responseBean.setUserId(encryptString(key, responseBean.getUserId()));
				responseBean.setLoginID(encryptString(key, responseBean.getLoginID()));
			}
		}
	}
	
}
