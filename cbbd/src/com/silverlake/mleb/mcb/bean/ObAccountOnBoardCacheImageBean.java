package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

import javax.activation.DataHandler;

public class ObAccountOnBoardCacheImageBean implements Serializable{
	//credit card
	
	//Normal Additioanl Info
	private static final long serialVersionUID = 1L;
	private DataHandler ktpImage;
	private DataHandler sktpImage;
	private DataHandler npwpImage;
	private DataHandler signImage;
	private DataHandler pslip01Image;
	private DataHandler pslip02Image;
	private DataHandler pslip03Image;
	private DataHandler sptImage;
	private DataHandler bstmtImage;
	public DataHandler getKtpImage() {
		return ktpImage;
	}
	public void setKtpImage(DataHandler ktpImage) {
		this.ktpImage = ktpImage;
	}
	public DataHandler getSktpImage() {
		return sktpImage;
	}
	public void setSktpImage(DataHandler sktpImage) {
		this.sktpImage = sktpImage;
	}
	public DataHandler getNpwpImage() {
		return npwpImage;
	}
	public void setNpwpImage(DataHandler npwpImage) {
		this.npwpImage = npwpImage;
	}
	public DataHandler getSignImage() {
		return signImage;
	}
	public void setSignImage(DataHandler signImage) {
		this.signImage = signImage;
	}
	public DataHandler getPslip01Image() {
		return pslip01Image;
	}
	public void setPslip01Image(DataHandler pslip01Image) {
		this.pslip01Image = pslip01Image;
	}
	public DataHandler getPslip02Image() {
		return pslip02Image;
	}
	public void setPslip02Image(DataHandler pslip02Image) {
		this.pslip02Image = pslip02Image;
	}
	public DataHandler getPslip03Image() {
		return pslip03Image;
	}
	public void setPslip03Image(DataHandler pslip03Image) {
		this.pslip03Image = pslip03Image;
	}
	public DataHandler getSptImage() {
		return sptImage;
	}
	public void setSptImage(DataHandler sptImage) {
		this.sptImage = sptImage;
	}
	public DataHandler getBstmtImage() {
		return bstmtImage;
	}
	public void setBstmtImage(DataHandler bstmtImage) {
		this.bstmtImage = bstmtImage;
	}
	
}
