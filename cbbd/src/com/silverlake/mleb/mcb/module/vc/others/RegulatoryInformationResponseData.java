package com.silverlake.mleb.mcb.module.vc.others;

import java.util.List;

import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;

public class RegulatoryInformationResponseData extends VCResponseData {
	private List<CategoryList> category_list;
	private List<ResidentStatusList> resident_status;
	private List<UnderlyingList> underlying_type;
	private List<RelationshipStatusList> relationship_status;
	private List<PaymentPurposeList> payment_purpose;
	
	public List<CategoryList> getCategory_list() {
		return category_list;
	}
	public void setCategory_list(List<CategoryList> category_list) {
		this.category_list = category_list;
	}
	public List<ResidentStatusList> getResident_status() {
		return resident_status;
	}
	public void setResident_status(List<ResidentStatusList> resident_status) {
		this.resident_status = resident_status;
	}
	public List<UnderlyingList> getUnderlying_type() {
		return underlying_type;
	}
	public void setUnderlying_type(List<UnderlyingList> underlying_type) {
		this.underlying_type = underlying_type;
	}
	public List<RelationshipStatusList> getRelationship_status() {
		return relationship_status;
	}
	public void setRelationship_status(List<RelationshipStatusList> relationship_status) {
		this.relationship_status = relationship_status;
	}
	public List<PaymentPurposeList> getPayment_purpose() {
		return payment_purpose;
	}
	public void setPayment_purpose(List<PaymentPurposeList> payment_purpose) {
		this.payment_purpose = payment_purpose;
	}
}
