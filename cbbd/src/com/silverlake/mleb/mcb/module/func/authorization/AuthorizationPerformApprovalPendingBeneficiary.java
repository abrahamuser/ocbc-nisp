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
import com.silverlake.mleb.mcb.module.vc.authorization.AuthPendingBeneRequestData;
import com.silverlake.mleb.mcb.module.vc.authorization.AuthPendingBeneResponseData;
import com.silverlake.mleb.mcb.module.vc.authorization.AuthorizationResult;
import com.silverlake.mleb.mcb.module.vc.authorization.PendingAccountMaintenanceRecord;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.transaction.BeneficiaryList;

/**
 * Purpose: To approve or reject the list of pending authorization for beneficiary maintenance
 * Omni Web Services:
 * authorization/administration/beneficiary_ft/maintenance_auth 
 * 
 * @author Alex Loo
 *
 */
@Service
public class AuthorizationPerformApprovalPendingBeneficiary extends CacheSessionFuncServices<ObAuthorizationRequest, ObAuthorizationResponse, ObAuthorizationSessionCache>{
	private static Logger log = LogManager.getLogger(AuthorizationPerformApprovalPendingBeneficiary.class);
	
	private static Comparator<BeneficiaryList> LIST_BENE_COMPARATOR = new Comparator<BeneficiaryList>() {
		@Override
		public int compare(BeneficiaryList o1, BeneficiaryList o2) {
			return o1.getPending_record_id().compareTo(o2.getPending_record_id());
		}
	};
	
	@Override
	public void processInternalWithCache(String locale, String sessionId, String trxId, ObAuthorizationRequest requestBean, ObAuthorizationResponse responseBean, ObAuthorizationSessionCache cacheBean, VCGenericService vcService) throws Exception {
		AuthPendingBeneRequestData requestData = new AuthPendingBeneRequestData();
		AuthPendingBeneResponseData responseData;
		processRequest(locale, requestData, requestBean, responseBean);
		if(responseBean.getObHeader().getStatusCode() != null){
			return;
		}
		VCResponse<AuthPendingBeneResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.AUTHORIZATION_ADMIN_BENE_MTN_AUTH, 
				requestData, 
				AuthPendingBeneResponseData.class, 
				true);
		if(processVCResponseError(vcResponse, responseBean)){
			return;
		}
		responseData = vcResponse.getData();
		processResponse(locale, requestData, responseData, requestBean, responseBean);
	}
	
	
	
	private void processRequest(String locale, AuthPendingBeneRequestData requestData, ObAuthorizationRequest requestBean, ObAuthorizationResponse responseBean) throws Exception{
		ObAuthorizationSessionCache authorizationRetrievePendingbBeneficiaryListCache = requestBean.getSessionCache().get(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_AUTH_ADMIN_BENE_PEND_AUTH_LIST.getId());
		if(authorizationRetrievePendingbBeneficiaryListCache.getBeneficiaryList() == null){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			log.info("Bene list cache is not found");
			return;
		}
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setAction_cd(requestBean.getAction_cd());
		if(requestBean.getRecordIds() != null && requestBean.getRecordIds().size() > 0) {
			requestData.setPending_bene_list(new ArrayList<BeneficiaryList>());
			Collections.sort(authorizationRetrievePendingbBeneficiaryListCache.getBeneficiaryList(), LIST_BENE_COMPARATOR);
			for(String recordId:requestBean.getRecordIds()) {
				BeneficiaryList key = new BeneficiaryList();
				key.setPending_record_id(recordId);
				int result = Collections.binarySearch(authorizationRetrievePendingbBeneficiaryListCache.getBeneficiaryList(), key, LIST_BENE_COMPARATOR);
				if(result >= 0) {
					BeneficiaryList accountData = authorizationRetrievePendingbBeneficiaryListCache.getBeneficiaryList().get(result);
					requestData.getPending_bene_list().add(accountData);
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
	
	private void processResponse(String locale, AuthPendingBeneRequestData requestData, AuthPendingBeneResponseData responseData, ObAuthorizationRequest requestBean, ObAuthorizationResponse responseBean) throws Exception{
		responseBean.setResultList(new ArrayList<ObResultBean>());
		if(responseData.getBene_result_list() != null){
			for(AuthorizationResult authorizationResult:responseData.getBene_result_list()) {
				ObResultBean obResultBean = new ObResultBean();
				obResultBean.setRecordID(authorizationResult.getPending_record_id());
				obResultBean.setStatusCode(authorizationResult.getResult_status_cd());
				obResultBean.setStatusDesc(authorizationResult.getResult_status_desc());
				responseBean.getResultList().add(obResultBean);
			}
		}
	}
}
