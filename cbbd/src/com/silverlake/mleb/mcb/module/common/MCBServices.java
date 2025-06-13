package com.silverlake.mleb.mcb.module.common;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.entity.DeviceProfile;
import com.silverlake.mleb.mcb.entity.UnBindDeviceProfile;
import com.silverlake.mleb.mcb.entity.dao.DeviceCIFDao;
import com.silverlake.mleb.mcb.init.InitApp;
import com.silverlake.mleb.mcb.util.PropertiesManager;


public class MCBServices
{
	private static Logger log = LogManager.getLogger(MCBServices.class);
	private PropertiesManager pro = new PropertiesManager();
	 
 
	
	public DeviceProfile unbindDevice(DeviceCIFDao custDao,String cif,String deviceID, String trxId)
	{
		DeviceProfile devPro = custDao.getCIFByDeviceID(deviceID);
		if(devPro!=null )
		{
			int rsremove = custDao.removeProfileByDevAndCif(cif, devPro.getDeviceId());
			if (rsremove == 1)
			{
				 
				UnBindDeviceProfile unbindDev = InitApp.mapper.map(devPro,UnBindDeviceProfile.class);
				unbindDev.setStatus(MiBConstant.MIB_DEVICEPROFILE_STATUS_UNTAGGED);
				unbindDev.setUnBindBy(cif);
				unbindDev.setDateUnBind(new Date());
				unbindDev.setTransId(trxId);
				custDao.insertEntity(unbindDev);
				 
			}
			
			return devPro;
		}
		return null;
	}
}
