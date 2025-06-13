package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class ObOVTListResponseBean extends ObResponse implements Serializable
{
	private static final long serialVersionUID = 1L;
	private List<ObAccountBean> ObAccountList;
	private List<ObFavoriteAccountBean> ObFavoriteAccList;
	private List<ObGeneralCodeBean> obCurrTypeCodeList;
	
	
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
	public List<ObGeneralCodeBean> getObCurrTypeCodeList() {
		return obCurrTypeCodeList;
	}
	public void setObCurrTypeCodeList(List<ObGeneralCodeBean> obCurrTypeCodeList) {
		this.obCurrTypeCodeList = obCurrTypeCodeList;
	}
	
	
	
		
}
