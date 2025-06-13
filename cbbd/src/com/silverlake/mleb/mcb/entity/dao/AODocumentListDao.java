package com.silverlake.mleb.mcb.entity.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import comx.silverlake.mleb.mcb.entity.AODocumentList;


@Service
@Repository
@Transactional
public class AODocumentListDao extends DAO
{
	private static Logger log = LogManager.getLogger(AODocumentListDao.class);


	@PersistenceContext(unitName="db_mleb")
	EntityManager emx ;
	
	public List<AODocumentList> getDocByProduct(String prodType)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AODocumentList.class.getSimpleName() + " WHERE product_code LIKE :prodType";
		List<AODocumentList> listDoc = em.createQuery(sql).setParameter("prodType", "%"+prodType+"%").getResultList();
		return listDoc;
	}
	
	public List<AODocumentList> getDocByProductKey(String productKey)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AODocumentList.class.getSimpleName() + " WHERE product_key LIKE :productKey";
		List<AODocumentList> listDoc = em.createQuery(sql).setParameter("productKey", "%"+productKey+"%").getResultList();
		return listDoc;
	}
	
	@Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return emx;
	}
	
	
	
}