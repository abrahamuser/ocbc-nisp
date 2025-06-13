
package com.silverlake.mleb.pex.server.webservice.bean;

import java.io.Serializable;



public class WSResponse
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    protected WSHeaderResponse obHeader;
    protected WSUserDetail obUser;

    /**
     * Gets the value of the obHeader property.
     * 
     * @return
     *     possible object is
     *     {@link WSHeaderResponse }
     *     
     */
    public WSHeaderResponse getObHeader() {
        return obHeader;
    }

    /**
     * Sets the value of the obHeader property.
     * 
     * @param value
     *     allowed object is
     *     {@link WSHeaderResponse }
     *     
     */
    public void setObHeader(WSHeaderResponse value) {
        this.obHeader = value;
    }

    /**
     * Gets the value of the obUser property.
     * 
     * @return
     *     possible object is
     *     {@link WSUserDetail }
     *     
     */
    public WSUserDetail getObUser() {
        return obUser;
    }

    /**
     * Sets the value of the obUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link WSUserDetail }
     *     
     */
    public void setObUser(WSUserDetail value) {
        this.obUser = value;
    }

	
    

}
