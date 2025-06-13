package com.silverlake.mleb.mcb.module.func.usermanagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.common.ObRoleBean;
import com.silverlake.mleb.mcb.bean.common.ObRoleGroupBean;
import com.silverlake.mleb.mcb.bean.usermanagement.ObAdministrationUserRequest;
import com.silverlake.mleb.mcb.bean.usermanagement.ObAdministrationUserResponse;
import com.silverlake.mleb.mcb.bean.usermanagement.ObAdministrationUserSessionCache;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.module.common.CacheSessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.administration.UserRloeListRequestData;
import com.silverlake.mleb.mcb.module.vc.administration.UserRoleListResponseData;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.user.List_role;

/**
 * Purpose: To get subscriber role list used for user maintenance
 * Omni Web Services:
 * /administration/subscriber_role/list

 *
 * @author Hemanth
 *
 */
@Service
public class AdministrationUserRoleList extends CacheSessionFuncServices<ObAdministrationUserRequest, ObAdministrationUserResponse, ObAdministrationUserSessionCache> {
		
    @Override
    public void processInternalWithCache(String locale, String sessionId, String trxId, ObAdministrationUserRequest requestBean, ObAdministrationUserResponse responseBean, ObAdministrationUserSessionCache cacheBean, VCGenericService vcService) throws Exception {
    	
    	UserRloeListRequestData requestData = new UserRloeListRequestData();
    	UserRoleListResponseData responseData;
		processRequest(requestData, requestBean);
		VCResponse<UserRoleListResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.USER_ROLE_LIST, 
				requestData, 
				UserRoleListResponseData.class, 
				true);
		if(processVCResponseError(vcResponse, responseBean)){
			return;
		}
		
		responseData = vcResponse.getData();
		processResponse(responseData, responseBean);
		cacheBean.setUserRoleListResponseData(responseData);      	
    	
    }

	private void processRequest(UserRloeListRequestData requestData, ObAdministrationUserRequest requestBean) throws Exception{
		
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
				
	}
	
	private void processResponse(UserRoleListResponseData responseData, ObAdministrationUserResponse responseBean) throws Exception{
		
		HashMap<String, ObRoleGroupBean> rolegrp = new HashMap<String, ObRoleGroupBean>();
		for (List_role lr : responseData.getRole_list()) {
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
