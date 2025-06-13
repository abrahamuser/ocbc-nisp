package com.silverlake.mleb.mcb.entity.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import comx.silverlake.mleb.mcb.entity.BankCity;
import comx.silverlake.mleb.mcb.entity.BankCountry;
import comx.silverlake.mleb.mcb.entity.BankList;

@Service
@Repository
@Transactional
public class TelegraphicTransferDAO extends DAO {
	
	private static Logger log = LogManager.getLogger(TelegraphicTransferDAO.class);

	@PersistenceContext(unitName="db_mleb")
	EntityManager emx ;
	
	public int removeBankCountry()
	{
		EntityManager em  = getEntityManager();
		String sql = "DELETE "+BankCountry.class.getSimpleName();
		return em.createQuery(sql).executeUpdate();
	}
	
	public int removeBankCity(String countryCode)
	{
		EntityManager em  = getEntityManager();
		String sql = "DELETE "+BankCity.class.getSimpleName() + " WHERE country_code=:country_code";
		return em.createQuery(sql).setParameter("country_code", countryCode).executeUpdate();
	}
	
	public int removeBankList(String countryCode, String cityId)
	{
		EntityManager em  = getEntityManager();
		String sql = "DELETE "+BankList.class.getSimpleName()+ " WHERE country_code=:country_code and city_id = :city_id";
		return em.createQuery(sql).setParameter("country_code", countryCode).setParameter("city_id", cityId).executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public List<BankCountry> getBankCountryList()
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+BankCountry.class.getSimpleName();
		List<BankCountry> bankCountryList = em.createQuery(sql).getResultList();
		if(bankCountryList != null && bankCountryList.size() > 0){
			return bankCountryList;
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public BankCountry getBankCountryByCountryCode(String countryCode)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+BankCountry.class.getSimpleName() + " where country_code=:countryCode";
		List<BankCountry> bankCountryList = em.createQuery(sql)
											  .setParameter("countryCode", countryCode)
											  .getResultList();
		if(bankCountryList != null && bankCountryList.size() > 0){
			return bankCountryList.get(0);
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public BankCountry getBankCountryByCountryName(String countryName)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+BankCountry.class.getSimpleName() + " where country_name=:countryName";
		List<BankCountry> bankCountryList = em.createQuery(sql)
											  .setParameter("countryName", countryName)
											  .getResultList();
		if(bankCountryList != null && bankCountryList.size() > 0){
			return bankCountryList.get(0);
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BankCity> getBankCityList(String countryCode, String countryName)
	{
		EntityManager em  = getEntityManager();
		String sql; 
		
		List<BankCity> bankCityList = null;
		if(countryCode!=null && !countryCode.isEmpty())
		{
			sql = "FROM "+BankCity.class.getSimpleName() + " where country_code=:countryCode";
			bankCityList = em.createQuery(sql).setParameter("countryCode", countryCode).getResultList();
		}
		else if(countryName!=null && !countryName.isEmpty())
		{
			sql = "FROM "+BankCity.class.getSimpleName() + " where country_name=:countryName";
			bankCityList = em.createQuery(sql).setParameter("countryName", countryName).getResultList();
		}
		
		return bankCityList;
	}
	
	@SuppressWarnings("unchecked")
	public BankCity getBankCityByCityId(String countryCode, String cityId)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+BankCity.class.getSimpleName() + " where country_code=:countryCode and city_id=:cityId";
		List<BankCity> bankCityList = em.createQuery(sql)
											  .setParameter("countryCode", countryCode)
											  .setParameter("cityId", cityId)
				                              .getResultList();
		if(bankCityList != null && bankCityList.size() > 0){
			return bankCityList.get(0);
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BankList> getBankList(String countryCode, String cityId)
	{
		EntityManager em  = getEntityManager();

		String sql = "FROM "+BankList.class.getSimpleName() + " where country_code=:countryCode and city_id=:cityId";
		List<BankList> bankListList = em.createQuery(sql)
										.setParameter("countryCode", countryCode)
										.setParameter("cityId", cityId)
										.getResultList();
		if(bankListList != null && bankListList.size() > 0){
			return bankListList;
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public BankList getBankList(String bankId)
	{
		EntityManager em  = getEntityManager();

		String sql = "FROM "+BankList.class.getSimpleName() + " where bank_id=:bankId ";
		List<BankList> bankListList = em.createQuery(sql)
										.setParameter("bankId", bankId)
										.getResultList();
		if(bankListList != null && bankListList.size() > 0){
			return bankListList.get(0);
		}
		
		return null;
	}
	
	@Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return emx;
	}
}
