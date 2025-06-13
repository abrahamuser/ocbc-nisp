package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;


public class ObTNCRequest extends ObRequestCache<ObTNCCache> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private ObTNCBean obTNCBean;
	private String tncType;
	private String refNo;
	private Boolean isLocal;
	private Boolean load;
	private String ccyCode;

	public Boolean getIsLocal() {
		return isLocal;
	}

	public void setIsLocal(Boolean isLocal) {
		this.isLocal = isLocal;
	}

	public ObTNCBean getObTNCBean() {
		return obTNCBean;
	}

	public void setObTNCBean(ObTNCBean obTNCBean) {
		this.obTNCBean = obTNCBean;
	}

	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	public String getTncType() {
		return tncType;
	}

	public void setTncType(String tncType) {
		this.tncType = tncType;
	}

	public String getCcyCode() {
		return ccyCode;
	}

	public void setCcyCode(String ccyCode) {
		this.ccyCode = ccyCode;
	}
    


}