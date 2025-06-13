package com.silverlake.mleb.mcb.module.func.onfx;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.MapPojo;
import com.silverlake.mleb.mcb.bean.MapPojoBean;
import com.silverlake.mleb.mcb.bean.ObForexRequest;
import com.silverlake.mleb.mcb.bean.ObForexResponse;
import com.silverlake.mleb.mcb.bean.ObForexSessionCache;
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
 * Purpose: Check online forex transaction allowed or not
 * Omni Web Services:
 * holiday/calendar
 * holiday/product
 * transaction/check_cot for cutoff time
 * 
 * @author Alex Loo
 *
 */
@Service
public class ONFXStep1 extends CacheSessionFuncServices<ObForexRequest, ObForexResponse, ObForexSessionCache>{
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
	public void processInternalWithCache(String locale, String sessionId, String trxId, ObForexRequest requestBean, ObForexResponse responseBean, ObForexSessionCache cacheBean, VCGenericService vcService) throws Exception {
		Calendar now = Calendar.getInstance();
		
		com.silverlake.mleb.mcb.module.vc.holiday.RequestData vcHolidayRequestData = new com.silverlake.mleb.mcb.module.vc.holiday.RequestData();
		com.silverlake.mleb.mcb.module.vc.holiday.ResponseData vcHolidayResponseData;
		processRequestHolidayProduct(vcHolidayRequestData, requestBean, now);
		VCResponse<com.silverlake.mleb.mcb.module.vc.holiday.ResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.HOLIDAY_PRODUCT, 
				vcHolidayRequestData, 
				com.silverlake.mleb.mcb.module.vc.holiday.ResponseData.class, 
				true,
				"onfx");
		if(processVCResponseError(vcResponse, responseBean)){
			return;
		}
		
		vcHolidayResponseData = vcResponse.getData();
		boolean isHolidayCalendarNeeded = processResponseHolidayProduct(vcHolidayResponseData, responseBean, now);
		
		//Call when holiday calendar is required for further checking of holiday dates.
		if(responseBean.getAllowProcess() && isHolidayCalendarNeeded){
			vcHolidayRequestData = new com.silverlake.mleb.mcb.module.vc.holiday.RequestData();
			processRequestHolidayCalendar(vcHolidayRequestData, requestBean, now);
			vcResponse = vcService.callOmniVC(
					VCMethodConstant.REST_METHODS.HOLIDAY_CALENDAR, 
					vcHolidayRequestData, 
					com.silverlake.mleb.mcb.module.vc.holiday.ResponseData.class, 
					true);
			if(processVCResponseError(vcResponse, responseBean)){
				return;
			}
			
			vcHolidayResponseData = vcResponse.getData();
			processResponseHolidayCalendar(vcHolidayResponseData, responseBean, now);
		}
		
		//Check for cutoff time
		//Check cutoff time even thought it is not allowed to process
		CheckCutOffTimeRequestData checkCutOffTimeRequestData = new CheckCutOffTimeRequestData();
		processRequestCheckCutOffTime(checkCutOffTimeRequestData, requestBean);
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
		
		if(responseBean.getAllowProcess()){
			responseBean.setPurposeList(new LinkedHashMap<String, String>(2));
			List<GeneralCode> gnList = gnDao.findByGnCodeType(MiBConstant.PURPOSE_LIST, locale);
			for(GeneralCode gnItem:gnList){
				responseBean.getPurposeList().put(gnItem.getGnCode(), gnItem.getGnCodeDescription());
			}
		}
	}
	
	private void processRequestHolidayProduct(com.silverlake.mleb.mcb.module.vc.holiday.RequestData requestData, ObForexRequest requestBean, Calendar now) throws Exception{
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setYear(now.get(Calendar.YEAR));
		requestData.setProd_cd(MiBConstant.PRODUCT_CODE_ONFX);
	}
	
	/**
	 * Process the holiday product response 
	 * @param responseData
	 * @param responseBean
	 * @return true if holiday calendar is needed for further holiday date checking
	 * @throws Exception
	 */
	private boolean processResponseHolidayProduct(com.silverlake.mleb.mcb.module.vc.holiday.ResponseData responseData, ObForexResponse responseBean, Calendar now) throws Exception{
		responseBean.setAllowProcess(true);
		responseBean.setIsPublicHoliday(false);
		
		for(ListHolidayProduct holidayProduct:responseData.getList_holiday_product()){
			if(holidayProduct.getHoliday_type().equalsIgnoreCase("PH") || holidayProduct.getHoliday_type().equalsIgnoreCase("WK")){
				//Flag to control holiday calendar web service is needed for further holiday date checking.
				//For PH and WK type of holiday, actual dates can only captured from holiday calendar ws.
				return true;
			}else{
				String dateString = holidayProduct.getCalendar_date();
				if(dateString.equals(DateUtil.formatTransactionDateOnly("yyyy-MM-dd", now.getTime()))){
					responseBean.setAllowProcess(false);
					responseBean.setIsPublicHoliday(true);
					break;
				}
			}
		}
		return false;
	}
	
	private void processRequestHolidayCalendar(com.silverlake.mleb.mcb.module.vc.holiday.RequestData requestData, ObForexRequest requestBean, Calendar now) throws Exception{
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setYear(now.get(Calendar.YEAR));
	}
	
	private void processResponseHolidayCalendar(com.silverlake.mleb.mcb.module.vc.holiday.ResponseData responseData, ObForexResponse responseBean, Calendar now) throws Exception{
		if(responseData.getList_holiday_calendar() != null){
			Collections.sort(responseData.getList_holiday_calendar(), holidayCalendarComparator);
			ListHolidayCalendar key = new ListHolidayCalendar();
			key.setCalendar_date(DateUtil.formatTransactionDateOnly("yyyy-MM-dd", now.getTime()));
			int result = Collections.binarySearch(responseData.getList_holiday_calendar(), key, holidayCalendarComparator);
			if(result >= 0){
				responseBean.setAllowProcess(false);
				responseBean.setIsPublicHoliday(true);
			}
		}
	}
	
	private void processRequestCheckCutOffTime(CheckCutOffTimeRequestData requestData, ObForexRequest requestBean) throws Exception{
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setProd_cd(MiBConstant.PRODUCT_CODE_ONFX);
	}
	
	private void processResponseCheckCutOffTime(String locale, CheckCutOffTimeResponseData responseData, ObForexResponse responseBean) throws Exception{
		if(responseData.getIs_cot()){
			responseBean.setAllowProcess(false);
			responseBean.setStartTime(responseData.getBus_hour_start());
			responseBean.setEndTime(responseData.getBus_hour_end());
			String message = RetrieveAppStatInfo_mcb.getMessageTable(locale).get("GENERAL_COT");
			message = message.replace("$1", responseBean.getStartTime());
			message = message.replace("$2", responseBean.getEndTime());
			responseBean.setErrorMessageCOT(message);
		}else{
			responseBean.setStartTime(responseData.getBus_hour_start());
			responseBean.setEndTime(responseData.getBus_hour_end());
		}
	}
}
