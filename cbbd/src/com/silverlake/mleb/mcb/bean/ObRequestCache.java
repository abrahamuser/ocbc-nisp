
package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ObRequestCache<C extends ObSessionCache> extends ObRequest implements Serializable {
	Map<String, C> sessionCache;//Only request have requirement to extract information for more than 1 previous cache 

	public Map<String, C> getSessionCache() {
		return sessionCache;
	}

	public void setSessionCache(Map<String, C> sessionCache) {
		this.sessionCache = sessionCache;
	}
	
	public void addSessionCache(String key, C sessionCache) {
		if(this.sessionCache == null){
			this.sessionCache = new HashMap<String, C>();
		}
		this.sessionCache.put(key, sessionCache);
	}
}
