package comx.silverlake.mleb.mcb.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//@Entity
@Table(name = "bank_city")
public class BankCity implements java.io.Serializable{
    
	private Integer rowId;
	private String cityId;
	private String cityCode;
	private String cityName;
	private String cityName_ID;
	private String countryCode;
	private String countryName;
	private String countryName_ID;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "BANK_CITY_ID")
	@Column(name = "row_id", nullable=false)
	public Integer getRowId() {
		return rowId;
	}
	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}
	
	@Column(name = "city_id", nullable = false, length = 50)
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	
	@Column(name = "city_code", length = 255)
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	
	@Column(name = "city_name", length = 255)
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	@Column(name = "city_name_id", length = 255)
	public String getCityName_ID() {
		return cityName_ID;
	}
	public void setCityName_ID(String cityName_ID) {
		this.cityName_ID = cityName_ID;
	}
	@Column(name = "country_code", nullable = false, length = 25)
	public String getCountryCode() {
		return countryCode;
	}
	
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	@Column(name = "country_name", nullable = false, length = 255)
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	@Column(name = "country_name_id", nullable = false, length = 255)
	public String getCountryName_ID() {
		return countryName_ID;
	}
	public void setCountryName_ID(String countryName_ID) {
		this.countryName_ID = countryName_ID;
	}
	
}
