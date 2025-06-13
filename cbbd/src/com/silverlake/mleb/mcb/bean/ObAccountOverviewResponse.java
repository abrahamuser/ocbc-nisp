package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.silverlake.mleb.mcb.module.vc.accountManagement.AccountStatement;
import com.silverlake.mleb.mcb.module.vc.accountManagement.Inquiry;

public class ObAccountOverviewResponse extends ObResponse implements Serializable
{
	private static final long serialVersionUID = 7709279929407878829L;

	private List<ObCorporateAccountOverview> accountList;
    private List<AccountStatement> list_statement ;
	
	private String currentPageNo;
	
	private List<ObAccountOverviewBean> whatIHave;

	private List<ObAccountOverviewBean> whatIOwe;

	private List<ObAccountOverviewBean> accountListing;
	
	private ObCompositionBean whatIHaveAccountComposition;
	
	private ObCompositionBean whatIOweAccountComposition;
	
	private ObCompositionBean idrComposition;

	private List<ObInsuranceBean> insuranceBeanList;
	
	private BigDecimal availableBalance; // selected multi currency balance

	private String accountCcy; // selected multi currency
	
	private String unitTrustRetrievingDate;
	
	private List<String> accountAddNewList;
	
	private Inquiry inquiry;
	
	
    private String opening_balance;
    private String closing_balance;
    private String statementFileData;
    private String totalPage;
    private String totalSize;
    private Integer maxYear;
    private Integer minYear;
    private String estatementData;
	
	public List<ObAccountOverviewBean> getWhatIHave()
	{
		return whatIHave;
	}

	public void setWhatIHave(List<ObAccountOverviewBean> whatIHave)
	{
		this.whatIHave = whatIHave;
	}

	public List<ObAccountOverviewBean> getWhatIOwe()
	{
		return whatIOwe;
	}

	public void setWhatIOwe(List<ObAccountOverviewBean> whatIOwe)
	{
		this.whatIOwe = whatIOwe;
	}

	public List<ObAccountOverviewBean> getAccountListing()
	{
		return accountListing;
	}

	public void setAccountListing(List<ObAccountOverviewBean> accountListing)
	{
		this.accountListing = accountListing;
	}

	public ObCompositionBean getWhatIHaveAccountComposition() {
		return whatIHaveAccountComposition;
	}

	public void setWhatIHaveAccountComposition(
			ObCompositionBean whatIHaveAccountComposition) {
		this.whatIHaveAccountComposition = whatIHaveAccountComposition;
	}

	public ObCompositionBean getWhatIOweAccountComposition() {
		return whatIOweAccountComposition;
	}

	public void setWhatIOweAccountComposition(
			ObCompositionBean whatIOweAccountComposition) {
		this.whatIOweAccountComposition = whatIOweAccountComposition;
	}

	public ObCompositionBean getIdrComposition() {
		return idrComposition;
	}

	public void setIdrComposition(ObCompositionBean idrComposition) {
		this.idrComposition = idrComposition;
	}

	public List<ObInsuranceBean> getInsuranceBeanList() {
		return insuranceBeanList;
	}

	public void setInsuranceBeanList(List<ObInsuranceBean> insuranceBeanList) {
		this.insuranceBeanList = insuranceBeanList;
	}

	public BigDecimal getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(BigDecimal availableBalance) {
		this.availableBalance = availableBalance;
	}

	public String getAccountCcy() {
		return accountCcy;
	}

	public void setAccountCcy(String accountCcy) {
		this.accountCcy = accountCcy;
	}

	public String getUnitTrustRetrievingDate() {
		return unitTrustRetrievingDate;
	}

	public void setUnitTrustRetrievingDate(String unitTrustRetrievingDate) {
		this.unitTrustRetrievingDate = unitTrustRetrievingDate;
	}

	public List<String> getAccountAddNewList() {
		return accountAddNewList;
	}

	public void setAccountAddNewList(List<String> accountAddNewList) {
		this.accountAddNewList = accountAddNewList;
	}

	public List<ObCorporateAccountOverview> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<ObCorporateAccountOverview> accountList) {
		this.accountList = accountList;
	}

	public Inquiry getInquiry() {
		return inquiry;
	}

	public void setInquiry(Inquiry inquiry2) {
		this.inquiry = inquiry2;
	}

	public String getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(String currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	public String getOpening_balance() {
		return opening_balance;
	}

	public void setOpening_balance(String opening_balance) {
		this.opening_balance = opening_balance;
	}

	public String getClosing_balance() {
		return closing_balance;
	}

	public void setClosing_balance(String closing_balance) {
		this.closing_balance = closing_balance;
	}

	public List<AccountStatement> getList_statement() {
		return list_statement;
	}

	public void setList_statement(List<AccountStatement> list_statement) {
		this.list_statement = list_statement;
	}

	public String getStatementFileData() {
		return statementFileData;
	}

	public void setStatementFileData(String statementFileData) {
		this.statementFileData = statementFileData;
	}

	public String getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(String totalPage) {
		this.totalPage = totalPage;
	}

	public String getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(String totalSize) {
		this.totalSize = totalSize;
	}
 
	public Integer getMaxYear() {
		return maxYear;
	}

	public void setMaxYear(Integer maxYear) {
		this.maxYear = maxYear;
	}

	public Integer getMinYear() {
		return minYear;
	}

	public void setMinYear(Integer minYear) {
		this.minYear = minYear;
	}

	public String getEstatementData() {
		return estatementData;
	}

	public void setEstatementData(String estatementData) {
		this.estatementData = estatementData;
	}
	
	
}
