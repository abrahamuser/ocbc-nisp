package com.silverlake.mleb.ccmcb.module;

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
import com.silverlake.mleb.mcb.bean.ObHeaderResponse;
import com.silverlake.mleb.mcb.bean.ObRequest;
import com.silverlake.mleb.mcb.bean.ObResponse;
import com.silverlake.mleb.mcb.constant.MiBConstant;

public class RetrieveAccountHist extends MiBServices
{

	public RetrieveAccountHist(WebServiceContext session)
	{
		super(session);
		// TODO Auto-generated constructor stub
	}

	public static PropertiesManager property = new PropertiesManager();
	UserLoginSession userloginsession = new UserLoginSession();
	SecurityUtil secUtil = new SecurityUtil();
	private static Logger log = LogManager.getLogger(RetrieveAccountHist.class);
	ObAccountOverviewRequest obRequest;

	@Override
	public ObRequest getBDObject()
	{
		func_id = MiBConstant.FUNC_MCB_ACC_HIST;
		return obRequest;
	}

	public void processData(String index,String noDays, String fromDate, String toDate, String pageNo)
	{
		Object ssResp =  httpSession.getAttribute(WSConstant.MIB_ACCOUNT_OVERVIEW);
		List<ObCorporateAccountOverview> ssAccountList = null;
		if(ssResp instanceof ObAccountOverviewResponse) 
		{
			ObAccountOverviewResponse ssRespTemp = (ObAccountOverviewResponse) ssResp;
			ssAccountList = ssRespTemp.getAccountList();
		}
		else if(ssResp instanceof ObDashboardInfoResponse)
		{
			ObDashboardInfoResponse ssRespTemp = (ObDashboardInfoResponse) ssResp;
			ssAccountList = ssRespTemp.getAccountList();
		}
		
		
		 
		if(ssAccountList!=null)
		{
			for(ObCorporateAccountOverview temp:ssAccountList)
			{
				if(temp.getIndex().equalsIgnoreCase(index))
				{
					obRequest = new ObAccountOverviewRequest();
					obRequest.setUserContext(userloginsession.getSessionKey(httpSession));
					obRequest.setObUser(userloginsession.getLoginSessionKeyData(httpSession));
					obRequest.setAccountNo(temp.getAcct_no());
					obRequest.setAccountCcy(temp.getAcct_ccy());
					obRequest.setPageNo(pageNo);
					obRequest.setFromDate(fromDate);
					obRequest.setToDate(toDate);
					obRequest.setNoDays(noDays);
					httpSession.setAttribute("helpFrontEndSetAccountDetailsIndex", index);
					return;
				}
			}
			
			
		}
		
		
		log.info("acctNo index not match :"+index);
		obResponse = new ObResponse();
		obResponse.setObHeader(new ObHeaderResponse());
		obResponse.getObHeader().setStatusCode(WSConstant.CCWS_COMMON_ERROR);
		return;
		
	
 
	}

	@Override
	public void processResponse()
	{
		if(obResponse instanceof ObAccountOverviewResponse )
		{
			ObAccountOverviewResponse  response = (ObAccountOverviewResponse) obResponse;
			 
			 if(response.getAccountList()!=null && response.getAccountList().size()>0)
			 {
 
				 response.getAccountList().get(0).setIndex(String.valueOf(httpSession.getAttribute("helpFrontEndSetAccountDetailsIndex")));
			 }

		}
	}

}
