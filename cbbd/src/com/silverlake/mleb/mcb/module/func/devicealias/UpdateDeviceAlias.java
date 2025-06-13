package com.silverlake.mleb.mcb.module.func.devicealias;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.ObUpdateDeviceAliasCache;
import com.silverlake.mleb.mcb.bean.ObUpdateDeviceAliasRequest;
import com.silverlake.mleb.mcb.bean.ObUpdateDeviceAliasResponse;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.entity.DeviceProfile;
import com.silverlake.mleb.mcb.entity.dao.DeviceCIFDao;
import com.silverlake.mleb.mcb.module.common.CacheSessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.others.UpdateDeviceAliasRequestData;
import com.silverlake.mleb.mcb.module.vc.others.UpdateDeviceAliasResponseData;

/**
 * Purpose: To update device alias
 * Omni Web Services:
 * /user/update/device_alias
 *
 * @author Hemanth
 *
 */
@Service
public class UpdateDeviceAlias extends CacheSessionFuncServices<ObUpdateDeviceAliasRequest, ObUpdateDeviceAliasResponse, ObUpdateDeviceAliasCache> {
	private static Logger log = LogManager.getLogger(UpdateDeviceAlias.class);
	
	@Autowired DeviceCIFDao deviceDao;
	
	
    @Override
    public void processInternalWithCache(String locale, String sessionId, String trxId, ObUpdateDeviceAliasRequest requestBean, ObUpdateDeviceAliasResponse responseBean, ObUpdateDeviceAliasCache cacheBean, VCGenericService vcService) throws Exception {
    	
    	UpdateDeviceAliasRequestData requestData = new UpdateDeviceAliasRequestData();
    	UpdateDeviceAliasResponseData responseData;
		processRequest(requestData, requestBean, responseBean);
		VCResponse<UpdateDeviceAliasResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.UPDATE_DEVICE_ALIAS, requestData,
				UpdateDeviceAliasResponseData.class, true);
		
		/*if (processVCResponseError(vcResponse, responseBean)) {
			return;
		}*/
		
		if(!processVCResponseError(vcResponse, responseBean, false)) {
			//Update Device alias in device_profile
			String deviceId = requestBean.getDevice_id();
			DeviceProfile devProfile = deviceDao.getCIFByDeviceID(deviceId);
			if(devProfile != null) {
				devProfile.setAliasName(requestBean.getDevice_alias());
				deviceDao.updateEntity(devProfile);
			}
		}
    
    }

	private void processRequest(UpdateDeviceAliasRequestData requestData, ObUpdateDeviceAliasRequest requestBean,
			ObUpdateDeviceAliasResponse responseBean) throws Exception {
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setDevice_id(requestBean.getDevice_id());
		requestData.setDevice_alias(requestBean.getDevice_alias());

	}
	
	
}
