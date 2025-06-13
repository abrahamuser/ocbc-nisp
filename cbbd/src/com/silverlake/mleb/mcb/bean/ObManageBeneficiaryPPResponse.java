package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObManageBeneficiaryPPResponse extends ObResponse implements Serializable {
	
	private static final long serialVersionUID = -4800737200536320464L;

	private String payeeIsExisting;
	private String payeeId;
	private String billerGroupCd;
	private String billerGroupName;
	private String billerCd;
	private String billerId;
	private String billerName;
	private String billerType;
	private String billerInputAmountBit;
	private String billerFlowType;
	private String billerSoaType;
	private String billerAutodebit;
	private String billerFuture;
	private String billerCustId;
	private String refNo;
	private Boolean isFavFlag;
    private String label1;
    private String label2;
    private String label3;
    private String label4;
    private String successMessage;
    private String errorMessage;
    private String billerCustNickName;
    
	public String getPayeeIsExisting() {
		return payeeIsExisting;
	}
	public void setPayeeIsExisting(String payeeIsExisting) {
		this.payeeIsExisting = payeeIsExisting;
	}
	public String getPayeeId() {
		return payeeId;
	}
	public void setPayeeId(String payeeId) {
		this.payeeId = payeeId;
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
	public String getBillerInputAmountBit() {
		return billerInputAmountBit;
	}
	public void setBillerInputAmountBit(String billerInputAmountBit) {
		this.billerInputAmountBit = billerInputAmountBit;
	}
	public String getBillerFlowType() {
		return billerFlowType;
	}
	public void setBillerFlowType(String billerFlowType) {
		this.billerFlowType = billerFlowType;
	}
	public String getBillerSoaType() {
		return billerSoaType;
	}
	public void setBillerSoaType(String billerSoaType) {
		this.billerSoaType = billerSoaType;
	}
	public String getBillerAutodebit() {
		return billerAutodebit;
	}
	public void setBillerAutodebit(String billerAutodebit) {
		this.billerAutodebit = billerAutodebit;
	}
	public String getBillerFuture() {
		return billerFuture;
	}
	public void setBillerFuture(String billerFuture) {
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
	public Boolean getIsFavFlag() {
		return isFavFlag;
	}
	public void setIsFavFlag(Boolean isFavFlag) {
		this.isFavFlag = isFavFlag;
	}
	public String getLabel1() {
		return label1;
	}
	public void setLabel1(String label1) {
		this.label1 = label1;
	}
	public String getLabel2() {
		return label2;
	}
	public void setLabel2(String label2) {
		this.label2 = label2;
	}
	public String getLabel3() {
		return label3;
	}
	public void setLabel3(String label3) {
		this.label3 = label3;
	}
	public String getLabel4() {
		return label4;
	}
	public void setLabel4(String label4) {
		this.label4 = label4;
	}
	public String getSuccessMessage() {
		return successMessage;
	}
	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getBillerCustNickName() {
		return billerCustNickName;
	}
	public void setBillerCustNickName(String billerCustNickName) {
		this.billerCustNickName = billerCustNickName;
	}
	
}
