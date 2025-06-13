
package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;


/**
 * <p>Java class for obHeaderRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="obHeaderRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="channelDateTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="channelId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="clientRemoteAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="clientRequestURL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="clientSessionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="maxPerPage" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="startIndex" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
public class ObHeaderRequest
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    protected String channelDateTime;
    protected String channelId;
    protected String clientRemoteAddress;
    protected String clientRequestURL;
    protected String clientSessionId;
    protected String id;
    protected Integer maxPerPage;
    protected Integer startIndex;
    protected String version;
    
    private String pnsDeviceToken;
    private String app_mode;
    private String ip;

    public String getPnsDeviceToken() {
		return pnsDeviceToken;
	}

	public void setPnsDeviceToken(String pnsDeviceToken) {
		this.pnsDeviceToken = pnsDeviceToken;
	}

	
	public String getApp_mode() {
		return app_mode;
	}

	public void setApp_mode(String app_mode) {
		this.app_mode = app_mode;
	}

	/**
     * Gets the value of the channelDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChannelDateTime() {
        return channelDateTime;
    }

    /**
     * Sets the value of the channelDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChannelDateTime(String value) {
        this.channelDateTime = value;
    }

    /**
     * Gets the value of the channelId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChannelId() {
        return channelId;
    }

    /**
     * Sets the value of the channelId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChannelId(String value) {
        this.channelId = value;
    }

    /**
     * Gets the value of the clientRemoteAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientRemoteAddress() {
        return clientRemoteAddress;
    }

    /**
     * Sets the value of the clientRemoteAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientRemoteAddress(String value) {
        this.clientRemoteAddress = value;
    }

    /**
     * Gets the value of the clientRequestURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientRequestURL() {
        return clientRequestURL;
    }

    /**
     * Sets the value of the clientRequestURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientRequestURL(String value) {
        this.clientRequestURL = value;
    }

    /**
     * Gets the value of the clientSessionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientSessionId() {
        return clientSessionId;
    }

    /**
     * Sets the value of the clientSessionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientSessionId(String value) {
        this.clientSessionId = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the maxPerPage property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMaxPerPage() {
        return maxPerPage;
    }

    /**
     * Sets the value of the maxPerPage property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMaxPerPage(Integer value) {
        this.maxPerPage = value;
    }

    /**
     * Gets the value of the startIndex property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getStartIndex() {
        return startIndex;
    }

    /**
     * Sets the value of the startIndex property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setStartIndex(Integer value) {
        this.startIndex = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
    
    

}

