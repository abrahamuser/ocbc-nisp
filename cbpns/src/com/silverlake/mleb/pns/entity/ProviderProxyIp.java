package com.silverlake.mleb.pns.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
//@Table(name = "mleb_provider_proxy_ip")
public class ProviderProxyIp implements java.io.Serializable
{
	private Integer rowId;
	
	private String os;
	
	private String proxyIpName;
	
	private String providerRealIp;
	
	private String status;

	@Id
	//@GeneratedValue(strategy = IDENTITY)
	@Column(name = "row_id", unique = true, nullable = false)
	public Integer getRowId() {
		return rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

	@Column(name = "os",  nullable = false, length = 20)
	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	@Column(name = "proxy_ip_name",  nullable = false, length = 100)
	public String getProxyIpName() {
		return proxyIpName;
	}

	public void setProxyIpName(String proxyIpName) {
		this.proxyIpName = proxyIpName;
	}

	@Column(name = "provider_real_ip",  nullable = false, length = 100)
	public String getProviderRealIp() {
		return providerRealIp;
	}

	public void setProviderRealIp(String providerRealIp) {
		this.providerRealIp = providerRealIp;
	}

	@Column(name = "status", nullable=false, length = 50)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
