package com.silverlake.mleb.mcb.module.common;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.google.gson.Gson;
import com.silverlake.micb.core.bean.HeaderBean;
import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.mcb.bean.ListMapPojo;
import com.silverlake.mleb.mcb.bean.MapPojo;
import com.silverlake.mleb.mcb.bean.ObASDebitCardRequest;
import com.silverlake.mleb.mcb.bean.ObASFixedDepositRequest;
import com.silverlake.mleb.mcb.bean.ObASLoanRequest;
import com.silverlake.mleb.mcb.bean.ObASSavingRequest;
import com.silverlake.mleb.mcb.bean.ObAuthenticationRequest;
import com.silverlake.mleb.mcb.bean.ObAuthenticationResponse;
import com.silverlake.mleb.mcb.bean.ObCrCrdDetailSummaryRequestBean;
import com.silverlake.mleb.mcb.bean.ObCrCrdPayDetailResponseBean;
import com.silverlake.mleb.mcb.bean.ObDynamicFieldBean;
import com.silverlake.mleb.mcb.bean.ObHeaderResponse;
import com.silverlake.mleb.mcb.bean.ObIGBPayDetailResponseBean;
import com.silverlake.mleb.mcb.bean.ObRequest;
import com.silverlake.mleb.mcb.bean.ObResponse;
import com.silverlake.mleb.mcb.bean.ObThirdPartyTransPayDetailResponseBean;
import com.silverlake.mleb.mcb.bean.TakaGoalSettingBean;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.entity.CustomerEvent;
import com.silverlake.mleb.mcb.listerner.ExtApiManager;
import com.silverlake.mleb.mcb.listerner.LogDBManager;
import com.silverlake.mleb.mcb.listerner.NightModeManager;
import com.silverlake.mleb.mcb.listerner.ServerMaintenanceManager;
import com.silverlake.mleb.mcb.listerner.UntagDeviceSessionRemover;
import com.silverlake.mleb.mcb.listerner.VersionManager;
import com.silverlake.mleb.mcb.module.func.RetrieveAppStatInfo_mcb;
import com.silverlake.mleb.mcb.util.GsonUtil;
import com.silverlake.mleb.mcb.util.PropertiesManager;


public abstract class FuncServices 
{
	private static Logger log = LogManager.getLogger(FuncServices.class);
	
	private static String[] logMaskFields = {"sessionCache"};
	private static Gson gsonLog = GsonUtil.getPrettyPrintingGson(logMaskFields); 
	//private MessageManager msgPro = new MessageManager();
	private PropertiesManager pro = new PropertiesManager();
	public FuncServices()
	{
		
	}
	
	
	@Autowired
	private VersionManager versionManager;
	
	@Autowired
	private ServerMaintenanceManager serverMaintenanceManager;
	
	@Autowired
	UntagDeviceSessionRemover untagDeviceCheck;
	
	@Autowired
	NightModeManager nightModeManager;
	
	@Autowired
	LogDBManager logDBManager;
	
	@Autowired 
	ExtApiManager extApiMgr;
	
	public MICBResponseBean acceptProcess(MICBRequestBean arg0)
	{
		MICBResponseBean micbResponseBean = new MICBResponseBean();
		ObRequest obRequestWS = new ObRequest();
		Date requestDate = new Date();
		try
		{
		
			
			log.info(this.getClass().getSimpleName()+" :- REQUEST ["+arg0.getTranxID()+"] \n "+filterGson(gsonLog.toJson(arg0.getBDObject())));
	        
			
		
			obRequestWS = (ObRequest) convertJsonObject(arg0.getBDObject(),ObRequest.class);
			Object resultCheck = null;
			//Before process the ws, check the app version required for force update or not
			if (this.getClass().getSimpleName().equals("PerformDeviceUnBindg")) {
				
			} else {
				resultCheck = versionManager.onCall(arg0);
				if (resultCheck instanceof MICBResponseBean) {
					micbResponseBean = (MICBResponseBean) resultCheck;
				} else {
					// Before process the ws, check the server under maintenance
					// or not
					resultCheck = serverMaintenanceManager.onCall(arg0);
					if (resultCheck instanceof MICBResponseBean) {
						micbResponseBean = (MICBResponseBean) resultCheck;
					}
				}
			}
			
			if(!(resultCheck instanceof MICBResponseBean))
			{
				ObResponse mibResponse = null;
				
				
				//arg0.setBDObject(obRequestWS);
				//obRequestWS = (ObRequest) arg0.getBDObject();
				String  rspData;
				Object  extApiCheck = extApiMgr.onCall(arg0);
				if(this.getClass().getSimpleName().equals("PerformDeviceUnBindg")){
				rspData = null;	
				}else {
				rspData = untagDeviceCheck.onCall(arg0,obRequestWS, new String[]{this.getClass().getSimpleName()});
				}
				if(extApiCheck instanceof MICBResponseBean)
				{					
					micbResponseBean = (MICBResponseBean) extApiCheck;
				}
				else if(null!=rspData && rspData.toString().equalsIgnoreCase(MiBConstant.MIB_STATUSCODE_SUCCESS_UNTAGGED_CURRENT_DEVICE) )
				{					
					mibResponse = new ObResponse();
					mibResponse.setObHeader(new ObHeaderResponse());
					mibResponse.getObHeader().setStatusCode(MiBConstant.MIB_STATUSCODE_SUCCESS_UNTAGGED_CURRENT_DEVICE);
					micbResponseBean.setBDObject(mibResponse);
				}
				else
				{
									
							//MuleMessage nightModeRsp = client.send("vm://nightModeCheck", arg0,null);
							String actualRequestInfo = null==arg0.getHeaderBean().getRequestInfo()?"":arg0.getHeaderBean().getRequestInfo();
						
							Object nightModeRsp = nightModeManager.onCall(arg0);				
							Object micbObj = nightModeRsp;
							if(micbObj instanceof MICBResponseBean)
							{
																
								micbResponseBean = (MICBResponseBean) micbObj;
							}
							else
							{								
								micbResponseBean = process(arg0);
							}
					        
					      
					       
							
							
							if(null!=micbResponseBean.getBDObject() && micbResponseBean.getBDObject() instanceof ObResponse)
							{
								 
								mibResponse = (ObResponse) micbResponseBean.getBDObject();
								//mibResponse.getObHeader()..setChannelDateTime(MiBConstant.MiB_TimeFormat.format(IB_Request_date)+";"+MiBConstant.MiB_TimeFormat.format(IB_Response_date));
								//mibResponse.getObHeader().setClientRequestURL(obHeaderRequest.getClientRequestURL());
								HeaderBean micbHeader = new HeaderBean();
								//micbHeader.setRequestInfo("[st:"+miB_TimeFormat.format(IB_Request_date)+"];[ws:"+obHeaderRequest.getClientRequestURL()+"];[et:"+miB_TimeFormat.format(IB_Response_date)+"]");
								micbResponseBean.setHeaderBean(micbHeader);
								micbResponseBean.setBDObject(mibResponse);
							
								micbResponseBean.setBdResponseCode(mibResponse.getObHeader().getStatusCode());
								micbResponseBean.setBdResponseMessage(mibResponse.getObHeader().getStatusMessage());
								
						
								actualRequestInfo = actualRequestInfo.trim().length()>0?" "+actualRequestInfo+"":"";
								
						       
						        
						    	/*MuleMessage muleMsg = futureMsg.getMessage();
								Object rspData = muleMsg.getPayload();
								if(null!=rspData && rspData.toString().equalsIgnoreCase(MiBConstant.MIB_STATUSCODE_SUCCESS_UNTAGGED_CURRENT_DEVICE) && !mibResponse.getObHeader().getStatusCode().equalsIgnoreCase(MiBConstant.MIB_STATUSCODE_SUCCESS_UNTAGGED_CURRENT_DEVICE))
								{
									mibResponse = new ObResponse();
									mibResponse.setObHeader(new ObHeaderResponse());
									mibResponse.getObHeader().setStatusCode(MiBConstant.MIB_STATUSCODE_SUCCESS_UNTAGGED_CURRENT_DEVICE);
									micbResponseBean.setBDObject(mibResponse);
								}*/
							
							}
							
							//String methodName = new Exception().fillInStackTrace().getStackTrace()[1].getMethodName();
						    /*	String cifNo = null==obRequest.getObUser()?null:obRequest.getObUser().getCifNumber();
							if(micbResponseBean.getBDObject() instanceof ObAuthenticationResponse && cifNo==null)
							{
								
								ObAuthenticationResponse obAuthentication = (ObAuthenticationResponse) micbResponseBean.getBDObject() ;
								cifNo = null==obAuthentication.getObUser()?null:obAuthentication.getObUser().getCifNumber();
							}
							
							String deviceID =  null==arg0.getDeviceBean()?null:arg0.getDeviceBean().getDeviceId();*/
							//insertCustomerEvent(obRequest,mibResponse,arg0,this.getClass().getSimpleName(),arg0.getHeaderBean().getRequestInfo(),requestDate,new Date());
							     
				}
			}
		}
		catch(Exception e)
		{
			log.error(e);
			ObResponse obResponseWS = new ObResponse();
			Date IB_Response_date = new Date(); 
			HeaderBean micbHeader = new HeaderBean();
			//micbHeader.setRequestInfo("[st:"+miB_TimeFormat.format(IB_Request_date)+"];[ws:"+obHeaderRequest.getClientRequestURL()+"];[et:"+miB_TimeFormat.format(IB_Response_date)+"]");
			micbResponseBean.setHeaderBean(micbHeader);
			ObHeaderResponse obheaderResponse = new ObHeaderResponse();
			obheaderResponse.setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			obResponseWS.setObHeader(obheaderResponse);
			micbResponseBean.setBDObject(obResponseWS);
		
		}
		
		
		ObResponse obResponseWS = (ObResponse) micbResponseBean.getBDObject();
		 log.info(this.getClass().getSimpleName()+" :- RESPONSE ["+arg0.getTranxID()+"] \n "+filterGson(gsonLog.toJson(obResponseWS)));

		insertCustomerEvent(obRequestWS,obResponseWS,arg0,arg0.getHeaderBean().getServiceID(),obRequestWS.getObHeader().getVersion(),requestDate,new Date());
		
		
		
		return micbResponseBean;
		
	}
	
	
	
	
	
	
	
	public abstract MICBResponseBean process(MICBRequestBean arg0);
	
	
	//public abstract MuleContext getMuleContext();
	

	public void insertCustomerEvent(ObRequest obRequest, ObResponse obResponse, MICBRequestBean micbRequestBean,String service, String appVersion,Date requestDate, Date responseDate) 
	{
		
		
		String cifNo = null==obRequest.getObUser()?null:obRequest.getObUser().getCifNumber();
		String orgId = null==obRequest.getObUser()?null:obRequest.getObUser().getOrgId();
		if(obResponse instanceof ObAuthenticationResponse && cifNo==null)
		{
			
			ObAuthenticationResponse obAuthentication = (ObAuthenticationResponse) obResponse ;
			cifNo = null==obAuthentication.getObUser()?null:obAuthentication.getObUser().getCifNumber();
		}
		
		//String deviceID =  null==micbRequestBean.getDeviceBean()?null:micbRequestBean.getDeviceBean().getDeviceId();
		String deviceID =  null==micbRequestBean.getDeviceBean()?null:micbRequestBean.getDeviceBean().getDeviceId();
		String deviceRooted = null==micbRequestBean.getDeviceBean()?null: String.valueOf(micbRequestBean.getDeviceBean().isRooted());
		
		if(null!=cifNo && cifNo.length()>0)
		{
			
			CustomerEvent custmorEvent = new CustomerEvent();
			
			
			if(obRequest instanceof ObASSavingRequest  )
			{
				ObASSavingRequest obRequestAS = (ObASSavingRequest) obRequest;
				custmorEvent.setAccountNumber(obRequestAS.getObASSavingBean().getAccountNumber());
				custmorEvent.setProduct(obRequestAS.getObASSavingBean().getProductTypeCode());
			}
			else if(obRequest instanceof ObASDebitCardRequest )
			{
				ObASDebitCardRequest obRequestAS = (ObASDebitCardRequest) obRequest;
				custmorEvent.setAccountNumber(obRequestAS.getObASDebitCardBean().getAccountNumber());
				custmorEvent.setProduct(obRequestAS.getObASDebitCardBean().getProductTypeCode());
			
			}
			else if(obRequest instanceof ObASLoanRequest )
			{
				ObASLoanRequest obRequestAS = (ObASLoanRequest) obRequest;
				custmorEvent.setAccountNumber(obRequestAS.getObASLoanBean().getAccountNumber());
				custmorEvent.setProduct(obRequestAS.getObASLoanBean().getProductTypeCode());
			
			}
			
			else if(obRequest instanceof ObASFixedDepositRequest )
			{
				ObASFixedDepositRequest obRequestAS = (ObASFixedDepositRequest) obRequest;
				custmorEvent.setAccountNumber(obRequestAS.getObASFixedDepositBean().getAccountNumber());
				custmorEvent.setProduct(obRequestAS.getObASFixedDepositBean().getProductTypeCode());

			}
			else if(obRequest instanceof ObCrCrdDetailSummaryRequestBean )
			{
				ObCrCrdDetailSummaryRequestBean obRequestAS = (ObCrCrdDetailSummaryRequestBean) obRequest;
				custmorEvent.setAccountNumber(obRequestAS.getObCreditCard().getAccountNumber());
				custmorEvent.setProduct(obRequestAS.getObCreditCard().getProductTypeCode());

			}
			else if(obResponse instanceof ObCrCrdPayDetailResponseBean )
			{
				ObCrCrdPayDetailResponseBean obRequestPAY = (ObCrCrdPayDetailResponseBean) obResponse;
				//obRequestPAY.getObCrCrdResponseResult().getFailureReason();
				//obRequestPAY.getObCrCrdResponseResult().getTranstatus();
				String errorCode = null==obRequestPAY.getObCrCrdResponseResult()?"":" - "+obRequestPAY.getObCrCrdResponseResult().getRejectMessage();
				String errorStatus = null== obRequestPAY.getObCrCrdResponseResult()? "" :obRequestPAY.getObCrCrdResponseResult().getTranstatus();
				if(null!=errorStatus){custmorEvent.setErrorMessage(errorStatus+errorCode);}
				custmorEvent.setReferenceNo(null==obRequestPAY.getObCrCrdResponseResult()?null:obRequestPAY.getObCrCrdResponseResult().getRefNo());

			}
			else if(obResponse instanceof ObIGBPayDetailResponseBean )
			{
				ObIGBPayDetailResponseBean obRequestPAY = (ObIGBPayDetailResponseBean) obResponse;
				//obRequestPAY.getObAccResult().getFailureReason();
				//obRequestPAY.getObAccResult().getTranstatus()
				String errorCode = null==obRequestPAY.getObAccResult()?"":" - "+obRequestPAY.getObAccResult().getRejectMessage();
				String errorStatus = null== obRequestPAY.getObAccResult()? "" :obRequestPAY.getObAccResult().getTranstatus();
				if(null!=errorStatus){custmorEvent.setErrorMessage(errorStatus+errorCode);}
				custmorEvent.setReferenceNo(null==obRequestPAY.getObFavAccResult()?null:obRequestPAY.getObFavAccResult().getRefNo());

			}
			else if(obResponse instanceof ObThirdPartyTransPayDetailResponseBean )
			{
				ObThirdPartyTransPayDetailResponseBean obRequestPAY = (ObThirdPartyTransPayDetailResponseBean) obResponse;
				
				//obRequestPAY.getObFavAccResult().getFailureReason()
				//obRequestPAY.getObFavAccResult().getTranstatus();
				String errorCode = null==obRequestPAY.getObFavAccResult()?"":" - "+obRequestPAY.getObFavAccResult().getRejectMessage();
				String errorStatus = null== obRequestPAY.getObFavAccResult() ? "" :obRequestPAY.getObFavAccResult().getTranstatus();
				if(null!=errorStatus){custmorEvent.setErrorMessage(errorStatus+errorCode);}
				custmorEvent.setReferenceNo(null==obRequestPAY.getObFavAccResult()?null:obRequestPAY.getObFavAccResult().getRefNo());

			}
			//if login using biometric, customer event service should be finger print login
			//any quick login will be store in description during login
			else if(obRequest instanceof ObAuthenticationRequest)
			{
				ObAuthenticationRequest authenRequest = (ObAuthenticationRequest) obRequest;
				if(service.equalsIgnoreCase(MiBConstant.MIB_PERFORMLOGIN) 
						&& authenRequest != null && authenRequest.getLoginType()!=null)
				{
					custmorEvent.setDescription(authenRequest.getLoginType());
				}
			}
			
			
			
			if(!obResponse.getObHeader().getStatusCode().equalsIgnoreCase(MiBConstant.MIB_COMMON_SUCCESS))
			{
				String error_msg = null;
				if(RetrieveAppStatInfo_mcb.msgtable!=null)
				{
				 
					error_msg = (String) RetrieveAppStatInfo_mcb.msgtable.get(obResponse.getObHeader().getStatusCode());
					 
				}
				
				
				
				error_msg = null==error_msg?"":error_msg;
				
				
				if(null!=obResponse.getParamValue())
				{
				
					for(int i=0;i<obResponse.getParamValue().length;i++)
					{
						if(null!= obResponse.getParamValue()[i])
						{
							error_msg = error_msg.replaceAll("{"+i+"}", obResponse.getParamValue()[i]);
						}
					}

				}
				
				
				
				error_msg = error_msg.length()>200?error_msg.substring(0, 200)+"...":error_msg;
				custmorEvent.setErrorMessage(error_msg);
			}
			
			
			
			String serviceMapName = pro.getProperty(MiBConstant.MIB_EVENT_PREFIX+service);
			if(null==serviceMapName)
			{
				custmorEvent.setService(service);
			}
			else
			{
				custmorEvent.setService(serviceMapName);
			}
			
			
			
			
			
			custmorEvent.setCif(cifNo);
			custmorEvent.setOrgId(orgId);
			custmorEvent.setAppVersion(appVersion);
			custmorEvent.setRequestDatetime(requestDate);
			custmorEvent.setResponseDatetime(responseDate);
			custmorEvent.setMlebTranxId(micbRequestBean.getTranxID());
			custmorEvent.setStatusCode(obResponse.getObHeader().getStatusCode());
			custmorEvent.setDeviceId(deviceID);
			custmorEvent.setDeviceRooted(deviceRooted);
			custmorEvent.setChannelCode(obRequest.getObHeader().getChannelId());
			long processTime = responseDate.getTime()-requestDate.getTime();
			custmorEvent.setProcessTime(appendZero(String.valueOf(processTime), 7));
			
			logDBManager.onCall(custmorEvent);
			/*MuleClient client = new MuleClient(getMuleContext());
			client.sendNoReceive("vm://ibwsTranxLogging", custmorEvent, null);*/
		}
		
	}
	
	

	public String appendZero(String value, int length)
	{
		while (value.length() < length)
		{
			value = "0" + value;
		}
		return value;
	}
	
	
	public String filterGson(String data)
	{
		String[] masks = {"pin","password","mPassword","pString","cString","pStringOld","cStringOld",
				"loginImage","promotionImag", "confirmPassword",
				"tncContent","descriptions","imageID","imageData","imageData2","promotionImag","idType","sessionId", "benePicture", "uploadBenePicture", "captchaImageData", "image"
				, "productImage", "imageStr", "goalImg","msgList","clientSessionId","profileImg", "fileData","passwordDataBlock","randomNumber","xString"};
		
		
		for(String mask: masks)
		{
			char b = '"';
			char bx = ']';
			int checkExist = data.indexOf(Character.toString(b)+mask+Character.toString(b)+":");
			int lengthTag = (Character.toString(b)+mask+Character.toString(b)+":").length();
			if(checkExist>0)
			{
				//System.out.println("Index Found "+checkExist);
				String checkCut =  data.substring(checkExist+lengthTag, data.length());
				
				
				
				if(mask.equalsIgnoreCase("mPassword") ||
				   mask.equalsIgnoreCase("loginImage"))
				{
					int indexCut = checkCut.indexOf(Character.toString(bx)+",\n");
					
					data = data.substring(0, checkExist+lengthTag+2)+"**********"+Character.toString(bx)+ data.substring(checkExist+lengthTag+2+indexCut);
					
				}
				else if(mask.equalsIgnoreCase("idType") || mask.equalsIgnoreCase("msgList"))
				{
					int indexCut = checkCut.indexOf(Character.toString(bx)+",\n");
					
					data = data.substring(0, checkExist+lengthTag+2)+"**********"+ data.substring(checkExist+lengthTag+indexCut);
					
				}
				else if(mask.equalsIgnoreCase("promotionImag"))
				{
					int indexCut = checkCut.indexOf(Character.toString(bx)+",\n");
					data = data.substring(0, checkExist+lengthTag+2)+"**********"+filterSpecificString(Character.toString(bx)+ data.substring(checkExist+lengthTag+2+indexCut),mask);
					
				}
				else if(mask.equalsIgnoreCase("imageData") ||
						mask.equalsIgnoreCase("imageStr") ||
						mask.equalsIgnoreCase("productImage") ||
						mask.equalsIgnoreCase("fileData") ||
						mask.equalsIgnoreCase("goalImg") 
					 
						)
				{
					String[] splitImageData = data.split("\""+mask+"\":");
					String markedStr = "";
					
					if(splitImageData.length > 0){
						markedStr = splitImageData[0];
						
						for(int i = 1; i < splitImageData.length; i++){
							splitImageData[i] = "\""+mask+"\": \""+"**********"+splitImageData[i].substring(splitImageData[i].indexOf("\"", 2), splitImageData[i].length());
							markedStr+= splitImageData[i];
						}
					}
					
					data = markedStr;	
				}
				else if(mask.equalsIgnoreCase("clientSessionId"))
				{
					int indexCut = checkCut.indexOf(Character.toString(b)+",\n");
					if(indexCut<0)
					{
						indexCut = checkCut.indexOf(Character.toString(b)+"\n");
						indexCut = indexCut-1;
					}
					 
					String clientSSID = "**********";
					
					if(indexCut>2)
					{
						String tmpSSID  = data.substring(checkExist+lengthTag+2, checkExist+lengthTag+indexCut);
						if(tmpSSID.length()>24)
						{
							String tmpSSID1 = tmpSSID.substring(0,8);
							String tmpSSID2 = tmpSSID.substring(tmpSSID.length()-8,tmpSSID.length());
							clientSSID = tmpSSID1+"********"+tmpSSID2;
						}
						
					}
			 
					
					data = data.substring(0, checkExist+lengthTag+2)+clientSSID+Character.toString(b)+ data.substring(checkExist+lengthTag+2+indexCut);
				}
				else
				{
					
					int indexCut = checkCut.indexOf(Character.toString(b)+",\n");
					if(indexCut<0)
					{
						indexCut = checkCut.indexOf(Character.toString(b)+"\n");
						indexCut = indexCut-1;
					}
					 
					
					data = data.substring(0, checkExist+lengthTag+2)+"**********"+Character.toString(b)+ data.substring(checkExist+lengthTag+2+indexCut);
				
				}
			}
			
			
			
		}
		
		
		
		
		return data;
	}
	
	
	

	public String filterSpecificString(String data, String specString)
	{

		
		char b = '"';
		char bx = ']';
		int checkExist = data.indexOf(Character.toString(b)+specString+Character.toString(b)+":");
		int lengthTag = (Character.toString(b)+specString+Character.toString(b)+":").length();
		if(checkExist>0)
		{
			String checkCut =  data.substring(checkExist+lengthTag, data.length());
		
			int indexCut = checkCut.indexOf(Character.toString(bx)+",\n");
			data = data.substring(0, checkExist+lengthTag+2)+"**********"+filterSpecificString(Character.toString(bx)+ data.substring(checkExist+lengthTag+2+indexCut),specString);
			
	
		}
		
		return data;
	}
	
	public byte[] handleBase64Image(byte[] image)
	{
 		byte[] imagex = image;
 		InputStream is = null;
		try {
			
			
			is = new BufferedInputStream(new ByteArrayInputStream(image));
			String mimeType = URLConnection.guessContentTypeFromStream(is);
			log.info("mimeType : "+mimeType);
			
			if(null==mimeType)
			{
				imagex = Base64.decodeBase64(new String(image));
			}			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.info("Exception Image handle for IOS ",e);
		}
		finally {
			if(is!=null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return imagex;
	}
	
	private void mapFieldToDisplay(ObDynamicFieldBean targetBean, String key, String value){
		MapPojo mapPojo = new MapPojo();
		mapPojo.setKey(key);
		mapPojo.setValue(value);
		targetBean.getListDynamicField().getMapPojo().add(mapPojo);
	}

	private String processAmount(String value)
	{
		value = value.replaceFirst ("^0*", "");
		if(value.isEmpty())
		{
			  value = "0";
		}
		else if(value.charAt(0)=='.')
		{
			value = "0"+value;
		}
		
		if(value.indexOf(".")!=-1)
		{
			String b4Precision = value.substring(0, value.indexOf("."));
			String startFromPrecision = value.substring(value.indexOf("."), value.length());
			int nValue = Integer.parseInt(b4Precision);
			DecimalFormat myFormatter = new DecimalFormat("#,###");
			String output = myFormatter.format(nValue);
		
			value = output+startFromPrecision;
		}
		else
		{
			int nValue = Integer.parseInt(value);
			DecimalFormat myFormatter = new DecimalFormat("#,###");
			String output = myFormatter.format(nValue);
			
			value = output;
		}
		
		return value;
	}
	
	public void parseXMLDetailsDOM(Properties properties, String moduleType, String xmlDetailsString, ObDynamicFieldBean targetBean) throws SAXException, IOException, ParserConfigurationException
	{
		xmlDetailsString.replaceAll("\\\"", "\"");
		
  	  if(targetBean.getListDynamicField()==null)
		  targetBean.setListDynamicField(new ListMapPojo());
	  
	  if(targetBean.getListDynamicField().getMapPojo()==null)
		  targetBean.getListDynamicField().setMapPojo(new ArrayList<MapPojo>());
	  
		org.xml.sax.InputSource is = new org.xml.sax.InputSource();
	    is.setCharacterStream(new StringReader(xmlDetailsString));
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(is);
		
		// Process the header
		NodeList headerNodes = document.getElementsByTagName("h");
		for (int i = 0; i < headerNodes.getLength(); i++) 
		{
			Element element = (Element) headerNodes.item(i);
			NodeList childNodes = element.getChildNodes();
			for (int j = 0; j < childNodes.getLength(); j++)
			{
//				if(targetBean.getListHeader()==null)
//				{
//					targetBean.setListHeader(new ArrayList<String>());
//				}
//				targetBean.getListHeader().add(childNodes.item(j).getTextContent().replaceAll("&amp;", "&"));
				if(targetBean.getLabel()==null)
					targetBean.setLabel(childNodes.item(j).getTextContent().replaceAll("&amp;", "&"));
				else
				{
					String label = targetBean.getLabel();
					label = label + "</br>"+childNodes.item(j).getTextContent().replaceAll("&amp;", "&");
					targetBean.setLabel(label);
				}
			}
		}
		 
		// Process the dynamic fields
		NodeList nodes = document.getElementsByTagName("d");
	    for (int i = 0; i < nodes.getLength(); i++) {
	      Element element = (Element) nodes.item(i);
	      NodeList childNodes = element.getChildNodes();
	      for (int j = 0; j < childNodes.getLength(); j++)
	      {	 
	    	  boolean isAmount = false;
	    	  String type = null;
	    	  String ccy = null;
	    	  String key = null;
	    	  String value = null;
	    	  String tagName = childNodes.item(j).getNodeName();
	    	  boolean isCategories = false;
	    	  if(tagName.equalsIgnoreCase("refno") 		||
	    		 tagName.equalsIgnoreCase("trfsvc") 	||
	    		 tagName.equalsIgnoreCase("frmacct") 	||
	    		 tagName.equalsIgnoreCase("sndrnm") 	||
	    		 tagName.equalsIgnoreCase("toacct") 	||
	    		 tagName.equalsIgnoreCase("toacctnm") 	||
	    		 tagName.equalsIgnoreCase("amt") 		||
	    		 tagName.equalsIgnoreCase("totamt") 	||
	    		 tagName.equalsIgnoreCase("tobank") 	||
	    		 tagName.equalsIgnoreCase("pymtdtl") 	||
	    		 tagName.equalsIgnoreCase("billernm") 	||
	    		 tagName.equalsIgnoreCase("custbillid") ||
	    		 tagName.equalsIgnoreCase("receiptno")
	    		)
	    	  {
	    		  key = properties.getProperty("TAG_"+tagName.toUpperCase());
	    		  isCategories = true;
	    	  }
	    	  else if(tagName.equalsIgnoreCase("trfdt"))
	    	  {
	    		  key = properties.getProperty(moduleType+"_TAG_"+tagName.toUpperCase());
	    		  isCategories = true;
	    	  }
			  else if(tagName.equals("fld"))
			  {
				  key = childNodes.item(j).getAttributes().getNamedItem("lbl").getNodeValue();
				  
				  if(childNodes.item(j).getAttributes().getNamedItem("type")!=null)
					  type = childNodes.item(j).getAttributes().getNamedItem("type").getNodeValue();
				  if(childNodes.item(j).getAttributes().getNamedItem("ccy")!=null)
					  ccy = childNodes.item(j).getAttributes().getNamedItem("ccy").getNodeValue();
				  if(type!=null && type.equalsIgnoreCase("dec"))
					  isAmount = true;
			  }
	    	  
	    	  value = childNodes.item(j).getTextContent();
	    	  if(isAmount)
	    	  {
	    		  value = processAmount(value);
	    	  }
	    	  
	    	  MapPojo mapPojo = new MapPojo();
	    	  mapPojo.setKey(key);
	    	  mapPojo.setValue(value);
	    	  
	    	  if(isCategories && value == null)
	    		  mapPojo = new MapPojo();
	    	  
	    	  targetBean.getListDynamicField().getMapPojo().add(mapPojo);
	      }
	    }
	    
	    // Process the footer
	    NodeList footerNodes = document.getElementsByTagName("f");
	    for (int i = 0; i < footerNodes.getLength(); i++) 
	    {
	    	Element element = (Element) footerNodes.item(i);
	    	NodeList childNodes = element.getChildNodes();
	    	for (int j = 0; j < childNodes.getLength(); j++)
	    	{
//	    		if(targetBean.getListFooter()==null)
//	    		{
//	    			targetBean.setListFooter(new ArrayList<String>());
//	    		}
//	    		targetBean.getListFooter().add(childNodes.item(j).getTextContent().replaceAll("&amp;", "&"));
	    		if(targetBean.getFooter()==null)
					targetBean.setFooter(childNodes.item(j).getTextContent().replaceAll("&amp;", "&"));
				else
				{
					String footer = targetBean.getFooter();
					footer = footer + "</br>"+childNodes.item(j).getTextContent().replaceAll("&amp;", "&");
					targetBean.setFooter(footer);
				}
	    	}
	    }
	}
	
	private static String HEADER_TAG = "header";
	private static String FOOTER_TAG = "footer";
	private static String TILDE_SEPERATOR = "~";
	private static String PIPE_SEPERATOR = "\\|";
	
	public static void main(String [] args)
	{
		String input = "header~BUKTI TRANSFER ANTAR BANK|Nomor Referensi~2017235710068|ID Terminal~90742396|Bank Pengirim~OCBC NISP|Transfer~ONLINE|Tanggal~23/08/2017 17:54:09|Transfer~ONLINE|No. Identifikasi~6034390130192120|No Resi~000000006090|Rekening Asal~010130192120|Nama Pengirim~KITKAT KETEK KOTO|Rekening Penerima~040818080808|Nama Penerima~S`AMU'DRA \"PUSPA\"|Jumlah~25000.00|Bank Penerima~BANK CENTRAL ASIA|No. Referensi Nasabah~2017235710065|footer~SIMPAN TANDA TERIMA INI SEBAGAI BUKTI TRANSAKSI|";
		String [] listSplit1 = input.split("\\|");
		if(listSplit1!=null)
		{
			for(String elem1 : listSplit1)
			{
				if(!elem1.isEmpty())
				{
					String [] listSplit2 = elem1.split("~");
					if(listSplit2!=null)
					{
						System.out.println(listSplit2[0]+" : "+ listSplit2[1]);
					}
				}
			}
		}
	}
	public void parseHeaderContentFooterString(String xmlDetailsString, ObDynamicFieldBean targetBean) 
	{
		xmlDetailsString.replaceAll("\\\"", "\"");
		
		if(targetBean.getListDynamicField()==null)
			targetBean.setListDynamicField(new ListMapPojo());
	  
		if(targetBean.getListDynamicField().getMapPojo()==null)
			targetBean.getListDynamicField().setMapPojo(new ArrayList<MapPojo>());
	  
		String strLabel = null;
		String strFooter = null;
		String [] listSplit1 = xmlDetailsString.split(PIPE_SEPERATOR);
		if(listSplit1!=null)
		{
			for(String elem1 : listSplit1)
			{
				if(!elem1.isEmpty())
				{
					String [] listSplit2 = elem1.split(TILDE_SEPERATOR);
					if(listSplit2!=null)
					{
						if(listSplit2[0]!=null && listSplit2[0].equalsIgnoreCase(HEADER_TAG))
						{
//							if(targetBean.getListHeader()==null)
//							{
//								targetBean.setListHeader(new ArrayList<String>());
//							}
//							targetBean.getListHeader().add(listSplit2[1]);
							if(strLabel==null)
								strLabel = listSplit2[1];
							else
								strLabel = strLabel+ "</br>"+listSplit2[1];
						}
						else if(listSplit2[0]!=null && listSplit2[0].equalsIgnoreCase(FOOTER_TAG))
						{
//							if(targetBean.getListFooter()==null)
//							{
//								targetBean.setListFooter(new ArrayList<String>());
//							}
//							targetBean.getListFooter().add(listSplit2[1]);
							if(strFooter==null)
								strFooter = listSplit2[1];
							else
								strFooter = strFooter+ "</br>"+listSplit2[1];
						}
						else
						{
							MapPojo mapPojo = new MapPojo();
					    	mapPojo.setKey(listSplit2[0]);
					    	mapPojo.setValue(listSplit2[1]);
					    	  
					    	targetBean.getListDynamicField().getMapPojo().add(mapPojo);
						}
					}
				}
			}
		}
		targetBean.setLabel(strLabel);
		targetBean.setFooter(strFooter);
	}
	
	protected ListMapPojo copyMapEntrySetToListMapPojo(Map<String, String> src)
	{
		ListMapPojo target = new ListMapPojo();
		List<MapPojo> mapPojos = new ArrayList<MapPojo>();
		
		for(Map.Entry<String, String> entry : src.entrySet()){
			mapPojos.add(new MapPojo(entry.getKey(), entry.getValue()));
		}
		
		target.setMapPojo(mapPojos);
		
		return target;
	}
	
	protected ListMapPojo copyListToListMapPojo(List<String> srcList)
	{
		ListMapPojo target = new ListMapPojo();
		List<MapPojo> mapPojos = new ArrayList<MapPojo>();
		
		for(String src : srcList){
			mapPojos.add(new MapPojo(src, src));
		}
		
		target.setMapPojo(mapPojos);
		
		return target;
	}
	
	protected String getDescription(ListMapPojo listMapPojo, String key, boolean splitFlag)
	{
		String value = null;
		if(key!=null && listMapPojo!=null && listMapPojo.getMapPojo()!=null && !listMapPojo.getMapPojo().isEmpty())
		{
			for(MapPojo mapPojo : listMapPojo.getMapPojo())
			{
				if(mapPojo.getKey().equalsIgnoreCase(key))
				{
					if(splitFlag)
					{
						String[] arrList = mapPojo.getValue().split(" ");
						value = arrList[0];
					}
					else
					{
						value = mapPojo.getValue();
					}
					break;
				}
			}
		}
		
		return value;
	}
	
	protected TakaGoalSettingBean getTakaGoalSettingBean(List<TakaGoalSettingBean> list, String key)
	{
		TakaGoalSettingBean bean = null;
		if(key!=null && list!=null && !list.isEmpty())
		{
			for(TakaGoalSettingBean elem : list)
			{
				if(elem.getGoalCode().equalsIgnoreCase(key))
				{
					bean = elem;
					break;
				}
			}
		}
		return bean;
	}
	
	/**
	 * Transforms a date String into a specified format. 
	 * Mainly used to convert date strings to a specific format for FE to display to users.
	 * Help synchronizes and controls the returned date in app.
	 * 
	 * @param dateInput Date string to be converted
	 * @param inputFormat Input date string format
	 * @param outputFormat Output date string format
	 * @return A date string converted to the output format
	 */
	protected String dateConverter(String dateInput, String inputFormat, String outputFormat) {
		DateFormat inputDateFormat = new SimpleDateFormat(inputFormat);
		DateFormat outputDateFormat = new SimpleDateFormat(outputFormat);
		try {
			return outputDateFormat.format(inputDateFormat.parse(dateInput));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Object convertJsonObject(Object obj, Class<?> clazz)
	{
		/*if(obj.getClass() == clazz)
		{
			log.info("Cast Request Object ::: "+clazz);
			return obj;
		}
		else
		{
			return gsonLog.fromJson(gsonLog.toJson(obj), clazz);
		}*/
		
		return obj;
	}
	
}


