package com.silverlake.mleb.ccmcb.util;
import java.util.List;
import java.util.Properties;

import com.silverlake.mleb.mcb.bean.ObGeneralCodeBean;
import com.silverlake.mleb.mcb.constant.MiBConstant;






public class CacheMessageManager
{
	private static Properties properties ;
	private static Properties propertiesIN ;
	private static Properties propertiesCN ;
	

	private static String outputPath ;
	
	public static String getOutputPath() {
		return outputPath;
	}


	public static void setOutputPath(String outputPath) {
		CacheMessageManager.outputPath = outputPath;
	}


	public CacheMessageManager()
	{
	
		try
		{
			
			if(properties == null)
			{
			
			
				properties = new Properties();
				propertiesIN = new Properties();
				propertiesCN = new Properties();
				
				
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
		else if (locale != null  && locale.toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase())) 
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
	
	public Properties getInProperties()
	{
		return propertiesIN;
	}

	public Properties getCnProperties()
	{
		return propertiesCN;
	}
	
	public void processUpdateCache(List<ObGeneralCodeBean> msgBean)
	{
		if(msgBean!=null)
		{
			getDefaultProperties().clear();
			getInProperties().clear();
			getCnProperties().clear();
			
			for(ObGeneralCodeBean temp:msgBean)
			{
				if(temp.getGnCodeType().equalsIgnoreCase(MiBConstant.LOCALE_EN))
				{
					getDefaultProperties().setProperty(temp.getGnCode(), temp.getGnCodeDescription());
				}
				else if(temp.getGnCodeType().toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase()))
				{
					getCnProperties().setProperty(temp.getGnCode(), temp.getGnCodeDescription());
				}
				else 
				{
					getInProperties().setProperty(temp.getGnCode(), temp.getGnCodeDescription());
				}
			}
		}
	}
	
	
}