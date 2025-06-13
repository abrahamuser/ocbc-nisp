package com.silverlake.mleb.mcb.module.vc.registration;

import java.util.List;

import com.silverlake.mleb.mcb.module.vc.administration.UserData;
import com.silverlake.mleb.mcb.module.vc.common.VCRequest;
import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;

public class RegistrationSubmitRevisionResponseData extends VCResponseData {
	private RegistrationData registration_data;
	
	
	public RegistrationData getRegistration_data() {
		return registration_data;
	}
	public void setRegistration_data(RegistrationData registration_data) {
		this.registration_data = registration_data;
	}
	
	
	
}
