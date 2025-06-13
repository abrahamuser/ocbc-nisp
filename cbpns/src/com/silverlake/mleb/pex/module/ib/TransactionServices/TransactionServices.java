package com.silverlake.mleb.pex.module.ib.TransactionServices;



import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.fuzion.ws.transaction.endpoint.AccountVO;
import com.fuzion.ws.transaction.endpoint.EndpointResponseHeader;
import com.fuzion.ws.transaction.endpoint.EndpointUserContext;
import com.fuzion.ws.transaction.endpoint.FromAccountListRequest;
import com.fuzion.ws.transaction.endpoint.FromAccountListResponse;
import com.fuzion.ws.transaction.endpoint.PexRequest;
import com.fuzion.ws.transaction.endpoint.PexUpdateRequest;
import com.fuzion.ws.transaction.endpoint.TransactionLimitRequest;
import com.fuzion.ws.transaction.endpoint.TransactionLimitResponse;
import com.fuzion.ws.transaction.endpoint.TransactionResponse;
import com.silverlake.hlb.mib.bean.ObUserContext;
import com.silverlake.mleb.pex.module.common.IBWSService;
import com.silverlake.mleb.pex.util.PropertiesManager;





public class TransactionServices extends IBWSService
{

	private static Logger log = LogManager.getLogger(TransactionServices.class);

	
	public TransactionServices (ApplicationContext appContext)
	{
		this.appContext = appContext;
	}

	private PropertiesManager pmgr = new PropertiesManager();
	
	
	//retrive account list selection 
	public FromAccountListResponse fuzionPerformFromAccountListx(ObUserContext userContext, FromAccountListRequest adRequest, String mleb_tranx_id ) throws Exception{
		String methodName = new Exception().fillInStackTrace().getStackTrace()[0].getMethodName();
		EndpointUserContext enpointUserContext = processTransactionRequestParam(userContext,adRequest,null,methodName,mleb_tranx_id);
		
		FromAccountListResponse resp = new FromAccountListResponse();
		if(null!=pmgr.getProperty("fuzion.mock") && enpointUserContext.getLoginId().toLowerCase().startsWith(pmgr.getProperty("fuzion.mock").toString())  && !pmgr.getProperty("fuzion.mock").equalsIgnoreCase("false") )	
		{
			resp.setResponse(new EndpointResponseHeader());
			resp.getResponse().setStatusCode(1);
			
			AccountVO accVo1 = new AccountVO();
			accVo1.setAccountDescription("Saving Account");
			accVo1.setAccountNumber("19835982983");
			accVo1.setAvailableBalance(new BigDecimal("20.00"));
			accVo1.setAllowTransactionFrom(true);
			accVo1.setAllowDisplay(true);
			accVo1.setCurrencyCode("USD");
			accVo1.setProductTypeCode("S");
			
			
			
			AccountVO accVo2 = new AccountVO();
			accVo2.setAccountDescription("Current Account");
			accVo2.setAccountNumber("29835982983");
			accVo2.setAvailableBalance(new BigDecimal("3000.00"));
			accVo2.setAllowTransactionFrom(true);
			accVo2.setAllowDisplay(true);
			accVo2.setCurrencyCode("USD");
			accVo2.setProductTypeCode("D");
			
			
			resp.getAccountList().add(accVo1);
			resp.getAccountList().add(accVo2);
		}
		else
		{
			TransactionWSConnection connection = new TransactionWSConnection();
			resp = connection.getServicePort().getFromAccountList(enpointUserContext, adRequest);  
			
		}
		
	
		
		processResponseParam(resp, resp.getResponse(), userContext, mleb_tranx_id);
		
		return resp; 
	}
	
	
	
	
	
	//get transaction limit
	public TransactionLimitResponse getAvailableTransactionLimit(ObUserContext userContext, TransactionLimitRequest limitRequest, String mleb_tranx_id ) throws Exception{
		String methodName = new Exception().fillInStackTrace().getStackTrace()[0].getMethodName();
		EndpointUserContext enpointUserContext = processTransactionRequestParam(userContext,limitRequest,null,methodName,mleb_tranx_id);
		TransactionLimitResponse resp = new TransactionLimitResponse();
		if(null!=pmgr.getProperty("fuzion.mock") /*&& enpointUserContext.getLoginId().startsWith(pmgr.getProperty("fuzion.mock").toString())*/  && !pmgr.getProperty("fuzion.mock").equalsIgnoreCase("false") )	
		{
			resp.setResponse(new EndpointResponseHeader());
			resp.getResponse().setStatusCode(1);
			if(enpointUserContext.getLoginId().toLowerCase().equalsIgnoreCase("hlb01"))
			{
				resp.setAvailableLimit(new BigDecimal("800.00"));
			}
			else if(enpointUserContext.getLoginId().toLowerCase().equalsIgnoreCase("hlb02"))
			{
				resp.setAvailableLimit(new BigDecimal("1000.00"));
			}
			else
			{
				resp.setAvailableLimit(new BigDecimal("500.00"));
			}
		
			
		}
		else
		{
			TransactionWSConnection connection = new TransactionWSConnection();
			resp = connection.getServicePort().getAvailableTransactionLimit(enpointUserContext, limitRequest); 
		}
		
		
		processResponseParam (resp, resp.getResponse(),userContext, mleb_tranx_id);
			
		return resp; 
	}
	
	
	
	
	
	//insert Pex Transaction
	public TransactionResponse insertIBPExTransaction(ObUserContext userContext, PexRequest PexRequest, String mleb_tranx_id ) throws Exception{
		String methodName = new Exception().fillInStackTrace().getStackTrace()[0].getMethodName();
		EndpointUserContext enpointUserContext = processTransactionRequestParam(userContext,PexRequest,null,methodName,mleb_tranx_id);
		TransactionResponse resp = new TransactionResponse();
		if(null!=pmgr.getProperty("fuzion.mock") && enpointUserContext.getLoginId().toLowerCase().startsWith(pmgr.getProperty("fuzion.mock").toString())  && !pmgr.getProperty("fuzion.mock").equalsIgnoreCase("false") )
		{
			
			resp.setResponse(new EndpointResponseHeader());
			resp.getResponse().setStatusCode(1);
		}
		else
		{
			TransactionWSConnection connection = new TransactionWSConnection();
			resp = connection.getServicePort().insertPexTransaction(enpointUserContext, PexRequest); 
		}
		
		
		processResponseParam (resp, resp.getResponse(),userContext, mleb_tranx_id);
			
		return resp; 
	}
	
	
	
	//update Pex Transaction
	public TransactionResponse updateIBPExTransaction(ObUserContext userContext, PexUpdateRequest PexRequest, String mleb_tranx_id ) throws Exception{
		String methodName = new Exception().fillInStackTrace().getStackTrace()[0].getMethodName();
		EndpointUserContext enpointUserContext = processTransactionRequestParam(userContext,PexRequest,null,methodName,mleb_tranx_id);
		TransactionResponse resp = new TransactionResponse();
		if(null!=pmgr.getProperty("fuzion.mock") && enpointUserContext.getLoginId().toLowerCase().startsWith(pmgr.getProperty("fuzion.mock").toString())  && !pmgr.getProperty("fuzion.mock").equalsIgnoreCase("false") )
		{
			
			resp.setResponse(new EndpointResponseHeader());
			resp.getResponse().setStatusCode(1);
		}
		else
		{
			TransactionWSConnection connection = new TransactionWSConnection();
			resp = connection.getServicePort().updatePexTransaction(enpointUserContext, PexRequest); 
		}
		
		
		processResponseParam (resp, resp.getResponse(),userContext, mleb_tranx_id);
			
		return resp; 
	}
	

}