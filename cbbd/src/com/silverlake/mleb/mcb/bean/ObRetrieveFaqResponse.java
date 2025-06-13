package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

import com.silverlake.mleb.mcb.bean.ListFaq;

public class ObRetrieveFaqResponse extends ObResponse implements Serializable
{
	private static final long serialVersionUID = -2950884870743426013L;
	
	private List<ListFaq> faq;

	public List<ListFaq> getFaq() {
		return faq;
	}

	public void setFaq(List<ListFaq> faq) {
		this.faq = faq;
	}
	
	
}
