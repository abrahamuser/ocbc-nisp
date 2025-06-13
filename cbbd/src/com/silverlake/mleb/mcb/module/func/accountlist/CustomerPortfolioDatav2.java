package com.silverlake.mleb.mcb.module.func.accountlist;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import com.silverlake.mleb.mcb.bean.accountlist.ObAccountListRequest;
import com.silverlake.mleb.mcb.bean.accountlist.ObAccountListResponse;
import com.silverlake.mleb.mcb.bean.accountlist.ObAccountListSessionCache;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.module.common.CacheSessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.accountlist.CustomerPortfolioRequestDatav2;
import com.silverlake.mleb.mcb.module.vc.accountlist.CustomerPortfolioResponseDatav2;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;

/**
 * Purpose: To view the customer portfolio for Loan, Unit Trust,Bonds.
 * 
 * Omni Web Services:
 *v2/acct_mgmt/portfolio/view
 *
 * @author Hemanth
 *
 */
@Service
public class CustomerPortfolioDatav2 extends CacheSessionFuncServices<ObAccountListRequest, ObAccountListResponse, ObAccountListSessionCache> {
	private static Logger log = LogManager.getLogger(CustomerPortfolioDatav2.class);

    @Override
    public void processInternalWithCache(String locale, String sessionId, String trxId, ObAccountListRequest requestBean, ObAccountListResponse responseBean, ObAccountListSessionCache cacheBean, VCGenericService vcService) throws Exception {
    	

    	CustomerPortfolioRequestDatav2 requestData = new CustomerPortfolioRequestDatav2();
    	CustomerPortfolioResponseDatav2 responseData;
		processRequest(requestData, requestBean);
		VCResponse<CustomerPortfolioResponseDatav2> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.CUSTOMER_PORTFOLIO_DATA_V2, 
				requestData, 
				CustomerPortfolioResponseDatav2.class, 
				true);
		if(processVCResponseError(vcResponse, responseBean)){
			return;
		}
		
		responseData = vcResponse.getData();
		processResponse(responseData, responseBean, requestBean);
		 
		    }

	private void processRequest(CustomerPortfolioRequestDatav2 requestData, ObAccountListRequest requestBean)
			throws Exception {
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setProd_cd(requestBean.getProductCode());
		requestData.setCif_no(requestBean.getCifNumber());

		if (requestBean.getProductCode().equalsIgnoreCase(MiBConstant.FUNC_PRODUCT_CODE_LOAN)) {

			requestData.setAgreement_id(requestBean.getAgreementID());
		}

	}
	
	private void processResponse(CustomerPortfolioResponseDatav2 responseData, ObAccountListResponse responseBean, ObAccountListRequest requestBean) throws Exception{
		
		if (requestBean.getProductCode().equalsIgnoreCase(MiBConstant.FUNC_PRODUCT_CODE_LOAN)) {

			if (requestBean.getProductCode().equalsIgnoreCase(MiBConstant.FUNC_PRODUCT_CODE_LOAN)
					&& responseData.getLoan_data_list() == null) {
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_NO_RESULT);
				return;
			} else {

				responseBean.setLoan_data_list(responseData.getLoan_data_list());
			}

		} else if(requestBean.getProductCode().equalsIgnoreCase(MiBConstant.FUNC_PRODUCT_CODE_BANCAS)){
			
			if (requestBean.getProductCode().equalsIgnoreCase(MiBConstant.FUNC_PRODUCT_CODE_BANCAS)
					&& responseData.getBancassurance_data_list() == null) {
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_NO_RESULT);
				return;
			} else {

				responseBean.setBancassurance_data_list(responseData.getBancassurance_data_list());
			}
		}
			
		else {
		
			if ((requestBean.getProductCode().equalsIgnoreCase(MiBConstant.FUNC_PRODUCT_CODE_UNIT_TRUST)
					|| requestBean.getProductCode().equalsIgnoreCase(MiBConstant.FUNC_PRODUCT_CODE_BONDS))
					&& responseData.getWealth_data_list() == null) {

				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_NO_RESULT);
				return;
			} else {

				responseBean.setWealth_data_list(responseData.getWealth_data_list());

			}

		}

	}

   }
