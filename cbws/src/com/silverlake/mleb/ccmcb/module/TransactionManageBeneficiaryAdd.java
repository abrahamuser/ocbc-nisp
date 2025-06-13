package com.silverlake.mleb.ccmcb.module;

import javax.xml.ws.WebServiceContext;

import com.silverlake.mleb.ccmcb.module.common.CacheSessionMiBServices;
import com.silverlake.mleb.ccmcb.module.common.WSCache;
import com.silverlake.mleb.ccmcb.module.common.WSClass;
import com.silverlake.mleb.ccmcb.module.common.WSParameter;
import com.silverlake.mleb.mcb.bean.mgmtbene.ObTransactionManageBeneficiaryRequest;
import com.silverlake.mleb.mcb.bean.mgmtbene.ObTransactionManageBeneficiaryResponse;
import com.silverlake.mleb.mcb.bean.mgmtbene.ObTransactionManageBeneficiarySessionCache;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;

@WSClass(functionId=VCMethodConstant.FUNCTION_CODES.FUNC_MCB_TRANS_MGMT_BENE_ADD)
@WSCache(
		cacheId=VCMethodConstant.CACHE_IDS.EMPTY,
		previousCacheIds={VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TRANSACTION_BENEFICIARY_INQUIRY},
		isPrevCacheMandatory={false},
		clearPreviousCacheIds={},
		isSuccessClearPreviousCache={}
)
public class TransactionManageBeneficiaryAdd extends CacheSessionMiBServices<ObTransactionManageBeneficiaryRequest, ObTransactionManageBeneficiaryResponse, ObTransactionManageBeneficiarySessionCache>{
	@WSParameter(isMandatory=true)
	protected String transferServiceType;
	
	@WSParameter(isMandatory=true)
	protected String accountNo;
	
	@WSParameter(isMandatory=false)
	protected String accountName;
	
	@WSParameter(isMandatory=false)
	protected String accountCcy;
	
	@WSParameter(isMandatory=true)
	protected String nickName;
	
	@WSParameter(isMandatory=false)
	protected String email;
	
	@WSParameter(isMandatory=false)
	protected String phoneNumber;
	
	@WSParameter(isMandatory=true)
	protected String isShared;
	
	@WSParameter(isMandatory=false)
	protected String beneAddress1;
	
	@WSParameter(isMandatory=false)
	protected String beneAddress2;
	
	@WSParameter(isMandatory=false)
	protected String beneAddress3;
	
	@WSParameter(isMandatory=false)
	protected String bankCountryCode;
	
	@WSParameter(isMandatory=false)
	protected String bankName;
	
	@WSParameter(isMandatory=false)
	protected String networkCode;

	@WSParameter(isMandatory=false)
	protected String bankCode;
	
	@WSParameter(isMandatory=false)
	protected String bankBranch;
	
	@WSParameter(isMandatory=false)
	protected String rtgsMemberCode;
	
	@WSParameter(isMandatory=false)
	protected String participantBic;
	
	public TransactionManageBeneficiaryAdd(WebServiceContext wsContext) {
		super(wsContext);
	}

	@Override
	public void processData() throws Exception{
		obRequest.setTransferServiceType(transferServiceType);
		obRequest.setAccountNo(accountNo);
		obRequest.setAccountName(accountName);
		obRequest.setAccountCcy(accountCcy);
		obRequest.setNickName(nickName);
		obRequest.setEmail(email);
		obRequest.setPhoneNumber(phoneNumber);
		obRequest.setIsShared(isShared);
		obRequest.setBeneAddress1(beneAddress1);
		obRequest.setBeneAddress2(beneAddress2);
		obRequest.setBeneAddress3(beneAddress3);
		obRequest.setBankCountryCode(bankCountryCode);
		obRequest.setBankName(bankName);
		obRequest.setNetworkCode(networkCode);
		obRequest.setBankCode(bankCode);
		obRequest.setBankBranch(bankBranch);
		obRequest.setRtgsMemberCode(rtgsMemberCode);
		obRequest.setParticipantBic(participantBic);
	}

	@Override
	public void processSessionResponse() {
		
	}
}
