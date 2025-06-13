package com.silverlake.mleb.pex.server.socket.bean;



public class RBSResponse 
{
	
	public DSPHeader dspHeader = new DSPHeader();
	public MBaseMsg mbaseMsg = new MBaseMsg();
	public MBaseResponseHeader responseHeader = new MBaseResponseHeader();
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
	public MBaseResponseHeader getResponseHeader() {
		return responseHeader;
	}
	public void setResponseHeader(MBaseResponseHeader responseHeader) {
		this.responseHeader = responseHeader;
	}

	

	
	
}
