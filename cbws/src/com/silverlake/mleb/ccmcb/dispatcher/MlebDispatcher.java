package com.silverlake.mleb.ccmcb.dispatcher;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.mule.api.MuleContext;
//import org.mule.api.MuleMessage;
//import org.mule.api.context.MuleContextFactory;
//import org.mule.config.spring.SpringXmlConfigurationBuilder;
//import org.mule.context.DefaultMuleContextFactory;
//import org.mule.module.client.MuleClient;
//import org.mule.module.client.RemoteDispatcher;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

import com.caucho.hessian.client.HessianProxyFactory;
import com.silverlake.micb.core.api.ApiInterface;
import com.silverlake.micb.core.bean.HeaderBean;
import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.ccmcb.init.InitApp;
import com.silverlake.mleb.ccmcb.util.PropertiesManager;
import com.silverlake.mleb.mcb.constant.MiBConstant;



public class MlebDispatcher {

	//private static Logger logger = Logger.getLogger(MuleDispatcher.class);
	//private static MuleContext muleContext;
	//private static MuleClient client;
	//private RemoteDispatcher  dispatcher;
	private final String SERVER_END_POINT = "targetCoreAddr";
	private final String BD_END_POINT = "addrToBD";
	private final String END_POINT = "vm://vmAggregator";
	private static long CONNECT_TIMEOUT = 60 * 1000; // 60 seconds
	private static long REQUEST_TIMEOUT = 60 * 1000; // 60 seconds
	public static PropertiesManager property = new PropertiesManager();
	
	private static String remoteMlebIp = "";
	private static ApiInterface mlebApi;
	
	private static String remoteMcbIp = "";
	private static ApiInterface mcbApi;
	
 
 

	private static final Logger logger = LogManager.getLogger(MlebDispatcher.class);
	
	public MlebDispatcher() 
	{

		
	}
	
	
	
	public MlebDispatcher(boolean resetConnection)
	{
		String remoteMleb = property.getProperty("mleb.ip");
		String remoteMcb = property.getProperty("mcb.ip");
		String remotePex = property.getProperty("pex.ip");
		String remoteMpos = property.getProperty("mpos.ip");
		
		
		
		/*
		if(remoteMleb==null || !remoteMleb.equalsIgnoreCase(remoteMlebIp))
		{
			mlebApi = getRemoteApi(remoteMleb);
			remoteMlebIp = remoteMleb;
		}
	
		//logger.info("MIB:::::::::"+remoteMib+"-"+remoteMibIp);
		if(remoteMcb==null || !remoteMcb.equalsIgnoreCase(remoteMcbIp))
		{
			mcbApi = getRemoteApi(remoteMcb);
			remoteMcbIp = remoteMcb;
		}
		
		
		if(remotePex==null || !remotePex.equalsIgnoreCase(remotePexIp))
		{
			pexApi = getRemoteApi(remotePex);
			remotePexIp = remotePex;
		}
		
		
		if(remoteMpos==null || !remoteMpos.equalsIgnoreCase(remoteMposIp))
		{
			mposApi = getRemoteApi(remoteMpos);
			remoteMposIp = remoteMpos;
		}*/
	}
	
	public MICBResponseBean sendToRemoteDispatcher(String endPoint, String functionCode, MICBRequestBean requestBean) {
		
		String serverEndPoint = "";
		logger.info("Process Service Id : " +  functionCode);
		MICBResponseBean result = null;
		HeaderBean headerBean = null==requestBean.getHeaderBean()?new HeaderBean():requestBean.getHeaderBean();
		
	
		
		
		headerBean.setServiceID(functionCode);
		requestBean.setHeaderBean(headerBean);
 
				
		if(endPoint==null){
			endPoint = END_POINT;
			serverEndPoint = SERVER_END_POINT;
		}else {
			serverEndPoint = BD_END_POINT;
		}
	
		
		try
		{
			
			initialApiContext();
			if(mlebApi!=null)
			{
				
				result = mlebApi.processSend(requestBean);
		 
			}
			else if(mcbApi!=null)
			{
			 
				result = mcbApi.processSend(requestBean);
			}
			 
			
			if(result!=null)
			{
				
				logger.info("Original ResponseCode [ "+functionCode+" ] : " +  result.getResponseCode());
				logger.info("Original Response transactionID ["+functionCode+"] : " + result.getTranxID());
				logger.info("BD ResponseCode [ "+functionCode+" ] : " +  result.getBdResponseCode());
				logger.info(" \n");
			}
		 
			
			
			
		}
		catch(Exception ex)
		{
			//ex.printStackTrace();
			if(!functionCode.equalsIgnoreCase(MiBConstant.FUNC_APP_STAT_INFO))
			{
				logger.info("Error Request - " + functionCode ,ex);
			}
			else
			{
				logger.info("Failed To Request - " + functionCode + " :: "+ex.getMessage());
			}
		}

				
		return result;
	}
	
	
	
	
	public void initialApiContext()  
	{
		if(mlebApi==null && mcbApi == null)
		{
			remoteMlebIp = property.getProperty("mleb.ip");
			remoteMcbIp = property.getProperty("mcb.ip");
	 
			String connectTimeout = property.getProperty("mleb.connect.timeout"); 
			String readTimeout = property.getProperty("mleb.request.timeout");
			
			if(connectTimeout != null && !connectTimeout.trim().isEmpty())
			{
				try{
					CONNECT_TIMEOUT = Long.parseLong(connectTimeout) * 1000L;
					 
				}catch(Exception e){
					
				}
			}
			
			if(readTimeout != null && !readTimeout.trim().isEmpty())
			{
				try{
					REQUEST_TIMEOUT = Long.parseLong(readTimeout) * 1000L;
					 
				}catch(Exception e){
					
				}
			}
		}
		
		
		if(mlebApi==null && remoteMlebIp!=null)
		{
			logger.info("-----------------create---["+remoteMlebIp+"]---mleb API");
			mlebApi = getRemoteApi(remoteMlebIp);
			logger.info("-----------------create done------mleb API");
		}
		else if(mcbApi==null && remoteMcbIp!=null)
		{
			mcbApi = getRemoteApi(remoteMcbIp);
		}
		else
		{
			
			mcbApi = getRemoteApi("http://localhost:8080/mcb/remoting/mcb_api");
		}

		
	}
	
	
	public ApiInterface getRemoteApi(String url) 
	{
		ApiInterface apiInterface = null;
		if(null!=url && url.trim().length()>0)
		{
			try
			{
				if(url.startsWith("rmi://"))
				{
					RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
				    rmiProxyFactoryBean.setServiceUrl(url);
				    rmiProxyFactoryBean.setServiceInterface(com.silverlake.micb.core.api.ApiInterface.class);
				    rmiProxyFactoryBean.afterPropertiesSet();
				    apiInterface = (ApiInterface) rmiProxyFactoryBean.getObject();
				}
				else
				{
					 HessianProxyFactory factory = new HessianProxyFactory();
					 //KIV - 6-6-2018
					 //P3OCBCUAT-408 - change the setting to properties file
					 logger.info("CC Connect TIMEOUT:"+CONNECT_TIMEOUT);
					 logger.info("CC Read TIMEOUT:"+REQUEST_TIMEOUT);
					 factory.setConnectTimeout(CONNECT_TIMEOUT);
					 factory.setReadTimeout(REQUEST_TIMEOUT);
					 apiInterface = (ApiInterface) factory.create(ApiInterface.class, url);
				}
			}catch(Exception e)
			{
				logger.info("Mleb Remote Api Exception : ",e);
			}
		}
		
		return apiInterface;
	}
	
	
	
	public void stopApiContext()
	{
		
	}

}
