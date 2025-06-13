package com.silverlake.mleb.mcb.module.vc.onfx;

import java.math.BigDecimal;

import com.silverlake.mleb.mcb.module.vc.common.VCRequest;

public class RequestData extends VCRequest{

	//onfx/rate
    private String ccy_cd;
    private BigDecimal fx_rate;
    
    //v2/onfx/rate
    private String ccy_cd1;//v2
    private String ccy_cd2;//v2

    //v2/onfx/post
    private String debit_acct_no;
    private String debit_acct_ccy;
    private BigDecimal amount;
    private String amount_ccy;
    private String bene_acct_no;
    private String bene_acct_ccy;
    private String bene_acct_name;
    private String remarks;
    private String purpose;
    private String device_id;
    private ListOnfxRate onfx_rate;
    private String prepost_check;

    public String getDebit_acct_no() {
    return debit_acct_no;
    }

    public void setDebit_acct_no(String debit_acct_no) {
    this.debit_acct_no = debit_acct_no;
    }

    public String getDebit_acct_ccy() {
    return debit_acct_ccy;
    }

    public void setDebit_acct_ccy(String debit_acct_ccy) {
    this.debit_acct_ccy = debit_acct_ccy;
    }

    public BigDecimal getAmount() {
    return amount;
    }

    public void setAmount(BigDecimal amount) {
    this.amount = amount;
    }

    public String getAmount_ccy() {
    return amount_ccy;
    }

    public void setAmount_ccy(String amount_ccy) {
    this.amount_ccy = amount_ccy;
    }

    public String getBene_acct_no() {
    return bene_acct_no;
    }

    public void setBene_acct_no(String bene_acct_no) {
    this.bene_acct_no = bene_acct_no;
    }

    public String getBene_acct_ccy() {
    return bene_acct_ccy;
    }

    public void setBene_acct_ccy(String bene_acct_ccy) {
    this.bene_acct_ccy = bene_acct_ccy;
    }

    public String getBene_acct_name() {
    return bene_acct_name;
    }

    public void setBene_acct_name(String bene_acct_name) {
    this.bene_acct_name = bene_acct_name;
    }

    public String getRemarks() {
    return remarks;
    }

    public void setRemarks(String remarks) {
    this.remarks = remarks;
    }

    public String getPurpose() {
    return purpose;
    }

    public void setPurpose(String purpose) {
    this.purpose = purpose;
    }

    public String getDevice_id() {
    return device_id;
    }

    public void setDevice_id(String device_id) {
    this.device_id = device_id;
    }
    

	public String getCcy_cd() {
		return ccy_cd;
	}

	public void setCcy_cd(String ccy_cd) {
		this.ccy_cd = ccy_cd;
	}

	public String getCcy_cd1() {
		return ccy_cd1;
	}

	public String getCcy_cd2() {
		return ccy_cd2;
	}

	public ListOnfxRate getOnfx_rate() {
		return onfx_rate;
	}

	public void setCcy_cd1(String ccy_cd1) {
		this.ccy_cd1 = ccy_cd1;
	}

	public void setCcy_cd2(String ccy_cd2) {
		this.ccy_cd2 = ccy_cd2;
	}

	public void setOnfx_rate(ListOnfxRate onfx_rate) {
		this.onfx_rate = onfx_rate;
	}

	public BigDecimal getFx_rate() {
		return fx_rate;
	}

	public void setFx_rate(BigDecimal fx_rate) {
		this.fx_rate = fx_rate;
	}

	public String getPrepost_check() {
		return prepost_check;
	}

	public void setPrepost_check(String prepost_check) {
		this.prepost_check = prepost_check;
	}
    

}
