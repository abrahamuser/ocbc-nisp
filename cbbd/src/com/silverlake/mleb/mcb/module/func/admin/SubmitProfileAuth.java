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
import com.silverlake.mleb.mcb.bean.ObProfileMaintRequest;
import com.silverlake.mleb.mcb.bean.ObProfileMaintResponse;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.entity.dao.CustomerDAO;
import com.silverlake.mleb.mcb.entity.dao.GeneralCodeDAO;
import com.silverlake.mleb.mcb.module.common.FuncServices;
import com.silverlake.mleb.mcb.module.vc.authorization.AuthRequestData;
import com.silverlake.mleb.mcb.module.vc.authorization.AuthResponseData;
import com.silverlake.mleb.mcb.module.vc.authorization.SubscriberProfile;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.common.VCService;







@Service
public class SubmitProfileAuth extends FuncServices  
{

	private static Logger log = LogManager.getLogger(SubmitProfileAuth.class);
	
	@Autowired
	CustomerDAO dao;
	
	@Autowired
	GeneralCodeDAO gnDao;
	
	 
	@Autowired ApplicationContext appContext;
	
	
	public MICBResponseBean process(MICBRequestBean arg0) {
		// TODO Auto-generated method stub
		
		
	
		MICBResponseBean response = new MICBResponseBean();
		ObProfileMaintResponse obResponse = new ObProfileMaintResponse();
		obResponse.setObHeader(new ObHeaderResponse());
	
		
		
		try {

			ObProfileMaintRequest requestData = (ObProfileMaintRequest) arg0.getBDObject();	
			
			String orgId = requestData.getObUser().getOrgId();
			String usrId = requestData.getObUser().getLoginId();
			String deviceOS = arg0.getDeviceBean() == null ? null : arg0.getDeviceBean().getType()+" "+arg0.getDeviceBean().getOs();
			String deviceType = arg0.getDeviceBean() == null ? null : arg0.getDeviceBean().getModel();
			String ipAddress = requestData.getObHeader() == null ? null : requestData.getObHeader().getIp();
			
			List<SubscriberProfile> subscriberProfileList = checkValidUsrProfileAuthList(requestData);
			
			if(subscriberProfileList == null )
			{
				
				  obResponse.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
			}
			else
			{
		 
					VCService usrService = new VCService(appContext);
					
					
					
					AuthRequestData vcRqData = new AuthRequestData();
					vcRqData.setOrg_cd(orgId);
					vcRqData.setUsr_cd(usrId);
					vcRqData.setPending_profile_list(subscriberProfileList);
					vcRqData.setAction_cd(requestData.getAction_cd());
					vcRqData.setDevice_type(deviceType);
					vcRqData.setDevice_os(deviceOS);
										
					
					AuthResponseData vcRpData = new AuthResponseData();
					VCResponse<AuthResponseData> vcResponseBankRef = 
							usrService.callOmniVC(
									AuthRequestData.method_auth_admin_userProfile_maintAuth,
									vcRqData, 
									vcRpData, 
									requestData.getUserContext().getSessionId(), arg0.getTranxID(), ipAddress);
					
					if(!vcResponseBankRef.getResponse_code().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS))
					{
						obResponse.getObHeader().setStatusCode(MiBConstant.MIB_OMNI_PREFIX+vcResponseBankRef.getResponse_code() );
						obResponse.getObHeader().setStatusMessage(vcResponseBankRef.getResponse_desc());
					}
					else
					{
					
						vcRpData = vcResponseBankRef.getData();
			 
						obResponse.setSubscriber_auth_result(vcRpData.getProfile_result_list());
						 
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
	
	 
	
	public List<SubscriberProfile> checkValidUsrProfileAuthList(ObProfileMaintRequest rq)
	{
		List<SubscriberProfile> subscriberProfileList = null;
		
		
		if(rq.getUser_profile_list()!=null && rq.getUser_profile_list().size()>0 && rq.getRecordIds()!=null && rq.getRecordIds().trim().length()>0)
		{
			
			String[] rcd_ids = rq.getRecordIds().trim().split(",");
			
			for(String id:rcd_ids)
			{
					//log.info("check id : ["+id+"]");
				
					for(SubscriberProfile tmp:rq.getUser_profile_list())
					{
						if(id.trim().equalsIgnoreCase(tmp.getPending_record_id().trim()))
						{
							if(subscriberProfileList==null)
							{
								subscriberProfileList = new ArrayList();
							}
							
							subscriberProfileList.add(tmp);
							break;
						}
						
					}
				
				
			}
			
			 
			
			
		}
		
		
		
		
		return subscriberProfileList;
	}
	
	
}
