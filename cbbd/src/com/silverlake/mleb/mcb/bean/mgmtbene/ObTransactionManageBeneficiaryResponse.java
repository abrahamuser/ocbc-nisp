package com.silverlake.mleb.mcb.bean.mgmtbene;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.silverlake.mleb.mcb.bean.ObBeneAccountBean;
import com.silverlake.mleb.mcb.bean.ObBiFastBean;
import com.silverlake.mleb.mcb.bean.ObResponseCache;
import com.silverlake.mleb.mcb.bean.ObTransactionBeneficiaryBean;

public class ObTransactionManageBeneficiaryResponse extends ObResponseCache implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private ObBeneAccountBean beneDetails; 
	private Map<String, String> shareBeneficiaryList;
	private List<ObBiFastBean> proxyTypeList;

	public ObBeneAccountBean getBeneDetails() {
		return beneDetails;
	}

	public void setBeneDetails(ObBeneAccountBean beneDetails) {
		this.beneDetails = beneDetails;
	}

	public Map<String, String> getShareBeneficiaryList() {
		return shareBeneficiaryList;
	}

	public void setShareBeneficiaryList(Map<String, String> shareBeneficiaryList) {
		this.shareBeneficiaryList = shareBeneficiaryList;
	}

	public List<ObBiFastBean> getProxyTypeList() {
		return proxyTypeList;
	}

	public void setProxyTypeList(List<ObBiFastBean> proxyTypeList) {
		this.proxyTypeList = proxyTypeList;
	}
	
}
