package com.silverlake.mleb.mcb.module.vc.transaction;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class BillDetailsData implements Serializable{
	Map<String, String> header;
	List<DetailData> detail;
	Map<String, String> footer;
	public Map<String, String> getHeader() {
		return header;
	}
	public void setHeader(Map<String, String> header) {
		this.header = header;
	}
	public Map<String, String> getFooter() {
		return footer;
	}
	public void setFooter(Map<String, String> footer) {
		this.footer = footer;
	}
	public List<DetailData> getDetail() {
		return detail;
	}
	public void setDetail(List<DetailData> detail) {
		this.detail = detail;
	}
	
	
}

