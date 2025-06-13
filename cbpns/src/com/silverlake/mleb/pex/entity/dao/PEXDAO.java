package com.silverlake.mleb.pex.entity.dao;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silverlake.hlb.mib.constant.MiBConstant;
import com.silverlake.mleb.pex.constant.PExConstant;
import com.silverlake.mleb.pex.entity.AccessLock;
import com.silverlake.mleb.pex.entity.PexProcessTranx;


@Service
@Repository
public class PEXDAO extends DAO
{
	
	private static Logger log = LogManager.getLogger(PEXDAO.class);
	@PersistenceContext(unitName="db_mleb")
	EntityManager em ;


	@Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}
	
	
	
	@Transactional
	public String[] updatePExStatus(String pexRefNo, String earmarkRefNo, String status, Date collectionDate)
	{
		
		//[0] statusCode
		//[1] pex status
		String [] result = new String[2];
		String sqlPexTranx = "From HlbPexProcessTranx " +
				"WHERE ref_no = :ref_no " +
				"AND rbs_hold_ref = :rbs_hold_ref " ;
		log.debug("Update Check PEx SQL : "+sqlPexTranx);
		
		Hashtable params02 = new Hashtable();
		params02.put("ref_no", pexRefNo);
		params02.put("rbs_hold_ref", earmarkRefNo);
		
		List<PexProcessTranx> pexTranxs = selectQueryParam(sqlPexTranx,params02);
		
		if(pexTranxs.size() == 1)
		{
			PexProcessTranx pexTranx = pexTranxs.get(0);
			
			if(pexTranx.getStatus().equalsIgnoreCase(PExConstant.PEX_TRANSACTION_STATUS_PROCESSING) || pexTranx.getStatus().equalsIgnoreCase(PExConstant.PEX_TRANSACTION_STATUS_ACCEPTED))
			{
				if(status.trim().equalsIgnoreCase("001"))
				{
					//try {Thread.sleep(3000);} catch (InterruptedException e) {}
					pexTranx.setCollectionDate(collectionDate);
					pexTranx.setUpdateIBFlag(PExConstant.PEX_IB_FLAG_PENDING_UPDATED);
					pexTranx.setStatus(PExConstant.PEX_TRANSACTION_STATUS_PAID);
				}
				else if(status.trim().equalsIgnoreCase("002"))
				{
					//002 - ATM insufficient cash - OCM will do reverse (Earmark Back amount)
					//mobility will revert status to ACTIVE, customer can collect again
					pexTranx.setStatus(PExConstant.PEX_TRANSACTION_STATUS_ACTIVE);
					
					
				}
				else
				{
					
					pexTranx.setCollectionDate(collectionDate);
					pexTranx.setErrorCode(status.trim());
					pexTranx.setUpdateIBFlag(PExConstant.PEX_IB_FLAG_PENDING_UPDATED);
					pexTranx.setStatus(PExConstant.PEX_TRANSACTION_STATUS_FAILED);
					
				}
				updateEntity(pexTranx);
				result[0] = PExConstant.PEX_ERR_COMMON_SUCCESS;
				result[1] = pexTranx.getStatus().equalsIgnoreCase(PExConstant.PEX_TRANSACTION_STATUS_PAID)?PExConstant.PEX_STATUS_TRANSACTION_SUCCESS:PExConstant.PEX_STATUS_TRANSACTION_FAILED;
				//return success
				log.debug("OCM update pex status success");
			}
			else
			{
				result[0] = PExConstant.PEX_ERR_INVALID_PEX_STATUS_TYPE;
				//return invalid status type
				log.debug("OCM update failed pex status invalid");
			}
			
		}
		else
		{
			log.debug("OCM update failed pex not found");
			//return no transaction record
			
			result[0] = PExConstant.PEX_ERR_NO_TRANSACTION_RECORD;
		}
		
		
		return result;
		
	}
	
	
	
	@Transactional
	public boolean putIfAbsent(String key,String data, long idle)
	{
			Date currentDate = new Date();
			String sql = "FROM HlbAccessLock WHERE key_value='"+key+"'" ;
			List<AccessLock> rs = selectQuery(sql);
	
			if(rs.size()>0)
			{
				for(AccessLock accLock:rs)
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
						accLock = (AccessLock) updateEntity(accLock);
				
						return true;

					
					}
				}
			}
			else
			{
				AccessLock lock = new AccessLock();
				
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
		List<AccessLock> rs = selectQuery(sql);
		
		if(rs.size()>0)
		{
			for(AccessLock accLock:rs)
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
	
	
	@Transactional
	public boolean removeLockData(String key)
	{
		
		String sql = "FROM HlbAccessLock WHERE key_value='"+key+"'" ;
		List<AccessLock> rs = selectQuery(sql);
		
		if(rs.size()>0)
		{
			for(AccessLock accLock:rs)
			{
				deleteEntity(accLock);
				return true;
			}
		}
		return false;
	}
	
	
	@Transactional
	public String[] updatePExStatus(String pexRefNo, String earmarkRefNo, String status, Date collectionDate,String atmTerminalID,String collectionTranxCode, String CollectionSeq)
	{
		
		//[0] statusCode
		//[1] pex status
		String [] result = new String[2];
		String sqlPexTranx = "From HlbPexProcessTranx " +
				"WHERE ref_no = :ref_no " +
				"AND rbs_hold_ref = :rbs_hold_ref " ;
		log.debug("Update Check PEx SQL : "+sqlPexTranx);
		
		Hashtable params02 = new Hashtable();
		params02.put("ref_no", pexRefNo);
		params02.put("rbs_hold_ref", earmarkRefNo);
		
		List<PexProcessTranx> pexTranxs = selectQueryParam(sqlPexTranx,params02);
		
		if(pexTranxs.size() == 1)
		{
			PexProcessTranx pexTranx = pexTranxs.get(0);
			
			
			if(!pexTranx.getAtmTerminalId().trim().equalsIgnoreCase(atmTerminalID.trim()))
			{
				result[0] = PExConstant.PEX_ERR_INVALID_ATM_TERMINALID;
				//return invalid status type
				log.debug("OCM update failed pex terminalID not match");
			}
			else if(pexTranx.getStatus().equalsIgnoreCase(PExConstant.PEX_TRANSACTION_STATUS_PROCESSING) || pexTranx.getStatus().equalsIgnoreCase(PExConstant.PEX_TRANSACTION_STATUS_ACCEPTED))
			{
				if(status.trim().equalsIgnoreCase("001"))
				{
					//success disburse money
					pexTranx.setCollectionDate(collectionDate);
					pexTranx.setUpdateIBFlag(PExConstant.PEX_IB_FLAG_PENDING_UPDATED);
					pexTranx.setStatus(PExConstant.PEX_TRANSACTION_STATUS_PAID);
					
					
				}
				else if(status.trim().equalsIgnoreCase("002"))
				{
					//002 - ATM insufficient cash - OCM will do reverse (Earmark Back amount)
					//mobility will revert status to ACTIVE, customer can collect again
					pexTranx.setStatus(PExConstant.PEX_TRANSACTION_STATUS_ACTIVE);
					
					
				}
				else
				{
					
					pexTranx.setCollectionDate(collectionDate);
					pexTranx.setErrorCode(status.trim());
					pexTranx.setErrorMessage(status.trim());
					pexTranx.setUpdateIBFlag(PExConstant.PEX_IB_FLAG_PENDING_UPDATED);
					pexTranx.setStatus(PExConstant.PEX_TRANSACTION_STATUS_FAILED);
					
				}
				
				//capture rbs dispense money transaction code and sequence number
				String  cseq = null==CollectionSeq?"0":CollectionSeq;
				cseq = String.valueOf(Integer.parseInt(cseq));
				pexTranx.setEaiCorrelationId((null==collectionTranxCode?"-":collectionTranxCode.trim())+";"+cseq);
				
				
				updateEntity(pexTranx);
				result[0] = PExConstant.PEX_ERR_COMMON_SUCCESS;
				result[1] = pexTranx.getStatus().equalsIgnoreCase(PExConstant.PEX_TRANSACTION_STATUS_PAID)?PExConstant.PEX_STATUS_TRANSACTION_SUCCESS:PExConstant.PEX_STATUS_TRANSACTION_FAILED;
				//return success
				log.debug("OCM update pex status success");
			}
			else
			{
				result[0] = PExConstant.PEX_ERR_INVALID_PEX_STATUS_TYPE;
				//return invalid status type
				log.debug("OCM update failed pex status invalid");
			}
			
		}
		else
		{
			log.debug("OCM update failed pex not found");
			//return no transaction record
			
			result[0] = PExConstant.PEX_ERR_NO_TRANSACTION_RECORD;
		}
		
		
		return result;
		
	}
	
	
}