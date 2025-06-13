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

////@Entity
//@Table(name = "ibt_batch_date")
public class IbtBatchDate implements java.io.Serializable
{
	public IbtBatchDate() {
	}
	
	private int rowId;
	private Date bankListDate;
	private Date proviceListDate;
	private Date branchListDate;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "IbtBatchDate_id")
	@Column(name = "row_id", nullable=false)
	public int getRowId() {
		return rowId;
	}
	public void setRowId(int rowId) {
		this.rowId = rowId;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "bank_list_date", length = 19)
	public Date getBankListDate() {
		return bankListDate;
	}
	public void setBankListDate(Date bankListDate) {
		this.bankListDate = bankListDate;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "province_list_date", length = 19)
	public Date getProviceListDate() {
		return proviceListDate;
	}
	public void setProviceListDate(Date proviceListDate) {
		this.proviceListDate = proviceListDate;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "branch_list_date", length = 19)
	public Date getBranchListDate() {
		return branchListDate;
	}
	public void setBranchListDate(Date branchListDate) {
		this.branchListDate = branchListDate;
	}
	
}
