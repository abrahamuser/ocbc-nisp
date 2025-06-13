package com.silverlake.mleb.mcb.module.func.usermanagement;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.RoleList;
import com.silverlake.mleb.mcb.bean.common.ObUserRoleBean;
import com.silverlake.mleb.mcb.bean.usermanagement.ObAdministrationUserRequest;
import com.silverlake.mleb.mcb.bean.usermanagement.ObAdministrationUserResponse;
import com.silverlake.mleb.mcb.bean.usermanagement.ObAdministrationUserSessionCache;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.module.common.CacheSessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.administration.UserData;
import com.silverlake.mleb.mcb.module.vc.administration.UserManagementCreateModifyRequestData;
import com.silverlake.mleb.mcb.module.vc.administration.UserManagementCreateModifyResponseData;
import com.silverlake.mleb.mcb.module.vc.administration.UserRoleListResponseData;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.user.List_role;

/**
 * Purpose:  To Add New or Modify a user of an Organization
 * Omni Web Services:
 * administration/user/create_modify
 *
 * @author Hemanth
 *
 */
@Service
public class AdministrationUserCreateModify extends CacheSessionFuncServices<ObAdministrationUserRequest, ObAdministrationUserResponse, ObAdministrationUserSessionCache> {
	private static Logger log = LogManager.getLogger(AdministrationUserCreateModify.class);
	
	
    @Override
    public void processInternalWithCache(String locale, String sessionId, String trxId, ObAdministrationUserRequest requestBean, ObAdministrationUserResponse responseBean, ObAdministrationUserSessionCache cacheBean, VCGenericService vcService) throws Exception {
    	if((requestBean.getActionCode().equalsIgnoreCase(MiBConstant.ACTION_CODE_EDIT)) && (requestBean.getUserId() == null || requestBean.getUserId().isEmpty())){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
			log.info("Invalid userID");
			return;
		}

    	UserManagementCreateModifyRequestData requestData = new UserManagementCreateModifyRequestData();
    	UserManagementCreateModifyResponseData responseData;
		processRequest(requestData, requestBean, responseBean);
		VCResponse<UserManagementCreateModifyResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.USER_CREATE_MODIFY, 
				requestData, 
				UserManagementCreateModifyResponseData.class, 
				true);
		
		if(processVCResponseError(vcResponse, responseBean)){
			return;
		}
	
    }

	private void processRequest(UserManagementCreateModifyRequestData requestData, ObAdministrationUserRequest requestBean, ObAdministrationUserResponse responseBean) throws Exception{
		
		
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setAction_cd(requestBean.getActionCode());
		requestData.setUsr_id_input(requestBean.getUserId());
		requestData.setUsr_cd_input(requestBean.getUserCode());
		requestData.setUsr_nm_input(requestBean.getUserName());

		if (requestBean.getUserProfileCode() == null || requestBean.getUserProfileCode().isEmpty()) {
			requestData.setUsr_profile_cd_input(MiBConstant.USER_PROFILE_CODE);
		} else {
			requestData.setUsr_profile_cd_input(requestBean.getUserProfileCode());
		}
		requestData.setUsr_email_addr_input(requestBean.getUserEmail());
		
		if (requestBean.getAuthOwn() == null || requestBean.getAuthOwn().isEmpty()) {
			requestData.setMaker_auth_own(MiBConstant.FUNC_TRUE);
		} else {
			requestData.setMaker_auth_own(requestBean.getAuthOwn());
		}

		ObAdministrationUserSessionCache obAdministrationUserSessionCache = requestBean.getSessionCache()
				.get(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_USER_ROLE_LIST.getId());

		if ((requestBean.getActionCode().equalsIgnoreCase(MiBConstant.ACTION_CODE_EDIT)) && (obAdministrationUserSessionCache == null || obAdministrationUserSessionCache.getUserRoleListResponseData() == null)) {

			ObAdministrationUserSessionCache administrationUserSessionCache = requestBean.getSessionCache()
					.get(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_USER_MANAGEMENT_DATA_LIST.getId());

			if (administrationUserSessionCache.getUserManagementListResponseData() == null) {
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
				log.info("user management list  cache is not found");
				return;
			}

			requestData.setUsr_role_list_input(new ArrayList<ObUserRoleBean>());

			for (UserData userData : administrationUserSessionCache.getUserManagementListResponseData()
					.getUser_list()) {

				if (requestBean.getUserId().equalsIgnoreCase(userData.getUsr_id())) {

					for (List_role role : userData.getList_role()) {

						ObUserRoleBean roleBean = new ObUserRoleBean();

						roleBean.setRole_cd(role.getRole_cd());
						roleBean.setRole_base(role.getRole_base());
						roleBean.setRole_desc(role.getRole_desc());
						roleBean.setVersion(role.getVersion());
						roleBean.setRole_group_cd(role.getRole_group_cd());
						roleBean.setRole_group_desc(role.getRole_group_desc());
						requestData.getUsr_role_list_input().add(roleBean);

					}
				}
			}
			return;
		}

		if (obAdministrationUserSessionCache.getUserRoleListResponseData() == null) {
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			log.info("Role List cache is not found");
			return;
		}

		UserRoleListResponseData userRoleListResponseData = obAdministrationUserSessionCache.getUserRoleListResponseData();

		requestData.setUsr_role_list_input(new ArrayList<ObUserRoleBean>());

		for (List_role lr : userRoleListResponseData.getRole_list()) {

			for (RoleList role : requestBean.getRoleList()) {

				if (lr.getRole_group_cd().equalsIgnoreCase(role.getRoleGroupCode())
						&& lr.getRole_cd().equalsIgnoreCase(role.getRoleCode())) {

					ObUserRoleBean roleBean = new ObUserRoleBean();

					roleBean.setRole_cd(lr.getRole_cd());
					roleBean.setRole_base(lr.getRole_base());
					roleBean.setRole_desc(lr.getRole_desc());
					roleBean.setVersion(lr.getVersion());
					roleBean.setRole_group_cd(lr.getRole_group_cd());
					roleBean.setRole_group_desc(lr.getRole_group_desc());
					requestData.getUsr_role_list_input().add(roleBean);

				}

			}

		}

	}			

 }
