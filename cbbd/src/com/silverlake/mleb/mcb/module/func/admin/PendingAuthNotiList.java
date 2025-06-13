package com.silverlake.mleb.mcb.module.func.admin;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.mcb.bean.ObHeaderResponse;
import com.silverlake.mleb.mcb.bean.ObTransNotiAuthRequest;
import com.silverlake.mleb.mcb.bean.ObTransNotiAuthResponse;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.entity.dao.GeneralCodeDAO;
import com.silverlake.mleb.mcb.module.common.FuncServices;
import com.silverlake.mleb.mcb.module.vc.authorization.AuthPendNotiRequestData;
import com.silverlake.mleb.mcb.module.vc.authorization.AuthPendNotiResponseData;
import com.silverlake.mleb.mcb.module.vc.authorization.TransactionNotification;
import com.silverlake.mleb.mcb.module.vc.authorization.TransactionNotificationDetail;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.common.VCService;



@Service
public class PendingAuthNotiList extends FuncServices  
{

	private static Logger log = LogManager.getLogger(PendingAuthNotiList.class);
	
		 
	@Autowired ApplicationContext appContext;
	
	@Autowired
	GeneralCodeDAO gnDao;
	
	public MICBResponseBean process(MICBRequestBean arg0) {
		// TODO Auto-generated method stub
		
		
	
		MICBResponseBean response = new MICBResponseBean();
		ObTransNotiAuthResponse obResponse = new ObTransNotiAuthResponse();
		obResponse.setObHeader(new ObHeaderResponse());
	
		
		
		try {

			ObTransNotiAuthRequest requestData = (ObTransNotiAuthRequest) arg0.getBDObject();			
			String orgId = requestData.getObUser().getOrgId();
			String usrId = requestData.getObUser().getLoginId();
			
			String pageSize = requestData.getPage_size()==null?"10":requestData.getPage_size();
			pageSize = pageSize.trim().length()==0?"10":pageSize;
			
			String pageNo = (String) (requestData.getPage_no()==null?"1":requestData.getPage_no());
			pageNo = pageNo.trim().length()==0?"1":pageNo;
			
			String ipAddress = requestData.getObHeader() == null ? null : requestData.getObHeader().getIp();
		 
			VCService usrService = new VCService(appContext);
			AuthPendNotiRequestData vcRqData = new AuthPendNotiRequestData();
			vcRqData.setOrg_cd(orgId);
			vcRqData.setUsr_cd(usrId);
			vcRqData.setPage_no(pageNo);
			vcRqData.setPage_size(pageSize);
		  
			AuthPendNotiResponseData vcRpData = new AuthPendNotiResponseData();
			VCResponse<AuthPendNotiResponseData> vcResponse = 
					usrService.callOmniVC(
							AuthPendNotiRequestData.method_auth_admin_trxnotif_pending_list,
							vcRqData, 
							vcRpData, 
							requestData.getUserContext().getSessionId(), arg0.getTranxID(), ipAddress);
			
			if(vcResponse.getResponse_code().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS))
			{
				String locale = arg0.getLocaleCode();
				
				vcRpData = vcResponse.getData();
				
				
				obResponse.setTotal_records(vcRpData.getTotal_records());
				
				for(TransactionNotification trf : vcRpData.getTrx_notif_list()){
					
					for(TransactionNotificationDetail dtl : trf.getTrx_notif_dtl_list()){
						
						String description = gnDao.getTransactionDescription(locale, dtl.getTrx_type());	
					     dtl.setDescription(description);
					}
				}
				
				obResponse.setTrxNotification_list(vcRpData.getTrx_notif_list());
 
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
