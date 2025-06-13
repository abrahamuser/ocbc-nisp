package com.silverlake.mleb.mcb.module.func.authorization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.ObAuthorizationRequest;
import com.silverlake.mleb.mcb.bean.ObAuthorizationResponse;
import com.silverlake.mleb.mcb.bean.ObAuthorizationSessionCache;
import com.silverlake.mleb.mcb.bean.ObBeneAccountBean;
import com.silverlake.mleb.mcb.bean.common.ObRoleBean;
import com.silverlake.mleb.mcb.bean.common.ObRoleGroupBean;
import com.silverlake.mleb.mcb.bean.common.ObUserDataBean;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.module.common.CacheSessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.authorization.PendingBeneListRequestData;
import com.silverlake.mleb.mcb.module.vc.authorization.PendingBeneListResponseData;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.transaction.BeneficiaryList;
import com.silverlake.mleb.mcb.module.vc.user.List_role;

/**
 * Purpose: To retrieve the list of pending authorization for beneficiary maintenance item
 * Omni Web Services:
 * authorization/administration/beneficiary_ft/pending_auth_list
 * 
 * @author Alex Loo
 *
 */
@Service
public class AuthorizationRetrievePendingBeneficiaryList extends CacheSessionFuncServices<ObAuthorizationRequest, ObAuthorizationResponse, ObAuthorizationSessionCache>{
	
	@Override
	public void processInternalWithCache(String locale, String sessionId, String trxId, ObAuthorizationRequest requestBean, ObAuthorizationResponse responseBean, ObAuthorizationSessionCache cacheBean, VCGenericService vcService) throws Exception {
		PendingBeneListRequestData requestData = new PendingBeneListRequestData();
		PendingBeneListResponseData responseData;
		processRequest(locale, requestData, requestBean, responseBean);
		if(responseBean.getObHeader().getStatusCode() != null){
			return;
		}
		VCResponse<PendingBeneListResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.AUTHORIZATION_ADMIN_BENE_PEND_AUTH_LIST, 
				requestData, 
				PendingBeneListResponseData.class, 
				true);
		if(processVCResponseError(vcResponse, responseBean)){
			return;
		}
		responseData = vcResponse.getData();
		processResponse(locale, requestData, responseData, requestBean, responseBean);
		
		cacheBean.setBeneficiaryList(responseData.getBene_list());
	}
	
	private void processRequest(String locale, PendingBeneListRequestData requestData, ObAuthorizationRequest requestBean, ObAuthorizationResponse responseBean) throws Exception{
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setPage_no(1);
		requestData.setPage_size(Integer.MAX_VALUE);
	}
	
	private void processResponse(String locale, PendingBeneListRequestData requestData, PendingBeneListResponseData responseData, ObAuthorizationRequest requestBean, ObAuthorizationResponse responseBean) throws Exception{
		responseBean.setBeneficiaryList(new ArrayList<ObBeneAccountBean>());
		if(responseData.getBene_list() != null){
			for(BeneficiaryList beneficiaryList:responseData.getBene_list()) {
				ObBeneAccountBean obBeneAccountBean = new ObBeneAccountBean();
				obBeneAccountBean.setBeneID(beneficiaryList.getPending_record_id());
				obBeneAccountBean.setBeneAccountCcy(beneficiaryList.getAccount_currency());
				obBeneAccountBean.setBeneAccountNo(beneficiaryList.getAccount_number());
				obBeneAccountBean.setBeneAccountName(beneficiaryList.getAccount_name());
				obBeneAccountBean.setBeneNickName(beneficiaryList.getNick_name());
				obBeneAccountBean.setBeneficiaryBankName(beneficiaryList.getBank_name());
				obBeneAccountBean.setBeneficiaryBankCode(beneficiaryList.getBank_code());
				obBeneAccountBean.setBeneBankClearingId(beneficiaryList.getBank_network_clearing_code());
				obBeneAccountBean.setBeneficiaryBankBranch(beneficiaryList.getBank_branch_name());
				obBeneAccountBean.setProvinceCode(beneficiaryList.getProvince_code());
				obBeneAccountBean.setBeneBankCityId(beneficiaryList.getCity_code());
				obBeneAccountBean.setBeneAddress1(beneficiaryList.getBene_address1());
				obBeneAccountBean.setBeneAddress2(beneficiaryList.getBene_address2());
				obBeneAccountBean.setBeneAddress3(beneficiaryList.getBene_address3());
				obBeneAccountBean.setBankCountryCode(beneficiaryList.getBank_country_code());
				obBeneAccountBean.setTypeCode(beneficiaryList.getMaintenance_type());
				obBeneAccountBean.setAuthStatusCode(beneficiaryList.getAuth_status_code());
				obBeneAccountBean.setAuthStatusDesc(beneficiaryList.getAuth_status());
				obBeneAccountBean.setProductCode(beneficiaryList.getProd_cd());
				obBeneAccountBean.setBankCity(beneficiaryList.getBank_city());
				
				responseBean.getBeneficiaryList().add(obBeneAccountBean);
			}
		}
		responseBean.setTotalRecords(responseData.getTotal_records());
	}
}
