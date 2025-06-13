package com.silverlake.mleb.mcb.module.vc.transaction;

import java.util.List;

import com.silverlake.mleb.mcb.module.vc.accountManagement.ListAccount;
import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;

public class PayeeResponseData extends VCResponseData{
	private List<PayeeList> payee_list;
	private Integer total_rows;
	
	public Integer getTotal_rows() {
		return total_rows;
	}
	public void setTotal_rows(Integer total_rows) {
		this.total_rows = total_rows;
	}
	public List<PayeeList> getPayee_list() {
		return payee_list;
	}
	public void setPayee_list(List<PayeeList> payee_list) {
		this.payee_list = payee_list;
	}

	
}
