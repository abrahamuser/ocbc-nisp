package com.silverlake.mleb.mcb.module.func.token;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import com.silverlake.mleb.mcb.bean.ObUserContext;
import com.silverlake.mleb.mcb.bean.ObUserDetail;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.entity.GeneralCode;
import com.silverlake.mleb.mcb.entity.dao.GeneralCodeDAO;
import com.silverlake.mleb.mcb.entity.dao.MLEBMIBDAO;
import com.silverlake.mleb.mcb.module.common.FuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.common.VCService;
import com.silverlake.mleb.mcb.module.vc.token.TokenRequestData;
import com.silverlake.mleb.mcb.module.vc.token.TokenResponseData;

@Service
public class OTPRequest extends FuncServices  
{
	private static Logger log = LogManager.getLogger(OTPRequest.class);
	
	@Autowired
	MLEBMIBDAO dao;
	
	@Autowired
	GeneralCodeDAO gnDao;
	 
	@Autowired ApplicationContext appContext;
	
	public MICBResponseBean process(MICBRequestBean arg0) {

		MICBResponseBean response = new MICBResponseBean();
		ObTokenResponse otpResponse = new ObTokenResponse();
		otpResponse.setObHeader(new ObHeaderResponse());
		otpResponse.setUserContext(new ObUserContext());
		int mibTagResentCount = 0; 
		
		try {
			ObTokenRequest requestData = (ObTokenRequest) arg0.getBDObject();			
			String ipAddress = requestData.getObHeader() == null ? null : requestData.getObHeader().getIp();
			
			log.info("-----mib tag resendCount getRequest----->"+requestData.getResendTagCount()); 
			mibTagResentCount = requestData.getResendTagCount() + 1; 
			log.info("-----mib tag resendCount AddUp----->"+mibTagResentCount); 
			List<GeneralCode> bidingSMSConf = gnDao.getBindingSMSConf();
			
			String limitSendSMS = "5";
			String limitResendtimeIntervalInSeconds = "30";
			Date lastResendDate = null;
			SimpleDateFormat dt = new SimpleDateFormat( MiBConstant.DB_DATETIME_FORMAT);
			lastResendDate = requestData.getResendTagLastDate()==null?null:dt.parse(requestData.getResendTagLastDate());
			
			if (requestData.getRequest_type()!=null) {
				if(requestData.getRequest_type().equalsIgnoreCase("") || requestData.getRequest_type().isEmpty()) {
					requestData.setRequest_type("SMS");
				}
			}else {
				requestData.setRequest_type("SMS");
			}
			
			try
			{
				for(GeneralCode temp:bidingSMSConf)
				{
					if(temp.getGnCode().equalsIgnoreCase("dev.binding.max.sms.resend"))
					{
						limitSendSMS = temp.getGnCodeDescription();
					}
					else if(temp.getGnCode().equalsIgnoreCase("dev.binding.max.sms.timeInterval"))
					{
						limitResendtimeIntervalInSeconds =  temp.getGnCodeDescription();
					}
				}
			}catch(Exception e) {}
			
			
			otpResponse = checkResendCondition(otpResponse,limitSendSMS,limitResendtimeIntervalInSeconds,mibTagResentCount,lastResendDate);
			if(otpResponse.getObHeader().getStatusCode()==null)
			{
				String deviceId = arg0.getDeviceBean()==null?null:arg0.getDeviceBean().getDeviceId();
				//call omni send sms
				TokenRequestData vcOtpRequest = new TokenRequestData();
				vcOtpRequest.setOrg_cd(requestData.getObUser().getOrgId());
				vcOtpRequest.setUsr_cd(requestData.getObUser().getUserId());
				vcOtpRequest.setDevice_id(deviceId);
				vcOtpRequest.setRequest_type(requestData.getRequest_type());
				
				TokenResponseData vcResponseData = new TokenResponseData();
				VCService tokenService = new VCService(appContext);
				VCResponse vcResponse = tokenService.callOmniVC(TokenRequestData.method_token_otp_request,vcOtpRequest, vcResponseData, requestData.getUserContext().getSessionId(), arg0.getTranxID(), ipAddress);
 
				if(vcResponse.getResponse_code().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS))
				{
					vcResponseData = (TokenResponseData) vcResponse.getData();
					
					otpResponse.setResendTagMax(Integer.parseInt(limitSendSMS));
					otpResponse.setResendTagInterval(Integer.parseInt(limitResendtimeIntervalInSeconds));
					otpResponse.setResendTagCount(mibTagResentCount); 
					
					otpResponse.setResendTagDateTime(dt.format(new Date()));
					otpResponse.setRequest_id(vcResponseData.getRequest_id());
					otpResponse.setRequest_type(requestData.getRequest_type());

					otpResponse.setObUser(new ObUserDetail());
					otpResponse.getObUser().setAccessId(requestData.getObUser().getAccessId());
					otpResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
				}
				else
				{
					otpResponse.getObHeader().setStatusCode(MiBConstant.MIB_OMNI_PREFIX+vcResponse.getResponse_code());
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//"ERROR.MIB.9999999"
			log.info(this.getClass().toString(), e);
			otpResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			otpResponse.getObHeader().setStatusMessage(e.getMessage());
		}
		response.setBDObject(otpResponse);
		return response;
	}


	public ObTokenResponse checkResendCondition(ObTokenResponse response, String limitSendSMS,String limitResendtimeIntervalInSeconds, int mibTagResentCount, Date lastResendDate )
	{
		 
		if(Integer.parseInt(limitSendSMS)<mibTagResentCount)
		{
			response.getObHeader().setStatusCode(MiBConstant.MCB_RESEND_SMS_OVER_LIMIT);
			 
		}
		else if(lastResendDate!=null)
		{
			Date currentDate = new Date();
			long interval = currentDate.getTime()-lastResendDate.getTime();
			interval = interval/1000;
			if(interval<Integer.parseInt(limitResendtimeIntervalInSeconds))
			{
				response.getObHeader().setStatusCode(MiBConstant.MCB_RESEND_SMS_OVER_INTERVAL);
			}
		}
		return response;
	}
}
