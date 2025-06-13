package com.silverlake.mleb.mcb.module.vc.task_list;

import java.util.List;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

public class TaskListRequestData extends VCRequest
{
     private String source;
     private String status;
     private String page_no;
     private List<String> list_prod_cd;
     private String page_size;
     private String value_date_from;
     private String value_date_to;
     private String source_file_name;
     private String upload_date_from;
     private String upload_date_to;
	 
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPage_no() {
		return page_no;
	}
	public void setPage_no(String page_no) {
		this.page_no = page_no;
	}
	public String getPage_size() {
		return page_size;
	}
	public void setPage_size(String page_size) {
		this.page_size = page_size;
	}
	public List<String> getList_prod_cd() {
		return list_prod_cd;
	}
	public void setList_prod_cd(List<String> list_prod_cd) {
		this.list_prod_cd = list_prod_cd;
	}
	public String getValue_date_from() {
		return value_date_from;
	}
	public void setValue_date_from(String value_date_from) {
		this.value_date_from = value_date_from;
	}
	public String getValue_date_to() {
		return value_date_to;
	}
	public void setValue_date_to(String value_date_to) {
		this.value_date_to = value_date_to;
	}

	public String getSource_file_name() {
		return source_file_name;
	}

	public void setSource_file_name(String source_file_name) {
		this.source_file_name = source_file_name;
	}

	public String getUpload_date_from() {
		return upload_date_from;
	}

	public void setUpload_date_from(String upload_date_from) {
		this.upload_date_from = upload_date_from;
	}

	public String getUpload_date_to() {
		return upload_date_to;
	}

	public void setUpload_date_to(String upload_date_to) {
		this.upload_date_to = upload_date_to;
	}
}



	
