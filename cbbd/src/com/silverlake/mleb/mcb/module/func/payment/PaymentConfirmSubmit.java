package com.silverlake.mleb.mcb.module.func.payment;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.GsonBuilder;
import com.silverlake.mleb.mcb.bean.ObPaymentRequest;
import com.silverlake.mleb.mcb.bean.ObPaymentResponse;
import com.silverlake.mleb.mcb.bean.ObPaymentSessionCache;
import com.silverlake.mleb.mcb.bean.ObSessionCache;
import com.silverlake.mleb.mcb.bean.ObTransactionSourceOfFundSessionCache;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.entity.GeneralCode;
import com.silverlake.mleb.mcb.entity.dao.GeneralCodeDAO;
import com.silverlake.mleb.mcb.module.common.CacheSessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.accountManagement.ListAccount;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.transaction.BillPaymentInquiryRequestData;
import com.silverlake.mleb.mcb.module.vc.transaction.BillPaymentInquiryResponseData;
import com.silverlake.mleb.mcb.module.vc.transaction.PaymentSubmitRequestData;
import com.silverlake.mleb.mcb.module.vc.transaction.PaymentSubmitResponseData;
import com.silverlake.mleb.mcb.module.vc.transaction.PurchaseDataList;
import com.silverlake.mleb.mcb.module.vc.transaction.SourceAccountResponseData;
import com.silverlake.mleb.mcb.module.vc.transaction.Transaction;

/**
 * Purpose: Submit payment transaction for BPM (Bill Payment) or ETAX
 * 
 * Omni Web Services:
 * transaction/payment/submit
 * 
 * @author Hemanth
 *
 */
@Service
public class PaymentConfirmSubmit extends CacheSessionFuncServices<ObPaymentRequest, ObPaymentResponse, ObPaymentSessionCache>{
	private static Logger log = LogManager.getLogger(PaymentConfirmSubmit.class);
	
	@Autowired GeneralCodeDAO gnDao;
	
	@Override
	public void processInternalWithCache(String locale, String sessionId, String trxId, ObPaymentRequest requestBean, ObPaymentResponse responseBean, ObPaymentSessionCache cacheBean, VCGenericService vcService) throws Exception {
		PaymentSubmitRequestData requestData = new PaymentSubmitRequestData();
		processRequest(vcService, requestData, requestBean, responseBean);
		if(responseBean.getObHeader().getStatusCode() != null){
			return;
		}
		
		requestData.setValidation_check(MiBConstant.FUNC_TRUE);
		requestData.setDevice_id(requestBean.getObDevice()==null?null:requestBean.getObDevice().getDeviceId());
		requestData.setDevice_os(requestBean.getObDevice()==null?null:requestBean.getObDevice().getOs());
		requestData.setDevice_type(requestBean.getObDevice()==null?null:requestBean.getObDevice().getModel());
		
		//Submit transaction
		PaymentSubmitResponseData responseData;
		VCResponse<PaymentSubmitResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.TRANSACTION_PAYMENT_SUBMIT, 
				requestData, 
				PaymentSubmitResponseData.class, 
				true,
				null,
				requestBean.getIp());
		if(processVCResponseError(vcResponse, responseBean)){
			return;
		}
		responseData = vcResponse.getData();
		processResponse(locale, responseData, requestBean, responseBean);
		
		//If error on processing response, return 
		if(responseBean.getObHeader().getStatusCode() != null){
			return;
		}
	}
	
	private void processRequest(VCGenericService vcService, PaymentSubmitRequestData requestData, ObPaymentRequest requestBean, ObPaymentResponse responseBean) throws Exception{
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
//			if(acc.getId().equals(requestBean.getDebitAccountId())){
			if(acc.getUuid().equals(requestBean.getDebitAccountUUID())){
				requestData.setDebit_acct_ccy(acc.getAcct_ccy());
				requestData.setDebit_acct_no(acc.getAcct_no());
				responseBean.setDebitAccountAliasName(acc.getAcct_alias());
				responseBean.setDebitAccountType(acc.getAcct_type_sibs());//return to FE later
				isDebitAccountFound = true;
				break;
			}
		}
		if(!isDebitAccountFound){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
			log.info("Account ID not matched with source of fund list");
			return;
		}
		
		ObPaymentSessionCache billerInquirySessionCache = requestBean.getSessionCache().get(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_PAYMENT_BILLER_INQUIRY.getId());
		if(billerInquirySessionCache == null || billerInquirySessionCache.getBillPaymentInquiryResponseData() == null){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			log.info("Biller inquiry cache is not found");
			return;
		}
		BillPaymentInquiryRequestData billPaymentInquiryRequestDataCache = billerInquirySessionCache.getBillPaymentInquiryRequestData();
		BillPaymentInquiryResponseData billPaymentInquiryResponseDataCache = billerInquirySessionCache.getBillPaymentInquiryResponseData();
		
		if(type.equalsIgnoreCase(MiBConstant.PAYMENT_BILLER_TYPE_ETAX)){
			requestData.setBilling_org_id("0000000");
			requestData.setProd_cd("ETAX");
		}else{
			requestData.setBilling_org_id(billPaymentInquiryRequestDataCache.getBilling_org_id());
			requestData.setProd_cd("BP");
		}
		requestData.setBilling_id(billPaymentInquiryRequestDataCache.getBilling_id());
		requestData.setBank_ref(billPaymentInquiryRequestDataCache.getBank_ref());
		requestData.setBilling_org_name(requestBean.getBillerName());
		if(requestBean.getCustomerRef() != null && !requestBean.getCustomerRef().isEmpty()) {
			requestData.setCustomer_ref(requestBean.getCustomerRef());
		}else {
			requestData.setCustomer_ref(billPaymentInquiryRequestDataCache.getCust_ref());
		}
		requestData.setSession_id(billPaymentInquiryResponseDataCache.getInq_session_id());
		if(billerInquirySessionCache.getFlowType() == 0){//manual input
			requestData.setBill_ref_no(billPaymentInquiryResponseDataCache.getTotal_inv_ref());
			requestData.setAmount(requestBean.getAmount());
		}else if(billerInquirySessionCache.getFlowType() == 1){//selection only
			if(requestBean.getAmountOptionId().equals(MiBConstant.PAYMENT_BILLER_AMOUNT_OPTION_LAST)){
				requestData.setBill_ref_no(billPaymentInquiryResponseDataCache.getLast_month_inv_ref());
				requestData.setAmount(billPaymentInquiryResponseDataCache.getLast_month_inv_amt());
			}else if(requestBean.getAmountOptionId().equals(MiBConstant.PAYMENT_BILLER_AMOUNT_OPTION_TOTAL)){
				requestData.setBill_ref_no(billPaymentInquiryResponseDataCache.getTotal_inv_ref());
				requestData.setAmount(billPaymentInquiryResponseDataCache.getTotal_inv_amt());
			}else if(requestBean.getAmountOptionId().equals(MiBConstant.PAYMENT_BILLER_AMOUNT_OPTION_MANUAL)){
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
				log.info("option id not valid for flow type 1 (selection only)");
				return;
			}else if(requestBean.getAmountOptionId().contains("PURCHASE")) {
				for(PurchaseDataList purchase:billPaymentInquiryResponseDataCache.getPurchase_data_list()) {
					if(purchase.getOptionId().equalsIgnoreCase(requestBean.getAmountOptionId())) {
						requestData.setBill_ref_no(purchase.getBill_reff());
						requestData.setAmount(purchase.getBill_amount());
						break;
					}
				}
			}
		}else if(billerInquirySessionCache.getFlowType() == 2){//selection or manual input
			if(requestBean.getAmountOptionId().equals(MiBConstant.PAYMENT_BILLER_AMOUNT_OPTION_LAST)){
				requestData.setBill_ref_no(billPaymentInquiryResponseDataCache.getLast_month_inv_ref());
				requestData.setAmount(billPaymentInquiryResponseDataCache.getLast_month_inv_amt());
			}else if(requestBean.getAmountOptionId().equals(MiBConstant.PAYMENT_BILLER_AMOUNT_OPTION_TOTAL)){
				requestData.setBill_ref_no(billPaymentInquiryResponseDataCache.getTotal_inv_ref());
				requestData.setAmount(billPaymentInquiryResponseDataCache.getTotal_inv_amt());
			}else if(requestBean.getAmountOptionId().equals(MiBConstant.PAYMENT_BILLER_AMOUNT_OPTION_MANUAL)){
				requestData.setBill_ref_no("X"+requestBean.getAmount().toPlainString());
				requestData.setAmount(requestBean.getAmount());
			}else if(requestBean.getAmountOptionId().contains("PURCHASE")) {
				for(PurchaseDataList purchase:billPaymentInquiryResponseDataCache.getPurchase_data_list()) {
					if(purchase.getOptionId().equalsIgnoreCase(requestBean.getAmountOptionId())) {
						requestData.setBill_ref_no(purchase.getBill_reff());
						requestData.setAmount(purchase.getBill_amount());
						break;
					}
				}
			}
		}
		if(billPaymentInquiryResponseDataCache.getDetails() != null){
			String responseDataStr = new GsonBuilder().create().toJson(billPaymentInquiryResponseDataCache.getDetails());
			requestData.setResponse_data(responseDataStr);
		}
		
		if(requestBean.getIsSavePayee() != null && requestBean.getIsSavePayee()){
			requestData.setSave_template(requestBean.getIsSavePayee());
			requestData.setTemplate_name(requestBean.getPayeeNickName());
		}else{
			requestData.setSave_template(false);
		}
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setValue_date(requestBean.getValueDate());
		//set the amount currency to debit account currency.
		requestData.setAmount_ccy(requestData.getDebit_acct_ccy());
		
		//set lang QC1383  
		if(requestBean.getLang().equalsIgnoreCase("id")) {
			requestData.setLang("id");
		} else if(requestBean.getLang().equalsIgnoreCase(MiBConstant.LOCALE_EN)) {
			requestData.setLang("en");
		} else if(requestBean.getLang().toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase())){
			requestData.setLang("cn");
		} else {
			log.info("Unknown localCode, only know lang ID and EN ");
			return;
		}
		
		//Set notification requestData
		if(!processNotificationRequest(requestBean, responseBean, requestData)){
			return;
		}
		
		//Recurring settings
		processRecurringRequest(requestBean, requestData);
		
		if(requestBean.getExec_time_batch_cd() != null &&  !requestBean.getExec_time_batch_cd().isEmpty()){
			requestData.setExec_time_batch_cd(requestBean.getExec_time_batch_cd());
		}else {
			requestData.setExec_time_batch_cd("B0000");
		}
	}
	
	private void processResponse(String locale, PaymentSubmitResponseData responseData, ObPaymentRequest requestBean, ObPaymentResponse responseBean) throws Exception{
		if(responseData.getTrx_data() == null){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_NO_RESULT);
			return;
		}
		Transaction trxData = responseData.getTrx_data();
		responseBean.setRefId(responseData.getTrx_data().getBank_ref());
		responseBean.setCustomerRefNo(trxData.getCustomer_ref());
		responseBean.setDebitAccountNo(trxData.getDebit_acct_no());
		responseBean.setDebitAccountCcy(trxData.getDebit_acct_ccy());
		responseBean.setBillingId(trxData.getBilling_id());
		responseBean.setBillerName(trxData.getBilling_org_name());
		responseBean.setValueDate(trxData.getValue_date());
		responseBean.setAmount(trxData.getAmount());
		responseBean.setAmountCcy(trxData.getAmount_ccy());
		responseBean.setBankRef(trxData.getBank_ref());
		
		responseBean.setIsRecurring(requestBean.getIsRecurring());
		responseBean.setRecurringType(requestBean.getRecurringType());
		responseBean.setRecurringValue(requestBean.getRecurringValue());
		responseBean.setRecurringStartDate(requestBean.getRecurringStartDate());
		responseBean.setRecurringEndDate(requestBean.getRecurringEndDate());
		
		responseBean.setTrxStatusCode(trxData.getTrx_status());
		responseBean.setTrxStatusDesc(getTrxStatusDesc(locale, trxData.getTrx_status()));
	}
	
	public String getTrxStatusDesc(String locale, String trxStatus) throws Exception{
		if(trxStatus == null){
			return null;
		}
		if(locale.equalsIgnoreCase(MiBConstant.LOCALE_IN)){
			GeneralCode gnCode = gnDao.findByGnCodeAndGnCodeType(trxStatus, MiBConstant.TRANXSTATUSCODE_IN);
			if(gnCode != null) return gnCode.getGnCodeDescription();
		}else if(locale != null  && locale.toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase())){
			GeneralCode gnCode = gnDao.findByGnCodeAndGnCodeType(trxStatus, MiBConstant.TRANXSTATUSCODE_CN);
			if(gnCode != null) return gnCode.getGnCodeDescription();
		}else{
			GeneralCode gnCode = gnDao.findByGnCodeAndGnCodeType(trxStatus, MiBConstant.TRANXSTATUSCODE);
			if(gnCode != null) return gnCode.getGnCodeDescription();
		}
		return null;
	}
	
	private void processRecurringRequest(ObPaymentRequest requestBean, PaymentSubmitRequestData requestData){
		//Recurring settings
		if(requestBean.getIsRecurring() != null && requestBean.getIsRecurring()){
			requestData.setSave_recurring(true);
			/*
			 * D0 - day
			 * D1 - date
			 * D2 - daily
			 */
			requestData.setRecurring_type(requestBean.getRecurringType());
			/*
			 * When Type = 'D0' 1 - SUNDAY, 2 - MONDAY ... 7 - SATURDAY 
			 * When Type = 'D1' Specify Date (1 to 31) 
			 * When Type = 'D2' Specify the recurring will be occurs every n days, n = 1-100
			 */
			requestData.setRecurring_value(requestBean.getRecurringValue());
			requestData.setRecurring_start(requestBean.getRecurringStartDate());
			requestData.setRecurring_end(requestBean.getRecurringEndDate());
			requestData.setRecurring_exec_time_batch_cd(requestBean.getRecurring_exec_time_batch_cd());
		}else{
			requestData.setSave_recurring(false);
		}
	}
	
	private boolean processNotificationRequest(ObPaymentRequest requestBean, ObPaymentResponse responseBean, PaymentSubmitRequestData requestData){
		if(requestBean.getIsSendSenderNotification() != null && requestBean.getIsSendSenderNotification()){
			if(requestBean.getSenderNotificationEmail() == null || requestBean.getSenderNotificationEmail().isEmpty()){
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
				log.info("Sender notification email address cannot be empty");
				return false;
			}
			requestData.setSend_notif_sender(requestBean.getIsSendSenderNotification());
			requestData.setNotif_sender_email(requestBean.getSenderNotificationEmail());
			requestData.setNotif_sender_completed((requestBean.getIsSenderNotifyCompleted() != null)?requestBean.getIsSenderNotifyCompleted():false);
			requestData.setNotif_sender_rejected((requestBean.getIsSenderNotifyRejected() != null)?requestBean.getIsSenderNotifyRejected():false);
			requestData.setNotif_sender_suspected((requestBean.getIsSenderNotifySuspected() != null)?requestBean.getIsSenderNotifySuspected():false);
		}
		
		return true;
	}
}
