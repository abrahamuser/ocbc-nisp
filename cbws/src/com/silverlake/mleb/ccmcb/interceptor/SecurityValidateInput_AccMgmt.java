package com.silverlake.mleb.ccmcb.interceptor;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
//import org.apache.commons.lang.StringEscapeUtils;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.silverlake.mleb.ccmcb.util.SecurityUtil;


public class SecurityValidateInput_AccMgmt implements SOAPHandler<SOAPMessageContext>
{
	private static Logger log = LogManager.getLogger(SecurityValidateInput_AccMgmt.class);
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
		
		
		
		
		if (outbound && secUtil.is_input_check())
		{
			try
			{
				//clear error
				secUtil.removeSecurityError(session);
				
	            SOAPBody body = context.getMessage().getSOAPBody();
	           
	            String wsMethod = secUtil.getWSMethod(body);
	            
	            
	           
			  	
			  	
			  	SOAPEnvelope respHeader = context.getMessage().getSOAPPart().getEnvelope();
  
	      
			}
			catch(Exception e)
			{
				log.debug("Exception in MiBWSRequestChain.handleMessage() :: "+e);
				e.printStackTrace();
			}
		}
		else if(secUtil.is_input_check() && !secUtil.is_security_counter(session))
		{
			log.debug("INCOMING SOAP MESSAGE : Validate Input");
			
			try
			{
				
				SOAPHeader header = context.getMessage().getSOAPHeader();
				
			
			  	Iterator headers = secUtil.getWSParamValue(header).entrySet().iterator();
			  	while(headers.hasNext())
                {
                	Map.Entry mEntry = (Map.Entry) headers.next();
                	if(!secUtil.isValidHeaderParam(mEntry.getValue().toString()))
                	{
                		log.info("WS HEADER PARAM VALUE : "+mEntry.getKey() + " : " + mEntry.getValue());
                		session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_TAG, SecurityUtil.SECURITY_ERROR_INVALID_INPUT_PARAM);
	            		return true;
                	}
                }
				
				
	            SOAPBody body = context.getMessage().getSOAPBody();
	            

	    
	            String wsMethod = secUtil.getWSMethod(body);

	          
	            Iterator bodyParam = secUtil.getWSParamValue( context.getMessage().getSOAPBody()).entrySet().iterator();
	            while(bodyParam.hasNext())
	            {
	            	Map.Entry mBody = (Map.Entry) bodyParam.next();

	            	
	            	
	            	
	            	if(!secUtil.isValidParam(mBody.getValue().toString()))
	            	{
	            		log.info("WS BODY PARAM VALUE : "+mBody.getKey() + " : " + mBody.getValue());
	            		session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_TAG, SecurityUtil.SECURITY_ERROR_INVALID_INPUT_PARAM);
	            		return true;
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