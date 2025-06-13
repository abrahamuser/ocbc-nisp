
package com.silverlake.mleb.pex.server.webservice.bean;

import java.io.Serializable;



public class WSPNDetails 
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    
    private String deviceToken;
    private String message;
    private String connCode;
    private String channel;
    private Integer badge;
    private long timeToLive;
	public String getDeviceToken() {
		return deviceToken;
	}
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getConnCode() {
		return connCode;
	}
	public void setConnCode(String connCode) {
		this.connCode = connCode;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public Integer getBadge() {
		return badge;
	}
	public void setBadge(Integer badge) {
		this.badge = badge;
	}
	public long getTimeToLive() {
		return timeToLive;
	}
	public void setTimeToLive(long timeToLive) {
		this.timeToLive = timeToLive;
	}
    
    
}
