package com.silverlake.mleb.mcb.entity.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silverlake.mleb.mcb.bean.ScheduledJobsBean;
import com.silverlake.mleb.mcb.constant.MiBConstant;

import comx.silverlake.mleb.mcb.entity.ScheduledJobs;


@Service
@Repository
@Transactional
public class ScheduledJobsDAO {
	
	@Autowired DozerBeanMapper mapper;
	
	@PersistenceContext(unitName="db_mleb")
	EntityManager em ;
	
	public List<ScheduledJobs> listAllScheduledJobs() {
		
		String sql = "FROM "+ScheduledJobs.class.getSimpleName() + " WHERE status=:status";
		List<ScheduledJobs> scheduledJobs = em.createQuery(sql).setParameter("status", MiBConstant.STATUS_ACTIVE).getResultList();
		
		return scheduledJobs;
		
	}

	public List<ScheduledJobsBean> listAllScheduledJobsBean() {
		
		
		List<ScheduledJobsBean> responseList = new ArrayList<ScheduledJobsBean>();
		List<ScheduledJobs> scheduledJobs = this.listAllScheduledJobs();
		
		if(scheduledJobs!=null && scheduledJobs.size()>0){
			for(ScheduledJobs entity: scheduledJobs) 
			{
				ScheduledJobsBean bean = new ScheduledJobsBean();
				mapper.map(entity, bean);
				
				responseList.add(bean);
			}
		}
		
		return responseList;
		
	}

}
