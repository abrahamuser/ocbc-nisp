package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

import com.silverlake.mleb.mcb.module.vc.authorization.AuthPayment;
import com.silverlake.mleb.mcb.module.vc.transaction.Transaction;

public class ObAuthorizationRequest extends ObRequestCache<ObAuthorizationSessionCache> implements Serializable{
	
	 private String prod_cd;
	 private String value_date_from;
	 private String value_date_to;
	 private String upload_date_from;
	 private String upload_date_to;
	 private String amount_from;
	 private String amount_to;
	 private String debit_acct_no;
	 private String debit_acct_ccy;
	 private String transfer_ccy;
	 private List<String> source_trx;
	 private List<String> prodCodeList;
	 private String source_file_name;
	 private String upload_date;
	 private String bank_ref;
	 private String cust_ref;
	 private String on_behalf;
	 private String trx_status;
	 private String page_no;
    
	 private String page_size;
	 private List<AuthPayment> list_payment;
	 
	 private List<Transaction> list_trx;
	 
	 private String idTransaction;
	 private String action_cd;
	 
	 private int resendTagCount;
	 private String resendTagLastDate;
	 private String request_type;
	 private String device_id;
	 private String pymtMasterId;
	 
	 //Authorization Administration fields
	 private String userCode;
	 private String userName;
	 private String accountNumber;
	 private String accountName;
	 
	 private List<String> recordIds;
	 
	public String getProd_cd() {
		return prod_cd;
	}
	public void setProd_cd(String prod_cd) {
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
	public List<AuthPayment> getList_payment() {
		return list_payment;
	}
	public void setList_payment(List<AuthPayment> list_payment) {
		this.list_payment = list_payment;
	}
	
	public List<Transaction> getList_trx() {
		return list_trx;
	}
	public void setList_trx(List<Transaction> list_trx) {
		this.list_trx = list_trx;
	}
	public String getIdTransaction() {
		return idTransaction;
	}
	public void setIdTransaction(String idTransaction) {
		this.idTransaction = idTransaction;
	}
	public String getAction_cd() {
		return action_cd;
	}
	public void setAction_cd(String action_cd) {
		this.action_cd = action_cd;
	}
	public int getResendTagCount() {
		return resendTagCount;
	}
	public void setResendTagCount(int resendTagCount) {
		this.resendTagCount = resendTagCount;
	}
	public String getResendTagLastDate() {
		return resendTagLastDate;
	}
	public void setResendTagLastDate(String resendTagLastDate) {
		this.resendTagLastDate = resendTagLastDate;
	}
	public String getRequest_type() {
		return request_type;
	}
	public void setRequest_type(String request_type) {
		this.request_type = request_type;
	}
	public String getDevice_id() {
		return device_id;
	}
	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}
	public String getUpload_date() {
		return upload_date;
	}
	public void setUpload_date(String upload_date) {
		this.upload_date = upload_date;
	}
	public String getPymtMasterId() {
		return pymtMasterId;
	}
	public void setPymtMasterId(String pymtMasterId) {
		this.pymtMasterId = pymtMasterId;
	}
	public List<String> getProdCodeList() {
		return prodCodeList;
	}
	public void setProdCodeList(List<String> prodCodeList) {
		this.prodCodeList = prodCodeList;
	}
	public String getUpload_date_from() {
		return upload_date_from;
	}
	public String getUpload_date_to() {
		return upload_date_to;
	}
	public void setUpload_date_from(String upload_date_from) {
		this.upload_date_from = upload_date_from;
	}
	public void setUpload_date_to(String upload_date_to) {
		this.upload_date_to = upload_date_to;
	}
	public String getUserCode() {
		return userCode;
	}
	public String getUserName() {
		return userName;
	}
	public List<String> getRecordIds() {
		return recordIds;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setRecordIds(List<String> recordIds) {
		this.recordIds = recordIds;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	


}