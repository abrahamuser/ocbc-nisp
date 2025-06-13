package com.silverlake.mleb.mcb.module.vc.others;

import java.io.Serializable;
import java.util.List;

public class TimeBatchList implements Serializable {
	
	
	private String prod_cd;
    private List [] time;
	
	public List[] getTime() {
		return time;
	}
	public void setTime(List[] time) {
		this.time = time;
	}
	   
	public String getProd_cd() {
		return prod_cd;
	}
	public void setProd_cd(String prod_cd) {
		this.prod_cd = prod_cd;
	}
	
    
    
	
}

