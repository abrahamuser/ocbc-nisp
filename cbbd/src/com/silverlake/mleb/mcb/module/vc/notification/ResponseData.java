package com.silverlake.mleb.mcb.module.vc.notification;


import java.util.List;

import com.silverlake.mleb.mcb.module.vc.common.VCResponseData;

public class ResponseData extends VCResponseData{
	
	private Integer total_rows;
	private List<PushNotification> list_push_notif;
	private List<BoardNotification> list_notif_board;
	
	public Integer getTotal_rows() {
		return total_rows;
	}
	public void setTotal_rows(Integer total_rows) {
		this.total_rows = total_rows;
	}
	public List<PushNotification> getList_push_notif() {
		return list_push_notif;
	}
	public void setList_push_notif(List<PushNotification> list_push_notif) {
		this.list_push_notif = list_push_notif;
	}
	public List<BoardNotification> getList_notif_board() {
		return list_notif_board;
	}
	public void setList_notif_board(List<BoardNotification> list_notif_board) {
		this.list_notif_board = list_notif_board;
	}
	
	
	
	
	
	
}
