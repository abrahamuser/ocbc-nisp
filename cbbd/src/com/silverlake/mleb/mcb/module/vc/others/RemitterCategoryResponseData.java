package com.silverlake.mleb.mcb.module.vc.others;

import java.util.List;

import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;

public class RemitterCategoryResponseData extends VCResponseData {
	private List<RemitterCategoryList> data;

	public List<RemitterCategoryList> getData() {
		return data;
	}

	public void setData(List<RemitterCategoryList> data) {
		this.data = data;
	}
    
}

