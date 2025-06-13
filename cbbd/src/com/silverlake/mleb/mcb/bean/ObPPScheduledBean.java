package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObPPScheduledBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String amount;
    private String amountCcy;
    private String billerAutodebit;
    private String billerCd;
    private String billerCustId;
    private String billerCustNickName;
    private String billerFlowType;
    private String billerFuture;
    private String billerGroupCd;
    private String billerGroupName;
    private String billerInputAmountBit;
    private String billerName;
    private String billerSoaType;
    private String billerType;
    private String createdBy;
    private String debitAccountBankCode;
    private String debitAccountBranch;
    private String debitAccountCcy;
    private String debitAccountMcBit;
    private String debitAccountNo;
    private String debitAccountProdCode;
    private String debitAccountType;
    private String isDel;
    private String prodName;
    private String referenceNo;
    private String timeCreated;
    private String timeUpdated;
    private String version;
    
    // future
    private String beneAccountCcy;
    private String beneAccountName;
    private String beneAccountNo;
    private String beneficiaryBankBranch;
    private String beneficiaryBankBranchCode;
    private String beneficiaryBankCode;
    private String beneficiaryBankName;
    private String beneNickName;
    private String billerId;
    private String billingDate;
    private String cif;
    private String debitBankCode;
    private String debitCif;
    private String onlineSessionId;
    private String paymentId;
    private String paymentPurchaseId;
    private String productCode;
    private String productName;
    private String recurringFlag;
    private String refNo;
    private String registerFlag;
    private String remark;
    private String source;
    private String transferDate;
    private String transferServiceCode;
    private String transferStatus;
    private String transferStatusDesc;
    private String updatedBy;
    private String xmlDetails;
    
    // auto debit 
    private String detailRecurringId;
    private String detailRefNo;
    private String detailStatus;
    private String detailTrxDate;
    private String endDate;
    private String newTransactionDate;
    private String nextTransactionDate;
    private String numOfRecurring;
    private String recurringId;
    private String recurringType;
    private String startDate;
    private String status;
    private Integer totalRecurring;
    private String userCif;
    
    // group sorting
    private String groupNumber;
  	private String groupType;
	
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getAmountCcy() {
		return amountCcy;
	}
	public void setAmountCcy(String amountCcy) {
		this.amountCcy = amountCcy;
	}
	public String getBillerAutodebit() {
		return billerAutodebit;
	}
	public void setBillerAutodebit(String billerAutodebit) {
		this.billerAutodebit = billerAutodebit;
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
	public String getBillerFlowType() {
		return billerFlowType;
	}
	public void setBillerFlowType(String billerFlowType) {
		this.billerFlowType = billerFlowType;
	}
	public String getBillerFuture() {
		return billerFuture;
	}
	public void setBillerFuture(String billerFuture) {
		this.billerFuture = billerFuture;
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
	public String getBillerInputAmountBit() {
		return billerInputAmountBit;
	}
	public void setBillerInputAmountBit(String billerInputAmountBit) {
		this.billerInputAmountBit = billerInputAmountBit;
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
	public String getDebitAccountBankCode() {
		return debitAccountBankCode;
	}
	public void setDebitAccountBankCode(String debitAccountBankCode) {
		this.debitAccountBankCode = debitAccountBankCode;
	}
	public String getDebitAccountBranch() {
		return debitAccountBranch;
	}
	public void setDebitAccountBranch(String debitAccountBranch) {
		this.debitAccountBranch = debitAccountBranch;
	}
	public String getDebitAccountCcy() {
		return debitAccountCcy;
	}
	public void setDebitAccountCcy(String debitAccountCcy) {
		this.debitAccountCcy = debitAccountCcy;
	}
	public String getDebitAccountMcBit() {
		return debitAccountMcBit;
	}
	public void setDebitAccountMcBit(String debitAccountMcBit) {
		this.debitAccountMcBit = debitAccountMcBit;
	}
	public String getDebitAccountNo() {
		return debitAccountNo;
	}
	public void setDebitAccountNo(String debitAccountNo) {
		this.debitAccountNo = debitAccountNo;
	}
	public String getDebitAccountProdCode() {
		return debitAccountProdCode;
	}
	public void setDebitAccountProdCode(String debitAccountProdCode) {
		this.debitAccountProdCode = debitAccountProdCode;
	}
	public String getDebitAccountType() {
		return debitAccountType;
	}
	public void setDebitAccountType(String debitAccountType) {
		this.debitAccountType = debitAccountType;
	}
	public String getIsDel() {
		return isDel;
	}
	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getReferenceNo() {
		return referenceNo;
	}
	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
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
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getBeneAccountCcy() {
		return beneAccountCcy;
	}
	public void setBeneAccountCcy(String beneAccountCcy) {
		this.beneAccountCcy = beneAccountCcy;
	}
	public String getBeneAccountName() {
		return beneAccountName;
	}
	public void setBeneAccountName(String beneAccountName) {
		this.beneAccountName = beneAccountName;
	}
	public String getBeneAccountNo() {
		return beneAccountNo;
	}
	public void setBeneAccountNo(String beneAccountNo) {
		this.beneAccountNo = beneAccountNo;
	}
	public String getBeneficiaryBankBranch() {
		return beneficiaryBankBranch;
	}
	public void setBeneficiaryBankBranch(String beneficiaryBankBranch) {
		this.beneficiaryBankBranch = beneficiaryBankBranch;
	}
	public String getBeneficiaryBankBranchCode() {
		return beneficiaryBankBranchCode;
	}
	public void setBeneficiaryBankBranchCode(String beneficiaryBankBranchCode) {
		this.beneficiaryBankBranchCode = beneficiaryBankBranchCode;
	}
	public String getBeneficiaryBankCode() {
		return beneficiaryBankCode;
	}
	public void setBeneficiaryBankCode(String beneficiaryBankCode) {
		this.beneficiaryBankCode = beneficiaryBankCode;
	}
	public String getBeneficiaryBankName() {
		return beneficiaryBankName;
	}
	public void setBeneficiaryBankName(String beneficiaryBankName) {
		this.beneficiaryBankName = beneficiaryBankName;
	}
	public String getBeneNickName() {
		return beneNickName;
	}
	public void setBeneNickName(String beneNickName) {
		this.beneNickName = beneNickName;
	}
	public String getBillerId() {
		return billerId;
	}
	public void setBillerId(String billerId) {
		this.billerId = billerId;
	}
	public String getBillingDate() {
		return billingDate;
	}
	public void setBillingDate(String billingDate) {
		this.billingDate = billingDate;
	}
	public String getCif() {
		return cif;
	}
	public void setCif(String cif) {
		this.cif = cif;
	}
	public String getDebitBankCode() {
		return debitBankCode;
	}
	public void setDebitBankCode(String debitBankCode) {
		this.debitBankCode = debitBankCode;
	}
	public String getDebitCif() {
		return debitCif;
	}
	public void setDebitCif(String debitCif) {
		this.debitCif = debitCif;
	}
	public String getOnlineSessionId() {
		return onlineSessionId;
	}
	public void setOnlineSessionId(String onlineSessionId) {
		this.onlineSessionId = onlineSessionId;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public String getPaymentPurchaseId() {
		return paymentPurchaseId;
	}
	public void setPaymentPurchaseId(String paymentPurchaseId) {
		this.paymentPurchaseId = paymentPurchaseId;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getRecurringFlag() {
		return recurringFlag;
	}
	public void setRecurringFlag(String recurringFlag) {
		this.recurringFlag = recurringFlag;
	}
	public String getRefNo() {
		return refNo;
	}
	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}
	public String getRegisterFlag() {
		return registerFlag;
	}
	public void setRegisterFlag(String registerFlag) {
		this.registerFlag = registerFlag;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getTransferDate() {
		return transferDate;
	}
	public void setTransferDate(String transferDate) {
		this.transferDate = transferDate;
	}
	public String getTransferServiceCode() {
		return transferServiceCode;
	}
	public void setTransferServiceCode(String transferServiceCode) {
		this.transferServiceCode = transferServiceCode;
	}
	public String getTransferStatus() {
		return transferStatus;
	}
	public void setTransferStatus(String transferStatus) {
		this.transferStatus = transferStatus;
	}
	public String getTransferStatusDesc() {
		return transferStatusDesc;
	}
	public void setTransferStatusDesc(String transferStatusDesc) {
		this.transferStatusDesc = transferStatusDesc;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getXmlDetails() {
		return xmlDetails;
	}
	public void setXmlDetails(String xmlDetails) {
		this.xmlDetails = xmlDetails;
	}
	public String getDetailRecurringId() {
		return detailRecurringId;
	}
	public void setDetailRecurringId(String detailRecurringId) {
		this.detailRecurringId = detailRecurringId;
	}
	public String getDetailRefNo() {
		return detailRefNo;
	}
	public void setDetailRefNo(String detailRefNo) {
		this.detailRefNo = detailRefNo;
	}
	public String getDetailStatus() {
		return detailStatus;
	}
	public void setDetailStatus(String detailStatus) {
		this.detailStatus = detailStatus;
	}
	public String getDetailTrxDate() {
		return detailTrxDate;
	}
	public void setDetailTrxDate(String detailTrxDate) {
		this.detailTrxDate = detailTrxDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getNewTransactionDate() {
		return newTransactionDate;
	}
	public void setNewTransactionDate(String newTransactionDate) {
		this.newTransactionDate = newTransactionDate;
	}
	public String getNextTransactionDate() {
		return nextTransactionDate;
	}
	public void setNextTransactionDate(String nextTransactionDate) {
		this.nextTransactionDate = nextTransactionDate;
	}
	public String getNumOfRecurring() {
		return numOfRecurring;
	}
	public void setNumOfRecurring(String numOfRecurring) {
		this.numOfRecurring = numOfRecurring;
	}
	public String getRecurringId() {
		return recurringId;
	}
	public void setRecurringId(String recurringId) {
		this.recurringId = recurringId;
	}
	public String getRecurringType() {
		return recurringType;
	}
	public void setRecurringType(String recurringType) {
		this.recurringType = recurringType;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getTotalRecurring() {
		return totalRecurring;
	}
	public void setTotalRecurring(Integer totalRecurring) {
		this.totalRecurring = totalRecurring;
	}
	public String getUserCif() {
		return userCif;
	}
	public void setUserCif(String userCif) {
		this.userCif = userCif;
	}
	public String getGroupNumber() {
		return groupNumber;
	}
	public void setGroupNumber(String groupNumber) {
		this.groupNumber = groupNumber;
	}
	public String getGroupType() {
		return groupType;
	}
	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

}
