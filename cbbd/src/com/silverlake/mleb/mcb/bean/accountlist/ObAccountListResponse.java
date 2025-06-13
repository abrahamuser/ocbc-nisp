package com.silverlake.mleb.mcb.bean.accountlist;

import com.silverlake.mleb.mcb.bean.ObCustomerAccountDataListBean;
import com.silverlake.mleb.mcb.bean.ObCustomerFacilityDataListBean;
import com.silverlake.mleb.mcb.bean.ObCustomerInvestmentDataBean;
import com.silverlake.mleb.mcb.bean.ObResponseCache;
import com.silverlake.mleb.mcb.module.vc.accountlist.BancassuranceData;
import com.silverlake.mleb.mcb.module.vc.accountlist.CustomerFacilityDatav2;
import com.silverlake.mleb.mcb.module.vc.accountlist.CustomerInvestmentData;
import com.silverlake.mleb.mcb.module.vc.accountlist.CustomerLoanDatav2;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class ObAccountListResponse extends ObResponseCache implements Serializable {
	
	private List<ObCustomerAccountDataListBean> customerAccountList;
	private Integer totalRows;
	private Integer minYear;
	private Integer maxYear;
	private String groupCode;
	private String groupName;
	private String loanAcctName;
	private String loanCustomerNumber;
	private BigDecimal amountLimit;
	private BigDecimal outstandingAmount;
	private BigDecimal availableAmount;
	private String currencyCode;
	private String lastUpdate;
    List<ObCustomerFacilityDataListBean> facilityList;
	private List<ObCustomerInvestmentDataBean> investmentData;
	private String documentFileData;
	
	private List<CustomerLoanDatav2> loan_data_list;
	private List<CustomerInvestmentData> wealth_data_list;
	
	private List<CustomerFacilityDatav2> facility_list;
	private List<BancassuranceData> bancassurance_data_list;

	public List<ObCustomerAccountDataListBean> getCustomerAccountList() {
		return customerAccountList;
	}

	public void setCustomerAccountList(List<ObCustomerAccountDataListBean> customerAccountList) {
		this.customerAccountList = customerAccountList;
	}

	public Integer getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(Integer totalRows) {
		this.totalRows = totalRows;
	}
	    
	public Integer getMinYear() {
		return minYear;
	}

	public void setMinYear(Integer minYear) {
		this.minYear = minYear;
	}

	public Integer getMaxYear() {
		return maxYear;
	}

	public void setMaxYear(Integer maxYear) {
		this.maxYear = maxYear;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getLoanAcctName() {
		return loanAcctName;
	}

	public void setLoanAcctName(String loanAcctName) {
		this.loanAcctName = loanAcctName;
	}

	public String getLoanCustomerNumber() {
		return loanCustomerNumber;
	}

	public void setLoanCustomerNumber(String loanCustomerNumber) {
		this.loanCustomerNumber = loanCustomerNumber;
	}

	public BigDecimal getAmountLimit() {
		return amountLimit;
	}

	public void setAmountLimit(BigDecimal amountLimit) {
		this.amountLimit = amountLimit;
	}

	public BigDecimal getOutstandingAmount() {
		return outstandingAmount;
	}

	public void setOutstandingAmount(BigDecimal outstandingAmount) {
		this.outstandingAmount = outstandingAmount;
	}

	public BigDecimal getAvailableAmount() {
		return availableAmount;
	}

	public void setAvailableAmount(BigDecimal availableAmount) {
		this.availableAmount = availableAmount;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public List<ObCustomerFacilityDataListBean> getFacilityList() {
		return facilityList;
	}

	public void setFacilityList(List<ObCustomerFacilityDataListBean> facilityList) {
		this.facilityList = facilityList;
	}

	public List<ObCustomerInvestmentDataBean> getInvestmentData() {
		return investmentData;
	}

	public void setInvestmentData(List<ObCustomerInvestmentDataBean> investmentData) {
		this.investmentData = investmentData;
	}

	public String getDocumentFileData() {
		return documentFileData;
	}

	public void setDocumentFileData(String documentFileData) {
		this.documentFileData = documentFileData;
	}

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

	public List<CustomerFacilityDatav2> getFacility_list() {
		return facility_list;
	}

	public void setFacility_list(List<CustomerFacilityDatav2> facility_list) {
		this.facility_list = facility_list;
	}

	public List<BancassuranceData> getBancassurance_data_list() {
		return bancassurance_data_list;
	}

	public void setBancassurance_data_list(List<BancassuranceData> bancassurance_data_list) {
		this.bancassurance_data_list = bancassurance_data_list;
	}

	    
}
