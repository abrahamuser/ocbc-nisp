package com.silverlake.mleb.mcb.entity.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import comx.silverlake.mleb.mcb.entity.ONAGeneralCode;

@Service
@Repository
@Transactional
public class ONADao extends DAO {

	private static Logger log = LogManager.getLogger(ONADao.class);


	@PersistenceContext(unitName="db_mleb")
	EntityManager emx ;
	
	public String getONAGeneralCodeDescription(String gnCode)
	{
		String strValue = null;
		EntityManager em  = getEntityManager();
		String sql = "FROM " + ONAGeneralCode.class.getSimpleName() + " WHERE gnCode =:gnCode and status =:status";
		List<ONAGeneralCode> list = em.createQuery(sql).setParameter("gnCode", gnCode).setParameter("status", "A").getResultList();
		if(list!=null && !list.isEmpty())
			strValue = list.get(0).getGnCodeDescription();
		
		return strValue;
	}
	
	@Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return emx;
	}
	
}
