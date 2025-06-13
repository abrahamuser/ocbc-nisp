package com.silverlake.mleb.mcb.module.func.transaction;


import java.util.ArrayList;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import com.silverlake.mleb.mcb.bean.ObMT103DataBean;
import com.silverlake.mleb.mcb.bean.ObTransactionSummaryRequest;
import com.silverlake.mleb.mcb.bean.ObTransactionSummaryResponse;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.module.common.SessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.transaction.MT103InquiryRequestData;
import com.silverlake.mleb.mcb.module.vc.transaction.MT103InquiryResponseData;
import com.silverlake.mleb.mcb.module.vc.transaction.Mt103Data;

/**
 * Purpose:  To perform inquiry of MT103
 * 
 * Omni Web Services:
 *transaction/inquiry/mt103
 * 
 * @author Hemanth
 *
 */
@Service
public class PerformMT103Inquiry extends SessionFuncServices<ObTransactionSummaryRequest, ObTransactionSummaryResponse>{
	private static Logger log = LogManager.getLogger(PerformMT103Inquiry.class);
	
	@Override
	public void processInternal(String locale, String sessionId, String trxId, ObTransactionSummaryRequest requestBean, ObTransactionSummaryResponse responseBean, VCGenericService vcService) throws Exception {
		MT103InquiryRequestData requestData = new MT103InquiryRequestData();
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setProd_cd(requestBean.getProductCode());
		requestData.setPymt_master_id(requestBean.getPymtMasterId());
		requestData.setBank_ref(requestBean.getBankRef());
		
		VCResponse<MT103InquiryResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.TRANSACTION_MT103_INQUIRY, 
				requestData, 
				MT103InquiryResponseData.class, 
				true);
		
		if(processVCResponseError(vcResponse, responseBean)){
			return;
		}
		
		MT103InquiryResponseData responseData = vcResponse.getData();
		processResponse(responseData, responseBean);
	}
	
	private void processResponse(MT103InquiryResponseData responseData, ObTransactionSummaryResponse responseBean)
			throws Exception {

		responseBean.setMt103DataList(new ArrayList<ObMT103DataBean>());

		for (Mt103Data mt103Data : responseData.getMt103_data()) {

			ObMT103DataBean mtData = new ObMT103DataBean();

			mtData.setRowId(mt103Data.getRow_id());
			mtData.setMtData(mt103Data.getMt_data());

			responseBean.getMt103DataList().add(mtData);

		}
	}
	
}	
