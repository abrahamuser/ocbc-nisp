package comx.silverlake.mleb.mcb.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

////@Entity
//@Table(name = "branch_list")
public class BranchList implements java.io.Serializable
{
	public BranchList() {
	}
	
	private String branchCode;
	private String branchName;
	private String branchNameLocale1;
	private String bankCode;
	private String provinceCode;
	private Date updatedDate;
	private String isOnTop;
	
	@Id
	@Column(name = "branch_code", unique = true, nullable = false, length = 100)
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	
	@Column(name = "branch_name", nullable = true, length = 500)
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	
	@Column(name = "branch_name_locale1", nullable = true, length = 500)
	public String getBranchNameLocale1() {
		return branchNameLocale1;
	}
	public void setBranchNameLocale1(String branchNameLocale1) {
		this.branchNameLocale1 = branchNameLocale1;
	}
	
	@Column(name = "bank_code", nullable = true, length = 100)
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	
	@Column(name = "province_code", nullable = true, length = 100)
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date", length = 19)
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	@Column(name = "is_on_top", nullable = true, length = 10)
	public String getIsOnTop() {
		return isOnTop;
	}
	public void setIsOnTop(String isOnTop) {
		this.isOnTop = isOnTop;
	}
	
}
