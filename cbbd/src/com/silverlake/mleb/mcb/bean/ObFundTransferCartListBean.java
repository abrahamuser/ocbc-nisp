package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ObFundTransferCartListBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String amount;
	private String amountCcy;
	private String transactionUUID;
	
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
	public String getTransactionUUID() {
		return transactionUUID;
	}
	public void setTransactionUUID(String transactionUUID) {
		this.transactionUUID = transactionUUID;
	}
	
	
	
}
