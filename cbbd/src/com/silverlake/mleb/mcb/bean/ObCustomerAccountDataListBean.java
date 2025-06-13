package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;


public class ObCustomerAccountDataListBean implements Serializable{
	 
	private String customerNumber;
	private String customerName;
	private String customerId;
		
	public String getCustomerNumber() {
		return customerNumber;
	}
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
    	    
}

