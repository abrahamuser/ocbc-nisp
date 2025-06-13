package com.silverlake.mleb.mcb.module.vc.task_list;

import java.io.Serializable;
import java.util.List;

public class TaskStatusCount  implements Serializable{
	private String source;
	private List<TaskCount> list_task_count_detail;
	
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public List<TaskCount> getList_task_count_detail() {
		return list_task_count_detail;
	}
	public void setList_task_count_detail(List<TaskCount> list_task_count_detail) {
		this.list_task_count_detail = list_task_count_detail;
	}
}



	
