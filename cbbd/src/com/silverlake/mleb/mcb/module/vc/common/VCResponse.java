package com.silverlake.mleb.mcb.module.vc.common;

import java.io.Serializable;

public class VCResponse<T> implements Serializable{
	
	private String response_code;
	private String response_desc;
	
    private T data;

	public String getResponse_code() {
		return response_code;
	}

	public void setResponse_code(String response_code) {
		this.response_code = response_code;
	}

	public String getResponse_desc() {
		return response_desc;
	}

	public void setResponse_desc(String response_desc) {
		this.response_desc = response_desc;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
    
    
   
}