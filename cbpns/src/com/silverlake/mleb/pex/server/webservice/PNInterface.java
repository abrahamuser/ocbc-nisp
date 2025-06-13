package com.silverlake.mleb.pex.server.webservice;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.silverlake.mleb.pex.server.webservice.bean.WSPExResponse;
import com.silverlake.mleb.pex.server.webservice.bean.WSPNRequest;
import com.silverlake.mleb.pex.server.webservice.bean.WSPNResponse;
import com.silverlake.mleb.pex.server.webservice.bean.WSResponse;




@WebService
@XmlSeeAlso({WSPNResponse.class})
public interface  PNInterface
{
	public WSResponse sendPushNotification(@WebParam(name="pnRequest")WSPNRequest request);
	
}