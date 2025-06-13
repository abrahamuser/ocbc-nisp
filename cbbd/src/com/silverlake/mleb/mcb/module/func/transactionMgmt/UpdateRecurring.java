package com.silverlake.mleb.mcb.module.func.transactionMgmt;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.mcb.bean.ObHeaderResponse;
import com.silverlake.mleb.mcb.bean.ObRecurringRequest;
import com.silverlake.mleb.mcb.bean.ObRecurringResponse;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.entity.dao.CustomerDAO;
import com.silverlake.mleb.mcb.entity.dao.GeneralCodeDAO;
import com.silverlake.mleb.mcb.module.common.FuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.common.VCService;
import com.silverlake.mleb.mcb.module.vc.transaction.management.RecurringRequestData;
import com.silverlake.mleb.mcb.module.vc.transaction.management.RecurringResponseData;







@Service
public class UpdateRecurring extends FuncServices  
{

	private static Logger log = LogManager.getLogger(UpdateRecurring.class);
	
	@Autowired
	CustomerDAO dao;
	
	@Autowired
	GeneralCodeDAO gnDao;
	
	 
	@Autowired ApplicationContext appContext;
	
	
	public MICBResponseBean process(MICBRequestBean arg0) {
		// TODO Auto-generated method stub
		
		
	
		MICBResponseBean response = new MICBResponseBean();
		ObRecurringResponse obResponse = new ObRecurringResponse();
		obResponse.setObHeader(new ObHeaderResponse());
	
		
		
		try {
			
			ObRecurringRequest requestData = (ObRecurringRequest) arg0.getBDObject();			
			String orgId = requestData.getObUser().getOrgId();
			String usrId = requestData.getObUser().getLoginId();
			String ipAddress = requestData.getObHeader() == null ? null : requestData.getObHeader().getIp();
			RecurringRequestData omniReq = new RecurringRequestData();
			omniReq.setOrg_cd(orgId);
			omniReq.setUsr_cd(usrId);
			omniReq.setProd_cd(requestData.getProd_cd());
			omniReq.setRecurring_id(requestData.getRecurring_id());
			omniReq.setDelete_type(requestData.getDelete_type());
			
			RecurringResponseData omniResp = new RecurringResponseData();
			VCService vcService = new VCService(appContext);
			VCResponse<com.silverlake.mleb.mcb.module.vc.transaction.management.RecurringResponseData> vcResponse = 
					vcService.callOmniVC(RecurringRequestData.method_trx_mgmt_delRecurring,omniReq, omniResp, requestData.getUserContext().getSessionId(), arg0.getTranxID(), ipAddress);
			
			if(vcResponse.getResponse_code().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS))
			{
				obResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
			}
			else 
			{
				obResponse.getObHeader().setStatusCode(MiBConstant.MIB_OMNI_PREFIX+vcResponse.getResponse_code() );
				obResponse.getObHeader().setStatusMessage(vcResponse.getResponse_desc());
			}
			
		} catch (Exception e) {
		
			log.info(this.getClass().toString(), e);
			obResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			obResponse.getObHeader().setStatusMessage(e.getMessage());
		}
		
		response.setBDObject(obResponse);
		
		return response;
	}
	
	
	 
	 
}
