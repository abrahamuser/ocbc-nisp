package com.silverlake.mleb.mcb.module.func.transaction;

import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.ObBeneAccountBean;
import com.silverlake.mleb.mcb.bean.mgmtbene.ObTransactionManageBeneficiaryRequest;
import com.silverlake.mleb.mcb.bean.mgmtbene.ObTransactionManageBeneficiaryResponse;
import com.silverlake.mleb.mcb.bean.mgmtbene.ObTransactionManageBeneficiarySessionCache;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.module.common.CacheSessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.transaction.management.ViewBeneficiaryRequestData;
import com.silverlake.mleb.mcb.module.vc.transaction.management.ViewBeneficiaryResponseData;

/**
 * Purpose: To get the details of the beneficiary account at manage beneficiary module 
 * 
 * Omni Web Services:
 * transaction_mgmt/view_bene
 * 
 * @author Alex Loo
 *
 */
@Service
public class TransactionManageBeneficiaryDetails extends CacheSessionFuncServices<ObTransactionManageBeneficiaryRequest, ObTransactionManageBeneficiaryResponse, ObTransactionManageBeneficiarySessionCache>{
	
	@Override
	public void processInternalWithCache(String locale, String sessionId, String trxId, ObTransactionManageBeneficiaryRequest requestBean, ObTransactionManageBeneficiaryResponse responseBean, ObTransactionManageBeneficiarySessionCache cacheBean, VCGenericService vcService) throws Exception {
		ViewBeneficiaryRequestData requestData = new ViewBeneficiaryRequestData();
		ViewBeneficiaryResponseData responseData;
		processRequest(requestData, requestBean);
		VCResponse<ViewBeneficiaryResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.TRANSACTION_MGMT_VIEW_BENE, 
				requestData, 
				ViewBeneficiaryResponseData.class, 
				true);
		if(processVCResponseError(vcResponse, responseBean)){
			return;
		}
		
		responseData = vcResponse.getData();
		processResponse(responseData, requestBean, responseBean);
		cacheBean.setViewBeneficiaryResponseData(responseData);
	}
	
	private void processRequest(ViewBeneficiaryRequestData requestData, ObTransactionManageBeneficiaryRequest requestBean) throws Exception{
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setBene_id(requestBean.getBeneId());
	}
	
	private void processResponse(ViewBeneficiaryResponseData responseData, ObTransactionManageBeneficiaryRequest requestBean, ObTransactionManageBeneficiaryResponse responseBean) throws Exception{
		ObBeneAccountBean obAccountBean = new ObBeneAccountBean();
		obAccountBean.setAccountId(requestBean.getBeneId());
		obAccountBean.setBeneAccountCcy(responseData.getAccount_currency());
		obAccountBean.setBeneAccountNo(responseData.getAccount_number());
		obAccountBean.setBeneAccountName(responseData.getBene_name());
		obAccountBean.setBeneNickName(responseData.getNick_name());
		obAccountBean.setBeneficiaryBankName(responseData.getBank_name());
		obAccountBean.setBeneficiaryBankCode(responseData.getBank_code());
		obAccountBean.setBeneBankClearingId(responseData.getBank_network_clearing_code());
		obAccountBean.setBeneficiaryBankBranch(responseData.getBank_city());//Bank branch for LLG and RTGS, TT will be bank city
		obAccountBean.setBeneAddress1(responseData.getBene_address1());
		obAccountBean.setBeneAddress2(responseData.getBene_address2());
		obAccountBean.setBeneAddress3(responseData.getBene_address3());
		obAccountBean.setIsFavFlag(responseData.getIs_favorite());
		obAccountBean.setProxy_type(responseData.getProxy_type());
		obAccountBean.setProxy_data(responseData.getProxy_data());
		responseData.setBene_id(requestBean.getBeneId());//Use for data integrity checking for whichever ws to use this cahce
		responseBean.setBeneDetails(obAccountBean);
	}
}
