package com.silverlake.mleb.mcb.entity.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import comx.silverlake.mleb.mcb.entity.PostalCode;
import comx.silverlake.mleb.mcb.entity.PostalCodeWhitelist;
import comx.silverlake.mleb.mcb.entity.SelectionMapping;


@Service
@Repository
@Transactional
public class SelectionMappingDao extends DAO
{
	private static Logger log = LogManager.getLogger(SelectionMappingDao.class);


	@PersistenceContext(unitName="db_mleb")
	EntityManager emx ;
	
	public List<SelectionMapping> getListByWorkType(String selectionKey)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+SelectionMapping.class.getSimpleName()+" WHERE worktype_key = :key";
		List<SelectionMapping> listSelectionMap = em.createQuery(sql).setParameter("key", selectionKey).getResultList();
		return listSelectionMap;
	}
	
	@Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return emx;
	}
	
	
	
}