package com.silverlake.mleb.mcb.module.func.payment;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.MapPojo;
import com.silverlake.mleb.mcb.bean.ObAmountOptionBean;
import com.silverlake.mleb.mcb.bean.ObPaymentRequest;
import com.silverlake.mleb.mcb.bean.ObPaymentResponse;
import com.silverlake.mleb.mcb.bean.ObPaymentSessionCache;
import com.silverlake.mleb.mcb.bean.ObSessionCache;
import com.silverlake.mleb.mcb.bean.ObTransactionSourceOfFundSessionCache;
import com.silverlake.mleb.mcb.bean.Payee;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.module.common.CacheSessionFuncServices;
import com.silverlake.mleb.mcb.module.func.RetrieveAppStatInfo_mcb;
import com.silverlake.mleb.mcb.module.vc.accountManagement.ListAccount;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.others.InquiryTransTimeBatchRequestData;
import com.silverlake.mleb.mcb.module.vc.others.InquiryTransTimeBatchResponseData;
import com.silverlake.mleb.mcb.module.vc.transaction.BillDetailsData;
import com.silverlake.mleb.mcb.module.vc.transaction.BillPaymentInquiryRequestData;
import com.silverlake.mleb.mcb.module.vc.transaction.BillPaymentInquiryResponseData;
import com.silverlake.mleb.mcb.module.vc.transaction.DetailData;
import com.silverlake.mleb.mcb.module.vc.transaction.PayeeList;
import com.silverlake.mleb.mcb.module.vc.transaction.PayeeResponseData;
import com.silverlake.mleb.mcb.module.vc.transaction.PurchaseDataList;
import com.silverlake.mleb.mcb.module.vc.transaction.SourceAccountResponseData;
import com.silverlake.mleb.mcb.module.vc.transaction.TransactionBankRefRequestData;
import com.silverlake.mleb.mcb.module.vc.transaction.TransactionBankRefResponseData;

/**
 * Purpose: Check payment cutoff and get it's related parameters
 * First check cut off time, if within cut off returned allowProcess false
 * 
 * Omni Web Services:
 * holiday/calendar
 * holiday/product
 * transaction/check_cot for cutoff time
 * 
 * @author Alex Loo
 */
@Service
public class PaymentInquiryBiller extends CacheSessionFuncServices<ObPaymentRequest, ObPaymentResponse, ObPaymentSessionCache>{
	private static Logger log = LogManager.getLogger(PaymentInquiryBiller.class);
	
	@Override
	public void processInternalWithCache(String locale, String sessionId, String trxId, ObPaymentRequest requestBean, ObPaymentResponse responseBean, ObPaymentSessionCache cacheBean, VCGenericService vcService) throws Exception {
		if(requestBean.getFlowType() < 0 || requestBean.getFlowType() > 2){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
			log.info("Invalid flow type");
			return;
		}
		//Generate bank reference number
		TransactionBankRefRequestData requestBankRefData = new TransactionBankRefRequestData();
		requestBankRefData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestBankRefData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestBankRefData.setProd_cd(requestBean.getBillerType());
		VCResponse<TransactionBankRefResponseData> vcBankRefResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.TRANSACTION_GET_BANK_REFERENCE, 
				requestBankRefData, 
				TransactionBankRefResponseData.class, 
				true);
		if(processVCResponseError(vcBankRefResponse, responseBean)){
			return;
		}
		TransactionBankRefResponseData responseBankRefData = vcBankRefResponse.getData();
		responseBean.setBankRef(responseBankRefData.getBank_ref());
				
		BillPaymentInquiryRequestData requestData = new BillPaymentInquiryRequestData();
		processRequest(locale, requestData, requestBean, responseBean);
		if(responseBean.getObHeader().getStatusCode() != null){
			return;
		}
		
		/*
		 * Request biller details based on the biller type payment or purchase from OMNI
		 * 
		 * if biller type is purchase calling transaction/inquiry/purchase
		 * 
		 * if biller type is payment calling transaction/inquiry/bpm
		*/
		
		BillPaymentInquiryResponseData responseData;
		
		if(requestBean.getPpType() != null && requestBean.getPpType().equalsIgnoreCase(MiBConstant.MIB_PAYMENT)){
			
			VCResponse<BillPaymentInquiryResponseData> vcResponse = vcService.callOmniVC(
					VCMethodConstant.REST_METHODS.TRANSACTION_INQUIRY_PURCHASE, 
					requestData, 
					BillPaymentInquiryResponseData.class, 
					true);
			if(processVCResponseError(vcResponse, responseBean)){
				return;
			}
			
			responseData = vcResponse.getData();
			purchaseprocessResponse(locale, responseData, requestBean, responseBean);
			cacheBean.setBillPaymentInquiryResponseData(responseData);
			
		}else  {	
			
		    VCResponse<BillPaymentInquiryResponseData> vcResponse = vcService.callOmniVC(
				   VCMethodConstant.REST_METHODS.TRANSACTION_INQUIRY_BPM, 
				   requestData, 
				   BillPaymentInquiryResponseData.class, 
				   true);
		   if(processVCResponseError(vcResponse, responseBean)){
			return;
		   }
		
		   responseData = vcResponse.getData();
		   processResponse(locale, responseData, requestBean, responseBean);
		   cacheBean.setBillPaymentInquiryResponseData(responseData);
		}
		
		//Execute Time Batch
		
		InquiryTransTimeBatchRequestData batchRequestData = new InquiryTransTimeBatchRequestData();
		InquiryTransTimeBatchResponseData batchResponseData;
		batchRequestData.setOrg_cd(requestBean.getObUser().getOrgId());
		batchRequestData.setUsr_cd(requestBean.getObUser().getLoginId());
		
		String[] prod_cd_list = requestBean.getBillerType()==null?null:requestBean.getBillerType().split(",");
		if(null!=prod_cd_list)
		{
			batchRequestData.setProd_cd_list(new ArrayList());
			for(String prd_cd:prod_cd_list)
			{
				batchRequestData.getProd_cd_list().add(prd_cd);
			}
		}
		batchRequestData.setLang(locale);
		
		VCResponse<InquiryTransTimeBatchResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.TIME_BATCH_INQUIRY, 
				batchRequestData, 
				InquiryTransTimeBatchResponseData.class, 
				true);
		if(processVCResponseError(vcResponse, responseBean)){
			return;
		}
		batchResponseData = vcResponse.getData();
		
		responseBean.setTime_batch_list(batchResponseData.getTime_batch_list());
	
				 
		   cacheBean.setBillPaymentInquiryRequestData(requestData);
		   cacheBean.setFlowType(requestBean.getFlowType());
	}
	
	private void processRequest(String locale, BillPaymentInquiryRequestData requestData, ObPaymentRequest requestBean, ObPaymentResponse responseBean) throws Exception{
		ObTransactionSourceOfFundSessionCache transactionSOFSessionCache = (ObTransactionSourceOfFundSessionCache)((ObSessionCache)requestBean.getSessionCache().get(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TRANSACTION_SOURCE_OF_FUND.getId()));
		if(transactionSOFSessionCache.getVcSourceResponseData() == null){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			log.info("Transaction source of fund cache is not found");
			return;
		}
		String type = requestBean.getBillerType();
		SourceAccountResponseData sofResponseDataCache = transactionSOFSessionCache.getVcSourceResponseData().get(type);
		if(sofResponseDataCache == null){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			log.info("Transaction source of fund sub cache is not found");
			return;
		}
		boolean isDebitAccountFound = false;
		for(ListAccount acc:sofResponseDataCache.getList_account()){
			if(acc.getUuid().equals(requestBean.getDebitAccountUUID())){
				requestData.setDebit_acct_ccy(acc.getAcct_ccy());
				requestData.setDebit_acct_no(acc.getAcct_no());
				isDebitAccountFound = true;
				break;
			}
		}
		if(!isDebitAccountFound){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
			log.info("Account ID not matched with source of fund list");
			return;
		}
		//Get payee details from cache
		if(requestBean.getBillerId() != null && !requestBean.getBillerId().isEmpty()){
			ObPaymentSessionCache payeeListSessionCache = requestBean.getSessionCache().get(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_PAYMENT_PAYEE_LIST.getId());
			if(payeeListSessionCache == null || payeeListSessionCache.getPayeeResponseData() == null){
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
				log.info("Payee list cache is not found");
				return;
			}
			PayeeResponseData payeeListResponseDataCache = payeeListSessionCache.getPayeeResponseData();
			boolean isPayeeFound = false;
			for(PayeeList acc:payeeListResponseDataCache.getPayee_list()){
				if(acc.getRecord_id().equals(requestBean.getBillerId())){
					requestData.setBilling_id(acc.getBilling_id());
					requestData.setBilling_org_id(acc.getBiller_code());
					isPayeeFound = true;
					break;
				}
			}
			if(!isPayeeFound){
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
				log.info("Biller ID not matched with payee list");
				return;
			}
		}
		//Unsaved bill payment
		else{
			if(type.equalsIgnoreCase(MiBConstant.PAYMENT_BILLER_TYPE_ETAX)){
				if(requestBean.getBillingId() == null || requestBean.getBillingId().isEmpty()){
					responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
					log.info("Incomplete data for unsaved bill payment");
					return;
				}
				requestData.setBilling_org_id("0000000");
			}else{
				if(requestBean.getBillingId() == null || requestBean.getBillingId().isEmpty() ||
						requestBean.getBillerCode() == null || requestBean.getBillerCode().isEmpty()){
					responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
					log.info("Incomplete data for unsaved bill payment");
					return;
				}
				requestData.setBilling_org_id(requestBean.getBillerCode());
			}
			requestData.setBilling_id(requestBean.getBillingId());
		}
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		if(locale.equalsIgnoreCase(MiBConstant.LANG_EN)){
			requestData.setLang("en");
		}else if(locale.toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase())){
			requestData.setLang("cn");
		}
		else{
			requestData.setLang("id");
		}
		requestData.setBank_ref(responseBean.getBankRef());
		requestData.setCust_ref(requestBean.getCustomerRef());
		//flowType 0 = Manual amount input only
		if(requestBean.getFlowType() == 0 && (requestBean.getAmount() == null)){
			requestData.setAmount(new BigDecimal("10000"));//Default to 10k, for first call. (There is second call to check actual amount.)
		}else{
			requestData.setAmount(requestBean.getAmount());
		}
		if(requestBean.getValueDate() == null || requestBean.getValueDate().isEmpty()){
			requestData.setEffective_date(new SimpleDateFormat(MiBConstant.YYYYMMDD_FORMAT).format(new Date()));
		}else{
			requestData.setEffective_date(requestBean.getValueDate());
		}
		requestData.setPpType(requestBean.getPpType());
	}
	
	private void processResponse(String locale, BillPaymentInquiryResponseData responseData, ObPaymentRequest requestBean, ObPaymentResponse responseBean) throws Exception{
		if(requestBean.getFlowType() == 0){
			
		}else {
			responseBean.setAmountOptions(new ArrayList<ObAmountOptionBean>(3));
			if(responseData.getLast_month_inv_amt() != null && responseData.getLast_month_inv_amt().signum() > 0){
				ObAmountOptionBean option = new ObAmountOptionBean();
				option.setAmountDesc(responseData.getLast_month_inv_caption());
				option.setRefNo(responseData.getLast_month_inv_ref());
				option.setAmount(responseData.getLast_month_inv_amt());
				option.setOptionId(MiBConstant.PAYMENT_BILLER_AMOUNT_OPTION_LAST);
				responseBean.getAmountOptions().add(option);
			}
			if(responseData.getTotal_inv_amt() != null && responseData.getTotal_inv_amt().signum() > 0){
				ObAmountOptionBean option = new ObAmountOptionBean();
				option.setAmountDesc(responseData.getTotal_inv_caption());
				option.setRefNo(responseData.getTotal_inv_ref());
				option.setAmount(responseData.getTotal_inv_amt());
				option.setOptionId(MiBConstant.PAYMENT_BILLER_AMOUNT_OPTION_TOTAL);
				responseBean.getAmountOptions().add(option);
			}
			//flowType 1: Amount get from selection only.
			if(requestBean.getFlowType() == 1){
				if(responseBean.getAmountOptions().isEmpty()){
					//Throw no available option error
					responseBean.getObHeader().setStatusCode(MiBConstant.MCB_NO_AVAILABLE_AMOUNT_OPTION);
				}
			}else if(requestBean.getFlowType() == 2){				
				String label = RetrieveAppStatInfo_mcb.getMessageTable(locale).get("LABEL_OTHER_AMOUNT");
				ObAmountOptionBean option = new ObAmountOptionBean();
				option.setAmountDesc(label);
				option.setRefNo("X");
				option.setAmount(BigDecimal.ZERO);
				option.setOptionId(MiBConstant.PAYMENT_BILLER_AMOUNT_OPTION_MANUAL);
				responseBean.getAmountOptions().add(option);
			}
		}
		
		responseBean.setDetails(new ArrayList<MapPojo>());
		BillDetailsData billDetailsData = responseData.getDetails();
		if(billDetailsData.getDetail() != null){
			for(DetailData detailData:billDetailsData.getDetail()){
				responseBean.getDetails().add(new MapPojo(detailData.getLabel(), detailData.getValue()));
			}
		}
	}
	
	
	private void purchaseprocessResponse(String locale, BillPaymentInquiryResponseData responseData, ObPaymentRequest requestBean, ObPaymentResponse responseBean) throws Exception{
		if(requestBean.getFlowType() == 0){
			
		}else {
			
			if(responseData.getPurchase_data_list() != null){
						
				responseBean.setAmountOptions(new ArrayList<ObAmountOptionBean>());
				
				int i = 1; 
				for(PurchaseDataList purchase:responseData.getPurchase_data_list()){
					ObAmountOptionBean option = new ObAmountOptionBean();
					option.setAmountDesc(purchase.getBill_caption());
					option.setRefNo(purchase.getBill_reff());
					option.setAmount(purchase.getBill_amount());
					option.setOptionId("PURCHASE_" + i);
					purchase.setOptionId(option.getOptionId());
					responseBean.getAmountOptions().add(option);
					i++;
				}
			}
		
			if(requestBean.getFlowType() == 1){
				if(responseBean.getAmountOptions().isEmpty()){
					//Throw no available option error
					responseBean.getObHeader().setStatusCode(MiBConstant.MCB_NO_AVAILABLE_AMOUNT_OPTION);
				}
			}else if(requestBean.getFlowType() == 2){				
				String label = RetrieveAppStatInfo_mcb.getMessageTable(locale).get("LABEL_OTHER_AMOUNT");
				ObAmountOptionBean option = new ObAmountOptionBean();
				option.setAmountDesc(label);
				option.setRefNo("X");
				option.setAmount(BigDecimal.ZERO);
				option.setOptionId(MiBConstant.PAYMENT_BILLER_AMOUNT_OPTION_MANUAL);
				responseBean.getAmountOptions().add(option);
			}
		}
		
		responseBean.setDetails(new ArrayList<MapPojo>());
		BillDetailsData billDetailsData = responseData.getDetails();
		if(billDetailsData.getDetail() != null){
			for(DetailData detailData:billDetailsData.getDetail()){
				responseBean.getDetails().add(new MapPojo(detailData.getLabel(), detailData.getValue()));
			}
		}
	}
}
