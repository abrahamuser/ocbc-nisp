package com.silverlake.mleb.mcb.bean;

import java.util.List;


public class ObBlitzResponseBean extends ObResponse implements java.io.Serializable{

	private static final long serialVersionUID = -800895080564949840L;
	
	private List<String> cityList;
	
	private String city;
	
	private String cinemaCode;
	
	private String movieCode;
	
	private List<ObBlitzBean> movieList;
	
	private List<ObBlitzBean> cinemaList;
	
	private List<String> showDateList;

	private String showDate;
	
	private String audiType;
	
	private String moviePrice;
	
	private String showTime;
	
	private String movieformat;
	
	private String midnight;
	
	private String capacity;
	
	private String audiNo;
	
	private List<ObBlitzBean> listAudiTypes;
	
	private String audiWidth;
	
	private String audiHeight;
	
	private List<SeatLayoutBean> listSeatLayout;
	
	private List<ObAccountBean> ObAccountList;
	
	public String getShowDate() {
		return showDate;
	}
	public void setShowDate(String showDate) {
		this.showDate = showDate;
	}
	public List<ObBlitzBean> getListAudiTypes() {
		return listAudiTypes;
	}
	public void setListAudiTypes(List<ObBlitzBean> listAudiTypes) {
		this.listAudiTypes = listAudiTypes;
	}
	public List<String> getCityList() {
		return cityList;
	}

	public void setCityList(List<String> cityList) {
		this.cityList = cityList;
	}
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

	public List<String> getShowDateList() {
		return showDateList;
	}

	public void setShowDateList(List<String> showDateList) {
		this.showDateList = showDateList;
	}
	public String getAudiType() {
		return audiType;
	}
	public void setAudiType(String audiType) {
		this.audiType = audiType;
	}
	public String getMoviePrice() {
		return moviePrice;
	}
	public void setMoviePrice(String moviePrice) {
		this.moviePrice = moviePrice;
	}
	public String getShowTime() {
		return showTime;
	}
	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}
	public String getMovieformat() {
		return movieformat;
	}
	public void setMovieformat(String movieformat) {
		this.movieformat = movieformat;
	}
	public String getMidnight() {
		return midnight;
	}
	public void setMidnight(String midnight) {
		this.midnight = midnight;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	public String getAudiNo() {
		return audiNo;
	}
	public void setAudiNo(String audiNo) {
		this.audiNo = audiNo;
	}
	public String getAudiWidth() {
		return audiWidth;
	}
	public void setAudiWidth(String audiWidth) {
		this.audiWidth = audiWidth;
	}
	public String getAudiHeight() {
		return audiHeight;
	}
	public void setAudiHeight(String audiHeight) {
		this.audiHeight = audiHeight;
	}
	public List<SeatLayoutBean> getListSeatLayout() {
		return listSeatLayout;
	}
	public void setListSeatLayout(List<SeatLayoutBean> listSeatLayout) {
		this.listSeatLayout = listSeatLayout;
	}
	public List<ObBlitzBean> getMovieList() {
		return movieList;
	}
	public void setMovieList(List<ObBlitzBean> movieList) {
		this.movieList = movieList;
	}
	public List<ObBlitzBean> getCinemaList() {
		return cinemaList;
	}
	public void setCinemaList(List<ObBlitzBean> cinemaList) {
		this.cinemaList = cinemaList;
	}
	public List<ObAccountBean> getObAccountList() {
		return ObAccountList;
	}
	public void setObAccountList(List<ObAccountBean> obAccountList) {
		ObAccountList = obAccountList;
	}
}
