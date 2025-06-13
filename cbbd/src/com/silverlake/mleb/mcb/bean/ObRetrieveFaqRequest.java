package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObRetrieveFaqRequest extends ObRequest implements Serializable
{
	private static final long serialVersionUID = -3822270038236396582L;


	private String faq_cat_cd;
	private String localCode;


	public String getFaq_cat_cd() {
		return faq_cat_cd;
	}


	public void setFaq_cat_cd(String faq_cat_cd) {
		this.faq_cat_cd = faq_cat_cd;
	}


	public String getLocalCode() {
		return localCode;
	}


	public void setLocalCode(String localCode) {
		this.localCode = localCode;
	}
	
	
}