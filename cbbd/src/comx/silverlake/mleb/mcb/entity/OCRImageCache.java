package comx.silverlake.mleb.mcb.entity;

import javax.persistence.*;
import java.util.Date;

//@Entity
@Table(name = "OCR_Image_Cache")
public class OCRImageCache implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private Integer rowId;
	private byte[] imageData;
	private String imageType;
	private Date createTimestamp;
	private String userId;
	private String uuid;
	private String moduleCode;
	private String status;
	private String imageAdditionalInfo;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "OCR_IMAGE_CACHE_ID")

	@Column(name = "row_id", nullable=false)
	public Integer getRowId() {
		return rowId;
	}
	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}
	
	@Column(name = "image_data", columnDefinition="BLOB")
	public byte[] getImageData() {
		return imageData;
	}
	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}
	
	@Column(name = "image_Type")
	public String getImageType() {
		return imageType;
	}
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_timestamp")
	public Date getCreateTimestamp() {
		return createTimestamp;
	}
	public void setCreateTimestamp(Date createTimestamp) {
		this.createTimestamp = createTimestamp;
	}
	
	@Column(name = "uuid")
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	@Column(name = "module_code")
	public String getModuleCode() {
		return moduleCode;
	}
	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}
	
	@Column(name = "status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "user_id")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "image_additional_info")
	public String getImageAdditionalInfo() {
		return imageAdditionalInfo;
	}

	public void setImageAdditionalInfo(String imageAdditionalInfo) {
		this.imageAdditionalInfo = imageAdditionalInfo;
	}
}
