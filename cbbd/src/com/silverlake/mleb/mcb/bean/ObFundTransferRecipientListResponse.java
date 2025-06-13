package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class ObFundTransferRecipientListResponse extends ObResponse implements Serializable {
	
	private static final long serialVersionUID = -8736078206993230303L;
	
	private List<ObBeneAccountBean> obRecipientIntraAccList;
	private List<ObBeneAccountBean> obRecipientInterAccList;
	
	public List<ObBeneAccountBean> getObRecipientIntraAccList() {
		return obRecipientIntraAccList;
	}
	public void setObRecipientIntraAccList(
			List<ObBeneAccountBean> obRecipientIntraAccList) {
		this.obRecipientIntraAccList = obRecipientIntraAccList;
	}
	public List<ObBeneAccountBean> getObRecipientInterAccList() {
		return obRecipientInterAccList;
	}
	public void setObRecipientInterAccList(
			List<ObBeneAccountBean> obRecipientInterAccList) {
		this.obRecipientInterAccList = obRecipientInterAccList;
	}

}
