package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ListFaq implements Serializable {

	private String id;
    private String cat_cd;
    private String cat_desc;
    private String title;
    private String content;
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
	public String getCat_desc() {
		return cat_desc;
	}
	public void setCat_desc(String cat_desc) {
		this.cat_desc = cat_desc;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
    
    

}

