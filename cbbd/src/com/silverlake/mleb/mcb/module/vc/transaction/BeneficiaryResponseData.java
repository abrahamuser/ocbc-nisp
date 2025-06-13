package com.silverlake.mleb.mcb.module.vc.transaction;

import java.util.List;

import com.silverlake.mleb.mcb.module.vc.accountManagement.ListAccount;
import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;

public class BeneficiaryResponseData extends VCResponseData{
	private List<BeneficiaryList> beneficiary_list;
	private Integer total_rows;
	
	public List<BeneficiaryList> getBeneficiary_list() {
		return beneficiary_list;
	}
	public void setBeneficiary_list(List<BeneficiaryList> beneficiary_list) {
		this.beneficiary_list = beneficiary_list;
	}
	public Integer getTotal_rows() {
		return total_rows;
	}
	public void setTotal_rows(Integer total_rows) {
		this.total_rows = total_rows;
	}

	
}
