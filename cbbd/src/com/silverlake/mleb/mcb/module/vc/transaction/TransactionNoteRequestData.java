package com.silverlake.mleb.mcb.module.vc.transaction;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

public class TransactionNoteRequestData extends VCRequest{
	private String pymt_master_id;//For transaction's note
	private String trx_note_id;//For delete transaction's note
	private String batch_id;//For upload's note
	private String upload_notes_id;//For delete upload's note
	
	//For add action
	private String remark;

	public String getPymt_master_id() {
		return pymt_master_id;
	}

	public void setPymt_master_id(String pymt_master_id) {
		this.pymt_master_id = pymt_master_id;
	}

	public String getBatch_id() {
		return batch_id;
	}

	public void setBatch_id(String batch_id) {
		this.batch_id = batch_id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTrx_note_id() {
		return trx_note_id;
	}

	public void setTrx_note_id(String trx_note_id) {
		this.trx_note_id = trx_note_id;
	}

	public String getUpload_notes_id() {
		return upload_notes_id;
	}

	public void setUpload_notes_id(String upload_notes_id) {
		this.upload_notes_id = upload_notes_id;
	}
}



	
