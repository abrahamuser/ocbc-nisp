package com.silverlake.mleb.ccmcb.module;

import java.util.ArrayList;

import javax.activation.DataHandler;
import javax.xml.ws.WebServiceContext;

import org.apache.commons.io.IOUtils;
import org.apache.geronimo.mail.util.Base64;

import com.silverlake.mleb.ccmcb.module.common.SessionMiBServices;
import com.silverlake.mleb.ccmcb.module.common.WSClass;
import com.silverlake.mleb.ccmcb.module.common.WSParameter;
import com.silverlake.mleb.mcb.bean.ObFile;
import com.silverlake.mleb.mcb.bean.ObSendFeedbackRequest;
import com.silverlake.mleb.mcb.bean.ObSendFeedbackResponse;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;

@WSClass(functionId=VCMethodConstant.FUNCTION_CODES.FUNC_MCB_SEND_FEEDBACK)
public class SendFeedbackBase64 extends SessionMiBServices<ObSendFeedbackRequest, ObSendFeedbackResponse>{
	@WSParameter(isMandatory=true)
	protected String email;
	
	@WSParameter(isMandatory=true)
	protected String phone;
	
	@WSParameter(isMandatory=true)
	protected String category;
	
	@WSParameter(isMandatory=true)
	protected String feedback;
	
	@WSParameter(isMandatory=false)
	protected String fileName;
	
	@WSParameter(isMandatory=false)
	protected String fileType;
	
	@WSParameter(isMandatory=false)
	protected String fileData;
	
	public SendFeedbackBase64(WebServiceContext wsContext) {
		super(wsContext);
	}

	@Override
	public void processData() throws Exception{
		obRequest.setEmail(email);
		obRequest.setPhone(phone);
		obRequest.setCategory(category);
		obRequest.setFeedback(feedback);
		
		if(fileName != null && fileType != null && fileData != null){
			obRequest.setImageList(new ArrayList<ObFile>(1));
			ObFile file = new ObFile();
			file.setFileName(fileName);
			file.setFileType(fileType);
			byte[] bytes = Base64.decode(fileData);
			file.setFileData(bytes);
			obRequest.getImageList().add(file);
		}
	}

	@Override
	public void processSessionResponse() {
		
	}
}
