package com.silverlake.mleb.pex.server.socket.bean;



public class RBSRequest 
{
	
	public DSPHeader dspHeader = new DSPHeader();
	public MBaseMsg mbaseMsg = new MBaseMsg();
	public HostMbaseMsg hostMsg = new HostMbaseMsg();
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
	public HostMbaseMsg getHostMsg() {
		return hostMsg;
	}
	public void setHostMsg(HostMbaseMsg hostMsg) {
		this.hostMsg = hostMsg;
	}
	
	
	
	
	
}
