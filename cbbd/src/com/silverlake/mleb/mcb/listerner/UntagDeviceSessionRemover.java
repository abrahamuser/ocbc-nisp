package com.silverlake.mleb.mcb.listerner;


import java.util.Hashtable;
import java.util.List;

import org.apache.logging.log4j.Logger;import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silverlake.micb.core.bean.DeviceBean;
import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.mleb.mcb.bean.ObAuthenticationRequest;
import com.silverlake.mleb.mcb.bean.ObRequest;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.entity.dao.MLEBMIBDAO;
import com.silverlake.mleb.mcb.util.PropertiesManager;

import com.silverlake.mleb.mcb.entity.DeviceProfile;



@Service("UntagDeviceSessionRemover")
public class UntagDeviceSessionRemover {
	
	
	private static Logger log = LogManager.getLogger(UntagDeviceSessionRemover.class);
	private PropertiesManager pro = new PropertiesManager();
	@Autowired
	MLEBMIBDAO dao;

	public String onCall(MICBRequestBean arg0,ObRequest obRsp, String[] ingoreFunction ) throws Exception {
		// TODO Auto-generated method stub
		MICBRequestBean micbReq = new MICBRequestBean();
		try
		{
			/*if(null!=ingoreFunction)
			{
				for(String temp:ingoreFunction)
				{
					if(temp.equalsIgnoreCase(""))
					{
						return null;
					}
				}
			}*/
		
			
			micbReq = (MICBRequestBean) arg0;
		
			//ObRequest obRsp = (ObRequest) micbReq.getBDObject();
			String checkDeviceBindingFlag = pro.getProperty(MiBConstant.DEVICE_BINDING_FLAG);
			boolean checkDevice = null==checkDeviceBindingFlag?false:(checkDeviceBindingFlag.equalsIgnoreCase("true")?true:false);
			
			
			
			log.info("Check Unbind Interceptor");
			if(checkDevice && null!=obRsp.getObUser() && null!=obRsp.getObUser().getLoginId() && null!=obRsp.getObUser().getCifNumber() && !micbReq.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_DVBINDING) && !micbReq.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_DVBINDING_V2) && !micbReq.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_DEVICETAC) && !micbReq.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_DVENQUIRY) && !micbReq.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_SECTOKEN) &&   !micbReq.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_LOGOUT) && !micbReq.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_DVBINDING_UPDATE.getCode()))
			{
				//log.info("Login Session Checking  Untag ::::::: "+obRsp.getObUser().getLoginId());
				
				DeviceBean dvBean = micbReq.getDeviceBean();
				DeviceBean preDefineDvBean = new DeviceBean(); 
				//::::::::::::::::::::::::::: CHECK -IMAC- ::::::::::::::::::::::::::::::::::::::
				
				String deviceId = dvBean.getDeviceId();
				String sql = "FROM "+DeviceProfile.class.getSimpleName()+" WHERE deviceId = :deviceIDFull ";

				Hashtable params = new Hashtable();
				params.put("deviceIDFull", deviceId);

				

				List<DeviceProfile> deviceProfiles = dao.selectQueryParam(sql, params);
				
				log.info("Check device profile : "+deviceProfiles.size());
				if(deviceProfiles != null && deviceProfiles.size() == 1)
				{
					DeviceProfile deviceTag = deviceProfiles.get(0);
					if(null==deviceTag || (!deviceTag.getStatus().equalsIgnoreCase(MiBConstant.MIB_DEVICEPROFILE_STATUS_ACTIVE) && !deviceTag.getStatus().equalsIgnoreCase(MiBConstant.MIB_DEVICEPROFILE_STATUS_PENDING)) || !deviceTag.getCif().equalsIgnoreCase(obRsp.getObUser().getCifNumber()))
					{
						return MiBConstant.MIB_STATUSCODE_SUCCESS_UNTAGGED_CURRENT_DEVICE;
					}
					
				}
				else
				{
					return MiBConstant.MIB_STATUSCODE_SUCCESS_UNTAGGED_CURRENT_DEVICE;
				}
				
				
				
			}
			
		
		}
		catch(Exception ex)
		{
			log.info(this.getClass().getCanonicalName(), ex);
		}
		
		
		return null;
	}	
	


}
