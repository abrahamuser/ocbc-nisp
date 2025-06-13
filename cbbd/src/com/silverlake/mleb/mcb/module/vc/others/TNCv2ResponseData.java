package com.silverlake.mleb.mcb.module.vc.others;

import java.util.List;

import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;

public class TNCv2ResponseData extends VCResponseData {
	
	private List<File> file_list ;

	public List<File> getFile_list() {
		return file_list;
	}

	public void setFile_list(List<File> file_list) {
		this.file_list = file_list;
	}
	
	
}
