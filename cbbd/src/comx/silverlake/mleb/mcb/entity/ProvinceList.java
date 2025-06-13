package comx.silverlake.mleb.mcb.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

////@Entity
//@Table(name = "province_list")
public class ProvinceList implements java.io.Serializable
{
	public ProvinceList() {
	}
	
	private String provinceCode;
	private String provinceName;
	private String provinceNameLocale1;
	private Date updatedDate;
	
	@Id
	@Column(name = "province_code", unique = true, nullable = false, length = 100)
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	
	@Column(name = "province_name", nullable = true, length = 1000)
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	
	@Column(name = "province_name_locale1", nullable = true, length = 1000)
	public String getProvinceNameLocale1() {
		return provinceNameLocale1;
	}
	public void setProvinceNameLocale1(String provinceNameLocale1) {
		this.provinceNameLocale1 = provinceNameLocale1;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date", length = 19)
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	
}
