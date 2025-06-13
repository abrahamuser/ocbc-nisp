package com.silverlake.mleb.mcb.module.vc.transaction;

import java.io.Serializable;
import java.math.BigDecimal;

public class Transaction  implements Serializable
{
	  
	private String pymt_master_id;
	private String org_cd;
	private String prod_cd;
	private String bank_ref;
	private String customer_ref;
	private String debit_acct_no;
	private String debit_acct_ccy;
	private String sender_name;
	private String bene_acct_no;
	private String bene_acct_ccy;
	private String bene_name;
	private String bene_nick_name;
	private String bene_bank_code;
	private String bene_bank_name;
	private String value_date;
	private BigDecimal amount;
	private String amount_ccy;
	private int total_item;
	private String trx_status;
	private String trx_status_desc;
	private String fx_type;
	private BigDecimal fx_rate;
	private String fx_dealer_name;
	private String fx_contract_no;
	private String fx_dealer_rate;
	private String billing_id;
	private String billing_org_id;
	private String billing_org_name;
	private String trx_source;
	private String trx_origin;
	private String file_name;
	private String upload_date;
	private String upload_format;
	private String upload_format_desc;
	private String interest_rate;
	private int tenor;
	private String tenor_code;
	private int interest_term;
	private String interest_term_code;
	private String ao_effective_date;
	private String ao_maturity_date;
	private String rollover_type;
	private String rollover_type_desc;
	private String rollover_type_new;
	private String rollover_type_new_desc;
	private String ao_product_code;
	private String ao_product_type;
	private String created_by;
	private String time_created;
	private String updated_by;
	private String time_updated;
	private String ntpn;
	private String ntb;
	private String upload_origin;
	private String index;
	private String prod_desc;
	private String bene_bank_branch;
	private String bene_bank_city;
	private String bene_bank_country;
	private String start_collect_date;
	private String end_collect_date;
	private Boolean save_recurring;
	private String recurring_type;
	private String recurring_value;
	private String payment_purpose;
	private String remarks;
	private BigDecimal target_balance_amount;
	private String target_balance_ccy;
	
	private String map;
	private String kjs;
	private String mir;
	private String sor;
	
	//Non transactional fields
	private String task_id;
	private String task_name;
	private String count;
	
	private int version;
	
	//For onfx product type usage (not from omni, BE generated)
	private BigDecimal sellingAmount;
	private BigDecimal buyingAmount;
	
	private String trx_source_link_id;
	private String debit_acct_name;
	private String exec_time_batch_cd;
	private String remittance_no;
	
	private String recurring_start;
	private String recurring_end;
	private String additional_info;
	
	
	public String getAdditional_info() {
		return additional_info;
	}
	public void setAdditional_info(String additional_info) {
		this.additional_info = additional_info;
	}
	public String getPymt_master_id() {
		return pymt_master_id;
	}
	public void setPymt_master_id(String pymt_master_id) {
		this.pymt_master_id = pymt_master_id;
	}
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
	public String getSender_name() {
		return sender_name;
	}
	public void setSender_name(String sender_name) {
		this.sender_name = sender_name;
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
	public int getTotal_item() {
		return total_item;
	}
	public void setTotal_item(int total_item) {
		this.total_item = total_item;
	}
	public String getTrx_status() {
		return trx_status;
	}
	public void setTrx_status(String trx_status) {
		this.trx_status = trx_status;
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
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getOrg_cd() {
		return org_cd;
	}
	public void setOrg_cd(String org_cd) {
		this.org_cd = org_cd;
	}
	public String getBene_name() {
		return bene_name;
	}
	public void setBene_name(String bene_name) {
		this.bene_name = bene_name;
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
	public String getTrx_source() {
		return trx_source;
	}
	public void setTrx_source(String trx_source) {
		this.trx_source = trx_source;
	}
	public String getTrx_origin() {
		return trx_origin;
	}
	public void setTrx_origin(String trx_origin) {
		this.trx_origin = trx_origin;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getUpload_date() {
		return upload_date;
	}
	public void setUpload_date(String upload_date) {
		this.upload_date = upload_date;
	}
	public String getUpload_format() {
		return upload_format;
	}
	public void setUpload_format(String upload_format) {
		this.upload_format = upload_format;
	}
	public String getInterest_rate() {
		return interest_rate;
	}
	public void setInterest_rate(String interest_rate) {
		this.interest_rate = interest_rate;
	}
	public int getTenor() {
		return tenor;
	}
	public void setTenor(int tenor) {
		this.tenor = tenor;
	}
	public String getTenor_code() {
		return tenor_code;
	}
	public void setTenor_code(String tenor_code) {
		this.tenor_code = tenor_code;
	}
	public int getInterest_term() {
		return interest_term;
	}
	public void setInterest_term(int interest_term) {
		this.interest_term = interest_term;
	}
	public String getInterest_term_code() {
		return interest_term_code;
	}
	public void setInterest_term_code(String interest_term_code) {
		this.interest_term_code = interest_term_code;
	}
	public String getAo_effective_date() {
		return ao_effective_date;
	}
	public void setAo_effective_date(String ao_effective_date) {
		this.ao_effective_date = ao_effective_date;
	}
	public String getAo_maturity_date() {
		return ao_maturity_date;
	}
	public void setAo_maturity_date(String ao_maturity_date) {
		this.ao_maturity_date = ao_maturity_date;
	}
	public String getRollover_type() {
		return rollover_type;
	}
	public void setRollover_type(String rollover_type) {
		this.rollover_type = rollover_type;
	}
	public String getRollover_type_new() {
		return rollover_type_new;
	}
	public void setRollover_type_new(String rollover_type_new) {
		this.rollover_type_new = rollover_type_new;
	}
	public String getAo_product_code() {
		return ao_product_code;
	}
	public void setAo_product_code(String ao_product_code) {
		this.ao_product_code = ao_product_code;
	}
	public String getAo_product_type() {
		return ao_product_type;
	}
	public void setAo_product_type(String ao_product_type) {
		this.ao_product_type = ao_product_type;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public String getTime_created() {
		return time_created;
	}
	public void setTime_created(String time_created) {
		this.time_created = time_created;
	}
	public String getUpdated_by() {
		return updated_by;
	}
	public void setUpdated_by(String updated_by) {
		this.updated_by = updated_by;
	}
	public String getTime_updated() {
		return time_updated;
	}
	public void setTime_updated(String time_updated) {
		this.time_updated = time_updated;
	}
	public String getNtpn() {
		return ntpn;
	}
	public void setNtpn(String ntpn) {
		this.ntpn = ntpn;
	}
	public String getNtb() {
		return ntb;
	}
	public void setNtb(String ntb) {
		this.ntb = ntb;
	}
	public String getUpload_origin() {
		return upload_origin;
	}
	public void setUpload_origin(String upload_origin) {
		this.upload_origin = upload_origin;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getProd_desc() {
		return prod_desc;
	}
	public void setProd_desc(String prod_desc) {
		this.prod_desc = prod_desc;
	}
	public String getBene_bank_branch() {
		return bene_bank_branch;
	}
	public void setBene_bank_branch(String bene_bank_branch) {
		this.bene_bank_branch = bene_bank_branch;
	}
	public String getBene_bank_city() {
		return bene_bank_city;
	}
	public void setBene_bank_city(String bene_bank_city) {
		this.bene_bank_city = bene_bank_city;
	}
	public String getBene_bank_country() {
		return bene_bank_country;
	}
	public void setBene_bank_country(String bene_bank_country) {
		this.bene_bank_country = bene_bank_country;
	}
	public String getStart_collect_date() {
		return start_collect_date;
	}
	public void setStart_collect_date(String start_collect_date) {
		this.start_collect_date = start_collect_date;
	}
	public String getEnd_collect_date() {
		return end_collect_date;
	}
	public void setEnd_collect_date(String end_collect_date) {
		this.end_collect_date = end_collect_date;
	}
	public String getUpload_format_desc() {
		return upload_format_desc;
	}
	public void setUpload_format_desc(String upload_format_desc) {
		this.upload_format_desc = upload_format_desc;
	}
	public String getTrx_status_desc() {
		return trx_status_desc;
	}
	public void setTrx_status_desc(String trx_status_desc) {
		this.trx_status_desc = trx_status_desc;
	}
	public String getTask_id() {
		return task_id;
	}
	public void setTask_id(String task_id) {
		this.task_id = task_id;
	}
	public String getTask_name() {
		return task_name;
	}
	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}

	public String getFx_dealer_rate() {
		return fx_dealer_rate;
	}

	public void setFx_dealer_rate(String fx_dealer_rate) {
		this.fx_dealer_rate = fx_dealer_rate;
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

	public String getPayment_purpose() {
		return payment_purpose;
	}

	public void setPayment_purpose(String payment_purpose) {
		this.payment_purpose = payment_purpose;
	}
	public String getRollover_type_desc() {
		return rollover_type_desc;
	}
	public void setRollover_type_desc(String rollover_type_desc) {
		this.rollover_type_desc = rollover_type_desc;
	}
	public String getRollover_type_new_desc() {
		return rollover_type_new_desc;
	}
	public void setRollover_type_new_desc(String rollover_type_new_desc) {
		this.rollover_type_new_desc = rollover_type_new_desc;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public BigDecimal getTarget_balance_amount() {
		return target_balance_amount;
	}
	public void setTarget_balance_amount(BigDecimal target_balance_amount) {
		this.target_balance_amount = target_balance_amount;
	}
	public String getTarget_balance_ccy() {
		return target_balance_ccy;
	}
	public void setTarget_balance_ccy(String target_balance_ccy) {
		this.target_balance_ccy = target_balance_ccy;
	}
	public BigDecimal getSellingAmount() {
		return sellingAmount;
	}
	public BigDecimal getBuyingAmount() {
		return buyingAmount;
	}
	public void setSellingAmount(BigDecimal sellingAmount) {
		this.sellingAmount = sellingAmount;
	}
	public void setBuyingAmount(BigDecimal buyingAmount) {
		this.buyingAmount = buyingAmount;
	}
	public String getBene_nick_name() {
		return bene_nick_name;
	}
	public void setBene_nick_name(String bene_nick_name) {
		this.bene_nick_name = bene_nick_name;
	}
	public String getMap() {
		return map;
	}
	public void setMap(String map) {
		this.map = map;
	}
	public String getKjs() {
		return kjs;
	}
	public void setKjs(String kjs) {
		this.kjs = kjs;
	}
	public String getMir() {
		return mir;
	}
	public void setMir(String mir) {
		this.mir = mir;
	}
	public String getSor() {
		return sor;
	}
	public void setSor(String sor) {
		this.sor = sor;
	}
	public String getTrx_source_link_id() {
		return trx_source_link_id;
	}
	public void setTrx_source_link_id(String trx_source_link_id) {
		this.trx_source_link_id = trx_source_link_id;
	}
	public String getDebit_acct_name() {
		return debit_acct_name;
	}
	public void setDebit_acct_name(String debit_acct_name) {
		this.debit_acct_name = debit_acct_name;
	}
	public String getExec_time_batch_cd() {
		return exec_time_batch_cd;
	}
	public void setExec_time_batch_cd(String exec_time_batch_cd) {
		this.exec_time_batch_cd = exec_time_batch_cd;
	}
	public String getRemittance_no() {
		return remittance_no;
	}
	public void setRemittance_no(String remittance_no) {
		this.remittance_no = remittance_no;
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
	
	
}



	
