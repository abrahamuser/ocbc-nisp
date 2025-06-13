package com.silverlake.mleb.mcb.bean.accountlist;

import java.io.Serializable;

import com.silverlake.mleb.mcb.bean.ObSessionCache;
import com.silverlake.mleb.mcb.module.vc.accountlist.CustomerAccountResponseData;
import com.silverlake.mleb.mcb.module.vc.accountlist.CustomerPortfolioDownloadResponseData;
import com.silverlake.mleb.mcb.module.vc.accountlist.CustomerPortfolioRequestDatav2;
import com.silverlake.mleb.mcb.module.vc.accountlist.CustomerPortfolioResponseData;
import com.silverlake.mleb.mcb.module.vc.accountlist.CustomerPortfolioResponseDatav2;


public class ObAccountListSessionCache extends ObSessionCache implements Serializable {
		
	private CustomerAccountResponseData customerAccountResponseData;
	private CustomerPortfolioResponseData customerPortfolioResponseData;
	private CustomerPortfolioDownloadResponseData customerPortfolioDownloadResponseData;
	private CustomerPortfolioResponseDatav2 customerPortfolioResponseDatav2;
	
	
	public CustomerPortfolioResponseData getCustomerPortfolioResponseData() {
		return customerPortfolioResponseData;
	}

	public void setCustomerPortfolioResponseData(CustomerPortfolioResponseData customerPortfolioResponseData) {
		this.customerPortfolioResponseData = customerPortfolioResponseData;
	}

	public CustomerAccountResponseData getCustomerAccountResponseData() {
		return customerAccountResponseData;
	}

	public void setCustomerAccountResponseData(CustomerAccountResponseData customerAccountResponseData) {
		this.customerAccountResponseData = customerAccountResponseData;
	}

	public CustomerPortfolioDownloadResponseData getCustomerPortfolioDownloadResponseData() {
		return customerPortfolioDownloadResponseData;
	}

	public void setCustomerPortfolioDownloadResponseData(
			CustomerPortfolioDownloadResponseData customerPortfolioDownloadResponseData) {
		this.customerPortfolioDownloadResponseData = customerPortfolioDownloadResponseData;
	}

	public CustomerPortfolioResponseDatav2 getCustomerPortfolioResponseDatav2() {
		return customerPortfolioResponseDatav2;
	}

	public void setCustomerPortfolioResponseDatav2(CustomerPortfolioResponseDatav2 customerPortfolioResponseDatav2) {
		this.customerPortfolioResponseDatav2 = customerPortfolioResponseDatav2;
	}

	

	
	
}
