package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

import com.silverlake.mleb.mcb.module.vc.accountManagement.ListAccount;
import com.silverlake.mleb.mcb.module.vc.administration.UserData;
import com.silverlake.mleb.mcb.module.vc.transaction.BeneficiaryList;
import com.silverlake.mleb.mcb.module.vc.transaction.Transaction;


public class ObAuthorizationSessionCache extends ObSessionCache implements Serializable  {
	private List<Transaction> transactionList;
	private ObAuthorizationRequest requestBean;
	private ObTaskListRequest taskListRequestBean;
	
	//Administration Authorization
	private List<UserData> userList;
	private List<ListAccount> accountList;
	private List<BeneficiaryList> beneficiaryList;

	public List<Transaction> getTransactionList() {
		return transactionList;
	}

	public void setTransactionList(List<Transaction> transactionList) {
		this.transactionList = transactionList;
	}

	public ObAuthorizationRequest getRequestBean() {
		return requestBean;
	}

	public void setRequestBean(ObAuthorizationRequest requestBean) {
		this.requestBean = requestBean;
	}

	public ObTaskListRequest getTaskListRequestBean() {
		return taskListRequestBean;
	}

	public void setTaskListRequestBean(ObTaskListRequest taskListRequestBean) {
		this.taskListRequestBean = taskListRequestBean;
	}

	public List<UserData> getUserList() {
		return userList;
	}

	public void setUserList(List<UserData> userList) {
		this.userList = userList;
	}

	public List<ListAccount> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<ListAccount> accountList) {
		this.accountList = accountList;
	}

	public List<BeneficiaryList> getBeneficiaryList() {
		return beneficiaryList;
	}

	public void setBeneficiaryList(List<BeneficiaryList> beneficiaryList) {
		this.beneficiaryList = beneficiaryList;
	}
}
