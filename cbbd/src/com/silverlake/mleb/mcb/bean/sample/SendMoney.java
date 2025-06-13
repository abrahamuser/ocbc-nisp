package com.silverlake.mleb.mcb.bean.sample;

import java.io.Serializable;

public class SendMoney implements Serializable {
	 private Schedule schedule;

	    private Amount amount;

	    private String addFavouriteNickname;

	    private CreditingAccount creditingAccount;

	    private String addFavourite;

	    private String otherPaymentDetails;

	    private String recipientReference;

	    private String paymentPurpose;

	    private BeneficiaryAlert beneficiaryAlert;

	    private DebitingAccount debitingAccount;

	    private LocalAmount localAmount;

	    public Schedule getSchedule ()
	    {
	        return schedule;
	    }

	    public void setSchedule (Schedule schedule)
	    {
	        this.schedule = schedule;
	    }

	    public Amount getAmount ()
	    {
	        return amount;
	    }

	    public void setAmount (Amount amount)
	    {
	        this.amount = amount;
	    }

	    public String getAddFavouriteNickname ()
	    {
	        return addFavouriteNickname;
	    }

	    public void setAddFavouriteNickname (String addFavouriteNickname)
	    {
	        this.addFavouriteNickname = addFavouriteNickname;
	    }

	    public CreditingAccount getCreditingAccount ()
	    {
	        return creditingAccount;
	    }

	    public void setCreditingAccount (CreditingAccount creditingAccount)
	    {
	        this.creditingAccount = creditingAccount;
	    }

	    public String getAddFavourite ()
	    {
	        return addFavourite;
	    }

	    public void setAddFavourite (String addFavourite)
	    {
	        this.addFavourite = addFavourite;
	    }

	    public String getOtherPaymentDetails ()
	    {
	        return otherPaymentDetails;
	    }

	    public void setOtherPaymentDetails (String otherPaymentDetails)
	    {
	        this.otherPaymentDetails = otherPaymentDetails;
	    }

	    public String getRecipientReference ()
	    {
	        return recipientReference;
	    }

	    public void setRecipientReference (String recipientReference)
	    {
	        this.recipientReference = recipientReference;
	    }

	    public String getPaymentPurpose ()
	    {
	        return paymentPurpose;
	    }

	    public void setPaymentPurpose (String paymentPurpose)
	    {
	        this.paymentPurpose = paymentPurpose;
	    }

	    public BeneficiaryAlert getBeneficiaryAlert ()
	    {
	        return beneficiaryAlert;
	    }

	    public void setBeneficiaryAlert (BeneficiaryAlert beneficiaryAlert)
	    {
	        this.beneficiaryAlert = beneficiaryAlert;
	    }

	    public DebitingAccount getDebitingAccount ()
	    {
	        return debitingAccount;
	    }

	    public void setDebitingAccount (DebitingAccount debitingAccount)
	    {
	        this.debitingAccount = debitingAccount;
	    }

	    public LocalAmount getLocalAmount ()
	    {
	        return localAmount;
	    }

	    public void setLocalAmount (LocalAmount localAmount)
	    {
	        this.localAmount = localAmount;
	    }

	    @Override
	    public String toString()
	    {
	        return "ClassPojo [schedule = "+schedule+", amount = "+amount+", addFavouriteNickname = "+addFavouriteNickname+", creditingAccount = "+creditingAccount+", addFavourite = "+addFavourite+", otherPaymentDetails = "+otherPaymentDetails+", recipientReference = "+recipientReference+", paymentPurpose = "+paymentPurpose+", beneficiaryAlert = "+beneficiaryAlert+", debitingAccount = "+debitingAccount+", localAmount = "+localAmount+"]";
	    }
}
