package com.silverlake.mleb.mcb.module.func.others;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.ObDocumentDownloadRequest;
import com.silverlake.mleb.mcb.bean.ObDocumentDownloadResponse;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.module.common.SessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.others.DocumentDownloadRequest;
import com.silverlake.mleb.mcb.module.vc.others.DocumentDownloadResponse;
import com.silverlake.mleb.mcb.module.vc.others.File;

/**
 * Purpose: To  get soft copy (in pdf format) of estatement from selected account.
 * 
 * Omni Web Services:
 * others/document
 *
 */
@Service
public class DocumentDownload extends SessionFuncServices<ObDocumentDownloadRequest, ObDocumentDownloadResponse> {
	private static Logger log = LogManager.getLogger(DocumentDownload.class); 

    @Override
    public void processInternal(String locale, String sessionId, String trxId, ObDocumentDownloadRequest requestBean, ObDocumentDownloadResponse obResponseBean,VCGenericService vcService) throws Exception {
    	    	    	
    	DocumentDownloadRequest requestData = new DocumentDownloadRequest();
    	DocumentDownloadResponse responseData;
		processRequest(requestData, requestBean);
		VCResponse<DocumentDownloadResponse> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.OTHERS_DOCUMENT, 
				requestData, 
				DocumentDownloadResponse.class, 
				true);
		if(processVCResponseError(vcResponse, obResponseBean)){
			return;
		}
		
		responseData = vcResponse.getData();
		processResponse(responseData, obResponseBean);
    }
   	

	private void processRequest(DocumentDownloadRequest requestData, ObDocumentDownloadRequest requestBean) throws Exception
	{
		requestData.setDocument_id(requestBean.getDocumentId());
		requestData.setDocument_type(requestBean.getDocumentType());
	}
	
	private void processResponse(DocumentDownloadResponse responseData, ObDocumentDownloadResponse obResponseBean) throws Exception{
		if(responseData.getFile_list() != null) 
		{
			obResponseBean.setFile_list(new ArrayList<File>());
			for(File temp : responseData.getFile_list()){
				File fileList = new File();
				BeanUtils.copyProperties(temp, fileList);
				obResponseBean.getFile_list().add(fileList);
			}
			obResponseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
		} else {
			obResponseBean.getObHeader().setStatusCode(MiBConstant.MIB_FILE_NOT_FOUND);
		}
	}		
}
