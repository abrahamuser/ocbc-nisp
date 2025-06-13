package com.silverlake.mleb.mcb.module.func.transaction;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.GsonBuilder;
import com.silverlake.mleb.mcb.bean.ObBankBean;
import com.silverlake.mleb.mcb.bean.ObBeneAccountBean;
import com.silverlake.mleb.mcb.bean.ObSessionCache;
import com.silverlake.mleb.mcb.bean.ObTNCCache;
import com.silverlake.mleb.mcb.bean.ObTransactionBeneficiarySessionCache;
import com.silverlake.mleb.mcb.bean.ObTransactionRequest;
import com.silverlake.mleb.mcb.bean.ObTransactionResponse;
import com.silverlake.mleb.mcb.bean.ObTransactionSourceOfFundSessionCache;
import com.silverlake.mleb.mcb.bean.administration.ObAdminBeneficiaryListResponse;
import com.silverlake.mleb.mcb.bean.administration.ObAdminBeneficiaryListSessionCache;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.entity.GeneralCode;
import com.silverlake.mleb.mcb.entity.dao.GeneralCodeDAO;
import com.silverlake.mleb.mcb.module.common.CacheSessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.accountManagement.ListAccount;
import com.silverlake.mleb.mcb.module.vc.administration.BeneficiaryListResponseData;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.others.BankListInfo;
import com.silverlake.mleb.mcb.module.vc.others.BankListRequestData;
import com.silverlake.mleb.mcb.module.vc.others.BankListResponseData;
import com.silverlake.mleb.mcb.module.vc.others.TNCRequestData;
import com.silverlake.mleb.mcb.module.vc.others.TNCResponseData;
import com.silverlake.mleb.mcb.module.vc.others.ValidateValueDateRequestData;
import com.silverlake.mleb.mcb.module.vc.others.ValidateValueDateResponseData;
import com.silverlake.mleb.mcb.module.vc.transaction.BeneficiaryList;
import com.silverlake.mleb.mcb.module.vc.transaction.BeneficiaryResponseData;
import com.silverlake.mleb.mcb.module.vc.transaction.FastBeneficiaryRequestData;
import com.silverlake.mleb.mcb.module.vc.transaction.FastBeneficiaryResponseData;
import com.silverlake.mleb.mcb.module.vc.transaction.OnlineBeneficiaryRequestData;
import com.silverlake.mleb.mcb.module.vc.transaction.OnlineBeneficiaryResponseData;
import com.silverlake.mleb.mcb.module.vc.transaction.SourceAccountResponseData;
import com.silverlake.mleb.mcb.module.vc.transaction.Transaction;
import com.silverlake.mleb.mcb.module.vc.transaction.TransactionBankRefRequestData;
import com.silverlake.mleb.mcb.module.vc.transaction.TransactionBankRefResponseData;
import com.silverlake.mleb.mcb.module.vc.transaction.TransactionSubmitRequestData;
import com.silverlake.mleb.mcb.module.vc.transaction.TransactionSubmitResponseData;
import com.silverlake.mleb.mcb.util.GsonUtil;

/**
 * Purpose: Submit the fund transfer
 * 
 * Omni Web Services:
 * transaction/bank_ref - olt usage
 * transaction/fundstransfer/submit
 * transaction/inquiry/olt - olt usage
 * others/tnc_confirm
 * acct_mgmt/inquiry - IFT usage
 * transaction/check_value_date - Check value date
 * 
 * @author Alex Loo
 *
 */
@Service
public class TransactionFundTransferSubmit extends CacheSessionFuncServices<ObTransactionRequest, ObTransactionResponse, ObSessionCache>{
	private static Logger log = LogManager.getLogger(TransactionFundTransferSubmit.class);
	
	@Autowired GeneralCodeDAO gnDao;
	
	@Override
	public void processInternalWithCache(String locale, String sessionId, String trxId, ObTransactionRequest requestBean, ObTransactionResponse responseBean, ObSessionCache cacheBean, VCGenericService vcService) throws Exception {
		TransactionSubmitRequestData requestData = new TransactionSubmitRequestData();
		if(requestBean.getTransferServiceType().equals(MiBConstant.TRANS_SERVICE_TYPE_OAT) || 
				requestBean.getTransferServiceType().equals(MiBConstant.TRANS_SERVICE_TYPE_IFT)){
			processRequestOAT_IFT(vcService, requestData, requestBean, responseBean);
		}else if(requestBean.getTransferServiceType().equals(MiBConstant.TRANS_SERVICE_TYPE_OLT)){
			processRequestOLT(locale, vcService, requestData, requestBean, responseBean);
		}else if(requestBean.getTransferServiceType().equals(MiBConstant.TRANS_SERVICE_TYPE_LLG) || 
				requestBean.getTransferServiceType().equals(MiBConstant.TRANS_SERVICE_TYPE_RTGS)){
			processRequestLLG_RTGS(vcService, requestData, requestBean, responseBean);
		}else if(requestBean.getTransferServiceType().equals(MiBConstant.TRANS_SERVICE_TYPE_TT)){
			processRequestTT(vcService, requestData, requestBean, responseBean);
		}else if(requestBean.getTransferServiceType().equals(MiBConstant.TRANS_SERVICE_TYPE_FAST)){
			processRequestFAST(locale, vcService, requestData, requestBean, responseBean);
		}else {
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
			log.info("Unsupported transaction service type");
			return;
		}
		if(responseBean.getObHeader().getStatusCode() != null){
			return;
		}
		
		boolean isCrossCcy = false;
		if(requestData.getFx_type() != null && !requestData.getFx_type().isEmpty()){
			isCrossCcy = true;
		}
		
		//Validate the value date
		processValueDateChecking(requestData, requestBean, responseBean, vcService);
		if(responseBean.getObHeader().getStatusCode() != null){
			return;
		}
		
		if(requestBean.getExec_time_batch_cd() != null &&  !requestBean.getExec_time_batch_cd().isEmpty()){
			requestData.setExec_time_batch_cd(requestBean.getExec_time_batch_cd());
		}else {
			requestData.setExec_time_batch_cd("B0000");
		}
		
		if(requestBean.getAdditional_info() != null &&  !requestBean.getAdditional_info().isEmpty()){
			requestData.setAdditional_info(requestBean.getAdditional_info());
		}
		
		requestData.setDevice_id(requestBean.getObDevice()==null?null:requestBean.getObDevice().getDeviceId());
		requestData.setDevice_os(requestBean.getObDevice()==null?null:requestBean.getObDevice().getOs());
		requestData.setDevice_type(requestBean.getObDevice()==null?null:requestBean.getObDevice().getModel());
		
		//Submit transaction
		TransactionSubmitResponseData responseData;
		VCResponse<TransactionSubmitResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.TRANSACTION_SUBMIT, 
				requestData, 
				TransactionSubmitResponseData.class, 
				true,
				requestBean.getTransferServiceType().toLowerCase()+
				((isCrossCcy)?"c":""),
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
		
		//Send the term and condition confirmation to omni and do not interrupt successful transaction
		//Only term and condition is required for cross ccy transfer (Destination not IDR only, therefore LLG and RTGS is impossible to have this happened)
		if(requestData.getDebit_acct_ccy().equals(MiBConstant.CURRENCY_IDR) && !requestData.getBene_acct_ccy().equals(MiBConstant.CURRENCY_IDR)){
			ObTNCCache tncCache = (ObTNCCache)(ObSessionCache)requestBean.getSessionCache().get(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TNC_GET.getId());
			if(tncCache != null){
				try{
					TNCRequestData tncRequestData = new TNCRequestData();
					tncRequestData.setOrg_cd(requestBean.getObUser().getOrgId());
					tncRequestData.setUsr_cd(requestBean.getObUser().getLoginId());
					tncRequestData.setTnc_type(tncCache.getRequestData().getTncType());
					tncRequestData.setTxn_reference_no(responseData.getTrx_data().getBank_ref());
					tncRequestData.setFile(tncCache.getOmniResponse().getFile());
					vcService.callOmniVC(
							VCMethodConstant.REST_METHODS.OTHERS_TNC_CONFIRM, 
							tncRequestData, 
							TNCResponseData.class, 
							true);
					//Do not care about the negative response of tnc
				}catch(Exception e){
					log.catching(e);
				}
			}
		}
	}
	
	private void processRequestOAT_IFT(VCGenericService vcService, TransactionSubmitRequestData requestData, ObTransactionRequest requestBean, ObTransactionResponse responseBean) throws Exception{
		ObTransactionSourceOfFundSessionCache transactionSOFSessionCache = (ObTransactionSourceOfFundSessionCache)((ObSessionCache)requestBean.getSessionCache().get(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TRANSACTION_SOURCE_OF_FUND.getId()));
		if(transactionSOFSessionCache.getVcSourceResponseData() == null){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			log.info("Transaction source of fund cache is not found");
			return;
		}
		String transferServiceType = requestBean.getTransferServiceType();
		SourceAccountResponseData sofResponseDataCache = transactionSOFSessionCache.getVcSourceResponseData().get(transferServiceType);
		if(sofResponseDataCache == null){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			log.info("Transaction source of fund sub cache is not found");
			return;
		}
		//Only IFT is required to get the beneficiary details from cache (FE called before hand)
		ObTransactionBeneficiarySessionCache beneficiaryInquirySessionCache = (ObTransactionBeneficiarySessionCache)((ObSessionCache)requestBean.getSessionCache().get(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TRANSACTION_BENEFICIARY_INQUIRY.getId()));
		if(transferServiceType.equals(MiBConstant.TRANS_SERVICE_TYPE_IFT)){
			if(beneficiaryInquirySessionCache == null || beneficiaryInquirySessionCache.getAccountDetailsResponseData() == null){
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
				log.info("Transaction beneficiary inquiry cache is not found");
				return;
			}
		}
		boolean isDebitAccountFound = false;
		for(ListAccount acc:sofResponseDataCache.getList_account()){
//			if(acc.getId().equals(requestBean.getDebitAccountId())){
			if(acc.getUuid().equals(requestBean.getDebitAccountUUID())){
				requestData.setDebit_acct_ccy(acc.getAcct_ccy());
				requestData.setDebit_acct_no(acc.getAcct_no());
				responseBean.setDebitAccountType(acc.getAcct_type_sibs());//return to FE later
				responseBean.setDebitAccountAliasName(acc.getAcct_alias());//return to FE later
				isDebitAccountFound = true;
				break;
			}
		}
		if(!isDebitAccountFound){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
			log.info("Account ID not matched with source of fund list");
			return;
		}
		//Bene account select from list
		if(requestBean.getBeneAccountUUID()!= null && !requestBean.getBeneAccountUUID().isEmpty()){
			boolean isBeneAccountFound = false;
			//OAT beneficiary use back it's source of fund list
			if(transferServiceType.equals(MiBConstant.TRANS_SERVICE_TYPE_OAT)){
				if(requestBean.getBeneAccountUUID().equals(requestBean.getDebitAccountUUID())){
					responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
					log.info("Bene account and Source account cannot be same.");
					return;
				}
				for(ListAccount acc:sofResponseDataCache.getList_account()){
					if(acc.getUuid().equals(requestBean.getBeneAccountUUID())){
						requestData.setBene_acct_no(acc.getAcct_no());
						requestData.setBene_acct_ccy(acc.getAcct_ccy());
						requestData.setBene_acct_mc_bit(acc.getMcbit());
						requestData.setBene_acct_type(acc.getAcct_type_sibs());
						requestData.setBene_acct_branch(acc.getBranch_cd_sibs());
						requestData.setBene_acct_status(String.valueOf(acc.getAcct_status()));
						requestData.setBene_name(acc.getAcct_name());
						responseBean.setBeneAccountType(acc.getAcct_type_sibs());//return to FE later
						responseBean.setBeneAccountAliasName(acc.getAcct_alias());//return to FE later
						responseBean.setBeneAccountName(acc.getAcct_name());//return to FE later
						//Optional
//						requestData.setBene_email(acc.getEmail_address());
//						requestData.setBene_phone(acc.getPhone_number());
//						requestData.setBene_address1(acc.getBank_address1());
//						requestData.setBene_address2(acc.getBank_address2());
//						requestData.setBene_address3(acc.getBank_address3());
//						requestData.setBene_bank_code(acc.getBank_code());
//						requestData.setBene_bank_name(acc.getBank_name());
//						requestData.setBene_bank_network_id(acc.getBank_network_clearing_code());
//						requestData.setBene_bank_branch(acc.getBank_branch_name());
//						requestData.setBene_bank_country(acc.getBank_country_code());
						isBeneAccountFound = true;
						break;
					}
				}
			}
			//IFT beneficiary list get from TRANSACTION BENEFICIARIES
			else{
				
				if(requestBean.getBeneAccountUUID().startsWith("B")){
				ObTransactionBeneficiarySessionCache transactionBeneficiarySessionCache = (ObTransactionBeneficiarySessionCache)((ObSessionCache)requestBean.getSessionCache().get(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TRANSACTION_BENEFICIARIES.getId()));
				if(transactionBeneficiarySessionCache.getVcBeneficiaryResponseData() == null){
					responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
					log.info("Transaction beneficiary list cache is not found");
					return;
				}
				BeneficiaryResponseData beneficiaryAcctResponseDataCache = transactionBeneficiarySessionCache.getVcBeneficiaryResponseData().get(transferServiceType);
				for(BeneficiaryList acc:beneficiaryAcctResponseDataCache.getBeneficiary_list()){
					if(acc.getUuid().equals(requestBean.getBeneAccountUUID())){
						requestData.setBene_acct_no(acc.getAccount_number());
						requestData.setBene_acct_ccy(acc.getAccount_currency());
						
						com.silverlake.mleb.mcb.module.vc.accountManagement.ResponseData accInquiryResponseData = beneficiaryInquirySessionCache.getAccountDetailsResponseData();
						if(!accInquiryResponseData.getAcct_no().equals(acc.getAccount_number())){
							responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
							log.info("Transaction beneficiary inquiry cache account no not matched");
							return;
						}
						requestData.setBene_name(accInquiryResponseData.getAcct_name());
						requestData.setBene_acct_mc_bit(accInquiryResponseData.getMcbit());
						requestData.setBene_acct_type(accInquiryResponseData.getAcct_type_sibs());
						requestData.setBene_acct_branch(accInquiryResponseData.getBranch_cd_sibs());
						requestData.setBene_acct_status(String.valueOf(accInquiryResponseData.getAcct_status()));
						requestData.setBene_id(acc.getRecord_id());
						responseBean.setBeneAccountType(accInquiryResponseData.getAcct_type_sibs());//return to FE later
						responseBean.setBeneAccountAliasName(acc.getNick_name());//return to FE later
						responseBean.setBeneAccountName(accInquiryResponseData.getAcct_name());//return to FE later
						isBeneAccountFound = true;
						break;
					}
				}
			 }
				if(requestBean.getBeneAccountUUID().startsWith("AB")){
					ObAdminBeneficiaryListSessionCache adminBeneficiaryListSessionCache = (ObAdminBeneficiaryListSessionCache)((ObSessionCache)requestBean.getSessionCache().get(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_ADMINISTRATION_BENEFICIARIES.getId()));
					if(adminBeneficiaryListSessionCache.getBeneficiaryListResponseData() == null){
						responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
						log.info("Transaction beneficiary list cache is not found");
						return;
					}
					ObAdminBeneficiaryListResponse beneficiaryAcctResponseDataCache = adminBeneficiaryListSessionCache.getBeneficiaryListResponseData();
					for(ObBeneAccountBean acc:beneficiaryAcctResponseDataCache.getBeneficiaryList()){
						if(acc.getAccountUUID().equals(requestBean.getBeneAccountUUID())){
							requestData.setBene_acct_no(acc.getBeneAccountNo());
							requestData.setBene_acct_ccy(acc.getBeneAccountCcy());
							
							com.silverlake.mleb.mcb.module.vc.accountManagement.ResponseData accInquiryResponseData = beneficiaryInquirySessionCache.getAccountDetailsResponseData();
							if(!accInquiryResponseData.getAcct_no().equals(acc.getBeneAccountNo())){
								responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
								log.info("Transaction beneficiary inquiry cache account no not matched");
								return;
							}
							requestData.setBene_name(accInquiryResponseData.getAcct_name());
							requestData.setBene_acct_mc_bit(accInquiryResponseData.getMcbit());
							requestData.setBene_acct_type(accInquiryResponseData.getAcct_type_sibs());
							requestData.setBene_acct_branch(accInquiryResponseData.getBranch_cd_sibs());
							requestData.setBene_acct_status(String.valueOf(accInquiryResponseData.getAcct_status()));
							requestData.setBene_id(acc.getBeneID());
							responseBean.setBeneAccountType(accInquiryResponseData.getAcct_type_sibs());//return to FE later
							responseBean.setBeneAccountAliasName(acc.getBeneNickName());//return to FE later
							responseBean.setBeneAccountName(accInquiryResponseData.getAcct_name());//return to FE later
							isBeneAccountFound = true;
							break;
						}
					}
				}
				
			}
			if(!isBeneAccountFound){
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
				log.info("Account ID not matched with beneficiary list");
				return;
			}
			//For term and condition cache checking
			//If invalid tnc cache, stop continue the process
			if(!validateTnCCache(requestData.getDebit_acct_ccy(), requestData.getBene_acct_ccy(), requestBean, responseBean)){
				return;
			}
			//Set optional favorite flag, if exist
			if(requestBean.getIsFavBene() != null) {
				requestData.setIs_favorite_bene((requestBean.getIsFavBene()?"Y":"N"));
			}
		}
		//Bene account open input
		else {
			if(requestBean.getBeneAccountNo() == null || requestBean.getBeneAccountNo().isEmpty() ||
					requestBean.getIsSaveBene() == null || 
					requestBean.getBeneAccountCcy() == null || requestBean.getBeneAccountCcy().isEmpty()){
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
				log.info("Beneficiary data is incomplete for open input");
				return;
			}
			if(requestBean.getIsSaveBene() && (requestBean.getBeneAliasName() == null || requestBean.getBeneAliasName().isEmpty())){
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
				log.info("Beneficiary data is incomplete for save beneficiary");
				return;
			}
			//check for product code start with GRRFIN 
			for(ListAccount acc:sofResponseDataCache.getList_account()){
				if(acc.getUuid().equals(requestBean.getDebitAccountUUID())){
					
					if(acc.getProd_cd_sibs().startsWith("GRRFIN")){
						responseBean.getObHeader().setStatusCode(MiBConstant.MIB_UNABLE_TRANSFER_NEW_BENE);
						log.info("Unable to transfer new beneficiary");
						return;
					}
					
				}
			}
			//For term and condition cache checking
			//If invalid tnc cache, stop continue the process
			if(!validateTnCCache(requestData.getDebit_acct_ccy(), requestBean.getBeneAccountCcy(), requestBean, responseBean)){
				return;
			}
			requestData.setBene_acct_no(requestBean.getBeneAccountNo());
			requestData.setBene_acct_ccy(requestBean.getBeneAccountCcy());
			com.silverlake.mleb.mcb.module.vc.accountManagement.ResponseData accInquiryResponseData = beneficiaryInquirySessionCache.getAccountDetailsResponseData();
			if(!accInquiryResponseData.getAcct_no().equals(requestBean.getBeneAccountNo())){
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
				log.info("Transaction beneficiary inquiry cache account no not matched");
				return;
			}
			
			requestData.setBene_name(accInquiryResponseData.getAcct_name());
			requestData.setBene_acct_mc_bit(accInquiryResponseData.getMcbit());
			requestData.setBene_acct_type(accInquiryResponseData.getAcct_type_sibs());
			requestData.setBene_acct_branch(accInquiryResponseData.getBranch_cd_sibs());
			requestData.setBene_acct_status(String.valueOf(accInquiryResponseData.getAcct_status()));
			requestData.setSave_bene(requestBean.getIsSaveBene());
			//Only set isFavBene when isSaveBene is true
			if(requestBean.getIsSaveBene() != null && requestBean.getIsSaveBene()) {
				if(requestBean.getIsFavBene() != null) {
					requestData.setIs_favorite_bene((requestBean.getIsFavBene()?"Y":"N"));
				}else {
					requestData.setIs_favorite_bene("N");
				}
			}
			requestData.setBene_nick_name(requestBean.getBeneAliasName());
			responseBean.setBeneAccountType(accInquiryResponseData.getAcct_type_sibs());//return to FE later
			responseBean.setBeneAccountAliasName(requestBean.getBeneAliasName());//return to FE later
			responseBean.setBeneAccountName(accInquiryResponseData.getAcct_name());//return to FE later
		}
		//cross currency transfer
		if(!requestData.getDebit_acct_ccy().equals(requestData.getBene_acct_ccy())){
			if(requestBean.getFxType() == null || requestBean.getFxType().isEmpty() ||
				requestBean.getAmountCcy() == null || requestBean.getAmountCcy().isEmpty()){
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
				log.info("Additional data is required for cross currency transfer");
				return;
			}
			//FCY -> FCY, must use special rate 
			if(!requestData.getDebit_acct_ccy().equals(MiBConstant.CURRENCY_IDR) && 
					!requestData.getBene_acct_ccy().equals(MiBConstant.CURRENCY_IDR) &&
					!requestBean.getFxType().equalsIgnoreCase("S")){
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
				log.info("Foreign currency to foreign currency can only accept special rate");
				return;
			}
			if(requestBean.getFxType().equalsIgnoreCase("S")){
				if(requestBean.getFxDealerName() == null || requestBean.getFxDealerName().isEmpty() ||
					requestBean.getFxDealerRate() == null ||
					requestBean.getFxContractNo() == null || requestBean.getFxContractNo().isEmpty()){
					responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
					log.info("Additional data is required for forex counter special");
					return;
				}
				requestData.setFx_dealer_rate(requestBean.getFxDealerRate());
				requestData.setFx_dealer_name(requestBean.getFxDealerName());
				requestData.setFx_contract_no(requestBean.getFxContractNo());
			}else{
				if(requestBean.getFxRate() == null){
					responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
					log.info("Additional data is required for forex counter");
					return;
				}
				requestData.setFx_rate(requestBean.getFxRate());
			}
			requestData.setFx_type(requestBean.getFxType());
			requestData.setAmount_ccy(requestBean.getAmountCcy());
		}
		//same currency transfer
		else{
			//set the amount currency to debit account currency.
			requestData.setAmount_ccy(requestData.getDebit_acct_ccy());
		}
		
		//Set notification requestData
		if(!processNotificationRequest(requestBean, responseBean, requestData)){
			return;
		}
		
		//Recurring settings
		processRecurringRequest(requestBean, requestData);
		
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setProd_cd(requestBean.getTransferServiceType());
		requestData.setCustomer_ref(requestBean.getCustomerReferenceNo());
		requestData.setValue_date(requestBean.getValueDate());
		requestData.setAmount(requestBean.getAmount());
		requestData.setRemarks(requestBean.getRemark());
	}
	
	private void processRequestOLT(String locale, VCGenericService vcService, TransactionSubmitRequestData requestData, ObTransactionRequest requestBean, ObTransactionResponse responseBean) throws Exception{
		if(requestBean.getSenderName() == null || requestBean.getSenderName().isEmpty()){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
			log.info("Incomplete data for OLT");
			return;
		}
		ObTransactionSourceOfFundSessionCache transactionSOFSessionCache = (ObTransactionSourceOfFundSessionCache)((ObSessionCache)requestBean.getSessionCache().get(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TRANSACTION_SOURCE_OF_FUND.getId()));
		if(transactionSOFSessionCache.getVcSourceResponseData() == null){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			log.info("Transaction source of fund cache is not found");
			return;
		}
		String transferServiceType = requestBean.getTransferServiceType();
		SourceAccountResponseData sofResponseDataCache = transactionSOFSessionCache.getVcSourceResponseData().get(transferServiceType);
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
				responseBean.setDebitAccountType(acc.getAcct_type_sibs());//return to FE later
				responseBean.setDebitAccountAliasName(acc.getAcct_alias());//return to FE later
				isDebitAccountFound = true;
				break;
			}
		}
		if(!isDebitAccountFound){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
			log.info("Account ID not matched with source of fund list");
			return;
		}
		//Bene account select from list
		if(requestBean.getBeneAccountUUID()!= null && !requestBean.getBeneAccountUUID().isEmpty()){
			boolean isBeneAccountFound = false;
			if (requestBean.getBeneAccountUUID().startsWith("B")) {
				ObTransactionBeneficiarySessionCache transactionBeneficiarySessionCache = (ObTransactionBeneficiarySessionCache) ((ObSessionCache) requestBean
						.getSessionCache()
						.get(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TRANSACTION_BENEFICIARIES.getId()));
				if (transactionBeneficiarySessionCache.getVcBeneficiaryResponseData() == null) {
					responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
					log.info("Transaction beneficiary list cache is not found");
					return;
				}
				BeneficiaryResponseData beneficiaryAcctResponseDataCache = transactionBeneficiarySessionCache
						.getVcBeneficiaryResponseData().get(transferServiceType);
				for (BeneficiaryList acc : beneficiaryAcctResponseDataCache.getBeneficiary_list()) {
					if(acc.getUuid().equals(requestBean.getBeneAccountUUID())){
						requestData.setBene_acct_no(acc.getAccount_number());
						requestData.setBene_acct_ccy(acc.getAccount_currency());
						
						if (acc.getP2p_flag().equals("Y")) {

							requestData.setBene_bank_code(requestBean.getBeneBankCode());
							requestData.setBene_bank_name(requestBean.getBeneBankName());

						} else {
							requestData.setBene_bank_code(acc.getBank_code());
							requestData.setBene_bank_name(acc.getBank_name());
						}
						requestData.setBene_id(acc.getRecord_id());
						responseBean.setBeneAccountAliasName(acc.getNick_name());
						isBeneAccountFound = true;
						break;
					}
				}
			}	
			if (requestBean.getBeneAccountUUID().startsWith("AB")) {
				ObAdminBeneficiaryListSessionCache adminBeneficiaryListSessionCache = (ObAdminBeneficiaryListSessionCache) ((ObSessionCache) requestBean
						.getSessionCache()
						.get(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_ADMINISTRATION_BENEFICIARIES.getId()));
				if (adminBeneficiaryListSessionCache.getBeneficiaryListResponseData() == null) {
					responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
					log.info("Transaction beneficiary list cache is not found");
					return;
				}
				ObAdminBeneficiaryListResponse beneficiaryAcctResponseDataCache = adminBeneficiaryListSessionCache
						.getBeneficiaryListResponseData();
				for (ObBeneAccountBean acc : beneficiaryAcctResponseDataCache.getBeneficiaryList()) {
					if (acc.getAccountUUID().equals(requestBean.getBeneAccountUUID())) {
						requestData.setBene_acct_no(acc.getBeneAccountNo());
						requestData.setBene_acct_ccy(acc.getBeneAccountCcy());
						requestData.setBene_bank_code(acc.getBeneficiaryBankCode());
						requestData.setBene_bank_name(acc.getBeneficiaryBankName());
						requestData.setBene_id(acc.getBeneID());
						responseBean.setBeneAccountAliasName(acc.getBeneNickName());
																					
						isBeneAccountFound = true;
						break;
					}
				}
			}
			if(!isBeneAccountFound){
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
				log.info("Account ID not matched with beneficiary list");
				return;
			}
			//Perform online transfer inquiry
			//Step1: Get bank reference number
			//Generate bank reference number when it is OLT
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
			requestData.setBank_ref(responseBankRefData.getBank_ref());
			//Get beneficiary data from velo
			OnlineBeneficiaryRequestData accInquiryRequestData = new OnlineBeneficiaryRequestData();
			accInquiryRequestData.setOrg_cd(requestBean.getObUser().getOrgId());
			accInquiryRequestData.setUsr_cd(requestBean.getObUser().getLoginId());
			accInquiryRequestData.setDebit_acct_no(requestData.getDebit_acct_no());
			accInquiryRequestData.setDebit_acct_ccy(requestData.getDebit_acct_ccy());
			accInquiryRequestData.setBene_acct_no(requestData.getBene_acct_no());
			accInquiryRequestData.setBank_ref(responseBankRefData.getBank_ref());
			accInquiryRequestData.setBene_bank_code(requestData.getBene_bank_code());
			accInquiryRequestData.setEffective_date(requestBean.getValueDate());
			accInquiryRequestData.setAmount(requestBean.getAmount());
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
			requestData.setSession_id(accInquiryResponseData.getInq_session_id());
			requestData.setBill_ref_no(accInquiryResponseData.getBill_ref_no());
			if(accInquiryResponseData.getDetails() != null){
				String responseDataStr = new GsonBuilder().create().toJson(accInquiryResponseData.getDetails());
				requestData.setResponse_data(responseDataStr);
			}
			responseBean.setBeneAccountName(accInquiryResponseData.getBeneficiary_name());//return to FE later
			//Set optional favorite flag, if exist
			if(requestBean.getIsFavBene() != null) {
				requestData.setIs_favorite_bene((requestBean.getIsFavBene()?"Y":"N"));
			}
			requestData.setBene_name(accInquiryResponseData.getBeneficiary_name());
		}
		//Bene account open input
		else {
			if(requestBean.getBeneAccountNo() == null || requestBean.getBeneAccountNo().isEmpty() ||
					requestBean.getIsSaveBene() == null || 
					requestBean.getBeneAccountCcy() == null || requestBean.getBeneAccountCcy().isEmpty()){
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
				log.info("Beneficiary data is incomplete for open input");
				return;
			}
			if(requestBean.getIsSaveBene() && (requestBean.getBeneAliasName() == null || requestBean.getBeneAliasName().isEmpty())){
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
				log.info("Beneficiary data is incomplete for save beneficiary");
				return;
			}
			ObTransactionBeneficiarySessionCache beneficiaryInquirySessionCache = (ObTransactionBeneficiarySessionCache)(ObSessionCache)requestBean.getSessionCache().get(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TRANSACTION_BENEFICIARY_INQUIRY.getId());
			if(beneficiaryInquirySessionCache == null || beneficiaryInquirySessionCache.getOnlineBeneficiaryResponseData() == null){
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
				log.info("Transaction beneficiary inquiry cache is not found (Required by OLT)");
				return;
			}
			//check for product code start with GRRFIN 
			for(ListAccount acc:sofResponseDataCache.getList_account()){
				if(acc.getUuid().equals(requestBean.getDebitAccountUUID())){
					
					if(acc.getProd_cd_sibs().startsWith("GRRFIN")){
						responseBean.getObHeader().setStatusCode(MiBConstant.MIB_UNABLE_TRANSFER_NEW_BENE);
						log.info("Unable to transfer new beneficiary");
						return;
					}
					
				}
			}
			requestData.setBene_acct_no(requestBean.getBeneAccountNo());
			requestData.setBene_acct_ccy(requestBean.getBeneAccountCcy());
			requestData.setBene_bank_code(beneficiaryInquirySessionCache.getBeneBankDetails().getBankCode());
			requestData.setBene_bank_name(beneficiaryInquirySessionCache.getBeneBankDetails().getBankName());
			requestData.setBank_ref(beneficiaryInquirySessionCache.getOnlineBeneficiaryRequestData().getBank_ref());
			requestData.setSession_id(beneficiaryInquirySessionCache.getOnlineBeneficiaryResponseData().getInq_session_id());
			requestData.setBill_ref_no(beneficiaryInquirySessionCache.getOnlineBeneficiaryResponseData().getBill_ref_no());
			if(beneficiaryInquirySessionCache.getOnlineBeneficiaryResponseData().getDetails() != null){
				String responseDataStr = new GsonBuilder().create().toJson(beneficiaryInquirySessionCache.getOnlineBeneficiaryResponseData().getDetails());
				requestData.setResponse_data(responseDataStr);
			}
			requestData.setSave_bene(requestBean.getIsSaveBene());
			//Only set isFavBene when isSaveBene is true
			if(requestBean.getIsSaveBene() != null && requestBean.getIsSaveBene()) {
				if(requestBean.getIsFavBene() != null) {
					requestData.setIs_favorite_bene((requestBean.getIsFavBene()?"Y":"N"));
				}else {
					requestData.setIs_favorite_bene("N");
				}
			}
			requestData.setBene_nick_name(requestBean.getBeneAliasName());
			requestData.setBene_name(beneficiaryInquirySessionCache.getOnlineBeneficiaryResponseData().getBeneficiary_name());
			responseBean.setBeneAccountAliasName(requestBean.getBeneAliasName());//return to FE later
			responseBean.setBeneAccountName(beneficiaryInquirySessionCache.getOnlineBeneficiaryResponseData().getBeneficiary_name());//return to FE later
		}
		//cross currency transfer
		if(!requestData.getDebit_acct_ccy().equals(requestData.getBene_acct_ccy())){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
			log.info("Cross currency is not allowed for OLT");
			return;
		}
		//same currency transfer
		else{
			//set the amount currency to debit account currency.
			requestData.setAmount_ccy(requestData.getDebit_acct_ccy());
		}
		
		//Set notification requestData
		if(!processNotificationRequest(requestBean, responseBean, requestData)){
			return;
		}
		
		//Recurring settings
		processRecurringRequest(requestBean, requestData);
		
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setProd_cd(requestBean.getTransferServiceType());
		requestData.setSender_name(requestBean.getSenderName());
		requestData.setCustomer_ref(requestBean.getCustomerReferenceNo());
		requestData.setValue_date(requestBean.getValueDate());
		requestData.setAmount(requestBean.getAmount());
		requestData.setRemarks(requestBean.getRemark());
	}
	
	private void processRequestLLG_RTGS(VCGenericService vcService, TransactionSubmitRequestData requestData, ObTransactionRequest requestBean, ObTransactionResponse responseBean) throws Exception{
		ObTransactionSourceOfFundSessionCache transactionSOFSessionCache = (ObTransactionSourceOfFundSessionCache)((ObSessionCache)requestBean.getSessionCache().get(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TRANSACTION_SOURCE_OF_FUND.getId()));
		if(transactionSOFSessionCache.getVcSourceResponseData() == null){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			log.info("Transaction source of fund cache is not found");
			return;
		}
		String transferServiceType = requestBean.getTransferServiceType();
		SourceAccountResponseData sofResponseDataCache = transactionSOFSessionCache.getVcSourceResponseData().get(transferServiceType);
		if(sofResponseDataCache == null){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			log.info("Transaction source of fund sub cache is not found");
			return;
		}
		if(requestBean.getSenderName() == null || requestBean.getSenderName().isEmpty() ||
				requestBean.getResidentStatus() == null || requestBean.getResidentStatus().isEmpty() ||
				requestBean.getBeneAddress1() == null || requestBean.getBeneAddress1().isEmpty()){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
			log.info("Incomplete data for LLG/RTGS");
			return;
		}
		if(requestBean.getTransferServiceType().equals(MiBConstant.TRANS_SERVICE_TYPE_LLG)){
			if(requestBean.getRemitterCategory() == null || requestBean.getRemitterCategory().isEmpty() ||
					requestBean.getBeneCategory() == null || requestBean.getBeneCategory().isEmpty()){
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
				log.info("Incomplete data for LLG");
				return;
			}
		}else if(requestBean.getTransferServiceType().equals(MiBConstant.TRANS_SERVICE_TYPE_RTGS)){
			
		}
		boolean isDebitAccountFound = false;
		for(ListAccount acc:sofResponseDataCache.getList_account()){
//			if(acc.getId().equals(requestBean.getDebitAccountId())){
			if(acc.getUuid().equals(requestBean.getDebitAccountUUID())){
				requestData.setDebit_acct_ccy(acc.getAcct_ccy());
				requestData.setDebit_acct_no(acc.getAcct_no());
				responseBean.setDebitAccountType(acc.getAcct_type_sibs());//return to FE later
				responseBean.setDebitAccountAliasName(acc.getAcct_alias());//return to FE later
				isDebitAccountFound = true;
				break;
			}
		}
		if(!isDebitAccountFound){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
			log.info("Account ID not matched with source of fund list");
			return;
		}
		//Bene account select from list
		if(requestBean.getBeneAccountUUID()!= null && !requestBean.getBeneAccountUUID().isEmpty()){
			boolean isBeneAccountFound = false;
			if (requestBean.getBeneAccountUUID().startsWith("B")) {
				ObTransactionBeneficiarySessionCache transactionBeneficiarySessionCache = (ObTransactionBeneficiarySessionCache) ((ObSessionCache) requestBean
						.getSessionCache()
						.get(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TRANSACTION_BENEFICIARIES.getId()));
				if (transactionBeneficiarySessionCache.getVcBeneficiaryResponseData() == null) {
					responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
					log.info("Transaction beneficiary list cache is not found");
					return;
				}
				BeneficiaryResponseData beneficiaryAcctResponseDataCache = transactionBeneficiarySessionCache
						.getVcBeneficiaryResponseData().get(transferServiceType);
				for (BeneficiaryList acc : beneficiaryAcctResponseDataCache.getBeneficiary_list()) {
					if(acc.getUuid().equals(requestBean.getBeneAccountUUID())){
						requestData.setBene_acct_no(acc.getAccount_number());
						requestData.setBene_acct_ccy(acc.getAccount_currency());
						requestData.setBene_name(acc.getAccount_name());
						requestData.setBene_address1(requestBean.getBeneAddress1());
						requestData.setBene_address2(requestBean.getBeneAddress2());
						requestData.setBene_address3(requestBean.getBeneAddress3());
						
						if (acc.getP2p_flag().equals("Y")) {
							requestData.setBene_bank_code(requestBean.getBeneBankCode());
							requestData.setBene_bank_name(requestBean.getBeneBankName());
							requestData.setBene_bank_address(requestBean.getBeneBankAddress1());
							requestData.setBene_bank_address2(requestBean.getBeneBankAddress2());
							requestData.setBene_bank_address3(requestBean.getBeneBankAddress3());
							requestData.setBene_bank_branch(requestBean.getBeneBankBranch());
							requestData.setBene_bank_network_id(requestBean.getBeneBankNetworkClearingCode());
						} else {
							requestData.setBene_bank_code(acc.getBank_code());
							requestData.setBene_bank_name(acc.getBank_name());
							requestData.setBene_bank_address(acc.getBank_address1());
							requestData.setBene_bank_address2(acc.getBank_address2());
							requestData.setBene_bank_address3(acc.getBank_address3());
							requestData.setBene_bank_branch(acc.getBank_branch_name());
							requestData.setBene_bank_network_id(acc.getBank_network_clearing_code());
						}
						
						requestData.setBene_id(acc.getRecord_id());
						responseBean.setBeneAccountAliasName(acc.getNick_name());//return to FE later
						responseBean.setBeneAccountName(acc.getAccount_name());//return to FE later
						responseBean.setBeneAddress1(requestBean.getBeneAddress1());//return to FE later
						responseBean.setBeneAddress2(requestBean.getBeneAddress2());//return to FE later
						responseBean.setBeneAddress3(requestBean.getBeneAddress3());//return to FE later
						isBeneAccountFound = true;
						break;
					}
				}
			}
			
			if (requestBean.getBeneAccountUUID().startsWith("AB")) {
				ObAdminBeneficiaryListSessionCache adminBeneficiaryListSessionCache = (ObAdminBeneficiaryListSessionCache) ((ObSessionCache) requestBean
						.getSessionCache()
						.get(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_ADMINISTRATION_BENEFICIARIES.getId()));
				if (adminBeneficiaryListSessionCache.getBeneficiaryListResponseData() == null) {
					responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
					log.info("Transaction beneficiary list cache is not found");
					return;
				}
				ObAdminBeneficiaryListResponse beneficiaryAcctResponseDataCache = adminBeneficiaryListSessionCache
						.getBeneficiaryListResponseData();
				for (ObBeneAccountBean acc : beneficiaryAcctResponseDataCache.getBeneficiaryList()) {
					if (acc.getAccountUUID().equals(requestBean.getBeneAccountUUID())) {
						requestData.setBene_acct_no(acc.getBeneAccountNo());
						requestData.setBene_acct_ccy(acc.getBeneAccountCcy());
						requestData.setBene_name(acc.getBeneAccountName());
						requestData.setBene_address1(requestBean.getBeneAddress1());
						requestData.setBene_address2(requestBean.getBeneAddress2());
						requestData.setBene_address3(requestBean.getBeneAddress3());
						requestData.setBene_bank_code(acc.getBeneficiaryBankCode());
						requestData.setBene_bank_name(acc.getBeneficiaryBankName());
						requestData.setBene_bank_address(acc.getBankAddress1());
						requestData.setBene_bank_address2(acc.getBankAddress2());
						requestData.setBene_bank_address3(acc.getBankAddress3());
						requestData.setBene_bank_branch(acc.getBeneficiaryBankBranch());
						requestData.setBene_bank_network_id(acc.getBeneBankClearingId());
						requestData.setBene_id(acc.getBeneID());
						responseBean.setBeneAccountAliasName(acc.getBeneNickName());//return to FE later
						responseBean.setBeneAccountName(acc.getBeneAccountName());//return to FE later
						responseBean.setBeneAddress1(requestBean.getBeneAddress1());//return to FE later
						responseBean.setBeneAddress2(requestBean.getBeneAddress2());//return to FE later
						responseBean.setBeneAddress3(requestBean.getBeneAddress3());//return to FE later
						isBeneAccountFound = true;
						break;
					}
				}
			}
			if(!isBeneAccountFound){
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
				log.info("Account ID not matched with beneficiary list");//return to FE later
				return;
			}
			//Set optional favorite flag, if exist
			if(requestBean.getIsFavBene() != null) {
				requestData.setIs_favorite_bene((requestBean.getIsFavBene()?"Y":"N"));
			}
		}
		//Bene account open input
		else {
			if(requestBean.getBeneAccountNo() == null || requestBean.getBeneAccountNo().isEmpty() ||
					requestBean.getIsSaveBene() == null || 
					requestBean.getBeneAccountName() == null || requestBean.getBeneAccountName().isEmpty()){
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
				log.info("Beneficiary data is incomplete for open input");
				return;
			}
			if(requestBean.getIsSaveBene() && (requestBean.getBeneAliasName() == null || requestBean.getBeneAliasName().isEmpty())){
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
				log.info("Beneficiary data is incomplete for save beneficiary");
				return;
			}
			//check for product code start with GRRFIN 
			for(ListAccount acc:sofResponseDataCache.getList_account()){
				if(acc.getUuid().equals(requestBean.getDebitAccountUUID())){
					
					if(acc.getProd_cd_sibs().startsWith("GRRFIN")){
						responseBean.getObHeader().setStatusCode(MiBConstant.MIB_UNABLE_TRANSFER_NEW_BENE);
						log.info("Unable to transfer new beneficiary");
						return;
					}
					
				}
			}
			requestData.setBene_acct_no(requestBean.getBeneAccountNo());
			requestData.setBene_acct_ccy(MiBConstant.CURRENCY_IDR);//LLG and RTGS, destination always IDR
			requestData.setBene_name(requestBean.getBeneAccountName());
			requestData.setBene_address1(requestBean.getBeneAddress1());
			requestData.setBene_address2(requestBean.getBeneAddress2());
			requestData.setBene_address3(requestBean.getBeneAddress3());
			requestData.setSave_bene(requestBean.getIsSaveBene());
			//Only set isFavBene when isSaveBene is true
			if(requestBean.getIsSaveBene() != null && requestBean.getIsSaveBene()) {
				if(requestBean.getIsFavBene() != null) {
					requestData.setIs_favorite_bene((requestBean.getIsFavBene()?"Y":"N"));
				}else {
					requestData.setIs_favorite_bene("N");
				}
			}
			requestData.setBene_nick_name(requestBean.getBeneAliasName());
			responseBean.setBeneAccountAliasName(requestBean.getBeneAliasName());//return to FE later
			responseBean.setBeneAccountName(requestBean.getBeneAccountName());//return to FE later
			responseBean.setBeneAddress1(requestBean.getBeneAddress1());//return to FE later
			responseBean.setBeneAddress2(requestBean.getBeneAddress2());//return to FE later
			responseBean.setBeneAddress3(requestBean.getBeneAddress3());//return to FE later
			
			//Get bene bank details from omni
			if(requestBean.getBeneBankCode() == null || requestBean.getBeneBankCode().isEmpty() ||
					requestBean.getBeneBankBranch() == null || requestBean.getBeneBankBranch().isEmpty()){
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
				log.info("Beneficiary bank data is incomplete for LLG/RTGS");
				return;
			}
			BankListRequestData bankListRequestData = new BankListRequestData();
			bankListRequestData.setOrg_cd(requestBean.getObUser().getOrgId());
			bankListRequestData.setUsr_cd(requestBean.getObUser().getLoginId());
			if(requestBean.getTransferServiceType().equalsIgnoreCase(MiBConstant.TRANS_SERVICE_TYPE_LLG)){
				bankListRequestData.setTransfer_type("LL");
				bankListRequestData.setBank_code(requestBean.getBeneBankCode());
				bankListRequestData.setBranch_name(requestBean.getBeneBankBranch());
			}else if(requestBean.getTransferServiceType().equalsIgnoreCase(MiBConstant.TRANS_SERVICE_TYPE_RTGS)){
				bankListRequestData.setTransfer_type("RT");
				bankListRequestData.setBank_code(requestBean.getBeneBankCode());
				bankListRequestData.setBranch_name(requestBean.getBeneBankBranch());
			}
			bankListRequestData.setPage_no(1);
			bankListRequestData.setPage_size(Integer.MAX_VALUE);
			VCResponse<BankListResponseData> vcResponse = vcService.callOmniVC(
					VCMethodConstant.REST_METHODS.OTHERS_BANKS, 
					bankListRequestData, 
					BankListResponseData.class, 
					true,
					"branch_details");
			if(processVCResponseError(vcResponse, responseBean)){
				return;
			}
			BankListResponseData responseData = vcResponse.getData();
			if(responseData.getBank_list() == null || responseData.getBank_list().isEmpty()){
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
				log.info("Bank branch not found");
				return;
			}
			requestData.setBene_bank_code(requestBean.getBeneBankCode());
			requestData.setBene_bank_name(responseData.getBank_list().get(0).getBank_name());
			requestData.setBene_bank_address(responseData.getBank_list().get(0).getAddress_1());
			requestData.setBene_bank_address2(responseData.getBank_list().get(0).getAddress_2());
			requestData.setBene_bank_address3(responseData.getBank_list().get(0).getAddress_3());
			requestData.setBene_bank_branch(requestBean.getBeneBankBranch());
			requestData.setBene_bank_network_id(responseData.getBank_list().get(0).getNetwork_clearing_code());
		}
		//cross currency transfer
		if(!requestData.getDebit_acct_ccy().equals(requestData.getBene_acct_ccy())){
			if(!requestData.getBene_acct_ccy().equalsIgnoreCase(MiBConstant.CURRENCY_IDR)){
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
				log.info("For LLG and RTGS, destination account cannot be other than IDR");
				return;
			}
			if(requestBean.getFxType() == null || requestBean.getFxType().isEmpty()){
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
				log.info("Additional data is required for cross currency transfer");
				return;
			}
			if(requestBean.getFxType().equalsIgnoreCase("S")){
				if(requestBean.getFxDealerName() == null || requestBean.getFxDealerName().isEmpty() ||
					requestBean.getFxDealerRate() == null ||
					requestBean.getFxContractNo() == null || requestBean.getFxContractNo().isEmpty()){
					responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
					log.info("Additional data is required for forex counter special");
					return;
				}
				requestData.setFx_dealer_rate(requestBean.getFxDealerRate());
				requestData.setFx_dealer_name(requestBean.getFxDealerName());
				requestData.setFx_contract_no(requestBean.getFxContractNo());
			}else{
				if(requestBean.getFxRate() == null){
					responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
					log.info("Additional data is required for forex counter");
					return;
				}
				requestData.setFx_rate(requestBean.getFxRate());
			}
			requestData.setFx_type(requestBean.getFxType());
		}
		requestData.setAmount_ccy(requestData.getBene_acct_ccy());//amount currency always in bene account currency (LLG and RTGS destination currency always IDR)
		
		//Set notification requestData
		if(!processNotificationRequest(requestBean, responseBean, requestData)){
			return;
		}
		
		//Recurring settings
		processRecurringRequest(requestBean, requestData);
		
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setProd_cd(requestBean.getTransferServiceType());
		requestData.setSender_name(requestBean.getSenderName());
		requestData.setSender_address1(requestBean.getObUser().getAddress1());
		requestData.setSender_address2(requestBean.getObUser().getAddress2());
		requestData.setSender_address3(requestBean.getObUser().getAddress3());
		requestData.setCustomer_ref(requestBean.getCustomerReferenceNo());
		requestData.setValue_date(requestBean.getValueDate());
		requestData.setAmount(requestBean.getAmount());
		requestData.setRemarks(requestBean.getRemark());
		requestData.setResident_status(requestBean.getResidentStatus());
		requestData.setBene_category(requestBean.getBeneCategory());
		requestData.setRemitter_category(requestBean.getRemitterCategory());
	}
	
	private void processRequestTT(VCGenericService vcService, TransactionSubmitRequestData requestData, ObTransactionRequest requestBean, ObTransactionResponse responseBean) throws Exception{
		ObTransactionSourceOfFundSessionCache transactionSOFSessionCache = (ObTransactionSourceOfFundSessionCache)((ObSessionCache)requestBean.getSessionCache().get(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TRANSACTION_SOURCE_OF_FUND.getId()));
		if(transactionSOFSessionCache.getVcSourceResponseData() == null){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			log.info("Transaction source of fund cache is not found");
			return;
		}
		String transferServiceType = requestBean.getTransferServiceType();
		SourceAccountResponseData sofResponseDataCache = transactionSOFSessionCache.getVcSourceResponseData().get(transferServiceType);
		if(sofResponseDataCache == null){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			log.info("Transaction source of fund sub cache is not found");
			return;
		}
		if(requestBean.getSenderName() == null || requestBean.getSenderName().isEmpty() ||
				requestBean.getResidentStatus() == null || requestBean.getResidentStatus().isEmpty() ||
				requestBean.getBeneAddress1() == null || requestBean.getBeneAddress1().isEmpty() ||
				requestBean.getRemitterCategory() == null || requestBean.getRemitterCategory().isEmpty() ||
				requestBean.getBeneCategory() == null || requestBean.getBeneCategory().isEmpty() ||
				requestBean.getSenderCountryCode() == null || requestBean.getSenderCountryCode().isEmpty() ||
				requestBean.getBeneCountryCode() == null || requestBean.getBeneCountryCode().isEmpty() ||
				requestBean.getBeneRelationshipStatus() == null || requestBean.getBeneRelationshipStatus().isEmpty() ||
				requestBean.getPaymentPurpose() == null || requestBean.getPaymentPurpose().isEmpty() ||
				requestBean.getChargesMethod() == null || requestBean.getChargesMethod().isEmpty()){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
			log.info("Incomplete data for TT");
			return;
		}
		boolean isDebitAccountFound = false;
		for(ListAccount acc:sofResponseDataCache.getList_account()){
//			if(acc.getId().equals(requestBean.getDebitAccountId())){
			if(acc.getUuid().equals(requestBean.getDebitAccountUUID())){
				requestData.setDebit_acct_ccy(acc.getAcct_ccy());
				requestData.setDebit_acct_no(acc.getAcct_no());
				responseBean.setDebitAccountType(acc.getAcct_type_sibs());//return to FE later
				responseBean.setDebitAccountAliasName(acc.getAcct_alias());//return to FE later
				isDebitAccountFound = true;
				break;
			}
		}
		if(!isDebitAccountFound){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
			log.info("Account ID not matched with source of fund list");
			return;
		}
		//Bene account select from list
		if(requestBean.getBeneAccountUUID()!= null && !requestBean.getBeneAccountUUID().isEmpty()){
			boolean isBeneAccountFound = false;
			if (requestBean.getBeneAccountUUID().startsWith("B")) {
				ObTransactionBeneficiarySessionCache transactionBeneficiarySessionCache = (ObTransactionBeneficiarySessionCache) ((ObSessionCache) requestBean
						.getSessionCache()
						.get(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TRANSACTION_BENEFICIARIES.getId()));
				if (transactionBeneficiarySessionCache.getVcBeneficiaryResponseData() == null) {
					responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
					log.info("Transaction beneficiary list cache is not found");
					return;
				}
				BeneficiaryResponseData beneficiaryAcctResponseDataCache = transactionBeneficiarySessionCache
						.getVcBeneficiaryResponseData().get(transferServiceType);
				for (BeneficiaryList acc : beneficiaryAcctResponseDataCache.getBeneficiary_list()) {
					if(acc.getUuid().equals(requestBean.getBeneAccountUUID())){
						requestData.setBene_acct_no(acc.getAccount_number());
						requestData.setBene_acct_ccy(acc.getAccount_currency());
						requestData.setBene_name(acc.getAccount_name());
						requestData.setBene_address1(requestBean.getBeneAddress1());
						requestData.setBene_address2(requestBean.getBeneAddress2());
						requestData.setBene_address3(requestBean.getBeneAddress3());

						if (acc.getP2p_flag().equals("Y")) {
							requestData.setBene_bank_name(requestBean.getBeneBankName());
							requestData.setBene_bank_address(requestBean.getBeneBankAddress1());
							requestData.setBene_bank_address2(requestBean.getBeneBankAddress2());
							requestData.setBene_bank_address3(requestBean.getBeneBankAddress3());
							requestData.setBene_bank_branch(requestBean.getBeneBankBranch());
							requestData.setBene_bank_city(requestBean.getBeneBankCity());
							requestData.setBene_bank_country(requestBean.getBeneBankCountryCode());
							requestData.setBene_bank_network_id(requestBean.getBeneBankNetworkClearingCode());
						} else {
							requestData.setBene_bank_name(acc.getBank_name());
							requestData.setBene_bank_address(acc.getBank_address1());
							requestData.setBene_bank_address2(acc.getBank_address2());
							requestData.setBene_bank_address3(acc.getBank_address3());
							requestData.setBene_bank_branch(acc.getBank_branch_name());
							requestData.setBene_bank_city(acc.getBank_city());
							requestData.setBene_bank_country(acc.getBank_country_code());
							requestData.setBene_bank_network_id(acc.getBank_network_clearing_code());
						}
						
						requestData.setBene_id(acc.getRecord_id());
						responseBean.setBankCode(acc.getBank_network_clearing_code());//return to FE later
						responseBean.setBeneAccountAliasName(acc.getNick_name());//return to FE later
						responseBean.setBeneAccountName(acc.getAccount_name());//return to FE later
						responseBean.setBeneAddress1(requestBean.getBeneAddress1());//return to FE later
						responseBean.setBeneAddress2(requestBean.getBeneAddress2());//return to FE later
						responseBean.setBeneAddress3(requestBean.getBeneAddress3());//return to FE later
						isBeneAccountFound = true;
						break;
					}
				}
			}
			
			if (requestBean.getBeneAccountUUID().startsWith("AB")) {
				ObAdminBeneficiaryListSessionCache adminBeneficiaryListSessionCache = (ObAdminBeneficiaryListSessionCache) ((ObSessionCache) requestBean
						.getSessionCache().get(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_ADMINISTRATION_BENEFICIARIES.getId()));
				if (adminBeneficiaryListSessionCache.getBeneficiaryListResponseData() == null) {
					responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
					log.info("Transaction beneficiary list cache is not found");
					return;
				}
				ObAdminBeneficiaryListResponse beneficiaryAcctResponseDataCache = adminBeneficiaryListSessionCache.getBeneficiaryListResponseData();
				for (ObBeneAccountBean acc : beneficiaryAcctResponseDataCache.getBeneficiaryList()) {
					if (acc.getAccountUUID().equals(requestBean.getBeneAccountUUID())) {
						requestData.setBene_acct_no(acc.getBeneAccountNo());
						requestData.setBene_acct_ccy(acc.getBeneAccountCcy());
						requestData.setBene_name(acc.getBeneAccountName());
						requestData.setBene_address1(requestBean.getBeneAddress1());
						requestData.setBene_address2(requestBean.getBeneAddress2());
						requestData.setBene_address3(requestBean.getBeneAddress3());

						requestData.setBene_bank_name(acc.getBeneficiaryBankName());
						requestData.setBene_bank_address(acc.getBankAddress1());
						requestData.setBene_bank_address2(acc.getBankAddress2());
						requestData.setBene_bank_address3(acc.getBankAddress3());
						requestData.setBene_bank_branch(acc.getBeneficiaryBankBranch());
						requestData.setBene_bank_city(acc.getBankCity());
						requestData.setBene_bank_country(acc.getBankCountryCode());
						requestData.setBene_id(acc.getBeneID());
						requestData.setBene_bank_network_id(acc.getBeneBankClearingId());

						responseBean.setBankCode(acc.getBeneBankClearingId());//return to FE later
						responseBean.setBeneAccountAliasName(acc.getBeneNickName());//return to FE later
						responseBean.setBeneAccountName(acc.getBeneAccountName());//return to FE later
						responseBean.setBeneAddress1(requestBean.getBeneAddress1());//return to FE later
						responseBean.setBeneAddress2(requestBean.getBeneAddress2());//return to FE later
						responseBean.setBeneAddress3(requestBean.getBeneAddress3());//return to FE later
						isBeneAccountFound = true;
						break;
					}
				}
			}

			if(!isBeneAccountFound){
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
				log.info("Account ID not matched with beneficiary list");
				return;
			}
			//Set optional favorite flag, if exist
			if(requestBean.getIsFavBene() != null) {
				requestData.setIs_favorite_bene((requestBean.getIsFavBene()?"Y":"N"));
			}
		}
		//Bene account open input
		else {
			if(requestBean.getBeneAccountNo() == null || requestBean.getBeneAccountNo().isEmpty() ||
					requestBean.getIsSaveBene() == null || 
					requestBean.getBeneAccountName() == null || requestBean.getBeneAccountName().isEmpty() ||
					requestBean.getBeneBankCode() == null || requestBean.getBeneBankCode().isEmpty() ||		
					requestBean.getBeneBankName() == null || requestBean.getBeneBankName().isEmpty() ||
					requestBean.getBeneBankBranch() == null || requestBean.getBeneBankBranch().isEmpty() ||
					requestBean.getBeneBankCountryCode() == null || requestBean.getBeneBankCountryCode().isEmpty() ||
					requestBean.getBeneBankAddress1() == null || requestBean.getBeneBankAddress1().isEmpty()){
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
				log.info("Beneficiary data is incomplete for open input");
				return;
			}
			if(requestBean.getIsSaveBene() && (requestBean.getBeneAliasName() == null || requestBean.getBeneAliasName().isEmpty())){
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
				log.info("Beneficiary data is incomplete for save beneficiary");
				return;
			}
			//check for product code start with GRRFIN 
			for(ListAccount acc:sofResponseDataCache.getList_account()){
				if(acc.getUuid().equals(requestBean.getDebitAccountUUID())){
					
					if(acc.getProd_cd_sibs().startsWith("GRRFIN")){
						responseBean.getObHeader().setStatusCode(MiBConstant.MIB_UNABLE_TRANSFER_NEW_BENE);
						log.info("Unable to transfer new beneficiary");
						return;
					}
					
				}
			}
			requestData.setBene_acct_no(requestBean.getBeneAccountNo());
			requestData.setBene_acct_ccy(requestBean.getBeneAccountCcy());
			requestData.setBene_name(requestBean.getBeneAccountName());
			requestData.setBene_address1(requestBean.getBeneAddress1());
			requestData.setBene_address2(requestBean.getBeneAddress2());
			requestData.setBene_address3(requestBean.getBeneAddress3());
			requestData.setSave_bene(requestBean.getIsSaveBene());
			//Only set isFavBene when isSaveBene is true
			if(requestBean.getIsSaveBene() != null && requestBean.getIsSaveBene()) {
				if(requestBean.getIsFavBene() != null) {
					requestData.setIs_favorite_bene((requestBean.getIsFavBene()?"Y":"N"));
				}else {
					requestData.setIs_favorite_bene("N");
				}
			}
			requestData.setBene_nick_name(requestBean.getBeneAliasName());
			
			requestData.setBene_bank_name(requestBean.getBeneBankName());
			requestData.setBene_bank_city(requestBean.getBeneBankBranch());
			requestData.setBene_bank_country(requestBean.getBeneBankCountryCode());
			requestData.setBene_bank_address(requestBean.getBeneBankAddress1());
			requestData.setBene_bank_address2(requestBean.getBeneBankAddress2());
			requestData.setBene_bank_address3(requestBean.getBeneBankAddress3());
			requestData.setBene_bank_network_id(requestBean.getBeneBankCode());
			
			responseBean.setBankCode(requestBean.getBeneBankCode());//return to FE later
			responseBean.setBeneAccountAliasName(requestBean.getBeneAliasName());//return to FE later
			responseBean.setBeneAccountName(requestBean.getBeneAccountName());//return to FE later
			responseBean.setBeneAddress1(requestBean.getBeneAddress1());//return to FE later
			responseBean.setBeneAddress2(requestBean.getBeneAddress2());//return to FE later
			responseBean.setBeneAddress3(requestBean.getBeneAddress3());//return to FE later
		}
		//cross currency transfer
		if(!requestData.getDebit_acct_ccy().equals(requestData.getBene_acct_ccy())){
			if(requestData.getBene_acct_ccy().equalsIgnoreCase(MiBConstant.CURRENCY_IDR)){
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
				log.info("For TT, destination account cannot be IDR");
				return;
			}
			if(requestBean.getFxType() == null || requestBean.getFxType().isEmpty()){
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
				log.info("Additional data is required for cross currency transfer");
				return;
			}
			if(requestBean.getFxType().equalsIgnoreCase("S")){
				if(requestBean.getFxDealerName() == null || requestBean.getFxDealerName().isEmpty() ||
					requestBean.getFxDealerRate() == null ||
					requestBean.getFxContractNo() == null || requestBean.getFxContractNo().isEmpty()){
					responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
					log.info("Additional data is required for forex counter special");
					return;
				}
				requestData.setFx_dealer_rate(requestBean.getFxDealerRate());
				requestData.setFx_dealer_name(requestBean.getFxDealerName());
				requestData.setFx_contract_no(requestBean.getFxContractNo());
			}else{
				if(requestBean.getFxRate() == null){
					responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
					log.info("Additional data is required for forex counter");
					return;
				}
				requestData.setFx_rate(requestBean.getFxRate());
			}
			requestData.setFx_type(requestBean.getFxType());
		}
		requestData.setAmount_ccy(requestData.getBene_acct_ccy());//amount currency always in bene account currency (LLG and RTGS destination currency always IDR)
		
		//Set notification requestData
		if(!processNotificationRequest(requestBean, responseBean, requestData)){
			return;
		}
		
		//Recurring settings
		processRecurringRequest(requestBean, requestData);
		
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setProd_cd(requestBean.getTransferServiceType());
		requestData.setSender_name(requestBean.getSenderName());
		if(requestBean.getSender_address1() != null && !requestBean.getSender_address1().isEmpty()) {
			requestData.setSender_address1(requestBean.getSender_address1());
			requestData.setSender_address2(requestBean.getSender_address2());
			requestData.setSender_address3(requestBean.getSender_address3());
		} else {
			requestData.setSender_address1(requestBean.getObUser().getAddress1());
			requestData.setSender_address2(requestBean.getObUser().getAddress2());
			requestData.setSender_address3(requestBean.getObUser().getAddress3());
		}
		requestData.setCustomer_ref(requestBean.getCustomerReferenceNo());
		requestData.setValue_date(requestBean.getValueDate());
		requestData.setAmount(requestBean.getAmount());
		requestData.setRemarks(requestBean.getRemark());
		requestData.setResident_status(requestBean.getResidentStatus());
		requestData.setBene_category(requestBean.getBeneCategory());
		requestData.setRemitter_category(requestBean.getRemitterCategory());
		requestData.setRemitter_country_residence(requestBean.getSenderCountryCode());
		requestData.setBene_country_residence(requestBean.getBeneCountryCode());
		requestData.setBene_affiliation_status(requestBean.getBeneRelationshipStatus());
		requestData.setPayment_purpose(requestBean.getPaymentPurpose());
		requestData.setSwift_charges_method(requestBean.getChargesMethod());
	}
	
	private void processRequestFAST(String locale, VCGenericService vcService, TransactionSubmitRequestData requestData, ObTransactionRequest requestBean, ObTransactionResponse responseBean) throws Exception{
		if((requestBean.getTrx_purpose() == null || requestBean.getTrx_purpose().isEmpty())){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
			log.info("Incomplete data for FAST");
			return;
		}
		ObTransactionSourceOfFundSessionCache transactionSOFSessionCache = (ObTransactionSourceOfFundSessionCache)((ObSessionCache)requestBean.getSessionCache().get(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TRANSACTION_SOURCE_OF_FUND.getId()));
		if(transactionSOFSessionCache.getVcSourceResponseData() == null){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			log.info("Transaction source of fund cache is not found");
			return;
		}
		String transferServiceType = requestBean.getTransferServiceType();
		SourceAccountResponseData sofResponseDataCache = transactionSOFSessionCache.getVcSourceResponseData().get(transferServiceType);
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
				responseBean.setDebitAccountType(acc.getAcct_type_sibs());//return to FE later
				responseBean.setDebitAccountAliasName(acc.getAcct_alias());//return to FE later
				isDebitAccountFound = true;
				break;
			}
		}
		if(!isDebitAccountFound){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
			log.info("Account ID not matched with source of fund list");
			return;
		}
		//Bene account select from list
		if(requestBean.getBeneAccountUUID()!= null && !requestBean.getBeneAccountUUID().isEmpty()){
			boolean isBeneAccountFound = false;
			if (requestBean.getBeneAccountUUID().startsWith("B")) {
				ObTransactionBeneficiarySessionCache transactionBeneficiarySessionCache = (ObTransactionBeneficiarySessionCache) ((ObSessionCache) requestBean
						.getSessionCache()
						.get(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TRANSACTION_BENEFICIARIES.getId()));
				if (transactionBeneficiarySessionCache.getVcBeneficiaryResponseData() == null) {
					responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
					log.info("Transaction beneficiary list cache is not found");
					return;
				}
				BeneficiaryResponseData beneficiaryAcctResponseDataCache = transactionBeneficiarySessionCache
						.getVcBeneficiaryResponseData().get(transferServiceType);
				for (BeneficiaryList acc : beneficiaryAcctResponseDataCache.getBeneficiary_list()) {
					if (acc.getUuid().equals(requestBean.getBeneAccountUUID())) {
						requestData.setBene_acct_no(acc.getAccount_number());
						requestData.setBene_acct_ccy(acc.getAccount_currency());
						
						if (acc.getP2p_flag().equals("Y")) {

							requestData.setBene_bank_code(requestBean.getBeneBankCode());
							requestData.setBene_bank_name(requestBean.getBeneBankName());

						} else {
							requestData.setBene_bank_code(acc.getBank_code());
							requestData.setBene_bank_name(acc.getBank_name());
						}
						
						requestData.setBene_id(acc.getRecord_id());
						requestData.setProxy_type(acc.getProxy_type());
						requestData.setProxy_data(acc.getProxy_data());
						responseBean.setBeneAccountAliasName(acc.getNick_name());
						isBeneAccountFound = true;
						break;
					}
				}
			}	
			if (requestBean.getBeneAccountUUID().startsWith("AB")) {
				ObAdminBeneficiaryListSessionCache adminBeneficiaryListSessionCache = (ObAdminBeneficiaryListSessionCache) ((ObSessionCache) requestBean
						.getSessionCache()
						.get(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_ADMINISTRATION_BENEFICIARIES.getId()));
				if (adminBeneficiaryListSessionCache.getBeneficiaryListResponseData() == null) {
					responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
					log.info("Transaction beneficiary list cache is not found");
					return;
				}
				ObAdminBeneficiaryListResponse beneficiaryAcctResponseDataCache = adminBeneficiaryListSessionCache
						.getBeneficiaryListResponseData();
				for (ObBeneAccountBean acc : beneficiaryAcctResponseDataCache.getBeneficiaryList()) {
					if (acc.getAccountUUID().equals(requestBean.getBeneAccountUUID())) {
						requestData.setBene_acct_no(acc.getBeneAccountNo());
						requestData.setBene_acct_ccy(acc.getBeneAccountCcy());
						requestData.setBene_bank_code(acc.getBeneficiaryBankCode());
						requestData.setBene_bank_name(acc.getBeneficiaryBankName());
						requestData.setBene_id(acc.getBeneID());
						requestData.setProxy_type(acc.getProxy_type());
						requestData.setProxy_data(acc.getProxy_data());
						responseBean.setBeneAccountAliasName(acc.getBeneNickName());
																					
						isBeneAccountFound = true;
						break;
					}
				}
			}
			if(!isBeneAccountFound){
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
				log.info("Account ID not matched with beneficiary list");
				return;
			}
			
			
			ObTransactionBeneficiarySessionCache beneficiaryInquirySessionCache = (ObTransactionBeneficiarySessionCache)(ObSessionCache)requestBean.getSessionCache().get(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TRANSACTION_BENEFICIARY_INQUIRY.getId());
			if(beneficiaryInquirySessionCache == null || beneficiaryInquirySessionCache.getFastBeneficiaryResponseData() == null){
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
				log.info("Transaction beneficiary inquiry cache is not found (Required by FAST)");
				return;
			}
			
			requestData.setTrx_purpose(requestBean.getTrx_purpose());
			requestData.setBifast_data(beneficiaryInquirySessionCache.getFastBeneficiaryResponseData().getInquiry_bifast_data());
			requestData.setCharges_acct_no(requestBean.getCharges_acct_no());
			requestData.setCharges_acct_ccy(requestBean.getCharges_acct_ccy());
			
			responseBean.setTrx_purpose(requestBean.getTrx_purpose());
			responseBean.setBene_name(beneficiaryInquirySessionCache.getFastBeneficiaryResponseData().getBene_name());//return to FE later
			responseBean.setBene_acct_no(beneficiaryInquirySessionCache.getFastBeneficiaryResponseData().getBene_acct_no());//return to FE later
			responseBean.setBene_acct_cc(beneficiaryInquirySessionCache.getFastBeneficiaryResponseData().getBene_acct_ccy());//return to FE later
			responseBean.setBene_bank_id(beneficiaryInquirySessionCache.getFastBeneficiaryResponseData().getBene_bank_id());//return to FE later
			responseBean.setBene_bank_name(beneficiaryInquirySessionCache.getFastBeneficiaryResponseData().getBene_bank_name());//return to FE later
		//	responseBean.setBeneAccountName(accInquiryResponseData.getBene_name());//return to FE later
			//Set optional favorite flag, if exist
			if(requestBean.getIsFavBene() != null) {
				requestData.setIs_favorite_bene((requestBean.getIsFavBene()?"Y":"N"));
			}
			requestData.setBene_name(beneficiaryInquirySessionCache.getFastBeneficiaryResponseData().getBene_name());
		}
		//Bene account open input
		else {
			if(requestBean.getBeneAccountNo() == null || requestBean.getBeneAccountNo().isEmpty() ||
					requestBean.getIsSaveBene() == null || 
					requestBean.getBeneAccountCcy() == null || requestBean.getBeneAccountCcy().isEmpty()){
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
				log.info("Beneficiary data is incomplete for open input");
				return;
			}
			if(requestBean.getIsSaveBene() && (requestBean.getBeneAliasName() == null || requestBean.getBeneAliasName().isEmpty())){
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
				log.info("Beneficiary data is incomplete for save beneficiary");
				return;
			}
			
			//check for product code start with GRRFIN 
			for(ListAccount acc:sofResponseDataCache.getList_account()){
				if(acc.getUuid().equals(requestBean.getDebitAccountUUID())){
					
					if(acc.getProd_cd_sibs().startsWith("GRRFIN")){
						responseBean.getObHeader().setStatusCode(MiBConstant.MIB_UNABLE_TRANSFER_NEW_BENE);
						log.info("Unable to transfer new beneficiary");
						return;
					}
					
				}
			}
			ObTransactionBeneficiarySessionCache beneficiaryInquirySessionCache = (ObTransactionBeneficiarySessionCache)(ObSessionCache)requestBean.getSessionCache().get(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TRANSACTION_BENEFICIARY_INQUIRY.getId());
			if(beneficiaryInquirySessionCache == null || beneficiaryInquirySessionCache.getFastBeneficiaryResponseData() == null){
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
				log.info("Transaction beneficiary inquiry cache is not found (Required by FAST)");
				return;
			}
			requestData.setBene_acct_no(requestBean.getBeneAccountNo());
			requestData.setBene_acct_ccy(requestBean.getBeneAccountCcy());
			requestData.setBene_bank_code(beneficiaryInquirySessionCache.getBeneBankDetails().getBankCode());
			requestData.setBene_bank_name(beneficiaryInquirySessionCache.getBeneBankDetails().getBankName());
			requestData.setProxy_data(beneficiaryInquirySessionCache.getFastBeneficiaryRequestData().getProxy_data());
			requestData.setProxy_type(beneficiaryInquirySessionCache.getFastBeneficiaryRequestData().getProxy_type());
			requestData.setBifast_data(beneficiaryInquirySessionCache.getFastBeneficiaryResponseData().getInquiry_bifast_data());
			requestData.setTrx_purpose(requestBean.getTrx_purpose());
			requestData.setCharges_acct_no(requestBean.getCharges_acct_no());
			requestData.setCharges_acct_ccy(requestBean.getCharges_acct_ccy());
			requestData.setCharges_acct_no(requestBean.getCharges_acct_no());
			requestData.setSave_bene(requestBean.getIsSaveBene());
			//Only set isFavBene when isSaveBene is true
			if(requestBean.getIsSaveBene() != null && requestBean.getIsSaveBene()) {
				if(requestBean.getIsFavBene() != null) {
					requestData.setIs_favorite_bene((requestBean.getIsFavBene()?"Y":"N"));
				}else {
					requestData.setIs_favorite_bene("N");
				}
			}
			requestData.setBene_nick_name(requestBean.getBeneAliasName());
			requestData.setBene_name(beneficiaryInquirySessionCache.getFastBeneficiaryResponseData().getBene_name());
			responseBean.setBeneAccountAliasName(requestBean.getBeneAliasName());//return to FE later
			responseBean.setBeneAccountName(beneficiaryInquirySessionCache.getFastBeneficiaryResponseData().getBene_name());//return to FE later
			responseBean.setBene_name(beneficiaryInquirySessionCache.getFastBeneficiaryResponseData().getBene_name());//return to FE later
			responseBean.setBene_acct_no(beneficiaryInquirySessionCache.getFastBeneficiaryResponseData().getBene_acct_no());//return to FE later
			responseBean.setBene_acct_cc(beneficiaryInquirySessionCache.getFastBeneficiaryResponseData().getBene_acct_ccy());//return to FE later
			responseBean.setBene_bank_id(beneficiaryInquirySessionCache.getFastBeneficiaryResponseData().getBene_bank_id());//return to FE later
			responseBean.setBene_bank_name(beneficiaryInquirySessionCache.getFastBeneficiaryResponseData().getBene_bank_name());//return to FE later
			responseBean.setTrx_purpose(requestBean.getTrx_purpose());
		}
		//cross currency transfer
		if(!requestData.getDebit_acct_ccy().equals(requestData.getBene_acct_ccy())){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
			log.info("Cross currency is not allowed for FAST");
			return;
		}
		//same currency transfer
		else{
			//set the amount currency to debit account currency.
			requestData.setAmount_ccy(requestData.getDebit_acct_ccy());
		}
		
		//Set notification requestData
		if(!processNotificationRequest(requestBean, responseBean, requestData)){
			return;
		}
		
		//Recurring settings
		processRecurringRequest(requestBean, requestData);
		
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setProd_cd(requestBean.getTransferServiceType());
		requestData.setSender_name(requestBean.getSenderName());
		requestData.setCustomer_ref(requestBean.getCustomerReferenceNo());
		requestData.setValue_date(requestBean.getValueDate());
		requestData.setAmount(requestBean.getAmount());
		requestData.setRemarks(requestBean.getRemark());
	}

	
	private void processResponse(String locale, TransactionSubmitResponseData responseData, ObTransactionRequest requestBean, ObTransactionResponse responseBean) throws Exception{
		if(responseData.getTrx_data() == null){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_NO_RESULT);
			return;
		}
		Transaction trxData = responseData.getTrx_data();
		responseBean.setRefId(responseData.getTrx_data().getBank_ref());
		responseBean.setCustomerRefNo(trxData.getCustomer_ref());
		responseBean.setDebitAccountNo(trxData.getDebit_acct_no());
		responseBean.setDebitAccountCcy(trxData.getDebit_acct_ccy());
//		responseBean.setBeneAccountAliasName(trxData.getBene_name());
		responseBean.setBeneAccountNo(trxData.getBene_acct_no());
		responseBean.setBeneAccountCcy(trxData.getBene_acct_ccy());
		responseBean.setValueDate(trxData.getValue_date());
		responseBean.setAmount(trxData.getAmount());
		responseBean.setAmountCcy(trxData.getAmount_ccy());
		responseBean.setRemark(requestBean.getRemark());
		responseBean.setBankRef(trxData.getBank_ref());
		
		/*
		 * LLG and RTGS response only
		 */
		responseBean.setSenderName(trxData.getSender_name());
		responseBean.setResidentStatus(requestBean.getResidentStatus());
		responseBean.setRemitterCategory(requestBean.getRemitterCategory());
		responseBean.setBeneCategory(requestBean.getBeneCategory());
		
		responseBean.setIsRecurring(requestBean.getIsRecurring());
		responseBean.setRecurringType(requestBean.getRecurringType());
		responseBean.setRecurringValue(requestBean.getRecurringValue());
		responseBean.setRecurringStartDate(requestBean.getRecurringStartDate());
		responseBean.setRecurringEndDate(requestBean.getRecurringEndDate());
		
		responseBean.setFxType(responseData.getTrx_data().getFx_type());
		responseBean.setFxDealerName(responseData.getTrx_data().getFx_dealer_name());
		responseBean.setFxContractNo(responseData.getTrx_data().getFx_contract_no());
		responseBean.setRate(trxData.getFx_rate());
		
		responseBean.setTrxStatusCode(trxData.getTrx_status());
		responseBean.setTrxStatusDesc(getTrxStatusDesc(locale, trxData.getTrx_status()));
		
		responseBean.setAdditional_info(trxData.getAdditional_info());
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
	
	private com.silverlake.mleb.mcb.module.vc.accountManagement.ResponseData getBeneDetails(VCGenericService vcService, ObTransactionRequest requestBean, ObTransactionResponse responseBean, String accountNo, String accountCcy) throws Exception{
		//Get beneficiary data from velo
		com.silverlake.mleb.mcb.module.vc.accountManagement.RequestData accInquiryRequestData = new com.silverlake.mleb.mcb.module.vc.accountManagement.RequestData();
		accInquiryRequestData.setOrg_cd(requestBean.getObUser().getOrgId());
		accInquiryRequestData.setUsr_cd(requestBean.getObUser().getLoginId());
		accInquiryRequestData.setAcct_no(accountNo);
		accInquiryRequestData.setAcct_ccy(accountCcy);
		accInquiryRequestData.setBalance_check("N");
		VCResponse<com.silverlake.mleb.mcb.module.vc.accountManagement.ResponseData> vcAccInquiryResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.ACCOUNT_MANAGEMENT_INQUIRY, 
				accInquiryRequestData, 
				com.silverlake.mleb.mcb.module.vc.accountManagement.ResponseData.class, 
				true);
		if(processVCResponseError(vcAccInquiryResponse, responseBean)){
			return null;
		}
		return vcAccInquiryResponse.getData();
	}
	
	private void processRecurringRequest(ObTransactionRequest requestBean, TransactionSubmitRequestData requestData){
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
	
	private boolean processNotificationRequest(ObTransactionRequest requestBean, ObTransactionResponse responseBean, TransactionSubmitRequestData requestData){
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
		
		if(requestBean.getIsSendBeneNotificaation() != null && requestBean.getIsSendBeneNotificaation()){
			if(requestBean.getBeneNotificationEmail() == null || requestBean.getBeneNotificationEmail().isEmpty()){
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
				log.info("Beneficiary notification email address cannot be empty");
				return false;
			}
			requestData.setSend_notif_bene(requestBean.getIsSendBeneNotificaation());
			requestData.setNotif_bene_email(requestBean.getBeneNotificationEmail());
		}
		
		return true;
	}
	
	/**
	 * For cross currency transfer (only when destination is not IDR), get the term and condition cache exist and match or not.
	 * Therefore, only OAT and IFT (allowed other currency on destination account) is eligible to have term and condition
	 * @param beneCcy
	 * @param requestBean
	 * @param responseBean
	 * @return true if tnc cache not required or valid
	 */
	private boolean validateTnCCache(String debitCcy, String beneCcy, ObTransactionRequest requestBean, ObTransactionResponse responseBean){
		//For cross currency transfer (only for IDR -> XXX), get the term and condition cache exist and match or not.
		if(debitCcy.equals(MiBConstant.CURRENCY_IDR) && !beneCcy.equals(MiBConstant.CURRENCY_IDR)){
			ObTNCCache tncCache = (ObTNCCache)(ObSessionCache)requestBean.getSessionCache().get(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TNC_GET.getId());
			if(tncCache == null || tncCache.getOmniResponse() == null || tncCache.getRequestData() == null){
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
				log.info("Unable to get cache data of TNC for cross currency transfer");
				return false;
			}
			if(!tncCache.getRequestData().getTncType().equals(MiBConstant.TNC_TYPE_FX)){
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
				log.info("tnc type not matched. "+tncCache.getRequestData().getTncType());
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Validate the value date with omni
	 * Return error code of MCB.1000124 when value date is invalid
	 * @param requestData
	 * @param requestBean
	 * @param responseBean
	 * @param vcService
	 * @throws Exception
	 */
	private void processValueDateChecking(TransactionSubmitRequestData requestData, ObTransactionRequest requestBean, ObTransactionResponse responseBean, VCGenericService vcService) throws Exception{
		//To check the value date
		ValidateValueDateResponseData valueDateResponseData;
		ValidateValueDateRequestData valueDateRequestData = new ValidateValueDateRequestData();
		valueDateRequestData.setOrg_cd(requestBean.getObUser().getOrgId());
		valueDateRequestData.setUsr_cd(requestBean.getObUser().getLoginId());
		valueDateRequestData.setProd_cd(requestBean.getTransferServiceType());
		valueDateRequestData.setValue_date(requestBean.getValueDate());
		valueDateRequestData.setBene_acct_ccy(requestData.getBene_acct_ccy());
		valueDateRequestData.setDebit_acct_ccy(requestData.getDebit_acct_ccy());
		valueDateRequestData.setBene_bank_code(requestData.getBene_bank_code());
		valueDateRequestData.setBene_bank_network_id(requestData.getBene_bank_network_id());
		valueDateRequestData.setBene_bank_branch(requestData.getBene_bank_branch());
		valueDateRequestData.setBene_bank_country(requestData.getBene_bank_country());
						
		VCResponse<ValidateValueDateResponseData> vdResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.TRANSACTION_CHECK_VALUE_DATE, 
				valueDateRequestData, 
				ValidateValueDateResponseData.class, 
				true);
		
		if(processVCResponseError(vdResponse, responseBean)){
			return;
		}	
		valueDateResponseData = vdResponse.getData();
		
		boolean IsBackDated = false;
		boolean IsCot = false;
		boolean IsHoliday = false;
		
		if(valueDateResponseData.getIs_backdated() != null && valueDateResponseData.getIs_backdated().equalsIgnoreCase("Y")){
			IsBackDated = true;
		}
		if(valueDateResponseData.getIs_cot() != null && valueDateResponseData.getIs_cot().equalsIgnoreCase("Y")){
			IsCot = true;
		}
		if(valueDateResponseData.getIs_holiday() != null && valueDateResponseData.getIs_holiday().equalsIgnoreCase("Y")){
			IsHoliday = true;
		}
		
		responseBean.setIsBackDated(IsBackDated);
		responseBean.setIsCot(IsCot);
		responseBean.setIsHoliday(IsHoliday);
		
		if(IsBackDated || IsCot || IsHoliday){
			responseBean.setNextValueDate(valueDateResponseData.getNext_value_date());
			responseBean.getObHeader().setStatusCode(MiBConstant.NOT_VALID_VALUE_DATE);
			return;
		}
	}
}
