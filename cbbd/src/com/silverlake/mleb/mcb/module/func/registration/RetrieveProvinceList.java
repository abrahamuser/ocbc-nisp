package com.silverlake.mleb.mcb.module.func.registration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.ObRegistrationRequest;
import com.silverlake.mleb.mcb.bean.ObRegistrationResponse;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.module.common.SessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.registration.ProvinceListRequestData;
import com.silverlake.mleb.mcb.module.vc.registration.ProvinceListResponseData;

/**
 * Purpose: Get the list of provinces 
 *  
 * Omni Web Services:
 * others/province
 *  */
@Service
public class RetrieveProvinceList extends SessionFuncServices<ObRegistrationRequest, ObRegistrationResponse>{
	private static Logger log = LogManager.getLogger(RetrieveProvinceList.class);

	@Override
	public void processInternal(String locale, String sessionId, String trxId, ObRegistrationRequest requestBean,
			ObRegistrationResponse responseBean, VCGenericService vcService) throws Exception {
		ProvinceListRequestData requestData = new ProvinceListRequestData();
			
		VCResponse<ProvinceListResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.PROVINCE_LIST, 
				requestData, 
				ProvinceListResponseData.class, 
				true);
		if(processVCResponseError(vcResponse, responseBean)){
			return;
		}
		ProvinceListResponseData responseData = vcResponse.getData();
		if(responseData.getProvince_list() != null){
			responseBean.setProvince_list(responseData.getProvince_list());
		}
	}
}
