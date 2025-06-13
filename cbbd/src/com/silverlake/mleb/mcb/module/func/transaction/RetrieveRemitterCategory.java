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
import com.silverlake.mleb.mcb.module.vc.others.RemitterCategoryList;
import com.silverlake.mleb.mcb.module.vc.others.RemitterCategoryResponseData;

/**
 * Purpose: To get List of TT Remitter Category
 * 
 * Omni Web Services:
 * others/lld/remitter_category
 *
 */
@Service
public class RetrieveRemitterCategory extends SessionFuncServices<ObTransactionRequest, ObTransactionResponse> {
	
	private static Logger log = LogManager.getLogger(RetrieveRemitterCategory.class); 

    @Override
    public void processInternal(String locale, String sessionId, String trxId, ObTransactionRequest requestBean, ObTransactionResponse responseBean, VCGenericService vcService) throws Exception {
    	    	    	
    	LLDRequestData lldRequestData = new LLDRequestData();
    	processRequest(locale, lldRequestData, requestBean);
    	
		VCResponse<RemitterCategoryResponseData> vcremitterCatResp = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.OTHERS_LLD_REMITTER_CATEGORY, 
				lldRequestData, 
				null, 
				true);
		if(processVCResponseError(vcremitterCatResp, responseBean)){
			return;
		}
		
		processResponse(vcremitterCatResp, responseBean);
    }
   	

	private void processRequest(String locale, LLDRequestData requestData, ObTransactionRequest requestBean) throws Exception
	{
		requestData.setLang(locale);
		requestData.setPurpose_code(requestBean.getPurposeCode());
		requestData.setBene_country_code(requestBean.getBeneCountryCode());
		requestData.setBene_category_code(requestBean.getBeneCategoryCode());
		requestData.setRemitter_country_code(requestBean.getRemitterCountryCode());
	}
	
	private void processResponse(VCResponse<RemitterCategoryResponseData> vcremitterCatResp, ObTransactionResponse responseBean) throws Exception {
		responseBean.setRemitterCategoryList(new LinkedHashMap<String, String>(10));
		
		if(vcremitterCatResp.getData() != null){
			RemitterCategoryResponseData remitterCatbean = InitApp.mapper.map(vcremitterCatResp, RemitterCategoryResponseData.class);
			for (RemitterCategoryList remitterCat : remitterCatbean.getData()) {
				responseBean.getRemitterCategoryList().put(remitterCat.getRemitter_category_code(), remitterCat.getDescription());
			}
		}
		
		responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
	}
}
