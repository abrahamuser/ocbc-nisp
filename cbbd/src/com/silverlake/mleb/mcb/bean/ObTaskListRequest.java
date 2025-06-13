package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class ObTaskListRequest extends ObRequest implements Serializable
{
	//
	private String tranxSourcelist;
	private String pageSize;
	private String pageNo;
	private String status;
	private String pymt_master_id;
	private String prodCodeList;
	private String valueDateFrom;
	private String valueDateTo;
	private String trxStatus;
	private String sourceFileName;
	private String uploadDateFrom;
	private String uploadDateTo;
	private String module;

	public String getTranxSourcelist() {
		return tranxSourcelist;
	}

	public void setTranxSourcelist(String tranxSourcelist) {
		this.tranxSourcelist = tranxSourcelist;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPymt_master_id() {
		return pymt_master_id;
	}

	public void setPymt_master_id(String pymt_master_id) {
		this.pymt_master_id = pymt_master_id;
	}

	public String getProdCodeList() {
		return prodCodeList;
	}

	public void setProdCodeList(String prodCodeList) {
		this.prodCodeList = prodCodeList;
	}

	public String getValueDateFrom() {
		return valueDateFrom;
	}

	public void setValueDateFrom(String valueDateFrom) {
		this.valueDateFrom = valueDateFrom;
	}

	public String getValueDateTo() {
		return valueDateTo;
	}

	public void setValueDateTo(String valueDateTo) {
		this.valueDateTo = valueDateTo;
	}

	public String getTrxStatus() {
		return trxStatus;
	}

	public void setTrxStatus(String trxStatus) {
		this.trxStatus = trxStatus;
	}

	public String getSourceFileName() {
		return sourceFileName;
	}

	public void setSourceFileName(String sourceFileName) {
		this.sourceFileName = sourceFileName;
	}

	public String getUploadDateFrom() {
		return uploadDateFrom;
	}

	public void setUploadDateFrom(String uploadDateFrom) {
		this.uploadDateFrom = uploadDateFrom;
	}

	public String getUploadDateTo() {
		return uploadDateTo;
	}

	public void setUploadDateTo(String uploadDateTo) {
		this.uploadDateTo = uploadDateTo;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}
}