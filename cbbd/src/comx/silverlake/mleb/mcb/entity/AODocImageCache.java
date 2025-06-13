package comx.silverlake.mleb.mcb.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//@Entity
@Table(name = "ao_doc_image_cache")
public class AODocImageCache implements java.io.Serializable{

	private Integer rowId;
	private String deviceId;
	private String nik;
//	@Lob
//	private byte[] ktpImage;
//	@Lob
//	private byte[] sktpImage;
//	@Lob
//	private byte[] npwpImage;
//	@Lob
//	private byte[] signImage;
//	@Lob
//	private byte[] sptImage;
	private String docType;
	private String uuid;
	private String isFinal;
	
			
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "AO_DOC_IMAGE_CACHE_ID")
	@Column(name = "row_id", nullable=false)
	public Integer getRowId() {
		return rowId;
	}
	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}
	@Column(name = "device_id")
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	@Column(name = "nik")
	public String getNik() {
		return nik;
	}
	public void setNik(String nik) {
		this.nik = nik;
	}
//	@Column(name = "ktp_image", columnDefinition="BLOB")
//	public byte[] getKtpImage() {
//		return ktpImage;
//	}
//	public void setKtpImage(byte[] ktpImage) {
//		this.ktpImage = ktpImage;
//	}
//	@Column(name = "sktp_image", columnDefinition="BLOB")
//	public byte[] getSktpImage() {
//		return sktpImage;
//	}
//	public void setSktpImage(byte[] sktpImage) {
//		this.sktpImage = sktpImage;
//	}
//	@Column(name = "npwp_image", columnDefinition="BLOB")
//	public byte[] getNpwpImage() {
//		return npwpImage;
//	}
//	public void setNpwpImage(byte[] npwpImage) {
//		this.npwpImage = npwpImage;
//	}
//	@Column(name = "sign_image", columnDefinition="BLOB")
//	public byte[] getSignImage() {
//		return signImage;
//	}
//	public void setSignImage(byte[] signImage) {
//		this.signImage = signImage;
//	}
//	@Column(name = "spt_image", columnDefinition="BLOB")
//	public byte[] getSptImage() {
//		return sptImage;
//	}
//	public void setSptImage(byte[] sptImage) {
//		this.sptImage = sptImage;
//	}
	@Column(name = "is_final")
	public String getIsFinal() {
		return isFinal;
	}
	public void setIsFinal(String isFinal) {
		this.isFinal = isFinal;
	}
	@Column(name = "uuid")
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	@Column(name = "doc_type")
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	
}
