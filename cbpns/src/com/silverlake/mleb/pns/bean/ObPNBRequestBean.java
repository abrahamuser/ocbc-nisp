package com.silverlake.mleb.pns.bean;

import java.io.Serializable;
import java.util.List;

import com.silverlake.hlb.mib.bean.ObRequest;

public class ObPNBRequestBean extends ObRequest implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String header;
	
	private String content;
	
	private List<ObPNBDeviceRequestBean> deviceBeanList;
	
	private String template;
	
	private String requestId;
	
	private List<String> customProperties;
	
	private String deviceOsType;

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<ObPNBDeviceRequestBean> getDeviceBeanList() {
		return deviceBeanList;
	}

	public void setDeviceBeanList(List<ObPNBDeviceRequestBean> deviceBeanList) {
		this.deviceBeanList = deviceBeanList;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public List<String> getCustomProperties() {
		return customProperties;
	}

	public void setCustomProperties(List<String> customProperties) {
		this.customProperties = customProperties;
	}

	public String getDeviceOsType() {
		return deviceOsType;
	}

	public void setDeviceOsType(String deviceOsType) {
		this.deviceOsType = deviceOsType;
	}
}
