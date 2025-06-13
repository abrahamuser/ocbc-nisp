package com.silverlake.mleb.mcb.entity.dao;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silverlake.mleb.mcb.entity.CustomerEvent;
import com.silverlake.mleb.mcb.entity.RegCacheData;

@Service
@Repository
@Transactional
public class RegCacheDao extends DAO{
	
	private static Logger log = LogManager.getLogger(RegCacheDao.class);


	@PersistenceContext(unitName="db_mleb")
	EntityManager emx ;
		
	public int removeRegCache(Date day)
	{
		EntityManager em  = getEntityManager();
		log.info("--------day-------"+day);
		String sql = "DELETE "+RegCacheData.class.getSimpleName()+" WHERE createDate <= :time";
		return em.createQuery(sql).setParameter("time", day).executeUpdate();
	}
	
	
	@Override
	public EntityManager getEntityManager() {
		return emx;
	}

}
