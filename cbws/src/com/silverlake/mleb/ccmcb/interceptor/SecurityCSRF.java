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
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.silverlake.mleb.ccmcb.util.PropertiesManager;
import com.silverlake.mleb.ccmcb.util.SecurityUtil;



public class SecurityCSRF implements SOAPHandler<SOAPMessageContext>
{

	private static ConcurrentMap recordsCSRF = new ConcurrentHashMap();
	public static PropertiesManager property = new PropertiesManager();
	private static Logger log = LogManager.getLogger(SecurityCSRF.class);
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
		
		//log.debug("WSInterceptor :: SESSION ID :: "+outbound+" :: "+session.getId());
		
		
		if (outbound && secUtil.is_csrf_check())
		{
			try
			{
				//clear error
				secUtil.removeSecurityError(session);
				
				
	            SOAPBody body = context.getMessage().getSOAPBody();
	           
	            String wsMethod = secUtil.getWSMethod(body);
	            //log.debug("Outgoing SOAP Method :: "+wsMethod);
	            
	            /*Iterator resBody = secUtil.getWSResponseHeaderParamValue(body).entrySet().iterator();
			  	while(resBody.hasNext())
                {
                	Map.Entry mEntry = (Map.Entry) resBody.next();
    				//log.debug("WS Response PARAM VALUE : "+mEntry.getKey() + " : " + mEntry.getValue());
                }*/
			  	
			  	
			  	SOAPEnvelope respHeader = context.getMessage().getSOAPPart().getEnvelope();
			  	
			  	
			  	//login response 
			  	if(wsMethod.equalsIgnoreCase(SecurityUtil.RESPONSE_Login+SecurityUtil.RESPONSE_TAG) || 
			  			wsMethod.equalsIgnoreCase(SecurityUtil.RESPONSE_LoginV2+SecurityUtil.RESPONSE_TAG) ||
			  			wsMethod.equalsIgnoreCase(SecurityUtil.RESPONSE_LoginV3+SecurityUtil.RESPONSE_TAG))
			  	{
			  		String csrfToken = secUtil.createCsrfToken();
			  		log.info("response csrfToken ::: "+csrfToken);
			  		secUtil.addSecurityResponseHeader(SecurityUtil.CSRF_TOKEN_TAG,csrfToken,respHeader);
			  		session.setAttribute(SecurityUtil.CSRF_SESSION_TAG, csrfToken);
			  		//secUtil.addSecurityResponseBody(SecurityUtil.CSRF_SESSION_TAG,csrfToken,respHeader);
			  	}
			  	//logout response
			  	else if(wsMethod.equalsIgnoreCase(SecurityUtil.RESPONSE_Logout+SecurityUtil.RESPONSE_TAG))
	  			{
			  		
	  			}
			  	//session response
			  	else if(secUtil.isSessionMethod(wsMethod.substring(0, wsMethod.length()-SecurityUtil.RESPONSE_TAG.length())) || 
			  			SecurityUtil.RESPONSE_DeviceTagging.equalsIgnoreCase(wsMethod.substring(0, wsMethod.length()-SecurityUtil.RESPONSE_TAG.length())) || 
			  			SecurityUtil.RESPONSE_RequestDeviceTAC.equalsIgnoreCase(wsMethod.substring(0, wsMethod.length()-SecurityUtil.RESPONSE_TAG.length())) || 
			  			SecurityUtil.RESPONSE_UpdateTNC.equalsIgnoreCase(wsMethod.substring(0, wsMethod.length()-SecurityUtil.RESPONSE_TAG.length())) ||
			  			SecurityUtil.RESPONSE_DeviceTaggingV2.equalsIgnoreCase(wsMethod.substring(0, wsMethod.length()-SecurityUtil.RESPONSE_TAG.length())))
			  	{

			  		String csrfToken = secUtil.createCsrfToken();
			  		log.info("response csrfToken ::: "+csrfToken);
			  		secUtil.addSecurityResponseHeader(SecurityUtil.CSRF_TOKEN_TAG,csrfToken,respHeader);
			  		session.setAttribute(SecurityUtil.CSRF_SESSION_TAG, csrfToken);
			  		//secUtil.addSecurityResponseBody(SecurityUtil.CSRF_SESSION_TAG,csrfToken,respHeader);
			  	}
			  	
			  	
			  
	            
	      
			}
			catch(Exception e)
			{
				//log.debug("Exception in MiBWSRequestChain.handleMessage() :: "+e);
				e.printStackTrace();
			}
		}
		else if(secUtil.is_csrf_check() && !secUtil.is_security_counter(session))
		{
			log.info("INCOMING SOAP MESSAGE : CSRF");
			
			try
			{
				
				SOAPHeader header = context.getMessage().getSOAPHeader();

	            SOAPBody body = context.getMessage().getSOAPBody();
	            
	            //SOAPMessage msg = ((SOAPMessageContext) context).getMessage ();
	            //dumpSOAPMessage(msg);
	            String wsMethod = secUtil.getWSMethod(body);
	           
	            
	            
	            
	            
	            if(secUtil.isSessionMethod(wsMethod) || SecurityUtil.RESPONSE_DeviceTagging.equalsIgnoreCase(wsMethod) || SecurityUtil.RESPONSE_RequestDeviceTAC.equalsIgnoreCase(wsMethod) || SecurityUtil.RESPONSE_UpdateTNC.equalsIgnoreCase(wsMethod) || SecurityUtil.RESPONSE_DeviceTaggingV2.equalsIgnoreCase(wsMethod))
	            {
	            	
	            	Object sessionObj = session.getAttribute(SecurityUtil.CSRF_SESSION_TAG);
	            	
	            	if(null!=sessionObj)
	            	{
	            		
	            	 	Iterator headers = secUtil.getWSParamValue(header).entrySet().iterator();
		                String wsCsrfToken = "";
		                
		            	while(headers.hasNext())
		                {
		                	Map.Entry mEntry = (Map.Entry) headers.next();
		                	if(mEntry.getKey().toString().equalsIgnoreCase(SecurityUtil.CSRF_TOKEN_TAG))
		                	{
		                		wsCsrfToken =  null==mEntry.getValue()?"":mEntry.getValue().toString();
		                		
		                		break;
		                	}
		                }
		            	
		            	log.info("sessionObj token ::: "+sessionObj);
		            	log.info("ws csrfToken ::: "+wsCsrfToken);
		            	Object rcrdCSRF =  recordsCSRF.putIfAbsent(session.getId()+wsCsrfToken, session.getId());
		            	String csrf = property.getProperty(SecurityUtil.PRO_CSRF_CHECK_TAG);
		            	log.info("PRO_CSRF_CHECK_TAG ::: "+csrf);
		            	if(null==rcrdCSRF)
		            	{
		            			
		            		if(null!=csrf && !csrf.equalsIgnoreCase("true") && !csrf.equalsIgnoreCase("false") && csrf.trim().equalsIgnoreCase(wsCsrfToken.trim()))
			                {
		            			
			                	session.removeAttribute(SecurityUtil.CSRF_SESSION_TAG);
			                }
		            		else if(!sessionObj.toString().equalsIgnoreCase(wsCsrfToken))
			                {
		            			log.info("invalid csrfToken - token not match");
			                	session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_TAG, SecurityUtil.SECURITY_ERROR_INVALID_CSRF_TOKEN);
		
			                }	
			                else
			                {
			                	//remove csrf session once success
		                		session.removeAttribute(SecurityUtil.CSRF_SESSION_TAG);
		                		
		
			                }
		            	}
		            	else
		            	{
		            		log.info("invalid csrfToken - rcrdCSRF");
		            		session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_TAG, SecurityUtil.SECURITY_ERROR_INVALID_CSRF_TOKEN);
		            	}
		            	recordsCSRF.remove(session.getId()+wsCsrfToken);
		          
	            	}
	            	else
	            	{
	            		log.info("invalid csrfToken - sessionObj is null");
	            		session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_TAG, SecurityUtil.SECURITY_ERROR_INVALID_CSRF_TOKEN);
	            		
	            	}
 
	            }
	            	
	            
	            
	            
	            
	           
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		return true;
	}
	
	
	
	
}