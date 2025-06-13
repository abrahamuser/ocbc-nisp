package com.silverlake.mleb.ccmcb.module;

import javax.xml.ws.WebServiceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.silverlake.mleb.ccmcb.constant.WSConstant;
import com.silverlake.mleb.ccmcb.util.PropertiesManager;
import com.silverlake.mleb.ccmcb.util.SecurityUtil;
import com.silverlake.mleb.ccmcb.util.UserLoginSession;
import com.silverlake.mleb.mcb.bean.ObRetrieveNotificationInboxRequest;
import com.silverlake.mleb.mcb.constant.MiBConstant;

public class RetrievePushNotificationInbox extends MiBServices
{

	public RetrievePushNotificationInbox(WebServiceContext session)
	{
		super(session);
		// TODO Auto-generated constructor stub
	}

	public static PropertiesManager property = new PropertiesManager();
	UserLoginSession userloginsession = new UserLoginSession();
	SecurityUtil secUtil = new SecurityUtil();
	private static Logger log = LogManager.getLogger(RetrievePushNotificationInbox.class);
	ObRetrieveNotificationInboxRequest obRequest;

	@Override
	public ObRetrieveNotificationInboxRequest getBDObject()
	{
		func_id = MiBConstant.FUNC_MCB_PUSH_NOTIFICATION_INBOX;
		return obRequest;
	}

	public void processData(String page_no, String pageSize)
	{
		// set request to bd
		obRequest = new ObRetrieveNotificationInboxRequest();
		obRequest.setUserContext(userloginsession.getSessionKey(httpSession));
		obRequest.setObUser(userloginsession.getLoginSessionKeyData(httpSession));
		obRequest.setPage_no(page_no);
		obRequest.setPage_size(pageSize);
 
	}

	@Override
	public void processResponse()
	{
		// set attribute for account detail use index get object
		if (obResponse.getObHeader().getStatusCode()
				.equalsIgnoreCase(MiBConstant.MIB_COMMON_SUCCESS))
		{
			//httpSession.setAttribute(WSConstant.MIB_ACCOUNT_OVERVIEW, obResponse);
		}
	}

}
