package com.silverlake.mleb.mcb.module.func.holiday;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.mcb.bean.ObHeaderResponse;
import com.silverlake.mleb.mcb.bean.ObHolidayRequest;
import com.silverlake.mleb.mcb.bean.ObHolidayResponse;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.entity.dao.CustomerDAO;
import com.silverlake.mleb.mcb.entity.dao.GeneralCodeDAO;
import com.silverlake.mleb.mcb.module.common.FuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.common.VCService;
import com.silverlake.mleb.mcb.module.vc.holiday.ListHolidayCalendar;


@Service
public class RetrieveHolidayList extends FuncServices  
{
	private static Logger log = LogManager.getLogger(RetrieveHolidayList.class);

	@Autowired
	CustomerDAO dao;

	@Autowired
	GeneralCodeDAO gnDao;

	@Autowired ApplicationContext appContext;

	public MICBResponseBean process(MICBRequestBean arg0) {
		
		MICBResponseBean response = new MICBResponseBean();
		ObHolidayResponse obResponse = new ObHolidayResponse();
		obResponse.setObHeader(new ObHeaderResponse());

		try {
			ObHolidayRequest requestData = (ObHolidayRequest) arg0.getBDObject();	
			Integer year = requestData.getYear();
			String ipAddress = requestData.getObHeader() == null ? null : requestData.getObHeader().getIp();
			
			//Call REQ OMNI HolidayList
			VCService usrService = new VCService(appContext);
			com.silverlake.mleb.mcb.module.vc.holiday.RequestData vcHolidayRequest = new com.silverlake.mleb.mcb.module.vc.holiday.RequestData();
			vcHolidayRequest.setYear(year);
			//Call RES OMNI HolidayList 
			com.silverlake.mleb.mcb.module.vc.holiday.ResponseData vcHolidayListResponseData = new com.silverlake.mleb.mcb.module.vc.holiday.ResponseData();
			VCResponse<com.silverlake.mleb.mcb.module.vc.holiday.ResponseData> vcResponse = usrService.callOmniVC(VCMethodConstant.REST_METHODS.HOLIDAY_CALENDAR_CALENDAR,vcHolidayRequest, vcHolidayListResponseData, null, arg0.getTranxID(), ipAddress);
			if(vcResponse.getResponse_code().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS))
			{
				vcHolidayListResponseData = vcResponse.getData();
				if(vcHolidayListResponseData.getList_holiday_calendar()!=null)
				{
					obResponse.setHolidayCalendar(new ArrayList());
					for(ListHolidayCalendar temp:vcHolidayListResponseData.getList_holiday_calendar())
					{
						ListHolidayCalendar holidayList = new ListHolidayCalendar();
						BeanUtils.copyProperties(temp, holidayList);
						obResponse.getHolidayCalendar().add(holidayList);
					}
					obResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
				}
				else
				{
					obResponse.getObHeader().setStatusCode(MiBConstant.MCB_NO_ACCOUNT_FOUND);
				}
				
			}
			else
			{
				obResponse.getObHeader().setStatusCode(MiBConstant.MIB_OMNI_PREFIX+vcResponse.getResponse_code() );
				obResponse.getObHeader().setStatusMessage(vcResponse.getResponse_desc());
			}

		} catch (Exception e) {

			log.info(this.getClass().toString(), e);
			obResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			obResponse.getObHeader().setStatusMessage(e.getMessage());
		}

		response.setBDObject(obResponse);

		return response;
	}
}
