package com.silverlake.mleb.ccmcb.module;

import javax.xml.ws.WebServiceContext;

import com.silverlake.mleb.ccmcb.module.common.CacheSessionMiBServices;
import com.silverlake.mleb.ccmcb.module.common.WSCache;
import com.silverlake.mleb.ccmcb.module.common.WSClass;
import com.silverlake.mleb.ccmcb.module.common.WSParameter;
import com.silverlake.mleb.mcb.bean.ObTNCCache;
import com.silverlake.mleb.mcb.bean.ObTNCRequest;
import com.silverlake.mleb.mcb.bean.ObTNCResponse;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;

@WSClass(functionId=VCMethodConstant.FUNCTION_CODES.FUNC_MCB_TNC_GET)
@WSCache(cacheId=VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TNC_GET)
public class RetrieveTnC extends CacheSessionMiBServices<ObTNCRequest, ObTNCResponse, ObTNCCache>{
	@WSParameter(isMandatory=true)
	protected String tncType;
	
	@WSParameter(isMandatory=false)
	protected Boolean isLocal;
	
	public RetrieveTnC(WebServiceContext wsContext) {
		super(wsContext);
	}

	@Override
	public void processData() throws Exception{
		obRequest.setTncType(tncType);
		obRequest.setIsLocal(isLocal);
	}

	@Override
	public void processSessionResponse() {
		
	}
}
