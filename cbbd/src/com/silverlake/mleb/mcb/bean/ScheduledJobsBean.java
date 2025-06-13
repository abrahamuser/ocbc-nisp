package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ScheduledJobsBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer jobId;
	private String jobName;
	private String groupJobName;
	private String cronExpression;
	private String status;
	
	public Integer getJobId() {
		return jobId;
	}
	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getGroupJobName() {
		return groupJobName;
	}
	public void setGroupJobName(String groupJobName) {
		this.groupJobName = groupJobName;
	}
	public String getCronExpression() {
		return cronExpression;
	}
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}

