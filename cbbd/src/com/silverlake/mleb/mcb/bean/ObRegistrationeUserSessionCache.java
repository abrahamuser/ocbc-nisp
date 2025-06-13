package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

import com.silverlake.mleb.mcb.bean.ObSessionCache;
import com.silverlake.mleb.mcb.module.vc.administration.UserManagementListResponseData;
import com.silverlake.mleb.mcb.module.vc.administration.UserRoleListResponseData;
import com.silverlake.mleb.mcb.module.vc.registration.RegistrationInquiryResponseData;
import com.silverlake.mleb.mcb.module.vc.registration.SignerDetails;


public class ObRegistrationeUserSessionCache extends ObSessionCache implements Serializable {
		
	private RegistrationInquiryResponseData registrationInquiryResponseData;
	private SignerDetails signer_details;

	public RegistrationInquiryResponseData getRegistrationInquiryResponseData() {
		return registrationInquiryResponseData;
	}

	public void setRegistrationInquiryResponseData(RegistrationInquiryResponseData registrationInquiryResponseData) {
		this.registrationInquiryResponseData = registrationInquiryResponseData;
	}

	public SignerDetails getSigner_details() {
		return signer_details;
	}

	public void setSigner_details(SignerDetails signer_details) {
		this.signer_details = signer_details;
	}
	

	
}
