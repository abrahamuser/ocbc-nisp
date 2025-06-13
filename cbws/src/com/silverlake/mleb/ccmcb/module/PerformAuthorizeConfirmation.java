package com.silverlake.mleb.ccmcb.module;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.WebServiceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;

import com.silverlake.mleb.ccmcb.constant.WSConstant;
import com.silverlake.mleb.ccmcb.util.PropertiesManager;
import com.silverlake.mleb.ccmcb.util.SecurityUtil;
import com.silverlake.mleb.ccmcb.util.UserLoginSession;
import com.silverlake.mleb.mcb.bean.ObAuthorizationRequest;
import com.silverlake.mleb.mcb.bean.ObAuthorizationResponse;
import com.silverlake.mleb.mcb.bean.ObAuthorizationSessionCache;
import com.silverlake.mleb.mcb.bean.ObHeaderResponse;
import com.silverlake.mleb.mcb.bean.ObResponse;
import com.silverlake.mleb.mcb.bean.ObTaskListResponse;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.module.vc.transaction.Transaction;

public class PerformAuthorizeConfirmation extends MiBServices
{

	public PerformAuthorizeConfirmation(WebServiceContext session)
	{
		super(session);
		// TODO Auto-generated constructor stub
	}

	public static PropertiesManager property = new PropertiesManager();
	UserLoginSession userloginsession = new UserLoginSession();
	SecurityUtil secUtil = new SecurityUtil();
	private static Logger log = LogManager.getLogger(PerformAuthorizeConfirmation.class);
	ObAuthorizationRequest obRequest;

	@Override
	public ObAuthorizationRequest getBDObject()
	{
		func_id = MiBConstant.FUNC_MCB_AUTH_TRANX_CONFIRM;
		return obRequest;
	}

	public void processData(String page_no, String pageSize, List<String> source_trx, String trx_status, String value_date_from, String value_date_to,String index)
	{
		obRequest = new ObAuthorizationRequest();
		obRequest.setUserContext(userloginsession.getSessionKey(httpSession));
		obRequest.setObUser(userloginsession.getLoginSessionKeyData(httpSession));
		obRequest.setPage_no(page_no);
		obRequest.setPage_size(pageSize);
		obRequest.setSource_trx(source_trx);
		obRequest.setTrx_status(trx_status);
		obRequest.setValue_date_from(value_date_from);
		obRequest.setValue_date_to(value_date_to);
		
		boolean isNBApproval = false;
		if(source_trx != null && source_trx.size() > 0){
			for(String sourceTrx:source_trx){
				if(sourceTrx.equalsIgnoreCase("NB")){
					isNBApproval = true;
					break;
				}
			}
		}
		
		String[] indexList = index.split(",");
		
		Object ssResp = null;
		//When source_trx is NB, get the cache from web service of RetrieveTaskTransaction (retrieveTaskTranx). 
		//Other than that, get cache from web service of RetrieveAuthorizeTransaction (retrieveAuthTransaction). 
		if(isNBApproval){
			ssResp =  httpSession.getAttribute(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TRANSACTION_LIST.getId());
		}else{
			ssResp =  httpSession.getAttribute(WSConstant.MIB_AUTHORIZATION_TRANSACTION);
		}
		List<Transaction> ssTransactionList = null;
		if (ssResp instanceof ObAuthorizationResponse)
		{
			ObAuthorizationResponse ssRespTemp = (ObAuthorizationResponse) ssResp;
			ssTransactionList = ssRespTemp.getList_trx();
		}else if (ssResp instanceof ObAuthorizationSessionCache) {
			//RetrieveTaskTransaction using different cache object class
			ObAuthorizationSessionCache ssRespTemp = (ObAuthorizationSessionCache) ssResp;
			ssTransactionList = ssRespTemp.getTransactionList();
		}
		
		if(ssTransactionList!=null)
		{
			obRequest.setList_trx(new ArrayList());
			for(Transaction temp:ssTransactionList)
			{
				for(String indexValue:indexList) {
					if(temp.getIndex().equalsIgnoreCase(indexValue))
					{
						obRequest.getList_trx().add(temp);	
					}
				}
				
			}
			if (obRequest.getList_trx().size() == indexList.length) {
				return;
			}
			
		}
		
		obResponse = new ObResponse();
		obResponse.setObHeader(new ObHeaderResponse());
		obResponse.getObHeader().setStatusCode(WSConstant.CCWS_COMMON_ERROR);
		return;
 
	}

	@Override
	public void processResponse()
	{
		// set attribute for account detail use index get object
		if (obResponse.getObHeader().getStatusCode()
				.equalsIgnoreCase(MiBConstant.MIB_COMMON_SUCCESS))
		{
			//edit constant
			httpSession.setAttribute(WSConstant.MIB_AUTHORIZATION_TRANSACTION_CONFIRM, obResponse);
		}
	}

}
