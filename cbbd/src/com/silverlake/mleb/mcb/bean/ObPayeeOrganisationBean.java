package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;


public class ObPayeeOrganisationBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer inputAmountBit;

    private Integer flowType;

    private String billerType;

    private Integer ccBit;

    private String batchId;

    private String soaType;

    private String billerName;

    private Integer version;

    private String billerCd;

    private Integer sequence;

    private String billerId;

    private String futureFlag;

    private String billerGrpCd;

    private String recurringFlag;

    private Integer casaBit;

    private String systemGroup;
    
    private String billerGrpName;

    private String billerGrpId;

	public Integer getInputAmountBit() {
		return inputAmountBit;
	}

	public void setInputAmountBit(Integer inputAmountBit) {
		this.inputAmountBit = inputAmountBit;
	}

	public Integer getFlowType() {
		return flowType;
	}

	public void setFlowType(Integer flowType) {
		this.flowType = flowType;
	}

	public String getBillerType() {
		return billerType;
	}

	public void setBillerType(String billerType) {
		this.billerType = billerType;
	}

	public Integer getCcBit() {
		return ccBit;
	}

	public void setCcBit(Integer ccBit) {
		this.ccBit = ccBit;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getSoaType() {
		return soaType;
	}

	public void setSoaType(String soaType) {
		this.soaType = soaType;
	}

	public String getBillerName() {
		return billerName;
	}

	public void setBillerName(String billerName) {
		this.billerName = billerName;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getBillerCd() {
		return billerCd;
	}

	public void setBillerCd(String billerCd) {
		this.billerCd = billerCd;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public String getBillerId() {
		return billerId;
	}

	public void setBillerId(String billerId) {
		this.billerId = billerId;
	}

	public String getFutureFlag() {
		return futureFlag;
	}

	public void setFutureFlag(String futureFlag) {
		this.futureFlag = futureFlag;
	}

	public String getBillerGrpCd() {
		return billerGrpCd;
	}

	public void setBillerGrpCd(String billerGrpCd) {
		this.billerGrpCd = billerGrpCd;
	}

	public String getRecurringFlag() {
		return recurringFlag;
	}

	public void setRecurringFlag(String recurringFlag) {
		this.recurringFlag = recurringFlag;
	}

	public Integer getCasaBit() {
		return casaBit;
	}

	public void setCasaBit(Integer casaBit) {
		this.casaBit = casaBit;
	}

	public String getSystemGroup() {
		return systemGroup;
	}

	public void setSystemGroup(String systemGroup) {
		this.systemGroup = systemGroup;
	}

	public String getBillerGrpName() {
		return billerGrpName;
	}

	public void setBillerGrpName(String billerGrpName) {
		this.billerGrpName = billerGrpName;
	}

	public String getBillerGrpId() {
		return billerGrpId;
	}

	public void setBillerGrpId(String billerGrpId) {
		this.billerGrpId = billerGrpId;
	}

}
