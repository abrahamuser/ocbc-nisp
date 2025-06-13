package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


public class ObAuditBean implements Serializable {
	private String id;
	private String usrCode;
	private String usrName;
	private String timestamp;
	private String action;
	private String remark;
	private String channel;
	
	private Boolean isSeq;
	private String nextAuth;
	private List<ObAuditBean> detailList;
	
	private Integer weight;
	private String authProfileId;
	private String authProfileName;
	private String authDate;
	private String authBy;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsrCode() {
		return usrCode;
	}
	public void setUsrCode(String usrCode) {
		this.usrCode = usrCode;
	}
	public String getUsrName() {
		return usrName;
	}
	public void setUsrName(String usrName) {
		this.usrName = usrName;
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
	public Boolean getIsSeq() {
		return isSeq;
	}
	public void setIsSeq(Boolean isSeq) {
		this.isSeq = isSeq;
	}
	public String getNextAuth() {
		return nextAuth;
	}
	public void setNextAuth(String nextAuth) {
		this.nextAuth = nextAuth;
	}
	public List<ObAuditBean> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<ObAuditBean> detailList) {
		this.detailList = detailList;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public String getAuthProfileId() {
		return authProfileId;
	}
	public void setAuthProfileId(String authProfileId) {
		this.authProfileId = authProfileId;
	}
	public String getAuthDate() {
		return authDate;
	}
	public void setAuthDate(String authDate) {
		this.authDate = authDate;
	}
	public String getAuthBy() {
		return authBy;
	}
	public void setAuthBy(String authBy) {
		this.authBy = authBy;
	}
	public String getAuthProfileName() {
		return authProfileName;
	}
	public void setAuthProfileName(String authProfileName) {
		this.authProfileName = authProfileName;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
}
