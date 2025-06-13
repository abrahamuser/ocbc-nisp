package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class ObPaymentBillerBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private Boolean isDel;
    private Boolean isFavFlag;
    private Integer billerAutodebit;
    private Integer billerFlowType;
    private Integer billerFuture;
    private Integer billerInputAmountBit;
    private Integer version;
    private String amountCcy;
    private String billerCd;
    private String billerCustId;
    private String billerCustNickName;
    private String billerGroupCd;
    private String billerGroupName;
    private String billerId;
    private String billerName;
    private String billerSoaType;
    private String billerType;
    private String billerUUID;
    private String createdBy;
    private String isOwn;
    private String payeeId;
    private String timeCreated;
    private String timeUpdated;
    private String updatedBy;
    private String userCif;
   
    private BigDecimal availableLimit;
    private BigDecimal creditLimit;
    private BigDecimal minPayment;
    private BigDecimal outstandingBalance;
    private BigDecimal statementBalance;
    private String cif;
    private String dueDate;
    private String paymentPurchaseId;
    private String prodName;
    private String source;
    private String amount;
    
    private Integer casaBit;
    private Integer ccBit;
    private Integer sequence;
    private String batchId;
    private String billerGrpId;
    private String fee;	
    private String futureFlag;
    private String minAmount;
    private String recurringFlag;
    private String systemGroup;
    
    private String label1;
    private String label2;
    private String label3;
    private String label4;
    
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
	public String getAmountCcy() {
		return amountCcy;
	}
	public void setAmountCcy(String amountCcy) {
		this.amountCcy = amountCcy;
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
	public String getBillerCustNickName() {
		return billerCustNickName;
	}
	public void setBillerCustNickName(String billerCustNickName) {
		this.billerCustNickName = billerCustNickName;
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
	public String getBillerUUID() {
		return billerUUID;
	}
	public void setBillerUUID(String billerUUID) {
		this.billerUUID = billerUUID;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getIsOwn() {
		return isOwn;
	}
	public void setIsOwn(String isOwn) {
		this.isOwn = isOwn;
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
	public BigDecimal getAvailableLimit() {
		return availableLimit;
	}
	public void setAvailableLimit(BigDecimal availableLimit) {
		this.availableLimit = availableLimit;
	}
	public BigDecimal getCreditLimit() {
		return creditLimit;
	}
	public void setCreditLimit(BigDecimal creditLimit) {
		this.creditLimit = creditLimit;
	}
	public BigDecimal getMinPayment() {
		return minPayment;
	}
	public void setMinPayment(BigDecimal minPayment) {
		this.minPayment = minPayment;
	}
	public BigDecimal getOutstandingBalance() {
		return outstandingBalance;
	}
	public void setOutstandingBalance(BigDecimal outstandingBalance) {
		this.outstandingBalance = outstandingBalance;
	}
	public BigDecimal getStatementBalance() {
		return statementBalance;
	}
	public void setStatementBalance(BigDecimal statementBalance) {
		this.statementBalance = statementBalance;
	}
	public String getCif() {
		return cif;
	}
	public void setCif(String cif) {
		this.cif = cif;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getPaymentPurchaseId() {
		return paymentPurchaseId;
	}
	public void setPaymentPurchaseId(String paymentPurchaseId) {
		this.paymentPurchaseId = paymentPurchaseId;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public Integer getCasaBit() {
		return casaBit;
	}
	public void setCasaBit(Integer casaBit) {
		this.casaBit = casaBit;
	}
	public Integer getCcBit() {
		return ccBit;
	}
	public void setCcBit(Integer ccBit) {
		this.ccBit = ccBit;
	}
	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	public String getBillerGrpId() {
		return billerGrpId;
	}
	public void setBillerGrpId(String billerGrpId) {
		this.billerGrpId = billerGrpId;
	}
	public String getFee() {
		return fee;
	}
	public void setFee(String fee) {
		this.fee = fee;
	}
	public String getFutureFlag() {
		return futureFlag;
	}
	public void setFutureFlag(String futureFlag) {
		this.futureFlag = futureFlag;
	}
	public String getMinAmount() {
		return minAmount;
	}
	public void setMinAmount(String minAmount) {
		this.minAmount = minAmount;
	}
	public String getRecurringFlag() {
		return recurringFlag;
	}
	public void setRecurringFlag(String recurringFlag) {
		this.recurringFlag = recurringFlag;
	}
	public String getSystemGroup() {
		return systemGroup;
	}
	public void setSystemGroup(String systemGroup) {
		this.systemGroup = systemGroup;
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
    
}
