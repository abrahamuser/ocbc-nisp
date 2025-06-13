package com.silverlake.mleb.ccmcb.module;

import javax.xml.ws.WebServiceContext;

import com.silverlake.mleb.ccmcb.module.common.CacheSessionMiBServices;
import com.silverlake.mleb.ccmcb.module.common.WSCache;
import com.silverlake.mleb.ccmcb.module.common.WSClass;
import com.silverlake.mleb.ccmcb.module.common.WSParameter;
import com.silverlake.mleb.mcb.bean.administration.ObAdminBeneficiaryListRequest;
import com.silverlake.mleb.mcb.bean.administration.ObAdminBeneficiaryListResponse;
import com.silverlake.mleb.mcb.bean.administration.ObAdminBeneficiaryListSessionCache;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;

@WSClass(functionId= VCMethodConstant.FUNCTION_CODES.FUNC_MCB_ADM_BENE_LIST)
@WSCache(
		cacheId=VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_ADMINISTRATION_BENEFICIARIES,
		previousCacheIds={},
		isPrevCacheMandatory={},
		clearPreviousCacheIds={},
		isSuccessClearPreviousCache={}
)
public class AdministrationBeneficiaryList extends CacheSessionMiBServices<ObAdminBeneficiaryListRequest, ObAdminBeneficiaryListResponse, ObAdminBeneficiaryListSessionCache>{

	@WSParameter(isMandatory=true)
	protected String productCode;

    public AdministrationBeneficiaryList(WebServiceContext wsContext) {
        super(wsContext);
    }

    @Override
    protected void processData() throws Exception {
        obRequest.setTransferServiceType(productCode);
    }

    @Override
    protected void processSessionResponse() {

    }
}
