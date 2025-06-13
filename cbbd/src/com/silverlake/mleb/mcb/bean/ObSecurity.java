
package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;



public class ObSecurity implements Serializable
{

    private final static long serialVersionUID = 1L;
    
    
    private String csrf_token;
    private String model;
    private String type;
    private String os;
    private String appversion;
    private String locale;
    private String id;
    
    private String altitude;
    private String latitude;
    private String longtitude;
    private String city;
    private String state;
    private String country;
    private String rooted;
    private String make;
    private String pnstoken;
    private String app_mode;
    private String ip;
    
	public String getPnstoken() {
		return pnstoken;
	}
	public void setPnstoken(String pnstoken) {
		this.pnstoken = pnstoken;
	}
	public String getCsrf_token() {
		return csrf_token;
	}
	public void setCsrf_token(String csrf_token) {
		this.csrf_token = csrf_token;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getAppversion() {
		return appversion;
	}
	public void setAppversion(String appversion) {
		this.appversion = appversion;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAltitude() {
		return altitude;
	}
	public void setAltitude(String altitude) {
		this.altitude = altitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongtitude() {
		return longtitude;
	}
	public void setLongtitude(String longtitude) {
		this.longtitude = longtitude;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getRooted() {
		return rooted;
	}
	public void setRooted(String rooted) {
		this.rooted = rooted;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getApp_mode() {
		return app_mode;
	}
	public void setApp_mode(String app_mode) {
		this.app_mode = app_mode;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
    
   
    

}

