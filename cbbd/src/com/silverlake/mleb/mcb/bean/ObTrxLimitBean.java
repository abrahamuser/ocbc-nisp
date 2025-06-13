package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObTrxLimitBean implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ObOATSBean OATS;
	private ObOATCBean OATC;
	private ObIFTSBean IFTS;
	private ObIFTCBean IFTC;
	private ObOLTSBean OLTS;
	private ObRTGSSBean RTGSS;
	private ObLLGSBean LLGS;
	public ObOATSBean getOATS() {
		return OATS;
	}
	public void setOATS(ObOATSBean oATS) {
		OATS = oATS;
	}
	public ObOATCBean getOATC() {
		return OATC;
	}
	public void setOATC(ObOATCBean oATC) {
		OATC = oATC;
	}
	
	public ObIFTSBean getIFTS() {
		return IFTS;
	}
	public void setIFTS(ObIFTSBean iFTS) {
		IFTS = iFTS;
	}
	public ObIFTCBean getIFTC() {
		return IFTC;
	}
	public void setIFTC(ObIFTCBean iFTC) {
		IFTC = iFTC;
	}
	public ObOLTSBean getOLTS() {
		return OLTS;
	}
	public void setOLTS(ObOLTSBean oLTS) {
		OLTS = oLTS;
	}
	public ObRTGSSBean getRTGSS() {
		return RTGSS;
	}
	public void setRTGSS(ObRTGSSBean rTGSS) {
		RTGSS = rTGSS;
	}
	public ObLLGSBean getLLGS() {
		return LLGS;
	}
	public void setLLGS(ObLLGSBean lLGS) {
		LLGS = lLGS;
	}
	
}
