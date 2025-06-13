package com.silverlake.mleb.mcb.module.func.transaction;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.ObBiFastBean;
import com.silverlake.mleb.mcb.bean.ObSessionCache;
import com.silverlake.mleb.mcb.bean.ObTransactionRequest;
import com.silverlake.mleb.mcb.bean.ObTransactionResponse;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.entity.GeneralCode;
import com.silverlake.mleb.mcb.entity.dao.GeneralCodeDAO;
import com.silverlake.mleb.mcb.init.InitApp;
import com.silverlake.mleb.mcb.module.common.CacheSessionFuncServices;
import com.silverlake.mleb.mcb.module.func.RetrieveAppStatInfo_mcb;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.holiday.ListHolidayCalendar;
import com.silverlake.mleb.mcb.module.vc.holiday.ListHolidayProduct;
import com.silverlake.mleb.mcb.module.vc.others.CategoryList;
import com.silverlake.mleb.mcb.module.vc.others.InquiryTransTimeBatchRequestData;
import com.silverlake.mleb.mcb.module.vc.others.InquiryTransTimeBatchResponseData;
import com.silverlake.mleb.mcb.module.vc.others.LLDRequestData;
import com.silverlake.mleb.mcb.module.vc.others.PaymentPurposeList;
import com.silverlake.mleb.mcb.module.vc.others.PaymentPurposeResponseData;
import com.silverlake.mleb.mcb.module.vc.others.RegulatoryInformationRequestData;
import com.silverlake.mleb.mcb.module.vc.others.RegulatoryInformationResponseData;
import com.silverlake.mleb.mcb.module.vc.others.RelationshipStatusList;
import com.silverlake.mleb.mcb.module.vc.others.ResidentStatusList;
import com.silverlake.mleb.mcb.module.vc.transaction.CheckCutOffTimeRequestData;
import com.silverlake.mleb.mcb.module.vc.transaction.CheckCutOffTimeResponseData;
import com.silverlake.mleb.mcb.util.DateUtil;

/**
 * Purpose: Check transaction fund transfer cutoff and get it's related parameters
 * First check cut off time, if within cut off returned allowProcess false
 * When allowProcess is false, further checking is cross currency transaction is allowed.
 * If cross currency transaction is not allowed (false), return the list of from/to currency which are not allowed.  
 * 
 * Omni Web Services:
 * holiday/calendar
 * holiday/product
 * transaction/check_cot for cutoff time
 * others/regulatory
 * transaction/time-batch/inquiry
 * @author Alex Loo
 *
 */
@Service
public class TransactionFundTransferStep1 extends CacheSessionFuncServices<ObTransactionRequest, ObTransactionResponse, ObSessionCache>{
	
	private static Logger log = LogManager.getLogger(TransactionFundTransferStep1.class);
	
	private Comparator<ListHolidayCalendar> holidayCalendarComparator = new Comparator<ListHolidayCalendar>(){
		@Override
		public int compare(ListHolidayCalendar o1, ListHolidayCalendar o2) {
			if ( o1.getCalendar_date() == null ) return o2.getCalendar_date() == null ? 0 : 1;
		    if ( o2.getCalendar_date() == null ) return 1;
			return o1.getCalendar_date().compareTo(o2.getCalendar_date());
		}
	};
	
	@Autowired GeneralCodeDAO gnDao;
	
	@Override
	public void processInternalWithCache(String locale, String sessionId, String trxId, ObTransactionRequest requestBean, ObTransactionResponse responseBean, ObSessionCache cacheBean, VCGenericService vcService) throws Exception {
		Calendar now = Calendar.getInstance();
		
		String transferServiceType = requestBean.getTransferServiceType();
		/*
		 * Transaction that required to check cutoff
		 * - Own account transfer (OAT)
		 * - Internal Fund Transfer (IFT)
		 * - ONLINE (OLT)
		 * - LLG
		 * - RTGS
		 */
		if(transferServiceType.equals(MiBConstant.TRANS_SERVICE_TYPE_OAT) || transferServiceType.equals(MiBConstant.TRANS_SERVICE_TYPE_IFT) ||
				transferServiceType.equals(MiBConstant.TRANS_SERVICE_TYPE_OLT) || transferServiceType.equals(MiBConstant.TRANS_SERVICE_TYPE_LLG) ||  
				transferServiceType.equals(MiBConstant.TRANS_SERVICE_TYPE_RTGS) || transferServiceType.equals(MiBConstant.TRANS_SERVICE_TYPE_TT) || 
				transferServiceType.equals(MiBConstant.TRANS_SERVICE_TYPE_FAST)){
			//Check for cutoff time
			CheckCutOffTimeRequestData checkCutOffTimeRequestData = new CheckCutOffTimeRequestData();
			processRequestCheckCutOffTime(checkCutOffTimeRequestData, requestBean, transferServiceType);
			VCResponse<CheckCutOffTimeResponseData> vcCheckCutOffTimeResponse = vcService.callOmniVC(
					VCMethodConstant.REST_METHODS.TRANSACTION_CHECK_CUT_OFF_TIME, 
					checkCutOffTimeRequestData, 
					CheckCutOffTimeResponseData.class, 
					true);
			if(processVCResponseError(vcCheckCutOffTimeResponse, responseBean)){
				return;
			}
			
			CheckCutOffTimeResponseData checkCutOffTimeResponseData = vcCheckCutOffTimeResponse.getData();
			processResponseCheckCutOffTime(locale, checkCutOffTimeResponseData, responseBean);
			
			//Global cross currency checking 
			if(requestBean.getObUser().getIsEligiblePurchaseForeignCcy() != null && requestBean.getObUser().getIsEligiblePurchaseForeignCcy()){
				responseBean.setIsEligiblePurchaseForeignCcy(true);
			}else{
				responseBean.setIsEligiblePurchaseForeignCcy(false);
			}
			
			//Determine it is cross ccy allowed
			if(responseBean.getAllowProcess()){
				com.silverlake.mleb.mcb.module.vc.holiday.RequestData vcHolidayRequestData = new com.silverlake.mleb.mcb.module.vc.holiday.RequestData();
				com.silverlake.mleb.mcb.module.vc.holiday.ResponseData vcHolidayResponseData;
				processRequestHolidayProduct(vcHolidayRequestData, requestBean, transferServiceType, now);
				VCResponse<com.silverlake.mleb.mcb.module.vc.holiday.ResponseData> vcResponse = vcService.callOmniVC(
						VCMethodConstant.REST_METHODS.HOLIDAY_PRODUCT, 
						vcHolidayRequestData, 
						com.silverlake.mleb.mcb.module.vc.holiday.ResponseData.class, 
						true);
				if(processVCResponseError(vcResponse, responseBean)){
					return;
				}
				
				vcHolidayResponseData = vcResponse.getData();
				processResponseHolidayProduct(vcHolidayResponseData, requestBean, responseBean, now, vcService);
			}
		}else{
			responseBean.setAllowProcess(true);
		}
		
		responseBean.setRecurringList(new LinkedHashMap<String, String>(2));
		List<GeneralCode> gnList = gnDao.findByGnCodeType(MiBConstant.FT_RECURRING, locale);
		for(GeneralCode gnItem:gnList){
			responseBean.getRecurringList().put(gnItem.getGnCode(), gnItem.getGnCodeDescription());
		}
		
		responseBean.setFxOptionList(new LinkedHashMap<String, String>(2));
		gnList = gnDao.findByGnCodeType(MiBConstant.FT_FX_OPTION, locale);
		for(GeneralCode gnItem:gnList){
			responseBean.getFxOptionList().put(gnItem.getGnCode(), gnItem.getGnCodeDescription());
		}
		
		//Only TT required charges method
		if(transferServiceType.equals(MiBConstant.TRANS_SERVICE_TYPE_TT) ){
			responseBean.setChargesMethodList(new LinkedHashMap<String, String>(2));
			gnList = gnDao.findByGnCodeType(MiBConstant.FT_TT_CHARGES_METHOD, locale);
			for(GeneralCode gnItem:gnList){
				responseBean.getChargesMethodList().put(gnItem.getGnCode(), gnItem.getGnCodeDescription());
			}
		}
		
		//Get regulatory information
		if(transferServiceType.equals(MiBConstant.TRANS_SERVICE_TYPE_LLG) || 
				transferServiceType.equals(MiBConstant.TRANS_SERVICE_TYPE_RTGS) ||
				transferServiceType.equals(MiBConstant.TRANS_SERVICE_TYPE_TT) ){
			RegulatoryInformationRequestData regulatoryInfoRequestData = new RegulatoryInformationRequestData();
			RegulatoryInformationResponseData regulatoryInfoResponseData;
			VCResponse<RegulatoryInformationResponseData> vcResponse = vcService.callOmniVC(
					VCMethodConstant.REST_METHODS.OTHERS_REGULATORY, 
					regulatoryInfoRequestData, 
					RegulatoryInformationResponseData.class, 
					true);
			if(processVCResponseError(vcResponse, responseBean)){
				return;
			}
			
			responseBean.setCategoryList(new LinkedHashMap<String, String>(10));
			regulatoryInfoResponseData = vcResponse.getData();
			if(regulatoryInfoResponseData.getCategory_list() != null){
				for(CategoryList categoryList:regulatoryInfoResponseData.getCategory_list()){
					if(locale.equalsIgnoreCase(MiBConstant.LANG_EN)){
						responseBean.getCategoryList().put(categoryList.getCategory_code(), categoryList.getCategory_name_en());
					}else if (locale.equalsIgnoreCase(MiBConstant.LANG_CN_FE) || locale.equalsIgnoreCase(MiBConstant.LANG_CN_FE_IOS)){
						responseBean.getCategoryList().put(categoryList.getCategory_code(), categoryList.getCategory_name_cn());
					}else{
						responseBean.getCategoryList().put(categoryList.getCategory_code(), categoryList.getCategory_name_id());
					}
				}
			}
			
			responseBean.setResidentStatusList(new LinkedHashMap<String, String>(10));
			if(regulatoryInfoResponseData.getResident_status() != null){
				for(ResidentStatusList residentStatusList:regulatoryInfoResponseData.getResident_status()){
					if(locale.equalsIgnoreCase(MiBConstant.LANG_EN)){
						responseBean.getResidentStatusList().put(residentStatusList.getRes_code(), residentStatusList.getRes_name_en());
					}else if(locale != null  && locale.toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase())){
						responseBean.getResidentStatusList().put(residentStatusList.getRes_code(), residentStatusList.getRes_name_cn());
					}else{
						responseBean.getResidentStatusList().put(residentStatusList.getRes_code(), residentStatusList.getRes_name_id());
					}
				}
			}
			
			//Only TT required relationship and payment purpose list
			if(transferServiceType.equals(MiBConstant.TRANS_SERVICE_TYPE_TT) ){
				responseBean.setRelationshipStatusList(new LinkedHashMap<String, String>(10));
				if(regulatoryInfoResponseData.getRelationship_status() != null){
					for(RelationshipStatusList relationshipStatusList:regulatoryInfoResponseData.getRelationship_status()){
						if(locale.equalsIgnoreCase(MiBConstant.LANG_EN)){
							responseBean.getRelationshipStatusList().put(relationshipStatusList.getRel_code(), relationshipStatusList.getRel_name_en());
						}else if(locale != null  && locale.toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase())){
							responseBean.getRelationshipStatusList().put(relationshipStatusList.getRel_code(), relationshipStatusList.getRel_name_cn());
						}else{
							responseBean.getRelationshipStatusList().put(relationshipStatusList.getRel_code(), relationshipStatusList.getRel_name_id());
						}
					}
				}
				
				responseBean.setPaymentPurposeList(new LinkedHashMap<String, String>(10));
				if(regulatoryInfoResponseData.getPayment_purpose() != null){
					for(PaymentPurposeList paymentPurposeList:regulatoryInfoResponseData.getPayment_purpose()){
						if(locale.equalsIgnoreCase(MiBConstant.LANG_EN)){
							responseBean.getPaymentPurposeList().put(paymentPurposeList.getPurpose_code(), paymentPurposeList.getPurpose_name_en());
						}else if(locale != null  && locale.toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase())){
							responseBean.getPaymentPurposeList().put(paymentPurposeList.getPurpose_code(), paymentPurposeList.getPurpose_name_cn());
						}else{
							responseBean.getPaymentPurposeList().put(paymentPurposeList.getPurpose_code(), paymentPurposeList.getPurpose_name_id());
						}
					}
				}
				
				LLDRequestData lldRequestData = new LLDRequestData();
				lldRequestData.setLang(locale);
				VCResponse<PaymentPurposeResponseData> vcPaymentResp = vcService.callOmniVC(
						VCMethodConstant.REST_METHODS.OTHERS_LLD_PAYMENT_PURPOSE, 
						lldRequestData, 
						null, 
						true);
				if(processVCResponseError(vcPaymentResp, responseBean)){
					return;
				}
				
				responseBean.setPaymentPurposeList(new LinkedHashMap<String, String>(10));
				if(vcPaymentResp.getData() != null){
					PaymentPurposeResponseData paymentpurposebean = InitApp.mapper.map(vcPaymentResp, PaymentPurposeResponseData.class);
					for (PaymentPurposeList paymentPurpose : paymentpurposebean.getData()) {
						responseBean.getPaymentPurposeList().put(paymentPurpose.getPurpose_code(), paymentPurpose.getDescription());
					}
				}
				
				//TT document download
				responseBean.setDocumentId(MiBConstant.DOCUMENT_ID_TT);
				responseBean.setDocumentType(MiBConstant.DOCUMENT_TYPE_LLD);
			}
		}
		
		////Execute Time Batch
		
		InquiryTransTimeBatchRequestData batchRequestData = new InquiryTransTimeBatchRequestData();
		InquiryTransTimeBatchResponseData batchResponseData;
		batchRequestData.setOrg_cd(requestBean.getObUser().getOrgId());
		batchRequestData.setUsr_cd(requestBean.getObUser().getLoginId());
		
		String[] prod_cd_list = requestBean.getTransferServiceType()==null?null:requestBean.getTransferServiceType().split(",");
		if(null!=prod_cd_list)
		{
			batchRequestData.setProd_cd_list(new ArrayList());
			for(String prd_cd:prod_cd_list)
			{
				batchRequestData.getProd_cd_list().add(prd_cd);
			}
		}
		batchRequestData.setLang(locale);
		
		VCResponse<InquiryTransTimeBatchResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.TIME_BATCH_INQUIRY, 
				batchRequestData, 
				InquiryTransTimeBatchResponseData.class, 
				true);
		if(processVCResponseError(vcResponse, responseBean)){
			return;
		}
		batchResponseData = vcResponse.getData();
		
		responseBean.setTime_batch_list(batchResponseData.getTime_batch_list());
		
		List<ObBiFastBean> proxyTypeList = new ArrayList<ObBiFastBean>();
		List<GeneralCode> proxy = gnDao.getproxyList(locale);
		for(GeneralCode temp:proxy){
			
			ObBiFastBean proxyLs = new ObBiFastBean();
			proxyLs.setCode(temp.getGnCode());
			proxyLs.setDescription(temp.getGnCodeDescription());
			proxyTypeList.add(proxyLs);
		}
		
		List<ObBiFastBean> trxPurposeList = new ArrayList<ObBiFastBean>();
		List<GeneralCode> trx = gnDao.gettrxPurposeList(locale);
		for(GeneralCode temp:trx){
			
			ObBiFastBean trxLs = new ObBiFastBean();
			trxLs.setCode(temp.getGnCode());
			trxLs.setDescription(temp.getGnCodeDescription());
			trxPurposeList.add(trxLs);
		}
	
		Collections.sort(trxPurposeList, compareOrder);
		responseBean.setProxyTypeList(proxyTypeList);
		responseBean.setTrxPurposeList(trxPurposeList);
				
	}
	
	private void processRequestHolidayProduct(com.silverlake.mleb.mcb.module.vc.holiday.RequestData requestData, ObTransactionRequest requestBean, String productCode, Calendar now) throws Exception{
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setYear(now.get(Calendar.YEAR));
		requestData.setProd_cd(productCode);
	}
	
	/**
	 * Process the holiday product response 
	 * @param responseData
	 * @param responseBean
	 * @return true if holiday calendar is needed for further holiday date checking
	 * @throws Exception
	 */
	private void processResponseHolidayProduct(com.silverlake.mleb.mcb.module.vc.holiday.ResponseData responseData, ObTransactionRequest requestBean, ObTransactionResponse responseBean, Calendar now, VCGenericService vcService) throws Exception{
		boolean isCalendarHolidayExecuted = false;
		boolean isCalendarWeekendExecuted = false;
		//When it is not public holiday, can perform the transfer
		responseBean.setIsPublicHoliday(false);
		responseBean.setIsCrossCurrencyAllowed(true);
		com.silverlake.mleb.mcb.module.vc.holiday.ResponseData vcHolidayResponseData = null;
		for(ListHolidayProduct holidayProduct:responseData.getList_holiday_product()){
			if(holidayProduct.getHoliday_type().equalsIgnoreCase("PH") || holidayProduct.getHoliday_type().equalsIgnoreCase("WK")){
				//Due to Product Holiday might returned multiple records for PH and WK, -
				//if holiday dates of PH and WK is checked (cannot be found), -
				//ignore for any subsequence record with PH or WK
				if(isCalendarHolidayExecuted && isCalendarWeekendExecuted){
					continue;
				}
				//Flag to control holiday calendar web service is needed for further holiday date checking.
				//For PH and WK type of holiday, actual dates can only captured from holiday calendar ws.
				if(vcHolidayResponseData == null){
					com.silverlake.mleb.mcb.module.vc.holiday.RequestData vcHolidayRequestData = new com.silverlake.mleb.mcb.module.vc.holiday.RequestData();
					vcHolidayRequestData.setOrg_cd(requestBean.getObUser().getOrgId());
					vcHolidayRequestData.setUsr_cd(requestBean.getObUser().getLoginId());
					vcHolidayRequestData.setYear(now.get(Calendar.YEAR));
					VCResponse<com.silverlake.mleb.mcb.module.vc.holiday.ResponseData> vcResponse = vcService.callOmniVC(
							VCMethodConstant.REST_METHODS.HOLIDAY_CALENDAR, 
							vcHolidayRequestData, 
							com.silverlake.mleb.mcb.module.vc.holiday.ResponseData.class, 
							true);
					if(processVCResponseError(vcResponse, responseBean)){
						return;
					}
					
					vcHolidayResponseData = vcResponse.getData();
				}
				
				if(vcHolidayResponseData.getList_holiday_calendar() != null){
					Collections.sort(vcHolidayResponseData.getList_holiday_calendar(), holidayCalendarComparator);
					ListHolidayCalendar key = new ListHolidayCalendar();
					key.setCalendar_date(DateUtil.formatTransactionDateOnly("yyyy-MM-dd", now.getTime()));
					int result = Collections.binarySearch(vcHolidayResponseData.getList_holiday_calendar(), key, holidayCalendarComparator);
					if(result >= 0){
						if(vcHolidayResponseData.getList_holiday_calendar().get(result).getHoliday_type().equals(holidayProduct.getHoliday_type())){
							/*
							 * When cross_ccy is true, cross currency is allowed
							 */
							responseBean.setIsCrossCurrencyAllowed(holidayProduct.getCross_ccy());
							if(!holidayProduct.getCross_ccy()){
								responseBean.setNotAllowedFromCurrency(holidayProduct.getFrom_ccy());
								responseBean.setNotAllowedToCurrency(holidayProduct.getDest_ccy());
								responseBean.setNotAllowedProvinceCode(holidayProduct.getDest_prov_id());
							}
							responseBean.setIsPublicHoliday(true);//Inclusive weekend
							break;
						}
					}else{
						if(holidayProduct.getHoliday_type().equals("PH")){
							isCalendarHolidayExecuted = true;//PH is processed, but no matched of date.
						}else if(holidayProduct.getHoliday_type().equals("WK")){
							isCalendarWeekendExecuted = true;//WK is processed, but no matched of date.
						}
					}
				}else{
					//No holiday dates, can safely marked PH and WK is processed.
					isCalendarHolidayExecuted = true;
					isCalendarWeekendExecuted = true;
				}
			}else{
				String dateString = holidayProduct.getCalendar_date();
				if(dateString.equals(DateUtil.formatTransactionDateOnly("yyyy-MM-dd", now.getTime()))){
					/*
					 * When cross_ccy is true, cross currency is allowed
					 */
					responseBean.setIsCrossCurrencyAllowed(holidayProduct.getCross_ccy());
					if(!holidayProduct.getCross_ccy()){
						responseBean.setNotAllowedFromCurrency(holidayProduct.getFrom_ccy());
						responseBean.setNotAllowedToCurrency(holidayProduct.getDest_ccy());
						responseBean.setNotAllowedProvinceCode(holidayProduct.getDest_prov_id());
					}
					responseBean.setIsPublicHoliday(true);
					break;
				}
			}
		}
	}
	
	private void processRequestCheckCutOffTime(CheckCutOffTimeRequestData requestData, ObTransactionRequest requestBean, String transferServiceType) throws Exception{
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setProd_cd(transferServiceType);
	}
	
	private void processResponseCheckCutOffTime(String locale, CheckCutOffTimeResponseData responseData, ObTransactionResponse responseBean) throws Exception{
		if(responseData.getIs_cot()){
			responseBean.setAllowProcess(false);
			responseBean.setStartTime(responseData.getBus_hour_start());
			responseBean.setEndTime(responseData.getBus_hour_end());
			String message = RetrieveAppStatInfo_mcb.getMessageTable(locale).get("GENERAL_COT");
			message = message.replace("$1", responseBean.getStartTime());
			message = message.replace("$2", responseBean.getEndTime());
			responseBean.setErrorMessageCOT(message);
		}else{
			responseBean.setAllowProcess(true);
		}
	}
	
	Comparator<ObBiFastBean> compareOrder = new Comparator<ObBiFastBean>() {
	    @Override
	    public int compare(ObBiFastBean o1, ObBiFastBean o2) {
	        return o1.getCode().compareTo(o2.getCode());
	    }
	};
}
