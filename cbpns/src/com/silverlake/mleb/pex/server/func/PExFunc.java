package com.silverlake.mleb.pex.server.func;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.apache.cxf.phase.PhaseInterceptorChain;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.silverlake.mleb.pex.constant.PExConstant;
import com.silverlake.mleb.pex.entity.WSPExLog;
import com.silverlake.mleb.pex.entity.dao.MLEBPExDAO;
import com.silverlake.mleb.pex.server.webservice.PExWSInInterceptor;
import com.silverlake.mleb.pex.server.webservice.bean.WSHeaderRequest;
import com.silverlake.mleb.pex.server.webservice.bean.WSHeaderResponse;
import com.silverlake.mleb.pex.server.webservice.bean.WSPExResponse;
import com.silverlake.mleb.pex.server.webservice.bean.WSRequest;
import com.silverlake.mleb.pex.server.webservice.bean.WSResponse;
import com.silverlake.mleb.pex.util.MessageManager;
import com.silverlake.mleb.pex.util.PropertiesManager;
import com.silverlake.mleb.pex.util.StringDataUtil;



public abstract class PExFunc {

	private PropertiesManager property = new PropertiesManager();
	private MessageManager msgPro = new MessageManager();
	private static Logger log = LogManager.getLogger(PExFunc.class);
	private static String filterNames[] = {"collectionCode"};
	protected HttpSession httpSession;
	protected WebServiceContext wsContext;
	private static final String version = "1.0";
	
	@Autowired MLEBPExDAO dao;
	
	
	public WSResponse tcpProcess(WSRequest wsRequest)
	{
		WSResponse wsResponse = new WSResponse();
		try
		{
			Gson gsonLog = new GsonBuilder().setPrettyPrinting().create();
			StringDataUtil dataUtil = new StringDataUtil();
			Date wsRequestDateTime = new Date();
			String genID = "";
			
			log.debug(this.getClass().getSimpleName()+" :- TCP[REQUEST]["+genID+"] \n "+dataUtil.filterGson(gsonLog.toJson(wsRequest),filterNames));
			if(null== wsRequest.getObHeader() || !PExConstant.PEX_COLLECTION_CHANNEL_OCM.equalsIgnoreCase(wsRequest.getObHeader().getChannelId()) ||  !PExConstant.PEX_COLLECTION_VERSION_OCM.equalsIgnoreCase(wsRequest.getObHeader().getVersion()))
			{
				wsResponse = new WSResponse();
				wsResponse.setObHeader(new WSHeaderResponse());
				wsResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_INVALID_WS_REQUEST_HEADER);
			}
			else
			{
				wsResponse = processService(wsRequest);
			}
			
			
			log.info(this.getClass().getSimpleName()+" :- TCP[RESPONSE]["+genID+"] \n "+dataUtil.filterGson(gsonLog.toJson(wsResponse),filterNames));
			
			
			
			
		}catch (Exception e){
			log.info("PEx ERROR :"+e.getMessage(),e);
			wsResponse = new WSResponse();
			wsResponse.setObHeader(new WSHeaderResponse());
			wsResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COMMON_ERROR);
		}
		
		return  wsResponse;
	}
	
	
	
	
	
	public  WSResponse process(WebServiceContext wsContext , WSRequest wsRequest)
	{
		WSResponse wsResponse;
		try
		{
			Gson gsonLog = new GsonBuilder().setPrettyPrinting().create();
			StringDataUtil dataUtil = new StringDataUtil();
			Date wsRequestDateTime = new Date();
			
			if(null!=wsContext)
			{
				this.wsContext = wsContext;
				MessageContext msgCtxt = wsContext.getMessageContext();
				HttpServletRequest httpRequest = (HttpServletRequest)msgCtxt.get(MessageContext.SERVLET_REQUEST);
				HttpSession session = httpRequest.getSession();
				httpSession = session;
			}
			
			
			String genIDx = (String) PhaseInterceptorChain.getCurrentMessage().getExchange().get(PExWSInInterceptor.exchange_id_key);
			String genID = wsRequest.getObHeader().getId();
			
			if(genID == null)
			{
				genID = genIDx;
			}
			
			String wsMethod = (String) PhaseInterceptorChain.getCurrentMessage().getExchange().get(PExWSInInterceptor.exchange_id_METHOD);
			
			
			if(null==wsRequest.getObHeader())
				wsRequest.setObHeader(new WSHeaderRequest());
			
			//wsRequest.getObHeader().setId(genID);

			log.info(this.getClass().getSimpleName()+" :- WS[REQUEST]["+genID+"] \n "+dataUtil.filterGson(gsonLog.toJson(wsRequest),filterNames));
			
			//do checking heading
			//channel id & version
			
			if(null== wsRequest.getObHeader() || !wsRequest.getObHeader().getChannelId().equalsIgnoreCase(PExConstant.PEX_COLLECTION_CHANNEL_FUZION) ||  !wsRequest.getObHeader().getVersion().equalsIgnoreCase(PExConstant.PEX_COLLECTION_VERSION_FUZION))
			{
				wsResponse = new WSResponse();
				wsResponse.setObHeader(new WSHeaderResponse());
				wsResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_INVALID_WS_REQUEST_HEADER);
			}
			else 
			{
			
			
			
			
				wsResponse = processService(wsRequest);
				//wsResponse.getObHeader().setId(genID);
				
				Date wsResponseDateTime = new Date();
				WSPExLog pexLog = new WSPExLog();
				pexLog.setService(wsMethod);
				//pexLog.setRequestId(genID);
				
				if(wsResponse instanceof WSPExResponse)
				{
					WSPExResponse wsxResponse = (WSPExResponse) wsResponse;
					pexLog.setErrorCode(wsxResponse.getTransactionStatus());
					pexLog.setErrorDescription(wsxResponse.getReasonFailure());
					pexLog.setRefNo(wsxResponse.getPexReferenceNo());
				}
				
				
				pexLog.setDescription("".getBytes());
				
				pexLog.setRequestDateTime(wsRequestDateTime);
				pexLog.setProcessTime(Integer.parseInt(String.valueOf((wsResponseDateTime.getTime()-wsRequestDateTime.getTime()))));
				pexLog.setStatusCode(wsResponse.getObHeader().getStatusCode());
				//dao.insertEntity(pexLog);
			}
			log.info(this.getClass().getSimpleName()+" :- WS[RESPONSE]["+genID+"] \n "+dataUtil.filterGson(gsonLog.toJson(wsResponse),filterNames));
			
		}
		catch(Exception ex)
		{
			log.info("PEx ERROR :"+ex.getMessage(),ex);
			wsResponse = new WSResponse();
			wsResponse.setObHeader(new WSHeaderResponse());
			wsResponse.getObHeader().setStatusCode(PExConstant.PEX_ERR_COMMON_ERROR);
		}
		
		
		return loadMsg(wsResponse);
	}
	
	
	public abstract WSResponse processService(WSRequest wsRequest);
	

	public WSResponse loadMsg(WSResponse obRsp)
	{
		/*String statusCode = obRsp.getObHeader().getStatusCode();
		String message = msgPro.getProperty(statusCode,"en");
		
		if(null==message)
		{
			obRsp.getObHeader().setStatusMessage(statusCode);
		}
		else
		{
			obRsp.getObHeader().setStatusMessage(message);
		}*/
		
		
		return obRsp;
	}
	
	
	
}
