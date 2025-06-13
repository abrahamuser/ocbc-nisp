package com.silverlake.mleb.mcb.module.vc.registration;

import java.io.Serializable;
import java.util.List;

public class RegistrationData  implements Serializable
{
      private String record_id;
	  private String registration_no;
	  private Integer version_no;
	  private Integer revision_no;
	  private String submission_date;
	  private String last_revision_date;
	  private String registration_status;
	  private AccountDetails account_details;
	  private InputterDetails inputter_details;
	  private SignerDetails signer_details;
	//  private DocumentDetails document_details;
	  private List<DocumentDetails> document_details_list;
	 
	  
	  
	public String getRecord_id() {
		return record_id;
	}
	public void setRecord_id(String record_id) {
		this.record_id = record_id;
	}
	public String getRegistration_no() {
		return registration_no;
	}
	public void setRegistration_no(String registration_no) {
		this.registration_no = registration_no;
	}
	public Integer getRevision_no() {
		return revision_no;
	}
	public Integer getVersion_no() {
		return version_no;
	}
	public void setVersion_no(Integer version_no) {
		this.version_no = version_no;
	}
	public void setRevision_no(Integer revision_no) {
		this.revision_no = revision_no;
	}
	public String getSubmission_date() {
		return submission_date;
	}
	public void setSubmission_date(String submission_date) {
		this.submission_date = submission_date;
	}
	public String getLast_revision_date() {
		return last_revision_date;
	}
	public void setLast_revision_date(String last_revision_date) {
		this.last_revision_date = last_revision_date;
	}
	public String getRegistration_status() {
		return registration_status;
	}
	public void setRegistration_status(String registration_status) {
		this.registration_status = registration_status;
	}
	public AccountDetails getAccount_details() {
		return account_details;
	}
	public void setAccount_details(AccountDetails account_details) {
		this.account_details = account_details;
	}
	public InputterDetails getInputter_details() {
		return inputter_details;
	}
	public void setInputter_details(InputterDetails inputter_details) {
		this.inputter_details = inputter_details;
	}
	public SignerDetails getSigner_details() {
		return signer_details;
	}
	public void setSigner_details(SignerDetails signer_details) {
		this.signer_details = signer_details;
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



	
