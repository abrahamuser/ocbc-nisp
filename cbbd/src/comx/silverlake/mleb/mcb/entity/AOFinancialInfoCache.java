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
@Table(name = "AO_FINANCIAL_INFO_CACHE")
public class AOFinancialInfoCache implements java.io.Serializable{

	private Integer rowId;
	private String deviceId;
	private String nik;
	private String monthlyNetIncome;
	private String yearlyNetIncome;
	private String yearlyAdditionalIncome;
	private String sourceOfIncome;
	private String status;
	private String isFinal;
	private String userId;
	private String moduleCode;
			
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "AO_FINANCIAL_INFO_CACHE_id")
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
	@Column(name = "monthlyNetIncome")
	public String getMonthlyNetIncome() {
		return monthlyNetIncome;
	}
	public void setMonthlyNetIncome(String monthlyNetIncome) {
		this.monthlyNetIncome = monthlyNetIncome;
	}
	@Column(name = "yearlyNetIncome")
	public String getYearlyNetIncome() {
		return yearlyNetIncome;
	}
	public void setYearlyNetIncome(String yearlyNetIncome) {
		this.yearlyNetIncome = yearlyNetIncome;
	}
	@Column(name = "yearlyAdditionalIncome")
	public String getYearlyAdditionalIncome() {
		return yearlyAdditionalIncome;
	}
	public void setYearlyAdditionalIncome(String yearlyAdditionalIncome) {
		this.yearlyAdditionalIncome = yearlyAdditionalIncome;
	}
	@Column(name = "sourceOfIncome")
	public String getSourceOfIncome() {
		return sourceOfIncome;
	}
	public void setSourceOfIncome(String sourceOfIncome) {
		this.sourceOfIncome = sourceOfIncome;
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
