package com.silverlake.mleb.mcb.module.func.admin;



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
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.common.VCService;







@Service
public class RetrieveProfileAuth extends FuncServices  
{

	private static Logger log = LogManager.getLogger(RetrieveProfileAuth.class);
	
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
			
			String pageSize = requestData.getPage_size()==null?"10":requestData.getPage_size();
			pageSize = pageSize.trim().length()==0?"10":pageSize;
			
			String pageNo = (String) (requestData.getPage_no()==null?"1":requestData.getPage_no());
			pageNo = pageNo.trim().length()==0?"1":pageNo;
			
			String ipAddress = requestData.getObHeader() == null ? null : requestData.getObHeader().getIp();
		 
			VCService usrService = new VCService(appContext);
			AuthRequestData userProfilePendingAuthRq = new AuthRequestData();
			userProfilePendingAuthRq.setOrg_cd(orgId);
			userProfilePendingAuthRq.setUsr_cd(usrId);
			userProfilePendingAuthRq.setPage_no(pageNo);
			userProfilePendingAuthRq.setPage_size(pageSize);
		  
			AuthResponseData userProfilePendingAuthRp = new AuthResponseData();
			VCResponse<AuthResponseData> vcResponse = 
					usrService.callOmniVC(
							AuthRequestData.method_auth_admin_userProfile_pending,
							userProfilePendingAuthRq, 
							userProfilePendingAuthRp, 
							requestData.getUserContext().getSessionId(), arg0.getTranxID(), ipAddress);
			
			if(vcResponse.getResponse_code().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS))
			{
				
				userProfilePendingAuthRp = vcResponse.getData();
				
				
				obResponse.setTotal_records(userProfilePendingAuthRp.getTotal_records());
				obResponse.setUser_profile_list(userProfilePendingAuthRp.getUser_profile_list());
 
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
