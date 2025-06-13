package com.silverlake.mleb.pex.server.socket.bean;



public class OCM_UpdatePExStatusRequest 
{
	
	public OCMRequestHeader requestHeader = new OCMRequestHeader();
	public String pexRefNo_20_b_$;
	public String status_12_b_$;
	public String earmarkRefId_40_b_$;
	public String tranCode_10_b_$;
	public String seqNumber_9_f_0;
	
	
	public OCMRequestHeader getRequestHeader() {
		return requestHeader;
	}


	public String getPexRefNo_20_b_$() {
		return pexRefNo_20_b_$;
	}


	public void setPexRefNo_20_b_$(String pexRefNo_20_b_$) {
		this.pexRefNo_20_b_$ = pexRefNo_20_b_$;
	}


	public String getStatus_12_b_$() {
		return status_12_b_$;
	}


	public void setStatus_12_b_$(String status_12_b_$) {
		this.status_12_b_$ = status_12_b_$;
	}


	public String getEarmarkRefId_40_b_$() {
		return earmarkRefId_40_b_$;
	}


	public void setEarmarkRefId_40_b_$(String earmarkRefId_40_b_$) {
		this.earmarkRefId_40_b_$ = earmarkRefId_40_b_$;
	}


	public String getTranCode_10_b_$() {
		return tranCode_10_b_$;
	}


	public void setTranCode_10_b_$(String tranCode_10_b_$) {
		this.tranCode_10_b_$ = tranCode_10_b_$;
	}


	public String getSeqNumber_9_f_0() {
		return seqNumber_9_f_0;
	}


	public void setSeqNumber_9_f_0(String seqNumber_9_f_0) {
		this.seqNumber_9_f_0 = seqNumber_9_f_0;
	}


	public void setRequestHeader(OCMRequestHeader requestHeader) {
		this.requestHeader = requestHeader;
	}
	
	
	
	
	
	
	
	
	
	
}
