package com.silverlake.mleb.mcb.module.func.transaction;

import java.util.LinkedHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.ObTransactionRequest;
import com.silverlake.mleb.mcb.bean.ObTransactionResponse;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.init.InitApp;
import com.silverlake.mleb.mcb.module.common.SessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.others.BeneficiaryAffiliationList;
import com.silverlake.mleb.mcb.module.vc.others.BeneficiaryAffiliationResponseData;
import com.silverlake.mleb.mcb.module.vc.others.LLDRequestData;

/**
 * Purpose: To get List of TT Beneficiary Affiliation
 * 
 * Omni Web Services:
 * others/lld/beneficiary_affiliation
 *
 */
@Service
public class RetrieveBeneficiaryAffiliation extends SessionFuncServices<ObTransactionRequest, ObTransactionResponse> {
	
	private static Logger log = LogManager.getLogger(RetrieveBeneficiaryAffiliation.class); 

    @Override
    public void processInternal(String locale, String sessionId, String trxId, ObTransactionRequest requestBean, ObTransactionResponse responseBean, VCGenericService vcService) throws Exception {
    	    	    	
    	LLDRequestData lldRequestData = new LLDRequestData();
    	processRequest(locale, lldRequestData, requestBean);
    	
		VCResponse<BeneficiaryAffiliationResponseData> vcBeneAResp = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.OTHERS_LLD_BENEFICIARY_AFFILIATION, 
				lldRequestData, 
				null, 
				true);
		if(processVCResponseError(vcBeneAResp, responseBean)){
			return;
		}
		
		processResponse(vcBeneAResp, responseBean);
    }
   	

	private void processRequest(String locale, LLDRequestData requestData, ObTransactionRequest requestBean) throws Exception
	{
		requestData.setLang(locale);
		requestData.setPurpose_code(requestBean.getPurposeCode());
		requestData.setBene_country_code(requestBean.getBeneCountryCode());
		requestData.setBene_category_code(requestBean.getBeneCategoryCode());
		requestData.setRemitter_country_code(requestBean.getRemitterCountryCode());
		requestData.setRemitter_category_code(requestBean.getRemitterCategoryCode());
	}
	
	private void processResponse(VCResponse<BeneficiaryAffiliationResponseData> vcBeneAResp, ObTransactionResponse responseBean) throws Exception {
		responseBean.setBeneficiaryAffiliationList(new LinkedHashMap<String, String>(10));
		
		if(vcBeneAResp.getData() != null){
			BeneficiaryAffiliationResponseData beneABean = InitApp.mapper.map(vcBeneAResp, BeneficiaryAffiliationResponseData.class);
			for (BeneficiaryAffiliationList beneficiaryAffiliation : beneABean.getData()) {
				responseBean.getBeneficiaryAffiliationList().put(beneficiaryAffiliation.getBene_affiliation_code(), beneficiaryAffiliation.getDescription());
			}
		}
		
		responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
	}
}
