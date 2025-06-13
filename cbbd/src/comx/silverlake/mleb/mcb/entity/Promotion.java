package comx.silverlake.mleb.mcb.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;

//@Entity
@Table(name = "promotion")
public class Promotion implements java.io.Serializable{
	
	private Integer promotionId;
	private String promotionName;
	private String promotionDesc;
	private Date createDt;
	private String createBy;
	private Date modifyDt;
	private String modifyBy;
	private String status;
	private Date publishFrom;
	private Date publishTo;
	private String website;
	private String contactNumber;
	private String promotionType;
	private Integer position;
	private String promotionImageName;
	private Date promotionTo;
	
	private String attachmentFileName;
	private String attachmentFilePath;
	
	private Date promotionFrom;
	private byte[] promoImage;
	
	private List<PromotionI18n> promotionI18nList;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "Promotion_id")
	@Column(name = "promotion_id", nullable=false)
	public Integer getPromotionId() {
		return promotionId;
	}
	public void setPromotionId(Integer promotionId) {
		this.promotionId = promotionId;
	}
	
	@Column(name = "promotion_name", length = 50)
	public String getPromotionName() {
		return promotionName;
	}
	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
	}
	
	@Column(name = "promotion_desc", length = 150)
	public String getPromotionDesc() {
		return promotionDesc;
	}
	public void setPromotionDesc(String promotionDesc) {
		this.promotionDesc = promotionDesc;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_dt", length = 19)
	public Date getCreateDt() {
		return createDt;
	}
	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}
	
	@Column(name = "create_by", length = 50)
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_dt", length = 19)
	public Date getModifyDt() {
		return modifyDt;
	}
	public void setModifyDt(Date modifyDt) {
		this.modifyDt = modifyDt;
	}
	
	@Column(name = "modify_by", length = 50)
	public String getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}
	
	@Column(name = "status", length = 12)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "publish_from", length = 19)
	public Date getPublishFrom() {
		return publishFrom;
	}
	public void setPublishFrom(Date publishFrom) {
		this.publishFrom = publishFrom;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "publish_to", length = 19)
	public Date getPublishTo() {
		return publishTo;
	}
	public void setPublishTo(Date publishTo) {
		this.publishTo = publishTo;
	}
	
	@Column(name = "website", length = 255)
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	
	@Column(name = "contact_number", length = 20)
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	@Column(name = "promotion_type", length = 5)
	public String getPromotionType() {
		return promotionType;
	}
	public void setPromotionType(String promotionType) {
		this.promotionType = promotionType;
	}
	
	@Column(name = "position")
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
	
	@Column(name = "promotion_image_name")
	public String getPromotionImageName() {
		return promotionImageName;
	}
	public void setPromotionImageName(String promotionImageName) {
		this.promotionImageName = promotionImageName;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "promotion_to", length = 19)
	public Date getPromotionTo() {
		return promotionTo;
	}
	public void setPromotionTo(Date promotionTo) {
		this.promotionTo = promotionTo;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "promotion_from", length = 19)
	public Date getPromotionFrom() {
		return promotionFrom;
	}
	public void setPromotionFrom(Date promotionFrom) {
		this.promotionFrom = promotionFrom;
	}
	
	@OneToMany(mappedBy="promotion", fetch=FetchType.LAZY)
	@ForeignKey(name = "none") 
	public List<PromotionI18n> getPromotionI18nList() {
		return promotionI18nList;
	}
	public void setPromotionI18nList(List<PromotionI18n> promotionI18nList) {
		this.promotionI18nList = promotionI18nList;
	}
	
	@Column(name = "attachment_file_name", length = 100)
	public String getAttachmentFileName() {
		return attachmentFileName;
	}

	public void setAttachmentFileName(String attachmentFileName) {
		this.attachmentFileName = attachmentFileName;
	}

	@Column(name = "attachment_file_path")
	public String getAttachmentFilePath() {
		return attachmentFilePath;
	}

	public void setAttachmentFilePath(String attachmentFilePath) {
		this.attachmentFilePath = attachmentFilePath;
	}
	
	@Column(name = "promoImage", columnDefinition="BLOB")
	public byte[] getPromoImage() {
		return promoImage;
	}
	public void setPromoImage(byte[] promoImage) {
		this.promoImage = promoImage;
	}
	

}
