
package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;



public class ObExtHeader implements Serializable
{

    private final static long serialVersionUID = 1L;
    
    
   
   
    private String id;
    private String password;
    private String version;
    
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
   
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}

}

