package com.silverlake.mleb.mcb.entity.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import comx.silverlake.mleb.mcb.entity.UTProductInfo;

@Service
@Repository
@Transactional
public class UTProductInfoDAO extends DAO {
	
	private static Logger log = LogManager.getLogger(UTProductInfoDAO.class);

	@PersistenceContext(unitName="db_mleb")
	EntityManager emx ;
	
	public List<UTProductInfo> getUTProductInfoList()
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+UTProductInfo.class.getSimpleName();
		List<UTProductInfo> listUTProductInfo = em.createQuery(sql).getResultList();
		if(listUTProductInfo != null && listUTProductInfo.size() > 0){
			return listUTProductInfo;
		}
		
		return null;
	}
	
	public UTProductInfo retrieveUTProductInfoByProductCategory(String productCategory){
		EntityManager em  = getEntityManager();
		String sql = "FROM "+UTProductInfo.class.getSimpleName() + " where product_category = :productCategory";
		List<UTProductInfo> resultlist = em.createQuery(sql).setParameter("productCategory", productCategory).getResultList();
		
		if(resultlist != null && resultlist.size() > 0)
			return resultlist.get(0);
		
		return null;
	}
	
	public UTProductInfo retrieveUTProductInfoByProductCode(String productCode){
		EntityManager em  = getEntityManager();
		String sql = "FROM "+UTProductInfo.class.getSimpleName() + " where product_code = :productCode";
		List<UTProductInfo> resultlist = em.createQuery(sql).setParameter("productCode", productCode).getResultList();
		
		if(resultlist != null && resultlist.size() > 0)
			return resultlist.get(0);
		
		return null;
	}
	
	public int removeUTProductInfo()
	{
		EntityManager em  = getEntityManager();
		String sql = "DELETE "+UTProductInfo.class.getSimpleName();
		return em.createQuery(sql).executeUpdate();
	}
	
	@Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return emx;
	}
}
