package com.silverlake.mleb.mcb.module.vc.transaction;

import java.io.Serializable;
import java.math.BigDecimal;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

public class TransactionStatusUpdateRequestData extends VCRequest implements Serializable {
	  
	private String prod_cd;
	private String source_trx;
	private String version;
	private String pymt_master_id;
	private String trx_status;
	private String validation_check;
	private String device_type;
    private String device_os;
	
	public String getProd_cd() {
		return prod_cd;
	}
	public void setProd_cd(String prod_cd) {
		this.prod_cd = prod_cd;
	}
	public String getSource_trx() {
		return source_trx;
	}
	public void setSource_trx(String source_trx) {
		this.source_trx = source_trx;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getPymt_master_id() {
		return pymt_master_id;
	}
	public void setPymt_master_id(String pymt_master_id) {
		this.pymt_master_id = pymt_master_id;
	}
	public String getTrx_status() {
		return trx_status;
	}
	public void setTrx_status(String trx_status) {
		this.trx_status = trx_status;
	}
	public String getValidation_check() {
		return validation_check;
	}
	public void setValidation_check(String validation_check) {
		this.validation_check = validation_check;
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



	
