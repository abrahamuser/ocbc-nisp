package com.silverlake.mleb.ccmcb.module.ext;



import javax.xml.ws.WebServiceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.silverlake.mleb.ccmcb.module.MiBServices;
import com.silverlake.mleb.ccmcb.util.PropertiesManager;
import com.silverlake.mleb.mcb.bean.ObExtHeader;
import com.silverlake.mleb.mcb.bean.ObRequest;
import com.silverlake.mleb.mcb.bean.ObSysConfRequest;
import com.silverlake.mleb.mcb.bean.ObUserContext;
import com.silverlake.mleb.mcb.constant.MiBConstant;


public class SysConfUpdateAck extends MiBServices
{


	public SysConfUpdateAck(WebServiceContext session) {
		super(session);
		// TODO Auto-generated constructor stub
	}


	public static PropertiesManager property = new PropertiesManager();

	private static Logger log = LogManager.getLogger(SysConfUpdateAck.class);
	ObSysConfRequest obRequest;
	
	@Override
	public ObRequest getBDObject() {
		func_id = MiBConstant.FUNC_EXT_SYSUPDATEACK;
		return obRequest;
	}
	
	
	
	public void processData(ObExtHeader extHeader, String maxDevice, String sessionExpiration)
	{
		obRequest = new ObSysConfRequest();
		obRequest.setObExtHeader(extHeader);
		obRequest.setUserContext(new ObUserContext());
		obRequest.setMaxDevBinding(maxDevice);
		obRequest.setSessionExpiration(sessionExpiration);
	
	}


	@Override
	public void processResponse() 
	{
		
	}
	
}
