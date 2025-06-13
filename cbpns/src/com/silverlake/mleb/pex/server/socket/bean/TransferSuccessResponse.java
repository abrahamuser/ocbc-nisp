package com.silverlake.mleb.pex.server.socket.bean;



public class TransferSuccessResponse 
{
	
	public DSPHeader dspHeader = new DSPHeader();
	public MBaseMsg mbaseMsg = new MBaseMsg();
	public MBaseResponseHeader responseHeader = new MBaseResponseHeader();
	public String codeField3_3_b_$; 
	public String shortName_20_b_$; 
	public String taxCodeID_20_b_$;
	public String ledgerBal_8_p_0; 
	public String availableBal_8_p_0; 
	
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
	public String getCodeField3_3_b_$() {
		return codeField3_3_b_$;
	}
	public void setCodeField3_3_b_$(String codeField3_3_b_$) {
		this.codeField3_3_b_$ = codeField3_3_b_$;
	}
	public String getShortName_20_b_$() {
		return shortName_20_b_$;
	}
	public void setShortName_20_b_$(String shortName_20_b_$) {
		this.shortName_20_b_$ = shortName_20_b_$;
	}
	public String getTaxCodeID_20_b_$() {
		return taxCodeID_20_b_$;
	}
	public void setTaxCodeID_20_b_$(String taxCodeID_20_b_$) {
		this.taxCodeID_20_b_$ = taxCodeID_20_b_$;
	}
	public String getLedgerBal_8_p_0() {
		return ledgerBal_8_p_0;
	}
	public void setLedgerBal_8_p_0(String ledgerBal_8_p_0) {
		this.ledgerBal_8_p_0 = ledgerBal_8_p_0;
	}
	public String getAvailableBal_8_p_0() {
		return availableBal_8_p_0;
	}
	public void setAvailableBal_8_p_0(String availableBal_8_p_0) {
		this.availableBal_8_p_0 = availableBal_8_p_0;
	}
	
	
	
	

	
	
}
