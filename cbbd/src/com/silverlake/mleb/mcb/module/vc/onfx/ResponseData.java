package com.silverlake.mleb.mcb.module.vc.onfx;


import java.util.List;

import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;
import com.silverlake.mleb.mcb.module.vc.transaction.Transaction;

public class ResponseData extends VCResponseData{
	private List<ListOnfxRate> list_onfx_rate = null;
	private List<ListOnfxRate> list_onfx_rate_v2 = null;
	private Transaction trx_data;
	private String error_message;
	private String error_message_en;
	private String error_message_cn;
	private String error_message_id;
	private List<String> list_onfx_ccy;

	public List<ListOnfxRate> getList_onfx_rate() {
		return list_onfx_rate;
	}

	public void setList_onfx_rate(List<ListOnfxRate> list_onfx_rate) {
		this.list_onfx_rate = list_onfx_rate;
	}

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

	public List<ListOnfxRate> getList_onfx_rate_v2() {
		return list_onfx_rate_v2;
	}

	public void setList_onfx_rate_v2(List<ListOnfxRate> list_onfx_rate_v2) {
		this.list_onfx_rate_v2 = list_onfx_rate_v2;
	}

	public List<String> getList_onfx_ccy() {
		return list_onfx_ccy;
	}

	public void setList_onfx_ccy(List<String> list_onfx_ccy) {
		this.list_onfx_ccy = list_onfx_ccy;
	}

	public String getError_message_en() {
		return error_message_en;
	}

	public String getError_message_id() {
		return error_message_id;
	}

	public void setError_message_en(String error_message_en) {
		this.error_message_en = error_message_en;
	}

	public void setError_message_id(String error_message_id) {
		this.error_message_id = error_message_id;
	}

	public String getError_message_cn() {
		return error_message_cn;
	}

	public void setError_message_cn(String error_message_cn) {
		this.error_message_cn = error_message_cn;
	}
}
