package com.silverlake.mleb.mcb.module.vc.transaction;

import java.util.List;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

public class TransactionDataListRequestData extends VCRequest {
	private String pymt_master_id;
	private List<String> prod_cd;
	private String value_date_from;
	private String value_date_to;
	private String amount_from;
	private String amount_to;
	private String debit_acct_no;
	private String debit_acct_ccy;
	private String transfer_ccy;
	private List<String> source_trx;
	private String source_file_name;
	private String upload_date;
	private String bank_ref;
	private String cust_ref;
	private String on_behalf;
	private String trx_status;
	private String page_no;
	private String page_size;
	
	public String getPymt_master_id() {
		return pymt_master_id;
	}
	public void setPymt_master_id(String pymt_master_id) {
		this.pymt_master_id = pymt_master_id;
	}
	public List<String> getProd_cd() {
		return prod_cd;
	}
	public void setProd_cd(List<String> prod_cd) {
		this.prod_cd = prod_cd;
	}
	public String getValue_date_from() {
		return value_date_from;
	}
	public void setValue_date_from(String value_date_from) {
		this.value_date_from = value_date_from;
	}
	public String getValue_date_to() {
		return value_date_to;
	}
	public void setValue_date_to(String value_date_to) {
		this.value_date_to = value_date_to;
	}
	public String getAmount_from() {
		return amount_from;
	}
	public void setAmount_from(String amount_from) {
		this.amount_from = amount_from;
	}
	public String getAmount_to() {
		return amount_to;
	}
	public void setAmount_to(String amount_to) {
		this.amount_to = amount_to;
	}
	public String getDebit_acct_no() {
		return debit_acct_no;
	}
	public void setDebit_acct_no(String debit_acct_no) {
		this.debit_acct_no = debit_acct_no;
	}
	public String getDebit_acct_ccy() {
		return debit_acct_ccy;
	}
	public void setDebit_acct_ccy(String debit_acct_ccy) {
		this.debit_acct_ccy = debit_acct_ccy;
	}
	public String getTransfer_ccy() {
		return transfer_ccy;
	}
	public void setTransfer_ccy(String transfer_ccy) {
		this.transfer_ccy = transfer_ccy;
	}
	public List<String> getSource_trx() {
		return source_trx;
	}
	public void setSource_trx(List<String> source_trx) {
		this.source_trx = source_trx;
	}
	public String getSource_file_name() {
		return source_file_name;
	}
	public void setSource_file_name(String source_file_name) {
		this.source_file_name = source_file_name;
	}
	public String getUpload_date() {
		return upload_date;
	}
	public void setUpload_date(String upload_date) {
		this.upload_date = upload_date;
	}
	public String getBank_ref() {
		return bank_ref;
	}
	public void setBank_ref(String bank_ref) {
		this.bank_ref = bank_ref;
	}
	public String getCust_ref() {
		return cust_ref;
	}
	public void setCust_ref(String cust_ref) {
		this.cust_ref = cust_ref;
	}
	public String getOn_behalf() {
		return on_behalf;
	}
	public void setOn_behalf(String on_behalf) {
		this.on_behalf = on_behalf;
	}
	public String getTrx_status() {
		return trx_status;
	}
	public void setTrx_status(String trx_status) {
		this.trx_status = trx_status;
	}
	public String getPage_no() {
		return page_no;
	}
	public void setPage_no(String page_no) {
		this.page_no = page_no;
	}
	public String getPage_size() {
		return page_size;
	}
	public void setPage_size(String page_size) {
		this.page_size = page_size;
	}
}



	
