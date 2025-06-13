
package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;



public class ObHeaderResponse
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    private String acknowledgementNumber;
    private String id;
    private String statusCode;
    private String statusMessage;
    private Boolean success;
    private Integer totalCount;
    private String asOfDate; 
    private String responseTime;
    private boolean maintenanceNoticeFlag;
    private String maintenanceNotice;
    private int maintenanceUpfrontNoticeTime;
    private String maintenanceStartDateTime;
    private String maintenanceEndDateTime;
    private String serviceFlag; 
    private String serviceMsg; 
    private String errorMsg;

    /**
     * Gets the value of the acknowledgementNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcknowledgementNumber() {
        return acknowledgementNumber;
    }

    /**
     * Sets the value of the acknowledgementNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcknowledgementNumber(String value) {
        this.acknowledgementNumber = value;
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
     * Gets the value of the statusCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * Sets the value of the statusCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusCode(String value) {
        this.statusCode = value;
    }

    /**
     * Gets the value of the statusMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusMessage() {
        return statusMessage;
    }

    /**
     * Sets the value of the statusMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusMessage(String value) {
        this.statusMessage = value;
    }

    /**
     * Gets the value of the success property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSuccess() {
        return success;
    }

    /**
     * Sets the value of the success property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSuccess(Boolean value) {
        this.success = value;
    }

    /**
     * Gets the value of the totalCount property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTotalCount() {
        return totalCount;
    }

    /**
     * Sets the value of the totalCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTotalCount(Integer value) {
        this.totalCount = value;
    }
    
    
	public String getAsOfDate() {
		return asOfDate;
	}

	public void setAsOfDate(String asOfDate) {
		this.asOfDate = asOfDate;
	}

	public String getResponseTime()
	{
		return responseTime;
	}

	public void setResponseTime(String responseTime)
	{
		this.responseTime = responseTime;
	}

	public Boolean getSuccess()
	{
		return success;
	}

	public String getMaintenanceStartDateTime() {
		return maintenanceStartDateTime;
	}

	public void setMaintenanceStartDateTime(String maintenanceStartDateTime) {
		this.maintenanceStartDateTime = maintenanceStartDateTime;
	}

	public String getMaintenanceEndDateTime() {
		return maintenanceEndDateTime;
	}

	public void setMaintenanceEndDateTime(String maintenanceEndDateTime) {
		this.maintenanceEndDateTime = maintenanceEndDateTime;
	}

	public boolean isMaintenanceNoticeFlag() {
		return maintenanceNoticeFlag;
	}

	public void setMaintenanceNoticeFlag(boolean maintenanceNoticeFlag) {
		this.maintenanceNoticeFlag = maintenanceNoticeFlag;
	}

	public String getMaintenanceNotice() {
		return maintenanceNotice;
	}

	public void setMaintenanceNotice(String maintenanceNotice) {
		this.maintenanceNotice = maintenanceNotice;
	}

	public int getMaintenanceUpfrontNoticeTime() {
		return maintenanceUpfrontNoticeTime;
	}

	public void setMaintenanceUpfrontNoticeTime(int maintenanceUpfrontNoticeTime) {
		this.maintenanceUpfrontNoticeTime = maintenanceUpfrontNoticeTime;
	}

	public String getServiceFlag() {
		return serviceFlag;
	}

	public void setServiceFlag(String serviceFlag) {
		this.serviceFlag = serviceFlag;
	}

	public String getServiceMsg() {
		return serviceMsg;
	}

	public void setServiceMsg(String serviceMsg) {
		this.serviceMsg = serviceMsg;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}


	
	
	
}
