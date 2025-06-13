package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class ObDashboardInfoRequest extends ObRequest implements Serializable
{
	private static final long serialVersionUID = -3822270038236396582L;

	private String accountCcy;
	private List<String> accountTypeList;
	private String isWhatIHave;
	private String accountNo;
	private String pageNo;
	private String tncAction;

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

	public String getTncAction() {
		return tncAction;
	}

	public void setTncAction(String tncAction) {
		this.tncAction = tncAction;
	}
	
	
}