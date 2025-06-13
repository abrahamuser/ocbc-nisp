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

@Service
@Repository
@Transactional
public class TimeDepositDao extends DAO
{
	private static Logger log = LogManager.getLogger(TimeDepositDao.class);


	@PersistenceContext(unitName="db_mleb")
	EntityManager emx ;
	
	public List getTDProduct(String localeCode)
	{
		EntityManager em  = getEntityManager();
		String getGroupTypeLocale = "";
		
		if(localeCode.equalsIgnoreCase(MiBConstant.LANG_EN)){
			getGroupTypeLocale = " tdpc.groupTypeEN ";
			localeCode = MiBConstant.LANG_EN;
		}
		else if(localeCode.toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase())){
			getGroupTypeLocale = " tdpc.groupTypeCN ";
			localeCode = MiBConstant.LANG_CN;
		}
		else{
			getGroupTypeLocale = " tdpc.groupTypeIN ";
			localeCode = MiBConstant.LANG_IN;
		}
		
		String sql = "select tdp.groupSeq, " + getGroupTypeLocale + " ,tdp.productName, tdp.productDesc, tdp.productCode, tdp.sequence " +
				     " from TDProduct tdp, TDProductCategory tdpc where tdp.groupSeq = tdpc.groupSeq " +
				     " and tdp.status = '" + MiBConstant.STATUS_ACTIVE +"' and UPPER(tdp.lang) = '" + localeCode +"'";
		
		List result = em.createQuery(sql).getResultList();
		
		if(result != null && result.size() > 0){
			return result;
		}
		
        return null;	
        
	}
	
	@Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return emx;
	}
}