package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


public class ObMT103DataBean implements Serializable {
	
	private BigDecimal rowId;
	private String mtData;
	
	
	public BigDecimal getRowId() {
		return rowId;
	}
	public void setRowId(BigDecimal rowId) {
		this.rowId = rowId;
	}
	public String getMtData() {
		return mtData;
	}
	public void setMtData(String mtData) {
		this.mtData = mtData;
	}

	
}
