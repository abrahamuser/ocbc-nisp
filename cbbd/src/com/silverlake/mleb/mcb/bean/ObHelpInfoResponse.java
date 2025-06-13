package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;


public class ObHelpInfoResponse extends ObResponse implements Serializable
{
	private static final long serialVersionUID = 4389872192410116666L;

	private List<HelpInfoBean> helpInfoList;
	
	public List<HelpInfoBean> getHelpInfoList() {
		return helpInfoList;
	}

	public void setHelpInfoList(List<HelpInfoBean> helpInfoList) {
		this.helpInfoList = helpInfoList;
	}
	
}
