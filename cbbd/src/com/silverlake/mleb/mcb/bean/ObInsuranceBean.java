package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class ObInsuranceBean extends ObResponse implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	private String insuranceCompany;
	private String insuranceName;
	private String insuranceDesc;
	private String sumAssuredCcy;
	private String sumAssured;
	private String typeOfInsurance;
	private String acceptableAgeRange;
	private String coveragePeriod;
	private String permiPerMonth;
	private List<String> ridersInsurance;
	private String riskTolerance;
	
	public String getInsuranceCompany() {
		return insuranceCompany;
	}
	public void setInsuranceCompany(String insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}
	public String getInsuranceName() {
		return insuranceName;
	}
	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}
	public String getInsuranceDesc() {
		return insuranceDesc;
	}
	public void setInsuranceDesc(String insuranceDesc) {
		this.insuranceDesc = insuranceDesc;
	}
	public String getSumAssuredCcy() {
		return sumAssuredCcy;
	}
	public void setSumAssuredCcy(String sumAssuredCcy) {
		this.sumAssuredCcy = sumAssuredCcy;
	}
	public String getSumAssured() {
		return sumAssured;
	}
	public void setSumAssured(String sumAssured) {
		this.sumAssured = sumAssured;
	}
	public String getTypeOfInsurance() {
		return typeOfInsurance;
	}
	public void setTypeOfInsurance(String typeOfInsurance) {
		this.typeOfInsurance = typeOfInsurance;
	}
	public String getAcceptableAgeRange() {
		return acceptableAgeRange;
	}
	public void setAcceptableAgeRange(String acceptableAgeRange) {
		this.acceptableAgeRange = acceptableAgeRange;
	}
	public String getCoveragePeriod() {
		return coveragePeriod;
	}
	public void setCoveragePeriod(String coveragePeriod) {
		this.coveragePeriod = coveragePeriod;
	}
	public String getPermiPerMonth() {
		return permiPerMonth;
	}
	public void setPermiPerMonth(String permiPerMonth) {
		this.permiPerMonth = permiPerMonth;
	}
	public List<String> getRidersInsurance() {
		return ridersInsurance;
	}
	public void setRidersInsurance(List<String> ridersInsurance) {
		this.ridersInsurance = ridersInsurance;
	}
	public String getRiskTolerance() {
		return riskTolerance;
	}
	public void setRiskTolerance(String riskTolerance) {
		this.riskTolerance = riskTolerance;
	}
	
}
