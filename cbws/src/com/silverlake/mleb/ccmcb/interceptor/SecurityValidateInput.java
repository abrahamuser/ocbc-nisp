package com.silverlake.mleb.ccmcb.interceptor;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.logging.log4j.LogManager;
//import org.apache.commons.lang.StringEscapeUtils;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.silverlake.mleb.ccmcb.util.SecurityUtil;
import com.silverlake.mleb.mcb.constant.MiBConstant;



public class SecurityValidateInput implements SOAPHandler<SOAPMessageContext>
{
	private static Logger log = LogManager.getLogger(SecurityValidateInput.class);
	public Set<QName> getHeaders() {
		return null;
	}

	public void close(MessageContext context) {
		
	}

	public boolean handleFault(SOAPMessageContext context) {
		return true;
	}
	
	public boolean handleMessage(SOAPMessageContext context)
	{
		
		SecurityUtil secUtil = new SecurityUtil();
		Gson gsonLog = new GsonBuilder().setPrettyPrinting().create();
		Boolean outbound = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		
		HttpServletRequest request =   (HttpServletRequest)context.get(MessageContext.SERVLET_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(MessageContext.SERVLET_RESPONSE);
		HttpSession session = request.getSession();
		
		
		
		
		if (outbound && secUtil.is_input_check())
		{
			try
			{
				//clear error
				secUtil.removeSecurityError(session);
				
	            SOAPBody body = context.getMessage().getSOAPBody();
	           
	            String wsMethod = secUtil.getWSMethod(body);
	            
	            
	           
			  	
			  	
			  	SOAPEnvelope respHeader = context.getMessage().getSOAPPart().getEnvelope();
  
	      
			}
			catch(Exception e)
			{
				log.debug("Exception in MiBWSRequestChain.handleMessage() :: "+e);
				e.printStackTrace();
			}
		}
		
		
		else if(secUtil.is_input_check() && !secUtil.is_security_counter(session))
		{
			log.info("INCOMING SOAP MESSAGE : Validate Input");
			
			try
			{
				
				SOAPHeader header = context.getMessage().getSOAPHeader();
				
			
			  	Iterator headers = secUtil.getWSParamValue(header).entrySet().iterator();
			 
			  	while(headers.hasNext())
                {
                	Map.Entry mEntry = (Map.Entry) headers.next();
    				//log.debug("WS HEADER PARAM VALUE : "+mEntry.getKey() + " : " + mEntry.getValue());
                	//P3OCBCUAT-79
                	if(mEntry.getKey().toString().equalsIgnoreCase("model")){
                		if(!secUtil.isValidDeviceModelCheck(mEntry.getValue().toString())){
	            			
	            			session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_TAG, SecurityUtil.SECURITY_ERROR_INVALID_INPUT_PARAM);
		            		return true;
	            		}
                	}
                	else if(mEntry.getKey().toString().equalsIgnoreCase("pnstoken")){
                		if(!secUtil.isValidPNSTokenCheck(mEntry.getValue().toString())){
	            			
	            			session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_TAG, SecurityUtil.SECURITY_ERROR_INVALID_INPUT_PARAM);
		            		return true;
	            		}
                	}
                	else if(mEntry.getKey().toString().equalsIgnoreCase("ip")) {
                	}
                	else if(!secUtil.isValidHeaderParam(mEntry.getValue().toString()))
                	{
                		session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_TAG, SecurityUtil.SECURITY_ERROR_INVALID_INPUT_PARAM);
	            		return true;
                	}
                }
				
				
	            SOAPBody body = context.getMessage().getSOAPBody();
	         
	            String wsMethod = secUtil.getWSMethod(body);
	           
	            Iterator bodyParam = secUtil.getWSParamValue(context.getMessage().getSOAPBody()).entrySet().iterator();
	            //log.info(":::::::::::::::::xx:::::::BODY");
	            while(bodyParam.hasNext())
	            {
	            	Map.Entry mBody = (Map.Entry) bodyParam.next();
	            	log.info(":::::::::::::::::xx:::::::["+mBody.getKey().toString()+"]::: ["+mBody.getValue().toString()+"]");
	            	/*if(mBody.getKey().toString().equalsIgnoreCase("contactNumber") )
	            	{
	            		if( !secUtil.isValidContactNumber(mBody.getValue().toString()))
        				{
	            			session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_TAG, SecurityUtil.SECURITY_ERROR_INVALID_INPUT_PARAM);
	            			return true;
        				}

	            	}*/	     
	            	
	            	
	            	if(mBody.getKey().toString().equalsIgnoreCase("over_flow_list_check"))
        			{
	            		if( !secUtil.isIdNumber(mBody.getValue().toString()))
        				{
	            			session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_TAG, SecurityUtil.SECURITY_ERROR_INVALID_INPUT_PARAM);
	            			return true;
        				}
        			}
	            	else if(mBody.getKey().toString().equalsIgnoreCase("idNumber") )
	            	{
	            		if( !secUtil.isIdNumber(mBody.getValue().toString()))
        				{
	            			session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_TAG, SecurityUtil.SECURITY_ERROR_INVALID_INPUT_PARAM);
	            			return true;
        				}

	            	}
	            	else if(mBody.getKey().toString().equalsIgnoreCase("sessionID") | mBody.getKey().toString().startsWith("sessionID_"))
	            	{

	            		if( !secUtil.isLoginClientSession(mBody.getValue().toString()))
        				{
	            			session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_TAG, SecurityUtil.SECURITY_ERROR_INVALID_INPUT_PARAM);
	            			return true;
        				}
	            		
	            	}
	            	else if( mBody.getKey().toString().equalsIgnoreCase("loginPassword"))
	            	{
	            		if( !secUtil.isPasswordParam(mBody.getValue().toString()))
        				{
	            			session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_TAG, SecurityUtil.SECURITY_ERROR_INVALID_INPUT_PARAM);
	            			return true;
        				}

	            	}
	            	
	            	else if(mBody.getKey().toString().equalsIgnoreCase("accountDesc")
	 	            			||mBody.getKey().toString().equalsIgnoreCase("receipientRef")
								||mBody.getKey().toString().equalsIgnoreCase("otherDetails"))
								 	            	{
            			 if(!secUtil.isValidPaymentDescParam(mBody.getValue().toString()))
            			 {
            				session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_TAG, MiBConstant.MIB_OTH_DETAIL);
 		            		session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_PARAM_TAG, mBody.getKey().toString());
 		            		return true;
            			 }
 	            	}
	            	else if(	mBody.getKey().toString().equalsIgnoreCase("othPayAmt")
	            			|| mBody.getKey().toString().equalsIgnoreCase("state") 
	            			|| mBody.getKey().toString().equalsIgnoreCase("serviceType") || mBody.getKey().toString().contains("Income") || mBody.getKey().toString().equalsIgnoreCase("pageCacheJson") || mBody.getKey().toString().contains("Spending"))
	            	{
	            		
	            		//log.debug(" DESCRIPTION CHECK : "+mBody.getValue().toString());
	            		//log.debug(" DESCRIPTION Pattern : "+SecurityUtil.descInputPattern);
	            		
	            		if( !secUtil.isValidDescParam(mBody.getValue().toString()))
	            		{
		            		session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_TAG, MiBConstant.MIB_OTH_DETAIL);
		            		session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_PARAM_TAG, mBody.getKey().toString());
		            		return true;
	            		}else if(mBody.getKey().toString().equalsIgnoreCase("receipientRef") && secUtil.isSpaceOnly(mBody.getValue().toString()))   //space only
	            		{
	            			session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_TAG, MiBConstant.MIB_OTH_DETAIL);
		            		session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_PARAM_TAG, mBody.getKey().toString());
		            		return true;
	            		}
	            	}
	            	
	            	else if (mBody.getKey().toString().startsWith("address")) {
	            		if( !secUtil.isValidAddressParam(mBody.getValue().toString()))
	            		{
	            			session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_TAG, MiBConstant.MIB_OTH_DETAIL);
		            		session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_PARAM_TAG, mBody.getKey().toString());
		            		return true;
	            		}
	            	}
	            	
	            	else if( mBody.getKey().toString().equalsIgnoreCase("senderName")  ||  mBody.getKey().toString().equalsIgnoreCase("contactName") || mBody.getKey().toString().equalsIgnoreCase("emailId") )
	            	{
	            		
	            		if( !secUtil.isValidDescNoVNParam(mBody.getValue().toString()))
	            		{
		            		session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_TAG, MiBConstant.MIB_INVALID_INPUT_CHARACTER_ELEVEN);
		            		session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_PARAM_TAG, mBody.getKey().toString());
		            		session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_REGEX_TAG, SecurityUtil.getValidDescNoVNParamReadable());
		            		return true;
	            		}
	            	}
	        
	            	else if(mBody.getKey().toString().equalsIgnoreCase("transactionDate")){
	            		if(!secUtil.isMposDateParam(mBody.getValue().toString())){
		            		session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_TAG, MiBConstant.MIB_INVALID_INPUT_CHARACTER_ELEVEN);
		            		session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_PARAM_TAG, mBody.getKey().toString());
		            		session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_REGEX_TAG, SecurityUtil.getMposDateParamReadable());
		            		return true;
	            		}
	            		
	            	}
	            	else if(mBody.getKey().toString().equalsIgnoreCase("loginType") || mBody.getKey().toString().equalsIgnoreCase("passCode") || 
	            			mBody.getKey().toString().equalsIgnoreCase("qrContent") || mBody.getKey().toString().equalsIgnoreCase("fileData") || 
	            			mBody.getKey().toString().equalsIgnoreCase("custPhone")){
	            		
	            		if(!secUtil.isValidDynamicParam(mBody.getValue().toString())){
	            			
	            			session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_TAG, MiBConstant.MIB_INVALID_INPUT_CHARACTER_ELEVEN);
	            			session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_PARAM_TAG, mBody.getKey().toString());
	            			session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_REGEX_TAG, SecurityUtil.getValidDynamicParamReadable());
	            			return true;
	            		}
	            		
	            	}
	            	else if(mBody.getKey().toString().startsWith("transferDate_") || mBody.getKey().toString().startsWith("recurringDate_") || 
	            			mBody.getKey().toString().startsWith("futureDated_")){
	            		if(!secUtil.isValidDateParam(mBody.getValue().toString())){
	            			
		            		session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_TAG, MiBConstant.MIB_INVALID_INPUT_CHARACTER_ELEVEN);
		            		session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_PARAM_TAG, mBody.getKey().toString());
		            		session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_REGEX_TAG, SecurityUtil.getValidDateParamReadable());
		            		return true;
	            		}
	            	}
	            	else if(mBody.getKey().toString().startsWith("email_") || mBody.getKey().toString().startsWith("email") || mBody.getKey().toString().startsWith("custEmail") || mBody.getKey().toString().startsWith("emailAddr") || (mBody.getKey().toString().startsWith("businessEmail_"))){
	            		
	            		if(!secUtil.isValidEmailParam(mBody.getValue().toString())){
	            			
		            		session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_TAG, MiBConstant.MIB_INVALID_INPUT_CHARACTER_ELEVEN);
		            		session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_PARAM_TAG, mBody.getKey().toString());
		            		session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_REGEX_TAG, SecurityUtil.getValidEmailParamReadable());
		            		return true;
	            		}
	            		
	            	}
	            	else if(mBody.getKey().toString().startsWith("alias_") || mBody.getKey().toString().equalsIgnoreCase("nickName") || mBody.getKey().toString().startsWith("beneNickName")){
	            		if(!secUtil.isValidAliasCheck(mBody.getValue().toString())){
	            			
		            		session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_TAG, MiBConstant.MIB_INVALID_INPUT_CHARACTER_ELEVEN);
		            		session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_PARAM_TAG, mBody.getKey().toString());
		            		session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_REGEX_TAG, SecurityUtil.getValidAliasReadable());
		            		return true;
	            		}
	            	}
	            	else if(mBody.getKey().toString().equalsIgnoreCase("remark") 
	            			|| mBody.getKey().toString().startsWith("remark_") 
 	            			|| mBody.getKey().toString().startsWith("description_") || 
 	            			mBody.getKey().toString().equalsIgnoreCase("description")){
	            		if(!secUtil.isValidDescCheck(mBody.getValue().toString())){
	            			
		            		session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_TAG, MiBConstant.MIB_INVALID_INPUT_CHARACTER_ELEVEN);
		            		session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_PARAM_TAG, mBody.getKey().toString());
		            		session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_REGEX_TAG, SecurityUtil.getValidDescReadable());
		            		return true;
	            		}
	            	}
	            	else if(mBody.getKey().toString().equalsIgnoreCase("additional_info")){
	            		if(!secUtil.isValidAddInfoCheck(mBody.getValue().toString())){
	            			
		            		session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_TAG, MiBConstant.MIB_INVALID_INPUT_CHARACTER_ELEVEN);
		            		session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_PARAM_TAG, mBody.getKey().toString());
		            		session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_REGEX_TAG, SecurityUtil.getValidAddInfoReadable());
		            		return true;
	            		}
	            	}
	            	else if(mBody.getKey().toString().equalsIgnoreCase("errorMsg")){
	            		if(!secUtil.isErrorMsgParam(mBody.getValue().toString())){
	            			
	            			session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_TAG, SecurityUtil.SECURITY_ERROR_INVALID_INPUT_PARAM);
		            		return true;
	            		}
	            	}
	            	else if(mBody.getKey().toString().equalsIgnoreCase("uuid")){
	            			if(!secUtil.isValidUUIDCheck(mBody.getValue().toString())){
	            			
	            			session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_TAG, SecurityUtil.SECURITY_ERROR_INVALID_INPUT_PARAM);
		            		return true;
	            		}
	            	}else if(mBody.getKey().toString().startsWith("businessIndustry_")){
            			if(!secUtil.isValidBusinessIndustryCheck(mBody.getValue().toString())){
	            			
            			session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_TAG, SecurityUtil.SECURITY_ERROR_INVALID_INPUT_PARAM);
	            		return true;
	            		}
	            	}else if(mBody.getKey().toString().startsWith("golonganDarah_")){
            			if(!secUtil.isValidBlood(mBody.getValue().toString())){
	            			
            			session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_TAG, SecurityUtil.SECURITY_ERROR_INVALID_INPUT_PARAM);
	            		return true;
	            		}
	            	}
	            	else if(mBody.getKey().toString().startsWith("ktpInfo_"))
	            	{
	            		if( !secUtil.isValidDescParam(mBody.getValue().toString())){
	            			session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_TAG, SecurityUtil.SECURITY_ERROR_INVALID_INPUT_PARAM);
		            		return true;
	            		}
	            	}
	            	else if(mBody.getKey().toString().startsWith("feedback"))
	            	{
	            		if( !secUtil.isValidDescParamNoLimit(mBody.getValue().toString())){
	            			session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_TAG, SecurityUtil.SECURITY_ERROR_INVALID_INPUT_PARAM);
		            		return true;
	            		}
	            	}
	            	else if(mBody.getKey().toString().startsWith("beneAccountName") ){
	            		if(!secUtil.isValidAddress(mBody.getValue().toString())){
	            			
		            		session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_TAG, MiBConstant.MIB_INVALID_INPUT_CHARACTER_ELEVEN);
		            		session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_PARAM_TAG, mBody.getKey().toString());
		            		session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_REGEX_TAG, SecurityUtil.getValidAddressReadable());
		            		return true;
	            		}
	            	}
	            	else if(mBody.getKey().toString().startsWith("identificationRisk_"))
        			{
	            		if( !secUtil.isAdditionalWorkInfoParam(mBody.getValue().toString()))
        				{
	            			session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_TAG, SecurityUtil.SECURITY_ERROR_INVALID_INPUT_PARAM);
	            			return true;
        				}
        			}
	            	else if(mBody.getKey().toString().equalsIgnoreCase("noteRemark")){
	            		if(!secUtil.isValidDescNewlineCheck(mBody.getValue().toString())){
	            			session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_TAG, MiBConstant.MIB_INVALID_INPUT_CHARACTER_ELEVEN);
		            		session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_PARAM_TAG, mBody.getKey().toString());
		            		session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_REGEX_TAG, SecurityUtil.getValidDescNewlineReadable());
		            		return true;
	            		}
	            	}
	            	else if(mBody.getKey().toString().equalsIgnoreCase("recordIds") 
	            			|| mBody.getKey().toString().equalsIgnoreCase("record_id") ){
	            		if(!secUtil.isValidRecordId(mBody.getValue().toString())){
	            			
	            			session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_TAG, SecurityUtil.SECURITY_ERROR_INVALID_INPUT_PARAM);
		            		return true;
	            		}
	            	}else if(mBody.getKey().toString().equalsIgnoreCase("proxy_data"))
	            	{
	            		if (!secUtil.isValidEmailParam(mBody.getValue().toString())) {

							session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_TAG,
									SecurityUtil.SECURITY_ERROR_INVALID_INPUT_PARAM);
							return true;
						}
	            	}
	            	else if(mBody.getKey().toString().equalsIgnoreCase("beneAccountNo") || mBody.getKey().toString().startsWith("accountNo"))
	            	{
	            		if (!secUtil.isValidAlphanumeric(mBody.getValue().toString())) {
	            			session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_TAG, MiBConstant.MIB_INVALID_INPUT_ALPHANUMERIC);
		            		return true;
						}
	            	}
	            	else if(mBody.getKey().toString().startsWith("beneAddress") || mBody.getKey().toString().startsWith("beneBankAddress"))
	            	{
	            		if (!secUtil.isValidParam(mBody.getValue().toString())) {
	            			session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_TAG, MiBConstant.MIB_INVALID_INPUT_ADDRESS);
		            		return true;
						}
	            	}
	            	else if(!secUtil.isValidParam(mBody.getValue().toString()))
	            	{
	            		session.setAttribute(SecurityUtil.SECURITY_SESSION_ERROR_TAG, SecurityUtil.SECURITY_ERROR_INVALID_INPUT_PARAM);
	            		return true;
	            	}
	            	
	            	

	            }
	            
	            
	           
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		return true;
	}
	
	
	public static void main(String args[])
	{
		String unicodemix = "hlb01\u1EE1";
		
		String spe = "á»¡";
		String reg = "^[0-9a-zA-Zá»¡\\-\\s /]*$";
		
//		System.out.println(""+StringEscapeUtils.escapeJava(spe));
//		System.out.println(""+spe);
//		System.out.println(""+unicodemix.matches(reg));
		
	}
	
}