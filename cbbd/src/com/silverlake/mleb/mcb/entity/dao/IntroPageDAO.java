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

import comx.silverlake.mleb.mcb.entity.IntroPage;

@Service
@Repository
@Transactional
public class IntroPageDAO extends DAO {
	
	private static Logger log = LogManager.getLogger(IntroPageDAO.class);
	
	@PersistenceContext(unitName="db_mleb")
	EntityManager emx ;
	
	public List<IntroPage> getIntroPageByModuleCode(String moduleCode, String locale)
	{
		if(locale.equalsIgnoreCase(MiBConstant.LANG_EN))
			locale = MiBConstant.LANG_EN.toUpperCase();
		else if(locale != null  && locale.toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase()))
			locale = MiBConstant.LANG_CN.toUpperCase();
		else
			locale = MiBConstant.LANG_IN.toUpperCase();

		
		
		EntityManager em  = getEntityManager();
		String sql = "FROM "+IntroPage.class.getSimpleName() + " WHERE module_code=:moduleCode AND national_code=:nationalCode ORDER BY row_id ASC";
		List<IntroPage> list = em.createQuery(sql).setParameter("moduleCode", moduleCode).setParameter("nationalCode", locale).getResultList();
		return list;
	}
	
	@Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return emx;
	}

}
