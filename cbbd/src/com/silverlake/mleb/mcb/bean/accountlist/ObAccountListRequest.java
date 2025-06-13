package com.silverlake.mleb.mcb.bean.accountlist;

import java.io.Serializable;
import java.util.List;

import com.silverlake.mleb.mcb.bean.ObRequestCache;
import com.silverlake.mleb.mcb.module.vc.accountlist.CustomerLoanDatav2;

public class ObAccountListRequest extends ObRequestCache<ObAccountListSessionCache> implements Serializable {


    private String searchCustNo;
    private String searchCustName;
    private String productCode;
    private String pageSize;
    private String pageNo;
    private String customerNumber;
    private Boolean agreementOnly;
    private String periodFrom;
    private String periodTo;
    private String cifNumber;
    private String agreementID;
    private List<CustomerLoanDatav2> customer_loan_data;
    
	public String getSearchCustNo() {
		return searchCustNo;
	}
	public void setSearchCustNo(String searchCustNo) {
		this.searchCustNo = searchCustNo;
	}
	public String getSearchCustName() {
		return searchCustName;
	}
	public void setSearchCustName(String searchCustName) {
		this.searchCustName = searchCustName;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	
	public String getPageNo() {
		return pageNo;
	}
	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}
	public String getCustomerNumber() {
		return customerNumber;
	}
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}
	public Boolean getAgreementOnly() {
		return agreementOnly;
	}
	public void setAgreementOnly(Boolean agreementOnly) {
		this.agreementOnly = agreementOnly;
	}
	public String getPeriodFrom() {
		return periodFrom;
	}
	public void setPeriodFrom(String periodFrom) {
		this.periodFrom = periodFrom;
	}
	public String getPeriodTo() {
		return periodTo;
	}
	public void setPeriodTo(String periodTo) {
		this.periodTo = periodTo;
	}
	public String getCifNumber() {
		return cifNumber;
	}
	public void setCifNumber(String cifNumber) {
		this.cifNumber = cifNumber;
	}
	public String getAgreementID() {
		return agreementID;
	}
	public void setAgreementID(String agreementID) {
		this.agreementID = agreementID;
	}
	public List<CustomerLoanDatav2> getCustomer_loan_data() {
		return customer_loan_data;
	}
	public void setCustomer_loan_data(List<CustomerLoanDatav2> customer_loan_data) {
		this.customer_loan_data = customer_loan_data;
	}
	
	
           
    
    }
