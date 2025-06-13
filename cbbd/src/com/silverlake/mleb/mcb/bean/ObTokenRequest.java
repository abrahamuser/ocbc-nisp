package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObTokenRequest extends ObRequest implements Serializable
{
	
	 public static String method_token_otp_request  = "token/otp/request";
	 public static String method_token_otp_verify  = "token/otp/verify";
	 public static String method_token_inquiry  = "token/inquiry";
	 public static String method_token_cr_request  = "token/cr/request";
	 public static String method_token_cr_verify = "token/cr/verify";
	 
	 public static String method_customer_token_inquiry = "token/inquiry";
	 public static String method_software_token_preBind1 = "token/sw/prebind_1";
	 public static String method_software_token_preBind2 = "token/sw/prebind_2";
	 public static String method_software_token_postBind = "token/sw/post_bind";
	 public static String method_software_token_eligibility = "token/sw/eligibility_check"; 
	 
	 
	 private String request_type;
	 private String device_id;
	 private String request_id;
	 private String otp;
	 private String response_code;
	 
	 private String token_type_old;
	 private String token_type_new;
	 private String device_finger_print;
	 private String operation_code;
	 private String channel;
	 private String device_code;
	 private String otip_type;
	 private String appli_name;
	 private Integer appli_no;
	 private Integer seq_no;
	 
	 private String requestId_stats;
	 
	 private int resendTagCount;
	 private String resendTagLastDate;
	 
	public String getRequest_type() {
		return request_type;
	}
	public void setRequest_type(String request_type) {
		this.request_type = request_type;
	}
	public String getDevice_id() {
		return device_id;
	}
	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}
	public String getRequest_id() {
		return request_id;
	}
	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public String getResponse_code() {
		return response_code;
	}
	public void setResponse_code(String response_code) {
		this.response_code = response_code;
	}
	public String getToken_type_old() {
		return token_type_old;
	}
	public void setToken_type_old(String token_type_old) {
		this.token_type_old = token_type_old;
	}
	public String getToken_type_new() {
		return token_type_new;
	}
	public void setToken_type_new(String token_type_new) {
		this.token_type_new = token_type_new;
	}
	public String getDevice_finger_print() {
		return device_finger_print;
	}
	public void setDevice_finger_print(String device_finger_print) {
		this.device_finger_print = device_finger_print;
	}
	public String getOperation_code() {
		return operation_code;
	}
	public void setOperation_code(String operation_code) {
		this.operation_code = operation_code;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getDevice_code() {
		return device_code;
	}
	public void setDevice_code(String device_code) {
		this.device_code = device_code;
	}
	public String getOtip_type() {
		return otip_type;
	}
	public void setOtip_type(String otip_type) {
		this.otip_type = otip_type;
	}
	public String getAppli_name() {
		return appli_name;
	}
	public void setAppli_name(String appli_name) {
		this.appli_name = appli_name;
	}
	public Integer getAppli_no() {
		return appli_no;
	}
	public void setAppli_no(Integer appli_no) {
		this.appli_no = appli_no;
	}
	public Integer getSeq_no() {
		return seq_no;
	}
	public void setSeq_no(Integer seq_no) {
		this.seq_no = seq_no;
	}
	public int getResendTagCount() {
		return resendTagCount;
	}
	public void setResendTagCount(int resendTagCount) {
		this.resendTagCount = resendTagCount;
	}
	public String getResendTagLastDate() {
		return resendTagLastDate;
	}
	public void setResendTagLastDate(String resendTagLastDate) {
		this.resendTagLastDate = resendTagLastDate;
	}
	public String getRequestId_stats() {
		return requestId_stats;
	}
	public void setRequestId_stats(String requestId_stats) {
		this.requestId_stats = requestId_stats;
	} 
}