package com.silverlake.mleb.mcb.module.vc.transaction;

import java.util.List;

import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;

public class TransactionNoteResponseData extends VCResponseData{
	private List<TransactionNote> list_txn_notes;//For transaction's notes
	private List<TransactionNote> list_upload_notes;//For upload's notes

	public List<TransactionNote> getList_txn_notes() {
		return list_txn_notes;
	}

	public void setList_txn_notes(List<TransactionNote> list_txn_notes) {
		this.list_txn_notes = list_txn_notes;
	}

	public List<TransactionNote> getList_upload_notes() {
		return list_upload_notes;
	}

	public void setList_upload_notes(List<TransactionNote> list_upload_notes) {
		this.list_upload_notes = list_upload_notes;
	}
}



	
