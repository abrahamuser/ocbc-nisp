package com.silverlake.mleb.mcb.bean;

import java.util.List;

public class ObGetPromotionListResponseBean extends ObResponse implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ObPromotionBean> ObGetPromotionIconList;
	private List<ObPromotionBean> ObEndPromotionList;
	
	public List<ObPromotionBean> getObGetPromotionIconList() {
		return ObGetPromotionIconList;
	}
	public void setObGetPromotionIconList(
			List<ObPromotionBean> obGetPromotionIconList) {
		ObGetPromotionIconList = obGetPromotionIconList;
	}
	public List<ObPromotionBean> getObEndPromotionList() {
		return ObEndPromotionList;
	}
	public void setObEndPromotionList(List<ObPromotionBean> obEndPromotionList) {
		ObEndPromotionList = obEndPromotionList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
