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
import com.silverlake.hlb.mib.entity.HlbDeviceProfile;
import com.silverlake.mleb.pex.entity.dao.MLEBDAO;
import com.silverlake.mleb.pex.entity.dao.PEXDAO;
import com.silverlake.mleb.pex.task.TaskRenewSession;
import com.silverlake.mleb.pns.constant.PNSConstant;
import com.silverlake.mleb.pns.entity.PnbConf;
import com.silverlake.mleb.pns.entity.PnbPromotion;
import com.silverlake.mleb.pns.entity.PnbRequest;
import com.silverlake.mleb.pns.entity.PnbResponse;
import com.silverlake.mleb.pns.module.services.PNSServices;

@Service
public class PNBPromotionInsertToRequestTask 
{

	//private static Logger log = Logger.getLogger(PNBPromotionInsertToRequestTask.class);
	private static Logger log = LogManager.getLogger(PNBPromotionInsertToRequestTask.class);
	//private PropertiesManager property = new PropertiesManager();
	private static final String KEYLOCK = "LOCK_PUSH_BROADCAST_INSERT_TO_REQUEST_TASK";
	
	
	@Autowired TaskRenewSession renewSession;
	@Autowired MLEBDAO dao;
	
	@Autowired PEXDAO pexDao;
	
	//MuleContext muleContext;
	
	//@Scheduled(fixedDelay=60000) //Run ever 1 minutes
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
		log.info("Push Notification Broadcast Promotion insert to PND_REQUEST Table Task Started.");
	}
	
	public void taskProcess()
	{
		Gson gson = new Gson();
		log.info("Insert to PNB_REQUEST Table Started.");
		try
		{
			PNSServices pnsServices = new PNSServices(dao);
			
			PnbConf pnbConf = pnsServices.getPNBConf();
			
			if(pnbConf != null && pnbConf.getIsPushEnabled() != null && pnbConf.getIsPushEnabled().equalsIgnoreCase(PNSConstant.PNB_PUSH_ENABLE_FLAG_YES))
			{
				PnbPromotion pnbPromotion = pnsServices.retrieveActivePromotionsToInsertToRequestTable();
				
				if(pnbPromotion != null)
				{
					log.info("There is a PENDING promotion to insert into Request Table with MsgCode :: "+pnbPromotion.getMsgCode()+" :: "+pnbPromotion.getStatus()+" :: "+pnbPromotion.getFlag());
					if(pnbPromotion.getStatus().equalsIgnoreCase(PNSConstant.PNB_PROMO_STATUS_PENDING) || pnbPromotion.getStatus().equalsIgnoreCase(PNSConstant.PNB_PROMO_STATUS_CANCELLED))
					{
						log.info("Inserting a Pending/Cancelled Promotion :: "+pnbPromotion.getStatus()+" :: "+pnbPromotion.getInsertReqStatus());
						log.info(gson.toJson(pnbPromotion));
						List<Object> pnbRequestList = new ArrayList<Object>();
						
						String month = "";
						
						if(pnbPromotion.getInsertReqStatus() == null || pnbPromotion.getInsertReqStatus().equals(""))
						{
							month = "01";
						}
						else
						{
							int mon = Integer.parseInt( pnbPromotion.getInsertReqStatus()) + 1;
							month = ""+mon;
						}
						
						if(month.length() == 1)
						{
							month = "0"+month;
						}
						
						List<HlbDeviceProfile> deviceProfiles = pnsServices.retrieveDeviceListByMonth(month);
						
						if(deviceProfiles != null && deviceProfiles.size() > 0)
						{
							log.info("Total Device Profiles for the month ::"+month+" is :: "+deviceProfiles.size());
							for(int i=0; i<deviceProfiles.size(); i++)
							{
								HlbDeviceProfile deviceProfile = deviceProfiles.get(i);
								PnbRequest pnbRequest = new PnbRequest();
								
								pnbRequest.setCif(deviceProfile.getHlbCustomerProfile().getCif());
								pnbRequest.setDeviceId(deviceProfile.getDeviceId());
								pnbRequest.setDeviceModel(deviceProfile.getDeviceModel());
								pnbRequest.setDeviceToken(deviceProfile.getPnsToken());
								pnbRequest.setDeviceType(deviceProfile.getDeviceType());
								pnbRequest.setMsgCode(pnbPromotion.getMsgCode());
								pnbRequest.setNotifSendDate(new Date());
								pnbRequest.setRetryCount(0);
								pnbRequest.setStatus(PNSConstant.PNB_TRX_STATUS_PENDING);
								pnbRequest.setDeviceTaggedDate(deviceProfile.getDateTagged());
								pnbRequest.setResponseRowId(0);
								
								pnbRequestList.add(pnbRequest);
							}
							log.info("Inserting below records for the Month :: "+month);
							//log.info(gson.toJson(pnbRequestList));
							
							int successCount = dao.insertMultiEntity(pnbRequestList, log);
							log.info("TOTAL INSERTED = "+pnbRequestList.size()+" :: TOTAL SUCCESS = "+successCount);
							
							int mon = Integer.parseInt(month);
							pnbPromotion.setInsertReqStatus(""+mon);
							
							dao.updateEntity(pnbPromotion);
							log.info("Updated InsertedReqStatus for the Promotion from "+month+" TO "+mon);
						}
						else
						{
							log.info("No Device Profiles Found for the month :: "+month+"  to Insert to Request Table.");
							
							int mon = Integer.parseInt(month);
							pnbPromotion.setInsertReqStatus(""+mon);
							
							dao.updateEntity(pnbPromotion);
							
							log.info("Updated InsertedReqStatus for the Promotion TO "+mon);
						}
						
						
					}
					else if(pnbPromotion.getStatus().equalsIgnoreCase(PNSConstant.PNB_PROMO_STATUS_COMPLETED))
					{
						log.info("Inserting a Retry messages for Completed Promotion :: "+pnbPromotion.getMsgCode()+" :: "+pnbPromotion.getInsertReqStatus());
						
						String month = "";
						
						if(pnbPromotion.getInsertReqStatus() == null || pnbPromotion.getInsertReqStatus().equals(""))
						{
							month = "01";
						}
						else
						{
							int mon = Integer.parseInt( pnbPromotion.getInsertReqStatus()) + 1;
							month = ""+mon;
						}
						
						if(month.length() == 1)
						{
							month = "0"+month;
						}
						
						List<PnbResponse> pnbResponseList = pnsServices.retrieveTranxToRetry(pnbPromotion.getMsgCode(), month);
						
						if(pnbResponseList != null && pnbResponseList.size() > 0)
						{
							log.info("Total Failed Transaction for the month ::"+month+" is :: "+pnbResponseList.size());
							List<Object> pnbRequestList = new ArrayList<Object>();
							
							for(int i=0; i<pnbResponseList.size(); i++)
							{
								PnbResponse pnbResponse = pnbResponseList.get(i);
								PnbRequest pnbRequest = new PnbRequest();
								
								pnbRequest.setCif(pnbResponse.getCif());
								pnbRequest.setDeviceId(pnbResponse.getDeviceId());
								pnbRequest.setDeviceModel(pnbResponse.getDeviceModel());
								pnbRequest.setDeviceToken(pnbResponse.getDeviceToken());
								pnbRequest.setDeviceType(pnbResponse.getDeviceType());
								pnbRequest.setMsgCode(pnbPromotion.getMsgCode());
								pnbRequest.setNotifSendDate(new Date());
								pnbRequest.setRetryCount((pnbPromotion.getRetryCount() + 1));
								pnbRequest.setStatus(PNSConstant.PNB_TRX_STATUS_RETRY);
								pnbRequest.setResponseRowId(pnbResponse.getRowId());
								pnbRequest.setDeviceTaggedDate(pnbResponse.getDeviceTaggedDate()==null?null:pnbResponse.getDeviceTaggedDate());
								
								pnbRequestList.add(pnbRequest);
							}
							log.info("Inserting below records for the Month :: "+month);
							//log.info(gson.toJson(pnbRequestList));
							
							int successCount = dao.insertMultiEntity(pnbRequestList, log);
							log.info("TOTAL INSERTED = "+pnbRequestList.size()+" :: TOTAL SUCCESS = "+successCount);
							
							
							int mon = Integer.parseInt(month);
							pnbPromotion.setInsertReqStatus(""+mon);
							
							dao.updateEntity(pnbPromotion);
							log.info("Updated InsertedReqStatus for the Retry Promotion TO "+mon);
						}
						else
						{
							log.info("No Failed Transaction Found for the month :: "+month+"  to Insert to Request Table.");
							
							int mon = Integer.parseInt(month);
							pnbPromotion.setInsertReqStatus(""+mon);
							
							dao.updateEntity(pnbPromotion);
							
							log.info("Updated InsertedReqStatus for the Promotion TO "+mon);
						}
					}
					else
					{
						log.info("Not Inpmelented anything for this status :: "+pnbPromotion.getStatus());
					}
			
				}
				else
				{
					log.info("There is no Promotion to Insert to Request Table.");
				}
			}
			else
			{
				log.info("Sending out Push is Disabled.");
			}
			
			
		}
		catch(Exception e)
		{
			log.info("Exception in processing :: "+e);
			e.printStackTrace();
		}
		
		log.info("Insert to PNB_REQUEST Table End.");
	}
	
	
	

}
