package com.silverlake.mleb.mcb.module.func;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.mcb.bean.ObAuthenticationResponse;
import com.silverlake.mleb.mcb.bean.ObHeaderResponse;
import com.silverlake.mleb.mcb.bean.ObTokenTnCResponse;
import com.silverlake.mleb.mcb.bean.ObUserContext;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.module.common.FuncServices;
import com.silverlake.mleb.mcb.util.PropertiesManager;

@Service
public class RetrieveResetPassTnC extends FuncServices  
{
	private static Logger log = LogManager.getLogger(RetrieveResetPassTnC.class);
	
	@Autowired ApplicationContext appContext;
	
	public MICBResponseBean process(MICBRequestBean arg0) {
		// TODO Auto-generated method stub

		MICBResponseBean response = new MICBResponseBean();
		ObAuthenticationResponse obResponse = new ObAuthenticationResponse();
		obResponse.setObHeader(new ObHeaderResponse());
		obResponse.setUserContext(new ObUserContext());
		
		try {
			
			String tncContent = showTNCFlag(arg0.getLocaleCode());
			if(null!=tncContent)
			{
				obResponse.setTncContent(tncContent);
				obResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
			}else {
				obResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
				obResponse.getObHeader().setStatusMessage(MiBConstant.MIB_FILE_NOT_FOUND);
			}
			
		
		} catch (Exception e) {
			log.info(this.getClass().toString(), e);
			obResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			obResponse.getObHeader().setStatusMessage(e.getMessage());
		}
		
		response.setBDObject(obResponse);
		
		return response;
	}

	public String showTNCFlag(String locale) throws IOException
	{
		String appPropertyPath = System.getProperty(PropertiesManager.PROPERTIES_SYSTEM_PATH_NAME);
		locale = locale==null?MiBConstant.LOCALE_EN:(locale.trim().length()==0?MiBConstant.LOCALE_EN:locale);
		String fileLoc = "";
		if(locale.equalsIgnoreCase(MiBConstant.LOCALE_IN))
		{
			fileLoc = "file:"+appPropertyPath+"/tnc/"+MiBConstant.TNC_RESET_PASSWORD+"/"+"resetPassTnC.html"+"_"+locale.toLowerCase();
		}else if(locale != null  && locale.toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase()))
		{
			fileLoc = "file:"+appPropertyPath+"/tnc/"+MiBConstant.TNC_RESET_PASSWORD+"/"+"resetPassTnC.html"+"_cn";
		}
		else
		{
			fileLoc = "file:"+appPropertyPath+"/tnc/"+MiBConstant.TNC_RESET_PASSWORD+"/"+"resetPassTnC.html";
		}
		InputStream inx= appContext.getResource(fileLoc).getInputStream();
		byte[] dataFile = IOUtils.toByteArray(inx);
		
		String base64String = Base64.encodeBase64String(dataFile);
		String TNCBase64File = base64String;
		inx.close();
		return TNCBase64File;
	}
}