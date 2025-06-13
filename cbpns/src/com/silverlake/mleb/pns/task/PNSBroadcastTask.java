package com.silverlake.mleb.pns.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
//import org.apache.log4j.Logger;
import org.apache.logging.log4j.Logger;
//import org.mule.api.MuleContext;
//import org.mule.api.context.MuleContextAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.silverlake.mleb.pex.entity.dao.MLEBDAO;
import com.silverlake.mleb.pex.entity.dao.PEXDAO;
import com.silverlake.mleb.pex.task.TaskRenewSession;
import com.silverlake.mleb.pns.bean.ObPNBDeviceRequestBean;
import com.silverlake.mleb.pns.bean.ObPNBRequestBean;
import com.silverlake.mleb.pns.bean.ObPNBResponseBean;
import com.silverlake.mleb.pns.constant.PNSConstant;
import com.silverlake.mleb.pns.entity.PnbConf;
import com.silverlake.mleb.pns.entity.PnbPromotion;
import com.silverlake.mleb.pns.entity.PnbRequest;
import com.silverlake.mleb.pns.entity.PnbResponse;
import com.silverlake.mleb.pns.entity.dao.PnsDao;
import com.silverlake.mleb.pns.module.PushNotificationBroadcastService;
import com.silverlake.mleb.pns.module.services.PNSServices;

@Service
public class PNSBroadcastTask 
{
	//private static Logger log = Logger.getLogger(PNSBroadcastTask.class);
	private static Logger log = LogManager.getLogger(PNSBroadcastTask.class);
	private static final String KEYLOCK = "LOCK_PUSH_BROADCAST_TASK";
	
	
	@Autowired TaskRenewSession renewSession;
	@Autowired MLEBDAO dao;
	
	@Autowired PEXDAO pexDao;
	
	@Autowired PnsDao pnsDao;
	
	@Autowired
	PushNotificationBroadcastService pushNotificationBroadcastService;
	
	
	
	//@Scheduled(fixedDelay=60000) //Run ever minute once
	public void process()
	{
		try
		{
			boolean data = renewSession.checkTask(KEYLOCK);
			if(data)
			{
				Future<String>future  = renewSession.processTask(KEYLOCK);
				try
				{
					taskProcess();
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				
				future.cancel(true);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	//@PostConstruct
	public void initIt() throws Exception
	{
		log.info("Push Notification Broadcast Task Initialized.");
	}
	
	public void taskProcess()
	{
		try
		{
			PNSServices pnsServices = new PNSServices(dao);
			PnbConf pnbConf = pnsServices.getPNBConf();
			
			Gson gson = new Gson();
			
			PnbPromotion pendingPromotion = pnsServices.retrieveActivePromotions();
			
			if(pendingPromotion != null)
			{
				log.info("There is a Pending promotion to be Pushed out with MessageCode :: "+pendingPromotion.getMsgCode()+" :: "+pendingPromotion.getStatus()+" :: "+pendingPromotion.getFlag());
				
				/*
				 * There is a Pending Promotion to be Pushed out. Read from the PNBCONF setting on Android, iOs, iPad max counts to send out.
				 * Retrieve Records to be send out from REQUEST table.
				 */
				log.info("Retrieve Android Devices Start");
				List<PnbRequest> pnbRequestListAndroid = pnsServices.retrievePendingRequestList(PNSConstant.OS_TYPE_ANDROID, pnbConf.getAndroidMaxTokens(),
						pendingPromotion.getMsgCode());
				log.info("Retrieve Android Devices End");
				/*
				 * Push to Android Devices
				 */
				log.info("Push to Android Devices Start");
				if(pnbRequestListAndroid != null && pnbRequestListAndroid.size() > 0)
				{
					log.info("Total Android Devices to Push :: "+pnbConf.getAndroidMaxTokens()+" :: "+pnbRequestListAndroid.size());
					List<ObPNBDeviceRequestBean> pnbDeviceBeanList = new ArrayList<ObPNBDeviceRequestBean>();
					for(PnbRequest pnbRequest : pnbRequestListAndroid)
					{
						ObPNBDeviceRequestBean pnbDeviceBean = new ObPNBDeviceRequestBean();
						pnbDeviceBean.setCif(pnbRequest.getCif());
						pnbDeviceBean.setDeviceId(pnbRequest.getDeviceId());
						pnbDeviceBean.setDeviceToken(pnbRequest.getDeviceToken());
						pnbDeviceBean.setDeviceModel(pnbRequest.getDeviceModel());
						pnbDeviceBean.setDeviceType(pnbRequest.getDeviceType());
						pnbDeviceBean.setRetryCount(pnbRequest.getRetryCount());
						pnbDeviceBean.setRowId(pnbRequest.getRowId());
						pnbDeviceBean.setTrxStatus(pnbRequest.getStatus());
						pnbDeviceBean.setResponseRowId(pnbRequest.getResponseRowId());
						pnbDeviceBean.setDeviceTaggedDate(pnbRequest.getDeviceTaggedDate());
						
						pnbDeviceBeanList.add(pnbDeviceBean);
					}
					List<String> customPropertiesList = new ArrayList<String>();
					if(pendingPromotion.getUrlLink() != null && !pendingPromotion.getUrlLink().equals(""))
					{
						String customProperties = "url|"+pendingPromotion.getUrlLink();
						customPropertiesList.add(customProperties);
					}
					ObPNBRequestBean pnbRequestBean = new ObPNBRequestBean();
					
					pnbRequestBean.setContent(pendingPromotion.getNotifMsg());
					pnbRequestBean.setDeviceOsType(PNSConstant.OS_TYPE_ANDROID);
					pnbRequestBean.setRequestId(pendingPromotion.getMsgCode());
					pnbRequestBean.setTemplate(PNSConstant.PNS_TEMPLATE_PROMOTION);
					pnbRequestBean.setCustomProperties(customPropertiesList);
					pnbRequestBean.setDeviceBeanList(pnbDeviceBeanList);
					
					log.info("Sending out to Android Devices Start");
					ObPNBResponseBean androidPnbResponse = pushNotificationBroadcastService.process(pnbRequestBean);
					log.info("Sending out to Android Devices End");
					
					if(androidPnbResponse != null && androidPnbResponse.getStatusCode() != null && androidPnbResponse.getStatusCode().equalsIgnoreCase(PNSConstant.PNS_SUCCESS_CODE))
					{
						log.info("Insert to Response Table & Delete from Request Table Start.");
						
						if((pendingPromotion.getStatus().equalsIgnoreCase(PNSConstant.PNB_PROMO_STATUS_FAILED) || pendingPromotion.getStatus().equalsIgnoreCase(PNSConstant.PNB_PROMO_STATUS_COMPLETED))
								&& (pendingPromotion.getFlag().equalsIgnoreCase(PNSConstant.PNB_PROMO_FLAG_RETRY) || pendingPromotion.getFlag().equalsIgnoreCase(PNSConstant.PNB_PROMO_FLAG_INPROGRESS_RETRY)))
						{
							List<PnbResponse> hlbPnbResponseList = new ArrayList<PnbResponse>();
							
							for(ObPNBDeviceRequestBean pnbDeviceBean : androidPnbResponse.getDeviceBeanList())
							{
								PnbResponse hlbPnbResponse = new PnbResponse();
								hlbPnbResponse.setCif(pnbDeviceBean.getCif());
								hlbPnbResponse.setDeviceId(pnbDeviceBean.getDeviceId());
								hlbPnbResponse.setDeviceModel(pnbDeviceBean.getDeviceModel());
								hlbPnbResponse.setDeviceToken(pnbDeviceBean.getDeviceToken());
								hlbPnbResponse.setDeviceType(pnbDeviceBean.getDeviceType());
								hlbPnbResponse.setPnbPromo(pendingPromotion);
								hlbPnbResponse.setNotifSendDate(new Date());
								hlbPnbResponse.setRetryCount(pnbDeviceBean.getRetryCount());
								hlbPnbResponse.setRowId(pnbDeviceBean.getResponseRowId());
								hlbPnbResponse.setDeviceTaggedDate(pnbDeviceBean.getDeviceTaggedDate());
								
								if(pnbDeviceBean.getErrorCode().equalsIgnoreCase(PNSConstant.PNS_SUCCESS_CODE))
								{
									hlbPnbResponse.setStatus(PNSConstant.PNB_TRX_STATUS_SUCCESS);
								}
								else
								{
									hlbPnbResponse.setStatus(PNSConstant.PNB_TRX_STATUS_FAILED);
								}
								
								hlbPnbResponseList.add(hlbPnbResponse);
										
							}
							
							int count = pnsDao.updatePnbResponseMultiEntity(hlbPnbResponseList);
							
							if(count == hlbPnbResponseList.size())
							{
								log.info("All Records Updated to Response Table. Remove those now from Request Table");
							}
							else
							{
								log.info("Tried to Update :: "+hlbPnbResponseList.size()+" Rows. but Only Updated :: "+count+" Rows.");
							}
							
							List<Object> pnsRequestListToDelete = new ArrayList<Object>(pnbRequestListAndroid);
							int delCount = pnsDao.deleteMultiEntityForBroadcast(pnbRequestListAndroid);
							
							log.info("Tried to Delete :: "+pnsRequestListToDelete.size()+" Rows. And Deleted :: "+delCount+" Rows.");
							
							log.info("Insert to Response Table & Delete from Request Table End.");
							
							
						}
						else
						{
							List<Object> hlbPnbResponseList = new ArrayList<Object>();
							
							for(ObPNBDeviceRequestBean pnbDeviceBean : androidPnbResponse.getDeviceBeanList())
							{
								PnbResponse hlbPnbResponse = new PnbResponse();
								hlbPnbResponse.setCif(pnbDeviceBean.getCif());
								hlbPnbResponse.setDeviceId(pnbDeviceBean.getDeviceId());
								hlbPnbResponse.setDeviceModel(pnbDeviceBean.getDeviceModel());
								hlbPnbResponse.setDeviceToken(pnbDeviceBean.getDeviceToken());
								hlbPnbResponse.setDeviceType(pnbDeviceBean.getDeviceType());
								hlbPnbResponse.setPnbPromo(pendingPromotion);
								hlbPnbResponse.setNotifSendDate(new Date());
								hlbPnbResponse.setRetryCount(pnbDeviceBean.getRetryCount());
								hlbPnbResponse.setDeviceTaggedDate(pnbDeviceBean.getDeviceTaggedDate());
								
								if(pnbDeviceBean.getErrorCode().equalsIgnoreCase(PNSConstant.PNS_SUCCESS_CODE))
								{
									hlbPnbResponse.setStatus(PNSConstant.PNB_TRX_STATUS_SUCCESS);
								}
								else
								{
									hlbPnbResponse.setStatus(PNSConstant.PNB_TRX_STATUS_FAILED);
								}
								
								hlbPnbResponseList.add(hlbPnbResponse);
								
										
							}
							int count = pnsDao.insertMultiEntity(hlbPnbResponseList, log);
							
							if(count == hlbPnbResponseList.size())
							{
								log.info("All Records Inserted to Response Table. Remove those now from Request Table");
							}
							else
							{
								log.info("Tried to Insert :: "+hlbPnbResponseList.size()+" Rows. but Only Inserted :: "+count+" Rows.");
							}
							
							List<Object> pnsRequestListToDelete = new ArrayList<Object>(pnbRequestListAndroid);
							int delCount = pnsDao.deleteMultiEntityForBroadcast(pnbRequestListAndroid);
							
							log.info("Tried to Delete :: "+pnsRequestListToDelete.size()+" Rows. And Deleted :: "+delCount+" Rows.");
							
							log.info("Insert to Response Table & Delete from Request Table End.");
						}
						
					}
					else
					{
						log.info("Failed To send out ");
					}
					
					
				}
				else
				{
					log.info("There is no Android Devices left to Push for the promotion :: "+pendingPromotion.getMsgCode());
				}
				log.info("Push to Android Devices End");
				
				log.info("Retrieve IOS Devices Start");
				List<PnbRequest> pnbRequestListiOS = pnsServices.retrievePendingRequestList(PNSConstant.OS_TYPE_IOS, pnbConf.getIosMaxTokens(),
						pendingPromotion.getMsgCode());
				log.info("Retrieve IOS Devices End");
				
				
				log.info("Push to IOS Devices Start");
				if(pnbRequestListiOS != null && pnbRequestListiOS.size() > 0)
				{
					log.info("Total IOS Devices to Push :: "+pnbConf.getIosMaxTokens()+" :: "+pnbRequestListiOS.size());
					List<ObPNBDeviceRequestBean> pnbDeviceBeanList = new ArrayList<ObPNBDeviceRequestBean>();
					for(PnbRequest pnbRequest : pnbRequestListiOS)
					{
						ObPNBDeviceRequestBean pnbDeviceBean = new ObPNBDeviceRequestBean();
						pnbDeviceBean.setCif(pnbRequest.getCif());
						pnbDeviceBean.setDeviceId(pnbRequest.getDeviceId());
						pnbDeviceBean.setDeviceToken(pnbRequest.getDeviceToken());
						pnbDeviceBean.setDeviceModel(pnbRequest.getDeviceModel());
						pnbDeviceBean.setDeviceType(pnbRequest.getDeviceType());
						pnbDeviceBean.setRetryCount(pnbRequest.getRetryCount());
						pnbDeviceBean.setRowId(pnbRequest.getResponseRowId());
						pnbDeviceBean.setDeviceTaggedDate(pnbRequest.getDeviceTaggedDate());
						
						pnbDeviceBeanList.add(pnbDeviceBean);
					}
					List<String> customPropertiesList = new ArrayList<String>();
					if(pendingPromotion.getUrlLink() != null && !pendingPromotion.getUrlLink().equals(""))
					{
						String customProperties = "url|"+pendingPromotion.getUrlLink();
						customPropertiesList.add(customProperties);
					}
					ObPNBRequestBean pnbRequestBean = new ObPNBRequestBean();
					
					pnbRequestBean.setContent(pendingPromotion.getNotifMsg());
					pnbRequestBean.setDeviceOsType(PNSConstant.OS_TYPE_IOS);
					pnbRequestBean.setRequestId(pendingPromotion.getMsgCode());
					pnbRequestBean.setTemplate(PNSConstant.PNS_TEMPLATE_PROMOTION);
					pnbRequestBean.setCustomProperties(customPropertiesList);
					pnbRequestBean.setDeviceBeanList(pnbDeviceBeanList);
					
					log.info("Sending out to IOS Devices Start");
					ObPNBResponseBean iosPnbResponse = pushNotificationBroadcastService.process(pnbRequestBean);
					log.info("Sending out to IOS Devices End");
					
					if(iosPnbResponse != null && iosPnbResponse.getStatusCode() != null && iosPnbResponse.getStatusCode().equalsIgnoreCase(PNSConstant.PNS_SUCCESS_CODE))
					{
						log.info("Update to Response Table & Delete from Request Table Start.");
						
						
						
						if((pendingPromotion.getStatus().equalsIgnoreCase(PNSConstant.PNB_PROMO_STATUS_FAILED) || pendingPromotion.getStatus().equalsIgnoreCase(PNSConstant.PNB_PROMO_STATUS_COMPLETED))
								&& (pendingPromotion.getFlag().equalsIgnoreCase(PNSConstant.PNB_PROMO_FLAG_RETRY) || pendingPromotion.getFlag().equalsIgnoreCase(PNSConstant.PNB_PROMO_FLAG_INPROGRESS_RETRY)))
						{
							List<PnbResponse> hlbPnbResponseList = new ArrayList<PnbResponse>();
							
							for(ObPNBDeviceRequestBean pnbDeviceBean : iosPnbResponse.getDeviceBeanList())
							{
								PnbResponse hlbPnbResponse = new PnbResponse();
								hlbPnbResponse.setCif(pnbDeviceBean.getCif());
								hlbPnbResponse.setDeviceId(pnbDeviceBean.getDeviceId());
								hlbPnbResponse.setDeviceModel(pnbDeviceBean.getDeviceModel());
								hlbPnbResponse.setDeviceToken(pnbDeviceBean.getDeviceToken());
								hlbPnbResponse.setDeviceType(pnbDeviceBean.getDeviceType());
								hlbPnbResponse.setPnbPromo(pendingPromotion);
								hlbPnbResponse.setNotifSendDate(new Date());
								hlbPnbResponse.setRetryCount(pnbDeviceBean.getRetryCount());
								hlbPnbResponse.setRowId(pnbDeviceBean.getResponseRowId());
								hlbPnbResponse.setDeviceTaggedDate(pnbDeviceBean.getDeviceTaggedDate());
								
								if(pnbDeviceBean.getErrorCode().equalsIgnoreCase(PNSConstant.PNS_SUCCESS_CODE))
								{
									hlbPnbResponse.setStatus(PNSConstant.PNB_TRX_STATUS_SUCCESS);
								}
								else
								{
									hlbPnbResponse.setStatus(PNSConstant.PNB_TRX_STATUS_FAILED);
								}
								
								hlbPnbResponseList.add(hlbPnbResponse);
										
							}
							
							int count = pnsDao.updatePnbResponseMultiEntity(hlbPnbResponseList);
							
							if(count == hlbPnbResponseList.size())
							{
								log.info("All Records Updated to Response Table. Remove those now from Request Table");
							}
							else
							{
								log.info("Tried to Update :: "+hlbPnbResponseList.size()+" Rows. but Only Updated :: "+count+" Rows.");
							}
							
							List<Object> pnsRequestListToDelete = new ArrayList<Object>(pnbRequestListiOS);
							int delCount = pnsDao.deleteMultiEntityForBroadcast(pnbRequestListiOS);
							
							log.info("Tried to Delete :: "+pnsRequestListToDelete.size()+" Rows. And Deleted :: "+delCount+" Rows.");
							
							log.info("Update to Response Table & Delete from Request Table End.");
							
							
						}
						else
						{
							List<Object> hlbPnbResponseList = new ArrayList<Object>();
							
							for(ObPNBDeviceRequestBean pnbDeviceBean : iosPnbResponse.getDeviceBeanList())
							{
								PnbResponse hlbPnbResponse = new PnbResponse();
								hlbPnbResponse.setCif(pnbDeviceBean.getCif());
								hlbPnbResponse.setDeviceId(pnbDeviceBean.getDeviceId());
								hlbPnbResponse.setDeviceModel(pnbDeviceBean.getDeviceModel());
								hlbPnbResponse.setDeviceToken(pnbDeviceBean.getDeviceToken());
								hlbPnbResponse.setDeviceType(pnbDeviceBean.getDeviceType());
								hlbPnbResponse.setPnbPromo(pendingPromotion);
								hlbPnbResponse.setNotifSendDate(new Date());
								hlbPnbResponse.setRetryCount(pnbDeviceBean.getRetryCount());
								hlbPnbResponse.setDeviceTaggedDate(pnbDeviceBean.getDeviceTaggedDate());
								
								if(pnbDeviceBean.getErrorCode().equalsIgnoreCase(PNSConstant.PNS_SUCCESS_CODE))
								{
									hlbPnbResponse.setStatus(PNSConstant.PNB_TRX_STATUS_SUCCESS);
								}
								else
								{
									hlbPnbResponse.setStatus(PNSConstant.PNB_TRX_STATUS_FAILED);
								}
								
								hlbPnbResponseList.add(hlbPnbResponse);
										
							}
							int count = pnsDao.insertMultiEntity(hlbPnbResponseList, log);
							
							if(count == hlbPnbResponseList.size())
							{
								log.info("All Records Inserted to Response Table. Remove those now from Request Table");
							}
							else
							{
								log.info("Tried to Insert :: "+hlbPnbResponseList.size()+" Rows. but Only Inserted :: "+count+" Rows.");
							}
							
							int delCount = pnsDao.deleteMultiEntityForBroadcast(pnbRequestListiOS);
							
							log.info("Tried to Delete :: "+pnbRequestListiOS.size()+" Rows. And Deleted :: "+delCount+" Rows.");
							
							
							log.info("Insert to Response Table & Delete from Request Table End.");
							
						}
						
						
						
					}
					else
					{
						log.info("Failed To send out ");
					}
					
					
				}
				else
				{
					log.info("There is no IOS Devices left to Push for the promotion :: "+pendingPromotion.getMsgCode());
				}
				log.info("Push to IOS Devices End");
				
				
				log.info("Retrieve IPAD Devices Start");
				List<PnbRequest> pnbRequestListiPad = pnsServices.retrievePendingRequestList(PNSConstant.OS_TYPE_IPAD, pnbConf.getIpadMaxTokens(),
						pendingPromotion.getMsgCode());
				log.info("Retrieve IPAD Devices Start");
				
				log.info("Push to IPAD Devices Start");
				if(pnbRequestListiPad != null && pnbRequestListiPad.size() > 0)
				{
					log.info("Total Android Devices to Push :: "+pnbConf.getIpadMaxTokens()+" :: "+pnbRequestListiPad.size());
					List<ObPNBDeviceRequestBean> pnbDeviceBeanList = new ArrayList<ObPNBDeviceRequestBean>();
					for(PnbRequest pnbRequest : pnbRequestListiPad)
					{
						ObPNBDeviceRequestBean pnbDeviceBean = new ObPNBDeviceRequestBean();
						pnbDeviceBean.setCif(pnbRequest.getCif());
						pnbDeviceBean.setDeviceId(pnbRequest.getDeviceId());
						pnbDeviceBean.setDeviceToken(pnbRequest.getDeviceToken());
						pnbDeviceBean.setDeviceModel(pnbRequest.getDeviceModel());
						pnbDeviceBean.setDeviceType(pnbRequest.getDeviceType());
						pnbDeviceBean.setRetryCount(pnbRequest.getRetryCount());
						pnbDeviceBean.setRowId(pnbRequest.getResponseRowId());
						pnbDeviceBean.setDeviceTaggedDate(pnbRequest.getDeviceTaggedDate());
						
						pnbDeviceBeanList.add(pnbDeviceBean);
					}
					List<String> customPropertiesList = new ArrayList<String>();
					if(pendingPromotion.getUrlLink() != null && !pendingPromotion.getUrlLink().equals(""))
					{
						String customProperties = "url|"+pendingPromotion.getUrlLink();
						customPropertiesList.add(customProperties);
					}
					ObPNBRequestBean pnbRequestBean = new ObPNBRequestBean();
					
					pnbRequestBean.setContent(pendingPromotion.getNotifMsg());
					pnbRequestBean.setDeviceOsType(PNSConstant.OS_TYPE_IPAD);
					pnbRequestBean.setRequestId(pendingPromotion.getMsgCode());
					pnbRequestBean.setTemplate(PNSConstant.PNS_TEMPLATE_PROMOTION);
					pnbRequestBean.setCustomProperties(customPropertiesList);
					pnbRequestBean.setDeviceBeanList(pnbDeviceBeanList);
					
					log.info("Sending out to IOS Devices Start");
					ObPNBResponseBean iPadPnbResponse = pushNotificationBroadcastService.process(pnbRequestBean);
					log.info("Sending out to IOS Devices End");
					
					if(iPadPnbResponse != null && iPadPnbResponse.getStatusCode() != null && iPadPnbResponse.getStatusCode().equalsIgnoreCase(PNSConstant.PNS_SUCCESS_CODE))
					{
						log.info("Insert to Response Table & Delete from Request Table Start.");
						
						if((pendingPromotion.getStatus().equalsIgnoreCase(PNSConstant.PNB_PROMO_STATUS_FAILED) || pendingPromotion.getStatus().equalsIgnoreCase(PNSConstant.PNB_PROMO_STATUS_COMPLETED))
								&& (pendingPromotion.getFlag().equalsIgnoreCase(PNSConstant.PNB_PROMO_FLAG_RETRY) || pendingPromotion.getFlag().equalsIgnoreCase(PNSConstant.PNB_PROMO_FLAG_INPROGRESS_RETRY)))
						{
							List<PnbResponse> hlbPnbResponseList = new ArrayList<PnbResponse>();
							
							for(ObPNBDeviceRequestBean pnbDeviceBean : iPadPnbResponse.getDeviceBeanList())
							{
								PnbResponse hlbPnbResponse = new PnbResponse();
								hlbPnbResponse.setCif(pnbDeviceBean.getCif());
								hlbPnbResponse.setDeviceId(pnbDeviceBean.getDeviceId());
								hlbPnbResponse.setDeviceModel(pnbDeviceBean.getDeviceModel());
								hlbPnbResponse.setDeviceToken(pnbDeviceBean.getDeviceToken());
								hlbPnbResponse.setDeviceType(pnbDeviceBean.getDeviceType());
								hlbPnbResponse.setPnbPromo(pendingPromotion);
								hlbPnbResponse.setNotifSendDate(new Date());
								hlbPnbResponse.setRetryCount(pnbDeviceBean.getRetryCount());
								hlbPnbResponse.setRowId(pnbDeviceBean.getResponseRowId());
								hlbPnbResponse.setDeviceTaggedDate(pnbDeviceBean.getDeviceTaggedDate());
								
								if(pnbDeviceBean.getErrorCode().equalsIgnoreCase(PNSConstant.PNS_SUCCESS_CODE))
								{
									hlbPnbResponse.setStatus(PNSConstant.PNB_TRX_STATUS_SUCCESS);
								}
								else
								{
									hlbPnbResponse.setStatus(PNSConstant.PNB_TRX_STATUS_FAILED);
								}
								
								hlbPnbResponseList.add(hlbPnbResponse);
										
							}
							
							int count = pnsDao.updatePnbResponseMultiEntity(hlbPnbResponseList);
							
							if(count == hlbPnbResponseList.size())
							{
								log.info("All Records Updated to Response Table. Remove those now from Request Table");
							}
							else
							{
								log.info("Tried to Update :: "+hlbPnbResponseList.size()+" Rows. but Only Updated :: "+count+" Rows.");
							}
							
							List<Object> pnsRequestListToDelete = new ArrayList<Object>(pnbRequestListiOS);
							int delCount = pnsDao.deleteMultiEntityForBroadcast(pnbRequestListiOS);
							
							log.info("Tried to Delete :: "+pnsRequestListToDelete.size()+" Rows. And Deleted :: "+delCount+" Rows.");
							
							log.info("Update to Response Table & Delete from Request Table End.");
							
							
						}
						else
						{
							List<Object> hlbPnbResponseList = new ArrayList<Object>();
							
							for(ObPNBDeviceRequestBean pnbDeviceBean : iPadPnbResponse.getDeviceBeanList())
							{
								PnbResponse hlbPnbResponse = new PnbResponse();
								hlbPnbResponse.setCif(pnbDeviceBean.getCif());
								hlbPnbResponse.setDeviceId(pnbDeviceBean.getDeviceId());
								hlbPnbResponse.setDeviceModel(pnbDeviceBean.getDeviceModel());
								hlbPnbResponse.setDeviceToken(pnbDeviceBean.getDeviceToken());
								hlbPnbResponse.setDeviceType(pnbDeviceBean.getDeviceType());
								hlbPnbResponse.setPnbPromo(pendingPromotion);
								hlbPnbResponse.setNotifSendDate(new Date());
								hlbPnbResponse.setRetryCount(pnbDeviceBean.getRetryCount());
								hlbPnbResponse.setDeviceTaggedDate(pnbDeviceBean.getDeviceTaggedDate());
								
								if(pnbDeviceBean.getErrorCode().equalsIgnoreCase(PNSConstant.PNS_SUCCESS_CODE))
								{
									hlbPnbResponse.setStatus(PNSConstant.PNB_TRX_STATUS_SUCCESS);
								}
								else
								{
									hlbPnbResponse.setStatus(PNSConstant.PNB_TRX_STATUS_FAILED);
								}
								
								hlbPnbResponseList.add(hlbPnbResponse);
								
										
							}
							int count = pnsDao.insertMultiEntity(hlbPnbResponseList, log);
							
							if(count == hlbPnbResponseList.size())
							{
								log.info("All Records Inserted to Response Table. Remove those now from Request Table");
							}
							else
							{
								log.info("Tried to Insert :: "+hlbPnbResponseList.size()+" Rows. but Only Inserted :: "+count+" Rows.");
							}
							
							int delCount = pnsDao.deleteMultiEntityForBroadcast(pnbRequestListiPad);
							
							log.info("Tried to Delete :: "+pnbRequestListiPad.size()+" Rows. And Deleted :: "+delCount+" Rows.");
							
							log.info("Insert to Response Table & Delete from Request Table End.");
						}
						
						
					}
					else
					{
						log.info("Failed To send out ");
					}
					
					
				}
				else
				{
					log.info("There is no IPAD Devices left to Push for the promotion :: "+pendingPromotion.getMsgCode());
				}
				log.info("Push to IPAD Devices End");
				
				if(pnbRequestListAndroid != null && pnbRequestListiOS != null && pnbRequestListiPad != null && pnbRequestListAndroid.size() == 0 &&
						pnbRequestListiOS.size() == 0 && pnbRequestListiPad.size() == 0 && pendingPromotion.getInsertReqStatus() != null && 
						pendingPromotion.getInsertReqStatus().equals("12"))
				{
					log.info("There is no more Requests to Push out for MESSAGE CODE :: "+pendingPromotion.getMsgCode()+" :: Double check for any remaining records,"
							+ "Update the Status to Completed.");
					/*
					 * As the Insert to Request Table Task Might be Inserting records at same time,
					 * Double check again, befor updating the promotion status to COMPLETED
					 */
					pnbRequestListAndroid = pnsServices.retrievePendingRequestList(PNSConstant.OS_TYPE_ANDROID, 2, pendingPromotion.getMsgCode());
					pnbRequestListiOS = pnsServices.retrievePendingRequestList(PNSConstant.OS_TYPE_IOS, 2, pendingPromotion.getMsgCode());
					pnbRequestListiPad = pnsServices.retrievePendingRequestList(PNSConstant.OS_TYPE_IPAD, 2, pendingPromotion.getMsgCode());
					
					if(pnbRequestListAndroid != null && pnbRequestListiOS != null && pnbRequestListiPad != null && pnbRequestListAndroid.size() == 0 &&
							pnbRequestListiOS.size() == 0 && pnbRequestListiPad.size() == 0)
					{
						log.info("No More Requests to Send Out.");
						int res = pnsDao.updatePNBPromoStatus(PNSConstant.PNB_PROMO_STATUS_COMPLETED, pendingPromotion.getMsgCode());
						log.info("Status Updated Result :: "+res);
					}
				}
				else
				{
					log.info(gson.toJson(pendingPromotion));
				}
			}
			else
			{
				log.info("There is no Pending Promotion to Push out.");
			}
			
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			log.info("Exception in PNSBroadcastTask :: "+ex);
		}
		
	}
	/*
	@Override
	public void setMuleContext(MuleContext arg0)
	{
		muleContext = arg0;
	}
	*/
	
}
