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

@WSClass(functionId=VCMethodConstant.FUNCTION_CODES.FUNC_MCB_TNC_V2)
@WSCache(cacheId=VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_TNC_V2)
public class RetrieveTnCv2 extends CacheSessionMiBServices<ObTNCRequest, ObTNCResponse, ObTNCCache>{
	@WSParameter(isMandatory=true)
	protected String tncType;
	
	@WSParameter(isMandatory=false)
	protected Boolean isLocal;
	
	@WSParameter(isMandatory=false)
	protected String ccyCode;
	
	public RetrieveTnCv2(WebServiceContext wsContext) {
		super(wsContext);
	}

	@Override
	public void processData() throws Exception{
		obRequest.setTncType(tncType);
		obRequest.setIsLocal(isLocal);
		obRequest.setCcyCode(ccyCode);
	}

	@Override
	public void processSessionResponse() {
		
	}
}
