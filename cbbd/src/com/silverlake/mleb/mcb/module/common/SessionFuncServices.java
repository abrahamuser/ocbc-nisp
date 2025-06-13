package com.silverlake.mleb.mcb.module.common;

import java.lang.reflect.ParameterizedType;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.mcb.bean.ObHeaderResponse;
import com.silverlake.mleb.mcb.bean.ObRequest;
import com.silverlake.mleb.mcb.bean.ObResponse;
import com.silverlake.mleb.mcb.bean.ObUserContext;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.token.TokenRequestData;
import com.silverlake.mleb.mcb.module.vc.token.TokenResponseData;
import com.silverlake.mleb.mcb.util.MessageManager;

/**
 * This is a session based service class that required authentication to omni before process the request.
 * This class generalized the error handling of exception and omni common error.
 * Any service that required such requirement is recommended to extend this class.
 * 
 * @author Alex Loo
 *
 * @param <S> the request bean class that is defined to the service
 * @param <R> the response bean class that is defined to the service
 */
public abstract class SessionFuncServices<S extends ObRequest, R extends ObResponse> extends FuncServices{
	private static Logger log = LogManager.getLogger(SessionFuncServices.class);
	
	@Autowired 
	protected ApplicationContext appContext;

	@Override
	public MICBResponseBean process(MICBRequestBean micbRequestBean) {
		MICBResponseBean response = new MICBResponseBean();
		R responseBean = null;
		try{
			ParameterizedType superClass = (ParameterizedType) getClass().getGenericSuperclass();
	        @SuppressWarnings("unchecked")
	        Class<S> stype = (Class<S>)superClass.getActualTypeArguments()[0];
	        
	        @SuppressWarnings("unchecked")
	        Class<R> rType = (Class<R>)superClass.getActualTypeArguments()[1];
	        
	        responseBean = rType.newInstance();
			responseBean.setObHeader(new ObHeaderResponse());
			responseBean.setUserContext(new ObUserContext());
			
			S requestBean =  stype.cast(micbRequestBean.getBDObject());
			
			String deviceId = micbRequestBean.getDeviceBean()==null?null:micbRequestBean.getDeviceBean().getDeviceId();
			if(deviceId != null){
				requestBean.setObDevice(micbRequestBean.getDeviceBean());
			}
			
			ObRequest obRequest = (ObRequest) micbRequestBean.getBDObject();
			String ipAddress = obRequest.getObHeader() == null ? null : obRequest.getObHeader().getIp();
			
			VCGenericService vcService = new VCGenericService(appContext, micbRequestBean.getTranxID(), requestBean.getUserContext().getSessionId(), ipAddress);
			
			//Automatic call token cr verify if cr verification is true
			if(requestBean.getIsCRVerify() != null && requestBean.getIsCRVerify()){
				if(!verifyCR(requestBean, responseBean, deviceId, vcService)){
					response.setBDObject(responseBean);
					return response;
				}
			}
			
			processInternal(micbRequestBean.getLocaleCode(), requestBean.getUserContext().getSessionId(), micbRequestBean.getTranxID(), requestBean, responseBean, vcService);
			
			//If it is null, meaning no error status code being assign along the processing
			if(responseBean.getObHeader().getStatusCode() == null){
				responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
				responseBean.getObHeader().setStatusMessage(null);
			}
		}catch(Exception e){
			log.info(this.getClass().toString(), e);
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			responseBean.getObHeader().setStatusMessage(e.getMessage());
		}
		
		response.setBDObject(responseBean);
		return response;
	}
	
	/**
	 * Return true if the vcresponse is negative
	 * @param ibResponse
	 * @return
	 */
	protected boolean processVCResponseError(VCResponse vcResponse, R responseBean, boolean isDataExpected){
		responseBean.getObHeader().setStatusCode(null);
		if(!vcResponse.getResponse_code().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS)){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_OMNI_PREFIX+vcResponse.getResponse_code());
			responseBean.getObHeader().setStatusMessage(vcResponse.getResponse_desc());
			return true;
		} else if(isDataExpected && vcResponse.getData() == null){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_NO_RESULT);
			responseBean.getObHeader().setStatusMessage(vcResponse.getResponse_desc());
			return true;
		}
		return false;
	}
	
	/**
	 * Return true if the vcresponse is negative
	 * @param ibResponse
	 * @return
	 */
	protected boolean processVCResponseError(VCResponse vcResponse, R responseBean){
		return processVCResponseError(vcResponse, responseBean, true);
	}
	
	protected Properties getMessageProperties(String locale){
		MessageManager ccMsg = new MessageManager();
		// check for the languageCode is EN or ID for properties
		if (locale != null && !locale.isEmpty() && locale.equalsIgnoreCase("EN"))
			return ccMsg.getDefaultProperties();
		else
			return ccMsg.getINProperties();
	}
	
	protected boolean verifyCR(S requestBean, R responseBean, String deviceId, VCGenericService vcService) throws Exception{
		TokenRequestData requestData = new TokenRequestData();
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setDevice_id(deviceId);
		requestData.setRequest_id(requestBean.getRequestId());
		requestData.setResponse_code(requestBean.getResponseCode());
		VCResponse<TokenResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.TOKEN_CR_VERIFY, 
				requestData, 
				TokenResponseData.class, 
				true);
		if(processVCResponseError(vcResponse, responseBean, false)){
			return false;
		}else{
			return true;
		}
	}
	
	public abstract void processInternal(String locale, String sessionId, String trxId, S requestBean, R responseBean, VCGenericService vcService) throws Exception;
}
