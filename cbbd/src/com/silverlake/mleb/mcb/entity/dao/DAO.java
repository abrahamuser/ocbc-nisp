package com.silverlake.mleb.mcb.entity.dao;


import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.hibernate.SQLQuery;
import org.springframework.transaction.annotation.Transactional;

import comx.silverlake.mleb.mcb.entity.AOParamCache;



@Transactional
public abstract class DAO
{
	
	
	
	public abstract EntityManager getEntityManager();
	
	
	public Object findByID(Class objClass, Object obj) {
		EntityManager em  = getEntityManager();
		Object rsObj = em.find(objClass, obj);
		return rsObj;
	}
	
	public void deleteByID(Class objClass, Object obj) {
		EntityManager em  = getEntityManager();
		Object rsObj = em.find(objClass, obj);
		if(rsObj != null){
		em.remove(rsObj);
		}
	}

	public int mergeEntity(String sql)
	{
		EntityManager em  = getEntityManager();
		em.createQuery(sql);
		return 0;
	}
	
	public int insertEntity(Object obj) {
		
		EntityManager em  = getEntityManager();
		em.persist(obj);
		em.flush();
		
		return 1;
	}
	
	
	public int insertMultiEntity(List<Object> objs) {
		
		EntityManager em  = getEntityManager();
		for(Object obj :objs)
		{
			em.persist(obj);
		}
		em.flush();
		
		return objs.size();
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

	
	public int updateSQLParam_timestamp(String SQL,  Hashtable params)
	{
		EntityManager em  = getEntityManager();
		Query query =  em.createQuery(SQL);
		Enumeration keys = params.keys();
		while( keys.hasMoreElements() ) {
		  Object key = keys.nextElement();
		  Object value = params.get(key);
		  if(key.toString().endsWith("_timestamp"))
		  {
			  query.setParameter(key.toString(), (Date)value, TemporalType.TIMESTAMP);
		
		  }
		  else
		  {
			  query.setParameter(key.toString(), value.toString());
		  }
	
		  
		}
		
		return query.executeUpdate();
	}
	
	public int removeParamCache()
	{
		EntityManager em  = getEntityManager();
		String sql = "DELETE "+AOParamCache.class.getSimpleName()+ " where row_id > 0 ";
		return em.createQuery(sql).executeUpdate();
	}
	
}