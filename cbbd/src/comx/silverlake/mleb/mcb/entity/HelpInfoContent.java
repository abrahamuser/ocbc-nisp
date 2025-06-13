package comx.silverlake.mleb.mcb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//@Entity
@Table(name = "HELP_INFO_CONTENT")
public class HelpInfoContent implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer rowId;
	private String productKey;
	private String productPage;
	private String contentTitle;
	private String helpInfoContentURL; 
	private String locale;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "HELP_INFO_CONTENT_ID")
	@Column(name = "row_id", nullable=false)
	public Integer getRowId() {
		return rowId;
	}
	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}
	
	@Column(name = "product_key")
	public String getProductKey() {
		return productKey;
	}
	public void setProductKey(String productKey) {
		this.productKey = productKey;
	}
	
	@Column(name = "product_page")
	public String getProductPage() {
		return productPage;
	}
	public void setProductPage(String productPage) {
		this.productPage = productPage;
	}
	
	@Column(name = "HELP_INFO_CONTENT_URL")
	public String getHelpInfoContentURL() {
		return helpInfoContentURL;
	}
	public void setHelpInfoContentURL(String helpInfoContentURL) {
		this.helpInfoContentURL = helpInfoContentURL;
	}
	
	@Column(name = "LOCALE")
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	
	@Column(name = "CONTENT_TITLE")
	public String getContentTitle() {
		return contentTitle;
	}
	public void setContentTitle(String contentTitle) {
		this.contentTitle = contentTitle;
	}
	
	
}
