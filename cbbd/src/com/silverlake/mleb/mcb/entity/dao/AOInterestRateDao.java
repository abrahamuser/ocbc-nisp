package com.silverlake.mleb.mcb.entity.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import comx.silverlake.mleb.mcb.entity.AOInterestRate;

@Service
@Repository
@Transactional
public class AOInterestRateDao extends DAO
{
	private static Logger log = LogManager.getLogger(AOInterestRateDao.class);


	@PersistenceContext(unitName="db_mleb")
	EntityManager emx ;

	public List<AOInterestRate> getInterestRateByKPR(){

		EntityManager em  = getEntityManager();
		String sql = "FROM "+AOInterestRate.class.getSimpleName() + " WHERE product_type=:prodType";
		List<AOInterestRate> listDoc = em.createQuery(sql).setParameter("prodType", "KPRPROD").getResultList();
		return listDoc;


	}

	public List<AOInterestRate> getInterestRateByKTA(String tenor, String isOverLimit){
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AOInterestRate.class.getSimpleName() + " WHERE product_type=:prodType AND tenor=:tenor AND is_over_limit=:isOverLimit ";
		List<AOInterestRate> listDoc = em.createQuery(sql).setParameter("prodType", "KTAOC1")
				.setParameter("tenor", tenor).setParameter("isOverLimit", isOverLimit).getResultList();
		return listDoc;

	}

	public List<AOInterestRate> getInterestRateByKPM(String tenor){

		EntityManager em  = getEntityManager();
		String sql = "FROM "+AOInterestRate.class.getSimpleName() + " WHERE product_type=:prodType AND tenor=:tenor";
		List<AOInterestRate> listDoc = em.createQuery(sql).setParameter("prodType", "KPMPROD").setParameter("tenor", tenor).getResultList();
		return listDoc;
	}

	@Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return emx;
	}



}