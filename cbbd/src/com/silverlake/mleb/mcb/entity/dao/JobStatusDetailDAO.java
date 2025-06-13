package com.silverlake.mleb.mcb.entity.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import comx.silverlake.mleb.mcb.entity.JobStatusDetail;



@Service
@Repository
@Transactional
public class JobStatusDetailDAO extends DAO {

	@PersistenceContext(unitName="db_mleb")
	EntityManager emx ;
	
	public JobStatusDetail findByJobName(String jobName) {
		
		EntityManager em  = getEntityManager();
		String sql = "FROM "+JobStatusDetail.class.getSimpleName() + " WHERE job_name=:jobName";
		List<JobStatusDetail> result = em.createQuery(sql).setParameter("jobName", jobName).getResultList();
		if(result.size()>0){
			return result.get(0);
		}else{
			return null;
		}
	}
	
	
	public void updateUpdateDate(String jobName) {
		
		Date currDate = new Date();
		
		EntityManager em  = getEntityManager();
		String sql = "UPDATE "+JobStatusDetail.class.getSimpleName()+" SET update_date = :updateDate WHERE job_name = :jobName";
		
		em.createQuery(sql).setParameter("updateDate", currDate, TemporalType.TIMESTAMP).setParameter("jobName", jobName).executeUpdate();
	
	}

	@Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return emx;
	}
}
