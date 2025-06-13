package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObRedirectURLsBean implements Serializable
{
	private Integer urlId;
	
	private String status;
	
	private String faqURL;
	
	private String insuranceURL;
	
	private String promotionURL;
	
	private String type;

	public Integer getUrlId() {
		return urlId;
	}

	public void setUrlId(Integer urlId) {
		this.urlId = urlId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFaqURL() {
		return faqURL;
	}

	public void setFaqURL(String faqURL) {
		this.faqURL = faqURL;
	}

	public String getInsuranceURL() {
		return insuranceURL;
	}

	public void setInsuranceURL(String insuranceURL) {
		this.insuranceURL = insuranceURL;
	}

	public String getPromotionURL() {
		return promotionURL;
	}

	public void setPromotionURL(String promotionURL) {
		this.promotionURL = promotionURL;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

//	public String getFaq_url() {
//		return faq_url;
//	}
//
//	public void setFaq_url(String faq_url) {
//		this.faq_url = faq_url;
//	}
//
//	public String getInsurance_url() {
//		return insurance_url;
//	}
//
//	public void setInsurance_url(String insurance_url) {
//		this.insurance_url = insurance_url;
//	}
//
//	public String getPromotion_url() {
//		return promotion_url;
//	}
//
//	public void setPromotion_url(String promotion_url) {
//		this.promotion_url = promotion_url;
//	}
	
}
