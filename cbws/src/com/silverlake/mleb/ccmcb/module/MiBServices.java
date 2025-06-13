package com.silverlake.mleb.ccmcb.module;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.silverlake.micb.core.bean.AgentBean;
import com.silverlake.micb.core.bean.DeviceBean;
import com.silverlake.micb.core.bean.HeaderBean;
import com.silverlake.micb.core.bean.LocationBean;
import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.micb.core.bean.UserBean;
import com.silverlake.micb.core.util.EncryptionServices;
import com.silverlake.mleb.ccmcb.constant.WSConstant;
import com.silverlake.mleb.ccmcb.dispatcher.MlebDispatcher;
import com.silverlake.mleb.ccmcb.util.CacheMessageManager;
import com.silverlake.mleb.ccmcb.util.EhCacheMsgManager;
import com.silverlake.mleb.ccmcb.util.SecurityUtil;
import com.silverlake.mleb.ccmcb.util.UserLoginSession;
import com.silverlake.mleb.mcb.bean.ObAuthenticationResponse;
import com.silverlake.mleb.mcb.bean.ObDeviceProfileBean;
import com.silverlake.mleb.mcb.bean.ObHeaderRequest;
import com.silverlake.mleb.mcb.bean.ObHeaderResponse;
import com.silverlake.mleb.mcb.bean.ObRequest;
import com.silverlake.mleb.mcb.bean.ObResponse;
import com.silverlake.mleb.mcb.bean.ObUserContext;
import com.silverlake.mleb.mcb.constant.MiBConstant;

public abstract class MiBServices {

	
	public EhCacheMsgManager cacheMsgManager = new EhCacheMsgManager();
	public static CacheMessageManager property = new CacheMessageManager();
	private static Logger log = LogManager.getLogger(MiBServices.class);
	protected HttpServletRequest requestweb = null;
	protected String mib_status_code;
	protected String mib_status_msg;
	protected DeviceBean deviceBean;
	protected LocationBean locationBean;
	protected AgentBean agentBean;
	protected String requestInfo;
	//protected ObRequest obRequest;
	protected ObResponse obResponse;
	protected String func_id;
	protected HttpSession httpSession;
	protected String mlebTransactionID;
	protected WebServiceContext wsServiceContext;
	protected String httpAccept;
	protected String httpAcceptChars;
	protected String httpAcceptLanguage;	
	public abstract ObRequest getBDObject();
	
	//to set micbRequestbean 
	MICBRequestBean micbRequestx = null;
	MICBResponseBean rsBean = null;
	
	protected Object security_error_param;
	protected String errorRegex;
	
	public void setMicbRequestBean(MICBRequestBean micbRequest)
	{
		this.micbRequestx=micbRequest;
	}
	
	public MICBResponseBean getMicbResponseBean()
	{
		return rsBean;
	}
	
	
	public <T> T convertResp(ObResponse resp, Class<T> responseType) 
	{
		  
			 if(resp.getClass() == responseType)
			 {
				 
				 //log.info("Return Original Class : "+responseType.getName());
				 return (T) resp;
			 }
			 else
			 {
				 Object obj = null ;
				 
					try {
						obj =  responseType.newInstance();
						 
						ObResponse objx = (ObResponse) obj;
						objx.setObHeader(resp.getObHeader());
						return (T) objx;
						
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						log.info("failed to convert cc response : ",e);
						return null;
					} 
			 }
			 
			
			 
			
			
		 
		
	}
	
	
	
	public  <T> T processSend(Class<T> responseType)
	{
		String locale = "";
		try
		{
		
			Object micbObj = null==httpSession?null:httpSession.getAttribute(SecurityUtil.APPDEVICE_SESSION_TAG);
			
			ObRequest obRequest = null;
			MICBRequestBean micbRequest = new MICBRequestBean();
			
			if(null!=micbObj && micbObj instanceof MICBRequestBean)
			{
				micbRequest = (MICBRequestBean) micbObj;
				obRequest = micbRequest.getBDObject() instanceof ObRequest? (ObRequest) micbRequest.getBDObject():new ObRequest();
				if(deviceBean == null && micbRequest.getDeviceBean() != null){
					deviceBean = micbRequest.getDeviceBean();
				}
			}
			if(null!=micbRequestx)
			{
				micbRequest.setUserBean(micbRequestx.getUserBean());
				micbRequest.setSessionID(micbRequestx.getSessionID());
			}
			
			Object sec_err_recheck = getSecurityResponse(httpSession, micbRequest.getLocaleCode());
			
			if(null!=sec_err_recheck)
			{
				obResponse = (ObResponse) sec_err_recheck;
				//special handle for IOS due to ios not able initial new connection when -->login-->device untagged-->home-->postlogin function
				getBDObject();
				if(func_id.equalsIgnoreCase(MiBConstant.FUNC_LOGOUT))
				{
					httpSession.invalidate();
				}
			}
			
			if(null!=obResponse)
			{
				log.info("REQUEST REJECTED [ "+func_id+" ] : " +  obResponse.getObHeader().getStatusCode());
				log.info(" \n");
				return loadMsg(obResponse,obResponse.getParamValue(),micbRequest.getLocaleCode(),responseType);
			}
			getBDObject().setUserContext(getBDObject().getUserContext()==null?new ObUserContext():getBDObject().getUserContext());
			getBDObject().getUserContext().setLocaleCode(micbRequest.getLocaleCode());
			getBDObject().getUserContext().setCountryCode(null==micbRequest.getLocationBean()?null:micbRequest.getLocationBean().getCountry());
			locale = getBDObject().getUserContext().getLocaleCode();
			
		
			
			
			if(null==micbRequest.getUserBean()){micbRequest.setUserBean(new UserBean());}
			UserLoginSession userloginsession = new UserLoginSession();
			
			if(!func_id.equalsIgnoreCase(MiBConstant.FUNC_MCB_RESET_PASSWORD) && null!=httpSession && null!=userloginsession.getLoginSessionKeyData(httpSession) )
			{
				getBDObject().setObUser(userloginsession.getLoginSessionKeyData(httpSession));
			}
			
			
			getBDObject().setObHeader(new ObHeaderRequest());
			getBDObject().getObHeader().setClientSessionId(httpSession==null?"":httpSession.getId());
			getBDObject().getObHeader().setVersion(null==micbRequest.getHeaderBean()?null:micbRequest.getHeaderBean().getRequestInfo());
			
			if(null!=obRequest && obRequest.getObHeader() != null)
			{
				//getBDObject().getObHeader().setDeviceToken(obRequest.getObHeader().getDeviceToken());
				//getBDObject().getObHeader().setDevicePrint(obRequest.getObHeader().getDevicePrint());
				getBDObject().getObHeader().setPnsDeviceToken(obRequest.getObHeader().getPnsDeviceToken());
				//log.info("Process Send PNS Token : " + obRequest.getObHeader().getPnsDeviceToken());
				//getBDObject().getObHeader().setHttpAccept(httpAccept);
				//getBDObject().getObHeader().setHttpAcceptChars(httpAcceptChars);
				//getBDObject().getObHeader().setHttpAcceptLanguage(httpAcceptLanguage);
				getBDObject().getObHeader().setApp_mode(obRequest.getObHeader().getApp_mode());
				getBDObject().getObHeader().setIp(obRequest.getObHeader().getIp());
			}			
			
			micbRequest.setBDObject(getBDObject());
			
			String C_END_POINT = null;
			
			
			MlebDispatcher muleDispatcher = new MlebDispatcher();
			rsBean = muleDispatcher.sendToRemoteDispatcher(C_END_POINT, func_id, micbRequest);
			
			
			Object obj = null;
			
			
			if(null!=rsBean && null!=rsBean.getResponseCode() && rsBean.getResponseCode().equalsIgnoreCase("00000"))
			{
				obj = rsBean.getBDObject();
				
			}
			
			
			if(null!=obj && obj instanceof ObResponse)
			{
				obResponse = (ObResponse) obj;
				mlebTransactionID = rsBean.getTranxID();
				//log.info("Response class : -----------"+mlebTransactionID+"-------- "+obResponse.getClass());
			}
			else
			{
				obResponse = new ObResponse();
				obResponse.setObHeader(new ObHeaderResponse());
				
				if(null==rsBean)
				{
					obResponse.getObHeader().setStatusCode(WSConstant.CCWS_MIB_ERROR);
					log.info("BD ResponseCode ALTERED to "+  obResponse.getObHeader().getStatusCode());
				}
				else if(null==rsBean.getResponseCode())
				{
					obResponse.getObHeader().setStatusCode(WSConstant.CCWS_MIB_ERROR);
					log.info("BD ResponseCode ALTERED to "+  obResponse.getObHeader().getStatusCode());
				}
				else if (rsBean.getResponseCode().equalsIgnoreCase("00000"))
				{
					obResponse.getObHeader().setStatusCode(WSConstant.CCWS_MIB_ERROR);
					log.info("Response object is empty. Possible ObResponse object unsync in between CC and BD.");
					log.info("BD ResponseCode ALTERED to "+  obResponse.getObHeader().getStatusCode());
				}
				else
				{
					obResponse.getObHeader().setStatusCode("MLEB."+rsBean.getResponseCode());
					log.info("BD ResponseCode ALTERED to "+  obResponse.getObHeader().getStatusCode());
				}
				
				
			}
			
			//log.info("Response class : ------------------- "+obResponse.getClass());
			processResponse();
			
			
			//untag device checking
			if(obResponse.getObHeader().getStatusCode().equalsIgnoreCase(MiBConstant.MIB_STATUSCODE_SUCCESS_UNTAGGED_CURRENT_DEVICE))
			{
				List<ObDeviceProfileBean> tempDeviceBean = new ArrayList();
				if(obResponse instanceof ObAuthenticationResponse)
				{
					ObAuthenticationResponse untagResp = (ObAuthenticationResponse) obResponse;
					tempDeviceBean = untagResp.getDeviceData();
				}
				
				PerformLogout logout = new PerformLogout(wsServiceContext);
				logout.setHttpSession(httpSession);
				logout.processData(true);
				logout.processSend(ObAuthenticationResponse.class);
				
				//obResponse = new ObAuthenticationResponse();
				//log.debug("Logout : "+obLogoutRsp.getLogoutDuration());
				//obResponse = obLogoutRsp;
				//((ObAuthenticationResponse)obResponse).setDeviceData(tempDeviceBean);
				//obResponse.getObHeader().setStatusCode(MiBConstant.MIB_STATUSCODE_SUCCESS_UNTAGGED_CURRENT_DEVICE);
			}
			else if(obResponse.getObHeader().getStatusCode().equalsIgnoreCase(MiBConstant.MIB_FUZION_ERROR_PREFIX+"ERRORXXX"))
			{
				userloginsession.removeSessionUser(httpSession);
				
			}
			
		}
		catch(Exception ex)
		{
//			ex.printStackTrace();
			log.error("ERROR ", ex);
			obResponse = new ObResponse();
			obResponse.setObHeader(new ObHeaderResponse());
			obResponse.getObHeader().setStatusCode(WSConstant.CCWS_COMMON_ERROR);
			log.error("Encountered error of MiBServices due to "+ex+" ["+ex.getMessage()+"], returning "+obResponse.getObHeader().getStatusCode());
		}
		

		
		
		return loadMsg(obResponse,obResponse.getParamValue(),locale,responseType);
	}
	
	//for pre-login module
	public   <T> T processSend(boolean checkTimeOut, Class<T> responseType)
	{
		String locale = "";
		try
		{
			if(checkTimeOut){
				Object micbObj = null==httpSession?null:httpSession.getAttribute(SecurityUtil.APPDEVICE_SESSION_TAG);
				
				ObRequest obRequest = null;
				MICBRequestBean micbRequest = new MICBRequestBean();
				
				if(null!=micbObj && micbObj instanceof MICBRequestBean)
				{
					micbRequest = (MICBRequestBean) micbObj;
					obRequest = micbRequest.getBDObject() instanceof ObRequest? (ObRequest) micbRequest.getBDObject():new ObRequest();
					if(deviceBean == null && micbRequest.getDeviceBean() != null){
						deviceBean = micbRequest.getDeviceBean();
					}
				}
				if(null!=micbRequestx)
				{
					micbRequest.setUserBean(micbRequestx.getUserBean());
					micbRequest.setSessionID(micbRequestx.getSessionID());
				}
				
				Object sec_err_recheck = getSecurityResponse(httpSession, micbRequest.getLocaleCode());
				
				if(null!=sec_err_recheck)
				{
					obResponse = (ObResponse) sec_err_recheck;
					//special handle for IOS due to ios not able initial new connection when -->login-->device untagged-->home-->postlogin function
					getBDObject();
					if(func_id.equalsIgnoreCase(MiBConstant.FUNC_LOGOUT))
					{
						httpSession.invalidate();
					}
				}
				
				if(null!=obResponse)
				{
					log.info("REQUEST REJECTED [ "+func_id+" ] : " +  obResponse.getObHeader().getStatusCode());
					log.info(" \n");
					return loadMsg(obResponse,obResponse.getParamValue(),micbRequest.getLocaleCode(),responseType);
				}
				getBDObject().setUserContext(getBDObject().getUserContext()==null?new ObUserContext():getBDObject().getUserContext());
				getBDObject().getUserContext().setLocaleCode(micbRequest.getLocaleCode());
				getBDObject().getUserContext().setCountryCode(null==micbRequest.getLocationBean()?null:micbRequest.getLocationBean().getCountry());
				locale = getBDObject().getUserContext().getLocaleCode();
				
			
				
				
				if(null==micbRequest.getUserBean()){micbRequest.setUserBean(new UserBean());}
				UserLoginSession userloginsession = new UserLoginSession();
				
				if(null!=httpSession && null!=userloginsession.getLoginSessionKeyData(httpSession) )
				{
					getBDObject().setObUser(userloginsession.getLoginSessionKeyData(httpSession));
				}
				
				
				getBDObject().setObHeader(new ObHeaderRequest());
				getBDObject().getObHeader().setClientSessionId(httpSession==null?"":httpSession.getId());
				
				if(null!=obRequest && obRequest.getObHeader() != null)
				{
					//getBDObject().getObHeader().setDeviceToken(obRequest.getObHeader().getDeviceToken());
					//getBDObject().getObHeader().setDevicePrint(obRequest.getObHeader().getDevicePrint());
					getBDObject().getObHeader().setPnsDeviceToken(obRequest.getObHeader().getPnsDeviceToken());
					//log.info("Process Send PNS Token : " + obRequest.getObHeader().getPnsDeviceToken());
					//getBDObject().getObHeader().setHttpAccept(httpAccept);
					//getBDObject().getObHeader().setHttpAcceptChars(httpAcceptChars);
					//getBDObject().getObHeader().setHttpAcceptLanguage(httpAcceptLanguage);
				}			
				
				micbRequest.setBDObject(getBDObject());
				
				String C_END_POINT = null;
				
				
				MlebDispatcher muleDispatcher = new MlebDispatcher();
				rsBean = muleDispatcher.sendToRemoteDispatcher(C_END_POINT, func_id, micbRequest);
				
				
				Object obj = null;
				
				
				if(null!=rsBean && null!=rsBean.getResponseCode() && rsBean.getResponseCode().equalsIgnoreCase("00000"))
				{
					obj = rsBean.getBDObject();
					
				}
				
				
				if(null!=obj && obj instanceof ObResponse)
				{
					obResponse = (ObResponse) obj;
					mlebTransactionID = rsBean.getTranxID();
				}
				else
				{
					obResponse = new ObResponse();
					obResponse.setObHeader(new ObHeaderResponse());
					
					if(null==rsBean)
					{
						obResponse.getObHeader().setStatusCode(WSConstant.CCWS_MIB_ERROR);
						log.info("BD ResponseCode ALTERED to "+  obResponse.getObHeader().getStatusCode());
					}
					else if(null==rsBean.getResponseCode())
					{
						obResponse.getObHeader().setStatusCode(WSConstant.CCWS_MIB_ERROR);
						log.info("BD ResponseCode ALTERED to "+  obResponse.getObHeader().getStatusCode());
					}
					else if (rsBean.getResponseCode().equalsIgnoreCase("00000"))
					{
						obResponse.getObHeader().setStatusCode(WSConstant.CCWS_MIB_ERROR);
						log.info("Response object is empty. Possible ObResponse object unsync in between CC and BD.");
						log.info("BD ResponseCode ALTERED to "+  obResponse.getObHeader().getStatusCode());
					}
					else
					{
						obResponse.getObHeader().setStatusCode("MLEB."+rsBean.getResponseCode());
						log.info("BD ResponseCode ALTERED to "+  obResponse.getObHeader().getStatusCode());
					}
				}
				
				processResponse();
				
				
				//untag device checking
				if(obResponse.getObHeader().getStatusCode().equalsIgnoreCase(MiBConstant.MIB_STATUSCODE_SUCCESS_UNTAGGED_CURRENT_DEVICE))
				{
					List<ObDeviceProfileBean> tempDeviceBean = new ArrayList();
					if(obResponse instanceof ObAuthenticationResponse)
					{
						ObAuthenticationResponse untagResp = (ObAuthenticationResponse) obResponse;
						tempDeviceBean = untagResp.getDeviceData();
					}
					
					PerformLogout logout = new PerformLogout(wsServiceContext);
					logout.setHttpSession(httpSession);
					logout.processData(true);
					 logout.processSend(responseType);
					
					//obResponse = new ObAuthenticationResponse();
					//log.debug("Logout : "+obLogoutRsp.getLogoutDuration());
					//obResponse = obLogoutRsp;
					//((ObAuthenticationResponse)obResponse).setDeviceData(tempDeviceBean);
					//obResponse.getObHeader().setStatusCode(MiBConstant.MIB_STATUSCODE_SUCCESS_UNTAGGED_CURRENT_DEVICE);
				}
				else if(obResponse.getObHeader().getStatusCode().equalsIgnoreCase(MiBConstant.MIB_FUZION_ERROR_PREFIX+"ERRORXXX"))
				{
					userloginsession.removeSessionUser(httpSession);
					
				}
			}		
			else{
				obResponse = new ObResponse();
				obResponse.setObHeader(new ObHeaderResponse());
				obResponse.getObHeader().setStatusCode(WSConstant.CCWS_INVALID_PRE_SESSION);
			}
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			obResponse = new ObResponse();
			obResponse.setObHeader(new ObHeaderResponse());
			obResponse.getObHeader().setStatusCode(WSConstant.CCWS_COMMON_ERROR);
			log.error("Encountered error of MiBServices due to "+ex+" ["+ex.getMessage()+"], returning "+obResponse.getObHeader().getStatusCode());
		}
		
		return loadMsg(obResponse,obResponse.getParamValue(),locale,responseType);
	}
	
	public <T> T loadMsg(ObResponse obRsp, String[] paramValue, String locale, Class<T> responseType)
	{
		//	
		String statusCode = obRsp.getObHeader().getStatusCode();
		String statusMessage = obRsp.getObHeader().getStatusMessage();
		String responseTime = obRsp.getObHeader().getResponseTime();
		String asOfDate = obRsp.getObHeader().getAsOfDate(); 
		
		//refresh CCWS error msg mapping handler
		if(null!=obResponse.getObHeader().getServiceMsg() && obResponse.getObHeader().getServiceMsg().equalsIgnoreCase(MiBConstant.RESET_ERROR_MSG))
		{
			//property.reloadMsg();
			obResponse.getObHeader().setServiceMsg(null);
		}
		
		
		if(null!=statusMessage && statusMessage.equalsIgnoreCase("ERR_SEC_SESSION_INVALID"))
		{
			obRsp.getObHeader().setStatusCode(SecurityUtil.SECURITY_ERROR_SESSION_REMOVED);
			statusCode = SecurityUtil.SECURITY_ERROR_SESSION_REMOVED;
		}
		
		
		
		//String message = property.getProperty(statusCode,locale);
		String message = cacheMsgManager.getMsg(statusCode, locale);
		if(null==message || message.length()==0)
		{
			if(statusMessage==null || statusMessage.isEmpty())
			{
				String General = cacheMsgManager.getMsg(MiBConstant.MIB_GENERAL_ERROR,locale);
				if(null==General || General.length()==0)
				{
					General = statusCode;
				}
				
				obRsp.getObHeader().setStatusMessage(General);
			}	
		}
		else
		{

			if(statusCode.equalsIgnoreCase(MiBConstant.MIB_INVALID_INPUT_CHARACTER_ELEVEN) &&
			   security_error_param!=null && !security_error_param.toString().isEmpty())
			{
				if(errorRegex == null || errorRegex.isEmpty()) errorRegex = SecurityUtil.getValidDescReadable();
				String msg = cacheMsgManager.getMsg(MiBConstant.MIB_INVALID_INPUT_CHARACTER_ELEVEN,locale);
				if(msg != null){
					String s = MessageFormat.format(
							cacheMsgManager.getMsg(MiBConstant.MIB_INVALID_INPUT_CHARACTER_ELEVEN,locale), security_error_param.toString(), errorRegex);
					obResponse.getObHeader().setStatusMessage(s);
				}
			}else{
				obRsp.getObHeader().setStatusMessage(message);
			}
			
		}
		
		
		
		//response timestamp 
		String fmat = "yyyy-MM-dd HH:mm:ss";
		String fmat2 = "yyyy-MM-dd HH:mm";
		String fmat3 = "dd-MM-yyyy HH:mm";
		Locale malaysia = new Locale("ms","MY","MY");
		SimpleDateFormat refDateTImeFormat = new SimpleDateFormat(fmat,malaysia);
		SimpleDateFormat refDateTImeFormat2 = new SimpleDateFormat(fmat2,malaysia);
		SimpleDateFormat refDateTImeFormat3 = new SimpleDateFormat(fmat3,malaysia);
		responseTime = refDateTImeFormat.format(new Date());
		asOfDate = refDateTImeFormat3.format(new Date()); 

		
		obRsp.getObHeader().setResponseTime(responseTime); 
		obRsp.getObHeader().setAsOfDate(asOfDate); 
		
		
		
		if(null!=paramValue)
		{
			String rspMsgData = obRsp.getObHeader().getStatusMessage();
			for(int i=0;i<paramValue.length;i++)
			{
				if(null!= paramValue[i])
				{
					rspMsgData = rspMsgData.replaceAll("\\{"+i+"\\}", paramValue[i]);
				}
			}
			obRsp.getObHeader().setStatusMessage(rspMsgData);
			obRsp.setParamValue(null);
		}
		
		
		
		
		return convertResp(obRsp,responseType);
	}
	
	
	
	
	
	/*public MiBServices(HttpSession session)
	{

		
		Object device = session.getAttribute(WSConstant.MIB_HTTP_SESSION_DEVICE_TAG);
		Object location = session.getAttribute(WSConstant.MIB_HTTP_SESSION_LOCATION_TAG);
		Object requestInfoData = session.getAttribute(WSConstant.MIB_HTTP_SESSION_REQUEST_INFO);
		
		
		if(null!=device && device instanceof DeviceBean)
		{
			deviceBean = (DeviceBean) device;
		}
		if(null!=location && location instanceof LocationBean)
		{
			locationBean = (LocationBean) location;
		}
		if(null!=requestInfoData)
		{
			requestInfo = (String) requestInfoData;
		}
		
		httpSession = session;
		obResponse = getSecurityResponse(session);
		
	}*/
	
	
	
	public MiBServices(WebServiceContext wsContext)
	{
		if(null!=wsContext)
		{
			wsServiceContext = wsContext;
			MessageContext msgCtxt = wsContext.getMessageContext();
			HttpServletRequest httpRequest = (HttpServletRequest)msgCtxt.get(MessageContext.SERVLET_REQUEST);
			HttpSession session = httpRequest.getSession();
			
			Object device = session.getAttribute(WSConstant.MIB_HTTP_SESSION_DEVICE_TAG);
			Object location = session.getAttribute(WSConstant.MIB_HTTP_SESSION_LOCATION_TAG);
			Object requestInfoData = session.getAttribute(WSConstant.MIB_HTTP_SESSION_REQUEST_INFO);
		
			if(null!=device && device instanceof DeviceBean)
			{
				deviceBean = (DeviceBean) device;
			}
			if(null!=location && location instanceof LocationBean)
			{
				locationBean = (LocationBean) location;
			}
			if(null!=requestInfoData)
			{
				requestInfo = (String) requestInfoData;
			}
			
			httpSession = session;
			Object sec_err_recheck = getSecurityResponse(httpSession, null);
			if(null!=sec_err_recheck)
			{
				obResponse = (ObResponse) sec_err_recheck;
			}
			
			String ssid = httpSession.getId();
			String showssid = "********";
			if(ssid!=null && ssid.length()>24)
			{
				String tmp1 = ssid.substring(0, 8);
				String tmp2 = ssid.substring(ssid.length()-8, ssid.length());
				showssid = tmp1+showssid+tmp2;
			}
			
			
			log.info("ssid:["+showssid+"]");
		}
		
	}
	

	
	public void setTrackData(DeviceBean deviceBean,LocationBean locationBean)
	{
		this.deviceBean = deviceBean;
		this.locationBean = locationBean;
	}
	
	public MICBRequestBean getRequestBean() 
	{

		MICBRequestBean request = new MICBRequestBean();
		request.setDeviceBean(deviceBean);
		request.setLocationBean(locationBean);
		request.setHeaderBean(new HeaderBean());
		request.getHeaderBean().setRequestInfo(requestInfo);
		AgentBean agentBean = new AgentBean();
		try {
			agentBean.setIP(null==requestweb?InetAddress.getLocalHost().getHostAddress().toString():requestweb.getRemoteAddr());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String useragent = null==requestweb?"":requestweb.getHeader("User-Agent");
		agentBean.setUserAgent(useragent);
		request.setAgentBean(agentBean);
		request.setSessionID(null==requestweb?"":requestweb.getSession().getId());
		return request;
	}
	

	public ObResponse getSecurityResponse(HttpSession session, String locale)
	{
		Object security_error = null==session?null:session.getAttribute(SecurityUtil.SECURITY_SESSION_ERROR_TAG);
		security_error_param = null==session?null:session.getAttribute(SecurityUtil.SECURITY_SESSION_ERROR_PARAM_TAG);
		errorRegex = ((null==session || null==session.getAttribute(SecurityUtil.SECURITY_SESSION_ERROR_REGEX_TAG))?
				null:session.getAttribute(SecurityUtil.SECURITY_SESSION_ERROR_REGEX_TAG).toString());
		
		if(null!=security_error && security_error.toString().length()>0)
		{
			log.debug("SECURITY CHECK : INVALID ACTION REQUEST FOUND : ["+security_error.toString()+"]");
			ObResponse obResponse = new ObResponse();
			obResponse.setObHeader(new ObHeaderResponse());
			obResponse.getObHeader().setStatusCode(security_error.toString());
			
			if(null!=security_error_param && security_error_param.toString().length()>0)
			{
				obResponse.setParamValue(new String[] { mapKeyField("key."+security_error_param.toString(), locale) });
			}
			
			return obResponse;
		}
		
		
		return null;
		
	}


	private String mapKeyField(String key, String locale)
	{
		String keyDesc = key;
		if(key!=null && key.length()>0 && locale!=null)
			keyDesc = property.getProperty(key, locale);
		
		return keyDesc;
	}
	
	public abstract void processResponse();


	public String getMlebTransactionID() {
		return mlebTransactionID;
	}


	public void setMlebTransactionID(String mlebTransactionID) {
		this.mlebTransactionID = mlebTransactionID;
	}


	public HttpSession getHttpSession() {
		return httpSession;
	}


	public void setHttpSession(HttpSession httpSession) {
		this.httpSession = httpSession;
	}



	public ObResponse getObResponse() {
		return obResponse;
	}



	public void setObResponse(ObResponse obResponse) {
		this.obResponse = obResponse;
	}
	
	
	protected String encryptString(String key, String value){
		if(value == null || value.isEmpty()){
			return value;
		}
		try{
			EncryptionServices encryptSvs = new EncryptionServices();
			String encrptedValue = encryptSvs.AESEncrypt(key, value);
		    return encrptedValue;
		}catch(Exception e){
			log.error("Unable ot encrypt ("+value+") due to "+e.getMessage());
			return value;
		}
	}
	
}
