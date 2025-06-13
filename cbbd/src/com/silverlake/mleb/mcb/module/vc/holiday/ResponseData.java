package com.silverlake.mleb.mcb.module.vc.holiday;


import java.util.List;

import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;

public class ResponseData extends VCResponseData{
	private List<ListHolidayProduct> list_holiday_product = null;
	private List<ListHolidayCalendar> list_holiday_calendar = null;

	public List<ListHolidayProduct> getList_holiday_product() {
		return list_holiday_product;
	}

	public void setList_holiday_product(List<ListHolidayProduct> list_holiday_product) {
		this.list_holiday_product = list_holiday_product;
	}

	public List<ListHolidayCalendar> getList_holiday_calendar() {
		return list_holiday_calendar;
	}

	public void setList_holiday_calendar(List<ListHolidayCalendar> list_holiday_calendar) {
		this.list_holiday_calendar = list_holiday_calendar;
	}
	
	
}
