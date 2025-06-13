package com.silverlake.mleb.pns.module.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
//import org.apache.log4j.Logger;
import org.apache.logging.log4j.Logger;
import com.google.gson.Gson;
import com.silverlake.hlb.mib.entity.HlbDeviceProfile;
import com.silverlake.mleb.pex.constant.PExConstant;
import com.silverlake.mleb.pex.entity.dao.MLEBDAO;
import com.silverlake.mleb.pex.util.MessageManager;
import com.silverlake.mleb.pex.util.PropertiesManager;
import com.silverlake.mleb.pns.bean.PushNotifBroadcastBean;
import com.silverlake.mleb.pns.constant.PNSConstant;
import com.silverlake.mleb.pns.entity.PnbConf;
import com.silverlake.mleb.pns.entity.PnbPromotion;
import com.silverlake.mleb.pns.entity.PnbRequest;
import com.silverlake.mleb.pns.entity.PnbResponse;

import javapns.communication.ConnectionToAppleServer;

public class PNSServices
{
	MLEBDAO dao;
	//private static Logger log = Logger.getLogger(PNSServices.class);
	private static Logger log = LogManager.getLogger(PNSServices.class);
	private PropertiesManager property = new PropertiesManager();
	private MessageManager msgPro = new MessageManager();
	
	public PNSServices(MLEBDAO dao)
	{
		this.dao =  dao;
	}
	
	public PnbConf getPNBConf()
	{
		String sql = "FROM HlbPnbConf WHERE status = '"+PExConstant.PEX_CONF_STATUS_ACTIVE+"'";
		
		List<PnbConf> pnbConfs = dao.selectQuery(sql);
		
		
		if(pnbConfs.size()>0)
		{
			return pnbConfs.get(0);
		}
		
		return null;
	}
	
	public PnbPromotion retrieveActivePromotions()
	{
		//String sql = "FROM HlbPnbPromotion where status = '"+PNSConstant.PNB_PROMO_STATUS_PENDING+"'";
		Date curDate = new Date();
		SimpleDateFormat refFormat = new SimpleDateFormat(PExConstant.COMMON_DATETIME_FORMAT);
		String sql = "FROM HlbPnbPromotion where status = '"+PNSConstant.PNB_PROMO_STATUS_PENDING+"' AND broadcastDt <= timestamp('"+refFormat.format(curDate)+"')";
		
		List<PnbPromotion> promotionPendingList = dao.selectQuery(sql);
		if(promotionPendingList.size() > 0)
		{
			return promotionPendingList.get(0);
		}
		else
		{
			sql = "FROM HlbPnbPromotion where (status = '"+PNSConstant.PNB_PROMO_STATUS_COMPLETED+"' OR status='"+PNSConstant.PNB_PROMO_STATUS_CANCELLED+"') AND flag='"+PNSConstant.PNB_PROMO_FLAG_RETRY+"'";
			
			List<PnbPromotion> promotionRetryList = dao.selectQuery(sql);
			
			if(promotionRetryList != null && promotionRetryList.size() > 0)
			{
				return promotionRetryList.get(0);
			}
			else
			{
				return null;
			}
		}
	}
	
	public List<PnbRequest> retrievePendingRequestList(String deviceType, int rowCount, String msgCode)
	{
		List<PnbRequest> pnbRequestList = new ArrayList<PnbRequest>();
		
		/*String sql = "FROM HlbPnbRequest where deviceType='"+deviceType.toLowerCase()+"' and msgCode='"+msgCode+"' fetch first "+rowCount+" rows only";
		pnbRequestList = dao.selectQuery(sql);*/
		
		String sql = "FROM HlbPnbRequest where deviceType='"+deviceType+"' and msgCode='"+msgCode+"'";
		
		Query query = dao.getEntityManager().createQuery(sql);
		query.setMaxResults(rowCount);
		pnbRequestList = query.getResultList();
		log.info("LUCKY CAME HERE.");
		return pnbRequestList;
	}
	
	public PnbPromotion retrieveActivePromotionsToInsertToRequestTable()
	{
		/**
		 * We will start Inserting records to Request Tables 1 hour before the schedule time to push.
		 * If insertReqStatus=12 means all 12 months records has been inserted to Request Tables.
		 */
		
		Date curDate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(curDate);
		calendar.add(Calendar.HOUR, 1);
		
		curDate = calendar.getTime();
		
		SimpleDateFormat refFormat = new SimpleDateFormat(PExConstant.COMMON_DATETIME_FORMAT);
		
		String sql = "FROM HlbPnbPromotion where status = '"+PNSConstant.PNB_PROMO_STATUS_PENDING+"' AND broadcastDt <= timestamp('"+refFormat.format(curDate)+"')";
		log.info("SQL :: "+sql);
		List<PnbPromotion> promotionPendingList = dao.selectQuery(sql);
		log.info(promotionPendingList);
		if(promotionPendingList!=null && promotionPendingList.size() > 0)
		{
			PnbPromotion pnbPromo = null;
			for(PnbPromotion pnbPromotion : promotionPendingList)
			{
				if(pnbPromotion.getInsertReqStatus() == null || !pnbPromotion.getInsertReqStatus().equals("12"))
				{
					return  pnbPromotion;
				}
			}
			
		}
		
		log.info("No Pending Promotion to Insert. Look for promotions set to retry.");
		sql = "FROM HlbPnbPromotion where (status = '"+PNSConstant.PNB_PROMO_STATUS_COMPLETED+"' OR status='"+PNSConstant.PNB_PROMO_STATUS_CANCELLED+"') AND flag='"+PNSConstant.PNB_PROMO_FLAG_RETRY+"'";
		log.info("SQL :: "+sql);
		List<PnbPromotion> promotionRetryList = dao.selectQuery(sql);
		log.info(promotionRetryList);
		if(promotionRetryList != null && promotionRetryList.size() > 0)
		{
			PnbPromotion pnbPromo = null;
			for(PnbPromotion pnbPromotion : promotionRetryList)
			{
				if(pnbPromotion.getInsertReqStatus() == null || !pnbPromotion.getInsertReqStatus().equals("12"))
				{
					pnbPromo = promotionRetryList.get(0);
				}
			}
			return pnbPromo;
		}
		else
		{
			return null;
		}
		
	}
	
	public List<HlbDeviceProfile> retrieveDeviceListByMonth(String month)
	{
		String dummyDate = "1000-"+month+"-01 00:00:00";
		log.info("dummyDate :: "+dummyDate);
		String sql = "FROM HlbDeviceProfile where month(dateTagged) = month('"+dummyDate+"') AND (deviceType='"+PNSConstant.OS_TYPE_ANDROID+"' OR deviceType='"+PNSConstant.OS_TYPE_IOS+"' OR deviceType='"+PNSConstant.OS_TYPE_IPAD+"') AND isPushNotifEnabled='Y' AND pnsToken != null";
		
		log.info("SQL :: "+sql);
		
		List<HlbDeviceProfile> monthDeviceProfiles = dao.selectQuery(sql);
		return monthDeviceProfiles;
	}
	
	public List<PnbResponse> retrieveTranxToRetry(String msgCode, String month)
	{
		List<PnbResponse> pnbResponseList = null;
		String dummyDate = "1000-"+month+"-01 00:00:00";
		log.info("dummyDate :: "+dummyDate);
		String sql = "FROM HlbPnbResponse where month(deviceTaggedDate) = month('"+dummyDate+"') AND status='"+PNSConstant.PNB_TRX_STATUS_FAILED+"'";
		
		log.info("SQL :: "+sql);
		pnbResponseList =  dao.selectQuery(sql);
		
		return pnbResponseList;
	}
	
	public List<PnbResponse> retrievePromotionNotifHistory(String deviceId, int maxRows)
	{
		try
		{
			System.setProperty("db2.jcc.charsetDecoderEncoder", "3");
			Date startDate = new Date();
			String sql = "FROM HlbPnbResponse where deviceId='"+deviceId+"' order by notifSendDate desc";
			log.info("SQL :: "+sql);
			/*Query query = dao.getEntityManager().createQuery(sql);
			query.setMaxResults(maxRows);
			List<HlbPnbResponse> respList =  query.getResultList();*/
			List<PnbResponse> respList = dao.selectQuery(sql);
			Date endDate = new Date();
			log.info("TOTAL TIME TAKEN :: "+(endDate.getTime()-startDate.getTime()));
			
			
			if(respList != null)
			{
				log.info("");
				for(PnbResponse  pnbResp : respList)
				{
					log.info(pnbResp.getDeviceId()+" :: "+pnbResp.getPnbPromo().getRowId()+" :: "+pnbResp.getPnbPromo().getMsgCode());
				}
			}
			
			return respList;
		}
		catch(Exception e)
		{
			log.info("Exception :: "+e);
			e.printStackTrace();
			return null;
		}
		
		
		
	}
	
	public PnbPromotion retrievePnbPromo(String msgCode)
	{
		PnbPromotion pnbPromo = null;

		String sql = "FROM HlbPnbPromotion where msgCode='"+msgCode+"'";
		log.info("SQL :: "+sql);
		
		List<PnbPromotion> respList = dao.selectQuery(sql);
		if(respList != null && respList.size() == 1)
		{
			pnbPromo = respList.get(0);
		}
		
		return pnbPromo;
	}
}
