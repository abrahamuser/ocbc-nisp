package com.silverlake.mleb.mcb.module.vc.others;

import java.util.List;

import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;

public class PaymentPurposeResponseData extends VCResponseData {
	private List<PaymentPurposeList> data;

	public List<PaymentPurposeList> getData() {
		return data;
	}

	public void setData(List<PaymentPurposeList> data) {
		this.data = data;
	}
    
}

