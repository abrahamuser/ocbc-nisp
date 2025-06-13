package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class ObDashboardInfoResponse extends ObResponse implements Serializable
{
	private static final long serialVersionUID = 7709279929407878829L;

	private List<ObCorporateAccountOverview> accountList;
	
	private String accountSize;
	
	private String favAccountSize;
	
	private String profileImage;
	
	private String futureTransactionCount;
	
	private List<TaskInfo> taskInfoList;

	public List<ObCorporateAccountOverview> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<ObCorporateAccountOverview> accountList) {
		this.accountList = accountList;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public String getFutureTransactionCount() {
		return futureTransactionCount;
	}

	public void setFutureTransactionCount(String futureTransactionCount) {
		this.futureTransactionCount = futureTransactionCount;
	}

	public List<TaskInfo> getTaskInfoList() {
		return taskInfoList;
	}

	public void setTaskInfoList(List<TaskInfo> taskInfoList) {
		this.taskInfoList = taskInfoList;
	}

	public String getAccountSize() {
		return accountSize;
	}

	public void setAccountSize(String accountSize) {
		this.accountSize = accountSize;
	}

	public String getFavAccountSize() {
		return favAccountSize;
	}

	public void setFavAccountSize(String favAccountSize) {
		this.favAccountSize = favAccountSize;
	}
	
	
	
	
}
