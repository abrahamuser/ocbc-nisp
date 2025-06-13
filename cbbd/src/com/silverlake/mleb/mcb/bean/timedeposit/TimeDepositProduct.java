package com.silverlake.mleb.mcb.bean.timedeposit;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class TimeDepositProduct implements Serializable {
    private String productCode;
    private String productName;
    private String currencyCode;
    private List<TimeDepositTenor> tenor;
    private String tenorType;
    private BigDecimal minAmount;
    private BigDecimal maxAmount;
    private BigDecimal interestRate;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getTenorType() {
        return tenorType;
    }

    public void setTenorType(String tenorType) {
        this.tenorType = tenorType;
    }
    
    public BigDecimal getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(BigDecimal minAmount) {
        this.minAmount = minAmount;
    }

    public BigDecimal getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

	public List<TimeDepositTenor> getTenor() {
		return tenor;
	}

	public void setTenor(List<TimeDepositTenor> tenor) {
		this.tenor = tenor;
	}
}
