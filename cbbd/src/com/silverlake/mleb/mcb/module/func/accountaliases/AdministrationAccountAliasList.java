package com.silverlake.mleb.mcb.module.func.accountaliases;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.ObAccountBean;
import com.silverlake.mleb.mcb.bean.accountaliases.ObAccountAliasListRequest;
import com.silverlake.mleb.mcb.bean.accountaliases.ObAccountAliasListResponse;
import com.silverlake.mleb.mcb.bean.accountaliases.ObAccountAliasListSessionCache;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.entity.dao.GeneralCodeDAO;
import com.silverlake.mleb.mcb.module.common.CacheSessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.accountManagement.ListAccount;
import com.silverlake.mleb.mcb.module.vc.administration.AccountAliasesRequestData;
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
public class AdministrationAccountAliasList extends CacheSessionFuncServices<ObAccountAliasListRequest, ObAccountAliasListResponse, ObAccountAliasListSessionCache> {
	private static Logger log = LogManager.getLogger(AdministrationAccountAliasList.class);
	
	@Autowired GeneralCodeDAO gnDao;

    @Override
    public void processInternalWithCache(String locale, String sessionId, String trxId, ObAccountAliasListRequest requestBean, ObAccountAliasListResponse responseBean, ObAccountAliasListSessionCache cacheBean, VCGenericService vcService) throws Exception {
    	

    	AccountAliasesRequestData requestData = new AccountAliasesRequestData();
    	AccountAliasesResponseData responseData;
		processRequest(requestData, requestBean);
		VCResponse<AccountAliasesResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.ACCOUNT_ALIASES_LIST, 
				requestData, 
				AccountAliasesResponseData.class, 
				true);
		if(processVCResponseError(vcResponse, responseBean)){
			return;
		}
		
		responseData = vcResponse.getData();
		processResponse(responseData, responseBean);
		cacheBean.setAccountAliasesResponseData(responseData);        	
    	
    }

	private void processRequest(AccountAliasesRequestData requestData, ObAccountAliasListRequest requestBean) throws Exception{
		
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setAccount_no(requestBean.getAccountNumber());
		requestData.setAccount_name(requestBean.getAccountName());
		requestData.setAlias_name(requestBean.getAliasName());

		if (requestBean.getPageNo() == null || requestBean.getPageNo().isEmpty()) {
			requestData.setPage_no(1);
		} else {
			requestData.setPage_no(Integer.valueOf(requestBean.getPageNo()));
		}

		if (requestBean.getPageSize() == null || requestBean.getPageSize().isEmpty()) {
			requestData.setPage_size(Integer.MAX_VALUE);
		} else {
			requestData.setPage_size(Integer.valueOf(requestBean.getPageSize()));
		}
			
	}
	
	private void processResponse(AccountAliasesResponseData responseData, ObAccountAliasListResponse responseBean) throws Exception{
		
		
		responseBean.setAccountList(new ArrayList<ObAccountBean>());

		for (ListAccount accountAliases : responseData.getAccount_list()) {

			ObAccountBean dataList = new ObAccountBean();

			dataList.setAccountNumber(accountAliases.getAcct_no());
			dataList.setAccountName(accountAliases.getAcct_name());
			dataList.setAccountAlias(accountAliases.getAcct_alias());
			dataList.setCurrencyCode(accountAliases.getAcct_ccy());
			dataList.setProductCode(accountAliases.getProd_cd_sibs());
			dataList.setProductName(accountAliases.getAcct_prod_name());
			dataList.setAccountId(accountAliases.getId());
			dataList.setVersion(String.valueOf(accountAliases.getVersion()));

			responseBean.getAccountList().add(dataList);
		}

		responseBean.setTotalRecords(responseData.getTotal_records());
		
	}
		

  }
