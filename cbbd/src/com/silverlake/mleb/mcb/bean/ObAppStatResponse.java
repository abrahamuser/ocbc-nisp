package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class ObAppStatResponse extends ObResponse implements Serializable
{
	private List<ObGeneralCodeBean> msgList;
	private List<SystemInfo> systemList;
	private List<Services> mlebList;
	private List<Services> clientList;
	private List<Services> requestList;
	
	public List<ObGeneralCodeBean> getMsgList() {
		return msgList;
	}
	public void setMsgList(List<ObGeneralCodeBean> msgList) {
		this.msgList = msgList;
	}
	public List<SystemInfo> getSystemList() {
		return systemList;
	}
	public void setSystemList(List<SystemInfo> systemList) {
		this.systemList = systemList;
	}
	public List<Services> getMlebList() {
		return mlebList;
	}
	public void setMlebList(List<Services> mlebList) {
		this.mlebList = mlebList;
	}
	public List<Services> getClientList() {
		return clientList;
	}
	public void setClientList(List<Services> clientList) {
		this.clientList = clientList;
	}
	public List<Services> getRequestList() {
		return requestList;
	}
	public void setRequestList(List<Services> requestList) {
		this.requestList = requestList;
	}
	
	public class Services implements Serializable{
		
		private String name;
		private String desc;
		private String status;
		private String type;
		
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		
		
	}
	
	
	public class SystemInfo implements Serializable{
		
		private String hostName;
		private String currentHeap;
		private String commitedHeap;
		private String maxHeap;
		private String nonHeap;
		private String maxNonHeap;
		public String getHostName() {
			return hostName;
		}
		public void setHostName(String hostName) {
			this.hostName = hostName;
		}
		public String getCurrentHeap() {
			return currentHeap;
		}
		public void setCurrentHeap(String currentHeap) {
			this.currentHeap = currentHeap;
		}
		public String getCommitedHeap() {
			return commitedHeap;
		}
		public void setCommitedHeap(String commitedHeap) {
			this.commitedHeap = commitedHeap;
		}
		public String getMaxHeap() {
			return maxHeap;
		}
		public void setMaxHeap(String maxHeap) {
			this.maxHeap = maxHeap;
		}
		public String getNonHeap() {
			return nonHeap;
		}
		public void setNonHeap(String nonHeap) {
			this.nonHeap = nonHeap;
		}
		public String getMaxNonHeap() {
			return maxNonHeap;
		}
		public void setMaxNonHeap(String maxNonHeap) {
			this.maxNonHeap = maxNonHeap;
		}
		
		
	}
	
}










