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
import com.silverlake.mleb.mcb.entity.dao.CustomerDAO;
import com.silverlake.mleb.mcb.entity.dao.GeneralCodeDAO;
import com.silverlake.mleb.mcb.module.common.FuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.common.VCService;
import com.silverlake.mleb.mcb.module.vc.token.Token;
import com.silverlake.mleb.mcb.module.vc.token.TokenRequestData;
import com.silverlake.mleb.mcb.module.vc.token.TokenResponseData;
import com.silverlake.mleb.mcb.module.vc.user.List_device;

@Service
public class CustomerTokenInquiryRequest extends FuncServices  
{
	private static Logger log = LogManager.getLogger(CustomerTokenInquiryRequest.class);

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
			VCResponse vcResponse = tokenService.callOmniVC(TokenRequestData.method_customer_token_inquiry,vcTokenRequest, vcTokenResponseData, requestData.getUserContext().getSessionId(), arg0.getTranxID(), ipAddress);
			
			if(vcResponse.getResponse_code().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS))
			{
				vcTokenResponseData = (TokenResponseData) vcResponse.getData();
				if(vcTokenResponseData.getList_token()!=null)
				{
					obResponse.setList_token(new ArrayList());
					for (Token temp:vcTokenResponseData.getList_token()) {
						Token tokenList = new Token();
						
						if(temp.getDevice_information() != null && !temp.getDevice_information().isEmpty()) {
							for(List_device device : temp.getDevice_information()) {
								if(device.getExt_channel_list() != null && !device.getExt_channel_list().isEmpty()) {
									for(int i=0;i<device.getExt_channel_list().size();i++) {
										String extChannel = device.getExt_channel_list().get(i);
										if(extChannel.equalsIgnoreCase(MiBConstant.CHANNEL001)) {
											device.getExtChannelListDesc().put(MiBConstant.CHANNEL001, MiBConstant.CHANNEL001_DESC);
											
										} else if (extChannel.equalsIgnoreCase(MiBConstant.CHANNEL002)) {
											device.getExtChannelListDesc().put(MiBConstant.CHANNEL002, MiBConstant.CHANNEL002_DESC);
											
										} else if (extChannel.equalsIgnoreCase(MiBConstant.CHANNEL003)) {
											device.getExtChannelListDesc().put(MiBConstant.CHANNEL003, MiBConstant.CHANNEL003_DESC);
											
										} else if (extChannel.equalsIgnoreCase(MiBConstant.CHANNEL004)) {
											device.getExtChannelListDesc().put(MiBConstant.CHANNEL004, MiBConstant.CHANNEL004_DESC);
											
										} else if (extChannel.equalsIgnoreCase(MiBConstant.CHANNEL005)) {
											device.getExtChannelListDesc().put(MiBConstant.CHANNEL005, MiBConstant.CHANNEL005_DESC);
										}
									}
								}
							}
						}
						BeanUtils.copyProperties(temp, tokenList);
						obResponse.getList_token().add(tokenList);
					}
					obResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
				}
				else 
				{
					obResponse.getObHeader().setStatusCode(MiBConstant.MCB_NO_TOKEN_FOUND);
				}
			}
			else
			{
				obResponse.getObHeader().setStatusCode(MiBConstant.MIB_OMNI_PREFIX+vcResponse.getResponse_code());
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
