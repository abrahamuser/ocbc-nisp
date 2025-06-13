package comx.silverlake.mleb.mcb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//@Entity
@Table(name = "AO_PARAM_CACHE")
public class AOParamCache implements java.io.Serializable{

	private int rowId;
	private String paramKey;
	private String paramValueEN;
	private String paramValueID;
	private String paramValueCN;
	private String paramType;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "AO_PARAM_CACHE_ID")
	@Column(name = "row_id")
	public int getRowId() {
		return rowId;
	}
	public void setRowId(int rowId) {
		this.rowId = rowId;
	}
	@Column(name = "param_key")
	public String getParamKey() {
		return paramKey;
	}
	public void setParamKey(String paramKey) {
		this.paramKey = paramKey;
	}
	
	@Column(name = "param_type")
	public String getParamType() {
		return paramType;
	}
	public void setParamType(String paramType) {
		this.paramType = paramType;
	}
	@Column(name = "param_value_en")
	public String getParamValueEN() {
		return paramValueEN;
	}
	public void setParamValueEN(String paramValueEN) {
		this.paramValueEN = paramValueEN;
	}
	@Column(name = "param_value_id")
	public String getParamValueID() {
		return paramValueID;
	}
	public void setParamValueID(String paramValueID) {
		this.paramValueID = paramValueID;
	}

	@Column(name = "param_value_cn")
	public String getParamValueCN() {
		return paramValueCN;
	}
	public void setParamValueCN(String paramValueCN) {
		this.paramValueCN = paramValueCN;
	}

}
