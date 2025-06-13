package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class ObUnbilledCreditCardStatementResponse extends ObResponse implements Serializable {

	private static final long serialVersionUID = 320188084444806447L;

	private String unbilledCreditCardStatement;

	public String getUnbilledCreditCardStatement() {
		return unbilledCreditCardStatement;
	}

	public void setUnbilledCreditCardStatement(String unbilledCreditCardStatement) {
		this.unbilledCreditCardStatement = unbilledCreditCardStatement;
	}

}
