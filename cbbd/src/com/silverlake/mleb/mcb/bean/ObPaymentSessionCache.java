package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

import com.silverlake.mleb.mcb.module.vc.transaction.BillPaymentInquiryRequestData;
import com.silverlake.mleb.mcb.module.vc.transaction.BillPaymentInquiryResponseData;
import com.silverlake.mleb.mcb.module.vc.transaction.PayeeResponseData;


public class ObPaymentSessionCache extends ObSessionCache implements Serializable  {
	private PayeeResponseData payeeResponseData;
	private BillPaymentInquiryResponseData BillPaymentInquiryResponseData;
	private BillPaymentInquiryRequestData BillPaymentInquiryRequestData;
	private Integer flowType;

	public PayeeResponseData getPayeeResponseData() {
		return payeeResponseData;
	}

	public void setPayeeResponseData(PayeeResponseData payeeResponseData) {
		this.payeeResponseData = payeeResponseData;
	}

	public BillPaymentInquiryResponseData getBillPaymentInquiryResponseData() {
		return BillPaymentInquiryResponseData;
	}

	public void setBillPaymentInquiryResponseData(BillPaymentInquiryResponseData billPaymentInquiryResponseData) {
		BillPaymentInquiryResponseData = billPaymentInquiryResponseData;
	}

	public BillPaymentInquiryRequestData getBillPaymentInquiryRequestData() {
		return BillPaymentInquiryRequestData;
	}

	public void setBillPaymentInquiryRequestData(BillPaymentInquiryRequestData billPaymentInquiryRequestData) {
		BillPaymentInquiryRequestData = billPaymentInquiryRequestData;
	}

	public Integer getFlowType() {
		return flowType;
	}

	public void setFlowType(Integer flowType) {
		this.flowType = flowType;
	}
	
}
