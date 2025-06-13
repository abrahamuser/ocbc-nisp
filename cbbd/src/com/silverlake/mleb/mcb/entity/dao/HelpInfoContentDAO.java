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

import comx.silverlake.mleb.mcb.entity.HelpInfoContent;

@Service
@Repository
@Transactional
public class HelpInfoContentDAO extends DAO {

	private static Logger log = LogManager.getLogger(HelpInfoContentDAO.class);

	@PersistenceContext(unitName = "db_mleb")
	EntityManager emx;

	@SuppressWarnings("unchecked")
	public List<HelpInfoContent> getHelpInfoContent(String productKey, String productPage, String locale) {
		EntityManager em = getEntityManager();

		// if (locale == null || locale.isEmpty() || !locale.equalsIgnoreCase(MiBConstant.LANG_EN))
		// 	locale = MiBConstant.LANG_IN;
		// else
		// 	locale = MiBConstant.LANG_EN;

		if (locale == null || locale.isEmpty()) {
			// Default to LANG_IN if null/empty
			locale = MiBConstant.LANG_IN;
		} 
		else if (locale != null  && locale.toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase())) {
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

		String sql = "FROM " + HelpInfoContent.class.getSimpleName()
				+ " where product_key = :productKey AND product_page =:productPage and UPPER(locale) = :locale ";
		List<HelpInfoContent> resultlist = em.createQuery(sql).setParameter("productKey", productKey)
				.setParameter("productPage", productPage).setParameter("locale", locale.toUpperCase()).getResultList();

		if (resultlist != null && resultlist.size() > 0)
			return resultlist;
		return null;
	}

	@Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return emx;
	}

}
