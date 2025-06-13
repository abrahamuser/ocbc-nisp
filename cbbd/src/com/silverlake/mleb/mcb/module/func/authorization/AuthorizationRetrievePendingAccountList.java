package com.silverlake.mleb.mcb.module.func.authorization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.ObAccountBean;
import com.silverlake.mleb.mcb.bean.ObAuthorizationRequest;
import com.silverlake.mleb.mcb.bean.ObAuthorizationResponse;
import com.silverlake.mleb.mcb.bean.ObAuthorizationSessionCache;
import com.silverlake.mleb.mcb.bean.common.ObRoleBean;
import com.silverlake.mleb.mcb.bean.common.ObRoleGroupBean;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.module.common.CacheSessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.accountManagement.ListAccount;
import com.silverlake.mleb.mcb.module.vc.authorization.PendingAccountListRequestData;
import com.silverlake.mleb.mcb.module.vc.authorization.PendingAccountListResponseData;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.user.List_role;

/**
 * Purpose: To retrieve the list of pending authorization for account maintenance item
 * Omni Web Services:
 * authorization/administration/account/pending_auth_list
 * 
 * @author Alex Loo
 *
 */
@Service
public class AuthorizationRetrievePendingAccountList extends CacheSessionFuncServices<ObAuthorizationRequest, ObAuthorizationResponse, ObAuthorizationSessionCache>{
	
	@Override
	public void processInternalWithCache(String locale, String sessionId, String trxId, ObAuthorizationRequest requestBean, ObAuthorizationResponse responseBean, ObAuthorizationSessionCache cacheBean, VCGenericService vcService) throws Exception {
		PendingAccountListRequestData requestData = new PendingAccountListRequestData();
		PendingAccountListResponseData responseData;
		processRequest(locale, requestData, requestBean, responseBean);
		if(responseBean.getObHeader().getStatusCode() != null){
			return;
		}
		VCResponse<PendingAccountListResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.AUTHORIZATION_ADMIN_ACCT_PEND_AUTH_LIST, 
				requestData, 
				PendingAccountListResponseData.class, 
				true);
		if(processVCResponseError(vcResponse, responseBean)){
			return;
		}
		responseData = vcResponse.getData();
		processResponse(locale, requestData, responseData, requestBean, responseBean);
		
		cacheBean.setAccountList(responseData.getAccount_list());
	}
	
	private void processRequest(String locale, PendingAccountListRequestData requestData, ObAuthorizationRequest requestBean, ObAuthorizationResponse responseBean) throws Exception{
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		if(requestBean.getAccountNumber() != null && !requestBean.getAccountNumber().isEmpty()) {
			requestData.setSrc_account_no(requestBean.getAccountNumber());
		}
		if(requestBean.getAccountName() != null && !requestBean.getAccountName().isEmpty()) {
			requestData.setSrc_account_name(requestBean.getAccountName());
		}
		requestData.setPage_no(1);
		requestData.setPage_size(Integer.MAX_VALUE);
	}
	
	private void processResponse(String locale, PendingAccountListRequestData requestData, PendingAccountListResponseData responseData, ObAuthorizationRequest requestBean, ObAuthorizationResponse responseBean) throws Exception{
		responseBean.setAccountList(new ArrayList<ObAccountBean>());
		if(responseData.getAccount_list() != null){
			for(ListAccount listAccount:responseData.getAccount_list()) {
				ObAccountBean obUserDataBean = new ObAccountBean();
				obUserDataBean.setAccountId(listAccount.getPending_record_id());
				obUserDataBean.setAccountName(listAccount.getAcct_name());
				obUserDataBean.setAccountAlias(listAccount.getAcct_alias());
				obUserDataBean.setCurrencyCode(listAccount.getAcct_ccy());
				obUserDataBean.setAccountNumber(listAccount.getAcct_no());
				obUserDataBean.setAccountStatus(String.valueOf(listAccount.getAcct_status()));
				obUserDataBean.setMaintenanceType(listAccount.getMaintenance_type());
				obUserDataBean.setAuthStatusCode(listAccount.getAuth_status_code());
				obUserDataBean.setAuthStatusDesc(listAccount.getAuth_status());
				
				responseBean.getAccountList().add(obUserDataBean);
			}
		}
		responseBean.setTotalRecords(responseData.getTotal_records());
	}
}
