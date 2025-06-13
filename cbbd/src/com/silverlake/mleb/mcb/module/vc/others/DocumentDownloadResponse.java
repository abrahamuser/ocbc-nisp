package com.silverlake.mleb.mcb.module.vc.others;

import java.util.List;

import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;

public class DocumentDownloadResponse extends VCResponseData {
	private List<File> file_list;

	public List<File> getFile_list() {
		return file_list;
	}

	public void setFile_list(List<File> file_list) {
		this.file_list = file_list;
	}
}
