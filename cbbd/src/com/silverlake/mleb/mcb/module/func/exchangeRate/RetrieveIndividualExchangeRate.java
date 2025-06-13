package com.silverlake.mleb.mcb.module.func.exchangeRate;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.ObExchangeRateBean;
import com.silverlake.mleb.mcb.bean.ObRetrieveExchangeRateRequest;
import com.silverlake.mleb.mcb.bean.ObRetrieveExchangeRateResponse;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.module.common.SessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.exchangeRate.ExchangeRate;
import com.silverlake.mleb.mcb.module.vc.exchangeRate.RequestData;
import com.silverlake.mleb.mcb.module.vc.exchangeRate.ResponseData;



@Service
public class RetrieveIndividualExchangeRate extends SessionFuncServices<ObRetrieveExchangeRateRequest, ObRetrieveExchangeRateResponse>{  
	private static Logger log = LogManager.getLogger(RetrieveIndividualExchangeRate.class);
	
	@Override
	public void processInternal(String locale, String sessionId, String trxId, ObRetrieveExchangeRateRequest requestBean, ObRetrieveExchangeRateResponse responseBean, VCGenericService vcService) throws Exception {
		//Get target ccy code and query based on today date
		Date now = new Date();
		String nowStr = new SimpleDateFormat(MiBConstant.YYYYMMDD_FORMAT).format(now);
		String targetCcy = null;
		if(requestBean.getSellCcy().equalsIgnoreCase(MiBConstant.CURRENCY_IDR)){
			targetCcy = requestBean.getBuyCcy();
		}else if(requestBean.getBuyCcy().equalsIgnoreCase(MiBConstant.CURRENCY_IDR)){
			targetCcy = requestBean.getSellCcy();
		}else{
			//No IDR exist in sell or buy currency
			log.info("Either sell or buy currency must be "+MiBConstant.CURRENCY_IDR);
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
			return;
		}
		RequestData vcRetrieveExchangeRate = new RequestData();
		vcRetrieveExchangeRate.setCcy_code(targetCcy);
		vcRetrieveExchangeRate.setPeriod_from(nowStr);
		vcRetrieveExchangeRate.setPeriod_to(nowStr);
		
		ResponseData responseData;
		VCResponse<ResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.EXCHANGE_RATE, 
				vcRetrieveExchangeRate, 
				ResponseData.class, 
				true);
		if(processVCResponseError(vcResponse, responseBean)){
			return;
		}
		
		responseData = vcResponse.getData();
		
		if(responseData.getList_fxrate()==null){
			responseBean.getObHeader().setStatusCode(MiBConstant.MCB_EXCHANGE_RATE_MISSING);
			return;
		}
		
		responseBean.setRateList(new ArrayList<ObExchangeRateBean>());
		
		for(ExchangeRate temp:responseData.getList_fxrate()){
			ObExchangeRateBean exchangeRate = new ObExchangeRateBean();
			exchangeRate.setCurrencyCode(temp.getCcy_code());
			exchangeRate.setBankBuyRate(temp.getBank_buy_rate());
			exchangeRate.setBankSellRate(temp.getBank_sell_rate());
			exchangeRate.setTtBuyRate(temp.getTt_buy_rate());
			exchangeRate.setTtSellRate(temp.getTt_sell_rate());
			exchangeRate.setPeriod(temp.getPeriod());
			responseBean.getRateList().add(exchangeRate);
		}
		
		if(requestBean.getSellCcy().equalsIgnoreCase(targetCcy)){
			responseBean.setRate(responseBean.getRateList().get(0).getTtBuyRate());
		}else{
			responseBean.setRate(responseBean.getRateList().get(0).getTtSellRate());
		}
	}
}
