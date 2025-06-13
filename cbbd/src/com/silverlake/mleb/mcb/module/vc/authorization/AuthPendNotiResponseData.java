package com.silverlake.mleb.mcb.module.vc.authorization;

import java.util.List;

import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;
import com.silverlake.mleb.mcb.module.vc.transaction.Transaction;

public class AuthPendNotiResponseData extends VCResponseData
{

	  private int total_rows;
	  private int total_records;
	  private List<TransactionNotification> trx_notif_list;
	  private List<CommonAuthorization> trxnotif_result_list;
	 
	  
	public int getTotal_rows() {
		return total_rows;
	}
	public void setTotal_rows(int total_rows) {
		this.total_rows = total_rows;
	}
	public int getTotal_records() {
		return total_records;
	}
	public void setTotal_records(int total_records) {
		this.total_records = total_records;
	}
	public List<TransactionNotification> getTrx_notif_list() {
		return trx_notif_list;
	}
	public void setTrx_notif_list(List<TransactionNotification> trx_notif_list) {
		this.trx_notif_list = trx_notif_list;
	}
	public List<CommonAuthorization> getTrxnotif_result_list() {
		return trxnotif_result_list;
	}
	public void setTrxnotif_result_list(List<CommonAuthorization> trxnotif_result_list) {
		this.trxnotif_result_list = trxnotif_result_list;
	}
	  
		
		
}



	
