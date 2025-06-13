package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class ObRedirectURLsResponse extends ObResponse implements Serializable
{
	private List<ObRedirectURLsBean> obRedirectURLsBean;

	public List<ObRedirectURLsBean> getObRedirectURLsBean() {
		return obRedirectURLsBean;
	}

	public void setObRedirectURLsBean(List<ObRedirectURLsBean> obRedirectURLsBean) {
		this.obRedirectURLsBean = obRedirectURLsBean;
	}
}
