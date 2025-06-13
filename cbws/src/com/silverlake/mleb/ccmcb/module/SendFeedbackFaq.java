package com.silverlake.mleb.ccmcb.module;

import javax.xml.ws.WebServiceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.silverlake.mleb.ccmcb.constant.WSConstant;
import com.silverlake.mleb.ccmcb.util.PropertiesManager;
import com.silverlake.mleb.ccmcb.util.SecurityUtil;
import com.silverlake.mleb.ccmcb.util.UserLoginSession;
import com.silverlake.mleb.mcb.bean.ObRequest;
import com.silverlake.mleb.mcb.bean.ObRetrieveFaqRequest;
import com.silverlake.mleb.mcb.bean.ObSendFeedbackFaqRequest;
import com.silverlake.mleb.mcb.constant.MiBConstant;

public class SendFeedbackFaq extends MiBServices
{

	public SendFeedbackFaq(WebServiceContext session)
	{
		super(session);
		// TODO Auto-generated constructor stub
	}

	public static PropertiesManager property = new PropertiesManager();
	UserLoginSession userloginsession = new UserLoginSession();
	SecurityUtil secUtil = new SecurityUtil();
	private static Logger log = LogManager.getLogger(SendFeedbackFaq.class);
	ObSendFeedbackFaqRequest obRequest;

	@Override
	public ObRequest getBDObject()
	{
		func_id = MiBConstant.FUNC_MCB_FAQ_RESPOND;
		return obRequest;
	}

	public void processData(String faq_id, String feedback, String org_cd, String usr_cd)
	{
		// set request to bd
		obRequest = new ObSendFeedbackFaqRequest();
		obRequest.setUserContext(userloginsession.getSessionKey(httpSession));
		obRequest.setObUser(userloginsession.getLoginSessionKeyData(httpSession));
		obRequest.setFaq_id(faq_id);
		obRequest.setFeedback(feedback);
		obRequest.setOrg_cd(org_cd);
		obRequest.setUsr_cd(usr_cd);
 
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
