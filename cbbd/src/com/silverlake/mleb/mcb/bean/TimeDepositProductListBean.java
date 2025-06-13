package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class TimeDepositProductListBean extends ObResponse implements Serializable
{
	
	private static final long serialVersionUID = 4611986287508313920L;
	
	private Integer groupSeq;
	private String groupType;
	private String productName;
	private String productDesc;
	private String productCode;
	private Integer sequence;
	public Integer getGroupSeq() {
		return groupSeq;
	}
	public void setGroupSeq(Integer groupSeq) {
		this.groupSeq = groupSeq;
	}
	public String getGroupType() {
		return groupType;
	}
	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	
	
}
