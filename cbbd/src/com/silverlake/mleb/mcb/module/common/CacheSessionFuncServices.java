package com.silverlake.mleb.mcb.module.common;

import java.lang.reflect.ParameterizedType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.silverlake.mleb.mcb.bean.ObRequestCache;
import com.silverlake.mleb.mcb.bean.ObResponseCache;
import com.silverlake.mleb.mcb.bean.ObSessionCache;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;

/**
 * This is a cache session based service class that extends on SessionFuncServices.
 * This class generalized the session cache handling to/from ccws.
 * Any service that required to utilized session cache is recommended to extend this class.
 * 
 * @author Alex Loo
 *
 * @param <S> the request bean class that is defined to the service
 * @param <R> the response bean class that is defined to the service
 * @param <C> the cache bean class that is defined to the service
 */
public abstract class CacheSessionFuncServices<S extends ObRequestCache<C>, R extends ObResponseCache, C extends ObSessionCache> extends SessionFuncServices<S, R>{
	private static Logger log = LogManager.getLogger(CacheSessionFuncServices.class);
	
	public abstract void processInternalWithCache(String locale, String sessionId, String trxId, S requestBean, R responseBean, C cacheBean, VCGenericService vcService) throws Exception;
	
	public void processInternal(String locale, String sessionId, String trxId, S requestBean, R responseBean, VCGenericService vcService) throws Exception{
//		Map<String, C> requestCacheList = requestBean.getSessionCache();
		
		ParameterizedType superClass = (ParameterizedType) getClass().getGenericSuperclass();
        @SuppressWarnings("unchecked")
        Class<C> cacheType = (Class<C>)superClass.getActualTypeArguments()[2];
		C cacheBean = cacheType.newInstance();
		
		processInternalWithCache(locale, sessionId, trxId, requestBean, responseBean, cacheBean, vcService);
		
		if(responseBean.getObHeader().getStatusCode() == null){
			responseBean.setSessionCache(cacheBean);
		}else {
			responseBean.setSessionCache(null);
		}
	}
}
