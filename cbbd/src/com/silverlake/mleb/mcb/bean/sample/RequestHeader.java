package com.silverlake.mleb.mcb.bean.sample;

import java.io.Serializable;
import java.math.BigDecimal;

public class RequestHeader implements Serializable{

	 private String channelTime;

	    private String deviceId;

	    public String getChannelTime ()
	    {
	        return channelTime;
	    }

	    public void setChannelTime (String channelTime)
	    {
	        this.channelTime = channelTime;
	    }

	    public String getDeviceId ()
	    {
	        return deviceId;
	    }

	    public void setDeviceId (String deviceId)
	    {
	        this.deviceId = deviceId;
	    }

	    @Override
	    public String toString()
	    {
	        return "ClassPojo [channelTime = "+channelTime+", deviceId = "+deviceId+"]";
	    }
}
