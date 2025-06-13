package com.silverlake.mleb.mcb.task;
//package com.silverlake.mleb.mib.task;
//
//
//import java.math.BigDecimal;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.commons.lang3.StringUtils;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.http.ResponseEntity;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//import org.springframework.util.concurrent.ListenableFuture;
//
//import com.google.gson.Gson;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//import com.google.gson.reflect.TypeToken;
//import com.silverlake.mleb.mib.bean.OBUnitTrustBean;
//import com.silverlake.mleb.mib.constant.MiBConstant;
//import com.silverlake.mleb.mib.entity.JobStatusDetail;
//import com.silverlake.mleb.mib.entity.UTProductInfo;
//import com.silverlake.mleb.mib.entity.UTTermTransaction;
//import com.silverlake.mleb.mib.entity.dao.FundTransferDao;
//import com.silverlake.mleb.mib.entity.dao.JobStatusDetailDAO;
//import com.silverlake.mleb.mib.module.func.UTProductListService;
//import com.silverlake.mleb.mib.module.ib.bean.IBRequest;
//import com.silverlake.mleb.mib.module.ib.bean.IBResponse;
//import com.silverlake.mleb.mib.module.ib.ftbean.Parameters;
//import com.silverlake.mleb.mib.module.ib.utApi.UTServiceThread;
//import com.silverlake.mleb.mib.util.DateUtil;
//
//@Service
//public class RefreshUTProductList
//{
//	private static Logger log = LogManager.getLogger(RefreshUTProductList.class);
//
//	@Autowired ApplicationContext appContext;
//
//	@Autowired JobStatusDetailDAO jobStatusDetailDAO;
//	
//	@Autowired FundTransferDao fundTransferDao;
//	
//	private boolean hasError = false;
//	private boolean hasNull = false;
//	
////	@Scheduled(cron="0 0 0/6 * * ?")
//	//@Scheduled(cron="0 0/30 2-6 * * ?")
//	@Scheduled(cron="0 0/30 2-6,9-10 * * ?")	// LimKN 10-11-2017 Ary asked to change to 2-6am,9-10am every 30 mins
//// 	Jtrac OCBC2BUIXU-362
////	@Scheduled(cron="0 0/30 2-6 * * ?")
//	public void process()
//	{
//		try{							
//			log.info("----- START RefreshUTProductList");
//			
//			// If date is null or == today, skip the following process.
//			Date today = new Date();
//			Date existingUpdateDate = null;
//			JobStatusDetail jobStatusDetail = jobStatusDetailDAO.findByJobName(MiBConstant.SCHEDULED_JOB_UT_PRODUCT_LIST);
//			if(jobStatusDetail!=null)
//				existingUpdateDate = jobStatusDetail.getUpdateDate();
//			
//			//if(existingUpdateDate==null || (existingUpdateDate!=null && today.after(existingUpdateDate) && !DateUtil.isToday(existingUpdateDate)))
//			{	
//				hasError = false;
//				hasNull = false;
//				
//				ListenableFuture<ResponseEntity<IBResponse>> resultUTProdInfo = null;
//				ListenableFuture<ResponseEntity<IBResponse>> resultUTTermOfTrans = null;
//				Map<String, List<OBUnitTrustBean>> map13014 = null;
//                Map<String, List<OBUnitTrustBean>> map13015 = null;
//				
//				UTServiceThread utServiceThread = new UTServiceThread(appContext);
//				IBRequest unitTrustRequest = new IBRequest();
//				IBResponse unitTrustResponse13014 = new IBResponse();
//				IBResponse unitTrustResponse13015 = new IBResponse();
//				
//				// process request omni 13014 && process request omni 13015
//				processRequest(unitTrustRequest);
//				resultUTProdInfo = utServiceThread.postRestWebServiceAsync("13014", unitTrustRequest, "", "");
//				
//				resultUTTermOfTrans = utServiceThread.postRestWebServiceAsync("13015", unitTrustRequest, "", "");
//				
//				// Retrieve Unit Trust Product Information
//				unitTrustResponse13014 = utServiceThread.doUnitTrustProductInformation(resultUTProdInfo, unitTrustRequest, "", "");
//				
//				unitTrustResponse13014 = utServiceThread.doUnitTrustProductInformation(resultUTProdInfo, unitTrustRequest, "", "");
//                if(unitTrustResponse13014 == null || (unitTrustResponse13014!=null && !unitTrustResponse13014.getStatus().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS))) {
//                    hasError = true;
//                }
//                else {
//                    map13014 = checkResponse13014(unitTrustResponse13014);
//                }
//				
//				unitTrustResponse13015 = utServiceThread.doUnitTrustTermOfTransaction(resultUTTermOfTrans, unitTrustRequest, "","");
//				
//				if(unitTrustResponse13015 == null || (unitTrustResponse13015!=null && !unitTrustResponse13015.getStatus().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS))) {
//                    hasError = true;
//                }
//                else {
//                    // prepare the bean list that going to insert into mleb_ocbc.EXCHANGE_RATE
//                	map13015 = checkResponse13015(unitTrustResponse13015);
//                }
//				
//				if(!hasError && !hasNull && map13014!=null && map13015!=null)
//				{	
//					saveRecord13014(map13014);
//                    saveRecord13015(map13015);
//                    // update the updateDate in mleb_ocbc.job_status_detail
//					jobStatusDetailDAO.updateUpdateDate(MiBConstant.SCHEDULED_JOB_UT_PRODUCT_LIST);
//				}
//				
//			}
//				
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		
//		log.info("----- END RefreshUTProductList");
//	}
//	
//	private void processRequest(IBRequest unitTrustRequest){
//		Parameters parameters = new Parameters();
//		parameters.setLang(MiBConstant.LOCALE_EN.toUpperCase());
//		parameters.setCif("0000000000000000000");
//		parameters.setUserId("000000000000");
//		parameters.setChannel(MiBConstant.CHANNEL_MB);
//		parameters.setOperationCode(MiBConstant.GENERAL_OPERATIONCODE);
//		unitTrustRequest.setParameters(parameters);
//	}
//	
//	private Map<String, List<OBUnitTrustBean>> checkResponse13014(IBResponse unitTrustResponse)
//    {
//		
//		log.info("----- START RefreshUTProductList ---- checkResponse13014");
//		
//        Map<String, List<OBUnitTrustBean>> map = null;
//       
//        if (unitTrustResponse != null && unitTrustResponse.getResponseObject() != null)
//        {
//        	Gson gson = new Gson();
//            JsonObject jsonObject = new JsonParser().parse(
//            		gson.toJson(unitTrustResponse.getResponseObject())).getAsJsonObject();
//            if (jsonObject != null)
//            {
//                map = gson.fromJson(jsonObject,
//                    new TypeToken<HashMap<String, List<OBUnitTrustBean>>>()
//                    {
//                    }.getType());
//
//                if (map != null) {
//                	// check if the list return error
//                    for (Map.Entry<String, List<OBUnitTrustBean>> entry : map.entrySet()) {
//                        if(entry.getKey().equalsIgnoreCase("ERR")){
//                            hasError = true;
//                            break;
//                        }
//	                }
//                   
//                    // check if any one of the field is null or empty
//                    for (Map.Entry<String, List<OBUnitTrustBean>> entry : map.entrySet()) {
//                        if(!entry.getKey().equalsIgnoreCase("ERR")) {
//                            List<OBUnitTrustBean> obUnitTrustBeans = entry.getValue();
//                            if (obUnitTrustBeans != null && !obUnitTrustBeans.isEmpty()) {
//                                for (OBUnitTrustBean obUnitTrustBean : obUnitTrustBeans) {
//                                    if(StringUtils.isEmpty(obUnitTrustBean.getProductCategory())
//                                            || StringUtils.isEmpty(obUnitTrustBean.getProductID())
//                                            || StringUtils.isEmpty(obUnitTrustBean.getProductCode())
//                                            || StringUtils.isEmpty(obUnitTrustBean.getProductName())
//                                            || StringUtils.isEmpty(obUnitTrustBean.getNav())
//                                            || StringUtils.isEmpty(obUnitTrustBean.getNavDate())
//                                            || StringUtils.isEmpty(obUnitTrustBean.getUrlProspectus())
//                                            || StringUtils.isEmpty(obUnitTrustBean.getUrlFundFactSheet())
//                                            // OCBC2BUIXU-470
////                                            || StringUtils.isEmpty(obUnitTrustBean.getRiskProfileEn())
//                                            || StringUtils.isEmpty(obUnitTrustBean.getRiskProfile())
//                                            || StringUtils.isEmpty(obUnitTrustBean.getPercentageDay())
//                                            || StringUtils.isEmpty(obUnitTrustBean.getPercentageWeek())
//                                            || StringUtils.isEmpty(obUnitTrustBean.getPercentageMonth())
//                                            || StringUtils.isEmpty(obUnitTrustBean.getPercentageYear())) {
//                                        hasNull = true;
//                                        break;
//                                    }
//                                }
//                            }
//                        }
//                    }
//                               
//                }
//            }
//        }
//        
//        log.info("----- END RefreshUTProductList ---- checkResponse13014 ---- "+hasNull);
//        
//        return map;	
//    }
//	
//	private void saveRecord13014(Map<String, List<OBUnitTrustBean>> map)
//    {
//		
//		log.info("----- START RefreshUTProductList ---- saveRecord13014");
//		
//	    UTProductListService utProductListService = (UTProductListService)appContext.getBean("utProductListService");
//	    //delete old records
//	    utProductListService.removeUTProductInfo();
//	     
//	    // save new records
//	    for (Map.Entry<String, List<OBUnitTrustBean>> entry : map.entrySet())
//	    {
//            List<OBUnitTrustBean> obUnitTrustBeans = entry.getValue();
//            if (obUnitTrustBeans != null && !obUnitTrustBeans.isEmpty())
//            {
//                for (OBUnitTrustBean obUnitTrustBean : obUnitTrustBeans)
//                {
//                    UTProductInfo utProductInfo = new UTProductInfo();
//                   
//                    utProductInfo.setProductCategory(obUnitTrustBean.getProductCategory());
//                    utProductInfo.setProductID(obUnitTrustBean.getProductID());
//                    utProductInfo.setProductCode(obUnitTrustBean.getProductCode());
//                    utProductInfo.setProductName(obUnitTrustBean.getProductName());
//                    utProductInfo.setNav( (obUnitTrustBean.getNav() == null 
//                    		|| obUnitTrustBean.getNav().isEmpty()) ? new BigDecimal("0.0") : new BigDecimal(obUnitTrustBean.getNav()));
//                    utProductInfo.setNavDate(obUnitTrustBean.getNavDate());
//                    utProductInfo.setUrlProspectus(obUnitTrustBean.getUrlProspectus());
//                    utProductInfo.setUrlFundFactSheet(obUnitTrustBean.getUrlFundFactSheet());
//                    utProductInfo.setRiskProfile(obUnitTrustBean.getRiskProfile());
//                    utProductInfo.setRiskProfileEn(obUnitTrustBean.getRiskProfileEn());
//                    utProductInfo.setPercentageDay( (obUnitTrustBean.getPercentageDay() == null
//                            || obUnitTrustBean.getPercentageDay().isEmpty()) ? new BigDecimal("0.0") : new BigDecimal(obUnitTrustBean.getPercentageDay()));
//                    utProductInfo.setPercentageWeek( (obUnitTrustBean.getPercentageWeek() == null
//                            || obUnitTrustBean.getPercentageWeek().isEmpty()) ? new BigDecimal("0.0") : new BigDecimal(obUnitTrustBean.getPercentageWeek()));
//                    utProductInfo.setPercentageMonth( (obUnitTrustBean.getPercentageMonth() == null
//                            || obUnitTrustBean.getPercentageMonth().isEmpty()) ? new BigDecimal("0.0") : new BigDecimal(obUnitTrustBean.getPercentageMonth()));
//                    utProductInfo.setPercentageYear( (obUnitTrustBean.getPercentageYear() == null
//                            || obUnitTrustBean.getPercentageYear().isEmpty()) ? new BigDecimal("0.0") : new BigDecimal(obUnitTrustBean.getPercentageYear()));
//                   
//                    if(utProductInfo.getProductCode() != null && !utProductInfo.getProductCode().isEmpty())
//                    	utProductListService.insertEntity(utProductInfo);
//                                               
//                }
//            }
//	    }
//	    
//	    log.info("----- END RefreshUTProductList ---- saveRecord13014");
//    }
//
//    private Map<String, List<OBUnitTrustBean>> checkResponse13015(IBResponse unitTrustResponse)
//    {
//    	log.info("----- START RefreshUTProductList ---- checkResponse13015");
//    	
//        Map<String, List<OBUnitTrustBean>> map = null;
//       
//        if (unitTrustResponse != null && unitTrustResponse.getResponseObject() != null)
//        {
//            Gson gson = new Gson();
//            JsonObject jsonObject = new JsonParser().parse(
//            		gson.toJson(unitTrustResponse.getResponseObject())).getAsJsonObject();
//            if (jsonObject != null)
//            {
//                map = gson.fromJson(jsonObject, 
//                		new TypeToken<HashMap<String, List<OBUnitTrustBean>>>()
//	                    {
//	                    }.getType());
//
//                if (map != null) {
//                	// check if the list return error
//                    for (Map.Entry<String, List<OBUnitTrustBean>> entry : map.entrySet()) {
//	                    if(entry.getKey().equalsIgnoreCase("ERR")){
//	                                    hasError = true;
//	                                    break;
//	                    }
//                    }
//                   
//              	  	// check if any one of the field is null or empty
//                    for (Map.Entry<String, List<OBUnitTrustBean>> entry : map.entrySet()) {
//                        if(!entry.getKey().equalsIgnoreCase("ERR")) {
//                            List<OBUnitTrustBean> obUnitTrustBeans = entry.getValue();
//                            if (obUnitTrustBeans != null && !obUnitTrustBeans.isEmpty()) {
//                                for (OBUnitTrustBean obUnitTrustBean : obUnitTrustBeans) {
//                                    if(StringUtils.isEmpty(obUnitTrustBean.getProductCategory())
//                                    		|| StringUtils.isEmpty(obUnitTrustBean.getProductID())
//                                            || StringUtils.isEmpty(obUnitTrustBean.getProductCode())
//                                            || StringUtils.isEmpty(obUnitTrustBean.getProductName())
//                                            || StringUtils.isEmpty(obUnitTrustBean.getProductCurrency())
//                                            || StringUtils.isEmpty(obUnitTrustBean.getMinSubsNew())
//                                            || StringUtils.isEmpty(obUnitTrustBean.getMinSubsAdd())
//                                            || StringUtils.isEmpty(obUnitTrustBean.getSubsFee())
//                                            || StringUtils.isEmpty(obUnitTrustBean.getMinRedempt())
//                                            || StringUtils.isEmpty(obUnitTrustBean.getRedemptFee())
//                                            || StringUtils.isEmpty(obUnitTrustBean.getMinSwitch())
//                                            || StringUtils.isEmpty(obUnitTrustBean.getSwitchFee())
//                                            || StringUtils.isEmpty(obUnitTrustBean.getMinUnitAfterTrx())
//                                            || StringUtils.isEmpty(obUnitTrustBean.getUrlProspectus())
//                                            || StringUtils.isEmpty(obUnitTrustBean.getUrlFundFactSheet())
//                                            || StringUtils.isEmpty(obUnitTrustBean.getProductCategoryEn())
//                                            || StringUtils.isEmpty(obUnitTrustBean.getRiskProfile())
//                                            || StringUtils.isEmpty(obUnitTrustBean.getRiskProfileEn())
//                                            || StringUtils.isEmpty(obUnitTrustBean.getMinRedemptNom())
//                                            || StringUtils.isEmpty(obUnitTrustBean.getMinSwitchNom())
//                                            || StringUtils.isEmpty(obUnitTrustBean.getIsVisible())) {
//                                        hasNull = true;
//                                        break;
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//       
//        log.info("----- END RefreshUTProductList ---- checkResponse13015 ---- "+hasNull);
//        
//        return map;
//    }
//   
//    private void saveRecord13015(Map<String, List<OBUnitTrustBean>> map)
//    {
//    	log.info("----- START RefreshUTProductList ---- saveRecord13015");
//    	
//        UTProductListService utProductListService = (UTProductListService)appContext.getBean("utProductListService");
//        // delete old records
//        utProductListService.removeUTTermTransaction();
//       
//        // save new records
//        for (Map.Entry<String, List<OBUnitTrustBean>> entry : map.entrySet())
//        {
//            List<OBUnitTrustBean> obUnitTrustBeans = entry.getValue();
//            if (obUnitTrustBeans != null && !obUnitTrustBeans.isEmpty())
//            {
//                for (OBUnitTrustBean obUnitTrustBean : obUnitTrustBeans)
//                {
//	                UTTermTransaction utTermTransaction = new UTTermTransaction();
//	               
//	                utTermTransaction.setProductCategory(obUnitTrustBean.getProductCategory());
//	                utTermTransaction.setProductID(obUnitTrustBean.getProductID());
//	                utTermTransaction.setProductCode(obUnitTrustBean.getProductCode());
//	                utTermTransaction.setProductName(obUnitTrustBean.getProductName());
//	                utTermTransaction.setProductCurrency(obUnitTrustBean.getProductCurrency());
//	                utTermTransaction.setMinSubsNew(obUnitTrustBean.getMinSubsNew());
//	                utTermTransaction.setMinSubsAdd(obUnitTrustBean.getMinSubsAdd());
//	                utTermTransaction.setSubsFee(obUnitTrustBean.getSubsFee());
//	                utTermTransaction.setMinRedempt(obUnitTrustBean.getMinRedempt());
//	                utTermTransaction.setRedemptFee(obUnitTrustBean.getRedemptFee());
//	                utTermTransaction.setMinSwitch(obUnitTrustBean.getMinSwitch());
//	                utTermTransaction.setSwitchFee(obUnitTrustBean.getSwitchFee());
//	                utTermTransaction.setMinUnitAfterTrx(obUnitTrustBean.getMinUnitAfterTrx());
//	                utTermTransaction.setUrlProspectus(obUnitTrustBean.getUrlProspectus());
//	                utTermTransaction.setUrlFundFactSheet(obUnitTrustBean.getUrlFundFactSheet());
//	                utTermTransaction.setProductCategoryEn(obUnitTrustBean.getProductCategoryEn());
//	                utTermTransaction.setRiskProfile(obUnitTrustBean.getRiskProfile());
//	                utTermTransaction.setRiskProfileEn(obUnitTrustBean.getRiskProfileEn());
//	                utTermTransaction.setMinRedemptNom(obUnitTrustBean.getMinRedemptNom());
//	                utTermTransaction.setMinSwitchNom(obUnitTrustBean.getMinSwitchNom());
//	                utTermTransaction.setIsVisible(obUnitTrustBean.getIsVisible());
//	               
//	                if(utTermTransaction.getProductCode() != null && !utTermTransaction.getProductCode().isEmpty())
//                        utProductListService.insertEntity(utTermTransaction);
//                }
//            }
//        } 
//        log.info("----- END RefreshUTProductList ---- saveRecord13015");
//    }
//}
//
