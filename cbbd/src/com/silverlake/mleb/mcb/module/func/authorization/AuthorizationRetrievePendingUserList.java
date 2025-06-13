package com.silverlake.mleb.mcb.module.func.authorization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.ObAuthorizationRequest;
import com.silverlake.mleb.mcb.bean.ObAuthorizationResponse;
import com.silverlake.mleb.mcb.bean.ObAuthorizationSessionCache;
import com.silverlake.mleb.mcb.bean.common.ObRoleBean;
import com.silverlake.mleb.mcb.bean.common.ObRoleGroupBean;
import com.silverlake.mleb.mcb.bean.common.ObUserDataBean;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.module.common.CacheSessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.administration.UserData;
import com.silverlake.mleb.mcb.module.vc.authorization.PendingUserListRequestData;
import com.silverlake.mleb.mcb.module.vc.authorization.PendingUserListResponseData;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.user.List_role;

/**
 * Purpose: To retrieve the list of pending authorization for user management item
 * Omni Web Services:
 * authorization/administration/user/pending_auth_list
 * 
 * @author Alex Loo
 *
 */
@Service
public class AuthorizationRetrievePendingUserList extends CacheSessionFuncServices<ObAuthorizationRequest, ObAuthorizationResponse, ObAuthorizationSessionCache>{
	
	@Override
	public void processInternalWithCache(String locale, String sessionId, String trxId, ObAuthorizationRequest requestBean, ObAuthorizationResponse responseBean, ObAuthorizationSessionCache cacheBean, VCGenericService vcService) throws Exception {
		PendingUserListRequestData requestData = new PendingUserListRequestData();
		PendingUserListResponseData responseData;
		processRequest(locale, requestData, requestBean, responseBean);
		if(responseBean.getObHeader().getStatusCode() != null){
			return;
		}
		VCResponse<PendingUserListResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.AUTHORIZATION_ADMIN_USER_PEND_AUTH_LIST, 
				requestData, 
				PendingUserListResponseData.class, 
				true);
		if(processVCResponseError(vcResponse, responseBean)){
			return;
		}
		responseData = vcResponse.getData();
		processResponse(locale, requestData, responseData, requestBean, responseBean);
		
		cacheBean.setUserList(responseData.getUser_list());
	}
	
	private void processRequest(String locale, PendingUserListRequestData requestData, ObAuthorizationRequest requestBean, ObAuthorizationResponse responseBean) throws Exception{
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		if(requestBean.getUserCode() != null && !requestBean.getUserCode().isEmpty()) {
			requestData.setSrc_usr_cd(requestBean.getUserCode());
		}
		if(requestBean.getUserName() != null && !requestBean.getUserName().isEmpty()) {
			requestData.setSrc_usr_name(requestBean.getUserName());
		}else {
			requestData.setSrc_usr_name("");//Default to blank
		}
		requestData.setPage_no(1);
		requestData.setPage_size(Integer.MAX_VALUE);
	}
	
	private void processResponse(String locale, PendingUserListRequestData requestData, PendingUserListResponseData responseData, ObAuthorizationRequest requestBean, ObAuthorizationResponse responseBean) throws Exception{
		responseBean.setUserDataList(new ArrayList<ObUserDataBean>());
		if(responseData.getUser_list() != null){
			for(UserData userData:responseData.getUser_list()) {
				ObUserDataBean obUserDataBean = new ObUserDataBean();
				obUserDataBean.setRecordID(userData.getPending_record_id());
				obUserDataBean.setUserID(userData.getUsr_id());
				obUserDataBean.setUserCode(userData.getUsr_cd());
				obUserDataBean.setUserName(userData.getUsr_nm());
				obUserDataBean.setPrtOrgCode(userData.getPrt_org_cd());
				obUserDataBean.setOrgCode(userData.getOrg_cd());
				obUserDataBean.setEmail(userData.getEmail());
				obUserDataBean.setUserProfileCode(userData.getUser_profile_cd());
				obUserDataBean.setUserProfileName(userData.getUser_profile_nm());
				obUserDataBean.setUserGroup(userData.getUsr_group());
				obUserDataBean.setOrgName(userData.getOrg_name());
				obUserDataBean.setUserStatusCode(userData.getUsr_status_cd());
				obUserDataBean.setUserStateCode(userData.getUsr_state_cd());
				obUserDataBean.setAuthStatusCode(userData.getAuth_status_code());
				obUserDataBean.setAuthStatus(userData.getAuth_status());
				obUserDataBean.setListRole(new ArrayList<ObRoleGroupBean>());
				obUserDataBean.setMaintenanceType(userData.getMaintenance_type());
				obUserDataBean.setActionCode(userData.getAction_code());
				obUserDataBean.setMakerAuthOwn(userData.getMaker_auth_own());
				parseRoleList(userData.getList_role(), obUserDataBean.getListRole());
				
				responseBean.getUserDataList().add(obUserDataBean);
			}
		}
		responseBean.setTotalRecords(responseData.getTotal_records());
	}
	
	private static Comparator<ObRoleGroupBean> ROLE_GROUP_COMPARATOR = new Comparator<ObRoleGroupBean>() {
		@Override
		public int compare(ObRoleGroupBean o1, ObRoleGroupBean o2) {
			return o1.getRoleGroupCode().compareTo(o2.getRoleGroupCode());
		}
	};
	
	private void parseRoleList(List<List_role> source, List<ObRoleGroupBean> target) throws Exception{
		if(target == null) {
			throw new Exception("Role target list is null");
		}
		if(source != null) {
			for(List_role role:source) {
				ObRoleGroupBean key = new ObRoleGroupBean();
				key.setRoleGroupCode(role.getRole_group_cd());
				int result = Collections.binarySearch(target, key, ROLE_GROUP_COMPARATOR);
				if(result < 0) {
					ObRoleGroupBean roleGroupBean = new ObRoleGroupBean();
					roleGroupBean.setRoleGroupCode(role.getRole_group_cd());
					roleGroupBean.setRoleGroupDescription(role.getRole_group_desc());
					roleGroupBean.setRoles(new ArrayList<ObRoleBean>());
					ObRoleBean roleBean = new ObRoleBean();
					roleBean.setRoleBase(role.getRole_base());
					roleBean.setRoleCode(role.getRole_cd());
					roleBean.setRoleDescription(role.getRole_desc());
					roleGroupBean.getRoles().add(roleBean);
					target.add(roleGroupBean);
					Collections.sort(target, ROLE_GROUP_COMPARATOR);
				}else {
					ObRoleGroupBean roleGroupBean = target.get(result);
					ObRoleBean roleBean = new ObRoleBean();
					roleBean.setRoleBase(role.getRole_base());
					roleBean.setRoleCode(role.getRole_cd());
					roleBean.setRoleDescription(role.getRole_desc());
					roleGroupBean.getRoles().add(roleBean);
				}
			}
			
		}
	}
}
