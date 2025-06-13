package com.silverlake.mleb.mcb.bean.timedeposit;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class TimeDepositTenor implements Serializable {
    private Integer tenor;
    private List<Integer> interestTerm;
    private String interestTermType;
	public Integer getTenor() {
		return tenor;
	}
	public List<Integer> getInterestTerm() {
		return interestTerm;
	}
	public String getInterestTermType() {
		return interestTermType;
	}
	public void setTenor(Integer tenor) {
		this.tenor = tenor;
	}
	public void setInterestTerm(List<Integer> interestTerm) {
		this.interestTerm = interestTerm;
	}
	public void setInterestTermType(String interestTermType) {
		this.interestTermType = interestTermType;
	}
}
