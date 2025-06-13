package com.silverlake.mleb.mcb.module.vc.transaction;

import java.util.List;

import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;

public class TransactionAuditTrailResponseData extends VCResponseData{
	private List<TransactionAudit> list_audit;
	private List<TransactionAuthorizationSequenceMaster> list_auth_list;
	
	public List<TransactionAudit> getList_audit() {
		return list_audit;
	}
	public void setList_audit(List<TransactionAudit> list_audit) {
		this.list_audit = list_audit;
	}
	public List<TransactionAuthorizationSequenceMaster> getList_auth_list() {
		return list_auth_list;
	}
	public void setList_auth_list(List<TransactionAuthorizationSequenceMaster> list_auth_list) {
		this.list_auth_list = list_auth_list;
	}
}



	
