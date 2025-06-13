package com.silverlake.mleb.mcb.module.vc.exchangeRate;

import java.util.List;

import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;

public class ResponseData extends VCResponseData {

	private List<ExchangeRate> list_fxrate;

	public List<ExchangeRate> getList_fxrate() {
		return list_fxrate;
	}

	public void setList_fxrate(List<ExchangeRate> list_fxrate) {
		this.list_fxrate = list_fxrate;
	}
}
