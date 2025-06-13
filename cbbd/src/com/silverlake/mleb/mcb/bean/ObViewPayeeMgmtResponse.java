package com.silverlake.mleb.mcb.bean;


import java.io.Serializable;

public class ObViewPayeeMgmtResponse extends ObResponse implements Serializable
{
private static final long serialVersionUID = 1L;
	
	private Integer billerAutodebit;
    private Integer billerFlowType;
    private Integer billerFuture;
    private Integer billerInputAmountBit;
    private Integer version;
    private String billerCd;
    private String billerCustId;
    private String billerGroupCd;
    private String billerGroupName;
    private String billerId;
    private String billerName;
    private String billerSoaType;
    private String billerType;
    private String createdBy;
    private Boolean isDel;
    private Boolean isFavFlag;
    private String payeeId;
    private String timeCreated;
    private String timeUpdated;
    private String updatedBy;
    private String userCif;
	
	public Integer getBillerAutodebit() {
		return billerAutodebit;
	}
	public void setBillerAutodebit(Integer billerAutodebit) {
		this.billerAutodebit = billerAutodebit;
	}
	public Integer getBillerFlowType() {
		return billerFlowType;
	}
	public void setBillerFlowType(Integer billerFlowType) {
		this.billerFlowType = billerFlowType;
	}
	public Integer getBillerFuture() {
		return billerFuture;
	}
	public void setBillerFuture(Integer billerFuture) {
		this.billerFuture = billerFuture;
	}
	public Integer getBillerInputAmountBit() {
		return billerInputAmountBit;
	}
	public void setBillerInputAmountBit(Integer billerInputAmountBit) {
		this.billerInputAmountBit = billerInputAmountBit;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getBillerCd() {
		return billerCd;
	}
	public void setBillerCd(String billerCd) {
		this.billerCd = billerCd;
	}
	public String getBillerCustId() {
		return billerCustId;
	}
	public void setBillerCustId(String billerCustId) {
		this.billerCustId = billerCustId;
	}
	public String getBillerGroupCd() {
		return billerGroupCd;
	}
	public void setBillerGroupCd(String billerGroupCd) {
		this.billerGroupCd = billerGroupCd;
	}
	public String getBillerGroupName() {
		return billerGroupName;
	}
	public void setBillerGroupName(String billerGroupName) {
		this.billerGroupName = billerGroupName;
	}
	public String getBillerId() {
		return billerId;
	}
	public void setBillerId(String billerId) {
		this.billerId = billerId;
	}
	public String getBillerName() {
		return billerName;
	}
	public void setBillerName(String billerName) {
		this.billerName = billerName;
	}
	public String getBillerSoaType() {
		return billerSoaType;
	}
	public void setBillerSoaType(String billerSoaType) {
		this.billerSoaType = billerSoaType;
	}
	public String getBillerType() {
		return billerType;
	}
	public void setBillerType(String billerType) {
		this.billerType = billerType;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Boolean getIsDel() {
		return isDel;
	}
	public void setIsDel(Boolean isDel) {
		this.isDel = isDel;
	}
	public Boolean getIsFavFlag() {
		return isFavFlag;
	}
	public void setIsFavFlag(Boolean isFavFlag) {
		this.isFavFlag = isFavFlag;
	}
	public String getPayeeId() {
		return payeeId;
	}
	public void setPayeeId(String payeeId) {
		this.payeeId = payeeId;
	}
	public String getTimeCreated() {
		return timeCreated;
	}
	public void setTimeCreated(String timeCreated) {
		this.timeCreated = timeCreated;
	}
	public String getTimeUpdated() {
		return timeUpdated;
	}
	public void setTimeUpdated(String timeUpdated) {
		this.timeUpdated = timeUpdated;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getUserCif() {
		return userCif;
	}
	public void setUserCif(String userCif) {
		this.userCif = userCif;
	}

}