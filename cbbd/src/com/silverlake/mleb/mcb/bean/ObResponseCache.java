
package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;

public class ObResponseCache extends ObResponse implements Serializable {
	ObSessionCache sessionCache;

	public ObSessionCache getSessionCache() {
		return sessionCache;
	}

	public void setSessionCache(ObSessionCache sessionCache) {
		this.sessionCache = sessionCache;
	}
}
