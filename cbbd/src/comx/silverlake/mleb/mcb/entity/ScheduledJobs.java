package comx.silverlake.mleb.mcb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//@Entity
@Table(name="SCHEDULED_JOBS")
public class ScheduledJobs implements Serializable {
	
	private Integer jobId;
	private String jobName;
	private String groupJobName;
	private String cronExpression;
	private String status;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "SCHEDULED_JOBS_ID")
	@Column(name="job_id", nullable=false) 
	public Integer getJobId() {
		return jobId;
	}
	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}
	
	@Column(name="job_name")
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	
	@Column(name="group_job_name")
	public String getGroupJobName() {
		return groupJobName;
	}
	public void setGroupJobName(String groupJobName) {
		this.groupJobName = groupJobName;
	}
	
	@Column(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name="cron_expression")
	public String getCronExpression() {
		return cronExpression;
	}
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}
}
