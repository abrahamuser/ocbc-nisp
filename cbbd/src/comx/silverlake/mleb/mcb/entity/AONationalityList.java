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
@Table(name = "ao_nationalityList")
public class AONationalityList implements java.io.Serializable{

	private Integer rowId;
	private String nationalCode;
	private String nationalName;
	private String isEligible;
	private String status;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "AO_Nationality_id")
	@Column(name = "row_id", nullable=false)
	public Integer getRowId() {
		return rowId;
	}
	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}
	@Column(name = "national_code")
	public String getNationalCode() {
		return nationalCode;
	}
	public void setNationalCode(String nationalCode) {
		this.nationalCode = nationalCode;
	}
	@Column(name = "national_name")
	public String getNationalName() {
		return nationalName;
	}
	public void setNationalName(String nationalName) {
		this.nationalName = nationalName;
	}
	@Column(name = "status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name = "is_eligible")
	public String getIsEligible() {
		return isEligible;
	}
	public void setIsEligible(String isEligible) {
		this.isEligible = isEligible;
	}
	
	
}
