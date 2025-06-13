package com.silverlake.mleb.mcb.module.vc.registration;

import java.io.Serializable;
import java.util.List;

public class DocumentDetails  implements Serializable
{
      private String doc_id;
	  private Integer version;
	  private String doc_type;
	  private String file_name;
	  private String ext;
	  private String upload_date;
	  private Boolean need_sign;
	  private String doc_blob;
	  private String final_signed_doc;
	  
	  
	public String getDoc_id() {
		return doc_id;
	}
	public void setDoc_id(String doc_id) {
		this.doc_id = doc_id;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getDoc_type() {
		return doc_type;
	}
	public void setDoc_type(String doc_type) {
		this.doc_type = doc_type;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	public String getUpload_date() {
		return upload_date;
	}
	public void setUpload_date(String upload_date) {
		this.upload_date = upload_date;
	}
	public Boolean getNeed_sign() {
		return need_sign;
	}
	public void setNeed_sign(Boolean need_sign) {
		this.need_sign = need_sign;
	}
	public String getDoc_blob() {
		return doc_blob;
	}
	public void setDoc_blob(String doc_blob) {
		this.doc_blob = doc_blob;
	}
	public String getFinal_signed_doc() {
		return final_signed_doc;
	}
	public void setFinal_signed_doc(String final_signed_doc) {
		this.final_signed_doc = final_signed_doc;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	  
		 
}



	
