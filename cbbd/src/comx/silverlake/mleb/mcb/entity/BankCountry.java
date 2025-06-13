package comx.silverlake.mleb.mcb.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
@Table(name = "bank_country")
public class BankCountry implements java.io.Serializable{


	private String countryCode;
	private String countryName;
	private String countryName_ID;
	
	@Id
	@Column(name = "country_code", unique = true, nullable = false, length = 25)
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
