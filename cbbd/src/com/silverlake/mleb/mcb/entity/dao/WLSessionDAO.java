package com.silverlake.mleb.mcb.entity.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Repository
@Transactional
public class WLSessionDAO extends DAO {
	
	private static Logger log = LogManager.getLogger(WLSessionDAO.class);
	
	@PersistenceContext(unitName="db_mleb")
	EntityManager emx ;
	
	public void clear()
	{
		EntityManager em  = getEntityManager();
		em.createNativeQuery("DELETE FROM SESSION_OCBC.WL_SERVLET_SESSIONS").executeUpdate();
	}
	
	
	@Override
	public EntityManager getEntityManager() {
		return emx;
	}
	

}
