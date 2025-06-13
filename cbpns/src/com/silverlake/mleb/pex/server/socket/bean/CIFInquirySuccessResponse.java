package com.silverlake.mleb.pex.server.socket.bean;



public class CIFInquirySuccessResponse 
{
	
	public DSPHeader dspHeader = new DSPHeader();
	public MBaseMsg mbaseMsg = new MBaseMsg();
	public MBaseResponseEC responseEC = new MBaseResponseEC();
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
	public MBaseResponseEC getResponseEC() {
		return responseEC;
	}
	public void setResponseEC(MBaseResponseEC responseEC) {
		this.responseEC = responseEC;
	}
	
	
	
	
	
}
