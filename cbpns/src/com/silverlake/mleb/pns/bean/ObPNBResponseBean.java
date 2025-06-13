package com.silverlake.mleb.pns.bean;

import java.io.Serializable;
import java.util.List;

import com.silverlake.hlb.mib.bean.ObResponse;

public class ObPNBResponseBean extends ObResponse implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String statusCode;
	 
	private String statusMessage;
	
	private List<ObPNBDeviceRequestBean> deviceBeanList;
	
	private List<PushNotifBroadcastBean> promoNotificationHist;

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public List<ObPNBDeviceRequestBean> getDeviceBeanList() {
		return deviceBeanList;
	}

	public void setDeviceBeanList(List<ObPNBDeviceRequestBean> deviceBeanList) {
		this.deviceBeanList = deviceBeanList;
	}

	public List<PushNotifBroadcastBean> getPromoNotificationHist() {
		return promoNotificationHist;
	}

	public void setPromoNotificationHist(
			List<PushNotifBroadcastBean> promoNotificationHist) {
		this.promoNotificationHist = promoNotificationHist;
	}
}
