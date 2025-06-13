package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObHolidayRequest extends ObRequest implements Serializable
{
	private static final long serialVersionUID = -3822270038236396582L;


	private Integer year;


	public Integer getYear() {
		return year;
	}


	public void setYear(Integer year) {
		this.year = year;
	}
	
	
}