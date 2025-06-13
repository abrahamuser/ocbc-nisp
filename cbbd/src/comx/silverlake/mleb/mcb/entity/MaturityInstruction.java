package comx.silverlake.mleb.mcb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//@Entity
@Table(name = "maturity_instruction")
public class MaturityInstruction implements java.io.Serializable{

	private Integer rowId;
	private String maturityCode;
	private String maturityValue;
	private String maturityDesc;
	private String renewalCode;
	private String nationalCode;
	private String category;
	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "MATURITY_ID")
	@Column(name = "row_id", nullable=false)
	public Integer getRowId() {
		return rowId;
	}
	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}
	@Column(name = "maturity_code", length=25)
	public String getMaturityCode() {
		return maturityCode;
	}
	public void setMaturityCode(String maturityCode) {
		this.maturityCode = maturityCode;
	}
	@Column(name = "maturity_value")
	public String getMaturityValue() {
		return maturityValue;
	}
	public void setMaturityValue(String maturityValue) {
		this.maturityValue = maturityValue;
	}
	@Column(name = "maturity_desc", length=700)
	public String getMaturityDesc() {
		return maturityDesc;
	}
	public void setMaturityDesc(String maturityDesc) {
		this.maturityDesc = maturityDesc;
	}
	@Column(name = "renewal_code", length=1)
	public String getRenewalCode() {
		return renewalCode;
	}
	public void setRenewalCode(String renewalCode) {
		this.renewalCode = renewalCode;
	}
	@Column(name = "national_code", length=5)
	public String getNationalCode() {
		return nationalCode;
	}
	public void setNationalCode(String nationalCode) {
		this.nationalCode = nationalCode;
	}
	@Column(name = "category", length=50)
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
}
