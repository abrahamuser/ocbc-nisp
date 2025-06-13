package com.silverlake.mleb.mcb.module.func.admin;



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
import com.silverlake.mleb.mcb.bean.ObTransNotiAuthRequest;
import com.silverlake.mleb.mcb.bean.ObTransNotiAuthResponse;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.module.common.FuncServices;
import com.silverlake.mleb.mcb.module.vc.authorization.AuthPendNotiRequestData;
import com.silverlake.mleb.mcb.module.vc.authorization.AuthPendNotiResponseData;
import com.silverlake.mleb.mcb.module.vc.authorization.TransactionNotification;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.common.VCService;







@Service
public class PendingAuthNotiMaint extends FuncServices  
{

	private static Logger log = LogManager.getLogger(PendingAuthNotiMaint.class);
	 
	@Autowired ApplicationContext appContext;
	
	
	public MICBResponseBean process(MICBRequestBean arg0) {
		// TODO Auto-generated method stub
		
		
	
		MICBResponseBean response = new MICBResponseBean();
		ObTransNotiAuthResponse obResponse = new ObTransNotiAuthResponse();
		obResponse.setObHeader(new ObHeaderResponse());
	
		
		
		try {

			ObTransNotiAuthRequest requestData = (ObTransNotiAuthRequest) arg0.getBDObject();	
			
			String orgId = requestData.getObUser().getOrgId();
			String usrId = requestData.getObUser().getLoginId();
			String deviceOS = arg0.getDeviceBean() == null ? null : arg0.getDeviceBean().getType()+" "+arg0.getDeviceBean().getOs();
			String deviceType = arg0.getDeviceBean() == null ? null : arg0.getDeviceBean().getModel();
			String ipAddress = requestData.getObHeader() == null ? null : requestData.getObHeader().getIp();
			
			List<TransactionNotification> transNotiList = checkValidTransNotiList(requestData);
			
			if(transNotiList == null )
			{
				
				  obResponse.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
			}
			else
			{
		 
					VCService usrService = new VCService(appContext);
					
					
					
					AuthPendNotiRequestData vcRqData = new AuthPendNotiRequestData();
					vcRqData.setOrg_cd(orgId);
					vcRqData.setUsr_cd(usrId);
					vcRqData.setPending_authpol_list(transNotiList);
					vcRqData.setAction_cd(requestData.getAction_cd());
					vcRqData.setDevice_type(deviceType);
					vcRqData.setDevice_os(deviceOS);
									
					
					AuthPendNotiResponseData vcRpData = new AuthPendNotiResponseData();
					VCResponse<AuthPendNotiResponseData> vcResponseNotiMaint = 
							usrService.callOmniVC(
									AuthPendNotiRequestData.method_auth_admin_trxnotif_pending_maint,
									vcRqData, 
									vcRpData, 
									requestData.getUserContext().getSessionId(), arg0.getTranxID(), ipAddress);
					
					if(!vcResponseNotiMaint.getResponse_code().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS))
					{
						obResponse.getObHeader().setStatusCode(MiBConstant.MIB_OMNI_PREFIX+vcResponseNotiMaint.getResponse_code() );
						obResponse.getObHeader().setStatusMessage(vcResponseNotiMaint.getResponse_desc());
					}
					else
					{
					
						vcRpData = vcResponseNotiMaint.getData();
			 
					  obResponse.setTrxNotifResult_list(vcRpData.getTrxnotif_result_list());
					  obResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
					 
					 				
					}
			
			}
		} catch (Exception e) {
		
			log.info(this.getClass().toString(), e);
			obResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			obResponse.getObHeader().setStatusMessage(e.getMessage());
		}
		
		response.setBDObject(obResponse);
		
		return response;
	}
	
	 
	
	public List<TransactionNotification> checkValidTransNotiList(ObTransNotiAuthRequest rq) {
		List<TransactionNotification> transNotiMaintList = null;

		if (rq.getTrxNotification_list() != null && rq.getTrxNotification_list().size() > 0 && rq.getRecordIds() != null
				&& rq.getRecordIds().trim().length() > 0) {

			String[] transNotiMaintIds = rq.getRecordIds().trim().split(",");

			for (String id : transNotiMaintIds) {

				for (TransactionNotification tmp : rq.getTrxNotification_list()) {
					if (id.trim().equalsIgnoreCase(tmp.getRecord_id().trim())) {
						if (transNotiMaintList == null) {
							transNotiMaintList = new ArrayList();
						}

						transNotiMaintList.add(tmp);
						break;
					}

				}

			}

		}

		return transNotiMaintList;
	}	
	
}
