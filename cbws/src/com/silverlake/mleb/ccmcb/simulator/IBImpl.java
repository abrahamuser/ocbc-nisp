package com.silverlake.mleb.ccmcb.simulator;



import java.util.UUID;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

import org.apache.logging.log4j.Logger;

import com.silverlake.mleb.ccmcb.util.SecurityUtil;
import com.silverlake.mleb.ccmcb.util.UserLoginSession;

import org.apache.logging.log4j.LogManager;




//@WebService(endpointInterface = "com.silverlake.mleb.ccws.simulator.IBInterface")
public class IBImpl implements  IBInterface
{
	UserLoginSession userloginsession = new UserLoginSession();
	SecurityUtil secUtil = new SecurityUtil();
	private static Logger log = LogManager.getLogger(IBImpl.class);
	
	private static int dataCount = 2;
	
	@Resource
	WebServiceContext wsContext;

	@Override
	public String performIBLogin(String userID, String password) {
		// TODO Auto-generated method stub
		
		try {
			Thread.sleep(1000);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return UUID.randomUUID().toString();
	}

	@Override
	public int getExpirationPeriod() {
		// TODO Auto-generated method stub
		log.debug("EXPIRATION !!!!!!!!!!!!!!!!!!!!!!!");
		
		dataCount++;
		
		return dataCount;
	}



	
}
