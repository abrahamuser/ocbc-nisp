package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

import com.silverlake.mleb.mcb.module.vc.authorization.AcctAccess;
import com.silverlake.mleb.mcb.module.vc.authorization.AuthPolicy;
import com.silverlake.mleb.mcb.module.vc.authorization.SubscriberProfile;

 
public class ObProfileMaintRequest extends ObRequest implements Serializable {

	 	private String page_no;
	 	private String page_size;
	 	private String recordIds;
	 	private List<SubscriberProfile> user_profile_list;
	 	private List<AuthPolicy> policy_list;
	 	private List<AcctAccess> access_list;
	 	private String action_cd;
	 	
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
	 
		
		 
		public List<SubscriberProfile> getUser_profile_list() {
			return user_profile_list;
		}
		public void setUser_profile_list(List<SubscriberProfile> user_profile_list) {
			this.user_profile_list = user_profile_list;
		}
		public String getAction_cd() {
			return action_cd;
		}
		public void setAction_cd(String action_cd) {
			this.action_cd = action_cd;
		}
		public List<AuthPolicy> getPolicy_list() {
			return policy_list;
		}
		public void setPolicy_list(List<AuthPolicy> policy_list) {
			this.policy_list = policy_list;
		}
	 
		public String getRecordIds() {
			return recordIds;
		}
		public void setRecordIds(String recordIds) {
			this.recordIds = recordIds;
		}
		public List<AcctAccess> getAccess_list() {
			return access_list;
		}
		public void setAccess_list(List<AcctAccess> access_list) {
			this.access_list = access_list;
		}
		
		
		
	    

}
