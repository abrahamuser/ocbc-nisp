package com.silverlake.mleb.pex.server.webservice;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.security.SecurityContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.google.gson.Gson;
import com.silverlake.mleb.pex.module.services.PExServices;
import com.silverlake.mleb.pex.server.webservice.bean.WSPExRequest;
import com.silverlake.mleb.pex.util.TimeRefUtil;


public class PExWSInInterceptor extends AbstractPhaseInterceptor<Message>
{
	public PExWSInInterceptor()
	{
        super(Phase.USER_LOGICAL);
    }

	private static Logger log = LogManager.getLogger(PExWSInInterceptor.class);
	public static final String ref_date_format = "yyyyMMdd";
	public static final String exchange_id_key = "PEX_WS_ID";
	public static final String exchange_id_METHOD = "PEX_WS_METHOD";
    
    @SuppressWarnings("unchecked")
    public void handleMessage(Message message) throws Fault
    {
    	Gson gson = new Gson();
        try
        {
        	List<Object> myList = message.getContent(List.class);
        	HttpServletRequest httpRequest = (HttpServletRequest)message.get("HTTP.REQUEST");
        	HttpServletResponse httpResponse = (HttpServletResponse)message.get("HTTP.RESPONSE");
        	HttpSession session = httpRequest.getSession();

	        Object SoapData = null;
	       
	        if(null!=myList)
	        {
	        	for (Object item : myList)
	        	{
	        		SoapData = item;
	        	}

	        	
	        	String soapMethod =  SoapData.getClass().getSimpleName();
	        	SecurityContext c = message.get(SecurityContext.class); 
	        	Principal p = c.getUserPrincipal(); 
	        	
	        	SimpleDateFormat refFormat = new SimpleDateFormat(ref_date_format);
	        	String gen_request_id = refFormat.format(new Date())+TimeRefUtil.genTimeRef(3);
	        	message.getExchange().put(exchange_id_key, gen_request_id);
	        	message.getExchange().put(exchange_id_METHOD, soapMethod);
	        	//log.info("SOAP METHOD :: "+soapMethod + "  :  " +p.getName());
        		String jsonString = gson.toJson(SoapData);
        		
        		JSONObject object = (JSONObject) JSONValue.parse(jsonString);

        		WSPExRequest pexRequest = new WSPExRequest();
        		Object pexRq =  object.get("pexRequest");
        		pexRequest =  gson.fromJson(gson.toJson(pexRq), WSPExRequest.class);
	        		
        		
	        		
	        		/*String navPage = (String) object.get("navPage");
	        		 
	        		 
	        		DeviceBean deviceBean = new DeviceBean();
	        		Object dB =  object.get("deviceBean");
	        		deviceBean = gson.fromJson(gson.toJson(dB), DeviceBean.class);

	        		log.info("navPage "+navPage);
	        		log.info("DeviceBean "+deviceBean.getDeviceId());
	        		 */
	        	
	        	 
	        	
	        }
        }
        catch (Exception ex)
        {
        	ex.printStackTrace();
        }
    }
}
