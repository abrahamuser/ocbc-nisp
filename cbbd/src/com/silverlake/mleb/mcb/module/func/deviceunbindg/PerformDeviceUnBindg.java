package com.silverlake.mleb.mcb.module.func.deviceunbindg;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.ObDeviceUnbindCache;
import com.silverlake.mleb.mcb.bean.ObDeviceUnbindgRequest;
import com.silverlake.mleb.mcb.bean.ObDeviceUnbindgResponse;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.entity.DeviceProfile;
import com.silverlake.mleb.mcb.entity.GeneralCode;
import com.silverlake.mleb.mcb.entity.UnBindDeviceProfile;
import com.silverlake.mleb.mcb.entity.dao.DeviceCIFDao;
import com.silverlake.mleb.mcb.entity.dao.GeneralCodeDAO;
import com.silverlake.mleb.mcb.init.InitApp;
import com.silverlake.mleb.mcb.module.common.CacheSessionFuncServices;
import com.silverlake.mleb.mcb.module.func.deviceBinding.DeviceCIFUnbindAck;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;

/**
 * Purpose: To unbind the device
 
 * @author Hemanth
 *
 */
@Service
public class PerformDeviceUnBindg extends CacheSessionFuncServices<ObDeviceUnbindgRequest, ObDeviceUnbindgResponse, ObDeviceUnbindCache> {
	private static Logger log = LogManager.getLogger(PerformDeviceUnBindg.class);
	
	@Autowired DeviceCIFDao deviceDao;
	
	@Autowired
	GeneralCodeDAO gnDao;
	
    @Override
    public void processInternalWithCache(String locale, String sessionId, String trxId, ObDeviceUnbindgRequest requestBean, ObDeviceUnbindgResponse responseBean, ObDeviceUnbindCache cacheBean, VCGenericService vcService) throws Exception {
    	
    	
		
			//unbind the device
			String deviceId = requestBean.getDevice_id();
			String cif = requestBean.getCif();
			String ipAddress = requestBean.getObHeader() == null ? null : requestBean.getObHeader().getIp();
			
			DeviceProfile devPro = deviceDao.getCIFByDeviceID(deviceId);
			if(devPro!=null)
			{
				int rsremove = deviceDao.removeProfileByDevAndCif(cif, devPro.getDeviceId());
				if (rsremove == 1)
				{
					 
					UnBindDeviceProfile unbindDev = InitApp.mapper.map(devPro,UnBindDeviceProfile.class);
					unbindDev.setStatus(MiBConstant.MIB_DEVICEPROFILE_STATUS_UNTAGGED);
					unbindDev.setUnBindBy(cif);
					unbindDev.setDateUnBind(new Date());
					unbindDev.setTransId(trxId);
					deviceDao.insertEntity(unbindDev);
					
					GeneralCode primaryGnCode = gnDao.findByGnCode(MiBConstant.GNCODE_PRIMARY_DEV_BINDING);
					if(primaryGnCode != null) {
						String desc = primaryGnCode.getGnCodeDescription();
						if(desc != null && desc.equalsIgnoreCase("true")) {
							if(devPro.getDeviceLevel() != null && devPro.getDeviceLevel().equalsIgnoreCase(MiBConstant.MIB_DEVICEPROFILE_LEVEL_PRIMARY)) {
								List<DeviceProfile> otherBindedDevice = deviceDao.getDeviceByCIFAndStatus(cif);
								//if primary and got secondary dev, mleb will switch secondary over to become Primary
								if(otherBindedDevice != null && otherBindedDevice.size() > 0){
									DeviceCIFUnbindAck deviceUpdate = new DeviceCIFUnbindAck();
									for(DeviceProfile otherDevice:otherBindedDevice) {
										String orgId = requestBean.getObUser() != null? requestBean.getObUser().getOrgId() : null;
										String userId = requestBean.getObUser() != null? requestBean.getObUser().getUserId() : null;
										
										if(deviceDao.isDevicePrimaryDevice(otherDevice.getDeviceId(), otherDevice.getCif()) && otherDevice.getDeviceLevel() != null && otherDevice.getDeviceLevel().equals(MiBConstant.MIB_DEVICEPROFILE_LEVEL_SECONDARY)) {
											//cater for secondary active device
											otherDevice.setDeviceLevel(MiBConstant.MIB_DEVICEPROFILE_LEVEL_PRIMARY);
											deviceDao.updateEntity(otherDevice);
											
											deviceUpdate.sendDeviceInfo(appContext, "U", orgId, userId, 
													otherDevice.getDeviceId(), otherDevice.getOs(), otherDevice.getDeviceType(), otherDevice.getDeviceModel(), 
													otherDevice.getPnsToken(), sessionId, trxId,
													locale, otherDevice.getDeviceLevel(), ipAddress);
											
										} else if (otherBindedDevice.size() == 1 && otherDevice.getDeviceLevel() != null && otherDevice.getDeviceLevel().equals(MiBConstant.MIB_DEVICEPROFILE_LEVEL_SECONDARY) 
												&& otherDevice.getStatus().equals(MiBConstant.MIB_DEVICEPROFILE_STATUS_PENDING)) {
											
											//cater for secondary pending device
											otherDevice.setStatus(MiBConstant.MIB_DEVICEPROFILE_STATUS_ACTIVE);
											otherDevice.setDeviceLevel(MiBConstant.MIB_DEVICEPROFILE_LEVEL_PRIMARY);
											otherDevice.setApprovalAction(MiBConstant.MIB_DEVICEPROFILE_STATUS_SYS_APPROVE);
											otherDevice.setDateBind(new Date());
											deviceDao.updateEntity(otherDevice);
										}
									}
								}
							}
						}
					}
				}
				
				
			}else {
				
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_NOTFOUND);
				
			}
		
    
    }

	
	
	
}
