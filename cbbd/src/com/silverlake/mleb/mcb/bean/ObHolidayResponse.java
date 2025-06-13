package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

import com.silverlake.mleb.mcb.module.vc.holiday.ListHolidayCalendar;

public class ObHolidayResponse extends ObResponse implements Serializable
{
	private static final long serialVersionUID = -2950884870743426013L;
	
	private List<ListHolidayCalendar> holidayCalendar;

	public List<ListHolidayCalendar> getHolidayCalendar() {
		return holidayCalendar;
	}

	public void setHolidayCalendar(List<ListHolidayCalendar> holidayCalendar) {
		this.holidayCalendar = holidayCalendar;
	}
	
	
}
