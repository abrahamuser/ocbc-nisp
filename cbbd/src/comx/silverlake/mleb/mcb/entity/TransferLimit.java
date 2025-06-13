package comx.silverlake.mleb.mcb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//@Entity
@Table(name = "transfer_limit")
public class TransferLimit implements java.io.Serializable {

	private static final long serialVersionUID = -7869668039074501759L;
	
	private Integer rowId;
	private String transferCode;
	private String minAmount;
	private String singleTransAmount;
	private String dailyAcctTransAmount;
	private String dailyCIFTransAmount;
	private String usedTxnAmount;
	private String usedTxnCIFAmount;
	private String availableTransAccount;
	private String availableTransCIF;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "TRANSFER_LIMIT_ID")
	@Column(name = "row_id", nullable=false)
	public Integer getRowId() {
		return rowId;
	}
	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}
	
	@Column(name = "transfer_code", length=25)
	public String getTransferCode() {
		return transferCode;
	}
	public void setTransferCode(String transferCode) {
		this.transferCode = transferCode;
	}
	
	@Column(name = "min_amount")
	public String getMinAmount() {
		return minAmount;
	}
	public void setMinAmount(String minAmount) {
		this.minAmount = minAmount;
	}
	
	@Column(name = "single_trans_amount")
	public String getSingleTransAmount() {
		return singleTransAmount;
	}
	public void setSingleTransAmount(String singleTransAmount) {
		this.singleTransAmount = singleTransAmount;
	}
	
	@Column(name = "daily_acct_trans_amount")
	public String getDailyAcctTransAmount() {
		return dailyAcctTransAmount;
	}
	public void setDailyAcctTransAmount(String dailyAcctTransAmount) {
		this.dailyAcctTransAmount = dailyAcctTransAmount;
	}
	
	@Column(name = "daily_cif_trans_amount")
	public String getDailyCIFTransAmount() {
		return dailyCIFTransAmount;
	}
	public void setDailyCIFTransAmount(String dailyCIFTransAmount) {
		this.dailyCIFTransAmount = dailyCIFTransAmount;
	}
	
	@Column(name = "used_txn_amount")
	public String getUsedTxnAmount() {
		return usedTxnAmount;
	}
	public void setUsedTxnAmount(String usedTxnAmount) {
		this.usedTxnAmount = usedTxnAmount;
	}
	
	@Column(name = "used_txn_cif_amount")
	public String getUsedTxnCIFAmount() {
		return usedTxnCIFAmount;
	}
	public void setUsedTxnCIFAmount(String usedTxnCIFAmount) {
		this.usedTxnCIFAmount = usedTxnCIFAmount;
	}
	
	@Column(name = "available_trans_account")
	public String getAvailableTransAccount() {
		return availableTransAccount;
	}
	public void setAvailableTransAccount(String availableTransAccount) {
		this.availableTransAccount = availableTransAccount;
	}
	
	@Column(name = "available_trans_cif")
	public String getAvailableTransCIF() {
		return availableTransCIF;
	}
	public void setAvailableTransCIF(String availableTransCIF) {
		this.availableTransCIF = availableTransCIF;
	}
	
	

}
