package com.silverlake.mleb.mcb.module.func.authorization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.ObAuthorizationRequest;
import com.silverlake.mleb.mcb.bean.ObAuthorizationResponse;
import com.silverlake.mleb.mcb.bean.ObAuthorizationSessionCache;
import com.silverlake.mleb.mcb.bean.common.ObResultBean;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.module.common.CacheSessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.accountManagement.ListAccount;
import com.silverlake.mleb.mcb.module.vc.administration.UserData;
import com.silverlake.mleb.mcb.module.vc.authorization.AuthPendingAccountRequestData;
import com.silverlake.mleb.mcb.module.vc.authorization.AuthPendingAccountResponseData;
import com.silverlake.mleb.mcb.module.vc.authorization.AuthorizationResult;
import com.silverlake.mleb.mcb.module.vc.authorization.PendingAccountMaintenanceRecord;
import com.silverlake.mleb.mcb.module.vc.authorization.PendingUserRecord;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;

/**
 * Purpose: To approve or reject the list of pending authorization for account alias amendment
 * Omni Web Services:
 * authorization/administration/account/maintenance_auth
 * 
 * @author Alex Loo
 *
 */
@Service
public class AuthorizationPerformApprovalPendingAccount extends CacheSessionFuncServices<ObAuthorizationRequest, ObAuthorizationResponse, ObAuthorizationSessionCache>{
	private static Logger log = LogManager.getLogger(AuthorizationPerformApprovalPendingAccount.class);
	
	private static Comparator<ListAccount> LIST_ACCOUNT_COMPARATOR = new Comparator<ListAccount>() {
		@Override
		public int compare(ListAccount o1, ListAccount o2) {
			return o1.getPending_record_id().compareTo(o2.getPending_record_id());
		}
	};
	
	@Override
	public void processInternalWithCache(String locale, String sessionId, String trxId, ObAuthorizationRequest requestBean, ObAuthorizationResponse responseBean, ObAuthorizationSessionCache cacheBean, VCGenericService vcService) throws Exception {
		AuthPendingAccountRequestData requestData = new AuthPendingAccountRequestData();
		AuthPendingAccountResponseData responseData;
		processRequest(locale, requestData, requestBean, responseBean);
		if(responseBean.getObHeader().getStatusCode() != null){
			return;
		}
		VCResponse<AuthPendingAccountResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.AUTHORIZATION_ADMIN_ACCT_MTN_AUTH, 
				requestData, 
				AuthPendingAccountResponseData.class, 
				true);
		if(processVCResponseError(vcResponse, responseBean)){
			return;
		}
		responseData = vcResponse.getData();
		processResponse(locale, requestData, responseData, requestBean, responseBean);
	}
	
	
	
	private void processRequest(String locale, AuthPendingAccountRequestData requestData, ObAuthorizationRequest requestBean, ObAuthorizationResponse responseBean) throws Exception{
		ObAuthorizationSessionCache authorizationRetrievePendingAccountListCache = requestBean.getSessionCache().get(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_AUTH_ADMIN_ACCT_PEND_AUTH_LIST.getId());
		if(authorizationRetrievePendingAccountListCache.getAccountList() == null){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			log.info("Account list cache is not found");
			return;
		}
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setAction_cd(requestBean.getAction_cd());
		if(requestBean.getRecordIds() != null && requestBean.getRecordIds().size() > 0) {
			requestData.setPending_acct_list(new ArrayList<PendingAccountMaintenanceRecord>());
			Collections.sort(authorizationRetrievePendingAccountListCache.getAccountList(), LIST_ACCOUNT_COMPARATOR);
			for(String recordId:requestBean.getRecordIds()) {
				ListAccount key = new ListAccount();
				key.setPending_record_id(recordId);
				int result = Collections.binarySearch(authorizationRetrievePendingAccountListCache.getAccountList(), key, LIST_ACCOUNT_COMPARATOR);
				if(result >= 0) {
					ListAccount accountData = authorizationRetrievePendingAccountListCache.getAccountList().get(result);
					PendingAccountMaintenanceRecord pendingAccountMaintenanceRecord = new PendingAccountMaintenanceRecord();
					pendingAccountMaintenanceRecord.setPending_record_id(accountData.getPending_record_id());
					pendingAccountMaintenanceRecord.setId(accountData.getId());
					pendingAccountMaintenanceRecord.setAccount_no(accountData.getAcct_no());
					pendingAccountMaintenanceRecord.setCcy_code(accountData.getAcct_ccy());
					pendingAccountMaintenanceRecord.setAccount_name(accountData.getAcct_name());
					pendingAccountMaintenanceRecord.setAlias_name(accountData.getAcct_alias());
					pendingAccountMaintenanceRecord.setVersion(accountData.getVersion());
					pendingAccountMaintenanceRecord.setMaintenance_type(accountData.getMaintenance_type());
					pendingAccountMaintenanceRecord.setAuth_status_code(accountData.getAuth_status_code());
					pendingAccountMaintenanceRecord.setAuth_status(accountData.getAuth_status());
					requestData.getPending_acct_list().add(pendingAccountMaintenanceRecord);
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
	
	private void processResponse(String locale, AuthPendingAccountRequestData requestData, AuthPendingAccountResponseData responseData, ObAuthorizationRequest requestBean, ObAuthorizationResponse responseBean) throws Exception{
		responseBean.setResultList(new ArrayList<ObResultBean>());
		if(responseData.getAcctmtn_result_list() != null){
			for(AuthorizationResult authorizationResult:responseData.getAcctmtn_result_list()) {
				ObResultBean obResultBean = new ObResultBean();
				obResultBean.setRecordID(authorizationResult.getPending_record_id());
				obResultBean.setStatusCode(authorizationResult.getResult_status_cd());
				obResultBean.setStatusDesc(authorizationResult.getResult_status_desc());
				responseBean.getResultList().add(obResultBean);
			}
		}
	}
}
