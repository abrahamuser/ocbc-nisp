package com.silverlake.mleb.mcb.bean.sample;

import java.io.Serializable;


public class CreditingAccount implements Serializable
{
    private String toAcc;

    private String creditingType;

    private String bankCode;

    private String recipientType;

    private String idType;

    private String bankName;

    private String transferMethod;

    private String toAccLabel;

    private String toAccHolderName;

    private String currencyCode;

    private String idNo;

    private String paymentType;

    public String getToAcc ()
    {
        return toAcc;
    }

    public void setToAcc (String toAcc)
    {
        this.toAcc = toAcc;
    }

    public String getCreditingType ()
    {
        return creditingType;
    }

    public void setCreditingType (String creditingType)
    {
        this.creditingType = creditingType;
    }

    public String getBankCode ()
    {
        return bankCode;
    }

    public void setBankCode (String bankCode)
    {
        this.bankCode = bankCode;
    }

    public String getRecipientType ()
    {
        return recipientType;
    }

    public void setRecipientType (String recipientType)
    {
        this.recipientType = recipientType;
    }

    public String getIdType ()
    {
        return idType;
    }

    public void setIdType (String idType)
    {
        this.idType = idType;
    }

    public String getBankName ()
    {
        return bankName;
    }

    public void setBankName (String bankName)
    {
        this.bankName = bankName;
    }

    public String getTransferMethod ()
    {
        return transferMethod;
    }

    public void setTransferMethod (String transferMethod)
    {
        this.transferMethod = transferMethod;
    }

    public String getToAccLabel ()
    {
        return toAccLabel;
    }

    public void setToAccLabel (String toAccLabel)
    {
        this.toAccLabel = toAccLabel;
    }

    public String getToAccHolderName ()
    {
        return toAccHolderName;
    }

    public void setToAccHolderName (String toAccHolderName)
    {
        this.toAccHolderName = toAccHolderName;
    }

    public String getCurrencyCode ()
    {
        return currencyCode;
    }

    public void setCurrencyCode (String currencyCode)
    {
        this.currencyCode = currencyCode;
    }

    public String getIdNo ()
    {
        return idNo;
    }

    public void setIdNo (String idNo)
    {
        this.idNo = idNo;
    }

    public String getPaymentType ()
    {
        return paymentType;
    }

    public void setPaymentType (String paymentType)
    {
        this.paymentType = paymentType;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [toAcc = "+toAcc+", creditingType = "+creditingType+", bankCode = "+bankCode+", recipientType = "+recipientType+", idType = "+idType+", bankName = "+bankName+", transferMethod = "+transferMethod+", toAccLabel = "+toAccLabel+", toAccHolderName = "+toAccHolderName+", currencyCode = "+currencyCode+", idNo = "+idNo+", paymentType = "+paymentType+"]";
    }
}


