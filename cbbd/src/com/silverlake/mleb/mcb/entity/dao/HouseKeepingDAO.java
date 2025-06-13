package com.silverlake.mleb.mcb.entity.dao;


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silverlake.mleb.mcb.entity.CustomerEvent;
import com.silverlake.mleb.mcb.entity.MlebRequestLog;
import com.silverlake.mleb.mcb.entity.VcwsTranx;

@Service
@Repository
@Transactional
public class HouseKeepingDAO extends DAO
{
	private static Logger log = LogManager.getLogger(HouseKeepingDAO.class);


	@PersistenceContext(unitName="db_mleb")
	EntityManager emx ;
	
	public int purgingCustomerEvent(Date dateBefore){
		EntityManager em  = getEntityManager();
		String sql = "DELETE "+CustomerEvent.class.getSimpleName()+" WHERE requestDatetime <= :time";
		return em.createQuery(sql).setParameter("time", dateBefore).executeUpdate();
	}
	
	public int purgingVcwsTranx(Date dateBefore){
		EntityManager em  = getEntityManager();
		//Datetime format yyyy-MM-dd HH:mm:ss
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sql = "DELETE "+VcwsTranx.class.getSimpleName()+" WHERE requestTimeStamp <= :time";
		return em.createQuery(sql).setParameter("time", sdf.format(dateBefore)).executeUpdate();
	}
	
	public int purgingMlebRequestLog(Date dateBefore){
		EntityManager em  = getEntityManager();
		String sql = "DELETE "+MlebRequestLog.class.getSimpleName()+" WHERE dateTime <= :time";
		return em.createQuery(sql).setParameter("time", dateBefore).executeUpdate();
	}
		
	@Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return emx;
	}
	
	
	
}