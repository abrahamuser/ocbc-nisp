package com.silverlake.mleb.mcb.module.func.transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.ObBeneAccountBean;
import com.silverlake.mleb.mcb.bean.ObTransactionBeneficiaryBean;
import com.silverlake.mleb.mcb.bean.ObTransactionBeneficiaryRequest;
import com.silverlake.mleb.mcb.bean.ObTransactionBeneficiaryResponse;
import com.silverlake.mleb.mcb.bean.ObTransactionBeneficiarySessionCache;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.module.common.CacheSessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.transaction.BeneficiaryList;
import com.silverlake.mleb.mcb.module.vc.transaction.BeneficiaryRequestData;
import com.silverlake.mleb.mcb.module.vc.transaction.BeneficiaryResponseData;
import com.silverlake.mleb.mcb.util.StringUtil;

/**
 * Purpose: Get transaction beneficiary list
 * Supported transfer type
 * 1. XXX (Default option where this value will pass as it's to omni)
 * 
 * Omni Web Services:
 * transaction/beneficiaries
 * 
 * @author Alex Loo
 *
 */
@Service
public class TransactionBeneficiaries extends CacheSessionFuncServices<ObTransactionBeneficiaryRequest, ObTransactionBeneficiaryResponse, ObTransactionBeneficiarySessionCache>{
	
	@Override
	public void processInternalWithCache(String locale, String sessionId, String trxId, ObTransactionBeneficiaryRequest requestBean, ObTransactionBeneficiaryResponse responseBean, ObTransactionBeneficiarySessionCache cacheBean, VCGenericService vcService) throws Exception {
		processDefaultBeneficiary(locale, sessionId, trxId, requestBean, responseBean, cacheBean, vcService);
	}
	
	/**
	 * Default scenario for beneficiaries inquiry.
	 * @param locale
	 * @param sessionId
	 * @param trxId
	 * @param requestBean
	 * @param responseBean
	 * @param cacheBean
	 * @param vcService
	 * @throws Exception
	 */
	private void processDefaultBeneficiary(String locale, String sessionId, String trxId, ObTransactionBeneficiaryRequest requestBean, ObTransactionBeneficiaryResponse responseBean, ObTransactionBeneficiarySessionCache cacheBean, VCGenericService vcService) throws Exception{
		BeneficiaryRequestData requestData = new BeneficiaryRequestData();
		BeneficiaryResponseData responseData;
		processRequest(requestData, requestBean, requestBean.getTransferServiceType());
		VCResponse<BeneficiaryResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.TRANSACTION_BENEFICIARIES, 
				requestData, 
				BeneficiaryResponseData.class, 
				true);
		if(processVCResponseError(vcResponse, responseBean)){
			return;
		}
		
		responseData = vcResponse.getData();
		processResponse(responseData, responseBean, requestBean.getTransferServiceType());
		
		Map<String, BeneficiaryResponseData> beneficiaryResponseDataList = new HashMap<String, BeneficiaryResponseData>(2);
		beneficiaryResponseDataList.put(requestBean.getTransferServiceType(), responseData);
		cacheBean.setVcBeneficiaryResponseData(beneficiaryResponseDataList);
	}
	
	private void processRequest(BeneficiaryRequestData requestData, ObTransactionBeneficiaryRequest requestBean, String productCode) throws Exception{
		List<String> listProductCode = new ArrayList<String>();
		listProductCode.add(productCode);
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setProd_cd(listProductCode);
		requestData.setPage_no(1);
		requestData.setPage_size(Integer.MAX_VALUE);
		requestData.setDebit_acct_no(requestBean.getAccountNo());
		requestData.setDebit_acct_ccy(requestBean.getAccountCcy());
	}
	
	private void processResponse(BeneficiaryResponseData responseData, ObTransactionBeneficiaryResponse responseBean, String category) throws Exception{
		if(responseBean.getCategoryList() == null){
			responseBean.setCategoryList(new ArrayList<ObTransactionBeneficiaryBean>(2));
		}
		ObTransactionBeneficiaryBean categorySourceOfFund = new ObTransactionBeneficiaryBean();
		categorySourceOfFund.setCategory(category);
		if(responseData.getBeneficiary_list() == null || responseData.getBeneficiary_list().isEmpty()){	
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_NO_ACCOUNT); 
			return;
		}
		
		List<ObBeneAccountBean> obAccountBeanList = new ArrayList<ObBeneAccountBean>();
		int i=1;
		for(BeneficiaryList acc: responseData.getBeneficiary_list()){
						
			if(acc.getBank_code() == null || acc.getBank_code().isEmpty()){
				acc.setP2p_flag("Y");
			}else{
				acc.setP2p_flag("N");
			}
			
			if (category.equals(MiBConstant.TRANS_SERVICE_TYPE_TT)) {
				if (acc.getBank_network_clearing_code() == null || acc.getBank_network_clearing_code().isEmpty()) {
					acc.setP2p_flag("Y");
				} else {
					acc.setP2p_flag("N");
				}
			}
			String uuid = StringUtil.leftPadString(Integer.toString(i), 5, "0");
			acc.setUuid("B"+uuid);
			ObBeneAccountBean obAccountBean = new ObBeneAccountBean();
			obAccountBean.setBeneID(acc.getRecord_id());
			obAccountBean.setBeneAccountCcy(acc.getAccount_currency());
			obAccountBean.setBeneAccountNo(acc.getAccount_number());
			obAccountBean.setBeneAccountName(acc.getAccount_name());
			obAccountBean.setBeneNickName(acc.getNick_name());
			obAccountBean.setBeneficiaryBankName(acc.getBank_name());
			obAccountBean.setBeneficiaryBankCode(acc.getBank_code());
			obAccountBean.setBeneBankClearingId(acc.getBank_network_clearing_code());
			obAccountBean.setBeneficiaryBankBranch(acc.getBank_branch_name());
			obAccountBean.setProvinceCode(acc.getProvince_code());
			obAccountBean.setBeneBankCityId(acc.getCity_code());
			obAccountBean.setBeneAddress1(acc.getBene_address1());
			obAccountBean.setBeneAddress2(acc.getBene_address2());
			obAccountBean.setBeneAddress3(acc.getBene_address3());
			obAccountBean.setIsFavFlag(acc.getIs_favorite());

			obAccountBean.setAccountId(acc.getRecord_id());
			obAccountBean.setAccountUUID(acc.getUuid());
			obAccountBean.setP2pFlag(acc.getP2p_flag());
			obAccountBean.setProxy_data(acc.getProxy_data());
			obAccountBean.setProxy_type(acc.getProxy_type());
			obAccountBeanList.add(obAccountBean);
			i++;
			
						
		}

		categorySourceOfFund.setObAccountList(obAccountBeanList);
		responseBean.getCategoryList().add(categorySourceOfFund);
	}
}
