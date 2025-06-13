package com.silverlake.mleb.mcb.module.func.timedeposit;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.ObSessionCache;
import com.silverlake.mleb.mcb.bean.ObTNCCache;
import com.silverlake.mleb.mcb.bean.common.ObUserRoleBean;
import com.silverlake.mleb.mcb.bean.timedeposit.ObTimeDepositRequest;
import com.silverlake.mleb.mcb.bean.timedeposit.ObTimeDepositResponse;
import com.silverlake.mleb.mcb.bean.timedeposit.ObTimeDepositSessionCache;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.entity.GeneralCode;
import com.silverlake.mleb.mcb.entity.dao.GeneralCodeDAO;
import com.silverlake.mleb.mcb.module.common.CacheSessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.others.File;
import com.silverlake.mleb.mcb.module.vc.others.TNCRequestData;
import com.silverlake.mleb.mcb.module.vc.others.TNCResponseData;
import com.silverlake.mleb.mcb.module.vc.others.TNCv2RequestData;
import com.silverlake.mleb.mcb.module.vc.timedeposit.TimeDepositSubmitRequestData;
import com.silverlake.mleb.mcb.module.vc.user.List_role;
import com.silverlake.mleb.mcb.module.vc.timedeposit.TimeDepositResponseData;

@Service
public class TimeDepositAccountOpeningAcknowledgement extends CacheSessionFuncServices<ObTimeDepositRequest, ObTimeDepositResponse, ObTimeDepositSessionCache>{
	private static Logger log = LogManager.getLogger(TimeDepositAccountOpeningAcknowledgement.class);
	
	@Autowired GeneralCodeDAO gnDao;
	
	@Override
	public void processInternalWithCache(String locale, String sessionId, String trxId, ObTimeDepositRequest requestBean, ObTimeDepositResponse responseBean, ObTimeDepositSessionCache cacheBean, VCGenericService vcService) throws Exception {
		TimeDepositSubmitRequestData requestData = new TimeDepositSubmitRequestData();
		TimeDepositResponseData responseData;
		processRequest(locale, requestData, requestBean, responseBean, vcService);
		if(responseBean.getObHeader().getStatusCode() != null){
			return;
		}
		VCResponse<TimeDepositResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.TIME_DEPOSIT_OPENING_SUBMIT, 
				requestData, 
				TimeDepositResponseData.class, 
				true,
				null,
				requestBean.getIp());
		
		if(processVCResponseError(vcResponse, responseBean)){
			return;
		}
		
		responseData = vcResponse.getData();
		processResponse(locale, responseData, requestBean, responseBean);
		
		ObTNCCache tncCache = (ObTNCCache)(ObSessionCache)requestBean.getSessionCache().get(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TNC_V2.getId());
		if(tncCache != null){
			try{
				TNCv2RequestData tncRequestData = new TNCv2RequestData();
				tncRequestData.setOrg_cd(requestBean.getObUser().getOrgId());
				tncRequestData.setUsr_cd(requestBean.getObUser().getLoginId());
				tncRequestData.setTnc_type(tncCache.getRequestData().getTncType());
				tncRequestData.setTxn_reference_no(responseData.getTrx_data().getBank_ref());
				tncRequestData.setFile_list(tncCache.getOmniResponseTnCv2().getFile_list());
				for (int i = 0; i < tncCache.getOmniResponseTnCv2().getFile_list().size(); i++) {
				    tncRequestData.setFile_type(tncCache.getOmniResponseTnCv2().getFile_list().get(0).getFile_type());
				}
				vcService.callOmniVC(
						VCMethodConstant.REST_METHODS.OTHERS_TNC_CONFIRM_V2, 
						tncRequestData, 
						TNCResponseData.class, 
						true);
				//Do not care about the negative response of tnc
			}catch(Exception e){
				log.catching(e);
			}
		}
		
		if(tncCache != null){
			try{
				TNCv2RequestData tncRequestData = new TNCv2RequestData();
				tncRequestData.setOrg_cd(requestBean.getObUser().getOrgId());
				tncRequestData.setUsr_cd(requestBean.getObUser().getLoginId());
				tncRequestData.setTnc_type(tncCache.getRequestData().getTncType());
				tncRequestData.setTxn_reference_no(responseData.getTrx_data().getBank_ref());
				tncRequestData.setFile_list(tncCache.getOmniResponseTnCv2().getFile_list());
				for (int i = 0; i < tncCache.getOmniResponseTnCv2().getFile_list().size(); i++) {
					tncRequestData.setFile_type(tncCache.getOmniResponseTnCv2().getFile_list().get(1).getFile_type());
				}
				vcService.callOmniVC(
						VCMethodConstant.REST_METHODS.OTHERS_TNC_CONFIRM_V2, 
						tncRequestData, 
						TNCResponseData.class, 
						true);
				//Do not care about the negative response of tnc
			}catch(Exception e){
				log.catching(e);
			}
		}
	}
	
	private void processRequest(String locale, TimeDepositSubmitRequestData requestData, ObTimeDepositRequest requestBean, ObTimeDepositResponse responseBean, VCGenericService vcService) throws Exception{
		ObTimeDepositSessionCache confirmationSessionCache = requestBean.getSessionCache().get(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TIME_DEPOSIT_ACCOUNT_OPENING_CONFIRMATION.getId());
		if(confirmationSessionCache.getInquiryRateResponseData() == null){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			log.info("Confirmation cache is not found");
			return;
		}
		
		if(!requestBean.getTransactionId().equals(confirmationSessionCache.getTransactionId())){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			log.info("Transaction id not matched");
			return;
		}
		
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setPurpose_cd(confirmationSessionCache.getInquiryRateRequestData().getPurpose_cd());
		requestData.setProd_cd(confirmationSessionCache.getInquiryRateRequestData().getProd_cd());
		requestData.setSource_fund_cd(confirmationSessionCache.getInquiryRateRequestData().getSource_fund_cd());
		requestData.setRollover_type_cd(confirmationSessionCache.getInquiryRateRequestData().getRollover_type_cd());

		requestData.setBank_ref(confirmationSessionCache.getInquiryRateRequestData().getBank_ref());
		requestData.setDebit_acct_no(confirmationSessionCache.getInquiryRateRequestData().getDebit_acct_no());
		requestData.setDebit_acct_ccy(confirmationSessionCache.getInquiryRateRequestData().getDebit_acct_ccy());
		requestData.setAmount(confirmationSessionCache.getInquiryRateRequestData().getAmount());
		requestData.setAmount_ccy(confirmationSessionCache.getInquiryRateRequestData().getAmount_ccy());
		requestData.setTenor(confirmationSessionCache.getInquiryRateRequestData().getTenor());
		requestData.setTenor_type(confirmationSessionCache.getInquiryRateRequestData().getTenor_type());
		requestData.setInterest_term(confirmationSessionCache.getInquiryRateRequestData().getInterest_term());
		requestData.setInterest_term_code(confirmationSessionCache.getInquiryRateRequestData().getInterest_term_code());
		requestData.setSpecial_cd(confirmationSessionCache.getInquiryRateRequestData().getSpecial_cd());
		
		requestData.setBase_interest_rate(confirmationSessionCache.getInquiryRateResponseData().getBase_interest_rate());
		requestData.setVariant_rate(confirmationSessionCache.getInquiryRateResponseData().getVariant_rate());
		requestData.setVariant_rate_op(confirmationSessionCache.getInquiryRateResponseData().getVariant_rate_op());
		requestData.setDeal_type(confirmationSessionCache.getInquiryRateResponseData().getDeal_type());
		requestData.setVersion_variant_rate(confirmationSessionCache.getInquiryRateResponseData().getVersion_variant_rate());
		if(locale.equalsIgnoreCase(MiBConstant.LANG_EN)){
			requestData.setLang("en");
		}else if(locale.toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase())){
			requestData.setLang("cn");
		}else{
			requestData.setLang("id");
		}
		
		requestData.setDevice_id(requestBean.getObDevice()==null?null:requestBean.getObDevice().getDeviceId());
		requestData.setDevice_os(requestBean.getObDevice()==null?null:requestBean.getObDevice().getOs());
		requestData.setDevice_type(requestBean.getObDevice()==null?null:requestBean.getObDevice().getModel());
		
		//Set value for response bean
		responseBean.setInterestRate(confirmationSessionCache.getInterestRate());
		responseBean.setAmount(requestData.getAmount());
		responseBean.setAmountCcy(requestData.getAmount_ccy());
		responseBean.setSourceOfFundCode(requestData.getSource_fund_cd());
		responseBean.setPurposeCode(requestData.getPurpose_cd());
		responseBean.setTenor(requestData.getTenor());
		responseBean.setTenorType(requestData.getTenor_type());
		responseBean.setInterestTerm(requestData.getInterest_term());
		responseBean.setInterestTermCode(requestData.getInterest_term_code());
		responseBean.setRolloverTypeCode(requestData.getRollover_type_cd());
		responseBean.setSourceOfFundDesc(confirmationSessionCache.getSourceOfFundDesc());
		responseBean.setPurposeDesc(confirmationSessionCache.getPurposeDesc());
		responseBean.setRolloverTypeDesc(confirmationSessionCache.getRolloverTypeDesc());
		responseBean.setDebitAccountAliasName(confirmationSessionCache.getDebitAccountAliasName());
		responseBean.setDebitAccountType(confirmationSessionCache.getDebitAccountType());
	}
	
	private void processResponse(String locale, TimeDepositResponseData responseData, ObTimeDepositRequest requestBean, ObTimeDepositResponse responseBean) throws Exception{
		if(responseData.getTrx_data() != null) {
			responseBean.setBankRef(responseData.getTrx_data().getBank_ref());
			responseBean.setDebitAccountNo(responseData.getTrx_data().getDebit_acct_no());
			responseBean.setTrxStatusCode(responseData.getTrx_data().getTrx_status());
			responseBean.setTrxStatusDesc(getTrxStatusDesc(locale, responseData.getTrx_data().getTrx_status()));
		}
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
}
