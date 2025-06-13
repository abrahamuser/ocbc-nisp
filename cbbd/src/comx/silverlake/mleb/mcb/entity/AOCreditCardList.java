package comx.silverlake.mleb.mcb.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//@Entity
@Table(name = "AO_CreditCardList")
public class AOCreditCardList implements java.io.Serializable{

	private Integer rowId;
	private String cardType;
	private String cardDesc;
	private String cardBenefits;
	private String cardDescIN;
	private String cardBenefitsIN;
	private String productType;
	private String isBonus;
	private String imageURL;
	private String status;
	private String productName;
	private String productNameIN;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "AO_CreditCardList_id")
	@Column(name = "row_id", nullable=false)
	public Integer getRowId() {
		return rowId;
	}
	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}
	@Column(name = "status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name = "card_type")
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	@Column(name = "card_desc")
	public String getCardDesc() {
		return cardDesc;
	}
	public void setCardDesc(String cardDesc) {
		this.cardDesc = cardDesc;
	}
	@Column(name = "card_benefits")
	public String getCardBenefits() {
		return cardBenefits;
	}
	public void setCardBenefits(String cardBenefits) {
		this.cardBenefits = cardBenefits;
	}
	@Column(name = "image_url")
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	@Column(name = "card_desc_in", length=900)
	public String getCardDescIN() {
		return cardDescIN;
	}
	public void setCardDescIN(String cardDescIN) {
		this.cardDescIN = cardDescIN;
	}
	@Column(name = "card_benefits_in")
	public String getCardBenefitsIN() {
		return cardBenefitsIN;
	}
	public void setCardBenefitsIN(String cardBenefitsIN) {
		this.cardBenefitsIN = cardBenefitsIN;
	}
	@Column(name = "product_type")
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	@Column(name = "is_bonus")
	public String getIsBonus() {
		return isBonus;
	}
	public void setIsBonus(String isBonus) {
		this.isBonus = isBonus;
	}
	@Column(name = "product_name")
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	@Column(name = "product_name_in")
	public String getProductNameIN() {
		return productNameIN;
	}
	public void setProductNameIN(String productNameIN) {
		this.productNameIN = productNameIN;
	}
	
	
	
}
