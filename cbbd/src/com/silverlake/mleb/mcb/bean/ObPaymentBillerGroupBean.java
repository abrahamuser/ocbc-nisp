package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class ObPaymentBillerGroupBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String billerType;

    private Integer sequence;

    private String billerGrpName;

    private String billerGrpCd;

    private Integer version;

    private String billerGrpId;
    
    private String image;
    
    private List<ObPaymentBillerBean> obPaymentBillerBean;
    
    private String label1;
    private String label2;
    private String label3;
    private String label4;

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

	public List<ObPaymentBillerBean> getObPaymentBillerBean() {
		return obPaymentBillerBean;
	}

	public void setObPaymentBillerBean(List<ObPaymentBillerBean> obPaymentBillerBean) {
		this.obPaymentBillerBean = obPaymentBillerBean;
	}

	public String getLabel1() {
		return label1;
	}

	public void setLabel1(String label1) {
		this.label1 = label1;
	}

	public String getLabel2() {
		return label2;
	}

	public void setLabel2(String label2) {
		this.label2 = label2;
	}

	public String getLabel3() {
		return label3;
	}

	public void setLabel3(String label3) {
		this.label3 = label3;
	}

	public String getLabel4() {
		return label4;
	}

	public void setLabel4(String label4) {
		this.label4 = label4;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
}
