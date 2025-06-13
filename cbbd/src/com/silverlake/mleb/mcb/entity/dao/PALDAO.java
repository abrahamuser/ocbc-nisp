package com.silverlake.mleb.mcb.entity.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import comx.silverlake.mleb.mcb.entity.AOImageDocCacheData;
import comx.silverlake.mleb.mcb.entity.ETBRates;
import comx.silverlake.mleb.mcb.entity.PALCompletedApplication;

@Service
@Repository
@Transactional
public class PALDAO extends DAO {
	private static Logger log = LogManager.getLogger(PALDAO.class);

	@PersistenceContext(unitName = "db_mleb")
	EntityManager emx;

	@SuppressWarnings("unchecked")
	public List<PALCompletedApplication> getPALApplication(String userId) {
		EntityManager em = getEntityManager();
		String sql = "FROM " + PALCompletedApplication.class.getSimpleName()
				+ " WHERE user_id =:userId";
		List<PALCompletedApplication> listInfo = em.createQuery(sql).setParameter("userId", userId)
				.getResultList();
		return listInfo;
	}
	
	@SuppressWarnings("unchecked")
	public ETBRates getAllETBRates(String mscCode, String loanSizeType) {
		EntityManager em = getEntityManager();
		String sql = "FROM " + ETBRates.class.getSimpleName()
				+ " WHERE msc =:msc AND loan_size_type =:loanSizeType";
		List<ETBRates> listInfo = em.createQuery(sql).setParameter("msc", mscCode)
				.setParameter("loanSizeType", loanSizeType).getResultList();
		return listInfo.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public List<ETBRates> getETBRatesByMsc(String mscCode) {
		EntityManager em = getEntityManager();
		String sql = "FROM " + ETBRates.class.getSimpleName()
				+ " WHERE msc =:msc";
		List<ETBRates> listInfo = em.createQuery(sql).setParameter("msc", mscCode).getResultList();
		return listInfo;
	}
	
//	@SuppressWarnings("unchecked")
//	public ETBRates getStoredOCRImagesByUUID(String uuid) {
//		EntityManager em = getEntityManager();
//		String sql = "FROM " + ETBRates.class.getSimpleName() + " WHERE uuid =:uuid";
//		List<ETBRates> listInfo = em.createQuery(sql).setParameter("uuid", uuid).getResultList();
//		return listInfo;
//	}
	
	@Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return emx;
	}

}