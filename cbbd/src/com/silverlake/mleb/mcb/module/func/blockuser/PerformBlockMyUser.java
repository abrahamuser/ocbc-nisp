package com.silverlake.mleb.mcb.module.func.blockuser;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.ObBlockMyUserCache;
import com.silverlake.mleb.mcb.bean.ObBlockMyUserRequest;
import com.silverlake.mleb.mcb.bean.ObBlockMyUserResponse;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.entity.dao.DeviceCIFDao;
import com.silverlake.mleb.mcb.module.common.CacheSessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.others.BlockMyUserRequestData;
import com.silverlake.mleb.mcb.module.vc.others.BlockMyUserResponseData;

/**
 * Purpose: To block user
 * Omni Web Services:
 * /user/block_myuser
 *
 * @author Hemanth
 *
 */
@Service
public class PerformBlockMyUser extends CacheSessionFuncServices<ObBlockMyUserRequest, ObBlockMyUserResponse, ObBlockMyUserCache> {
	private static Logger log = LogManager.getLogger(PerformBlockMyUser.class);
	
	@Autowired DeviceCIFDao deviceDao;	
	
    @Override
    public void processInternalWithCache(String locale, String sessionId, String trxId, ObBlockMyUserRequest requestBean, ObBlockMyUserResponse responseBean, ObBlockMyUserCache cacheBean, VCGenericService vcService) throws Exception {
    	
    	BlockMyUserRequestData requestData = new BlockMyUserRequestData();
    	BlockMyUserResponseData responseData;
		processRequest(requestData, requestBean, responseBean);
		VCResponse<BlockMyUserResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.BLOCK_MY_USER, requestData,
				BlockMyUserResponseData.class, true);
		
	
		
		if(!processVCResponseError(vcResponse, responseBean, false)) {
			
		}
    
    }

	private void processRequest(BlockMyUserRequestData requestData, ObBlockMyUserRequest requestBean,
			ObBlockMyUserResponse responseBean) throws Exception {
		
		String deviceOS = requestBean.getObDevice() == null ? null : requestBean.getObDevice().getOs();
		String deviceType = requestBean.getObDevice() == null ? null : requestBean.getObDevice().getModel();
		String type = requestBean.getObDevice() == null ? null : requestBean.getObDevice().getType();
		
		requestData.setOrg_cd(requestBean.getOrg_cd());
		requestData.setUsr_cd(requestBean.getUsr_cd());
		requestData.setDevice_type(deviceType);
		requestData.setDevice_os(type+" "+deviceOS);
		requestData.setEmail(requestBean.getEmail());
		requestData.setPhone(requestBean.getPhone());

	}
	
	
}
