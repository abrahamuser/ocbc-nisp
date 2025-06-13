package comx.silverlake.mleb.mcb.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//@Entity
@Table(name = "redirect_url")
public class RedirectURL
{
	private Integer urlId;
	private String faqUrl;
	private String insuranceUrl;
	private String promotionUrl;
	
	private String modifiedBy; 
	
	private Date modifiedDate; 
	
	private String status;
	
	private String type;
	
	public RedirectURL()
	{
		
	}
	
	public RedirectURL(Integer urlId, String faqUrl, String insuranceUrl, String promotionUrl, String modifiedBy, Date modifiedDate, String status, String type)
	{
		this.urlId = urlId;
		this.faqUrl = faqUrl;
		this.insuranceUrl = insuranceUrl;
		this.promotionUrl = promotionUrl;
		
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.status = status;
		this.type = type;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "RedirectURL_id")
	@Column(name = "url_id", nullable=false)
	public Integer getUrlId() {
		return urlId;
	}

	public void setUrlId(Integer urlId) {
		this.urlId = urlId;
	}

	@Column(name = "modified_by", length = 50)
	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Column(name = "modified_date")
	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Column(name = "status", length = 20)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "insurance_url", length = 200)
	public String getInsuranceUrl() {
		return insuranceUrl;
	}

	public void setInsuranceUrl(String insuranceUrl) {
		this.insuranceUrl = insuranceUrl;
	}
	
	@Column(name = "promotion_url", length = 200)
	public String getPromotionUrl() {
		return promotionUrl;
	}

	public void setPromotionUrl(String promotionUrl) {
		this.promotionUrl = promotionUrl;
	}
	
	@Column(name = "faq_url", length = 200)
	public String getFaqUrl() {
		return faqUrl;
	}

	public void setFaqUrl(String faqUrl) {
		this.faqUrl = faqUrl;
	}


	@Column(name = "type", length = 20)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
