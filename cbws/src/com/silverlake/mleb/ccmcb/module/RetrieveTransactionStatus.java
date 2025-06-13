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



public class RetrieveTransactionStatus extends MiBServices
{

	
	
	public RetrieveTransactionStatus(WebServiceContext session) {
		super(session);
		// TODO Auto-generated constructor stub
	}


	public static PropertiesManager property = new PropertiesManager();
	UserLoginSession userloginsession = new UserLoginSession();
	SecurityUtil secUtil = new SecurityUtil();
	private static Logger log = LogManager.getLogger(RetrieveTransactionStatus.class);
	ObTaskListRequest obRequest;
	private String loginID;
	
	@Override
	public ObRequest getBDObject() {
		func_id = MiBConstant.FUNC_MCB_TRANX_STATUS;
		return obRequest;
	}
	
	
	
	public void processData(String trnxSource, String masterId )
	{

		obRequest = new ObTaskListRequest();
		ObUserContext userContext = userloginsession.getSessionKey(httpSession);
		userContext = userContext == null ? new ObUserContext(): userContext;
		obRequest.setUserContext(userContext);
		obRequest.setObUser(userloginsession.getLoginSessionKeyData(httpSession));
		obRequest.setTranxSourcelist(trnxSource);
		obRequest.setPymt_master_id(masterId);
	}



	@Override
	public void processResponse() {
		
		
	}
	
}
