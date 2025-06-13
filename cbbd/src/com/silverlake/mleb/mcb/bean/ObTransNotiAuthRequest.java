package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

import com.silverlake.mleb.mcb.module.vc.authorization.AcctAccess;
import com.silverlake.mleb.mcb.module.vc.authorization.AuthPolicy;
import com.silverlake.mleb.mcb.module.vc.authorization.SubscriberProfile;
import com.silverlake.mleb.mcb.module.vc.authorization.TransactionNotification;

 
public class ObTransNotiAuthRequest extends ObRequest implements Serializable {

	 	private String page_no;
	 	private String page_size;
	 	private String recordIds;
	  	private String action_cd;
	    private List<TransactionNotification> trxNotification_list;
	    
	    
		public String getPage_no() {
			return page_no;
		}
		public void setPage_no(String page_no) {
			this.page_no = page_no;
		}
		public String getPage_size() {
			return page_size;
		}
		public void setPage_size(String page_size) {
			this.page_size = page_size;
		}
		public String getRecordIds() {
			return recordIds;
		}
		public void setRecordIds(String recordIds) {
			this.recordIds = recordIds;
		}
		public String getAction_cd() {
			return action_cd;
		}
		public void setAction_cd(String action_cd) {
			this.action_cd = action_cd;
		}
		public List<TransactionNotification> getTrxNotification_list() {
			return trxNotification_list;
		}
		public void setTrxNotification_list(List<TransactionNotification> trxNotification_list) {
			this.trxNotification_list = trxNotification_list;
		}
	    
	    		 
}
