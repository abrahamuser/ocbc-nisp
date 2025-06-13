package com.silverlake.mleb.mcb.module.vc.others;

import java.io.Serializable;

public class File implements Serializable {
	private String data;
    private String mime_type;
    private String file_name;
    private String file_type;
    private String ccy_code;
    
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getMime_type() {
		return mime_type;
	}
	public void setMime_type(String mime_type) {
		this.mime_type = mime_type;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getFile_type() {
		return file_type;
	}
	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}
	public String getCcy_code() {
		return ccy_code;
	}
	public void setCcy_code(String ccy_code) {
		this.ccy_code = ccy_code;
	}
	
}

