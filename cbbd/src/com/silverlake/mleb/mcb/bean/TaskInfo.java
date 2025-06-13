package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

import com.silverlake.mleb.mcb.module.vc.task_list.TaskCount;

public class TaskInfo implements Serializable{
 
	private String tranxSource;
	
	private List<TaskCount> taskCountList;

	public String getTranxSource() {
		return tranxSource;
	}

	public void setTranxSource(String tranxSource) {
		this.tranxSource = tranxSource;
	}

	public List<TaskCount> getTaskCountList() {
		return taskCountList;
	}

	public void setTaskCountList(List<TaskCount> taskCountList) {
		this.taskCountList = taskCountList;
	}
	
	
	
	
}
