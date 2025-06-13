package com.silverlake.mleb.mcb.module.func.accountlist;

import java.util.ArrayList;
import java.util.Calendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.ObCustomerAccountDataListBean;
import com.silverlake.mleb.mcb.bean.accountlist.ObAccountListRequest;
import com.silverlake.mleb.mcb.bean.accountlist.ObAccountListResponse;
import com.silverlake.mleb.mcb.bean.accountlist.ObAccountListSessionCache;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.entity.GeneralCode;
import com.silverlake.mleb.mcb.entity.dao.GeneralCodeDAO;
import com.silverlake.mleb.mcb.module.common.CacheSessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.accountlist.CustomerAccountRequestData;
import com.silverlake.mleb.mcb.module.vc.accountlist.CustomerAccountResponseData;
import com.silverlake.mleb.mcb.module.vc.accountlist.CustomerAccountData;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;

/**
 * Purpose: To get list of all customer account available for Loan, Unit Trust,
            Bonds and SPT Statement.
 * Omni Web Services:
 * acct_mgmt/customer/account
 *
 * @author Hemanth
 *
 */
@Service
public class CustomerAccountList extends CacheSessionFuncServices<ObAccountListRequest, ObAccountListResponse, ObAccountListSessionCache> {
	private static Logger log = LogManager.getLogger(CustomerAccountList.class);
	
	@Autowired GeneralCodeDAO gnDao;

    @Override
    public void processInternalWithCache(String locale, String sessionId, String trxId, ObAccountListRequest requestBean, ObAccountListResponse responseBean, ObAccountListSessionCache cacheBean, VCGenericService vcService) throws Exception {
    	

    	CustomerAccountRequestData requestData = new CustomerAccountRequestData();
    	CustomerAccountResponseData responseData;
		processRequest(requestData, requestBean);
		VCResponse<CustomerAccountResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.CUSTOMER_ACCOUNT_LIST, 
				requestData, 
				CustomerAccountResponseData.class, 
				true);
		if(processVCResponseError(vcResponse, responseBean)){
			return;
		}
		
		responseData = vcResponse.getData();
		processResponse(responseData, responseBean);
		        	
    	
    }

	private void processRequest(CustomerAccountRequestData requestData, ObAccountListRequest requestBean) throws Exception{
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setSearch_cust_no(requestBean.getSearchCustNo());
		requestData.setSearch_cust_name(requestBean.getSearchCustName());
		requestData.setProd_cd(requestBean.getProductCode());
		
				
		if(requestBean.getPageNo() == null || requestBean.getPageNo().isEmpty()){
			requestData.setPage_no(1);
		}else{
			requestData.setPage_no(Integer.valueOf(requestBean.getPageNo()));
		}
		
		if(requestBean.getPageSize() == null || requestBean.getPageSize().isEmpty()){
			requestData.setPage_size(Integer.MAX_VALUE);
		}else{
			requestData.setPage_size(Integer.valueOf(requestBean.getPageSize()));
		}
			
	}
	
	private void processResponse(CustomerAccountResponseData responseData, ObAccountListResponse responseBean) throws Exception{
		
		
		responseBean.setCustomerAccountList(new ArrayList<ObCustomerAccountDataListBean>());
		
		for(CustomerAccountData  customerAccount : responseData.getList_account()){
			
			ObCustomerAccountDataListBean dataList = new ObCustomerAccountDataListBean();
			
			dataList.setCustomerNumber(customerAccount.getCustomer_number());
			dataList.setCustomerName(customerAccount.getCustomer_name());
			dataList.setCustomerId(customerAccount.getCustomer_id());
			
			responseBean.getCustomerAccountList().add(dataList);
		}
		
		responseBean.setTotalRows(responseData.getTotal_rows());
		
		GeneralCode minYear = gnDao.findByGnCode(MiBConstant.MIN_YEAR);
		if(minYear != null){
			responseBean.setMinYear(Integer.valueOf(minYear.getGnCodeDescription()));
		}
		
		GeneralCode maxYear = gnDao.findByGnCode(MiBConstant.MAX_YEAR);
		if(maxYear != null){
			responseBean.setMaxYear(Integer.valueOf(maxYear.getGnCodeDescription()));
		}
	}
		

  }
