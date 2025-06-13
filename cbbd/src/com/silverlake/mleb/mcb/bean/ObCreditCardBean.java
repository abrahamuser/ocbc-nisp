package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

//import com.fuzion.ws.account.endpoint.PackageVO;

public class ObCreditCardBean extends ObAccountBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String accountIndex;
	private String principalSuppIndicator;
	private String payDueDt;
	private String lstPayDt;
	private String expDt;
	private String crdStatus;
	private String stmtDt;
	private String stmtDueDt;
	private String payDt;
//	private XMLGregorianCalendar transDt;
	private String accPayCode; 
	private String references;
	private String receipientReferences; 
	private String otherInfo; 
	private String senderName; 
	
	private BigDecimal lstPayAmt;
	private BigDecimal lstStmtBal;
	private BigDecimal minPayAmt;
	private BigDecimal outstdBal;
	private BigDecimal stmtBal;
	private BigDecimal combCrLim;
	private BigDecimal combAvlLim;
	private BigDecimal payAmt;
	private BigDecimal minBal;
	private BigDecimal balAftTrans;
	private String cardExpDt;
	
	
//	private XMLGregorianCalendar transactionDate; 
	private BigDecimal creditAmount; 
	private BigDecimal debitAmount;
	private String postingDate; 
	private String transactionDescription;
	
	private String nextPackageEffDate;
	
	private List<CrCrdLifeStyleBean> currentLifeStyleOptions;
	private List<CrCrdLifeStyleBean> nextLifeStyleOptions;

		
	public List<CrCrdLifeStyleBean> getCurrentLifeStyleOptions() {
		return currentLifeStyleOptions;
	}
	public void setCurrentLifeStyleOptions(
			List<CrCrdLifeStyleBean> currentLifeStyleOptions) {
		this.currentLifeStyleOptions = currentLifeStyleOptions;
	}
	public List<CrCrdLifeStyleBean> getNextLifeStyleOptions() {
		return nextLifeStyleOptions;
	}
	public void setNextLifeStyleOptions(
			List<CrCrdLifeStyleBean> nextLifeStyleOptions) {
		this.nextLifeStyleOptions = nextLifeStyleOptions;
	}
	//	public XMLGregorianCalendar getTransactionDate() {
//		return transactionDate;
//	}
//	public void setTransactionDate(XMLGregorianCalendar transactionDate) {
//		this.transactionDate = transactionDate;
//	}
	public BigDecimal getCreditAmount() {
		return creditAmount;
	}
	public void setCreditAmount(BigDecimal creditAmount) {
		this.creditAmount = creditAmount;
	}
	public BigDecimal getDebitAmount() {
		return debitAmount;
	}
	public void setDebitAmount(BigDecimal debitAmount) {
		this.debitAmount = debitAmount;
	}
	public String getPostingDate() {
		return postingDate;
	}
	public void setPostingDate(String postingDate) {
		this.postingDate = postingDate;
	}
	public String getTransactionDescription() {
		return transactionDescription;
	}
	public void setTransactionDescription(String transactionDescription) {
		this.transactionDescription = transactionDescription;
	}
	public String getPrincipalSuppIndicator() {
		return principalSuppIndicator;
	}
	public void setPrincipalSuppIndicator(String principalSuppIndicator) {
		this.principalSuppIndicator = principalSuppIndicator;
	}
	public BigDecimal getLstPayAmt() {
		return lstPayAmt;
	}
	public void setLstPayAmt(BigDecimal lstPayAmt) {
		this.lstPayAmt = lstPayAmt;
	}
	public String getCrdStatus() {
		return crdStatus;
	}
	public void setCrdStatus(String crdStatus) {
		this.crdStatus = crdStatus;
	}
	public String getAccPayCode() {
		return accPayCode;
	}
	public void setAccPayCode(String accPayCode) {
		this.accPayCode = accPayCode;
	}
	public BigDecimal getLstStmtBal() {
		return lstStmtBal;
	}
	public void setLstStmtBal(BigDecimal lstStmtBal) {
		this.lstStmtBal = lstStmtBal;
	}
	public BigDecimal getMinPayAmt() {
		return minPayAmt;
	}
	public void setMinPayAmt(BigDecimal minPayAmt) {
		this.minPayAmt = minPayAmt;
	}
	public BigDecimal getOutstdBal() {
		return outstdBal;
	}
	public void setOutstdBal(BigDecimal outstdBal) {
		this.outstdBal = outstdBal;
	}
	public BigDecimal getStmtBal() {
		return stmtBal;
	}
	public void setStmtBal(BigDecimal stmtBal) {
		this.stmtBal = stmtBal;
	}
	public BigDecimal getCombCrLim() {
		return combCrLim;
	}
	public void setCombCrLim(BigDecimal combCrLim) {
		this.combCrLim = combCrLim;
	}
	public BigDecimal getCombAvlLim() {
		return combAvlLim;
	}
	public void setCombAvlLim(BigDecimal combAvlLim) {
		this.combAvlLim = combAvlLim;
	}
	public BigDecimal getPayAmt() {
		return payAmt;
	}
	public void setPayAmt(BigDecimal payAmt) {
		this.payAmt = payAmt;
	}
	public BigDecimal getMinBal() {
		return minBal;
	}
	public void setMinBal(BigDecimal minBal) {
		this.minBal = minBal;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAccountIndex() {
		return accountIndex;
	}
	public void setAccountIndex(String accountIndex) {
		this.accountIndex = accountIndex;
	}

	public String getCardExpDt() {
		return cardExpDt;
	}
	public void setCardExpDt(String cardExpDt) {
		this.cardExpDt = cardExpDt;
	}
	public String getPayDueDt() {
		return payDueDt;
	}
	public void setPayDueDt(String payDueDt) {
		this.payDueDt = payDueDt;
	}
	public String getLstPayDt() {
		return lstPayDt;
	}
	public void setLstPayDt(String lstPayDt) {
		this.lstPayDt = lstPayDt;
	}
	public String getExpDt() {
		return expDt;
	}
	public void setExpDt(String expDt) {
		this.expDt = expDt;
	}
	public String getStmtDt() {
		return stmtDt;
	}
	public void setStmtDt(String stmtDt) {
		this.stmtDt = stmtDt;
	}
	public String getStmtDueDt() {
		return stmtDueDt;
	}
	public void setStmtDueDt(String stmtDueDt) {
		this.stmtDueDt = stmtDueDt;
	}
	public String getPayDt() {
		return payDt;
	}
	public void setPayDt(String payDt) {
		this.payDt = payDt;
	}
//	public XMLGregorianCalendar getTransDt() {
//		return transDt;
//	}
//	public void setTransDt(XMLGregorianCalendar transDt) {
//		this.transDt = transDt;
//	}
	public BigDecimal getBalAftTrans() {
		return balAftTrans;
	}
	public void setBalAftTrans(BigDecimal balAftTrans) {
		this.balAftTrans = balAftTrans;
	}
	public String getNextPackageEffDate() {
		return nextPackageEffDate;
	}
	public void setNextPackageEffDate(String nextPackageEffDate) {
		this.nextPackageEffDate = nextPackageEffDate;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getReferences() {
		return references;
	}
	public void setReferences(String references) {
		this.references = references;
	}
	public String getOtherInfo() {
		return otherInfo;
	}
	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}
	public String getReceipientReferences() {
		return receipientReferences;
	}
	public void setReceipientReferences(String receipientReferences) {
		this.receipientReferences = receipientReferences;
	}


	
	
}
