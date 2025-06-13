package com.silverlake.mleb.mcb.entity.dao;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Repository
@Transactional
public class MLEBMIBDAO extends DAO
{


	@PersistenceContext(unitName="db_mleb")
	EntityManager emx ;
	
	
	
	@Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return emx;
	}
	
	
}