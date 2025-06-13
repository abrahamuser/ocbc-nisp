package com.silverlake.mleb.mcb.module.func;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.mcb.bean.ObAuthenticationRequest;
import com.silverlake.mleb.mcb.bean.ObAuthenticationResponse;
import com.silverlake.mleb.mcb.bean.ObHeaderResponse;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.entity.dao.CustomerDAO;
import com.silverlake.mleb.mcb.module.common.FuncServices;






@Service
public class RetrieveSessionInfo extends FuncServices  
{

	private static Logger log = LogManager.getLogger(RetrieveSessionInfo.class);
	
	@Autowired
	CustomerDAO dao;
	
	 
	@Autowired ApplicationContext appContext;
	
	
	public MICBResponseBean process(MICBRequestBean arg0) {
		// TODO Auto-generated method stub
		
		
	
		MICBResponseBean response = new MICBResponseBean();
		ObAuthenticationResponse custProfileResponse = new ObAuthenticationResponse();
		custProfileResponse.setObHeader(new ObHeaderResponse());
	
		
		
		try {


			ObAuthenticationRequest requestData = (ObAuthenticationRequest) arg0.getBDObject();			
			
			
			
			
			
			
			//MiBServices mibServices = new MiBServices(dao);
			//custProfileResponse.setModuleMaintenance(mibServices.checkModuleMaintenance());
			
			 
					custProfileResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
				 
		 
			 
		
		} catch (Exception e) {
			
			
		
			log.info(this.getClass().toString(), e);
			custProfileResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			custProfileResponse.getObHeader().setStatusMessage(e.getMessage());
	
		}
		
		response.setBDObject(custProfileResponse);
		
		return response;
	}






	
	
}