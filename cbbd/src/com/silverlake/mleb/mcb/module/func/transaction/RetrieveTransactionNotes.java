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
 * Purpose: Get transaction notes
 * 
 * Omni Web Services:
 * transaction/txn_notes_list - for usage of source transaction of NB, MN and TON 
 * transaction/upload_notes_list - for other usage
 * 
 * @author Alex Loo
 *
 */
@Service
public class RetrieveTransactionNotes extends SessionFuncServices<ObTransactionSummaryRequest, ObTransactionSummaryResponse>{
	
	@Override
	public void processInternal(String locale, String sessionId, String trxId, ObTransactionSummaryRequest requestBean, ObTransactionSummaryResponse responseBean, VCGenericService vcService) throws Exception {
		TransactionNoteResponseData responseData;
		TransactionNoteRequestData requestData = new TransactionNoteRequestData();
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		
		VCResponse<TransactionNoteResponseData> vcResponse = null;
		//Query from transaction/txn_notes_list when source of transaction is NB or MN
		if(requestBean.getSourceTrx().equals("NB") || requestBean.getSourceTrx().equals("MN") || requestBean.getSourceTrx().equals("TON")){
			requestData.setPymt_master_id(requestBean.getPymtMasterId());
			vcResponse = vcService.callOmniVC(
					VCMethodConstant.REST_METHODS.TRANSACTION_TXN_NOTES_LIST, 
					requestData, 
					TransactionNoteResponseData.class, 
					true);
		}else{
			//Query from transaction/upload_notes_list when source of transaction is other
			requestData.setBatch_id(requestBean.getPymtMasterId());
			vcResponse = vcService.callOmniVC(
					VCMethodConstant.REST_METHODS.TRANSACTION_UPLOAD_NOTES_LIST, 
					requestData, 
					TransactionNoteResponseData.class, 
					true);
		}
		
		if(processVCResponseError(vcResponse, responseBean)){
			return;
		}
		
		responseData = vcResponse.getData();
		processResponse(locale, responseData, responseBean);
	}
	
	private void processResponse(String locale, TransactionNoteResponseData responseData, ObTransactionSummaryResponse responseBean) throws Exception{
		responseBean.setTrxNotesList(new ArrayList<ObNoteBean>());
		//For transaction's notes response
		if(responseData.getList_txn_notes() != null){
			for(TransactionNote listTxnNotes:responseData.getList_txn_notes()){
				ObNoteBean itemBean = new ObNoteBean();
				itemBean.setId(listTxnNotes.getId());
				itemBean.setCreatedBy(listTxnNotes.getCreated_by());
				itemBean.setCreatedByName(listTxnNotes.getCreated_by_name());
				itemBean.setDateCreated(listTxnNotes.getDate_created());
				itemBean.setRemark(listTxnNotes.getRemark());
				itemBean.setPymtMasterId(listTxnNotes.getPymt_master_id());
				responseBean.getTrxNotesList().add(itemBean);
			}
		}
		//For upload's notes response
		if(responseData.getList_upload_notes() != null){
			for(TransactionNote listTxnNotes:responseData.getList_upload_notes()){
				ObNoteBean itemBean = new ObNoteBean();
				itemBean.setId(listTxnNotes.getId());
				itemBean.setCreatedBy(listTxnNotes.getCreated_by());
				itemBean.setCreatedByName(listTxnNotes.getCreated_by_name());
				itemBean.setDateCreated(listTxnNotes.getDate_created());
				itemBean.setRemark(listTxnNotes.getRemark());
				itemBean.setPymtMasterId(listTxnNotes.getBatch_id());
				responseBean.getTrxNotesList().add(itemBean);
			}
		}
	}
}
