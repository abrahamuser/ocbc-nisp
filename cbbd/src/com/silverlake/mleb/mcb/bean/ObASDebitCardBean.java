package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ObASDebitCardBean extends ObAccountBean implements Serializable{

	private static final long serialVersionUID = 1L;

	//------ new as debitCard ---------------
	//private XMLGregorianCalendar transactionDate;
	private String transactionDescription; 
	private String transactonRef; 
	private String transactonRef3; 
	private BigDecimal transactionAmount; 
	private int transactionPeriod;
	private int transactionID;
	private String tranFromDate;
	private String tranToDate;
	private String debitSavingAccNo; 
	//---------------------------------------
	
	private String accountIndex;
	
	


	public String getTranFromDate() {
		return tranFromDate;
	}

	public void setTranFromDate(String tranFromDate) {
		this.tranFromDate = tranFromDate;
	}

	public String getTranToDate() {
		return tranToDate;
	}

	public void setTranToDate(String tranToDate) {
		this.tranToDate = tranToDate;
	}

//	public XMLGregorianCalendar getTransactionDate() {
//		return transactionDate;
//	}
//
//	public void setTransactionDate(XMLGregorianCalendar transactionDate) {
//		this.transactionDate = transactionDate;
//	}

	public BigDecimal getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(BigDecimal transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public int getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}


	public int getTransactionPeriod() {
		return transactionPeriod;
	}

	public void setTransactionPeriod(int transactionPeriod) {
		this.transactionPeriod = transactionPeriod;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getTransactionDescription() {
		return transactionDescription;
	}

	public void setTransactionDescription(String transactionDescription) {
		this.transactionDescription = transactionDescription;
	}

	public String getTransactonRef() {
		return transactonRef;
	}

	public void setTransactonRef(String transactonRef) {
		this.transactonRef = transactonRef;
	}

	public String getAccountIndex() {
		return accountIndex;
	}

	public void setAccountIndex(String accountIndex) {
		this.accountIndex = accountIndex;
	}

	public String getDebitSavingAccNo() {
		return debitSavingAccNo;
	}

	public void setDebitSavingAccNo(String debitSavingAccNo) {
		this.debitSavingAccNo = debitSavingAccNo;
	}

	public String getTransactonRef3() {
		return transactonRef3;
	}

	public void setTransactonRef3(String transactonRef3) {
		this.transactonRef3 = transactonRef3;
	}

	

}
