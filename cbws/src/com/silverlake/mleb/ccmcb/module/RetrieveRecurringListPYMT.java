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
import com.silverlake.mleb.mcb.bean.ObRecurringRequest;
import com.silverlake.mleb.mcb.bean.ObRequest;
import com.silverlake.mleb.mcb.constant.MiBConstant;

public class RetrieveRecurringListPYMT extends MiBServices
{

	public RetrieveRecurringListPYMT(WebServiceContext session)
	{
		super(session);
		// TODO Auto-generated constructor stub
	}

	public static PropertiesManager property = new PropertiesManager();
	UserLoginSession userloginsession = new UserLoginSession();
	SecurityUtil secUtil = new SecurityUtil();
	private static Logger log = LogManager.getLogger(RetrieveRecurringListPYMT.class);
	ObRecurringRequest obRequest;

	@Override
	public ObRequest getBDObject()
	{
		func_id = MiBConstant.FUNC_MCB_RECURRING_PYMT;
		return obRequest;
	}

	public void processData(String pageNo, String billerCode, String amount, String debit_acct_no,String billingId, String showNewest, String recurringId)
	{
		// set request to bd
		obRequest = new ObRecurringRequest();
		obRequest.setBillerCode(billerCode);
		obRequest.setAmount(amount);
		obRequest.setDebit_acct_no(debit_acct_no);
		obRequest.setBillingId(billingId);
		obRequest.setRecurringType("PYMT");
		obRequest.setPage_no(pageNo);
		obRequest.setShow_newest(showNewest);
		obRequest.setRecurring_id(recurringId);
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
			
			 
			httpSession.setAttribute(WSConstant.MIB_RECURRING_TRX, obResponse);
		}
		
		
		
	}
	 
	
	

}
