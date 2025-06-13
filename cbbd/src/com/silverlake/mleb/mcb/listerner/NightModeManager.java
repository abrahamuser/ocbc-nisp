package com.silverlake.mleb.mcb.listerner;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.Logger;import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.mcb.bean.ObAuthenticationResponse;
import com.silverlake.mleb.mcb.bean.ObHeaderResponse;
import com.silverlake.mleb.mcb.bean.ObIGBPayDetailRequestBean;
import com.silverlake.mleb.mcb.bean.ObRequest;
import com.silverlake.mleb.mcb.bean.ObResponse;
import com.silverlake.mleb.mcb.bean.ObThirdPartyTransPayDetailRequestBean;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.entity.dao.MLEBMIBDAO;
import com.silverlake.mleb.mcb.module.common.MiBServices;
import com.silverlake.mleb.mcb.util.PropertiesManager;

@Service("NightModeManager")
public class NightModeManager {
	
	
	private static Logger log = LogManager.getLogger(NightModeManager.class);
	private static PropertiesManager pro = new PropertiesManager();
	
	@Autowired
	MLEBMIBDAO dao;
	


	public Object onCall(MICBRequestBean arg0) throws Exception {
		// TODO Auto-generated method stub
		
		Object objBean = arg0;
		
		
		if(null!=objBean && objBean instanceof MICBRequestBean)
		{
			
			MICBRequestBean micbRequestBean = (MICBRequestBean) objBean;
			ObAuthenticationResponse obResponse = new ObAuthenticationResponse();
			obResponse.setObHeader(new ObHeaderResponse());
			
			String requestInfo_serviceID = MiBConstant.FUNC_REQUEST_INFO;
			
			if(checkNightModeService(micbRequestBean.getHeaderBean().getServiceID()))
			{
				
				
				
				
				if(micbRequestBean.getBDObject() instanceof ObRequest)
				{
				
				
					ObRequest obRequest = (ObRequest) micbRequestBean.getBDObject();
					if(null!=obRequest.getObUser())
					{
						String endTime = obRequest.getObUser().getnModeEndTime();
						String startTime =  obRequest.getObUser().getnModeStartTime();
						
						Date currentDate = new Date();
						SimpleDateFormat sft = new SimpleDateFormat("HHmm");
						String currentTime = sft.format(currentDate);
						
						if(currentTime.compareTo(startTime)<0 && startTime.compareTo(endTime)>0)
						{
							startTime = "0000";
						}
						else if(startTime.compareTo(endTime)>0)
						{
							endTime = "2359";
						}
						
						
						if(currentTime.compareTo(endTime)<=0 && currentTime.compareTo(startTime)>=0)
						{
							
							
							
							
							if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase("FUNC_HLB_THIRDACCTRANSPAYCONF") || micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase("FUNC_HLB_THIRDACCTRANSPAYACKGT"))
							{
								
								//ObThirdPartyTransPayDetailRequestBean
								ObThirdPartyTransPayDetailRequestBean requestSsBean =  (ObThirdPartyTransPayDetailRequestBean) micbRequestBean.getBDObject();
								if(requestSsBean.getObFavAccResult().getProductTypeCode().equalsIgnoreCase("L"))
								{
									obResponse.getObHeader().setStatusCode(MiBConstant.MIB_SERVER_UNDER_NIGHTMODE);
									MICBResponseBean responseBean = new MICBResponseBean();
									responseBean.setBDObject(obResponse);
									return responseBean;
								}
								else
								{
									return objBean;
								}

							}
							
						

							obResponse.getObHeader().setStatusCode(MiBConstant.MIB_SERVER_UNDER_NIGHTMODE);
							MICBResponseBean responseBean = new MICBResponseBean();
							responseBean.setBDObject(obResponse);
							return responseBean;
						}
						
					}
					
					
	
				}
			}
			else
			{
				return objBean;
			}
			
			
		}
		
		
		
		
		return objBean;
	}	
	
	
	
	public boolean checkNightModeService(String serviceid)
	{
		String nightModeBlockService = pro.getProperty("nightModeServices");
		String nightModes[] = null==nightModeBlockService?new String[0]:nightModeBlockService.split(",");
		
		
		for(String night:nightModes)
		{
			if(serviceid.equalsIgnoreCase(night))
			{
				return true;
			}
		}
		
		
		return false;
	}


}

