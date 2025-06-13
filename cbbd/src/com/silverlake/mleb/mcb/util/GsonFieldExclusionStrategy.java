package com.silverlake.mleb.mcb.util;

import java.util.Arrays;
import java.util.HashSet;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class GsonFieldExclusionStrategy implements ExclusionStrategy {
	public HashSet<String> excludeFields;
	
	public GsonFieldExclusionStrategy(String[] fields){
		this.excludeFields = new HashSet<String>();
		excludeFields.addAll(Arrays.asList(fields));
	}

	@Override
	public boolean shouldSkipField(FieldAttributes f) {
		if(excludeFields.contains(f.getName())){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean shouldSkipClass(Class<?> clazz) {
		return false;
	}

}
