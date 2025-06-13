package com.silverlake.mleb.mcb.module.vc.holiday;

import java.io.Serializable;

/**
 * This json object is compatible with json object of 
 * Holiday Calendar
 * Holiday Product
 * 
 * @author Alex Loo
 *
 */
public class ListHolidayProduct implements Serializable {

	private String id;
	private String holiday_type;
	private String prod_cd;
	private String from_ccy;
	private String dest_ccy;
	private String dest_country;
	private String dest_prov_id;
	private Boolean cross_ccy;
	private String calendar_date;
	private String desc_id;
	private String desc_en;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHoliday_type() {
		return holiday_type;
	}

	public void setHoliday_type(String holiday_type) {
		this.holiday_type = holiday_type;
	}

	public String getProd_cd() {
		return prod_cd;
	}

	public void setProd_cd(String prod_cd) {
		this.prod_cd = prod_cd;
	}

	public String getFrom_ccy() {
		return from_ccy;
	}

	public void setFrom_ccy(String from_ccy) {
		this.from_ccy = from_ccy;
	}

	public String getDest_ccy() {
		return dest_ccy;
	}

	public void setDest_ccy(String dest_ccy) {
		this.dest_ccy = dest_ccy;
	}

	public String getDest_country() {
		return dest_country;
	}

	public void setDest_country(String dest_country) {
		this.dest_country = dest_country;
	}

	public String getDest_prov_id() {
		return dest_prov_id;
	}

	public void setDest_prov_id(String dest_prov_id) {
		this.dest_prov_id = dest_prov_id;
	}

	public Boolean getCross_ccy() {
		return cross_ccy;
	}

	public void setCross_ccy(Boolean cross_ccy) {
		this.cross_ccy = cross_ccy;
	}

	public String getCalendar_date() {
		return calendar_date;
	}

	public void setCalendar_date(String calendar_date) {
		this.calendar_date = calendar_date;
	}

	public String getDesc_id() {
		return desc_id;
	}

	public void setDesc_id(String desc_id) {
		this.desc_id = desc_id;
	}

	public String getDesc_en() {
		return desc_en;
	}

	public void setDesc_en(String desc_en) {
		this.desc_en = desc_en;
	}

}