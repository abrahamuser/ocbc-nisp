package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.Map;

public class ObBankOfInterbank implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Map<String, String> listBankOfInterbankOLT;
	Map<String, String> listBankOfInterbankLLG;
	Map<String, String> listBankOfInterbankRTGS;
	
	public Map<String, String> getListBankOfInterbankOLT() {
		return listBankOfInterbankOLT;
	}
	public void setListBankOfInterbankOLT(Map<String, String> listBankOfInterbankOLT) {
		this.listBankOfInterbankOLT = listBankOfInterbankOLT;
	}
	public Map<String, String> getListBankOfInterbankLLG() {
		return listBankOfInterbankLLG;
	}
	public void setListBankOfInterbankLLG(Map<String, String> listBankOfInterbankLLG) {
		this.listBankOfInterbankLLG = listBankOfInterbankLLG;
	}
	public Map<String, String> getListBankOfInterbankRTGS() {
		return listBankOfInterbankRTGS;
	}
	public void setListBankOfInterbankRTGS(Map<String, String> listBankOfInterbankRTGS) {
		this.listBankOfInterbankRTGS = listBankOfInterbankRTGS;
	}

	
	
}
