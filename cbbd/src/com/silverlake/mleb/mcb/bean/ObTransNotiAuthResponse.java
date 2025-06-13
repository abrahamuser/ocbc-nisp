package com.silverlake.mleb.mcb.bean;


import java.io.Serializable;
import java.util.List;

import com.silverlake.mleb.mcb.module.vc.authorization.AcctAccess;
import com.silverlake.mleb.mcb.module.vc.authorization.AuthPolicy;
import com.silverlake.mleb.mcb.module.vc.authorization.CommonAuthorization;
import com.silverlake.mleb.mcb.module.vc.authorization.SubscriberAuthResult;
import com.silverlake.mleb.mcb.module.vc.authorization.SubscriberProfile;
import com.silverlake.mleb.mcb.module.vc.authorization.TransactionNotification;

public class ObTransNotiAuthResponse extends ObResponse implements Serializable{
    
	private int total_records;
	private List<TransactionNotification> trxNotification_list;
	private List<CommonAuthorization> trxNotifResult_list;
		
	public int getTotal_records() {
		return total_records;
	}
	public void setTotal_records(int total_records) {
		this.total_records = total_records;
	}
	public List<TransactionNotification> getTrxNotification_list() {
		return trxNotification_list;
	}
	public void setTrxNotification_list(List<TransactionNotification> trxNotification_list) {
		this.trxNotification_list = trxNotification_list;
	}
	public List<CommonAuthorization> getTrxNotifResult_list() {
		return trxNotifResult_list;
	}
	public void setTrxNotifResult_list(List<CommonAuthorization> trxNotifResult_list) {
		this.trxNotifResult_list = trxNotifResult_list;
	}
	   
	
	
}
