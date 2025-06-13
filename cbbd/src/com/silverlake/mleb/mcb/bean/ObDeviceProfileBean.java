package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.silverlake.micb.core.bean.DeviceBean;

@XmlRootElement
public class ObDeviceProfileBean extends DeviceBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private String dateTagged;
	private String dateUnTagged;
	private String unTaggedBy;
	private String unTaggedType;
	private String deviceStatus;
	private String cif;
	private String biometricStatus;
	private String pnsFlagStatus;
	
	public String getBiometricStatus() {
		return biometricStatus;
	}
	public void setBiometricStatus(String biometricStatus) {
		this.biometricStatus = biometricStatus;
	}
	
	
	public String getPnsFlagStatus() {
		return pnsFlagStatus;
	}
	public void setPnsFlagStatus(String pnsFlagStatus) {
		this.pnsFlagStatus = pnsFlagStatus;
	}
	public String getDateTagged() {
		return dateTagged;
	}
	public void setDateTagged(String dateTagged) {
		this.dateTagged = dateTagged;
	}
	public String getDateUnTagged() {
		return dateUnTagged;
	}
	public void setDateUnTagged(String dateUnTagged) {
		this.dateUnTagged = dateUnTagged;
	}
	public String getUnTaggedBy() {
		return unTaggedBy;
	}
	public void setUnTaggedBy(String unTaggedBy) {
		this.unTaggedBy = unTaggedBy;
	}
	public String getUnTaggedType() {
		return unTaggedType;
	}
	public void setUnTaggedType(String unTaggedType) {
		this.unTaggedType = unTaggedType;
	}
	public String getDeviceStatus() {
		return deviceStatus;
	}
	public void setDeviceStatus(String deviceStatus) {
		this.deviceStatus = deviceStatus;
	}
	public String getCif() {
		return cif;
	}
	public void setCif(String cif) {
		this.cif = cif;
	}
}

