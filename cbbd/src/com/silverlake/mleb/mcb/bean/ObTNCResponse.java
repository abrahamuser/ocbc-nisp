package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;


public class ObTNCResponse extends ObResponseCache implements Serializable
{
	
	public List<ObFileBean> getFileList() {
		return fileList;
	}

	public void setFileList(List<ObFileBean> fileList) {
		this.fileList = fileList;
	}

	private static final long serialVersionUID = 4389872192410116666L;

	private String tncContent;
	private String tncType;
	private String tncName;
	private String tncFileType;
	private String ccyCode;
	
	private String footer;
	private String productKey;
	private String locale;
	private List<String> listTnC;
	private List<ObTNCCheckbox> tncCheckboxList;
	
	private List<ObFileBean> fileList;

	public String getTncContent() {
		return tncContent;
	}

	public void setTncContent(String tncContent) {
		this.tncContent = tncContent;
	}

	public String getFooter() {
		return footer;
	}

	public void setFooter(String footer) {
		this.footer = footer;
	}

	public String getProductKey() {
		return productKey;
	}

	public void setProductKey(String productKey) {
		this.productKey = productKey;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public List<ObTNCCheckbox> getTncCheckboxList() {
		return tncCheckboxList;
	}

	public void setTncCheckboxList(List<ObTNCCheckbox> tncCheckboxList) {
		this.tncCheckboxList = tncCheckboxList;
	}

	public List<String> getListTnC() {
		return listTnC;
	}

	public void setListTnC(List<String> listTnC) {
		this.listTnC = listTnC;
	}

	public String getTncType() {
		return tncType;
	}

	public void setTncType(String tncType) {
		this.tncType = tncType;
	}

	public String getTncName() {
		return tncName;
	}

	public void setTncName(String tncName) {
		this.tncName = tncName;
	}

	public String getTncFileType() {
		return tncFileType;
	}

	public void setTncFileType(String tncFileType) {
		this.tncFileType = tncFileType;
	}

	public String getCcyCode() {
		return ccyCode;
	}

	public void setCcyCode(String ccyCode) {
		this.ccyCode = ccyCode;
	}
	
}
