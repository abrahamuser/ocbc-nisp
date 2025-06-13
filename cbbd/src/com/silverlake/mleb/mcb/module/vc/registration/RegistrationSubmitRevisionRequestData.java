package com.silverlake.mleb.mcb.module.vc.registration;

import java.util.List;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

public class RegistrationSubmitRevisionRequestData extends VCRequest {
	private String role;
	private String record_id;
	private Integer version_no;
	private String registration_no;
	private String revision_no;
	private InputterDetails user_details;
	private SignerDetails signer_details;
	private List<DocumentDetails> document_details_list;
//	private DocumentDetails document_details;
	private Boolean signers_changed;
	private String device_id;
	private String device_type;
	private String device_os;
	
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
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
	public InputterDetails getUser_details() {
		return user_details;
	}
	public void setUser_details(InputterDetails user_details) {
		this.user_details = user_details;
	}
	public SignerDetails getSigner_details() {
		return signer_details;
	}
	public void setSigner_details(SignerDetails signer_details) {
		this.signer_details = signer_details;
	}
	public Boolean getSigners_changed() {
		return signers_changed;
	}
	public void setSigners_changed(Boolean signers_changed) {
		this.signers_changed = signers_changed;
	}
	public String getDevice_id() {
		return device_id;
	}
	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}
	public String getDevice_type() {
		return device_type;
	}
	public void setDevice_type(String device_type) {
		this.device_type = device_type;
	}
	public String getDevice_os() {
		return device_os;
	}
	public void setDevice_os(String device_os) {
		this.device_os = device_os;
	}
	public List<DocumentDetails> getDocument_details_list() {
		return document_details_list;
	}
	public void setDocument_details_list(List<DocumentDetails> document_details_list) {
		this.document_details_list = document_details_list;
	}
	/*public DocumentDetails getDocument_details() {
		return document_details;
	}
	public void setDocument_details(DocumentDetails document_details) {
		this.document_details = document_details;
	}*/
		
		
}
