package com.silverlake.mleb.ccmcb.util;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import com.silverlake.mleb.mcb.constant.MiBConstant;






public class MessageManager
{
	private static Properties properties ;
	private static Properties propertiesIN ;
	private static Properties propertiesCN ;
	private static final String appName = "ccmsg";
	private static final String appName_in = "ccmsg_in";
	private static final String appName_cn = "ccmsg_cn";

	public static String getAppname() {
		//return appName;
		return "";
	}


	public static String getAppnameKh() {
		//return appName_in;
		return "";
	}

	private static String outputPath ;
	
	public static String getOutputPath() {
		return outputPath;
	}


	public static void setOutputPath(String outputPath) {
		MessageManager.outputPath = outputPath;
	}


	public MessageManager()
	{
		String serverPath = "";
		//System.out.println(serverPath);
		
		if(true)
		{
			return;
		}
		
		try
		{
			
			if(properties == null)
			{
			
				
				//PropertiesManager.printLine(100,false);
				//PropertiesManager.printMsg("Initial Application properties [ "+appName+" ] ",100);
				
				
				
				
				properties = new Properties();
				propertiesIN = new Properties();
				propertiesCN = new Properties();
				FileInputStream in = null;
				FileInputStream in2 = null;
				FileInputStream in3 = null;
					
					//PropertiesManager.printMsg("server log path : "+serverPath+"/properties/"+appName+".properties",100);
					
					
					String appPropertyPath = System.getProperty(PropertiesManager.PROPERTIES_SYSTEM_PATH_NAME);
					//System.out.println("app_properties_path :: "+appPropertyPath);
					if(appPropertyPath != null && !appPropertyPath.equals(""))
					{
						serverPath = appPropertyPath;
					}
					
					outputPath = serverPath+"/properties/";
					
					in = new FileInputStream(outputPath+appName+".properties");
					try{in2 = new FileInputStream(outputPath+appName_in+".properties");}catch(Exception e){};
					try{in3 = new FileInputStream(outputPath+appName_cn+".properties");}catch(Exception e){};
					properties.load(in);
					try{propertiesIN.load(in2);}catch(Exception e){}
					try{propertiesCN.load(in3);}catch(Exception e){}
				
				
				try
				{
					in.close();
					
					if(null!=in2)
						in2.close();
					if(null!=in3)
						in3.close();
				}catch(Exception e)
				{
					e.printStackTrace();
				}
				
				//PropertiesManager.printLine(100,true);
			}
	
		}
		catch(Exception E)
		{
			E.printStackTrace();
		}
			

	}
	
	
	public void reloadMsg()
	{
		String serverPath = "";
		//System.out.println(serverPath);
		try
		{
			
			
				
				//PropertiesManager.printLine(100,false);
				//PropertiesManager.printMsg("Initial Application properties [ "+appName+" ] ",100);
				
				
				
				
				properties = new Properties();
				propertiesIN = new Properties();
				propertiesCN = new Properties();
				FileInputStream in = null;
				FileInputStream in2 = null;
				FileInputStream in3 = null;
					
					//PropertiesManager.printMsg("server log path : "+serverPath+"/properties/"+appName+".properties",100);
					
					
					String appPropertyPath = System.getProperty(PropertiesManager.PROPERTIES_SYSTEM_PATH_NAME);
					//System.out.println("app_properties_path :: "+appPropertyPath);
					if(appPropertyPath != null && !appPropertyPath.equals(""))
					{
						serverPath = appPropertyPath;
					}
					
					outputPath = serverPath+"/properties/";
					
					in = new FileInputStream(outputPath+appName+".properties");
					try{in2 = new FileInputStream(outputPath+appName_in+".properties");}catch(Exception e){};
					try{in3 = new FileInputStream(outputPath+appName_cn+".properties");}catch(Exception e){};
					properties.load(in);
					try{propertiesIN.load(in2);}catch(Exception e){}
					try{propertiesCN.load(in3);}catch(Exception e){}
				
				
				try
				{
					in.close();
					
					if(null!=in2)
						in2.close();
					if(null!=in3)
						in3.close();
				}catch(Exception e)
				{
					e.printStackTrace();
				}
				
				//PropertiesManager.printLine(100,true);
			
	
		}
		catch(Exception E)
		{
			E.printStackTrace();
		}
			

	}
	
	


	public String getProperty(String property,String locale)
	{	locale=null==locale?MiBConstant.LOCALE_EN:locale;
		if(locale.equalsIgnoreCase(MiBConstant.LOCALE_EN))
		{
			return properties.getProperty(property);	
		}
		else if(locale != null  && locale.toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase()))
		{
			return propertiesCN.getProperty(property);	
		}
		else 
		{
			return propertiesIN.getProperty(property);	
		}
			
	}
	
	
	public Properties getDefaultProperties()
	{
		return properties;
	}
	
	public Properties getVNProperties()
	{
		return propertiesIN;
	}

	public Properties getCNProperties()
	{
		return propertiesCN;
	}
	
	public void updateDefaultPropertiesFile() 
	{
		try
		{
			SortedProperties newPro = new SortedProperties();
			newPro.putAll(properties);
			
			FileOutputStream out = new FileOutputStream(outputPath+appName+".properties");
			//FileOutputStream out2 = new FileOutputStream(outputPath+appName_vn+".propertiesx");
			newPro.store(out, "");
			out.close();
			//out2.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	public void updateINPropertiesFile() 
	{
		try
		{
			SortedProperties newPro = new SortedProperties();
			newPro.putAll(propertiesIN);
			
			FileOutputStream out = new FileOutputStream(outputPath+appName_in+".properties");
			//FileOutputStream out2 = new FileOutputStream(outputPath+appName_vn+".propertiesx");
			newPro.store(out, "");
			out.close();
			//out2.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	public void updateCNPropertiesFile() 
	{
		try
		{
			SortedProperties newPro = new SortedProperties();
			newPro.putAll(propertiesCN);
			
			FileOutputStream out = new FileOutputStream(outputPath+appName_cn+".properties");
			newPro.store(out, "");
			out.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
//	public static void main(String args[])
//	{
//		
//		try {
//			
//			for(int i=0;i<10000000;i++)
//			{
//				properties = new Properties();
//				FileInputStream in = null;
//				in = new FileInputStream("d:\\test.properties");
//				properties.load(in);
//				System.out.println("Done : "+i);
//			}
//			
//			
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		
//	}
	
}