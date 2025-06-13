package com.silverlake.mleb.mcb.entity;

import static javax.persistence.GenerationType.IDENTITY;

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

@Entity
@Table(name = "version_control")
public class VersionControl implements java.io.Serializable {

	private Integer rowId;
	private String appName;
	private Date updateDate;
	private String version;
	private String status;
	private String displayVersion;

	public VersionControl() {
	}

	



	@Column(name = "app_name", nullable = false)
	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_date", nullable = false, length = 19)
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Column(name = "version")
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	@Column(name = "status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	



	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "VersionControl_id")
	@Column(name = "row_id", nullable=false)
	public Integer getRowId() {
		return rowId;
	}




	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

	@Column(name = "display_version")
	public String getDisplayVersion() {
		return displayVersion;
	}
	
	public void setDisplayVersion(String displayVersion) {
		this.displayVersion = displayVersion;
	}

}
