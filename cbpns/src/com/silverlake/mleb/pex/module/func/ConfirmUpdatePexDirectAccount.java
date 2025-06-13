package com.silverlake.mleb.pex.module.func;



import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.fuzion.ws.common.endpoint.CutOffTimeResponse;
import com.silverlake.hlb.mib.bean.ObHeaderResponse;
import com.silverlake.hlb.mib.bean.ObUserContext;
import com.silverlake.hlb.mib.constant.MiBConstant;
import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.pex.bean.ObPexRequest;
import com.silverlake.mleb.pex.bean.ObPexResponse;
import com.silverlake.mleb.pex.constant.PExConstant;
import com.silverlake.mleb.pex.entity.PexAccount;
import com.silverlake.mleb.pex.entity.dao.MLEBPExDAO;
import com.silverlake.mleb.pex.module.common.FuncServices;
import com.silverlake.mleb.pex.module.ib.commonServices.CommoService;
import com.silverlake.mleb.pex.module.services.PExServices;






@Service
public class ConfirmUpdatePexDirectAccount extends FuncServices 
{

	private static Logger log = LogManager.getLogger(ConfirmUpdatePexDirectAccount.class);
	
	
	@Autowired
	MLEBPExDAO dao;
	
	@Autowired ApplicationContext appContext;
	
	public MICBResponseBean process(MICBRequestBean arg0) {
		// TODO Auto-generated method stub
		
		
	
		MICBResponseBean response = new MICBResponseBean();
	
		
		ObPexResponse pexResponse = new ObPexResponse();
		pexResponse.setObHeader(new ObHeaderResponse());
		pexResponse.setUserContext(new ObUserContext());
		
		
		try {
			
			
			ObPexRequest pexRequest = (ObPexRequest) arg0.getBDObject();
			String loginID = pexRequest.getUserContext().getLoginId();
			String mobileNo = pexRequest.getObUser().getMobileNumber();
			
			pexRequest.getPexUserDetails().setCif(pexRequest.getObUser().getCifNumber());
			pexRequest.getPexUserDetails().setMobileNumber(mobileNo);

			PExServices pexServices = new PExServices(dao);
			//List<HlbPexAccount> pexUserActiveAcc  = pexServices.getDirectPexUserActiveAcc(pexRequest.getObUser().getCifNumber(),pexRequest.getObUser().getMobileNumber(),new ArrayList());
			List<PexAccount> pexUserAllcc = pexServices.getDirectPexMobileActiveAcc(mobileNo,pexRequest.getUserContext().getCountryCode());
			//HlbPexAccount updatePexAcc = pexServices.updatePExAccount(pexRequest.getPexUserDetails(),arg0.getTranxID());
			
			CommoService commonService = new CommoService(appContext);
			CutOffTimeResponse cutResp = commonService.getCutOffTime(pexRequest.getUserContext(),  arg0.getTranxID());
			if(cutResp.getResponse().getStatusCode() == 0)
			{
				pexResponse.getObHeader().setStatusCode(MiBConstant.MIB_FUZION_ERROR_PREFIX+cutResp.getResponse().getErrorCode());
			}
			//disable
			else if(null==pexRequest.getPexUserDetails().getPexAccount())
			{
				boolean checkActive = false;
				for(PexAccount pexAcc : pexUserAllcc)
				{
					String cifx = pexRequest.getObUser().getCifNumber();
					if(cifx.equalsIgnoreCase(pexAcc.getCif()) )
					{
						checkActive = true;
					}
				}
				
				if(!checkActive)
				{
					//disable but no active mobile number
					pexResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_ACTIVE_MOBILE_NOT_FOUND);
				}
				else
				{
					pexResponse.setMobileNumber(mobileNo);
					pexResponse.setPexUserDetails(pexRequest.getPexUserDetails());
					pexResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COMMON_SUCCESS);
				}
			}
			//update or add
			else
			{
				
				
				boolean allowAddUpdate = true;
				for(PexAccount pexAcc : pexUserAllcc)
				{
					String cifx = pexRequest.getObUser().getCifNumber();
					if(!cifx.equalsIgnoreCase(pexAcc.getCif()) )
					{
						allowAddUpdate = false;
					}
				}
				
				if(!allowAddUpdate)
				{
					//update or add not allow, duplicate mobile number found
					pexResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_DUPLICATE_MOBILE_FOUND);
				}
				else
				{
					pexResponse.setMobileNumber(mobileNo);
					pexResponse.setPexUserDetails(pexRequest.getPexUserDetails());
					pexResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COMMON_SUCCESS);
				}
			}
			
			
			
			
		
		
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//"ERROR.MIB.9999999"
			log.error(this.getClass().toString(), e);
			pexResponse = new ObPexResponse();
			pexResponse.setObHeader(new ObHeaderResponse());
			pexResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COMMON_ERROR);
			pexResponse.getObHeader().setStatusMessage(e.getMessage());
	
		}
		
		response.setBDObject(pexResponse);	
		return response;
	}

/*

	@Override
	public MuleContext getMuleContext() {
		// TODO Auto-generated method stub
		return muleContext;
	}


	@Override
	public void setMuleContext(MuleContext arg0) {
		// TODO Auto-generated method stub
		
		
		
		
		muleContext = arg0;
	}
	*/
	
	
	
	
	
}