package com.silverlake.mleb.mcb.module.vc.transaction;

public class TransactionAudit {
	private String id;
	private String usr_cd;
	private String usr_name;
	private String timestamp;
	private String action;
	private String remark;
	private String channel;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsr_cd() {
		return usr_cd;
	}
	public void setUsr_cd(String usr_cd) {
		this.usr_cd = usr_cd;
	}
	public String getUsr_name() {
		return usr_name;
	}
	public void setUsr_name(String usr_name) {
		this.usr_name = usr_name;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
}



	
