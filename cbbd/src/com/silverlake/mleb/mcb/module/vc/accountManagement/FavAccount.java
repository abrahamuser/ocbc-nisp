package com.silverlake.mleb.mcb.module.vc.accountManagement;

import java.io.Serializable;
import java.util.List;

public class FavAccount implements Serializable{

	 
	 
    private String acct_no;
    private String acct_ccy;
    private String seq;
    private String action_cd;
    
    
	public String getAcct_no() {
		return acct_no;
	}
	public void setAcct_no(String acct_no) {
		this.acct_no = acct_no;
	}
	public String getAcct_ccy() {
		return acct_ccy;
	}
	public void setAcct_ccy(String acct_ccy) {
		this.acct_ccy = acct_ccy;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getAction_cd() {
		return action_cd;
	}
	public void setAction_cd(String action_cd) {
		this.action_cd = action_cd;
	}
    
     
	
	
}

