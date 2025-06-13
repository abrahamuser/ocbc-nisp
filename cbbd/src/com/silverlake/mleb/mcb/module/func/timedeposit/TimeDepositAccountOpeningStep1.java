package com.silverlake.mleb.mcb.module.func.timedeposit;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;

import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.timedeposit.ObTimeDepositRequest;
import com.silverlake.mleb.mcb.bean.timedeposit.ObTimeDepositResponse;
import com.silverlake.mleb.mcb.bean.timedeposit.ObTimeDepositSessionCache;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.module.common.CacheSessionFuncServices;
import com.silverlake.mleb.mcb.module.func.RetrieveAppStatInfo_mcb;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.holiday.ListHolidayCalendar;
import com.silverlake.mleb.mcb.module.vc.holiday.ListHolidayProduct;
import com.silverlake.mleb.mcb.module.vc.holiday.ResponseData;
import com.silverlake.mleb.mcb.module.vc.timedeposit.ListPurposeCode;
import com.silverlake.mleb.mcb.module.vc.timedeposit.ListRolloverCode;
import com.silverlake.mleb.mcb.module.vc.timedeposit.ListSourceOfFundCode;
import com.silverlake.mleb.mcb.module.vc.timedeposit.ParameterRequestData;
import com.silverlake.mleb.mcb.module.vc.timedeposit.ParameterResponseData;
import com.silverlake.mleb.mcb.module.vc.transaction.CheckCutOffTimeRequestData;
import com.silverlake.mleb.mcb.module.vc.transaction.CheckCutOffTimeResponseData;
import com.silverlake.mleb.mcb.util.DateUtil;

/**
 * Purpose: Check online time deposit allowed or not
 * Omni Web Services:
 * holiday/calendar
 * holiday/product
 * transaction/check_cot for cutoff time
 *
 * @author Afif
 *
 */
@Service
public class TimeDepositAccountOpeningStep1 extends CacheSessionFuncServices<ObTimeDepositRequest, ObTimeDepositResponse, ObTimeDepositSessionCache> {

    private Comparator<ListHolidayCalendar> holidayCalendarComparator = new Comparator<ListHolidayCalendar>(){
        @Override
        public int compare(ListHolidayCalendar o1, ListHolidayCalendar o2) {
            if ( o1.getCalendar_date() == null ) return o2.getCalendar_date() == null ? 0 : 1;
            if ( o2.getCalendar_date() == null ) return 1;
            return o1.getCalendar_date().compareTo(o2.getCalendar_date());
        }

    };

    @Override
    public void processInternalWithCache(String locale, String sessionId, String trxId, ObTimeDepositRequest requestBean, ObTimeDepositResponse responseBean, ObTimeDepositSessionCache cacheBean, VCGenericService vcService) throws Exception {

        if(requestBean.getIsCheckCutOff()) {
            Calendar now = Calendar.getInstance();

            com.silverlake.mleb.mcb.module.vc.holiday.RequestData vcHolidayRequestData = new com.silverlake.mleb.mcb.module.vc.holiday.RequestData();
            com.silverlake.mleb.mcb.module.vc.holiday.ResponseData vcHolidayResponseData;
            processRequestHolidayProduct(vcHolidayRequestData, requestBean, now);
            VCResponse<ResponseData> vcResponse = vcService.callOmniVC(
                    VCMethodConstant.REST_METHODS.HOLIDAY_PRODUCT,
                    vcHolidayRequestData,
                    com.silverlake.mleb.mcb.module.vc.holiday.ResponseData.class,
                    true);
            if (processVCResponseError(vcResponse, responseBean)) {
                return;
            }

            vcHolidayResponseData = vcResponse.getData();
            boolean isHolidayCalendarNeeded = processResponseHolidayProduct(vcHolidayResponseData, responseBean, now);

            //Call when holiday calendar is required for further checking of holiday dates.
            if (responseBean.getAllowProcess() && isHolidayCalendarNeeded) {
                vcHolidayRequestData = new com.silverlake.mleb.mcb.module.vc.holiday.RequestData();
                processRequestHolidayCalendar(vcHolidayRequestData, requestBean, now);
                vcResponse = vcService.callOmniVC(
                        VCMethodConstant.REST_METHODS.HOLIDAY_CALENDAR,
                        vcHolidayRequestData,
                        com.silverlake.mleb.mcb.module.vc.holiday.ResponseData.class,
                        true);
                if (processVCResponseError(vcResponse, responseBean)) {
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
            if (processVCResponseError(vcCheckCutOffTimeResponse, responseBean)) {
                return;
            }

            CheckCutOffTimeResponseData checkCutOffTimeResponseData = vcCheckCutOffTimeResponse.getData();
            processResponseCheckCutOffTime(locale, checkCutOffTimeResponseData, responseBean);
        }

        ParameterRequestData parameterRequestData = new ParameterRequestData();
        ParameterResponseData parameterResponseData;
        processRequestCommonParameter(parameterRequestData, requestBean);
        VCResponse<ParameterResponseData> vcParameterResponseDataResponse = vcService.callOmniVC(
                VCMethodConstant.REST_METHODS.TIME_DEPOSIT_OPENING_COMMON_PARAMETER,
                parameterRequestData,
                ParameterResponseData.class,
                true);
        if (processVCResponseError(vcParameterResponseDataResponse, responseBean)) {
            return;
        }

        parameterResponseData = vcParameterResponseDataResponse.getData();
        processResponseCommonParameter(locale, parameterResponseData, responseBean);
        cacheBean.setParameterResponseData(parameterResponseData);
    }

    private void processResponseCommonParameter(String locale, ParameterResponseData parameterResponseData, ObTimeDepositResponse responseBean) {
        LinkedHashMap<String, String> purposeCodeList = new LinkedHashMap<String, String>();
        for(ListPurposeCode purposeCode : parameterResponseData.getPurpose_code_list()){
            if(locale.equalsIgnoreCase(MiBConstant.LANG_EN)){
                purposeCodeList.put(purposeCode.getPurpose_code(),purposeCode.getPurpose_desc_en());
            } else if(locale.toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase())){
                purposeCodeList.put(purposeCode.getPurpose_code(),purposeCode.getPurpose_desc_cn());
            } else {
                purposeCodeList.put(purposeCode.getPurpose_code(),purposeCode.getPurpose_desc_id());
            }
        }

        responseBean.setPurposeList(purposeCodeList);

        LinkedHashMap<String, String> sourceOfFundCodeList = new LinkedHashMap<String, String>();
        for(ListSourceOfFundCode sourceOfFundCode : parameterResponseData.getSource_fund_list()){
            if(locale.equalsIgnoreCase(MiBConstant.LANG_EN)){
                sourceOfFundCodeList.put(sourceOfFundCode.getSource_fund_code(),sourceOfFundCode.getSource_fund_desc_en());
            } else if(locale.toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase())){
                sourceOfFundCodeList.put(sourceOfFundCode.getSource_fund_code(),sourceOfFundCode.getSource_fund_desc_cn());
            } else {
                sourceOfFundCodeList.put(sourceOfFundCode.getSource_fund_code(),sourceOfFundCode.getSource_fund_desc_id());
            }
        }

        responseBean.setSourceOfFundList(sourceOfFundCodeList);

        LinkedHashMap<String, String> rolloverCodeList = new LinkedHashMap<String, String>();
        for(ListRolloverCode rolloverCode : parameterResponseData.getRollover_type_list()){
            if(locale.equalsIgnoreCase(MiBConstant.LANG_EN)){
                rolloverCodeList.put(rolloverCode.getRollover_code(),rolloverCode.getRollover_desc_en());
            } else if(locale.toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase())){
                rolloverCodeList.put(rolloverCode.getRollover_code(),rolloverCode.getRollover_desc_cn());
            } else {
                rolloverCodeList.put(rolloverCode.getRollover_code(),rolloverCode.getRollover_desc_id());
            }
        }

        responseBean.setRolloverTypeList(rolloverCodeList);
    }

    private void processRequestHolidayProduct(com.silverlake.mleb.mcb.module.vc.holiday.RequestData requestData, ObTimeDepositRequest requestBean, Calendar now) throws Exception{
        requestData.setOrg_cd(requestBean.getObUser().getOrgId());
        requestData.setUsr_cd(requestBean.getObUser().getLoginId());
        requestData.setYear(now.get(Calendar.YEAR));
        if(requestBean.getProductCode() != null && !requestBean.getProductCode().isEmpty()) {
        	requestData.setProd_cd(requestBean.getProductCode());
        }else {
        	requestData.setProd_cd(MiBConstant.PRODUCT_CODE_TDAO);
        }
    }

    /**
     * Process the holiday product response
     * @param responseData
     * @param responseBean
     * @return true if holiday calendar is needed for further holiday date checking
     * @throws Exception
     */
    private boolean processResponseHolidayProduct(com.silverlake.mleb.mcb.module.vc.holiday.ResponseData responseData, ObTimeDepositResponse responseBean, Calendar now) throws Exception{
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

    private void processRequestHolidayCalendar(com.silverlake.mleb.mcb.module.vc.holiday.RequestData requestData, ObTimeDepositRequest requestBean, Calendar now) throws Exception{
        requestData.setOrg_cd(requestBean.getObUser().getOrgId());
        requestData.setUsr_cd(requestBean.getObUser().getLoginId());
        requestData.setYear(now.get(Calendar.YEAR));
    }

    private void processResponseHolidayCalendar(com.silverlake.mleb.mcb.module.vc.holiday.ResponseData responseData, ObTimeDepositResponse responseBean, Calendar now) throws Exception{
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

    private void processRequestCheckCutOffTime(CheckCutOffTimeRequestData requestData, ObTimeDepositRequest requestBean) throws Exception{
        requestData.setOrg_cd(requestBean.getObUser().getOrgId());
        requestData.setUsr_cd(requestBean.getObUser().getLoginId());
        if(requestBean.getProductCode() != null && !requestBean.getProductCode().isEmpty()) {
        	requestData.setProd_cd(requestBean.getProductCode());
        }else {
        	requestData.setProd_cd(MiBConstant.PRODUCT_CODE_TDAO);
        }
    }

    private void processResponseCheckCutOffTime(String locale, CheckCutOffTimeResponseData responseData, ObTimeDepositResponse responseBean) throws Exception{
        if(responseData.getIs_cot()){
            responseBean.setAllowProcess(false);
            responseBean.setStartTime(responseData.getBus_hour_start());
            responseBean.setEndTime(responseData.getBus_hour_end());
            String message = RetrieveAppStatInfo_mcb.getMessageTable(locale).get("GENERAL_COT");
            message = message.replace("$1", responseBean.getStartTime());
            message = message.replace("$2", responseBean.getEndTime());
            responseBean.setErrorMessageCOT(message);
        }
    }

    private void processRequestCommonParameter(ParameterRequestData parameterRequestData, ObTimeDepositRequest requestBean) {
        parameterRequestData.setOrg_cd(requestBean.getObUser().getOrgId());
        parameterRequestData.setUsr_cd(requestBean.getObUser().getLoginId());
    }
}
