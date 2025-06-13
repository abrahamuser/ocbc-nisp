package com.silverlake.mleb.mcb.module.vc.task_list;

import java.util.List;

import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;

public class TaskListCountResponseData extends VCResponseData{
	private List<TaskStatusCount> list_task_count;

	public List<TaskStatusCount> getList_task_count() {
		return list_task_count;
	}

	public void setList_task_count(List<TaskStatusCount> list_task_count) {
		this.list_task_count = list_task_count;
	}
}



	
