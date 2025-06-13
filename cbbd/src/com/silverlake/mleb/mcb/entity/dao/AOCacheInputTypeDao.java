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

import comx.silverlake.mleb.mcb.entity.AOBankInfoCache;
import comx.silverlake.mleb.mcb.entity.AOBusinessAddressCache;
import comx.silverlake.mleb.mcb.entity.AOBusinessInfoCache;
import comx.silverlake.mleb.mcb.entity.AOCCAdditionalInfoCache;
import comx.silverlake.mleb.mcb.entity.AOCCSupplementInfoCache;
import comx.silverlake.mleb.mcb.entity.AODocImageBankStatementCache;
import comx.silverlake.mleb.mcb.entity.AODocImageCache;
import comx.silverlake.mleb.mcb.entity.AODocImagePaySlipCache;
import comx.silverlake.mleb.mcb.entity.AOEmergencyContactAddressCache;
import comx.silverlake.mleb.mcb.entity.AOEmergencyContactCache;
import comx.silverlake.mleb.mcb.entity.AOFinancialInfoCache;
import comx.silverlake.mleb.mcb.entity.AOGeneralInfoCache;
import comx.silverlake.mleb.mcb.entity.AOKPRAdditionalInfoCache;
import comx.silverlake.mleb.mcb.entity.AOKTPInfoCache;
import comx.silverlake.mleb.mcb.entity.AOMailingAddressCache;
import comx.silverlake.mleb.mcb.entity.AOWorkAddressCache;

@Service
@Repository
@Transactional
public class AOCacheInputTypeDao extends DAO
{
	private static Logger log = LogManager.getLogger(AOCacheInputTypeDao.class);


	@PersistenceContext(unitName="db_mleb")
	EntityManager emx ;
	
	//search by device id
	public List<AOBankInfoCache> getBankCacheInputByDeviceId(String deviceId, String isFinal)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AOBankInfoCache.class.getSimpleName() + " WHERE device_id = :device_id and is_final = :is_final and status=:status and module_code =:moduleCode";
		List<AOBankInfoCache> listInfo = em.createQuery(sql).setParameter("device_id", deviceId).setParameter("is_final", isFinal).setParameter("status", MiBConstant.MIB_PENDING_STATUS).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).getResultList();
		return listInfo;
	}

	public List<AOBusinessAddressCache> getBusinessAddressCacheByDeviceId(String deviceId, String isFinal)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AOBusinessAddressCache.class.getSimpleName() + " WHERE device_id = :device_id and is_final = :is_final and status=:status and module_code =:moduleCode";
		List<AOBusinessAddressCache> listInfo = em.createQuery(sql).setParameter("device_id", deviceId).setParameter("is_final", isFinal).setParameter("status", MiBConstant.MIB_PENDING_STATUS).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).getResultList();
		return listInfo;
	}

	public List<AOBusinessInfoCache> getBusinessInfoCacheByDeviceId(String deviceId, String isFinal)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AOBusinessInfoCache.class.getSimpleName() + " WHERE device_id = :device_id and is_final = :is_final and status=:status and module_code =:moduleCode";
		List<AOBusinessInfoCache> listInfo = em.createQuery(sql).setParameter("device_id", deviceId).setParameter("is_final", isFinal).setParameter("status", MiBConstant.MIB_PENDING_STATUS).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).getResultList();
		return listInfo;
	}

	public List<AOCCSupplementInfoCache> getCCSupplementInfoCacheByDeviceId(String deviceId, String isFinal)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AOCCSupplementInfoCache.class.getSimpleName() + " WHERE device_id = :device_id and is_final = :is_final and status=:status and module_code =:moduleCode";
		List<AOCCSupplementInfoCache> listInfo = em.createQuery(sql).setParameter("device_id", deviceId).setParameter("is_final", isFinal).setParameter("status", MiBConstant.MIB_PENDING_STATUS).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).getResultList();
		return listInfo;
	}

	public List<AOCCAdditionalInfoCache> getCCAdditionalInfoCacheByDeviceId(String deviceId, String isFinal)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AOCCAdditionalInfoCache.class.getSimpleName() + " WHERE device_id = :device_id and is_final = :is_final and status=:status and module_code =:moduleCode";
		List<AOCCAdditionalInfoCache> listInfo = em.createQuery(sql).setParameter("device_id", deviceId).setParameter("is_final", isFinal).setParameter("status", MiBConstant.MIB_PENDING_STATUS).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).getResultList();
		return listInfo;
	}

	public List<AODocImageCache> getDocImageCacheByDeviceId(String deviceId, String isFinal)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AODocImageCache.class.getSimpleName() + " WHERE device_id = :device_id and is_final = :is_final";
		List<AODocImageCache> listInfo = em.createQuery(sql).setParameter("device_id", deviceId).setParameter("is_final", isFinal).getResultList();
		return listInfo;
	}

	public List<AOEmergencyContactAddressCache> getEmergencyContactAddressCacheByDeviceId(String deviceId, String isFinal)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AOEmergencyContactAddressCache.class.getSimpleName() + " WHERE device_id = :device_id and is_final = :is_final and status=:status and module_code =:moduleCode";
		List<AOEmergencyContactAddressCache> listInfo = em.createQuery(sql).setParameter("device_id", deviceId).setParameter("is_final", isFinal).setParameter("status", MiBConstant.MIB_PENDING_STATUS).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).getResultList();
		return listInfo;
	}

	public List<AOEmergencyContactCache> getEmergencyContactCacheByDeviceId(String deviceId, String isFinal)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AOEmergencyContactCache.class.getSimpleName() + " WHERE device_id = :device_id and is_final = :is_final and status=:status and module_code =:moduleCode";
		List<AOEmergencyContactCache> listInfo = em.createQuery(sql).setParameter("device_id", deviceId).setParameter("is_final", isFinal).setParameter("status", MiBConstant.MIB_PENDING_STATUS).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).getResultList();
		return listInfo;
	}

	public List<AOGeneralInfoCache> getAOGeneralInfoCacheByDeviceId(String deviceId, String isFinal)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AOGeneralInfoCache.class.getSimpleName() + " WHERE device_id = :device_id and is_final = :is_final and status=:status";
		List<AOGeneralInfoCache> listInfo = em.createQuery(sql).setParameter("device_id", deviceId).setParameter("is_final", isFinal).setParameter("status", MiBConstant.MIB_PENDING_STATUS).getResultList();
		return listInfo;
	}

	public List<AOKPRAdditionalInfoCache> getKPRAdditionalInfoCacheByDeviceId(String deviceId, String isFinal)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AOKPRAdditionalInfoCache.class.getSimpleName() + " WHERE device_id = :device_id and is_final = :is_final and status=:status and module_code =:moduleCode";
		List<AOKPRAdditionalInfoCache> listInfo = em.createQuery(sql).setParameter("device_id", deviceId).setParameter("is_final", isFinal).setParameter("status", MiBConstant.MIB_PENDING_STATUS).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).getResultList();
		return listInfo;
	}

	public List<AOKTPInfoCache> getKTPInfoCacheByDeviceId(String deviceId, String isFinal)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AOKTPInfoCache.class.getSimpleName() + " WHERE device_id = :device_id and is_final = :is_final and status=:status and module_code =:moduleCode";
		List<AOKTPInfoCache> listInfo = em.createQuery(sql).setParameter("device_id", deviceId).setParameter("is_final", isFinal).setParameter("status", MiBConstant.MIB_PENDING_STATUS).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).getResultList();
		return listInfo;
	}

	public List<AOMailingAddressCache> getMailingAddressCacheByDeviceId(String deviceId, String isFinal)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AOMailingAddressCache.class.getSimpleName() + " WHERE device_id = :device_id and is_final = :is_final and status=:status and module_code =:moduleCode";
		List<AOMailingAddressCache> listInfo = em.createQuery(sql).setParameter("device_id", deviceId).setParameter("is_final", isFinal).setParameter("status", MiBConstant.MIB_PENDING_STATUS).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).getResultList();
		return listInfo;
	}

	public List<AOFinancialInfoCache> getFinancialInfoByDeviceId(String deviceId, String isFinal)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AOFinancialInfoCache.class.getSimpleName() + " WHERE device_id = :device_id and is_final = :is_final and status=:status and module_code =:moduleCode";
		List<AOFinancialInfoCache> listInfo = em.createQuery(sql).setParameter("device_id", deviceId).setParameter("is_final", isFinal).setParameter("status", MiBConstant.MIB_PENDING_STATUS).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).getResultList();
		return listInfo;
	}

	public List<AOWorkAddressCache> getWorkAddressCacheByDeviceId(String deviceId, String isFinal)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AOWorkAddressCache.class.getSimpleName() + " WHERE device_id = :device_id and is_final = :is_final and status=:status and module_code =:moduleCode";
		List<AOWorkAddressCache> listInfo = em.createQuery(sql).setParameter("device_id", deviceId).setParameter("is_final", isFinal).setParameter("status", MiBConstant.MIB_PENDING_STATUS).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).getResultList();
		return listInfo;
	}

	public List<AODocImagePaySlipCache> getImagePayslipCacheByDeviceId(String deviceId, String isFinal)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AODocImagePaySlipCache.class.getSimpleName() + " WHERE deviceId = :deviceId and is_final = :is_final";
		List<AODocImagePaySlipCache> listInfo = em.createQuery(sql).setParameter("deviceId", deviceId).setParameter("is_final", isFinal).getResultList();
		return listInfo;
	}

	public List<AODocImageBankStatementCache> getImageBankStatementCacheByDeviceId(String deviceId, String isFinal)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AODocImageBankStatementCache.class.getSimpleName() + " WHERE deviceId = :deviceId and is_final = :is_final";
		List<AODocImageBankStatementCache> listInfo = em.createQuery(sql).setParameter("deviceId", deviceId).setParameter("is_final", isFinal).getResultList();
		return listInfo;
	}

	// retrieve by nik
	public List<AOBankInfoCache> getBankCacheInputByNIK(String nik, String isFinal)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AOBankInfoCache.class.getSimpleName() + " WHERE nik = :nik and is_final = :is_final and status=:status and module_code =:moduleCode";
		List<AOBankInfoCache> listInfo = em.createQuery(sql).setParameter("nik", nik).setParameter("is_final", isFinal).setParameter("status", MiBConstant.MIB_PENDING_STATUS).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).getResultList();
		return listInfo;
	}

	public List<AOBusinessAddressCache> getBusinessAddressCacheByNIK(String nik, String isFinal)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AOBusinessAddressCache.class.getSimpleName() + " WHERE nik = :nik and is_final = :is_final and status=:status and module_code =:moduleCode";
		List<AOBusinessAddressCache> listInfo = em.createQuery(sql).setParameter("nik", nik).setParameter("is_final", isFinal).setParameter("status", MiBConstant.MIB_PENDING_STATUS).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).getResultList();
		return listInfo;
	}

	public List<AOBusinessInfoCache> getBusinessInfoCacheByNIK(String nik, String isFinal)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AOBusinessInfoCache.class.getSimpleName() + " WHERE nik = :nik and is_final = :is_final and status=:status and module_code =:moduleCode";
		List<AOBusinessInfoCache> listInfo = em.createQuery(sql).setParameter("nik", nik).setParameter("is_final", isFinal).setParameter("status", MiBConstant.MIB_PENDING_STATUS).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).getResultList();
		return listInfo;
	}

	public List<AOCCSupplementInfoCache> getCCSupplementInfoCacheByNIK(String nik, String isFinal)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AOCCSupplementInfoCache.class.getSimpleName() + " WHERE nik = :nik and is_final = :is_final and status=:status and module_code =:moduleCode";
		List<AOCCSupplementInfoCache> listInfo = em.createQuery(sql).setParameter("nik", nik).setParameter("is_final", isFinal).setParameter("status", MiBConstant.MIB_PENDING_STATUS).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).getResultList();
		return listInfo;
	}

	public List<AOCCAdditionalInfoCache> getCCAdditionalInfoCacheByNIK(String nik, String isFinal)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AOCCAdditionalInfoCache.class.getSimpleName() + " WHERE nik = :nik and is_final = :is_final and status=:status and module_code =:moduleCode";
		List<AOCCAdditionalInfoCache> listInfo = em.createQuery(sql).setParameter("nik", nik).setParameter("is_final", isFinal).setParameter("status", MiBConstant.MIB_PENDING_STATUS).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).getResultList();
		return listInfo;
	}

	public List<AODocImageCache> getDocImageCacheByNIK(String nik, String isFinal)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AODocImageCache.class.getSimpleName() + " WHERE nik = :nik and is_final = :is_final";
		List<AODocImageCache> listInfo = em.createQuery(sql).setParameter("nik", nik).setParameter("is_final", isFinal).getResultList();
		return listInfo;
	}

	public List<AOEmergencyContactAddressCache> getEmergencyContactAddressCacheByNIK(String nik, String isFinal)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AOEmergencyContactAddressCache.class.getSimpleName() + " WHERE nik = :nik and is_final = :is_final and status=:status and module_code =:moduleCode";
		List<AOEmergencyContactAddressCache> listInfo = em.createQuery(sql).setParameter("nik", nik).setParameter("is_final", isFinal).setParameter("status", MiBConstant.MIB_PENDING_STATUS).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).getResultList();
		return listInfo;
	}

	public List<AOEmergencyContactCache> getEmergencyContactCacheByNIK(String nik, String isFinal)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AOEmergencyContactCache.class.getSimpleName() + " WHERE nik = :nik and is_final = :is_final and status=:status and module_code =:moduleCode";
		List<AOEmergencyContactCache> listInfo = em.createQuery(sql).setParameter("nik", nik).setParameter("is_final", isFinal).setParameter("status", MiBConstant.MIB_PENDING_STATUS).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).getResultList();
		return listInfo;
	}

	public List<AOGeneralInfoCache> getAOGeneralInfoCacheByNIK(String nik, String isFinal)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AOGeneralInfoCache.class.getSimpleName() + " WHERE nik = :nik and is_final = :is_final and status=:status";
		List<AOGeneralInfoCache> listInfo = em.createQuery(sql).setParameter("nik", nik).setParameter("is_final", isFinal).setParameter("status", MiBConstant.MIB_PENDING_STATUS).getResultList();
		return listInfo;
	}

	public List<AOKPRAdditionalInfoCache> getKPRAdditionalInfoCacheByNIK(String nik, String isFinal)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AOKPRAdditionalInfoCache.class.getSimpleName() + " WHERE nik = :nik and is_final = :is_final and status=:status and module_code =:moduleCode";
		List<AOKPRAdditionalInfoCache> listInfo = em.createQuery(sql).setParameter("nik", nik).setParameter("is_final", isFinal).setParameter("status", MiBConstant.MIB_PENDING_STATUS).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).getResultList();
		return listInfo;
	}

	public List<AOKTPInfoCache> getKTPInfoCacheByNIK(String nik, String isFinal)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AOKTPInfoCache.class.getSimpleName() + " WHERE nik = :nik and is_final = :is_final and status=:status and module_code =:moduleCode";
		List<AOKTPInfoCache> listInfo = em.createQuery(sql).setParameter("nik", nik).setParameter("is_final", isFinal).setParameter("status", MiBConstant.MIB_PENDING_STATUS).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).getResultList();
		return listInfo;
	}

	public List<AOMailingAddressCache> getMailingAddressCacheByNIK(String nik, String isFinal)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AOMailingAddressCache.class.getSimpleName() + " WHERE nik = :nik and is_final = :is_final and status=:status and module_code =:moduleCode";
		List<AOMailingAddressCache> listInfo = em.createQuery(sql).setParameter("nik", nik).setParameter("is_final", isFinal).setParameter("status", MiBConstant.MIB_PENDING_STATUS).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).getResultList();
		return listInfo;
	}

	public List<AOFinancialInfoCache> getFinancialInfoByNIK(String nik, String isFinal)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AOFinancialInfoCache.class.getSimpleName() + " WHERE nik = :nik and is_final = :is_final and status=:status and module_code =:moduleCode";
		List<AOFinancialInfoCache> listInfo = em.createQuery(sql).setParameter("nik", nik).setParameter("is_final", isFinal).setParameter("status", MiBConstant.MIB_PENDING_STATUS).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).getResultList();
		return listInfo;
	}

	public List<AOWorkAddressCache> getWorkAddressCacheByNIK(String nik, String isFinal)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AOWorkAddressCache.class.getSimpleName() + " WHERE nik = :nik and is_final = :is_final and status=:status and module_code =:moduleCode";
		List<AOWorkAddressCache> listInfo = em.createQuery(sql).setParameter("nik", nik).setParameter("is_final", isFinal).setParameter("status", MiBConstant.MIB_PENDING_STATUS).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).getResultList();
		return listInfo;
	}

	public List<AODocImagePaySlipCache> getImagePayslipCacheByNIK(String nik, String isFinal)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AODocImagePaySlipCache.class.getSimpleName() + " WHERE nik = :nik and is_final = :is_final";
		List<AODocImagePaySlipCache> listInfo = em.createQuery(sql).setParameter("nik", nik).setParameter("is_final", isFinal).getResultList();
		return listInfo;
	}

	public List<AODocImageBankStatementCache> getImageBankStatementCacheByNIK(String nik, String isFinal)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AODocImageBankStatementCache.class.getSimpleName() + " WHERE nik = :nik and is_final = :is_final";
		List<AODocImageBankStatementCache> listInfo = em.createQuery(sql).setParameter("nik", nik).setParameter("is_final", isFinal).getResultList();
		return listInfo;
	}

	//update
	public void updateDeletedStatusByDeviceId(String deviceId, String isFinal)
	{
		EntityManager em  = getEntityManager();
		String sql = "UPDATE "+AOGeneralInfoCache.class.getSimpleName()+" SET status = :status WHERE device_id = :device_id and is_final = :is_final ";
		String sql2 = "UPDATE "+AOBankInfoCache.class.getSimpleName()+" SET status = :status WHERE device_id = :device_id and is_final = :is_final and module_code =:moduleCode";
		String sql3 = "UPDATE "+AOBusinessAddressCache.class.getSimpleName()+" SET status = :status WHERE device_id = :device_id and is_final = :is_final and module_code =:moduleCode";
		String sql4 = "UPDATE "+AOBusinessInfoCache.class.getSimpleName()+" SET status = :status WHERE device_id = :device_id and is_final = :is_final and module_code =:moduleCode";
		String sql5 = "UPDATE "+AOCCSupplementInfoCache.class.getSimpleName()+" SET status = :status WHERE device_id = :device_id and is_final = :is_final and module_code =:moduleCode";
		String sql6 = "UPDATE "+AOCCAdditionalInfoCache.class.getSimpleName()+" SET status = :status WHERE device_id = :device_id and is_final = :is_final and module_code =:moduleCode";
		String sql7 = "UPDATE "+AOEmergencyContactAddressCache.class.getSimpleName()+" SET status = :status WHERE device_id = :device_id and is_final = :is_final and module_code =:moduleCode";
		String sql8 = "UPDATE "+AOEmergencyContactCache.class.getSimpleName()+" SET status = :status WHERE device_id = :device_id and is_final = :is_final and module_code =:moduleCode";
		String sql9 = "UPDATE "+AOKPRAdditionalInfoCache.class.getSimpleName()+" SET status = :status WHERE device_id = :device_id and is_final = :is_final and module_code =:moduleCode";
		String sql10 = "UPDATE "+AOKTPInfoCache.class.getSimpleName()+" SET status = :status WHERE device_id = :device_id and is_final = :is_final and module_code =:moduleCode";
		String sql11 = "UPDATE "+AOMailingAddressCache.class.getSimpleName()+" SET status = :status WHERE device_id = :device_id and is_final = :is_final and module_code =:moduleCode";
		String sql12 = "UPDATE "+AOWorkAddressCache.class.getSimpleName()+" SET status = :status WHERE device_id = :device_id and is_final = :is_final and module_code =:moduleCode";
		String sql13 = "UPDATE "+AOFinancialInfoCache.class.getSimpleName()+" SET status = :status WHERE device_id = :device_id and is_final = :is_final and module_code =:moduleCode";

		em.createQuery(sql).setParameter("status", MiBConstant.MIB_DELETED_STATUS).setParameter("device_id", deviceId).setParameter("is_final", isFinal).executeUpdate();
		em.createQuery(sql2).setParameter("status", MiBConstant.MIB_DELETED_STATUS).setParameter("device_id", deviceId).setParameter("is_final", isFinal).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
		em.createQuery(sql3).setParameter("status", MiBConstant.MIB_DELETED_STATUS).setParameter("device_id", deviceId).setParameter("is_final", isFinal).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
		em.createQuery(sql4).setParameter("status", MiBConstant.MIB_DELETED_STATUS).setParameter("device_id", deviceId).setParameter("is_final", isFinal).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
		em.createQuery(sql5).setParameter("status", MiBConstant.MIB_DELETED_STATUS).setParameter("device_id", deviceId).setParameter("is_final", isFinal).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
		em.createQuery(sql6).setParameter("status", MiBConstant.MIB_DELETED_STATUS).setParameter("device_id", deviceId).setParameter("is_final", isFinal).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
		em.createQuery(sql7).setParameter("status", MiBConstant.MIB_DELETED_STATUS).setParameter("device_id", deviceId).setParameter("is_final", isFinal).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
		em.createQuery(sql8).setParameter("status", MiBConstant.MIB_DELETED_STATUS).setParameter("device_id", deviceId).setParameter("is_final", isFinal).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
		em.createQuery(sql9).setParameter("status", MiBConstant.MIB_DELETED_STATUS).setParameter("device_id", deviceId).setParameter("is_final", isFinal).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
		em.createQuery(sql10).setParameter("status", MiBConstant.MIB_DELETED_STATUS).setParameter("device_id", deviceId).setParameter("is_final", isFinal).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
		em.createQuery(sql11).setParameter("status", MiBConstant.MIB_DELETED_STATUS).setParameter("device_id", deviceId).setParameter("is_final", isFinal).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
		em.createQuery(sql12).setParameter("status", MiBConstant.MIB_DELETED_STATUS).setParameter("device_id", deviceId).setParameter("is_final", isFinal).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
		em.createQuery(sql13).setParameter("status", MiBConstant.MIB_DELETED_STATUS).setParameter("device_id", deviceId).setParameter("is_final", isFinal).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();

	}

	public void updateDeletedStatusByNIK(String nik, String isFinal)
	{
		EntityManager em  = getEntityManager();
		String sql = "UPDATE "+AOGeneralInfoCache.class.getSimpleName()+" SET status = :status WHERE nik = :nik and is_final = :is_final ";
		String sql2 = "UPDATE "+AOBankInfoCache.class.getSimpleName()+" SET status = :status WHERE nik = :nik and is_final = :is_final and module_code =:moduleCode";
		String sql3 = "UPDATE "+AOBusinessAddressCache.class.getSimpleName()+" SET status = :status WHERE nik = :nik and is_final = :is_final and module_code =:moduleCode";
		String sql4 = "UPDATE "+AOBusinessInfoCache.class.getSimpleName()+" SET status = :status WHERE nik = :nik and is_final = :is_final and module_code =:moduleCode";
		String sql5 = "UPDATE "+AOCCSupplementInfoCache.class.getSimpleName()+" SET status = :status WHERE nik = :nik and is_final = :is_final and module_code =:moduleCode";
		String sql6 = "UPDATE "+AOCCAdditionalInfoCache.class.getSimpleName()+" SET status = :status WHERE nik = :nik and is_final = :is_final and module_code =:moduleCode";
		String sql7 = "UPDATE "+AOEmergencyContactAddressCache.class.getSimpleName()+" SET status = :status WHERE nik = :nik and is_final = :is_final and module_code =:moduleCode";
		String sql8 = "UPDATE "+AOEmergencyContactCache.class.getSimpleName()+" SET status = :status WHERE nik = :nik and is_final = :is_final and module_code =:moduleCode";
		String sql9 = "UPDATE "+AOKPRAdditionalInfoCache.class.getSimpleName()+" SET status = :status WHERE nik = :nik and is_final = :is_final and module_code =:moduleCode";
		String sql10 = "UPDATE "+AOKTPInfoCache.class.getSimpleName()+" SET status = :status WHERE nik = :nik and is_final = :is_final and module_code =:moduleCode";
		String sql11 = "UPDATE "+AOMailingAddressCache.class.getSimpleName()+" SET status = :status WHERE nik = :nik and is_final = :is_final and module_code =:moduleCode";
		String sql12 = "UPDATE "+AOWorkAddressCache.class.getSimpleName()+" SET status = :status WHERE nik = :nik and is_final = :is_final and module_code =:moduleCode";
		String sql13 = "UPDATE "+AOFinancialInfoCache.class.getSimpleName()+" SET status = :status WHERE nik = :nik and is_final = :is_final and module_code =:moduleCode";

		em.createQuery(sql).setParameter("status", MiBConstant.MIB_DELETED_STATUS).setParameter("nik", nik).setParameter("is_final", isFinal).executeUpdate();
		em.createQuery(sql2).setParameter("status", MiBConstant.MIB_DELETED_STATUS).setParameter("nik", nik).setParameter("is_final", isFinal).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
		em.createQuery(sql3).setParameter("status", MiBConstant.MIB_DELETED_STATUS).setParameter("nik", nik).setParameter("is_final", isFinal).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
		em.createQuery(sql4).setParameter("status", MiBConstant.MIB_DELETED_STATUS).setParameter("nik", nik).setParameter("is_final", isFinal).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
		em.createQuery(sql5).setParameter("status", MiBConstant.MIB_DELETED_STATUS).setParameter("nik", nik).setParameter("is_final", isFinal).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
		em.createQuery(sql6).setParameter("status", MiBConstant.MIB_DELETED_STATUS).setParameter("nik", nik).setParameter("is_final", isFinal).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
		em.createQuery(sql7).setParameter("status", MiBConstant.MIB_DELETED_STATUS).setParameter("nik", nik).setParameter("is_final", isFinal).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
		em.createQuery(sql8).setParameter("status", MiBConstant.MIB_DELETED_STATUS).setParameter("nik", nik).setParameter("is_final", isFinal).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
		em.createQuery(sql9).setParameter("status", MiBConstant.MIB_DELETED_STATUS).setParameter("nik", nik).setParameter("is_final", isFinal).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
		em.createQuery(sql10).setParameter("status", MiBConstant.MIB_DELETED_STATUS).setParameter("nik", nik).setParameter("is_final", isFinal).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
		em.createQuery(sql11).setParameter("status", MiBConstant.MIB_DELETED_STATUS).setParameter("nik", nik).setParameter("is_final", isFinal).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
		em.createQuery(sql12).setParameter("status", MiBConstant.MIB_DELETED_STATUS).setParameter("nik", nik).setParameter("is_final", isFinal).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
		em.createQuery(sql13).setParameter("status", MiBConstant.MIB_DELETED_STATUS).setParameter("nik", nik).setParameter("is_final", isFinal).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();

	}

	
	
	@Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return emx;
	}



}