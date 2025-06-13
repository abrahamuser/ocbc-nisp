package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class ObAccountOverviewRequest extends ObRequest implements Serializable
{
	private static final long serialVersionUID = -3822270038236396582L;

	private String accountCcy;
	private List<String> accountTypeList;
	private String isWhatIHave;
	private String accountNo;
	private String accountType;
	private String pageNo;
	private String noDays;
	private String fromDate;
	private String toDate;
	private String prod_cd;
	private String acct_no;
	private String acct_ccy;
	private String periode;

	public String getAccountCcy()
	{
		return accountCcy;
	}

	public void setAccountCcy(String accountCcy)
	{
		this.accountCcy = accountCcy;
	}

	public List<String> getAccountTypeList()
	{
		return accountTypeList;
	}

	public void setAccountTypeList(List<String> accountTypeList)
	{
		this.accountTypeList = accountTypeList;
	}

	public String getIsWhatIHave() {
		return isWhatIHave;
	}

	public void setIsWhatIHave(String isWhatIHave) {
		this.isWhatIHave = isWhatIHave;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getNoDays() {
		return noDays;
	}

	public void setNoDays(String noDays) {
		this.noDays = noDays;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getProd_cd() {
		return prod_cd;
	}

	public void setProd_cd(String prod_cd) {
		this.prod_cd = prod_cd;
	}

	public String getAcct_no() {
		return acct_no;
	}

	public void setAcct_no(String acct_no) {
		this.acct_no = acct_no;
	}

	public String getAcct_ccy() {
		return acct_ccy;
	}

	public void setAcct_ccy(String acct_ccy) {
		this.acct_ccy = acct_ccy;
	}

	public String getPeriode() {
		return periode;
	}

	public void setPeriode(String periode) {
		this.periode = periode;
	}
	
	
	
}