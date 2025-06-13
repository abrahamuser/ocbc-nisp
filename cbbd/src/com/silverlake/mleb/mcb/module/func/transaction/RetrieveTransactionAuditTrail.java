package com.silverlake.mleb.mcb.module.func.transaction;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.ObAuditBean;
import com.silverlake.mleb.mcb.bean.ObTransactionSummaryRequest;
import com.silverlake.mleb.mcb.bean.ObTransactionSummaryResponse;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.entity.dao.GeneralCodeDAO;
import com.silverlake.mleb.mcb.module.common.SessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.transaction.TransactionAudit;
import com.silverlake.mleb.mcb.module.vc.transaction.TransactionAuditTrailRequestData;
import com.silverlake.mleb.mcb.module.vc.transaction.TransactionAuditTrailResponseData;
import com.silverlake.mleb.mcb.module.vc.transaction.TransactionAuthorizationSequenceDetail;
import com.silverlake.mleb.mcb.module.vc.transaction.TransactionAuthorizationSequenceMaster;

/**
 * Purpose: Get transaction audit trail
 * 
 * Omni Web Services:
 * transaction/audit_trail
 * 
 * @author Alex Loo
 *
 */
@Service
public class RetrieveTransactionAuditTrail extends SessionFuncServices<ObTransactionSummaryRequest, ObTransactionSummaryResponse>{
	
	@Override
	public void processInternal(String locale, String sessionId, String trxId, ObTransactionSummaryRequest requestBean, ObTransactionSummaryResponse responseBean, VCGenericService vcService) throws Exception {
		TransactionAuditTrailRequestData requestData = new TransactionAuditTrailRequestData();
		TransactionAuditTrailResponseData responseData;
		processRequest(requestData, requestBean);
		VCResponse<TransactionAuditTrailResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.TRANSACTION_AUDIT_TRAIL, 
				requestData, 
				TransactionAuditTrailResponseData.class, 
				true);
		if(processVCResponseError(vcResponse, responseBean)){
			return;
		}
		
		responseData = vcResponse.getData();
		processResponse(locale, responseData, responseBean);
	}
	
	private void processRequest(TransactionAuditTrailRequestData requestData, ObTransactionSummaryRequest requestBean) throws Exception{
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setPymt_master_id(requestBean.getPymtMasterId());
	}
	
	private void processResponse(String locale, TransactionAuditTrailResponseData responseData, ObTransactionSummaryResponse responseBean) throws Exception{
		responseBean.setAuditList(new ArrayList<ObAuditBean>());
		if(responseData.getList_audit() != null){
			for(TransactionAudit listAudit:responseData.getList_audit()){
				ObAuditBean auditBean = new ObAuditBean();
				auditBean.setId(listAudit.getId());
				auditBean.setUsrCode(listAudit.getUsr_cd());
				auditBean.setUsrName(listAudit.getUsr_name());
				auditBean.setAction(listAudit.getAction());
				auditBean.setTimestamp(listAudit.getTimestamp());
				auditBean.setRemark(listAudit.getRemark());
				auditBean.setChannel(listAudit.getChannel());
				
				responseBean.getAuditList().add(auditBean);
			}
		}
		
		if(responseData.getList_auth_list() != null){
			responseBean.setAuthList(new ArrayList<ObAuditBean>());
			for(TransactionAuthorizationSequenceMaster itemMaster:responseData.getList_auth_list()){
				ObAuditBean auditMasterBean = new ObAuditBean();
				auditMasterBean.setId(itemMaster.getId());
				auditMasterBean.setIsSeq(itemMaster.getIs_seq());
				auditMasterBean.setNextAuth(itemMaster.getNext_auth());
				auditMasterBean.setDetailList(new ArrayList<ObAuditBean>());
				
				for(TransactionAuthorizationSequenceDetail itemDetail:itemMaster.getList_detail()){
					ObAuditBean auditDetailBean = new ObAuditBean();
					auditDetailBean.setId(itemDetail.getId());
					auditDetailBean.setWeight(itemDetail.getWeight());
					auditDetailBean.setAuthProfileId(itemDetail.getAuth_profile_id());
					auditDetailBean.setAuthProfileName(itemDetail.getAuth_profile_name());
					auditDetailBean.setAuthDate(itemDetail.getAuth_date());
					auditDetailBean.setAuthBy(itemDetail.getAuth_by());
					
					auditMasterBean.getDetailList().add(auditDetailBean);
				}
				
				responseBean.getAuthList().add(auditMasterBean);
			}
		}
	}
}
