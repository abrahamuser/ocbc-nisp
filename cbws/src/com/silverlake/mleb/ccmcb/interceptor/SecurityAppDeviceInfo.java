package com.silverlake.mleb.ccmcb.interceptor;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPHeader;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.silverlake.micb.core.bean.AgentBean;
import com.silverlake.micb.core.bean.DeviceBean;
import com.silverlake.micb.core.bean.HeaderBean;
import com.silverlake.micb.core.bean.LocationBean;
import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.mleb.ccmcb.util.PropertiesManager;
import com.silverlake.mleb.ccmcb.util.SecurityUtil;
import com.silverlake.mleb.mcb.bean.ObHeaderRequest;
import com.silverlake.mleb.mcb.bean.ObRequest;



public class SecurityAppDeviceInfo implements SOAPHandler<SOAPMessageContext>
{

	private static ConcurrentMap recordsCSRF = new ConcurrentHashMap();
	public static PropertiesManager property = new PropertiesManager();
	private static Logger log = LogManager.getLogger(SecurityAppDeviceInfo.class);
	public Set<QName> getHeaders() {
		return null;
	}

	public void close(MessageContext context) {
		
	}

	public boolean handleFault(SOAPMessageContext context) {
		return true;
	}
	
	public boolean handleMessage(SOAPMessageContext context)
	{
		SecurityUtil secUtil = new SecurityUtil();
		Gson gsonLog = new GsonBuilder().setPrettyPrinting().create();
		Boolean outbound = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		
		HttpServletRequest request =   (HttpServletRequest)context.get(MessageContext.SERVLET_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(MessageContext.SERVLET_RESPONSE);
		HttpSession session = request.getSession();
		ObRequest obRequest = new ObRequest();
		obRequest.setObHeader(new ObHeaderRequest());
		
		//log.debug("WSInterceptor :: SESSION ID :: "+outbound+" :: "+session.getId());
		
		
		if (outbound && secUtil.is_csrf_check())
		{
			try
			{
				//clear error
				secUtil.removeSecurityError(session);
	      
			}
			catch(Exception e)
			{
				//log.debug("Exception in MiBWSRequestChain.handleMessage() :: "+e);
				e.printStackTrace();
			}
		}
		else
		{
			log.debug("INCOMING SOAP MESSAGE : AppDeviceInfo");
			
			try
			{
				
				SOAPHeader header = context.getMessage().getSOAPHeader();
	            SOAPBody body = context.getMessage().getSOAPBody();
	            String wsMethod = secUtil.getWSMethod(body);
	            log.info("INCOMING SOAP MESSAGE : AppDeviceInfo ["+wsMethod+"]");
	            
	       
	            Object micbObj = session.getAttribute(SecurityUtil.APPDEVICE_SESSION_TAG);
	            MICBRequestBean micbRequestBean = null;
            	if(null!=micbObj && micbObj instanceof MICBRequestBean)
            	{
            		micbRequestBean = (MICBRequestBean) micbObj; 
            	}
            	else
            	{
            		micbRequestBean = new MICBRequestBean();
            		micbRequestBean.setDeviceBean(new DeviceBean());
            		micbRequestBean.setLocationBean(new LocationBean());
            		micbRequestBean.setHeaderBean(new HeaderBean());
            	}
            	
	            	

        	 	Iterator headers = secUtil.getWSParamValue(header).entrySet().iterator();
                String _Model = "";
                String _Id = "";
                String _Make = "";
                String _Type = "";
                String _Os = "";

                String _Latitude = "";
            	String _Longtitude = "";
            	String _Altitude = "";
            	String _City = "";
            	String _State = "";
            	String _Country = "";
            	
            	String _Locale = "en";
            	String _AppVersion = "";
            	String _Rooted = "";
            	String _PnsToken = "";
            	String _AppMode = "";
            	String _IP = "";
            	
                
            	while(headers.hasNext())
                {
            		
            		
                	Map.Entry mEntry = (Map.Entry) headers.next();
                	//System.out.println("^^^^^^^^^^^^^^^^^^^^ mEntry.getKey().toString() ^^^^^^^^^^^^"+mEntry.getKey().toString()+" :: "+mEntry.getValue()); 
                	
                	if(mEntry.getKey().toString().equalsIgnoreCase(SecurityUtil._MODEL_TAG))
                	{
                		_Model =  null==mEntry.getValue()?"":mEntry.getValue().toString();
                		micbRequestBean.getDeviceBean().setModel(syncMICBRequestValue(_Model,micbRequestBean.getDeviceBean().getModel()));
                		log.info("_Model -- :: "+_Model); 
                	}
                	else if(mEntry.getKey().toString().equalsIgnoreCase(SecurityUtil._ALTITUDE_TAG))
                	{
                		_Altitude =  null==mEntry.getValue()?"":mEntry.getValue().toString();
                		micbRequestBean.getLocationBean().setAltitude(syncMICBRequestValue(_Altitude,micbRequestBean.getLocationBean().getAltitude()));
                	}
                	else if(mEntry.getKey().toString().equalsIgnoreCase(SecurityUtil._APPVERSION_TAG))
                	{
                		_AppVersion =  null==mEntry.getValue()?"":mEntry.getValue().toString();
                		micbRequestBean.getHeaderBean().setRequestInfo(syncMICBRequestValue(_AppVersion,micbRequestBean.getHeaderBean().getRequestInfo()));
                	}
                	else if(mEntry.getKey().toString().equalsIgnoreCase(SecurityUtil._CITY_TAG))
                	{
                		_City =  null==mEntry.getValue()?"":mEntry.getValue().toString();
                		micbRequestBean.getLocationBean().setCity(syncMICBRequestValue(_City,micbRequestBean.getLocationBean().getCity()));
                	}
                	else if(mEntry.getKey().toString().equalsIgnoreCase(SecurityUtil._COUNTRY_TAG))
                	{
                		_Country =  null==mEntry.getValue()?"":mEntry.getValue().toString();
                		micbRequestBean.getLocationBean().setCountry(syncMICBRequestValue(_Country,micbRequestBean.getLocationBean().getCountry()));
                	}
                	else if(mEntry.getKey().toString().equalsIgnoreCase(SecurityUtil._ID_TAG))
                	{
                		_Id =  null==mEntry.getValue()?"":mEntry.getValue().toString();
                		micbRequestBean.getDeviceBean().setDeviceId(syncMICBRequestValue(_Id,micbRequestBean.getDeviceBean().getDeviceId()));
                	}
                	else if(mEntry.getKey().toString().equalsIgnoreCase(SecurityUtil._LATITUDE_TAG))
                	{
                		_Latitude =  null==mEntry.getValue()?"":mEntry.getValue().toString();
                		micbRequestBean.getLocationBean().setLatitude(syncMICBRequestValue(_Latitude,micbRequestBean.getLocationBean().getLatitude()));
                	}
                	else if(mEntry.getKey().toString().equalsIgnoreCase(SecurityUtil._LONGTITUDE_TAG))
                	{
                		_Longtitude =  null==mEntry.getValue()?"":mEntry.getValue().toString();
                		micbRequestBean.getLocationBean().setLongtitude(syncMICBRequestValue(_Longtitude,micbRequestBean.getLocationBean().getLongtitude()));
                	}
                	else if(mEntry.getKey().toString().equalsIgnoreCase(SecurityUtil._MAKE_TAG))
                	{
                		_Make =  null==mEntry.getValue()?"":mEntry.getValue().toString();
                		micbRequestBean.getDeviceBean().setMake(syncMICBRequestValue(_Make,micbRequestBean.getDeviceBean().getMake()));
                	}
                	else if(mEntry.getKey().toString().equalsIgnoreCase(SecurityUtil._STATE_TAG))
                	{
                		_State =  null==mEntry.getValue()?"":mEntry.getValue().toString();
                		micbRequestBean.getLocationBean().setState(syncMICBRequestValue(_State,micbRequestBean.getLocationBean().getState()));
                	}
                	else if(mEntry.getKey().toString().equalsIgnoreCase(SecurityUtil._TYPE_TAG))
                	{
                		_Type =  null==mEntry.getValue()?"":mEntry.getValue().toString();
                		micbRequestBean.getDeviceBean().setType(syncMICBRequestValue(_Type,micbRequestBean.getDeviceBean().getType()));
                		log.info("_Type -- :: "+_Type);
                	}
                	else if(mEntry.getKey().toString().equalsIgnoreCase(SecurityUtil._OS_TAG))
                	{
                		_Os =  null==mEntry.getValue()?"":mEntry.getValue().toString();
                		micbRequestBean.getDeviceBean().setOs(syncMICBRequestValue(_Os,micbRequestBean.getDeviceBean().getOs()));
                		log.info("_Os -- :: "+_Os);
                	}
                	else if(mEntry.getKey().toString().equalsIgnoreCase(SecurityUtil._LOCALE_TAG))
                	{
                		_Locale =  null==mEntry.getValue()?"":mEntry.getValue().toString();
                		log.info("_Locale :: "+_Locale);
                		//ib required uppercase locale
                		_Locale = _Locale==null?"":_Locale.toUpperCase();
                		micbRequestBean.setLocaleCode(syncMICBRequestValue(_Locale,micbRequestBean.getLocaleCode()));
                		
                	}
                	else if(mEntry.getKey().toString().equalsIgnoreCase(SecurityUtil._ROOTED_TAG))
                	{          		
                   		//System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+micbRequestBean.getDeviceBean().isRooted()); 
                		
                		_Rooted =  null==mEntry.getValue()?"":mEntry.getValue().toString();
                		micbRequestBean.getDeviceBean().setRooted(syncMICBRequestValue(_Rooted,micbRequestBean.getDeviceBean().isRooted()));
                	}
                	else if(mEntry.getKey().toString().equalsIgnoreCase(SecurityUtil._PNS_DEVICE_TOKEN))
                	{
                		
                		_PnsToken =  null==mEntry.getValue()?"":mEntry.getValue().toString();
                		obRequest.getObHeader().setPnsDeviceToken(_PnsToken);
                		micbRequestBean.setBDObject(obRequest);
                		//log.info("PNS :- TOKEN :: "+_PnsToken);
                	}
                	else if(mEntry.getKey().toString().equalsIgnoreCase(SecurityUtil._APP_MODE))
                	{
                		
                		_AppMode =  null==mEntry.getValue()?"":mEntry.getValue().toString();
                		obRequest.getObHeader().setApp_mode(_AppMode);
                		micbRequestBean.setBDObject(obRequest);
                		//log.info("PNS :- TOKEN :: "+_PnsToken);
                	}
//                	else if(mEntry.getKey().toString().equalsIgnoreCase(SecurityUtil._IP))
//                	{
//                		
//                		_IP =  null==mEntry.getValue()?"":mEntry.getValue().toString();
//                		obRequest.getObHeader().setIp(_IP);
//                		micbRequestBean.setBDObject(obRequest);
//                		log.info("_IP -- :: "+_IP);
//                	}
                	
                }
            	
            	
            	
            	if(null == micbRequestBean.getLocationBean().getCountry())
            	{
            		micbRequestBean.getLocationBean().setCountry(_Country);
            	}
            	if(null == micbRequestBean.getLocaleCode() )
            	{
            		micbRequestBean.setLocaleCode(_Locale);
            	}
            		
            	
            	String clientIpAddress = request.getHeader("Incap-Client-IP");
            	String userAgent =  request.getHeader("User-Agent");
    			String ipAddress = request.getHeader("X-FORWARDED-FOR");
    			
    			
    			if (ipAddress == null) {
    				  ipAddress = request.getRemoteAddr();  
    				}
    		
    			if(clientIpAddress == null) {
    				clientIpAddress = ipAddress;
    			}
    			
    			obRequest.getObHeader().setIp(clientIpAddress);
        		micbRequestBean.setBDObject(obRequest);
        		log.info("_IP -- :: "+clientIpAddress);
    			
    			AgentBean agentBean = new AgentBean();
    			if(null!=userAgent)
    			{
    				agentBean.setUserAgent(userAgent);
    			}
    			if(null!=ipAddress)
    			{
    				agentBean.setIP(ipAddress);
    			}
            	
            	
    			micbRequestBean.setAgentBean(agentBean);
            	
            	
            	
    			log.debug("Device ID : "+micbRequestBean.getDeviceBean().getDeviceId());
    			
            	session.setAttribute(SecurityUtil.APPDEVICE_SESSION_TAG,micbRequestBean);
            	
            	
            	
    			
    			
    			
    			
    			
            	
 
	           
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		
		return true;
	}
	
	
	public String syncMICBRequestValue(String newValue, String currentValue)
	{
		if(null!=newValue && newValue.length()>0)
		{
			return newValue;
		}
		else
		{
			return currentValue;
		}
		

	}
	
	//overload
	public Boolean syncMICBRequestValue(String newValue, Boolean currentValue)
	{
		Boolean newBooleanValue = false; 
		
		if(null!=newValue)
		{
			if(newValue.equalsIgnoreCase("true")){
				
				newBooleanValue = true; 
				
			}else {             //if(newBooleanValue.equals("false"))
				
				newBooleanValue = false;
				
			}
			//System.out.println("~~~~~~~~~~~~ newBooleanValue:"+newBooleanValue); 
			return newBooleanValue; 
		}
		else
		{
			return currentValue;
		}
		

	}
	
}