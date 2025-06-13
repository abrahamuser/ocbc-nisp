package com.silverlake.mleb.mcb.module.vc.common;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.AsyncListenableTaskExecutor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.util.GsonUtil;
import com.silverlake.mleb.mcb.util.PropertiesManager;

public class VCService extends VCRestService
{
	private static Logger log = LogManager.getLogger(VCService.class);

	public VCService(ApplicationContext appContext)
	{
		this.appContext = appContext;
	}

	public <R extends VCRequest, S extends VCResponseData> VCResponse<S> callOmniVC(String methodName, R req, S responseData, String sessionID, String mleb_tranxID, String ip)
			throws Exception
	{
		 
		VCResponse<S> resp = new VCResponse<S>();
		
		processRequest(req, methodName, sessionID, mleb_tranxID);
		Date reqDate = new Date();
		
		if((resp = isMockTest(methodName, (Class<S>)responseData.getClass(), null)) != null)
		{
			//refer to folder web-inf/vc_test_data
		} 
		else
		{
			RestTemplate restTemplate = getRestConnectionCustomJsonBuilder();
			resp = postForEntityWithSession(restTemplate,this.getRestURL(methodName), req, sessionID, VCResponse.class, ip);
		}
		
		
		Date resDate = new Date();
		processOmniChannelResponse(resp, responseData, methodName,
				sessionID, mleb_tranxID,reqDate,resDate);
		return resp;
		
	}
	
	public <R extends VCRequest, S extends VCResponseData> VCResponse<S> callOmniVC(VCMethodConstant.REST_METHODS methodName, R req, S responseData, String sessionID, String mleb_tranxID, String ip)
			throws Exception
	{
		return callOmniVC(methodName.getUri(), req, responseData, sessionID, mleb_tranxID, ip);
	}
	
	Date reqDate = null;
	public <R extends VCRequest, S extends VCResponseData> ListenableFuture<ResponseEntity<VCResponse>> callOmniVCAsync(String methodName, R req, S responseData, String sessionID, String mleb_tranxID, String ip) throws Exception
	{
		 
		return callOmniVCAsync(methodName, null, req, responseData, sessionID, mleb_tranxID, ip);
	}
	
	/**
	 * Call to velo omni asynchronously with specific task executor which able to control thread and connection pool.
	 * @param methodName
	 * @param taskExecutor custom task executor which to control thread and connection pool. Put null if default (SimpleAsyncTaskExecutor) to be used.
	 * @param req
	 * @param responseData
	 * @param sessionID
	 * @param mleb_tranxID
	 * @return
	 * @throws Exception
	 */
	public <R extends VCRequest, S extends VCResponseData> ListenableFuture<ResponseEntity<VCResponse>> callOmniVCAsync(String methodName, AsyncListenableTaskExecutor taskExecutor, R req, S responseData, String sessionID, String mleb_tranxID, String ip) throws Exception
	{
		ListenableFuture<ResponseEntity<VCResponse>> resp = null;
		processRequest(req, methodName, sessionID, mleb_tranxID);
		reqDate = new Date();
		
		if(isMockTestAsyncCheck(methodName))
		{
			//refer to folder web-inf/vc_test_data
		} 
		else
		{
			AsyncRestTemplate restTemplate = getRestAsyncConnectionCustomJsonBuilder(taskExecutor);
			resp = postForObjectAsync(restTemplate,this.getRestURL(methodName), req,sessionID, VCResponse.class, ip);
		}
		//processOmniChannelResponse(resp, VCResponse, methodName,sessionID, mleb_tranxID);
		return resp;
	}
	
	public <R extends VCRequest, S extends VCResponseData> ListenableFuture<ResponseEntity<VCResponse>> callOmniVCAsync(VCMethodConstant.REST_METHODS methodName, R req, S responseData, String sessionID, String mleb_tranxID, String ip) throws Exception
	{
		return callOmniVCAsync(methodName.getUri(), req, responseData, sessionID, mleb_tranxID, ip);
	}
	
	
	public <S extends VCResponseData> VCResponse<S> getAscyncResponseData(String methodName, ListenableFuture<ResponseEntity<VCResponse>> futureVCResponse, S responseData, String sessionID, String mleb_tranxID ) throws Exception
	{
		
		VCResponse<S> resp = new VCResponse<S>();
		Date processDate = new Date();
		if((resp = isMockTestAsync(methodName, (Class<S>)responseData.getClass(), null)) != null)
		{
			 
		}
		else
		{
			try
			{
				resp = futureVCResponse.get().getBody();
				
			}catch(Exception e)
			{
				e.printStackTrace();
				
				if(e.getMessage().startsWith("401"))
				{
					resp = new VCResponse();
					resp.setResponse_code("401");;
				}
				else
				{
					resp = null;
				}
				
				
				
			}
			
			processDate = new Date();
			
		}
		
		
		//special handle resp if null
		if(resp==null)
		{
			resp = new VCResponse();
			resp.setResponse_code("xx");
			resp.setResponse_desc("Omni response null");
		}
		
		processOmniChannelResponse(resp, responseData, methodName,sessionID, mleb_tranxID,reqDate,processDate);
		
		
		return resp;

	}
	
	public <S extends VCResponseData> VCResponse<S> getAscyncResponseData(VCMethodConstant.REST_METHODS methodName, ListenableFuture<ResponseEntity<VCResponse>> futureVCResponse, S responseData, String sessionID, String mleb_tranxID ) throws Exception
	{
		
		return getAscyncResponseData(methodName.getUri(), futureVCResponse, responseData, sessionID, mleb_tranxID );

	}
 
}
