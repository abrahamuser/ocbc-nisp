package com.silverlake.mleb.ccmcb.module;



import javax.xml.ws.WebServiceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.silverlake.mleb.ccmcb.util.PropertiesManager;
import com.silverlake.mleb.ccmcb.util.SecurityUtil;
import com.silverlake.mleb.ccmcb.util.UserLoginSession;
import com.silverlake.mleb.mcb.bean.ObAuthenticationRequest;
import com.silverlake.mleb.mcb.bean.ObRequest;
import com.silverlake.mleb.mcb.bean.ObTaskListRequest;
import com.silverlake.mleb.mcb.bean.ObUserContext;
import com.silverlake.mleb.mcb.constant.MiBConstant;



public class RetrieveTransactionSummary extends MiBServices
{

	
	
	public RetrieveTransactionSummary(WebServiceContext session) {
		super(session);
		// TODO Auto-generated constructor stub
	}


	public static PropertiesManager property = new PropertiesManager();
	UserLoginSession userloginsession = new UserLoginSession();
	SecurityUtil secUtil = new SecurityUtil();
	private static Logger log = LogManager.getLogger(RetrieveTransactionSummary.class);
	ObTaskListRequest obRequest;
	private String loginID;
	
	@Override
	public ObRequest getBDObject() {
		func_id = MiBConstant.FUNC_MCB_TRANX_SUM;
		return obRequest;
	}
	
	
	
	public void processData(String trnxSource, String masterId, String trxStatus)
	{

		obRequest = new ObTaskListRequest();
		ObUserContext userContext = userloginsession.getSessionKey(httpSession);
		userContext = userContext == null ? new ObUserContext(): userContext;
		obRequest.setUserContext(userContext);
		obRequest.setObUser(userloginsession.getLoginSessionKeyData(httpSession));
		obRequest.setTranxSourcelist(trnxSource);
		obRequest.setPymt_master_id(masterId);
		obRequest.setTrxStatus(trxStatus);
	}



	@Override
	public void processResponse() {
		
		
	}
	
}
