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
import com.silverlake.mleb.mcb.bean.vctransaction.ObVcTranxRequest;
import com.silverlake.mleb.mcb.constant.MiBConstant;



public class RetrieveFutureTranx extends MiBServices
{

	
	
	public RetrieveFutureTranx(WebServiceContext session) {
		super(session);
		// TODO Auto-generated constructor stub
	}


	public static PropertiesManager property = new PropertiesManager();
	UserLoginSession userloginsession = new UserLoginSession();
	SecurityUtil secUtil = new SecurityUtil();
	private static Logger log = LogManager.getLogger(RetrieveFutureTranx.class);
	ObVcTranxRequest obRequest;
	private String loginID;
	
	@Override
	public ObRequest getBDObject() {
		func_id = MiBConstant.FUNC_MCB_RETRIEVE_FUTURE_TRANX;
		return obRequest;
	}
	
	
	
	public void processData(String nDays,String pageNo)
	{
		obRequest = new ObVcTranxRequest();
		ObUserContext userContext = userloginsession.getSessionKey(httpSession);
		userContext = userContext == null ? new ObUserContext(): userContext;
		obRequest.setUserContext(userContext);
		obRequest.setObUser(userloginsession.getLoginSessionKeyData(httpSession));
		obRequest.setNdays(nDays);
		obRequest.setPageNo(pageNo);
	}



	@Override
	public void processResponse() {
		
		
	}
	
}
