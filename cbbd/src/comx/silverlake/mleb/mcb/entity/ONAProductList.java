package comx.silverlake.mleb.mcb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//@Entity
@Table(name = "ona_productList")
public class ONAProductList implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer rowId;
	private String productName;
	private String productDesc;
	private String productNameIN;
	private String productDescIN;
	private String productType;
	private String accountType;
	private String category;
	private String subscriptionType;
//	private String imageURL;
	private String nationalCode;
	private String nationalName;
	private String isEligible;
	private String status;
	private Integer digit;
	private String productKey;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "ONA_ProductList_id")
	@Column(name = "row_id", nullable=false)
	public Integer getRowId() {
		return rowId;
	}
	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}
	@Column(name = "product_name")
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	@Column(name = "product_desc")
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	/*@Column(name = "image_url")
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}*/
	@Column(name = "status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name = "product_type")
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	@Column(name = "account_type")
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	@Column(name = "subscription_type")
	public String getSubscriptionType() {
		return subscriptionType;
	}
	public void setSubscriptionType(String subscriptionType) {
		this.subscriptionType = subscriptionType;
	}
	@Column(name = "category")
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Column(name = "product_name_in")
	public String getProductNameIN() {
		return productNameIN;
	}
	public void setProductNameIN(String productNameIN) {
		this.productNameIN = productNameIN;
	}
	@Column(name = "product_desc_in")
	public String getProductDescIN() {
		return productDescIN;
	}
	public void setProductDescIN(String productDescIN) {
		this.productDescIN = productDescIN;
	}
	@Column(name = "national_code")
	public String getNationalCode() {
		return nationalCode;
	}
	public void setNationalCode(String nationalCode) {
		this.nationalCode = nationalCode;
	}
	@Column(name = "national_name")
	public String getNationalName() {
		return nationalName;
	}
	public void setNationalName(String nationalName) {
		this.nationalName = nationalName;
	}
	@Column(name = "is_eligible")
	public String getIsEligible() {
		return isEligible;
	}
	public void setIsEligible(String isEligible) {
		this.isEligible = isEligible;
	}
	@Column(name = "digit")
	public Integer getDigit() {
		return digit;
	}
	public void setDigit(Integer digit) {
		this.digit = digit;
	}
	@Column(name = "product_key")
	public String getProductKey() {
		return productKey;
	}
	public void setProductKey(String productKey) {
		this.productKey = productKey;
	}

}
