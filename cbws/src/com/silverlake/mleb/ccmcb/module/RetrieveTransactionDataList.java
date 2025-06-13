package com.silverlake.mleb.ccmcb.module;

import java.util.List;

import javax.xml.ws.WebServiceContext;

import com.silverlake.mleb.ccmcb.module.common.CacheSessionMiBServices;
import com.silverlake.mleb.ccmcb.module.common.WSCache;
import com.silverlake.mleb.ccmcb.module.common.WSClass;
import com.silverlake.mleb.ccmcb.module.common.WSParameter;
import com.silverlake.mleb.mcb.bean.ObAuthorizationRequest;
import com.silverlake.mleb.mcb.bean.ObAuthorizationResponse;
import com.silverlake.mleb.mcb.bean.ObAuthorizationSessionCache;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;

@WSClass(functionId=VCMethodConstant.FUNCTION_CODES.FUNC_MCB_TRANS_DATA_LIST)
@WSCache(
		cacheId=VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TRANSACTION_DATA_LIST,
		previousCacheIds={VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TRANSACTION_DATA_LIST},
		isPrevCacheMandatory={false},
		clearPreviousCacheIds={},
		isSuccessClearPreviousCache={}
)
public class RetrieveTransactionDataList extends CacheSessionMiBServices<ObAuthorizationRequest, ObAuthorizationResponse, ObAuthorizationSessionCache>{
	@WSParameter(isMandatory=true)
	protected List<String> source_trx;
	
	@WSParameter(isMandatory=true)
	protected String pymtMasterId;
	
	@WSParameter(isMandatory=false)
	protected String trx_status;
	
	@WSParameter(isMandatory=false)
	protected String value_date_from;
	
	@WSParameter(isMandatory=false)
	protected String value_date_to;
	
	@WSParameter(isMandatory=false)
	protected String pageSize;
	
	@WSParameter(isMandatory=false)
	protected String page_no;
	
	@WSParameter(isMandatory=false)
	protected List<String> prodCode;
	
	public RetrieveTransactionDataList(WebServiceContext wsContext) {
		super(wsContext);
	}

	@Override
	public void processData() throws Exception{
		obRequest.setSource_trx(source_trx);
		obRequest.setProdCodeList(prodCode);
		obRequest.setPymtMasterId(pymtMasterId);
		obRequest.setTrx_status(trx_status);
		obRequest.setValue_date_from(value_date_from);
		obRequest.setValue_date_to(value_date_to);
		obRequest.setPage_size(pageSize);
		obRequest.setPage_no(page_no);
	}

	@Override
	public void processSessionResponse() {
		
	}
}
