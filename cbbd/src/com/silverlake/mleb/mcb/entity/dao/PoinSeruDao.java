package com.silverlake.mleb.mcb.entity.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import comx.silverlake.mleb.mcb.entity.PoinSeruInfo;

@Service
@Repository
@Transactional
public class PoinSeruDao extends DAO{
	private static Logger log = LogManager.getLogger(PoinSeruDao.class);

	@PersistenceContext(unitName="db_mleb")
	EntityManager emx ;
	
	public List<PoinSeruInfo> getByCifNo(String cifNo)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+PoinSeruInfo.class.getSimpleName() + " WHERE cif_no = :cifNo";
		List<PoinSeruInfo> list = em.createQuery(sql).setParameter("cifNo", cifNo).getResultList();
		return list;
	}
	
	public List<PoinSeruInfo> getByUserId(String userId)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+PoinSeruInfo.class.getSimpleName() + " WHERE user_id = :userId";
		List<PoinSeruInfo> list = em.createQuery(sql).setParameter("userId", userId).getResultList();
		return list;
	}

	@Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return emx;
	}
}