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

import comx.silverlake.mleb.mcb.entity.TakaCacheData;

@Service
@Repository
@Transactional
public class TakaCacheDataDao extends DAO
{
	private static Logger log = LogManager.getLogger(TakaCacheDataDao.class);


	@PersistenceContext(unitName="db_mleb")
	EntityManager emx ;
	
	//search by userId
	@SuppressWarnings("unchecked")
	public TakaCacheData getCachedTakaData(String userId)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+TakaCacheData.class.getSimpleName() + " WHERE userId=:userId and status=:status";
		List<TakaCacheData> listInfo = em.createQuery(sql)
				                         .setParameter("userId", userId)
				                         .setParameter("status", MiBConstant.PRO_ACTIVE)
				                         .getResultList();
		if(listInfo!=null && !listInfo.isEmpty())
			return listInfo.get(0);
		else
			return null;
	}
	
	public List<TakaCacheData> getAllCachedTakaData()
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+TakaCacheData.class.getSimpleName()+ " WHERE status=:status";
		List<TakaCacheData> listInfo = em.createQuery(sql)
										.setParameter("status", MiBConstant.PRO_ACTIVE)
										.getResultList();
		if(listInfo!=null && !listInfo.isEmpty())
			return listInfo;
		else
			return null;
	}
	
	public int deleteCachedTakaData(String userId)
	{
		EntityManager em  = getEntityManager();
		String sql = "DELETE "+TakaCacheData.class.getSimpleName()+" WHERE userId = :userId";
		return em.createQuery(sql).setParameter("userId", userId).executeUpdate();
		
	}
	
	@Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return emx;
	}

}