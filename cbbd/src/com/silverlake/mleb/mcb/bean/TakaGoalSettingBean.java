package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class TakaGoalSettingBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
    private String goalCode;
    private String goalTitle;
    private String goalDesc;
    private String goalImg;
	public String getGoalCode() {
		return goalCode;
	}
	public void setGoalCode(String goalCode) {
		this.goalCode = goalCode;
	}
	public String getGoalTitle() {
		return goalTitle;
	}
	public void setGoalTitle(String goalTitle) {
		this.goalTitle = goalTitle;
	}
	public String getGoalDesc() {
		return goalDesc;
	}
	public void setGoalDesc(String goalDesc) {
		this.goalDesc = goalDesc;
	}
	public String getGoalImg() {
		return goalImg;
	}
	public void setGoalImg(String goalImg) {
		this.goalImg = goalImg;
	}
}
