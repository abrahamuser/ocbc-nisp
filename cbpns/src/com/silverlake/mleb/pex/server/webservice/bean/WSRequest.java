
package com.silverlake.mleb.pex.server.webservice.bean;

import java.io.Serializable;



public class WSRequest
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    protected WSHeaderRequest obHeader;
    //protected WSUserDetail obUser;
   
    /**
     * Gets the value of the obHeader property.
     * 
     * @return
     *     possible object is
     *     {@link WSHeaderRequest }
     *     
     */
    public WSHeaderRequest getObHeader() {
        return obHeader;
    }

    /**
     * Sets the value of the obHeader property.
     * 
     * @param value
     *     allowed object is
     *     {@link WSHeaderRequest }
     *     
     */
    public void setObHeader(WSHeaderRequest value) {
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
    /*public WSUserDetail getObUser() {
        return obUser;
    }

    *//**
     * Sets the value of the obUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link WSUserDetail }
     *     
     *//*
    public void setObUser(WSUserDetail value) {
        this.obUser = value;
    }*/


    

}
