package com.silverlake.mleb.ccmcb.module.common;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.silverlake.mleb.mcb.constant.VCMethodConstant;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface WSCache {
	/**
	 * Cache ids of previous caches that is required by web service
	 * @return cache ids from CacheConstant class
	 */
	VCMethodConstant.CACHE_IDS[] previousCacheIds() default {};
	/**
	 * List of flag for previous caches is mandatory or not, default is false
	 * @return true if it is mandatory
	 */
	boolean[] isPrevCacheMandatory() default false;
	/**
	 * Current web service cache id to be used
	 * @return cache id from CacheConstant class
	 */
	VCMethodConstant.CACHE_IDS cacheId() default VCMethodConstant.CACHE_IDS.EMPTY;
	/**
	 * Cache ids of previous caches that is required to be cleared before response.
	 * @return cache ids from CacheConstant class
	 */
	VCMethodConstant.CACHE_IDS[] clearPreviousCacheIds() default {};
	/**
	 * List of flag for previous caches required to be cleared even success response or not
	 * By default all negative response will clear previous cache
	 * @return true if it is required to be clear even success
	 */
	boolean[] isSuccessClearPreviousCache() default false;
	/**
	 * If any cache value defined here, the cache must exist to call.
	 * If not exist, double posting error will be returned
	 * @return
	 */
	VCMethodConstant.CACHE_IDS doublePostingCacheCheck() default VCMethodConstant.CACHE_IDS.EMPTY;
}
