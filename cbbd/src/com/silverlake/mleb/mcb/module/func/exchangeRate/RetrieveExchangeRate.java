package com.silverlake.mleb.mcb.module.func.exchangeRate;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.mcb.bean.ObHeaderResponse;
import com.silverlake.mleb.mcb.bean.ObRetrieveExchangeRateRequest;
import com.silverlake.mleb.mcb.bean.ObRetrieveExchangeRateResponse;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.entity.dao.CustomerDAO;
import com.silverlake.mleb.mcb.entity.dao.MLEBMIBDAO;
import com.silverlake.mleb.mcb.module.common.FuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.common.VCService;
import com.silverlake.mleb.mcb.module.vc.exchangeRate.ExchangeRate;
import com.silverlake.mleb.mcb.module.vc.exchangeRate.RequestData;
import com.silverlake.mleb.mcb.module.vc.exchangeRate.ResponseData;



@Service
public class RetrieveExchangeRate extends FuncServices  
{

	private static Logger log = LogManager.getLogger(RetrieveExchangeRate.class);
	
	@Autowired
	MLEBMIBDAO dao;
	
	@Autowired
	CustomerDAO custDao;
	 
	@Autowired ApplicationContext appContext;
	
	public MICBResponseBean process(MICBRequestBean arg0) {
	
		MICBResponseBean response = new MICBResponseBean();
		ObRetrieveExchangeRateResponse obResponse = new ObRetrieveExchangeRateResponse();
		obResponse.setObHeader(new ObHeaderResponse());
		
		try {
			ObRetrieveExchangeRateRequest requestData = (ObRetrieveExchangeRateRequest) arg0.getBDObject();	
			String ipAddress = requestData.getObHeader() == null ? null : requestData.getObHeader().getIp();
			
			//Call REQ OMNI RetrieveExchangeRate
			VCService usrService = new VCService(appContext); 
			RequestData vcRetrieveExchangeRate = new RequestData();
			vcRetrieveExchangeRate.setCcy_code(requestData.getCcy_code());
			vcRetrieveExchangeRate.setPeriod_from(requestData.getPeriod_from());
			vcRetrieveExchangeRate.setPeriod_to(requestData.getPeriod_to());
			
			//Call RES OMNI RetrieveExchangeRate
			com.silverlake.mleb.mcb.module.vc.exchangeRate.ResponseData vcRetrieveExchangeRateResponseData = new com.silverlake.mleb.mcb.module.vc.exchangeRate.ResponseData();
			VCResponse vcResponse = usrService.callOmniVC(VCMethodConstant.REST_METHODS.EXCHANGE_RATE,vcRetrieveExchangeRate, vcRetrieveExchangeRateResponseData, null, arg0.getTranxID(), ipAddress);
			
			if(vcResponse.getResponse_code().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS))
			{
				vcRetrieveExchangeRateResponseData = (ResponseData) vcResponse.getData();
				if(vcRetrieveExchangeRateResponseData.getList_fxrate()!=null)
				{
					obResponse.setList_fxrate(new ArrayList());
					List<ExchangeRate> exchangeRateList = new ArrayList();
					for(ExchangeRate temp:vcRetrieveExchangeRateResponseData.getList_fxrate())
					{
						ExchangeRate exchangeRate = new ExchangeRate();
						BeanUtils.copyProperties(temp, exchangeRate);
						exchangeRateList.add(exchangeRate);
					}

					if(requestData.getCcy_code()==null || requestData.getCcy_code().trim().equalsIgnoreCase("")) {
						Collections.sort(exchangeRateList, compareCCY);
						
						obResponse.getList_fxrate().addAll(exchangeRateList);
					}else {
						Collections.sort(exchangeRateList, compareExchangeRate);
						obResponse.getList_fxrate().addAll(exchangeRateList);
					}
					
					
					
					obResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
				}
				else
				{
					obResponse.getObHeader().setStatusCode(MiBConstant.MCB_EXCHANGE_RATE_MISSING);
				}
				
			}
			else
			{
				obResponse.getObHeader().setStatusCode(MiBConstant.MIB_OMNI_PREFIX+vcResponse.getResponse_code() );
				obResponse.getObHeader().setStatusMessage(vcResponse.getResponse_desc());
			}
		
		} catch (Exception e) {
			 
			log.info(this.getClass().toString()+" ERROR Logout ", e);
			obResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			obResponse.getObHeader().setStatusMessage(e.getMessage());
		}
		
		response.setBDObject(obResponse);
		
		return response;
	}
	
	Comparator<ExchangeRate> compareExchangeRate = new Comparator<ExchangeRate>() {
	    @Override
	    public int compare(ExchangeRate o1, ExchangeRate o2) {
	        return o1.getPeriod().compareTo(o2.getPeriod());
	    }
	};
	
	
	Comparator<ExchangeRate> compareCCY = new Comparator<ExchangeRate>() {
		
 
		
	    @Override
	    public int compare(ExchangeRate o1, ExchangeRate o2) {

	    	List<String> orderCCy = new ArrayList();
	    	 
	    	orderCCy.add("USD");
	    	orderCCy.add("SGD");
	    	orderCCy.add("EUR");
	    	orderCCy.add("GBP");
	    	orderCCy.add("AUD");
	    	orderCCy.add("NZD");
	    	orderCCy.add("JPY");
	    	orderCCy.add("HKD");
	    	orderCCy.add("CHF");
	    	orderCCy.add("CAD");
	    	orderCCy.add("CNH");
	    	
	    	int ccy1 = orderCCy.indexOf(o1.getCcy_code());
	    	ccy1 = ccy1>=0?ccy1:100;
	    	int ccy2 = orderCCy.indexOf(o2.getCcy_code());
	    	ccy2 = ccy2>=0?ccy2:100;

	        return ccy1-ccy2;
	    }
	};

}
