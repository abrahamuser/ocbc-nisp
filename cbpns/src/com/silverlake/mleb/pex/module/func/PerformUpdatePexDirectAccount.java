package com.silverlake.mleb.pex.module.func;



import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.fuzion.ws.common.endpoint.CutOffTimeResponse;
import com.silverlake.hlb.mib.bean.ObAccountBean;
import com.silverlake.hlb.mib.bean.ObHeaderResponse;
import com.silverlake.hlb.mib.bean.ObUserContext;
import com.silverlake.hlb.mib.constant.MiBConstant;
import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.pex.bean.ObPexRequest;
import com.silverlake.mleb.pex.bean.ObPexResponse;
import com.silverlake.mleb.pex.bean.ObPexTransactionDetails;
import com.silverlake.mleb.pex.bean.ObPexUserDetails;
import com.silverlake.mleb.pex.constant.PExConstant;
import com.silverlake.mleb.pex.entity.PexAccount;
import com.silverlake.mleb.pex.entity.dao.MLEBPExDAO;
import com.silverlake.mleb.pex.module.common.FuncServices;
import com.silverlake.mleb.pex.module.ib.commonServices.CommoService;
import com.silverlake.mleb.pex.module.services.PExServices;






@Service
public class PerformUpdatePexDirectAccount extends FuncServices
{

	private static Logger log = LogManager.getLogger(PerformUpdatePexDirectAccount.class);
	
	
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
			
			CommoService commonService = new CommoService(appContext);
			CutOffTimeResponse cutResp = commonService.getCutOffTime(pexRequest.getUserContext(),  arg0.getTranxID());
			if(cutResp.getResponse().getStatusCode() == 0)
			{
				pexResponse.getObHeader().setStatusCode(MiBConstant.MIB_FUZION_ERROR_PREFIX+cutResp.getResponse().getErrorCode());
			}
			else
			{
				PexAccount updatePexAcc = pexServices.updatePExAccount(pexRequest.getPexUserDetails(),arg0.getTranxID(), arg0.getLocaleCode());
				SimpleDateFormat simDate = new SimpleDateFormat(PExConstant.COMMON_DATETIME_FORMAT);
				Date transSactionDate = new Date();
				
				log.info("Update PEX Account : "+updatePexAcc);
				if(updatePexAcc == null)
				{
					if(null==pexRequest.getPexUserDetails().getPexAccount())
					{
						//active mobile number found
						pexResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_ACTIVE_MOBILE_NOT_FOUND);
					}
					else
					{
						//duplicate active mobile number found
						pexResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_DUPLICATE_MOBILE_FOUND);
					}
					ObPexTransactionDetails tranxx = new ObPexTransactionDetails();
					if(pexRequest.getUserContext().getLocaleCode().equalsIgnoreCase("km_KH")){
						tranxx.setStatus(PExConstant.PEX_STATUS_TRANSACTION_FAILED_KH);
					}else{
						tranxx.setStatus(PExConstant.PEX_STATUS_TRANSACTION_FAILED);
					}
					
					tranxx.setDatetime(simDate.format(transSactionDate));
					pexResponse.setPexTransactionDetails(tranxx);
					pexResponse.getObHeader().setAcknowledgementNumber(arg0.getTranxID());
				}
				else
				{
					ObPexTransactionDetails tranxx = new ObPexTransactionDetails();
					
					String getPexTranx_Status = ""; 
					
					if(pexRequest.getUserContext().getLocaleCode().equalsIgnoreCase("km_KH")){
						tranxx.setStatus(PExConstant.PEX_STATUS_TRANSACTION_SUCCESS_KH);
						
						if(updatePexAcc.getStatus().equalsIgnoreCase(PExConstant.PEX_ACC_STATUS_ACTIVE)){
							
							getPexTranx_Status = PExConstant.PEX_ACC_STATUS_ACTIVE_KH; 
							
						}else if(updatePexAcc.getStatus().equalsIgnoreCase(PExConstant.PEX_ACC_STATUS_DISABLED)){
							
							getPexTranx_Status = PExConstant.PEX_ACC_STATUS_DISABLED_KH; 
							
						}else if(updatePexAcc.getStatus().equalsIgnoreCase(PExConstant.PEX_ACC_STATUS_INACTIVEx)){
							
							getPexTranx_Status = PExConstant.PEX_ACC_STATUS_INACTIVEx_KH;
						}
						
						
					}else{
						tranxx.setStatus(PExConstant.PEX_STATUS_TRANSACTION_SUCCESS);
						getPexTranx_Status = updatePexAcc.getStatus(); 
					}
					
					tranxx.setDatetime(simDate.format(transSactionDate));
					pexResponse.setPexTransactionDetails(tranxx);
					ObPexUserDetails pexUserAcc = new ObPexUserDetails();
					pexUserAcc.setStatus(getPexTranx_Status);
					
					pexUserAcc.setCreateDate(simDate.format(updatePexAcc.getCreationDate()));
					pexUserAcc.setMobileNumber(updatePexAcc.getMobileNo());
					
					if(null!=pexRequest.getPexUserDetails().getPexAccount())
					{
						pexUserAcc.setPexAccount(new ObAccountBean());
						pexUserAcc.getPexAccount().setAccountDescription(updatePexAcc.getAccountDesc());
						pexUserAcc.getPexAccount().setAccountName(updatePexAcc.getAccountName());
						pexUserAcc.getPexAccount().setAccountNumber(updatePexAcc.getAccountNumber());
						pexUserAcc.getPexAccount().setProductCode(updatePexAcc.getAccountProductType());
						pexUserAcc.getPexAccount().setAccountDescription(updatePexAcc.getAccountDesc());
						
					}
					pexResponse.setPexUserDetails(pexUserAcc);
					pexResponse.getObHeader().setAcknowledgementNumber(arg0.getTranxID());
					
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