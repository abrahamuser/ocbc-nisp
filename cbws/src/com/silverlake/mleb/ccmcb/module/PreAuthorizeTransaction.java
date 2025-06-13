package com.silverlake.mleb.ccmcb.module;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.WebServiceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.silverlake.mleb.ccmcb.constant.WSConstant;
import com.silverlake.mleb.ccmcb.util.PropertiesManager;
import com.silverlake.mleb.ccmcb.util.SecurityUtil;
import com.silverlake.mleb.ccmcb.util.UserLoginSession;
import com.silverlake.mleb.mcb.bean.ObAuthorizationRequest;
import com.silverlake.mleb.mcb.bean.ObAuthorizationResponse;
import com.silverlake.mleb.mcb.bean.ObAuthorizationSessionCache;
import com.silverlake.mleb.mcb.bean.ObHeaderResponse;
import com.silverlake.mleb.mcb.bean.ObResponse;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.module.vc.transaction.Transaction;

public class PreAuthorizeTransaction extends MiBServices
{

	public PreAuthorizeTransaction(WebServiceContext session)
	{
		super(session);
		// TODO Auto-generated constructor stub
	}

	public static PropertiesManager property = new PropertiesManager();
	UserLoginSession userloginsession = new UserLoginSession();
	SecurityUtil secUtil = new SecurityUtil();
	private static Logger log = LogManager.getLogger(PreAuthorizeTransaction.class);
	ObAuthorizationRequest obRequest;

	@Override
	public ObAuthorizationRequest getBDObject()
	{
		func_id = MiBConstant.FUNC_MCB_PRE_AUTHORIZE_TRANSAC;
		return obRequest;
	}

	public void processData(String idTransaction, String action_cd, String index, String sourceTrx, Boolean isParentLevel)
	{
		obRequest = new ObAuthorizationRequest();
		obRequest.setUserContext(userloginsession.getSessionKey(httpSession));
		obRequest.setObUser(userloginsession.getLoginSessionKeyData(httpSession));
		obRequest.setIdTransaction(idTransaction);
		obRequest.setAction_cd(action_cd);
		String[] indexList = index.split(",");
		
		/*
		 * The cache object need to depend on action code where
		 * -- A - Approve
		 * Take cache response from performAuthConfirmation
		 * -- R/V/X - Reject/Verify/Revert
		 * Take cache response from retrieveAuthTransaction due to these action not calling performAuthConfirmation prior to this ws
		 * Exception on source trx of NB when action taken from file level,  change to take cache response from retrieveTaskTranx 
		 */
		Object ssResp = null;
		if (obRequest.getAction_cd().equalsIgnoreCase("A")) {
			ssResp =  httpSession.getAttribute(WSConstant.MIB_AUTHORIZATION_TRANSACTION_CONFIRM);
		} else if (obRequest.getAction_cd().equalsIgnoreCase("R") || obRequest.getAction_cd().equalsIgnoreCase("V") || obRequest.getAction_cd().equalsIgnoreCase("X")) {
			if(sourceTrx != null && sourceTrx.equalsIgnoreCase("NB") && isParentLevel != null && isParentLevel){
				ssResp =  httpSession.getAttribute(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TRANSACTION_LIST.getId());
			}else{
				ssResp =  httpSession.getAttribute(WSConstant.MIB_AUTHORIZATION_TRANSACTION);
			}
		}
		List<Transaction> ssTransactionList = null;
		String cacheIdTransaction = null;
		if (ssResp instanceof ObAuthorizationResponse)
		{
			ObAuthorizationResponse ssRespTemp = (ObAuthorizationResponse) ssResp;
			ssTransactionList = ssRespTemp.getList_trx();
			cacheIdTransaction = ssRespTemp.getIdTransaction();
		}else if (ssResp instanceof ObAuthorizationSessionCache) {
			//RetrieveTaskTransaction using different object class
			ObAuthorizationSessionCache ssTaskRespTemp = (ObAuthorizationSessionCache) ssResp;
			ssTransactionList = ssTaskRespTemp.getTransactionList();
		}
		else {
		
			obResponse = new ObResponse();
			obResponse.setObHeader(new ObHeaderResponse());
			obResponse.getObHeader().setStatusCode(WSConstant.CCWS_COMMON_ERROR);
			return;
		}
		
		if(ssTransactionList!=null)
		{
			obRequest.setList_trx(new ArrayList());
			if (obRequest.getAction_cd().equalsIgnoreCase("A")) {
				if(cacheIdTransaction != null && cacheIdTransaction.equalsIgnoreCase(obRequest.getIdTransaction()))
				{
					obRequest.setList_trx(ssTransactionList);	
				}
			} else if (obRequest.getAction_cd().equalsIgnoreCase("R") || obRequest.getAction_cd().equalsIgnoreCase("V") || obRequest.getAction_cd().equalsIgnoreCase("X")) {
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
		}
		else {
		
			obResponse = new ObResponse();
			obResponse.setObHeader(new ObHeaderResponse());
			obResponse.getObHeader().setStatusCode(WSConstant.CCWS_COMMON_ERROR);
			return;
		}
 
	}

	@Override
	public void processResponse()
	{
		// set attribute for account detail use index get object
		if (obResponse.getObHeader().getStatusCode().equalsIgnoreCase(MiBConstant.MIB_COMMON_SUCCESS) || 
				obResponse.getObHeader().getStatusCode().equalsIgnoreCase(MiBConstant.MCB_BACKDATEDLIST_EMPTY))
		{
			//edit constant
			httpSession.setAttribute(WSConstant.MIB_PRE_AUTHORIZATION_TRANSACTION, obResponse);
		}
	}

}
