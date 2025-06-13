package comx.silverlake.mleb.mcb.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//@Entity
@Table(name = "AO_CC_ADDITIONAL_INFO_CACHE")
public class AOCCAdditionalInfoCache implements java.io.Serializable{

	private Integer rowId;
	private String deviceId;
	private String nik;
	private String garudaMember;
	private String krisflyerMember;
	private String noOfCCOwn;
	private String ccDeliveryLocation;
	private String ccStatementDelivery;
	private String hardCopyDeliveryLocation;
	private String status;
	private String isFinal;
	private String userId;
	private String moduleCode;
			
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "AO_CC_ADDITIONAL_INFO_CACHE_id")
	@Column(name = "row_id", nullable=false)
	public Integer getRowId() {
		return rowId;
	}
	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}
	@Column(name = "device_id")
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	@Column(name = "nik")
	public String getNik() {
		return nik;
	}
	public void setNik(String nik) {
		this.nik = nik;
	}
	@Column(name = "garudaMember")
	public String getGarudaMember() {
		return garudaMember;
	}
	public void setGarudaMember(String garudaMember) {
		this.garudaMember = garudaMember;
	}
	@Column(name = "krisflyerMember")
	public String getKrisflyerMember() {
		return krisflyerMember;
	}
	public void setKrisflyerMember(String krisflyerMember) {
		this.krisflyerMember = krisflyerMember;
	}
	@Column(name = "noOfCCOwn")
	public String getNoOfCCOwn() {
		return noOfCCOwn;
	}
	public void setNoOfCCOwn(String noOfCCOwn) {
		this.noOfCCOwn = noOfCCOwn;
	}
	@Column(name = "ccDeliveryLocation")
	public void setCcDeliveryLocation(String ccDeliveryLocation) {
		this.ccDeliveryLocation = ccDeliveryLocation;
	}
	public void setCcStatementDelivery(String ccStatementDelivery) {
		this.ccStatementDelivery = ccStatementDelivery;
	}
	@Column(name = "ccStatementDelivery")
	public String getCcStatementDelivery() {
		return ccStatementDelivery;
	}
	public String getCcDeliveryLocation() {
		return ccDeliveryLocation;
	}
	
	@Column(name = "hardCopyDeliveryLocation")
	public String getHardCopyDeliveryLocation() {
		return hardCopyDeliveryLocation;
	}
	public void setHardCopyDeliveryLocation(String hardCopyDeliveryLocation) {
		this.hardCopyDeliveryLocation = hardCopyDeliveryLocation;
	}
	@Column(name = "status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name = "is_final")
	public String getIsFinal() {
		return isFinal;
	}
	public void setIsFinal(String isFinal) {
		this.isFinal = isFinal;
	}
	@Column(name = "user_id")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Column(name = "module_code")
	public String getModuleCode() {
		return moduleCode;
	}
	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}
}
