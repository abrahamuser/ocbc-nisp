package com.silverlake.mleb.mcb.entity.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silverlake.hlb.pex.entity.HlbAccessLock;
import com.silverlake.mleb.mcb.constant.MiBConstant;

@Service
@Repository
@Transactional
public class InterbankDAO extends DAO
{
	@Autowired DozerBeanMapper mapper;
	
	@PersistenceContext(unitName="db_mleb")
	EntityManager emx ;
	
	private static Logger log = LogManager.getLogger(InterbankDAO.class);
	
	@Transactional
	public boolean putIfAbsent(String key,String data, long idle)
	{
			Date currentDate = new Date();
			
			
			String sql = "FROM HlbAccessLock WHERE KEY_VALUE = :key";
			Hashtable params = new Hashtable();
			params.put("key", key);
			log.info("SQL :: "+sql);
			List<HlbAccessLock> rs = selectQueryParam(sql, params);
			
			log.info("rs :: "+rs);
			if(rs.size()>0)
			{log.info("rs.size() :: "+rs.size());
				
				for(HlbAccessLock accLock:rs)
				{
					
					long duration = currentDate.getTime() - accLock.getAccessDate().getTime();
					SimpleDateFormat dt = new SimpleDateFormat( MiBConstant.DB_DATETIME_FORMAT);
					String currentDB =dt.format(accLock.getAccessDate());
					
					if( duration>idle)
					{

						accLock.setEntryDate(currentDate);
						accLock.setAccessDate(currentDate);
						accLock.setData(data);
						accLock.setStatus("ACTIVE");
						accLock.setIdleTimeSecond(Integer.parseInt(""+idle));
						accLock = (HlbAccessLock) updateEntity(accLock);
				
						return true;

					
					}
				}
			}
			else
			{
				HlbAccessLock lock = new HlbAccessLock();
				
				lock.setEntryDate(currentDate);
				lock.setData(data);
				lock.setKeyValue(key);
				lock.setStatus("ACTIVE");
				lock.setAccessDate(currentDate);
				lock.setIdleTimeSecond(Integer.parseInt(""+idle));
				insertEntity(lock);
				
				return true;
			}
		
		
		return false;
	}
	
	@Transactional
	public String getLockData(String key)
	{
		Date currentDate = new Date();
		String sql = "FROM HlbAccessLock WHERE key_value='"+key+"'" ;
		List<HlbAccessLock> rs = selectQuery(sql);
		
		if(rs.size()>0)
		{
			for(HlbAccessLock accLock:rs)
			{
				long duration = currentDate.getTime() - accLock.getAccessDate().getTime();
				SimpleDateFormat dt = new SimpleDateFormat( MiBConstant.DB_DATETIME_FORMAT);
				String currentDB =dt.format(accLock.getAccessDate());
				
				if(duration>accLock.getIdleTimeSecond())
				{


				}
				else
				{
					accLock.setAccessDate(currentDate);
					updateEntity(accLock);
					
					return accLock.getData();
				}
			}
		}
		
		
		
		return null;
	}
	
	public List selectQuery(String JQL)
	{
		EntityManager em  = getEntityManager();
		return em.createQuery(JQL).getResultList();
	}

	@Override
	public EntityManager getEntityManager() {
		return emx;
	}
}
