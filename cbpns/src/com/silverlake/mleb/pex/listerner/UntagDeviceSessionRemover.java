package com.silverlake.mleb.pex.listerner;


import java.util.Hashtable;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silverlake.hlb.mib.bean.ObRequest;
import com.silverlake.hlb.mib.constant.MiBConstant;
import com.silverlake.hlb.mib.entity.HlbDeviceProfile;
import com.silverlake.micb.core.bean.DeviceBean;
import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.mleb.pex.entity.dao.MLEBPExDAO;

@Service("UntagDeviceSessionRemover")
public class UntagDeviceSessionRemover {
	
	
	private static Logger log = LogManager.getLogger(UntagDeviceSessionRemover.class);
	
	@Autowired
	MLEBPExDAO dao;
	

	

	public String onCall(MICBRequestBean arg0) throws Exception {
		// TODO Auto-generated method stub
		MICBRequestBean micbReq = new MICBRequestBean();
		try
		{
			
		
			
			micbReq = (MICBRequestBean) arg0;
		
			ObRequest obRsp = (ObRequest) micbReq.getBDObject();
			
			if(null!=obRsp.getObUser() && null!=obRsp.getObUser().getLoginId() && !micbReq.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_PERFORM_DEVICETAGGING) &&  !micbReq.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_LOGOUT) )
			{
				log.info("Login Session Checking  ::::::: "+obRsp.getObUser().getLoginId());
				
				DeviceBean dvBean = micbReq.getDeviceBean();
				
				String sql = "FROM HlbDeviceProfile WHERE device_id = :deviceID ";
				
				Hashtable params = new Hashtable();
				params.put("deviceID", dvBean.getDeviceId());
				
				
				if(dvBean.getDeviceId().indexOf("-IMAC-") > 0)
				{
					String deviceIdCon = dvBean.getDeviceId().substring(0, dvBean.getDeviceId().indexOf("-IMAC-"));
					sql = sql + " OR deviceId = :deviceIDConc";
					params.put("deviceIDConc", deviceIdCon);
				}


				List<HlbDeviceProfile> hlbDeviceProfiles = dao.selectQueryParam(sql, params);

				if(hlbDeviceProfiles != null && hlbDeviceProfiles.size() == 1)
				{
					HlbDeviceProfile deviceTag = hlbDeviceProfiles.get(0);
					if(null==deviceTag || !deviceTag.getStatus().equalsIgnoreCase(MiBConstant.MIB_DEVICEPROFILE_STATUS_ACTIVE) || !deviceTag.getHlbCustomerProfile().getCif().equalsIgnoreCase(obRsp.getObUser().getCifNumber()))
					{
						return MiBConstant.MIB_STATUSCODE_SUCCESS_UNTAGGED_CURRENT_DEVICE;
					}
				}
				else
				{
					return MiBConstant.MIB_STATUSCODE_SUCCESS_UNTAGGED_CURRENT_DEVICE;
				}
				
				
				/*HlbDeviceProfile deviceTag = (HlbDeviceProfile) dao.findByID(HlbDeviceProfile.class, dvBean.getDeviceId());
				
				
				if(null==deviceTag || !deviceTag.getStatus().equalsIgnoreCase(MiBConstant.MIB_DEVICEPROFILE_STATUS_ACTIVE)  || !deviceTag.getHlbCustomerProfile().getCif().equalsIgnoreCase(obRsp.getObUser().getCifNumber()))
				{
					return MiBConstant.MIB_STATUSCODE_SUCCESS_UNTAGGED_CURRENT_DEVICE;
				}*/
				
				
			}
			
		
		}
		catch(Exception ex)
		{
			log.error(this.getClass().getCanonicalName(), ex);
		}
		
		
		return null;
	}	
	


}
