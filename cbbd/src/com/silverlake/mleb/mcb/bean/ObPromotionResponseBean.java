package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ObPromotionResponseBean extends ObResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<ObPromotionBean> ObPromotionBeanList;

	public List<ObPromotionBean> getObPromotionBeanList() {
		return ObPromotionBeanList;
	}

	public void setObPromotionBeanList(List<ObPromotionBean> obPromotionBeanList) {
		ObPromotionBeanList = obPromotionBeanList;
	}
	
}
