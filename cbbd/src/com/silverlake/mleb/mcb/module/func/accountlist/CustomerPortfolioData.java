package com.silverlake.mleb.mcb.module.func.accountlist;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.ObCustomerFacilityDataListBean;
import com.silverlake.mleb.mcb.bean.ObCustomerInvestmentDataBean;
import com.silverlake.mleb.mcb.bean.accountlist.ObAccountListRequest;
import com.silverlake.mleb.mcb.bean.accountlist.ObAccountListResponse;
import com.silverlake.mleb.mcb.bean.accountlist.ObAccountListSessionCache;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.module.common.CacheSessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.accountlist.CustomerFacilityData;
import com.silverlake.mleb.mcb.module.vc.accountlist.CustomerInvestmentData;
import com.silverlake.mleb.mcb.module.vc.accountlist.CustomerLoanData;
import com.silverlake.mleb.mcb.module.vc.accountlist.CustomerPortfolioRequestData;
import com.silverlake.mleb.mcb.module.vc.accountlist.CustomerPortfolioResponseData;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;

/**
 * Purpose: To view the customer portfolio for Loan, Unit Trust,Bonds.
 * 
 * Omni Web Services:
 *acct_mgmt/portfolio/view
 *
 * @author Hemanth
 *
 */
@Service
public class CustomerPortfolioData extends CacheSessionFuncServices<ObAccountListRequest, ObAccountListResponse, ObAccountListSessionCache> {
	private static Logger log = LogManager.getLogger(CustomerPortfolioData.class);

    @Override
    public void processInternalWithCache(String locale, String sessionId, String trxId, ObAccountListRequest requestBean, ObAccountListResponse responseBean, ObAccountListSessionCache cacheBean, VCGenericService vcService) throws Exception {
    	

    	CustomerPortfolioRequestData requestData = new CustomerPortfolioRequestData();
    	CustomerPortfolioResponseData responseData;
		processRequest(requestData, requestBean);
		VCResponse<CustomerPortfolioResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.CUSTOMER_PORTFOLIO_DATA, 
				requestData, 
				CustomerPortfolioResponseData.class, 
				true);
		if(processVCResponseError(vcResponse, responseBean)){
			return;
		}
		
		responseData = vcResponse.getData();
		processResponse(responseData, responseBean, requestBean);
		  	        	
    	
    }

	private void processRequest(CustomerPortfolioRequestData requestData, ObAccountListRequest requestBean) throws Exception{
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setCustomer_number(requestBean.getCustomerNumber());
		requestData.setProd_cd(requestBean.getProductCode());
		requestData.setAgreement_only(MiBConstant.FUNC_TRUE);
			
		if(requestBean.getProductCode().equalsIgnoreCase(MiBConstant.FUNC_PRODUCT_CODE_LOAN) ){
		
		    if(requestBean.getAgreementOnly()){
			    requestData.setAgreement_only(MiBConstant.FUNC_TRUE);
		    }else{
			    requestData.setAgreement_only(MiBConstant.FUNC_FALSE);			
		    }
		}
		
	}
	
	private void processResponse(CustomerPortfolioResponseData responseData, ObAccountListResponse responseBean, ObAccountListRequest requestBean) throws Exception{
		
		if (requestBean.getProductCode().equalsIgnoreCase(MiBConstant.FUNC_PRODUCT_CODE_LOAN)) {

			if (requestBean.getProductCode().equalsIgnoreCase(MiBConstant.FUNC_PRODUCT_CODE_LOAN)
					&& responseData.getLoan_data() == null) {
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_NO_RESULT);
				return;
			} else {

				CustomerLoanData loan = responseData.getLoan_data();

				responseBean.setGroupCode(loan.getGroup_cd());
				responseBean.setGroupName(loan.getGroup_name());
				responseBean.setLoanAcctName(loan.getLoan_acct_name());
				responseBean.setLoanCustomerNumber(loan.getLoan_cust_number());
				responseBean.setAmountLimit(loan.getAmount_limit());
				responseBean.setOutstandingAmount(loan.getOutstanding_amt());
				responseBean.setAvailableAmount(loan.getAvailable_amt());
				responseBean.setCurrencyCode(loan.getCurrency_code());
				responseBean.setLastUpdate(loan.getLast_update());

				responseBean.setFacilityList(new ArrayList<ObCustomerFacilityDataListBean>());

				for (CustomerFacilityData facilityData : loan.getFacility_list()) {

					ObCustomerFacilityDataListBean facility = new ObCustomerFacilityDataListBean();

					facility.setId(facilityData.getId());
					facility.setParentId(facilityData.getParent_id());
					facility.setDescription(facilityData.getDescription());
					facility.setProductCodeStr(facilityData.getProduct_code_str());
					facility.setGroupId(facilityData.getGroup_id());
					facility.setGroupName(facilityData.getGroup_name());
					facility.setSourceApplication(facilityData.getSource_application());
					facility.setDebtorCif(facilityData.getDebtor_cif());
					facility.setDebtorName(facilityData.getDebtor_name());
					facility.setCurrencyCode(facilityData.getCurrency_code());
					facility.setAmountLimit(facilityData.getAmount_limit());
					facility.setOutstandingAmount(facilityData.getOutstanding_amt());
					facility.setAvailableAmount(facilityData.getAvailable_amt());
					facility.setValueDate(facilityData.getValue_date());
					facility.setDueDate(facilityData.getDue_date());

					responseBean.getFacilityList().add(facility);
				}

			}

		} else {

			if ((requestBean.getProductCode().equalsIgnoreCase(MiBConstant.FUNC_PRODUCT_CODE_UNIT_TRUST)
					|| requestBean.getProductCode().equalsIgnoreCase(MiBConstant.FUNC_PRODUCT_CODE_BONDS))
					&& responseData.getWealth_data_list() == null) {

				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_NO_RESULT);
				return;
			} else {

				responseBean.setInvestmentData(new ArrayList<ObCustomerInvestmentDataBean>());

				for (CustomerInvestmentData inveastmentData : responseData.getWealth_data_list()) {

					ObCustomerInvestmentDataBean investment = new ObCustomerInvestmentDataBean();

					investment.setCustomerNumber(inveastmentData.getCustomer_number());
					investment.setClientCode(inveastmentData.getClient_code());
					investment.setProductName(inveastmentData.getProduct_name());
					investment.setProductCode(inveastmentData.getProduct_code());
					investment.setUnitBalance(inveastmentData.getUnit_balance());
					investment.setCurrencyCode(inveastmentData.getCurrency_code());
					investment.setNavAmount(inveastmentData.getNav_amount());
					investment.setAmountBalance(inveastmentData.getAmount_balance());
					investment.setNavDate(inveastmentData.getNav_date());
					investment.setInvestType(inveastmentData.getInvest_type());
					investment.setMaturityDate(inveastmentData.getMaturity_date());
					investment.setCuponPct(inveastmentData.getCoupon_pct());
					                    
					responseBean.getInvestmentData().add(investment);
				}
			}

		}

	}		

   }
