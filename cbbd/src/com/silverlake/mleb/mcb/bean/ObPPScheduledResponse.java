package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class ObPPScheduledResponse extends ObResponse implements Serializable {

	private static final long serialVersionUID = 4738018521098067169L;
	
	private List<ObPPScheduledBean> lsFuturePayment;
	
	private List<ObPPScheduledBean> lsRecurringPayment;

	public List<ObPPScheduledBean> getLsFuturePayment() {
		return lsFuturePayment;
	}

	public void setLsFuturePayment(List<ObPPScheduledBean> lsFuturePayment) {
		this.lsFuturePayment = lsFuturePayment;
	}

	public List<ObPPScheduledBean> getLsRecurringPayment() {
		return lsRecurringPayment;
	}

	public void setLsRecurringPayment(List<ObPPScheduledBean> lsRecurringPayment) {
		this.lsRecurringPayment = lsRecurringPayment;
	}

}
