package com.silverlake.mleb.pns.module.func;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


//import org.apache.log4j.BasicConfigurator;
import org.apache.logging.log4j.LogManager;
//import org.apache.log4j.Logger;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
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

import javapns.Push;
import javapns.communication.exceptions.CommunicationException;
import javapns.communication.exceptions.KeystoreException;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotification;
import javapns.notification.ResponsePacket;

@Service
public class PushToIOS
{
	//private static Logger log = Logger.getLogger(PushToIOS.class);
	private static Logger log = LogManager.getLogger(PushToIOS.class);
	private PropertiesManager pmgr = new PropertiesManager();
	
	
	@Autowired
	PnsDao dao;
	
	public ObPNSResponseBean push(PnsApps clientdata,MICBRequestBean requestBean, String sendThroughProxy, List<ProviderProxyIp> proxyIps, String proxyIp, String proxyPort, String channel)

	{
		ObPNSResponseBean pnsResponseBean = new ObPNSResponseBean();
		
		Gson gson = new Gson();
		
		try
		{
			int iphoneIconId = pmgr.getProperty("iphone.icon.id") == null?0:Integer.parseInt(pmgr.getProperty("iphone.icon.id"));
			int ipadIconId = pmgr.getProperty("ipad.icon.id") == null?0:Integer.parseInt(pmgr.getProperty("ipad.icon.id"));
			//String iphoneKeystorePassword = pmgr.getProperty("iphone.keystore.password");
			//String ipadKeystorePassword = pmgr.getProperty("ipad.keystore.password");
			
			String iphoneKeystorePassword = clientdata.getApplePass();
			String ipadKeystorePassword = clientdata.getAppleIpadPass();
			String appleURL = clientdata.getAppleUrl().isEmpty()?null:clientdata.getAppleUrl();
			
			String p12Path = "";
			
			ObPNSRequestBean pnsRequest = (ObPNSRequestBean)requestBean.getBDObject();
			
			//P4OCBCSIT-22
			String category = pnsRequest.getTemplate();
			if(pnsRequest.getMessageType()!=null && !pnsRequest.getMessageType().trim().isEmpty()){
				category = pnsRequest.getMessageType(); //P4OCBCSIT-22
			}
			
			//BasicConfigurator.configure();//Comment out this line while going Production to avoid detail printing of Log's
			
			String mainJason = "{\"aps\":<replaceAppsData>,<replaceCustomData>}";
			
			String appData = "{\"alert\"=\""+pnsRequest.getContent()+"\",\"sound\"=\"default\",\"category\"=\""+category+"\",\"badge\"=\"0\",\"content-available\"=\"1\"}";
			
			String customData = "\"refNo\"=\""+pnsRequest.getRequestId()+"\",\"type\"=\""+pnsRequest.getType()+"\"";
			
			
			mainJason = mainJason.replaceAll("<replaceAppsData>", appData);
			mainJason = mainJason.replaceAll("<replaceCustomData>", customData);
			

			PushNotificationPayload payload = PushNotificationPayload.fromJSON(mainJason);
			
			//PushNotificationPayload payload = PushNotificationPayload.complex();
			
			/*payload.addAlert( pnsRequest.getContent());
			payload.addSound("default");
			payload.addCategory(pnsRequest.getTemplate());
			payload.addCustomDictionary("refNo", pnsRequest.getRequestId());
			payload.addCustomDictionary("content-available", 1);*/
			
			String keystorePassword = "";
			if(channel!=null && channel.equalsIgnoreCase("T") )
			{
				//payload.addBadge(iphoneIconId);
				keystorePassword = ipadKeystorePassword;
				//p12Path = pmgr.getAppPath()+"/properties/p12/ipad.p12";
				p12Path = clientdata.getAppleIpadCert();
				
			}
			else
			{
				//payload.addBadge(ipadIconId);
				keystorePassword = iphoneKeystorePassword;
				//p12Path = pmgr.getAppPath()+"/properties/p12/iphone.p12";
				p12Path = clientdata.getAppleCert();
			}
			log.info("P12 Path :: "+p12Path);
			log.info("PAYLOAD :: "+payload);
			InputStream inputStream = new FileInputStream(p12Path);// PushToIOS.class.getClassLoader().getResourceAsStream(p12Path);
	
			
		/*	log.info("PROXY IP ::"+System.getProperty("https.proxyHost")+", PROXY PORT :: "+System.getProperty("https.proxyPort"));*/
			
			
			Object deviceTokenObj = pnsRequest.getDeviceToken();
			String[] deviceList = pnsRequest.getDeviceToken().split(",");
			if(deviceList.length>1)
			{
				deviceTokenObj = deviceList;
				log.info("push multiple token : "+deviceTokenObj);
			}
			
			
			List<PushedNotification> notifications = null;
			if(proxyIps != null && proxyIps.size() > 0)
			{
				boolean doProcess = true;
				for(int i=0; i<proxyIps.size(); i++)
				{
					ProviderProxyIp providerProxyIp = proxyIps.get(i);
					log.info(i+" :: "+doProcess+" :: "+providerProxyIp.getProxyIpName()+" :: "+providerProxyIp.getProviderRealIp());
					if(doProcess)
					{
						try
						{
							notifications = Push.payload(payload, inputStream, keystorePassword, true, deviceTokenObj, providerProxyIp.getProxyIpName());
							doProcess = false;//Successfully Pushed Notification. Stop Sending through other IP's
						}
						catch (CommunicationException e)
						{
							e.printStackTrace();
							pnsResponseBean.setStatusCode(PNSConstant.PNS_APN_FAIL_CODE);
							pnsResponseBean.setStatusMessage(e.getMessage());
							pnsResponseBean.setErrorCode(PNSConstant.PNS_APN_FAIL_CODE);
						}
						catch (KeystoreException e)
						{
							log.info("ERROR :: "+e.getMessage());
							e.printStackTrace();
							
							pnsResponseBean.setStatusCode(PNSConstant.PNS_APN_FAIL_CODE);
							pnsResponseBean.setStatusMessage(e.getMessage());
							pnsResponseBean.setErrorCode(PNSConstant.PNS_APN_FAIL_CODE);
						}
						catch(Exception e)
						{
							log.info("Exception :: "+e);
							log.info("Failed to Push Notification through ");
							pnsResponseBean.setStatusCode(PNSConstant.PNS_COMMON_ERROR_CODE);
							pnsResponseBean.setStatusMessage(PNSConstant.PNS_COMMON_ERROR_MESG);
							pnsResponseBean.setErrorCode(PNSConstant.PNS_COMMON_ERROR_CODE);
						}
					}
				}
			}
			else
			{
				log.info("push without proxy : "+deviceTokenObj);
				log.info("Push to Apple URl Host :: " + appleURL);
				//notifications = Push.payload(payload, inputStream, keystorePassword, true, deviceTokenObj, null);//Send ServerIp=null will be defaulted to Apple Production IP
				notifications = Push.payload(payload, inputStream, keystorePassword, false, deviceTokenObj, appleURL);
			}

			if(notifications != null)
			{
				for (PushedNotification notification : notifications)
				{
					if (notification.isSuccessful())
					{
						log.info("PUSH NOTIFICATION SENT SUCCESSFULLY TO: " + 
								notification.getDevice().getToken());
					/*	
					
						
						log.info(gson.toJson(notification));
					
						log.info(notification.getResponse());*/
						
						pnsResponseBean.setStatusCode(PNSConstant.PNS_SUCCESS_CODE);
						pnsResponseBean.setStatusMessage(PNSConstant.PNS_SUCCESS_MESG);
						pnsResponseBean.setErrorCode("");
						
					}
					else
					{
						Exception error = notification.getException();
						pnsResponseBean.setStatusCode(PNSConstant.PNS_APN_FAIL_CODE);
						pnsResponseBean.setStatusMessage(error.getMessage());
						
						String invalidToken = notification.getDevice().getToken();

						log.info("APNS failed for token " + invalidToken + " , Are you sure the token is valid?");

						

						log.info("Error apns ::" + error.getMessage());

						ResponsePacket errorResponse = notification.getResponse();
						if (errorResponse != null)
						{
							pnsResponseBean.setErrorCode(""+errorResponse.getStatus());
							
							/*if (status == 0) return prefix + "No errors encountered";
	                        if (status == 1) return prefix + "Processing error";
	                        if (status == 2) return prefix + "Missing device token";
	                        if (status == 3) return prefix + "Missing topic";
	                        if (status == 4) return prefix + "Missing payload";
	                        if (status == 5) return prefix + "Invalid token size";
	                        if (status == 6) return prefix + "Invalid topic size";
	                        if (status == 7) return prefix + "Invalid payload size";
	                        if (status == 8) return prefix + "Invalid token";
	                        if (status == 255) return prefix + "None (unknown)";*/
							
						}
						else
						{
							pnsResponseBean.setErrorCode(PNSConstant.PNS_APN_FAIL_CODE);
						}
					}
				}
			}
			
			
		}
		/*catch (JSONException e)
		{
			// for payload
			e.printStackTrace();
			pnsResponseBean.setStatusCode(PNSConstant.PNS_APN_FAIL_CODE);
			pnsResponseBean.setStatusMessage(e.getMessage());
			pnsResponseBean.setErrorCode(PNSConstant.PNS_APN_FAIL_CODE);
		}*/
		catch(Exception e)
		{
			e.printStackTrace();
			pnsResponseBean.setStatusCode(PNSConstant.PNS_COMMON_ERROR_CODE);
			pnsResponseBean.setStatusMessage(PNSConstant.PNS_COMMON_ERROR_MESG);
			pnsResponseBean.setErrorCode(PNSConstant.PNS_COMMON_ERROR_CODE);
		}
		
		return pnsResponseBean;
	}
	
	public ObPNBResponseBean boradcast(ObPNBRequestBean pnbRequestBean, String sendThroughProxy, List<ProviderProxyIp> proxyIps, String proxyIp, String proxyPort)
	{
		ObPNBResponseBean pnbResponseBean = new ObPNBResponseBean();
		
		Gson gson = new Gson();
		
		try
		{
			int iphoneIconId = pmgr.getProperty("iphone.icon.id") == null?0:Integer.parseInt(pmgr.getProperty("iphone.icon.id"));
			int ipadIconId = pmgr.getProperty("ipad.icon.id") == null?0:Integer.parseInt(pmgr.getProperty("ipad.icon.id"));
			String iphoneKeystorePassword = pmgr.getProperty("iphone.keystore.password");
			String ipadKeystorePassword = pmgr.getProperty("ipad.keystore.password");
			
			String p12Path = "";
			
			//BasicConfigurator.configure();//Comment out this line while going Production to avoid detail printing of Log's
			
			String mainJason = "{\"aps\":<replaceAppsData>,<replaceCustomData>}";
			String appData = "{\"alert\"=\""+pnbRequestBean.getContent()+"\",\"sound\"=\"default\",\"category\"=\""+pnbRequestBean.getTemplate()+"\",\"badge\"=\"0\",\"content-available\"=\"1\"}";
			String customData = "\"refNo\"=\""+pnbRequestBean.getRequestId()+"\"";
			
			mainJason = mainJason.replaceAll("<replaceAppsData>", appData);
			mainJason = mainJason.replaceAll("<replaceCustomData>", customData);
			
			PushNotificationPayload payload = PushNotificationPayload.fromJSON(mainJason);
			
			String keystorePassword = "";
			if(pnbRequestBean.getDeviceOsType().equalsIgnoreCase("ios"))
			{
				//payload.addBadge(iphoneIconId);
				keystorePassword = iphoneKeystorePassword;
				p12Path = pmgr.getAppPath()+"/properties/p12/iphone.p12";
			}
			else
			{
				//payload.addBadge(ipadIconId);
				keystorePassword = ipadKeystorePassword;
				p12Path = pmgr.getAppPath()+"/properties/p12/ipad.p12";
			}
			log.info("P12 Path :: "+p12Path);
			log.info("PAYLOAD :: "+payload);
			InputStream inputStream = new FileInputStream(p12Path);// PushToIOS.class.getClassLoader().getResourceAsStream(p12Path);
			
			List<String> deviceTokenList = new ArrayList<String>();
			List<ObPNBDeviceRequestBean> pndDeviceRequestList = pnbRequestBean.getDeviceBeanList();
			pnbResponseBean.setDeviceBeanList(pndDeviceRequestList);
			
			for(ObPNBDeviceRequestBean pnnDevice:  pndDeviceRequestList)
			{
				deviceTokenList.add(pnnDevice.getDeviceToken());
			}
			log.info("DeviceT oken List 1 :: "+deviceTokenList.size());
			
			List<PushedNotification> notifications = null;
			if(proxyIps != null && proxyIps.size() > 0)
			{
				boolean doProcess = true;
				for(int i=0; i<proxyIps.size(); i++)
				{
					ProviderProxyIp providerProxyIp = proxyIps.get(i);
					log.info(i+" :: "+doProcess+" :: "+providerProxyIp.getProxyIpName()+" :: "+providerProxyIp.getProviderRealIp());
					if(doProcess)
					{
						try
						{
							notifications = Push.payload(payload, inputStream, keystorePassword, true, deviceTokenList, providerProxyIp.getProxyIpName());
							doProcess = false;//Successfully Pushed Notification. Stop Sending through other IP's
						}
						catch (CommunicationException e)
						{
							e.printStackTrace();
							pnbResponseBean.setStatusCode(PNSConstant.PNS_APN_FAIL_CODE);
							pnbResponseBean.setStatusMessage(e.getMessage());
						}
						catch (KeystoreException e)
						{
							log.info("ERROR :: "+e.getMessage());
							e.printStackTrace();
							
							pnbResponseBean.setStatusCode(PNSConstant.PNS_APN_FAIL_CODE);
							pnbResponseBean.setStatusMessage(e.getMessage());
							
						}
						catch(Exception e)
						{
							log.info("Exception :: "+e);
							log.info("Failed to Push Notification through ");
							pnbResponseBean.setStatusCode(PNSConstant.PNS_COMMON_ERROR_CODE);
							pnbResponseBean.setStatusMessage(PNSConstant.PNS_COMMON_ERROR_MESG);
							
						}
					}
				}
			}
			else
			{
				log.info("There is not Proxy IP Set. Send out direcrtly to the Default APN IP.");
				log.info("DeviceT oken List 2 :: "+deviceTokenList.size());
				
				try
				{
					notifications = Push.payload(payload, inputStream, keystorePassword, true, deviceTokenList, null);//Send ServerIp=null will be defaulted to Apple Production IP
				}
				catch (CommunicationException e)
				{
					e.printStackTrace();
					pnbResponseBean.setStatusCode(PNSConstant.PNS_APN_FAIL_CODE);
					pnbResponseBean.setStatusMessage(e.getMessage());
				}
				catch (KeystoreException e)
				{
					log.info("ERROR :: "+e.getMessage());
					e.printStackTrace();
					
					pnbResponseBean.setStatusCode(PNSConstant.PNS_APN_FAIL_CODE);
					pnbResponseBean.setStatusMessage(e.getMessage());
					
				}
				catch(Exception e)
				{
					log.info("Exception :: "+e);
					log.info("Failed to Push Notification through ");
					pnbResponseBean.setStatusCode(PNSConstant.PNS_COMMON_ERROR_CODE);
					pnbResponseBean.setStatusMessage(PNSConstant.PNS_COMMON_ERROR_MESG);
					
				}
				
			}
			//log.info("notifications :: "+notifications);
			//log.info(gson.toJson(notifications));
			if(notifications != null)
			{
				log.info("notifications size :: "+notifications.size());
				pnbResponseBean.setStatusCode(PNSConstant.PNS_SUCCESS_CODE);
				pnbResponseBean.setStatusMessage(PNSConstant.PNS_SUCCESS_MESG);
				for(int i=0; i<notifications.size(); i++)
				{
					PushedNotification notification = notifications.get(i);
					
					if (notification.isSuccessful())
					{
						pndDeviceRequestList.get(i).setErrorCode(PNSConstant.PNS_SUCCESS_CODE);
						
					}
					else
					{
						Exception error = notification.getException();
						
						String invalidToken = notification.getDevice().getToken();

						log.info("APNS failed for token " + invalidToken);

						log.info("Error apns ::" + error.getMessage());

						ResponsePacket errorResponse = notification.getResponse();
						if (errorResponse != null)
						{
							pndDeviceRequestList.get(i).setErrorCode(PNSConstant.PNS_INVALID_DEVICE_TOKEN_CODE);
							pndDeviceRequestList.get(i).setErrorMsg(errorResponse.getMessage());
							
							log.info("Status :: "+errorResponse.getStatus()+", Message :: "+errorResponse.getMessage()+" :: ROW :: "+i+"-"+errorResponse.getIdentifier());
							/*if (status == 0) return prefix + "No errors encountered";
	                        if (status == 1) return prefix + "Processing error";
	                        if (status == 2) return prefix + "Missing device token";
	                        if (status == 3) return prefix + "Missing topic";
	                        if (status == 4) return prefix + "Missing payload";
	                        if (status == 5) return prefix + "Invalid token size";
	                        if (status == 6) return prefix + "Invalid topic size";
	                        if (status == 7) return prefix + "Invalid payload size";
	                        if (status == 8) return prefix + "Invalid token";
	                        if (status == 255) return prefix + "None (unknown)";*/
							
						}
						else
						{
							pndDeviceRequestList.get(i).setErrorCode(PNSConstant.PNS_APN_FAIL_CODE);
						}
					}
				}
				
				pnbResponseBean.setDeviceBeanList(pndDeviceRequestList);
				
			}
			else
			{
				pnbResponseBean.setStatusCode(PNSConstant.PNS_COMMON_ERROR_CODE);
				pnbResponseBean.setStatusMessage(PNSConstant.PNS_COMMON_ERROR_MESG);
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
