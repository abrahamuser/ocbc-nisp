package com.silverlake.mleb.pex.server.socket.bean;



public class RBSViRequest 
{
	
	public DSPHeader dspHeader = new DSPHeader();
	public MBaseMsg mbaseMsg = new MBaseMsg();
	public HostMbaseViMsg hostMsg = new HostMbaseViMsg();
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
	public HostMbaseViMsg getHostMsg() {
		return hostMsg;
	}
	public void setHostMsg(HostMbaseViMsg hostMsg) {
		this.hostMsg = hostMsg;
	}
	
	
	
	
	
	
}
