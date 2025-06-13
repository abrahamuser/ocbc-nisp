package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class ObAccountOverviewCompositionRequest extends ObRequest implements Serializable
{

	private static final long serialVersionUID = -805567559209184882L;
	
	private String accountCcy;
	private List<String> accountTypeList;
	private String isWhatIHave;
	private String accountNo;
	private ObAccountOverviewResponse obAccountOverviewResponse;

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

	public ObAccountOverviewResponse getObAccountOverviewResponse() {
		return obAccountOverviewResponse;
	}

	public void setObAccountOverviewResponse(
			ObAccountOverviewResponse obAccountOverviewResponse) {
		this.obAccountOverviewResponse = obAccountOverviewResponse;
	}

}