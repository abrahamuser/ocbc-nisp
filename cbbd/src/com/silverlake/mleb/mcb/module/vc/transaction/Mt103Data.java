package com.silverlake.mleb.mcb.module.vc.transaction;

import java.io.Serializable;
import java.math.BigDecimal;

public class Mt103Data  implements Serializable
{
	private BigDecimal row_id;
	private String mt_data;
	
	
	
	public BigDecimal getRow_id() {
		return row_id;
	}
	public void setRow_id(BigDecimal row_id) {
		this.row_id = row_id;
	}
	public String getMt_data() {
		return mt_data;
	}
	public void setMt_data(String mt_data) {
		this.mt_data = mt_data;
	}
	
		
}



	
