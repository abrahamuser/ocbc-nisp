package com.silverlake.mleb.mcb.module.vc.authorization;

import java.util.List;

import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;
import com.silverlake.mleb.mcb.module.vc.transaction.Transaction;

public class AuthResponseData extends VCResponseData
{
	  private List<Backdate> list_backdated_info;
	  private List<AuthResult> list_auth_result_info;
	  private List<SubscriberAuthResult> authpol_result_list;
	  private List<AcctAccess> accprofile_list;
	  
	  
	  private List<Transaction> list_trx;
	  private int total_rows;
	  
	  private List<SubscriberProfile> user_profile_list;
	  private List<AuthPolicy> authpol_list;
	  
	  
	  private int total_records;
	  
	  private List<SubscriberAuthResult> profile_result_list;
	  private List<SubscriberAuthResult> accprofile_result_list;
	  
	  private List<TransactionNotification> trx_notif_list;
	  
	public List<Backdate> getList_backdated_info() {
		return list_backdated_info;
	}
	public void setList_backdated_info(List<Backdate> list_backdated_info) {
		this.list_backdated_info = list_backdated_info;
	}
	public List<AuthResult> getList_auth_result_info() {
		return list_auth_result_info;
	}
	public void setList_auth_result_info(List<AuthResult> list_auth_result_info) {
		this.list_auth_result_info = list_auth_result_info;
	}
	public List<Transaction> getList_trx() {
		return list_trx;
	}
	public void setList_trx(List<Transaction> list_trx) {
		this.list_trx = list_trx;
	}
	public int getTotal_rows() {
		return total_rows;
	}
	public void setTotal_rows(int total_rows) {
		this.total_rows = total_rows;
	}
	public List<SubscriberProfile> getUser_profile_list() {
		return user_profile_list;
	}
	public void setUser_profile_list(List<SubscriberProfile> user_profile_list) {
		this.user_profile_list = user_profile_list;
	}
	public int getTotal_records() {
		return total_records;
	}
	public void setTotal_records(int total_records) {
		this.total_records = total_records;
	}
	 
	public List<AuthPolicy> getAuthpol_list() {
		return authpol_list;
	}
	public void setAuthpol_list(List<AuthPolicy> authpol_list) {
		this.authpol_list = authpol_list;
	}
	
	public List<AcctAccess> getAccprofile_list() {
		return accprofile_list;
	}
	public void setAccprofile_list(List<AcctAccess> accprofile_list) {
		this.accprofile_list = accprofile_list;
	}
	public List<SubscriberAuthResult> getProfile_result_list() {
		return profile_result_list;
	}
	public void setProfile_result_list(List<SubscriberAuthResult> profile_result_list) {
		this.profile_result_list = profile_result_list;
	}
	public List<SubscriberAuthResult> getAccprofile_result_list() {
		return accprofile_result_list;
	}
	public void setAccprofile_result_list(List<SubscriberAuthResult> accprofile_result_list) {
		this.accprofile_result_list = accprofile_result_list;
	}
	public List<SubscriberAuthResult> getAuthpol_result_list() {
		return authpol_result_list;
	}
	public void setAuthpol_result_list(List<SubscriberAuthResult> authpol_result_list) {
		this.authpol_result_list = authpol_result_list;
	}
	public List<TransactionNotification> getTrx_notif_list() {
		return trx_notif_list;
	}
	public void setTrx_notif_list(List<TransactionNotification> trx_notif_list) {
		this.trx_notif_list = trx_notif_list;
	}
	
		
}



	
