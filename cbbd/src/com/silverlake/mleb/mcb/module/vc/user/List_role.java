
package com.silverlake.mleb.mcb.module.vc.user;

import java.io.Serializable;

public class List_role implements Serializable {

    private String role_cd;
    private String role_base;
    private String role_desc;
    private Integer version;
    private String role_group_cd;
    private String role_group_desc;
    

    public String getRole_cd() {
        return role_cd;
    }

    public void setRole_cd(String role_cd) {
        this.role_cd = role_cd;
    }

    public String getRole_base() {
        return role_base;
    }

    public void setRole_base(String role_base) {
        this.role_base = role_base;
    }

    public String getRole_desc() {
        return role_desc;
    }

    public void setRole_desc(String role_desc) {
        this.role_desc = role_desc;
    }

	public Integer getVersion() {
		return version;
	}

	public String getRole_group_cd() {
		return role_group_cd;
	}

	public String getRole_group_desc() {
		return role_group_desc;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public void setRole_group_cd(String role_group_cd) {
		this.role_group_cd = role_group_cd;
	}

	public void setRole_group_desc(String role_group_desc) {
		this.role_group_desc = role_group_desc;
	}

}
