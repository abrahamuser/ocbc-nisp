package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.Date;


public class ObFAQBean implements Serializable
{
	
	private Integer faqID;
	private String question;
	private String content;
	private String modifiedBy; 
	private Date modifiedDate; 
	private String status;


	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getFaqID() {
		return faqID;
	}
	public void setFaqID(Integer faqID) {
		this.faqID = faqID;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	
}