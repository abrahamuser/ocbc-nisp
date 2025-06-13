package com.silverlake.mleb.mcb.module.vc.notification;

import java.io.Serializable;

public class PushNotification implements Serializable {

	private String id;
	private String device_id;
	private String notif_subject;
	private String notif_message;
	private String notif_date;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDevice_id() {
		return device_id;
	}
	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}
	public String getNotif_subject() {
		return notif_subject;
	}
	public void setNotif_subject(String notif_subject) {
		this.notif_subject = notif_subject;
	}
	public String getNotif_message() {
		return notif_message;
	}
	public void setNotif_message(String notif_message) {
		this.notif_message = notif_message;
	}
	public String getNotif_date() {
		return notif_date;
	}
	public void setNotif_date(String notif_date) {
		this.notif_date = notif_date;
	}
}