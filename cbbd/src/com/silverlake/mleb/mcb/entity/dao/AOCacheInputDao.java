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
import comx.silverlake.mleb.mcb.entity.AOImageDocCacheData;
import comx.silverlake.mleb.mcb.entity.AOKPRAdditionalInfoCache;
import comx.silverlake.mleb.mcb.entity.AOKTPInfoCache;
import comx.silverlake.mleb.mcb.entity.AOMailingAddressCache;
import comx.silverlake.mleb.mcb.entity.AOParamCache;
import comx.silverlake.mleb.mcb.entity.AOWorkAddressCache;

@Service
@Repository
@Transactional
public class AOCacheInputDao extends DAO
{
	private static Logger log = LogManager.getLogger(AOCacheInputDao.class);


	@PersistenceContext(unitName="db_mleb")
	EntityManager emx ;

	public List<AOGeneralInfoCache> getAOGeneralInfoCache()
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AOGeneralInfoCache.class.getSimpleName();
		List<AOGeneralInfoCache> listInfo = em.createQuery(sql).getResultList();
		return listInfo;
	}

	public List<AOGeneralInfoCache> getAOGeneralInfoCacheByPendingStatus()
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AOGeneralInfoCache.class.getSimpleName() + " WHERE status =:status";
		List<AOGeneralInfoCache> listInfo = em.createQuery(sql).setParameter("status", MiBConstant.MIB_PENDING_STATUS).getResultList();
		return listInfo;
	}
	
	//search by device id
	public List<AODocImageCache> getDocImageCacheByDeviceId(String deviceId)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AODocImageCache.class.getSimpleName() + " WHERE device_id = :device_id";
		List<AODocImageCache> listInfo = em.createQuery(sql).setParameter("device_id", deviceId).getResultList();
		return listInfo;
	}

	public List<AOGeneralInfoCache> getAOGeneralInfoCacheByDeviceId(String deviceId)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AOGeneralInfoCache.class.getSimpleName() + " WHERE device_id = :device_id";
		List<AOGeneralInfoCache> listInfo = em.createQuery(sql).setParameter("device_id", deviceId).getResultList();
		return listInfo;
	}

	public List<AODocImagePaySlipCache> getImagePayslipCacheByDeviceId(String deviceId)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AODocImagePaySlipCache.class.getSimpleName() + " WHERE deviceId = :deviceId";
		List<AODocImagePaySlipCache> listInfo = em.createQuery(sql).setParameter("deviceId", deviceId).getResultList();
		return listInfo;
	}

	public List<AODocImageBankStatementCache> getImageBankStatementCacheByDeviceId(String deviceId)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AODocImageBankStatementCache.class.getSimpleName() + " WHERE deviceId = :deviceId";
		List<AODocImageBankStatementCache> listInfo = em.createQuery(sql).setParameter("deviceId", deviceId).getResultList();
		return listInfo;
	}
	
	@SuppressWarnings("unchecked")
	public List<AOParamCache> getAOParamCache(){
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AOParamCache.class.getSimpleName();
		List<AOParamCache> listInfo = em.createQuery(sql).getResultList();
		return listInfo;
	}
	
	@SuppressWarnings("unchecked")
	public List<AOParamCache> getAOParamCache(String paramType){
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AOParamCache.class.getSimpleName()+ " WHERE paramType = :paramType";
		List<AOParamCache> listInfo = em.createQuery(sql).setParameter("paramType", paramType).getResultList();
		return listInfo;
	}
	
	@SuppressWarnings("unchecked")
	public List<AOParamCache> getAOParamCacheLike(String paramType){
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AOParamCache.class.getSimpleName()+ " WHERE paramType LIKE :paramType";
		List<AOParamCache> listInfo = em.createQuery(sql).setParameter("paramType", paramType+"%").getResultList();
		return listInfo;
	}
	
	@SuppressWarnings("unchecked")
	public List<AOParamCache> getAOParamCacheSortKey(String paramType, boolean isDesc){
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AOParamCache.class.getSimpleName()+ " WHERE paramType = :paramType ORDER BY paramKey "+((isDesc)?"DESC":"");
		List<AOParamCache> listInfo = em.createQuery(sql).setParameter("paramType", paramType).getResultList();
		return listInfo;
	}
	
	@SuppressWarnings("unchecked")
	public String getParamValue(String paramType, String paramKey, String localeCode){
		String paramValue = "";
		
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AOParamCache.class.getSimpleName()+ " WHERE paramType = :paramType and paramKey = :paramKey";
		List<AOParamCache> listInfo = em.createQuery(sql).setParameter("paramType", paramType)
														 .setParameter("paramKey", paramKey)
														 .getResultList();
		if(listInfo!=null && !listInfo.isEmpty())
		{
			if(localeCode!=null && localeCode.equalsIgnoreCase(MiBConstant.LOCALE_EN))
			{
				paramValue = listInfo.get(0).getParamValueEN();
			}
			else if(localeCode!=null && (localeCode.equalsIgnoreCase(MiBConstant.LOCALE_CN)))
			{
				paramValue = listInfo.get(0).getParamValueCN();
			}
			else
			{
				paramValue = listInfo.get(0).getParamValueID();
			}
		}
		return paramValue;
	}
	
	// delete by deviceId

//	public void removeAllCacheByDeviceId(String deviceId)
//	{
//		EntityManager em  = getEntityManager();
//		String sql1 = "DELETE "+AOBankInfoCache.class.getSimpleName()+" WHERE device_id = :device_id AND module_code = :moduleCode";
//		String sql2 = "DELETE "+AOBusinessAddressCache.class.getSimpleName()+" WHERE device_id = :device_id AND module_code = :moduleCode";
//		String sql3 = "DELETE "+AOBusinessInfoCache.class.getSimpleName()+" WHERE device_id = :device_id AND module_code = :moduleCode";
//		String sql4 = "DELETE "+AOCCSupplementInfoCache.class.getSimpleName()+" WHERE device_id = :device_id AND module_code = :moduleCode";
//		String sql5 = "DELETE "+AOCCAdditionalInfoCache.class.getSimpleName()+" WHERE device_id = :device_id AND module_code = :moduleCode";
//		String sql6 = "DELETE "+AOEmergencyContactAddressCache.class.getSimpleName()+" WHERE device_id = :device_id AND module_code = :moduleCode";
//		String sql7 = "DELETE "+AOEmergencyContactCache.class.getSimpleName()+" WHERE device_id = :device_id AND module_code = :moduleCode";
//		String sql8 = "DELETE "+AOKPRAdditionalInfoCache.class.getSimpleName()+" WHERE device_id = :device_id AND module_code = :moduleCode";
//		String sql9 = "DELETE "+AOKTPInfoCache.class.getSimpleName()+" WHERE device_id = :device_id AND module_code = :moduleCode";
//		String sql10 = "DELETE "+AOMailingAddressCache.class.getSimpleName()+" WHERE device_id = :device_id AND module_code = :moduleCode";
//		String sql11 = "DELETE "+AOWorkAddressCache.class.getSimpleName()+" WHERE device_id = :device_id AND module_code = :moduleCode";
//		String sql12 = "DELETE "+AOFinancialInfoCache.class.getSimpleName()+" WHERE device_id = :device_id AND module_code = :moduleCode";
//		String sql13 = "DELETE "+AODocImageBankStatementCache.class.getSimpleName()+" WHERE device_id = :device_id";
//		String sql14 = "DELETE "+AODocImagePaySlipCache.class.getSimpleName()+" WHERE device_id = :device_id";
//		String sql15 = "DELETE "+AODocImageCache.class.getSimpleName()+" WHERE device_id = :device_id";
//		String sql16 = "DELETE "+AOGeneralInfoCache.class.getSimpleName()+" WHERE device_id = :device_id";
//		
//		em.createQuery(sql1).setParameter("device_id", deviceId).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
//		em.createQuery(sql2).setParameter("device_id", deviceId).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
//		em.createQuery(sql3).setParameter("device_id", deviceId).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
//		em.createQuery(sql4).setParameter("device_id", deviceId).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
//		em.createQuery(sql5).setParameter("device_id", deviceId).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
//		em.createQuery(sql6).setParameter("device_id", deviceId).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
//		em.createQuery(sql7).setParameter("device_id", deviceId).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
//		em.createQuery(sql8).setParameter("device_id", deviceId).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
//		em.createQuery(sql9).setParameter("device_id", deviceId).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
//		em.createQuery(sql10).setParameter("device_id", deviceId).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
//		em.createQuery(sql11).setParameter("device_id", deviceId).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
//		em.createQuery(sql12).setParameter("device_id", deviceId).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
//		em.createQuery(sql13).setParameter("device_id", deviceId).executeUpdate();
//		em.createQuery(sql14).setParameter("device_id", deviceId).executeUpdate();
//		em.createQuery(sql15).setParameter("device_id", deviceId).executeUpdate();
//		em.createQuery(sql16).setParameter("device_id", deviceId).executeUpdate();
//
//	}

	public void removeImageCacheByDeviceId(String deviceId){
		EntityManager em  = getEntityManager();
		String sql1 = "DELETE "+AODocImageCache.class.getSimpleName()+" WHERE device_id = :device_id";
		String sql2 = "DELETE "+AODocImageBankStatementCache.class.getSimpleName()+" WHERE device_id = :device_id";
		String sql3 = "DELETE "+AODocImagePaySlipCache.class.getSimpleName()+" WHERE device_id = :device_id";
		
		em.createQuery(sql1).setParameter("device_id", deviceId).executeUpdate();
		em.createQuery(sql2).setParameter("device_id", deviceId).executeUpdate();
		em.createQuery(sql3).setParameter("device_id", deviceId).executeUpdate();
	}
	
//	public void removeAllCacheByNIK(String nik)
//	{
//		EntityManager em  = getEntityManager();
//		String sql1 = "DELETE "+AOBankInfoCache.class.getSimpleName()+" WHERE nik = :nik AND module_code = :moduleCode";
//		String sql2 = "DELETE "+AOBusinessAddressCache.class.getSimpleName()+" WHERE nik = :nik AND module_code = :moduleCode";
//		String sql3 = "DELETE "+AOBusinessInfoCache.class.getSimpleName()+" WHERE nik = :nik AND module_code = :moduleCode";
//		String sql4 = "DELETE "+AOCCSupplementInfoCache.class.getSimpleName()+" WHERE nik = :nik AND module_code = :moduleCode";
//		String sql5 = "DELETE "+AOCCAdditionalInfoCache.class.getSimpleName()+" WHERE nik = :nik AND module_code = :moduleCode";
//		String sql6 = "DELETE "+AOEmergencyContactAddressCache.class.getSimpleName()+" WHERE nik = :nik AND module_code = :moduleCode";
//		String sql7 = "DELETE "+AOEmergencyContactCache.class.getSimpleName()+" WHERE nik = :nik AND module_code = :moduleCode";
//		String sql8 = "DELETE "+AOKPRAdditionalInfoCache.class.getSimpleName()+" WHERE nik = :nik AND module_code = :moduleCode";
//		String sql9 = "DELETE "+AOKTPInfoCache.class.getSimpleName()+" WHERE nik = :nik AND module_code = :moduleCode";
//		String sql10 = "DELETE "+AOMailingAddressCache.class.getSimpleName()+" WHERE nik = :nik AND module_code = :moduleCode";
//		String sql11 = "DELETE "+AOWorkAddressCache.class.getSimpleName()+" WHERE nik = :nik AND module_code = :moduleCode";
//		String sql12 = "DELETE "+AOFinancialInfoCache.class.getSimpleName()+" WHERE nik = :nik AND module_code = :moduleCode";
//		String sql13 = "DELETE "+AOGeneralInfoCache.class.getSimpleName()+" WHERE nik = :nik";
//		String sql14 = "DELETE "+AODocImageBankStatementCache.class.getSimpleName()+" WHERE nik = :nik";
//		String sql15 = "DELETE "+AODocImagePaySlipCache.class.getSimpleName()+" WHERE nik = :nik";
//		String sql16 = "DELETE "+AODocImageCache.class.getSimpleName()+" WHERE nik = :nik";
//		
//		em.createQuery(sql1).setParameter("nik", nik).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
//		em.createQuery(sql2).setParameter("nik", nik).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
//		em.createQuery(sql3).setParameter("nik", nik).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
//		em.createQuery(sql4).setParameter("nik", nik).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
//		em.createQuery(sql5).setParameter("nik", nik).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
//		em.createQuery(sql6).setParameter("nik", nik).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
//		em.createQuery(sql7).setParameter("nik", nik).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
//		em.createQuery(sql8).setParameter("nik", nik).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
//		em.createQuery(sql9).setParameter("nik", nik).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
//		em.createQuery(sql10).setParameter("nik", nik).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
//		em.createQuery(sql11).setParameter("nik", nik).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
//		em.createQuery(sql12).setParameter("nik", nik).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
//		em.createQuery(sql13).setParameter("nik", nik).executeUpdate();
//		em.createQuery(sql14).setParameter("nik", nik).executeUpdate();
//		em.createQuery(sql15).setParameter("nik", nik).executeUpdate();
//		em.createQuery(sql16).setParameter("nik", nik).executeUpdate();
//
//	}
	public void removeImageCacheByNIK(String nik){
		EntityManager em  = getEntityManager();
		String sql1 = "DELETE "+AODocImageCache.class.getSimpleName()+" WHERE nik = :nik";
		String sql12 = "DELETE "+AODocImageBankStatementCache.class.getSimpleName()+" WHERE nik = :nik";
		String sql13 = "DELETE "+AODocImagePaySlipCache.class.getSimpleName()+" WHERE nik = :nik";
		
		em.createQuery(sql1).setParameter("nik", nik).executeUpdate();
		em.createQuery(sql12).setParameter("nik", nik).executeUpdate();
		em.createQuery(sql13).setParameter("nik", nik).executeUpdate();
	}
	
	
//	public int removeBankCacheInputByDeviceId(String deviceId)
//	{
//		EntityManager em  = getEntityManager();
//		String sql = "DELETE "+AOBankInfoCache.class.getSimpleName()+" WHERE device_id = :device_id";
//		return em.createQuery(sql).setParameter("device_id", deviceId).executeUpdate();
//	}

//	public int removeBusinessAddressCacheByDeviceId(String deviceId)
//	{
//		EntityManager em  = getEntityManager();
//		String sql = "DELETE "+AOBusinessAddressCache.class.getSimpleName()+" WHERE device_id = :device_id";
//		return em.createQuery(sql).setParameter("device_id", deviceId).executeUpdate();
//	}

//	public int removeBusinessInfoCacheByDeviceId(String deviceId)
//	{
//		EntityManager em  = getEntityManager();
//		String sql = "DELETE "+AOBusinessInfoCache.class.getSimpleName()+" WHERE device_id = :device_id";
//		return em.createQuery(sql).setParameter("device_id", deviceId).executeUpdate();
//	}

//	public int removeCCSupplementInfoCacheByDeviceId(String deviceId)
//	{
//		EntityManager em  = getEntityManager();
//		String sql = "DELETE "+AOCCSupplementInfoCache.class.getSimpleName()+" WHERE device_id = :device_id";
//		return em.createQuery(sql).setParameter("device_id", deviceId).executeUpdate();
//	}

//	public int removeCCAdditionalInfoCacheByDeviceId(String deviceId)
//	{
//		EntityManager em  = getEntityManager();
//		String sql = "DELETE "+AOCCAdditionalInfoCache.class.getSimpleName()+" WHERE device_id = :device_id";
//		return em.createQuery(sql).setParameter("device_id", deviceId).executeUpdate();
//	}

//	public int removeDocImageCacheByDeviceId(String deviceId)
//	{
//		EntityManager em  = getEntityManager();
//		String sql = "DELETE "+AODocImageCache.class.getSimpleName()+" WHERE device_id = :device_id";
//		return em.createQuery(sql).setParameter("device_id", deviceId).executeUpdate();
//	}

//	public int removeEmergencyContactAddressCacheByDeviceId(String deviceId)
//	{
//		EntityManager em  = getEntityManager();
//		String sql = "DELETE "+AOEmergencyContactAddressCache.class.getSimpleName()+" WHERE device_id = :device_id";
//		return em.createQuery(sql).setParameter("device_id", deviceId).executeUpdate();
//	}

//	public int removeEmergencyContactCacheByDeviceId(String deviceId)
//	{
//		EntityManager em  = getEntityManager();
//		String sql = "DELETE "+AOEmergencyContactCache.class.getSimpleName()+" WHERE device_id = :device_id";
//		return em.createQuery(sql).setParameter("device_id", deviceId).executeUpdate();
//	}

//	public int removeAOGeneralInfoCacheByDeviceId(String deviceId)
//	{
//		EntityManager em  = getEntityManager();
//		String sql = "DELETE "+AOGeneralInfoCache.class.getSimpleName()+" WHERE device_id = :device_id";
//		return em.createQuery(sql).setParameter("device_id", deviceId).executeUpdate();
//	}

//	public int removeKPRAdditionalInfoCacheByDeviceId(String deviceId)
//	{
//		EntityManager em  = getEntityManager();
//		String sql = "DELETE "+AOKPRAdditionalInfoCache.class.getSimpleName()+" WHERE device_id = :device_id";
//		return em.createQuery(sql).setParameter("device_id", deviceId).executeUpdate();
//	}

//	public int removeKTPInfoCacheByDeviceId(String deviceId)
//	{
//		EntityManager em  = getEntityManager();
//		String sql = "DELETE "+AOKTPInfoCache.class.getSimpleName()+" WHERE device_id = :device_id";
//		return em.createQuery(sql).setParameter("device_id", deviceId).executeUpdate();
//	}

//	public int removeMailingAddressCacheByDeviceId(String deviceId)
//	{
//		EntityManager em  = getEntityManager();
//		String sql = "DELETE "+AOMailingAddressCache.class.getSimpleName()+" WHERE device_id = :device_id";
//		return em.createQuery(sql).setParameter("device_id", deviceId).executeUpdate();
//	}

//	public int removeWorkAddressCacheByDeviceId(String deviceId)
//	{
//		EntityManager em  = getEntityManager();
//		String sql = "DELETE "+AOWorkAddressCache.class.getSimpleName()+" WHERE device_id = :device_id";
//		return em.createQuery(sql).setParameter("device_id", deviceId).executeUpdate();
//	}

//	public int removeFinancialInfoCacheByDeviceId(String deviceId)
//	{
//		EntityManager em  = getEntityManager();
//		String sql = "DELETE "+AOFinancialInfoCache.class.getSimpleName()+" WHERE device_id = :device_id";
//		return em.createQuery(sql).setParameter("device_id", deviceId).executeUpdate();
//	}

	// retrieve by nik
	public List<AODocImageCache> getDocImageCacheByNIK(String nik)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AODocImageCache.class.getSimpleName() + " WHERE nik = :nik";
		List<AODocImageCache> listInfo = em.createQuery(sql).setParameter("nik", nik).getResultList();
		return listInfo;
	}

	public List<AOGeneralInfoCache> getAOGeneralInfoCacheByNIK(String nik)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AOGeneralInfoCache.class.getSimpleName() + " WHERE nik = :nik";
		List<AOGeneralInfoCache> listInfo = em.createQuery(sql).setParameter("nik", nik).getResultList();
		return listInfo;
	}

	public List<AODocImagePaySlipCache> getImagePayslipCacheByNIK(String nik)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AODocImagePaySlipCache.class.getSimpleName() + " WHERE nik = :nik";
		List<AODocImagePaySlipCache> listInfo = em.createQuery(sql).setParameter("nik", nik).getResultList();
		return listInfo;
	}

	public List<AODocImageBankStatementCache> getImageBankStatementCacheByNIK(String nik)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AODocImageBankStatementCache.class.getSimpleName() + " WHERE nik = :nik";
		List<AODocImageBankStatementCache> listInfo = em.createQuery(sql).setParameter("nik", nik).getResultList();
		return listInfo;
	}

	//update
	public void updateDeletedStatusByDeviceId(String deviceId)
	{
		EntityManager em  = getEntityManager();
		String sql = "UPDATE "+AOGeneralInfoCache.class.getSimpleName()+" SET status = :status WHERE device_id = :device_id";
		String sql2 = "UPDATE "+AOBankInfoCache.class.getSimpleName()+" SET status = :status WHERE device_id = :device_id and module_code =:moduleCode";
		String sql3 = "UPDATE "+AOBusinessAddressCache.class.getSimpleName()+" SET status = :status WHERE device_id = :device_id and module_code =:moduleCode";
		String sql4 = "UPDATE "+AOBusinessInfoCache.class.getSimpleName()+" SET status = :status WHERE device_id = :device_id and module_code =:moduleCode";
		String sql5 = "UPDATE "+AOCCSupplementInfoCache.class.getSimpleName()+" SET status = :status WHERE device_id = :device_id and module_code =:moduleCode";
		String sql6 = "UPDATE "+AOCCAdditionalInfoCache.class.getSimpleName()+" SET status = :status WHERE device_id = :device_id and module_code =:moduleCode";
		String sql7 = "UPDATE "+AOEmergencyContactAddressCache.class.getSimpleName()+" SET status = :status WHERE device_id = :device_id and module_code =:moduleCode";
		String sql8 = "UPDATE "+AOEmergencyContactCache.class.getSimpleName()+" SET status = :status WHERE device_id = :device_id and module_code =:moduleCode";
		String sql9 = "UPDATE "+AOKPRAdditionalInfoCache.class.getSimpleName()+" SET status = :status WHERE device_id = :device_id and module_code =:moduleCode";
		String sql10 = "UPDATE "+AOKTPInfoCache.class.getSimpleName()+" SET status = :status WHERE device_id = :device_id and module_code =:moduleCode";
		String sql11 = "UPDATE "+AOMailingAddressCache.class.getSimpleName()+" SET status = :status WHERE device_id = :device_id and module_code =:moduleCode";
		String sql12 = "UPDATE "+AOWorkAddressCache.class.getSimpleName()+" SET status = :status WHERE device_id = :device_id and module_code =:moduleCode";
		String sql13 = "UPDATE "+AOFinancialInfoCache.class.getSimpleName()+" SET status = :status WHERE device_id = :device_id and module_code =:moduleCode";

		em.createQuery(sql).setParameter("status", MiBConstant.MIB_DELETED_STATUS).setParameter("device_id", deviceId).executeUpdate();
		em.createQuery(sql2).setParameter("status", MiBConstant.MIB_DELETED_STATUS).setParameter("device_id", deviceId).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
		em.createQuery(sql3).setParameter("status", MiBConstant.MIB_DELETED_STATUS).setParameter("device_id", deviceId).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
		em.createQuery(sql4).setParameter("status", MiBConstant.MIB_DELETED_STATUS).setParameter("device_id", deviceId).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
		em.createQuery(sql5).setParameter("status", MiBConstant.MIB_DELETED_STATUS).setParameter("device_id", deviceId).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
		em.createQuery(sql6).setParameter("status", MiBConstant.MIB_DELETED_STATUS).setParameter("device_id", deviceId).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
		em.createQuery(sql7).setParameter("status", MiBConstant.MIB_DELETED_STATUS).setParameter("device_id", deviceId).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
		em.createQuery(sql8).setParameter("status", MiBConstant.MIB_DELETED_STATUS).setParameter("device_id", deviceId).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
		em.createQuery(sql9).setParameter("status", MiBConstant.MIB_DELETED_STATUS).setParameter("device_id", deviceId).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
		em.createQuery(sql10).setParameter("status", MiBConstant.MIB_DELETED_STATUS).setParameter("device_id", deviceId).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
		em.createQuery(sql11).setParameter("status", MiBConstant.MIB_DELETED_STATUS).setParameter("device_id", deviceId).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
		em.createQuery(sql12).setParameter("status", MiBConstant.MIB_DELETED_STATUS).setParameter("device_id", deviceId).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
		em.createQuery(sql13).setParameter("status", MiBConstant.MIB_DELETED_STATUS).setParameter("device_id", deviceId).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();

	}


	public void updateDeletedStatusByNIK(String nik)
	{
		EntityManager em  = getEntityManager();
		String sql = "UPDATE "+AOGeneralInfoCache.class.getSimpleName()+" SET status = :status WHERE nik = :nik";
		String sql2 = "UPDATE "+AOBankInfoCache.class.getSimpleName()+" SET status = :status WHERE nik = :nik and module_code =:moduleCode";
		String sql3 = "UPDATE "+AOBusinessAddressCache.class.getSimpleName()+" SET status = :status WHERE nik = :nik and module_code =:moduleCode";
		String sql4 = "UPDATE "+AOBusinessInfoCache.class.getSimpleName()+" SET status = :status WHERE nik = :nik and module_code =:moduleCode";
		String sql5 = "UPDATE "+AOCCSupplementInfoCache.class.getSimpleName()+" SET status = :status WHERE nik = :nik and module_code =:moduleCode";
		String sql6 = "UPDATE "+AOCCAdditionalInfoCache.class.getSimpleName()+" SET status = :status WHERE nik = :nik and module_code =:moduleCode";
		String sql7 = "UPDATE "+AOEmergencyContactAddressCache.class.getSimpleName()+" SET status = :status WHERE nik = :nik and module_code =:moduleCode";
		String sql8 = "UPDATE "+AOEmergencyContactCache.class.getSimpleName()+" SET status = :status WHERE nik = :nik and module_code =:moduleCode";
		String sql9 = "UPDATE "+AOKPRAdditionalInfoCache.class.getSimpleName()+" SET status = :status WHERE nik = :nik and module_code =:moduleCode";
		String sql10 = "UPDATE "+AOKTPInfoCache.class.getSimpleName()+" SET status = :status WHERE nik = :nik and module_code =:moduleCode";
		String sql11 = "UPDATE "+AOMailingAddressCache.class.getSimpleName()+" SET status = :status WHERE nik = :nik and module_code =:moduleCode";
		String sql12 = "UPDATE "+AOWorkAddressCache.class.getSimpleName()+" SET status = :status WHERE nik = :nik and module_code =:moduleCode";
		String sql13 = "UPDATE "+AOFinancialInfoCache.class.getSimpleName()+" SET status = :status WHERE nik = :nik and module_code =:moduleCode";

		em.createQuery(sql).setParameter("status", MiBConstant.MIB_DELETED_STATUS).setParameter("nik", nik).executeUpdate();
		em.createQuery(sql2).setParameter("status", MiBConstant.MIB_DELETED_STATUS).setParameter("nik", nik).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
		em.createQuery(sql3).setParameter("status", MiBConstant.MIB_DELETED_STATUS).setParameter("nik", nik).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
		em.createQuery(sql4).setParameter("status", MiBConstant.MIB_DELETED_STATUS).setParameter("nik", nik).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
		em.createQuery(sql5).setParameter("status", MiBConstant.MIB_DELETED_STATUS).setParameter("nik", nik).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
		em.createQuery(sql6).setParameter("status", MiBConstant.MIB_DELETED_STATUS).setParameter("nik", nik).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
		em.createQuery(sql7).setParameter("status", MiBConstant.MIB_DELETED_STATUS).setParameter("nik", nik).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
		em.createQuery(sql8).setParameter("status", MiBConstant.MIB_DELETED_STATUS).setParameter("nik", nik).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
		em.createQuery(sql9).setParameter("status", MiBConstant.MIB_DELETED_STATUS).setParameter("nik", nik).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
		em.createQuery(sql10).setParameter("status", MiBConstant.MIB_DELETED_STATUS).setParameter("nik", nik).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
		em.createQuery(sql11).setParameter("status", MiBConstant.MIB_DELETED_STATUS).setParameter("nik", nik).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
		em.createQuery(sql12).setParameter("status", MiBConstant.MIB_DELETED_STATUS).setParameter("nik", nik).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();
		em.createQuery(sql13).setParameter("status", MiBConstant.MIB_DELETED_STATUS).setParameter("nik", nik).setParameter("moduleCode", MiBConstant.MODULE_CODE_ACC_ON_BOARDING).executeUpdate();

	}

	// search by device id and status/////////////////////////////////////////////////////////////////////////
	

//	public List<AOBankInfoCache> getBankPendingCacheInputByDeviceId(String deviceId)
//	{
//		EntityManager em  = getEntityManager();
//		String sql = "FROM "+AOBankInfoCache.class.getSimpleName() + " WHERE device_id = :device_id and status=:status";
//		List<AOBankInfoCache> listInfo = em.createQuery(sql).setParameter("device_id", deviceId).setParameter("status", MiBConstant.MIB_PENDING_STATUS).getResultList();
//		return listInfo;
//	}

//	public List<AOBusinessAddressCache> getBusinessAddressPendingCacheByDeviceId(String deviceId)
//	{
//		EntityManager em  = getEntityManager();
//		String sql = "FROM "+AOBusinessAddressCache.class.getSimpleName() + " WHERE device_id = :device_id and status=:status";
//		List<AOBusinessAddressCache> listInfo = em.createQuery(sql).setParameter("device_id", deviceId).setParameter("status", MiBConstant.MIB_PENDING_STATUS).getResultList();
//		return listInfo;
//	}

//	public List<AOBusinessInfoCache> getBusinessInfoPendingCacheByDeviceId(String deviceId)
//	{
//		EntityManager em  = getEntityManager();
//		String sql = "FROM "+AOBusinessInfoCache.class.getSimpleName() + " WHERE device_id = :device_id and status=:status";
//		List<AOBusinessInfoCache> listInfo = em.createQuery(sql).setParameter("device_id", deviceId).setParameter("status", MiBConstant.MIB_PENDING_STATUS).getResultList();
//		return listInfo;
//	}

//	public List<AOCCSupplementInfoCache> getCCSupplementInfoPendingCacheByDeviceId(String deviceId)
//	{
//		EntityManager em  = getEntityManager();
//		String sql = "FROM "+AOCCSupplementInfoCache.class.getSimpleName() + " WHERE device_id = :device_id and status=:status";
//		List<AOCCSupplementInfoCache> listInfo = em.createQuery(sql).setParameter("device_id", deviceId).setParameter("status", MiBConstant.MIB_PENDING_STATUS).getResultList();
//		return listInfo;
//	}

//	public List<AOCCAdditionalInfoCache> getCCAdditionalInfoPendingCacheByDeviceId(String deviceId)
//	{
//		EntityManager em  = getEntityManager();
//		String sql = "FROM "+AOCCAdditionalInfoCache.class.getSimpleName() + " WHERE device_id = :device_id and status=:status";
//		List<AOCCAdditionalInfoCache> listInfo = em.createQuery(sql).setParameter("device_id", deviceId).setParameter("status", MiBConstant.MIB_PENDING_STATUS).getResultList();
//		return listInfo;
//	}

//	public List<AOEmergencyContactAddressCache> getEmergencyContactAddressPendingCacheByDeviceId(String deviceId)
//	{
//		EntityManager em  = getEntityManager();
//		String sql = "FROM "+AOEmergencyContactAddressCache.class.getSimpleName() + " WHERE device_id = :device_id and status=:status";
//		List<AOEmergencyContactAddressCache> listInfo = em.createQuery(sql).setParameter("device_id", deviceId).setParameter("status", MiBConstant.MIB_PENDING_STATUS).getResultList();
//		return listInfo;
//	}

//	public List<AOEmergencyContactCache> getEmergencyContactPendingCacheByDeviceId(String deviceId)
//	{
//		EntityManager em  = getEntityManager();
//		String sql = "FROM "+AOEmergencyContactCache.class.getSimpleName() + " WHERE device_id = :device_id and status=:status";
//		List<AOEmergencyContactCache> listInfo = em.createQuery(sql).setParameter("device_id", deviceId).setParameter("status", MiBConstant.MIB_PENDING_STATUS).getResultList();
//		return listInfo;
//	}

	public List<AOGeneralInfoCache> getAOGeneralInfoPendingCacheByDeviceId(String deviceId)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AOGeneralInfoCache.class.getSimpleName() + " WHERE device_id = :device_id and status=:status";
		List<AOGeneralInfoCache> listInfo = em.createQuery(sql).setParameter("device_id", deviceId).setParameter("status", MiBConstant.MIB_PENDING_STATUS).getResultList();
		return listInfo;
	}

//	public List<AOKPRAdditionalInfoCache> getKPRAdditionalInfoPendingCacheByDeviceId(String deviceId)
//	{
//		EntityManager em  = getEntityManager();
//		String sql = "FROM "+AOKPRAdditionalInfoCache.class.getSimpleName() + " WHERE device_id = :device_id and status=:status";
//		List<AOKPRAdditionalInfoCache> listInfo = em.createQuery(sql).setParameter("device_id", deviceId).setParameter("status", MiBConstant.MIB_PENDING_STATUS).getResultList();
//		return listInfo;
//	}

//	public List<AOKTPInfoCache> getKTPInfoPendingCacheByDeviceId(String deviceId)
//	{
//		EntityManager em  = getEntityManager();
//		String sql = "FROM "+AOKTPInfoCache.class.getSimpleName() + " WHERE device_id = :device_id and status=:status";
//		List<AOKTPInfoCache> listInfo = em.createQuery(sql).setParameter("device_id", deviceId).setParameter("status", MiBConstant.MIB_PENDING_STATUS).getResultList();
//		return listInfo;
//	}

//	public List<AOMailingAddressCache> getMailingAddressPendingCacheByDeviceId(String deviceId)
//	{
//		EntityManager em  = getEntityManager();
//		String sql = "FROM "+AOMailingAddressCache.class.getSimpleName() + " WHERE device_id = :device_id and status=:status";
//		List<AOMailingAddressCache> listInfo = em.createQuery(sql).setParameter("device_id", deviceId).setParameter("status", MiBConstant.MIB_PENDING_STATUS).getResultList();
//		return listInfo;
//	}

//	public List<AOFinancialInfoCache> getFinancialInfoPendingCacheByDeviceId(String deviceId)
//	{
//		EntityManager em  = getEntityManager();
//		String sql = "FROM "+AOFinancialInfoCache.class.getSimpleName() + " WHERE device_id = :device_id and status=:status";
//		List<AOFinancialInfoCache> listInfo = em.createQuery(sql).setParameter("device_id", deviceId).setParameter("status", MiBConstant.MIB_PENDING_STATUS).getResultList();
//		return listInfo;
//	}

//	public List<AOWorkAddressCache> getWorkAddressPendingCacheByDeviceId(String deviceId)
//	{
//		EntityManager em  = getEntityManager();
//		String sql = "FROM "+AOWorkAddressCache.class.getSimpleName() + " WHERE device_id = :device_id and status=:status";
//		List<AOWorkAddressCache> listInfo = em.createQuery(sql).setParameter("device_id", deviceId).setParameter("status", MiBConstant.MIB_PENDING_STATUS).getResultList();
//		return listInfo;
//	}
	
	//search by nik and status/////////////////////////////////////////////////////////////
	public List<AOGeneralInfoCache> getAOGeneralInfoPendingCacheByNIK(String nik)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AOGeneralInfoCache.class.getSimpleName() + " WHERE nik = :nik and status=:status";
		List<AOGeneralInfoCache> listInfo = em.createQuery(sql).setParameter("nik", nik).setParameter("status", MiBConstant.MIB_PENDING_STATUS).getResultList();
		return listInfo;
	}

//	public void deletePendingCacheByDeviceId(String deviceId)
//	{
//		EntityManager em  = getEntityManager();
//		String sql = "DELETE "+AOBankInfoCache.class.getSimpleName()+" WHERE device_id = :device_id and status='"+MiBConstant.MIB_PENDING_STATUS+"'";
//		String sql2 = "DELETE "+AOBusinessAddressCache.class.getSimpleName()+" WHERE device_id = :device_id and status='"+MiBConstant.MIB_PENDING_STATUS+"'";
//		String sql3 = "DELETE "+AOBusinessInfoCache.class.getSimpleName()+" WHERE device_id = :device_id and status='"+MiBConstant.MIB_PENDING_STATUS+"'";
//		String sql4 = "DELETE "+AOCCSupplementInfoCache.class.getSimpleName()+" WHERE device_id = :device_id and status='"+MiBConstant.MIB_PENDING_STATUS+"'";
//		String sql5 = "DELETE "+AOCCAdditionalInfoCache.class.getSimpleName()+" WHERE device_id = :device_id and status='"+MiBConstant.MIB_PENDING_STATUS+"'";
//		String sql6 = "DELETE "+AODocImageCache.class.getSimpleName()+" WHERE device_id = :device_id";
//		String sql7 = "DELETE "+AOEmergencyContactAddressCache.class.getSimpleName()+" WHERE device_id = :device_id and status='"+MiBConstant.MIB_PENDING_STATUS+"'";
//		String sql8 = "DELETE "+AOEmergencyContactCache.class.getSimpleName()+" WHERE device_id = :device_id and status='"+MiBConstant.MIB_PENDING_STATUS+"'";
//		String sql9 = "DELETE "+AOGeneralInfoCache.class.getSimpleName()+" WHERE device_id = :device_id and status='"+MiBConstant.MIB_PENDING_STATUS+"'";
//		String sql10 = "DELETE "+AOKPRAdditionalInfoCache.class.getSimpleName()+" WHERE device_id = :device_id and status='"+MiBConstant.MIB_PENDING_STATUS+"'";
//		String sql11 = "DELETE "+AOKTPInfoCache.class.getSimpleName()+" WHERE device_id = :device_id and status='"+MiBConstant.MIB_PENDING_STATUS+"'";
//		String sql12 = "DELETE "+AOMailingAddressCache.class.getSimpleName()+" WHERE device_id = :device_id and status='"+MiBConstant.MIB_PENDING_STATUS+"'";
//		String sql13 = "DELETE "+AOWorkAddressCache.class.getSimpleName()+" WHERE device_id = :device_id and status='"+MiBConstant.MIB_PENDING_STATUS+"'";
//		String sql14 = "DELETE "+AOFinancialInfoCache.class.getSimpleName()+" WHERE device_id = :device_id and status='"+MiBConstant.MIB_PENDING_STATUS+"'";
//		String sql15 = "DELETE "+AODocImageBankStatementCache.class.getSimpleName()+" WHERE device_id = :device_id";
//		String sql16 = "DELETE "+AODocImagePaySlipCache.class.getSimpleName()+" WHERE device_id = :device_id";
//
//		em.createQuery(sql).setParameter("device_id", deviceId).executeUpdate();
//		em.createQuery(sql2).setParameter("device_id", deviceId).executeUpdate();
//		em.createQuery(sql3).setParameter("device_id", deviceId).executeUpdate();
//		em.createQuery(sql4).setParameter("device_id", deviceId).executeUpdate();
//		em.createQuery(sql5).setParameter("device_id", deviceId).executeUpdate();
//		em.createQuery(sql6).setParameter("device_id", deviceId).executeUpdate();
//		em.createQuery(sql7).setParameter("device_id", deviceId).executeUpdate();
//		em.createQuery(sql8).setParameter("device_id", deviceId).executeUpdate();
//		em.createQuery(sql9).setParameter("device_id", deviceId).executeUpdate();
//		em.createQuery(sql10).setParameter("device_id", deviceId).executeUpdate();
//		em.createQuery(sql11).setParameter("device_id", deviceId).executeUpdate();
//		em.createQuery(sql12).setParameter("device_id", deviceId).executeUpdate();
//		em.createQuery(sql13).setParameter("device_id", deviceId).executeUpdate();
//		em.createQuery(sql14).setParameter("device_id", deviceId).executeUpdate();
//		em.createQuery(sql15).setParameter("device_id", deviceId).executeUpdate();
//		em.createQuery(sql16).setParameter("device_id", deviceId).executeUpdate();
//
//	}

//	public void deletePendingCacheByNIK(String nik)
//	{
//		EntityManager em  = getEntityManager();
//		String sql = "DELETE "+AOBankInfoCache.class.getSimpleName()+" WHERE nik = :nik and status='"+MiBConstant.MIB_PENDING_STATUS+"'";
//		String sql2 = "DELETE "+AOBusinessAddressCache.class.getSimpleName()+" WHERE nik = :nik and status='"+MiBConstant.MIB_PENDING_STATUS+"'";
//		String sql3 = "DELETE "+AOBusinessInfoCache.class.getSimpleName()+" WHERE nik = :nik and status='"+MiBConstant.MIB_PENDING_STATUS+"'";
//		String sql4 = "DELETE "+AOCCSupplementInfoCache.class.getSimpleName()+" WHERE nik = :nik and status='"+MiBConstant.MIB_PENDING_STATUS+"'";
//		String sql5 = "DELETE "+AOCCAdditionalInfoCache.class.getSimpleName()+" WHERE nik = :nik and status='"+MiBConstant.MIB_PENDING_STATUS+"'";
//		String sql6 = "DELETE "+AODocImageCache.class.getSimpleName()+" WHERE nik = :nik";
//		String sql7 = "DELETE "+AOEmergencyContactAddressCache.class.getSimpleName()+" WHERE nik = :nik and status='"+MiBConstant.MIB_PENDING_STATUS+"'";
//		String sql8 = "DELETE "+AOEmergencyContactCache.class.getSimpleName()+" WHERE nik = :nik and status='"+MiBConstant.MIB_PENDING_STATUS+"'";
//		String sql9 = "DELETE "+AOGeneralInfoCache.class.getSimpleName()+" WHERE nik = :nik and status='"+MiBConstant.MIB_PENDING_STATUS+"'";
//		String sql10 = "DELETE "+AOKPRAdditionalInfoCache.class.getSimpleName()+" WHERE nik = :nik and status='"+MiBConstant.MIB_PENDING_STATUS+"'";
//		String sql11 = "DELETE "+AOKTPInfoCache.class.getSimpleName()+" WHERE nik = :nik and status='"+MiBConstant.MIB_PENDING_STATUS+"'";
//		String sql12 = "DELETE "+AOMailingAddressCache.class.getSimpleName()+" WHERE nik = :nik and status='"+MiBConstant.MIB_PENDING_STATUS+"'";
//		String sql13 = "DELETE "+AOWorkAddressCache.class.getSimpleName()+" WHERE nik = :nik and status='"+MiBConstant.MIB_PENDING_STATUS+"'";
//		String sql14 = "DELETE "+AOFinancialInfoCache.class.getSimpleName()+" WHERE nik = :nik and status='"+MiBConstant.MIB_PENDING_STATUS+"'";
//		String sql15 = "DELETE "+AODocImageBankStatementCache.class.getSimpleName()+" WHERE nik = :nik";
//		String sql16 = "DELETE "+AODocImagePaySlipCache.class.getSimpleName()+" WHERE nik = :nik";
//
//		em.createQuery(sql).setParameter("nik", nik).executeUpdate();
//		em.createQuery(sql2).setParameter("nik", nik).executeUpdate();
//		em.createQuery(sql3).setParameter("nik", nik).executeUpdate();
//		em.createQuery(sql4).setParameter("nik", nik).executeUpdate();
//		em.createQuery(sql5).setParameter("nik", nik).executeUpdate();
//		em.createQuery(sql6).setParameter("nik", nik).executeUpdate();
//		em.createQuery(sql7).setParameter("nik", nik).executeUpdate();
//		em.createQuery(sql8).setParameter("nik", nik).executeUpdate();
//		em.createQuery(sql9).setParameter("nik", nik).executeUpdate();
//		em.createQuery(sql10).setParameter("nik", nik).executeUpdate();
//		em.createQuery(sql11).setParameter("nik", nik).executeUpdate();
//		em.createQuery(sql12).setParameter("nik", nik).executeUpdate();
//		em.createQuery(sql13).setParameter("nik", nik).executeUpdate();
//		em.createQuery(sql14).setParameter("nik", nik).executeUpdate();
//		em.createQuery(sql15).setParameter("nik", nik).executeUpdate();
//		em.createQuery(sql16).setParameter("nik", nik).executeUpdate();
//
//	}
	
	@Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return emx;
	}

	public List<AOImageDocCacheData> getAOImageDataByUUID(String uuid)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AOImageDocCacheData.class.getSimpleName()+" Where uuid = :uuid ";
		List<AOImageDocCacheData> listInfo = em.createQuery(sql).setParameter("uuid", uuid).getResultList();
		return listInfo;
	}

//	public List<AOImageDocCacheData> getALLAOImageData()
//	{
//		EntityManager em  = getEntityManager();
//		String sql = "FROM "+AOImageDocCacheData.class.getSimpleName() + " WHERE status <> :status";
//		List<AOImageDocCacheData> listInfo = em.createQuery(sql).setParameter("status", MiBConstant.ITH_STATUS_FAIL.toUpperCase()).getResultList();
//		return listInfo;
//	}
	
	public void deleteAOImageData(Integer rowId, String uuid)
	{
		EntityManager em  = getEntityManager();
		String sql = "DELETE "+AOImageDocCacheData.class.getSimpleName()+" WHERE row_id = :rowId";
		String sql2 = "DELETE "+AODocImageCache.class.getSimpleName()+" WHERE uuid = :uuid";
		String sql3 = "DELETE "+AODocImagePaySlipCache.class.getSimpleName()+" WHERE uuid = :uuid";
		String sql4 = "DELETE "+AODocImageBankStatementCache.class.getSimpleName()+" WHERE uuid = :uuid";
		
		em.createQuery(sql).setParameter("rowId", rowId).executeUpdate();
		em.createQuery(sql2).setParameter("uuid", uuid).executeUpdate();
		em.createQuery(sql3).setParameter("uuid", uuid).executeUpdate();
		em.createQuery(sql4).setParameter("uuid", uuid).executeUpdate();
	}
	
	public List<AOImageDocCacheData> getAOImageDataByDeviceIdAndDocType(String deviceId, String docType, String refNo)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+AOImageDocCacheData.class.getSimpleName() + " WHERE device_id = :device_id and doc_type=:doc_type and status <> :status and ref_no=:refNo";
		List<AOImageDocCacheData> listInfo = em.createQuery(sql).setParameter("device_id", deviceId).setParameter("doc_type", docType).setParameter("status", MiBConstant.ITH_STATUS_FAIL.toUpperCase()).setParameter("refNo", refNo).getResultList();
		return listInfo;
	}
	
	public void deletePendingImageCacheByDeviceId(String deviceId)
	{
		EntityManager em  = getEntityManager();
		String sql = "DELETE "+AODocImageCache.class.getSimpleName()+" WHERE device_id = :device_id";
		String sql2 = "DELETE "+AODocImageBankStatementCache.class.getSimpleName()+" WHERE device_id = :device_id";
		String sql3 = "DELETE "+AODocImagePaySlipCache.class.getSimpleName()+" WHERE device_id = :device_id";

		em.createQuery(sql).setParameter("device_id", deviceId).executeUpdate();
		em.createQuery(sql2).setParameter("device_id", deviceId).executeUpdate();
		em.createQuery(sql3).setParameter("device_id", deviceId).executeUpdate();
	}

	public void deletePendingImageCacheByNIK(String nik)
	{
		EntityManager em  = getEntityManager();
		String sql = "DELETE "+AODocImageCache.class.getSimpleName()+" WHERE nik = :nik";
		String sql2 = "DELETE "+AODocImageBankStatementCache.class.getSimpleName()+" WHERE nik = :nik";
		String sql3 = "DELETE "+AODocImagePaySlipCache.class.getSimpleName()+" WHERE nik = :nik";

		em.createQuery(sql).setParameter("nik", nik).executeUpdate();
		em.createQuery(sql2).setParameter("nik", nik).executeUpdate();
		em.createQuery(sql3).setParameter("nik", nik).executeUpdate();
	}
	
//	public void deleteAOImageData(String date)
//	{
//		EntityManager em  = getEntityManager();
//		String sql = "DELETE "+AOImageDocCacheData.class.getSimpleName()+" WHERE created_time <= :created_time and status =:status";
//
//		em.createQuery(sql).setParameter("created_time", date).setParameter("status", MiBConstant.ITH_STATUS_SUCCESS.toUpperCase()).executeUpdate();
//	}
}