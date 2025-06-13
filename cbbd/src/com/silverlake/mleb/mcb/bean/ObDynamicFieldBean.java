package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


public class ObDynamicFieldBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String label; 
	private String refno;
    private String frmacct;
    private String receiptno;
    private String billernm;
    private String custbillid;
	private Map<String, String> dynamicFieldMap;
	private ListMapPojo listDynamicField;
	private String footer;
	private List<String> listHeader;
	private List<String> listFooter;
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getRefno() {
		return refno;
	}
	public void setRefno(String refno) {
		this.refno = refno;
	}
	public String getFrmacct() {
		return frmacct;
	}
	public void setFrmacct(String frmacct) {
		this.frmacct = frmacct;
	}
	public String getReceiptno() {
		return receiptno;
	}
	public void setReceiptno(String receiptno) {
		this.receiptno = receiptno;
	}
	public String getBillernm() {
		return billernm;
	}
	public void setBillernm(String billernm) {
		this.billernm = billernm;
	}
	public String getCustbillid() {
		return custbillid;
	}
	public void setCustbillid(String custbillid) {
		this.custbillid = custbillid;
	}
	public Map<String, String> getDynamicFieldMap() {
		return dynamicFieldMap;
	}
	public void setDynamicFieldMap(Map<String, String> dynamicFieldMap) {
		this.dynamicFieldMap = dynamicFieldMap;
	}
	public ListMapPojo getListDynamicField() {
		return listDynamicField;
	}
	public void setListDynamicField(ListMapPojo listDynamicField) {
		this.listDynamicField = listDynamicField;
	}
	public String getFooter() {
		return footer;
	}
	public void setFooter(String footer) {
		this.footer = footer;
	}
	public List<String> getListHeader() {
		return listHeader;
	}
	public void setListHeader(List<String> listHeader) {
		this.listHeader = listHeader;
	}
	public List<String> getListFooter() {
		return listFooter;
	}
	public void setListFooter(List<String> listFooter) {
		this.listFooter = listFooter;
	} 
	
}
