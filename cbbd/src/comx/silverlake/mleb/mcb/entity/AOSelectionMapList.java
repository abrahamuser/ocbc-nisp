package comx.silverlake.mleb.mcb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//@Entity
@Table(name = "ao_selection_map_list")
public class AOSelectionMapList implements java.io.Serializable{

	private Integer rowId;
	private Integer selectionId1;
	private Integer selectionId2;
	private String isHighRisk;
	private String listType;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "AO_SELECTION_MAP_LIST_ID")
	@Column(name = "ROW_ID", nullable=false)
	public Integer getRowId() {
		return rowId;
	}
	
	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}
	
	@Column(name = "SELECTION_ID1")
	public Integer getSelectionId1() {
		return selectionId1;
	}
	public void setSelectionId1(Integer selectionId1) {
		this.selectionId1 = selectionId1;
	}
	
	@Column(name = "SELECTION_ID2")
	public Integer getSelectionId2() {
		return selectionId2;
	}
	public void setSelectionId2(Integer selectionId2) {
		this.selectionId2 = selectionId2;
	}
	
	@Column(name = "ISHIGHRISK")
	public String getIsHighRisk() {
		return isHighRisk;
	}
	public void setIsHighRisk(String isHighRisk) {
		this.isHighRisk = isHighRisk;
	}
	
	@Column(name = "LISTTYPE")
	public String getListType() {
		return listType;
	}
	public void setListType(String listType) {
		this.listType = listType;
	}
	
	
}
