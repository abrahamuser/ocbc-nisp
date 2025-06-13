package com.silverlake.mleb.pex.util;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import com.silverlake.hlb.mib.constant.MiBConstant;






public class MessageManager
{
	private static Properties properties ;
	private static Properties propertiesKH ;
	private static final String appName = "ccmsg";
	private static final String appName_kh = "ccmsg_kh";
	private static String outputPath ;
	
	public MessageManager()
	{
		String serverPath = "";
		//System.out.println(serverPath);
		try
		{
			
			if(properties == null)
			{
			
				
				//PropertiesManager.printLine(100,false);
				//PropertiesManager.printMsg("Initial Application properties [ "+appName+" ] ",100);
				
				
				
				
				properties = new Properties();
				propertiesKH = new Properties();
				FileInputStream in = null;
				FileInputStream in2 = null;
				
					
					//PropertiesManager.printMsg("server log path : "+serverPath+"/properties/"+appName+".properties",100);
					
					
					String appPropertyPath = System.getProperty(PropertiesManager.PROPERTIES_SYSTEM_PATH_NAME);
					//System.out.println("app_properties_path :: "+appPropertyPath);
					if(appPropertyPath != null && !appPropertyPath.equals(""))
					{
						serverPath = appPropertyPath;
					}
					
					outputPath = serverPath+"/properties/";
					
					in = new FileInputStream(outputPath+appName+".properties");
					try{in2 = new FileInputStream(outputPath+appName_kh+".properties");}catch(Exception e){};
					properties.load(in);
					try{propertiesKH.load(in2);}catch(Exception e){}
				
				
				try
				{
					in.close();
					
					if(null!=in2)
						in2.close();
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
	


	public String getProperty(String property,String locale)
	{	locale=null==locale?MiBConstant.LOCALE_EN:locale;
		if(locale.equalsIgnoreCase(MiBConstant.LOCALE_EN))
		{
			return properties.getProperty(property);	
		}
		else if(locale.equalsIgnoreCase(MiBConstant.LOCALE_KH))
		{
			return properties.getProperty(property);	
		}
		else
		{
			return null;
		}
			
	}
	
	
	public Properties getDefaultProperties()
	{
		return properties;
	}
	
	public Properties getVNProperties()
	{
		return propertiesKH;
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
	
	
	public void updateVNPropertiesFile() 
	{
		try
		{
			SortedProperties newPro = new SortedProperties();
			newPro.putAll(propertiesKH);
			
			FileOutputStream out = new FileOutputStream(outputPath+appName_kh+".properties");
			//FileOutputStream out2 = new FileOutputStream(outputPath+appName_vn+".propertiesx");
			newPro.store(out, "");
			out.close();
			//out2.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public static void main(String args[])
	{
		
		try {
			
			for(int i=0;i<10000000;i++)
			{
				properties = new Properties();
				FileInputStream in = null;
				in = new FileInputStream("d:\\test.properties");
				properties.load(in);
				System.out.println("Done : "+i);
			}
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
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



	public static String getAppnameKh() {
		return appName_kh;
	}
	
}