package com.silverlake.mleb.pns.module;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import org.apache.log4j.Logger;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
//import org.mule.api.MuleContext;
//import org.mule.api.context.MuleContextAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.silverlake.mleb.pex.util.PropertiesManager;
import com.silverlake.mleb.pns.bean.ObPNBDeviceRequestBean;
import com.silverlake.mleb.pns.bean.ObPNBRequestBean;
import com.silverlake.mleb.pns.bean.ObPNBResponseBean;
import com.silverlake.mleb.pns.constant.PNSConstant;
import com.silverlake.mleb.pns.entity.PnsTranx;
import com.silverlake.mleb.pns.entity.ProviderProxyIp;
import com.silverlake.mleb.pns.entity.dao.PnsDao;
import com.silverlake.mleb.pns.module.func.PushToAndroid;
import com.silverlake.mleb.pns.module.func.PushToIOS;

import javapns.communication.ConnectionToAppleServer;

@Service
public class PushNotificationBroadcastService 
{
	//MuleContext muleContext;
	//private static Logger log = Logger.getLogger(PushNotificationBroadcastService.class);
	private static Logger log = LogManager.getLogger(PushNotificationBroadcastService.class);
	
	PropertiesManager pmgr = new PropertiesManager();
	
	@Autowired
	PnsDao dao;
	
	@Autowired
	PushToAndroid pushToAndroid;
	
	@Autowired
	PushToIOS pushToIOS;
	

	public ObPNBResponseBean process(ObPNBRequestBean pnbRequestBean)
	{
		ObPNBResponseBean pnbResponse = new ObPNBResponseBean();
		
		if(pnbRequestBean != null && pnbRequestBean.getDeviceOsType() != null && (pnbRequestBean.getDeviceOsType().equalsIgnoreCase(PNSConstant.OS_TYPE_ANDROID) || 
				pnbRequestBean.getDeviceOsType().equalsIgnoreCase(PNSConstant.OS_TYPE_IOS) || pnbRequestBean.getDeviceOsType().equalsIgnoreCase(PNSConstant.OS_TYPE_IPAD)))
		{
			Gson gson = new Gson();
			//log.info(gson.toJson(pnbRequestBean));
			
			String sendThroughProxy = "false";
			
			if(pmgr.getProperty("send.to.proxy") != null && !pmgr.getProperty("send.to.proxy").equals(""))
			{
				sendThroughProxy = pmgr.getProperty("send.to.proxy");
			}
			String proxyIp = pmgr.getProperty("proxy.ip");
			String proxyPort = pmgr.getProperty("proxy.port");
			
			if(pnbRequestBean.getDeviceOsType().equalsIgnoreCase(PNSConstant.OS_TYPE_ANDROID))
			{
				log.info("Broadcast to Android");
				List<ProviderProxyIp> providerProxyIpList = dao.retrieveProviderIPList(PNSConstant.OS_TYPE_ANDROID);
				pnbResponse = pushToAndroid.boradcast(pnbRequestBean, sendThroughProxy, providerProxyIpList, proxyIp, proxyPort);
			}
			
			if(pnbRequestBean.getDeviceOsType().equalsIgnoreCase(PNSConstant.OS_TYPE_IOS))
			{
				log.info("Broadcast to IOS");
				List<ProviderProxyIp> providerProxyIpList = dao.retrieveProviderIPList(PNSConstant.OS_TYPE_IOS);
				pnbResponse = pushToIOS.boradcast(pnbRequestBean, sendThroughProxy, providerProxyIpList, proxyIp, proxyPort);
			}
			
			if(pnbRequestBean.getDeviceOsType().equalsIgnoreCase(PNSConstant.OS_TYPE_IPAD))
			{
				log.info("Broadcast to IPAD");
				List<ProviderProxyIp> providerProxyIpList = dao.retrieveProviderIPList(PNSConstant.OS_TYPE_IOS);
				pnbResponse = pushToIOS.boradcast(pnbRequestBean, sendThroughProxy, providerProxyIpList, proxyIp, proxyPort);
			}
			
			if(pnbResponse != null && pnbResponse.getStatusCode() != null)
			{
				if(!pnbResponse.getStatusCode().equalsIgnoreCase(PNSConstant.PNS_COMMON_ERROR_CODE))
				{
					log.info("Insert Records to DB.");
					
					List<PnsTranx> pnsTranxList = new ArrayList<PnsTranx>();
					
					List<ObPNBDeviceRequestBean> pnbDeviceReqList = pnbResponse.getDeviceBeanList();
					
					for(int i=0; i < pnbDeviceReqList.size(); i++)
					{
						ObPNBDeviceRequestBean pnbDeviceReq = pnbDeviceReqList.get(i);
						
						PnsTranx pnsTranx = new PnsTranx();
						//pnsTranx.setCif(pnbDeviceReq.getCif());
						pnsTranx.setDeviceId(pnbDeviceReq.getDeviceId());
						//pnsTranx.setDeviceOs(pnbDeviceReq.getDeviceType());
						pnsTranx.setDeviceToken(pnbDeviceReq.getDeviceToken());
						pnsTranx.setErrorCode(pnbDeviceReq.getErrorCode());
						pnsTranx.setMessageContent(pnbRequestBean.getContent());
						pnsTranx.setMessageHeader(pnbRequestBean.getHeader());
						pnsTranx.setMulticastId(pnbDeviceReq.getMulticastId());
						pnsTranx.setPns_template(pnbRequestBean.getTemplate());
						pnsTranx.setRequestDateTime(new Date());
						//pnsTranx.setRequestId(pnbRequestBean.getRequestId());
						pnsTranx.setResendCount(0);
						pnsTranx.setStoreMessageId(pnbDeviceReq.getStoreMessageId());
						
						if(pnbDeviceReq.getErrorCode() != null && pnbDeviceReq.getErrorCode().equalsIgnoreCase(PNSConstant.PNS_SUCCESS_CODE))
						{
							pnsTranx.setStatus(PNSConstant.TRX_STATUS_SENT);
						}
						else
						{
							pnsTranx.setStatus(PNSConstant.TRX_STATUS_FAILED);
						}
						
						pnsTranxList.add(pnsTranx);
					}
					
					List<Object> insertObjList = new ArrayList<Object>(pnsTranxList);
					int insertCount = dao.insertMultiEntity(insertObjList, log);
					
					log.info("Total Records :: "+pnsTranxList.size()+", Inserted Records :: "+insertCount);
					
				}
				else
				{
					log.info(pnbResponse.getStatusCode()+" ::  Internal Error. No need to Insert Records into DB.");
				}
			}
			else
			{
				log.info("Something Went Wrong. Inverstigate. No need to Insert Records into DB.");
			}
		}
		else
		{
			log.info("Invalid Request");
			pnbResponse.setStatusCode(PNSConstant.PNS_INVALID_REQUEST_CODE);
		}
		
		return pnbResponse;
	}

/*
	@Override
	public void setMuleContext(MuleContext arg0) {
		muleContext = arg0;
	}

	
	public MuleContext getMuleContext() {
		return muleContext;
	}
	*/
}
