package comx.silverlake.mleb.mcb.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;


//@Entity
@Table(name = "store_profile")
public class StoreProfile implements java.io.Serializable {

	private Integer storeId;
	private String currencyCode;
	private String currency;
	private String languageCode;
	private Language language;
	private Integer maxImageSize;
	private Character status;
	private Date createDt;
	private String createBy;
	private Date modifyDt;
	private String modifyBy;
	
	public StoreProfile() {
	}

	public StoreProfile(int storeId, String currencyCode, String languageCode) {
		this.storeId = storeId;
		this.currencyCode = currencyCode;
		this.languageCode = languageCode;
		
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "StoreProfile_id")
	@Column(name = "store_id", nullable=false)
	public Integer getStoreId() {
		return this.storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	@Column(name = "currency_code", length = 5)
	public String getCurrencyCode() {
		return this.currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	
	@Column(name = "currency", length = 5)
	public String getCurrency(){
		return this.currency;
	}
	
	public void setCurrency(String currency){
		this.currency = currency;
	}

	@Column(name = "language_code", length = 30)
	public String getLanguageCode() {
		return this.languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	@Column(name = "max_image_size")
	public Integer getMaxImageSize() {
		return maxImageSize;
	}

	public void setMaxImageSize(Integer maxImageSize) {
		this.maxImageSize = maxImageSize;
	}

	@Column(name = "status", length = 1)
	public Character getStatus() {
		return this.status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_dt", length = 23)
	public Date getCreateDt() {
		return this.createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	@Column(name = "create_by", length = 50)
	public String getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_dt", length = 23)
	public Date getModifyDt() {
		return this.modifyDt;
	}

	public void setModifyDt(Date modifyDt) {
		this.modifyDt = modifyDt;
	}

	@Column(name = "modify_by", length = 50)
	public String getModifyBy() {
		return this.modifyBy;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	@OneToOne
	@ForeignKey(name = "none") 
    @JoinColumn(name = "language_code", referencedColumnName = "language_code", insertable=false, updatable=false)
	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

}
