package com.silverlake.mleb.mcb.module.func.SwiftoutboundTracking;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import com.silverlake.mleb.mcb.bean.ObSwiftOutboundTrackingRequest;
import com.silverlake.mleb.mcb.bean.ObSwiftOutboundTrackingResponse;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.module.common.SessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.swift.SwiftGPIOutbound;
import com.silverlake.mleb.mcb.module.vc.swift.SwiftOutboundRequestData;
import com.silverlake.mleb.mcb.module.vc.swift.SwiftOutboundResponseData;
import com.silverlake.mleb.mcb.module.vc.swift.TrackingList;

/**
 * Purpose: To perform tracking of Outbound Telegraphic Transfer
 *  
 * Omni Web Services:
 * transaction/gpi/outbound/inquiry
 *  */
@Service
public class PerformSWIFTOutboundTracking
		extends SessionFuncServices<ObSwiftOutboundTrackingRequest, ObSwiftOutboundTrackingResponse> {
	private static Logger log = LogManager.getLogger(PerformSWIFTOutboundTracking.class);

	@Override
	public void processInternal(String locale, String sessionId, String trxId,
			ObSwiftOutboundTrackingRequest requestBean, ObSwiftOutboundTrackingResponse responseBean,
			VCGenericService vcService) throws Exception {

		SwiftOutboundRequestData requestData = new SwiftOutboundRequestData();
		SwiftOutboundResponseData responseData;
		processRequest(requestData, requestBean);

		VCResponse<SwiftOutboundResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.SWIFT_OUTBOUND_TRACKING, 
				requestData, 
				SwiftOutboundResponseData.class,
				true);
		if (processVCResponseError(vcResponse, responseBean)) {
			return;
		}
		responseData = vcResponse.getData();

		processResponse(responseData, responseBean, locale);

	}

	private void processRequest(SwiftOutboundRequestData requestData, ObSwiftOutboundTrackingRequest requestBean)
			throws Exception {
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setRemittance_no(requestBean.getRemittance_no());

	}

	private void processResponse(SwiftOutboundResponseData responseData, ObSwiftOutboundTrackingResponse responseBean,
			String locale) throws Exception {

		if (responseData.getGpi_data() == null) {

			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_NO_RESULT);
			return;

		} else {

			SwiftGPIOutbound gpiData = responseData.getGpi_data();

			SwiftGPIOutbound swift = new SwiftGPIOutbound();

			swift.setTransaction_status(gpiData.getTransaction_status());
			swift.setInitiation_datetime(gpiData.getInitiation_datetime());
			swift.setCompletion_datetime(gpiData.getCompletion_datetime());
			swift.setInstructed_amount(gpiData.getInstructed_amount());
			swift.setCredited_amount(gpiData.getCredited_amount());
			swift.setDeducted_amount(gpiData.getDeducted_amount());
			swift.setInstructed_ccy(gpiData.getInstructed_ccy());
			swift.setCredited_ccy(gpiData.getCredited_ccy());
			swift.setDeducted_ccy(gpiData.getDeducted_ccy());
			swift.setValue_date(gpiData.getValue_date());
			swift.setCharged_bearer(gpiData.getCharged_bearer());
			swift.setUetr(gpiData.getUetr());
			if (locale.equalsIgnoreCase(MiBConstant.LANG_EN)) {
				swift.setStatus_desc_en(gpiData.getStatus_desc_en());
				swift.setCharged_bearer_desc_en(gpiData.getCharged_bearer_desc_en());
			} else if(locale.toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase())){
				swift.setStatus_desc_cn(gpiData.getStatus_desc_cn());
				swift.setCharged_bearer_desc_cn(gpiData.getCharged_bearer_desc_cn());
			}else {
				swift.setStatus_desc_id(gpiData.getStatus_desc_id());
				swift.setCharged_bearer_desc_id(gpiData.getCharged_bearer_desc_id());
			}

			swift.setTracking_list(new ArrayList<TrackingList>());

			for (TrackingList tl : gpiData.getTracking_list()) {

				TrackingList trkli = new TrackingList();

				trkli.setBic_from(tl.getBic_from());
				trkli.setBic_to(tl.getBic_to());
				trkli.setBic_from_desc(tl.getBic_from_desc());
				trkli.setBic_to_desc(tl.getBic_to_desc());
				trkli.setTransaction_status(tl.getTransaction_status());
				trkli.setTrx_reason_code(tl.getTrx_reason_code());
				trkli.setLast_update(tl.getLast_update());
				trkli.setReceive_date(tl.getReceive_date());
				trkli.setSettlement_date(tl.getSettlement_date());
				if (locale.equalsIgnoreCase(MiBConstant.LANG_EN)) {
					trkli.setStatus_desc_en(tl.getStatus_desc_en());
					trkli.setTrx_reason_desc_en(tl.getTrx_reason_desc_en());
				} else if(locale.toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase())){
					trkli.setStatus_desc_cn(tl.getStatus_desc_cn());
					trkli.setTrx_reason_desc_cn(tl.getTrx_reason_desc_cn());
				}else {
					trkli.setStatus_desc_id(tl.getStatus_desc_id());
					trkli.setTrx_reason_desc_id(tl.getTrx_reason_desc_id());
				}

				swift.getTracking_list().add(trkli);

			}

			responseBean.setGpi_data(swift);

		}

	}

}
