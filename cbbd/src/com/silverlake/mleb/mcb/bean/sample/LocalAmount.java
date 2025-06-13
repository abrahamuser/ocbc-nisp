package com.silverlake.mleb.mcb.bean.sample;

import java.io.Serializable;

public class LocalAmount implements Serializable
{
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
