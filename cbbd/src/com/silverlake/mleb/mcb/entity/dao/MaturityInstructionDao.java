package com.silverlake.mleb.mcb.entity.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silverlake.mleb.mcb.constant.MiBConstant;

import comx.silverlake.mleb.mcb.entity.MaturityInstruction;

@Service
@Repository
@Transactional
public class MaturityInstructionDao extends DAO
{
	private static Logger log = LogManager.getLogger(MaturityInstructionDao.class);


	@PersistenceContext(unitName="db_mleb")
	EntityManager emx ;
	
	public List<MaturityInstruction> getMaturityInstructionSelection(String locale)
	{
		return getMaturityInstructionSelectionByCategory(MiBConstant.MATURITY_LISTING_TIME_DEPOSIT, locale);
	}
	
	public List<MaturityInstruction> getMaturityInstructionSelectionByCategory(String category, String locale)
	{
		if(!locale.equalsIgnoreCase(MiBConstant.LOCALE_EN) 
			&& locale != null  && locale.toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase()))
			locale = "IN";
		
		
		EntityManager em  = getEntityManager();
		String sql = "FROM "+MaturityInstruction.class.getSimpleName() + " WHERE category=:category and national_code=:nationalCode";
		List<MaturityInstruction> list = em.createQuery(sql).setParameter("nationalCode", locale.toUpperCase())
				.setParameter("category", category.toUpperCase())
				.getResultList();
//		String sql = "FROM "+MaturityInstruction.class.getSimpleName() + " WHERE national_code=:nationalCode";
//		List<MaturityInstruction> list = em.createQuery(sql).setParameter("nationalCode", locale.toUpperCase())
//				.getResultList();
		return list;
	}
	
	@Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return emx;
	}
	
	
	
}