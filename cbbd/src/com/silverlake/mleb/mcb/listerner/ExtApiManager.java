package com.silverlake.mleb.mcb.listerner;


import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Formatter;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.Logger;import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.mcb.bean.ObAuthenticationResponse;
import com.silverlake.mleb.mcb.bean.ObHeaderResponse;
import com.silverlake.mleb.mcb.bean.ObRequest;
import com.silverlake.mleb.mcb.bean.ObResponse;
import com.silverlake.mleb.mcb.bean.ObUserContext;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.entity.dao.DeviceCIFDao;
import com.silverlake.mleb.mcb.entity.dao.MLEBMIBDAO;
import com.silverlake.mleb.mcb.module.common.MiBServices;

import com.silverlake.mleb.mcb.entity.ExtChannelProfile;

@Service("ExtApiManager")
public class ExtApiManager {
	
	
	private static Logger log = LogManager.getLogger(ExtApiManager.class);
	
	@Autowired
	DeviceCIFDao dao;
	
	public Object onCall(MICBRequestBean arg0) throws Exception {
		// TODO Auto-generated method stub
		
		Object objBean = arg0;
		
		if(null!=objBean && objBean instanceof MICBRequestBean)
		{
			
			MICBRequestBean micbRequestBean = (MICBRequestBean) objBean;
			ObAuthenticationResponse obResponse = new ObAuthenticationResponse();
			obResponse.setObHeader(new ObHeaderResponse());
			
			
			
			
			
			if( 
				 "xxxxx".equalsIgnoreCase(micbRequestBean.getHeaderBean().getServiceID())
					)
			{
				ObRequest obRequest = (ObRequest) micbRequestBean.getBDObject();
				obRequest.setUserContext(new ObUserContext());
				micbRequestBean.setBDObject(obRequest);
				String channelId = obRequest.getObExtHeader().getId();
				String password =  obRequest.getObExtHeader().getPassword();
				String version =  obRequest.getObExtHeader().getVersion();
				channelId = channelId==null?"":channelId;
				password = password==null?"":DigestUtils.sha256Hex(password).toUpperCase();
				ExtChannelProfile profile = dao.validateChannelProfile(channelId, password);
				if(null==profile)
				{
					
					obResponse.getObHeader().setStatusCode(MiBConstant.MIB_DEVAPI_UNATHORIZED);	

					MICBResponseBean responseBean = new MICBResponseBean();
					responseBean.setBDObject(obResponse);
					return responseBean;
				}
				
			}
			
		}

		
		return objBean;
	}	
	
	
	public String calculateHMAC256(String data, String key)
			throws SignatureException, NoSuchAlgorithmException, InvalidKeyException
		{
			SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), "HmacSHA256");
			Mac mac = Mac.getInstance("HmacSHA256");
			mac.init(signingKey);
			return toHexString(mac.doFinal(data.getBytes()));
		}
	
	private  String toHexString(byte[] bytes) {
		Formatter formatter = new Formatter();
		
		for (byte b : bytes) {
			formatter.format("%02x", b);
		}
 
		return formatter.toString();
	}


}
