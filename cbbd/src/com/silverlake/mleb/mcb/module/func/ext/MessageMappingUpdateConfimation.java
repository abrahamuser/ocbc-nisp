package com.silverlake.mleb.mcb.module.func.ext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

//import com.silverlake.hlb.mib.entity.dao.MLEBDAO;
import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.mcb.bean.ObHeaderResponse;
import com.silverlake.mleb.mcb.bean.ObMessageMappingRequest;
import com.silverlake.mleb.mcb.bean.ObMessageMappingResponse;
import com.silverlake.mleb.mcb.bean.ObUserContext;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.entity.MessagePropertiesI18n;
import com.silverlake.mleb.mcb.entity.dao.ConfDao;
import com.silverlake.mleb.mcb.entity.dao.MessagePropertiesDAO;
import com.silverlake.mleb.mcb.module.common.FuncServices;
import com.silverlake.mleb.mcb.util.MessageManager;
import com.silverlake.mleb.mcb.util.PropertiesManager;






@Service
public class MessageMappingUpdateConfimation extends FuncServices  
{

	private static Logger log = LogManager.getLogger(MessageMappingUpdateConfimation.class);
	private MessageManager msgPro = new MessageManager();
	private PropertiesManager pro = new PropertiesManager();
	@Autowired ApplicationContext appContext;
	
	@Autowired
	ConfDao confDao;
	
	@Autowired
	MessagePropertiesDAO msgDAO;
	
	public MICBResponseBean process(MICBRequestBean arg0) {
		// TODO Auto-generated method stub
		
		
	
		MICBResponseBean response = new MICBResponseBean();
		ObMessageMappingResponse loginResponse = new ObMessageMappingResponse();
		loginResponse.setObHeader(new ObHeaderResponse());
		loginResponse.setUserContext(new ObUserContext());
		
		try {

			ObMessageMappingRequest requestData = (ObMessageMappingRequest) arg0.getBDObject();	
			
			
			loginResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
			if(requestData.getAction().equalsIgnoreCase(MiBConstant.MSG_MAPPING_ACTION_ADD))
			{
				MessagePropertiesI18n rs = msgDAO.findByMsgCode(requestData.getMsgMapping().getErrorCode(), requestData.getMsgMapping().getLocale());
				if(null!=rs)
				{
					loginResponse.getObHeader().setStatusCode(MiBConstant.MIB_MSGCODE_EXISTED);
				}
			
			}
			else if(requestData.getAction().equalsIgnoreCase(MiBConstant.MSG_MAPPING_ACTION_UPDATE))
			{
				MessagePropertiesI18n rs = msgDAO.findByRecordId(requestData.getMsgMapping().getRecordID());
				if(null==rs)
				{
					loginResponse.getObHeader().setStatusCode(MiBConstant.MIB_MSGCODE_INVALID_RECORDID);
				}
			}
			else if(requestData.getAction().equalsIgnoreCase(MiBConstant.MSG_MAPPING_ACTION_DELETE))
			{
				MessagePropertiesI18n rs = msgDAO.findByRecordId(requestData.getMsgMapping().getRecordID());
				if(null==rs)
				{
					loginResponse.getObHeader().setStatusCode(MiBConstant.MIB_MSGCODE_INVALID_RECORDID);
				}
			}
			else
			{
				loginResponse.getObHeader().setStatusCode(MiBConstant.MIB_MSGCODE_INVALID_ACTION);
			}
			
			
		
			
			


		} catch (Exception e) {
			// TODO Auto-generated catch block
			//"ERROR.MIB.9999999"
			

			log.info(this.getClass().toString(), e);
			loginResponse = new ObMessageMappingResponse();
			loginResponse.setObHeader(new ObHeaderResponse());
			loginResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			loginResponse.getObHeader().setStatusMessage(e.getMessage());
			
		}
		
		response.setBDObject(loginResponse);
		
		return response;
	}


	

	

	
	
	
}