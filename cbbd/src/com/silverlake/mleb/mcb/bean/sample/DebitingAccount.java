package com.silverlake.mleb.mcb.bean.sample;

import java.io.Serializable;

public class DebitingAccount implements Serializable {

	/**
	 * 
	 */
	private String accLabel;

    private String accNo;

    private String accType;

    public String getAccLabel ()
    {
        return accLabel;
    }

    public void setAccLabel (String accLabel)
    {
        this.accLabel = accLabel;
    }

    public String getAccNo ()
    {
        return accNo;
    }

    public void setAccNo (String accNo)
    {
        this.accNo = accNo;
    }

    public String getAccType ()
    {
        return accType;
    }

    public void setAccType (String accType)
    {
        this.accType = accType;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [accLabel = "+accLabel+", accNo = "+accNo+", accType = "+accType+"]";
    }
	
	
}

