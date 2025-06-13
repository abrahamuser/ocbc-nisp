package com.silverlake.mleb.pex.bean;

import java.io.Serializable;




public class ObCIFDetailsBean extends ObEAIHeader implements Serializable
{


	String contactNo;
	String addressSeq1;
	String contactNo1;
	String addressSeq3;
	
	
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getAddressSeq1() {
		return addressSeq1;
	}
	public void setAddressSeq1(String addressSeq1) {
		this.addressSeq1 = addressSeq1;
	}
	public String getContactNo1() {
		return contactNo1;
	}
	public void setContactNo1(String contactNo1) {
		this.contactNo1 = contactNo1;
	}
	public String getAddressSeq3() {
		return addressSeq3;
	}
	public void setAddressSeq3(String addressSeq3) {
		this.addressSeq3 = addressSeq3;
	}
	
	
	
	
}
