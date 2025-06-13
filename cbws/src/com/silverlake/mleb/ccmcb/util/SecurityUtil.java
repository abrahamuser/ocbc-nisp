package com.silverlake.mleb.ccmcb.util;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import javax.crypto.Cipher;
import javax.servlet.http.HttpSession;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

//import com.sun.org.apache.xerces.internal.dom.TextImpl;



public class SecurityUtil
{

	public  static final  String PRO_CSRF_CHECK_TAG = "security.csrf.check";
	public  static final  String PRO_INPUT_CHECK_TAG = "security.input.check";
//	public static final String MPOS_LOGIN_USER_SESSION_TAG = "MPOS_LOGIN_USER_SESSION_TAG";
	private static Logger log = LogManager.getLogger(SecurityUtil.class);
	 


	public  static final  String [] WSSESSIONMETHOD = {"performLogin", "retrieveSecInfo","retrieveHolidayList","retrieveFaq","sendFeedbackFaq","retrieveExchangeRate","performResetPassword",
			SecurityUtil.RESPONSE_UpdateTNC,
			SecurityUtil.RESPONSE_PerformDeviceUnBind,
			"retrieveResetPassTnC",
			"performBiometricSetup",
			"retrieveInquiryRegistration",
			"retrieveInputterSignerData",
			"addDeleteSignerData",
			"retrieveProvinceList",
			"registrationRevisionSubmit",
			"registrationAckSubmit",
			"registrationCancel",
			"performDeviceUnBindg",
			"retrieveTnCv2",
			"performBlockMyUser",
			"retrieveForm",
			"otpVerify",
			"performLoginv2",
			};
	
	
 

	public  static final  String RESPONSE_Login = "performLogin";
	public  static final  String RESPONSE_LoginV2 = "performLoginV2";
	public  static final  String RESPONSE_LoginV3 = "performLoginV3";
	public  static final  String RESPONSE_Logout = "performLogout";
	public  static final  String RESPONSE_DeviceTagging = "performDeviceBinding";
	public  static final  String RESPONSE_DeviceTaggingV2 = "performDeviceBindingv2";
	public 	static final  String RESPONSE_RequestDeviceTAC = "requestDeviceTAC";
	public 	static final  String RESPONSE_RetrieveDeviceProfile = "retrieveDeviceProfile";
	public 	static final  String RESPONSE_PerformDeviceUnBind = "performDeviceUnBindOpen";
	public 	static final  String RESPONSE_UpdateTNC = "performUpdateTnC";
	public  static final  String RESPONSE_TAG = "Response";
	public  static final  String CSRF_TOKEN_TAG = "csrf_token";
	
	
	public  static final String _MODEL_TAG = "model";
	public  static final String _ID_TAG = "id";
	public  static final String _MAKE_TAG = "make";
	public  static final String _TYPE_TAG = "type";
	public  static final String _OS_TAG = "os";

	public  static final String _LATITUDE_TAG = "latitude";
	public  static final String _LONGTITUDE_TAG = "longtitude";
	public  static final String _ALTITUDE_TAG = "altitude";
	public  static final String _CITY_TAG = "city";
	public  static final String _STATE_TAG = "state";
	public  static final String _COUNTRY_TAG = "country";
	
	public  static final String _LOCALE_TAG = "locale";
	public  static final String _APPVERSION_TAG = "appversion";
	public  static final String _ROOTED_TAG = "rooted";
	public static final String _PNS_DEVICE_TOKEN = "pnstoken";
	public static final String _APP_MODE = "app_mode";
	public static final String _IP = "ip";
	
	

	public  static final  String CSRF_SESSION_TAG = "HLB@CSRF_TOKEN";
	public  static final  String LOGIN_SESSION_TAG = "HLB@LOGIN_KEYID";
	public  static final  String PRELOGIN_SESSION_TAG = "HLB@PRELOGIN_KEYID";
	public  static final  String PRETNC_SESSION_TAG = "HLB@PRETNC_KEYID";
	public  static final  String LOGIN_USER_SESSION_TAG = "HLB@LOGIN_USER_DATA";
	public  static final  String APPDEVICE_SESSION_TAG = "HLB@APP_DEVICE_INFO";
	public  static final  String MPOS_LOGIN_USER_SESSION_TAG = "HLB@MPOS_LOGIN_USER_DATA";
	
	

	public  static final  String SECURITY_SESSION_ERROR_TAG = "WSS_ERROR";
	public  static final  String SECURITY_SESSION_ERROR_PARAM_TAG = "WSS_ERROR_PARAM";
	public  static final  String SECURITY_SESSION_ERROR_REGEX_TAG = "WSS_ERROR_REGEX";
	
	public  static final  String SECURITY_SESSION_REPLACE_TAG = "WSS_ERROR_REPLACE";
	public  static final  String SECURITY_ERROR_INVALID_LOGIN_SESSION = "WSS_ERROR_00001";
	public  static final  String SECURITY_ERROR_INVALID_CSRF_TOKEN = "WSS_ERROR_00002";
	public  static final  String SECURITY_ERROR_SESSION_USER_EXISTED = "WSS_ERROR_00003";
	public  static final  String SECURITY_ERROR_INVALID_INPUT_PARAM = "WSS_ERROR_00004";
	public  static final  String SECURITY_ERROR_INVALID_LOGIN_ACTION = "WSS_ERROR_00005";
	public  static final  String SECURITY_ERROR_SESSION_REMOVED = "WSS_ERROR_00006";
	public  static final  String SECURITY_ERROR_INVALID_DEVICETAG_SESSION = "WSS_ERROR_00007";
	public  static final  String SECURITY_ERROR_UNTAGGED_DEVICE_SESSION = "WSS_ERROR_00008";
	public  static final  String SECURITY_ERROR_INVALID_TNC_SESSION = "WSS_ERROR_00009";
	public  static final  String SECURITY_ERROR_UNKNOW_TNC_SESSION = "WSS_ERROR_00010";
	public  static final  String SECURITY_ERROR_UNKNOW_TAC_SESSION = "WSS_ERROR_00011";
	
	public  static final  String USER_SUCCESSFUL_LOGIN_TIME = "USR_SUC_LOGIN_TIME";
	
	
	
	public static PropertiesManager property = new PropertiesManager();
	
	
	
	
	
	public String createCsrfToken()
	{	
		Random rnd = new Random();
  		int crsfValue = rnd.nextInt(1000000);
  		String csrfToken = crsfValue+"";
  		
  		while(csrfToken.length()<6)
  		{
  			csrfToken = "0"+csrfToken;
  		}
  		
  		return csrfToken;
	}
	
	
	public String getWSMethod(SOAPBody body)
	{
		String wsMethod = null;
		 NodeList nl = body.getChildNodes();
         
         for (int i = 0; i < nl.getLength(); i++) {
         
         	Node node = (Node)nl.item(i);
         	
         	if (node instanceof SOAPElement) {
                 SOAPElement se = (SOAPElement)node;

                 // get the name of operation being performed
                 wsMethod = se.getLocalName();
         	}
         }
	
		return wsMethod;
	}
	
	
	public HashMap getWSHeaderParamValue(SOAPHeader header)
	{
		HashMap hmap = new HashMap();
		
		NodeList nh = header.getChildNodes();
		for(int ihx = 0 ; ihx<nh.getLength();ihx++)
		{
			Node nodeh = (Node)nh.item(ihx);
			if (nodeh instanceof SOAPElement)
			{
				hmap.put(nodeh.getNodeName(), nodeh.getTextContent());
			}
			
		}
		
		return hmap;
	}
	
	
	
	public HashMap getWSParamValue(SOAPBody body)
	{
		
		HashMap hmap = new HashMap();
		String wsMethod = null;
		 NodeList nl = body.getChildNodes();
		// log.info("------------------------body check---------------------------"+nl.getLength());
        for (int i = 0; i < nl.getLength(); i++) {
        
        	Node node = (Node)nl.item(i);
        	
        	if (node instanceof SOAPElement) {
                SOAPElement soapElement = (SOAPElement)node;

                Iterator<?> ie = soapElement.getChildElements();
                int secondLevelID = 0;
                while(ie.hasNext())
                {
                	Object ob = ie.next();
                	
             	   	if(ob instanceof SOAPElement)
             	   	{
             	   		
             	   		SOAPElement soapElement2 = (SOAPElement)ob;
             	   		
             	   		//log.info("------------------------body value :::: ["+soapElement2+"]["+soapElement2.getValue()+"]");
             	   		if(null!=soapElement2.getValue() && soapElement2.getValue().trim().length()>0 )
             	   		{
             	   			//log.info("------------------------body add "+soapElement2.getTextContent());
             	   			
             	   			if(hmap.containsKey(soapElement2.getTagName()))
             	   			{

             	   				hmap.put(soapElement2.getTagName(), hmap.get(soapElement2.getTagName())+soapElement2.getTextContent());
             	   			}
             	   			else
             	   			{
             	   				hmap.put(soapElement2.getTagName(), soapElement2.getTextContent());
             	   			}
             	   		}
             	   		else
             	   		{
             	   			
             	   			//second level attribute
             	   			Iterator<?> ie2 =  soapElement2.getChildElements();
             	   			while(ie2.hasNext())
             	   			{
             	   				
             	   				Object ob2 = ie2.next();
             	   				//log.info("------------------------body check---------------------------xxx class : "+ob2.getClass()+" -- "+ob2.toString());
	             	   			if(ob2 instanceof SOAPElement)
	                     	   	{
	             	   			   
	             	   				SOAPElement soapElement3 = (SOAPElement)ob2;
	             	   				//log.info("--------------"+soapElement3.getTagName()+"----------body check elements---------------------------xxx soap : "+soapElement3.getTextContent());
	             	   				if(secondLevelID<30)
	             	   				{
	             	   					if(soapElement2 != null && soapElement2.getTagName() != null && soapElement2.getTagName().toString().equalsIgnoreCase("ktpInfo")){
	             	   						hmap.put(soapElement2.getTagName() + "_" + soapElement3.getTagName()+"_"+secondLevelID, soapElement3.getTextContent());
	             	   					}else{
	             	   						hmap.put(soapElement3.getTagName()+"_"+secondLevelID, soapElement3.getTextContent());
	             	   					}
	             	   				}
	             	   				else
	             	   				{
	             	   					hmap.put("over_flow_list_check", "");
	             	   					break;
	             	   				}
	             	   				
	             	   				
	                     	   	}
	             	   			else
	             	   			{
	             	   				//SOAPElement  xxx =  (SOAPElement) ob2;
	             	   				
	             	   				//log.info("------------["+ob2.getClass()+"]------------body check text-----"+soapElement2.getTagName()+"--------"+soapElement2.getTextContent());
	             	   				//hmap.put(soapElement2.getTagName()+"_"+secondLevelID, soapElement2.getTextContent());
	             	   			}
             	   			}
             	   			secondLevelID++;
             	   		}
             	   	}
             	  
                }
        	}
        }
		
		
		return hmap;
	}
	
	
	
	
	
	
	public HashMap getWSParamValue(SOAPHeader body)
	{
		
		HashMap hmap = new HashMap();
		String wsMethod = null;
		 NodeList nl = body.getChildNodes();
        
        for (int i = 0; i < nl.getLength(); i++) {
        
        	Node node = (Node)nl.item(i);
        	
        	if (node instanceof SOAPElement) {
                SOAPElement soapElement = (SOAPElement)node;

                Iterator<?> ie = soapElement.getChildElements();
                
                while(ie.hasNext())
                {
                	Object ob = ie.next();
             	   	if(ob instanceof SOAPElement)
             	   	{
             	   		SOAPElement soapElement2 = (SOAPElement)ob;
             	   
             	   		hmap.put(soapElement2.getTagName(), soapElement2.getTextContent());
             	   	}
             	  
                }
        	}
        }
		
		
		return hmap;
	}
	
	
	public void addSecurityResponseHeader (String header, String value, SOAPEnvelope envelope) throws SOAPException
	{
		
		if (envelope.getHeader() != null) {
		     envelope.getHeader().detachNode();
		  }
        SOAPHeader respHeader = envelope.addHeader();
        QName qname = new QName("http://security.ccmcb.mleb.silverlake.com/", "security");
        SOAPHeaderElement securityElement = respHeader.addHeaderElement(qname);
        SOAPElement tagCsrfToken = securityElement.addChildElement(header);
        tagCsrfToken.addTextNode(value);
        
	}
	
	
	public void addSecurityResponseBody (String header, String value, SOAPEnvelope envelope) throws SOAPException
	{
		
		
        SOAPBody respHeader = envelope.getBody();
     
        
        NodeList nl1 = respHeader.getChildNodes();
		 Node n_ns = (Node)nl1.item(0);
		 
		 NodeList nl2 = n_ns.getChildNodes();
		 Node n_obHeader = (Node)nl2.item(0);
		 
		 
		 NodeList nl3 = n_obHeader.getChildNodes();
		 Node n_obHeader_data = (Node)nl3.item(0);
		 if (n_obHeader_data instanceof SOAPElement) {
             SOAPElement soapElement = (SOAPElement)n_obHeader_data;
          
             
             SOAPElement tagCsrfToken = soapElement.addChildElement("acknowledgementNumber");
             tagCsrfToken.addTextNode(value);
		 }
        
        
       
        
	}
	
	
	
	
	public void updateCSRFValue(SOAPBody body,String csrf) throws SOAPException
	{
		
		HashMap hmap = new HashMap();
	
		 NodeList nl1 = body.getChildNodes();
		 Node n_ns = (Node)nl1.item(0);
		 
		 NodeList nl2 = n_ns.getChildNodes();
		 Node n_obHeader = (Node)nl2.item(0);
		 
		 
		 NodeList nl3 = n_obHeader.getChildNodes();
		 Node n_obHeader_data = (Node)nl3.item(0);
		 
		 
		 
		 
		 
		 if (n_obHeader_data instanceof SOAPElement) {
             SOAPElement soapElement = (SOAPElement)n_obHeader_data;
            
             
             MessageFactory factory = MessageFactory.newInstance();
             SOAPMessage message = factory.createMessage();
             SOAPFactory soapFactory = SOAPFactory.newInstance();
             soapFactory = SOAPFactory.newInstance();
             Name childName = soapFactory.createName("acknowledgementNumber","","");
             SOAPElement symbol = soapElement.addChildElement(childName);
             symbol.addTextNode(csrf);
             
           
        
             
            /* Iterator<?> ie = soapElement.getChildElements();
             
             
             
             
             while(ie.hasNext())
             {
             	Object ob = ie.next();
          	   	if(ob instanceof SOAPBodyElement)
          	   	{
          	   		SOAPBodyElement soapElement2 = (SOAPBodyElement)ob;
          	   
          	   		hmap.put(soapElement2.getTagName(), soapElement2.getTextContent());
          	   	}
          	  
             }*/
     	}
		
		
		
	}
	
	
	
	
	

	public HashMap getWSResponseHeaderParamValue(SOAPBody body)
	{
		
		HashMap hmap = new HashMap();
	
		 NodeList nl1 = body.getChildNodes();
		 Node n_ns = (Node)nl1.item(0);
		 
		 NodeList nl2 = n_ns.getChildNodes();
		 Node n_obHeader = (Node)nl2.item(0);
		 
		 
		 NodeList nl3 = n_obHeader.getChildNodes();
		 Node n_obHeader_data = (Node)nl3.item(0);
		 
		 
		 
		 
		 
		 if (n_obHeader_data instanceof SOAPElement) {
             SOAPElement soapElement = (SOAPElement)n_obHeader_data;

             Iterator<?> ie = soapElement.getChildElements();
             
             while(ie.hasNext())
             {
             	Object ob = ie.next();
          	   	if(ob instanceof SOAPBodyElement)
          	   	{
          	   		SOAPBodyElement soapElement2 = (SOAPBodyElement)ob;
          	   
          	   		hmap.put(soapElement2.getTagName(), soapElement2.getTextContent());
          	   	}
          	  
             }
     	}
		
		
		return hmap;
	}
	
	
	
	
	
	
	
	public boolean isSessionMethod(String methodName)
	{
		
		for(int i = 0 ; i<WSSESSIONMETHOD.length; i++)
		{
			
			if(null != methodName && methodName.equals(WSSESSIONMETHOD[i]))
			{
				return false;
			}
		}
		return true;
	}
	
	
 
	
	
	 private void dumpSOAPMessage (SOAPMessage msg)
	  {
	    if (msg == null)
	    {
	      log.debug ("SOAP Message is null");
	      return;
	    }
	    log.debug ("");
	    log.debug ("--------------------");
	    log.debug ("DUMP OF SOAP MESSAGE");
	    log.debug ("--------------------");
	    try
	    {
	      ByteArrayOutputStream baos = new ByteArrayOutputStream ();
	      msg.writeTo (baos);
	      log.debug (baos.toString (getMessageEncoding (msg)));
	      // show included values
	      String values = msg.getSOAPBody ().getTextContent ();
	      log.debug ("Included values:" + values);
	      
	      baos.close();
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace ();
	    }
	  }
	 
	 private String getMessageEncoding (SOAPMessage msg) throws SOAPException
	  {
	    String encoding = "utf-8";
	    if (msg.getProperty (SOAPMessage.CHARACTER_SET_ENCODING) != null)
	    {
	      encoding = msg.getProperty (SOAPMessage.CHARACTER_SET_ENCODING)
	          .toString ();
	    }
	    return encoding;
	  }
	 

		
		public boolean is_csrf_check()
		{
			String csrf = property.getProperty(PRO_CSRF_CHECK_TAG);
			
			if(null!=csrf && csrf.trim().equalsIgnoreCase("true"))
			{
				return true;
			}
			else if(null!=csrf && !csrf.trim().equalsIgnoreCase("false"))
			{
				return true;
			}

			return false;
		}
		

		
		
		public boolean is_input_check()
		{
			String csrf = property.getProperty(PRO_INPUT_CHECK_TAG);
			
			if(null!=csrf && csrf.trim().equalsIgnoreCase("true"))
			{
				return true;
			}
		
			return false;
		}
		
		
	
		
		
		
		public boolean is_security_counter(HttpSession session)
		{
			Object securityErr = session.getAttribute(SecurityUtil.SECURITY_SESSION_ERROR_TAG);
			
			if(null!=securityErr && securityErr.toString().length()>0)
			{
				return true;
			}
			
			
			
			return false;
		}
		
	
		public void removeSecurityError(HttpSession session)
		{
			session.removeAttribute(SecurityUtil.SECURITY_SESSION_ERROR_TAG);
		}
	

		//{20}
		private static final String  generalHeaderPattern = "^[0-9a-zA-Z\\.\\-_ /]{0,1000}$";
//		private static final String  generalHeaderPattern = "^[0-9a-zA-Z\\.\\-_ /]{0,256}$";
//		private static final String  generalInputPattern = "^[0-9a-zA-Z\\.\\-_,: /]{0,256}$";
		//OCBC2BUIXU-681
		//P3OCBCUAT-357 allow & char in input
		//P3OCBCUAT-375 allow > char in input
		private static final String  generalInputPattern = "^[0-9a-zA-Z()>&|\\.\\-_,:`\'\" /\\[\\]]{0,1000}$";
		
		
//		private static final String  descInputPattern = "^[0-9a-zA-Z\\.\\-_'&\\(\\)\\#\\*_/\\s\\+\\/\\, ]*$";




		public static final String  descInputPattern = "^[0-9a-zA-Z@#&~`!$%^+={}|:;?<>,\\[\\]_\\-'\"\\./()*\\s/]{0,256}$";
		public static final String  descInputPatternNoLimit = "^[0-9a-zA-Z@#&~`!$%^+={}|:;?<>,\\[\\]_\\-'\"\\./()*\\s/]*$";
		public static final String  addressPattern =   "^[0-9a-zA-Z@#&~`!\\\\$%^+={}|:;?<>,\\[\\]_\\-'\"\\./()*\\s/]{0,256}$";
		private static final String  descInputNoVNPattern = "^[0-9a-zA-Z,.@#&()*'_\\-\\s /]*$";
		private static final String  descInputNoVNReadable= "0-9a-zA-Z,.@#&()*'_- /{tab}{linebreak}";
		private static final String  contactNumberPattern = "601\\d{8,9}";
		private static final String  idNumberPattern = "^[0-9a-zA-ZAa.@#&()*'_\\-\\s /]*$";

		public static final String  paymentDescInputPattern = "^[0-9a-zA-ZAa.@#&()*'_\\-\\s /]*$";	
//		public static final String  paymentDescInputPattern ="^[0-9a-zA-Z.@#&()*'~`!$%^+={}|:;?<>,\\\"_\\[\\]\\-\\s\\\\ /]*$";
		private static final String  loginInputPattern = "^[0-9a-zA-Z_]{0,256}$";
		private static final String  loginSessionInputPattern = "^[0-9a-zA-Z_/+=]{0,256}$";
		private static final String  dynamicPattern = "^[0-9a-zA-Z.@#&()*'~`!$%^+={}|:;?<>,\\\"_\\[\\]\\-\\s\\\\ /]*$";
		private static final String  dynamicReadable= "0-9a-zA-Z.@#&()*'~`!$%^+={}|:;?<>,\\\"_[]- /{tab}{linebreak}";
		//~!@$%^_
		private static final String passwordInputPattern = "^[0-9a-zA-Z~!@$%^_]{0,256}$";
		private static final String omniDatePattern = "^[0-9a-zA-Z\\.\\-_,: /]{0,256}$";
		private static final String omniDateReadable= "0-9a-zA-Z.-_,: /";
		private static final String omniEmailPattern = "^[0-9a-zA-Z\\.\\@._\\- /,]{0,1000}$";
		private static final String omniEmailReadable = "0-9a-zA-Z.@._- /,";
		private static final String omniAliasPattern = "^[0-9a-zA-Z ,.:;+()?\\-]{0,50}$";
		private static final String omniAliasReadable= "0-9a-zA-Z ,.:;+()?-";
		private static final String omniDescPattern = "^[0-9a-zA-Z ,.:;+()?\\//-]{0,100}$";
		private static final String omniDescReadable = "0-9a-zA-Z ,.:;+()?-";
		private static final String omniDescNewlinePattern = "^[0-9a-zA-Z ,.:;+()?\\-\\r\\n]{0,256}$";
		private static final String omniDescNewlineReadable = "0-9a-zA-Z ,.:;+()?-{linebreak}";
		private static final String omniAddressPattern = "^[0-9a-zA-Z ,.:;+()?\\-/]{0,50}$";
		private static final String omniAddressReadable= "0-9a-zA-Z ,.:;+()?-/";
		private static final String omniErroMsgPattern = "^[0-9a-zA-Z ,.:;+()?\\-\\[\\]]{0,256}$";
		private static final String mposDateInputPatter = "^[0-9a-zA-ZAa:\\-\\s /]*$";
		private static final String mposDateInputReadable = "0-9a-zA-ZAa:- /{tab}{linebreak}";
		private static final String uuidInputPattern = "^[0-9a-zA-Z\\.\\-_,:`\'\" /]{0,4096}$";
		private static final String businessIndustryInputPattern = "^[0-9a-zA-Z|\\.\\-_,&:`\'\" /]{0,256}$";
		private static final String bloodInputPattern = "^[0-9a-zA-Z|\\.\\-+_,:`\'\" /]{0,256}$";
		private static final String deviceModelPattern = "^[0-9a-zA-Z\\.\\-_,():`\'\" /]{0,256}$";
		private static final String additionalWorkInfoPattern = "^[0-9a-zA-Z()=>&|<\\.\\-_,:`\'\" /]{0,256}$";
		private static final String pnsTokenPattern = "^[0-9a-zA-Z\\:_\\-/]{0,256}$";
		private static final String omniAddInfoPattern = "^[0-9a-zA-Z ,.:;+()?\\//-]{0,1000}$";
		
		private static final String  recordIds = "^[0-9a-zA-Z()>&|\\.\\-_,:`\'\" /\\[\\]]*$";
		private static final String  alphanumericPatterns = "^[0-9a-zA-Z]*$";
		
		
		public boolean isErrorMsgParam(String data){
			
			return data.matches(omniErroMsgPattern);
		}
		
		public boolean isMposDateParam(String data){
			
			return data.matches(mposDateInputPatter);
		}
		
		public static String getMposDateParamReadable(){
			
			return mposDateInputReadable;
		}
		
		public boolean isValidParam(String data)
		{
			
			//log.debug("VALIDATE DATA : "+data);
			return data.matches(generalInputPattern);
			
		}
		
		public boolean isValidRecordId(String data)
		{
			
			return data.matches(recordIds);
			
		}
		
		
		public boolean isLoginParam(String data)
		{
			
			//log.debug("VALIDATE DATA : "+data);
			return data.matches(loginInputPattern);
			
		}
		
		public boolean isLoginClientSession(String data)
		{
			
			log.info("VALIDATE DATA : "+data);
			return data.matches(loginSessionInputPattern);
			
		}
		
		
		public boolean isPasswordParam(String data)
		{
			
			//log.debug("VALIDATE DATA : "+data);
			return data.matches(passwordInputPattern);
			
		}
		
		public boolean isValidDescParam(String data)
		{
//			log.info("descInputPattern : " + data);
			return data.matches(descInputPattern);
		}

		public boolean isValidDescParamNoLimit(String data)
		{
//			log.info("descInputPattern : " + data);
			return data.matches(descInputPatternNoLimit);
		}
		
		public boolean isValidDescNoVNParam(String data)
		{
			return data.matches(descInputNoVNPattern);
		}
		
		public static String getValidDescNoVNParamReadable(){
			return descInputNoVNReadable;
		}
		
		public boolean isValidAddressParam(String data)
		{
			return data.matches(addressPattern);
		}

		public boolean isValidHeaderParam(String data)
		{
			
			//log.info("VALIDATE DATA Header : "+data);
			return data.matches(generalHeaderPattern);
			
		}
	
		
		public boolean isValidContactNumber(String data)
		{
			
			//log.debug("VALIDATE DATA : "+data);
			return data.matches(contactNumberPattern);
			
		}
		
		public boolean isIdNumber(String data)
		{
			
			//log.debug("VALIDATE DATA : "+data);
			return data.matches(idNumberPattern);
			
		}
		
		public boolean isSpaceOnly(String data)
		{
			String cleanData = data.trim(); 			
			if(cleanData.length()<=0){
				return true; 
			}
			
			return false;
		}
		
		public boolean isValidDynamicParam(String data)
		{
			return data.matches(dynamicPattern);
		}
		
		public static String getValidDynamicParamReadable()
		{
			return dynamicReadable;
		}
		
		public boolean isValidEmailParam(String data)
		{
			return data.matches(omniEmailPattern);
		}
		
		public static String getValidEmailParamReadable()
		{
			return omniEmailReadable;
		}
		
		public boolean isValidDateParam(String data)
		{
			return data.matches(omniDatePattern);
		}
		
		public static String getValidDateParamReadable()
		{
			return omniDateReadable;
		}
		
		public boolean isValidAliasCheck(String data){
			return data.matches(omniAliasPattern);
		}
		
		public static String getValidAliasReadable(){
			return omniAliasReadable;
		}
		
		public boolean isValidDescCheck(String data){
			return data.matches(omniDescPattern);
		}
		
		public static String getValidDescReadable(){
			return omniDescPattern;
		}
		
		public boolean isValidAddInfoCheck(String data){
			return data.matches(omniAddInfoPattern);
		}
		
		public static String getValidAddInfoReadable(){
			return omniAddInfoPattern;
		}
		
		public boolean isValidDescNewlineCheck(String data){
			return data.matches(omniDescNewlinePattern);
		}
		
		public static String getValidDescNewlineReadable(){
			return omniDescNewlineReadable;
		}
		
		public boolean isValidUUIDCheck(String data){
			return data.matches(uuidInputPattern);
		}
		
		public boolean isValidBusinessIndustryCheck(String data){
			return data.matches(businessIndustryInputPattern);
		}
		
		public boolean isValidBlood(String data){
			return data.matches(bloodInputPattern);
		}
		
		public boolean isValidAddress(String data){
			return data.matches(omniAddressPattern);
		}
		
		public static String getValidAddressReadable(){
			return omniAddressReadable;
		}
		
		public boolean isValidDeviceModelCheck(String data){
			return data.matches(deviceModelPattern);
		}
		
		public boolean isValidPNSTokenCheck(String data){
			return data.matches(pnsTokenPattern);
		}
		
		public boolean isAdditionalWorkInfoParam(String data){
			return data.matches(additionalWorkInfoPattern);
		}
		
		public boolean isValidAlphanumeric(String data)
		{
			return data.matches(alphanumericPatterns);
		}
		
		public byte[] RSAEncryption(String modulusString, String Exponent, String targetString) throws Exception
		{
			
			BigInteger modulus = new BigInteger(modulusString, 16);
			BigInteger pubExp = new BigInteger(Exponent, 16);

			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(modulus, pubExp);
			RSAPublicKey key = (RSAPublicKey) keyFactory.generatePublic(pubKeySpec);

			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key);

			byte[] cipherData = cipher.doFinal(targetString.getBytes());
			
			return cipherData;
		}
		
		
		
		public static void main(String argss[]) throws Exception
		{
			//SecurityUtil su = new SecurityUtil();
			/*String modulus = "8ef38189d8ef3dd160c8910e07cf5152d0d3248d6f707c7bced974447b1d8e70886d4c6777555f650fbace4739d07ed808056b45a87b3c83b7bba5047039f8c777559e135e10973003e254ed8a1524201eaebfbf2ee8ca94953b6f9bfcbed69f601c39a4006a98261cf994465fe9ee823aed0d20fb245b12d13e7720a2cd18eb";

			String exponent = "10001";
			
			//String modulus = "a5261939975948bb7a58dffe5ff54e65f0498f9175f5a09288810b8975871e99af3b5dd94057b0fc07535f5f97444504fa35169d461d0d30cf0192e307727c065168c788771c561a9400fb49175e9e6aa4e23fe11af69e9412dd23b0cb6684c4c2429bce139e848ab26d0829073351f4acd36074eafd036a5eb83359d2a698d3";
			//String exponent = "10001";
			byte[] encrypted = su.RSAEncryption(modulus, exponent, "Welcome1");
			//welcom1 [uat] mobility01, 02, 06
			//Helo8080 [sit] mycust320, 307,308
			StringBuffer result = new StringBuffer();
			for (byte b : encrypted) {
			    result.append(String.format("%02X ", b));
			    //result.append(""); // delimiter
			}
			System.out.println(modulus.length()+"---"+result);
			System.out.println(result.toString().replaceAll(" ", ""));*/
			
		/*	SecurityUtil secUtil = new SecurityUtil();
			log.debug("asdasd-asdas".matches(generalHeaderPattern));*/
//			log.debug(su.isIdNumber("0123123a-123123"));

//			log.debug("pay+   ::   "+su.isValidParam("asd-asd./asd-aa.s/dasd.asd")); 
			String  generalInputPattern = "^[0-9a-zA-Z\\\\.\\\\-_,:`\\'\\\" /]{0,4096}$"; 
			String xx = "^[0-9a-zA-Z\\:_\\-/]{0,256}$";
			System.out.println("dsr4UblIQzI:APA91bHnyMZ0DRQOCcVdelNyw5iapAXjlQhUyIcLoDa-jwVKfsgSZKbOerQB_z6JdHdCxZnrcvNxoEoeqwsCJ-jFAIKjHhTb-jl52koWo_fLxGC_SFLq0loVj8YTscTj-Qr1hl-xsHD2".matches(xx));
			
//			log.debug("asdasdad {0}".replaceAll("\\{0\\}", "123"));
			
		}
		
		
	    public void PrintBytes(byte[] bytes)
        {
            int cBytes = bytes.length;
            int iByte = 0;

            for (;;) {
                for (int i = 0; i < 8; i++) {
                    String hex = Integer.toHexString(bytes[iByte++] & 0xff);
                    if (hex.length() == 1) {
                        hex = "0" + hex;
                    }

                    //System.out.print(hex);
                    if (iByte >= cBytes) {
                        log.debug("\n");
                        return;
                    }
                }
                log.debug("\n");
            }
        }
	    
	    public boolean isValidPaymentDescParam(String data)
		{
			return data.matches(paymentDescInputPattern);
		}
	    
}