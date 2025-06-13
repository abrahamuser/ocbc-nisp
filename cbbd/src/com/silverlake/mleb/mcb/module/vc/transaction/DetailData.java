package com.silverlake.mleb.mcb.module.vc.transaction;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class DetailData implements Serializable{
	private String label;
	private String value;
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}

