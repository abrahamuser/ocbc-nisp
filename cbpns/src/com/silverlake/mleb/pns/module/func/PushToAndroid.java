package com.silverlake.mleb.pns.module.func;

import static com.google.android.gcm.server.Constants.FCM;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
//import org.apache.log4j.Logger;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.ProxySender;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Message.Builder;
import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.mleb.pex.util.PropertiesManager;
import com.silverlake.mleb.pns.bean.ObPNBDeviceRequestBean;
import com.silverlake.mleb.pns.bean.ObPNBRequestBean;
import com.silverlake.mleb.pns.bean.ObPNBResponseBean;
import com.silverlake.mleb.pns.bean.ObPNSRequestBean;
import com.silverlake.mleb.pns.bean.ObPNSResponseBean;
import com.silverlake.mleb.pns.constant.PNSConstant;
import com.silverlake.mleb.pns.entity.PnsApps;
import com.silverlake.mleb.pns.entity.ProviderProxyIp;
import com.silverlake.mleb.pns.entity.dao.PnsDao;


@Service
public class PushToAndroid
{
	//private static Logger log = Logger.getLogger(PushToAndroid.class);
	private static Logger log = LogManager.getLogger(PushToAndroid.class);
	
	private PropertiesManager pmgr = new PropertiesManager();
	
	@Autowired
	PnsDao dao;
	
	@SuppressWarnings("unchecked")
	public ObPNSResponseBean push(PnsApps clientdata, MICBRequestBean requestBean, String sendThroughProxy, List<ProviderProxyIp> providerProxyIpList, String proxyIp, String proxyPort)

	{
		ObPNSResponseBean pnsResponseBean = new ObPNSResponseBean();
		
		try
		{
			
			//String androidApplicationId = pmgr.getProperty("android.app.id");
			String androidApplicationId = clientdata.getAndroidApiKey();
			String androidIconId = pmgr.getProperty("android.icon.id");
			String androidHeaderName = pmgr.getProperty("android.header.name");
			String androidContentName = pmgr.getProperty("android.content.name");
			
			/*androidHeaderName = "header";
			androidContentName = "message";
			androidApplicationId = "AIzaSyCDikcFmZ8u8ngm6OBvlwKI8arMxJba2QY";
			androidIconId  = "1";*/
			
			ObPNSRequestBean pnsRequest = (ObPNSRequestBean)requestBean.getBDObject();
			
			List<String> androidTargets = new ArrayList<String>();
			String[] tokenDeviceList = pnsRequest.getDeviceToken().split(",");
			if(tokenDeviceList.length>1)
			{
				for(String tokenTemp : tokenDeviceList)
				{
					androidTargets.add(tokenTemp);
				}
				log.info("GCN Multi Token Added");
			}
			else
			{
				androidTargets.add(pnsRequest.getDeviceToken());
				log.info("GCN Single Token ADDED");
			}
			
			
			
			ProxySender sender = new ProxySender(androidApplicationId, sendThroughProxy);
			
			MulticastResult result = null;
			
			if(pmgr.getProperty("android.cloud.messaging")!=null && 
			   pmgr.getProperty("android.cloud.messaging").equalsIgnoreCase(FCM)) {
				
				JSONObject json = new JSONObject();
				
				//Push03 22-Nov-2017
				JSONObject infoNotification = new JSONObject();
				infoNotification.put("title", pnsRequest.getHeader()); 
				infoNotification.put("body", pnsRequest.getContent()); 
				if(pnsRequest.getMessageType()!=null && !pnsRequest.getMessageType().trim().isEmpty()){
					infoNotification.put("click_action", pnsRequest.getMessageType()); //P4OCBCSIT-22
				}else{
					infoNotification.put("click_action", "ONEMOBILEPUSH"); //P3OCBCUAT-420
				}
				infoNotification.put("color", "#00ACD4"); 
				infoNotification.put("priority", "high"); 
				infoNotification.put("show_in_foreground", true); 
//				json.put("notification", infoNotification);
				
//				//Push04 22-Nov-2017
//				JSONObject infoNotification = new JSONObject();
//				infoNotification.put("title", pnsRequest.getHeader()); 
//				infoNotification.put("body", pnsRequest.getContent()); 
//				infoNotification.put("color", "#00ACD4"); 
//				infoNotification.put("priority", "high"); 
//				infoNotification.put("show_in_foreground", true); 
//				json.put("custom_notification", infoNotification);
				
				JSONObject infoData = new JSONObject();
				infoData.put("template", pnsRequest.getTemplate()); 
				infoData.put("crd", "1"); 
				infoData.put("refNo", pnsRequest.getRequestId()); 
				infoData.put("message", pnsRequest.getContent());
				//P4OCBCSIT-22
				if(pnsRequest.getMessageType()!=null && !pnsRequest.getMessageType().trim().isEmpty()){
					infoData.put("module", pnsRequest.getMessageType()); 
				}
				json.put("data", infoData);
				
				if(providerProxyIpList != null && providerProxyIpList.size() > 0)
				{
					boolean doProcess = true;
					for(int i=0; i<providerProxyIpList.size(); i++)
					{
						ProviderProxyIp providerProxyIp = providerProxyIpList.get(i);
						log.info("Push GCN WITH Proxy "+i+" :: "+doProcess+" :: "+providerProxyIp.getProxyIpName()+" :: "+providerProxyIp.getProviderRealIp());
						if(doProcess)
						{
							try
							{
								result = sender.sendJson(json, androidTargets, 0, providerProxyIp.getProxyIpName(), proxyIp, proxyPort);
								
								if(result.getResults() != null)
								{
									doProcess = false;//Successfully Pushed Notification. Stop Sending through other IP's
								}
							}
							catch(IOException e)
							{
								log.info("Exception here 2 :: "+e);
								pnsResponseBean.setStatusCode(PNSConstant.PNS_CANT_PUSH_TO_GCM_CODE);
								pnsResponseBean.setStatusMessage(PNSConstant.PNS_CANT_PUSH_TO_GCM_MESG);
								pnsResponseBean.setErrorCode(PNSConstant.PNS_CANT_PUSH_TO_GCM_CODE);
								e.printStackTrace();
							}
							
							
						}
					}
					
				}
				else
				{
					try
					{
						log.info("Push GCN Without Proxy");
						result = sender.sendJson(json, androidTargets, 0, null, proxyIp, proxyPort);
					}
					catch(IOException e)
					{
						log.info("Exception here 3 :: "+e);
						pnsResponseBean.setStatusCode(PNSConstant.PNS_CANT_PUSH_TO_GCM_CODE);
						pnsResponseBean.setStatusMessage(PNSConstant.PNS_CANT_PUSH_TO_GCM_MESG);
						pnsResponseBean.setErrorCode(PNSConstant.PNS_CANT_PUSH_TO_GCM_CODE);
						e.printStackTrace();
					}
					
					
				}
			}
			else
			{
				Builder builder = new Message.Builder();
				builder.addData("crd", "1");
				builder.addData(androidHeaderName, pnsRequest.getHeader());
				builder.addData(androidContentName, pnsRequest.getContent());
				builder.addData("refNo", pnsRequest.getRequestId());
				builder.collapseKey("11341234243");
				builder.addData("template", pnsRequest.getTemplate());
				builder.addData("type", pnsRequest.getType());
				
				builder.timeToLive(259200);//3Days
				builder.delayWhileIdle(true);
				Message message = builder.build();
				
				if(providerProxyIpList != null && providerProxyIpList.size() > 0)
				{
					boolean doProcess = true;
					for(int i=0; i<providerProxyIpList.size(); i++)
					{
						ProviderProxyIp providerProxyIp = providerProxyIpList.get(i);
						log.info("Push GCN WITH Proxy "+i+" :: "+doProcess+" :: "+providerProxyIp.getProxyIpName()+" :: "+providerProxyIp.getProviderRealIp());
						if(doProcess)
						{
							try
							{
								result = sender.send(message, androidTargets, 0, providerProxyIp.getProxyIpName(), proxyIp, proxyPort);
								
								if(result.getResults() != null)
								{
									doProcess = false;//Successfully Pushed Notification. Stop Sending through other IP's
								}
							}
							catch(IOException e)
							{
								log.info("Exception here 2 :: "+e);
								pnsResponseBean.setStatusCode(PNSConstant.PNS_CANT_PUSH_TO_GCM_CODE);
								pnsResponseBean.setStatusMessage(PNSConstant.PNS_CANT_PUSH_TO_GCM_MESG);
								pnsResponseBean.setErrorCode(PNSConstant.PNS_CANT_PUSH_TO_GCM_CODE);
								e.printStackTrace();
							}
							
							
						}
					}
					
				}
				else
				{
					try
					{
						log.info("Push GCN Without Proxy");
						result = sender.send(message, androidTargets, 0, null, proxyIp, proxyPort);
					}
					catch(IOException e)
					{
						log.info("Exception here 3 :: "+e);
						pnsResponseBean.setStatusCode(PNSConstant.PNS_CANT_PUSH_TO_GCM_CODE);
						pnsResponseBean.setStatusMessage(PNSConstant.PNS_CANT_PUSH_TO_GCM_MESG);
						pnsResponseBean.setErrorCode(PNSConstant.PNS_CANT_PUSH_TO_GCM_CODE);
						e.printStackTrace();
					}
					
					
				}
			}
			
			log.info("RESULT :: "+result);
			if (result != null && result.getResults() != null)
			{
				pnsResponseBean.setMulticastId(""+result.getMulticastId());
				
				if (result.getFailure() !=0)
				{
					
					if(result.getResults().get(0).getErrorCodeName() != null && !result.getResults().get(0).getErrorCodeName().equals(""))
					{
						pnsResponseBean.setStatusCode(PNSConstant.PNS_GCM_FAIL_CODE);
						pnsResponseBean.setStatusMessage(result.getResults().get(0).getErrorCodeName());
						pnsResponseBean.setErrorCode(result.getResults().get(0).getErrorCodeName());
					}
					else
					{
						pnsResponseBean.setStatusCode(PNSConstant.PNS_GCM_FAIL_CODE);
						pnsResponseBean.setStatusMessage(PNSConstant.PNS_GCM_FAIL_MESG);
						pnsResponseBean.setErrorCode(PNSConstant.PNS_GCM_FAIL_CODE);
					}					
				}
				else
				{
					pnsResponseBean.setStatusCode(PNSConstant.PNS_SUCCESS_CODE);
					pnsResponseBean.setStatusMessage(PNSConstant.PNS_SUCCESS_MESG);
					pnsResponseBean.setStoreMessageId(result.getResults().get(0).getMessageId());
				}
			}
			else if(result != null)
			{
				int error = result.getFailure();
				log.info("GCM failure: " + error);
				pnsResponseBean.setStatusCode(PNSConstant.PNS_GCM_FAIL_CODE);
				pnsResponseBean.setStatusMessage(PNSConstant.PNS_GCM_FAIL_MESG);
				pnsResponseBean.setErrorCode(PNSConstant.PNS_GCM_FAIL_CODE);
				
			}
		}
		catch(Exception e)
		{
			log.info("Exception here 1 :: "+e);
			pnsResponseBean.setStatusCode(PNSConstant.PNS_COMMON_ERROR_CODE);
			pnsResponseBean.setStatusMessage(PNSConstant.PNS_COMMON_ERROR_MESG);
			pnsResponseBean.setErrorCode(PNSConstant.PNS_COMMON_ERROR_CODE);
			e.printStackTrace();
		}
		
		return pnsResponseBean;
	}
	
	public ObPNBResponseBean boradcast(ObPNBRequestBean pnbRequestBean, String sendThroughProxy, List<ProviderProxyIp> providerProxyIpList, String proxyIp, String proxyPort)
	{
		ObPNBResponseBean pnbResponseBean = new ObPNBResponseBean();
		
		try
		{
			String androidApplicationId = pmgr.getProperty("android.app.id");
			String androidIconId = pmgr.getProperty("android.icon.id");
			String androidHeaderName = pmgr.getProperty("android.header.name");
			String androidContentName = pmgr.getProperty("android.content.name");
			
			List<String> androidTargets = new ArrayList<String>();
			
			List<ObPNBDeviceRequestBean> pndDeviceRequestList = pnbRequestBean.getDeviceBeanList();
			pnbResponseBean.setDeviceBeanList(pndDeviceRequestList);
			
			for(ObPNBDeviceRequestBean pnnDevice:  pndDeviceRequestList)
			{
				androidTargets.add(pnnDevice.getDeviceToken());
			}
			
			log.info("1 :: Device Request Size :: "+pndDeviceRequestList.size()+", Android Target Size :: "+androidTargets.size());
			
			ProxySender sender = new ProxySender(androidApplicationId, sendThroughProxy);
			Builder builder = new Message.Builder();
			builder.addData("crd", androidIconId);
			builder.addData(androidHeaderName, pnbRequestBean.getHeader());
			builder.addData(androidContentName, pnbRequestBean.getContent());
			builder.addData("refNo", pnbRequestBean.getRequestId());
			builder.collapseKey("11341234243");
			builder.addData("template", pnbRequestBean.getTemplate());
			builder.timeToLive(259200);//3Days
			builder.delayWhileIdle(true);
			builder.restrictedPackageName("com.bank.cambodia.hlbconnect"); //new requirement from FE
			Message message = builder.build();
			MulticastResult multiCastResult = null;
			
			if(providerProxyIpList != null && providerProxyIpList.size() > 0)
			{
				boolean doProcess = true;
				for(int i=0; i<providerProxyIpList.size(); i++)
				{
					ProviderProxyIp providerProxyIp = providerProxyIpList.get(i);
					log.info(i+" :: "+doProcess+" :: "+providerProxyIp.getProxyIpName()+" :: "+providerProxyIp.getProviderRealIp());
					if(doProcess)
					{
						try
						{
							multiCastResult = sender.send(message, androidTargets, 0, providerProxyIp.getProxyIpName(), proxyIp, proxyPort);
							
							if(multiCastResult.getResults() != null)
							{
								doProcess = false;//Successfully Pushed Notification. Stop Sending through other IP's
							}
						}
						catch(IOException e)
						{
							log.info("Exception here 2 :: "+e);
							
							pnbResponseBean.setStatusCode(PNSConstant.PNS_CANT_PUSH_TO_GCM_CODE);
							pnbResponseBean.setStatusMessage(PNSConstant.PNS_CANT_PUSH_TO_GCM_MESG);
							pnbResponseBean.setDeviceBeanList(pndDeviceRequestList);
							
							e.printStackTrace();
						}
						
						
					}
				}
				
			}
			else
			{
				try
				{
					multiCastResult = sender.send(message, androidTargets, 0, null, proxyIp, proxyPort);
				}
				catch(IOException e)
				{
					log.info("Exception here 3 :: "+e);
					
					pnbResponseBean.setStatusCode(PNSConstant.PNS_CANT_PUSH_TO_GCM_CODE);
					pnbResponseBean.setStatusMessage(PNSConstant.PNS_CANT_PUSH_TO_GCM_MESG);
					pnbResponseBean.setDeviceBeanList(pndDeviceRequestList);

					e.printStackTrace();
				}
				
				
			}
			
			
			//log.info("RESULT :: "+multiCastResult);
			if (multiCastResult != null && multiCastResult.getResults() != null)
			{
				log.info("1 :: Device Request Size :: "+pndDeviceRequestList.size()+", Android Target Size :: "+androidTargets.size()+", Response Size :: "+multiCastResult.getResults().size());
				
				pnbResponseBean.setStatusCode(PNSConstant.PNS_SUCCESS_CODE);
				pnbResponseBean.setStatusMessage(PNSConstant.PNS_SUCCESS_MESG);
				pnbResponseBean.setDeviceBeanList(pndDeviceRequestList);
				
				for(int i=0; i<multiCastResult.getResults().size(); i++)
				{
					Result result = multiCastResult.getResults().get(i);
					pnbResponseBean.getDeviceBeanList().get(i).setMulticastId(""+multiCastResult.getMulticastId());
					
					if(result.getMessageId() != null)
					{
						pndDeviceRequestList.get(i).setErrorCode(PNSConstant.PNS_SUCCESS_CODE);
						pndDeviceRequestList.get(i).setStoreMessageId(result.getMessageId());
					}
					else
					{
						pndDeviceRequestList.get(i).setErrorCode(PNSConstant.PNS_INVALID_DEVICE_TOKEN_MESG);
						pndDeviceRequestList.get(i).setErrorMsg(result.getErrorCodeName());
					}
				}
				pnbResponseBean.setDeviceBeanList(pndDeviceRequestList);
			}
			else
			{
				pnbResponseBean.setStatusCode(PNSConstant.PNS_GCM_FAIL_CODE);
				pnbResponseBean.setStatusMessage(PNSConstant.PNS_GCM_FAIL_MESG);
				pnbResponseBean.setDeviceBeanList(pndDeviceRequestList);
				
			}
			
		}
		catch(Exception e)
		{
			log.info("Exception here 1 :: "+e);
			pnbResponseBean.setStatusCode(PNSConstant.PNS_COMMON_ERROR_CODE);
			pnbResponseBean.setStatusMessage(PNSConstant.PNS_COMMON_ERROR_MESG);
			e.printStackTrace();
		}
		
		
		return pnbResponseBean;
	}
}
