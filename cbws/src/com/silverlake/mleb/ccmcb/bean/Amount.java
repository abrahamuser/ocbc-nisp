package com.silverlake.mleb.ccmcb.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class Amount implements Serializable{

	 private String amount;

	    private String currencyCode;

	    public String getAmount ()
	    {
	        return amount;
	    }

	    public void setAmount (String amount)
	    {
	        this.amount = amount;
	    }

	    public String getCurrencyCode ()
	    {
	        return currencyCode;
	    }

	    public void setCurrencyCode (String currencyCode)
	    {
	        this.currencyCode = currencyCode;
	    }

	    @Override
	    public String toString()
	    {
	        return "ClassPojo [amount = "+amount+", currencyCode = "+currencyCode+"]";
	    }
	
}
