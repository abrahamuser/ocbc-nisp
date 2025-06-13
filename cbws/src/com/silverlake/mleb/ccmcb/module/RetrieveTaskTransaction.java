package com.silverlake.mleb.ccmcb.module;



import java.util.List;

import javax.xml.ws.WebServiceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;

import com.silverlake.mleb.ccmcb.constant.WSConstant;
import com.silverlake.mleb.ccmcb.util.PropertiesManager;
import com.silverlake.mleb.ccmcb.util.SecurityUtil;
import com.silverlake.mleb.ccmcb.util.UserLoginSession;
import com.silverlake.mleb.mcb.bean.ObAuthorizationSessionCache;
import com.silverlake.mleb.mcb.bean.ObHeaderResponse;
import com.silverlake.mleb.mcb.bean.ObRequest;
import com.silverlake.mleb.mcb.bean.ObResponse;
import com.silverlake.mleb.mcb.bean.ObTaskListRequest;
import com.silverlake.mleb.mcb.bean.ObTaskListResponse;
import com.silverlake.mleb.mcb.bean.ObUserContext;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.module.vc.transaction.Transaction;



public class RetrieveTaskTransaction extends MiBServices
{

	
	
	public RetrieveTaskTransaction(WebServiceContext session) {
		super(session);
		// TODO Auto-generated constructor stub
	}


	public static PropertiesManager property = new PropertiesManager();
	UserLoginSession userloginsession = new UserLoginSession();
	SecurityUtil secUtil = new SecurityUtil();
	private static Logger log = LogManager.getLogger(RetrieveTaskTransaction.class);
	ObTaskListRequest obRequest;
	private String loginID;
	
	@Override
	public ObRequest getBDObject() {
		func_id = MiBConstant.FUNC_MCB_RETRIEVE_TASK_TRANX;
		return obRequest;
	}
	
	
	
	public void processData(String trnxSource, String status, String prdCodeList, String pageNo, String pageSize, String valueDateFrom, String valueDateTo, String sourceFileName, String uploadDateFrom, String uploadDateTo, String module)
	{
		obRequest = new ObTaskListRequest();
		ObUserContext userContext = userloginsession.getSessionKey(httpSession);
		userContext = userContext == null ? new ObUserContext(): userContext;
		obRequest.setUserContext(userContext);
		obRequest.setObUser(userloginsession.getLoginSessionKeyData(httpSession));
		obRequest.setTranxSourcelist(trnxSource);
		obRequest.setPageNo(pageNo);
		obRequest.setStatus(status);
		obRequest.setPageSize(pageSize);
		obRequest.setProdCodeList(prdCodeList);
		obRequest.setValueDateFrom(valueDateFrom);
		obRequest.setValueDateTo(valueDateTo);
		obRequest.setSourceFileName(sourceFileName);
		obRequest.setUploadDateFrom(uploadDateFrom);
		obRequest.setUploadDateTo(uploadDateTo);
		obRequest.setModule(module);
	}



	@Override
	public void processResponse() {
		//Put the list into the cache
		if(obResponse.getObHeader().getStatusCode().equalsIgnoreCase(MiBConstant.MIB_COMMON_SUCCESS)){
			if(obResponse instanceof ObTaskListResponse){
				Object ssDataObject = httpSession.getAttribute(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TRANSACTION_LIST.getId());
				if(ssDataObject==null){
					//Empty cache, set the whole object directly.
					ObAuthorizationSessionCache sessionCache = new ObAuthorizationSessionCache();
					sessionCache.setTaskListRequestBean(obRequest);
					sessionCache.setTransactionList(((ObTaskListResponse)obResponse).getTaskTransactionList());
					httpSession.setAttribute(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TRANSACTION_LIST.getId(), sessionCache);
				} else {
					//To support pagination, when changing page, append the session cache instead of replacing.
					ObAuthorizationSessionCache ssData = (ObAuthorizationSessionCache) ssDataObject;
					boolean isSameFilterRemained = true;
					if(!obRequest.getTranxSourcelist().equals(ssData.getTaskListRequestBean().getTranxSourcelist())){
						isSameFilterRemained = false;
					}
					if(obRequest.getValueDateFrom() == null){
						if(ssData.getTaskListRequestBean().getValueDateFrom() != null){
							isSameFilterRemained = false;
						}
					}else {
						if(!obRequest.getValueDateFrom().equals(ssData.getTaskListRequestBean().getValueDateFrom())){
							isSameFilterRemained = false;
						}
					}
					if(obRequest.getValueDateTo() == null){
						if(ssData.getTaskListRequestBean().getValueDateTo() != null){
							isSameFilterRemained = false;
						}
					}else {
						if(!obRequest.getValueDateTo().equals(ssData.getTaskListRequestBean().getValueDateTo())){
							isSameFilterRemained = false;
						}
					}
					if(obRequest.getStatus() == null){
						if(ssData.getTaskListRequestBean().getStatus() != null){
							isSameFilterRemained = false;
						}
					}else {
						if(!obRequest.getStatus().equals(ssData.getTaskListRequestBean().getStatus())){
							isSameFilterRemained = false;
						}
					}
					if(obRequest.getProdCodeList() == null){
						if(ssData.getTaskListRequestBean().getProdCodeList() != null){
							isSameFilterRemained = false;
						}
					}else {
						if(!obRequest.getProdCodeList().equals(ssData.getTaskListRequestBean().getProdCodeList())){
							isSameFilterRemained = false;
						}
					}
					if(isSameFilterRemained){
						addIndexData(ssData.getTransactionList(),((ObTaskListResponse)obResponse).getTaskTransactionList());
						ssData.setTaskListRequestBean(obRequest);
						httpSession.setAttribute(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TRANSACTION_LIST.getId(),ssData);
					}else{
						ObAuthorizationSessionCache sessionCache = new ObAuthorizationSessionCache();
						sessionCache.setTaskListRequestBean(obRequest);
						sessionCache.setTransactionList(((ObTaskListResponse)obResponse).getTaskTransactionList());
						httpSession.setAttribute(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TRANSACTION_LIST.getId(), sessionCache);
					}
				}
			}
		}else {
			httpSession.removeAttribute(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TRANSACTION_LIST.getId());//Remove self cache once failed 
		}
	}
	
	public void addIndexData(List<Transaction> ssTranx, List<Transaction> respTranx)
	{
		
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
	}
	
}
