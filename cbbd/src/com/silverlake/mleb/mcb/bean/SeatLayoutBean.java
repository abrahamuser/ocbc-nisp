package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class SeatLayoutBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String codeSeat;
	private String type;
	private String xCoordinate;
	private String yCoordinate;
	private String isTaken;
	
	public String getCodeSeat() {
		return codeSeat;
	}
	public void setCodeSeat(String codeSeat) {
		this.codeSeat = codeSeat;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getxCoordinate() {
		return xCoordinate;
	}
	public void setxCoordinate(String xCoordinate) {
		this.xCoordinate = xCoordinate;
	}
	public String getyCoordinate() {
		return yCoordinate;
	}
	public void setyCoordinate(String yCoordinate) {
		this.yCoordinate = yCoordinate;
	}
	public String getIsTaken() {
		return isTaken;
	}
	public void setIsTaken(String isTaken) {
		this.isTaken = isTaken;
	}
	

}
