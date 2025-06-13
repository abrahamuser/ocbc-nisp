package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObManageBeneficiaryModulesEditRequest extends ObRequest implements Serializable
{
	private static final long serialVersionUID = -8139950239349609697L;

	private String beneficiaryId;
	private String cif;
	private String beneAccountNo;
	private String beneAccountCcy;
	private String beneficiaryName;
	private String email;
	private String phoneNumber;
	private byte[] benePicture;
	private String version;
	private String bankId;
	private String bankName;
	private String transferServiceGrp;
	private String transferServiceGrpDesc;
	private String updateDate;
	private String nickName;
	private String citizenFlag;
	private String residentFlag;
	private String beneBankCityId;
	private String beneBankClearingId;
	private String beneBankRTGSCode;

	public String getBeneficiaryId()
	{
		return beneficiaryId;
	}

	public void setBeneficiaryId(String beneficiaryId)
	{
		this.beneficiaryId = beneficiaryId;
	}

	public String getCif()
	{
		return cif;
	}

	public void setCif(String cif)
	{
		this.cif = cif;
	}

	public String getBeneAccountNo()
	{
		return beneAccountNo;
	}

	public void setBeneAccountNo(String beneAccountNo)
	{
		this.beneAccountNo = beneAccountNo;
	}

	public String getBeneAccountCcy()
	{
		return beneAccountCcy;
	}

	public void setBeneAccountCcy(String beneAccountCcy)
	{
		this.beneAccountCcy = beneAccountCcy;
	}

	public String getBeneficiaryName()
	{
		return beneficiaryName;
	}

	public void setBeneficiaryName(String beneficiaryName)
	{
		this.beneficiaryName = beneficiaryName;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	public byte[] getBenePicture()
	{
		return benePicture;
	}

	public void setBenePicture(byte[] benePicture)
	{
		this.benePicture = benePicture;
	}

	public String getVersion()
	{
		return version;
	}

	public void setVersion(String version)
	{
		this.version = version;
	}

	public String getBankId()
	{
		return bankId;
	}

	public void setBankId(String bankId)
	{
		this.bankId = bankId;
	}

	public String getBankName()
	{
		return bankName;
	}

	public void setBankName(String bankName)
	{
		this.bankName = bankName;
	}

	public String getTransferServiceGrp()
	{
		return transferServiceGrp;
	}

	public void setTransferServiceGrp(String transferServiceGrp)
	{
		this.transferServiceGrp = transferServiceGrp;
	}

	public String getTransferServiceGrpDesc()
	{
		return transferServiceGrpDesc;
	}

	public void setTransferServiceGrpDesc(String transferServiceGrpDesc)
	{
		this.transferServiceGrpDesc = transferServiceGrpDesc;
	}

	public String getUpdateDate()
	{
		return updateDate;
	}

	public void setUpdateDate(String updateDate)
	{
		this.updateDate = updateDate;
	}

	public String getNickName()
	{
		return nickName;
	}

	public void setNickName(String nickName)
	{
		this.nickName = nickName;
	}

	public String getCitizenFlag()
	{
		return citizenFlag;
	}

	public void setCitizenFlag(String citizenFlag)
	{
		this.citizenFlag = citizenFlag;
	}

	public String getResidentFlag()
	{
		return residentFlag;
	}

	public void setResidentFlag(String residentFlag)
	{
		this.residentFlag = residentFlag;
	}

	public String getBeneBankCityId()
	{
		return beneBankCityId;
	}

	public void setBeneBankCityId(String beneBankCityId)
	{
		this.beneBankCityId = beneBankCityId;
	}

	public String getBeneBankClearingId()
	{
		return beneBankClearingId;
	}

	public void setBeneBankClearingId(String beneBankClearingId)
	{
		this.beneBankClearingId = beneBankClearingId;
	}

	public String getBeneBankRTGSCode()
	{
		return beneBankRTGSCode;
	}

	public void setBeneBankRTGSCode(String beneBankRTGSCode)
	{
		this.beneBankRTGSCode = beneBankRTGSCode;
	}

}