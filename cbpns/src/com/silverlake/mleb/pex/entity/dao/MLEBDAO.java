package com.silverlake.mleb.pex.entity.dao;


import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Repository
@Transactional
public class MLEBDAO extends DAO
{
	@PersistenceContext(unitName="db_mleb")
	EntityManager em ;

	@Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}

	//private static Logger log = Logger.getLogger(MicbDAO.class);

	
	/*public Object findByID(Class objClass, Object obj) {
		Object rsObj = em.find(objClass, obj);
		return rsObj;
	}

	
	public int insertEntity(Object obj) {
		
		em.persist(obj);
		em.flush();
		
		return 1;
	}
	
	
	
	public Object insertRetrieveEntity(Object obj) {
		
		em.persist(obj);
		em.flush();
		
		return obj;
	}

	
	public Object updateEntity(Object obj) {
		Object ss = em.merge(obj);
		
		return ss;
	}
	
	public void deleteEntity(Object obj) {
		em.remove(obj);
	}
	
	
	public List selectQuery(String JQL)
	{
		return em.createQuery(JQL).getResultList();
	}

	
	public List selectQueryLimited(String JQL, int x)
	{
		return em.createQuery(JQL).setMaxResults(x).getResultList();
	}


	public EntityManager getEm() {
		return em;
	}


	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	
	public int updateSQL(String SQL)
	{
		return this.em.createQuery(SQL).executeUpdate();
	}
	
	
	public int updateNativeSQL(String SQL)
	{
		return this.em.createNativeQuery(SQL).executeUpdate();
	}
	
	public int updateSQLParam(String SQL,  Hashtable params)
	{
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
		
		Query query =  em.createQuery(JQL);
		
		
		Enumeration keys = params.keys();
		while( keys.hasMoreElements() ) {
		  Object key = keys.nextElement();
		  Object value = params.get(key);
		  
		  query.setParameter(key.toString(), value.toString());
		  
		}

		
		
		return query.getResultList();
	} */
	
}