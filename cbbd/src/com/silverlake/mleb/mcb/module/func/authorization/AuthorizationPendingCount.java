package com.silverlake.mleb.mcb.module.func.authorization;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.ObAuthorizationRequest;
import com.silverlake.mleb.mcb.bean.ObAuthorizationResponse;
import com.silverlake.mleb.mcb.bean.ObAuthorizationSessionCache;
import com.silverlake.mleb.mcb.bean.ObPendingAuthCountBean;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.module.common.CacheSessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.authorization.AuthCountRequestData;
import com.silverlake.mleb.mcb.module.vc.authorization.AuthCountResponseData;
import com.silverlake.mleb.mcb.module.vc.authorization.PendingAuthCount;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;

/**
 * Purpose: Get the summary count of all pending authorization item
 * Omni Web Services:
 * authorization/count
 * 
 * @author Alex Loo
 *
 */
@Service
public class AuthorizationPendingCount extends CacheSessionFuncServices<ObAuthorizationRequest, ObAuthorizationResponse, ObAuthorizationSessionCache>{
	
	@Override
	public void processInternalWithCache(String locale, String sessionId, String trxId, ObAuthorizationRequest requestBean, ObAuthorizationResponse responseBean, ObAuthorizationSessionCache cacheBean, VCGenericService vcService) throws Exception {
		AuthCountRequestData requestData = new AuthCountRequestData();
		AuthCountResponseData responseData;
		processRequest(locale, requestData, requestBean, responseBean);
		if(responseBean.getObHeader().getStatusCode() != null){
			return;
		}
		VCResponse<AuthCountResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.AUTHORIZATION_COUNT, 
				requestData, 
				AuthCountResponseData.class, 
				true);
		if(processVCResponseError(vcResponse, responseBean)){
			return;
		}
		responseData = vcResponse.getData();
		processResponse(locale, requestData, responseData, requestBean, responseBean);
	}
	
	private void processRequest(String locale, AuthCountRequestData requestData, ObAuthorizationRequest requestBean, ObAuthorizationResponse responseBean) throws Exception{
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		if(requestBean.getProd_cd() != null && !requestBean.getProd_cd().isEmpty()) {
			requestData.setCategory_cd(requestBean.getProd_cd());
		}else {
			requestData.setCategory_cd("ADM");
		}
		
	}
	
	private void processResponse(String locale, AuthCountRequestData requestData, AuthCountResponseData responseData, ObAuthorizationRequest requestBean, ObAuthorizationResponse responseBean) throws Exception{
		responseBean.setPendingAuthCountList(new ArrayList<ObPendingAuthCountBean>());
		if(responseData.getPending_auth_count_list() != null){
			for(PendingAuthCount pendingAuthCount:responseData.getPending_auth_count_list()) {
				ObPendingAuthCountBean obPendingAuthCountBean = new ObPendingAuthCountBean();
				obPendingAuthCountBean.setMenuItemId(pendingAuthCount.getMenu_item_id());
				obPendingAuthCountBean.setCount(pendingAuthCount.getCount());
				
				responseBean.getPendingAuthCountList().add(obPendingAuthCountBean);
			}
		}
	}
}
