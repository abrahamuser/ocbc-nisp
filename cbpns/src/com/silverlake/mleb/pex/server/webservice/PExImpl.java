package com.silverlake.mleb.pex.server.webservice;



import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;

import com.silverlake.mleb.pex.server.func.PerformPexCollectionTransfer;
import com.silverlake.mleb.pex.server.func.RetrieveBeneficiaryDetails;
import com.silverlake.mleb.pex.server.func.ValidatePexCollection;
import com.silverlake.mleb.pex.server.webservice.bean.WSPExRequest;
import com.silverlake.mleb.pex.server.webservice.bean.WSResponse;



@org.apache.cxf.interceptor.InInterceptors (interceptors = {"com.silverlake.mleb.pex.server.webservice.PExWSInInterceptor","org.apache.cxf.binding.soap.saaj.SAAJInInterceptor" })
@org.apache.cxf.interceptor.OutInterceptors (interceptors = {"com.silverlake.mleb.pex.server.webservice.PExWSOutInterceptor","org.apache.cxf.binding.soap.saaj.SAAJOutInterceptor" })
@WebService(endpointInterface = "com.silverlake.mleb.pex.server.webservice.PExInterface")
@Scope(value=BeanDefinition.SCOPE_PROTOTYPE)
public class PExImpl implements  PExInterface
{
	
	private static Logger log = LogManager.getLogger(PExImpl.class);
	
	@Resource
	WebServiceContext wsContext;
	
	@Autowired ValidatePexCollection validatePex;
	@Autowired PerformPexCollectionTransfer perforPex;
	@Autowired RetrieveBeneficiaryDetails retrieveBeneDetails;
	
	
	@Override
	public WSResponse validatePExCollection(WSPExRequest request) {
		
		return validatePex.process(wsContext, request);
	}

	@Override
	public WSResponse performPExCollection(WSPExRequest request) {
		// TODO Auto-generated method stub
		return perforPex.process(wsContext, request);
	}

	@Override
	public WSResponse retrieveBeneficiaryDetails(WSPExRequest request) {
		// TODO Auto-generated method stub
		return retrieveBeneDetails.process(wsContext, request);
	}

	



	
}
