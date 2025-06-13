package com.silverlake.mleb.mcb.module.func.transaction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.ObSessionCache;
import com.silverlake.mleb.mcb.bean.ObTransactionBeneficiarySessionCache;
import com.silverlake.mleb.mcb.bean.mgmtbene.ObTransactionManageBeneficiaryRequest;
import com.silverlake.mleb.mcb.bean.mgmtbene.ObTransactionManageBeneficiaryResponse;
import com.silverlake.mleb.mcb.bean.mgmtbene.ObTransactionManageBeneficiarySessionCache;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.entity.McbConf;
import com.silverlake.mleb.mcb.module.common.CacheSessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.transaction.OnlineBeneficiaryResponseData;
import com.silverlake.mleb.mcb.module.vc.transaction.management.AddBeneficiaryRequestData;
import com.silverlake.mleb.mcb.module.vc.transaction.management.AddBeneficiaryResponseData;

/**
 * Purpose: To add a beneficiary account into saved beneficiary list
 * 
 * For IFT following fields are required to grab from cache of beneficiary inquiry
 * bene_name
 * 
 * For OLT following fields are required to grab from cache of beneficiary inquiry
 * bene_name, bank_code, bank_name
 * 
 * Omni Web Services:
 * transaction_mgmt/add_bene
 * 
 * Required cache
 * MIB_HTTP_SESSION_TRANSACTION_BENEFICIARY_INQUIRY
 * 
 * @author Alex Loo
 *
 */
@Service
public class TransactionManageBeneficiaryAdd extends CacheSessionFuncServices<ObTransactionManageBeneficiaryRequest, ObTransactionManageBeneficiaryResponse, ObTransactionManageBeneficiarySessionCache>{
	private static Logger log = LogManager.getLogger(TransactionManageBeneficiaryAdd.class);
	
	@Override
	public void processInternalWithCache(String locale, String sessionId, String trxId, ObTransactionManageBeneficiaryRequest requestBean, ObTransactionManageBeneficiaryResponse responseBean, ObTransactionManageBeneficiarySessionCache cacheBean, VCGenericService vcService) throws Exception {
		AddBeneficiaryRequestData requestData = new AddBeneficiaryRequestData();
		AddBeneficiaryResponseData responseData;
		processRequestGeneral(requestData, requestBean, responseBean);
		if(requestBean.getTransferServiceType().equals(MiBConstant.TRANS_SERVICE_TYPE_IFT)){
			processRequestIFT(requestData, requestBean, responseBean);
		}else if(requestBean.getTransferServiceType().equals(MiBConstant.TRANS_SERVICE_TYPE_OLT)){
			processRequestOLT(requestData, requestBean, responseBean);
		}else if(requestBean.getTransferServiceType().equals(MiBConstant.TRANS_SERVICE_TYPE_LLG) || 
				requestBean.getTransferServiceType().equals(MiBConstant.TRANS_SERVICE_TYPE_RTGS)){
			processRequestLLG_RTGS(requestData, requestBean, responseBean);
		}else if(requestBean.getTransferServiceType().equals(MiBConstant.TRANS_SERVICE_TYPE_TT)){
			processRequestTT(requestData, requestBean, responseBean);
		}else if(requestBean.getTransferServiceType().equals(MiBConstant.TRANS_SERVICE_TYPE_FAST)){
			processRequestFAST(requestData, requestBean, responseBean);
		}else {
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
			log.info("Unsupported transaction service type");
			return;
		}
		if(responseBean.getObHeader().getStatusCode() != null){
			return;
		}
		VCResponse<AddBeneficiaryResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.TRANSACTION_MGMT_ADD_BENE, 
				requestData, 
				AddBeneficiaryResponseData.class, 
				true);
		if(processVCResponseError(vcResponse, responseBean, false)){
			return;
		}
		
		responseData = vcResponse.getData();
		processResponse(responseData, requestBean, responseBean);
	}
	
	private void processRequestGeneral(AddBeneficiaryRequestData requestData, ObTransactionManageBeneficiaryRequest requestBean, ObTransactionManageBeneficiaryResponse responseBean) throws Exception{
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setProd_cd(requestBean.getTransferServiceType());
		requestData.setNick_name(requestBean.getNickName());
		requestData.setAccount_number(requestBean.getAccountNo());
		requestData.setEmail_address(requestBean.getEmail());
		requestData.setPhone_number(requestBean.getPhoneNumber());
		requestData.setIs_shared(requestBean.getIsShared());
	}
	
	private void processRequestIFT(AddBeneficiaryRequestData requestData, ObTransactionManageBeneficiaryRequest requestBean, ObTransactionManageBeneficiaryResponse responseBean) throws Exception{
		//Only IFT is required to get the beneficiary details from cache (FE called before hand)
		ObTransactionBeneficiarySessionCache beneficiaryInquirySessionCache = (ObTransactionBeneficiarySessionCache)((ObSessionCache)requestBean.getSessionCache().get(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TRANSACTION_BENEFICIARY_INQUIRY.getId()));
		if(beneficiaryInquirySessionCache == null || beneficiaryInquirySessionCache.getAccountDetailsResponseData() == null){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			log.info("Transaction beneficiary inquiry cache is not found");
			return;
		}
		
		com.silverlake.mleb.mcb.module.vc.accountManagement.ResponseData accInquiryResponseData = beneficiaryInquirySessionCache.getAccountDetailsResponseData();
		if(!accInquiryResponseData.getAcct_no().equals(requestBean.getAccountNo())){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
			log.info("Transaction beneficiary inquiry cache account do not matched");
			return;
		}
		requestData.setBene_name(accInquiryResponseData.getAcct_name());
		if(accInquiryResponseData.getMcbit().equalsIgnoreCase("Y")) {
			if(requestBean.getAccountCcy() == null || requestBean.getAccountCcy().isEmpty()){
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
				log.info("Account currency is required for IFT");
				return;
			}
			requestData.setAccount_currency(requestBean.getAccountCcy());
		}else {
			requestData.setAccount_currency(MiBConstant.CURRENCY_IDR);
		}
		requestData.setBank_country_code("ID");
		requestData.setBank_code("028");
		requestData.setBank_name("OCBC NISP");
	}
	
	private void processRequestOLT(AddBeneficiaryRequestData requestData, ObTransactionManageBeneficiaryRequest requestBean, ObTransactionManageBeneficiaryResponse responseBean) throws Exception{
		ObTransactionBeneficiarySessionCache beneficiaryInquirySessionCache = (ObTransactionBeneficiarySessionCache)(ObSessionCache)requestBean.getSessionCache().get(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TRANSACTION_BENEFICIARY_INQUIRY.getId());
		if(beneficiaryInquirySessionCache == null || beneficiaryInquirySessionCache.getOnlineBeneficiaryResponseData() == null){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			log.info("Transaction beneficiary inquiry cache is not found (Required by OLT)");
			return;
		}
		
		requestData.setBene_name(beneficiaryInquirySessionCache.getOnlineBeneficiaryResponseData().getBeneficiary_name());
		requestData.setAccount_currency(MiBConstant.CURRENCY_IDR);
		requestData.setBank_country_code("ID");
		requestData.setBank_code(beneficiaryInquirySessionCache.getBeneBankDetails().getBankCode());
		requestData.setBank_name(beneficiaryInquirySessionCache.getBeneBankDetails().getBankName());
	}
	
	private void processRequestFAST(AddBeneficiaryRequestData requestData, ObTransactionManageBeneficiaryRequest requestBean, ObTransactionManageBeneficiaryResponse responseBean) throws Exception{
		ObTransactionBeneficiarySessionCache beneficiaryInquirySessionCache = (ObTransactionBeneficiarySessionCache)(ObSessionCache)requestBean.getSessionCache().get(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TRANSACTION_BENEFICIARY_INQUIRY.getId());
		if(beneficiaryInquirySessionCache == null || beneficiaryInquirySessionCache.getFastBeneficiaryResponseData() == null){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			log.info("Transaction beneficiary inquiry cache is not found (Required by FAST)");
			return;
		}
		
		requestData.setBene_name(beneficiaryInquirySessionCache.getFastBeneficiaryResponseData().getBene_name());
		requestData.setAccount_currency(MiBConstant.CURRENCY_IDR);
		requestData.setBank_country_code("ID");
		requestData.setBank_code(beneficiaryInquirySessionCache.getBeneBankDetails().getBankCode());
		requestData.setBank_name(beneficiaryInquirySessionCache.getBeneBankDetails().getBankName());
		requestData.setProxy_data(beneficiaryInquirySessionCache.getFastBeneficiaryRequestData().getProxy_data());
		requestData.setProxy_type(beneficiaryInquirySessionCache.getFastBeneficiaryRequestData().getProxy_type());
	}
	
	private void processRequestLLG_RTGS(AddBeneficiaryRequestData requestData, ObTransactionManageBeneficiaryRequest requestBean, ObTransactionManageBeneficiaryResponse responseBean) throws Exception{
		if(requestBean.getAccountName() == null || requestBean.getAccountName().isEmpty() ||
				requestBean.getBankCode() == null || requestBean.getBankCode().isEmpty() ||
				requestBean.getBankName() == null || requestBean.getBankName().isEmpty() ||
				requestBean.getBankBranch() == null || requestBean.getBankBranch().isEmpty() ||
				requestBean.getNetworkCode() == null || requestBean.getNetworkCode().isEmpty() ||
				requestBean.getBeneAddress1() == null || requestBean.getBeneAddress1().isEmpty()){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
			log.info("Incomplete data for LLG/RTGS");
			return;
		}
		requestData.setBene_name(requestBean.getAccountName());
		requestData.setAccount_currency(MiBConstant.CURRENCY_IDR);
		requestData.setBank_country_code("ID");
		requestData.setBank_code(requestBean.getBankCode());
		requestData.setBank_name(requestBean.getBankName());
		requestData.setBene_address1(requestBean.getBeneAddress1());
		requestData.setBene_address2(requestBean.getBeneAddress2());
		requestData.setBene_address3(requestBean.getBeneAddress3());
		requestData.setBank_city(requestBean.getBankBranch());
		requestData.setBank_network_clearing_code(requestBean.getNetworkCode());
		requestData.setRtgs_member_code(requestBean.getRtgsMemberCode());
		requestData.setBic_code(requestBean.getParticipantBic());
	}
	
	private void processRequestTT(AddBeneficiaryRequestData requestData, ObTransactionManageBeneficiaryRequest requestBean, ObTransactionManageBeneficiaryResponse responseBean) throws Exception{
		if(requestBean.getAccountName() == null || requestBean.getAccountName().isEmpty() ||
				requestBean.getAccountCcy() == null || requestBean.getAccountCcy().isEmpty() ||
				requestBean.getBankCountryCode() == null || requestBean.getBankCountryCode().isEmpty() ||
				requestBean.getBankName() == null || requestBean.getBankName().isEmpty() ||
				requestBean.getBankBranch() == null || requestBean.getBankBranch().isEmpty() ||
				requestBean.getNetworkCode() == null || requestBean.getNetworkCode().isEmpty() ||
				requestBean.getBeneAddress1() == null || requestBean.getBeneAddress1().isEmpty()){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
			log.info("Incomplete data for TT");
			return;
		}
		requestData.setBene_name(requestBean.getAccountName());
		requestData.setAccount_currency(requestBean.getAccountCcy());
		requestData.setBank_country_code(requestBean.getBankCountryCode());
		requestData.setBank_code("");
		requestData.setBank_name(requestBean.getBankName());
		requestData.setBene_address1(requestBean.getBeneAddress1());
		requestData.setBene_address2(requestBean.getBeneAddress2());
		requestData.setBene_address3(requestBean.getBeneAddress3());
		requestData.setBank_city(requestBean.getBankBranch());
		requestData.setBank_network_clearing_code(requestBean.getNetworkCode());
	}
	
	private void processResponse(AddBeneficiaryResponseData responseData, ObTransactionManageBeneficiaryRequest requestBean, ObTransactionManageBeneficiaryResponse responseBean) throws Exception{
		
	}
}
