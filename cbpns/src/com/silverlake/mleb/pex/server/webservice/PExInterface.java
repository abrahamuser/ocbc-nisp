package com.silverlake.mleb.pex.server.webservice;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.silverlake.mleb.pex.server.webservice.bean.WSPExRequest;
import com.silverlake.mleb.pex.server.webservice.bean.WSPExResponse;
import com.silverlake.mleb.pex.server.webservice.bean.WSResponse;




@WebService
@XmlSeeAlso({WSPExResponse.class})
public interface  PExInterface
{
	public WSResponse validatePExCollection(@WebParam(name="pexRequest")WSPExRequest request);
	
	public WSResponse retrieveBeneficiaryDetails(@WebParam(name="pexRequest")WSPExRequest request);
	
	public WSResponse performPExCollection(@WebParam(name="pexRequest")WSPExRequest request);
	
}