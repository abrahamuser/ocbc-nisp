package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

import com.silverlake.mleb.mcb.module.vc.others.File;

public class ObDocumentDownloadResponse extends ObResponse implements Serializable {

	private List<File> file_list ;

	public List<File> getFile_list() {
		return file_list;
	}
	public void setFile_list(List<File> file_list) {
		this.file_list = file_list;
	}
}
