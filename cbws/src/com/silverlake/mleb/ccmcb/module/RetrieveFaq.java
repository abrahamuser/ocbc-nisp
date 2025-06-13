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
import com.silverlake.mleb.mcb.constant.MiBConstant;

public class RetrieveFaq extends MiBServices
{

	public RetrieveFaq(WebServiceContext session)
	{
		super(session);
		// TODO Auto-generated constructor stub
	}

	public static PropertiesManager property = new PropertiesManager();
	UserLoginSession userloginsession = new UserLoginSession();
	SecurityUtil secUtil = new SecurityUtil();
	private static Logger log = LogManager.getLogger(RetrieveFaq.class);
	ObRetrieveFaqRequest obRequest;

	@Override
	public ObRequest getBDObject()
	{
		func_id = MiBConstant.FUNC_MCB_FAQ_LIST;
		return obRequest;
	}

	public void processData(String faqCatCd)
	{
		// set request to bd
		obRequest = new ObRetrieveFaqRequest();
		obRequest.setUserContext(userloginsession.getSessionKey(httpSession));
		obRequest.setObUser(userloginsession.getLoginSessionKeyData(httpSession));
		obRequest.setFaq_cat_cd(faqCatCd);
 
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
