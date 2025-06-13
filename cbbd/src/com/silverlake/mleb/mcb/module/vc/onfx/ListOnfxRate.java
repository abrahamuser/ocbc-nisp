package com.silverlake.mleb.mcb.module.vc.onfx;

import java.io.Serializable;
import java.math.BigDecimal;

public class ListOnfxRate implements Serializable {
	private final static long serialVersionUID = 5970650356662603347L;
    //v1
	private String ccy_code;
	private String tt_buy_rate;
	private String tt_sell_rate;
	
	//v2
	private String based_ccy;
	private String quote_ccy;
	private String ccy_pair;
	private BigDecimal ask_rate;
	private BigDecimal ask_spread;
	private BigDecimal bid_rate;
	private BigDecimal bid_spread;
	private BigDecimal bank_buy_rate;
	private BigDecimal bank_sell_rate;

	public String getCcy_code() {
		return ccy_code;
	}

	public void setCcy_code(String ccy_code) {
		this.ccy_code = ccy_code;
	}

	public String getTt_buy_rate() {
		return tt_buy_rate;
	}

	public void setTt_buy_rate(String tt_buy_rate) {
		this.tt_buy_rate = tt_buy_rate;
	}

	public String getTt_sell_rate() {
		return tt_sell_rate;
	}

	public void setTt_sell_rate(String tt_sell_rate) {
		this.tt_sell_rate = tt_sell_rate;
	}

	public String getQuote_ccy() {
		return quote_ccy;
	}

	public String getCcy_pair() {
		return ccy_pair;
	}

	public BigDecimal getAsk_rate() {
		return ask_rate;
	}

	public BigDecimal getAsk_spread() {
		return ask_spread;
	}

	public BigDecimal getBid_rate() {
		return bid_rate;
	}

	public BigDecimal getBid_spread() {
		return bid_spread;
	}

	public BigDecimal getBank_buy_rate() {
		return bank_buy_rate;
	}

	public BigDecimal getBank_sell_rate() {
		return bank_sell_rate;
	}

	public void setQuote_ccy(String quote_ccy) {
		this.quote_ccy = quote_ccy;
	}

	public void setCcy_pair(String ccy_pair) {
		this.ccy_pair = ccy_pair;
	}

	public void setAsk_rate(BigDecimal ask_rate) {
		this.ask_rate = ask_rate;
	}

	public void setAsk_spread(BigDecimal ask_spread) {
		this.ask_spread = ask_spread;
	}

	public void setBid_rate(BigDecimal bid_rate) {
		this.bid_rate = bid_rate;
	}

	public void setBid_spread(BigDecimal bid_spread) {
		this.bid_spread = bid_spread;
	}

	public void setBank_buy_rate(BigDecimal bank_buy_rate) {
		this.bank_buy_rate = bank_buy_rate;
	}

	public void setBank_sell_rate(BigDecimal bank_sell_rate) {
		this.bank_sell_rate = bank_sell_rate;
	}

	public String getBased_ccy() {
		return based_ccy;
	}

	public void setBased_ccy(String based_ccy) {
		this.based_ccy = based_ccy;
	}
}