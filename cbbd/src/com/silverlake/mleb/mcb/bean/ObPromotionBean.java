package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ObPromotionBean implements Serializable
{
	private int rowID;
	private byte[] image;
	private String title;
	private String descriptions;
	private String link;
	
	private String promotionName;
	private String promotionDesc;
	private String promotionType;
	private Character status;
	private Date createDt;
	private String createBy;
	private Date modifyDt;
	private String modifyBy;
	private String website;
	private String contactNumber;
	private String publishFrom;
	private String publishTo;
	private Integer position;
	private String promotionImageName;
	private byte[] promotionImag;
	private String promotionTo;
	private String promotionFrom;
	
	
	public int getRowID() {
		return rowID;
	}
	public void setRowID(int rowID) {
		this.rowID = rowID;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescriptions() {
		return descriptions;
	}
	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getPromotionName() {
		return promotionName;
	}
	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
	}
	public String getPromotionDesc() {
		return promotionDesc;
	}
	public void setPromotionDesc(String promotionDesc) {
		this.promotionDesc = promotionDesc;
	}
	public String getPromotionType() {
		return promotionType;
	}
	public void setPromotionType(String promotionType) {
		this.promotionType = promotionType;
	}
	public Character getStatus() {
		return status;
	}
	public void setStatus(Character status) {
		this.status = status;
	}
	public Date getCreateDt() {
		return createDt;
	}
	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getModifyDt() {
		return modifyDt;
	}
	public void setModifyDt(Date modifyDt) {
		this.modifyDt = modifyDt;
	}
	public String getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getPublishFrom() {
		return publishFrom;
	}
	public void setPublishFrom(String publishFrom) {
		this.publishFrom = publishFrom;
	}
	public String getPublishTo() {
		return publishTo;
	}
	public void setPublishTo(String publishTo) {
		this.publishTo = publishTo;
	}
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
	public String getPromotionImageName() {
		return promotionImageName;
	}
	public void setPromotionImageName(String promotionImageName) {
		this.promotionImageName = promotionImageName;
	}
	public byte[] getPromotionImag() {
		return promotionImag;
	}
	public void setPromotionImag(byte[] promotionImag) {
		this.promotionImag = promotionImag;
	}
//	public Date getPromotionTo() {
//		return promotionTo;
//	}
//	public void setPromotionTo(Date promotionTo) {
//		this.promotionTo = promotionTo;
//	}
//	public Date getPromotionFrom() {
//		return promotionFrom;
//	}
//	public void setPromotionFrom(Date promotionFrom) {
//		this.promotionFrom = promotionFrom;
//	}
	public String getPromotionTo() {
		return promotionTo;
	}
	public void setPromotionTo(String promotionTo) {
		this.promotionTo = promotionTo;
	}
	public String getPromotionFrom() {
		return promotionFrom;
	}
	public void setPromotionFrom(String promotionFrom) {
		this.promotionFrom = promotionFrom;
	}
	
}
