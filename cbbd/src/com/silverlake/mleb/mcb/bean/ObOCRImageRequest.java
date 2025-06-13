package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObOCRImageRequest extends ObRequestCache<ObSessionCache> implements Serializable{

	private static final long serialVersionUID = -3405372928402024993L;
	private ObOCRImageBean imageBean;
	private String moduleCode;
	private String imageType;
	private String imageAdditionalInfo;
	
	
	public String getModuleCode() {
		return moduleCode;
	}
	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}
	public ObOCRImageBean getImageBean() {
		return imageBean;
	}
	public void setImageBean(ObOCRImageBean imageBean) {
		this.imageBean = imageBean;
	}
	public String getImageType() {
		return imageType;
	}
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public String getImageAdditionalInfo() {
		return imageAdditionalInfo;
	}

	public void setImageAdditionalInfo(String imageAdditionalInfo) {
		this.imageAdditionalInfo = imageAdditionalInfo;
	}
}
