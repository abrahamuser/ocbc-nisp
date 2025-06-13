package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class ObAmountOptionBean implements Serializable{
	private String optionId;
	private String refNo;
	private BigDecimal amount;
	private String amountDesc;
	
	public String getRefNo() {
		return refNo;
	}
	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getAmountDesc() {
		return amountDesc;
	}
	public void setAmountDesc(String amountDesc) {
		this.amountDesc = amountDesc;
	}
	public String getOptionId() {
		return optionId;
	}
	public void setOptionId(String optionId) {
		this.optionId = optionId;
	}
}
