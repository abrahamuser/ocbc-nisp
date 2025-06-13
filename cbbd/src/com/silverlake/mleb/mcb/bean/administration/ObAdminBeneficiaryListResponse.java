package com.silverlake.mleb.mcb.bean.administration;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.silverlake.mleb.mcb.bean.ObBeneAccountBean;
import com.silverlake.mleb.mcb.bean.ObResponseCache;
import com.silverlake.mleb.mcb.bean.ObTransactionBeneficiaryBean;

public class ObAdminBeneficiaryListResponse extends ObResponseCache implements Serializable{
	private List<ObBeneAccountBean> beneficiaryList;
	private Integer totalRecords;
	
	public List<ObBeneAccountBean> getBeneficiaryList() {
		return beneficiaryList;
	}
	public Integer getTotalRecords() {
		return totalRecords;
	}
	public void setBeneficiaryList(List<ObBeneAccountBean> beneficiaryList) {
		this.beneficiaryList = beneficiaryList;
	}
	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}	
}
