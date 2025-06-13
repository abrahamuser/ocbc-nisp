package com.silverlake.mleb.pns.module;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
//import org.apache.log4j.Logger;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.silverlake.hlb.mib.bean.ObHeaderResponse;
import com.silverlake.hlb.mib.constant.MiBConstant;
import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.pex.module.common.FuncServices;
import com.silverlake.mleb.pex.util.PropertiesManager;
import com.silverlake.mleb.pns.bean.ObPNSRequestBean;
import com.silverlake.mleb.pns.bean.ObPNSResponseBean;
import com.silverlake.mleb.pns.constant.PNSConstant;
import com.silverlake.mleb.pns.entity.PnsApps;
import com.silverlake.mleb.pns.entity.PnsTranx;
import com.silverlake.mleb.pns.entity.ProviderProxyIp;
import com.silverlake.mleb.pns.entity.dao.PnsDao;
import com.silverlake.mleb.pns.module.func.PushToAndroid;
import com.silverlake.mleb.pns.module.func.PushToIOS;


@Service
public class PushNotificationService  extends FuncServices
{
	

	
	private static Logger log = LogManager.getLogger(PushNotificationService.class);
	
	private PropertiesManager pmgr = new PropertiesManager();
	
	@Autowired
	PnsDao dao;
	
	@Autowired
	PushToAndroid pushToAndroid;
	
	@Autowired
	PushToIOS pushToIOS;

	
	@Override
	public MICBResponseBean process(MICBRequestBean arg0)
	{
		MICBResponseBean response = new MICBResponseBean();
		ObPNSResponseBean pnsResponse = new ObPNSResponseBean();
		
		try
		{
			Gson gson = new Gson();
			//log.info(gson.toJson(arg0));
			
			if(arg0.getBDObject() instanceof ObPNSRequestBean)
			{
				ObPNSRequestBean pnsRequestBean = (ObPNSRequestBean)arg0.getBDObject();
				//log.info(pnsRequestBean.getDeviceToken());
				log.info(gson.toJson(pnsRequestBean));
				
				if(pnsRequestBean.getDeviceToken() != null && !pnsRequestBean.getDeviceToken().equals(""))
				{
					
					PnsApps clientAppData = dao.getAppPushData(pnsRequestBean.getAppId(),pnsRequestBean.getAppPass());
					if(null==clientAppData)
					{
						pnsResponse.setStatusCode(PNSConstant.PNS_INVALID_APP_ID);
						pnsResponse.setStatusMessage(PNSConstant.PNS_INVALID_APP_ID_MESG);
						pnsResponse.setObHeader(new ObHeaderResponse());
						pnsResponse.getObHeader().setStatusCode(pnsResponse.getStatusCode());
					}
					else if(arg0.getDeviceBean() != null  && 
							(arg0.getDeviceBean().getType().equalsIgnoreCase("A") ||
									arg0.getDeviceBean().getType().equalsIgnoreCase("G") ))
					{
						String sendThroughProxy = "false";
						
						if(pmgr.getProperty("send.to.proxy") != null && !pmgr.getProperty("send.to.proxy").equals(""))
						{
							sendThroughProxy = pmgr.getProperty("send.to.proxy");
						}
						String proxyIp = pmgr.getProperty("proxy.ip");
						String proxyPort = pmgr.getProperty("proxy.port");
						
						PnsTranx pnsTranx = new PnsTranx();
						//pnsTranx.setCif(pnsRequestBean.getCif());
						pnsTranx.setChannel(pnsRequestBean.getObHeader().getChannelId());
						pnsTranx.setDeviceId(pnsRequestBean.getDeviceId());

						pnsTranx.setNotificationType(arg0.getDeviceBean().getType());
						pnsTranx.setDeviceToken(pnsRequestBean.getDeviceToken());
						pnsTranx.setMessageHeader(pnsRequestBean.getHeader());
						pnsTranx.setMessageContent(pnsRequestBean.getContent());
						pnsTranx.setPns_template(clientAppData.getAndroidMsgTemplate());
						pnsTranx.setRequestDateTime(new Date());
						pnsTranx.setRefId(pnsRequestBean.getRequestId());
						pnsTranx.setResendCount(0);
						pnsTranx.setResponseDateTime(new Date());
						pnsTranx.setStatus(PNSConstant.TRX_STATUS_ACCEPTED);
						pnsTranx.setAppId(pnsRequestBean.getAppId());
						
						//log.info("BEFORE INSERT :: "+gson.toJson(pnsTranx));
						pnsTranx = (PnsTranx)dao.insertandReturnEntity(pnsTranx);
						
						pnsRequestBean.setTemplate(clientAppData.getAndroidMsgTemplate());
						arg0.setBDObject(pnsRequestBean);
						
						//log.info("Inserted to DB :: "+pnsTranx);
						
						/*
						 * Retrieve Proxy IP's
						 */
						//List<ProviderProxyIp> providerProxyIpList = dao.retrieveProviderIPList(arg0.getDeviceBean().getOs());
						
						if(arg0.getDeviceBean().getType().equalsIgnoreCase("G"))
						{
							log.info("Send Notification to Android :: "+((ObPNSRequestBean)arg0.getBDObject()).getTemplate());
							pnsResponse = pushToAndroid.push(clientAppData,arg0, sendThroughProxy, null, proxyIp, proxyPort);
						}
						else
						{
							//log.info("Send Notification to IOS :: "+proxyIp+" :: "+proxyPort);
							pnsResponse = pushToIOS.push(clientAppData,arg0, sendThroughProxy, null, proxyIp, proxyPort,arg0.getDeviceBean().getModel());
						}
						
						pnsTranx.setStoreMessageId(pnsResponse.getStoreMessageId());
						pnsTranx.setErrorCode(pnsResponse.getErrorCode());
						pnsTranx.setMulticastId(pnsResponse.getMulticastId());
						pnsTranx.setResponseDateTime(new Date());
						if(pnsResponse.getStatusCode().equalsIgnoreCase(PNSConstant.PNS_SUCCESS_CODE))
						{
							pnsTranx.setStatus(PNSConstant.TRX_STATUS_SENT);
						}
						else
						{
							pnsTranx.setStatus(PNSConstant.TRX_STATUS_FAILED);
						}
						pnsTranx = (PnsTranx)dao.updateEntity(pnsTranx);
						log.info(gson.toJson(pnsTranx));
						
						pnsResponse.setObHeader(new ObHeaderResponse());
						pnsResponse.getObHeader().setStatusCode(pnsResponse.getStatusCode());
					}
					else
					{
						pnsResponse.setStatusCode(PNSConstant.PNS_INVALID_DEVICE_OS_CODE);
						pnsResponse.setStatusMessage(PNSConstant.PNS_INVALID_DEVICE_OS_MESG);
						pnsResponse.setObHeader(new ObHeaderResponse());
						pnsResponse.getObHeader().setStatusCode(pnsResponse.getStatusCode());
					}
				}
				else
				{
					pnsResponse.setStatusCode(PNSConstant.PNS_INVALID_DEVICE_TOKEN_CODE);
					pnsResponse.setStatusMessage(PNSConstant.PNS_INVALID_DEVICE_TOKEN_MESG);
					pnsResponse.setObHeader(new ObHeaderResponse());
					pnsResponse.getObHeader().setStatusCode(pnsResponse.getStatusCode());
				}
			}
		}
		catch(Exception e)
		{
			log.info("Exception in PushNotificationService. "+e.getMessage());
			e.printStackTrace();
			pnsResponse.setStatusCode(PNSConstant.PNS_COMMON_ERROR_CODE);
			pnsResponse.setStatusMessage(e.getMessage());
			pnsResponse.setObHeader(new ObHeaderResponse());
			pnsResponse.getObHeader().setStatusCode(pnsResponse.getStatusCode());
		}
		response.setBDObject(pnsResponse);
		return response;
	}
}
