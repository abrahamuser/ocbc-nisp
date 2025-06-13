package com.silverlake.mleb.ccmcb.init;

import java.net.MalformedURLException;

import javax.servlet.ServletContext;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.RollingFileAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import com.caucho.hessian.client.HessianProxyFactory;
import com.silverlake.micb.core.api.ApiInterface;
import com.silverlake.mleb.ccmcb.dispatcher.MlebDispatcher;
import com.silverlake.mleb.ccmcb.util.EhCacheMsgManager;
import com.silverlake.mleb.ccmcb.util.PropertiesManager;


 

public class InitApp {
 
	@Autowired
    private ServletContext servletContext;
	
	private static CacheManager cacheManager;
	private static String url ;
	private static String mcbUrl ;
	private static final Logger log = LogManager.getLogger(InitApp.class);
	private static ApiInterface api;
	private HessianProxyFactory factory = new HessianProxyFactory();
	public static PropertiesManager property = new PropertiesManager();
	
	public void initApp() throws Exception
	{
		log.info("check 1");
		PropertiesManager.resetConf();
		String appPath = System.getProperty("app.store.path");
		PropertiesManager prox = new PropertiesManager();
		//MessageManager msgManager = new MessageManager();
		String proFilePath = prox.getRealProPath();
		//String msgFilePath= msgManager.getOutputPath()+msgManager.getAppname()+".properties";
		//String msgFilePathKH= msgManager.getOutputPath()+msgManager.getAppnameKh()+".properties";

		log.info("check 2");
		LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
		Configuration config = ctx.getConfiguration();
		RollingFileAppender techical = (RollingFileAppender) config.getAppender("logfile");
		
		
		
		log.info("check 3");
		url = prox.getProperty("mleb.ip");
		mcbUrl = prox.getProperty("mcb.ip");
		
		
		
		log.info("check 4");
		EhCacheMsgManager msgManager = new EhCacheMsgManager();
		msgManager.initialCache();
		 
		 
		
		//url = "http://localhost:8080/MiB/remoting/mib_api";
		String nameUrl = "MLEB.ip";
		if(null==url)
		{
			if(null==mcbUrl)
			{
				url = "http://localhost:8080/mcb/remoting/mcb_api";
			}
			else
			{
				nameUrl = "mcb.ip";
				url = mcbUrl;
			}
		}
		
		
		 
		
		log.info(printBox("["+servletContext.getContextPath()+"]"
        		+ "\nLog File : "+techical.getFileName()
        		+ "\nProperties File : "+proFilePath
        		//+ "\nMsg Pro File : "+msgFilePath
        		//+ "\nMsg in_id Pro File : "+msgFilePathKH
        		+ "\n"+nameUrl+" : ["+url+"]"));
		
		
	 
			 
 
	}
	
	public void stopApp()
	{
		cacheManager.shutdown();
	}
	
	public String printBox(String data)
	{
		String key= "*";
		int lengthx = 0;
		String printMsg = "\n";
		
		String msgs[] = data.split("\n");
		
		for(String msg:msgs)
		{
			if(msg.length()>lengthx)
			{
				lengthx = msg.length();
			}
		}
		
		printMsg = printMsg+printLine(key,lengthx+4);
		for(String msg:msgs)
		{
			int remainSpace = lengthx-msg.length();
			printMsg = printMsg+ "\n"+"* "+msg+printLine(" ",remainSpace)+" *";
		}
		printMsg = printMsg+"\n"+printLine(key,lengthx+4);
		
		return printMsg;
	}
	
	
	public String printLine(String key, int length)
	{
		
		String line = "";
		for(int i=0;i<length;i++)
		{
			line = line+key;
		}
		
		
		return line;
	}


	/*public ApiInterface getApi() {
		
		if(null==api)
		{
			try {
				int defaultTimeOut =  60 * 1000;
				String connectTimeout = property.getProperty("mleb.connect.timeout"); 
				String readTimeout = property.getProperty("mleb.request.timeout");
				long CONNECT_TIMEOUT = defaultTimeOut;
				long REQUEST_TIMEOUT = defaultTimeOut;
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
				
				
				HessianProxyFactory factory = new HessianProxyFactory();
				 //KIV - 6-6-2018
				 //P3OCBCUAT-408 - change the setting to properties file
				 factory.setConnectTimeout(CONNECT_TIMEOUT);
				 factory.setReadTimeout(REQUEST_TIMEOUT);
				
				
				api = (ApiInterface) factory.create(ApiInterface.class, url);
				 
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return api;
	}*/


	public void setApi(ApiInterface api) {
		this.api = api;
	}

 
}