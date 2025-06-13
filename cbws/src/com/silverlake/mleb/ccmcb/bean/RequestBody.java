package com.silverlake.mleb.ccmcb.bean;

import java.io.Serializable;

import com.silverlake.mleb.mcb.bean.sample.SendMoney;

public class RequestBody implements Serializable {

	private SendMoney[] result;
	private String trxToken;
	private String trxTimestamp;
   

    public SendMoney[] getResult() {
		return result;
	}



	public void setResult(SendMoney[] result) {
		this.result = result;
	}
	
	



	public String getTrxToken() {
		return trxToken;
	}



	public void setTrxToken(String trxToken) {
		this.trxToken = trxToken;
	}



	public String getTrxTimestamp() {
		return trxTimestamp;
	}



	public void setTrxTimestamp(String trxTimestamp) {
		this.trxTimestamp = trxTimestamp;
	}



	@Override
    public String toString()
    {
        return "ClassPojo [result = "+result+"]";
    }
}
