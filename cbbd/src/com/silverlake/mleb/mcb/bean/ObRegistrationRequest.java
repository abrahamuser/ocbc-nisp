package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

import com.silverlake.mleb.mcb.bean.ObRequestCache;
import com.silverlake.mleb.mcb.bean.RoleList;


public class ObRegistrationRequest extends ObRequestCache<ObRegistrationeUserSessionCache> implements Serializable {

     
	private String role;
    private String email;
    private String mobileNo;
    private String ktpNo;
    private String registrationNo;
    private String accountName;
    private String verificationNo;
    private String revisionNo;
    private String user_id;
    
    private String action;
    private String name;
    private String ktp_no;
    private String npwp_no;
    private String gender;
    private String birth_place;
    private String birth_date;
    private String address;
    private String Province;
    private String phone;
    private String ktp_photo;
    private String npwp_photo;
    private String ktp_file_name;
	private String ktp_file_ext;
	private String npwp_file_name;
	private String npwp_file_ext;
	private byte[] ktpPhoto;
    private byte[] npwpPhoto;
    
    private String record_id;
    private Integer version_no;
    private String registration_no;
    private String revision_no;
    private String signature_flow;
    private String city;
    
    private Boolean signers_changed;
    private String discardChanges;
    
    private List<SignerList> signerList;
    
    private Integer sequence;
    private Boolean isBase64;
       
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getKtpNo() {
		return ktpNo;
	}
	public void setKtpNo(String ktpNo) {
		this.ktpNo = ktpNo;
	}
	public String getRegistrationNo() {
		return registrationNo;
	}
	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getVerificationNo() {
		return verificationNo;
	}
	public void setVerificationNo(String verificationNo) {
		this.verificationNo = verificationNo;
	}
	public String getRevisionNo() {
		return revisionNo;
	}
	public void setRevisionNo(String revisionNo) {
		this.revisionNo = revisionNo;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKtp_no() {
		return ktp_no;
	}
	public void setKtp_no(String ktp_no) {
		this.ktp_no = ktp_no;
	}
	public String getNpwp_no() {
		return npwp_no;
	}
	public void setNpwp_no(String npwp_no) {
		this.npwp_no = npwp_no;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirth_place() {
		return birth_place;
	}
	public void setBirth_place(String birth_place) {
		this.birth_place = birth_place;
	}
	public String getBirth_date() {
		return birth_date;
	}
	public void setBirth_date(String birth_date) {
		this.birth_date = birth_date;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getProvince() {
		return Province;
	}
	public void setProvince(String province) {
		Province = province;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	public String getRecord_id() {
		return record_id;
	}
	public void setRecord_id(String record_id) {
		this.record_id = record_id;
	}
		public Integer getVersion_no() {
		return version_no;
	}
	public void setVersion_no(Integer version_no) {
		this.version_no = version_no;
	}
	public String getRegistration_no() {
		return registration_no;
	}
	public void setRegistration_no(String registration_no) {
		this.registration_no = registration_no;
	}
	public String getRevision_no() {
		return revision_no;
	}
	public void setRevision_no(String revision_no) {
		this.revision_no = revision_no;
	}
	public String getSignature_flow() {
		return signature_flow;
	}
	public void setSignature_flow(String signature_flow) {
		this.signature_flow = signature_flow;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Boolean getSigners_changed() {
		return signers_changed;
	}
	public void setSigners_changed(Boolean signers_changed) {
		this.signers_changed = signers_changed;
	}
	public String getDiscardChanges() {
		return discardChanges;
	}
	public void setDiscardChanges(String discardChanges) {
		this.discardChanges = discardChanges;
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
	public byte[] getKtpPhoto() {
		return ktpPhoto;
	}
	public void setKtpPhoto(byte[] ktpPhoto) {
		this.ktpPhoto = ktpPhoto;
	}
	public byte[] getNpwpPhoto() {
		return npwpPhoto;
	}
	public void setNpwpPhoto(byte[] npwpPhoto) {
		this.npwpPhoto = npwpPhoto;
	}
	public List<SignerList> getSignerList() {
		return signerList;
	}
	public void setSignerList(List<SignerList> signerList) {
		this.signerList = signerList;
	}
	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	public Boolean getIsBase64() {
		return isBase64;
	}
	public void setIsBase64(Boolean isBase64) {
		this.isBase64 = isBase64;
	}
	
           
}
