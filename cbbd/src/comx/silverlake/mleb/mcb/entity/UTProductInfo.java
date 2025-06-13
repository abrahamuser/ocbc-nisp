package comx.silverlake.mleb.mcb.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//@Entity
@Table(name = "UT_PRODUCT_INFO")
public class UTProductInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String productCategory;
	private String productID;
	private String productCode;
	private String productName;
	private BigDecimal nav;
	private String navDate;
	private String urlProspectus;
	private String urlFundFactSheet;
	private String riskProfileEn;
	private String riskProfile;
	private BigDecimal percentageDay;
	private BigDecimal percentageWeek;
	private BigDecimal percentageMonth;
	private BigDecimal percentageYear;
	
	@Id
	@Column(name = "product_code")
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	@Column(name = "product_category")
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	@Column(name = "product_id")
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	@Column(name = "product_name")
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	@Column(name = "nav")
	public BigDecimal getNav() {
		return nav;
	}
	public void setNav(BigDecimal nav) {
		this.nav = nav;
	}
	@Column(name = "nav_date")
	public String getNavDate() {
		return navDate;
	}
	public void setNavDate(String navDate) {
		this.navDate = navDate;
	}
	@Column(name = "url_prospectus")
	public String getUrlProspectus() {
		return urlProspectus;
	}
	public void setUrlProspectus(String urlProspectus) {
		this.urlProspectus = urlProspectus;
	}
	@Column(name = "url_fund_fact_sheet")
	public String getUrlFundFactSheet() {
		return urlFundFactSheet;
	}
	public void setUrlFundFactSheet(String urlFundFactSheet) {
		this.urlFundFactSheet = urlFundFactSheet;
	}
	@Column(name = "risk_profile_en")
	public String getRiskProfileEn() {
		return riskProfileEn;
	}
	public void setRiskProfileEn(String riskProfileEn) {
		this.riskProfileEn = riskProfileEn;
	}
	@Column(name = "percentage_day")
	public BigDecimal getPercentageDay() {
		return percentageDay;
	}
	public void setPercentageDay(BigDecimal percentageDay) {
		this.percentageDay = percentageDay;
	}
	@Column(name = "percentage_week")
	public BigDecimal getPercentageWeek() {
		return percentageWeek;
	}
	public void setPercentageWeek(BigDecimal percentageWeek) {
		this.percentageWeek = percentageWeek;
	}
	@Column(name = "percentage_month")
	public BigDecimal getPercentageMonth() {
		return percentageMonth;
	}
	public void setPercentageMonth(BigDecimal percentageMonth) {
		this.percentageMonth = percentageMonth;
	}
	@Column(name = "percentage_year")
	public BigDecimal getPercentageYear() {
		return percentageYear;
	}
	public void setPercentageYear(BigDecimal percentageYear) {
		this.percentageYear = percentageYear;
	}
	@Column(name = "risk_profile")
	public String getRiskProfile() {
		return riskProfile;
	}
	public void setRiskProfile(String riskProfile) {
		this.riskProfile = riskProfile;
	}
	
}
