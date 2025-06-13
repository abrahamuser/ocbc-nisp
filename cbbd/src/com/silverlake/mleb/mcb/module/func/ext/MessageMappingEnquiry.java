package com.silverlake.mleb.mcb.module.func.ext;



import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.mcb.bean.ObHeaderResponse;
import com.silverlake.mleb.mcb.bean.ObMessageMappingBean;
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
public class MessageMappingEnquiry extends FuncServices  
{

	private static Logger log = LogManager.getLogger(MessageMappingEnquiry.class);
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
			
			if(requestData.getMsgMapping().getErrorCode()!=null || requestData.getMsgMapping().getErrorMsg()!=null)
			{
				List<MessagePropertiesI18n> rsList =  msgDAO.getMessageMappingByPage(requestData.getMsgMapping().getErrorCode(), requestData.getMsgMapping().getErrorMsg(),requestData.getMsgMapping().getLocale(), requestData.getPageNum(), requestData.getRecordPerPage());
				
				
				if(rsList.size()>0)
				{
					loginResponse.setMsgMappingList(new ArrayList());
					for(MessagePropertiesI18n event:rsList)
					{
						ObMessageMappingBean custEve = new ObMessageMappingBean();
						custEve.setRecordID(String.valueOf(event.getRowId()));
						custEve.setErrorCode(event.getErrMessageCode());
						custEve.setErrorMsg(event.getErrMessage());
						custEve.setLocale(event.getLanguageCode());
						loginResponse.getMsgMappingList().add(custEve);
					}
				}
				
				//loginResponse.setTotalRecord(String.valueOf(custEventDao.getMaxCustEventByCif(requestData.getCif(),requestData.getStartDate(),requestData.getEndDate())));
				loginResponse.setCurrentPageNum(requestData.getPageNum()==null?"1":requestData.getPageNum());
				loginResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
			}
			else
			{
				loginResponse.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT_PARAM);
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