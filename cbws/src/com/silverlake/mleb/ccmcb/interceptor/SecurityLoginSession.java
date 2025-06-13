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
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.silverlake.mleb.ccmcb.constant.WSConstant;
import com.silverlake.mleb.ccmcb.util.SecurityUtil;
import com.silverlake.mleb.ccmcb.util.UserLoginSession;



public class SecurityLoginSession implements SOAPHandler<SOAPMessageContext>
{

	private static Logger log = LogManager.getLogger(SecurityLoginSession.class);
	private UserLoginSession userLoginSession = new UserLoginSession();
	
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
		
		
		
		
		if (outbound )
		{
			try
			{
				//clear error
				secUtil.removeSecurityError(session);
				
	            SOAPBody body = context.getMessage().getSOAPBody();
	           
	            String wsMethod = secUtil.getWSMethod(body);
	            
	            
	           
			  	
			  	
			  	SOAPEnvelope respHeader = context.getMessage().getSOAPPart().getEnvelope();
			  	
			  	
			  	
			  	//logout response
			  	if(wsMethod.equalsIgnoreCase(SecurityUtil.RESPONSE_Logout+SecurityUtil.RESPONSE_TAG))
	  			{
			  		userLoginSession.removeSessionUser(session);
	  			}
			  	/*	
			  	//session response
			  	else if(secUtil.isSessionMethod(wsMethod.substring(0, wsMethod.length()-SecurityUtil.RESPONSE_TAG.length())))
			  	{

			  		
			  	}*/
			  	
			    /*Iterator bodyParam = secUtil.getWSParamValue( context.getMessage().getSOAPBody()).entrySet().iterator();
	            while(bodyParam.hasNext())
	            {
	            	Map.Entry mBody = (Map.Entry) bodyParam.next();
	            	if(mBody.getKey().toString().equalsIgnoreCase("statusCode") )
	            	{
	            		if(mBody.getValue().toString().equalsIgnoreCase(SecurityUtil.SECURITY_ERROR_INVALID_LOGIN_SESSION))
	            		{
	            			userLoginSession.removeSessionUser(session);
	            		}
	            	}
	            	
	            	
	            }*/
			  	
			  	
			  
	            
	      
			}
			catch(Exception e)
			{
				log.debug("Exception in MiBWSRequestChain.handleMessage() :: "+e);
				e.printStackTrace();
			}
		}
		else if( !secUtil.is_security_counter(session))
		{
			log.debug("INCOMING SOAP MESSAGE : SessionLogin ");
			
			try
			{
				
				SOAPHeader header = context.getMessage().getSOAPHeader();
				
			
			  	Iterator headers = secUtil.getWSParamValue(header).entrySet().iterator();
			  	while(headers.hasNext())
                {
                	Map.Entry mEntry = (Map.Entry) headers.next();
    				//log.debug("WS HEADER PARAM VALUE : "+mEntry.getKey() + " : " + mEntry.getValue());
                }
				
				
	            SOAPBody body = context.getMessage().getSOAPBody();
	            

	    
	            String wsMethod = secUtil.getWSMethod(body);
	            Object preLoginSession = session.getAttribute(SecurityUtil.PRELOGIN_SESSION_TAG);
	            Object preLoginTNCSession = session.getAttribute(SecurityUtil.PRETNC_SESSION_TAG);
	              log.info("wsMethod :: "+wsMethod+" :: "+secUtil.isSessionMethod(wsMethod));
	            if(secUtil.isSessionMethod(wsMethod))
	            {
	            	
	            	if(!userLoginSession.isSessionUser(session))
	            	{
	            		
	            		if(userLoginSession.isSessionRemoveUser(session))
	            		{
	            			
	            			session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_TAG, SecurityUtil.SECURITY_ERROR_SESSION_REMOVED);
	            		}
	            		else
	            		{
	            			
	            			//invalid login session
	            			
	            			session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_TAG, SecurityUtil.SECURITY_ERROR_INVALID_LOGIN_SESSION);
	            		}
	            	}
	            	else if( null!=preLoginSession)
	            	{
	            		if( !SecurityUtil.RESPONSE_RetrieveDeviceProfile.equalsIgnoreCase(wsMethod) && !SecurityUtil.RESPONSE_RequestDeviceTAC.equalsIgnoreCase(wsMethod) && !SecurityUtil.RESPONSE_DeviceTagging.equalsIgnoreCase(wsMethod) && !SecurityUtil.RESPONSE_DeviceTaggingV2.equalsIgnoreCase(wsMethod) )
	            		{
	            			session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_TAG, SecurityUtil.SECURITY_ERROR_UNTAGGED_DEVICE_SESSION);
	            		}
	            		
	            	}
	            	else if( null!=preLoginTNCSession)
	            	{
	            		
	            		session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_TAG, SecurityUtil.SECURITY_ERROR_UNKNOW_TNC_SESSION);
	            	}
	            	else if(SecurityUtil.RESPONSE_RequestDeviceTAC.equalsIgnoreCase(wsMethod) &&  null==preLoginSession )
	            	{
	            		session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_TAG, SecurityUtil.SECURITY_ERROR_UNKNOW_TAC_SESSION);
	            	}
	            	

	            }
	            //login method
	            else if(SecurityUtil.RESPONSE_Login.equalsIgnoreCase(wsMethod) || SecurityUtil.RESPONSE_LoginV2.equalsIgnoreCase(wsMethod))
	            {   
	            	//maintain cust profile data
	            	Object data = session.getAttribute(WSConstant.MIB_HTTP_SESSION_CUST_PROFILE);
	            	
	            	session.invalidate();
	            	session = request.getSession();
	            	session.setAttribute(WSConstant.MIB_HTTP_SESSION_CUST_PROFILE, data);
	            	
	            	
	            }
	            else if(SecurityUtil.RESPONSE_DeviceTagging.equalsIgnoreCase(wsMethod) || SecurityUtil.RESPONSE_RequestDeviceTAC.equalsIgnoreCase(wsMethod) || SecurityUtil.RESPONSE_DeviceTaggingV2.equalsIgnoreCase(wsMethod))
	            {
	            	if(null==preLoginSession)
	            	{
	            		//invalid device tag session
	            		session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_TAG, SecurityUtil.SECURITY_ERROR_INVALID_DEVICETAG_SESSION);
	            	}
	            }
	            else if(SecurityUtil.RESPONSE_UpdateTNC.equalsIgnoreCase(wsMethod) )
	            {
	            	if(null==preLoginTNCSession)
	            	{
	            		//invalid device tag session
	            		session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_TAG, SecurityUtil.SECURITY_ERROR_INVALID_TNC_SESSION);
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