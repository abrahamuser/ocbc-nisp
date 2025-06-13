package com.silverlake.mleb.mcb.init;


import java.lang.reflect.Field;

import javax.servlet.ServletContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.RollingFileAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.caucho.hessian.client.HessianProxyFactory;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant.FUNCTION_CODES;
import com.silverlake.mleb.mcb.entity.dao.MLEBCOREDAO;
import com.silverlake.mleb.mcb.task.ReloadMessageProperties;
import com.silverlake.mleb.mcb.util.MessageManager;
import com.silverlake.mleb.mcb.util.PropertiesManager;
import com.silverlake.mleb.mcb.util.PropertiesUtil;


 

public class InitApp {
 
	@Autowired
    private ServletContext servletContext;
	
//	@Autowired RefreshBankList refreshBankList;
//	@Autowired RefreshExchangeRate refreshExchangeRate;
//	@Autowired RefreshFundTransferTransferLimit refreshFundTransferTransferLimit;
//	@Autowired RefreshUTProductList refreshUTProductList;
//	@Autowired RefreshTelegraphicTransferBankInfo refreshTelegraphicTransferBankInfo;
	@Autowired ReloadMessageProperties reloadMessageProperties;
//	@Autowired ClearWLSession clearWLSession;
	
	private static final Logger log = LogManager.getLogger(InitApp.class);
	public static Mapper mapper = new DozerBeanMapper();	
	
	@Value ("${rmi.registryPort}")
	private String rmiPort;

	private PropertiesUtil proSys = new PropertiesUtil();
	private HessianProxyFactory factory = new HessianProxyFactory();
	
	@Autowired MLEBCOREDAO mlebDao ;
	
	public void initApp() throws Exception
	{
		PropertiesManager.resetConf();
		String appPath = System.getProperty("app.store.path");
		PropertiesManager prox = new PropertiesManager();
		MessageManager msgManager = new MessageManager();
		String proFilePath = prox.getRealProPath();
		//String msgFilePath= msgManager.getOutputPath()+msgManager.getAppname()+".properties";
		//String msgFilePathKH= msgManager.getOutputPath()+msgManager.getAppnameIN()+".properties";
		
		LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
		Configuration config = ctx.getConfiguration();
		RollingFileAppender techical = (RollingFileAppender) config.getAppender("logfile");
		
		
		log.info(printBox("["+servletContext.getContextPath()+"]"
        		+ "\nLog File : "+techical.getFileName()
        		+ "\nProperties File : "+proFilePath
        		+ "\nRMI Port : "+rmiPort
				));
		
		// LimKN 11-Nov-2017
		// To fasten the lauching of mib.war, c
		// omment out the line if you want to bump data into the empty table
//		refreshBankList.process();
//		refreshExchangeRate.process();
//		refreshFundTransferTransferLimit.process();
//		refreshUTProductList.process();
		//reloadMessageProperties.reloadMsg();
//		clearWLSession.clearSession();
//		refreshTelegraphicTransferBankInfo.process();
		
		addServiceId(prox);

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
	
	
	
	
	
	
	
	 
	public void addServiceId(PropertiesManager pro)  
	{ 
		
		String check =  pro.getProperty("add.mleb.function");
		check = check==null?"false":check;
		log.info("insert function ID : "+check);
		Field[] aClassFields = MiBConstant.class.getDeclaredFields();
	         
		for(Field f : aClassFields){
		    String fName = f.getName();
		    
			if(fName.toLowerCase().startsWith("func_mcb_"))
			{
				if(!check.equalsIgnoreCase("false"))
				{						
					String[] DB_PORT = check.split(":");
					String url = "http://localhost:"+DB_PORT[1]+"/mcb/remoting/mcb_api";
					try
					{
						mlebDao.insertMLEBFunction(f.get(this).toString(), url, DB_PORT[0]);
					}catch(Exception e)
					{
						e.printStackTrace();
					}
				}				    	 
			}
		}
		
		if(!check.equalsIgnoreCase("false"))
		{
			for(FUNCTION_CODES functionCodeEnum: VCMethodConstant.FUNCTION_CODES.values()){
				String fName = functionCodeEnum.getCode();
				if(fName != null && !fName.isEmpty()){
					String[] DB_PORT = check.split(":");
					String url = "http://localhost:"+DB_PORT[1]+"/mcb/remoting/mcb_api";
					try
					{
						mlebDao.insertMLEBFunction(fName, url, DB_PORT[0]);
					}catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		}
	}
 
}