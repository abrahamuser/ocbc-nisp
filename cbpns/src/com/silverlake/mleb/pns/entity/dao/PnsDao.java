package com.silverlake.mleb.pns.entity.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silverlake.hlb.mib.entity.HlbCustomerProfile;
import com.silverlake.mleb.pex.constant.PExConstant;
import com.silverlake.mleb.pex.entity.dao.DAO;
import com.silverlake.mleb.pns.bean.PushNotifBroadcastBean;
import com.silverlake.mleb.pns.constant.PNSConstant;
import com.silverlake.mleb.pns.entity.PnbPromotion;
import com.silverlake.mleb.pns.entity.PnbRequest;
import com.silverlake.mleb.pns.entity.PnbResponse;
import com.silverlake.mleb.pns.entity.PnsApps;
import com.silverlake.mleb.pns.entity.ProviderProxyIp;

import javapns.communication.ConnectionToAppleServer;


@Service
@Repository
@Transactional
public class PnsDao extends DAO
{
	
	//private static Logger log = Logger.getLogger(PnsDao.class);
	private static Logger log = LogManager.getLogger(PnsDao.class);

	@PersistenceContext(unitName="db_mleb")
	EntityManager emx ;

	@Override
	public EntityManager getEntityManager() {
		return emx;
	}
	
	@Transactional
	public List<ProviderProxyIp> retrieveProviderIPList(String os)
	{
		String sql = "FROM HlbProviderProxyIp WHERE os = :oskey and status = :statuskey";
		Hashtable<String, String> params = new Hashtable<String, String>();
		params.put("oskey", os);
		params.put("statuskey", PNSConstant.PROXY_IP_STATUS_ACTIVE);
		log.info("SQL :: "+sql);
		List<ProviderProxyIp> rs = selectQueryParam(sql, params);
		
		return rs;
	}
	
	public int updatePNBPromoStatus(String status, String msgCode)
	{
		String sql = "FROM HlbPnbPromotion where msgCode = :promoCode";
		Hashtable<String, String> params = new Hashtable<String, String>();
		params.put("promoCode", msgCode);
		
		List<PnbPromotion> promoList =  selectQueryParam(sql, params);
		
		if(promoList.size() == 1)
		{
			PnbPromotion pnbPromo = promoList.get(0);
			
			pnbPromo.setStatus(status);
			
			if(status.equalsIgnoreCase(PNSConstant.PNB_PROMO_STATUS_COMPLETED))
			{
				pnbPromo.setCompletedDate(new Date());
				pnbPromo.setFlag("");
				pnbPromo.setInsertReqStatus("");
			}
			
			updateEntity(pnbPromo);
			return 1;
		}
		else
		{
			return 0;
		}
		
		
	}
	
	
	public int deleteMultiEntityForBroadcast(List<PnbRequest> objs)
	{
		Date startDate = new Date();
		log.info("NAGEN DELETE STAET TIME :: "+startDate);
		
		String deleteSQL = "DELETE FROM HlbPnbRequest WHERE ";
		EntityManager em  = getEntityManager();
		for(PnbRequest obj :objs)
		{
			deleteSQL = deleteSQL + "rowId = '"+obj.getRowId()+"' OR ";
			
		}
		
		deleteSQL= deleteSQL.substring(0, deleteSQL.length()-3);
		//log.info("Delete Query :: "+deleteSQL);
		em.createQuery(deleteSQL).executeUpdate();
		em.flush();
		Date endDate = new Date();
		log.info("NAGEN DELETE END TIME :: "+endDate);
		log.info("TOTAL TIME TO DELETE "+objs.size()+" ROWS is "+((endDate.getTime() - startDate.getTime())/1000)+" Seconds");
		return objs.size();
	}
	
	
	public int updatePnbResponseMultiEntity(List<PnbResponse> pnbRespList)
	{
		Date startDate = new Date();
		log.info("NAGEN INSERT STAET TIME :: "+startDate);
		
		
		
		
		EntityManager em  = getEntityManager();
		int count = 0;
		for(PnbResponse pnbResp :pnbRespList)
		{
			String updateSQL = "UPDATE HlbPnbResponse SET status='"+pnbResp.getStatus()+"', retryCount='"+pnbResp.getRetryCount()+"' WHERE rowId="+pnbResp.getRowId();
			em.createQuery(updateSQL).executeUpdate();
			count++;
		}
		
		Date endDate = new Date();
		log.info("NAGEN INSERT END TIME :: "+endDate);
		log.info("TOTAL TIME TO INSERT "+pnbRespList.size()+" ROWS is "+((endDate.getTime() - startDate.getTime())/1000)+" Seconds");
		return count;
	}
	
	
	public List<PushNotifBroadcastBean> retrievePromotionNotifHistory(String deviceId, int maxRows)
	{
		log.info("------------------------ 3 -------------------------");
		EntityManager em  = getEntityManager();
		try
		{
			Date startDate = new Date();
			
			log.info("------------------------ 4 -------------------------"+startDate);
			String sql = "FROM HlbPnbResponse where ";
			
			
			if(deviceId.indexOf("-IMAC-")>0 || deviceId.indexOf("_iPad") > 0) {
				
				if(deviceId.indexOf("-IMAC-") > 0) {
					sql = sql + "(deviceId='"+deviceId+"' or deviceId='"+deviceId.substring(0, deviceId.indexOf("-IMAC-"))+"')";
				}
				else if(deviceId.indexOf("_iPad") > 0) {
					sql = sql + "(deviceId='"+deviceId+"' or deviceId='"+deviceId.substring(0, deviceId.indexOf("_iPad"))+"')";
				}
			}
			
			else {
				sql = sql + " deviceId='"+deviceId+"'";
			}
			
			sql = sql + "  order by notifSendDate desc";
			//List<HlbPnbResponse> respList = em.createQuery(sql).getResultList();
			
			Query query = em.createQuery(sql);
			query.setMaxResults(maxRows);
			List<PnbResponse> respList =  query.getResultList();
			log.info("Response List :: " + respList.get(0));
			
			log.info("------------------------ 5 -------------------------"+respList);
			Date endDate = new Date();
			log.info("TOTAL TIME TAKEN :: "+(endDate.getTime()-startDate.getTime()));
			
			SimpleDateFormat refFormat = new SimpleDateFormat(PExConstant.COMMON_DATETIME_FORMAT_HIST);
			List<PushNotifBroadcastBean> pnbbList = new ArrayList<PushNotifBroadcastBean>();
			if(respList != null)
			{
				log.info("------------------------ 6 -------------------------");
				
				for(PnbResponse  pnbResp : respList)
				{	
					log.info("pnbResp :: " + pnbResp.getPnbPromo().getMsgCode());
					PushNotifBroadcastBean pnbb = new PushNotifBroadcastBean();
					pnbb.setPromoCode(pnbResp.getPnbPromo().getMsgCode());
					pnbb.setCreationDate(refFormat.format(pnbResp.getNotifSendDate()));
					pnbb.setMsgContent(pnbResp.getPnbPromo().getNotifMsg());
					pnbb.setPromoUrl(pnbResp.getPnbPromo().getUrlLink() == null? "":pnbResp.getPnbPromo().getUrlLink());
					pnbbList.add(pnbb);
				}
				log.info("------------------------ 7 -------------------------");
			}
			
			return pnbbList;
		}
		catch(Exception e)
		{
			log.info("Exception :: "+e);
			e.printStackTrace();
			return null;
		}
		finally
		{
			em.close();
		}
		
		
	}
	
	
	public PnsApps getAppPushData(String appId, String password)
	{
		PnsApps clientApp = new PnsApps();
		
		clientApp = (PnsApps) findByID(PnsApps.class, appId);
		
		String hexpass = DigestUtils.sha256Hex(password).toUpperCase();
		if(null!=clientApp && null!=clientApp.getAppPass() && clientApp.getAppPass().equalsIgnoreCase(hexpass))
		{
			return clientApp;
		}
		
		return null;
	}

}
