package com.silverlake.mleb.mcb.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "generalCode")
public class GeneralCode implements java.io.Serializable{

	private Integer rowId;
	private String gnCode;
	private String gnCodeDescription;
	private String gnCodeType;
	private String data;
	private String status;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "GeneralCode_id")
	@Column(name = "row_id", nullable=false)
	public Integer getRowId() {
		return rowId;
	}
	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}
	
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
	
	@Column(name = "gncode_type")
	public String getGnCodeType() {
		return gnCodeType;
	}
	public void setGnCodeType(String gnCodeType) {
		this.gnCodeType = gnCodeType;
	}
	
	@Column(name = "status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "data")
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	
}
