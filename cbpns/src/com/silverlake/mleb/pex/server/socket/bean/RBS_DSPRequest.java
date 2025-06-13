package com.silverlake.mleb.pex.server.socket.bean;



public class RBS_DSPRequest 
{
	
	public DSPHeader dspHeader = new DSPHeader();
	public MBaseMsg mbaseMsg = new MBaseMsg();
	public MBaseHD0Msg mbaseHd0Msg = new MBaseHD0Msg();
	public MBaseHD1Msg mbaseHd1Msg = new MBaseHD1Msg();
	public MBaseEx0Msg mbaseEx0Msg = new MBaseEx0Msg();
	public MBaseEx1Msg mbaseEx1Msg = new MBaseEx1Msg();
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
	public MBaseHD0Msg getMbaseHd0Msg() {
		return mbaseHd0Msg;
	}
	public void setMbaseHd0Msg(MBaseHD0Msg mbaseHd0Msg) {
		this.mbaseHd0Msg = mbaseHd0Msg;
	}
	public MBaseHD1Msg getMbaseHd1Msg() {
		return mbaseHd1Msg;
	}
	public void setMbaseHd1Msg(MBaseHD1Msg mbaseHd1Msg) {
		this.mbaseHd1Msg = mbaseHd1Msg;
	}
	public MBaseEx0Msg getMbaseEx0Msg() {
		return mbaseEx0Msg;
	}
	public void setMbaseEx0Msg(MBaseEx0Msg mbaseEx0Msg) {
		this.mbaseEx0Msg = mbaseEx0Msg;
	}
	public MBaseEx1Msg getMbaseEx1Msg() {
		return mbaseEx1Msg;
	}
	public void setMbaseEx1Msg(MBaseEx1Msg mbaseEx1Msg) {
		this.mbaseEx1Msg = mbaseEx1Msg;
	}
	
	
	
	
}
