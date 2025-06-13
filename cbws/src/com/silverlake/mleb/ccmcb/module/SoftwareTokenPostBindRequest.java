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

public class SoftwareTokenPostBindRequest extends MiBServices
{
	public SoftwareTokenPostBindRequest(WebServiceContext session)
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
		func_id = MiBConstant.FUNC_MCB_SOFT_TOKEN_POSTBIND;
		return obRequest;
	}

	public void processData(String device_finger_print, String otip_type,
			String appli_name, Integer appli_no, Integer seq_no)
	{
		// set request to bd
		obRequest = new ObTokenRequest();
		obRequest.setUserContext(userloginsession.getSessionKey(httpSession));
		obRequest.setObUser(userloginsession.getLoginSessionKeyData(httpSession));
		
		obRequest.setDevice_finger_print(device_finger_print);
		obRequest.setOtip_type(otip_type);
		obRequest.setAppli_name(appli_name);
		obRequest.setAppli_no(appli_no);
		obRequest.setSeq_no(seq_no);
 
	}

	@Override
	public void processResponse()
	{
		// set attribute for account detail use index get object
		if (obResponse.getObHeader().getStatusCode()
				.equalsIgnoreCase(MiBConstant.MIB_COMMON_SUCCESS))
		{
			httpSession.setAttribute(WSConstant.MIB_SOFT_TOKEN_POSTBIND, obResponse);
		}
	}

	
}
