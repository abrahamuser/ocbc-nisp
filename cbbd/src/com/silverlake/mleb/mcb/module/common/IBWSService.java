package com.silverlake.mleb.mcb.module.common;

import java.util.Date;

import org.apache.logging.log4j.Logger;import org.apache.logging.log4j.LogManager;
import org.springframework.context.ApplicationContext;

import com.fuzion.ws.security.endpoint.EndpointResponseHeader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.silverlake.mleb.mcb.bean.ObUserContext;
import com.silverlake.mleb.mcb.listerner.LogDBManager;

import comx.silverlake.mleb.mcb.entity.IbwsTranx;


public abstract class IBWSService 
{
	private static Logger log = LogManager.getLogger(IBWSService.class);	

	
	private Gson gsonLog = new GsonBuilder().setPrettyPrinting().create();
	
	private long requestTime;
	private long responseTime ; 
	private String dataRandom;
	private String functionName;
	protected ApplicationContext appContext;

	
	
	public IBWSService()
	{
		
	}
	
	
	public boolean excludeRequestPrint(String service_id)
	{
		String [] services = {"ValidateTokenPin","ValidateResetMobilePassword","ValidateMobilePassword",
				"ValidateInternetPin","ValidateCardPin","PerformResetMobilePassword","PerformResetInternetPin",
				"PerformOnlineRegistration","PerformChangeInternetPin","PerformChangeMobilePassword"};
		
		for(String service: services)
		{
			if(service_id.toLowerCase().startsWith(service.toLowerCase()))
			{
				return true;
			}
		}
		
		
		return false;
	}
	
	
	
	protected com.fuzion.ws.account.endpoint.EndpointUserContext processAccountRequestParam(ObUserContext arg01, Object arg02, Object arg03,String methodName,String tranxid)
	{
		functionName = methodName;
		arg01 =  processUserContext(arg01,arg02,arg03,tranxid);
		com.fuzion.ws.account.endpoint.EndpointUserContext endPontUserContext = new com.fuzion.ws.account.endpoint.EndpointUserContext();
		endPontUserContext.setChannelCode(arg01.getChannelCode());
		endPontUserContext.setCountryCode(arg01.getCountryCode());
		endPontUserContext.setLocaleCode(arg01.getLocaleCode());
		endPontUserContext.setLoginId(arg01.getLoginId());
		endPontUserContext.setRequestId(arg01.getRequestId());
		endPontUserContext.setSessionId(arg01.getSessionId());
	
		requestTime = new Date().getTime();
		return endPontUserContext;
	}
	
	protected com.fuzion.ws.common.endpoint.EndpointUserContext processCommonRequestParam(ObUserContext arg01, Object arg02, Object arg03,String methodName,String tranxid)
	{
		functionName = methodName;
		arg01 =  processUserContext(arg01,arg02,arg03,tranxid);
		com.fuzion.ws.common.endpoint.EndpointUserContext endPontUserContext = new com.fuzion.ws.common.endpoint.EndpointUserContext();
		endPontUserContext.setChannelCode(arg01.getChannelCode());
		endPontUserContext.setCountryCode(arg01.getCountryCode());
		endPontUserContext.setLocaleCode(arg01.getLocaleCode());
		endPontUserContext.setLoginId(arg01.getLoginId());
		endPontUserContext.setRequestId(arg01.getRequestId());
		endPontUserContext.setSessionId(arg01.getSessionId());
		
		requestTime = new Date().getTime();

		return endPontUserContext;
	}
	
	
	protected com.fuzion.ws.transaction.endpoint.EndpointUserContext processTransactionRequestParam(ObUserContext arg01, Object arg02, Object arg03,String methodName,String tranxid)
	{
		functionName = methodName;
		arg01 =  processUserContext(arg01,arg02,arg03,tranxid);
		com.fuzion.ws.transaction.endpoint.EndpointUserContext endPontUserContext = new com.fuzion.ws.transaction.endpoint.EndpointUserContext();
		endPontUserContext.setChannelCode(arg01.getChannelCode());
		endPontUserContext.setCountryCode(arg01.getCountryCode());
		endPontUserContext.setLocaleCode(arg01.getLocaleCode());
		endPontUserContext.setLoginId(arg01.getLoginId());
		endPontUserContext.setRequestId(arg01.getRequestId());
		endPontUserContext.setSessionId(arg01.getSessionId());
		
		
		requestTime = new Date().getTime();
		return endPontUserContext;
	}
	
	
	protected com.fuzion.ws.transaction.endpoint.EndpointUserContext processTransactionRequestForIBTGetLists(String methodName,String tranxid)
	{
		ObUserContext arg01 = new ObUserContext();
		
		functionName = methodName;
		
		
		com.fuzion.ws.transaction.endpoint.EndpointUserContext endPontUserContext = new com.fuzion.ws.transaction.endpoint.EndpointUserContext();
		endPontUserContext.setChannelCode("MB");
		endPontUserContext.setCountryCode("VN");
		endPontUserContext.setLocaleCode("en");
		endPontUserContext.setLoginId("MOBILITY004");
		endPontUserContext.setRequestId("1425349660239717c590");
		endPontUserContext.setSessionId("F9AEA9FA-65C5-4ECA-B185-D6D9BB937CDA");
		log.info(this.getClass().getCanonicalName()+"-"+functionName+" :- REQUEST \n "+filterGson(gsonLog.toJson(endPontUserContext)+"\n"));
		
		requestTime = new Date().getTime();
		return endPontUserContext;
	}
	
	protected com.fuzion.ws.customer.endpoint.EndpointUserContext processCustomerRequestParam(ObUserContext arg01, Object arg02, Object arg03,String methodName,String tranxid)
	{
		functionName = methodName;
		arg01 =  processUserContext(arg01,arg02,arg03,tranxid);
		com.fuzion.ws.customer.endpoint.EndpointUserContext endPontUserContext = new com.fuzion.ws.customer.endpoint.EndpointUserContext();
		endPontUserContext.setChannelCode(arg01.getChannelCode());
		endPontUserContext.setCountryCode(arg01.getCountryCode());
		endPontUserContext.setLocaleCode(arg01.getLocaleCode());
		endPontUserContext.setLoginId(arg01.getLoginId());
		endPontUserContext.setRequestId(arg01.getRequestId());
		endPontUserContext.setSessionId(arg01.getSessionId());
		
		
		requestTime = new Date().getTime();
		return endPontUserContext;
	}
	
	
	
	protected com.fuzion.ws.security.endpoint.EndpointUserContext processSecurityRequestParam(ObUserContext arg01, Object arg02, Object arg03,String methodName,String tranxid)
	{
		functionName = methodName;
		arg01 =  processUserContext(arg01,arg02,arg03,tranxid);
		com.fuzion.ws.security.endpoint.EndpointUserContext endPontUserContext = new com.fuzion.ws.security.endpoint.EndpointUserContext();
		endPontUserContext.setChannelCode(arg01.getChannelCode());
		endPontUserContext.setCountryCode(arg01.getCountryCode());
		endPontUserContext.setLocaleCode(arg01.getLocaleCode());
		endPontUserContext.setLoginId(arg01.getLoginId());
		endPontUserContext.setRequestId(arg01.getRequestId());
		endPontUserContext.setSessionId(arg01.getSessionId());
		
		requestTime = new Date().getTime();
		
		return endPontUserContext;
	}
	
	
	public ObUserContext processUserContext(ObUserContext arg01, Object arg02, Object arg03,String tranxid)
	{

		arg01.setRequestId(tranxid);
		arg01.setChannelCode("MB");
		
		
		/*if(null==arg01.getCountryCode())
		{
			arg01.setCountryCode("MY");
		}
		else
		{
			arg01.setCountryCode(arg01.getCountryCode());
		}
		
		
		
		if(null==arg01.getLocaleCode())
		{
			arg01.setLocaleCode("en");
		}
		else
		{
			arg01.setLocaleCode(arg01.getLocaleCode());
		}*/
		
		
		log.info(this.getClass().getCanonicalName()+"-"+functionName+" :- REQUEST \n "+filterGson(gsonLog.toJson(arg01)+"\n"+gsonLog.toJson(arg02)+"\n"+gsonLog.toJson(arg03)));
		
		return arg01;
	}
	

	
	protected void processResponseParam(Object arg01, EndpointResponseHeader responseHeader,ObUserContext userContext, String mleb_tranx_id) 
	{
		responseTime = new Date().getTime();
		log.info(this.getClass().getCanonicalName()+"-"+functionName+" :- RESPONSE \n "+filterGson(gsonLog.toJson(arg01)));
		processResponse(responseHeader,userContext,mleb_tranx_id);

	}
	
	
	protected void processResponseParam(Object arg01,  com.fuzion.ws.account.endpoint.EndpointResponseHeader responseHeader,   ObUserContext userContext,String mleb_tranx_id) 
	{
		EndpointResponseHeader oriResponseHeader = new EndpointResponseHeader();
		oriResponseHeader.setErrorCode(responseHeader.getErrorCode());
		oriResponseHeader.setErrorMessage(responseHeader.getErrorMessage());
		oriResponseHeader.setRequestId(responseHeader.getRequestId());
		oriResponseHeader.setRequestTimestamp(responseHeader.getRequestTimestamp());
		oriResponseHeader.setResponseTimestamp(responseHeader.getResponseTimestamp());
		oriResponseHeader.setStatusCode(responseHeader.getStatusCode());
		oriResponseHeader.setStatusDescription(responseHeader.getStatusDescription());
		oriResponseHeader.setVersion(responseHeader.getVersion());
		
		processResponseParam(arg01,oriResponseHeader,userContext,mleb_tranx_id);

	}
	
	
	
	
	protected void processResponseParam(Object arg01,  com.fuzion.ws.transaction.endpoint.EndpointResponseHeader responseHeader,  ObUserContext userContext, String mleb_tranx_id) 
	{
		EndpointResponseHeader oriResponseHeader = new EndpointResponseHeader();
		oriResponseHeader.setErrorCode(responseHeader.getErrorCode());
		oriResponseHeader.setErrorMessage(responseHeader.getErrorMessage());
		oriResponseHeader.setRequestId(responseHeader.getRequestId());
		oriResponseHeader.setRequestTimestamp(responseHeader.getRequestTimestamp());
		oriResponseHeader.setResponseTimestamp(responseHeader.getResponseTimestamp());
		oriResponseHeader.setStatusCode(responseHeader.getStatusCode());
		oriResponseHeader.setStatusDescription(responseHeader.getStatusDescription());
		oriResponseHeader.setVersion(responseHeader.getVersion());
		
		processResponseParam(arg01,oriResponseHeader,userContext,mleb_tranx_id);

	}
	
	
	
	protected void processResponseParam(Object arg01,  com.fuzion.ws.customer.endpoint.EndpointResponseHeader responseHeader, ObUserContext userContext, String mleb_tranx_id) 
	{
		EndpointResponseHeader oriResponseHeader = new EndpointResponseHeader();
		oriResponseHeader.setErrorCode(responseHeader.getErrorCode());
		oriResponseHeader.setErrorMessage(responseHeader.getErrorMessage());
		oriResponseHeader.setRequestId(responseHeader.getRequestId());
		oriResponseHeader.setRequestTimestamp(responseHeader.getRequestTimestamp());
		oriResponseHeader.setResponseTimestamp(responseHeader.getResponseTimestamp());
		oriResponseHeader.setStatusCode(responseHeader.getStatusCode());
		oriResponseHeader.setStatusDescription(responseHeader.getStatusDescription());
		oriResponseHeader.setVersion(responseHeader.getVersion());
		
		processResponseParam(arg01,oriResponseHeader,userContext,mleb_tranx_id);

	}
	
	

	protected void processResponseParam(Object arg01,  com.fuzion.ws.common.endpoint.EndpointResponseHeader responseHeader, ObUserContext userContext,String mleb_tranx_id) 
	{
		EndpointResponseHeader oriResponseHeader = new EndpointResponseHeader();
		oriResponseHeader.setErrorCode(responseHeader.getErrorCode());
		oriResponseHeader.setErrorMessage(responseHeader.getErrorMessage());
		oriResponseHeader.setRequestId(responseHeader.getRequestId());
		oriResponseHeader.setRequestTimestamp(responseHeader.getRequestTimestamp());
		oriResponseHeader.setResponseTimestamp(responseHeader.getResponseTimestamp());
		oriResponseHeader.setStatusCode(responseHeader.getStatusCode());
		oriResponseHeader.setStatusDescription(responseHeader.getStatusDescription());
		oriResponseHeader.setVersion(responseHeader.getVersion());
		
		processResponseParam(arg01,oriResponseHeader,userContext,mleb_tranx_id);

	}
	
	
	
	
	private void processResponse(EndpointResponseHeader responseHeader, ObUserContext userContext, String mleb_tranx_id) 
	{
		//update db
		IbwsTranx logTranx = new IbwsTranx();
		
		logTranx.setLoginId(userContext.getLoginId());
		logTranx.setDescription("");
		logTranx.setErrorCode(responseHeader.getErrorCode());
		if(null!=responseHeader.getErrorMessage() && responseHeader.getErrorMessage().length()>256)
			logTranx.setErrorMessage(responseHeader.getErrorMessage().substring(0, 256));
		else
			logTranx.setErrorMessage(responseHeader.getErrorMessage());
		logTranx.setMlebTranxId(mleb_tranx_id);
		logTranx.setRequestId(userContext.getRequestId());
		logTranx.setRequestTimeStamp(responseHeader.getRequestTimestamp()+"");
		logTranx.setResponseTimeStamp(responseHeader.getResponseTimestamp()+"");
		logTranx.setServerRequestTimeStamp(requestTime+"");
		logTranx.setServerResponseTime((responseTime-requestTime)+"");
		logTranx.setService(this.getClass().getSimpleName()+"-"+functionName);
		logTranx.setStatusCode(responseHeader.getStatusCode()+"");
		if(null!=responseHeader.getStatusDescription() && responseHeader.getStatusDescription().length()>256)
			logTranx.setStatusDescription(responseHeader.getStatusDescription().substring(0, 256));
		else
			logTranx.setStatusDescription(responseHeader.getStatusDescription());
		logTranx.setVersion(responseHeader.getVersion());
		
		//dao.insertEntity(logTranx);
		
		
		LogDBManager logDBManager = (LogDBManager) appContext.getBean("LogDBManager");
		logDBManager.onCall(logTranx);	
		/*MuleClient client = getMuleClient(muleContext);
		client.sendNoReceive("vm://ibwsTranxLogging", logTranx, null);*/
		
	}
	
	/*private static MuleClient client;
	public MuleClient getMuleClient(MuleContext muleContext)
	{
		if(client == null)
		{
			try {
				client =  new MuleClient(muleContext);
			} catch (MuleException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return client;
	}*/
	
	
	
	public String filterGson(String data)
	{
		String[] masks = {"pin","password","mPassword","confirmPassword","tncContent","descriptions"};
		
		
		for(String mask: masks)
		{
			char b = '"';
			char bx = ']';
			int checkExist = data.indexOf(Character.toString(b)+mask+Character.toString(b)+":");
			int lengthTag = (Character.toString(b)+mask+Character.toString(b)+":").length();
			if(checkExist>0)
			{
				
				
				//System.out.println("Index Found "+checkExist);
				String checkCut =  data.substring(checkExist+lengthTag, data.length());
				
				
				
				if(mask.equalsIgnoreCase("mPassword"))
				{
					//System.out.println("Cut data msg : "+checkCut.substring(2,indexCut));
					int indexCut = checkCut.indexOf(Character.toString(bx)+",\n");
					String remainData = indexCut>0?data.substring(checkExist+lengthTag+2+indexCut):"";
					data = data.substring(0, checkExist+lengthTag+2)+"**********"+Character.toString(bx)+ remainData;
					
				}
				else
				{
					int indexCut = checkCut.indexOf(Character.toString(b)+",\n");
					
				
					String remainData = indexCut>0?data.substring(checkExist+lengthTag+2+indexCut):"\n}";
					data = data.substring(0, checkExist+lengthTag+2)+"**********"+Character.toString(b)+remainData;
				
				}
			}
			
			
			
		}
		
		
		
		
		return data;
	}


	
	
	
	
	
	
	
	
}
