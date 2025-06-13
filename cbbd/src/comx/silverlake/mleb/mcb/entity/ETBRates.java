package comx.silverlake.mleb.mcb.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//@Entity
@Table(name = "ETB_Rates")
public class ETBRates implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private Integer rowId;
	private String msc;
	private String loanSizeType;
	
	private BigDecimal interestRate12Month;
	private BigDecimal interestRate24Month;
	private BigDecimal interestRate36Month;
	private BigDecimal interestRate48Month;
	
	private BigDecimal effectiveRate12Month;
	private BigDecimal effectiveRate24Month;
	private BigDecimal effectiveRate36Month;
	private BigDecimal effectiveRate48Month;
	
	private BigDecimal provision12Month;
	private BigDecimal provision24Month;
	private BigDecimal provision36Month;
	private BigDecimal provision48Month;
	
	private BigDecimal insurance12Month;
	private BigDecimal insurance24Month;
	private BigDecimal insurance36Month;
	private BigDecimal insurance48Month;
	
	private BigDecimal transferFee12Month;
	private BigDecimal transferFee24Month;
	private BigDecimal transferFee36Month;
	private BigDecimal transferFee48Month;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "ETB_Rates_ID")
	
	@Column(name = "row_id", nullable=false)
	public Integer getRowId() {
		return rowId;
	}
	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}
	
	@Column(name = "msc")
	public String getMsc() {
		return msc;
	}
	public void setMsc(String msc) {
		this.msc = msc;
	}
	
	@Column(name = "loan_size_type")
	public String getLoanSizeType() {
		return loanSizeType;
	}
	public void setLoanSizeType(String loanSizeType) {
		this.loanSizeType = loanSizeType;
	}
	
	@Column(name = "interest_rate_12month")
	public BigDecimal getInterestRate12Month() {
		return interestRate12Month;
	}
	public void setInterestRate12Month(BigDecimal interestRate12Month) {
		this.interestRate12Month = interestRate12Month;
	}
	
	@Column(name = "interest_rate_24month")
	public BigDecimal getInterestRate24Month() {
		return interestRate24Month;
	}
	public void setInterestRate24Month(BigDecimal interestRate24Month) {
		this.interestRate24Month = interestRate24Month;
	}
	
	@Column(name = "interest_rate_36month")
	public BigDecimal getInterestRate36Month() {
		return interestRate36Month;
	}
	public void setInterestRate36Month(BigDecimal interestRate36Month) {
		this.interestRate36Month = interestRate36Month;
	}
	
	@Column(name = "interest_rate_48month")
	public BigDecimal getInterestRate48Month() {
		return interestRate48Month;
	}
	public void setInterestRate48Month(BigDecimal interestRate48Month) {
		this.interestRate48Month = interestRate48Month;
	}
	
	@Column(name = "effective_rate_12month")
	public BigDecimal getEffectiveRate12Month() {
		return effectiveRate12Month;
	}
	public void setEffectiveRate12Month(BigDecimal effectiveRate12Month) {
		this.effectiveRate12Month = effectiveRate12Month;
	}
	
	@Column(name = "effective_rate_24month")
	public BigDecimal getEffectiveRate24Month() {
		return effectiveRate24Month;
	}
	public void setEffectiveRate24Month(BigDecimal effectiveRate24Month) {
		this.effectiveRate24Month = effectiveRate24Month;
	}
	
	@Column(name = "effective_rate_36month")
	public BigDecimal getEffectiveRate36Month() {
		return effectiveRate36Month;
	}
	public void setEffectiveRate36Month(BigDecimal effectiveRate36Month) {
		this.effectiveRate36Month = effectiveRate36Month;
	}
	
	@Column(name = "effective_rate_48month")
	public BigDecimal getEffectiveRate48Month() {
		return effectiveRate48Month;
	}
	public void setEffectiveRate48Month(BigDecimal effectiveRate48Month) {
		this.effectiveRate48Month = effectiveRate48Month;
	}
	
	@Column(name = "provision_12month")
	public BigDecimal getProvision12Month() {
		return provision12Month;
	}
	public void setProvision12Month(BigDecimal provision12Month) {
		this.provision12Month = provision12Month;
	}
	
	@Column(name = "provision_24month")
	public BigDecimal getProvision24Month() {
		return provision24Month;
	}
	public void setProvision24Month(BigDecimal provision24Month) {
		this.provision24Month = provision24Month;
	}
	
	@Column(name = "provision_36month")
	public BigDecimal getProvision36Month() {
		return provision36Month;
	}
	public void setProvision36Month(BigDecimal provision36Month) {
		this.provision36Month = provision36Month;
	}
	
	@Column(name = "provision_48month")
	public BigDecimal getProvision48Month() {
		return provision48Month;
	}
	public void setProvision48Month(BigDecimal provision48Month) {
		this.provision48Month = provision48Month;
	}
	
	@Column(name = "insurance_12month")
	public BigDecimal getInsurance12Month() {
		return insurance12Month;
	}
	public void setInsurance12Month(BigDecimal insurance12Month) {
		this.insurance12Month = insurance12Month;
	}
	
	@Column(name = "insurance_24month")
	public BigDecimal getInsurance24Month() {
		return insurance24Month;
	}
	public void setInsurance24Month(BigDecimal insurance24Month) {
		this.insurance24Month = insurance24Month;
	}
	
	@Column(name = "insurance_36month")
	public BigDecimal getInsurance36Month() {
		return insurance36Month;
	}
	public void setInsurance36Month(BigDecimal insurance36Month) {
		this.insurance36Month = insurance36Month;
	}
	
	@Column(name = "insurance_48month")
	public BigDecimal getInsurance48Month() {
		return insurance48Month;
	}
	public void setInsurance48Month(BigDecimal insurance48Month) {
		this.insurance48Month = insurance48Month;
	}
	
	@Column(name = "transfer_fee_12month")
	public BigDecimal getTransferFee12Month() {
		return transferFee12Month;
	}
	public void setTransferFee12Month(BigDecimal transferFee12Month) {
		this.transferFee12Month = transferFee12Month;
	}
	
	@Column(name = "transfer_fee_24month")
	public BigDecimal getTransferFee24Month() {
		return transferFee24Month;
	}
	public void setTransferFee24Month(BigDecimal transferFee24Month) {
		this.transferFee24Month = transferFee24Month;
	}
	
	@Column(name = "transfer_fee_36month")
	public BigDecimal getTransferFee36Month() {
		return transferFee36Month;
	}
	public void setTransferFee36Month(BigDecimal transferFee36Month) {
		this.transferFee36Month = transferFee36Month;
	}
	
	@Column(name = "transfer_fee_48month")
	public BigDecimal getTransferFee48Month() {
		return transferFee48Month;
	}
	public void setTransferFee48Month(BigDecimal transferFee48Month) {
		this.transferFee48Month = transferFee48Month;
	}
}
