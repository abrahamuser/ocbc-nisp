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
import com.silverlake.mleb.mcb.bean.ObRequestCache;
import com.silverlake.mleb.mcb.bean.ObResponseCache;
import com.silverlake.mleb.mcb.bean.ObSessionCache;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant.CACHE_IDS;

public abstract class CacheSessionMiBServices<S extends ObRequestCache<C>, R extends ObResponseCache, C extends ObSessionCache> extends MiBServices{
	private static Logger log = LogManager.getLogger(CacheSessionMiBServices.class);
	
	UserLoginSession userloginsession = new UserLoginSession();
	SecurityUtil secUtil = new SecurityUtil();
	
	protected S obRequest;
	protected R obResponse;
	
	public CacheSessionMiBServices(WebServiceContext wsContext) {
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
			
			WSCache cacheAnnotation = this.getClass().getAnnotation(WSCache.class);
			if(cacheAnnotation != null){
				//Double posting cache checking
				if(cacheAnnotation.doublePostingCacheCheck() != VCMethodConstant.CACHE_IDS.EMPTY) {
					Object cacheObject = httpSession.getAttribute(cacheAnnotation.doublePostingCacheCheck().getId());
					if(cacheObject == null){
						log.info("Suspected double posting due to cache "+cacheAnnotation.doublePostingCacheCheck().getId()+" not exist.");
						obResponse.setObHeader(new ObHeaderResponse());
						obResponse.getObHeader().setStatusCode(WSConstant.CCMCB_DOUBLE_POSTING);
						return;
					}
				}
				
				int ii = 0;
				for(CACHE_IDS cacheId:cacheAnnotation.previousCacheIds()){
					if(cacheId.getId() != null && !cacheId.getId().isEmpty()){
						C cacheObject = (C)httpSession.getAttribute(cacheId.getId());
						if(cacheAnnotation.isPrevCacheMandatory()[ii]){
							if(cacheObject == null){
								log.info(cacheId.getId() + " not found");
								obResponse.setObHeader(new ObHeaderResponse());
								obResponse.getObHeader().setStatusCode(WSConstant.CCWS_COMMON_ERROR);
								return;
							}
						}
						if(cacheObject != null){
							obRequest.addSessionCache(cacheId.getId(), cacheObject);
						}
					}
					ii++;
				}
			}
			
			processData();
		}catch (Exception e){
			log.catching(e);
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
		if(super.obResponse instanceof ObResponseCache){
			obResponse = (R) super.obResponse;
			processSessionResponse();
			
			WSCache cacheAnnotation = this.getClass().getAnnotation(WSCache.class);
			if(cacheAnnotation != null){
				if(cacheAnnotation.cacheId().getId() !=null && !cacheAnnotation.cacheId().getId().isEmpty()){
					if(obResponse.getObHeader().getStatusCode().equalsIgnoreCase(MiBConstant.MIB_COMMON_SUCCESS)){
						httpSession.setAttribute(cacheAnnotation.cacheId().getId(), obResponse.getSessionCache());
						obResponse.setSessionCache(null);
					}else {
						httpSession.removeAttribute(cacheAnnotation.cacheId().getId());//Remove self cache once failed 
					}
				}
				
				int ii = 0;
				for(CACHE_IDS cacheId:cacheAnnotation.clearPreviousCacheIds()){
					if(cacheId.getId() != null && !cacheId.getId().isEmpty()){
						if(cacheAnnotation.isSuccessClearPreviousCache()[ii]){
							httpSession.removeAttribute(cacheId.getId());//Success or failed also clear the cache
						}else{
							if(!obResponse.getObHeader().getStatusCode().equalsIgnoreCase(MiBConstant.MIB_COMMON_SUCCESS)){
								httpSession.removeAttribute(cacheId.getId());//Only clear the cache when failed
							}
						}
					}
					ii++;
				}
				
				//Remove the cache which check the double posting for success response. If this ws is recalled in subsequence call, will throw double posting error.
				if(obResponse.getObHeader().getStatusCode().equalsIgnoreCase(MiBConstant.MIB_COMMON_SUCCESS) &&
						cacheAnnotation.doublePostingCacheCheck() != VCMethodConstant.CACHE_IDS.EMPTY) {
					httpSession.removeAttribute(cacheAnnotation.doublePostingCacheCheck().getId());
				}
			}
		}else {
			WSCache cacheAnnotation = this.getClass().getAnnotation(WSCache.class);
			if(cacheAnnotation != null){
				if(cacheAnnotation.cacheId().getId() !=null && !cacheAnnotation.cacheId().getId().isEmpty()){
					if(!super.obResponse.getObHeader().getStatusCode().equalsIgnoreCase(MiBConstant.MIB_COMMON_SUCCESS)){
						httpSession.removeAttribute(cacheAnnotation.cacheId().getId());//Remove self cache once failed
					}
				}
				
				int ii = 0;
				for(CACHE_IDS cacheId:cacheAnnotation.clearPreviousCacheIds()){
					if(cacheId.getId() != null && !cacheId.getId().isEmpty()){
						if(cacheAnnotation.isSuccessClearPreviousCache()[ii]){
							httpSession.removeAttribute(cacheId.getId());//Success or failed also clear the cache
						}else{
							if(!super.obResponse.getObHeader().getStatusCode().equalsIgnoreCase(MiBConstant.MIB_COMMON_SUCCESS)){
								httpSession.removeAttribute(cacheId.getId());//Only clear the cache when failed
							}
						}
					}
					ii++;
				}
				
				//Remove the cache which check the double posting for success response. If this ws is recalled in subsequence call, will throw double posting error.
				if(super.obResponse.getObHeader().getStatusCode().equalsIgnoreCase(MiBConstant.MIB_COMMON_SUCCESS) &&
						cacheAnnotation.doublePostingCacheCheck() != VCMethodConstant.CACHE_IDS.EMPTY) {
					httpSession.removeAttribute(cacheAnnotation.doublePostingCacheCheck().getId());
				}
			}
		}
	}
	
	protected abstract void processData() throws Exception;
	protected abstract void processSessionResponse();
}
