package com.silverlake.mleb.mcb.module.func.transaction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.mgmtbene.ObTransactionManageBeneficiaryRequest;
import com.silverlake.mleb.mcb.bean.mgmtbene.ObTransactionManageBeneficiaryResponse;
import com.silverlake.mleb.mcb.bean.mgmtbene.ObTransactionManageBeneficiarySessionCache;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.module.common.CacheSessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.transaction.management.DeleteBeneficiaryRequestData;
import com.silverlake.mleb.mcb.module.vc.transaction.management.DeleteBeneficiaryResponseData;
import com.silverlake.mleb.mcb.module.vc.transaction.management.EditBeneficiaryRequestData;
import com.silverlake.mleb.mcb.module.vc.transaction.management.EditBeneficiaryResponseData;
import com.silverlake.mleb.mcb.util.MapperUtil;

/**
 * Purpose: To remove the beneficiary account from saved beneficiary list
 * 
 * Omni Web Services:
 * transaction_mgmt/delete_bene
 * 
 * @author Alex Loo
 *
 */
@Service
public class TransactionManageBeneficiaryDelete extends CacheSessionFuncServices<ObTransactionManageBeneficiaryRequest, ObTransactionManageBeneficiaryResponse, ObTransactionManageBeneficiarySessionCache>{
	private static Logger log = LogManager.getLogger(TransactionManageBeneficiaryDelete.class);
	
	@Override
	public void processInternalWithCache(String locale, String sessionId, String trxId, ObTransactionManageBeneficiaryRequest requestBean, ObTransactionManageBeneficiaryResponse responseBean, ObTransactionManageBeneficiarySessionCache cacheBean, VCGenericService vcService) throws Exception {
		DeleteBeneficiaryRequestData requestData = new DeleteBeneficiaryRequestData();
		DeleteBeneficiaryResponseData responseData;
		processRequest(requestData, requestBean, responseBean);
		if(responseBean.getObHeader().getStatusCode() != null){
			return;
		}
		VCResponse<DeleteBeneficiaryResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.TRANSACTION_MGMT_DELETE_BENE, 
				requestData, 
				DeleteBeneficiaryResponseData.class, 
				true);
		if(processVCResponseError(vcResponse, responseBean, false)){
			return;
		}
		
		responseData = vcResponse.getData();
		processResponse(responseData, requestBean, responseBean);
	}
	
	private void processRequest(DeleteBeneficiaryRequestData requestData, ObTransactionManageBeneficiaryRequest requestBean, ObTransactionManageBeneficiaryResponse responseBean) throws Exception{
		ObTransactionManageBeneficiarySessionCache transactionManageBeneficiaryDetailsSessionCache = requestBean.getSessionCache().get(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TRANSACTION_MANAGEMENT_BENEFICIARY_DETAILS.getId());
		if(transactionManageBeneficiaryDetailsSessionCache.getViewBeneficiaryResponseData() == null){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			log.info("Beneficiary details cache is not found");
			return;
		}
		
		if(!transactionManageBeneficiaryDetailsSessionCache.getViewBeneficiaryResponseData().getBene_id().equals(requestBean.getBeneId())){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			log.info("Beneficiary details bene ID not matched");
			return;
		}
		
		MapperUtil.copyFields(transactionManageBeneficiaryDetailsSessionCache.getViewBeneficiaryResponseData(), requestData);
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
	}
	
	private void processResponse(DeleteBeneficiaryResponseData responseData, ObTransactionManageBeneficiaryRequest requestBean, ObTransactionManageBeneficiaryResponse responseBean) throws Exception{
		
	}
}
