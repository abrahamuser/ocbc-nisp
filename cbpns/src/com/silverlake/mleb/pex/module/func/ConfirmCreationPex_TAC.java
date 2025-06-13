package com.silverlake.mleb.pex.module.func;



import java.text.DecimalFormat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.fuzion.ws.security.endpoint.EndpointResponse;
import com.fuzion.ws.security.endpoint.ResendAuthorizationCodeRequest;
import com.silverlake.hlb.mib.bean.ObHeaderResponse;
import com.silverlake.hlb.mib.bean.ObUserContext;
import com.silverlake.hlb.mib.constant.MiBConstant;
import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.pex.bean.ObPexRequest;
import com.silverlake.mleb.pex.bean.ObPexResponse;
import com.silverlake.mleb.pex.constant.PExConstant;
import com.silverlake.mleb.pex.entity.dao.MLEBPExDAO;
import com.silverlake.mleb.pex.module.common.FuncServices;
import com.silverlake.mleb.pex.module.ib.securityServices.SecuirtyService;
import com.silverlake.mleb.pex.module.services.PExServices;






@Service
public class ConfirmCreationPex_TAC extends FuncServices
{

	private static Logger log = LogManager.getLogger(ConfirmCreationPex_TAC.class);
	
	
	@Autowired
	MLEBPExDAO dao;
	
	@Autowired ApplicationContext appContext;
	
	
	public MICBResponseBean process(MICBRequestBean arg0) {
		// TODO Auto-generated method stub
		
		
	
		MICBResponseBean response = new MICBResponseBean();
	
		DecimalFormat tranxAmountFormat = new DecimalFormat(PExConstant.PEX_TRANSACTION_AMOUNT_FORMAT);
		ObPexResponse pexResponse = new ObPexResponse();
		pexResponse.setObHeader(new ObHeaderResponse());
		pexResponse.setUserContext(new ObUserContext());
		
		
		try {
			
			PExServices pexServices = new PExServices(dao);
			ObPexRequest pexRequest = (ObPexRequest) arg0.getBDObject();
			
			
			//vn auto send tac
			SecuirtyService securityService = new SecuirtyService(appContext);
			ResendAuthorizationCodeRequest authorizationCode = new ResendAuthorizationCodeRequest();
			authorizationCode.setAuthorizationUUID(pexRequest.getObUser().getAccessId());
			EndpointResponse respTAC = securityService.resendAuthorizationCode(pexRequest.getUserContext(), authorizationCode, arg0.getTranxID());
			if(respTAC.getResponse().getStatusCode()==1)
			{
				
				
				pexResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
			}
			else
			{
				pexResponse.getObHeader().setStatusCode(MiBConstant.MIB_FUZION_ERROR_PREFIX+respTAC.getResponse().getErrorCode());
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



	 
		/*MuleContext muleContext;
		


		@Override
		public MuleContext getMuleContext() {
			// TODO Auto-generated method stub
			return muleContext;
		}


		@Override
		public void setMuleContext(MuleContext arg0) {
			// TODO Auto-generated method stub
			
			
			
			
			muleContext = arg0;
		}*/
		



	
}