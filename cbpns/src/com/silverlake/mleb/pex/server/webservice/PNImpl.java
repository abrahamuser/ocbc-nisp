package com.silverlake.mleb.pex.server.webservice;



import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;

import com.silverlake.mleb.pex.server.func.ProcessPushNotificationRequest;
import com.silverlake.mleb.pex.server.webservice.bean.WSPNRequest;
import com.silverlake.mleb.pex.server.webservice.bean.WSPNResponse;
import com.silverlake.mleb.pex.server.webservice.bean.WSResponse;



@org.apache.cxf.interceptor.InInterceptors (interceptors = {"com.silverlake.mleb.pex.server.webservice.PExWSInInterceptor","org.apache.cxf.binding.soap.saaj.SAAJInInterceptor" })
@org.apache.cxf.interceptor.OutInterceptors (interceptors = {"com.silverlake.mleb.pex.server.webservice.PExWSOutInterceptor","org.apache.cxf.binding.soap.saaj.SAAJOutInterceptor" })
@WebService(endpointInterface = "com.silverlake.mleb.pex.server.webservice.PNInterface")
@Scope(value=BeanDefinition.SCOPE_PROTOTYPE)
public class PNImpl implements  PNInterface
{
	//private static Logger log = Logger.getLogger(PExImpl.class);
	
	@Resource
	WebServiceContext wsContext;
	
	@Autowired ProcessPushNotificationRequest processPushNotificationRequest;

	@Override
	public WSResponse sendPushNotification(WSPNRequest request)
	{
		
		return (WSPNResponse)processPushNotificationRequest.process(wsContext,request);
	}
	
}
