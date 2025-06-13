package com.silverlake.mleb.mcb.bean;


import java.io.Serializable;
import java.math.BigDecimal;

public class ObOwnCreditCardBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private BigDecimal availableLimit;
    private BigDecimal creditLimit;
    private BigDecimal minPayment;
    private BigDecimal outstandingBalance;
    private BigDecimal statementBalance;
    private Integer billerAutodebit;
    private Integer billerFlowType;
    private Integer billerFuture;
    private Integer billerInputAmountBit;
    private String amountCcy;
    private String billerCd;
    private String billerCustId;
    private String billerGroupCd;
    private String billerGroupName;
    private String billerId;
    private String billerName;
    private String billerSoaType;
    private String billerType;
    private String cif;
    private String dueDate;
    private String paymentPurchaseId;
    private String prodName;
    private String source;
    private String amount;
    
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
    
}
