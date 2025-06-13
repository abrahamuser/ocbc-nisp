package com.silverlake.mleb.mcb.bean;


import java.io.Serializable;
import java.util.List;

import com.silverlake.mleb.mcb.module.vc.authorization.AcctAccess;
import com.silverlake.mleb.mcb.module.vc.authorization.AuthPolicy;
import com.silverlake.mleb.mcb.module.vc.authorization.SubscriberAuthResult;
import com.silverlake.mleb.mcb.module.vc.authorization.SubscriberProfile;

public class ObProfileMaintResponse extends ObResponse implements Serializable{

    
	private List<SubscriberProfile> user_profile_list;
	private List<SubscriberAuthResult> subscriber_auth_result;
	private List<AuthPolicy> policy_list;
	private List<AcctAccess> accprofile_list;
	private int total_records;
	
	
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
 
	public List<AuthPolicy> getPolicy_list() {
		return policy_list;
	}
	public void setPolicy_list(List<AuthPolicy> policy_list) {
		this.policy_list = policy_list;
	}
 
	public List<AcctAccess> getAccprofile_list() {
		return accprofile_list;
	}
	public void setAccprofile_list(List<AcctAccess> accprofile_list) {
		this.accprofile_list = accprofile_list;
	}
	public List<SubscriberAuthResult> getSubscriber_auth_result() {
		return subscriber_auth_result;
	}
	public void setSubscriber_auth_result(List<SubscriberAuthResult> subscriber_auth_result) {
		this.subscriber_auth_result = subscriber_auth_result;
	}
	
	
	
	
	 
}
