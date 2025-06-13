package com.silverlake.mleb.mcb.module.vc.authorization;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class AuthPolicyRule  implements Serializable
{
	  private String record_id;
	  private String auth_policy_recid;
	  
	  private int rule_weight;
	  private String rule_name;
	  
	  private int transfer_amt;
	  private String transfer_ccy;
	  private String transfer_fmtAmt;
	  
	  private String operator_cd;
	  private String is_sequence;
	  
	  private String created_by_ucode;
	  private String created_by_uname;
	  private String created_date;
	  private String updated_by_ucode;
	  private String updated_by_uname;
	  private String updated_date;
	  private int version;
	  private String maintenance_type;
	  
	  private List<AuthPolicyProduct> authpol_product_list;
	  private List<AuthPolicyAccount> authpol_account_list;
	  private List<AuthPolicyAuthorizer> authpol_auth_list;
	  
	  
	public String getRecord_id() {
		return record_id;
	}
	public void setRecord_id(String record_id) {
		this.record_id = record_id;
	}
	public String getAuth_policy_recid() {
		return auth_policy_recid;
	}
	public void setAuth_policy_recid(String auth_policy_recid) {
		this.auth_policy_recid = auth_policy_recid;
	}
	public int getRule_weight() {
		return rule_weight;
	}
	public void setRule_weight(int rule_weight) {
		this.rule_weight = rule_weight;
	}
	public String getRule_name() {
		return rule_name;
	}
	public void setRule_name(String rule_name) {
		this.rule_name = rule_name;
	}
	/*public String getTransfer_amt() {
		
		
		
		Double dblValue = Double.parseDouble(transfer_amt);  
		String amt = String.format("%.2f", dblValue);
	 
		
		return amt;
	}
	public void setTransfer_amt(String transfer_amt) {
		this.transfer_amt = transfer_amt;
	}*/
	public String getTransfer_ccy() {
		return transfer_ccy;
	}
	public int getTransfer_amt() {
		return transfer_amt;
	}
	public void setTransfer_amt(int transfer_amt) {
		this.transfer_amt = transfer_amt;
	}
	public void setTransfer_ccy(String transfer_ccy) {
		this.transfer_ccy = transfer_ccy;
	}
	public String getOperator_cd() {
		return operator_cd;
	}
	public void setOperator_cd(String operator_cd) {
		this.operator_cd = operator_cd;
	}
	public String getIs_sequence() {
		return is_sequence;
	}
	public void setIs_sequence(String is_sequence) {
		this.is_sequence = is_sequence;
	}
	public String getCreated_by_ucode() {
		return created_by_ucode;
	}
	public void setCreated_by_ucode(String created_by_ucode) {
		this.created_by_ucode = created_by_ucode;
	}
	public String getCreated_by_uname() {
		return created_by_uname;
	}
	public void setCreated_by_uname(String created_by_uname) {
		this.created_by_uname = created_by_uname;
	}
	public String getCreated_date() {
		return created_date;
	}
	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}
	public String getUpdated_by_ucode() {
		return updated_by_ucode;
	}
	public void setUpdated_by_ucode(String updated_by_ucode) {
		this.updated_by_ucode = updated_by_ucode;
	}
	public String getUpdated_by_uname() {
		return updated_by_uname;
	}
	public void setUpdated_by_uname(String updated_by_uname) {
		this.updated_by_uname = updated_by_uname;
	}
	public String getUpdated_date() {
		return updated_date;
	}
	public void setUpdated_date(String updated_date) {
		this.updated_date = updated_date;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getMaintenance_type() {
		return maintenance_type;
	}
	public void setMaintenance_type(String maintenance_type) {
		this.maintenance_type = maintenance_type;
	}
	 
	public List<AuthPolicyProduct> getAuthpol_product_list() {
		return authpol_product_list;
	}
	public void setAuthpol_product_list(List<AuthPolicyProduct> authpol_product_list) {
		this.authpol_product_list = authpol_product_list;
	}
	public List<AuthPolicyAccount> getAuthpol_account_list() {
		return authpol_account_list;
	}
	public void setAuthpol_account_list(List<AuthPolicyAccount> authpol_account_list) {
		this.authpol_account_list = authpol_account_list;
	}
	public List<AuthPolicyAuthorizer> getAuthpol_auth_list() {
		return authpol_auth_list;
	}
	public void setAuthpol_auth_list(List<AuthPolicyAuthorizer> authpol_auth_list) {
		this.authpol_auth_list = authpol_auth_list;
	}
	public String getTransfer_fmtAmt() {
		
		
		//if(this.transfer_ccy.equalsIgnoreCase("IDR"))
		{
			Double dblValue = Double.parseDouble(String.valueOf(transfer_amt));
			this.transfer_fmtAmt = String.format("%.2f", dblValue);
		}
		return transfer_fmtAmt;
		
 
	}
	public void setTransfer_fmtAmt(String transfer_fmtAmt) {
		this.transfer_fmtAmt = transfer_fmtAmt;
	}
 
	  
	  
	 
	 
	 
}



	
