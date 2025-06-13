package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

import javax.activation.DataHandler;

public class ObAccountOnBoardCacheImageByteBean implements Serializable{
	//credit card
	
	//Normal Additioanl Info
	private static final long serialVersionUID = 1L;
	// cache image after convert data handler
		private byte[] ktpImage;
		private byte[] sktpImage;
		private byte[] npwpImage;
		private byte[] signImage;
		private byte[] pslip01Image;
		private byte[] pslip02Image;
		private byte[] pslip03Image;
		private byte[] sptImage;
		private byte[] bstmtImage;
		public byte[] getKtpImage() {
			return ktpImage;
		}
		public void setKtpImage(byte[] ktpImage) {
			this.ktpImage = ktpImage;
		}
		public byte[] getSktpImage() {
			return sktpImage;
		}
		public void setSktpImage(byte[] sktpImage) {
			this.sktpImage = sktpImage;
		}
		public byte[] getNpwpImage() {
			return npwpImage;
		}
		public void setNpwpImage(byte[] npwpImage) {
			this.npwpImage = npwpImage;
		}
		public byte[] getSignImage() {
			return signImage;
		}
		public void setSignImage(byte[] signImage) {
			this.signImage = signImage;
		}
		public byte[] getPslip01Image() {
			return pslip01Image;
		}
		public void setPslip01Image(byte[] pslip01Image) {
			this.pslip01Image = pslip01Image;
		}
		public byte[] getPslip02Image() {
			return pslip02Image;
		}
		public void setPslip02Image(byte[] pslip02Image) {
			this.pslip02Image = pslip02Image;
		}
		public byte[] getPslip03Image() {
			return pslip03Image;
		}
		public void setPslip03Image(byte[] pslip03Image) {
			this.pslip03Image = pslip03Image;
		}
		public byte[] getSptImage() {
			return sptImage;
		}
		public void setSptImage(byte[] sptImage) {
			this.sptImage = sptImage;
		}
		public byte[] getBstmtImage() {
			return bstmtImage;
		}
		public void setBstmtImage(byte[] bstmtImage) {
			this.bstmtImage = bstmtImage;
		}
		
		
}
