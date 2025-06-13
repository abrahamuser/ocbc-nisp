package com.silverlake.mleb.pex.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
//@Table(name = "hlb_bank_route",catalog = "mleb_hlb")
public class BankRoute implements java.io.Serializable{

	private Integer rowId;
	private String bankCode;
	private String bankName;
	private String routeNo;
	private String routeAbbr;
	private String type;
	private String flag;
	private String status;

	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "row_id", unique = true, nullable = false)
	public Integer getRowId() {
		return rowId;
	}
	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}
	
	@Column(name = "bank_code", length = 100)
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	
	
	@Column(name = "route_no", length = 100)
	public String getRouteNo() {
		return routeNo;
	}
	public void setRouteNo(String routeNo) {
		this.routeNo = routeNo;
	}
	
	@Column(name = "route_abbr", length = 100)
	public String getRouteAbbr() {
		return routeAbbr;
	}
	public void setRouteAbbr(String routeAbbr) {
		this.routeAbbr = routeAbbr;
	}
	
	
	
	@Column(name = "type", length = 45)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	@Column(name = "flag", length = 45)
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	
	
	@Column(name = "status", length = 45)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

			
	@Column(name = "bank_name", length = 100)
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	
	
	
}
