package com.silverlake.mleb.mcb.module.vc.others;

import java.util.List;

import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;

public class BankListResponseData extends VCResponseData {
	private List<BankListInfo> bank_list;
	private Integer total_rows;
	
	private BankListInfo bank_info;//Use by swift code (other/banks/swift) search (only 1 record to be returned)
	
	public List<BankListInfo> getBank_list() {
		return bank_list;
	}
	public void setBank_list(List<BankListInfo> bank_list) {
		this.bank_list = bank_list;
	}
	public Integer getTotal_rows() {
		return total_rows;
	}
	public void setTotal_rows(Integer total_rows) {
		this.total_rows = total_rows;
	}
	public BankListInfo getBank_info() {
		return bank_info;
	}
	public void setBank_info(BankListInfo bank_info) {
		this.bank_info = bank_info;
	}

	
}
