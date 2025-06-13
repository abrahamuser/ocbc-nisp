package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;


public class ObPayeeTypeBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String billerType;

    private Integer sequence;

    private String billerGrpName;

    private String billerGrpCd;

    private Integer version;

    private String billerGrpId;
	
	private List<ObPayeeOrganisationBean> obPayeeBeanList;
	
	public String getBillerType() {
		return billerType;
	}
	public void setBillerType(String billerType) {
		this.billerType = billerType;
	}
	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	public String getBillerGrpName() {
		return billerGrpName;
	}
	public void setBillerGrpName(String billerGrpName) {
		this.billerGrpName = billerGrpName;
	}
	public String getBillerGrpCd() {
		return billerGrpCd;
	}
	public void setBillerGrpCd(String billerGrpCd) {
		this.billerGrpCd = billerGrpCd;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getBillerGrpId() {
		return billerGrpId;
	}
	public void setBillerGrpId(String billerGrpId) {
		this.billerGrpId = billerGrpId;
	}
	public List<ObPayeeOrganisationBean> getObPayeeBeanList() {
		return obPayeeBeanList;
	}
	public void setObPayeeBeanList(List<ObPayeeOrganisationBean> obPayeeBeanList) {
		this.obPayeeBeanList = obPayeeBeanList;
	}

}
