package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class ObPaymentUnRegisteredBillerResponseBean extends ObResponse implements Serializable {

	private static final long serialVersionUID = 8657755970165821144L;
	
	private List<ObPaymentBillerGroupBean> lsBillerGroup;

	public List<ObPaymentBillerGroupBean> getLsBillerGroup() {
		return lsBillerGroup;
	}

	public void setLsBillerGroup(
			List<ObPaymentBillerGroupBean> lsBillerGroup) {
		this.lsBillerGroup = lsBillerGroup;
	}
	
	

}
