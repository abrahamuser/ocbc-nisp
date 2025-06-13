package com.silverlake.mleb.mcb.module.vc.common;

import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;

public class VCAsyncResponseBean<R, T> {
	private R requestData;
	private Date requestDate;
	private String casePath;
	private ListenableFuture<ResponseEntity<T>> responseListener;

	public ListenableFuture<ResponseEntity<T>> getResponseListener() {
		return responseListener;
	}

	public void setResponseListener(ListenableFuture<ResponseEntity<T>> responseListener) {
		this.responseListener = responseListener;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public String getCasePath() {
		return casePath;
	}

	public void setCasePath(String casePath) {
		this.casePath = casePath;
	}

	public R getRequestData() {
		return requestData;
	}

	public void setRequestData(R requestData) {
		this.requestData = requestData;
	}
	
	
}
