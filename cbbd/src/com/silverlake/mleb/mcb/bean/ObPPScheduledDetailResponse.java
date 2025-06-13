package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObPPScheduledDetailResponse extends ObResponse implements Serializable {

	private static final long serialVersionUID = -514660554544799409L;
	
	private ObPPScheduledBean futurePaymentDetail;
	
	private ObPPScheduledBean recurringPaymentDetail;
	
	private String responseCode;
	
	private String responseMessage;

	public ObPPScheduledBean getFuturePaymentDetail() {
		return futurePaymentDetail;
	}

	public void setFuturePaymentDetail(ObPPScheduledBean futurePaymentDetail) {
		this.futurePaymentDetail = futurePaymentDetail;
	}

	public ObPPScheduledBean getRecurringPaymentDetail() {
		return recurringPaymentDetail;
	}

	public void setRecurringPaymentDetail(ObPPScheduledBean recurringPaymentDetail) {
		this.recurringPaymentDetail = recurringPaymentDetail;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	
}
