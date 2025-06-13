package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class ObIGBPayDetailResponseBean extends ObResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ObAccountBean ObAccResult;
	private ObFavoriteAccountBean ObFavAccResult;
	
	public ObAccountBean getObAccResult() {
		return ObAccResult;
	}
	public void setObAccResult(ObAccountBean obAccResult) {
		ObAccResult = obAccResult;
	}
	public ObFavoriteAccountBean getObFavAccResult() {
		return ObFavAccResult;
	}
	public void setObFavAccResult(ObFavoriteAccountBean obFavAccResult) {
		ObFavAccResult = obFavAccResult;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
