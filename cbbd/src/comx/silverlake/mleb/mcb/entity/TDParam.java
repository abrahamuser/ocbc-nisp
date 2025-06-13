package comx.silverlake.mleb.mcb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//@Entity
@Table(name = "TD_PARAM")
public class TDParam implements java.io.Serializable
{	
	private Integer rowId;
	private String purposeCode;
	private String purposeName;
	private String sourceOfFundCode;
	private String sourceOfFundName;
	private String tncEn;
	private String tncIn;
	private String tncCn;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "TD_PARAM_ID")
	@Column(name = "row_id", nullable=false)
	public Integer getRowId() {
		return rowId;
	}
	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}
	
	@Column(name = "purpose_code")
	public String getPurposeCode() {
		return purposeCode;
	}
	public void setPurposeCode(String purposeCode) {
		this.purposeCode = purposeCode;
	}
	
	@Column(name = "purpose_name")
	public String getPurposeName() {
		return purposeName;
	}
	public void setPurposeName(String purposeName) {
		this.purposeName = purposeName;
	}
	
	@Column(name = "source_of_fund_code")
	public String getSourceOfFundCode() {
		return sourceOfFundCode;
	}
	public void setSourceOfFundCode(String sourceOfFundCode) {
		this.sourceOfFundCode = sourceOfFundCode;
	}
	
	@Column(name = "source_of_fund_name")
	public String getSourceOfFundName() {
		return sourceOfFundName;
	}
	public void setSourceOfFundName(String sourceOfFundName) {
		this.sourceOfFundName = sourceOfFundName;
	}
	
	@Column(name = "tnc_en", length=500)
	public String getTncEn() {
		return tncEn;
	}
	public void setTncEn(String tncEn) {
		this.tncEn = tncEn;
	}
	
	@Column(name = "tnc_in", length=500)
	public String getTncIn() {
		return tncIn;
	}
	public void setTncIn(String tncIn) {
		this.tncIn = tncIn;
	}

	@Column(name = "tnc_cn", length=500)
	public String getTncCn() {
		return tncCn;
	}
	public void setTncCn(String tncCn) {
		this.tncCn = tncCn;
	}
}
