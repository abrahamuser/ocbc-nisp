package com.silverlake.mleb.mcb.module.vc.authorization;

import java.util.List;

import com.silverlake.mleb.mcb.module.vc.accountManagement.ListAccount;
import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;

public class PendingAccountListResponseData extends VCResponseData {
	private List<ListAccount> account_list;
	private Integer total_records;
	public List<ListAccount> getAccount_list() {
		return account_list;
	}
	public Integer getTotal_records() {
		return total_records;
	}
	public void setAccount_list(List<ListAccount> account_list) {
		this.account_list = account_list;
	}
	public void setTotal_records(Integer total_records) {
		this.total_records = total_records;
	}
}
