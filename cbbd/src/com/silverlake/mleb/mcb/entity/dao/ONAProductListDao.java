package com.silverlake.mleb.mcb.entity.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import comx.silverlake.mleb.mcb.entity.ONAProductList;
import comx.silverlake.mleb.mcb.entity.ONAProductTitleDesc;

@Service
@Repository
@Transactional
public class ONAProductListDao extends DAO {

	private static Logger log = LogManager.getLogger(ONAProductListDao.class);


	@PersistenceContext(unitName="db_mleb")
	EntityManager emx ;
	
	public List<ONAProductList> getAllProduct()
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+ONAProductList.class.getSimpleName() + " WHERE status =:status";
		List<ONAProductList> listPostal = em.createQuery(sql).setParameter("status", "A").getResultList();
		return listPostal;
	}
	
	public List<ONAProductList> getProductByProductKey(String productKey)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+ONAProductList.class.getSimpleName() + " WHERE status =:status AND prod_key=:productKey";
		List<ONAProductList> listPostal = em.createQuery(sql).setParameter("status", "A").setParameter("productKey", productKey).getResultList();
		return listPostal;
	}
	
	public List<ONAProductList> getProductByProductType(String productType)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+ONAProductList.class.getSimpleName() + " WHERE status =:status AND product_type=:productType";
		List<ONAProductList> listPostal = em.createQuery(sql).setParameter("status", "A").setParameter("productType", productType).getResultList();
		return listPostal;
	}
	
	public List<ONAProductTitleDesc> getProductTitleDescByProductKey(String productKey, String locale)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+ONAProductTitleDesc.class.getSimpleName() + " WHERE product_key=:productKey AND national_code=:nationalCode";
		List<ONAProductTitleDesc> listPostal = em.createQuery(sql).setParameter("productKey", productKey).setParameter("nationalCode", locale.toUpperCase()).getResultList();
		return listPostal;
	}
	
	@Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return emx;
	}
	
}
