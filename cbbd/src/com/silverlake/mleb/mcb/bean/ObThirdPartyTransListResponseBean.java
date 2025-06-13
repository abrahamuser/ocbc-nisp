package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ObThirdPartyTransListResponseBean extends ObResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<ObAccountBean> ObAccountList;
	
	private List<ObFavoriteAccountBean> ObFavoriteAccList;
	private List<ObGeneralCodeBean> obAccTypeCodeList;
	
	public List<ObGeneralCodeBean> getObAccTypeCodeList() {
		return obAccTypeCodeList;
	}
	public void setObAccTypeCodeList(List<ObGeneralCodeBean> obAccTypeCodeList) {
		this.obAccTypeCodeList = obAccTypeCodeList;
	}
	public List<ObAccountBean> getObAccountList() {
		return ObAccountList;
	}
	public void setObAccountList(List<ObAccountBean> obAccountList) {
		ObAccountList = obAccountList;
	}
	public List<ObFavoriteAccountBean> getObFavoriteAccList() {
		return ObFavoriteAccList;
	}
	public void setObFavoriteAccList(List<ObFavoriteAccountBean> obFavoriteAccList) {
		ObFavoriteAccList = obFavoriteAccList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
