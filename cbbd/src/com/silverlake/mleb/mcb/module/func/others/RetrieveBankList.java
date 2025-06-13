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
 * Purpose: Get fund transfer bank list or branch list or bank details for OLT/RTGS/LLG/TT/FAST
 * For OLT -> bank list (one level)
 * For LLG/RTGS -> bank list > branch list (two levels) 
 *  
 * Omni Web Services:
 * other/banks
 * 
 * @author Alex Loo
 *
 */
@Service
public class RetrieveBankList extends SessionFuncServices<ObBankListRequest, ObBankListResponse>{
	private static Logger log = LogManager.getLogger(RetrieveBankList.class);

	@Override
	public void processInternal(String locale, String sessionId, String trxId, ObBankListRequest requestBean,
			ObBankListResponse responseBean, VCGenericService vcService) throws Exception {
		BankListRequestData requestData = new BankListRequestData();
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		if(requestBean.getTransferServiceType().equalsIgnoreCase(MiBConstant.TRANS_SERVICE_TYPE_OLT)){
			requestData.setTransfer_type("OLT");
		}else if(requestBean.getTransferServiceType().equalsIgnoreCase(MiBConstant.TRANS_SERVICE_TYPE_LLG)){
			requestData.setTransfer_type("LL");
			requestData.setBank_code(requestBean.getBankCode());//Set bank code will get branch list belong to the bank
			requestData.setBranch_name(requestBean.getBranchName());
			requestData.setNetwork_clearing_code(requestBean.getNetworkClearingCode());
		}else if(requestBean.getTransferServiceType().equalsIgnoreCase(MiBConstant.TRANS_SERVICE_TYPE_RTGS)){
			requestData.setTransfer_type("RT");
			requestData.setBank_code(requestBean.getBankCode());//Set bank code will get branch list belong to the bank
			requestData.setBranch_name(requestBean.getBranchName());
			requestData.setNetwork_clearing_code(requestBean.getNetworkClearingCode());
		}else if(requestBean.getTransferServiceType().equalsIgnoreCase(MiBConstant.TRANS_SERVICE_TYPE_TT)){
			requestData.setTransfer_type("TT");
			if(requestBean.getCountryCode() == null || requestBean.getCountryCode().isEmpty()){
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
				log.info("Country code is mandatory for "+MiBConstant.TRANS_SERVICE_TYPE_TT);
				return;
			}
			requestData.setCountry_code(requestBean.getCountryCode());
			requestData.setBank_name(requestBean.getBankCode());//Set bank code will get branch list belong to the bank
			requestData.setBranch_name(requestBean.getBranchName());
			requestData.setNetwork_clearing_code(requestBean.getNetworkClearingCode());
		}else if(requestBean.getTransferServiceType().equalsIgnoreCase(MiBConstant.TRANS_SERVICE_TYPE_FAST)){
			requestData.setTransfer_type("FAST");
			requestData.setBank_code(requestBean.getBankCode());//Set bank code will get branch list belong to the bank
		}
		requestData.setPage_no(1);
		requestData.setPage_size(Integer.MAX_VALUE);
		
		String testSubPath = null;
		if(requestData.getBank_code() != null && !requestData.getBank_code().isEmpty()){
			testSubPath = "branch";
		}else{
			testSubPath = "bank";
		}
		
		VCResponse<BankListResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.OTHERS_BANKS, 
				requestData, 
				BankListResponseData.class, 
				true,
				testSubPath);
		if(processVCResponseError(vcResponse, responseBean)){
			return;
		}
		BankListResponseData responseData = vcResponse.getData();
		if(responseData.getBank_list() != null){
			responseBean.setBankList(new ArrayList<ObBankBean>());
			for(BankListInfo bankInfo:responseData.getBank_list()){
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
				bankBean.setRtgsMemberCode(bankInfo.getRtgs_member_code());
				bankBean.setParticipantBic(bankInfo.getParticipant_bic());
				
				responseBean.getBankList().add(bankBean);
			}
		}else {
			responseBean.setBankList(Collections.<ObBankBean>emptyList());
		}
	}
}
