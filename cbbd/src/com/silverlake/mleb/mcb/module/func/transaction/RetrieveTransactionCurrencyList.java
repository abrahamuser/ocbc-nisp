package com.silverlake.mleb.mcb.module.func.transaction;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.ObTransactionCurrencyRequest;
import com.silverlake.mleb.mcb.bean.ObTransactionCurrencyResponse;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.module.common.SessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.exchangeRate.ExchangeRate;

/**
 * Purpose: Get currency list that available for transaction
 * Usage of this web service currently only for TT which needed to set the beneficiary currency. 
 * 
 * Omni Web Services:
 * fxrate/inquiry
 * 
 * @author Alex Loo
 *
 */
@Service
public class RetrieveTransactionCurrencyList extends SessionFuncServices<ObTransactionCurrencyRequest, ObTransactionCurrencyResponse>{
	private static Logger log = LogManager.getLogger(RetrieveTransactionCurrencyList.class);
	
	@Override
	public void processInternal(String locale, String sessionId, String trxId, ObTransactionCurrencyRequest requestBean, ObTransactionCurrencyResponse responseBean, VCGenericService vcService) throws Exception {
		List<String> currencyList = new ArrayList<String>();
		//Get target ccy code and query based on today date
		Date now = new Date();
		String nowStr = new SimpleDateFormat(MiBConstant.YYYYMMDD_FORMAT).format(now);
		com.silverlake.mleb.mcb.module.vc.exchangeRate.RequestData vcRetrieveExchangeRate = new com.silverlake.mleb.mcb.module.vc.exchangeRate.RequestData();
		vcRetrieveExchangeRate.setPeriod_from(nowStr);
		vcRetrieveExchangeRate.setPeriod_to(nowStr);
		
		com.silverlake.mleb.mcb.module.vc.exchangeRate.ResponseData responseData;
		VCResponse<com.silverlake.mleb.mcb.module.vc.exchangeRate.ResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.EXCHANGE_RATE, 
				vcRetrieveExchangeRate, 
				com.silverlake.mleb.mcb.module.vc.exchangeRate.ResponseData.class, 
				true);
		if(processVCResponseError(vcResponse, responseBean)){
			return;
		}
		
		responseData = vcResponse.getData();
		if(responseData.getList_fxrate()!=null){
			for(ExchangeRate temp:responseData.getList_fxrate()){
				//For TT filter the IDR from the list
				if(requestBean.getTransferServiceType().equals(MiBConstant.TRANS_SERVICE_TYPE_TT)){
					if(!temp.getCcy_code().equals(MiBConstant.CURRENCY_IDR)){
						currencyList.add(temp.getCcy_code());
					}
				}
			}
		}
		responseBean.setCurrencyList(currencyList);
	}
}
