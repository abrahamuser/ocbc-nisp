package com.silverlake.mleb.mcb.module.func.others;

import java.util.Collections;
import java.util.LinkedHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.ObBankBean;
import com.silverlake.mleb.mcb.bean.ObCountryListRequest;
import com.silverlake.mleb.mcb.bean.ObCountryListResponse;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.module.common.SessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.others.CountryList;
import com.silverlake.mleb.mcb.module.vc.others.CountryListRequestData;
import com.silverlake.mleb.mcb.module.vc.others.CountryListResponseData;

/**
 * Purpose: Get country list 
 *  
 * Omni Web Services:
 * other/country
 * 
 * @author Alex Loo
 *
 */
@Service
public class RetrieveCountryList extends SessionFuncServices<ObCountryListRequest, ObCountryListResponse>{
	private static Logger log = LogManager.getLogger(RetrieveCountryList.class);

	@Override
	public void processInternal(String locale, String sessionId, String trxId, ObCountryListRequest requestBean,
			ObCountryListResponse responseBean, VCGenericService vcService) throws Exception {
		CountryListRequestData requestData = new CountryListRequestData();
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		
		VCResponse<CountryListResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.OTHERS_COUNTRY, 
				requestData, 
				CountryListResponseData.class, 
				true);
		if(processVCResponseError(vcResponse, responseBean)){
			return;
		}
		CountryListResponseData responseData = vcResponse.getData();
		if(responseData.getCountry_list() != null){
			responseBean.setCountryList(new LinkedHashMap<String, String>(responseData.getCountry_list().size()));
			for(CountryList countryList:responseData.getCountry_list()){
				responseBean.getCountryList().put(countryList.getCountry_code(), countryList.getCountry_name());
			}
		}else {
			responseBean.setCountryList(Collections.<String, String>emptyMap());
		}
	}
}
