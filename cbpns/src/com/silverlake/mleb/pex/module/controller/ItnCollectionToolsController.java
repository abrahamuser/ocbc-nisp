package com.silverlake.mleb.pex.module.controller;


import java.math.BigDecimal;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.apache.cxf.binding.soap.saaj.SAAJOutInterceptor;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.ws.security.handler.WSHandlerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.caucho.hessian.client.HessianProxyFactory;
import com.silverlake.mleb.pex.server.webservice.PExInterface;
import com.silverlake.mleb.pex.server.webservice.bean.WSAmount;
import com.silverlake.mleb.pex.server.webservice.bean.WSHeaderRequest;
import com.silverlake.mleb.pex.server.webservice.bean.WSPExRequest;
import com.silverlake.mleb.pex.server.webservice.bean.WSPExResponse;
import com.silverlake.mleb.pex.util.ClientPasswordCallback;
import com.silverlake.mleb.pex.util.ClientPasswordCallbackPExWS;
import com.silverlake.mleb.pex.util.DataBeanUtil;
import com.silverlake.mleb.pex.util.PropertiesManager;

 
@Controller
public class ItnCollectionToolsController {
 
	final static Logger log = LogManager.getLogger(ItnCollectionToolsController.class);
 
	
	
	@RequestMapping(value = "itnsimtools", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView();
		model.setViewName("itnTools");
		log.info("--------------  Get Internet Collection ------------------");
		return model;
	}
	
	@RequestMapping(value = "itnsimtools", method = RequestMethod.POST)
	public ModelAndView post(HttpServletRequest request,
	        HttpServletResponse response) {
		ModelAndView model = new ModelAndView();
		model.setViewName("itnTools");
		
		
		DataBeanUtil dataBeanUtil = new DataBeanUtil();
		PropertiesManager pm = new PropertiesManager();
		String pdata = pm.getProperty("itnsimtool");
		
		log.info("------------------------:["+pdata+"]");
		if(null!=pdata && pdata.equalsIgnoreCase("true"))
		{
			try
			{
			
			String amount = request.getParameter("amount");
			String ccode = request.getParameter("ccode");
			String mnumber = request.getParameter("mnumber");
			
			
			amount = ""==amount?null:(null==amount?null:amount.replaceAll("\\.", ""));;
			ccode = ""==ccode?null:ccode;
			mnumber = ""==mnumber?null:mnumber;
			
			log.info("["+amount+"] : ["+ccode+"] : ["+mnumber+"]");
			
			if( null!=amount && null!=ccode && null!=mnumber)
			{
				
				
				int serverPort = request.getServerPort();
				URL wsdlURL = new URL("http://localhost:"+serverPort+"/pex_ws/ws/PExWSServices?wsdl");
				log.info("WSDL :::: "+"http://localhost:"+serverPort+"/pex_ws/ws/PExWSServices?wsdl");
				QName SERVICE_NAME = new QName("http://webservice.server.pex.hlb.silverlake.com/", "PExImplService");
				Service service = Service.create(wsdlURL, SERVICE_NAME);
				PExInterface client = service.getPort(PExInterface.class);
				
				
				Client wsclient = ClientProxy.getClient(client);
		        Endpoint cxfEndpoint = wsclient.getEndpoint();
		        Map<String, Object> outProps = new HashMap<String, Object>();
		        
		        outProps.put("action", "UsernameToken");
		        outProps.put("user", "arthas");
		        outProps.put("passwordType", "PasswordText");
		        outProps.put(WSHandlerConstants.PW_CALLBACK_CLASS, ClientPasswordCallbackPExWS.class.getName());
		        WSS4JOutInterceptor wssOut = new WSS4JOutInterceptor(outProps);
		       
		        cxfEndpoint.getOutInterceptors().add(wssOut);
		        cxfEndpoint.getOutInterceptors().add(new SAAJOutInterceptor());
				
				
				WSPExRequest wsReq = new WSPExRequest();
				wsReq.setObHeader(new WSHeaderRequest());
				wsReq.getObHeader().setId(UUID.randomUUID().toString());
				wsReq.getObHeader().setChannelId("01");
				wsReq.getObHeader().setVersion("1.0");
				wsReq.setMobileNumber(mnumber);
				wsReq.setCollectionCode(ccode);
				WSAmount wsAmt = new WSAmount();
				wsAmt.setAmount(new BigDecimal(amount));
				wsReq.setAmount(wsAmt);
				
				WSPExResponse result = (WSPExResponse) client.validatePExCollection(wsReq);
				
				model.addObject("rs1", result.getObHeader().getStatusCode());
				
				
				if(result.getObHeader().getStatusCode().equalsIgnoreCase("0000000"))
				{
					
					
					result = (WSPExResponse) client.performPExCollection(wsReq);
					model.addObject("rs2", result.getObHeader().getStatusCode());
				}
				
				
			}
			else if( null!=amount || null!=ccode || null!=mnumber)
			{
				log.info("----invalid Input-----");
		
				model.addObject("rsx", "Invalid Input Data");
			}
			
			
			}catch(Exception ex)
			{
				log.info("ERROR ",ex);
			}
		}
		
		
		
		
		return model;
	}
 
}