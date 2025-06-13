
package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

import com.silverlake.micb.core.bean.DeviceBean;



public class ObRequest
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    private ObHeaderRequest obHeader;
    private ObUserDetail obUser;
    private ObUserContext userContext;
    private ObExtHeader obExtHeader;
    private DeviceBean obDevice;
    
    //For token/cr usage
    private Boolean isCRVerify;
	private String requestId;
    private String responseCode;

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	
    /**
     * Gets the value of the obHeader property.
     * 
     * @return
     *     possible object is
     *     {@link ObHeaderRequest }
     *     
     */
    public ObHeaderRequest getObHeader() {
        return obHeader;
    }

    /**
     * Sets the value of the obHeader property.
     * 
     * @param value
     *     allowed object is
     *     {@link ObHeaderRequest }
     *     
     */
    public void setObHeader(ObHeaderRequest value) {
        this.obHeader = value;
    }

    /**
     * Gets the value of the obUser property.
     * 
     * @return
     *     possible object is
     *     {@link ObUserDetail }
     *     
     */
    public ObUserDetail getObUser() {
        return obUser;
    }

    /**
     * Sets the value of the obUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link ObUserDetail }
     *     
     */
    public void setObUser(ObUserDetail value) {
        this.obUser = value;
    }

	public ObUserContext getUserContext() {
		return userContext;
	}

	public void setUserContext(ObUserContext userContext) {
		this.userContext = userContext;
	}

	public ObExtHeader getObExtHeader() {
		return obExtHeader;
	}

	public void setObExtHeader(ObExtHeader obExtHeader) {
		this.obExtHeader = obExtHeader;
	}

	public Boolean getIsCRVerify() {
		return isCRVerify;
	}

	public void setIsCRVerify(Boolean isCRVerify) {
		this.isCRVerify = isCRVerify;
	}

	public DeviceBean getObDevice() {
		return obDevice;
	}

	public void setObDevice(DeviceBean obDevice) {
		this.obDevice = obDevice;
	}
    
	
    
    

}
