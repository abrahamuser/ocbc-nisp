package com.silverlake.mleb.mcb.module.func.transaction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.mgmtbene.ObTransactionManageBeneficiaryRequest;
import com.silverlake.mleb.mcb.bean.mgmtbene.ObTransactionManageBeneficiaryResponse;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.module.common.SessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.transaction.management.SetFavBeneficiaryRequestData;
import com.silverlake.mleb.mcb.module.vc.transaction.management.SetFavBeneficiaryResponseData;

/**
 * Purpose: To flag or unflag the beneficiary account as favorite in the saved beneficiary list
 * 
 * Omni Web Services:
 * transaction_mgmt/favorite_bene
 * 
 * @author Alex Loo
 *
 */
@Service
public class TransactionManageBeneficiarySetFavorite extends SessionFuncServices<ObTransactionManageBeneficiaryRequest, ObTransactionManageBeneficiaryResponse>{
	private static Logger log = LogManager.getLogger(TransactionManageBeneficiarySetFavorite.class);
	
	@Override
	public void processInternal(String locale, String sessionId, String trxId, ObTransactionManageBeneficiaryRequest requestBean, ObTransactionManageBeneficiaryResponse responseBean, VCGenericService vcService) throws Exception {
		SetFavBeneficiaryRequestData requestData = new SetFavBeneficiaryRequestData();
		SetFavBeneficiaryResponseData responseData;
		processRequest(requestData, requestBean, responseBean);
		if(responseBean.getObHeader().getStatusCode() != null){
			return;
		}
		VCResponse<SetFavBeneficiaryResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.TRANSACTION_MGMT_FAVORITE_BENE, 
				requestData, 
				SetFavBeneficiaryResponseData.class, 
				true);
		if(processVCResponseError(vcResponse, responseBean, false)){
			return;
		}
		
		responseData = vcResponse.getData();
		processResponse(responseData, requestBean, responseBean);
	}
	
	private void processRequest(SetFavBeneficiaryRequestData requestData, ObTransactionManageBeneficiaryRequest requestBean, ObTransactionManageBeneficiaryResponse responseBean) throws Exception{
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setBene_id(requestBean.getBeneId());
		if(requestBean.getIsFavorite() != null && requestBean.getIsFavorite()) {
			requestData.setIs_favorite("Y");
		}else {
			requestData.setIs_favorite("N");
		}
	}
	
	private void processResponse(SetFavBeneficiaryResponseData responseData, ObTransactionManageBeneficiaryRequest requestBean, ObTransactionManageBeneficiaryResponse responseBean) throws Exception{
		
	}
}
