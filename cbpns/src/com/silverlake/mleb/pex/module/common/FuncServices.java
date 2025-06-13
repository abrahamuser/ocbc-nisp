package com.silverlake.mleb.pex.module.common;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.silverlake.hlb.mib.bean.ObASDebitCardRequest;
import com.silverlake.hlb.mib.bean.ObASFixedDepositRequest;
import com.silverlake.hlb.mib.bean.ObASLoanRequest;
import com.silverlake.hlb.mib.bean.ObASSavingRequest;
import com.silverlake.hlb.mib.bean.ObAuthenticationResponse;
import com.silverlake.hlb.mib.bean.ObCrCrdPayDetailResponseBean;
import com.silverlake.hlb.mib.bean.ObFTResponse;
import com.silverlake.hlb.mib.bean.ObHeaderResponse;
import com.silverlake.hlb.mib.bean.ObIGBPayDetailResponseBean;
import com.silverlake.hlb.mib.bean.ObLoanResponse;
import com.silverlake.hlb.mib.bean.ObRequest;
import com.silverlake.hlb.mib.bean.ObResponse;
import com.silverlake.hlb.mib.bean.ObThirdPartyTransPayDetailResponseBean;
import com.silverlake.hlb.mib.constant.MiBConstant;
import com.silverlake.hlb.mib.entity.HlbCustomerEvent;
import com.silverlake.micb.core.bean.HeaderBean;
import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.pex.bean.ObPexResponse;
import com.silverlake.mleb.pex.listerner.LogDBManager;
import com.silverlake.mleb.pex.listerner.ServerMaintenanceManager;
import com.silverlake.mleb.pex.listerner.UntagDeviceSessionRemover;
import com.silverlake.mleb.pex.util.MessageManager;
import com.silverlake.mleb.pex.util.PropertiesManager;


public abstract class FuncServices 
{
	private static Logger log = LogManager.getLogger(FuncServices.class);	
	
	private Gson gsonLog = new GsonBuilder().setPrettyPrinting().create();
	
	private PropertiesManager pro = new PropertiesManager();
	private MessageManager msgPro = new MessageManager();
	public FuncServices()
	{
		
	}
	
	@Autowired
	LogDBManager logDBManager;
	
	@Autowired
	UntagDeviceSessionRemover untagDeviceCheck;
	
	
	@Autowired
	private ServerMaintenanceManager serverMaintenanceManager;
	
	
	public MICBResponseBean acceptProcess(MICBRequestBean arg0)
	{
		MICBResponseBean micbResponseBean = new MICBResponseBean();
		ObRequest obRequestWS = new ObRequest();
		Date requestDate = new Date();
		try
		{
			
			/*ObRequest obRequest = (ObRequest) arg0.getBDObject();
			
			obHeaderRequest.setRequestId(arg0.getTranxID());
			obHeaderRequest.setChannelCode(MiBConstant.IB_ChannelID);
			obHeaderRequest.setCountryCode("");
			obHeaderRequest.setLocaleCode(arg0.getLocaleCode());
			obHeaderRequest.setLoginId(obRequest.getUserContext().getLoginId());
			obHeaderRequest.setSessionId(obRequest.getUserContext().getSessionId());
			*/
			
			
		/*	MuleClient client = new MuleClient(getMuleContext());
			FutureMessageResult futureMsg = client.sendAsync("vm://untagDeviceCheck", arg0, null);*/
			
	        	
			log.info(this.getClass().getSimpleName()+" :- REQUEST \n "+filterGson(gsonLog.toJson(arg0.getBDObject())));
			

			Object resultCheck = serverMaintenanceManager.onCall(arg0);
			if(resultCheck instanceof MICBResponseBean )
			{
				return (MICBResponseBean) resultCheck;
			}
			arg0 = (MICBRequestBean) resultCheck;
			
			
			ObResponse mibResponse = null;
			String  rspData = untagDeviceCheck.onCall(arg0);
			 obRequestWS = (ObRequest) arg0.getBDObject();
			if(null!=rspData && rspData.toString().equalsIgnoreCase(MiBConstant.MIB_STATUSCODE_SUCCESS_UNTAGGED_CURRENT_DEVICE) && !mibResponse.getObHeader().getStatusCode().equalsIgnoreCase(MiBConstant.MIB_STATUSCODE_SUCCESS_UNTAGGED_CURRENT_DEVICE))
			{
				mibResponse = new ObResponse();
				mibResponse.setObHeader(new ObHeaderResponse());
				mibResponse.getObHeader().setStatusCode(MiBConstant.MIB_STATUSCODE_SUCCESS_UNTAGGED_CURRENT_DEVICE);
				micbResponseBean.setBDObject(mibResponse);
			}
			else
			{
	        
		        String actualRequestInfo = null==arg0.getHeaderBean().getRequestInfo()?"":arg0.getHeaderBean().getRequestInfo();
		      
				micbResponseBean = process(arg0);
				
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
					if(null!=rspData && rspData.toString().equalsIgnoreCase(MiBConstant.MIB_STATUSCODE_SUCCESS_UNTAGGED_CURRENT_DEVICE))
					{
						mibResponse = new ObResponse();
						mibResponse.setObHeader(new ObHeaderResponse());
						mibResponse.getObHeader().setStatusCode(MiBConstant.MIB_STATUSCODE_SUCCESS_UNTAGGED_CURRENT_DEVICE);
						micbResponseBean.setBDObject(mibResponse);
					}*/
				
				}
			
			}
				 
		}
		catch(Exception e)
		{
			e.printStackTrace();
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
		log.info(this.getClass().getSimpleName()+" :- RESPONSE \n "+filterGson(gsonLog.toJson(obResponseWS)));
		insertCustomerEvent(obRequestWS,obResponseWS,arg0,this.getClass().getSimpleName(),arg0.getHeaderBean().getRequestInfo(),requestDate,new Date());
	    
		return micbResponseBean;
		
	}
	
	
	
	
	
	
	
	public abstract MICBResponseBean process(MICBRequestBean arg0);
	
	
	//public abstract MuleContext getMuleContext();
	

	public void insertCustomerEvent(ObRequest obRequest, ObResponse obResponse, MICBRequestBean micbRequestBean,String service, String appVersion,Date requestDate, Date responseDate)
	{
		
		
		String cifNo = null==obRequest.getObUser()?null:obRequest.getObUser().getCifNumber();
		if(obResponse instanceof ObAuthenticationResponse && cifNo==null)
		{
			
			ObAuthenticationResponse obAuthentication = (ObAuthenticationResponse) obResponse ;
			cifNo = null==obAuthentication.getObUser()?null:obAuthentication.getObUser().getCifNumber();
		}
		
		//String deviceID =  null==micbRequestBean.getDeviceBean()?null:micbRequestBean.getDeviceBean().getDeviceId();
		String deviceID =  null==micbRequestBean.getDeviceBean()?null:micbRequestBean.getDeviceBean().getDeviceId() + "[Rooted : "+ micbRequestBean.getDeviceBean().isRooted() + "]";
		
		
		if(null!=cifNo && cifNo.length()>0)
		{
			HlbCustomerEvent custmorEvent = new HlbCustomerEvent();
			
			
			
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
			else if(obResponse instanceof ObCrCrdPayDetailResponseBean )
			{
				ObCrCrdPayDetailResponseBean obRequestPAY = (ObCrCrdPayDetailResponseBean) obResponse;
				custmorEvent.setReferenceNo(null==obRequestPAY.getObCrCrdResponseResult()?null:obRequestPAY.getObCrCrdResponseResult().getRefNo());

			}
			else if(obResponse instanceof ObFTResponse )
			{
				ObFTResponse obRequestPAY = (ObFTResponse) obResponse;
				custmorEvent.setReferenceNo(null==obRequestPAY.getObFTConfirmBean()?null:obRequestPAY.getObFTConfirmBean().getReferenceNumber());

			}
			else if(obResponse instanceof ObIGBPayDetailResponseBean )
			{
				ObIGBPayDetailResponseBean obRequestPAY = (ObIGBPayDetailResponseBean) obResponse;
				custmorEvent.setReferenceNo(null==obRequestPAY.getObFavAccResult()?null:obRequestPAY.getObFavAccResult().getRefNo());

			}
			else if(obResponse instanceof ObLoanResponse )
			{
				ObLoanResponse obRequestPAY = (ObLoanResponse) obResponse;
				custmorEvent.setReferenceNo(null==obRequestPAY.getObloanCommitBean()?null:obRequestPAY.getObloanCommitBean().getReferenceNumber());

			}
			else if(obResponse instanceof ObThirdPartyTransPayDetailResponseBean )
			{
				ObThirdPartyTransPayDetailResponseBean obRequestPAY = (ObThirdPartyTransPayDetailResponseBean) obResponse;
				custmorEvent.setReferenceNo(null==obRequestPAY.getObFavAccResult()?null:obRequestPAY.getObFavAccResult().getRefNo());

			}
			else if(obResponse instanceof ObPexResponse)
			{
				ObPexResponse pexResponse = (ObPexResponse) obResponse;
				//pexResponse.getPexTransactionDetails().getErrorMessage();
				//pexResponse.getPexTransactionDetails().getStatus();
				
				String errorCode = null==pexResponse.getPexTransactionDetails()?"":" - "+pexResponse.getPexTransactionDetails().getErrorMessage();
				String errorStatus = null==pexResponse.getPexTransactionDetails()? "" :pexResponse.getPexTransactionDetails().getStatus();
				if(null!=errorStatus){custmorEvent.setErrorMessage(errorStatus+errorCode);}
				custmorEvent.setReferenceNo(null==pexResponse.getPexTransactionDetails()?null:pexResponse.getPexTransactionDetails().getReferenceNumber());
			}
			
			
			
			if(!obResponse.getObHeader().getStatusCode().equalsIgnoreCase(MiBConstant.MIB_COMMON_SUCCESS))
			{
				
				
				String error_msg = msgPro.getProperty(obResponse.getObHeader().getStatusCode(), obRequest.getUserContext().getLocaleCode());
				
				
				if(error_msg==null)
				{
					error_msg = msgPro.getProperty(MiBConstant.MIB_GENERAL_ERROR, obRequest.getUserContext().getLocaleCode());
					
				}
				
				
				error_msg = null==error_msg?"":error_msg;
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
	
			custmorEvent.setAppVersion(appVersion);
			custmorEvent.setRequestDatetime(requestDate);
			custmorEvent.setResponseDatetime(responseDate);
			custmorEvent.setMlebTranxId(micbRequestBean.getTranxID());
			custmorEvent.setStatusCode(obResponse.getObHeader().getStatusCode());
			custmorEvent.setDeviceId(deviceID);
			
			//log.info(gsonLog.toJson(custmorEvent));
			/*MuleClient client = new MuleClient(getMuleContext());
			client.sendNoReceive("vm://ibwsTranxLogging", custmorEvent, null);*/
			logDBManager.onCall(custmorEvent);
		}
		
	}
	
	
	
	public String filterGson(String data)
	{
		String[] masks = {"pin","password","mPassword","loginImage","promotionImag", "confirmPassword","collectionCode"};
		
		
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
				
				
				
				if(mask.equalsIgnoreCase("mPassword"))
				{
					
					
					//System.out.println("Cut data msg : "+checkCut.substring(2,indexCut));
					int indexCut = checkCut.indexOf(Character.toString(bx)+",\n");
					
					data = data.substring(0, checkExist+lengthTag+2)+"**********"+Character.toString(bx)+ data.substring(checkExist+lengthTag+2+indexCut);
					
				}
				else if(mask.equalsIgnoreCase("loginImage"))
				{
					
					
					int indexCut = checkCut.indexOf(Character.toString(bx)+",\n");
					data = data.substring(0, checkExist+lengthTag+2)+"**********"+Character.toString(bx)+ data.substring(checkExist+lengthTag+2+indexCut);
					
				}
				else if(mask.equalsIgnoreCase("promotionImag"))
				{
					
					
					int indexCut = checkCut.indexOf(Character.toString(bx)+",\n");
					data = data.substring(0, checkExist+lengthTag+2)+"**********"+Character.toString(bx)+ data.substring(checkExist+lengthTag+2+indexCut);
					
				}
				else
				{
				
					int indexCut = checkCut.indexOf(Character.toString(b)+",\n");
					data = data.substring(0, checkExist+lengthTag+2)+"**********"+Character.toString(b)+ data.substring(checkExist+lengthTag+2+indexCut);
				
				}
			}
			
			
			
		}
		
		
		
		
		return data;
	}
	
	
	

	
	
	
	
}
