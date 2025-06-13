package com.silverlake.mleb.mcb.module.func.accountaliases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.accountaliases.ObAccountAliasListRequest;
import com.silverlake.mleb.mcb.bean.accountaliases.ObAccountAliasListResponse;
import com.silverlake.mleb.mcb.bean.accountaliases.ObAccountAliasListSessionCache;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.module.common.CacheSessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.accountManagement.ListAccount;
import com.silverlake.mleb.mcb.module.vc.administration.AccountAliasesMaintenanceRequestData;
import com.silverlake.mleb.mcb.module.vc.administration.AccountAliasesMaintenanceResponseData;
import com.silverlake.mleb.mcb.module.vc.administration.AccountAliasesResponseData;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;

/**
 * Purpose: To get list of all accounts and their aliases name of an Organization.
 * Omni Web Services:
 * /administration/account/list
 *
 * @author Hemanth
 *
 */
@Service
public class AdministrationAccountAliasMaintenance extends CacheSessionFuncServices<ObAccountAliasListRequest, ObAccountAliasListResponse, ObAccountAliasListSessionCache> {
	private static Logger log = LogManager.getLogger(AdministrationAccountAliasMaintenance.class);
	
	
    @Override
    public void processInternalWithCache(String locale, String sessionId, String trxId, ObAccountAliasListRequest requestBean, ObAccountAliasListResponse responseBean, ObAccountAliasListSessionCache cacheBean, VCGenericService vcService) throws Exception {
    	
		AccountAliasesMaintenanceRequestData requestData = new AccountAliasesMaintenanceRequestData();
		AccountAliasesMaintenanceResponseData responseData;
		processRequest(requestData, requestBean, responseBean);
		VCResponse<AccountAliasesMaintenanceResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.ACCOUNT_ALIASES_MAINTENANCE, requestData,
				AccountAliasesMaintenanceResponseData.class, true);
		
		if (processVCResponseError(vcResponse, responseBean)) {
			return;
		}
    
    }

	private void processRequest(AccountAliasesMaintenanceRequestData requestData, ObAccountAliasListRequest requestBean, ObAccountAliasListResponse responseBean) throws Exception{
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setAction_cd(MiBConstant.UPDATE);
		requestData.setRecord_id(requestBean.getAccountId());
		requestData.setAlias_name(requestBean.getAliasName());
		
		
		ObAccountAliasListSessionCache obAccountAliasListSessionCache = requestBean.getSessionCache().get(VCMethodConstant.CACHE_IDS.MIB_HTTP_SESSION_ACCOUNT_ALIAS_LIST.getId());
		if (obAccountAliasListSessionCache.getAccountAliasesResponseData() == null) {
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			log.info("Account Aliases details cache is not found");
			return;
		}
		
		AccountAliasesResponseData accountAliasesResponseData = obAccountAliasListSessionCache.getAccountAliasesResponseData();
		
		for (ListAccount accountAliases : accountAliasesResponseData.getAccount_list()) {
			if (accountAliases.getId().equals(requestBean.getAccountId())) {
				requestData.setAccount_no(accountAliases.getAcct_no());
				requestData.setAccount_name(accountAliases.getAcct_name());
				requestData.setVersion(accountAliases.getVersion());
				break;
			}

		}
	}
	
	
}
