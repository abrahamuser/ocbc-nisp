package com.silverlake.mleb.mcb.module.vc.registration;

import java.io.Serializable;
import java.util.List;

public class VerficationInfo  implements Serializable
{
      private String verification_no;
	  private String verification_used_date;
	  private String verification_exp_date;
	  
	  
	public String getVerification_no() {
		return verification_no;
	}
	public void setVerification_no(String verification_no) {
		this.verification_no = verification_no;
	}
	public String getVerification_used_date() {
		return verification_used_date;
	}
	public void setVerification_used_date(String verification_used_date) {
		this.verification_used_date = verification_used_date;
	}
	public String getVerification_exp_date() {
		return verification_exp_date;
	}
	public void setVerification_exp_date(String verification_exp_date) {
		this.verification_exp_date = verification_exp_date;
	}
	  
	  
	 	 
}



	
