package com.silverlake.mleb.pex.server.socket.bean;



public class OCM_UpdatePExStatusResponse 
{
	
	public OCMResponseHeader responseHeader = new OCMResponseHeader();
	public String pexRefNo_20_b_$;
	public String transactionDate_23_b_$;
	public String transactionStatus_50_b_$;
	
	
	
	
	public String getTransactionDate_23_b_$() {
		return transactionDate_23_b_$;
	}
	public void setTransactionDate_23_b_$(String transactionDate_23_b_$) {
		this.transactionDate_23_b_$ = transactionDate_23_b_$;
	}
	public String getTransactionStatus_50_b_$() {
		return transactionStatus_50_b_$;
	}
	public void setTransactionStatus_50_b_$(String transactionStatus_50_b_$) {
		this.transactionStatus_50_b_$ = transactionStatus_50_b_$;
	}
	public OCMResponseHeader getResponseHeader() {
		return responseHeader;
	}
	public void setResponseHeader(OCMResponseHeader responseHeader) {
		this.responseHeader = responseHeader;
	}
	public String getPexRefNo_20_b_$() {
		return pexRefNo_20_b_$;
	}
	public void setPexRefNo_20_b_$(String pexRefNo_20_b_$) {
		this.pexRefNo_20_b_$ = pexRefNo_20_b_$;
	}
	
	
	

	
}
