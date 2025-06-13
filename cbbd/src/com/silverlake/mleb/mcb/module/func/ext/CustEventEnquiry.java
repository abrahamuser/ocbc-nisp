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
import com.silverlake.mleb.mcb.bean.ObCustEventBean;
import com.silverlake.mleb.mcb.bean.ObCustEventRequest;
import com.silverlake.mleb.mcb.bean.ObCustEventResponse;
import com.silverlake.mleb.mcb.bean.ObHeaderResponse;
import com.silverlake.mleb.mcb.bean.ObUserContext;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.entity.CustomerEvent;
import com.silverlake.mleb.mcb.entity.dao.ConfDao;
import com.silverlake.mleb.mcb.entity.dao.CustEventDao;
import com.silverlake.mleb.mcb.module.common.FuncServices;
import com.silverlake.mleb.mcb.util.MessageManager;
import com.silverlake.mleb.mcb.util.PropertiesManager;






@Service
public class CustEventEnquiry extends FuncServices  
{

	private static Logger log = LogManager.getLogger(CustEventEnquiry.class);
	private MessageManager msgPro = new MessageManager();
	private PropertiesManager pro = new PropertiesManager();
	@Autowired ApplicationContext appContext;
	
	@Autowired
	ConfDao confDao;
	
	@Autowired
	CustEventDao custEventDao;
	
	public MICBResponseBean process(MICBRequestBean arg0) {
		// TODO Auto-generated method stub
		
		
	
		MICBResponseBean response = new MICBResponseBean();
		ObCustEventResponse loginResponse = new ObCustEventResponse();
		loginResponse.setObHeader(new ObHeaderResponse());
		loginResponse.setUserContext(new ObUserContext());
		
		try {

			ObCustEventRequest requestData = (ObCustEventRequest) arg0.getBDObject();			
			
			if(requestData.getCif()!=null && requestData.getRecordPerPage()!=null)
			{
				List<CustomerEvent> rsList =  custEventDao.getCustEventByPage(requestData.getCif(),requestData.getStartDate(),requestData.getEndDate(), requestData.getPageNum(), requestData.getRecordPerPage());
				
				
				if(rsList.size()>0)
				{
					loginResponse.setCustEventBean(new ArrayList());
					for(CustomerEvent event:rsList)
					{
						ObCustEventBean custEve = new ObCustEventBean();
						custEve.setCif(event.getCif());
						custEve.setMlebTranxId(event.getMlebTranxId());
						custEve.setRecordID(String.valueOf(event.getRowId()));
						custEve.setRequestDatetime(event.getRequestDatetime());
						custEve.setResponseDatetime(event.getResponseDatetime());
						custEve.setService(event.getService());
						custEve.setStatusCode(event.getStatusCode());
						loginResponse.getCustEventBean().add(custEve);
					}
				}
				
				loginResponse.setTotalRecord(String.valueOf(custEventDao.getMaxCustEventByCif(requestData.getCif(),requestData.getStartDate(),requestData.getEndDate())));
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
			loginResponse = new ObCustEventResponse();
			loginResponse.setObHeader(new ObHeaderResponse());
			loginResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			loginResponse.getObHeader().setStatusMessage(e.getMessage());
			
		}
		
		response.setBDObject(loginResponse);
		
		return response;
	}


	

	

	
	
	
}