package com.silverlake.mleb.mcb.module.func.transaction;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.ObBankBean;
import com.silverlake.mleb.mcb.bean.ObBeneAccountBean;
import com.silverlake.mleb.mcb.bean.ObTransactionBeneficiaryRequest;
import com.silverlake.mleb.mcb.bean.ObTransactionBeneficiaryResponse;
import com.silverlake.mleb.mcb.bean.ObTransactionBeneficiarySessionCache;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.module.common.CacheSessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.exchangeRate.ExchangeRate;
import com.silverlake.mleb.mcb.module.vc.transaction.FastBeneficiaryRequestData;
import com.silverlake.mleb.mcb.module.vc.transaction.FastBeneficiaryResponseData;
import com.silverlake.mleb.mcb.module.vc.transaction.OnlineBeneficiaryRequestData;
import com.silverlake.mleb.mcb.module.vc.transaction.OnlineBeneficiaryResponseData;
import com.silverlake.mleb.mcb.module.vc.transaction.TransactionBankRefRequestData;
import com.silverlake.mleb.mcb.module.vc.transaction.TransactionBankRefResponseData;

/**
 * Purpose: Get transaction beneficiary details for OAT and IFT (Bank internal account) or OLT (Other bank account)
 * When it is multi currency, get the support currency list (supported by IFT/OAT)
 * 
 * Omni Web Services:
 * acct_mgmt/inquiry
 * fxrate/inquiry
 * transaction/inquiry/olt
 * 
 * @author Alex Loo
 *
 */
@Service
public class TransactionBeneficiaryInquiry extends CacheSessionFuncServices<ObTransactionBeneficiaryRequest, ObTransactionBeneficiaryResponse, ObTransactionBeneficiarySessionCache>{
	private static Logger log = LogManager.getLogger(TransactionBeneficiaryInquiry.class);
	
	@Override
	public void processInternalWithCache(String locale, String sessionId, String trxId, ObTransactionBeneficiaryRequest requestBean, ObTransactionBeneficiaryResponse responseBean, ObTransactionBeneficiarySessionCache cacheBean, VCGenericService vcService) throws Exception {
		if(requestBean.getTransferServiceType().equals(MiBConstant.TRANS_SERVICE_TYPE_IFT) || requestBean.getTransferServiceType().equals(MiBConstant.TRANS_SERVICE_TYPE_OAT)){
			processInternalAccount(locale, sessionId, trxId, requestBean, responseBean, cacheBean, vcService);
		}else if(requestBean.getTransferServiceType().equals(MiBConstant.TRANS_SERVICE_TYPE_OLT)){
			processOtherBankAccount(locale, sessionId, trxId, requestBean, responseBean, cacheBean, vcService);
		}else if(requestBean.getTransferServiceType().equals(MiBConstant.TRANS_SERVICE_TYPE_FAST)){
			processOtherBankAccountFAST(locale, sessionId, trxId, requestBean, responseBean, cacheBean, vcService);
		}else {
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
			log.info("Unsupported transfer service type");
			return;
		}
	}
	
	private void processOtherBankAccount(String locale, String sessionId, String trxId, ObTransactionBeneficiaryRequest requestBean, ObTransactionBeneficiaryResponse responseBean, ObTransactionBeneficiarySessionCache cacheBean, VCGenericService vcService) throws Exception{
		if(requestBean.getDebitAccountNo() == null || requestBean.getDebitAccountNo().isEmpty() 
				|| requestBean.getBankCode() == null || requestBean.getBankCode().isEmpty()
				|| requestBean.getBankName() == null || requestBean.getBankName().isEmpty()
				|| requestBean.getAccountNo() == null || requestBean.getAccountNo().isEmpty()){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
			log.info("Incomplete parameters for OLT");
			return;
		}
		String bankRefNo = "SYSTEMREF000";//Set value to SYSTEMREF000 for using the service just to online checking beneficiary name on other domestic bank account.
		//Generate bank reference number when it is OLT
		if(requestBean.getIsInquiry() == null || !requestBean.getIsInquiry()) {
			TransactionBankRefRequestData requestBankRefData = new TransactionBankRefRequestData();
			requestBankRefData.setOrg_cd(requestBean.getObUser().getOrgId());
			requestBankRefData.setUsr_cd(requestBean.getObUser().getLoginId());
			requestBankRefData.setProd_cd(requestBean.getTransferServiceType());
			VCResponse<TransactionBankRefResponseData> vcBankRefResponse = vcService.callOmniVC(
					VCMethodConstant.REST_METHODS.TRANSACTION_GET_BANK_REFERENCE, 
					requestBankRefData, 
					TransactionBankRefResponseData.class, 
					true);
			if(processVCResponseError(vcBankRefResponse, responseBean)){
				return;
			}
			TransactionBankRefResponseData responseBankRefData = vcBankRefResponse.getData();
			bankRefNo = responseBankRefData.getBank_ref();
		}
		//Get beneficiary data from velo
		OnlineBeneficiaryRequestData accInquiryRequestData = new OnlineBeneficiaryRequestData();
		accInquiryRequestData.setOrg_cd(requestBean.getObUser().getOrgId());
		accInquiryRequestData.setUsr_cd(requestBean.getObUser().getLoginId());
		accInquiryRequestData.setDebit_acct_no(requestBean.getDebitAccountNo());
		accInquiryRequestData.setDebit_acct_ccy(requestBean.getAccountCcy());
		accInquiryRequestData.setBene_acct_no(requestBean.getAccountNo());
		accInquiryRequestData.setBank_ref(bankRefNo);
		accInquiryRequestData.setBene_bank_code(requestBean.getBankCode());
		//Set to today date
		accInquiryRequestData.setEffective_date(new SimpleDateFormat(MiBConstant.YYYYMMDD_FORMAT).format(new Date()));
		//Set to 10000 as default due to amount cannot be determined yet.
		accInquiryRequestData.setAmount(new BigDecimal("10000"));
		//Set to id when locale is in, only for transaction_inquiry_olt
		if(locale.equalsIgnoreCase(MiBConstant.LOCALE_IN)) {
			locale = "id";
		}
		if(locale != null  && locale.toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase())){
			locale = MiBConstant.LOCALE_CN;
		}
		accInquiryRequestData.setLang(locale.toLowerCase());
		locale = MiBConstant.LOCALE_IN;
		
		VCResponse<OnlineBeneficiaryResponseData> vcAccInquiryResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.TRANSACTION_INQUIRY_OLT, 
				accInquiryRequestData, 
				OnlineBeneficiaryResponseData.class, 
				true);
		if(processVCResponseError(vcAccInquiryResponse, responseBean)){
			return;
		}
		
		OnlineBeneficiaryResponseData accInquiryResponseData = vcAccInquiryResponse.getData();
		
		ObBeneAccountBean obAccountBean = new ObBeneAccountBean();
		obAccountBean.setBeneAccountCcy(requestBean.getAccountCcy());
		obAccountBean.setBeneAccountNo(requestBean.getAccountNo());
		obAccountBean.setBeneAccountName(accInquiryResponseData.getBeneficiary_name());
		obAccountBean.setBeneNickName(accInquiryResponseData.getBeneficiary_name());
		
		responseBean.setBeneDetails(obAccountBean);
		
		cacheBean.setOnlineBeneficiaryResponseData(accInquiryResponseData);
		cacheBean.setOnlineBeneficiaryRequestData(accInquiryRequestData);
		cacheBean.setBeneBankDetails(new ObBankBean());
		cacheBean.getBeneBankDetails().setBankCode(requestBean.getBankCode());
		cacheBean.getBeneBankDetails().setBankName(requestBean.getBankName());
	}
	
	private void processOtherBankAccountFAST(String locale, String sessionId, String trxId, ObTransactionBeneficiaryRequest requestBean, ObTransactionBeneficiaryResponse responseBean, ObTransactionBeneficiarySessionCache cacheBean, VCGenericService vcService) throws Exception{
		if(requestBean.getProxy_type() == null || requestBean.getProxy_type().isEmpty()){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
			log.info("Incomplete parameters for FAST");
			return;
		}
		if (requestBean.getProxy_type().equalsIgnoreCase("FAST01")) {
			if (requestBean.getAccountNo() == null || requestBean.getAccountNo().isEmpty() ||
					requestBean.getBankCode() == null || requestBean.getBankCode().isEmpty() ||
					requestBean.getBankName() == null || requestBean.getBankName().isEmpty()) {
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
				log.info("Incomplete parameters for FAST1");
				return;
			}
		}
		String debit_acct_no = "000000000000";//Set value to 000000000000 for using the service just to online checking beneficiary name on other domestic bank account.
		
		//Get beneficiary data from velo
		FastBeneficiaryRequestData fastInquiryRequestData = new FastBeneficiaryRequestData();
		fastInquiryRequestData.setOrg_cd(requestBean.getObUser().getOrgId());
		fastInquiryRequestData.setUsr_cd(requestBean.getObUser().getLoginId());
		fastInquiryRequestData.setProxy_data(requestBean.getProxy_data());
		fastInquiryRequestData.setProxy_type(requestBean.getProxy_type());
		if(requestBean.getDebitAccountNo()!= null){
			fastInquiryRequestData.setDebit_acct_no(requestBean.getDebitAccountNo());
		}else {
			fastInquiryRequestData.setDebit_acct_no(debit_acct_no);
		}
		if(requestBean.getProxy_type().equalsIgnoreCase("FAST01")){
		fastInquiryRequestData.setBene_acct_no(requestBean.getAccountNo());
		fastInquiryRequestData.setBene_bank_id(requestBean.getBankCode());
		fastInquiryRequestData.setBene_bank_name(requestBean.getBankName());
		}
			//Set to 10000 as default due to amount cannot be determined yet.
		fastInquiryRequestData.setAmount(new BigDecimal("10000"));
		fastInquiryRequestData.setAmount_ccy(MiBConstant.CURRENCY_IDR);
		fastInquiryRequestData.setTrx_purpose("01");
		
		
		VCResponse<FastBeneficiaryResponseData> vcAccInquiryResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.TRANSACTION_INQUIRY_FAST, 
				fastInquiryRequestData, 
				FastBeneficiaryResponseData.class, 
				true);
		if(processVCResponseError(vcAccInquiryResponse, responseBean)){
			return;
		}
		
		FastBeneficiaryResponseData fastInquiryResponseData = vcAccInquiryResponse.getData();
		
		ObBeneAccountBean obAccountBean = new ObBeneAccountBean();
		obAccountBean.setBeneAccountCcy(fastInquiryResponseData.getBene_acct_ccy());
		obAccountBean.setBeneAccountNo(fastInquiryResponseData.getBene_acct_no());
		obAccountBean.setBeneAccountName(fastInquiryResponseData.getBene_name());
		obAccountBean.setBeneNickName(fastInquiryResponseData.getBene_name());
		obAccountBean.setBeneficiaryBankCode(fastInquiryResponseData.getBene_bank_id());
		obAccountBean.setBeneficiaryBankName(fastInquiryResponseData.getBene_bank_name());
				
		responseBean.setBeneDetails(obAccountBean);
		
		cacheBean.setFastBeneficiaryResponseData(fastInquiryResponseData);
		cacheBean.setFastBeneficiaryRequestData(fastInquiryRequestData);
		cacheBean.setBeneBankDetails(new ObBankBean());
		cacheBean.getBeneBankDetails().setBankCode(fastInquiryResponseData.getBene_bank_id());
		cacheBean.getBeneBankDetails().setBankName(fastInquiryResponseData.getBene_bank_name());
	}
	
	private void processInternalAccount(String locale, String sessionId, String trxId, ObTransactionBeneficiaryRequest requestBean, ObTransactionBeneficiaryResponse responseBean, ObTransactionBeneficiarySessionCache cacheBean, VCGenericService vcService) throws Exception{
		
		if(requestBean.getAccountNo() == null || requestBean.getAccountNo().isEmpty()){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
			log.info("Incomplete parameters");
			return;
		}
		//Get beneficiary data from velo
		com.silverlake.mleb.mcb.module.vc.accountManagement.RequestData accInquiryRequestData = new com.silverlake.mleb.mcb.module.vc.accountManagement.RequestData();
		accInquiryRequestData.setOrg_cd(requestBean.getObUser().getOrgId());
		accInquiryRequestData.setUsr_cd(requestBean.getObUser().getLoginId());
		accInquiryRequestData.setAcct_no(requestBean.getAccountNo());
		if(requestBean.getAccountCcy() != null && !requestBean.getAccountCcy().isEmpty()){
			accInquiryRequestData.setAcct_ccy(requestBean.getAccountCcy());
			/**
			 * "Y" : Checking for Balance (Default) for balance snapshot
			 * "N" : Check any OCBC Account info for OCBC Funds Transfer (IFT)
			 */
			accInquiryRequestData.setBalance_check("Y");
		}else{
			accInquiryRequestData.setBalance_check("N");
		}
		VCResponse<com.silverlake.mleb.mcb.module.vc.accountManagement.ResponseData> vcAccInquiryResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.ACCOUNT_MANAGEMENT_INQUIRY, 
				accInquiryRequestData, 
				com.silverlake.mleb.mcb.module.vc.accountManagement.ResponseData.class, 
				true);
		if(processVCResponseError(vcAccInquiryResponse, responseBean)){
			return;
		}
		
		com.silverlake.mleb.mcb.module.vc.accountManagement.ResponseData accInquiryResponseData = vcAccInquiryResponse.getData();
		
		ObBeneAccountBean obAccountBean = new ObBeneAccountBean();
		obAccountBean.setBeneAccountCcy(accInquiryResponseData.getAcct_ccy());
		obAccountBean.setBeneAccountNo(accInquiryResponseData.getAcct_no());
		obAccountBean.setBeneAccountName(accInquiryResponseData.getAcct_name());
		obAccountBean.setBeneNickName(accInquiryResponseData.getAcct_alias());
		obAccountBean.setBeneficiaryBankBranch(accInquiryResponseData.getBranch_cd_sibs());
		obAccountBean.setBeneMcBit(accInquiryResponseData.getMcbit());
		obAccountBean.setBeneAccountStatusCode(String.valueOf(accInquiryResponseData.getAcct_status()));
		obAccountBean.setBeneAccountType(accInquiryResponseData.getAcct_type_sibs());
		obAccountBean.setBeneficiaryAvailableBalance((accInquiryResponseData.getBalance_available()!=null)?new BigDecimal(accInquiryResponseData.getBalance_available()):null);
		
		//Multi currency, get all available currency
		if(accInquiryResponseData.getMcbit() != null && accInquiryResponseData.getMcbit().equalsIgnoreCase("Y")){
			List<String> currencyList = new ArrayList<String>();
			//Get target ccy code and query based on today date
			Date now = new Date();
			String nowStr = new SimpleDateFormat(MiBConstant.YYYYMMDD_FORMAT).format(now);
			com.silverlake.mleb.mcb.module.vc.exchangeRate.RequestData vcRetrieveExchangeRate = new com.silverlake.mleb.mcb.module.vc.exchangeRate.RequestData();
			vcRetrieveExchangeRate.setPeriod_from(nowStr);
			vcRetrieveExchangeRate.setPeriod_to(nowStr);
			
			com.silverlake.mleb.mcb.module.vc.exchangeRate.ResponseData responseData;
			VCResponse<com.silverlake.mleb.mcb.module.vc.exchangeRate.ResponseData> vcResponse = vcService.callOmniVC(
					VCMethodConstant.REST_METHODS.EXCHANGE_RATE, 
					vcRetrieveExchangeRate, 
					com.silverlake.mleb.mcb.module.vc.exchangeRate.ResponseData.class, 
					true);
			if(processVCResponseError(vcResponse, responseBean)){
				return;
			}
			
			responseData = vcResponse.getData();
			if(responseData.getList_fxrate()!=null){
				for(ExchangeRate temp:responseData.getList_fxrate()){
					currencyList.add(temp.getCcy_code());
				}
			}
			
			obAccountBean.setLsBeneAccountCcyCode(currencyList);
		}
		
		responseBean.setBeneDetails(obAccountBean);
		
		cacheBean.setAccountDetailsResponseData(accInquiryResponseData);
	}
}
