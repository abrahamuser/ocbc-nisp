package com.silverlake.mleb.ccmcb.dispatcher;
/*package com.silverlake.mleb.ccws.dispatcher;


import java.net.MalformedURLException;
import java.util.Date;





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
import com.silverlake.mleb.ccws.util.PropertiesManager;
import com.silverlake.mleb.pex.constant.PExConstant;
import com.silverlake.micb.core.api.ApiInterface;
import com.silverlake.micb.core.bean.HeaderBean;
import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;



public class MuleDispatcher {

	//private static Logger logger = Logger.getLogger(MuleDispatcher.class);
	//private static MuleContext muleContext;
	//private static MuleClient client;
	//private RemoteDispatcher  dispatcher;
	private final String SERVER_END_POINT = "targetCoreAddr";
	private final String BD_END_POINT = "addrToBD";
	private final String END_POINT = "vm://vmAggregator";
	private final int RESPONSE_TIMEOUT = 100 * 1000; // 30 seconds
	public static PropertiesManager property = new PropertiesManager();
	private HessianProxyFactory factory = new HessianProxyFactory();
	private ApiInterface api;

	private static final Logger logger = LogManager.getLogger(MuleDispatcher.class);
	
	public MuleDispatcher()
	{

		String remote = property.getProperty("core.ip");
		
		

		if(null==remote)
		{
			remote = "http://localhost:8080/mib/remoting/mib_api";
		}
		
		
		
		if(null!=remote && remote.startsWith("rmi://"))
		{
			RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
		    rmiProxyFactoryBean.setServiceUrl(remote);
		    rmiProxyFactoryBean.setServiceInterface(com.silverlake.micb.core.api.ApiInterface.class);
		    rmiProxyFactoryBean.afterPropertiesSet();
		    api = (ApiInterface) rmiProxyFactoryBean.getObject();
		}
		else
		{

			try {
				api = (ApiInterface) factory.create(ApiInterface.class, remote);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public MICBResponseBean sendToRemoteDispatcher(String endPoint, String functionCode, MICBRequestBean requestBean) {
		
		String serverEndPoint = "";
		logger.info("Process Service Id : " +  functionCode);
		MICBResponseBean result = null;
		HeaderBean headerBean = null==requestBean.getHeaderBean()?new HeaderBean():requestBean.getHeaderBean();
		
		
		//headerBean.setBDDispatchURL("tcp://localhost:8081");
		//headerBean.setBDVMURL("vm://hlbMiB");
		
		
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
			//logger.debug("Destination endpoint : " +  endPoint);
		
		
			if(null!=api)
			{
				String remotePex = property.getProperty("core.ip.pex");
				String remoteMpos = property.getProperty("core.ip.mpos");
				
				
				
				boolean apiSent = false;
				if(null!=remotePex)
				{
					
					for(String pexFuncID : PExConstant.All_FUNCID)
					{
						if(functionCode.equalsIgnoreCase(pexFuncID))
						{	
							ApiInterface pexApi;
							try {
								
								pexApi = (ApiInterface) factory.create(ApiInterface.class, remotePex);
								result = pexApi.processSend(requestBean);
								apiSent = true;
							} catch (MalformedURLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}

				}

				if(!apiSent)
				{
					result = api.processSend(requestBean);
				}
				
				
				logger.info("Original ResponseCode [ "+functionCode+" ] : " +  result.getResponseCode());
				logger.info("Original Response transactionID ["+functionCode+"] : " + result.getTranxID());
				logger.info("BD ResponseCode [ "+functionCode+" ] : " +  result.getBdResponseCode());
				logger.info(" \n");
			}
			else
			{
			
				dispatcher = getDispatcher(serverEndPoint);
			
				MuleMessage rs = dispatcher.sendRemote(endPoint, requestBean, null, RESPONSE_TIMEOUT);
				dispatcher.dispose();
				if(rs!=null)
				{
					result = (MICBResponseBean)rs.getPayload();
					logger.info("Original ResponseCode ["+functionCode+"] : " +  result.getResponseCode());
					logger.info("Original Response transactionID ["+functionCode+"] : " + result.getTranxID());
					logger.info("BD ResponseCode ["+functionCode+"] : " +  result.getBdResponseCode());
					logger.info(" \n");
				
					
				}
			}
			//Date a = new Date();
			//requestBean.getHeaderBean().setBDVMURL("vm://hlbMiB");
			//result = api.processSend(requestBean);
			
			//Date b= new Date();
			//logger.info("TIME --- "+(b.getTime()-a.getTime()));
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			logger.info("Error Request - " + functionCode ,ex);
		}

				
		return result;
	}
	
	
	public static synchronized RemoteDispatcher getDispatcher(String endpoint) throws Exception
	{
		if(null==client)
		{
			client = new MuleClient(muleContext);
		}
		
		return client.getRemoteDispatcher(endpoint);
	}
	
	
	
	public void initialMuleContext()
	{
		System.out.println("logfile.path :: " +System.getProperty("logfile.path"));
		if(System.getProperty("logfile.path") == null || System.getProperty("logfile.path").equals(""))
		{
			System.setProperty("logfile.path", "/logs/my/");
		}
		System.out.println("\n\n\nMULE DISPATCHED"+System.getProperty("logfile.path")+"\n\n\n");
		//Logger logger = Logger.getLogger(MuleDispatcher.class);
		try {
			
			
			//SpringXmlConfigurationBuilder builder = new SpringXmlConfigurationBuilder("mule-config.xml");
			//MuleContextFactory muleContextFactory = new DefaultMuleContextFactory();
			//muleContext = muleContextFactory.createMuleContext(builder);
			//muleContext.start();
			
			
			//logger.info("Mule Context Initial Started Successfully : "+new Date(muleContext.getStartDate()));
			
		
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			logger.error("ERROR", e);
		}
	}
	
	public void stopMuleContext()
	{
		Logger logger = Logger.getLogger(MuleDispatcher.class);
		try {
			
			muleContext.stop();
			muleContext.dispose();
			logger.info("Mule stoped : "+muleContext.isDisposed()+" : "+new Date());	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Failed Stop Mule", e);
		}
	}

}
*/