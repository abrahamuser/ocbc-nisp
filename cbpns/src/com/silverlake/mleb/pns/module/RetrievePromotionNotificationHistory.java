package com.silverlake.mleb.pns.module;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
//import org.apache.log4j.Logger;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.silverlake.hlb.mib.bean.ObHeaderResponse;
import com.silverlake.hlb.mib.bean.ObUserContext;
import com.silverlake.hlb.mib.entity.HlbRedirectURL;
import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.pex.constant.PExConstant;
import com.silverlake.mleb.pex.entity.dao.MLEBDAO;
import com.silverlake.mleb.pex.entity.dao.PEXDAO;
import com.silverlake.mleb.pex.module.common.FuncServices;
import com.silverlake.mleb.pex.util.PropertiesManager;
import com.silverlake.mleb.pns.bean.ObPNBResponseBean;
import com.silverlake.mleb.pns.bean.PushNotifBroadcastBean;
import com.silverlake.mleb.pns.constant.PNSConstant;
import com.silverlake.mleb.pns.entity.PnbPromotion;
import com.silverlake.mleb.pns.entity.PnbResponse;
import com.silverlake.mleb.pns.entity.dao.PnsDao;
import com.silverlake.mleb.pns.module.services.PNSServices;

@Service
public class RetrievePromotionNotificationHistory extends FuncServices  
{

	//private static Logger log = Logger.getLogger(RetrievePromotionNotificationHistory.class);
	private static Logger log = LogManager.getLogger(RetrievePromotionNotificationHistory.class);
	private PropertiesManager property = new PropertiesManager();
	
	@Autowired
	MLEBDAO dao;
	
	@Autowired PnsDao pnsDao;
	
	
	
	
	@Override
	public MICBResponseBean process(MICBRequestBean arg0)
	{
		MICBResponseBean response = new MICBResponseBean();
		ObPNBResponseBean pnbResponse = new ObPNBResponseBean();
		pnbResponse.setObHeader(new ObHeaderResponse());
		pnbResponse.setUserContext(new ObUserContext());
		
		try
		{
			log.info("------------------------ 1 -------------------------");
			Gson gson = new Gson();
			PNSServices pnsServices = new PNSServices(dao);
			SimpleDateFormat refFormat = new SimpleDateFormat(PExConstant.COMMON_DATETIME_FORMAT_HIST);
			
			if(arg0 != null && arg0.getDeviceBean() != null && arg0.getDeviceBean().getDeviceId() != null)
			{log.info("------------------------ 2 -------------------------");
				//List<HlbPnbResponse> pnbResponseList = pnsServices.retrievePromotionNotifHistory(arg0.getDeviceBean().getDeviceId(), 10);
				List<PushNotifBroadcastBean> pnbbList = pnsDao.retrievePromotionNotifHistory(arg0.getDeviceBean().getDeviceId(), 10);
				log.info("------------------------ 8 -------------------------");
				pnbResponse.setPromoNotificationHist(pnbbList);
				
				pnbResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COMMON_SUCCESS);
			}
			else
			{
				pnbResponse = new ObPNBResponseBean();
				pnbResponse.setObHeader(new ObHeaderResponse());
				pnbResponse.getObHeader().setStatusCode(PNSConstant.PNS_INVALID_REQUEST_CODE);
				pnbResponse.getObHeader().setStatusMessage(PNSConstant.PNS_INVALID_REQUEST_MESG);
			}
			
		}
		catch(Exception e)
		{
			log.error(this.getClass().toString(), e);
			pnbResponse = new ObPNBResponseBean();
			pnbResponse.setObHeader(new ObHeaderResponse());
			pnbResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COMMON_ERROR);
			pnbResponse.getObHeader().setStatusMessage(e.getMessage());
		}
		
		response.setBDObject(pnbResponse);
		return response;
	}
	
	

}
