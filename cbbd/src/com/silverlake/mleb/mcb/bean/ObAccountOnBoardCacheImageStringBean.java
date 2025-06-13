package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

import javax.activation.DataHandler;

public class ObAccountOnBoardCacheImageStringBean implements Serializable{
	//credit card
	
	//Normal Additioanl Info
	private static final long serialVersionUID = 1L;
	// cache image after convert data handler
		private String ktpImage;
		private String sktpImage;
		private String npwpImage;
		private String signImage;
		private String sptImage;
		private List<String> pslipImage;
		private List<String> bstmtImage;
		public String getKtpImage() {
			return ktpImage;
		}
		public void setKtpImage(String ktpImage) {
			this.ktpImage = ktpImage;
		}
		public String getSktpImage() {
			return sktpImage;
		}
		public void setSktpImage(String sktpImage) {
			this.sktpImage = sktpImage;
		}
		public String getNpwpImage() {
			return npwpImage;
		}
		public void setNpwpImage(String npwpImage) {
			this.npwpImage = npwpImage;
		}
		public String getSignImage() {
			return signImage;
		}
		public void setSignImage(String signImage) {
			this.signImage = signImage;
		}
		public String getSptImage() {
			return sptImage;
		}
		public void setSptImage(String sptImage) {
			this.sptImage = sptImage;
		}
		public List<String> getPslipImage() {
			return pslipImage;
		}
		public void setPslipImage(List<String> pslipImage) {
			this.pslipImage = pslipImage;
		}
		public List<String> getBstmtImage() {
			return bstmtImage;
		}
		public void setBstmtImage(List<String> bstmtImage) {
			this.bstmtImage = bstmtImage;
		}
		
}
