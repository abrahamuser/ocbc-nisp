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
import com.silverlake.mleb.mcb.module.vc.others.BeneficiaryCategoryList;
import com.silverlake.mleb.mcb.module.vc.others.BeneficiaryCategoryResponseData;
import com.silverlake.mleb.mcb.module.vc.others.LLDRequestData;

/**
 * Purpose: To get List of TT Beneficiary Category
 * 
 * Omni Web Services:
 * others/lld/beneficiary_category
 *
 */
@Service
public class RetrieveBeneficiaryCategory extends SessionFuncServices<ObTransactionRequest, ObTransactionResponse> {
	
	private static Logger log = LogManager.getLogger(RetrieveBeneficiaryCategory.class); 

    @Override
    public void processInternal(String locale, String sessionId, String trxId, ObTransactionRequest requestBean, ObTransactionResponse responseBean, VCGenericService vcService) throws Exception {
    	    	    	
    	LLDRequestData lldRequestData = new LLDRequestData();
    	processRequest(locale, lldRequestData, requestBean);
    	
		VCResponse<BeneficiaryCategoryResponseData> vcBeneCatResp = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.OTHERS_LLD_BENE_CATEGORY, 
				lldRequestData, 
				null, 
				true);
		if(processVCResponseError(vcBeneCatResp, responseBean)){
			return;
		}
		
		processResponse(vcBeneCatResp, responseBean);
    }
   	

	private void processRequest(String locale, LLDRequestData requestData, ObTransactionRequest requestBean) throws Exception
	{
		requestData.setLang(locale);
		requestData.setPurpose_code(requestBean.getPurposeCode());
	}
	
	private void processResponse(VCResponse<BeneficiaryCategoryResponseData> vcBeneCatResp, ObTransactionResponse responseBean) throws Exception {
		responseBean.setBeneficiaryCategoryList(new LinkedHashMap<String, String>(10));
		
		if(vcBeneCatResp.getData() != null){
			BeneficiaryCategoryResponseData beneCatbean = InitApp.mapper.map(vcBeneCatResp, BeneficiaryCategoryResponseData.class);
			for (BeneficiaryCategoryList beneCat : beneCatbean.getData()) {
				responseBean.getBeneficiaryCategoryList().put(beneCat.getBene_category_code(), beneCat.getDescription());
			}
		}
		
		responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
	}
}
