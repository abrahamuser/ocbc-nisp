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
import com.silverlake.mleb.mcb.module.vc.others.LLDRequestData;
import com.silverlake.mleb.mcb.module.vc.others.RemitterCountryList;
import com.silverlake.mleb.mcb.module.vc.others.RemitterCountryResponseData;

/**
 * Purpose: To get List of TT Remitter Country Of Residence
 * 
 * Omni Web Services:
 * others/lld/remitter_country_of_resd
 *
 */
@Service
public class RetrieveRemitterCountry extends SessionFuncServices<ObTransactionRequest, ObTransactionResponse> {
	
	private static Logger log = LogManager.getLogger(RetrieveRemitterCountry.class); 

    @Override
    public void processInternal(String locale, String sessionId, String trxId, ObTransactionRequest requestBean, ObTransactionResponse responseBean, VCGenericService vcService) throws Exception {
    	    	    	
    	LLDRequestData lldRequestData = new LLDRequestData();
    	processRequest(locale, lldRequestData, requestBean);
    	
		VCResponse<RemitterCountryResponseData> vcremitterCountryResp = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.OTHERS_LLD_REMITTER_COUNTRY_RESD, 
				lldRequestData, 
				null, 
				true);
		if(processVCResponseError(vcremitterCountryResp, responseBean)){
			return;
		}
		
		processResponse(vcremitterCountryResp, responseBean);
    }
   	

	private void processRequest(String locale, LLDRequestData requestData, ObTransactionRequest requestBean) throws Exception
	{
		requestData.setLang(locale);
		requestData.setPurpose_code(requestBean.getPurposeCode());
		requestData.setBene_country_code(requestBean.getBeneCountryCode());
		requestData.setBene_category_code(requestBean.getBeneCategoryCode());
	}
	
	private void processResponse(VCResponse<RemitterCountryResponseData> vcremitterCountryResp, ObTransactionResponse responseBean) throws Exception {
		responseBean.setRemitterCountryList(new LinkedHashMap<String, String>(10));
		
		if(vcremitterCountryResp.getData() != null){
			RemitterCountryResponseData remitterCountrybean = InitApp.mapper.map(vcremitterCountryResp, RemitterCountryResponseData.class);
			for (RemitterCountryList remitterCountry : remitterCountrybean.getData()) {
				responseBean.getRemitterCountryList().put(remitterCountry.getRemitter_country_code(), remitterCountry.getDescription());
			}
		}
		
		responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
	}
}
