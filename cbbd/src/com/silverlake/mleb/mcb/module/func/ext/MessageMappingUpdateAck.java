package com.silverlake.mleb.mcb.module.func.ext;


import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.mcb.bean.ObHeaderResponse;
import com.silverlake.mleb.mcb.bean.ObMessageMappingRequest;
import com.silverlake.mleb.mcb.bean.ObMessageMappingResponse;
import com.silverlake.mleb.mcb.bean.ObUserContext;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.entity.MessageProperties;
import com.silverlake.mleb.mcb.entity.MessagePropertiesI18n;
import com.silverlake.mleb.mcb.entity.dao.ConfDao;
import com.silverlake.mleb.mcb.entity.dao.MessagePropertiesDAO;
import com.silverlake.mleb.mcb.module.common.FuncServices;
import com.silverlake.mleb.mcb.util.MessageManager;
import com.silverlake.mleb.mcb.util.PropertiesManager;






@Service
public class MessageMappingUpdateAck extends FuncServices  
{

	private static Logger log = LogManager.getLogger(MessageMappingUpdateAck.class);
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
			
			
			MessagePropertiesI18n rs = null;
			if(requestData.getAction().equalsIgnoreCase(MiBConstant.MSG_MAPPING_ACTION_ADD))
			{
				rs = msgDAO.findByMsgCode(requestData.getMsgMapping().getErrorCode(), requestData.getMsgMapping().getLocale());
				if(null!=rs)
				{
					loginResponse.getObHeader().setStatusCode(MiBConstant.MIB_MSGCODE_EXISTED);
				}
				else
				{
					MessageProperties msgPro = msgDAO.findByMainMsgCode(requestData.getMsgMapping().getErrorCode());
					
					if(null==msgPro)
					{
						msgPro = new MessageProperties();
						msgPro.setCreateDt(new Date());
						msgPro.setCreateBy(requestData.getObExtHeader().getId());
						msgPro.setErrMessageCode(requestData.getMsgMapping().getErrorCode());
						msgPro.setStatus("ACTIVE");
						msgDAO.insertEntity(msgPro);
					}
					
					
					MessagePropertiesI18n msgMapping = new MessagePropertiesI18n();
					msgMapping.setMessageProperties(msgPro);
					msgMapping.setCreateBy(requestData.getObExtHeader().getId());
					msgMapping.setCreateDt(new Date());
					msgMapping.setErrMessage(requestData.getMsgMapping().getErrorMsg());
					msgMapping.setErrMessageCode(requestData.getMsgMapping().getErrorCode());
					msgMapping.setLanguageCode(requestData.getMsgMapping().getLocale());
					msgDAO.insertEntity(msgMapping);
					loginResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
					
				}
			}
			else if(requestData.getAction().equalsIgnoreCase(MiBConstant.MSG_MAPPING_ACTION_UPDATE))
			{
				//rs = msgDAO.findByRecordId(requestData.getMsgMapping().getRecordID());
				rs = msgDAO.findByMsgCode(requestData.getMsgMapping().getErrorCode(), requestData.getMsgMapping().getLocale());
				if(null==rs)
				{
					loginResponse.getObHeader().setStatusCode(MiBConstant.MIB_MSGCODE_INVALID_RECORDID);
				}
				else
				{
					rs.setErrMessage(requestData.getMsgMapping().getErrorMsg());
					msgDAO.updateEntity(rs);
					loginResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
				}
			}
			else if(requestData.getAction().equalsIgnoreCase(MiBConstant.MSG_MAPPING_ACTION_DELETE))
			{
				//rs = msgDAO.findByRecordId(requestData.getMsgMapping().getRecordID());
				rs = msgDAO.findByMsgCode(requestData.getMsgMapping().getErrorCode(), requestData.getMsgMapping().getLocale());
				if(null==rs)
				{
					loginResponse.getObHeader().setStatusCode(MiBConstant.MIB_MSGCODE_INVALID_RECORDID);
				}
				else
				{

					msgDAO.deleteMsgMapping(rs);
					loginResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
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