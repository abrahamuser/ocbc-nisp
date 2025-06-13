package com.silverlake.mleb.mcb.module.vc.task_list;

import java.util.List;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

public class TaskListCountRequestData extends VCRequest{
	private List<String> list_source;

	public List<String> getList_source() {
		return list_source;
	}

	public void setList_source(List<String> list_source) {
		this.list_source = list_source;
	}
}



	
