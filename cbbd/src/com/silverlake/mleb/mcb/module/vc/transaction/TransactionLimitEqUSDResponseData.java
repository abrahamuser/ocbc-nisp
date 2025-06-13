package com.silverlake.mleb.mcb.module.vc.transaction;

import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;

public class TransactionLimitEqUSDResponseData extends VCResponseData {

	private String is_limit;
	private TransactionLimitEqUSDDetail detail;
	
	public String getIs_limit() {
		return is_limit;
	}
	public void setIs_limit(String is_limit) {
		this.is_limit = is_limit;
	}
	public TransactionLimitEqUSDDetail getDetail() {
		return detail;
	}
	public void setDetail(TransactionLimitEqUSDDetail detail) {
		this.detail = detail;
	}
}
