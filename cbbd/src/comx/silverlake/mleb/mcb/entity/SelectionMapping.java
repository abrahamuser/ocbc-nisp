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
@Table(name = "SELECTION_MAPPING")
public class SelectionMapping implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer rowId;
	private String workTypeKey;
	private String jabatanKey;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "SELECTION_MAPPING_ID")
	@Column(name = "row_id", nullable=false)
	public Integer getRowId() {
		return rowId;
	}
	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}
	@Column(name = "worktype_key")
	public String getWorkTypeKey() {
		return workTypeKey;
	}
	public void setWorkTypeKey(String workTypeKey) {
		this.workTypeKey = workTypeKey;
	}
	@Column(name = "jabatan_key")
	public String getJabatanKey() {
		return jabatanKey;
	}
	public void setJabatanKey(String jabatanKey) {
		this.jabatanKey = jabatanKey;
	}
}
