
package com.silverlake.mleb.pex.server.webservice.bean;

import java.io.Serializable;



public class WSPNDeliveryDetail 
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    
    private WSPNDetails gcm;
    private WSPNDetails apns;
	public WSPNDetails getGcm() {
		return gcm;
	}
	public void setGcm(WSPNDetails gcm) {
		this.gcm = gcm;
	}
	public WSPNDetails getApns() {
		return apns;
	}
	public void setApns(WSPNDetails apns) {
		this.apns = apns;
	}
    
    

}
