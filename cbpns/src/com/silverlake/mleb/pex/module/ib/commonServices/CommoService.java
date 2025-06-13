package com.silverlake.mleb.pex.module.ib.commonServices;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.fuzion.ws.common.endpoint.CutOffTimeResponse;
import com.fuzion.ws.common.endpoint.EndpointResponseHeader;
import com.fuzion.ws.common.endpoint.EndpointUserContext;
import com.fuzion.ws.common.endpoint.TncRequest;
import com.fuzion.ws.common.endpoint.TncResponse;
import com.fuzion.ws.common.endpoint.Tncvo;
import com.silverlake.hlb.mib.bean.ObUserContext;
import com.silverlake.mleb.pex.module.common.IBWSService;
import com.silverlake.mleb.pex.util.PropertiesManager;

public class CommoService extends IBWSService
{

	private static Logger log = LogManager.getLogger(CommoService.class);
	private PropertiesManager pmgr = new PropertiesManager();
	public CommoService (ApplicationContext appContext)
	{
		this.appContext = appContext;
	}
	
	public CutOffTimeResponse getCutOffTime(ObUserContext userContext,String mleb_tranx_id ) throws Exception
	{	
		String methodName = new Exception().fillInStackTrace().getStackTrace()[0].getMethodName();
		EndpointUserContext enpointUserContext = processCommonRequestParam(userContext,null,null,methodName,mleb_tranx_id);
		CutOffTimeResponse resp = new CutOffTimeResponse();
		if(null!=pmgr.getProperty("fuzion.mock") /*&& enpointUserContext.getLoginId().startsWith(pmgr.getProperty("fuzion.mock").toString())*/  && !pmgr.getProperty("fuzion.mock").equalsIgnoreCase("false")  )
		{
			resp.setResponse(new EndpointResponseHeader());
			resp.getResponse().setStatusCode(1);
			resp.setNmodeEndTime("0100");
			resp.setNmodeStartTime("0400");
			
		}
		else
		{

			WSCommonConnection connection = new WSCommonConnection();

		
			resp = connection.getServicePort().getCutOffTime(enpointUserContext);
		}
		
		
		processResponseParam(resp, resp.getResponse(),userContext, mleb_tranx_id);
		return resp;
	}
	
	
	public TncResponse getTNC(ObUserContext userContext, TncRequest tncRequest,String mleb_tranx_id ) throws Exception
	{	
		String methodName = new Exception().fillInStackTrace().getStackTrace()[0].getMethodName();
		EndpointUserContext enpointUserContext = processCommonRequestParam(userContext,null,null,methodName,mleb_tranx_id);
		
		TncResponse resp = new TncResponse();
		if(pmgr.getProperty("fuzion.mock").toString().equalsIgnoreCase("true") || (enpointUserContext.getLoginId().toLowerCase().startsWith(pmgr.getProperty("fuzion.mock").toString()) && !pmgr.getProperty("fuzion.mock").equalsIgnoreCase("false")) )
		{
			resp.setResponse(new EndpointResponseHeader());
			resp.getResponse().setStatusCode(1);
			resp.setTncVO(new Tncvo());
			resp.getTncVO().setTncContent("tnc content");
		}
		else
		{
			WSCommonConnection connection = new WSCommonConnection();
			resp = connection.getServicePort().getTnc(enpointUserContext, tncRequest);
		}
		
		
		processResponseParam(resp, resp.getResponse(),userContext, mleb_tranx_id);
		return resp;
	}


	public TncResponse fuzionPerformTNC(ObUserContext userContext, TncRequest tdRequest, String mleb_tranx_id ) throws Exception{
		
		String methodName = new Exception().fillInStackTrace().getStackTrace()[0].getMethodName();
		EndpointUserContext enpointUserContext = processCommonRequestParam(userContext,null,null,methodName,mleb_tranx_id);
		TncResponse resp = new TncResponse();
		if(pmgr.getProperty("fuzion.mock").toString().equalsIgnoreCase("true") || (userContext.getSessionId().startsWith(pmgr.getProperty("fuzion.mock")+"-") && !pmgr.getProperty("fuzion.mock").equalsIgnoreCase("false")) )
		{
			resp  = new TncResponse();
			resp.setResponse(new EndpointResponseHeader());
			resp.getResponse().setStatusCode(1);	
			resp.setTncVO(new Tncvo());
			resp.getTncVO().setTncContent("Terms and Conditions for Hong Leong Connect For Personal Digital Banking Services\n"+
					"Your use and access to the Hong Leong Connect shall be subject to the following Terms and Conditions. ");
		}
		else
		{
			WSCommonConnection connection = new WSCommonConnection();
			
			resp = connection.getServicePort().getTnc(enpointUserContext, tdRequest); 
			
			
			
		}
		
		processResponseParam(resp, resp.getResponse(),userContext, mleb_tranx_id);	
		return resp; 
	}
}