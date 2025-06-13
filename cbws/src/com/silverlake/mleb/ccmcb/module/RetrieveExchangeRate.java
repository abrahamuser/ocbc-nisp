package com.silverlake.mleb.ccmcb.module;

import javax.xml.ws.WebServiceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.silverlake.mleb.ccmcb.constant.WSConstant;
import com.silverlake.mleb.ccmcb.util.PropertiesManager;
import com.silverlake.mleb.ccmcb.util.SecurityUtil;
import com.silverlake.mleb.ccmcb.util.UserLoginSession;
import com.silverlake.mleb.mcb.bean.ObRetrieveExchangeRateRequest;
import com.silverlake.mleb.mcb.constant.MiBConstant;

public class RetrieveExchangeRate extends MiBServices
{

	public RetrieveExchangeRate(WebServiceContext session)
	{
		super(session);
		// TODO Auto-generated constructor stub
	}

	public static PropertiesManager property = new PropertiesManager();
	UserLoginSession userloginsession = new UserLoginSession();
	SecurityUtil secUtil = new SecurityUtil();
	private static Logger log = LogManager.getLogger(RetrieveExchangeRate.class);
	ObRetrieveExchangeRateRequest obRequest;

	@Override
	public ObRetrieveExchangeRateRequest getBDObject()
	{
		func_id = MiBConstant.FUNC_MCB_EXCHANGE_RATE;
		return obRequest;
	}

	public void processData(String ccy_code, String period_from, String period_to)
	{
		// set request to bd
		obRequest = new ObRetrieveExchangeRateRequest();
		obRequest.setUserContext(userloginsession.getSessionKey(httpSession));
		obRequest.setObUser(userloginsession.getLoginSessionKeyData(httpSession));
		obRequest.setCcy_code(ccy_code);
		obRequest.setPeriod_from(period_from);
		obRequest.setPeriod_to(period_to);
 
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
