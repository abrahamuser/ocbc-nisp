package com.silverlake.mleb.mcb.entity.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import comx.silverlake.mleb.mcb.entity.AOCreateAccountAmtShare;
import comx.silverlake.mleb.mcb.entity.AOCreditCardList;
import comx.silverlake.mleb.mcb.entity.AOProductList;
import comx.silverlake.mleb.mcb.entity.AOProductTitleDesc;

@Service
@Repository
@Transactional
public class AOProductListDao extends DAO
{
	private static Logger log = LogManager.getLogger(AOProductListDao.class);


	@PersistenceContext(unitName="db_mleb")
	EntityManager emx ;
	
	public List<AOProductList> getAllProduct()
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AOProductList.class.getSimpleName() + " WHERE status =:status";
		List<AOProductList> listPostal = em.createQuery(sql).setParameter("status", "A").getResultList();
		return listPostal;
	}
	
	public List<AOProductList> getProductByProductKey(String productKey)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AOProductList.class.getSimpleName() + " WHERE status =:status AND prod_key=:productKey";
		List<AOProductList> listPostal = em.createQuery(sql).setParameter("status", "A").setParameter("productKey", productKey).getResultList();
		return listPostal;
	}
	
	public List<AOProductList> getProductByProductType(String productType)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AOProductList.class.getSimpleName() + " WHERE status =:status AND product_type=:productType";
		List<AOProductList> listPostal = em.createQuery(sql).setParameter("status", "A").setParameter("productType", productType).getResultList();
		return listPostal;
	}
	
	public List<AOProductTitleDesc> getProductTitleDescByProductKey(String productKey, String locale)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AOProductTitleDesc.class.getSimpleName() + " WHERE product_key=:productKey AND national_code=:nationalCode";
		List<AOProductTitleDesc> listPostal = em.createQuery(sql).setParameter("productKey", productKey).setParameter("nationalCode", locale.toUpperCase()).getResultList();
		return listPostal;
	}
	
	public List<AOCreateAccountAmtShare> getAmountShareByProductType(String productType)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AOCreateAccountAmtShare.class.getSimpleName() + " WHERE product_type=:productType";
		List<AOCreateAccountAmtShare> listAmountShare = em.createQuery(sql).setParameter("productType", productType).getResultList();
		return listAmountShare;
		
	}
	
	public List<AOCreditCardList> getCardTypeByProductType(String productType){
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AOCreditCardList.class.getSimpleName() + " WHERE product_type=:productType";
		List<AOCreditCardList> listCard = em.createQuery(sql).setParameter("productType", productType).getResultList();
		return listCard;
	}
	
	@Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return emx;
	}
	
	
	
}