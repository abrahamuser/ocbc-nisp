package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObTNCCheckbox implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer seqNo;
	private String content;
	
	public Integer getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
