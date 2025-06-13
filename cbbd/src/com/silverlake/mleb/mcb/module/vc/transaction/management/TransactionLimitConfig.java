package com.silverlake.mleb.mcb.module.vc.transaction.management;

import java.io.Serializable;
import java.math.BigDecimal;

public class TransactionLimitConfig implements Serializable{
	private BigDecimal min_amount;
	private BigDecimal max_amount;
	
	public BigDecimal getMin_amount() {
		return min_amount;
	}
	public BigDecimal getMax_amount() {
		return max_amount;
	}
	public void setMin_amount(BigDecimal min_amount) {
		this.min_amount = min_amount;
	}
	public void setMax_amount(BigDecimal max_amount) {
		this.max_amount = max_amount;
	}
}

