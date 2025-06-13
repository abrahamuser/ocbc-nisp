package com.silverlake.mleb.mcb.entity.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import comx.silverlake.mleb.mcb.entity.ExchangeRate;
import comx.silverlake.mleb.mcb.entity.FTBankList;

@Service
@Repository
@Transactional
public class FundTransferDao extends DAO
{
	private static Logger log = LogManager.getLogger(FundTransferDao.class);


	@PersistenceContext(unitName="db_mleb")
	EntityManager emx ;
	
	public int removeBankList()
	{
		EntityManager em  = getEntityManager();
		String sql = "DELETE "+FTBankList.class.getSimpleName();
		return em.createQuery(sql).executeUpdate();
	}
	
	public FTBankList getFTBankList(String bankCode, String bankName, String transferService)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+FTBankList.class.getSimpleName() + 
				     " where bank_code=:bank_code and bank_name=:bank_name and transfer_service=:transfer_service";
		List<FTBankList> listInfo = em.createQuery(sql)
										.setParameter("bank_code", bankCode)
										.setParameter("bank_name", bankName)
										.setParameter("transfer_service", transferService)
										.getResultList();
		if(listInfo != null && listInfo.size() > 0){
			return listInfo.get(0);
		}
		else{
			return null;
		}
	}
	
	public List<FTBankList> getFTBankList()
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+FTBankList.class.getSimpleName();
		List<FTBankList> listInfo = em.createQuery(sql).getResultList();
		if(listInfo != null && listInfo.size() > 0){
			return listInfo;
		}
		
		return null;
	}
	
	public List<String> getDistinctBankList()
	{
		EntityManager em  = getEntityManager();
		String sql = "select DISTINCT bankName FROM "+FTBankList.class.getSimpleName();
		List<String> listInfo = em.createQuery(sql).getResultList();
		if(listInfo != null && listInfo.size() > 0){
			return listInfo;
		}
		
		return null;
	}
	
	public List<FTBankList> getFTBankListByBankName(String bankName)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+FTBankList.class.getSimpleName() + " where bank_name = :bankName";
		List<FTBankList> listInfo = em.createQuery(sql).setParameter("bankName", bankName).getResultList();
		if(listInfo != null && listInfo.size() > 0){
			return listInfo;
		}
		
		return null;
	}
	
	public String getFTBankNameByBankCode(String bankCode)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+FTBankList.class.getSimpleName() + " where bank_code = :bankCode";
		List<FTBankList> listInfo = em.createQuery(sql).setParameter("bankCode", bankCode).getResultList();
		if(listInfo != null && listInfo.size() > 0){
			return listInfo.get(0).getBankName();
		}
		else{
			return null;
		}
	}
	
	public int removeExchangeRate()
	{
		EntityManager em  = getEntityManager();
		String sql = "DELETE "+ExchangeRate.class.getSimpleName();
		return em.createQuery(sql).executeUpdate();
	}
	
	public List<ExchangeRate> getExchangeRateList()
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+ExchangeRate.class.getSimpleName();
		List<ExchangeRate> listExchangeRate = em.createQuery(sql).getResultList();
		if(listExchangeRate != null && listExchangeRate.size() > 0){
			return listExchangeRate;
		}
		
		return null;
	}
	
	@Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return emx;
	}
}