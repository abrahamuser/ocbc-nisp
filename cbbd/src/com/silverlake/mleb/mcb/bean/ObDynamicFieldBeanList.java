package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;


public class ObDynamicFieldBeanList extends ObResponse implements Serializable
{
	private static final long serialVersionUID = 7709279929407878829L;
	
	private String billerId;
	private String billerGroupName;
	private String billerName;
	private String billerCustId;
	private String dynamicMapId;
	private ObDynamicFieldBean obDynamicFieldBean;
	
	public String getBillerId() {
		return billerId;
	}
	public void setBillerId(String billerId) {
		this.billerId = billerId;
	}
	public String getBillerGroupName() {
		return billerGroupName;
	}
	public void setBillerGroupName(String billerGroupName) {
		this.billerGroupName = billerGroupName;
	}
	public String getBillerName() {
		return billerName;
	}
	public void setBillerName(String billerName) {
		this.billerName = billerName;
	}
	public String getBillerCustId() {
		return billerCustId;
	}
	public void setBillerCustId(String billerCustId) {
		this.billerCustId = billerCustId;
	}
	public ObDynamicFieldBean getObDynamicFieldBean() {
		return obDynamicFieldBean;
	}
	public void setObDynamicFieldBean(ObDynamicFieldBean obDynamicFieldBean) {
		this.obDynamicFieldBean = obDynamicFieldBean;
	}
	public String getDynamicMapId() {
		return dynamicMapId;
	}
	public void setDynamicMapId(String dynamicMapId) {
		this.dynamicMapId = dynamicMapId;
	}
	
	

}
