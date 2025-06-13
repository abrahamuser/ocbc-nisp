package com.silverlake.mleb.mcb.module.vc.swift;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class SwiftGPIOutbound  implements Serializable
{
      private String transaction_status;
	  private String status_desc_id;
	  private String status_desc_en;
	  private String status_desc_cn;
	  private String initiation_datetime;
	  private String completion_datetime;
	  private BigDecimal instructed_amount;
	  private BigDecimal credited_amount;
	  private BigDecimal deducted_amount;
	  private String instructed_ccy;
	  private String credited_ccy;
	  private String deducted_ccy;
	  private String value_date;
	  private String charged_bearer;
	  private String charged_bearer_desc_id;
	  private String charged_bearer_desc_en;
	  private String charged_bearer_desc_cn;
	  private String uetr;
	  private List<TrackingList> tracking_list;
	  
	public String getTransaction_status() {
		return transaction_status;
	}
	public void setTransaction_status(String transaction_status) {
		this.transaction_status = transaction_status;
	}
	public String getStatus_desc_id() {
		return status_desc_id;
	}
	public void setStatus_desc_id(String status_desc_id) {
		this.status_desc_id = status_desc_id;
	}
	public String getStatus_desc_en() {
		return status_desc_en;
	}
	public void setStatus_desc_en(String status_desc_en) {
		this.status_desc_en = status_desc_en;
	}
	public String getStatus_desc_cn() {
		return status_desc_cn;
	}
	public void setStatus_desc_cn(String status_desc_cn) {
		this.status_desc_cn = status_desc_cn;
	}
	public String getInitiation_datetime() {
		return initiation_datetime;
	}
	public void setInitiation_datetime(String initiation_datetime) {
		this.initiation_datetime = initiation_datetime;
	}
	public String getCompletion_datetime() {
		return completion_datetime;
	}
	public void setCompletion_datetime(String completion_datetime) {
		this.completion_datetime = completion_datetime;
	}
	public BigDecimal getInstructed_amount() {
		return instructed_amount;
	}
	public void setInstructed_amount(BigDecimal instructed_amount) {
		this.instructed_amount = instructed_amount;
	}
	public BigDecimal getCredited_amount() {
		return credited_amount;
	}
	public void setCredited_amount(BigDecimal credited_amount) {
		this.credited_amount = credited_amount;
	}
	public BigDecimal getDeducted_amount() {
		return deducted_amount;
	}
	public void setDeducted_amount(BigDecimal deducted_amount) {
		this.deducted_amount = deducted_amount;
	}
	public String getValue_date() {
		return value_date;
	}
	public void setValue_date(String value_date) {
		this.value_date = value_date;
	}
	public String getCharged_bearer() {
		return charged_bearer;
	}
	public void setCharged_bearer(String charged_bearer) {
		this.charged_bearer = charged_bearer;
	}
	public String getCharged_bearer_desc_id() {
		return charged_bearer_desc_id;
	}
	public void setCharged_bearer_desc_id(String charged_bearer_desc_id) {
		this.charged_bearer_desc_id = charged_bearer_desc_id;
	}
	public String getCharged_bearer_desc_en() {
		return charged_bearer_desc_en;
	}
	public void setCharged_bearer_desc_en(String charged_bearer_desc_en) {
		this.charged_bearer_desc_en = charged_bearer_desc_en;
	}
	public String getCharged_bearer_desc_cn() {
		return charged_bearer_desc_cn;
	}
	public void setCharged_bearer_desc_cn(String charged_bearer_desc_cn) {
		this.charged_bearer_desc_cn = charged_bearer_desc_cn;
	}
	public String getUetr() {
		return uetr;
	}
	public void setUetr(String uetr) {
		this.uetr = uetr;
	}
	public List<TrackingList> getTracking_list() {
		return tracking_list;
	}
	public void setTracking_list(List<TrackingList> tracking_list) {
		this.tracking_list = tracking_list;
	}
	public String getInstructed_ccy() {
		return instructed_ccy;
	}
	public void setInstructed_ccy(String instructed_ccy) {
		this.instructed_ccy = instructed_ccy;
	}
	public String getCredited_ccy() {
		return credited_ccy;
	}
	public void setCredited_ccy(String credited_ccy) {
		this.credited_ccy = credited_ccy;
	}
	public String getDeducted_ccy() {
		return deducted_ccy;
	}
	public void setDeducted_ccy(String deducted_ccy) {
		this.deducted_ccy = deducted_ccy;
	}
		
	 
}



	
