
package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;



public class ObResponse
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    private ObHeaderResponse obHeader;
    private ObUserDetail obUser;
    private ObUserContext userContext;
    private String[] paramValue;
    /**
     * Gets the value of the obHeader property.
     * 
     * @return
     *     possible object is
     *     {@link ObHeaderResponse }
     *     
     */
    public ObHeaderResponse getObHeader() {
        return obHeader;
    }

    /**
     * Sets the value of the obHeader property.
     * 
     * @param value
     *     allowed object is
     *     {@link ObHeaderResponse }
     *     
     */
    public void setObHeader(ObHeaderResponse value) {
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

	public String[] getParamValue() {
		return paramValue;
	}

	public void setParamValue(String[] paramValue) {
		this.paramValue = paramValue;
	}
    
    

}
