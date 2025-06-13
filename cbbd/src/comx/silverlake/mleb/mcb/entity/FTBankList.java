package comx.silverlake.mleb.mcb.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//@Entity
@Table(name = "FT_BANK_LIST")
public class FTBankList implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String bankId;
	private String bankCode;
	private String bankName;
	private String transferService;
	
	@Id
	@Column(name = "bank_id", nullable=false)
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	@Column(name = "bank_code", length=25)
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	@Column(name = "bank_name")
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	@Column(name = "transfer_service", length=20)
	public String getTransferService() {
		return transferService;
	}
	public void setTransferService(String transferService) {
		this.transferService = transferService;
	}
	
}
