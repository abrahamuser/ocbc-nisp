package com.silverlake.mleb.mcb.module.func.deviceBinding;

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
import com.silverlake.mleb.mcb.bean.ObAuthenticationRequest;
import com.silverlake.mleb.mcb.bean.ObAuthenticationResponse;
import com.silverlake.mleb.mcb.bean.ObHeaderResponse;
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
public class DeviceCIFBindingTAC extends FuncServices  
{

	private static Logger log = LogManager.getLogger(DeviceCIFBindingTAC.class);
	
	
	@Autowired
	MLEBMIBDAO dao;
	
	
	@Autowired
	GeneralCodeDAO gnDao;
	 
	
	@Autowired ApplicationContext appContext;
	
	public MICBResponseBean process(MICBRequestBean arg0) {
		// TODO Auto-generated method stub
		
		
	
		MICBResponseBean response = new MICBResponseBean();
		ObAuthenticationResponse authorazationCodeResponse = new ObAuthenticationResponse();
		authorazationCodeResponse.setObHeader(new ObHeaderResponse());
		authorazationCodeResponse.setUserContext(new ObUserContext());
		int mibTagResentCount = 0; 
		
		try {
			
			
			
			

			ObAuthenticationRequest requestData = (ObAuthenticationRequest) arg0.getBDObject();			
		
			
			
			log.info("-----mib tag resendCount getRequest----->"+requestData.getResendTagCount()); 
			mibTagResentCount = requestData.getResendTagCount() + 1; 
			log.info("-----mib tag resendCount AddUp----->"+mibTagResentCount); 
			List<GeneralCode> bidingSMSConf = gnDao.getBindingSMSConf();
			
			String limitSendSMS = "5";
			String limitResendtimeIntervalInSeconds = "30";
			Date lastResendDate = null;
			SimpleDateFormat dt = new SimpleDateFormat( MiBConstant.DB_DATETIME_FORMAT);
			lastResendDate = requestData.getResendTagLastDate()==null?null:dt.parse(requestData.getResendTagLastDate());
			String ipAddress = requestData.getObHeader() == null ? null : requestData.getObHeader().getIp();
			
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
			
			
			authorazationCodeResponse = checkResendCondition(authorazationCodeResponse,limitSendSMS,limitResendtimeIntervalInSeconds,mibTagResentCount,lastResendDate);
			if(authorazationCodeResponse.getObHeader().getStatusCode()==null)
			{
				String deviceId = arg0.getDeviceBean()==null?null:arg0.getDeviceBean().getDeviceId();
				//call omni send sms
				TokenRequestData vcLoginRequest = new TokenRequestData();
				vcLoginRequest.setOrg_cd(requestData.getObUser().getOrgId());
				vcLoginRequest.setUsr_cd(requestData.getObUser().getUserId());
				vcLoginRequest.setDevice_id(deviceId);
				vcLoginRequest.setRequest_type("OTP");
				
				TokenResponseData vcResponseData = new TokenResponseData();
				VCService tokenService = new VCService(appContext);
				VCResponse vcResponse = tokenService.callOmniVC(TokenRequestData.method_token_otp_request,vcLoginRequest, vcResponseData, requestData.getUserContext().getSessionId(), arg0.getTranxID(), ipAddress);
 
				if(vcResponse.getResponse_code().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS))
				{
					
					
					vcResponseData = (TokenResponseData) vcResponse.getData();
					
					authorazationCodeResponse.setResendTagMax(Integer.parseInt(limitSendSMS));
					authorazationCodeResponse.setResendTagInterval(Integer.parseInt(limitResendtimeIntervalInSeconds));
					authorazationCodeResponse.setResendTagCount(mibTagResentCount); 
					
					authorazationCodeResponse.setResendTagDateTime(dt.format(new Date()));
					String tokenRequestId = vcResponseData.getRequest_id();
					authorazationCodeResponse.setTokenReqId(tokenRequestId);
					
					authorazationCodeResponse.setDescriptions("");
					authorazationCodeResponse.setObUser(new ObUserDetail());
					authorazationCodeResponse.getObUser().setAccessId(requestData.getObUser().getAccessId());
					authorazationCodeResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
				}
				else
				{
					authorazationCodeResponse.getObHeader().setStatusCode(MiBConstant.MIB_OMNI_PREFIX+vcResponse.getResponse_code());
				}
				
			}
			 
			
			
			

		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//"ERROR.MIB.9999999"
			
		
			log.info(this.getClass().toString(), e);
			authorazationCodeResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			authorazationCodeResponse.getObHeader().setStatusMessage(e.getMessage());
	
		}
		
		response.setBDObject(authorazationCodeResponse);
		
		return response;
	}




	public ObAuthenticationResponse checkResendCondition(ObAuthenticationResponse response, String limitSendSMS,String limitResendtimeIntervalInSeconds, int mibTagResentCount, Date lastResendDate )
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
