package com.silverlake.mleb.mcb.entity.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silverlake.mleb.mcb.constant.MiBConstant;

import comx.silverlake.mleb.mcb.entity.TDParam;


@Service
@Repository
@Transactional
public class TDParamDAO {
	
	@Autowired DozerBeanMapper mapper;
	
	@PersistenceContext(unitName="db_mleb")
	EntityManager em ;
	
	public TDParam getTimeDepositAccountOpeningParameterSettings() {
		
		TDParam entity = null;
		String sql = "FROM "+TDParam.class.getSimpleName();
		List<TDParam> list = em.createQuery(sql).getResultList();
		if(list!=null && !list.isEmpty())
			entity = list.get(0);
		
		return entity;
		
	}
	
	public String getTimeDepositAccountOpeningTermNConditionFilePath(String localeCode)
	{
		String strFilePath = null;
		
		String sql = "FROM "+TDParam.class.getSimpleName();
		List<TDParam> list = em.createQuery(sql).getResultList();
		if(list!=null && !list.isEmpty())
		{	
			TDParam entity = list.get(0);
			if(localeCode.equalsIgnoreCase(MiBConstant.LANG_EN))
				strFilePath = entity.getTncEn();
			else if(localeCode.toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase()))
				strFilePath = entity.getTncCn();
			else
				strFilePath = entity.getTncIn();
		}
		
		return strFilePath;
	}
	

}
