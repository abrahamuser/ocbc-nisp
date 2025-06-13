package com.silverlake.mleb.mcb.task;
//package com.silverlake.mleb.mib.task;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//import java.util.Set;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//import com.silverlake.mleb.mib.constant.MiBConstant;
//import com.silverlake.mleb.mib.entity.AOParamCache;
//import com.silverlake.mleb.mib.entity.dao.MLEBMIBDAO;
//import com.silverlake.mleb.mib.entity.dao.MessagePropertiesDAO;
//import com.silverlake.mleb.mib.module.ib.aoApi.AOService;
//import com.silverlake.mleb.mib.module.ib.aobean.Parameters;
//import com.silverlake.mleb.mib.module.ib.aobean.ResponseObject;
//import com.silverlake.mleb.mib.module.ib.bean.IBRequest;
//import com.silverlake.mleb.mib.module.ib.bean.IBResponse;
//@Service
//public class RefreshParamFromOmniToDB
//{
//	private static Logger log = LogManager.getLogger(RefreshParamFromOmniToDB.class);
//
//	@Autowired 
//	ApplicationContext appContext;
//	
//	@Autowired
//	MLEBMIBDAO dao;
//	
//	@Autowired
//	MessagePropertiesDAO msgdao;
//	
//	@Autowired TaskRenewSession renewSession;
//	
////	@Scheduled(fixedDelay=(10000*120)) //Run every 5 min
//	public void process()
//	{
//		log.info("Update Param Table Start");
//		AOService aoService = new AOService(appContext);
//		IBRequest aoRequest = new IBRequest();
//		IBResponse aoResponse = new IBResponse();
//		Parameters parameters = new Parameters();
//		parameters.setLang(MiBConstant.LOCALE_EN.toUpperCase());
//		aoRequest.setParameters(parameters);
//		
//		//get all the key and value from omni
//		aoResponse = aoService.getParamsAccountOnBoarding(aoRequest, "", "");
//		if(aoResponse != null && aoResponse.getStatus().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS) && aoResponse.getStatus().equalsIgnoreCase("00")){
//			dao.removeParamCache();
//			ResponseObject respObject = (ResponseObject) aoResponse.getResponseObject();
//			// pass key, value and type to entity
//			if(respObject.getListParamWorkType()!=null){
//				processValueToEntity(respObject.getListParamWorkType(), MiBConstant.PARAM_TYPE_WORK_TYPE);
//			}
//			if(respObject.getListParamSourceOfFund()!=null){
//				processValueToEntity(respObject.getListParamSourceOfFund(), MiBConstant.PARAM_TYPE_SOURCE_FUND);
//			}
//			if(respObject.getListParamPurpose()!=null){
//				processValueToEntity(respObject.getListParamPurpose(), MiBConstant.PARAM_TYPE_PURPOSE);
//			}
//			if(respObject.getListParamMonthlySpending()!=null){
//				processValueToEntity(respObject.getListParamMonthlySpending(), MiBConstant.PARAM_TYPE_MONTHLY_SPENDING);
//			}
//			if(respObject.getListParamMonthlySalary()!=null){
//				processValueToEntity(respObject.getListParamMonthlySalary(), MiBConstant.PARAM_TYPE_MONTHLY_SALARY);
//			}
//			if(respObject.getListParamJabatan()!=null){
//				processValueToEntity(respObject.getListParamJabatan(), MiBConstant.PARAM_TYPE_JABATAN);
//			}
//			if(respObject.getListParamBusinessIndustry()!=null){
//				processValueToEntity(respObject.getListParamBusinessIndustry(), MiBConstant.PARAM_TYPE_BUSINESS_INDUSTRY);
//			}
//			if(respObject.getListEducation()!=null){
//				processValueToEntity(respObject.getListEducation(), MiBConstant.PARAM_TYPE_EDUCATION);
//			}
//			if(respObject.getListNumberOfDependants()!=null){
//				processValueToEntity(respObject.getListNumberOfDependants(), MiBConstant.PARAM_TYPE_NUMBER_DEPENDANTS);
//			}
//			if(respObject.getListCardDeliveryLocation()!=null){
//				processValueToEntity(respObject.getListCardDeliveryLocation(), MiBConstant.PARAM_TYPE_CARD_DELIVERY_LOCATION);
//			}
//			if(respObject.getListHomeCurrentStatus()!=null){
//				processValueToEntity(respObject.getListHomeCurrentStatus(), MiBConstant.PARAM_TYPE_HOME_CURRENT_STATUS);
//			}
//			if(respObject.getListNoOfCreditCardOwned()!=null){
//				processValueToEntity(respObject.getListNoOfCreditCardOwned(), MiBConstant.PARAM_TYPE_NO_CREDIT_CARD_OWNED);
//			}
//			if(respObject.getListCreditCardStatement()!=null){
//				processValueToEntity(respObject.getListCreditCardStatement(), MiBConstant.PARAM_TYPE_CREDIT_CARD_STATEMENT);
//			}
//			if(respObject.getListHardcopyDeliveryLocation()!=null){
//				processValueToEntity(respObject.getListHardcopyDeliveryLocation(), MiBConstant.PARAM_TYPE_HARDCOPY_DELIVERY_LOCATION);
//			}
//			if(respObject.getListSupplementLimit()!=null){
//				processValueToEntity(respObject.getListSupplementLimit(), MiBConstant.PARAM_TYPE_SUPPLEMENT_LIMIT);
//			}
//			if(respObject.getListPurposeKPR()!=null){
//				processValueToEntity(respObject.getListPurposeKPR(), MiBConstant.PARAM_TYPE_PURPOSE_KPR);
//			}
//			if(respObject.getListPurposeKPM()!=null){
//				processValueToEntity(respObject.getListPurposeKPM(), MiBConstant.PARAM_TYPE_PURPOSE_KPM);
//			}
//			if(respObject.getListTenorKPR()!=null){
//				processValueToEntity(respObject.getListTenorKPR(), MiBConstant.PARAM_TYPE_TENOR_KPR);
//			}
//			if(respObject.getListTenorKPM()!=null){
//				processValueToEntity(respObject.getListTenorKPM(), MiBConstant.PARAM_TYPE_TENOR_KPM);
//			}
//		}
//		else{
//			log.info("OMNI Error Status Code :: " + aoResponse.getStatus());
//		}
//		log.info("Update Param Table End");
//	}
//	
//	public void processValueToEntity(Map<String,String> value , String type){
//		Set<Entry<String,String>> setValue = value.entrySet();
//	    List<Entry<String,String>> listArr = new ArrayList<Entry<String,String>>(setValue);
//	    for(Map.Entry<String, String> result : listArr){
//	    	AOParamCache aoParamCache = new AOParamCache();
//	    	aoParamCache.setParamKey(result.getKey());
//	    	aoParamCache.setParamValue(result.getValue());
//	    	aoParamCache.setParamType(type);
//	    	// insert the entity to DB
//	    	dao.insertEntity(aoParamCache);
//	    }		
//	}
//}
//
