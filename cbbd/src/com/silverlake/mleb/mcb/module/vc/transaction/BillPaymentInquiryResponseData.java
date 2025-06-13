package com.silverlake.mleb.mcb.module.vc.transaction;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.silverlake.mleb.mcb.bean.ObAmountOptionBean;
import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;

public class BillPaymentInquiryResponseData extends VCResponseData{
	private BillDetailsData details;
	private String inq_session_id;
	private BigDecimal last_month_inv_amt;
	private String last_month_inv_caption;
	private String last_month_inv_ref;
	private BigDecimal total_inv_amt;
	private String total_inv_caption;
	private String total_inv_ref;
	private List<PurchaseDataList> purchase_data_list;
	
	public BillDetailsData getDetails() {
		return details;
	}
	public void setDetails(BillDetailsData details) {
		this.details = details;
	}
	public String getInq_session_id() {
		return inq_session_id;
	}
	public void setInq_session_id(String inq_session_id) {
		this.inq_session_id = inq_session_id;
	}
	public BigDecimal getLast_month_inv_amt() {
		return last_month_inv_amt;
	}
	public void setLast_month_inv_amt(BigDecimal last_month_inv_amt) {
		this.last_month_inv_amt = last_month_inv_amt;
	}
	public String getLast_month_inv_caption() {
		return last_month_inv_caption;
	}
	public void setLast_month_inv_caption(String last_month_inv_caption) {
		this.last_month_inv_caption = last_month_inv_caption;
	}
	public String getLast_month_inv_ref() {
		return last_month_inv_ref;
	}
	public void setLast_month_inv_ref(String last_month_inv_ref) {
		this.last_month_inv_ref = last_month_inv_ref;
	}
	public BigDecimal getTotal_inv_amt() {
		return total_inv_amt;
	}
	public void setTotal_inv_amt(BigDecimal total_inv_amt) {
		this.total_inv_amt = total_inv_amt;
	}
	public String getTotal_inv_caption() {
		return total_inv_caption;
	}
	public void setTotal_inv_caption(String total_inv_caption) {
		this.total_inv_caption = total_inv_caption;
	}
	public String getTotal_inv_ref() {
		return total_inv_ref;
	}
	public void setTotal_inv_ref(String total_inv_ref) {
		this.total_inv_ref = total_inv_ref;
	}
	public List<PurchaseDataList> getPurchase_data_list() {
		return purchase_data_list;
	}
	public void setPurchase_data_list(List<PurchaseDataList> purchase_data_list) {
		this.purchase_data_list = purchase_data_list;
	}
}
