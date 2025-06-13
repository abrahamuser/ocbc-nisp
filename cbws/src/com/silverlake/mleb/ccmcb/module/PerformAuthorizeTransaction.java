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
import com.silverlake.mleb.mcb.bean.ObAuthenticationResponse;
import com.silverlake.mleb.mcb.bean.ObAuthorizationRequest;
import com.silverlake.mleb.mcb.bean.ObAuthorizationResponse;
import com.silverlake.mleb.mcb.bean.ObHeaderResponse;
import com.silverlake.mleb.mcb.bean.ObResponse;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.module.vc.authorization.AuthPayment;

public class PerformAuthorizeTransaction extends MiBServices
{

	public PerformAuthorizeTransaction(WebServiceContext session)
	{
		super(session);
		// TODO Auto-generated constructor stub
	}

	public static PropertiesManager property = new PropertiesManager();
	UserLoginSession userloginsession = new UserLoginSession();
	SecurityUtil secUtil = new SecurityUtil();
	private static Logger log = LogManager.getLogger(PerformAuthorizeTransaction.class);
	ObAuthorizationRequest obRequest;

	@Override
	public ObAuthorizationRequest getBDObject()
	{
		func_id = MiBConstant.FUNC_MCB_AUTHORIZE_TRANSAC;
		return obRequest;
	}

	public void processData(String idTransaction, String responseCode)
	{
		obRequest = new ObAuthorizationRequest();
		obRequest.setUserContext(userloginsession.getSessionKey(httpSession));
		obRequest.setObUser(userloginsession.getLoginSessionKeyData(httpSession));
		obRequest.setIdTransaction(idTransaction);
		obRequest.setResponseCode(responseCode);
		
		Object ssResp =  httpSession.getAttribute(WSConstant.MIB_PRE_AUTHORIZATION_TRANSACTION);
		Object ssRespTac =  httpSession.getAttribute(WSConstant.MIB_RESEND_TAG_AUTH_TAC);
		List<AuthPayment> ssTransactionList = null;
		String ssTACrequestId = null;
		
		ObAuthorizationResponse ssRespTemp = (ObAuthorizationResponse) ssResp;
		ObAuthenticationResponse ssRespTempTac = (ObAuthenticationResponse) ssRespTac;
		if (ssResp instanceof ObAuthorizationResponse)
		{
			ssTransactionList = ssRespTemp.getList_payment();
		}
		
		if (ssRespTac instanceof ObAuthenticationResponse)
		{
			ssTACrequestId = ssRespTempTac.getTokenReqId();
		}
		
		if(ssTransactionList!=null)
		{
			obRequest.setList_payment(new ArrayList());
			if(ssRespTemp.getIdTransaction().equalsIgnoreCase(obRequest.getIdTransaction()))
			{
				obRequest.setList_payment(ssTransactionList);
				obRequest.setRequestId(ssTACrequestId);
			}
			else {
				obResponse = new ObResponse();
				obResponse.setObHeader(new ObHeaderResponse());
				obResponse.getObHeader().setStatusCode(WSConstant.CCWS_COMMON_ERROR);
				return;
			}
			return;
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
		if (obResponse.getObHeader().getStatusCode()
				.equalsIgnoreCase(MiBConstant.MIB_COMMON_SUCCESS))
		{
			//edit constant
			httpSession.setAttribute(WSConstant.MIB_PERFORM_AUTHORIZATION_TRANSACTION, obResponse);
		}
	}

}
