package com.silverlake.mleb.mcb.module.vc.registration;

import java.io.Serializable;
import java.util.List;

public class RevisionInfo  implements Serializable
{
      private String revision_no;
	  private String revision_used_date;
	  private String revision_exp_date;
	  
	  
	public String getRevision_no() {
		return revision_no;
	}
	public void setRevision_no(String revision_no) {
		this.revision_no = revision_no;
	}
	public String getRevision_used_date() {
		return revision_used_date;
	}
	public void setRevision_used_date(String revision_used_date) {
		this.revision_used_date = revision_used_date;
	}
	public String getRevision_exp_date() {
		return revision_exp_date;
	}
	public void setRevision_exp_date(String revision_exp_date) {
		this.revision_exp_date = revision_exp_date;
	}
	  
	  
	 	 
}



	
