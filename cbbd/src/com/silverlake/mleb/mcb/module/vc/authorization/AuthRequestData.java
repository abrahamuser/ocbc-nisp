package com.silverlake.mleb.mcb.module.vc.authorization;

import java.util.List;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

public class AuthRequestData extends VCRequest
{
	

	 public static String method_auth_List_pending_trx  = "authorization/list_pending_trx";
	 public static String method_auth_pre_authorize = "authorization/pre_authorize";
	 public static String method_auth_authorize  = "authorization/authorize";
	 
	 
	 public static String method_auth_admin_userProfile_pending = "authorization/administration/user_profile/pending_auth_list";
	 public static String method_auth_admin_userProfile_maintAuth  = "authorization/administration/user_profile/maintenance_auth";
	
	 public static String method_auth_admin_policy_pending = "authorization/administration/authpolicy/pending_auth_list";
	 public static String method_auth_admin_policy_maintAuth  = "authorization/administration/authpolicy/maintenance_auth";
	
	 public static String method_auth_admin_access_pending = "authorization/administration/accessprofile/pending_auth_list";
	 public static String method_auth_admin_access_maintAuth  = "authorization/administration/accessprofile/maintenance_auth";
	
	 private String pymt_master_id;
	 private String prod_cd;
	 private String value_date_from;
	 private String value_date_to;
	 public String upload_date_from;
	 public String upload_date_to;
	 private String amount_from;
	 private String amount_to;
	 private String debit_acct_no;
	 private String debit_acct_ccy;
	 private String transfer_ccy;
	 private List<String> source_trx;
	 private String source_file_name;
	 private String bank_ref;
	 private String cust_ref;
	 private String on_behalf;
	 private String trx_status;
	 private String page_no;
     
	 private String page_size;
	 private String request_id;
	 private String response_code;
	 private String device_id;
	 private List<AuthPayment> list_payment;
	 
	 
	 private String action_cd;
	 private List<SubscriberProfile> pending_profile_list;
	 private List<AuthPolicy> pending_authpol_list;
	 private List<AcctAccess> pending_accprofile_list;
	 
	 private String device_type;
	 private String device_os;
	 
	public String getProd_cd() {
		return prod_cd;
	}
	public void setProd_cd(String prod_cd) {
		this.prod_cd = prod_cd;
	}
	public String getValue_date_from() {
		return value_date_from;
	}
	public void setValue_date_from(String value_date_from) {
		this.value_date_from = value_date_from;
	}
	public String getValue_date_to() {
		return value_date_to;
	}
	public void setValue_date_to(String value_date_to) {
		this.value_date_to = value_date_to;
	}
	public String getAmount_from() {
		return amount_from;
	}
	public void setAmount_from(String amount_from) {
		this.amount_from = amount_from;
	}
	public String getAmount_to() {
		return amount_to;
	}
	public void setAmount_to(String amount_to) {
		this.amount_to = amount_to;
	}
	public String getDebit_acct_no() {
		return debit_acct_no;
	}
	public void setDebit_acct_no(String debit_acct_no) {
		this.debit_acct_no = debit_acct_no;
	}
	public String getDebit_acct_ccy() {
		return debit_acct_ccy;
	}
	public void setDebit_acct_ccy(String debit_acct_ccy) {
		this.debit_acct_ccy = debit_acct_ccy;
	}
	public String getTransfer_ccy() {
		return transfer_ccy;
	}
	public void setTransfer_ccy(String transfer_ccy) {
		this.transfer_ccy = transfer_ccy;
	}
	public List<String> getSource_trx() {
		return source_trx;
	}
	public void setSource_trx(List<String> source_trx2) {
		this.source_trx = source_trx2;
	}
	public String getSource_file_name() {
		return source_file_name;
	}
	public void setSource_file_name(String source_file_name) {
		this.source_file_name = source_file_name;
	}
	public String getBank_ref() {
		return bank_ref;
	}
	public void setBank_ref(String bank_ref) {
		this.bank_ref = bank_ref;
	}
	public String getCust_ref() {
		return cust_ref;
	}
	public void setCust_ref(String cust_ref) {
		this.cust_ref = cust_ref;
	}
	public String getOn_behalf() {
		return on_behalf;
	}
	public void setOn_behalf(String on_behalf) {
		this.on_behalf = on_behalf;
	}
	public String getTrx_status() {
		return trx_status;
	}
	public void setTrx_status(String trx_status) {
		this.trx_status = trx_status;
	}
	public String getPage_no() {
		return page_no;
	}
	public void setPage_no(String page_no) {
		this.page_no = page_no;
	}
	public String getPage_size() {
		return page_size;
	}
	public void setPage_size(String page_size) {
		this.page_size = page_size;
	}
	public String getRequest_id() {
		return request_id;
	}
	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}
	public String getResponse_code() {
		return response_code;
	}
	public void setResponse_code(String response_code) {
		this.response_code = response_code;
	}
	public String getDevice_id() {
		return device_id;
	}
	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}
	public List<AuthPayment> getList_payment() {
		return list_payment;
	}
	public void setList_payment(List<AuthPayment> list_payment) {
		this.list_payment = list_payment;
	}
	public String getPymt_master_id() {
		return pymt_master_id;
	}
	public void setPymt_master_id(String pymt_master_id) {
		this.pymt_master_id = pymt_master_id;
	}
	public String getUpload_date_from() {
		return upload_date_from;
	}
	public String getUpload_date_to() {
		return upload_date_to;
	}
	public void setUpload_date_from(String upload_date_from) {
		this.upload_date_from = upload_date_from;
	}
	public void setUpload_date_to(String upload_date_to) {
		this.upload_date_to = upload_date_to;
	}
	public String getAction_cd() {
		return action_cd;
	}
	public void setAction_cd(String action_cd) {
		this.action_cd = action_cd;
	}
	public List<SubscriberProfile> getPending_profile_list() {
		return pending_profile_list;
	}
	public void setPending_profile_list(List<SubscriberProfile> pending_profile_list) {
		this.pending_profile_list = pending_profile_list;
	}
	public List<AuthPolicy> getPending_authpol_list() {
		return pending_authpol_list;
	}
	public void setPending_authpol_list(List<AuthPolicy> pending_authpol_list) {
		this.pending_authpol_list = pending_authpol_list;
	}
	public List<AcctAccess> getPending_accprofile_list() {
		return pending_accprofile_list;
	}
	public void setPending_accprofile_list(List<AcctAccess> pending_accprofile_list) {
		this.pending_accprofile_list = pending_accprofile_list;
	}
	public String getDevice_type() {
		return device_type;
	}
	public void setDevice_type(String device_type) {
		this.device_type = device_type;
	}
	public String getDevice_os() {
		return device_os;
	}
	public void setDevice_os(String device_os) {
		this.device_os = device_os;
	}

	
	



}



	
