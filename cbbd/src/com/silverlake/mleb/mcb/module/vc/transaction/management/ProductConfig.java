package com.silverlake.mleb.mcb.module.vc.transaction.management;

import java.io.Serializable;
import java.util.List;

public class ProductConfig implements Serializable{
	private GeneralConfig general_config;
	private COTConfig cot_config;
	private TransactionLimitConfig trx_limit_config;
	private String prod_cd;
	private List<COTConfig> list_cot_ccy;
	
	public GeneralConfig getGeneral_config() {
		return general_config;
	}
	public COTConfig getCot_config() {
		return cot_config;
	}
	public TransactionLimitConfig getTrx_limit_config() {
		return trx_limit_config;
	}
	public String getProd_cd() {
		return prod_cd;
	}
	public void setGeneral_config(GeneralConfig general_config) {
		this.general_config = general_config;
	}
	public void setCot_config(COTConfig cot_config) {
		this.cot_config = cot_config;
	}
	public void setTrx_limit_config(TransactionLimitConfig trx_limit_config) {
		this.trx_limit_config = trx_limit_config;
	}
	public void setProd_cd(String prod_cd) {
		this.prod_cd = prod_cd;
	}
	public List<COTConfig> getList_cot_ccy() {
		return list_cot_ccy;
	}
	public void setList_cot_ccy(List<COTConfig> list_cot_ccy) {
		this.list_cot_ccy = list_cot_ccy;
	}
	
}

