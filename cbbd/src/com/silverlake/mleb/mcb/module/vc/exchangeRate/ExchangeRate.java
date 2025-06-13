package com.silverlake.mleb.mcb.module.vc.exchangeRate;

import java.io.Serializable;
import java.math.BigDecimal;

public class ExchangeRate implements Serializable {

	private String ccy_code;
	private String period;
	private BigDecimal bank_buy_rate;
	private BigDecimal bank_sell_rate;
	private BigDecimal tt_buy_rate;
	private BigDecimal tt_sell_rate;
	
	public String getCcy_code() {
		return ccy_code;
	}
	public void setCcy_code(String ccy_code) {
		this.ccy_code = ccy_code;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public BigDecimal getBank_buy_rate() {
		return bank_buy_rate;
	}
	public void setBank_buy_rate(BigDecimal bank_buy_rate) {
		this.bank_buy_rate = bank_buy_rate;
	}
	public BigDecimal getBank_sell_rate() {
		return bank_sell_rate;
	}
	public void setBank_sell_rate(BigDecimal bank_sell_rate) {
		this.bank_sell_rate = bank_sell_rate;
	}
	public BigDecimal getTt_buy_rate() {
		return tt_buy_rate;
	}
	public void setTt_buy_rate(BigDecimal tt_buy_rate) {
		this.tt_buy_rate = tt_buy_rate;
	}
	public BigDecimal getTt_sell_rate() {
		return tt_sell_rate;
	}
	public void setTt_sell_rate(BigDecimal tt_sell_rate) {
		this.tt_sell_rate = tt_sell_rate;
	}	
}