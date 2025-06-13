package com.silverlake.mleb.mcb.bean.mgmtbene;

import java.io.Serializable;

import com.silverlake.mleb.mcb.bean.ObRequestCache;

public class ObTransactionManageBeneficiaryRequest extends ObRequestCache<ObTransactionManageBeneficiarySessionCache> implements Serializable{
	private String transferServiceType;
	private String beneId;
	private Boolean isFavorite;
	
	//New bene fields (General)
	private String accountNo;
	private String accountName;
	private String accountCcy;
	private String nickName;//For edit alias usage too
	private String email;
	private String phoneNumber;
	private String isShared;
	
	//New bene fields (OLT, LLG, RTGS, TT)
	private String bankCode;
	
	//New bene fields (LLG, RTGS, TT)
	private String bankName;
	private String bankBranch;
	private String networkCode;
	private String beneAddress1;
	private String beneAddress2;
	private String beneAddress3;
	
	//New bene fields (TT)
	private String bankCountryCode;
	
	//New field bene Administration
	private String rtgsMemberCode;
    private String participantBic;
	
	public String getTransferServiceType() {
		return transferServiceType;
	}
	public void setTransferServiceType(String transferServiceType) {
		this.transferServiceType = transferServiceType;
	}
	public String getBeneId() {
		return beneId;
	}
	public void setBeneId(String beneId) {
		this.beneId = beneId;
	}
	public Boolean getIsFavorite() {
		return isFavorite;
	}
	public void setIsFavorite(Boolean isFavorite) {
		this.isFavorite = isFavorite;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getAccountCcy() {
		return accountCcy;
	}
	public void setAccountCcy(String accountCcy) {
		this.accountCcy = accountCcy;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getIsShared() {
		return isShared;
	}
	public void setIsShared(String isShared) {
		this.isShared = isShared;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBeneAddress1() {
		return beneAddress1;
	}
	public void setBeneAddress1(String beneAddress1) {
		this.beneAddress1 = beneAddress1;
	}
	public String getBeneAddress2() {
		return beneAddress2;
	}
	public void setBeneAddress2(String beneAddress2) {
		this.beneAddress2 = beneAddress2;
	}
	public String getBeneAddress3() {
		return beneAddress3;
	}
	public void setBeneAddress3(String beneAddress3) {
		this.beneAddress3 = beneAddress3;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getBankBranch() {
		return bankBranch;
	}
	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}
	public String getNetworkCode() {
		return networkCode;
	}
	public void setNetworkCode(String networkCode) {
		this.networkCode = networkCode;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankCountryCode() {
		return bankCountryCode;
	}
	public void setBankCountryCode(String bankCountryCode) {
		this.bankCountryCode = bankCountryCode;
	}
	public String getRtgsMemberCode() {
		return rtgsMemberCode;
	}
	public void setRtgsMemberCode(String rtgsMemberCode) {
		this.rtgsMemberCode = rtgsMemberCode;
	}
	public String getParticipantBic() {
		return participantBic;
	}
	public void setParticipantBic(String participantBic) {
		this.participantBic = participantBic;
	}
}
