package com.silverlake.mleb.ccmcb.module;

import java.util.List;

import javax.jws.WebParam;
import javax.xml.ws.WebServiceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.silverlake.mleb.ccmcb.constant.WSConstant;
import com.silverlake.mleb.ccmcb.util.PropertiesManager;
import com.silverlake.mleb.ccmcb.util.SecurityUtil;
import com.silverlake.mleb.ccmcb.util.UserLoginSession;
import com.silverlake.mleb.mcb.bean.ObAuthorizationRequest;
import com.silverlake.mleb.mcb.bean.ObAuthorizationResponse;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.module.vc.transaction.Transaction;
 

public class RetrieveAuthTransaction extends MiBServices
{

	public RetrieveAuthTransaction(WebServiceContext session)
	{
		super(session);
		// TODO Auto-generated constructor stub
	}

	public static PropertiesManager property = new PropertiesManager();
	UserLoginSession userloginsession = new UserLoginSession();
	SecurityUtil secUtil = new SecurityUtil();
	private static Logger log = LogManager.getLogger(RetrieveAuthTransaction.class);
	ObAuthorizationRequest obRequest;
	//private Gson gsonLog = new GsonBuilder().setPrettyPrinting().create();

	@Override
	public ObAuthorizationRequest getBDObject()
	{
		func_id = MiBConstant.FUNC_MCB_AUTH_TRANX;
		return obRequest;
	}

	public void processData(String page_no, String pageSize, List<String> source_trx, String trx_status, String value_date_from, String value_date_to,String pymtMasterId, String uploadDateFrom, String uploadDateTo, String prodCode)
	{
		// set request to bd
		obRequest = new ObAuthorizationRequest();
		obRequest.setUserContext(userloginsession.getSessionKey(httpSession));
		obRequest.setObUser(userloginsession.getLoginSessionKeyData(httpSession));
		obRequest.setPage_no(page_no);
		obRequest.setPage_size(pageSize);
		obRequest.setSource_trx(source_trx);
		obRequest.setTrx_status(trx_status);
		obRequest.setValue_date_from(value_date_from);
		obRequest.setValue_date_to(value_date_to);
		obRequest.setPymtMasterId(pymtMasterId);
		obRequest.setUpload_date_from(uploadDateFrom);
		obRequest.setUpload_date_to(uploadDateTo);
		obRequest.setProd_cd(prodCode);
	}

	@Override
	public void processResponse()
	{
		// set attribute for account detail use index get object
		if (obResponse.getObHeader().getStatusCode()
				.equalsIgnoreCase(MiBConstant.MIB_COMMON_SUCCESS))
		{
		 
			Object auth_transaction = httpSession.getAttribute(WSConstant.MIB_AUTHORIZATION_TRANSACTION);
			if(auth_transaction==null)
			{
				httpSession.setAttribute(WSConstant.MIB_AUTHORIZATION_TRANSACTION, obResponse);
			}
			else
			{
				
				ObAuthorizationResponse ssData = (ObAuthorizationResponse) auth_transaction;
				
				ssData = addIndexData(ssData,(ObAuthorizationResponse) obResponse);
		
				
				httpSession.setAttribute(WSConstant.MIB_AUTHORIZATION_TRANSACTION,ssData);
				
			}
			
			
			
			
		}
	}
	
	
	public ObAuthorizationResponse addIndexData(ObAuthorizationResponse sessionData, ObAuthorizationResponse responseData)
	{
		
		List<Transaction> ssTranx = sessionData.getList_trx();
		List<Transaction> respTranx = responseData.getList_trx();
		
		for(Transaction tranx:respTranx)
		{
			boolean addTranx = true;
			 for(Transaction tmp:ssTranx)
			 {
				 if(tmp.getIndex().equalsIgnoreCase(tranx.getIndex()))
				 {
					 
					 
					 BeanUtils.copyProperties(tranx, tmp);
					 addTranx = false;
					 break;
				 }
			 }
			 
			 if(addTranx)
			 {
				 
				 ssTranx.add(tranx);
			 }
			
		}
		
		
		return sessionData;
	}
	
	

}
