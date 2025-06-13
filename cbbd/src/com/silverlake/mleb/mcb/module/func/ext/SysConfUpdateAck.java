package com.silverlake.mleb.mcb.module.func.ext;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.mcb.bean.ObHeaderResponse;
import com.silverlake.mleb.mcb.bean.ObSysConfRequest;
import com.silverlake.mleb.mcb.bean.ObSysConfResponse;
import com.silverlake.mleb.mcb.bean.ObUserContext;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.entity.dao.ConfDao;
import com.silverlake.mleb.mcb.entity.dao.GeneralCodeDAO;
import com.silverlake.mleb.mcb.module.common.FuncServices;
import com.silverlake.mleb.mcb.util.MessageManager;
import com.silverlake.mleb.mcb.util.PropertiesManager;






@Service
public class SysConfUpdateAck extends FuncServices  
{

	private static Logger log = LogManager.getLogger(SysConfUpdateAck.class);
	private MessageManager msgPro = new MessageManager();
	private PropertiesManager pro = new PropertiesManager();
	@Autowired ApplicationContext appContext;
	
	@Autowired
	ConfDao confDao;
	
	@Autowired
	GeneralCodeDAO gnDao;
	
	public MICBResponseBean process(MICBRequestBean arg0) {
		// TODO Auto-generated method stub
		
		
	
		MICBResponseBean response = new MICBResponseBean();
		ObSysConfResponse loginResponse = new ObSysConfResponse();
		loginResponse.setObHeader(new ObHeaderResponse());
		loginResponse.setUserContext(new ObUserContext());
		
		try {

			ObSysConfRequest requestData = (ObSysConfRequest) arg0.getBDObject();			
			
			if(requestData.getMaxDevBinding()!=null && !requestData.getMaxDevBinding().matches("\\d+"))
			{
				loginResponse.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT_PARAM);
			}
			else if(requestData.getSessionExpiration()!=null && !requestData.getSessionExpiration().matches("\\d+"))
			{
				loginResponse.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT_PARAM);
			}
			else
			{
				
				int maxDev = Integer.parseInt(requestData.getMaxDevBinding());
				int session = Integer.parseInt(requestData.getSessionExpiration());
				
				
				confDao.updateSessionExpiration(session);
				gnDao.updateMaxDevice(String.valueOf(maxDev));
				
				
				loginResponse.setSessionExpiration(requestData.getSessionExpiration());
				loginResponse.setMaxDevBinding(requestData.getMaxDevBinding());
				loginResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
			}
			
			
			


		} catch (Exception e) {
			// TODO Auto-generated catch block
			//"ERROR.MIB.9999999"
			

			log.info(this.getClass().toString(), e);
			loginResponse = new ObSysConfResponse();
			loginResponse.setObHeader(new ObHeaderResponse());
			loginResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			loginResponse.getObHeader().setStatusMessage(e.getMessage());
			
		}
		
		response.setBDObject(loginResponse);
		
		return response;
	}


	

	

	
	
	
}