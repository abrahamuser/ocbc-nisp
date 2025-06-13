package com.silverlake.mleb.mcb.entity.dao;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.entity.MaintenanceNotification;

@Service
@Repository
@Transactional
public class MaintenanceNotificationDAO {

	@PersistenceContext(unitName="db_mleb")
	EntityManager em ;

	public Object findByID(Class objClass, Object obj) {
		Object rsObj = em.find(objClass, obj);
		return rsObj;
	}
	
	public List<MaintenanceNotification> getMaintenanceNotification(Date date){
		String sql = "FROM " + MaintenanceNotification.class.getSimpleName()
				+ " WHERE effective_start_dt <= :time AND effective_end_dt >= :time AND status= :status"
				+ "  ORDER BY maintenance_id DESC";
		List<MaintenanceNotification> resultlist = em.createQuery(sql).setParameter("time", date).setParameter("status", MiBConstant.MIB_MAINTENANCE_STATUS_ACTIVE).getResultList();
		if(resultlist != null){
			return resultlist;
		}else{
			return Collections.<MaintenanceNotification>emptyList();
		}
	}
	
	public List<MaintenanceNotification> getPreMaintenanceNotification(Date preNotiDate, Date currentDate){
		String sql = "FROM " + MaintenanceNotification.class.getSimpleName()
				+ " WHERE effective_start_dt <= :preNotiDate AND effective_start_dt >= :currentDate AND status= :status"
				+ "  ORDER BY maintenance_id DESC";
		List<MaintenanceNotification> resultlist = em.createQuery(sql)
				.setParameter("preNotiDate", preNotiDate)
				.setParameter("currentDate", currentDate)
				.setParameter("status", MiBConstant.MIB_MAINTENANCE_STATUS_ACTIVE).getResultList();
		if(resultlist != null){
			return resultlist;
		}else{
			return Collections.<MaintenanceNotification>emptyList();
		}
	}
}
