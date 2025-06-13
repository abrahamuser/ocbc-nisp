package com.silverlake.mleb.pex.server.webservice;

import java.util.List;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.google.gson.Gson;
import com.silverlake.mleb.pex.module.services.PExServices;
import com.silverlake.mleb.pex.server.webservice.bean.WSPExResponse;


public class PExWSOutInterceptor extends AbstractPhaseInterceptor<Message>
{
	public PExWSOutInterceptor()
	{
        super(Phase.USER_LOGICAL);
    }

	private static Logger log = LogManager.getLogger(PExWSOutInterceptor.class);
 
    
    @SuppressWarnings("unchecked")
    public void handleMessage(Message message) throws Fault
    {
    	Gson gson = new Gson();
        try
        {
        	
        	List<Object> myList = message.getContent(List.class);
        
	        Object SoapData = null;
	       
	        if(null!=myList)
	        {
	        	for (Object item : myList)
	        	{
	        		SoapData = item;
	        	}

	        	
	        	String soapMethod =  SoapData.getClass().getSimpleName();
	        	//SecurityContext c = message.get(SecurityContext.class); 
	        	//Principal p = c.getUserPrincipal(); 
	        	
	        	
	        	
	        	//log.info("SOAP METHOD :: "+soapMethod+" : "+message.getExchange().getBindingOperationInfo() );
	        	//log.info(message.getExchange().get(PExWSInInterceptor.exchange_id_key));
	        	//log.info(message.getId() );
	        	
	        	
        		String jsonString = gson.toJson(SoapData);
        		
        		JSONObject object = (JSONObject) JSONValue.parse(jsonString);
        		//log.info("SOAP string :: "+jsonString );
        	
        		Object dB =  object.get("_return");
        		WSPExResponse pexRp = gson.fromJson(gson.toJson(dB), WSPExResponse.class);
        		//log.info("SOAP Data :: "+pexRp.getObHeader().getStatusCode());
	        	
	        }
        }
        catch (Exception ex)
        {
        	ex.printStackTrace();
        }
    }
}
