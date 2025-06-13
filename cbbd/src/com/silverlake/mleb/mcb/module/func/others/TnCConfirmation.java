package com.silverlake.mleb.mcb.module.func.others;

import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.ObTNCRequest;
import com.silverlake.mleb.mcb.bean.ObTNCResponse;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.module.common.SessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.others.File;
import com.silverlake.mleb.mcb.module.vc.others.TNCRequestData;
import com.silverlake.mleb.mcb.module.vc.others.TNCResponseData;

/**
 * Purpose: Confirmation of term and condition
 * Omni Web Services:
 * others/tnc_confirm
 * 
 * @author Alex Loo
 *
 */
@Service
public class TnCConfirmation extends SessionFuncServices<ObTNCRequest, ObTNCResponse>{
	
	@Override
	public void processInternal(String locale, String sessionId, String trxId, ObTNCRequest requestBean, ObTNCResponse responseBean, VCGenericService vcService) throws Exception {
		TNCRequestData requestData = new TNCRequestData();
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setTnc_type(requestBean.getTncType());
		requestData.setTxn_reference_no(requestBean.getRefNo());
		if(requestBean.getObTNCBean() != null){
			File file = new File();
			file.setData(requestBean.getObTNCBean().getTncContent());
			file.setMime_type(requestBean.getObTNCBean().getTncTypeCode());
			file.setFile_name(requestBean.getObTNCBean().getTncName());
			requestData.setFile(file);
		}
		VCResponse<TNCResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.OTHERS_TNC_CONFIRM, 
				requestData, 
				TNCResponseData.class, 
				true);
		if(processVCResponseError(vcResponse, responseBean, false)){
			return;
		}
	}
}
