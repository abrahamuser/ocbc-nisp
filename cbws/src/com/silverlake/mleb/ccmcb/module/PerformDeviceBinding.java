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
import com.silverlake.mleb.mcb.bean.ObDeviceBindingResponse;
import com.silverlake.mleb.mcb.bean.ObDeviceCifBean;
import com.silverlake.mleb.mcb.bean.ObHeaderResponse;
import com.silverlake.mleb.mcb.bean.ObRequest;
import com.silverlake.mleb.mcb.bean.ObResponse;
import com.silverlake.mleb.mcb.bean.ObUserDetail;
import com.silverlake.mleb.mcb.constant.MiBConstant;



public class PerformDeviceBinding extends MiBServices
{

	
	
	public PerformDeviceBinding(WebServiceContext session) {
		super(session);
		// TODO Auto-generated constructor stub
	}


	public static PropertiesManager property = new PropertiesManager();
	UserLoginSession userloginsession = new UserLoginSession();
	SecurityUtil secUtil = new SecurityUtil();
	private static Logger log = LogManager.getLogger(PerformDeviceBinding.class);
	ObAuthenticationRequest obRequest;
	private String loginID;
	
	@Override
	public ObRequest getBDObject() {
		func_id = MiBConstant.FUNC_MCB_DVBINDING;
		return obRequest;
	}
	
	
	
	public void processData(String tac, String index)
	{
		//get resend tag count from session 		
		ObAuthenticationResponse authResponse =  (ObAuthenticationResponse) httpSession.getAttribute(WSConstant.MIB_RESEND_TAG_DEV_TAC);
		ObDeviceBindingResponse deviceList = (ObDeviceBindingResponse) httpSession.getAttribute(WSConstant.MIB_HTTP_SESSION_DEVICE_PROFILE_LISTING);
		obRequest = new ObAuthenticationRequest();
		if(index!=null && index.trim().length()>0 )
		{
			
 
			String untagDeviceId = null;
			if(null!=deviceList && deviceList.getDeviceListing()!=null)
			{
				for(ObDeviceCifBean temp:deviceList.getDeviceListing())
				{
					if(temp.getIndex().equalsIgnoreCase(index))
					{
	 
						untagDeviceId = temp.getDevId();
					}
				}
			}
		
			
			if(untagDeviceId!=null)
			{
				obRequest.setUntagDeviceID(untagDeviceId);
			}
			else
			{
				obResponse = new ObResponse();
				obResponse.setObHeader(new ObHeaderResponse());
				obResponse.getObHeader().setStatusCode(WSConstant.CCWS_INVALID_INPUT_ERROR);
			}
		}
		
		
		obRequest.setUserContext(userloginsession.getSessionKey(httpSession));
		obRequest.setObUser(userloginsession.getLoginSessionKeyData(httpSession));
		obRequest.setResendTagCount(authResponse==null?0:authResponse.getResendTagCount()); 
		obRequest.setTokenReqId(authResponse==null?null:authResponse.getTokenReqId());
		obRequest.setAuthorizationCode(tac);
	}



	@Override
	public void processResponse() {
		
		
		 
		if(obResponse.getObHeader().getStatusCode().equalsIgnoreCase(MiBConstant.MIB_COMMON_SUCCESS))
		{

			httpSession.removeAttribute(SecurityUtil.PRELOGIN_SESSION_TAG);
		}
		else if (obResponse.getObHeader().getStatusCode().equalsIgnoreCase(MiBConstant.MIB_OMNI_PREFIX+"S0016"))
		{
			 
			//userloginsession.removeSessionUser(httpSession);
			//userloginsession.removeUser(obRequest.getUserContext().getLoginId());
			httpSession.invalidate();
		}
	}
	
}
