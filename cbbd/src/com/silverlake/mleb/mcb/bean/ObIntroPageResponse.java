package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class ObIntroPageResponse extends ObResponse implements Serializable {

	private static final long serialVersionUID = 742440922059104769L;
	
	private List<ObIntroPageBean> lsIntroPage;

	public List<ObIntroPageBean> getLsIntroPage() {
		return lsIntroPage;
	}

	public void setLsIntroPage(List<ObIntroPageBean> lsIntroPage) {
		this.lsIntroPage = lsIntroPage;
	}

}
