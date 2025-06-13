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
import com.silverlake.mleb.mcb.entity.Tnc;

@Service
@Repository
@Transactional
public class TncDAO extends DAO {
	
	private static Logger log = LogManager.getLogger(TncDAO.class);
	
	@PersistenceContext(unitName="db_mleb")
	EntityManager emx ;
	
	public Tnc getTnc(String productKey, String locale)
	{
		EntityManager em  = getEntityManager();
		
		// if(locale == null || locale.isEmpty() || !locale.equalsIgnoreCase(MiBConstant.LANG_EN))
		// 	locale = MiBConstant.LANG_IN;
		// else 
		// 	locale = MiBConstant.LANG_EN;

		if (locale == null || locale.isEmpty()) {
			// Default to LANG_IN if null/empty
			locale = MiBConstant.LANG_IN;
		} 
		else if (locale.toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase())) {
			// Explicitly handle Chinese
			locale = MiBConstant.LANG_CN;
		} 
		else if (locale.equalsIgnoreCase(MiBConstant.LANG_EN)) {
			// Explicitly handle English
			locale = MiBConstant.LANG_EN;
		} 
		else {
			// Fallback for any other value
			locale = MiBConstant.LANG_IN;
		}
		
		String sql = "FROM "+Tnc.class.getSimpleName() + " where product_key = :productKey and UPPER(locale) = :locale ";
		List<Tnc> resultlist = em.createQuery(sql).setParameter("productKey", productKey).setParameter("locale", locale.toUpperCase()).getResultList();
		
		if(resultlist != null && resultlist.size() > 0)
			return resultlist.get(0);
		
		return null;
	}
	
	
	public List<Tnc> getTnCbyProductCode(String productKey, String locale)
	{
		// if(locale == null || locale.isEmpty() || !locale.equalsIgnoreCase(MiBConstant.LANG_EN))
		// 	locale = MiBConstant.LANG_IN;
		// else 
		// 	locale = MiBConstant.LANG_EN;

		if (locale == null || locale.isEmpty()) {
			// Default to LANG_IN if null/empty
			locale = MiBConstant.LANG_IN;
		} 
		else if (locale.toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase())) {
			// Explicitly handle Chinese
			locale = MiBConstant.LANG_CN;
		} 
		else if (locale.equalsIgnoreCase(MiBConstant.LANG_EN)) {
			// Explicitly handle English
			locale = MiBConstant.LANG_EN;
		} 
		else {
			// Fallback for any other value
			locale = MiBConstant.LANG_IN;
		}
		
		EntityManager em  = getEntityManager();
		String sql = "FROM "+Tnc.class.getSimpleName() + " WHERE product_key =:productKey AND UPPER(locale) = :locale ORDER BY TNC_URL";
		List<Tnc> listTnC = em.createQuery(sql).setParameter("productKey", productKey).setParameter("locale", locale).getResultList();
		return listTnC;
	}
	
	@Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return emx;
	}

}
