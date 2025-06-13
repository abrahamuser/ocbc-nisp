package comx.silverlake.mleb.mcb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
@Table(name = "UT_TERM_TRNSACTION")
public class UTTermTransaction implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String productCategory;
	private String productID;
	private String productCode;
	private String productName;
	private String productCurrency;
	private String minSubsNew;
	private String minSubsAdd;
	private String subsFee;
	private String minRedempt;
	private String redemptFee;
	private String minSwitch;
	private String switchFee;
	private String minUnitAfterTrx;
	private String urlProspectus;
	private String urlFundFactSheet;
	private String productCategoryEn;
	private String riskProfile;
	private String riskProfileEn;
	private String minRedemptNom;
	private String minSwitchNom;
	private String isVisible;
	
	private String isRDB;
	private String minRDBSubs;
	private String isRDBAllowRedeem;
	private String isRDBFullRedeem;
	private String minRDBRedeem;
	private String minRDBRedeemNom;
	private String isRDBAllowSwitch;
	private String isRDBFullSwitching;
	private String minRDBSwitching;
	private String minRDBSwitchingNom;
	
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
	@Column(name = "product_currency")
	public String getProductCurrency() {
		return productCurrency;
	}
	public void setProductCurrency(String productCurrency) {
		this.productCurrency = productCurrency;
	}
	@Column(name = "min_subs_new")
	public String getMinSubsNew() {
		return minSubsNew;
	}
	public void setMinSubsNew(String minSubsNew) {
		this.minSubsNew = minSubsNew;
	}
	@Column(name = "min_subs_add")
	public String getMinSubsAdd() {
		return minSubsAdd;
	}
	public void setMinSubsAdd(String minSubsAdd) {
		this.minSubsAdd = minSubsAdd;
	}
	@Column(name = "subs_fee")
	public String getSubsFee() {
		return subsFee;
	}
	public void setSubsFee(String subsFee) {
		this.subsFee = subsFee;
	}
	@Column(name = "min_redempt")
	public String getMinRedempt() {
		return minRedempt;
	}
	public void setMinRedempt(String minRedempt) {
		this.minRedempt = minRedempt;
	}
	@Column(name = "redempt_fee")
	public String getRedemptFee() {
		return redemptFee;
	}
	public void setRedemptFee(String redemptFee) {
		this.redemptFee = redemptFee;
	}
	@Column(name = "min_switch")
	public String getMinSwitch() {
		return minSwitch;
	}
	public void setMinSwitch(String minSwitch) {
		this.minSwitch = minSwitch;
	}
	@Column(name = "switch_fee")
	public String getSwitchFee() {
		return switchFee;
	}
	public void setSwitchFee(String switchFee) {
		this.switchFee = switchFee;
	}
	@Column(name = "min_unit_after_trx")
	public String getMinUnitAfterTrx() {
		return minUnitAfterTrx;
	}
	public void setMinUnitAfterTrx(String minUnitAfterTrx) {
		this.minUnitAfterTrx = minUnitAfterTrx;
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
	@Column(name = "product_category_en")
	public String getProductCategoryEn() {
		return productCategoryEn;
	}
	public void setProductCategoryEn(String productCategoryEn) {
		this.productCategoryEn = productCategoryEn;
	}
	@Column(name = "risk_profile")
	public String getRiskProfile() {
		return riskProfile;
	}
	public void setRiskProfile(String riskProfile) {
		this.riskProfile = riskProfile;
	}
	@Column(name = "risk_profile_en")
	public String getRiskProfileEn() {
		return riskProfileEn;
	}
	public void setRiskProfileEn(String riskProfileEn) {
		this.riskProfileEn = riskProfileEn;
	}
	@Column(name = "min_redempt_nom")
	public String getMinRedemptNom() {
		return minRedemptNom;
	}
	public void setMinRedemptNom(String minRedemptNom) {
		this.minRedemptNom = minRedemptNom;
	}
	@Column(name = "min_switch_nom")
	public String getMinSwitchNom() {
		return minSwitchNom;
	}
	public void setMinSwitchNom(String minSwitchNom) {
		this.minSwitchNom = minSwitchNom;
	}
	@Column(name = "is_visible")
	public String getIsVisible() {
		return isVisible;
	}
	public void setIsVisible(String isVisible) {
		this.isVisible = isVisible;
	}
	@Column(name = "is_rdb")
	public String getIsRDB() {
		return isRDB;
	}
	public void setIsRDB(String isRDB) {
		this.isRDB = isRDB;
	}
	@Column(name = "min_rdb_subs")
	public String getMinRDBSubs() {
		return minRDBSubs;
	}
	public void setMinRDBSubs(String minRDBSubs) {
		this.minRDBSubs = minRDBSubs;
	}
	@Column(name = "is_rdb_allow_redeem")
	public String getIsRDBAllowRedeem() {
		return isRDBAllowRedeem;
	}
	public void setIsRDBAllowRedeem(String isRDBAllowRedeem) {
		this.isRDBAllowRedeem = isRDBAllowRedeem;
	}
	@Column(name = "is_rdb_full_redeem")
	public String getIsRDBFullRedeem() {
		return isRDBFullRedeem;
	}
	public void setIsRDBFullRedeem(String isRDBFullRedeem) {
		this.isRDBFullRedeem = isRDBFullRedeem;
	}
	@Column(name = "min_rdb_redeem")
	public String getMinRDBRedeem() {
		return minRDBRedeem;
	}
	public void setMinRDBRedeem(String minRDBRedeem) {
		this.minRDBRedeem = minRDBRedeem;
	}
	@Column(name = "min_rdb_redeem_nom")
	public String getMinRDBRedeemNom() {
		return minRDBRedeemNom;
	}
	public void setMinRDBRedeemNom(String minRDBRedeemNom) {
		this.minRDBRedeemNom = minRDBRedeemNom;
	}
	@Column(name = "is_rdb_allow_switch")
	public String getIsRDBAllowSwitch() {
		return isRDBAllowSwitch;
	}
	public void setIsRDBAllowSwitch(String isRDBAllowSwitch) {
		this.isRDBAllowSwitch = isRDBAllowSwitch;
	}
	@Column(name = "is_rdb_full_switching")
	public String getIsRDBFullSwitching() {
		return isRDBFullSwitching;
	}
	public void setIsRDBFullSwitching(String isRDBFullSwitching) {
		this.isRDBFullSwitching = isRDBFullSwitching;
	}
	@Column(name = "min_rdb_switching")
	public String getMinRDBSwitching() {
		return minRDBSwitching;
	}
	public void setMinRDBSwitching(String minRDBSwitching) {
		this.minRDBSwitching = minRDBSwitching;
	}
	@Column(name = "min_rdb_switching_nom")
	public String getMinRDBSwitchingNom() {
		return minRDBSwitchingNom;
	}
	public void setMinRDBSwitchingNom(String minRDBSwitchingNom) {
		this.minRDBSwitchingNom = minRDBSwitchingNom;
	}
}