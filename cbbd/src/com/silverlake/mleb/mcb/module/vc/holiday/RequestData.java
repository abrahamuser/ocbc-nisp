package com.silverlake.mleb.mcb.module.vc.holiday;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

public class RequestData extends VCRequest{

    private int year;
    private String prod_cd;
    
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getProd_cd() {
		return prod_cd;
	}
	public void setProd_cd(String prod_cd) {
		this.prod_cd = prod_cd;
	}

}
