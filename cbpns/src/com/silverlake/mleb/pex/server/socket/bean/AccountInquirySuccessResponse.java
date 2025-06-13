package com.silverlake.mleb.pex.server.socket.bean;




public class AccountInquirySuccessResponse 
{
	
	public DSPHeader dspHeader = new DSPHeader();
	public MBaseMsg mbaseMsg = new MBaseMsg();
	
	
	
	public MBaseResponseDA responseDA = new MBaseResponseDA();
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
	public MBaseResponseDA getResponseDA() {
		return responseDA;
	}
	public void setResponseDA(MBaseResponseDA responseDA) {
		this.responseDA = responseDA;
	}
	
	

	
	
	

	
	
}
