package com.silverlake.mleb.mcb.module.func.token;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.mcb.bean.ObHeaderResponse;
import com.silverlake.mleb.mcb.bean.ObTokenRequest;
import com.silverlake.mleb.mcb.bean.ObTokenResponse;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.entity.dao.CustomerDAO;
import com.silverlake.mleb.mcb.entity.dao.GeneralCodeDAO;
import com.silverlake.mleb.mcb.module.common.FuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.common.VCService;
import com.silverlake.mleb.mcb.module.vc.token.Token;
import com.silverlake.mleb.mcb.module.vc.token.TokenRequestData;
import com.silverlake.mleb.mcb.module.vc.token.TokenResponseData;

/**
 * Purpose: used to update enrol software token
 * 2 Omni Web Services:
 * token/inquiry
 * token/default
 * 
 * @author AdamZalil
 *
 */
@Service
public class UpdateEnrolSwToken extends FuncServices  
{
	private static Logger log = LogManager.getLogger(UpdateEnrolSwToken.class);

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
			
			TokenRequestData vcTokenRequest = new TokenRequestData();
			vcTokenRequest.setOrg_cd(requestData.getObUser().getOrgId());
			vcTokenRequest.setUsr_cd(requestData.getObUser().getUserId());

			TokenResponseData vcTokenResponseData = new TokenResponseData();
			VCService tokenService = new VCService(appContext);
			VCResponse vcResponseInquiry = tokenService.callOmniVC(TokenRequestData.method_customer_token_inquiry,vcTokenRequest, vcTokenResponseData, 
					requestData.getUserContext().getSessionId(), arg0.getTranxID(), ipAddress);

			if(vcResponseInquiry.getResponse_code().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS))
			{
				vcTokenResponseData = (TokenResponseData) vcResponseInquiry.getData();
				if(vcTokenResponseData.getList_token()!=null)
				{
					obResponse.setList_token(new ArrayList());
					for (Token temp:vcTokenResponseData.getList_token()) {
						Token tokenList = new Token();
						BeanUtils.copyProperties(temp, tokenList);
						//checking HW and Default status
						if (tokenList.getToken_type().equalsIgnoreCase("HW") && tokenList.getIs_default().equalsIgnoreCase("Y")) {
							vcTokenRequest.setToken_type_old(tokenList.getToken_type());
							vcTokenRequest.setToken_type_new("SW");
							if(MiBConstant.LANG_EN.equalsIgnoreCase(arg0.getLocaleCode())) {
								vcTokenRequest.setLang("en");
							}else if(arg0 != null && arg0.getLocaleCode() != null && arg0.getLocaleCode().toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase())){
								vcTokenRequest.setLang("cn");
							}else {
								vcTokenRequest.setLang("id");
							}
							VCResponse vcResponseSwitch = tokenService.callOmniVC(VCMethodConstant.REST_METHODS.TOKEN_DEFAULT.getUri(),vcTokenRequest, 
									vcTokenResponseData, requestData.getUserContext().getSessionId(), arg0.getTranxID(), ipAddress);
							//proceed to ws for switch default
							if(vcResponseSwitch.getResponse_code().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS)) {
								vcTokenResponseData = (TokenResponseData) vcResponseSwitch.getData();
								obResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
							} else {
								obResponse.getObHeader().setStatusCode(MiBConstant.MIB_OMNI_PREFIX+vcResponseSwitch.getResponse_code());
								obResponse.getObHeader().setStatusMessage(vcResponseSwitch.getResponse_desc());
							}
							break;
						} else {
							obResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
						}
					}
				}
				else 
				{
					obResponse.getObHeader().setStatusCode(MiBConstant.MCB_NO_TOKEN_FOUND);
				}
			}
			else
			{
				obResponse.getObHeader().setStatusCode(MiBConstant.MIB_OMNI_PREFIX+vcResponseInquiry.getResponse_code());
				obResponse.getObHeader().setStatusMessage(vcResponseInquiry.getResponse_desc());
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
