package com.silverlake.mleb.ccmcb.util;


import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.silverlake.mleb.mcb.constant.MiBConstant;

public class MapperUtil {
	
	private MapperUtil(){
		
	}
	
	public static <S,D> List<D> mapIntoList(List<S> sourceList, Class<D> d){
		java.util.Date defaultValue = null;
		DateConverter converter = new DateConverter(defaultValue);
		ConvertUtils.register(converter, java.util.Date.class);
		BigDecimalConverter bigDecimalConverter = new BigDecimalConverter(0.0000);
		ConvertUtils.register(bigDecimalConverter, java.math.BigDecimal.class);
		
		List<D> result = new ArrayList<D>();
		
		try {
			
			for(S entity: sourceList){
				D bean = d.newInstance();
				BeanUtils.copyProperties(bean, entity);
				result.add(bean);
			}
			
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static <T> void mapToSetter(String fieldName, String value, Serializable object, Class<T> type){
		Class<?> cls = object.getClass();
		try {
			Method m = cls.getDeclaredMethod("set"+fieldName, type);
			m.invoke(object, value);
			
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
	}

	public static <T> void copyFields(Object source, Object target){
		java.util.Date defaultValue = null;
		DateConverter converter = new DateConverter(defaultValue);
		converter.setPatterns(new String[] {
				MiBConstant.OMNI_DATE_FORMAT, 
				"dd-MMM yyyy HH:mm:ss",
				"EEEE dd/MM/yyyy, HH:mm", 
				"dd-MMM-yyyy",
				"dd-MM-yyyy",
				"dd-MMM-yyyy HH:mm:ss",
				"HH:mm:ss",
				"MMM-yyyy",
				"yyyy-MM-dd HH:mm:ss",
				"yyyy-MM-dd"
				});
		ConvertUtils.register(converter, java.util.Date.class);
		
		BigDecimalConverter bigDecimalConverter = new BigDecimalConverter(0.0000);
		ConvertUtils.register(bigDecimalConverter, java.math.BigDecimal.class);
		
		try {
			BeanUtils.copyProperties(target, source);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    public static <T> T parseJSONToBDObject(Object o, Class<T> clazz){
		try{
			Gson gsonBuilder = new GsonBuilder().setPrettyPrinting().create();
		if(o != null){
			if(o instanceof String){
				return gsonBuilder.fromJson(o.toString(), clazz);
			}else{
				return (T)o;
			}
		}
		}catch (JsonSyntaxException e){
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
    
	public static <T> String parseBDObjectToJSON(Object o){
		try{
			Gson gsonBuilder = new GsonBuilder().setPrettyPrinting().create();
			if(o != null){
				return gsonBuilder.toJson(o);
			}
		}catch (JsonSyntaxException e){
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static void copyBeanProperties( final Object source,final Object target,final Iterable<String> properties){

	    final BeanWrapper src = new BeanWrapperImpl(source);
	    final BeanWrapper trg = new BeanWrapperImpl(target);

	    for(final String propertyName : properties){
	        trg.setPropertyValue(
	            propertyName,
	            src.getPropertyValue(propertyName)
	        );
	    }

	}
	
	public static List<Entry<String, String>> sortMapByValueFromA_Z_(Map<String, String> map)
	{
		Set<Entry<String, String>> set = map.entrySet();
        List<Entry<String, String>> list = new ArrayList<Entry<String, String>>(set);
        Collections.sort( list, new Comparator<Map.Entry<String, String>>()
        {
            public int compare( Map.Entry<String, String> o1, Map.Entry<String, String> o2 )
            {
                return (o1.getValue()).compareTo( o2.getValue() );
            }
        } );
        
        return list;
	}
	
	public static List<Entry<String, String>> sortListAmountAscending(Map<String, String> map)
	{
		Set<Entry<String, String>> set = map.entrySet();
        List<Entry<String, String>> list = new ArrayList<Entry<String, String>>(set);
        Collections.sort( list, new Comparator<Map.Entry<String, String>>()
        {
            public int compare( Map.Entry<String, String> o1, Map.Entry<String, String> o2 )
            {
            	BigDecimal o1Value = new BigDecimal(o1.getValue());
            	BigDecimal o2Value = new BigDecimal(o2.getValue());
            	
                return o1Value.compareTo(o2Value);
            }
        } );
        
        return list;
	}
}
