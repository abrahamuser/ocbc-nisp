package com.silverlake.mleb.mcb.module.vc.others;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

public class TNCRequestData extends VCRequest {
	private String tnc_type;
	private String txn_reference_no;
	private File file;

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
}