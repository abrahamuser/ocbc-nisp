package com.silverlake.mleb.pex.server.socket.bean;



public class RBS_DSPERRResponse 
{
	
	public DSPHeader dspHeader = new DSPHeader();
	public MBaseMsg mbaseMsg = new MBaseMsg();

	public MBaseResponse41 response41 = new MBaseResponse41();
	public MBaseResponse42 response42 = new MBaseResponse42();
	
	
	public DSPHeader getDspHeader() {
		return dspHeader;
	}
	public void setDspHeader(DSPHeader dspHeader) {
		this.dspHeader = dspHeader;
	}
	public MBaseMsg getMbaseMsg() {
		return mbaseMsg;
	}
	public void setMbaseMsg(MBaseMsg mbaseMsg) {
		this.mbaseMsg = mbaseMsg;
	}
	public MBaseResponse41 getResponse41() {
		return response41;
	}
	public void setResponse41(MBaseResponse41 response41) {
		this.response41 = response41;
	}
	public MBaseResponse42 getResponse42() {
		return response42;
	}
	public void setResponse42(MBaseResponse42 response42) {
		this.response42 = response42;
	}

	
	
	
	
}
