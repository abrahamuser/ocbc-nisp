package com.silverlake.mleb.mcb.module.func.token;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.mcb.bean.ObHeaderResponse;
import com.silverlake.mleb.mcb.bean.ObTokenRequest;
import com.silverlake.mleb.mcb.bean.ObTokenResponse;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.entity.CustomerProfile;
import com.silverlake.mleb.mcb.entity.DeviceProfile;
import com.silverlake.mleb.mcb.entity.dao.CustomerDAO;
import com.silverlake.mleb.mcb.entity.dao.DeviceCIFDao;
import com.silverlake.mleb.mcb.entity.dao.GeneralCodeDAO;
import com.silverlake.mleb.mcb.module.common.FuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.common.VCService;
import com.silverlake.mleb.mcb.module.vc.token.TokenRequestData;
import com.silverlake.mleb.mcb.module.vc.token.TokenResponseData;

@Service
public class OTPVerify extends FuncServices  
{
	private static Logger log = LogManager.getLogger(SoftwareTokenPreBind1Request.class);

	@Autowired
	CustomerDAO dao;

	@Autowired
	GeneralCodeDAO gnDao;

	@Autowired
	DeviceCIFDao deviceDao;
	
	@Autowired ApplicationContext appContext;

	public MICBResponseBean process(MICBRequestBean arg0) {
		
		MICBResponseBean response = new MICBResponseBean();
		ObTokenResponse obResponse = new ObTokenResponse();
		obResponse.setObHeader(new ObHeaderResponse());

		try {
			ObTokenRequest requestData = (ObTokenRequest) arg0.getBDObject();	
			String device_id = arg0.getDeviceBean()==null?null:arg0.getDeviceBean().getDeviceId();
			String ipAddress = requestData.getObHeader() == null ? null : requestData.getObHeader().getIp();
			
			TokenRequestData vcTokenRequest = new TokenRequestData();
			
			if(requestData.getObUser() != null) {
				vcTokenRequest.setOrg_cd(requestData.getObUser().getOrgId());
				vcTokenRequest.setUsr_cd(requestData.getObUser().getUserId());
			} else {
				//for Appli to support pre login otp verify
				DeviceProfile devProfile = deviceDao.getCIFByDeviceID(device_id);
				if(null!=devProfile)
				{
					String cif = devProfile.getCif();
					CustomerProfile custProfile = dao.getCustomerProfileByCif(cif);
					if(null!=custProfile)
					{
						vcTokenRequest.setOrg_cd(custProfile.getOrgId());
						vcTokenRequest.setUsr_cd(custProfile.getLoginId());
					}
				}
			}
			
			vcTokenRequest.setRequest_id(requestData.getRequest_id());
			vcTokenRequest.setOtp(requestData.getOtp());
			vcTokenRequest.setDevice_id(device_id);
			/*vcTokenRequest.setRequestId_stats(requestData.getRequestId_stats());*/
			if (requestData.getRequestId_stats().equalsIgnoreCase("FALSE")) {
				vcTokenRequest.setRequest_id(null);
			}

			TokenResponseData vcTokenResponseData = new TokenResponseData();
			VCService tokenService = new VCService(appContext);
			VCResponse vcResponse = tokenService.callOmniVC(TokenRequestData.method_token_otp_verify,vcTokenRequest, vcTokenResponseData, requestData.getUserContext().getSessionId(), arg0.getTranxID(), ipAddress);
			
			if(vcResponse.getResponse_code().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS))
			{
				obResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
			}
			else
			{
				obResponse.getObHeader().setStatusCode(MiBConstant.MIB_OMNI_PREFIX+vcResponse.getResponse_code());
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
