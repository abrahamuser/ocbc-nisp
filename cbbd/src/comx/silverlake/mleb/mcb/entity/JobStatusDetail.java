package comx.silverlake.mleb.mcb.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

//@Entity
@Table(name="JOB_STATUS_DETAIL")
public class JobStatusDetail implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String jobName;
	private Date updateDate;
	
	@Id
	@Column(name="job_name")
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_date", nullable = true)
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
}
