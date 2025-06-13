package com.silverlake.mleb.mcb.task;
//package com.silverlake.mleb.mib.task;
//
//
//import java.math.BigDecimal;
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//import com.silverlake.mleb.mib.module.func.FTExchangeRateService;
//import com.silverlake.mleb.mib.module.func.JobStatusDetailService;
//import com.silverlake.mleb.mib.module.ib.ftbean.Parameters;
//import com.silverlake.mleb.mib.bean.FTBankListBean;
//import com.silverlake.mleb.mib.constant.MiBConstant;
//import com.silverlake.mleb.mib.entity.ExchangeRate;
//import com.silverlake.mleb.mib.entity.FTBankList;
//import com.silverlake.mleb.mib.entity.JobStatusDetail;
//import com.silverlake.mleb.mib.entity.dao.FundTransferDao;
//import com.silverlake.mleb.mib.entity.dao.JobStatusDetailDAO;
//import com.silverlake.mleb.mib.module.ib.acctApi.AcctService;
//import com.silverlake.mleb.mib.module.ib.bean.IBRequest;
//import com.silverlake.mleb.mib.module.ib.bean.IBResponse;
//import com.silverlake.mleb.mib.module.ib.bean.ListExchangeRate;
//import com.silverlake.mleb.mib.module.ib.bean.ResponseObject;
//import com.silverlake.mleb.mib.module.ib.ftApi.FTService;
//import com.silverlake.mleb.mib.util.DateUtil;
//import com.silverlake.mleb.mib.util.MapperUtil;
//
//@Service
//public class RefreshExchangeRate
//{
//	private static Logger log = LogManager.getLogger(RefreshExchangeRate.class);
//
//	@Autowired ApplicationContext appContext;
//
//	@Autowired JobStatusDetailDAO jobStatusDetailDAO;
//
//	@Autowired FundTransferDao fundTransferDao;
//
//	@Scheduled(cron="0 0/5 7-10,15-18 * * ?")
//	public void process()
//	{
//		try{							
//			log.info("----- START RefreshExchangeRate");
//
//			// If date is null or == today, skip the following process.
//			//			Date today = new Date();
//			//			Date existingUpdateDate = null;
//			//			JobStatusDetail jobStatusDetail = jobStatusDetailDAO.findByJobName(MiBConstant.SCHEDULED_JOB_EXG_RATE);
//			//			if(jobStatusDetail!=null)
//			//				existingUpdateDate = jobStatusDetail.getUpdateDate();
//			//			
//			//			if(existingUpdateDate==null || (existingUpdateDate!=null && today.after(existingUpdateDate) && !DateUtil.isToday(existingUpdateDate)))
//			//			{							
//			AcctService acctService = new AcctService(appContext);
//
//			IBRequest exchangeRequest = new IBRequest();
//			IBResponse exchangeResponse = new IBResponse();
//
//			processRequest(exchangeRequest);
//			// Retrieve Exchange Rate
//			exchangeResponse = acctService.inquiryExchangeRate(exchangeRequest, "", "");
//
//			// If Exchange Rate is successful
//			if(exchangeResponse != null && exchangeResponse.getStatus().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS)){
//				// prepare the bean list that going to insert into mleb_ocbc.EXCHANGE_RATE
//				processResponse(exchangeResponse);
//
//				// update the updateDate in mleb_ocbc.job_status_detail
//				jobStatusDetailDAO.updateUpdateDate(MiBConstant.SCHEDULED_JOB_EXG_RATE);
//			}
//
//
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//
//		log.info("----- END RefreshExchangeRate");
//	}
//
//	private void processRequest(IBRequest exchangeRequest){
//		//set request to retrieve transaction limit
//		Parameters exchangeParam = new Parameters();
//		exchangeParam.setLang(MiBConstant.LOCALE_EN.toUpperCase());
//		exchangeParam.setCif("0000000000000000000");
//		exchangeParam.setUserId("000000000000");
//		exchangeParam.setChannel(MiBConstant.CHANNEL_MB);
//		exchangeParam.setOperationCode(MiBConstant.GENERAL_OPERATIONCODE);
//		exchangeRequest.setParameters(exchangeParam);
//	}
//
//	private void processResponse(IBResponse exchangeResponse){
//		FTExchangeRateService fTExchangeRateService = (FTExchangeRateService)appContext.getBean("fTExchangeRateService");
//		List<ExchangeRate> exchangeRatelistDB = fTExchangeRateService.retrieveAllExchangeRate();
//		ResponseObject responseObject = (ResponseObject) exchangeResponse.getResponseObject();
//		List<ListExchangeRate> listExchangeRate = responseObject.getListExchangeRate();
//		//Change Request by Jacky Wong and OCBC 
//		//if exchange rate table is empty
//		if(exchangeRatelistDB!=null && exchangeRatelistDB.isEmpty()){
//			// delete old records
//			fTExchangeRateService.removeExchangeRate();
//
//			if(listExchangeRate != null && listExchangeRate.size() > 0){
//				for(ListExchangeRate exchangeRate : listExchangeRate){
//					ExchangeRate exchangeRateEntity = new ExchangeRate();
//					//					MapperUtil.copyFields(exchangeRate, exchangeRateEntity);
//					exchangeRateEntity.setBankBuyRate((exchangeRate.getBankBuyRate() != null && !exchangeRate.getBankBuyRate().isEmpty()) ? new BigDecimal(exchangeRate.getBankBuyRate()) : new BigDecimal("0.00"));
//					exchangeRateEntity.setBankSellRate((exchangeRate.getBankSellRate() != null && !exchangeRate.getBankSellRate().isEmpty()) ? new BigDecimal(exchangeRate.getBankSellRate()) : new BigDecimal("0.00"));
//					exchangeRateEntity.setCbBuyRate((exchangeRate.getCbBuyRate() != null && !exchangeRate.getCbBuyRate().isEmpty()) ? new BigDecimal(exchangeRate.getCbBuyRate()) : new BigDecimal("0.00"));
//					exchangeRateEntity.setCbRate((exchangeRate.getCbRate() != null && !exchangeRate.getCbRate().isEmpty()) ? new BigDecimal(exchangeRate.getCbRate()) : new BigDecimal("0.00"));
//					exchangeRateEntity.setCbSellRate((exchangeRate.getCbSellRate() != null && !exchangeRate.getCbSellRate().isEmpty()) ? new BigDecimal(exchangeRate.getCbSellRate()) : new BigDecimal("0.00"));
//					exchangeRateEntity.setCurrencyCode(exchangeRate.getCurrencyCode());
//					exchangeRateEntity.setCurrencyUnit((exchangeRate.getCurrencyUnit() != null && !exchangeRate.getCurrencyUnit().isEmpty()) ? Integer.parseInt(exchangeRate.getCurrencyUnit()) : null);
//					exchangeRateEntity.setPriorityIndex((exchangeRate.getPriorityIndex() != null && !exchangeRate.getPriorityIndex().isEmpty()) ? Integer.parseInt(exchangeRate.getPriorityIndex()) : null);
//					DateFormat formatter = new SimpleDateFormat(MiBConstant.OMNI_DATE_FORMAT);
//					try {
//						if(exchangeRate.getTimeCreated() != null && !exchangeRate.getTimeCreated().isEmpty())
//							exchangeRateEntity.setTimeCreated((Date)formatter.parse(exchangeRate.getTimeCreated()));
//					} catch (ParseException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					exchangeRateEntity.setTtBuyRate((exchangeRate.getTtBuyRate() != null && !exchangeRate.getTtBuyRate().isEmpty()) ? new BigDecimal(exchangeRate.getTtBuyRate()) : new BigDecimal("0.00"));
//					exchangeRateEntity.setTtSellRate((exchangeRate.getTtSellRate() != null && !exchangeRate.getTtSellRate().isEmpty()) ? new BigDecimal(exchangeRate.getTtSellRate()) : new BigDecimal("0.00"));
//
//					fTExchangeRateService.insertEntity(exchangeRateEntity);
//				}
//			}
//		}
//		else{
//			boolean proceed = false;
//			for(ListExchangeRate exchangeRate : listExchangeRate){
//				for(ExchangeRate exchangeRateDB : exchangeRatelistDB){
//					if(exchangeRate.getCurrencyCode().equalsIgnoreCase(exchangeRateDB.getCurrencyCode())){
//						if(!exchangeRate.getBankBuyRate().equalsIgnoreCase(exchangeRateDB.getBankBuyRate().toPlainString())){
//							exchangeRateDB.setBankBuyRate((exchangeRate.getBankBuyRate() != null && !exchangeRate.getBankBuyRate().isEmpty()) ? new BigDecimal(exchangeRate.getBankBuyRate()) : new BigDecimal("0.00"));
//							proceed = true;
//						}
//						if(!exchangeRate.getBankSellRate().equalsIgnoreCase(exchangeRateDB.getBankSellRate().toPlainString())){
//							exchangeRateDB.setBankSellRate((exchangeRate.getBankSellRate() != null && !exchangeRate.getBankSellRate().isEmpty()) ? new BigDecimal(exchangeRate.getBankSellRate()) : new BigDecimal("0.00"));
//							proceed = true;
//						}
//						if(!exchangeRate.getCbBuyRate().equalsIgnoreCase(exchangeRateDB.getCbBuyRate().toPlainString())){
//							exchangeRateDB.setCbBuyRate((exchangeRate.getCbBuyRate() != null && !exchangeRate.getCbBuyRate().isEmpty()) ? new BigDecimal(exchangeRate.getCbBuyRate()) : new BigDecimal("0.00"));
//							proceed = true;
//						}
//						if(!exchangeRate.getCbRate().equalsIgnoreCase(exchangeRateDB.getCbRate().toPlainString())){
//							exchangeRateDB.setCbRate((exchangeRate.getCbRate() != null && !exchangeRate.getCbRate().isEmpty()) ? new BigDecimal(exchangeRate.getCbRate()) : new BigDecimal("0.00"));
//							proceed = true;
//						}
//						if(!exchangeRate.getCbSellRate().equalsIgnoreCase(exchangeRateDB.getCbSellRate().toPlainString())){
//							exchangeRateDB.setCbSellRate((exchangeRate.getCbSellRate() != null && !exchangeRate.getCbSellRate().isEmpty()) ? new BigDecimal(exchangeRate.getCbSellRate()) : new BigDecimal("0.00"));
//							proceed = true;
//						}
//						if(!exchangeRate.getTtBuyRate().equalsIgnoreCase(exchangeRateDB.getTtBuyRate().toPlainString())){
//							exchangeRateDB.setTtBuyRate((exchangeRate.getTtBuyRate() != null && !exchangeRate.getTtBuyRate().isEmpty()) ? new BigDecimal(exchangeRate.getTtBuyRate()) : new BigDecimal("0.00"));
//							proceed = true;
//						}
//						if(!exchangeRate.getTtSellRate().equalsIgnoreCase(exchangeRateDB.getTtSellRate().toPlainString())){
//							exchangeRateDB.setTtSellRate((exchangeRate.getTtSellRate() != null && !exchangeRate.getTtSellRate().isEmpty()) ? new BigDecimal(exchangeRate.getTtSellRate()) : new BigDecimal("0.00"));
//							proceed = true;
//						}
//						if(proceed){
//							fTExchangeRateService.updateEntity(exchangeRateDB);
//						}
//
//					}
//
//				}
//			}
//		}
//
//	}
//
//
//}
//
//
