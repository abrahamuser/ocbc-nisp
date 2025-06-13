package com.silverlake.mleb.mcb.module.func.usermanagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.common.ObRoleBean;
import com.silverlake.mleb.mcb.bean.common.ObRoleGroupBean;
import com.silverlake.mleb.mcb.bean.usermanagement.ObAdministrationUserRequest;
import com.silverlake.mleb.mcb.bean.usermanagement.ObAdministrationUserResponse;
import com.silverlake.mleb.mcb.bean.usermanagement.ObAdministrationUserSessionCache;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.module.common.CacheSessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.administration.UserGeneralMaintenanceRequestData;
import com.silverlake.mleb.mcb.module.vc.administration.UserGeneralMaintenanceResponseData;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.user.List_role;

/**
 * Purpose:  To Perform General User Maintenance of an Organization.
 * Omni Web Services:
 * /administration/user/maintenance
 *
 * @author Hemanth
 *
 */
@Service
public class AdministrationUserGeneralMaintenance extends CacheSessionFuncServices<ObAdministrationUserRequest, ObAdministrationUserResponse, ObAdministrationUserSessionCache> {
	private static Logger log = LogManager.getLogger(AdministrationUserGeneralMaintenance.class);
	
	
    @Override
    public void processInternalWithCache(String locale, String sessionId, String trxId, ObAdministrationUserRequest requestBean, ObAdministrationUserResponse responseBean, ObAdministrationUserSessionCache cacheBean, VCGenericService vcService) throws Exception {
    	
    	UserGeneralMaintenanceRequestData requestData = new UserGeneralMaintenanceRequestData();
    	UserGeneralMaintenanceResponseData responseData;
		processRequest(requestData, requestBean);
		VCResponse<UserGeneralMaintenanceResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.USER_MAINTENANCE, 
				requestData, 
				UserGeneralMaintenanceResponseData.class, 
				true);
		if(processVCResponseError(vcResponse, responseBean)){
			return;
		}
		       	
		responseData = vcResponse.getData();
		processResponse(responseData, responseBean);
    }

	private void processRequest(UserGeneralMaintenanceRequestData requestData, ObAdministrationUserRequest requestBean) throws Exception{
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setAction_cd(requestBean.getActionCode());
		requestData.setUsr_id_mnt(requestBean.getUserId());
		requestData.setUsr_cd_mnt(requestBean.getUserCode());	
	}
	
	
   private void processResponse(UserGeneralMaintenanceResponseData responseData, ObAdministrationUserResponse responseBean) throws Exception{
		
		responseBean.setUserID(responseData.getUsr_id());
		responseBean.setUserCode(responseData.getUsr_cd());
		responseBean.setUserName(responseData.getUsr_nm());
		responseBean.setPrtOrgCode(responseData.getPrt_org_cd());
		responseBean.setOrgCode(responseData.getOrg_cd());
		responseBean.setEmail(responseData.getEmail());
		responseBean.setUserStatusCode(responseData.getUsr_status_cd());
		responseBean.setUserStateCode(responseData.getUsr_state_cd());
		responseBean.setUserProfileCode(responseData.getUser_profile_cd());
		responseBean.setUserProfileName(responseData.getUser_profile_nm());
		responseBean.setExpDate(responseData.getExp_date());
		responseBean.setIsChangePass(responseData.getIs_change_pass());
		responseBean.setIsLogged(responseData.getIs_logged());
		responseBean.setIsDelete(responseData.getIs_del());
		responseBean.setIsEmail(responseData.getIs_email());
		responseBean.setLastSentEmail(responseData.getLast_sent_email());
		responseBean.setPinBlockFlag(responseData.getPin_block_flag());
		responseBean.setUserGroup(responseData.getUsr_group());
		responseBean.setOrgName(responseData.getOrg_name());
		if(responseData.getList_role() != null){
		HashMap<String, ObRoleGroupBean> rolegrp = new HashMap<String, ObRoleGroupBean>();
		for (List_role lr : responseData.getList_role()) {
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
		responseBean.setListRole(new ArrayList<ObRoleGroupBean>(rolegrp.values()));
		}
	}

}
