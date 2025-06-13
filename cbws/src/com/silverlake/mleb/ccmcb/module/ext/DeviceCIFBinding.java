package com.silverlake.mleb.ccmcb.module.ext;



import javax.xml.ws.WebServiceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.silverlake.mleb.ccmcb.module.MiBServices;
import com.silverlake.mleb.ccmcb.util.PropertiesManager;
import com.silverlake.mleb.mcb.bean.ObDeviceBindingRequest;
import com.silverlake.mleb.mcb.bean.ObExtHeader;
import com.silverlake.mleb.mcb.bean.ObRequest;
import com.silverlake.mleb.mcb.bean.ObUserContext;
import com.silverlake.mleb.mcb.constant.MiBConstant;


public class DeviceCIFBinding extends MiBServices
{


	public DeviceCIFBinding(WebServiceContext session) {
		super(session);
		// TODO Auto-generated constructor stub
	}


	public static PropertiesManager property = new PropertiesManager();

	private static Logger log = LogManager.getLogger(DeviceCIFBinding.class);
	ObDeviceBindingRequest obRequest;
	
	@Override
	public ObRequest getBDObject() {
		func_id = MiBConstant.FUNC_DVBINDING;
		return obRequest;
	}
	
	
	
	public void processData(ObExtHeader extHeader, String cif, String deviceId,
			String deviceModel, String deviceType, String deviceOs,
			String deviceIsRooted)
	{
		obRequest = new ObDeviceBindingRequest();
		obRequest.setObExtHeader(extHeader);

		obRequest.setUserContext(new ObUserContext());
		obRequest.setCif(cif);
		obRequest.setDevId(deviceId);
		obRequest.setDevIsRooted(deviceIsRooted);
		obRequest.setDevModel(deviceModel);
		obRequest.setDevOs(deviceOs);
		obRequest.setDevType(deviceType);
	}


	@Override
	public void processResponse() 
	{
		
	}
	
}
