package com.silverlake.mleb.mcb.bean;


import java.io.Serializable;
import java.math.BigDecimal;

public class ObTransactionSummaryRequest extends ObRequestCache<ObSessionCache> implements Serializable{
	private String sourceTrx;
	private String pymtMasterId;
	private String remark;
	private Integer index;
	private String trxStatus;
	private Boolean isRetrieveDetails;
	private String productCode;
	private String bankRef;
	private String validationCheck;
	private String is_additional_info;

	public String getPymtMasterId() {
		return pymtMasterId;
	}

	public void setPymtMasterId(String pymtMasterId) {
		this.pymtMasterId = pymtMasterId;
	}

	public String getSourceTrx() {
		return sourceTrx;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getBankRef() {
		return bankRef;
	}

	public void setBankRef(String bankRef) {
		this.bankRef = bankRef;
	}

	public void setSourceTrx(String sourceTrx) {
		this.sourceTrx = sourceTrx;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getTrxStatus() {
		return trxStatus;
	}

	public void setTrxStatus(String trxStatus) {
		this.trxStatus = trxStatus;
	}

	public Boolean getIsRetrieveDetails() {
		return isRetrieveDetails;
	}

	public void setIsRetrieveDetails(Boolean isRetrieveDetails) {
		this.isRetrieveDetails = isRetrieveDetails;
	}

	public String getValidationCheck() {
		return validationCheck;
	}

	public void setValidationCheck(String validationCheck) {
		this.validationCheck = validationCheck;
	}

	public String getIs_additional_info() {
		return is_additional_info;
	}

	public void setIs_additional_info(String is_additional_info) {
		this.is_additional_info = is_additional_info;
	}	 
	
	
}
