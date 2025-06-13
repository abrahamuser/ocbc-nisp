package com.silverlake.mleb.mcb.entity.dao;


import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silverlake.mleb.mcb.entity.CustomerEvent;

@Service
@Repository
@Transactional
public class CustEventDao extends DAO
{
	private static Logger log = LogManager.getLogger(CustEventDao.class);


	@PersistenceContext(unitName="db_mleb")
	EntityManager emx ;
	
	
	public List<CustomerEvent> getCustEventByPage(String cif, Date startDate, Date endDate, String pageNum, String totalRecord)
	{
		EntityManager em  = getEntityManager();
		//String sql = "FROM "+CustomerEvent.class.getSimpleName()+" WEHRE cif = :cif order by request_datetime DESC";
		
		CriteriaBuilder cb  = em.getCriteriaBuilder();
		CriteriaQuery<CustomerEvent> cq = cb.createQuery(CustomerEvent.class);
		Root<CustomerEvent> from = cq.from(CustomerEvent.class);
		CriteriaQuery<CustomerEvent> select = cq.select(from);
		
		
		
		
		/*if(lastRowId!=null && lastRowId.trim().length()>0)
		{
			CustomerEvent lastEvent = em.find(CustomerEvent.class, Integer.parseInt(lastRowId));
			
			Path<Date> dateCreatedPath = from.get("requestDatetime");
			cq.where(cb.equal(from.get("cif"), cif),cb.lessThan(dateCreatedPath, lastEvent.getRequestDatetime()));
		}
		else
		{
			cq.where(cb.equal(from.get("cif"), cif));
		}*/
		
		int firstRow = 1;
		if(pageNum!=null && pageNum.trim().length()>0)
		{
			firstRow = Integer.parseInt(pageNum);
			firstRow =( firstRow-1)*Integer.parseInt(totalRecord);
		}
		
		if(null!=startDate && null!=endDate)
		{
			log.info("FILTER DATE BETWEEN : "+startDate.toString()+ " --- "+endDate.toString());
			cq.where(cb.equal(from.get("cif"), cif),cb.between(from.<Date>get("requestDatetime"), startDate, endDate));
		}
		else
		{
			cq.where(cb.equal(from.get("cif"), cif));
		}
		
		
		
		
		cq.orderBy(cb.desc(from.get("requestDatetime")));
		return em.createQuery(cq).setFirstResult(firstRow).setMaxResults(Integer.parseInt(totalRecord)).getResultList();
	}
	
	
	
	public Long getMaxCustEventByCif(String cif, Date startDate, Date endDate)
	{
		EntityManager em  = getEntityManager();
		//String sql = "FROM "+CustomerEvent.class.getSimpleName()+" WEHRE cif = :cif order by request_datetime DESC";
		
		CriteriaBuilder cb  = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<CustomerEvent> from = cq.from(CustomerEvent.class);
		CriteriaQuery<Long> select = cq.select(cb.count(from));
		
		if(null!=startDate && null!=endDate)
		{
			cq.where(cb.equal(from.get("cif"), cif),cb.between(from.<Date>get("requestDatetime"), startDate, endDate));
		}
		else
		{
			select.where(cb.equal(from.get("cif"), cif));
		}
		
		//select.orderBy(cb.desc(from.get("requestDatetime")));
		
		
		return em.createQuery(select).getSingleResult();
	}
	
	
	
	
	@Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return emx;
	}
	
	
	
}