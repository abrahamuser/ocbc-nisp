package com.silverlake.mleb.ccmcb.module;

import javax.xml.ws.WebServiceContext;

import com.silverlake.mleb.ccmcb.module.common.SessionMiBServices;
import com.silverlake.mleb.ccmcb.module.common.WSClass;
import com.silverlake.mleb.ccmcb.module.common.WSParameter;
import com.silverlake.mleb.mcb.bean.ObDocumentDownloadRequest;
import com.silverlake.mleb.mcb.bean.ObDocumentDownloadResponse;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;

@WSClass(functionId= VCMethodConstant.FUNCTION_CODES.FUNC_MCB_DOCUMENT_DOWNLOAD)

public class DocumentDownload extends SessionMiBServices<ObDocumentDownloadRequest, ObDocumentDownloadResponse> {
	
	@WSParameter
	protected String documentId;
	
	@WSParameter(isMandatory=true)
	protected String documentType;
			
    public DocumentDownload(WebServiceContext wsContext) {
        super(wsContext);
    }

    @Override
    protected void processData() throws Exception {
        obRequest.setDocumentId(documentId);
        obRequest.setDocumentType(documentType);
    }

    @Override
    protected void processSessionResponse() {

    }
}
