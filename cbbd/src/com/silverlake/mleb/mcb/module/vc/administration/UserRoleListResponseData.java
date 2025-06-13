package com.silverlake.mleb.mcb.module.vc.administration;

import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;
import com.silverlake.mleb.mcb.module.vc.user.List_role;

import java.util.List;

public class UserRoleListResponseData extends VCResponseData {
	
	private List<List_role>role_list;

	public List<List_role> getRole_list() {
		return role_list;
	}

	public void setRole_list(List<List_role> role_list) {
		this.role_list = role_list;
	}

		      
}
