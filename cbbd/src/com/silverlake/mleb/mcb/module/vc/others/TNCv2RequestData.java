package com.silverlake.mleb.mcb.module.vc.others;

import java.util.List;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

public class TNCv2RequestData extends VCRequest {
	private String tnc_type;
	private String txn_reference_no;
	private File file;
	private String file_type;
	private String ccy_code;
	
	private List<File> file_list ;

	public String getTnc_type() {
		return tnc_type;
	}

	public void setTnc_type(String tnc_type) {
		this.tnc_type = tnc_type;
	}

	public String getTxn_reference_no() {
		return txn_reference_no;
	}

	public void setTxn_reference_no(String txn_reference_no) {
		this.txn_reference_no = txn_reference_no;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public List<File> getFile_list() {
		return file_list;
	}

	public void setFile_list(List<File> file_list) {
		this.file_list = file_list;
	}

	public String getFile_type() {
		return file_type;
	}

	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}

	public String getCcy_code() {
		return ccy_code;
	}

	public void setCcy_code(String ccy_code) {
		this.ccy_code = ccy_code;
	}	
	
}