package com.silverlake.mleb.mcb.module.vc.administration;

import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;

import java.util.List;

public class UserProfileListResponseData extends VCResponseData {
	
	private List<SubscriberProfile> profile_list ;

	public List<SubscriberProfile> getProfile_list() {
		return profile_list;
	}

	public void setProfile_list(List<SubscriberProfile> profile_list) {
		this.profile_list = profile_list;
	}
	
	      
 }
