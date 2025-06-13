package com.silverlake.mleb.pex.init;

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
import org.springframework.beans.factory.annotation.Value;

import com.caucho.hessian.client.HessianProxyFactory;
import com.silverlake.micb.core.api.ApiInterface;
import com.silverlake.mleb.pex.util.MessageManager;
import com.silverlake.mleb.pex.util.PropertiesManager;
import com.silverlake.mleb.pex.util.PropertiesUtil;


 

public class InitApp {
 
	@Autowired
    private ServletContext servletContext;
	private static final Logger log = LogManager.getLogger(InitApp.class);
	
	
	@Value ("${rmi.registryPort}")
	private String rmiPort;

	private PropertiesUtil proSys = new PropertiesUtil();
	private HessianProxyFactory factory = new HessianProxyFactory();
	
	public void initApp() throws Exception
	{
		String appPath = System.getProperty("app.store.path");
		PropertiesManager prox = new PropertiesManager();
		MessageManager msgManager = new MessageManager();
		String proFilePath = prox.getRealProPath();
		String msgFilePath= msgManager.getOutputPath()+msgManager.getAppname()+".properties";
		String msgFilePathKH= msgManager.getOutputPath()+msgManager.getAppnameKh()+".properties";
		
		LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
		Configuration config = ctx.getConfiguration();
		RollingFileAppender techical = (RollingFileAppender) config.getAppender("logfile");
		
		
		System.out.println(printBox("["+servletContext.getContextPath()+"]"
        		+ "\nLog File : "+techical.getFileName()
        		+ "\nProperties File : "+proFilePath
        		//+ "\nMsg Pro File : "+msgFilePath
        		//+ "\nMsg KH Pro File : "+msgFilePathKH
        		//+ "\nRMI Port : "+rmiPort
				));
		
		
	
		
	
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



 
}