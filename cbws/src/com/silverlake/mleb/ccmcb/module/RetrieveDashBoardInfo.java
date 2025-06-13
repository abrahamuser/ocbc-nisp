package com.silverlake.mleb.ccmcb.module;

import java.util.List;

import javax.xml.ws.WebServiceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.silverlake.mleb.ccmcb.constant.WSConstant;
import com.silverlake.mleb.ccmcb.util.MapperUtil;
import com.silverlake.mleb.ccmcb.util.PropertiesManager;
import com.silverlake.mleb.ccmcb.util.SecurityUtil;
import com.silverlake.mleb.ccmcb.util.UserLoginSession;
import com.silverlake.mleb.mcb.bean.ObAccountOverviewRequest;
import com.silverlake.mleb.mcb.bean.ObAccountOverviewResponse;
import com.silverlake.mleb.mcb.bean.ObDashboardInfoRequest;
import com.silverlake.mleb.mcb.bean.ObRequest;
import com.silverlake.mleb.mcb.constant.MiBConstant;

public class RetrieveDashBoardInfo extends MiBServices
{

	public RetrieveDashBoardInfo(WebServiceContext session)
	{
		super(session);
		// TODO Auto-generated constructor stub
	}

	public static PropertiesManager property = new PropertiesManager();
	UserLoginSession userloginsession = new UserLoginSession();
	SecurityUtil secUtil = new SecurityUtil();
	private static Logger log = LogManager.getLogger(RetrieveAccountList.class);
	ObDashboardInfoRequest obRequest;

	@Override
	public ObRequest getBDObject()
	{
		func_id = MiBConstant.FUNC_MCB_DASHBOARD_INFO;
		return obRequest;
	}

	public void processData(String tncAction)
	{
		 
		obRequest = new ObDashboardInfoRequest();
		obRequest.setUserContext(userloginsession.getSessionKey(httpSession));
		obRequest.setObUser(userloginsession.getLoginSessionKeyData(httpSession));
		obRequest.setTncAction(tncAction);
	}

	@Override
	public void processResponse()
	{
		 
		if (obResponse.getObHeader().getStatusCode()
				.equalsIgnoreCase(MiBConstant.MIB_COMMON_SUCCESS))
		{
	 
			httpSession.setAttribute(WSConstant.MIB_ACCOUNT_OVERVIEW, obResponse);
		}
	}

}
