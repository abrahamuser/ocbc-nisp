package com.silverlake.mleb.mcb.module.vc.others;

import java.io.Serializable;

public class ListFaq implements Serializable {

	private String id;
    private String cat_cd;
    private String cat_desc_en;
    private String cat_desc_id;
	private String cat_desc_cn;
    private String title_en;
    private String title_id;
	private String title_cn;
    private String content_en;
    private String content_id;
	private String content_cn;
    private Integer seq;
    
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCat_cd() {
		return cat_cd;
	}
	public void setCat_cd(String cat_cd) {
		this.cat_cd = cat_cd;
	}
	public String getCat_desc_en() {
		return cat_desc_en;
	}
	public void setCat_desc_en(String cat_desc_en) {
		this.cat_desc_en = cat_desc_en;
	}
	public String getCat_desc_id() {
		return cat_desc_id;
	}
	public void setCat_desc_id(String cat_desc_id) {
		this.cat_desc_id = cat_desc_id;
	}
	public String getCat_desc_cn() {
		return cat_desc_cn;
	}
	public void setCat_desc_cn(String cat_desc_cn) {
		this.cat_desc_cn = cat_desc_cn;
	}
	public String getTitle_en() {
		return title_en;
	}
	public void setTitle_en(String title_en) {
		this.title_en = title_en;
	}
	public String getTitle_id() {
		return title_id;
	}
	public void setTitle_id(String title_id) {
		this.title_id = title_id;
	}
	public String getTitle_cn() {
		return title_cn;
	}
	public void setTitle_cn(String title_cn) {
		this.title_cn = title_cn;
	}
	public String getContent_en() {
		return content_en;
	}
	public void setContent_en(String content_en) {
		this.content_en = content_en;
	}
	public String getContent_id() {
		return content_id;
	}
	public void setContent_id(String content_id) {
		this.content_id = content_id;
	}
	public String getContent_cn() {
		return content_cn;
	}
	public void setContent_cn(String content_cn) {
		this.content_cn = content_cn;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}

}

