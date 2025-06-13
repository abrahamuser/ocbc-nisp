package com.silverlake.mleb.mcb.module.func.others;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.ws.security.util.Base64;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.ObFile;
import com.silverlake.mleb.mcb.bean.ObSendFeedbackRequest;
import com.silverlake.mleb.mcb.bean.ObSendFeedbackResponse;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.module.common.SessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.others.FeedbackRequestData;
import com.silverlake.mleb.mcb.module.vc.others.FeedbackResponseData;
import com.silverlake.mleb.mcb.module.vc.others.File;

/**
 * Purpose: Send the customer feedback
 *   
 * Omni Web Services:
 * other/feedback
 * 
 * @author Alex Loo
 *
 */
@Service
public class SendFeedback extends SessionFuncServices<ObSendFeedbackRequest, ObSendFeedbackResponse>{
	private static Logger log = LogManager.getLogger(SendFeedback.class);
	
//	@Autowired GeneralCodeDAO gnDAO;
	
//	@Autowired AOCacheInputDao dao;

	@Override
	public void processInternal(String locale, String sessionId, String trxId, ObSendFeedbackRequest requestBean,
			ObSendFeedbackResponse responseBean, VCGenericService vcService) throws Exception {
		FeedbackRequestData requestData = new FeedbackRequestData();
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setCategory(requestBean.getCategory());
		requestData.setDevice_id(requestBean.getObDevice().getDeviceId());
		requestData.setDevice_os(requestBean.getObDevice().getOs());
		requestData.setDevice_type(requestBean.getObDevice().getType());
		requestData.setFeedback(requestBean.getFeedback());
		requestData.setEmail(requestBean.getEmail());
		requestData.setPhone(requestBean.getPhone());
		if(requestBean.getImageList() != null){
			requestData.setAttachments(new ArrayList<File>(requestBean.getImageList().size()));
			for(ObFile imageFile:requestBean.getImageList()){
				String dataBase64 = null;
				File file = new File();
				if(requestBean.getIsBase64() != null && requestBean.getIsBase64()) {
					dataBase64 = new String(imageFile.getFileData());
				}else {
					dataBase64 = Base64.encode(imageFile.getFileData());
				}
				file.setData(dataBase64);
				file.setFile_name(imageFile.getFileName());
				file.setMime_type(imageFile.getFileType());
				requestData.getAttachments().add(file);
				
//				GeneralCode code = gnDAO.findByGnCodeAndGnCodeType("FEEDBACK", "FEEDBACK");
//				if(code != null) {
//					code.setData(dataBase64);
//				}else {
//					code = new GeneralCode();
//					code.setGnCode("FEEDBACK");
//					code.setGnCodeType("FEEDBACK");
//					code.setData(dataBase64);
//				}
//				dao.updateEntity(code);
			}
		}
		
		VCResponse<FeedbackResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.OTHERS_FEEDBACK, 
				requestData, 
				FeedbackResponseData.class, 
				true);
		if(processVCResponseError(vcResponse, responseBean, false)){
			return;
		}
	}
}
