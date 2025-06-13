package com.silverlake.mleb.pex.entity.dao;


import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

//import org.apache.log4j.Logger;
import org.apache.logging.log4j.Logger;
import org.hibernate.SQLQuery;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public abstract class DAO
{
	
	
	
	public abstract EntityManager getEntityManager();
	
	
	public Object findByID(Class objClass, Object obj) {
		EntityManager em  = getEntityManager();
		Object rsObj = em.find(objClass, obj);
		return rsObj;
	}

	
	public int insertEntity(Object obj) {
		
		EntityManager em  = getEntityManager();
		em.persist(obj);
		em.flush();
		
		return 1;
	}
	
	
	public int insertMultiEntity(List<Object> objs, Logger log)
	{
		Date startDate = new Date();
		log.info("NAGEN INSERT STAET TIME :: "+startDate);
		EntityManager em  = getEntityManager();
		for(Object obj :objs)
		{
			em.persist(obj);
		}
		em.flush();
		Date endDate = new Date();
		log.info("NAGEN INSERT END TIME :: "+endDate);
		log.info("TOTAL TIME TO INSERT "+objs.size()+" ROWS is "+((endDate.getTime() - startDate.getTime())/1000)+" Seconds");
		return objs.size();
	}

	
	public void deleteEntity(Object obj) {
		EntityManager em  = getEntityManager();
		em.remove(obj);
		em.flush();
	}
	
	
	public Object updateEntity(Object obj) {
		EntityManager em  = getEntityManager();
		Object ss = em.merge(obj);
		
		return ss;
	}
	
	
	public List selectQuery(String JQL)
	{
		EntityManager em  = getEntityManager();
		return em.createQuery(JQL).getResultList();
	}
	
	
	public List selectSQLQuery(String SQL,String[] scalar)
	{
		EntityManager em  = getEntityManager();
		//Session sess = em.unwrap(Session.class);
		//SQLQuery query = sess.createSQLQuery(SQL);
		//return query;
		Query qy = em.createNativeQuery(SQL);
	
		SQLQuery sqy = qy.unwrap(SQLQuery.class);
		
		for(String sc:scalar)
		{
			sqy.addScalar(sc);
		}
		
		
		
		
		
        return qy.getResultList();
	}




	
	public int updateSQL(String SQL)
	{
		EntityManager em  = getEntityManager();
		return em.createQuery(SQL).executeUpdate();
	}
	
	
	public int updateSQLParam(String SQL,  Hashtable params)
	{
		EntityManager em  = getEntityManager();
		Query query =  em.createQuery(SQL);
		Enumeration keys = params.keys();
		while( keys.hasMoreElements() ) {
		  Object key = keys.nextElement();
		  Object value = params.get(key);
		  
		  query.setParameter(key.toString(), value.toString());
		  
		}
		
		return query.executeUpdate();
	}
	
	public List selectQueryParam(String JQL, Hashtable params)
	{
		EntityManager em  = getEntityManager();
		Query query =  em.createQuery(JQL);
		
		
		Enumeration keys = params.keys();
		while( keys.hasMoreElements() ) {
		  Object key = keys.nextElement();
		  Object value = params.get(key);
		  
		  query.setParameter(key.toString(), value.toString());
		  
		}

		
		
		return query.getResultList();
	}

	public List selectQueryLimited(String JQL, int x)
	{
		return getEntityManager().createQuery(JQL).setMaxResults(x).getResultList();
	}
	
	public Object insertandReturnEntity(Object obj)
	{
		
		EntityManager em  = getEntityManager();
		em.persist(obj);
		em.flush();
		
		return obj;
	}
	
	
	
}