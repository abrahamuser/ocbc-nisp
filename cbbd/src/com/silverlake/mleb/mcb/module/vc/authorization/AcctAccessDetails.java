package com.silverlake.mleb.mcb.module.vc.authorization;

import java.io.Serializable;
import java.util.List;

public class AcctAccessDetails  implements Serializable
{
	  private String record_id;
	  private String detail_record_id;
 
	  private String account_no;
	  
	  private String account_ccy;
	  private String account_name;
	  
	  private List<AcctAccessProduct> product_list;

	public String getRecord_id() {
		return record_id;
	}

	public void setRecord_id(String record_id) {
		this.record_id = record_id;
	}

	public String getDetail_record_id() {
		return detail_record_id;
	}

	public void setDetail_record_id(String detail_record_id) {
		this.detail_record_id = detail_record_id;
	}

	public String getAccount_no() {
		return account_no;
	}

	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}

	public String getAccount_ccy() {
		return account_ccy;
	}

	public void setAccount_ccy(String account_ccy) {
		this.account_ccy = account_ccy;
	}

	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public List<AcctAccessProduct> getProduct_list() {
		return product_list;
	}

	public void setProduct_list(List<AcctAccessProduct> product_list) {
		this.product_list = product_list;
	}
	   
	 
	 
	 
}



	
