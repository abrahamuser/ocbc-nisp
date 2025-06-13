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

import comx.silverlake.mleb.mcb.entity.AOCreditCardList;

@Service
@Repository
@Transactional
public class AOCreditCardListDao extends DAO
{
	private static Logger log = LogManager.getLogger(AOCreditCardListDao.class);


	@PersistenceContext(unitName="db_mleb")
	EntityManager emx ;
	
	public List<AOCreditCardList> getAllCreditCardList(String yearlyIncome)
	{
		String sqlYoyage = "";
		if(yearlyIncome != null && !yearlyIncome.equals("")){
			Integer i = Integer.parseInt(yearlyIncome);
			if(i != null && i <= MiBConstant.YEARLY_VOYAGE_INCOME){
				sqlYoyage += " and UPPER(product_type) <> '" + MiBConstant.CREDIT_CARD_YOYAGE.toUpperCase() + "'";
			}
		}
		
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AOCreditCardList.class.getSimpleName() + " WHERE status = :status" + sqlYoyage;
		List<AOCreditCardList> listCard = em.createQuery(sql).setParameter("status", "A").getResultList();
		return listCard;
	}
	
	
	@Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return emx;
	}
	
	
	
}