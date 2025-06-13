package com.silverlake.mleb.ccmcb.module;



import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.ws.WebServiceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.silverlake.mleb.ccmcb.constant.WSConstant;
import com.silverlake.mleb.ccmcb.util.HLBDateUtil;
import com.silverlake.mleb.ccmcb.util.SecurityUtil;
import com.silverlake.mleb.ccmcb.util.UserLoginSession;
import com.silverlake.mleb.mcb.bean.ObAuthenticationRequest;
import com.silverlake.mleb.mcb.bean.ObAuthenticationResponse;
import com.silverlake.mleb.mcb.bean.ObRequest;
import com.silverlake.mleb.mcb.constant.MiBConstant;




public class PerformLogout extends MiBServices
{
	

	public PerformLogout(WebServiceContext wsContext) {
		super(wsContext);
		// TODO Auto-generated constructor stub
	}

	UserLoginSession userloginsession = new UserLoginSession();
	SecurityUtil secUtil = new SecurityUtil();
	private static Logger log = LogManager.getLogger(PerformLogout.class);
	


	ObAuthenticationRequest obRequest;
	private String userID;
	@Override
	public ObRequest getBDObject() {
		func_id = MiBConstant.FUNC_MCB_LOGOUT;
		return obRequest;
	}


	@Override
	public void processResponse() {
		// TODO Auto-generated method stub
		log.debug("vvvvvvvv : "+httpSession.getAttribute(SecurityUtil.USER_SUCCESSFUL_LOGIN_TIME));
		userloginsession.removeSessionUser(httpSession);
		
		/*ObAuthenticationResponse authResponse;
		if(obResponse instanceof ObAuthenticationResponse)
		{
			authResponse = (ObAuthenticationResponse) obResponse;
		}
		else
		{
			authResponse = new ObAuthenticationResponse();
			authResponse.setObHeader(obResponse.getObHeader());
			authResponse.setObUser(obResponse.getObUser());
			authResponse.setUserContext(obResponse.getUserContext());
			
			
			obResponse = new ObAuthenticationResponse();
			authResponse = (ObAuthenticationResponse) obResponse;
		}*/
		log.info(""+obResponse.getClass());
		if(obResponse instanceof ObAuthenticationResponse)
		{
			ObAuthenticationResponse authResponse = (ObAuthenticationResponse) obResponse;
	
			String locale = obRequest.getUserContext().getLocaleCode(); 
			HLBDateUtil hlbDateUtil = new HLBDateUtil();  
			
			if(obResponse.getObHeader().getStatusCode().equalsIgnoreCase(MiBConstant.MIB_COMMON_SUCCESS)   )
			{
				
				if(httpSession.getAttribute(SecurityUtil.USER_SUCCESSFUL_LOGIN_TIME) != null)
				{
					
					
					Date loginDate = (Date)httpSession.getAttribute(SecurityUtil.USER_SUCCESSFUL_LOGIN_TIME);
					Date logoutDate = new Date();
					
					long loginDur = (((logoutDate.getTime() - loginDate.getTime()) / 60)/1000);
					
					String totalDur = "";
					if(loginDur > 60 && loginDur/60 > 0)
					{
						long hours;
						long mins;
						hours = loginDur/60;
						mins = loginDur - (hours * 60);
						
						
						
						if(locale.equalsIgnoreCase(MiBConstant.LOCALE_IN)){
							
							totalDur = hours+MiBConstant.HOUR_IN+" "+mins+MiBConstant.MINUTE_IN;
							
							if(hours < 10)
								totalDur = "0"+hours+MiBConstant.HOUR_IN+" ";
							else
								totalDur = hours+MiBConstant.HOUR_IN+" ";
							
							if(mins < 10)
								totalDur = totalDur +"0"+mins+MiBConstant.MINUTE_IN;
							else
								totalDur = totalDur + mins+MiBConstant.MINUTE_IN;
							
						}else{
							
							totalDur = hours+"h "+mins+"minutes";
							
							if(hours < 10)
								totalDur = "0"+hours+"h ";
							else
								totalDur = hours+"h";
							
							if(mins < 10)
								totalDur = totalDur +"0"+mins+"minutes";
							else
								totalDur = totalDur + mins+"minutes";
						}
	
					}
					else
					{
						if(locale.equalsIgnoreCase(MiBConstant.LOCALE_IN)){
							
							if(loginDur < 10)
								totalDur = "00 "+MiBConstant.HOUR_IN+"  0"+loginDur+" "+MiBConstant.MINUTE_IN;
							else
								totalDur = "00 "+MiBConstant.HOUR_IN+"  "+loginDur+" "+MiBConstant.MINUTE_IN;
							
						}else{
							
							if(loginDur < 10)
								totalDur = "00 h  0"+loginDur+" minutes";
							else
								totalDur = "00 h  "+loginDur+" minutes";
						}
						
						
						
						
					}
					
					SimpleDateFormat dateFormat = new SimpleDateFormat(WSConstant.LOGOUT_DATE_FORMAT);
					authResponse.setLastLoginDateTime(hlbDateUtil.cambodiaDateFormat(dateFormat.format(loginDate), locale));
					authResponse.setLastLogoutDateTime(hlbDateUtil.cambodiaDateFormat(dateFormat.format(logoutDate), locale));
					authResponse.setLogoutDuration(totalDur);
			
					
				}
				httpSession.invalidate();
			}
		}
	}
	
	public void processData(boolean isListerner)
	{
		obRequest = new ObAuthenticationRequest();

		obRequest.setUserContext(userloginsession.getSessionKey(httpSession));
		obRequest.setObUser(userloginsession.getLoginSessionKeyData(httpSession));
	}

	
}
