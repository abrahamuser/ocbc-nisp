package com.silverlake.mleb.mcb.module.vc.common;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.AsyncListenableTaskExecutor;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.util.GsonUtil;
import com.silverlake.mleb.mcb.util.PropertiesManager;
import com.silverlake.mleb.mcb.util.StringUtil;

public class VCGenericService extends VCRestService{
	private static Logger log = LogManager.getLogger(VCGenericService.class);
	private String transactionId;
	private String sessionId;
	private String ipAddress;
	
	public VCGenericService(ApplicationContext appContext, String transactionId, String sessionId, String ipAddress)
	{
		this.transactionId = transactionId;
		this.appContext = appContext;
		this.sessionId = sessionId;
		this.ipAddress = ipAddress;
	}
	
	private <R extends VCRequest, S extends VCResponseData> VCResponse<S> callOmniVC(VCMethodConstant.REST_METHODS methodName, R req, Class<S> responseDataClass, String sessionID, String mleb_tranxID, String testCasePath, String ip)
			throws Exception
	{
		VCResponse<S> resp = new VCResponse<S>();
		
		processRequest(req, methodName.getUri(), sessionID, mleb_tranxID);
		Date reqDate = new Date();
		
		if((resp = isMockTest(methodName.getUri(), responseDataClass, testCasePath)) != null)
		{
			//refer to folder web-inf/vc_test_data
		} 
		else
		{
			RestTemplate restTemplate = getRestConnectionCustomJsonBuilder();
			resp = postForEntityWithSession(restTemplate,this.getRestURL(methodName.getUri()), req, sessionID, VCResponse.class, ip);
		}
		
		
		Date resDate = new Date();
		processOmniChannelResponse(resp, responseDataClass!=null?responseDataClass.newInstance():null, methodName.getUri(),
				sessionID, mleb_tranxID,reqDate,resDate);
		return resp;
	}
	
	public <R extends VCRequest, S extends VCResponseData> VCResponse<S> callOmniVC(VCMethodConstant.REST_METHODS methodName, R req, Class<S> responseDataClass, boolean isSessionEnabled)
			throws Exception
	{
		return callOmniVC(methodName, req, responseDataClass, (isSessionEnabled)?sessionId:null, transactionId, null, ipAddress);
	}
	
	public <R extends VCRequest, S extends VCResponseData> VCResponse<S> callOmniVC(VCMethodConstant.REST_METHODS methodName, R req, Class<S> responseDataClass, boolean isSessionEnabled, String testCasePath)
			throws Exception
	{
		return callOmniVC(methodName, req, responseDataClass, (isSessionEnabled)?sessionId:null, transactionId, testCasePath, ipAddress);
	}
	
	public <R extends VCRequest, S extends VCResponseData> VCResponse<S> callOmniVC(VCMethodConstant.REST_METHODS methodName, R req, Class<S> responseDataClass, boolean isSessionEnabled, String testCasePath, String ip)
			throws Exception
	{
		return callOmniVC(methodName, req, responseDataClass, (isSessionEnabled)?sessionId:null, transactionId, testCasePath, (ip != null && !StringUtil.isEmpty(ip))?ip:ipAddress);
	}
	
	/**
	 * Call to velo omni asynchronously with specific task executor which able to control thread and connection pool.
	 * @param methodName
	 * @param restTemplate Pass null if new AsyncRestTemplate to be used. 
	 * @param req
	 * @param sessionID
	 * @param mleb_tranxID
	 * @param testCasePath
	 * @return
	 * @throws Exception
	 */
	private <R extends VCRequest, S extends VCResponseData> VCAsyncResponseBean<R, VCResponse> callOmniVCAsync(VCMethodConstant.REST_METHODS methodName, R req, String sessionID, String mleb_tranxID, String testCasePath, String ip) 
			throws Exception
	{
		ListenableFuture<ResponseEntity<VCResponse>> resp = null;
		processRequest(req, methodName.getUri(), sessionID, mleb_tranxID);
		Date reqDate = new Date();
		
		String testParam = null;
		if(isMockTestAsyncCheck(methodName.getUri()))
		{
			//refer to folder web-inf/vc_test_data
		} 
		else
		{
			AsyncRestTemplate restTemplate = null;
			if(asyncRestTemplate == null){
				restTemplate = getRestAsyncConnectionCustomJsonBuilder(null);
			}else{
				//Used customized configuration of AsyncRestTemplate.
				//This need init method of 
				restTemplate = asyncRestTemplate;
			}
			resp = postForObjectAsync(restTemplate,this.getRestURL(methodName.getUri()), req,sessionID, VCResponse.class, ipAddress);
		}
		
		VCAsyncResponseBean<R, VCResponse> vcAsyncResponseBean = new VCAsyncResponseBean<R, VCResponse>();
		vcAsyncResponseBean.setCasePath(testCasePath);
		vcAsyncResponseBean.setRequestDate(reqDate);
		vcAsyncResponseBean.setResponseListener(resp);
		vcAsyncResponseBean.setRequestData(req);
		return vcAsyncResponseBean;
	}
	
	public <R extends VCRequest, S extends VCResponseData> VCAsyncResponseBean<R, VCResponse> callOmniVCAsync(VCMethodConstant.REST_METHODS methodName, R req, boolean isSessionEnabled, String testCasePath) 
			throws Exception
	{
		return callOmniVCAsync(methodName, req, (isSessionEnabled)?sessionId:null, transactionId, testCasePath, ipAddress);
	}
	
	public <R extends VCRequest, S extends VCResponseData> VCAsyncResponseBean<R, VCResponse> callOmniVCAsync(VCMethodConstant.REST_METHODS methodName, R req, boolean isSessionEnabled) 
			throws Exception
	{
		return callOmniVCAsync(methodName, req, (isSessionEnabled)?sessionId:null, transactionId, null, ipAddress);
	}
	
	
	public <R extends VCRequest, S extends VCResponseData> VCResponse<S> getAscyncResponseData(String methodName, VCAsyncResponseBean<R, VCResponse> futureVCResponse, Class<S> responseDataClass)
			throws Exception
	{
		
		VCResponse<S> resp = new VCResponse<S>();
		Date processDate = new Date();
		if((resp = isMockTestAsync(methodName, responseDataClass, futureVCResponse)) != null)
		{
			 
		}
		else
		{
			
			resp = futureVCResponse.getResponseListener().get().getBody();
			processDate = new Date();
			
		}
		
		processOmniChannelResponse(resp, responseDataClass.newInstance(), methodName,sessionId, transactionId, futureVCResponse.getRequestDate(), processDate);
		
		
		return resp;

	}
	
	public <R extends VCRequest, S extends VCResponseData> VCResponse<S> getAscyncResponseData(VCMethodConstant.REST_METHODS methodName, VCAsyncResponseBean<R, VCResponse> futureVCResponse, Class<S> responseDataClass) 
			throws Exception
	{
		return getAscyncResponseData(methodName.getUri(), futureVCResponse, responseDataClass);
	}
 
	/**
	 * To initialize the async rest template which to be used by callOmniVCAsync with concurrency limit control
	 * If this is not initilize beforehand, a default asyncRestTemplate will be created on every trigger of callOmniVCAsync
	 */
	public void initAsyncRestTemplate(){
		if(asyncRestTemplate != null){
			return;
		}
		SimpleAsyncTaskExecutor vcTaskExecutor = new SimpleAsyncTaskExecutor();
		int concurrencyLimit = 5;
		if(pmgr.getProperty(MiBConstant.ASYNCREST_CONCURRENCY_LIMIT) != null){
			try{
				concurrencyLimit = Integer.parseInt(pmgr.getProperty(MiBConstant.ASYNCREST_CONCURRENCY_LIMIT));
			}catch(Exception e){
				log.info(MiBConstant.ASYNCREST_CONCURRENCY_LIMIT +" not set properly. Using default concurrency setting of "+concurrencyLimit);
			}
		}
		vcTaskExecutor.setConcurrencyLimit(concurrencyLimit);
		//Reuse the async rest template to realise the max concurrency limit
		asyncRestTemplate = getRestAsyncConnectionCustomJsonBuilder(vcTaskExecutor); 
	}
}
