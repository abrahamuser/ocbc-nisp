package com.silverlake.mleb.ccmcb.module.ext;



import java.util.Date;

import javax.xml.ws.WebServiceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.silverlake.mleb.ccmcb.module.MiBServices;
import com.silverlake.mleb.ccmcb.util.PropertiesManager;
import com.silverlake.mleb.mcb.bean.ObCustEventRequest;
import com.silverlake.mleb.mcb.bean.ObExtHeader;
import com.silverlake.mleb.mcb.bean.ObRequest;
import com.silverlake.mleb.mcb.bean.ObUserContext;
import com.silverlake.mleb.mcb.constant.MiBConstant;


public class CustEventEnquiry extends MiBServices
{


	public CustEventEnquiry(WebServiceContext session) {
		super(session);
		// TODO Auto-generated constructor stub
	}


	public static PropertiesManager property = new PropertiesManager();

	private static Logger log = LogManager.getLogger(CustEventEnquiry.class);
	ObCustEventRequest obRequest;
	
	@Override
	public ObRequest getBDObject() {
		func_id = MiBConstant.FUNC_EXT_CUSTEVENTENQ;
		return obRequest;
	}
	
	
	
	public void processData(ObExtHeader extHeader, String cif, Date startDate, Date endDate, String pageNum, String recordPerPage)
	{
		obRequest = new ObCustEventRequest();
		obRequest.setObExtHeader(extHeader);
		obRequest.setUserContext(new ObUserContext());
		obRequest.setRecordPerPage(recordPerPage);
		obRequest.setStartDate(startDate);
		obRequest.setEndDate(endDate);
		obRequest.setPageNum(pageNum);
		obRequest.setCif(cif);
	}


	@Override
	public void processResponse() 
	{
		
	}
	
}
