package com.silverlake.mleb.mcb.entity.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silverlake.mleb.mcb.constant.MiBConstant;

import com.silverlake.mleb.mcb.entity.McbTask;
import comx.silverlake.mleb.mcb.entity.TransferLimit;

@Service
@Repository
@Transactional
public class TransferLimitDAO extends DAO {
	
	private static Logger log = LogManager.getLogger(TransferLimitDAO.class);
	
	@PersistenceContext(unitName="db_mleb")
	EntityManager emx ;
	
	public List<TransferLimit> getTransferLimitByTransferCode(String transferCode)
	{
		
		EntityManager em  = getEntityManager();
		String sql = "FROM "+TransferLimit.class.getSimpleName() + " WHERE transfer_code=:transferCode";
		List<TransferLimit> list = em.createQuery(sql).setParameter("transferCode", transferCode).getResultList();
		return list;
	}
	
	public List<TransferLimit> getAllTransferLimit()
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+TransferLimit.class.getSimpleName();
		List<TransferLimit> list = em.createQuery(sql).getResultList();
		return list;
	}
	
	public void deleteTransferLimitByTransferCode(String transferCode){
		
		EntityManager em  = getEntityManager();
		String sql = "DELETE "+TransferLimit.class.getSimpleName()+" WHERE transfer_code = :transferCode";
		
		em.createQuery(sql).setParameter("transferCode", transferCode).executeUpdate();
	}
	
	public int deleteTransferLimit(){
		EntityManager em  = getEntityManager();
		String sql = "DELETE "+TransferLimit.class.getSimpleName();
		
		return em.createQuery(sql).executeUpdate();
	}
	
	public McbTask getTransferLimitTaskStatus()
	{
		
		EntityManager em  = getEntityManager();
		String sql = "FROM "+McbTask.class.getSimpleName() + " WHERE task=:task";
		List<McbTask> list = em.createQuery(sql).setParameter("task", MiBConstant.TRANSFER_LIMIT_TASK_STATUS).getResultList();
		
		if(list != null && list.size() >0)
			return list.get(0);
		
		return null;
	}
	
	@Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return emx;
	}

}
