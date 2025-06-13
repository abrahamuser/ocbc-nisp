package com.silverlake.mleb.mcb.module.vc.registration;

import java.io.Serializable;
import java.util.List;

public class InputterDetails  implements Serializable
{
      private String user_id;
	  private Integer version_no;
	  private Integer sequence;
	  private String name;
	  private String name_masked;
	  private String ktp_no;
	  private String ktp_no_masked;
	  private String npwp_no;
	  private String npwp_no_masked;
	  private String address;
	  private String address_masked;
	  private String email;
	  private String email_masked;
	  private String phone;
	  private String phone_masked;
	  private String gender;
	  private String gender_masked;
	  private String birth_place;
	  private String birth_place_masked;
	  private String birth_date;
	  private String birth_date_masked;
	  private String province;
	  private String province_masked;
	  private String city;
	  private String city_masked;
	  private String ktp_photo;
	  private String npwp_photo;
	  private String ktp_file_name;
	  private String ktp_file_ext;
	  private String npwp_file_name;
	  private String npwp_file_ext;
	  private String signer_own;
	  private SignatureDetails signature_details;
	  private RevisionInfo revision_info;
	  private VerficationInfo verification_info;
	  
	  
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public Integer getVersion_no() {
		return version_no;
	}
	public void setVersion_no(Integer version_no) {
		this.version_no = version_no;
	}
	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName_masked() {
		return name_masked;
	}
	public void setName_masked(String name_masked) {
		this.name_masked = name_masked;
	}
	public String getKtp_no() {
		return ktp_no;
	}
	public void setKtp_no(String ktp_no) {
		this.ktp_no = ktp_no;
	}
	public String getKtp_no_masked() {
		return ktp_no_masked;
	}
	public void setKtp_no_masked(String ktp_no_masked) {
		this.ktp_no_masked = ktp_no_masked;
	}
	public String getNpwp_no() {
		return npwp_no;
	}
	public void setNpwp_no(String npwp_no) {
		this.npwp_no = npwp_no;
	}
	public String getNpwp_no_masked() {
		return npwp_no_masked;
	}
	public void setNpwp_no_masked(String npwp_no_masked) {
		this.npwp_no_masked = npwp_no_masked;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress_masked() {
		return address_masked;
	}
	public void setAddress_masked(String address_masked) {
		this.address_masked = address_masked;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail_masked() {
		return email_masked;
	}
	public void setEmail_masked(String email_masked) {
		this.email_masked = email_masked;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPhone_masked() {
		return phone_masked;
	}
	public void setPhone_masked(String phone_masked) {
		this.phone_masked = phone_masked;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getGender_masked() {
		return gender_masked;
	}
	public void setGender_masked(String gender_masked) {
		this.gender_masked = gender_masked;
	}
	public String getBirth_place() {
		return birth_place;
	}
	public void setBirth_place(String birth_place) {
		this.birth_place = birth_place;
	}
	public String getBirth_place_masked() {
		return birth_place_masked;
	}
	public void setBirth_place_masked(String birth_place_masked) {
		this.birth_place_masked = birth_place_masked;
	}
	public String getBirth_date() {
		return birth_date;
	}
	public void setBirth_date(String birth_date) {
		this.birth_date = birth_date;
	}
	public String getBirth_date_masked() {
		return birth_date_masked;
	}
	public void setBirth_date_masked(String birth_date_masked) {
		this.birth_date_masked = birth_date_masked;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getProvince_masked() {
		return province_masked;
	}
	public void setProvince_masked(String province_masked) {
		this.province_masked = province_masked;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCity_masked() {
		return city_masked;
	}
	public void setCity_masked(String city_masked) {
		this.city_masked = city_masked;
	}
	public String getKtp_photo() {
		return ktp_photo;
	}
	public void setKtp_photo(String ktp_photo) {
		this.ktp_photo = ktp_photo;
	}
	public String getNpwp_photo() {
		return npwp_photo;
	}
	public void setNpwp_photo(String npwp_photo) {
		this.npwp_photo = npwp_photo;
	}
	public SignatureDetails getSignature_details() {
		return signature_details;
	}
	public void setSignature_details(SignatureDetails signature_details) {
		this.signature_details = signature_details;
	}
	public RevisionInfo getRevision_info() {
		return revision_info;
	}
	public void setRevision_info(RevisionInfo revision_info) {
		this.revision_info = revision_info;
	}
	public VerficationInfo getVerification_info() {
		return verification_info;
	}
	public void setVerification_info(VerficationInfo verification_info) {
		this.verification_info = verification_info;
	}
	public String getSigner_own() {
		return signer_own;
	}
	public void setSigner_own(String signer_own) {
		this.signer_own = signer_own;
	}
	public String getKtp_file_name() {
		return ktp_file_name;
	}
	public void setKtp_file_name(String ktp_file_name) {
		this.ktp_file_name = ktp_file_name;
	}
	public String getKtp_file_ext() {
		return ktp_file_ext;
	}
	public void setKtp_file_ext(String ktp_file_ext) {
		this.ktp_file_ext = ktp_file_ext;
	}
	public String getNpwp_file_name() {
		return npwp_file_name;
	}
	public void setNpwp_file_name(String npwp_file_name) {
		this.npwp_file_name = npwp_file_name;
	}
	public String getNpwp_file_ext() {
		return npwp_file_ext;
	}
	public void setNpwp_file_ext(String npwp_file_ext) {
		this.npwp_file_ext = npwp_file_ext;
	}
	  
	  	 
}



	
