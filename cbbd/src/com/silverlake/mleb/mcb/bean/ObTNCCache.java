package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

import com.silverlake.mleb.mcb.module.vc.others.TNCResponseData;
import com.silverlake.mleb.mcb.module.vc.others.TNCv2ResponseData;

public class ObTNCCache extends ObSessionCache implements Serializable  {
	private TNCResponseData omniResponse;
	private ObTNCRequest requestData;
	private TNCv2ResponseData omniResponseTnCv2;
	
	public ObTNCRequest getRequestData() {
		return requestData;
	}
	public void setRequestData(ObTNCRequest requestData) {
		this.requestData = requestData;
	}
	public TNCResponseData getOmniResponse() {
		return omniResponse;
	}
	public void setOmniResponse(TNCResponseData omniResponse) {
		this.omniResponse = omniResponse;
	}
	public TNCv2ResponseData getOmniResponseTnCv2() {
		return omniResponseTnCv2;
	}
	public void setOmniResponseTnCv2(TNCv2ResponseData omniResponseTnCv2) {
		this.omniResponseTnCv2 = omniResponseTnCv2;
	}
	
}
