package com.silverlake.mleb.mcb.module.vc.authorization;

import java.io.Serializable;
import java.util.List;


public class TransactionNotificationDetail  implements Serializable
{
	  private String trx_type;
	  private String description;
	  private NotificationSetup notification_setup;
	  private List<AmountSetup> list_amount_setup;
	  
	  
	public String getTrx_type() {
		return trx_type;
	}

	public void setTrx_type(String trx_type) {
		this.trx_type = trx_type;
	}

	public NotificationSetup getNotification_setup() {
		return notification_setup;
	}

	public void setNotification_setup(NotificationSetup notification_setup) {
		this.notification_setup = notification_setup;
	}

	public List<AmountSetup> getList_amount_setup() {
		return list_amount_setup;
	}

	public void setList_amount_setup(List<AmountSetup> list_amount_setup) {
		this.list_amount_setup = list_amount_setup;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	  
		 
}



	
