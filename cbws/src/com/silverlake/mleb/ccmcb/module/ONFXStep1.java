package com.silverlake.mleb.ccmcb.module;

import javax.xml.ws.WebServiceContext;

import com.silverlake.mleb.ccmcb.module.common.CacheSessionMiBServices;
import com.silverlake.mleb.ccmcb.module.common.WSCache;
import com.silverlake.mleb.ccmcb.module.common.WSClass;
import com.silverlake.mleb.mcb.bean.ObForexRequest;
import com.silverlake.mleb.mcb.bean.ObForexResponse;
import com.silverlake.mleb.mcb.bean.ObForexSessionCache;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;

@WSClass(functionId=VCMethodConstant.FUNCTION_CODES.FUNC_MCB_ONFX_STEP1)
@WSCache(
		cacheId=VCMethodConstant.CACHE_IDS.EMPTY,
		clearPreviousCacheIds={},
		isSuccessClearPreviousCache={}
)
public class ONFXStep1 extends CacheSessionMiBServices<ObForexRequest, ObForexResponse, ObForexSessionCache>{
	public ONFXStep1(WebServiceContext wsContext) {
		super(wsContext);
	}

	@Override
	public void processData() throws Exception{
		
	}

	@Override
	public void processSessionResponse() {
		
	}
}
