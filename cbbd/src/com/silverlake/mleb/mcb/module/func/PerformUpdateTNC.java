package com.silverlake.mleb.mcb.module.func;



import java.util.Date;
import java.util.List;

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
import com.silverlake.mleb.mcb.bean.ObUserDetail;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.entity.CustomerState;
import com.silverlake.mleb.mcb.entity.GeneralCode;
import com.silverlake.mleb.mcb.entity.dao.CustomerDAO;
import com.silverlake.mleb.mcb.entity.dao.GeneralCodeDAO;
import com.silverlake.mleb.mcb.module.common.FuncServices;
import com.silverlake.mleb.mcb.util.PropertiesManager;

@Service
public class PerformUpdateTNC extends FuncServices  
{

	private static Logger log = LogManager.getLogger(PerformUpdateTNC.class);
	
	@Autowired
	CustomerDAO dao;
	
	@Autowired
	GeneralCodeDAO gnDao;
	
	 
	@Autowired ApplicationContext appContext;
	
	
	public MICBResponseBean process(MICBRequestBean arg0) {
		// TODO Auto-generated method stub
		
		
	
		MICBResponseBean response = new MICBResponseBean();
		ObAuthenticationResponse obresp = new ObAuthenticationResponse();
		obresp.setObHeader(new ObHeaderResponse());
	
		
		
		try {


			ObAuthenticationRequest requestData = (ObAuthenticationRequest) arg0.getBDObject();			
			String tncAction = requestData.getTncAction();
			tncAction = tncAction==null?null:(tncAction.trim().length()==0?null:tncAction);
			obresp.setObUser(requestData.getObUser());
		 
			if(requestData.getObUser().isTncFlag() && null!= tncAction)
			{
				boolean updateFlag = updateTNC(tncAction, requestData.getObUser(),arg0.getTranxID(), arg0.getDeviceBean().getDeviceId(), arg0.getHeaderBean().getRequestInfo());
				obresp.getObUser().setTncFlag(!updateFlag);
				obresp.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
			}
			else
			{
				obresp.getObHeader().setStatusCode(MiBConstant.MCB_INVALID_TNC_ACTION );
				obresp.getObHeader().setStatusMessage("MCB_INVALID_TNC_ACTION");
			}
			
			
			
			 
			
				 
		 
			 
		
		} catch (Exception e) {
			
			
		
			log.info(this.getClass().toString(), e);
			obresp.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			obresp.getObHeader().setStatusMessage(e.getMessage());
	
		}
		
		response.setBDObject(obresp);
		
		return response;
	}



	public boolean updateTNC(String tncAction, ObUserDetail userinfo, String mlebTranxId, String deviceId, String appVersion)
	{
		
		//tncAction : accept or reject
		PropertiesManager proMgr = new PropertiesManager();
		boolean accept = false;
		String vc_userId = userinfo.getCifNumber();
		String orgId = userinfo.getOrgId();
		String usrId = userinfo.getUserId();
		String state = MiBConstant.TNC_GENERAL_CODE;
		GeneralCode gn = gnDao.getTnC(state);
		String stateValue = gn.getGnCodeDescription();
		String stateAction = tncAction;
		if(tncAction.equalsIgnoreCase(MiBConstant.CUSTOMER_STATE_TNC_ACCEPT_ACTION))
		{
			stateAction = MiBConstant.CUSTOMER_STATE_TNC_ACCEPT_ACTION;
			accept =  true;
		}
		
		
		
		
		
		List<CustomerState> listState = dao.getCustomerStateTnC(vc_userId,state, stateValue);
		
		if(null!=listState && listState.size()==1)
		{
			listState.get(0).setStateAction(stateAction);
			listState.get(0).setStateDatetime(new Date());
			dao.updateEntity(listState.get(0));
		}
		else
		{
			CustomerState custState = new CustomerState();
			custState.setAppVersion(appVersion);
			custState.setCif(vc_userId);
			custState.setDeviceId(deviceId);
			custState.setMlebTranxId(mlebTranxId);
			custState.setOrgId(orgId);
			custState.setState(state);
			custState.setStateAction(stateAction);
			custState.setStateValue(stateValue);
			custState.setStateDatetime(new Date());
			custState.setUsrId(usrId);
			dao.insertEntity(custState);
		}
		
		
		
		
 
	
		
		return accept;
	}


	
	
}