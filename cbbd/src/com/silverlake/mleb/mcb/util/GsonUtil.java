package com.silverlake.mleb.mcb.util;


import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.RoundingMode;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;


public class GsonUtil {
	
	protected static GsonBuilder gsonBuilder = new GsonBuilder()
					.disableHtmlEscaping() 
					.registerTypeAdapter(Double.class,  new JsonSerializer<Double>() {
				        @Override
				        public JsonElement serialize(final Double src, final Type typeOfSrc, final JsonSerializationContext context) {
				            BigDecimal value = BigDecimal.valueOf(src);
				
				            return new JsonPrimitive(value.setScale(7, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString());
				        }
					})
					.registerTypeAdapter(BigDecimal.class,  new JsonSerializer<BigDecimal>() {
				        @Override
				        public JsonElement serialize(final BigDecimal src, final Type typeOfSrc, final JsonSerializationContext context) {
				        		return new JsonPrimitive(src.setScale(7, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString());
				        	
				        }
					})
					.registerTypeAdapter(Integer.class,  new JsonSerializer<Integer>() {
				        @Override
				        public JsonElement serialize(final Integer src, final Type typeOfSrc, final JsonSerializationContext context) {
				
//				        	Integer target = (int)Math.round(src);
				            return new JsonPrimitive(src);
				        }
					});
	
	private GsonUtil() {
		
		
	}
	
	public static Gson getPrettyPrintingGson()
	{
		return gsonBuilder.setPrettyPrinting().create();
	}
	
	public static Gson getSerializeNullsGson()
	{
		return gsonBuilder.setPrettyPrinting().serializeNulls().create();
	}
	
	public static Gson getPrettyPrintingGson(String[] excludeFields){
		return gsonBuilder.setExclusionStrategies(new GsonFieldExclusionStrategy(excludeFields)).setPrettyPrinting().create();
	}
}
