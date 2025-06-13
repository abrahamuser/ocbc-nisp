package com.silverlake.mleb.mcb.module.func.onfx;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.ObForexRequest;
import com.silverlake.mleb.mcb.bean.ObForexResponse;
import com.silverlake.mleb.mcb.bean.ObForexSessionCache;
import com.silverlake.mleb.mcb.bean.common.ObCurrencyRate;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.entity.GeneralCode;
import com.silverlake.mleb.mcb.entity.dao.GeneralCodeDAO;
import com.silverlake.mleb.mcb.module.common.CacheSessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.onfx.ListOnfxRate;
import com.silverlake.mleb.mcb.module.vc.onfx.RequestData;
import com.silverlake.mleb.mcb.module.vc.onfx.ResponseData;

/**
 * Purpose: Get online forex rate either all rates or specific rate
 * Omni Web Services:
 * onfx/rate - original ws
 * v2/onfx/rate - to get rate for the currency
 * v2/onfx/currency - to get list of currencies available for cross currency
 * 
 * @author Alex Loo
 *
 */
@Service
public class ONFXGetRate extends CacheSessionFuncServices<ObForexRequest, ObForexResponse, ObForexSessionCache>{
	@Override
	public void processInternalWithCache(String locale, String sessionId, String trxId, ObForexRequest requestBean, ObForexResponse responseBean, ObForexSessionCache cacheBean, VCGenericService vcService) throws Exception {
		RequestData requestData = new RequestData();
		ResponseData responseData;
		VCResponse<ResponseData> vcResponse = null;
		//This is cross currency enabled ws
		if(requestBean.getIsCrossCurrency() != null && requestBean.getIsCrossCurrency()) {
			processRequestOnfxRateCurrency(requestData, requestBean);
			vcResponse = vcService.callOmniVC(
					VCMethodConstant.REST_METHODS.ONFX_RATE_CURRENCY, 
					requestData, 
					ResponseData.class, 
					true);
			if(processVCResponseError(vcResponse, responseBean)){
				return;
			}
			responseData = vcResponse.getData();
			//Responding list of currency available for onfx
			processResponseOnfxRateCurrency(responseData, responseBean);
			
			//Requesting list of currency available for onfx
			if(requestBean.getCurrency() != null && !requestBean.getCurrency().isEmpty()) {
				processRequestCrossCurrency(requestData, requestBean);
				vcResponse = vcService.callOmniVC(
						VCMethodConstant.REST_METHODS.ONFX_RATE_V2, 
						requestData, 
						ResponseData.class, 
						true);
				if(processVCResponseError(vcResponse, responseBean)){
					return;
				}
				responseData = vcResponse.getData();
				processResponseCrossCurrency(responseData, requestBean, responseBean);
			}
		}else {
			processRequestNonCrossCurrency(requestData, requestBean);
			vcResponse = vcService.callOmniVC(
					VCMethodConstant.REST_METHODS.ONFX_RATE_V2, 
					requestData, 
					ResponseData.class, 
					true);
			if(processVCResponseError(vcResponse, responseBean)){
				return;
			}
			responseData = vcResponse.getData();
			processResponseNonCrossCurrency(responseData, responseBean);
		}
	}
	
	//Depreciated omni web services
//	private void processRequest(RequestData requestData, ObForexRequest requestBean) throws Exception{
//		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
//		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
//		if(requestBean.getCurrency() != null && !requestBean.getCurrency().isEmpty()){
//			requestData.setCcy_cd(requestBean.getCurrency());
//		}
//	}
//	
//	private void processResponse(ResponseData responseData, ObForexResponse responseBean) throws Exception{
//		responseBean.setListCurrency(new ArrayList<ObCurrencyRate>());
//		for(ListOnfxRate rate:responseData.getList_onfx_rate()){
//			ObCurrencyRate obCurrencyRate = new ObCurrencyRate();
//			obCurrencyRate.setBankBuy(rate.getTt_buy_rate());
//			obCurrencyRate.setBankSell(rate.getTt_sell_rate());
//			obCurrencyRate.setCcy(rate.getCcy_code());
//			obCurrencyRate.setPairCcy(obCurrencyRate.getCcy()+"/IDR");
//			responseBean.getListCurrency().add(obCurrencyRate);
//		}
//	}
	
	private void processRequestNonCrossCurrency(RequestData requestData, ObForexRequest requestBean) throws Exception{
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setCcy_cd1(MiBConstant.CURRENCY_IDR);
		if(requestBean.getCurrency() != null && !requestBean.getCurrency().isEmpty()){
			requestData.setCcy_cd2(requestBean.getCurrency());
		}
	}
	
	private void processResponseNonCrossCurrency(ResponseData responseData, ObForexResponse responseBean) throws Exception{
		responseBean.setListCurrency(new ArrayList<ObCurrencyRate>());
		for(ListOnfxRate rate:responseData.getList_onfx_rate_v2()){
			ObCurrencyRate obCurrencyRate = new ObCurrencyRate();
			if(rate.getBank_buy_rate() != null && rate.getBank_buy_rate().scale() < 2) {
				obCurrencyRate.setBankBuy(rate.getBank_buy_rate().setScale(2).toPlainString());
			}else {
				obCurrencyRate.setBankBuy(rate.getBank_buy_rate().toPlainString());
			}
			if(rate.getBank_sell_rate() != null && rate.getBank_sell_rate().scale() < 2) {
				obCurrencyRate.setBankSell(rate.getBank_sell_rate().setScale(2).toPlainString());
			}else {
				obCurrencyRate.setBankSell(rate.getBank_sell_rate().toPlainString());
			}
			obCurrencyRate.setCcy(rate.getBased_ccy());
			obCurrencyRate.setPairCcy(rate.getBased_ccy()+"/IDR");
			responseBean.getListCurrency().add(obCurrencyRate);
		}
	}
	
	private void processRequestCrossCurrency(RequestData requestData, ObForexRequest requestBean) throws Exception{
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		if(requestBean.getCurrency() != null && !requestBean.getCurrency().isEmpty()){
			requestData.setCcy_cd1(requestBean.getCurrency());
		}
		if(requestBean.getCurrency2() != null && !requestBean.getCurrency2().isEmpty()){
			requestData.setCcy_cd2(requestBean.getCurrency2());
		}
	}
	
	private void processResponseCrossCurrency(ResponseData responseData, ObForexRequest requestBean, ObForexResponse responseBean) throws Exception{
		responseBean.setListCurrency(new ArrayList<ObCurrencyRate>());
		for(ListOnfxRate rate:responseData.getList_onfx_rate_v2()){
			ObCurrencyRate obCurrencyRate = new ObCurrencyRate();
			if(rate.getBank_buy_rate() != null && rate.getBank_buy_rate().scale() < 2) {
				obCurrencyRate.setBankBuy(rate.getBank_buy_rate().setScale(2).toPlainString());
			}else {
				obCurrencyRate.setBankBuy(rate.getBank_buy_rate().toPlainString());
			}
			if(rate.getBank_sell_rate() != null && rate.getBank_sell_rate().scale() < 2) {
				obCurrencyRate.setBankSell(rate.getBank_sell_rate().setScale(2).toPlainString());
			}else {
				obCurrencyRate.setBankSell(rate.getBank_sell_rate().toPlainString());
			}
			obCurrencyRate.setCcy(rate.getBased_ccy());
			obCurrencyRate.setPairCcy(rate.getBased_ccy()+"/"+rate.getQuote_ccy());
			if(requestBean.getCurrency2() != null && !requestBean.getCurrency2().isEmpty()) {
				//Currency that selected by customer in 'I want to buy' is base_ccy, Indicative Rate = bank_sell_rate
				if(requestBean.getCurrency2().equalsIgnoreCase(rate.getBased_ccy())) {
					obCurrencyRate.setIndicativeRate(new BigDecimal(obCurrencyRate.getBankSell()));
				}else {
					//Currency that selected by customer in 'I want to buy' is quote_ccy, Indicative Rate = bank_buy_rate
					obCurrencyRate.setIndicativeRate(new BigDecimal(obCurrencyRate.getBankBuy()));
				}
			}
			responseBean.getListCurrency().add(obCurrencyRate);
		}
	}
	
	private void processRequestOnfxRateCurrency(RequestData requestData, ObForexRequest requestBean) throws Exception{
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
	}
	
	private void processResponseOnfxRateCurrency(ResponseData responseData, ObForexResponse responseBean) throws Exception{
		responseBean.setAvailableCurrencyList(new ArrayList<String>());
		if(responseData.getList_onfx_ccy() != null) {
			responseBean.setAvailableCurrencyList(responseData.getList_onfx_ccy());
		}
	}
}
