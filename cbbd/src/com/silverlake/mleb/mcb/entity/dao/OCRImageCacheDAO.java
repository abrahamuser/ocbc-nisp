package com.silverlake.mleb.mcb.entity.dao;

import com.silverlake.mleb.mcb.constant.MiBConstant;

import comx.silverlake.mleb.mcb.entity.OCRImageCache;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Repository
@Transactional
public class OCRImageCacheDAO extends DAO {
	private static Logger log = LogManager.getLogger(OCRImageCacheDAO.class);

	@PersistenceContext(unitName = "db_mleb")
	EntityManager emx;

	@SuppressWarnings("unchecked")
	public List<OCRImageCache> getImageCacheByImageType(String userId, String moduleCode, String imageType) {
		EntityManager em = getEntityManager();
		String sql = "FROM " + OCRImageCache.class.getSimpleName()
				+ " WHERE user_id =:userId AND module_code =:moduleCode AND image_type =:imageType AND status =:status";
		List<OCRImageCache> cacheList = em.createQuery(sql).setParameter("userId", userId)
				.setParameter("moduleCode", moduleCode).setParameter("imageType", imageType)
				.setParameter("status", MiBConstant.CACHE_STATUS_ACTIVE)
				.getResultList();
		return cacheList;
	}

	@SuppressWarnings("unchecked")
	public List<OCRImageCache> getImageCacheByUuid(String userId, String moduleCode, String uuid) {
		EntityManager em = getEntityManager();
		String sql = "FROM " + OCRImageCache.class.getSimpleName()
				+ " WHERE user_id =:userId AND module_code =:moduleCode AND uuid =:uuid AND status =:status";
		return (List<OCRImageCache>) em.createQuery(sql).setParameter("userId", userId)
				.setParameter("moduleCode", moduleCode).setParameter("uuid", uuid)
				.setParameter("status", MiBConstant.CACHE_STATUS_ACTIVE)
				.getResultList();
	}

//	@SuppressWarnings("unchecked")
//	public List<OCRImageCache> getActiveAndSuccessImageCacheByUuid(String userId, String moduleCode, String uuid) {
//		EntityManager em = getEntityManager();
//		String sql = "FROM " + OCRImageCache.class.getSimpleName()
//				+ " WHERE user_id =:userId AND module_code =:moduleCode AND uuid =:uuid AND (status =:status OR status =:status2)";
//		List<OCRImageCache> cacheList = em.createQuery(sql).setParameter("userId", userId)
//				.setParameter("moduleCode", moduleCode).setParameter("uuid", uuid)
//				.setParameter("status", MiBConstant.CACHE_STATUS_ACTIVE)
//				.setParameter("status2", MiBConstant.CACHE_STATUS_SUCCESS)
//				.getResultList();
//		return cacheList;
//	}

	@Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return emx;
	}

}
