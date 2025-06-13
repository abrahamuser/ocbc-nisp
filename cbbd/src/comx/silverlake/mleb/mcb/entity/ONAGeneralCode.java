package comx.silverlake.mleb.mcb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
@Table(name = "ona_generalCode")
public class ONAGeneralCode implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String gnCode;
	private String gnCodeDescription;
	private String status;
	
	@Id
	@Column(name = "gncode")
	public String getGnCode() {
		return gnCode;
	}
	public void setGnCode(String gnCode) {
		this.gnCode = gnCode;
	}
	
	@Column(name = "gncode_description")
	public String getGnCodeDescription() {
		return gnCodeDescription;
	}
	public void setGnCodeDescription(String gnCodeDescription) {
		this.gnCodeDescription = gnCodeDescription;
	}
	
	@Column(name = "status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
