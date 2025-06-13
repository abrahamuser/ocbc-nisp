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
import com.silverlake.mleb.mcb.entity.dao.CustomerDAO;
import com.silverlake.mleb.mcb.entity.dao.GeneralCodeDAO;
import com.silverlake.mleb.mcb.module.common.FuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.common.VCService;
import com.silverlake.mleb.mcb.module.vc.token.TokenRequestData;
import com.silverlake.mleb.mcb.module.vc.token.TokenResponseData;

@Service
public class SoftwareTokenEligibilityRequest extends FuncServices  
{
	private static Logger log = LogManager.getLogger(SoftwareTokenEligibilityRequest.class);

	@Autowired
	CustomerDAO dao;

	@Autowired
	GeneralCodeDAO gnDao;

	@Autowired ApplicationContext appContext;

	public MICBResponseBean process(MICBRequestBean arg0) {
		
		MICBResponseBean response = new MICBResponseBean();
		ObTokenResponse obResponse = new ObTokenResponse();
		obResponse.setObHeader(new ObHeaderResponse());

		try {
			ObTokenRequest requestData = (ObTokenRequest) arg0.getBDObject();	
			String ipAddress = requestData.getObHeader() == null ? null : requestData.getObHeader().getIp();
			
			/*if (requestData.getChannel()!=null) {
				if(requestData.getChannel().equalsIgnoreCase("") || requestData.getChannel().isEmpty()) {
					requestData.setChannel("MB");
				}
			}else {
				requestData.setChannel("MB");
			}*/

			TokenRequestData vcTokenRequest = new TokenRequestData();
			vcTokenRequest.setOrg_cd(requestData.getObUser().getOrgId());
			vcTokenRequest.setUsr_cd(requestData.getObUser().getUserId());
			/*vcTokenRequest.setChannel(requestData.getChannel());*/
			
			TokenResponseData vcTokenResponseData = new TokenResponseData();
			VCService tokenService = new VCService(appContext);
			VCResponse vcResponse = tokenService.callOmniVC(TokenRequestData.method_software_token_eligibility,vcTokenRequest, vcTokenResponseData, requestData.getUserContext().getSessionId(), arg0.getTranxID(), ipAddress);
			
			if(vcResponse.getResponse_code().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS))
			{
				vcTokenResponseData = (TokenResponseData) vcResponse.getData();
				obResponse.setIs_eligible(vcTokenResponseData.getIs_eligible());
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
