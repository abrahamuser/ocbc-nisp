package com.silverlake.mleb.mcb.entity.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import comx.silverlake.mleb.mcb.entity.AONationalityList;


@Service
@Repository
@Transactional
public class AONationalityListDao extends DAO
{
	private static Logger log = LogManager.getLogger(AONationalityListDao.class);


	@PersistenceContext(unitName="db_mleb")
	EntityManager emx ;
	
	public List<AONationalityList> getNationalityList()
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AONationalityList.class.getSimpleName() + " WHERE status = :status";
		List<AONationalityList> listNat = em.createQuery(sql).setParameter("status", "A").getResultList();
		return listNat;
	}
	
	
	@Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return emx;
	}
	
	
	
}