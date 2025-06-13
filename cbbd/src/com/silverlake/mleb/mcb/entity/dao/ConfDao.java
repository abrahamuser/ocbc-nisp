package com.silverlake.mleb.mcb.entity.dao;


import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silverlake.mleb.mcb.entity.McbConf;

@Service
@Repository
@Transactional
public class ConfDao extends DAO
{
	private static Logger log = LogManager.getLogger(ConfDao.class);


	@PersistenceContext(unitName="db_mleb")
	EntityManager emx ;
	
	
	public McbConf getMibConf()
	{
		EntityManager em  = getEntityManager();
		McbConf mibConf = null;
		String sql = "FROM "+McbConf.class.getSimpleName()+" WHERE status = 'ACTIVE' ";
		List<McbConf> mib = em.createQuery(sql).getResultList();
		
		if(null!=mib && mib.size()==1)
		{
			return mib.get(0);
		}
		
		return mibConf;
	}
	
	public int updateSessionExpiration(int ssession)
	{
		EntityManager em  = getEntityManager();
		String sql = "UPDATE "+McbConf.class.getSimpleName()+" SET session_duration= :ss WHERE status = 'ACTIVE'";
		return em.createQuery(sql).setParameter("ss", ssession).executeUpdate();	
	}
	
	public int resetReloadMessageStatus(Date update_date, String originalDescription, String updatedDecription){
		EntityManager em  = getEntityManager();
		String updateSql = "UPDATE " + McbConf.class.getSimpleName()
				+ " set description = '"+updatedDecription+"' where description = '" + originalDescription
				+ "' AND status = 'ACTIVE' AND update_date = :update_dt__timestamp";
		return em.createQuery(updateSql).setParameter("update_dt__timestamp", update_date).executeUpdate();	
	}
	
	@Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return emx;
	}
	
	
	
}