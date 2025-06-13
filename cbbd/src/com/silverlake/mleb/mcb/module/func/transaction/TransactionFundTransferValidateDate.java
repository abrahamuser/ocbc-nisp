package com.silverlake.mleb.mcb.module.func.transaction;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.ObSessionCache;
import com.silverlake.mleb.mcb.bean.ObTransactionRequest;
import com.silverlake.mleb.mcb.bean.ObTransactionResponse;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.entity.GeneralCode;
import com.silverlake.mleb.mcb.entity.dao.GeneralCodeDAO;
import com.silverlake.mleb.mcb.module.common.CacheSessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.holiday.ListHolidayCalendar;
import com.silverlake.mleb.mcb.module.vc.holiday.ListHolidayProduct;
import com.silverlake.mleb.mcb.module.vc.transaction.CheckCutOffTimeRequestData;
import com.silverlake.mleb.mcb.module.vc.transaction.CheckCutOffTimeResponseData;
import com.silverlake.mleb.mcb.util.DateUtil;

/**
 * Purpose: Validate fund transfer transaction on future date for cross currency.  
 * Get product holiday list, and cross check if value date is fall on holiday date.
 * Check cross currency flag is true (allowed cross currency) or false(not allowed cross currency), 
 * When not allowed cross currency, return the list of from/to currency which are not allowed.  
 * 
 * Omni Web Services:
 * holiday/calendar
 * holiday/product
 * 
 * @author Alex Loo
 *
 */
@Service
public class TransactionFundTransferValidateDate extends CacheSessionFuncServices<ObTransactionRequest, ObTransactionResponse, ObSessionCache>{
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
		 * Transaction that required to check future date
		 * - Own account transfer (OAT)
		 * - Internal Fund Transfer (IFT)
		 * - ONLINE (OLT)
		 * - LLG
		 * - RTGS
		 * - TT
		 */
		if(transferServiceType.equals(MiBConstant.TRANS_SERVICE_TYPE_OAT) || transferServiceType.equals(MiBConstant.TRANS_SERVICE_TYPE_IFT) ||
				transferServiceType.equals(MiBConstant.TRANS_SERVICE_TYPE_OLT) || transferServiceType.equals(MiBConstant.TRANS_SERVICE_TYPE_LLG) ||  
				transferServiceType.equals(MiBConstant.TRANS_SERVICE_TYPE_RTGS) || transferServiceType.equals(MiBConstant.TRANS_SERVICE_TYPE_TT)){
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
	}
	
	private void processRequestHolidayProduct(com.silverlake.mleb.mcb.module.vc.holiday.RequestData requestData, ObTransactionRequest requestBean, String productCode, Calendar now) throws Exception{
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setYear(now.get(Calendar.YEAR));
		requestData.setProd_cd(productCode);
	}
	
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
					//Get the value date year
					int year = Integer.parseInt(requestBean.getValueDate().substring(0, requestBean.getValueDate().indexOf("-")));
					vcHolidayRequestData.setYear(year);
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
					key.setCalendar_date(requestBean.getValueDate());
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
				if(dateString.equals(requestBean.getValueDate())){
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
}
