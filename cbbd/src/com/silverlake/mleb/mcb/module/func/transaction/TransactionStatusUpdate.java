package com.silverlake.mleb.mcb.module.func.transaction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.ObAuthorizationSessionCache;
import com.silverlake.mleb.mcb.bean.ObSessionCache;
import com.silverlake.mleb.mcb.bean.ObTransactionSummaryRequest;
import com.silverlake.mleb.mcb.bean.ObTransactionSummaryResponse;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.module.common.CacheSessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.transaction.Transaction;
import com.silverlake.mleb.mcb.module.vc.transaction.TransactionStatusUpdateRequestData;
import com.silverlake.mleb.mcb.module.vc.transaction.TransactionStatusUpdateResponseData;

/**
 * Purpose: Update transaction status
 * 
 * Omni Web Services:
 * transaction/update_status
 * 
 * The selection of transaction to update the status come from ws of retrieveTaskTranx or retrieveTransactionDataList
 * NB - From retrieveTransactionDataList
 * others - From retrieveTaskTranx
 * 
 * @author Alex Loo
 *
 */
@Service
public class TransactionStatusUpdate extends CacheSessionFuncServices<ObTransactionSummaryRequest, ObTransactionSummaryResponse, ObSessionCache>{
	private static Logger log = LogManager.getLogger(TransactionStatusUpdate.class);
	
	@Override
	public void processInternalWithCache(String locale, String sessionId, String trxId, ObTransactionSummaryRequest requestBean, ObTransactionSummaryResponse responseBean, ObSessionCache cacheBean, VCGenericService vcService) throws Exception {
		TransactionStatusUpdateRequestData requestData = new TransactionStatusUpdateRequestData();
		processRequest(requestData, requestBean, responseBean);
		if(responseBean.getObHeader().getStatusCode() != null){
			return;
		}
		//Submit transaction
		TransactionStatusUpdateResponseData responseData;
		VCResponse<TransactionStatusUpdateResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.TRANSACTION_TXN_UPDATE_STATUS, 
				requestData, 
				TransactionStatusUpdateResponseData.class, 
				true);
		//Do not returned immediately if error, need to extract error message from response data.
		processVCResponseError(vcResponse, responseBean);
		processResponse(locale, vcResponse.getData(), requestBean, responseBean);
	}
	
	private void processRequest(TransactionStatusUpdateRequestData requestData, ObTransactionSummaryRequest requestBean, ObTransactionSummaryResponse responseBean) throws Exception{
		
		ObAuthorizationSessionCache trxListSessionCache = null;
		if(requestBean.getSourceTrx().equals("NB")){
			//When it is Non-bulk take the cache from retrieveTransactionDataList
			trxListSessionCache = (ObAuthorizationSessionCache)((ObSessionCache)requestBean.getSessionCache().get(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TRANSACTION_DATA_LIST.getId()));
			if(trxListSessionCache == null || trxListSessionCache.getTransactionList() == null){
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
				log.info("Transaction data list cache is not found which is needed for non-bulk source of transaction");
				return;
			}
		}else{
			//When it is other take the cache from retrieveTaskTranx
			trxListSessionCache = (ObAuthorizationSessionCache)((ObSessionCache)requestBean.getSessionCache().get(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TRANSACTION_LIST.getId()));
			if(trxListSessionCache == null || trxListSessionCache.getTransactionList() == null){
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
				log.info("Transaction list cache is not found");
				return;
			}
		}
		
		boolean isRecordFound = false;
		for(Transaction trx:trxListSessionCache.getTransactionList()){
//			if(acc.getId().equals(requestBean.getDebitAccountId())){
			if(trx.getIndex().equals(String.valueOf(requestBean.getIndex())) &&
					trx.getPymt_master_id().equals(requestBean.getPymtMasterId())){
				requestData.setVersion(String.valueOf(trx.getVersion()));
				requestData.setProd_cd(trx.getProd_cd());
				requestData.setPymt_master_id(trx.getPymt_master_id());
				isRecordFound = true;
				break;
			}
		}
		if(!isRecordFound){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
			log.info("Transaction index or pymt master id not matched with cache trx list");
			return;
		}
		
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setValidation_check(requestBean.getValidationCheck());
		if(requestBean.getTrxStatus() != null){
		requestData.setTrx_status(requestBean.getTrxStatus());
		}else {
			requestData.setTrx_status(MiBConstant.FUNC_FALSE);
		}
		requestData.setDevice_type(requestBean.getObDevice().getModel());
		requestData.setDevice_os(requestBean.getObDevice().getType()+" "+requestBean.getObDevice().getOs());
	}
	
	private void processResponse(String locale, TransactionStatusUpdateResponseData requestData, ObTransactionSummaryRequest requestBean, ObTransactionSummaryResponse responseBean) throws Exception{
		if(requestData != null){
			responseBean.setErrorMessage(requestData.getError_message());
		}
	}
}
