package com.silverlake.mleb.pex.module.ib.securityServices;



import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.fuzion.ws.security.endpoint.EndpointResponse;
import com.fuzion.ws.security.endpoint.EndpointResponseHeader;
import com.fuzion.ws.security.endpoint.EndpointUserContext;
import com.fuzion.ws.security.endpoint.RequestAuthorizationCodeRequest;
import com.fuzion.ws.security.endpoint.RequestAuthorizationCodeResponse;
import com.fuzion.ws.security.endpoint.ResendAuthorizationCodeRequest;
import com.fuzion.ws.security.endpoint.SendNotificationForPreLoginRequest;
import com.fuzion.ws.security.endpoint.SendNotificationRequest;
import com.fuzion.ws.security.endpoint.ValidateAuthorizationCodeRequest;
import com.fuzion.ws.security.endpoint.ValidateAuthorizationCodeResponse;
import com.silverlake.hlb.mib.bean.ObUserContext;
import com.silverlake.mleb.pex.module.common.IBWSService;
import com.silverlake.mleb.pex.util.PropertiesManager;

public class SecuirtyService extends IBWSService
{

	private static Logger log = LogManager.getLogger(SecuirtyService.class);

	private PropertiesManager pmgr = new PropertiesManager();
	public SecuirtyService (ApplicationContext appContext)
	{
		this.appContext = appContext;
	}
	
	
	
	public EndpointResponse sendNotification(ObUserContext userContext,SendNotificationRequest notificationRequest, String mleb_tranx_id ) throws Exception
	{	
		String methodName = new Exception().fillInStackTrace().getStackTrace()[0].getMethodName();
		EndpointUserContext enpointUserContext  = processSecurityRequestParam(userContext,notificationRequest,null,methodName,mleb_tranx_id);
		
		
		EndpointResponse resp = new EndpointResponse();
		if(null!=pmgr.getProperty("fuzion.mock") && enpointUserContext.getLoginId().toLowerCase().startsWith(pmgr.getProperty("fuzion.mock").toString())  && !pmgr.getProperty("fuzion.mock").equalsIgnoreCase("false")  )
		{
			resp.setResponse(new EndpointResponseHeader());
			resp.getResponse().setStatusCode(1);
		}
		else
		{
			WSConnection connection = new WSConnection();
			
			resp = connection.getServicePort().sendNotification(enpointUserContext, notificationRequest);
			
			
		}
		
		processResponseParam(resp, resp.getResponse(),userContext, mleb_tranx_id);
		
		return resp;
	}
	
	
	
	public EndpointResponse sendPreLoginNotification(ObUserContext userContext,SendNotificationForPreLoginRequest notificationRequest, String mleb_tranx_id ) throws Exception
	{	
		String methodName = new Exception().fillInStackTrace().getStackTrace()[0].getMethodName();
		EndpointUserContext enpointUserContext  = processSecurityRequestParam(userContext,notificationRequest,null,methodName,mleb_tranx_id);
		
		
		EndpointResponse resp = new EndpointResponse();
		if(null!=pmgr.getProperty("fuzion.mock") && enpointUserContext.getLoginId().toLowerCase().startsWith(pmgr.getProperty("fuzion.mock").toString())  && !pmgr.getProperty("fuzion.mock").equalsIgnoreCase("false")  )
		{
			resp.setResponse(new EndpointResponseHeader());
			resp.getResponse().setStatusCode(1);
		}
		else
		{
			WSConnection connection = new WSConnection();
			//vietnam mobile special handle - if mobile start with 0 
			if(notificationRequest.getMobileNumber().startsWith("0"))
			{
				notificationRequest.setMobileNumber("84"+notificationRequest.getMobileNumber().substring(1));
			}
			resp = connection.getServicePort().sendNotificationForPreLogin(enpointUserContext, notificationRequest);
			
			
		}
		
		processResponseParam(resp, resp.getResponse(),userContext, mleb_tranx_id);
		
		return resp;
	}
	
	
	public RequestAuthorizationCodeResponse requestAuthorizationCode(ObUserContext userContext,RequestAuthorizationCodeRequest authorizationCode, String mleb_tranx_id ) throws Exception
	{	
		String methodName = new Exception().fillInStackTrace().getStackTrace()[0].getMethodName();
		EndpointUserContext enpointUserContext  = processSecurityRequestParam(userContext,authorizationCode,null,methodName,mleb_tranx_id);
		
		RequestAuthorizationCodeResponse resp = new RequestAuthorizationCodeResponse();
		if(null!=pmgr.getProperty("fuzion.mock") && enpointUserContext.getLoginId().toLowerCase().startsWith(pmgr.getProperty("fuzion.mock").toString())  && !pmgr.getProperty("fuzion.mock").equalsIgnoreCase("false") )		
		{
			
			resp.setResponse(new EndpointResponseHeader());
			resp.getResponse().setStatusCode(1);
			resp.setAuthorizationUUID(UUID.randomUUID().toString());
			
		}
		else
		{
		
		
			WSConnection connection = new WSConnection();
			resp = connection.getServicePort().requestAuthorizationCode(enpointUserContext, authorizationCode);
		}

		
		processResponseParam(resp, resp.getResponse(),userContext, mleb_tranx_id);
		
		return resp;
	}
	
	
	
	public EndpointResponse resendAuthorizationCode(ObUserContext userContext,ResendAuthorizationCodeRequest authorizationCode, String mleb_tranx_id ) throws Exception
	{	
		String methodName = new Exception().fillInStackTrace().getStackTrace()[0].getMethodName();
		EndpointUserContext enpointUserContext  = processSecurityRequestParam(userContext,authorizationCode,null,methodName,mleb_tranx_id);
		
		EndpointResponse resp = new RequestAuthorizationCodeResponse();
		if(null!=pmgr.getProperty("fuzion.mock") && enpointUserContext.getLoginId().toLowerCase().startsWith(pmgr.getProperty("fuzion.mock").toString())  && !pmgr.getProperty("fuzion.mock").equalsIgnoreCase("false")  )		
		{
			
			resp.setResponse(new EndpointResponseHeader());
			resp.getResponse().setStatusCode(1);
			
		}
		else
		{
		
		
			WSConnection connection = new WSConnection();
			resp = connection.getServicePort().resendAuthorizationCode(enpointUserContext, authorizationCode);
		}

		
		processResponseParam(resp, resp.getResponse(),userContext, mleb_tranx_id);
		
		return resp;
	}
		
		
	
	
	public ValidateAuthorizationCodeResponse validateAuthorizationCode(ObUserContext userContext,ValidateAuthorizationCodeRequest authorizationCode, String mleb_tranx_id ) throws Exception
	{	
		String methodName = new Exception().fillInStackTrace().getStackTrace()[0].getMethodName();
		EndpointUserContext enpointUserContext  = processSecurityRequestParam(userContext,authorizationCode,null,methodName,mleb_tranx_id);
		
		ValidateAuthorizationCodeResponse resp = new ValidateAuthorizationCodeResponse();
		
		if(null!=pmgr.getProperty("fuzion.mock") && enpointUserContext.getLoginId().toLowerCase().startsWith(pmgr.getProperty("fuzion.mock").toString())  && !pmgr.getProperty("fuzion.mock").equalsIgnoreCase("false")  )
		//if(true)
		{
			resp.setResponse(new EndpointResponseHeader());

			resp.getResponse().setStatusCode(1);
		}
		else
		{
			
		
			WSConnection connection = new WSConnection();
			resp = connection.getServicePort().validateAuthorizationCode(enpointUserContext, authorizationCode);
		}
		
		/*
		
		
		*/
		
		
		
		processResponseParam(resp, resp.getResponse(),userContext, mleb_tranx_id);
		
		return resp;
	}
	
	
	
}