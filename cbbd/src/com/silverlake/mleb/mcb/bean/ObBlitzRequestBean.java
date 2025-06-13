package com.silverlake.mleb.mcb.bean;

public class ObBlitzRequestBean extends ObRequest implements java.io.Serializable{

	private static final long serialVersionUID = -2761210589122551947L;

	private String city;
	private String cinemaCode;
	private String movieCode;
	private String showDate;
	private String audiType;
	private String audiNo;
	private String showTime;
	private String movieFormat;
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCinemaCode() {
		return cinemaCode;
	}
	public void setCinemaCode(String cinemaCode) {
		this.cinemaCode = cinemaCode;
	}
	public String getMovieCode() {
		return movieCode;
	}
	public void setMovieCode(String movieCode) {
		this.movieCode = movieCode;
	}
	public String getShowDate() {
		return showDate;
	}
	public void setShowDate(String showDate) {
		this.showDate = showDate;
	}
	public String getAudiType() {
		return audiType;
	}
	public void setAudiType(String audiType) {
		this.audiType = audiType;
	}
	public String getAudiNo() {
		return audiNo;
	}
	public void setAudiNo(String audiNo) {
		this.audiNo = audiNo;
	}
	public String getShowTime() {
		return showTime;
	}
	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}
	public String getMovieFormat() {
		return movieFormat;
	}
	public void setMovieFormat(String movieFormat) {
		this.movieFormat = movieFormat;
	}
}
