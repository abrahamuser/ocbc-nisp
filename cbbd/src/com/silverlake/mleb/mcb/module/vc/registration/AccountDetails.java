package com.silverlake.mleb.mcb.module.vc.registration;

import java.io.Serializable;
import java.util.List;

public class AccountDetails  implements Serializable
{
      private String account_no;
	  private String account_no_masked;
	  private String account_name;
	  private String account_name_masked;
	  
	  
	public String getAccount_no() {
		return account_no;
	}
	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}
	public String getAccount_no_masked() {
		return account_no_masked;
	}
	public void setAccount_no_masked(String account_no_masked) {
		this.account_no_masked = account_no_masked;
	}
	public String getAccount_name() {
		return account_name;
	}
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
	public String getAccount_name_masked() {
		return account_name_masked;
	}
	public void setAccount_name_masked(String account_name_masked) {
		this.account_name_masked = account_name_masked;
	}

	
	 
}



	
