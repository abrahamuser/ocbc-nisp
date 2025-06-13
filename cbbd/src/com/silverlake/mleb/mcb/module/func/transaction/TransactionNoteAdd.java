package com.silverlake.mleb.mcb.module.func.transaction;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.ObAuditBean;
import com.silverlake.mleb.mcb.bean.ObNoteBean;
import com.silverlake.mleb.mcb.bean.ObTransactionSummaryRequest;
import com.silverlake.mleb.mcb.bean.ObTransactionSummaryResponse;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.entity.dao.GeneralCodeDAO;
import com.silverlake.mleb.mcb.module.common.SessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.transaction.TransactionNote;
import com.silverlake.mleb.mcb.module.vc.transaction.TransactionNoteRequestData;
import com.silverlake.mleb.mcb.module.vc.transaction.TransactionNoteResponseData;

/**
 * Purpose: Add note into transaction which using by summary module
 * 
 * Omni Web Services:
 * transaction/txn_notes_list/add - For source of transaction of MN and NB
 * transaction/upload_notes_list/add - All other source of transaction
 * 
 * @author Alex Loo
 *
 */
@Service
public class TransactionNoteAdd extends SessionFuncServices<ObTransactionSummaryRequest, ObTransactionSummaryResponse>{
	
	@Override
	public void processInternal(String locale, String sessionId, String trxId, ObTransactionSummaryRequest requestBean, ObTransactionSummaryResponse responseBean, VCGenericService vcService) throws Exception {
		VCResponse<TransactionNoteResponseData> vcResponse = null;
		
		TransactionNoteRequestData requestData = new TransactionNoteRequestData();
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setRemark(requestBean.getRemark());
		if(requestBean.getSourceTrx().equals("NB") || requestBean.getSourceTrx().equals("MN") || requestBean.getSourceTrx().equals("TON")){
			//use transaction/trx_notes_list/add when source of transaction is NB or MN
			requestData.setPymt_master_id(requestBean.getPymtMasterId());
			vcResponse = vcService.callOmniVC(
					VCMethodConstant.REST_METHODS.TRANSACTION_TXN_NOTES_LIST_ADD, 
					requestData, 
					TransactionNoteResponseData.class, 
					true);
		}else{
			//use transaction/upload_notes_list/add when source of transaction is other
			requestData.setBatch_id(requestBean.getPymtMasterId());
			vcResponse = vcService.callOmniVC(
					VCMethodConstant.REST_METHODS.TRANSACTION_UPLOAD_NOTES_LIST_ADD, 
					requestData, 
					TransactionNoteResponseData.class, 
					true);
		}
		
		if(processVCResponseError(vcResponse, responseBean, false)){
			return;
		}
	}
}
