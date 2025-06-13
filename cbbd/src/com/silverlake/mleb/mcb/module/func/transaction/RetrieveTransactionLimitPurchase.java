package com.silverlake.mleb.mcb.module.func.transaction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.ObTransactionLimitEqUSDRequest;
import com.silverlake.mleb.mcb.bean.ObTransactionLimitEqUSDResponse;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.module.common.SessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.transaction.TransactionLimitEqUSDRequestData;
import com.silverlake.mleb.mcb.module.vc.transaction.TransactionLimitEqUSDResponseData;
import com.silverlake.mleb.mcb.util.MapperUtil;

/**
 * Purpose: To get transaction limit equals to USD.
 * 
 * Omni Web Services:
 * transaction/limit/eqUSD
 *
 * @author shafiqahrahni
 *
 */
@Service
public class RetrieveTransactionLimitPurchase extends SessionFuncServices<ObTransactionLimitEqUSDRequest, ObTransactionLimitEqUSDResponse> {
	private static Logger log = LogManager.getLogger(RetrieveTransactionLimitPurchase.class); 

	@Override
    public void processInternal(String locale, String sessionId, String trxId, ObTransactionLimitEqUSDRequest requestBean, ObTransactionLimitEqUSDResponse responseBean, VCGenericService vcService) throws Exception {
    	    	    	
		TransactionLimitEqUSDRequestData requestData = new TransactionLimitEqUSDRequestData();
		TransactionLimitEqUSDResponseData responseData;
		processRequest(requestData, requestBean);
		
		VCResponse<TransactionLimitEqUSDResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.TRANSACTION_LIMIT_EQ_USD, 
				requestData, 
				TransactionLimitEqUSDResponseData.class, 
				true);
		
		if(processVCResponseError(vcResponse, responseBean)){
			return;
		}
		
		responseData = vcResponse.getData();
		processResponse(responseData, responseBean);
    }

	private void processRequest(TransactionLimitEqUSDRequestData requestData, ObTransactionLimitEqUSDRequest requestBean) throws Exception{
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setFrom_ccy(requestBean.getFromCcy());
		requestData.setTo_ccy(requestBean.getToCcy());
		requestData.setAccount_no(requestBean.getAccountNo());
		
		if(requestBean.getFromAmount() != null && !requestBean.getFromAmount().isEmpty()) {
			requestData.setFrom_amount(requestBean.getFromAmount());
		} else {
			requestData.setFrom_amount("0");
		}
		
		if(requestBean.getToAmount() != null && !requestBean.getToAmount().isEmpty()) {
			requestData.setTo_amount(requestBean.getToAmount());
		} else {
			requestData.setTo_amount("0");
		}
		
		if(requestBean.getCancelBit() != null && !requestBean.getCancelBit().isEmpty()) {
			requestData.setCancel_bit(requestBean.getCancelBit());
		} else {
			requestData.setCancel_bit("false");
		}
	}
	
	private void processResponse(TransactionLimitEqUSDResponseData responseData, ObTransactionLimitEqUSDResponse responseBean) throws Exception{
		MapperUtil.copyFields(responseData, responseBean);
	}		
}
