package com.silverlake.mleb.mcb.bean;

public class ObPPScheduledDetailRequest extends ObRequest implements java.io.Serializable {

	private static final long serialVersionUID = 3534767504270541333L;
	
	private String paymentPurchaseId;
	
	private String recurringId;
	
	private String ppScheduledType;
	
	private String isAll;
	
	public String getPaymentPurchaseId() {
		return paymentPurchaseId;
	}

	public void setPaymentPurchaseId(String paymentPurchaseId) {
		this.paymentPurchaseId = paymentPurchaseId;
	}

	public String getRecurringId() {
		return recurringId;
	}

	public void setRecurringId(String recurringId) {
		this.recurringId = recurringId;
	}

	public String getPpScheduledType() {
		return ppScheduledType;
	}

	public void setPpScheduledType(String ppScheduledType) {
		this.ppScheduledType = ppScheduledType;
	}

	public String getIsAll() {
		return isAll;
	}

	public void setIsAll(String isAll) {
		this.isAll = isAll;
	}
	
}
