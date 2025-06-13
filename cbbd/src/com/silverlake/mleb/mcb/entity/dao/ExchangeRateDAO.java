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

@Service
@Repository
@Transactional
public class ExchangeRateDAO extends DAO
{
	private static Logger log = LogManager.getLogger(ExchangeRateDAO.class);


	@PersistenceContext(unitName="db_mleb")
	EntityManager emx ;
	
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
	
	public ExchangeRate retrieveExchangeTTRateByCurrencyCode(String currencyCode){
		ExchangeRate exchangeRate = new ExchangeRate();
		EntityManager em  = getEntityManager();
		String sql = "FROM "+ExchangeRate.class.getSimpleName() + " where currency_code = :ccyCode";
		List<ExchangeRate> resultlist = em.createQuery(sql).setParameter("ccyCode", currencyCode).getResultList();
		
		if(resultlist != null && resultlist.size() > 0){
			exchangeRate.setTtBuyRate(resultlist.get(0).getTtBuyRate());
			exchangeRate.setTtSellRate(resultlist.get(0).getTtSellRate());
			return exchangeRate;
		}
		
		return exchangeRate;
	}
	
	public int removeExchangeRate()
	{
		EntityManager em  = getEntityManager();
		String sql = "DELETE "+ExchangeRate.class.getSimpleName();
		return em.createQuery(sql).executeUpdate();
	}
	
	@Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return emx;
	}
}
