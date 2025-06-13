package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObBlitzBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String cinemaCode;
	
	private String cinemaName;
	
	private String movieCode;
	
	private String movieTitle;
	
	private String audiTypeCode;
	
	private String audiTypeDescription;

	public String getCinemaCode() {
		return cinemaCode;
	}

	public void setCinemaCode(String cinemaCode) {
		this.cinemaCode = cinemaCode;
	}

	public String getCinemaName() {
		return cinemaName;
	}

	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}

	public String getMovieCode() {
		return movieCode;
	}

	public void setMovieCode(String movieCode) {
		this.movieCode = movieCode;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public String getAudiTypeCode() {
		return audiTypeCode;
	}

	public void setAudiTypeCode(String audiTypeCode) {
		this.audiTypeCode = audiTypeCode;
	}

	public String getAudiTypeDescription() {
		return audiTypeDescription;
	}

	public void setAudiTypeDescription(String audiTypeDescription) {
		this.audiTypeDescription = audiTypeDescription;
	}
	
}
