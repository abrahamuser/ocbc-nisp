package com.silverlake.mleb.mcb.module.func.usermanagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.common.ObRoleBean;
import com.silverlake.mleb.mcb.bean.common.ObRoleGroupBean;
import com.silverlake.mleb.mcb.bean.common.ObUserDataBean;
import com.silverlake.mleb.mcb.bean.usermanagement.ObAdministrationUserRequest;
import com.silverlake.mleb.mcb.bean.usermanagement.ObAdministrationUserResponse;
import com.silverlake.mleb.mcb.bean.usermanagement.ObAdministrationUserSessionCache;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.module.common.CacheSessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.administration.UserData;
import com.silverlake.mleb.mcb.module.vc.administration.UserManagementListRequestData;
import com.silverlake.mleb.mcb.module.vc.administration.UserManagementListResponseData;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.user.List_role;

/**
 * Purpose: To get list of all user's data of an Organization.
 * Omni Web Services:
 * administration/user/list
 *
 * @author Hemanth
 *
 */
@Service
public class AdministrationUserManagementDataList extends CacheSessionFuncServices<ObAdministrationUserRequest, ObAdministrationUserResponse, ObAdministrationUserSessionCache> {
	private static Logger log = LogManager.getLogger(AdministrationUserManagementDataList.class);
	
	
    @Override
    public void processInternalWithCache(String locale, String sessionId, String trxId, ObAdministrationUserRequest requestBean, ObAdministrationUserResponse responseBean, ObAdministrationUserSessionCache cacheBean, VCGenericService vcService) throws Exception {
    	

		UserManagementListRequestData requestData = new UserManagementListRequestData();
		UserManagementListResponseData responseData;
		processRequest(requestData, requestBean);
		VCResponse<UserManagementListResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.USER_MANAGEMENT_DATA_LIST, requestData,
				UserManagementListResponseData.class, true);
		
		if (processVCResponseError(vcResponse, responseBean)) {
			return;
		}

		responseData = vcResponse.getData();
		processResponse(responseData, responseBean);
		cacheBean.setUserManagementListResponseData(responseData);      	
    	
    }

	private void processRequest(UserManagementListRequestData requestData, ObAdministrationUserRequest requestBean) throws Exception{
		
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setSrc_usr_cd(requestBean.getUserCode());
		requestData.setSrc_usr_name(requestBean.getUserName());
						
		if (requestBean.getPageNo() == null || requestBean.getPageNo().isEmpty()) {
			requestData.setPage_no(1);
		} else {
			requestData.setPage_no(Integer.valueOf(requestBean.getPageNo()));
		}

		if (requestBean.getPageSize() == null || requestBean.getPageSize().isEmpty()) {
			requestData.setPage_size(Integer.MAX_VALUE);
		} else {
			requestData.setPage_size(Integer.valueOf(requestBean.getPageSize()));
		}
			
	}
	
	private void processResponse(UserManagementListResponseData responseData, ObAdministrationUserResponse responseBean) throws Exception{
				
		responseBean.setUserDataList(new ArrayList<ObUserDataBean>());
		
		for (UserData userData : responseData.getUser_list()) {

			ObUserDataBean dataList = new ObUserDataBean();

			dataList.setUserID(userData.getUsr_id());
			dataList.setUserCode(userData.getUsr_cd());
			dataList.setUserName(userData.getUsr_nm());
			dataList.setPrtOrgCode(userData.getPrt_org_cd());
			dataList.setOrgCode(userData.getOrg_cd());
			dataList.setEmail(userData.getEmail());
			dataList.setUserStatusCode(userData.getUsr_status_cd());
			dataList.setUserStateCode(userData.getUsr_state_cd());
			dataList.setUserProfileCode(userData.getUser_profile_cd());
			dataList.setUserProfileName(userData.getUser_profile_nm());
			dataList.setExpDate(userData.getExp_date());
			dataList.setIsChangePass(userData.getIs_change_pass());
			dataList.setIsLogged(userData.getIs_logged());
			dataList.setIsDelete(userData.getIs_del());
			dataList.setIsEmail(userData.getIs_email());
			dataList.setLastSentEmail(userData.getLast_sent_email());
			dataList.setPinBlockFlag(userData.getPin_block_flag());
			dataList.setUserGroup(userData.getUsr_group());
			dataList.setOrgName(userData.getOrg_name());
			dataList.setMakerAuthOwn(userData.getMaker_auth_own());
			if(userData.getList_role() != null){
			HashMap<String, ObRoleGroupBean> rolegrp = new HashMap<String, ObRoleGroupBean>();
			for (List_role lr : userData.getList_role()) {
				if (!rolegrp.containsKey(lr.getRole_group_cd())) {
					ObRoleGroupBean bean = new ObRoleGroupBean();
					List<ObRoleBean> obRoleBeans = new ArrayList<ObRoleBean>();
					bean.setRoleGroupCode(lr.getRole_group_cd());
					bean.setRoleGroupDescription(lr.getRole_group_desc());
					ObRoleBean obRoleBean = new ObRoleBean();
					obRoleBean.setRoleCode(lr.getRole_cd());
					obRoleBean.setRoleBase(lr.getRole_base());
					obRoleBean.setRoleDescription(lr.getRole_desc());
					obRoleBean.setVersion(lr.getVersion());
					obRoleBeans.add(obRoleBean);
					bean.setRoles(obRoleBeans);
					rolegrp.put(lr.getRole_group_cd(), bean);
				} else {
					ObRoleBean obRoleBean = new ObRoleBean();
					obRoleBean.setRoleCode(lr.getRole_cd());
					obRoleBean.setRoleBase(lr.getRole_base());
					obRoleBean.setRoleDescription(lr.getRole_desc());
					obRoleBean.setVersion(lr.getVersion());
					rolegrp.get(lr.getRole_group_cd()).getRoles().add(obRoleBean);
				}
			}
			dataList.setListRole(new ArrayList<ObRoleGroupBean>(rolegrp.values()));
			}
			responseBean.getUserDataList().add(dataList);
		}

		responseBean.setTotalRecords(responseData.getTotal_records());		
		
	}
		

}
