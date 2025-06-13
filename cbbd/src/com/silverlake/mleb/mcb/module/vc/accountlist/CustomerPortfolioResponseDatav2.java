package com.silverlake.mleb.mcb.module.vc.accountlist;

import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;

import java.util.List;

public class CustomerPortfolioResponseDatav2 extends VCResponseData {

	private List<CustomerLoanDatav2> loan_data_list;
	private List<CustomerInvestmentData> wealth_data_list;
	private List<BancassuranceData> bancassurance_data_list;

	public List<CustomerLoanDatav2> getLoan_data_list() {
		return loan_data_list;
	}

	public void setLoan_data_list(List<CustomerLoanDatav2> loan_data_list) {
		this.loan_data_list = loan_data_list;
	}

	public List<CustomerInvestmentData> getWealth_data_list() {
		return wealth_data_list;
	}

	public void setWealth_data_list(List<CustomerInvestmentData> wealth_data_list) {
		this.wealth_data_list = wealth_data_list;
	}

	public List<BancassuranceData> getBancassurance_data_list() {
		return bancassurance_data_list;
	}

	public void setBancassurance_data_list(List<BancassuranceData> bancassurance_data_list) {
		this.bancassurance_data_list = bancassurance_data_list;
	}

}
