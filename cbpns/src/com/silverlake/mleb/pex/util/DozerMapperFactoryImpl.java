package com.silverlake.mleb.pex.util;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;

public class DozerMapperFactoryImpl {
	private static DozerBeanMapper dozerBeanMapper = null;
	private static List<String> configFiles = null;
	
	public static DozerBeanMapper getInstance(){
		
		if(dozerBeanMapper==null){
			init();
		}
		
		return dozerBeanMapper;
	}
	
	
	private static void init(){
		configFiles = new ArrayList<String>();
		configFiles.add("dozerBeanMapping.xml");
		
		dozerBeanMapper = new DozerBeanMapper();
		dozerBeanMapper.setMappingFiles(configFiles);
		
		dozerBeanMapper.setCustomFieldMapper(new HibernateFieldMapper());
	}
	
	
}
