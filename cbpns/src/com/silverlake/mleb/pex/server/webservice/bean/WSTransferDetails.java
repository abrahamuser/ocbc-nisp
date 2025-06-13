
package com.silverlake.mleb.pex.server.webservice.bean;

import java.io.Serializable;



public class WSTransferDetails 
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    
    private String accountNumber;
    private String beneficiaryBankName;
    private String beneficiaryBankCode;
    
    
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getBeneficiaryBankName() {
		return beneficiaryBankName;
	}
	public void setBeneficiaryBankName(String beneficiaryBankName) {
		this.beneficiaryBankName = beneficiaryBankName;
	}
	public String getBeneficiaryBankCode() {
		return beneficiaryBankCode;
	}
	public void setBeneficiaryBankCode(String beneficiaryBankCode) {
		this.beneficiaryBankCode = beneficiaryBankCode;
	}
    

}
