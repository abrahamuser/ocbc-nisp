package com.silverlake.mleb.ccmcb.module.common;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

import javax.xml.ws.WebServiceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.silverlake.mleb.ccmcb.constant.WSConstant;
import com.silverlake.mleb.ccmcb.module.MiBServices;
import com.silverlake.mleb.ccmcb.util.SecurityUtil;
import com.silverlake.mleb.ccmcb.util.UserLoginSession;
import com.silverlake.mleb.mcb.bean.ObHeaderResponse;
import com.silverlake.mleb.mcb.bean.ObRequest;
import com.silverlake.mleb.mcb.bean.ObResponse;
import com.silverlake.mleb.mcb.bean.ObResponseCache;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant.CACHE_IDS;

public abstract class SessionMiBServices<S extends ObRequest, R extends ObResponse> extends MiBServices{
	private static Logger log = LogManager.getLogger(SessionMiBServices.class);
	
	UserLoginSession userloginsession = new UserLoginSession();
	SecurityUtil secUtil = new SecurityUtil();
	
	protected S obRequest;
	protected R obResponse;
	
	public SessionMiBServices(WebServiceContext wsContext) {
		super(wsContext);
		try{
			ParameterizedType superClass = (ParameterizedType) getClass().getGenericSuperclass();
	        @SuppressWarnings("unchecked")
	        Class<S> stype = (Class<S>)superClass.getActualTypeArguments()[0];
	        
	        @SuppressWarnings("unchecked")
	        Class<R> rType = (Class<R>)superClass.getActualTypeArguments()[1];
	        
	        obResponse = rType.newInstance();
			
	        obRequest = stype.newInstance();
			obRequest.setUserContext(userloginsession.getSessionKey(httpSession));
			obRequest.setObUser(userloginsession.getLoginSessionKeyData(httpSession));
			
			func_id = this.getClass().getAnnotation(WSClass.class).functionId().getCode();
		}catch(Exception e){
			log.info(this.getClass().toString(), e);
		}
	}
	
	public void setCRData(String requestId, String responseCode){
		obRequest.setRequestId(requestId);
		obRequest.setResponseCode(responseCode);
	}
	
	public void processGenericData(Object ... params) {
		try{
			boolean isCRVerify = this.getClass().getAnnotation(WSClass.class).isCRVerify();
			if(isCRVerify){
				obRequest.setIsCRVerify(true);
				if(obRequest.getRequestId() == null || obRequest.getRequestId().isEmpty() || obRequest.getResponseCode() == null ||  obRequest.getResponseCode().isEmpty() ){
					log.info("Incomplete parameters for CR Verfication");
					obResponse.setObHeader(new ObHeaderResponse());
					obResponse.getObHeader().setStatusCode(WSConstant.CCWS_INVALID_INPUT_ERROR);
					return;
				}
			}else{
				obRequest.setIsCRVerify(false);
			}
			
			Field[] fields = this.getClass().getDeclaredFields();
			int i = 0;
			for(Field field:fields){
				field.setAccessible(true);
				if(field.getAnnotation(WSParameter.class) != null){
					WSParameter annonation = field.getAnnotation(WSParameter.class);
					if(params[i] != null){
						if(annonation.isMandatory() && !annonation.isEmptyAllowed()){
							if(params[i].toString().isEmpty()){
								log.info(field.getName() + " cannot be empty");
								obResponse.setObHeader(new ObHeaderResponse());
								obResponse.getObHeader().setStatusCode(WSConstant.CCWS_INVALID_INPUT_ERROR);
								return;
							}
						}
						if(field.getType().isAssignableFrom(params[i].getClass())){
							field.set(this, params[i]);
						}else{
							log.error(field.getName()+" type not matched with "+params[i].getClass()+". Please check the coding");
							obResponse.setObHeader(new ObHeaderResponse());
							obResponse.getObHeader().setStatusCode(WSConstant.CCWS_MIB_ERROR);
							return;
						}
					}else{
						if(annonation.isMandatory()){
							log.info(field.getName() + " cannot be empty");
							obResponse.setObHeader(new ObHeaderResponse());
							obResponse.getObHeader().setStatusCode(WSConstant.CCWS_INVALID_INPUT_ERROR);
							return;
						}
						field.set(this, null);
					}
					i ++;
				}
			}
			
			processData();
		}catch (Exception e){
			log.info(this.getClass().toString(), e);
			obResponse.setObHeader(new ObHeaderResponse());
			obResponse.getObHeader().setStatusCode(WSConstant.CCWS_COMMON_ERROR);
		}finally{
			if(obResponse != null && obResponse.getObHeader() != null && obResponse.getObHeader().getStatusCode() != null){
				super.obResponse = obResponse;
			}
		}
	}

	@Override
	public ObRequest getBDObject() {
		return obRequest;
	}
	
	@Override
	public void processResponse() {
		if(super.obResponse instanceof ObResponse){
			obResponse = (R) super.obResponse;
			processSessionResponse();
		}
	}
	
	protected abstract void processData() throws Exception;
	protected abstract void processSessionResponse();
}
