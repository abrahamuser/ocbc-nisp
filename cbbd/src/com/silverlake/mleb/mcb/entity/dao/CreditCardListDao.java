package com.silverlake.mleb.mcb.entity.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silverlake.mleb.mcb.constant.MiBConstant;

import comx.silverlake.mleb.mcb.entity.CreditCardList;

@Service
@Repository
@Transactional
public class CreditCardListDao extends DAO
{
	private static Logger log = LogManager.getLogger(CreditCardListDao.class);

	@PersistenceContext(unitName="db_mleb")
	EntityManager emx ;
	
	@SuppressWarnings("unchecked")
	public List<CreditCardList> getCreditCardListByModule(String moduleName, String locale)
	{
		String sqlLocale;
		if (locale.equalsIgnoreCase(MiBConstant.LOCALE_EN)) 
			sqlLocale = "lang = 'EN'";
		else if(locale != null  && locale.toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase()))
			sqlLocale = "lang = 'CN'";
		else
			sqlLocale = "(lang != 'EN' AND lang != 'CN')";
		
		EntityManager em  = getEntityManager();
		String sql = "FROM "+CreditCardList.class.getSimpleName() + " WHERE status = :status AND module_name = :moduleName AND " + sqlLocale;
		List<CreditCardList> listCard = em.createQuery(sql)
				.setParameter("status", "A")
				.setParameter("moduleName", moduleName)
				.getResultList();
		return listCard;
	}	
	
	@Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return emx;
	}	
	
}