package com.silverlake.mleb.pex.util;

import java.util.Enumeration;
import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.util.ObjectUtils;





public class PropertiesUtil extends PropertyPlaceholderConfigurer {
   
	PropertiesManager property = new PropertiesManager();

	 @Override
	 protected void convertProperties(Properties props)
	 {
	  Enumeration<?> propertyNames = props.propertyNames();
	  	while (propertyNames.hasMoreElements()) {
	  		String propertyName = (String) propertyNames.nextElement();
	  		String propertyValue = props.getProperty(propertyName);
	   
	  		String convertedValue = property.getProperty(propertyName);
	  		if(null!=convertedValue)
	  		{
			  
			   if (!ObjectUtils.nullSafeEquals(propertyValue, convertedValue)) {
				   props.setProperty(propertyName, convertedValue);
			   }
	  		}
	
	   
	
	   
	  
	  }
	 }
    
}