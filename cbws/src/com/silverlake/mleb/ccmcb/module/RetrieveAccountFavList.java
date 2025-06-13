package com.silverlake.mleb.ccmcb.module;

import javax.xml.ws.WebServiceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.silverlake.mleb.ccmcb.constant.WSConstant;
import com.silverlake.mleb.ccmcb.util.PropertiesManager;
import com.silverlake.mleb.ccmcb.util.SecurityUtil;
import com.silverlake.mleb.ccmcb.util.UserLoginSession;
import com.silverlake.mleb.mcb.bean.ObAccountOverviewRequest;
import com.silverlake.mleb.mcb.bean.ObAccountOverviewResponse;
import com.silverlake.mleb.mcb.bean.ObCorporateAccountOverview;
import com.silverlake.mleb.mcb.bean.ObDashboardInfoRequest;
import com.silverlake.mleb.mcb.bean.ObDashboardInfoResponse;
import com.silverlake.mleb.mcb.bean.ObRequest;
import com.silverlake.mleb.mcb.constant.MiBConstant;

public class RetrieveAccountFavList extends MiBServices
{

	public RetrieveAccountFavList(WebServiceContext session)
	{
		super(session);
		// TODO Auto-generated constructor stub
	}

	public static PropertiesManager property = new PropertiesManager();
	UserLoginSession userloginsession = new UserLoginSession();
	SecurityUtil secUtil = new SecurityUtil();
	private static Logger log = LogManager.getLogger(RetrieveAccountFavList.class);
	ObDashboardInfoRequest obRequest;

	@Override
	public ObRequest getBDObject()
	{
		func_id = MiBConstant.FUNC_MCB_RETRIEVE_FAV;
		return obRequest;
	}

	public void processData( )
	{
		// set request to bd
		obRequest = new ObDashboardInfoRequest();
		obRequest.setUserContext(userloginsession.getSessionKey(httpSession));
		obRequest.setObUser(userloginsession.getLoginSessionKeyData(httpSession));
		 
 
	}

	@Override
	public void processResponse()
	{
		// set attribute for account detail use index get object
		if (obResponse.getObHeader().getStatusCode()
				.equalsIgnoreCase(MiBConstant.MIB_COMMON_SUCCESS))
		{
			
			Object ssData = httpSession.getAttribute(WSConstant.MIB_ACCOUNT_OVERVIEW);
			ObDashboardInfoResponse ssResponse = (ObDashboardInfoResponse) ssData;
			ObDashboardInfoResponse responseData = (ObDashboardInfoResponse) obResponse;
			ssResponse = updateAccount(ssResponse,responseData);
			httpSession.setAttribute(WSConstant.MIB_ACCOUNT_OVERVIEW, ssResponse);
		}
		
		
		
	}
	
	public ObDashboardInfoResponse updateAccount(ObDashboardInfoResponse session,ObDashboardInfoResponse responseData )
	{
		
		
		for(ObCorporateAccountOverview data:responseData.getAccountList())
		{
			boolean addAcct = true;
			
			for(ObCorporateAccountOverview tmp:session.getAccountList())
			{
				
				if(tmp.getIndex().equalsIgnoreCase(data.getIndex()))
				{
					
					tmp = data;
					
					addAcct = false;
					break;
				}
				
			}
			
			if(addAcct)
			{
				session.getAccountList().add(data);
			}
			
			
		}
		
		
		
		
		return session;
	}
	
	

}
