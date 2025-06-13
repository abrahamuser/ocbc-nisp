package com.silverlake.mleb.ccmcb.module;

import javax.xml.ws.WebServiceContext;

import com.silverlake.mleb.ccmcb.module.common.CacheSessionMiBServices;
import com.silverlake.mleb.ccmcb.module.common.WSCache;
import com.silverlake.mleb.ccmcb.module.common.WSClass;
import com.silverlake.mleb.ccmcb.module.common.WSParameter;
import com.silverlake.mleb.mcb.bean.ObTransactionBeneficiaryRequest;
import com.silverlake.mleb.mcb.bean.ObTransactionBeneficiaryResponse;
import com.silverlake.mleb.mcb.bean.ObTransactionBeneficiarySessionCache;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;

@WSClass(functionId=VCMethodConstant.FUNCTION_CODES.FUNC_MCB_TRANS_BENEFICIARY_INQUIRY)
@WSCache(
		cacheId=VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TRANSACTION_BENEFICIARY_INQUIRY,
		previousCacheIds={},
		isPrevCacheMandatory={},
		clearPreviousCacheIds={},
		isSuccessClearPreviousCache={}
)
public class TransactionBeneficiaryInquiry extends CacheSessionMiBServices<ObTransactionBeneficiaryRequest, ObTransactionBeneficiaryResponse, ObTransactionBeneficiarySessionCache>{
	@WSParameter(isMandatory=true)
	protected String transferServiceType;
	
	@WSParameter(isMandatory=false)
	protected String accountNo;
	
	@WSParameter(isMandatory=false)
	protected String accountCcy;
	
	@WSParameter(isMandatory=false)
	protected String debitAccountNo;
	
	@WSParameter(isMandatory=false)
	protected String bankCode;
	
	@WSParameter(isMandatory=false)
	protected String bankName;
	
	@WSParameter(isMandatory=false)
	protected Boolean isInquiry;
	
	@WSParameter(isMandatory=false)
	protected String proxy_data;
	
	@WSParameter(isMandatory=false)
	protected String proxy_type;
	
	public TransactionBeneficiaryInquiry(WebServiceContext wsContext) {
		super(wsContext);
	}

	@Override
	public void processData() throws Exception{
		obRequest.setTransferServiceType(transferServiceType);
		obRequest.setAccountNo(accountNo);
		obRequest.setAccountCcy(accountCcy);
		obRequest.setDebitAccountNo(debitAccountNo);
		obRequest.setBankCode(bankCode);
		obRequest.setBankName(bankName);
		obRequest.setIsInquiry(isInquiry);
		obRequest.setProxy_data(proxy_data);
		obRequest.setProxy_type(proxy_type);
	}

	@Override
	public void processSessionResponse() {
		
	}
}
