package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class Payee implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<String> listFavouritePayee;
	
	private String billerGroupCd;
	private String billerGroupName;
    private String billerCd;
    private String billerId;
    private String billerName;
    private String billerType;
    private Integer billerInputAmountBit;
    private Integer billerFlowType;
    private String billerSoaType;
    private Integer billerAutodebit;
    private Integer billerFuture;
    private String billerCustId;
    private String refNo;
    private String billerCustName;
    private String billerCustNickName;
    private String payeeId;
    private Boolean isFavFlag;
    private String ppType;
    
	public List<String> getListFavouritePayee() {
		return listFavouritePayee;
	}
	public void setListFavouritePayee(List<String> listFavouritePayee) {
		this.listFavouritePayee = listFavouritePayee;
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
	public String getBillerCd() {
		return billerCd;
	}
	public void setBillerCd(String billerCd) {
		this.billerCd = billerCd;
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
	public String getBillerType() {
		return billerType;
	}
	public void setBillerType(String billerType) {
		this.billerType = billerType;
	}
	public Integer getBillerInputAmountBit() {
		return billerInputAmountBit;
	}
	public void setBillerInputAmountBit(Integer billerInputAmountBit) {
		this.billerInputAmountBit = billerInputAmountBit;
	}
	public Integer getBillerFlowType() {
		return billerFlowType;
	}
	public void setBillerFlowType(Integer billerFlowType) {
		this.billerFlowType = billerFlowType;
	}
	public String getBillerSoaType() {
		return billerSoaType;
	}
	public void setBillerSoaType(String billerSoaType) {
		this.billerSoaType = billerSoaType;
	}
	public Integer getBillerAutodebit() {
		return billerAutodebit;
	}
	public void setBillerAutodebit(Integer billerAutodebit) {
		this.billerAutodebit = billerAutodebit;
	}
	public Integer getBillerFuture() {
		return billerFuture;
	}
	public void setBillerFuture(Integer billerFuture) {
		this.billerFuture = billerFuture;
	}
	public String getBillerCustId() {
		return billerCustId;
	}
	public void setBillerCustId(String billerCustId) {
		this.billerCustId = billerCustId;
	}
	public String getRefNo() {
		return refNo;
	}
	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}
	public String getBillerCustName() {
		return billerCustName;
	}
	public void setBillerCustName(String billerCustName) {
		this.billerCustName = billerCustName;
	}
	public String getBillerCustNickName() {
		return billerCustNickName;
	}
	public void setBillerCustNickName(String billerCustNickName) {
		this.billerCustNickName = billerCustNickName;
	}
	public String getPayeeId() {
		return payeeId;
	}
	public void setPayeeId(String payeeId) {
		this.payeeId = payeeId;
	}
	public Boolean getIsFavFlag() {
		return isFavFlag;
	}
	public void setIsFavFlag(Boolean isFavFlag) {
		this.isFavFlag = isFavFlag;
	}
	public String getPpType() {
		return ppType;
	}
	public void setPpType(String ppType) {
		this.ppType = ppType;
	}
	
}
