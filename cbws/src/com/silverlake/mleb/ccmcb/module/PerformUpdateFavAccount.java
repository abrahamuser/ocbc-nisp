package com.silverlake.mleb.ccmcb.module;

import java.util.ArrayList;
import java.util.List;

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
import com.silverlake.mleb.mcb.bean.ObDashboardInfoResponse;
import com.silverlake.mleb.mcb.bean.ObFavAccountRequest;
import com.silverlake.mleb.mcb.bean.ObHeaderResponse;
import com.silverlake.mleb.mcb.bean.ObRequest;
import com.silverlake.mleb.mcb.bean.ObResponse;
import com.silverlake.mleb.mcb.constant.MiBConstant;

public class PerformUpdateFavAccount extends MiBServices
{

	public PerformUpdateFavAccount(WebServiceContext session)
	{
		super(session);
		// TODO Auto-generated constructor stub
	}

	public static PropertiesManager property = new PropertiesManager();
	UserLoginSession userloginsession = new UserLoginSession();
	SecurityUtil secUtil = new SecurityUtil();
	private static Logger log = LogManager.getLogger(PerformUpdateFavAccount.class);
	ObFavAccountRequest obRequest;

	@Override
	public ObRequest getBDObject()
	{
		func_id = MiBConstant.FUNC_MCB_UPDATE_FAV;
		return obRequest;
	}

	public void processData(String favIndexList, String fav_prd_cd)
	{
		 
		obRequest = new ObFavAccountRequest();
		obRequest.setUserContext(userloginsession.getSessionKey(httpSession));
		obRequest.setObUser(userloginsession.getLoginSessionKeyData(httpSession));
		
		Object ssData = httpSession.getAttribute(WSConstant.MIB_ACCOUNT_OVERVIEW);
		ObDashboardInfoResponse ssResponse = (ObDashboardInfoResponse) ssData;
		if(favIndexList!=null)
		{
			String indexSplit[] = favIndexList.split(",");
			
			obRequest.setNewFavAccountList(new ArrayList());
			for(String tmp:indexSplit)
			{
				for(ObCorporateAccountOverview tmpAcct:ssResponse.getAccountList())
				{
					if(tmp.trim().equalsIgnoreCase(tmpAcct.getIndex()))
					{
						obRequest.getNewFavAccountList().add(tmpAcct);
					}
				}

			}
			
			obRequest.setOldFavAccountList(getOldFavAcct(ssResponse));
			obRequest.setPrd_cd(fav_prd_cd);
			
			
		}
		else
		{
			obResponse = new ObResponse();
			obResponse.setObHeader(new ObHeaderResponse());
			obResponse.getObHeader().setStatusCode(WSConstant.CCWS_INVALID_INPUT_ERROR);
		}
		
		
		
 
 
	}

	@Override
	public void processResponse()
	{
	 
		if (obResponse.getObHeader().getStatusCode()
				.equalsIgnoreCase(MiBConstant.MIB_COMMON_SUCCESS))
		{
			
			 
			
		}
		
		
		
	}
	
	
	public List<ObCorporateAccountOverview> getOldFavAcct(ObDashboardInfoResponse ssResponse)
	{
		
		List<ObCorporateAccountOverview>  existingFav = new ArrayList();
		
		for(ObCorporateAccountOverview tmp:ssResponse.getAccountList())
		{
			
			if(tmp.getIndex().startsWith("fav"))
			{
				existingFav.add(tmp);
			}
			
		}
		
		return existingFav;
	}
	 
	
	

}
