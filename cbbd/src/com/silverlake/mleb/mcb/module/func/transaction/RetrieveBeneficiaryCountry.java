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
import com.silverlake.mleb.mcb.module.vc.others.BeneficiaryCountryList;
import com.silverlake.mleb.mcb.module.vc.others.BeneficiaryCountryResponseData;
import com.silverlake.mleb.mcb.module.vc.others.LLDRequestData;

/**
 * Purpose: To get List of TT  Beneficiary Country Of Residence
 * 
 * Omni Web Services:
 * others/lld/beneficiary_country_of_resd
 *
 */
@Service
public class RetrieveBeneficiaryCountry extends SessionFuncServices<ObTransactionRequest, ObTransactionResponse> {
	
	private static Logger log = LogManager.getLogger(RetrieveBeneficiaryCountry.class); 

    @Override
    public void processInternal(String locale, String sessionId, String trxId, ObTransactionRequest requestBean, ObTransactionResponse responseBean, VCGenericService vcService) throws Exception {
    	    	    	
    	LLDRequestData lldRequestData = new LLDRequestData();
    	processRequest(locale, lldRequestData, requestBean);
    	
		VCResponse<BeneficiaryCountryResponseData> vcBeneCountryResp = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.OTHERS_LLD_BENE_COUNTRY_RESD, 
				lldRequestData, 
				null, 
				true);
		if(processVCResponseError(vcBeneCountryResp, responseBean)){
			return;
		}
		
		processResponse(vcBeneCountryResp, responseBean);
    }
   	

	private void processRequest(String locale, LLDRequestData requestData, ObTransactionRequest requestBean) throws Exception
	{
		requestData.setLang(locale);
		requestData.setPurpose_code(requestBean.getPurposeCode());
	}
	
	private void processResponse(VCResponse<BeneficiaryCountryResponseData> vcBeneCountryResp, ObTransactionResponse responseBean) throws Exception {
		responseBean.setBeneficiaryCountryList(new LinkedHashMap<String, String>(10));
		
		if(vcBeneCountryResp.getData() != null){
			BeneficiaryCountryResponseData beneCountrybean = InitApp.mapper.map(vcBeneCountryResp, BeneficiaryCountryResponseData.class);
			for (BeneficiaryCountryList beneCountry : beneCountrybean.getData()) {
				responseBean.getBeneficiaryCountryList().put(beneCountry.getBene_country_code(), beneCountry.getDescription());
			}
		}
		
		responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
	}
}
