package com.silverlake.mleb.pex.server.func;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.silverlake.hlb.mib.bean.ObHeaderRequest;
import com.silverlake.micb.core.bean.DeviceBean;
import com.silverlake.micb.core.bean.HeaderBean;
import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.pex.entity.dao.MLEBPExDAO;
import com.silverlake.mleb.pex.module.services.PExServices;
import com.silverlake.mleb.pex.server.webservice.bean.WSHeaderResponse;
import com.silverlake.mleb.pex.server.webservice.bean.WSPNDetails;
import com.silverlake.mleb.pex.server.webservice.bean.WSPNRequest;
import com.silverlake.mleb.pex.server.webservice.bean.WSPNResponse;
import com.silverlake.mleb.pex.server.webservice.bean.WSRequest;
import com.silverlake.mleb.pex.server.webservice.bean.WSResponse;
import com.silverlake.mleb.pex.util.PropertiesManager;
import com.silverlake.mleb.pns.bean.ObPNSRequestBean;
import com.silverlake.mleb.pns.bean.ObPNSResponseBean;
import com.silverlake.mleb.pns.constant.PNSConstant;
import com.silverlake.mleb.pns.module.PushNotificationService;


@Service
public class ProcessPushNotificationRequest extends PExFunc{

	
	private static Logger log = LogManager.getLogger(ProcessPushNotificationRequest.class);
	
	@Autowired MLEBPExDAO dao;
	@Autowired LockRenewSession taskSession;
	@Autowired PushNotificationService pushNotificationService;
	
	private PropertiesManager property = new PropertiesManager();
	
	@Override
	public WSResponse processService(WSRequest wsRequest) {
		MICBResponseBean respBean = new MICBResponseBean();
		WSPNResponse wsPNResponse = new WSPNResponse();
		wsPNResponse.setObHeader(new WSHeaderResponse());
		Gson gsonLog = new GsonBuilder().setPrettyPrinting().create();
		String refId = null;
		try
		{
			WSPNRequest pnsRequest = (WSPNRequest)wsRequest;

			PExServices pexServices = new PExServices(dao);
		//	String pnsHeader = property.getProperty("pns.header");
			String pnsHeader = pnsRequest.getTitle();
			String message = null;
			String deviceToken = null;
			refId = pnsRequest.getReferenceId();
			String appid = pnsRequest.getAppId();
			String appPass = pnsRequest.getAppPass();
			String type = pnsRequest.getType();
			String messageType = pnsRequest.getMessageType();
			
			if(null==refId || refId.trim().length()==0)
			{
				String svr_index = property.getProperty("server.index");
				refId = UUID.randomUUID().toString();
				if(null!=svr_index)
				{
					refId = svr_index+refId;
				}
			}
			
			
			//String channel = pnsRequest.getObHeader().getChannelId();
			boolean verified = false;
			//google
			if(pnsRequest.getNotificationType() != null && pnsRequest.getNotificationType().equalsIgnoreCase("G")) {
				//WSPNDetails gcm = pnsRequest.getDeliveryDetail().getGcm();
				message = pnsRequest.getMessage();
				deviceToken = pnsRequest.getDeviceToken();
				verified = true;
			}
			//APN
			else if(pnsRequest.getNotificationType() != null && pnsRequest.getNotificationType().equalsIgnoreCase("A")) {
				//WSPNDetails apn = pnsRequest.getDeliveryDetail().getApns();
				message = pnsRequest.getMessage();
				deviceToken = pnsRequest.getDeviceToken();
				verified = true;
			}
			//failed
			else {
				wsPNResponse.getObHeader().setStatusCode(PNSConstant.PNS_INVALID_REQUEST_CODE);
				wsPNResponse.getObHeader().setStatusCode(PNSConstant.PNS_INVALID_REQUEST_MESG);
			}
			
			
			if(verified) {
				
				ObPNSRequestBean pnsRequestBean = new ObPNSRequestBean();
				pnsRequestBean.setContent(message);
				pnsRequestBean.setDeviceToken(deviceToken);
				//pnsRequestBean.setTemplate(PNSConstant.PNS_TEMPLATE);
				pnsRequestBean.setHeader(pnsHeader);
				pnsRequestBean.setRequestId(refId);
				pnsRequestBean.setAppId(appid);
				pnsRequestBean.setAppPass(appPass);
				pnsRequestBean.setType(type);
				pnsRequestBean.setMessageType(messageType);
				pnsRequestBean.setObHeader(new ObHeaderRequest());
				pnsRequestBean.getObHeader().setChannelId(pnsRequest.getObHeader().getChannelId());
				
				MICBRequestBean micbRequestBean = new MICBRequestBean();
				micbRequestBean.setDeviceBean(new DeviceBean());
				micbRequestBean.getDeviceBean().setType(pnsRequest.getNotificationType());
				micbRequestBean.getDeviceBean().setModel(pnsRequest.getChannel());
				micbRequestBean.setHeaderBean(new HeaderBean());
				micbRequestBean.getHeaderBean().setBDVMURL("vm://hlbPNS");
				micbRequestBean.getHeaderBean().setServiceID(PNSConstant.FUNC_PUSH_NOTIF);

				micbRequestBean.setBDObject(pnsRequestBean);

				MICBResponseBean responseBean = pushNotificationService.process(micbRequestBean);
				log.info("PNS RESPONSE :: "+responseBean);

				if(responseBean != null && responseBean.getBDObject() != null && responseBean.getBDObject() instanceof ObPNSResponseBean) {
					ObPNSResponseBean pnsResp = (ObPNSResponseBean)responseBean.getBDObject();
					wsPNResponse.getObHeader().setStatusCode(pnsResp.getObHeader().getStatusCode());
				}
				else {
					wsPNResponse.getObHeader().setStatusCode(PNSConstant.PNS_INVALID_REQUEST_CODE);
					wsPNResponse.setReferenceId("");
				}
			}
		}
		catch(Exception e)
		{
			log.info("Exception :: "+e);
			e.printStackTrace();
			wsPNResponse.getObHeader().setStatusCode(PNSConstant.PNS_INTERNAL_ERROR_CODE);
			wsPNResponse.getObHeader().setStatusCode(PNSConstant.PNS_INTERNAL_ERROR_MESG);
			
		}
		wsPNResponse.setReferenceId(refId);
		return wsPNResponse;
	}
	
}

