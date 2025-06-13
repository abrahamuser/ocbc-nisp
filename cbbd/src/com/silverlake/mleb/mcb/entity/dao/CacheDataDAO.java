package com.silverlake.mleb.mcb.entity.dao;

import com.silverlake.mleb.mcb.constant.MiBConstant;

import comx.silverlake.mleb.mcb.entity.*;

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
public class CacheDataDAO extends DAO {
	private static Logger log = LogManager.getLogger(CacheDataDAO.class);

	@PersistenceContext(unitName = "db_mleb")
	EntityManager emx;

	@Override
	public EntityManager getEntityManager() {
		return emx;
	}

	@SuppressWarnings("unchecked")
	public List<PALGeneralInfoCache> getPreApprovedLoanCache(String userId) {
		EntityManager em = getEntityManager();
		String sql = "FROM " + PALGeneralInfoCache.class.getSimpleName()
				+ " WHERE STATUS =:status AND user_id =:userId";
		List<PALGeneralInfoCache> cacheList = em.createQuery(sql).setParameter("userId", userId)
				.setParameter("status", MiBConstant.CACHE_STATUS_ACTIVE).getResultList();
		return cacheList;
	}

	@SuppressWarnings("unchecked")
	public List<AOBusinessAddressCache> getPreApprovedLoanBusinessAddressCache(String userId) {
		EntityManager em = getEntityManager();
		String sql = "FROM " + AOBusinessAddressCache.class.getSimpleName()
				+ " WHERE STATUS =:status AND user_id = :userId AND module_code =:moduleCode";
		List<AOBusinessAddressCache> cacheList = em.createQuery(sql).setParameter("userId", userId)
				.setParameter("status", MiBConstant.CACHE_STATUS_ACTIVE)
				.setParameter("moduleCode", MiBConstant.MODULE_CODE_PRE_APPROVED_LOAN).getResultList();
		return cacheList;
	}

	@SuppressWarnings("unchecked")
	public List<AOBusinessInfoCache> getPreApprovedLoanBusinessInfoCache(String userId) {
		EntityManager em = getEntityManager();
		String sql = "FROM " + AOBusinessInfoCache.class.getSimpleName()
				+ " WHERE STATUS =:status AND user_id = :userId AND module_code =:moduleCode";
		List<AOBusinessInfoCache> cacheList = em.createQuery(sql).setParameter("userId", userId)
				.setParameter("status", MiBConstant.CACHE_STATUS_ACTIVE)
				.setParameter("moduleCode", MiBConstant.MODULE_CODE_PRE_APPROVED_LOAN).getResultList();
		return cacheList;
	}

	@SuppressWarnings("unchecked")
	public List<AOEmergencyContactCache> getPreApprovedLoanEmergencyContactCache(String userId) {
		EntityManager em = getEntityManager();
		String sql = "FROM " + AOEmergencyContactCache.class.getSimpleName()
				+ " WHERE STATUS =:status AND user_id = :userId AND module_code =:moduleCode";
		List<AOEmergencyContactCache> cacheList = em.createQuery(sql).setParameter("userId", userId)
				.setParameter("status", MiBConstant.CACHE_STATUS_ACTIVE)
				.setParameter("moduleCode", MiBConstant.MODULE_CODE_PRE_APPROVED_LOAN).getResultList();
		return cacheList;
	}

	@SuppressWarnings("unchecked")
	public List<AOEmergencyContactAddressCache> getPreApprovedLoanEmergencyContactAddressCache(String userId) {
		EntityManager em = getEntityManager();
		String sql = "FROM " + AOEmergencyContactAddressCache.class.getSimpleName()
				+ " WHERE STATUS =:status AND user_id = :userId AND module_code =:moduleCode";
		List<AOEmergencyContactAddressCache> cacheList = em.createQuery(sql).setParameter("userId", userId)
				.setParameter("status", MiBConstant.CACHE_STATUS_ACTIVE)
				.setParameter("moduleCode", MiBConstant.MODULE_CODE_PRE_APPROVED_LOAN).getResultList();
		return cacheList;
	}

	@SuppressWarnings("unchecked")
	public List<AOKPRAdditionalInfoCache> getPreApprovedLoanKPRAdditionalInfoCache(String userId) {
		EntityManager em = getEntityManager();
		String sql = "FROM " + AOKPRAdditionalInfoCache.class.getSimpleName()
				+ " WHERE STATUS =:status AND user_id = :userId AND module_code =:moduleCode";
		List<AOKPRAdditionalInfoCache> cacheList = em.createQuery(sql).setParameter("userId", userId)
				.setParameter("status", MiBConstant.CACHE_STATUS_ACTIVE)
				.setParameter("moduleCode", MiBConstant.MODULE_CODE_PRE_APPROVED_LOAN).getResultList();
		return cacheList;
	}

	@SuppressWarnings("unchecked")
	public List<AOMailingAddressCache> getPreApprovedLoanMailingAddressCache(String userId) {
		EntityManager em = getEntityManager();
		String sql = "FROM " + AOMailingAddressCache.class.getSimpleName()
				+ " WHERE STATUS =:status AND user_id = :userId AND module_code =:moduleCode";
		List<AOMailingAddressCache> cacheList = em.createQuery(sql).setParameter("userId", userId)
				.setParameter("status", MiBConstant.CACHE_STATUS_ACTIVE)
				.setParameter("moduleCode", MiBConstant.MODULE_CODE_PRE_APPROVED_LOAN).getResultList();
		return cacheList;
	}

	@SuppressWarnings("unchecked")
	public List<AOKTPInfoCache> getPreApprovedLoanEditedDataDukcapilCache(String userId) {
		EntityManager em = getEntityManager();
		String sql = "FROM " + AOKTPInfoCache.class.getSimpleName()
				+ " WHERE STATUS =:status AND user_id = :userId AND module_code =:moduleCode";
		List<AOKTPInfoCache> cacheList = em.createQuery(sql).setParameter("userId", userId)
				.setParameter("status", MiBConstant.CACHE_STATUS_ACTIVE)
				.setParameter("moduleCode", MiBConstant.MODULE_CODE_PRE_APPROVED_LOAN).getResultList();
		return cacheList;
	}

	public void updatePreApprovedLoanCacheStatusToDeleted(String userId, String moduleCode) {
		EntityManager em = getEntityManager();
		String sql1 = "UPDATE " + PALGeneralInfoCache.class.getSimpleName()
				+ " SET status = :status WHERE user_id =:userId";
		String sql2 = "UPDATE " + AOBusinessAddressCache.class.getSimpleName()
				+ " SET status = :status WHERE user_id =:userId AND module_code =:moduleCode";
		String sql3 = "UPDATE " + AOBusinessInfoCache.class.getSimpleName()
				+ " SET status = :status WHERE user_id =:userId AND module_code =:moduleCode";
		String sql4 = "UPDATE " + AOEmergencyContactCache.class.getSimpleName()
				+ " SET status = :status WHERE user_id =:userId AND module_code =:moduleCode";
		String sql5 = "UPDATE " + AOEmergencyContactAddressCache.class.getSimpleName()
				+ " SET status = :status WHERE user_id =:userId AND module_code =:moduleCode";
		String sql6 = "UPDATE " + AOKPRAdditionalInfoCache.class.getSimpleName()
				+ " SET status = :status WHERE user_id =:userId AND module_code =:moduleCode";
		String sql7 = "UPDATE " + AOMailingAddressCache.class.getSimpleName()
				+ " SET status = :status WHERE user_id =:userId AND module_code =:moduleCode";
		String sql8 = "UPDATE " + AOKTPInfoCache.class.getSimpleName()
				+ " SET status = :status WHERE user_id =:userId AND module_code =:moduleCode";
		
		em.createQuery(sql1).setParameter("status", MiBConstant.CACHE_STATUS_DELETED).setParameter("userId", userId)
				.executeUpdate();
		em.createQuery(sql2).setParameter("status", MiBConstant.CACHE_STATUS_DELETED).setParameter("userId", userId)
				.setParameter("moduleCode", moduleCode).executeUpdate();
		em.createQuery(sql3).setParameter("status", MiBConstant.CACHE_STATUS_DELETED).setParameter("userId", userId)
				.setParameter("moduleCode", moduleCode).executeUpdate();
		em.createQuery(sql4).setParameter("status", MiBConstant.CACHE_STATUS_DELETED).setParameter("userId", userId)
				.setParameter("moduleCode", moduleCode).executeUpdate();
		em.createQuery(sql5).setParameter("status", MiBConstant.CACHE_STATUS_DELETED).setParameter("userId", userId)
				.setParameter("moduleCode", moduleCode).executeUpdate();
		em.createQuery(sql6).setParameter("status", MiBConstant.CACHE_STATUS_DELETED).setParameter("userId", userId)
				.setParameter("moduleCode", moduleCode).executeUpdate();
		em.createQuery(sql7).setParameter("status", MiBConstant.CACHE_STATUS_DELETED).setParameter("userId", userId)
				.setParameter("moduleCode", moduleCode).executeUpdate();
		em.createQuery(sql8).setParameter("status", MiBConstant.CACHE_STATUS_DELETED).setParameter("userId", userId)
				.setParameter("moduleCode", moduleCode).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<NTICache> getNTICache(String userId) {
		EntityManager em = getEntityManager();
		String sql = "FROM " + NTICache.class.getSimpleName()
				+ " WHERE STATUS =:status AND user_id =:userId";
		return em.createQuery(sql).setParameter("userId", userId)
				.setParameter("status", MiBConstant.CACHE_STATUS_ACTIVE).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<AOKTPInfoCache> getNTIDukcapilData(String userId) {
		EntityManager em = getEntityManager();
		String sql = "FROM " + AOKTPInfoCache.class.getSimpleName()
				+ " WHERE STATUS =:status AND user_id = :userId AND module_code =:moduleCode";
		return (List<AOKTPInfoCache>) em.createQuery(sql).setParameter("userId", userId)
				.setParameter("status", MiBConstant.CACHE_STATUS_ACTIVE)
				.setParameter("moduleCode", MiBConstant.MODULE_CODE_NEW_TO_INVESTMENT).getResultList();
	}

	public void updateNTICacheStatusToDeleted(String userId, String moduleCode) {
		EntityManager em = getEntityManager();
		String sql1 = "UPDATE " + NTICache.class.getSimpleName() + " SET status = :status WHERE user_id =:userId";
		String sql2 = "UPDATE " + AOKTPInfoCache.class.getSimpleName() + " SET status = :status WHERE user_id =:userId AND module_code =:moduleCode";

		em.createQuery(sql1).setParameter("status", MiBConstant.CACHE_STATUS_DELETED).setParameter("userId", userId).executeUpdate();
		em.createQuery(sql2).setParameter("status", MiBConstant.CACHE_STATUS_DELETED).setParameter("userId", userId).setParameter("moduleCode", moduleCode).executeUpdate();
	}

}
