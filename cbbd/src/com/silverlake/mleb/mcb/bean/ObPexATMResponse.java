package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;


public class ObPexATMResponse extends ObResponse implements Serializable
{
	
	private List<ObAccountBean> obAccDetailBean;
	private ObPexATMBean ObPexATMBean;

	
	public ObPexATMBean getObPexATMBean() {
		return ObPexATMBean;
	}
	public void setObPexATMBean(ObPexATMBean obPexATMBean) {
		ObPexATMBean = obPexATMBean;
	}
	public List<ObAccountBean> getObAccDetailBean() {
		return obAccDetailBean;
	}
	public void setObAccDetailBean(List<ObAccountBean> obAccDetailBean) {
		this.obAccDetailBean = obAccDetailBean;
	}
	
}
