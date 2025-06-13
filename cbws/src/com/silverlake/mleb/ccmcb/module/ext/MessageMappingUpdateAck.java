package com.silverlake.mleb.ccmcb.module.ext;



import javax.xml.ws.WebServiceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.silverlake.mleb.ccmcb.module.MiBServices;
import com.silverlake.mleb.ccmcb.util.PropertiesManager;
import com.silverlake.mleb.mcb.bean.ObExtHeader;
import com.silverlake.mleb.mcb.bean.ObMessageMappingBean;
import com.silverlake.mleb.mcb.bean.ObMessageMappingRequest;
import com.silverlake.mleb.mcb.bean.ObRequest;
import com.silverlake.mleb.mcb.bean.ObUserContext;
import com.silverlake.mleb.mcb.constant.MiBConstant;


public class MessageMappingUpdateAck extends MiBServices
{


	public MessageMappingUpdateAck(WebServiceContext session) {
		super(session);
		// TODO Auto-generated constructor stub
	}


	public static PropertiesManager property = new PropertiesManager();

	private static Logger log = LogManager.getLogger(MessageMappingUpdateAck.class);
	ObMessageMappingRequest obRequest;
	
	@Override
	public ObRequest getBDObject() {
		func_id = MiBConstant.FUNC_EXT_MSGMAPACK;
		return obRequest;
	}
	
	
	
	public void processData(ObExtHeader extHeader, String action, String recordId, String errorCode, String errorMessage, String locale )
	{
		obRequest = new ObMessageMappingRequest();
		obRequest.setObExtHeader(extHeader);
		obRequest.setUserContext(new ObUserContext());
		obRequest.setMsgMapping(new ObMessageMappingBean());
		obRequest.setAction(action);
		obRequest.getMsgMapping().setRecordID(recordId);
		obRequest.getMsgMapping().setErrorCode(errorCode);
		obRequest.getMsgMapping().setErrorMsg(errorMessage);
		obRequest.getMsgMapping().setLocale(locale);
	}


	@Override
	public void processResponse() 
	{
		
	}
	
}
