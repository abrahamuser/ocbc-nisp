package com.silverlake.mleb.mcb.module.func.payment;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.ObPaymentRequest;
import com.silverlake.mleb.mcb.bean.ObPaymentResponse;
import com.silverlake.mleb.mcb.bean.ObPaymentSessionCache;
import com.silverlake.mleb.mcb.bean.ObTransactionRequest;
import com.silverlake.mleb.mcb.bean.ObTransactionResponse;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.entity.GeneralCode;
import com.silverlake.mleb.mcb.entity.dao.GeneralCodeDAO;
import com.silverlake.mleb.mcb.module.common.CacheSessionFuncServices;
import com.silverlake.mleb.mcb.module.func.RetrieveAppStatInfo_mcb;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.holiday.ListHolidayCalendar;
import com.silverlake.mleb.mcb.module.vc.holiday.ListHolidayProduct;
import com.silverlake.mleb.mcb.module.vc.transaction.CheckCutOffTimeRequestData;
import com.silverlake.mleb.mcb.module.vc.transaction.CheckCutOffTimeResponseData;
import com.silverlake.mleb.mcb.util.DateUtil;

/**
 * Purpose: Check payment cutoff and get it's related parameters
 * First check cut off time, if within cut off returned allowProcess false
 * 
 * Omni Web Services:
 * holiday/calendar
 * holiday/product
 * transaction/check_cot for cutoff time
 * 
 * @author Alex Loo
 */
@Service
public class PaymentStep1 extends CacheSessionFuncServices<ObPaymentRequest, ObPaymentResponse, ObPaymentSessionCache>{
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
	public void processInternalWithCache(String locale, String sessionId, String trxId, ObPaymentRequest requestBean, ObPaymentResponse responseBean, ObPaymentSessionCache cacheBean, VCGenericService vcService) throws Exception {
		Calendar now = Calendar.getInstance();
		
		String billerType = requestBean.getBillerType();
		
		//Check for cutoff time
		CheckCutOffTimeRequestData checkCutOffTimeRequestData = new CheckCutOffTimeRequestData();
		processRequestCheckCutOffTime(checkCutOffTimeRequestData, requestBean, billerType);
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
		
		//Determine it is cross ccy allowed
		if(responseBean.getAllowProcess()){
			/*//Check access retriction
			String menuItemId = null;
			if(billerType.equalsIgnoreCase("ETAX")){
				menuItemId = "5202";
			}else if(billerType.equalsIgnoreCase("BPM")){
				menuItemId = "5201";
			}
			if(menuItemId != null){
				processAccessRestriction(requestBean, responseBean, menuItemId, vcService);
				if(responseBean.getObHeader().getStatusCode() != null){
					return;
				}
			}*/
			
			com.silverlake.mleb.mcb.module.vc.holiday.RequestData vcHolidayRequestData = new com.silverlake.mleb.mcb.module.vc.holiday.RequestData();
			com.silverlake.mleb.mcb.module.vc.holiday.ResponseData vcHolidayResponseData;
			processRequestHolidayProduct(vcHolidayRequestData, requestBean, billerType, now);
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
		
		responseBean.setRecurringList(new LinkedHashMap<String, String>(2));
		List<GeneralCode> gnList = gnDao.findByGnCodeType(MiBConstant.FT_RECURRING, locale);
		for(GeneralCode gnItem:gnList){
			responseBean.getRecurringList().put(gnItem.getGnCode(), gnItem.getGnCodeDescription());
		}
	}
	
	private void processRequestHolidayProduct(com.silverlake.mleb.mcb.module.vc.holiday.RequestData requestData, ObPaymentRequest requestBean, String productCode, Calendar now) throws Exception{
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
	private void processResponseHolidayProduct(com.silverlake.mleb.mcb.module.vc.holiday.ResponseData responseData, ObPaymentRequest requestBean, ObPaymentResponse responseBean, Calendar now, VCGenericService vcService) throws Exception{
		responseBean.setIsPublicHoliday(false);
		responseBean.setAllowProcess(true);
		com.silverlake.mleb.mcb.module.vc.holiday.ResponseData vcHolidayResponseData = null;
		for(ListHolidayProduct holidayProduct:responseData.getList_holiday_product()){
			if(holidayProduct.getHoliday_type().equalsIgnoreCase("PH") || holidayProduct.getHoliday_type().equalsIgnoreCase("WK")){
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
						responseBean.setIsPublicHoliday(true);
						responseBean.setAllowProcess(false);
						break;
					}
				}
			}else{
				String dateString = holidayProduct.getCalendar_date();
				if(dateString.equals(DateUtil.formatTransactionDateOnly("yyyy-MM-dd", now.getTime()))){					
					responseBean.setIsPublicHoliday(true);
					responseBean.setAllowProcess(false);
					break;
				}
			}
		}
	}
	
	private void processRequestCheckCutOffTime(CheckCutOffTimeRequestData requestData, ObPaymentRequest requestBean, String transferServiceType) throws Exception{
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setProd_cd(transferServiceType);
	}
	
	private void processResponseCheckCutOffTime(String locale, CheckCutOffTimeResponseData responseData, ObPaymentResponse responseBean) throws Exception{
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
	
	/*private void processAccessRestriction(ObPaymentRequest requestBean, ObPaymentResponse responseBean, String menuItemId, VCGenericService vcService) throws Exception{
		//Check access retriction
		com.silverlake.mleb.mcb.module.vc.user.RequestData vcAccessRetrictionRequest = new com.silverlake.mleb.mcb.module.vc.user.RequestData();
		vcAccessRetrictionRequest.setOrg_cd(requestBean.getObUser().getOrgId());
		vcAccessRetrictionRequest.setUsr_cd(requestBean.getObUser().getLoginId());
		vcAccessRetrictionRequest.setMn_itm_id(menuItemId);
		com.silverlake.mleb.mcb.module.vc.user.ResponseData vcAccessRetrictionResponseData;
		VCResponse<com.silverlake.mleb.mcb.module.vc.user.ResponseData> vcAccessRetrictionResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.USER_ACCESS_RESTRICTION, 
				vcAccessRetrictionRequest, 
				com.silverlake.mleb.mcb.module.vc.user.ResponseData.class, 
				true);
		if(processVCResponseError(vcAccessRetrictionResponse, responseBean)){
			return;
		}
		vcAccessRetrictionResponseData = vcAccessRetrictionResponse.getData();
		responseBean.setAccessRestriction(vcAccessRetrictionResponseData.getList_restriction());
	}*/
}
