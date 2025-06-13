package com.silverlake.mleb.mcb.module.func.usermanagement;

import java.util.ArrayList;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.common.ObUserProfile;
import com.silverlake.mleb.mcb.bean.usermanagement.ObAdministrationUserRequest;
import com.silverlake.mleb.mcb.bean.usermanagement.ObAdministrationUserResponse;
import com.silverlake.mleb.mcb.bean.usermanagement.ObAdministrationUserSessionCache;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.module.common.CacheSessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.administration.SubscriberProfile;
import com.silverlake.mleb.mcb.module.vc.administration.UserProfileListRequestData;
import com.silverlake.mleb.mcb.module.vc.administration.UserProfileListResponseData;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;

/**
 * Purpose: To get  subscriber profile list used for user maintenance.
 * Omni Web Services:
 * administration/subscriber_profile/list

 *
 * @author Hemanth
 *
 */
@Service
public class AdministrationUserProfileList extends CacheSessionFuncServices<ObAdministrationUserRequest, ObAdministrationUserResponse, ObAdministrationUserSessionCache> {
		
    @Override
    public void processInternalWithCache(String locale, String sessionId, String trxId, ObAdministrationUserRequest requestBean, ObAdministrationUserResponse responseBean, ObAdministrationUserSessionCache cacheBean, VCGenericService vcService) throws Exception {
    	
    	UserProfileListRequestData requestData = new UserProfileListRequestData();
    	UserProfileListResponseData responseData;
		processRequest(requestData, requestBean);
		VCResponse<UserProfileListResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.USER_PROFILE_LIST, 
				requestData, 
				UserProfileListResponseData.class, 
				true);
		
		if(processVCResponseError(vcResponse, responseBean)){
			return;
		}
		
		responseData = vcResponse.getData();
		processResponse(responseData, responseBean);
	
    }

	private void processRequest(UserProfileListRequestData requestData, ObAdministrationUserRequest requestBean) throws Exception{
		
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
				
	}
	
	private void processResponse(UserProfileListResponseData responseData, ObAdministrationUserResponse responseBean)throws Exception {

		responseBean.setProfileList(new ArrayList<ObUserProfile>());

		for (SubscriberProfile profileData : responseData.getProfile_list()) {

			ObUserProfile dataList = new ObUserProfile();

			dataList.setProfileCode(profileData.getProfile_cd());
			dataList.setProfileDescription(profileData.getProfile_desc());
			dataList.setProfileRecordID(profileData.getProfile_record_id());
			dataList.setVersion(profileData.getVersion());

			responseBean.getProfileList().add(dataList);
		}

	}
		

}
