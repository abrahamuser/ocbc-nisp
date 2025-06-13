package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class PALBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	// Installment variables for calculator
	private BigDecimal insuranceFee;
	private BigDecimal totalNewPlafond;
	private BigDecimal provisionFee;
	private BigDecimal disbursementFee;
	private BigDecimal totalDisbursed;
	private MapPojo tenor;
	private BigDecimal interestRate;
	private BigDecimal monthlyInstallment;
	private BigDecimal limit;
	
	// OCRImage submission variables
	private String uuid;
	private String imageType;
	private String status;

	public BigDecimal getInsuranceFee() {
		return insuranceFee;
	}

	public void setInsuranceFee(BigDecimal insuranceFee) {
		this.insuranceFee = insuranceFee;
	}

	public BigDecimal getTotalNewPlafond() {
		return totalNewPlafond;
	}

	public void setTotalNewPlafond(BigDecimal totalNewPlafond) {
		this.totalNewPlafond = totalNewPlafond;
	}

	public BigDecimal getProvisionFee() {
		return provisionFee;
	}

	public void setProvisionFee(BigDecimal provisionFee) {
		this.provisionFee = provisionFee;
	}

	public BigDecimal getDisbursementFee() {
		return disbursementFee;
	}

	public void setDisbursementFee(BigDecimal disbursementFee) {
		this.disbursementFee = disbursementFee;
	}

	public BigDecimal getTotalDisbursed() {
		return totalDisbursed;
	}

	public void setTotalDisbursed(BigDecimal totalDisbursed) {
		this.totalDisbursed = totalDisbursed;
	}

	public MapPojo getTenor() {
		return tenor;
	}

	public void setTenor(MapPojo tenor) {
		this.tenor = tenor;
	}

	public BigDecimal getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}

	public BigDecimal getMonthlyInstallment() {
		return monthlyInstallment;
	}

	public void setMonthlyInstallment(BigDecimal monthlyInstallment) {
		this.monthlyInstallment = monthlyInstallment;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getLimit() {
		return limit;
	}

	public void setLimit(BigDecimal limit) {
		this.limit = limit;
	}
	
	
}

