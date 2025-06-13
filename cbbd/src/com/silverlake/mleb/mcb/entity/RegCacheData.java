package com.silverlake.mleb.mcb.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="REG_CACHE_DATA")
public class RegCacheData implements java.io.Serializable {
	
	private String id;
	private byte[] regObject;
	private byte[] signerObject;
	private Date createDate;
	
	@Id
	@Column(name="rec_id",nullable=false) 
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="reg_obj")
	public byte[] getRegObject() {
		return regObject;
	}
	public void setRegObject(byte[] regObject) {
		this.regObject = regObject;
	}
	@Column(name="signer_obj")
	public byte[] getSignerObject() {
		return signerObject;
	}
	public void setSignerObject(byte[] signerObject) {
		this.signerObject = signerObject;
	}
	@Column(name="create_dt")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	

}
