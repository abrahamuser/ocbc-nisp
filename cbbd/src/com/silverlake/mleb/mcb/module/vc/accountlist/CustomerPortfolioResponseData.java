package com.silverlake.mleb.mcb.module.vc.accountlist;

import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;

import java.util.List;

public class CustomerPortfolioResponseData extends VCResponseData {
	
	private CustomerLoanData loan_data;
	private List<CustomerInvestmentData> wealth_data_list;

	
	public CustomerLoanData getLoan_data() {
		return loan_data;
	}

	public void setLoan_data(CustomerLoanData loan_data) {
		this.loan_data = loan_data;
	}
	public List<CustomerInvestmentData> getWealth_data_list() {
		return wealth_data_list;
	}

	public void setWealth_data_list(List<CustomerInvestmentData> wealth_data_list) {
		this.wealth_data_list = wealth_data_list;
	}

    
    
    
    }
