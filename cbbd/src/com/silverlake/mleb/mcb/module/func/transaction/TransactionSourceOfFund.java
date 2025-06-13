package com.silverlake.mleb.mcb.module.func.transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.ObAccountBean;
import com.silverlake.mleb.mcb.bean.ObTransactionSourceOfFundBean;
import com.silverlake.mleb.mcb.bean.ObTransactionSourceOfFundRequest;
import com.silverlake.mleb.mcb.bean.ObTransactionSourceOfFundResponse;
import com.silverlake.mleb.mcb.bean.ObTransactionSourceOfFundSessionCache;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.entity.GeneralCode;
import com.silverlake.mleb.mcb.entity.dao.GeneralCodeDAO;
import com.silverlake.mleb.mcb.module.common.CacheSessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.accountManagement.ListAccount;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.transaction.SourceAccountRequestData;
import com.silverlake.mleb.mcb.module.vc.transaction.SourceAccountResponseData;
import com.silverlake.mleb.mcb.util.MapperUtil;
import com.silverlake.mleb.mcb.util.PropertiesManager;
import com.silverlake.mleb.mcb.util.StringUtil;

/**
 * Purpose: Get transaction source of fund
 * Supported transfer type
 * 1. ONFX (This for both source and beneficiary)
 * 2. OAT_IFT (Own account transfer and internal fund transfer)
 * 3. XXX (Default option where this value will pass as it's to omni)
 * 
 * Omni Web Services:
 * transaction/source_acct
 * 
 * @author Alex Loo
 *
 */
@Service
public class TransactionSourceOfFund extends CacheSessionFuncServices<ObTransactionSourceOfFundRequest, ObTransactionSourceOfFundResponse, ObTransactionSourceOfFundSessionCache>{
	
	private static final String TRANSFER_SERVICE_OAT_IFT = "OAT_IFT";
	
	@Autowired GeneralCodeDAO gnDao;
	
	@Override
	public void processInternalWithCache(String locale, String sessionId, String trxId, ObTransactionSourceOfFundRequest requestBean, ObTransactionSourceOfFundResponse responseBean, ObTransactionSourceOfFundSessionCache cacheBean, VCGenericService vcService) throws Exception {
		//OCBC NISP Transfer (own account transfer or internal fund transfer)
		if(requestBean.getTransferServiceType().equalsIgnoreCase(TRANSFER_SERVICE_OAT_IFT)){
			processOCBCTransferSourceOfFund(locale, sessionId, trxId, requestBean, responseBean, cacheBean, vcService);
		}else{
			processDefaultSourceOfFund(locale, sessionId, trxId, requestBean, responseBean, cacheBean, vcService);
		}
	}
	
	/**
	 * This scenario will get both OAT and IFT source of fund list and put into cache object for both OAT's and IFT's source of fund list.
	 * @param locale
	 * @param sessionId
	 * @param trxId
	 * @param requestBean
	 * @param responseBean
	 * @param cacheBean
	 * @param vcService
	 * @throws Exception
	 */
	private void processOCBCTransferSourceOfFund(String locale, String sessionId, String trxId, ObTransactionSourceOfFundRequest requestBean, ObTransactionSourceOfFundResponse responseBean, ObTransactionSourceOfFundSessionCache cacheBean, VCGenericService vcService) throws Exception{
		SourceAccountRequestData requestData = new SourceAccountRequestData();
		SourceAccountResponseData responseData1;
		processRequest(requestData, requestBean, MiBConstant.TRANS_SERVICE_TYPE_OAT);
		VCResponse<SourceAccountResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.TRANSACTION_SOURCE_ACCOUNT, 
				requestData, 
				SourceAccountResponseData.class, 
				true);
		if(processVCResponseError(vcResponse, responseBean)){
			return;
		}
		
		responseData1 = vcResponse.getData();
		processResponse(locale, responseData1, responseBean, MiBConstant.TRANS_SERVICE_TYPE_OAT);
		
		requestData = new SourceAccountRequestData();
		SourceAccountResponseData responseData2;
		processRequest(requestData, requestBean, MiBConstant.TRANS_SERVICE_TYPE_IFT);
		vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.TRANSACTION_SOURCE_ACCOUNT, 
				requestData, 
				SourceAccountResponseData.class, 
				true);
		if(processVCResponseError(vcResponse, responseBean)){
			return;
		}
		
		responseData2 = vcResponse.getData();
		processResponse(locale, responseData2, responseBean, MiBConstant.TRANS_SERVICE_TYPE_IFT);
		
		//Linked back the cache if found
		if(requestBean.getSessionCache() != null){
			ObTransactionSourceOfFundSessionCache prevCacheBean = requestBean.getSessionCache().get(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TRANSACTION_SOURCE_OF_FUND.getId());
			if(prevCacheBean != null){
				MapperUtil.copyFields(prevCacheBean, cacheBean);
			}
		}
		if(requestBean.getIsSourceOfFund()){
			Map<String, SourceAccountResponseData> sourceResponseDataList = new HashMap<String, SourceAccountResponseData>(2);
			sourceResponseDataList.put(MiBConstant.TRANS_SERVICE_TYPE_OAT, responseData1);
			sourceResponseDataList.put(MiBConstant.TRANS_SERVICE_TYPE_IFT, responseData2);
			cacheBean.setVcSourceResponseData(sourceResponseDataList);
		}else{
			//This block of code no usage, just to in-sync with isSourceOfFund flag usage. 
			Map<String, SourceAccountResponseData> beneficiaryResponseDataList = new HashMap<String, SourceAccountResponseData>(2);
			beneficiaryResponseDataList.put(MiBConstant.TRANS_SERVICE_TYPE_OAT, responseData1);
			beneficiaryResponseDataList.put(MiBConstant.TRANS_SERVICE_TYPE_IFT, responseData2);
			cacheBean.setVcBeneficiaryResponseData(beneficiaryResponseDataList);
		}
	}
	
	private void processDefaultSourceOfFund(String locale, String sessionId, String trxId, ObTransactionSourceOfFundRequest requestBean, ObTransactionSourceOfFundResponse responseBean, ObTransactionSourceOfFundSessionCache cacheBean, VCGenericService vcService) throws Exception{
		SourceAccountRequestData requestData = new SourceAccountRequestData();
		SourceAccountResponseData responseData;
		processRequest(requestData, requestBean, requestBean.getTransferServiceType());
		VCResponse<SourceAccountResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.TRANSACTION_SOURCE_ACCOUNT, 
				requestData, 
				SourceAccountResponseData.class, 
				true);
		if(processVCResponseError(vcResponse, responseBean)){
			return;
		}
		
		responseData = vcResponse.getData();
		processResponse(locale, responseData, responseBean, requestBean.getTransferServiceType());
		
		//Linked back the cache if found
		if(requestBean.getSessionCache() != null){
			ObTransactionSourceOfFundSessionCache prevCacheBean = requestBean.getSessionCache().get(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TRANSACTION_SOURCE_OF_FUND.getId());
			if(prevCacheBean != null){
				MapperUtil.copyFields(prevCacheBean, cacheBean);
			}
		}
		if(requestBean.getIsSourceOfFund()){
			Map<String, SourceAccountResponseData> sourceResponseDataList = new HashMap<String, SourceAccountResponseData>(2);
			sourceResponseDataList.put(requestBean.getTransferServiceType(), responseData);
			cacheBean.setVcSourceResponseData(sourceResponseDataList);
		}else{
			Map<String, SourceAccountResponseData> beneficiaryResponseDataList = new HashMap<String, SourceAccountResponseData>(2);
			beneficiaryResponseDataList.put(requestBean.getTransferServiceType(), responseData);
			cacheBean.setVcBeneficiaryResponseData(beneficiaryResponseDataList);
		}
	}
	
	private void processRequest(SourceAccountRequestData requestData, ObTransactionSourceOfFundRequest requestBean, String productCode) throws Exception{
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setProd_cd(productCode);
		requestData.setCcy_cd(requestBean.getCurrencyCode());
		requestData.setAcct_no(requestBean.getAccountNo());
	}
	
	private void processResponse(String locale, SourceAccountResponseData responseData, ObTransactionSourceOfFundResponse responseBean, String category) throws Exception{
		if(responseBean.getCategoryList() == null){
			responseBean.setCategoryList(new ArrayList<ObTransactionSourceOfFundBean>(2));
		}
		ObTransactionSourceOfFundBean categorySourceOfFund = new ObTransactionSourceOfFundBean();
		categorySourceOfFund.setCategory(category);
		if(responseData.getList_account() == null || responseData.getList_account().isEmpty()){	
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_NO_ACCOUNT); 
			return;
		}
		
		Map<String, String> groupNumberMap = new HashMap<String, String>();
		String ftCurrencyGrpList = new PropertiesManager().getProperty("ft.currency.group.sorting").toString().trim();
		if(ftCurrencyGrpList!=null && !ftCurrencyGrpList.isEmpty()){
			int index = 1;
			for(String str : ftCurrencyGrpList.split(","))
			{
				str = str.trim();
				groupNumberMap.put(str, Integer.toString(index));
				index++;
			}
		}
		
		List<GeneralCode> rolloverTypeList = null;
		
		List<ObAccountBean> obAccountBeanList = new ArrayList<ObAccountBean>();
		int i=1;
		for(ListAccount acc: responseData.getList_account()){
			String uuid = StringUtil.leftPadString(Integer.toString(i), 5, "0");
			acc.setUuid(uuid);
			ObAccountBean obAccountBean = new ObAccountBean();
			obAccountBean.setAvailableBalances(new BigDecimal(acc.getBalance_available()));
			obAccountBean.setCurrencyCode(acc.getAcct_ccy());
			obAccountBean.setGroupNumber(groupNumberMap.get(acc.getAcct_ccy()));
			obAccountBean.setGroupType(acc.getAcct_ccy());
			obAccountBean.setAccountNumber(acc.getAcct_no());
			obAccountBean.setAccountName(acc.getAcct_name());
			obAccountBean.setAccountAlias(acc.getAcct_alias());
			obAccountBean.setCif(acc.getCif_no());	
			obAccountBean.setAccountType(acc.getAcct_type_sibs());
			obAccountBean.setMcBit(acc.getMcbit());
			obAccountBean.setAccountId(acc.getId());
			obAccountBean.setDebitBranchCode(acc.getBranch_cd_sibs());
			obAccountBean.setAccountUUID(acc.getUuid());
			obAccountBean.setMaturityDate(acc.getMaturity_date());
			obAccountBean.setEffectiveDate(acc.getEffective_date());
			obAccountBean.setInterestRate((
					(acc.getInterest_rate() != null && !acc.getInterest_rate().isEmpty())?
							new BigDecimal(acc.getInterest_rate())
							:null));
			obAccountBean.setInterestAmount(acc.getInterest_amount());
			obAccountBean.setPrincipalAmount((
					(acc.getPrincipal_amout() != null && !acc.getPrincipal_amout().isEmpty())?
							new BigDecimal(acc.getPrincipal_amout())
							:null));
			obAccountBean.setInterestTerm(acc.getInterest_term());
			obAccountBean.setInterestTermCode(acc.getInterest_term_code());
			obAccountBean.setTenor(acc.getTerm());
			obAccountBean.setTenorType(acc.getTerm_cd());
			obAccountBean.setProductCode(acc.getProd_cd_sibs());
			if(acc.getAuto_renewal() != null && !acc.getAuto_renewal().isEmpty()) {
				obAccountBean.setRolloverTypeCode(acc.getAuto_renewal());
				if(rolloverTypeList == null) 
					rolloverTypeList = gnDao.findByGnCodeType(MiBConstant.ROLLOVERTYPE, locale);
				for(GeneralCode gnTmp:rolloverTypeList){
					if(acc.getAuto_renewal().equalsIgnoreCase(gnTmp.getGnCode())){
						obAccountBean.setRolloverTypeDesc(gnTmp.getGnCodeDescription());
						break;
					}
				}
			}
			
				
			obAccountBeanList.add(obAccountBean);
			i++;
		}

		categorySourceOfFund.setObAccountList(obAccountBeanList);
		responseBean.getCategoryList().add(categorySourceOfFund);
	}
}
