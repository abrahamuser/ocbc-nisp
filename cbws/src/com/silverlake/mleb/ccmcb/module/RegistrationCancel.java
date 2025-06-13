package com.silverlake.mleb.ccmcb.module;

import com.silverlake.mleb.ccmcb.module.common.CacheSessionMiBServices;
import com.silverlake.mleb.ccmcb.module.common.WSClass;
import com.silverlake.mleb.ccmcb.module.common.WSParameter;
import com.silverlake.mleb.mcb.bean.ObRegistrationRequest;
import com.silverlake.mleb.mcb.bean.ObRegistrationResponse;
import com.silverlake.mleb.mcb.bean.ObRegistrationeUserSessionCache;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;

import javax.activation.DataHandler;
import javax.xml.ws.WebServiceContext;

import org.apache.commons.io.IOUtils;

@WSClass(functionId= VCMethodConstant.FUNCTION_CODES.FUNC_MCB_REG_CANCEL)


public class RegistrationCancel extends CacheSessionMiBServices<ObRegistrationRequest, ObRegistrationResponse, ObRegistrationeUserSessionCache> {

	@WSParameter(isMandatory=true)
	protected String role;
	
	@WSParameter(isMandatory=true)
	protected String record_id;
		
	@WSParameter(isMandatory=true)
	protected Integer version_no;
	
	@WSParameter(isMandatory=true)
	protected String registration_no;
	
	@WSParameter(isMandatory=true)
	protected String name;
		
	@WSParameter(isMandatory=true)
	protected String ktp_no;
	
	@WSParameter(isMandatory=true)
	protected String address;

	@WSParameter(isMandatory=true)
	protected String email;
	
	@WSParameter(isMandatory=true)
	protected String phone;
		
	@WSParameter(isMandatory=false)
	protected String npwp_no;
	
	@WSParameter(isMandatory=false)
	protected String gender;
	
	@WSParameter(isMandatory=false)
	protected String birth_place;
		
	@WSParameter(isMandatory=false)
	protected String birth_date;
	
	@WSParameter(isMandatory=false)
	protected String province;
	
	@WSParameter(isMandatory=false)
	protected String city;
		
	@WSParameter(isMandatory=false)
	protected DataHandler ktp_photo;
	
	@WSParameter(isMandatory=false)
	protected DataHandler npwp_photo;
	
	@WSParameter(isMandatory=false)
	protected String ktp_file_name;
	
	@WSParameter(isMandatory=false)
	protected String ktp_file_ext;
	
	@WSParameter(isMandatory=false)
	protected String npwp_file_name;
	
	@WSParameter(isMandatory=false)
	protected String npwp_file_ext;
	
	@WSParameter(isMandatory=false)
	protected String verification_no;
	
	@WSParameter(isMandatory=false)
	protected Boolean isBase64;
	
    public RegistrationCancel(WebServiceContext wsContext) {
        super(wsContext);
    }

    @Override
    protected void processData() throws Exception {
        obRequest.setRole(role);
        obRequest.setRecord_id(record_id);
        obRequest.setVersion_no(version_no);
        obRequest.setRegistration_no(registration_no);
        obRequest.setName(name);
        obRequest.setKtp_no(ktp_no);
        obRequest.setAddress(address);
        obRequest.setEmail(email);
        obRequest.setPhone(phone);
        obRequest.setNpwp_no(npwp_no);
        obRequest.setGender(gender);
        obRequest.setBirth_place(birth_place);
        obRequest.setBirth_date(birth_date);
        obRequest.setProvince(province);
        obRequest.setCity(city);
        if(ktp_photo != null){
			byte[] bytes = IOUtils.toByteArray(ktp_photo.getInputStream());
		obRequest.setKtpPhoto(bytes);
		}
        if(npwp_photo != null){
			byte[] bytes = IOUtils.toByteArray(npwp_photo.getInputStream());
		obRequest.setNpwpPhoto(bytes);
		}
        obRequest.setKtp_file_name(ktp_file_name);
        obRequest.setKtp_file_ext(ktp_file_ext);
        obRequest.setNpwp_file_name(npwp_file_name);
        obRequest.setNpwp_file_ext(npwp_file_ext);
        obRequest.setVerificationNo(verification_no);
        obRequest.setIsBase64(isBase64);
        
    }

    @Override
    protected void processSessionResponse() {

    }
}
