package com.silverlake.mleb.ccmcb.module;



import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.silverlake.mleb.ccmcb.util.PropertiesManager;
import com.silverlake.mleb.ccmcb.util.SecurityUtil;
import com.silverlake.mleb.ccmcb.util.UserLoginSession;
import com.silverlake.mleb.mcb.bean.ObAppStatRequest;
import com.silverlake.mleb.mcb.bean.ObAuthenticationRequest;
import com.silverlake.mleb.mcb.bean.ObHeaderRequest;
import com.silverlake.mleb.mcb.bean.ObRequest;
import com.silverlake.mleb.mcb.bean.ObUserContext;
import com.silverlake.mleb.mcb.constant.MiBConstant;



public class RetrieveAppStatInfo extends MiBServices
{

	
	
	public RetrieveAppStatInfo(WebServiceContext session) {
		super(session);
		// TODO Auto-generated constructor stub
	}


	public static PropertiesManager property = new PropertiesManager();
	//public static MessageManager msgpro = new MessageManager();
	UserLoginSession userloginsession = new UserLoginSession();
	SecurityUtil secUtil = new SecurityUtil();
	private static Logger log = LogManager.getLogger(RetrieveAppStatInfo.class);
	ObAppStatRequest obRequest;
	private String loginID;
	
	@Override
	public ObRequest getBDObject() {
		func_id = MiBConstant.FUNC_MCB_APP_STAT_INFO;
		return obRequest;
	}
	
	
	
	public void processData(HttpSession session, String reload)
	{
	
		setHttpSession(session);
		obRequest = new ObAppStatRequest();
		obRequest.setObHeader(new ObHeaderRequest());
		obRequest.setUserContext(new ObUserContext());
		
		if(null!=session && null!=session.getAttribute("reloadProperties"))
		{
			obRequest.setAction(session.getAttribute("reloadProperties").toString());
		
		}
		
		if(null!=reload)
		{
			obRequest.setAction(reload);
		}
		

	}



	@Override
	public void processResponse() {
		
		
	}
	
}
