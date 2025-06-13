package comx.silverlake.mleb.mcb.entity;

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
//@Entity
@Table(name = "rooted_devices")
public class RootedDevices implements java.io.Serializable{
	
	private Integer rowId;
	private String deviceId;
	private String make;
	private String model;
	private String os;
	private String deviceType;
	private String login_id;
	private Date updateDate;
	private String idNo;
	private String cif;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "RootedDevices_id")
	@Column(name = "row_id", nullable=false)
	public Integer getRowId() {
		return rowId;
	}
	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}
	
	@Column(name = "login_id", length = 100)
	public String getLogin_id() {
		return login_id;
	}
	
	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}
	@Column(name = "device_id", unique = true, nullable = false, length = 100)
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	@Column(name = "device_make", nullable = true, length = 50)
	public String getMake() {
		return make;
	}
	
	public void setMake(String make) {
		this.make = make;
	}
	
	@Column(name = "device_model", nullable = true, length = 50)
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	
	@Column(name = "device_os", nullable = true, length = 50)
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	
	@Column(name = "device_type", nullable = true, length = 50)
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_date", nullable = true, length = 19)
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Column(name = "id_no", length = 100)
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
		@Column(name = "cif", length = 40)
	public String getCif() {
		return cif;
	}
	public void setCif(String cif) {
		this.cif = cif;
	}
	
	
}
