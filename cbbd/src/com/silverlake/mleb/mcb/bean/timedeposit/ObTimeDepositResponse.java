package com.silverlake.mleb.mcb.bean.timedeposit;

import com.silverlake.mleb.mcb.bean.ObResponseCache;
import com.silverlake.mleb.mcb.module.vc.timedeposit.ListTimeDepositProduct;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.math.BigDecimal;
import java.util.List;

public class ObTimeDepositResponse extends ObResponseCache implements Serializable {

	private Boolean allowProcess;
    private String startTime;
    private String endTime;
    private String errorMessageCOT;
    private Boolean isPublicHoliday;
    private LinkedHashMap<String, String> purposeList;
    private LinkedHashMap<String, String> sourceOfFundList;
    private LinkedHashMap<String, String> rolloverTypeList;

    private List<TimeDepositProduct> productList;

    //Confirmation/Acknowledgement
    private String debitAccountNo;
    private String debitAccountAliasName;
    private String debitAccountType;
    private String amountCcy;
    private BigDecimal amount;
    private BigDecimal interestRate;
    private String sourceOfFundCode;
    private String sourceOfFundDesc;
    private String purposeCode;
    private String purposeDesc;
    private Integer tenor;
    private String tenorType;
    private Integer interestTerm;
    private String interestTermCode;
    private String rolloverTypeCode;
    private String rolloverTypeDesc;
    private String originalRolloverTypeCode;
    private String originalRolloverTypeDesc;
    private String transactionId;
    
    //Acknowledgement
    private String bankRef;
    private String trxStatusCode;
    private String trxStatusDesc;
    
    //Additional fields for Account Details
    private String debitAccountName;
    private BigDecimal principalAmount;
    private BigDecimal interestAmount;
    private String maturityDate;
    private String effectiveDate;
    
    public Boolean getAllowProcess() {
        return allowProcess;
    }

    public void setAllowProcess(Boolean allowProcess) {
        this.allowProcess = allowProcess;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getErrorMessageCOT() {
        return errorMessageCOT;
    }

    public void setErrorMessageCOT(String errorMessageCOT) {
        this.errorMessageCOT = errorMessageCOT;
    }

    public Boolean getIsPublicHoliday() {
        return isPublicHoliday;
    }

    public void setIsPublicHoliday(Boolean isPublicHoliday) {
        this.isPublicHoliday = isPublicHoliday;
    }

    public LinkedHashMap<String, String> getPurposeList() {
        return purposeList;
    }

    public void setPurposeList(LinkedHashMap<String, String> purposeList) {
        this.purposeList = purposeList;
    }

	public String getDebitAccountNo() {
		return debitAccountNo;
	}

	public String getDebitAccountType() {
		return debitAccountType;
	}

	public String getAmountCcy() {
		return amountCcy;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public BigDecimal getInterestRate() {
		return interestRate;
	}

	public String getSourceOfFundCode() {
		return sourceOfFundCode;
	}

	public String getSourceOfFundDesc() {
		return sourceOfFundDesc;
	}

	public String getPurposeCode() {
		return purposeCode;
	}

	public String getPurposeDesc() {
		return purposeDesc;
	}

	public Integer getTenor() {
		return tenor;
	}

	public String getTenorType() {
		return tenorType;
	}

	public Integer getInterestTerm() {
		return interestTerm;
	}

	public String getInterestTermCode() {
		return interestTermCode;
	}

	public String getRolloverTypeCode() {
		return rolloverTypeCode;
	}

	public String getRolloverTypeDesc() {
		return rolloverTypeDesc;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setDebitAccountNo(String debitAccountNo) {
		this.debitAccountNo = debitAccountNo;
	}

	public void setDebitAccountType(String debitAccountType) {
		this.debitAccountType = debitAccountType;
	}

	public void setAmountCcy(String amountCcy) {
		this.amountCcy = amountCcy;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}

	public void setSourceOfFundCode(String sourceOfFundCode) {
		this.sourceOfFundCode = sourceOfFundCode;
	}

	public void setSourceOfFundDesc(String sourceOfFundDesc) {
		this.sourceOfFundDesc = sourceOfFundDesc;
	}

	public void setPurposeCode(String purposeCode) {
		this.purposeCode = purposeCode;
	}

	public void setPurposeDesc(String purposeDesc) {
		this.purposeDesc = purposeDesc;
	}

	public void setTenor(Integer tenor) {
		this.tenor = tenor;
	}

	public void setTenorType(String tenorType) {
		this.tenorType = tenorType;
	}

	public void setInterestTerm(Integer interestTerm) {
		this.interestTerm = interestTerm;
	}

	public void setInterestTermCode(String interestTermCode) {
		this.interestTermCode = interestTermCode;
	}

	public void setRolloverTypeCode(String rolloverTypeCode) {
		this.rolloverTypeCode = rolloverTypeCode;
	}

	public void setRolloverTypeDesc(String rolloverTypeDesc) {
		this.rolloverTypeDesc = rolloverTypeDesc;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getDebitAccountAliasName() {
		return debitAccountAliasName;
	}

	public void setDebitAccountAliasName(String debitAccountAliasName) {
		this.debitAccountAliasName = debitAccountAliasName;
	}

	public String getBankRef() {
		return bankRef;
	}

	public void setBankRef(String bankRef) {
		this.bankRef = bankRef;
	}

	public String getTrxStatusCode() {
		return trxStatusCode;
	}

	public String getTrxStatusDesc() {
		return trxStatusDesc;
	}

	public void setTrxStatusCode(String trxStatusCode) {
		this.trxStatusCode = trxStatusCode;
	}

	public void setTrxStatusDesc(String trxStatusDesc) {
		this.trxStatusDesc = trxStatusDesc;
	}

    public LinkedHashMap<String, String> getSourceOfFundList() {
        return sourceOfFundList;
    }

    public void setSourceOfFundList(LinkedHashMap<String, String> sourceOfFundList) {
        this.sourceOfFundList = sourceOfFundList;
    }

    public LinkedHashMap<String, String> getRolloverTypeList() {
        return rolloverTypeList;
    }

    public void setRolloverTypeList(LinkedHashMap<String, String> rolloverTypeList) {
        this.rolloverTypeList = rolloverTypeList;
    }

	public List<TimeDepositProduct> getProductList() {
		return productList;
	}

	public void setProductList(List<TimeDepositProduct> productList) {
		this.productList = productList;
	}

	public String getDebitAccountName() {
		return debitAccountName;
	}

	public BigDecimal getPrincipalAmount() {
		return principalAmount;
	}

	public BigDecimal getInterestAmount() {
		return interestAmount;
	}

	public void setDebitAccountName(String debitAccountName) {
		this.debitAccountName = debitAccountName;
	}

	public void setPrincipalAmount(BigDecimal principalAmount) {
		this.principalAmount = principalAmount;
	}

	public void setInterestAmount(BigDecimal interestAmount) {
		this.interestAmount = interestAmount;
	}

	public String getOriginalRolloverTypeCode() {
		return originalRolloverTypeCode;
	}

	public String getOriginalRolloverTypeDesc() {
		return originalRolloverTypeDesc;
	}

	public void setOriginalRolloverTypeCode(String originalRolloverTypeCode) {
		this.originalRolloverTypeCode = originalRolloverTypeCode;
	}

	public void setOriginalRolloverTypeDesc(String originalRolloverTypeDesc) {
		this.originalRolloverTypeDesc = originalRolloverTypeDesc;
	}

	public String getMaturityDate() {
		return maturityDate;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setMaturityDate(String maturityDate) {
		this.maturityDate = maturityDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
}
