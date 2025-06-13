package com.silverlake.mleb.mcb.module.func.transaction;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.ObCOTConfigDataBean;
import com.silverlake.mleb.mcb.bean.ObSessionCache;
import com.silverlake.mleb.mcb.bean.ObTransactionRequest;
import com.silverlake.mleb.mcb.bean.ObTransactionResponse;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.module.common.CacheSessionFuncServices;
import com.silverlake.mleb.mcb.module.func.RetrieveAppStatInfo_mcb;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.transaction.management.COTConfig;
import com.silverlake.mleb.mcb.module.vc.transaction.management.ProductConfig;
import com.silverlake.mleb.mcb.module.vc.transaction.management.ProductConfigurationRequestData;
import com.silverlake.mleb.mcb.module.vc.transaction.management.ProductConfigurationResponseData;

/**
 * Purpose: Initial step before start fund transfer to get the menu content which required to generate dynamically
 * Get the cutoff and transaction amount limit data from omni and construct the menu description based on the data
 * 
 * Omni Web Services:
 * transaction_mgmt/product_configuration
 * 
 * @author Alex Loo
 */
@Service
public class TransactionFundTransferMenu extends CacheSessionFuncServices<ObTransactionRequest, ObTransactionResponse, ObSessionCache>{
	@Override
	public void processInternalWithCache(String locale, String sessionId, String trxId, ObTransactionRequest requestBean, ObTransactionResponse responseBean, ObSessionCache cacheBean, VCGenericService vcService) throws Exception {
		ProductConfigurationRequestData requestData = new ProductConfigurationRequestData();
		processRequest(requestData, requestBean);
		VCResponse<ProductConfigurationResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.TRANSACTION_MGMT_PRODUCT_CONFIGURATION, 
				requestData, 
				ProductConfigurationResponseData.class, 
				true);
		if(processVCResponseError(vcResponse, responseBean)){
			return;
		}
		
		ProductConfigurationResponseData responseData = vcResponse.getData();
		processResponse(locale, requestBean, responseData, responseBean);
	}
	
	private void processRequest(ProductConfigurationRequestData requestData, ObTransactionRequest requestBean) throws Exception{
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setList_prod_cd(requestBean.getTransferServiceTypes());
	}
	
	private void processResponse(String locale, ObTransactionRequest requestBean, ProductConfigurationResponseData responseData, ObTransactionResponse responseBean) throws Exception{
		responseBean.setMenu(new LinkedHashMap<String, String>(requestBean.getTransferServiceTypes().size()));
		if(responseData.getProduct_config_list() != null) {
			for(ProductConfig productConfig:responseData.getProduct_config_list()) {
				String description = RetrieveAppStatInfo_mcb.getMessageTable(locale.toUpperCase()).get("MENU_LIST_DESC_DYNM_"+productConfig.getProd_cd().toUpperCase());
				if(description != null) {
					description = description.replaceAll("%start_time%", productConfig.getCot_config().getBus_hour_start());
					description = description.replaceAll("%end_time%", productConfig.getCot_config().getBus_hour_end());
				    String max = productConfig.getTrx_limit_config().getMax_amount().setScale(0).toPlainString();
				    String min = productConfig.getTrx_limit_config().getMin_amount().setScale(0).toPlainString();
					description = description.replaceAll("%min_amt%", NumberFormat.getNumberInstance(Locale.US).format(new Long(min)));
					description = description.replaceAll("%max_amt%", NumberFormat.getNumberInstance(Locale.US).format(new Long(max)));
					
					responseBean.getMenu().put(productConfig.getProd_cd().toUpperCase(), description);
				}
			}
		}
		
		
		//Insert legacy description if data not found
		for(String productCode:requestBean.getTransferServiceTypes()){
			if(responseBean.getMenu().get(productCode) == null) {
				responseBean.getMenu().put(productCode.toUpperCase(), "");
			}
		}
		
		if (responseData.getProduct_config_list() != null) {

		   responseBean.setCcyList(new ArrayList<ObCOTConfigDataBean>());
			
			for (ProductConfig productConfig : responseData.getProduct_config_list()) {
				
				if(productConfig.getList_cot_ccy() != null) {

				for (COTConfig COTConfig : productConfig.getList_cot_ccy()) {

					ObCOTConfigDataBean ccy = new ObCOTConfigDataBean();

					ccy.setBusHourStart(COTConfig.getBus_hour_start());
					ccy.setBusHourEnd(COTConfig.getBus_hour_end());
					ccy.setCurrencyCd(COTConfig.getCcy_cd());
					ccy.setIsCot(COTConfig.getIs_cot());
					ccy.setProductCd(productConfig.getProd_cd());

					responseBean.getCcyList().add(ccy);
				}
				
			  }
			}
		}
	}
}
