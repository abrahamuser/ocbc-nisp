package com.silverlake.mleb.mcb.bean.sample;

import java.io.Serializable;
 

public class RequestBody implements Serializable {

	
	private SendMoney[] sendMoney;

    public SendMoney[] getSendMoney ()
    {
        return sendMoney;
    }

    public void setSendMoney (SendMoney[] sendMoney)
    {
        this.sendMoney = sendMoney;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [sendMoney = "+sendMoney+"]";
    }
	
	
	
}
