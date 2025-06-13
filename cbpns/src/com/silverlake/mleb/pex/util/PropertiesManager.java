package com.silverlake.mleb.pex.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.StringReader;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;







public class PropertiesManager
{
	private static Properties properties ;
	private static final String appName = "pns";
	private static String realProPath = ""; 
	private static String appPath = ""; 
	public static String PROPERTIES_SYSTEM_PATH_NAME = "app.store.path";
	
	
	public PropertiesManager()
	{
		String serverPath = "";
		try
		{
			
			if(properties == null)
			{

				
				String proPath = "/properties/"+appName+".properties";
				properties = new Properties();
				

					String appPropertyPath = System.getProperty(PROPERTIES_SYSTEM_PATH_NAME);
					if(appPropertyPath != null && !appPropertyPath.equals(""))
					{
						serverPath = appPropertyPath;
					}
					
					realProPath = serverPath+proPath;
					appPath = serverPath;
					File yourFile = new File(realProPath);
					if(!yourFile.exists()) {
						yourFile.getParentFile().mkdirs();
					    yourFile.createNewFile();
					} 
					
					properties.load(new FileInputStream(realProPath));
				
			}
	
		}
		catch(Exception E)
		{
			E.printStackTrace();
		}
			

	}
	
	 
	
	
	
	public static String getRealProPath() {
		return realProPath;
	}





	public static void setRealProPath(String realProPath) {
		PropertiesManager.realProPath = realProPath;
	}





	public static String getAppPath() {
		return appPath;
	}





	public static void setAppPath(String appPath) {
		PropertiesManager.appPath = appPath;
	}





	public String getProperty(String property)
	{
		return properties.getProperty(property);		
	}
	
	 public static Document stringToDom(String xmlSource) throws Exception {
	        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder builder = factory.newDocumentBuilder();
	        return builder.parse(new InputSource(new StringReader(xmlSource)));
	    }
	
	public static void main(String args[]) throws Exception
	{
		String abc = "<Msg><Header><SC_TransType>270102</SC_TransType><SC_ApplName>MOBMY</SC_ApplName><SC_ApplID>MOBMY</SC_ApplID><SC_ApplTransID>14326055832215a10032</SC_ApplTransID><SC_TransDate>260515</SC_TransDate><SC_TransTime>090544</SC_TransTime><SC_TransUserID/><SC_TransUserInfo/><SC_ApplUserID>MOBILITY</SC_ApplUserID><SC_TellerID>MOBI001</SC_TellerID><SC_BranchCode>11</SC_BranchCode><SC_CtrlUnit>1</SC_CtrlUnit><SC_OCMUSER/><BrokerHost>UATEAIMBS01</BrokerHost><RequestDateStamp>2015-05-26 09:55:45.132216</RequestDateStamp><ResponseDateStamp>2015-05-26 09:55:45.624257</ResponseDateStamp></Header><Body><IBFTInq><Request><JournalSequence>000009</JournalSequence><CCNo>4293200004711101</CCNo><BeneBkRoute>501664</BeneBkRoute><AbvBeneBk>ABB</AbvBeneBk><BeneAccNo>200020614331</BeneAccNo><BeneAccType>1</BeneAccType></Request><Response><BeneName>NTHE*MASKED   CAT</BeneName></Response></IBFTInq></Body></Msg>";
		System.out.println(""+new String(abc.getBytes("UTF-8")));
		Document x = stringToDom(abc);
		
		

	}
}