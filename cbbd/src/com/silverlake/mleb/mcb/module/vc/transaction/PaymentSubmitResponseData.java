package com.silverlake.mleb.mcb.module.vc.transaction;

import java.math.BigDecimal;
import java.util.Map;

import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;

public class PaymentSubmitResponseData extends VCResponseData{
	private Transaction trx_data;
	private String error_message;

	public Transaction getTrx_data() {
		return trx_data;
	}

	public void setTrx_data(Transaction trx_data) {
		this.trx_data = trx_data;
	}

	public String getError_message() {
		return error_message;
	}

	public void setError_message(String error_message) {
		this.error_message = error_message;
	}
}
