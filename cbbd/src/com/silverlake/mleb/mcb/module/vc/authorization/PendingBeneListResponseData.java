package com.silverlake.mleb.mcb.module.vc.authorization;

import java.util.List;

import com.silverlake.mleb.mcb.module.vc.accountManagement.ListAccount;
import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;
import com.silverlake.mleb.mcb.module.vc.transaction.BeneficiaryList;

public class PendingBeneListResponseData extends VCResponseData {
	private List<BeneficiaryList> bene_list;
	private Integer total_records;
	
	public List<BeneficiaryList> getBene_list() {
		return bene_list;
	}
	public Integer getTotal_records() {
		return total_records;
	}
	public void setBene_list(List<BeneficiaryList> bene_list) {
		this.bene_list = bene_list;
	}
	public void setTotal_records(Integer total_records) {
		this.total_records = total_records;
	}	
}
