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
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
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
public class SoftwareTokenPostBindRequest extends FuncServices  
{
	private static Logger log = LogManager.getLogger(SoftwareTokenPostBindRequest.class);

	@Autowired
	CustomerDAO dao;

	@Autowired
	GeneralCodeDAO gnDao;
	
	@Autowired
	DeviceCIFDao dvDao;

	@Autowired ApplicationContext appContext;

	public MICBResponseBean process(MICBRequestBean arg0) {
		
		MICBResponseBean response = new MICBResponseBean();
		ObTokenResponse obResponse = new ObTokenResponse();
		obResponse.setObHeader(new ObHeaderResponse());

		try {
			ObTokenRequest requestData = (ObTokenRequest) arg0.getBDObject();	
			String device_id = arg0.getDeviceBean()==null?null:arg0.getDeviceBean().getDeviceId();
			String ipAddress = requestData.getObHeader() == null ? null : requestData.getObHeader().getIp();
			
			/*if (requestData.getChannel()!=null) {
				if(requestData.getChannel().equalsIgnoreCase("") || requestData.getChannel().isEmpty()) {
					requestData.setChannel("MB");
				}
			}else {
				requestData.setChannel("MB");
			}
			
			if (requestData.getOperation_code()!=null) {
				if(requestData.getOperation_code().equalsIgnoreCase("") || requestData.getOperation_code().isEmpty()) {
					requestData.setOperation_code("000");
				}
			}else {
				requestData.setOperation_code("000");
			}*/

			TokenRequestData vcTokenRequest = new TokenRequestData();
			vcTokenRequest.setOrg_cd(requestData.getObUser().getOrgId());
			vcTokenRequest.setUsr_cd(requestData.getObUser().getUserId());
			vcTokenRequest.setDevice_id(device_id);
			vcTokenRequest.setDevice_finger_print(requestData.getDevice_finger_print());
			
			vcTokenRequest.setOtip_type(requestData.getOtip_type());
			vcTokenRequest.setAppli_name(requestData.getAppli_name());
			vcTokenRequest.setAppli_no(requestData.getAppli_no());
			vcTokenRequest.setSeq_no(requestData.getSeq_no());
			if(MiBConstant.LANG_EN.equalsIgnoreCase(arg0.getLocaleCode())) {
				vcTokenRequest.setLang("en");
			}else if(arg0 != null && arg0.getLocaleCode() != null && arg0.getLocaleCode().toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase())){
				vcTokenRequest.setLang("cn");
			}else {
				vcTokenRequest.setLang("id");
			}
			/*vcTokenRequest.setOperation_code(requestData.getOperation_code());
			vcTokenRequest.setChannel(requestData.getChannel());*/

			TokenResponseData vcTokenResponseData = new TokenResponseData();
			VCService tokenService = new VCService(appContext);
			VCResponse vcResponse = tokenService.callOmniVC(VCMethodConstant.REST_METHODS.TOKEN_SW_POST_BIND.getUri(),vcTokenRequest, vcTokenResponseData, requestData.getUserContext().getSessionId(), arg0.getTranxID(), ipAddress);
			
			if(vcResponse.getResponse_code().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS))
			{
				DeviceProfile dev = dvDao.getCIFByDeviceIDAndStatus(device_id);
				dev.setTokenFingerPrint(requestData.getDevice_finger_print());
				dvDao.updateEntity(dev);
				
				
				vcTokenResponseData = (TokenResponseData) vcResponse.getData();
				obResponse.setVelis_timestamp(vcTokenResponseData.getVelis_timestamp());
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
