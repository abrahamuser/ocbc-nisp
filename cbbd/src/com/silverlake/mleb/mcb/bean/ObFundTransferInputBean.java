package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ObFundTransferInputBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	private String accFrom;
	private String accTo;
	private String amount;
	private String description;
	private String transferDate;
	private String transferPurpose;
	private String recurringType;
	private String numOfRecurring;
	private String amountCcy;
	private String recurringDate;
	private String recurringFlag;
	private String tranxID;
	
	public String getAccFrom() {
		return accFrom;
	}
	public void setAccFrom(String accFrom) {
		this.accFrom = accFrom;
	}
	public String getAccTo() {
		return accTo;
	}
	public void setAccTo(String accTo) {
		this.accTo = accTo;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTransferDate() {
		return transferDate;
	}
	public void setTransferDate(String transferDate) {
		this.transferDate = transferDate;
	}
	public String getTransferPurpose() {
		return transferPurpose;
	}
	public void setTransferPurpose(String transferPurpose) {
		this.transferPurpose = transferPurpose;
	}
	public String getRecurringType() {
		return recurringType;
	}
	public void setRecurringType(String recurringType) {
		this.recurringType = recurringType;
	}
	public String getNumOfRecurring() {
		return numOfRecurring;
	}
	public void setNumOfRecurring(String numOfRecurring) {
		this.numOfRecurring = numOfRecurring;
	}
	public String getAmountCcy() {
		return amountCcy;
	}
	public void setAmountCcy(String amountCcy) {
		this.amountCcy = amountCcy;
	}
	public String getRecurringDate() {
		return recurringDate;
	}
	public void setRecurringDate(String recurringDate) {
		this.recurringDate = recurringDate;
	}
	public String getRecurringFlag() {
		return recurringFlag;
	}
	public void setRecurringFlag(String recurringFlag) {
		this.recurringFlag = recurringFlag;
	}
	public String getTranxID() {
		return tranxID;
	}
	public void setTranxID(String tranxID) {
		this.tranxID = tranxID;
	}
	
	
	
	
	
}
