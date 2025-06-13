package com.silverlake.mleb.mcb.module.vc.token;

import java.util.List;

import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;

public class TokenResponseData extends VCResponseData
{
	  
	private String request_id;
	private String challenge_code;
	private List<Token> list_token;
	
	private String token_type;
	private String static_vector;
	private String message_activation_1;
	private Long velis_timestamp;
	private String device_id_velis;
	private String message_activation_2;
	private Integer sequence_number;
	private String is_eligible;
	
	public String getRequest_id() {
		return request_id;
	}
	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}
	public String getChallenge_code() {
		return challenge_code;
	}
	public void setChallenge_code(String challenge_code) {
		this.challenge_code = challenge_code;
	}
	public List<Token> getList_token() {
		return list_token;
	}
	public void setList_token(List<Token> list_token) {
		this.list_token = list_token;
	}
	public String getToken_type() {
		return token_type;
	}
	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}
	public String getStatic_vector() {
		return static_vector;
	}
	public void setStatic_vector(String static_vector) {
		this.static_vector = static_vector;
	}
	public String getMessage_activation_1() {
		return message_activation_1;
	}
	public void setMessage_activation_1(String message_activation_1) {
		this.message_activation_1 = message_activation_1;
	}
	public Long getVelis_timestamp() {
		return velis_timestamp;
	}
	public void setVelis_timestamp(Long velis_timestamp) {
		this.velis_timestamp = velis_timestamp;
	}
	public String getDevice_id_velis() {
		return device_id_velis;
	}
	public void setDevice_id_velis(String device_id_velis) {
		this.device_id_velis = device_id_velis;
	}
	public String getMessage_activation_2() {
		return message_activation_2;
	}
	public void setMessage_activation_2(String message_activation_2) {
		this.message_activation_2 = message_activation_2;
	}
	public Integer getSequence_number() {
		return sequence_number;
	}
	public void setSequence_number(Integer sequence_number) {
		this.sequence_number = sequence_number;
	}
	public String getIs_eligible() {
		return is_eligible;
	}
	public void setIs_eligible(String is_eligible) {
		this.is_eligible = is_eligible;
	}
}