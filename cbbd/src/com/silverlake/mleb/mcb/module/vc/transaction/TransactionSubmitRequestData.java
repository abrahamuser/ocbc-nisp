package com.silverlake.mleb.mcb.module.vc.transaction;

import java.io.Serializable;
import java.math.BigDecimal;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

public class TransactionSubmitRequestData extends VCRequest implements Serializable {
	  
	private String prod_cd;
	private String bank_ref;
	private String customer_ref;
	private String debit_acct_no;
	private String debit_acct_ccy;
	private String sender_name;
	private String sender_address1;
	private String sender_address2;
	private String sender_address3;
	private String bene_acct_no;
	private String bene_acct_ccy;
	private String bene_acct_mc_bit;
	private String bene_acct_type;
	private String bene_acct_branch;
	private String bene_acct_status;
	private String bene_name;
	private String bene_address1;
	private String bene_address2;
	private String bene_address3;
	private String bene_bank_code;
	private String bene_bank_name;
	private String bene_bank_network_id;
	private String bene_bank_branch;
	private String bene_bank_city;
	private String bene_bank_country;
	private String bene_bank_address;
	private String bene_bank_address2;
	private String bene_bank_address3;
	private String value_date;
	private BigDecimal amount;
	private String amount_ccy;
	private String remarks;
	private String fx_type;
	private BigDecimal fx_rate;
	private String fx_dealer_name;
	private String fx_contract_no;
	private BigDecimal fx_dealer_rate;
	private Boolean save_bene;
	private String is_favorite_bene;
	private String bene_nick_name;
	private String bene_email;
	private String bene_phone;
	private Boolean check_customer_ref;
	private Boolean check_transfer_amount;
	private Boolean save_favorite;
	private Boolean save_template;
	private Boolean send_notif_sender;
	private Boolean notif_sender_completed;
	private Boolean notif_sender_rejected;
	private Boolean notif_sender_suspected;
	private String notif_sender_email;
	private String notif_sender_phone;
	private String notif_transaction_status;
	private Boolean send_notif_bene;
	private String notif_bene_email;
	private String notif_bene_phone;
	private Boolean save_recurring;
	private String recurring_type;
	private String recurring_value;
	private String recurring_start;
	private String recurring_end;
	private String resident_status;
	private String remitter_country_residence;
	private String remitter_category;
	private String bene_country_residence;
	private String bene_category;
	private String bene_affiliation_status;
	private String payment_purpose;
	private String swift_charges_method;
	private String underlying_type;
	private String underlying_doc;
	private String session_id;
	private String bill_ref_no;
	private String response_data;
	private String bene_id;
	private String validation_check;
	private String exec_time_batch_cd;
	private String recurring_exec_time_batch_cd;
	private String additional_info;
	private String proxy_type;
	private String proxy_data;
	private String charges_acct_no;
	private String charges_acct_ccy;
	private String trx_purpose;
	private InquiryBIFastData bifast_data;
	private String device_id;
	private String device_type;
	private String device_os;
	
	public String getProd_cd() {
		return prod_cd;
	}
	public void setProd_cd(String prod_cd) {
		this.prod_cd = prod_cd;
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
	public String getSender_name() {
		return sender_name;
	}
	public void setSender_name(String sender_name) {
		this.sender_name = sender_name;
	}
	public String getSender_address1() {
		return sender_address1;
	}
	public void setSender_address1(String sender_address1) {
		this.sender_address1 = sender_address1;
	}
	public String getSender_address2() {
		return sender_address2;
	}
	public void setSender_address2(String sender_address2) {
		this.sender_address2 = sender_address2;
	}
	public String getSender_address3() {
		return sender_address3;
	}
	public void setSender_address3(String sender_address3) {
		this.sender_address3 = sender_address3;
	}
	public String getBene_acct_no() {
		return bene_acct_no;
	}
	public void setBene_acct_no(String bene_acct_no) {
		this.bene_acct_no = bene_acct_no;
	}
	public String getBene_acct_ccy() {
		return bene_acct_ccy;
	}
	public void setBene_acct_ccy(String bene_acct_ccy) {
		this.bene_acct_ccy = bene_acct_ccy;
	}
	public String getBene_acct_mc_bit() {
		return bene_acct_mc_bit;
	}
	public void setBene_acct_mc_bit(String bene_acct_mc_bit) {
		this.bene_acct_mc_bit = bene_acct_mc_bit;
	}
	public String getBene_acct_type() {
		return bene_acct_type;
	}
	public void setBene_acct_type(String bene_acct_type) {
		this.bene_acct_type = bene_acct_type;
	}
	public String getBene_acct_branch() {
		return bene_acct_branch;
	}
	public void setBene_acct_branch(String bene_acct_branch) {
		this.bene_acct_branch = bene_acct_branch;
	}
	public String getBene_acct_status() {
		return bene_acct_status;
	}
	public void setBene_acct_status(String bene_acct_status) {
		this.bene_acct_status = bene_acct_status;
	}
	public String getBene_name() {
		return bene_name;
	}
	public void setBene_name(String bene_name) {
		this.bene_name = bene_name;
	}
	public String getBene_address1() {
		return bene_address1;
	}
	public void setBene_address1(String bene_address1) {
		this.bene_address1 = bene_address1;
	}
	public String getBene_address2() {
		return bene_address2;
	}
	public void setBene_address2(String bene_address2) {
		this.bene_address2 = bene_address2;
	}
	public String getBene_address3() {
		return bene_address3;
	}
	public void setBene_address3(String bene_address3) {
		this.bene_address3 = bene_address3;
	}
	public String getBene_bank_code() {
		return bene_bank_code;
	}
	public void setBene_bank_code(String bene_bank_code) {
		this.bene_bank_code = bene_bank_code;
	}
	public String getBene_bank_name() {
		return bene_bank_name;
	}
	public void setBene_bank_name(String bene_bank_name) {
		this.bene_bank_name = bene_bank_name;
	}
	public String getBene_bank_network_id() {
		return bene_bank_network_id;
	}
	public void setBene_bank_network_id(String bene_bank_network_id) {
		this.bene_bank_network_id = bene_bank_network_id;
	}
	public String getBene_bank_branch() {
		return bene_bank_branch;
	}
	public void setBene_bank_branch(String bene_bank_branch) {
		this.bene_bank_branch = bene_bank_branch;
	}
	public String getBene_bank_country() {
		return bene_bank_country;
	}
	public void setBene_bank_country(String bene_bank_country) {
		this.bene_bank_country = bene_bank_country;
	}
	public String getBene_bank_address() {
		return bene_bank_address;
	}
	public void setBene_bank_address(String bene_bank_address) {
		this.bene_bank_address = bene_bank_address;
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
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getFx_type() {
		return fx_type;
	}
	public void setFx_type(String fx_type) {
		this.fx_type = fx_type;
	}
	public BigDecimal getFx_rate() {
		return fx_rate;
	}
	public void setFx_rate(BigDecimal fx_rate) {
		this.fx_rate = fx_rate;
	}
	public String getFx_dealer_name() {
		return fx_dealer_name;
	}
	public void setFx_dealer_name(String fx_dealer_name) {
		this.fx_dealer_name = fx_dealer_name;
	}
	public String getFx_contract_no() {
		return fx_contract_no;
	}
	public void setFx_contract_no(String fx_contract_no) {
		this.fx_contract_no = fx_contract_no;
	}
	public BigDecimal getFx_dealer_rate() {
		return fx_dealer_rate;
	}
	public void setFx_dealer_rate(BigDecimal fx_dealer_rate) {
		this.fx_dealer_rate = fx_dealer_rate;
	}
	public Boolean getSave_bene() {
		return save_bene;
	}
	public void setSave_bene(Boolean save_bene) {
		this.save_bene = save_bene;
	}
	public String getBene_nick_name() {
		return bene_nick_name;
	}
	public void setBene_nick_name(String bene_nick_name) {
		this.bene_nick_name = bene_nick_name;
	}
	public String getBene_email() {
		return bene_email;
	}
	public void setBene_email(String bene_email) {
		this.bene_email = bene_email;
	}
	public String getBene_phone() {
		return bene_phone;
	}
	public void setBene_phone(String bene_phone) {
		this.bene_phone = bene_phone;
	}
	public Boolean getCheck_customer_ref() {
		return check_customer_ref;
	}
	public void setCheck_customer_ref(Boolean check_customer_ref) {
		this.check_customer_ref = check_customer_ref;
	}
	public Boolean getCheck_transfer_amount() {
		return check_transfer_amount;
	}
	public void setCheck_transfer_amount(Boolean check_transfer_amount) {
		this.check_transfer_amount = check_transfer_amount;
	}
	public Boolean getSave_favorite() {
		return save_favorite;
	}
	public void setSave_favorite(Boolean save_favorite) {
		this.save_favorite = save_favorite;
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
	public String getNotif_transaction_status() {
		return notif_transaction_status;
	}
	public void setNotif_transaction_status(String notif_transaction_status) {
		this.notif_transaction_status = notif_transaction_status;
	}
	public Boolean getSend_notif_bene() {
		return send_notif_bene;
	}
	public void setSend_notif_bene(Boolean send_notif_bene) {
		this.send_notif_bene = send_notif_bene;
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
	public String getResident_status() {
		return resident_status;
	}
	public void setResident_status(String resident_status) {
		this.resident_status = resident_status;
	}
	public String getRemitter_country_residence() {
		return remitter_country_residence;
	}
	public void setRemitter_country_residence(String remitter_country_residence) {
		this.remitter_country_residence = remitter_country_residence;
	}
	public String getRemitter_category() {
		return remitter_category;
	}
	public void setRemitter_category(String remitter_category) {
		this.remitter_category = remitter_category;
	}
	public String getBene_country_residence() {
		return bene_country_residence;
	}
	public void setBene_country_residence(String bene_country_residence) {
		this.bene_country_residence = bene_country_residence;
	}
	public String getBene_category() {
		return bene_category;
	}
	public void setBene_category(String bene_category) {
		this.bene_category = bene_category;
	}
	public String getBene_affiliation_status() {
		return bene_affiliation_status;
	}
	public void setBene_affiliation_status(String bene_affiliation_status) {
		this.bene_affiliation_status = bene_affiliation_status;
	}
	public String getPayment_purpose() {
		return payment_purpose;
	}
	public void setPayment_purpose(String payment_purpose) {
		this.payment_purpose = payment_purpose;
	}
	public String getSwift_charges_method() {
		return swift_charges_method;
	}
	public void setSwift_charges_method(String swift_charges_method) {
		this.swift_charges_method = swift_charges_method;
	}
	public String getUnderlying_type() {
		return underlying_type;
	}
	public void setUnderlying_type(String underlying_type) {
		this.underlying_type = underlying_type;
	}
	public String getUnderlying_doc() {
		return underlying_doc;
	}
	public void setUnderlying_doc(String underlying_doc) {
		this.underlying_doc = underlying_doc;
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
	public String getNotif_bene_email() {
		return notif_bene_email;
	}
	public void setNotif_bene_email(String notif_bene_email) {
		this.notif_bene_email = notif_bene_email;
	}
	public String getNotif_bene_phone() {
		return notif_bene_phone;
	}
	public void setNotif_bene_phone(String notif_bene_phone) {
		this.notif_bene_phone = notif_bene_phone;
	}
	public String getBank_ref() {
		return bank_ref;
	}
	public void setBank_ref(String bank_ref) {
		this.bank_ref = bank_ref;
	}
	public String getBene_bank_address2() {
		return bene_bank_address2;
	}
	public void setBene_bank_address2(String bene_bank_address2) {
		this.bene_bank_address2 = bene_bank_address2;
	}
	public String getBene_bank_address3() {
		return bene_bank_address3;
	}
	public void setBene_bank_address3(String bene_bank_address3) {
		this.bene_bank_address3 = bene_bank_address3;
	}
	public String getBene_bank_city() {
		return bene_bank_city;
	}
	public void setBene_bank_city(String bene_bank_city) {
		this.bene_bank_city = bene_bank_city;
	}
	public String getIs_favorite_bene() {
		return is_favorite_bene;
	}
	public void setIs_favorite_bene(String is_favorite_bene) {
		this.is_favorite_bene = is_favorite_bene;
	}
	public String getBene_id() {
		return bene_id;
	}
	public void setBene_id(String bene_id) {
		this.bene_id = bene_id;
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
	public String getAdditional_info() {
		return additional_info;
	}
	public void setAdditional_info(String additional_info) {
		this.additional_info = additional_info;
	}
	public String getProxy_type() {
		return proxy_type;
	}
	public void setProxy_type(String proxy_type) {
		this.proxy_type = proxy_type;
	}
	public String getProxy_data() {
		return proxy_data;
	}
	public void setProxy_data(String proxy_data) {
		this.proxy_data = proxy_data;
	}
	public String getCharges_acct_no() {
		return charges_acct_no;
	}
	public void setCharges_acct_no(String charges_acct_no) {
		this.charges_acct_no = charges_acct_no;
	}
	public String getCharges_acct_ccy() {
		return charges_acct_ccy;
	}
	public void setCharges_acct_ccy(String charges_acct_ccy) {
		this.charges_acct_ccy = charges_acct_ccy;
	}
	public String getTrx_purpose() {
		return trx_purpose;
	}
	public void setTrx_purpose(String trx_purpose) {
		this.trx_purpose = trx_purpose;
	}
	public InquiryBIFastData getBifast_data() {
		return bifast_data;
	}
	public void setBifast_data(InquiryBIFastData bifast_data) {
		this.bifast_data = bifast_data;
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
	
		
}



	
