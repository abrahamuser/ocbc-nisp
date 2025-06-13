package com.silverlake.mleb.mcb.module.func.authorization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.ObAuthorizationRequest;
import com.silverlake.mleb.mcb.bean.ObAuthorizationResponse;
import com.silverlake.mleb.mcb.bean.ObAuthorizationSessionCache;
import com.silverlake.mleb.mcb.bean.common.ObResultBean;
import com.silverlake.mleb.mcb.bean.common.ObRoleBean;
import com.silverlake.mleb.mcb.bean.common.ObRoleGroupBean;
import com.silverlake.mleb.mcb.bean.common.ObUserDataBean;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.module.common.CacheSessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.administration.UserData;
import com.silverlake.mleb.mcb.module.vc.authorization.AuthPendingUserRequestData;
import com.silverlake.mleb.mcb.module.vc.authorization.AuthPendingUserResponseData;
import com.silverlake.mleb.mcb.module.vc.authorization.AuthorizationResult;
import com.silverlake.mleb.mcb.module.vc.authorization.PendingUserRecord;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.user.List_role;

/**
 * Purpose: To approve or reject the list of pending authorization for user management item
 * Omni Web Services:
 * authorization/administration/user/authorization
 * 
 * @author Alex Loo
 *
 */
@Service
public class AuthorizationPerformApprovalPendingUser extends CacheSessionFuncServices<ObAuthorizationRequest, ObAuthorizationResponse, ObAuthorizationSessionCache>{
	private static Logger log = LogManager.getLogger(AuthorizationPerformApprovalPendingUser.class);
	
	private static Comparator<UserData> USER_COMPARATOR = new Comparator<UserData>() {
		@Override
		public int compare(UserData o1, UserData o2) {
			return o1.getPending_record_id().compareTo(o2.getPending_record_id());
		}
	};
	
	@Override
	public void processInternalWithCache(String locale, String sessionId, String trxId, ObAuthorizationRequest requestBean, ObAuthorizationResponse responseBean, ObAuthorizationSessionCache cacheBean, VCGenericService vcService) throws Exception {
		AuthPendingUserRequestData requestData = new AuthPendingUserRequestData();
		AuthPendingUserResponseData responseData;
		processRequest(locale, requestData, requestBean, responseBean);
		if(responseBean.getObHeader().getStatusCode() != null){
			return;
		}
		VCResponse<AuthPendingUserResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.AUTHORIZATION_ADMIN_USER_AUTH, 
				requestData, 
				AuthPendingUserResponseData.class, 
				true);
		if(processVCResponseError(vcResponse, responseBean)){
			return;
		}
		responseData = vcResponse.getData();
		processResponse(locale, requestData, responseData, requestBean, responseBean);
	}
	
	
	
	private void processRequest(String locale, AuthPendingUserRequestData requestData, ObAuthorizationRequest requestBean, ObAuthorizationResponse responseBean) throws Exception{
		ObAuthorizationSessionCache authorizationRetrievePendingUserListCache = requestBean.getSessionCache().get(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_AUTH_ADMIN_USER_PEND_AUTH_LIST.getId());
		if(authorizationRetrievePendingUserListCache.getUserList() == null){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			log.info("User list cache is not found");
			return;
		}
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setAction_cd(requestBean.getAction_cd());
		if(requestBean.getRecordIds() != null && requestBean.getRecordIds().size() > 0) {
			requestData.setPending_record_list(new ArrayList<PendingUserRecord>());
			Collections.sort(authorizationRetrievePendingUserListCache.getUserList(), USER_COMPARATOR);
			for(String recordId:requestBean.getRecordIds()) {
				UserData key = new UserData();
				key.setPending_record_id(recordId);
				int result = Collections.binarySearch(authorizationRetrievePendingUserListCache.getUserList(), key, USER_COMPARATOR);
				if(result >= 0) {
					UserData userData = authorizationRetrievePendingUserListCache.getUserList().get(result);
					PendingUserRecord pendingUserRecord = new PendingUserRecord();
					pendingUserRecord.setPending_record_id(userData.getPending_record_id());
					pendingUserRecord.setUser_cd(userData.getUsr_cd());
					pendingUserRecord.setVersion(userData.getVersion());
					requestData.getPending_record_list().add(pendingUserRecord);
				}else {
					responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
					log.info("Record id "+recordId+" not matched");
					return;
				}
			}
		}
		requestData.setDevice_type(requestBean.getObDevice().getModel());
		requestData.setDevice_os(requestBean.getObDevice().getType()+" "+requestBean.getObDevice().getOs());
	}
	
	private void processResponse(String locale, AuthPendingUserRequestData requestData, AuthPendingUserResponseData responseData, ObAuthorizationRequest requestBean, ObAuthorizationResponse responseBean) throws Exception{
		responseBean.setResultList(new ArrayList<ObResultBean>());
		if(responseData.getUserauth_result_list() != null){
			for(AuthorizationResult authorizationResult:responseData.getUserauth_result_list()) {
				ObResultBean obResultBean = new ObResultBean();
				obResultBean.setRecordID(authorizationResult.getPending_record_id());
				obResultBean.setStatusCode(authorizationResult.getResult_status_cd());
				obResultBean.setStatusDesc(authorizationResult.getResult_status_desc());
				responseBean.getResultList().add(obResultBean);
			}
		}
	}
}
