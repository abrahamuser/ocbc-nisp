package com.silverlake.mleb.mcb.module.func.others;

import java.util.ArrayList;
import java.util.Collections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.ObBankBean;
import com.silverlake.mleb.mcb.bean.ObBankListRequest;
import com.silverlake.mleb.mcb.bean.ObBankListResponse;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.module.common.SessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.others.BankListInfo;
import com.silverlake.mleb.mcb.module.vc.others.BankListRequestData;
import com.silverlake.mleb.mcb.module.vc.others.BankListResponseData;

/**
 * Purpose: Get the bank swift details by swift code 
 *  
 * Omni Web Services:
 * other/banks/swift
 * 
 * @author Alex Loo
 *
 */
@Service
public class RetrieveBankSwiftDetails extends SessionFuncServices<ObBankListRequest, ObBankListResponse>{
	private static Logger log = LogManager.getLogger(RetrieveBankSwiftDetails.class);

	@Override
	public void processInternal(String locale, String sessionId, String trxId, ObBankListRequest requestBean,
			ObBankListResponse responseBean, VCGenericService vcService) throws Exception {
		BankListRequestData requestData = new BankListRequestData();
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setNetwork_clearing_code(requestBean.getNetworkClearingCode());
		
		VCResponse<BankListResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.OTHERS_BANKS_SWIFT, 
				requestData, 
				BankListResponseData.class, 
				true);
		if(processVCResponseError(vcResponse, responseBean)){
			return;
		}
		BankListResponseData responseData = vcResponse.getData();
		if(responseData.getBank_info() != null){
			responseBean.setBankList(new ArrayList<ObBankBean>());
			BankListInfo bankInfo = responseData.getBank_info();
			ObBankBean bankBean = new ObBankBean();
			bankBean.setBankCode(bankInfo.getBank_code());
			bankBean.setBankName(bankInfo.getBank_name());
			bankBean.setBranchName(bankInfo.getBranch_name());
			bankBean.setAddress1(bankInfo.getAddress_1());
			bankBean.setAddress2(bankInfo.getAddress_2());
			bankBean.setAddress3(bankInfo.getAddress_3());
			bankBean.setProvinceCode(bankInfo.getProvince_code());
			bankBean.setCountryCode(bankInfo.getCountry_code());
			bankBean.setCityCode(bankInfo.getCity_code());
			bankBean.setCityName(bankInfo.getCity_name());
			bankBean.setNetworkCode(bankInfo.getNetwork_clearing_code());
			
			responseBean.getBankList().add(bankBean);
		}else {
			responseBean.setBankList(Collections.<ObBankBean>emptyList());
		}
	}
}
