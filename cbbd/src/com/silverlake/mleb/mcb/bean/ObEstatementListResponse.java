package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObEstatementListResponse extends ObResponse implements Serializable {

	private static final long serialVersionUID = -5972334645142089078L;
	
	private MapPojoBean eStatementList;

	public MapPojoBean geteStatementList() {
		return eStatementList;
	}

	public void seteStatementList(MapPojoBean eStatementList) {
		this.eStatementList = eStatementList;
	}
	
	
}
