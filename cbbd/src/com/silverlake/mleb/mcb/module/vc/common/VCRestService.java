package com.silverlake.mleb.mcb.module.vc.common;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.AsyncListenableTaskExecutor;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.entity.VcwsTranx;
import com.silverlake.mleb.mcb.init.InitApp;
import com.silverlake.mleb.mcb.listerner.LogDBManager;
import com.silverlake.mleb.mcb.util.GsonUtil;
import com.silverlake.mleb.mcb.util.PropertiesManager;

public abstract class VCRestService
{
	private static Logger log = LogManager.getLogger(VCRestService.class);

	protected static Gson gsonLog = GsonUtil.getPrettyPrintingGson();
	protected PropertiesManager pmgr = new PropertiesManager();

	protected ApplicationContext appContext;
	
	private static int maxMaskSSID = 10;
	
	protected AsyncRestTemplate asyncRestTemplate;

	public VCRestService()
	{
	}

	public boolean excludeRequestPrint(String service_id)
	{
		String[] services =
		{ "ValidateTokenPin", "ValidateResetMobilePassword", "ValidateMobilePassword",
				"ValidateInternetPin", "ValidateCardPin", "PerformResetMobilePassword",
				"PerformResetInternetPin", "PerformOnlineRegistration", "PerformChangeInternetPin",
				"PerformChangeMobilePassword"};

		for (String service : services)
		{
			if (service_id.toLowerCase().startsWith(service.toLowerCase()))
			{
				return true;
			}
		}

		return false;
	}

	//private Date requestDate;

	public void processRequest(VCRequest ibReq, String funcName, String ibSessionID, String tranxid)
	{
		ibSessionID = ibSessionID == null ? "" : ibSessionID;
		ibSessionID = ibSessionID.length() > maxMaskSSID ? "****" + ibSessionID.substring(maxMaskSSID) : ibSessionID;

		/*
		 * if(null==arg01.getCountryCode()) { arg01.setCountryCode("MY"); } else
		 * { arg01.setCountryCode(arg01.getCountryCode()); }
		 * 
		 * 
		 * 
		 * if(null==arg01.getLocaleCode()) { arg01.setLocaleCode("en"); } else {
		 * arg01.setLocaleCode(arg01.getLocaleCode()); }
		 */

		log.info(this.getClass().getCanonicalName() + "-" + funcName + " :- [" + ibSessionID
				+ "] REQUEST \n " + filterGson(gsonLog.toJson(ibReq)));
		
		//requestDate = new Date();
	}

	private String getErrorCode(String oriStatus, Object responseObj)
	{
		try
		{
			Field errorCode = responseObj.getClass().getDeclaredField("errorCode");
			if (null != errorCode)
			{
				errorCode.setAccessible(true);
				Object errorCodeValue = errorCode.get(responseObj);

				if (errorCodeValue != null)
				{
					if(!errorCodeValue.toString().isEmpty())
						return (errorCodeValue.toString());
				}
			}
		} catch (Exception e)
		{
		}

		return oriStatus;
	}
	
	private String getErrorMessage(String oriStatusMessage, Object responseObj)
	{
		try
		{
			Field errorMessage = responseObj.getClass().getDeclaredField("errorDesc");
			if (errorMessage != null)
			{
				errorMessage.setAccessible(true);
				Object errorMessageValue = errorMessage.get(responseObj);

				if (errorMessageValue != null)
				{
					if(!errorMessageValue.toString().isEmpty())
						return (errorMessageValue.toString());
				}
			}

		} catch (Exception e)
		{
		}
		
		try
		{
			Field errorMessage = responseObj.getClass().getDeclaredField("errorMessage");
			if (errorMessage != null)
			{
				errorMessage.setAccessible(true);
				Object errorMessageValue = errorMessage.get(responseObj);

				if (errorMessageValue != null)
				{
					if(!errorMessageValue.toString().isEmpty())
						return (errorMessageValue.toString());
				}
			}

		} catch (Exception e)
		{
		}

		return oriStatusMessage;
	}

	 

	protected <T extends VCResponseData> void processOmniChannelResponse(VCResponse<T> response,
			T responseData, String funcName, String ibSessionID, String tranx_id, Date reqDate, Date resDate)
	{
		ibSessionID = ibSessionID == null ? "" : ibSessionID;
		ibSessionID = ibSessionID.length() > maxMaskSSID ? "****" + ibSessionID.substring(maxMaskSSID) : ibSessionID;
		log.info(this.getClass().getCanonicalName() + "-" + funcName + " :- [" + ibSessionID
				+ "] RESPONSE \n " + filterGson(gsonLog.toJson(response)));

		/*if (responseData != null)
			responseData = (response != null && response.getData() != null) ? gsonLog
					.fromJson(gsonLog.toJson(response.getData()), responseData.getClass())
					: null;*/
		 
		if (responseData != null) {			
					responseData = null == response.getData() ? null : (T)InitApp.mapper.map(
							response.getData(), responseData.getClass());
 
			response.setData(responseData);
		}
		ibSessionID = ibSessionID == null ? "" : ibSessionID;
		ibSessionID = ibSessionID.length() > 4 ? "****" + ibSessionID.substring(4) : ibSessionID;
		processLogDB(response, reqDate, resDate, ibSessionID, funcName, tranx_id);

	}
	
	
	
	 


	private void processLogDB(VCResponse response, Date requestDate, Date responseDate,
			String sessionID, String funcName, String mlebTransId)
	{
		sessionID = sessionID.length() > 45 ? "*****"
				+ sessionID.substring(sessionID.length() - 40) : sessionID;
		VcwsTranx logTranx = new VcwsTranx();
		
		
		
		String userID = null;
		String orgID = null;
		 
		logTranx.setOrgId(orgID);
		logTranx.setLoginId(userID);
		logTranx.setDescription("");
		logTranx.setErrorCode(response.getResponse_code());
		SimpleDateFormat sDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		logTranx.setMlebTranxId(mlebTransId);
		logTranx.setRequestId(sessionID);
		logTranx.setRequestTimeStamp(sDF.format(requestDate));
		logTranx.setResponseTimeStamp(sDF.format(responseDate));
		// logTranx.setServerRequestTimeStamp(requestTime+"");
		long respTime = responseDate.getTime() - requestDate.getTime();
		logTranx.setServerResponseTime(appendZero(String.valueOf(respTime), 7));

		logTranx.setService(funcName);
		logTranx.setStatusCode(response.getResponse_code());
		
		if(response.getResponse_desc()==null)
		{
			logTranx.setDescription(null);
		}
		else
		{
			logTranx.setDescription(response.getResponse_desc().length()>99?response.getResponse_desc().substring(0, 99):response.getResponse_desc());

		}
		
		// dao.insertEntity(logTranx);

		LogDBManager logDBManager = (LogDBManager) appContext.getBean("LogDBManager");
		logDBManager.onCall(logTranx);

	}

	public String filterGson(String data)
	{
		String[] masks =
		{ "pin", "password", "mPassword", "confirmPassword", "tncContent", "descriptions",
				"param_p","param_c",
				"sessionID", "benePicture", "image", "captchaImageData", "imageDoc", "goal_img", 
				"imageNPWP", "imageSign","profile_image","password_data_block","random_number","modulus_string"};

		for (String mask : masks)
		{
			char b = '"';
			char bx = ']';
			int checkExist = data.indexOf(Character.toString(b) + mask + Character.toString(b)
					+ ":");
			int lengthTag = (Character.toString(b) + mask + Character.toString(b) + ":").length();
			if (checkExist > 0)
			{

				// System.out.println("Index Found "+checkExist);
				String checkCut = data.substring(checkExist + lengthTag, data.length());

				if (mask.equalsIgnoreCase("mPassword"))
				{

					// System.out.println("Cut data msg : "+checkCut.substring(2,indexCut));
					int indexCut = checkCut.indexOf(Character.toString(bx) + ",\n");
					String remainData = indexCut > 0 ? data.substring(checkExist + lengthTag + 2
							+ indexCut) : "";
					data = data.substring(0, checkExist + lengthTag + 2) + "**********"
							+ Character.toString(bx) + remainData;

				} else if (mask.equalsIgnoreCase("benePicture") || 
						   mask.equalsIgnoreCase("captchaImageData") ||  
						   mask.equalsIgnoreCase("goal_img") || 
						   mask.equalsIgnoreCase("imageDoc") ||
						   mask.equalsIgnoreCase("image") || 
						   mask.equalsIgnoreCase("imageSign") ||
						   mask.equalsIgnoreCase("imageNPWP") 
						  )
				{
					String splitData = "\"" + mask +"\":";
					String[] splitImageData = data.split(splitData);
					String markedStr = "";
					
					if(splitImageData.length > 0){
						markedStr = splitImageData[0];
						
						for(int i = 1; i < splitImageData.length; i++){
							splitImageData[i] = splitData + " ["+"**********"+splitImageData[i].substring(splitImageData[i].indexOf(Character.toString(bx)), splitImageData[i].length());
							markedStr+= splitImageData[i];
						}
					}
					
					data = markedStr;
//					int indexCut = checkCut.indexOf(Character.toString(bx) + ",\n");
//					data = data.substring(0, checkExist + lengthTag + 2) + "**********"
//							+ Character.toString(bx);
//							+ data.substring(checkExist + lengthTag + 2 + indexCut);

				}
				else
				{

					int indexCut = checkCut.indexOf(Character.toString(b) + ",\n");

					String remainData = indexCut > 0 ? data.substring(checkExist + lengthTag + 2
							+ indexCut) : "\n}";
					data = data.substring(0, checkExist + lengthTag + 2) + "**********"
							+ Character.toString(b) + remainData;

				}
			}

		}

		return data;
	}

 
	
	public RestTemplate getRestConnection()
	{
		String timeout = pmgr.getProperty("ib.connect.timeout");
		timeout = null == timeout ? "60" : timeout;

		RestTemplate x = new RestTemplate();
		((SimpleClientHttpRequestFactory) x.getRequestFactory()).setReadTimeout(1000 * (Integer
				.parseInt(timeout)));
		((SimpleClientHttpRequestFactory) x.getRequestFactory()).setConnectTimeout(1000 * (Integer
				.parseInt(timeout)));

		return x;
	}
	
	/**
	 * This to return rest handler which customized with custom gson builder
	 * This is to resolve the issue of BigDecimal being translated into exponent value when it is too large which unable to handle at remote side.
	 * @return
	 */
	public RestTemplate getRestConnectionCustomJsonBuilder()
	{
		String timeout = pmgr.getProperty("ib.connect.timeout");
		timeout = null == timeout ? "60" : timeout;

		RestTemplate x = new RestTemplate();
		List<HttpMessageConverter<?>> converters = x.getMessageConverters();
		for(HttpMessageConverter<?> converter:converters){
			if(converter instanceof GsonHttpMessageConverter){
				GsonHttpMessageConverter gsonConverter = (GsonHttpMessageConverter) converter;
				gsonConverter.setGson(gsonLog);
				break;
			}
		}
		((SimpleClientHttpRequestFactory) x.getRequestFactory()).setReadTimeout(1000 * (Integer
				.parseInt(timeout)));
		((SimpleClientHttpRequestFactory) x.getRequestFactory()).setConnectTimeout(1000 * (Integer
				.parseInt(timeout)));

		return x;
	}
	
	/**
	 * This to return rest handler which customized with custom gson builder
	 * This is to resolve the issue of BigDecimal being translated into exponent value when it is too large which unable to handle at remote side.
	 * @param taskExecutor custom task executor which to control thread and connection pool. Put null if default (SimpleAsyncTaskExecutor) to be used.
	 * @return
	 */
	public AsyncRestTemplate getRestAsyncConnectionCustomJsonBuilder(AsyncListenableTaskExecutor taskExecutor)
	{
		String timeout = pmgr.getProperty("ib.connect.timeout");
		timeout = null == timeout ? "60" : timeout;

		AsyncRestTemplate x = null;
		if(taskExecutor == null){
			x = new AsyncRestTemplate();
		}else{
			x = new AsyncRestTemplate(taskExecutor);
		}
		List<HttpMessageConverter<?>> converters = x.getMessageConverters();
		for(HttpMessageConverter<?> converter:converters){
			if(converter instanceof GsonHttpMessageConverter){
				GsonHttpMessageConverter gsonConverter = (GsonHttpMessageConverter) converter;
				gsonConverter.setGson(gsonLog);
				break;
			}
		}
		((SimpleClientHttpRequestFactory) x.getAsyncRequestFactory()).setReadTimeout(1000 * (Integer
				.parseInt(timeout)));
		((SimpleClientHttpRequestFactory) x.getAsyncRequestFactory()).setConnectTimeout(1000 * (Integer
				.parseInt(timeout)));

		return x;
	}
	
	public AsyncRestTemplate getRestAsyncConnection()
	{
		String timeout = pmgr.getProperty("ib.connect.timeout");
		timeout = null == timeout ? "60" : timeout;

		AsyncRestTemplate x = new AsyncRestTemplate();
		((SimpleClientHttpRequestFactory) x.getAsyncRequestFactory()).setReadTimeout(1000 * (Integer
				.parseInt(timeout)));
		((SimpleClientHttpRequestFactory) x.getAsyncRequestFactory()).setConnectTimeout(1000 * (Integer
				.parseInt(timeout)));

		return x;
	}

	public String getRestURL(String method)
	{
		return pmgr.getProperty("vc.rest.url")+"/"+method;
	}

	public String getRestURL2()
	{
		log.info("IB Rest URL : " + pmgr.getProperty("ib.rest.url2"));
		return pmgr.getProperty("ib.rest.url2");
	}

	public <T> VCResponse<T> isMockTest(String path, Class<T> clazz, String casePath) throws Exception
	{
		if ((null != pmgr.getProperty("ib.mock")
				&& pmgr.getProperty("ib.mock").toString().equalsIgnoreCase("true")) ||
				isMockPath(path))
		{
			if(casePath != null && !casePath.isEmpty()){
				path = path + "_" + casePath;
			}
			String appPropertyPath = System.getProperty(PropertiesManager.PROPERTIES_SYSTEM_PATH_NAME);
			String fileLoc = "file:"+appPropertyPath+"/vc_test_data/"+path+".json";
			//InputStream inx= appContext.getResource("/WEB-INF/vc_test_data/"+path+".json").getInputStream();
			InputStream inx= appContext.getResource(fileLoc).getInputStream();
			VCResponse<T> resp = gsonLog.fromJson(IOUtils.toString(inx, "UTF-8"), VCResponse.class);
			inx.close();
			resp.setResponse_desc(resp.getResponse_desc()+" [test data!]");
			
			return resp;
		}
		
		return null;
	}
	
	
	
	
	public boolean isMockTestAsyncCheck(String path) throws Exception
	{
		if ((null != pmgr.getProperty("ib.mock")
				&& pmgr.getProperty("ib.mock").toString().equalsIgnoreCase("true")) ||
				isMockPath(path))
		{
		 
			return true;
		}

		return false;
	}
	
	
	public <R extends VCRequest, T> VCResponse<T> isMockTestAsync(String path, Class<T> clazz, VCAsyncResponseBean<R, VCResponse> futureVCResponse) throws Exception
	{
		if ((null != pmgr.getProperty("ib.mock")
				&& pmgr.getProperty("ib.mock").toString().equalsIgnoreCase("true")) ||
				isMockPath(path))
		{
			if(futureVCResponse != null && futureVCResponse.getCasePath() != null && !futureVCResponse.getCasePath().isEmpty()){
				path = path + "_" + futureVCResponse.getCasePath();
			}
			String appPropertyPath = System.getProperty(PropertiesManager.PROPERTIES_SYSTEM_PATH_NAME);
			String fileLoc = "file:"+appPropertyPath+"/vc_test_data/"+path+".json";
			//InputStream inx= appContext.getResource("/WEB-INF/vc_test_data/"+path+".json").getInputStream();
			InputStream inx= appContext.getResource(fileLoc).getInputStream();
			VCResponse<T> resp = gsonLog.fromJson(IOUtils.toString(inx, "UTF-8"), VCResponse.class);
			inx.close();
			resp.setResponse_desc(resp.getResponse_desc()+" [test data!]");
			 
			return resp;
		}

		return null;
	}
	
	
	

	public boolean isMockTestSession()
	{
		if (null != pmgr.getProperty("ib.session")
				&& !pmgr.getProperty("ib.session").toString().equalsIgnoreCase("false"))
		{
			return true;
		}

		return false;
	}

	

	public String appendZero(String value, int length)
	{
		while (value.length() < length)
		{
			value = "0" + value;
		}
		return value;
	}

	public String getEmptyIfNull(Object obj)
	{
		String returnString = "";
		if (obj != null && obj instanceof String)
			returnString = obj.toString();
		else if (obj != null)
		{
			try
			{
				returnString = obj.toString();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return returnString;
	}

	public String setBooleanFalseIfNull(Object obj)
	{
		String returnString = "false";
		if (obj != null && obj instanceof String)
			returnString = obj.toString();
		else if (obj != null)
		{
			try
			{
				returnString = obj.toString();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return returnString;
	}
	
	public <T> T postForEntityWithSession(RestTemplate restTemplate, String url, Object request, String omniSessionId, Class<T> responseType, String ip)
			throws Exception {
 
		try
		{
			
 
			/*HttpClient httpClient = HttpClientBuilder.create().build();
			HttpPost requestJson = new HttpPost(url);
			
			StringEntity params = new StringEntity(gsonLog.toJson(request));
			requestJson.addHeader("content-type", "application/json");
			if (omniSessionId != null) {
				requestJson.addHeader("X-ONEBC-SESSION", omniSessionId);
			}
			requestJson.setEntity(params);
			HttpResponse rsp = httpClient.execute(requestJson);*/
			
			
			
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("charset", "UTF-8");
			//headers.add("X-Content-Type-Options", "nosniff");
			//headers.add("Content-Type", "application/json;charset=UTF-8");
			//headers.add("Transfer-Encoding", "chunked");
			if(null!=omniSessionId  && omniSessionId.trim().length()>0)
			{
				log.info("ss:::::"+omniSessionId.length());
				log.info("ONEBC-SESSION:::::"+omniSessionId);
				headers.add("X-ONEBC-SESSION", omniSessionId);
			}
			
			if(null!=ip && !ip.isEmpty())
			{
				log.info("IP-ADDRESS:::::"+ip);
				headers.add("IP-ADDRESS", ip);
			}
			
			HttpEntity<Object> requestSession = new HttpEntity<Object>(request, headers);
			
			
			return restTemplate.postForEntity(url, requestSession, responseType).getBody();
			
		}catch (RestClientException e)
		{
			log.info("ERROR : ["+url+"] "+e.getMessage());
			e.printStackTrace();
			
			if(e.getMessage().toString().startsWith("401"))
			{
				 
				VCResponse resp = new VCResponse();
				resp.setResponse_code("401");
				 
				
				 
				return (T)resp;
			}
			else
			{
				throw e;
			}
		
			
			
			
		
		}
 
		
	}
	
	
	
	public <T> T postForObject(RestTemplate restTemplate, String url, Object request, Class<T> responseType)
			throws RestClientException {
 
		try
		{
 
			return restTemplate.postForObject(url, request, responseType);
			
		}catch (RestClientException e)
		{
			log.info("ERROR :: ["+url+"] "+e.getMessage());
			throw e;
		}
 
		
	}
	
	
	public <T> ListenableFuture<ResponseEntity<T>> postForObjectAsync(AsyncRestTemplate restTemplate, String url, Object request, String omniSessionId, Class<T> responseType, String ip)
			throws RestClientException {
 
		try
		{
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("charset", "UTF-8");
			//headers.add("X-Content-Type-Options", "nosniff");
			//headers.add("Content-Type", "application/json;charset=UTF-8");
			//headers.add("Transfer-Encoding", "chunked");
			if(null!=omniSessionId && omniSessionId.trim().length()>0 )
			{
				log.info("ss:::::"+omniSessionId.length());
				log.info("ONEBC-SESSION:::::"+omniSessionId);
				headers.add("X-ONEBC-SESSION", omniSessionId);
			}
			
			if(null!=ip && !ip.isEmpty())
			{
				log.info("IP-ADDRESS:::::"+ip);
				headers.add("IP-ADDRESS", ip);
			}
			
			HttpEntity<Object> requestSession = new HttpEntity<Object>(request, headers);
 
			return restTemplate.postForEntity(url, requestSession, responseType);
			
		}catch (RestClientException e)
		{
			log.info("ERROR :: ["+url+"] "+e.getMessage());
			throw e;
		}
 
		
	}
	
	private boolean isMockPath(String uri){
		String mockPath = pmgr.getProperty("ib.mock.path");
		if(mockPath != null && !mockPath.isEmpty()){
			String[] paths = mockPath.split(",");
			for(String path:paths){
				if(path.equals(uri)){
					return true;
				}
			}
		}
		return false;
	}
}
