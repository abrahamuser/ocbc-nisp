package com.silverlake.mleb.mcb.module.vc.transaction;

import java.math.BigDecimal;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

/**
 * @author hemanth
 *
 */
public class PaymentSubmitRequestData extends VCRequest{
	private String prod_cd;
	private String bank_ref;
	private String customer_ref;
	private String debit_acct_no;
	private String debit_acct_ccy;
	private String billing_id;
	private String billing_org_id;
	private String billing_org_name;
	private String value_date;
	private BigDecimal amount;
	private String amount_ccy;
	private Boolean save_template;
	private String template_name;
	private Boolean send_notif_sender;
	private Boolean notif_sender_completed;
	private Boolean notif_sender_rejected;
	private Boolean notif_sender_suspected;
	private String notif_sender_email;
	private String notif_sender_phone;
	private Boolean save_recurring;
	private String recurring_type;
	private String recurring_value;
	private String recurring_start;
	private String recurring_end;
	private String session_id;
	private String bill_ref_no;
	private String response_data;
	private String validation_check;
	private String exec_time_batch_cd;
	private String recurring_exec_time_batch_cd;
	private String device_id;
	private String device_type;
	private String device_os;
	private String lang;
	
	public String getProd_cd() {
		return prod_cd;
	}
	public void setProd_cd(String prod_cd) {
		this.prod_cd = prod_cd;
	}
	public String getBank_ref() {
		return bank_ref;
	}
	public void setBank_ref(String bank_ref) {
		this.bank_ref = bank_ref;
	}
	public String getCustomer_ref() {
		return customer_ref;
	}
	public void setCustomer_ref(String customer_ref) {
		this.customer_ref = customer_ref;
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
	public String getBilling_id() {
		return billing_id;
	}
	public void setBilling_id(String billing_id) {
		this.billing_id = billing_id;
	}
	public String getBilling_org_id() {
		return billing_org_id;
	}
	public void setBilling_org_id(String billing_org_id) {
		this.billing_org_id = billing_org_id;
	}
	public String getBilling_org_name() {
		return billing_org_name;
	}
	public void setBilling_org_name(String billing_org_name) {
		this.billing_org_name = billing_org_name;
	}
	public String getValue_date() {
		return value_date;
	}
	public void setValue_date(String value_date) {
		this.value_date = value_date;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getAmount_ccy() {
		return amount_ccy;
	}
	public void setAmount_ccy(String amount_ccy) {
		this.amount_ccy = amount_ccy;
	}
	public Boolean getSave_template() {
		return save_template;
	}
	public void setSave_template(Boolean save_template) {
		this.save_template = save_template;
	}
	public Boolean getSend_notif_sender() {
		return send_notif_sender;
	}
	public void setSend_notif_sender(Boolean send_notif_sender) {
		this.send_notif_sender = send_notif_sender;
	}
	public Boolean getNotif_sender_completed() {
		return notif_sender_completed;
	}
	public void setNotif_sender_completed(Boolean notif_sender_completed) {
		this.notif_sender_completed = notif_sender_completed;
	}
	public Boolean getNotif_sender_rejected() {
		return notif_sender_rejected;
	}
	public void setNotif_sender_rejected(Boolean notif_sender_rejected) {
		this.notif_sender_rejected = notif_sender_rejected;
	}
	public Boolean getNotif_sender_suspected() {
		return notif_sender_suspected;
	}
	public void setNotif_sender_suspected(Boolean notif_sender_suspected) {
		this.notif_sender_suspected = notif_sender_suspected;
	}
	public String getNotif_sender_email() {
		return notif_sender_email;
	}
	public void setNotif_sender_email(String notif_sender_email) {
		this.notif_sender_email = notif_sender_email;
	}
	public String getNotif_sender_phone() {
		return notif_sender_phone;
	}
	public void setNotif_sender_phone(String notif_sender_phone) {
		this.notif_sender_phone = notif_sender_phone;
	}
	public Boolean getSave_recurring() {
		return save_recurring;
	}
	public void setSave_recurring(Boolean save_recurring) {
		this.save_recurring = save_recurring;
	}
	public String getRecurring_type() {
		return recurring_type;
	}
	public void setRecurring_type(String recurring_type) {
		this.recurring_type = recurring_type;
	}
	public String getRecurring_value() {
		return recurring_value;
	}
	public void setRecurring_value(String recurring_value) {
		this.recurring_value = recurring_value;
	}
	public String getRecurring_start() {
		return recurring_start;
	}
	public void setRecurring_start(String recurring_start) {
		this.recurring_start = recurring_start;
	}
	public String getRecurring_end() {
		return recurring_end;
	}
	public void setRecurring_end(String recurring_end) {
		this.recurring_end = recurring_end;
	}
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	public String getBill_ref_no() {
		return bill_ref_no;
	}
	public void setBill_ref_no(String bill_ref_no) {
		this.bill_ref_no = bill_ref_no;
	}
	public String getResponse_data() {
		return response_data;
	}
	public void setResponse_data(String response_data) {
		this.response_data = response_data;
	}
	public String getTemplate_name() {
		return template_name;
	}
	public void setTemplate_name(String template_name) {
		this.template_name = template_name;
	}
	public String getValidation_check() {
		return validation_check;
	}
	public void setValidation_check(String validation_check) {
		this.validation_check = validation_check;
	}
	public String getExec_time_batch_cd() {
		return exec_time_batch_cd;
	}
	public void setExec_time_batch_cd(String exec_time_batch_cd) {
		this.exec_time_batch_cd = exec_time_batch_cd;
	}
	public String getRecurring_exec_time_batch_cd() {
		return recurring_exec_time_batch_cd;
	}
	public void setRecurring_exec_time_batch_cd(String recurring_exec_time_batch_cd) {
		this.recurring_exec_time_batch_cd = recurring_exec_time_batch_cd;
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
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	
}
