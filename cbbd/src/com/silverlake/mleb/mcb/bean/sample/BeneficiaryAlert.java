package com.silverlake.mleb.mcb.bean.sample;

import java.io.Serializable;

public class BeneficiaryAlert implements Serializable{

	private String sendEmail;

    private String sendSms;

    private String isNewEmail;

    private String mobileNo;

    private String email;

    public String getSendEmail ()
    {
        return sendEmail;
    }

    public void setSendEmail (String sendEmail)
    {
        this.sendEmail = sendEmail;
    }

    public String getSendSms ()
    {
        return sendSms;
    }

    public void setSendSms (String sendSms)
    {
        this.sendSms = sendSms;
    }

    public String getIsNewEmail ()
    {
        return isNewEmail;
    }

    public void setIsNewEmail (String isNewEmail)
    {
        this.isNewEmail = isNewEmail;
    }

    public String getMobileNo ()
    {
        return mobileNo;
    }

    public void setMobileNo (String mobileNo)
    {
        this.mobileNo = mobileNo;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [sendEmail = "+sendEmail+", sendSms = "+sendSms+", isNewEmail = "+isNewEmail+", mobileNo = "+mobileNo+", email = "+email+"]";
    }
}
