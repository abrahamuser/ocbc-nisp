package com.silverlake.mleb.mcb.util;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import com.silverlake.mleb.mcb.constant.MiBConstant;


public class MessageManager
{
	private static Properties properties ;
	private static Properties propertiesIn ;
	private static Properties propertiesCn ;
	private static final String appName = "mcbmsg";
	private static final String appName_in = "mcbmsg_in";
	private static final String appName_cn = "mcbmsg_cn";

	private static String outputPath ;

	public MessageManager()
	{
		
		String appPropertyPath = System.getProperty(PropertiesManager.PROPERTIES_SYSTEM_PATH_NAME);
		//System.out.println("app_properties_path :: "+appPropertyPath);
		if(true)
			return;
		
		
		try
		{
			
			if(properties == null)
			{
				String serverPath = "";
				
				properties = new Properties();
				FileInputStream in = null;
				
					

				if(appPropertyPath != null && !appPropertyPath.equals(""))
				{
					serverPath = appPropertyPath;
				}
				
				outputPath = serverPath+"/properties/";
				
				in = new FileInputStream(outputPath+appName+".properties");
			
				properties.load(in);
				
				try
				{
					if(in!=null)
						in.close();
					
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			
			if(propertiesIn == null)
			{
				String serverPath = "";
				
				propertiesIn = new Properties();
				FileInputStream in2 = null;
				
					
				if(appPropertyPath != null && !appPropertyPath.equals(""))
				{
					serverPath = appPropertyPath;
				}
				
				outputPath = serverPath+"/properties/";
				
				in2 = new FileInputStream(outputPath+appName_in+".properties");
			
				propertiesIn.load(in2);
				
				try
				{
					if(in2!=null)
						in2.close();
					
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}

			if(propertiesCn == null)
			{
				String serverPath = "";
				
				propertiesCn = new Properties();
				FileInputStream in3 = null;
				
					
				if(appPropertyPath != null && !appPropertyPath.equals(""))
				{
					serverPath = appPropertyPath;
				}
				
				outputPath = serverPath+"/properties/";
				
				in3 = new FileInputStream(outputPath+appName_cn+".properties");
			
				propertiesCn.load(in3);
				
				try
				{
					if(in3!=null)
						in3.close();
					
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		catch(Exception E)
		{
			E.printStackTrace();
		}
			
	}
	
	public String getProperty(String property,String locale)
	{	
		locale=null==locale?MiBConstant.LOCALE_EN:locale;

		if(locale.equalsIgnoreCase(MiBConstant.LOCALE_EN))
		{
			return properties.getProperty(property);	
		}
		else if(locale != null  && locale.toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase()))
		{
			return propertiesCn.getProperty(property);	
		}
		else
		{
			return propertiesIn.getProperty(property);	
		}
			
	}
	
	
	public Properties getDefaultProperties()
	{
		return properties;
	}
	
	public Properties getINProperties()
	{
		return propertiesIn;
	}

	public Properties getCNProperties()
	{
		return propertiesCn;
	}
	
	public void updateDefaultPropertiesFile() 
	{
		try
		{
			SortedProperties newPro = new SortedProperties();
			newPro.putAll(properties);
			
			FileOutputStream out = new FileOutputStream(outputPath+appName+".properties");
			newPro.store(out, "");
			out.close();
	
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
			newPro.putAll(propertiesIn);
			
			FileOutputStream out = new FileOutputStream(outputPath+appName_in+".properties");
			newPro.store(out, "");
			out.close();
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
			newPro.putAll(propertiesCn);
			
			FileOutputStream out = new FileOutputStream(outputPath+appName_cn+".properties");
			newPro.store(out, "");
			out.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	

	public static String getOutputPath() {
		return outputPath;
	}

	public static void setOutputPath(String outputPath) {
		MessageManager.outputPath = outputPath;
	}

	public static String getAppname() {
		return appName;
	}

	public static String getAppnameIN() {
		return appName_in;
	}	
}