package com.silverlake.mleb.pex.module.services;

// Generated May 22, 2013 6:08:42 PM by Hibernate Tools 3.4.0.CR1

import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

import javax.persistence.PersistenceException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.codec.binary.Hex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.fuzion.ws.account.endpoint.AccountVO;
import com.fuzion.ws.security.endpoint.EndpointResponse;
import com.fuzion.ws.security.endpoint.SendNotificationForPreLoginRequest;
import com.silverlake.hlb.mib.bean.ObAccountBean;
import com.silverlake.hlb.mib.bean.ObUserContext;
import com.silverlake.hlb.mib.constant.MiBConstant;
import com.silverlake.hlb.mib.entity.HlbCustomerAccOrder;
import com.silverlake.hlb.mib.entity.HlbCustomerProfile;
import com.silverlake.hlb.mib.entity.HlbGeneralCode;
import com.silverlake.mleb.pex.bean.ObPexUserDetails;
import com.silverlake.mleb.pex.constant.PExConstant;
import com.silverlake.mleb.pex.entity.BankRoute;
import com.silverlake.mleb.pex.entity.MessageMap;
import com.silverlake.mleb.pex.entity.PexAccount;
import com.silverlake.mleb.pex.entity.PexCharges;
import com.silverlake.mleb.pex.entity.PexCollectionCode;
import com.silverlake.mleb.pex.entity.PexConf;
import com.silverlake.mleb.pex.entity.PexDailySeqNum;
import com.silverlake.mleb.pex.entity.PexProcessTranx;
import com.silverlake.mleb.pex.entity.PexSystemDailySeqNum;
import com.silverlake.mleb.pex.entity.QuickPexLimit;
import com.silverlake.mleb.pex.entity.dao.MLEBPExDAO;
import com.silverlake.mleb.pex.module.func.PerformCreationPex;
import com.silverlake.mleb.pex.module.ib.securityServices.SecuirtyService;
import com.silverlake.mleb.pex.util.EncryptionServices;
import com.silverlake.mleb.pex.util.MessageManager;
import com.silverlake.mleb.pex.util.PropertiesManager;
import com.silverlake.mleb.pex.util.StringDataUtil;

import org.apache.commons.codec.binary.Base64;

public class PExServices
{
	MLEBPExDAO dao;
	private static Logger log = LogManager.getLogger(PExServices.class);
	private PropertiesManager property = new PropertiesManager();
	private MessageManager msgPro = new MessageManager();
	public PExServices(MLEBPExDAO dao)
	{
		this.dao =  dao;
	}
	
	
	public PexAccount updatePExAccount(ObPexUserDetails pexUser, String mlebTranxID, String locale)
	{
		
		
		List<PexAccount> pexUserAllcc = getDirectPexUserAllAcc(pexUser.getCif());
		
		Date tranDate = new Date();
		
		if(pexUserAllcc.size()>0)
		{
			//update existing pex user
			for(PexAccount user: pexUserAllcc)
			{
				if(user.getMobileNo().equalsIgnoreCase(pexUser.getMobileNumber()))
				{
					if(null!=pexUser.getPexAccount())
					{
						if(!user.getStatus().equalsIgnoreCase(PExConstant.PEX_ACC_STATUS_ACTIVE))
						{
							disableCurrentActivePExAcc(pexUser.getCif(),mlebTranxID);
							user.setCreationDate(tranDate);
						}
						user.setAccountName(pexUser.getPexAccount().getAccountName());
						user.setAccountDesc(pexUser.getPexAccount().getAccountDescription());
						user.setAccountNumber(pexUser.getPexAccount().getAccountNumber());
						user.setAccountStatus(pexUser.getPexAccount().getProductStatusCode());
						user.setAccountType(pexUser.getPexAccount().getAccountDescription());
						user.setAccountProductType(pexUser.getPexAccount().getAccountTypeCode());
						

						user.setStatus(PExConstant.PEX_ACC_STATUS_ACTIVE);
						
					}
					else if(user.getStatus().equalsIgnoreCase(PExConstant.PEX_ACC_STATUS_ACTIVE))
					{

						user.setStatus(PExConstant.PEX_ACC_STATUS_DISABLED);

						
					}
					else
					{
						disableCurrentActivePExAcc(pexUser.getCif(),mlebTranxID);
					}
				
					user.setMlebTranxID(mlebTranxID);
					user.setUpdateDate(tranDate);
					dao.updateEntity(user);
					return user;
					
					
					
				
					
				}
			}
			
		
			
		}
		
		//add new pex user
		if(null!=pexUser.getPexAccount())
		{
		
			PexAccount user = new PexAccount();
			user.setCif(pexUser.getCif());
			
			
			
			user.setMobileNo(pexUser.getMobileNumber());
			user.setCreationDate(new Date());
			user.setAccountName(pexUser.getPexAccount().getAccountName());
			user.setAccountDesc(pexUser.getPexAccount().getAccountDescription());
			user.setAccountNumber(pexUser.getPexAccount().getAccountNumber());
			user.setAccountStatus(pexUser.getPexAccount().getProductStatusCode());
			user.setAccountType(pexUser.getPexAccount().getAccountDescription());
			user.setAccountProductType(pexUser.getPexAccount().getAccountTypeCode());
			user.setMlebTranxID(mlebTranxID);
			user.setStatus(PExConstant.PEX_ACC_STATUS_ACTIVE);
			user.setAccountCurrency(pexUser.getPexAccount().getCurrencyCode());
			int rs = addPexActiveAcc(user);
			if(rs == 1 )
			{
				return user;
			}
		}
		
		return null;
	}
	
	
	
	
	
	
	
	public boolean checkPExAccount(ObPexUserDetails pexUser, String mlebTranxID)
	{
		
		
		List<PexAccount> pexUserAllcc = getDirectPexUserAllAcc(pexUser.getCif());
		
		
		
		if(pexUserAllcc.size()>0)
		{
			//update existing pex user
			for(PexAccount user: pexUserAllcc)
			{
				if(user.getMobileNo().equalsIgnoreCase(pexUser.getMobileNumber()))
				{
					
					
					return true;
				
					
				}
			}
			
		
			
		}
		
		return false;
	}
	

	public List<PexAccount> getDirectPexUserActiveAcc(String CIF, String mobileNumber,List<ObAccountBean> fromAccList)
	{
		String sql = "FROM HlbPexAccount WHERE cif = :cif AND status = '"+PExConstant.PEX_ACC_STATUS_ACTIVE+"'" ;
		Hashtable params = new Hashtable();
		params.put("cif", CIF);
		
		
		List<PexAccount> pexAccounts =  dao.selectQueryParam(sql, params);
		
		
		if(pexAccounts.size()>0 && !pexAccounts.get(0).getMobileNo().equalsIgnoreCase(mobileNumber))
		{
			//inactive acc
			pexAccounts.get(0).setStatus(PExConstant.PEX_ACC_STATUS_DISABLED);
			dao.updateEntity(pexAccounts.get(0));
			pexAccounts.remove(0);
		}
		boolean checkExisAcc = false;
		for(ObAccountBean obAcc : fromAccList)
		{
			if(pexAccounts.size()>0 && obAcc.getAccountNumber().equalsIgnoreCase(pexAccounts.get(0).getAccountNumber()))
			{
				checkExisAcc = true;
				if(syncAccName(pexAccounts.get(0).getAccountName(),obAcc.getAccountName()))
				{
					pexAccounts.get(0).setAccountName(obAcc.getAccountName());
					dao.updateEntity(pexAccounts.get(0));
				}
			}
		}
		
		if(!checkExisAcc && pexAccounts.size()>0 )
		{
			//inactive acc
			pexAccounts.get(0).setStatus(PExConstant.PEX_ACC_STATUS_DISABLED);
			dao.updateEntity(pexAccounts.get(0));
			pexAccounts.remove(0);
		}
		
		
		
		
		return pexAccounts;
	}
	
	
	
	public boolean syncAccName(String pexAcc, String newAcc)
	{
		pexAcc = null== pexAcc?"": pexAcc;
		
		newAcc = null== newAcc?"": newAcc;
		
		if(!newAcc.trim().equalsIgnoreCase(pexAcc.trim()))
		{
			return false;
		}

		
		return true;
	}
	
	
	
	public List<PexAccount> getDirectPexMobileActiveAcc(String mobileNumber, String countryCode)
	{
		String sql = "FROM HlbPexAccount WHERE (mobile_no = :mobile OR mobile_no = :mobilex) AND status = '"+PExConstant.PEX_ACC_STATUS_ACTIVE+"'" ;
		Hashtable params = new Hashtable();
		
		params.put("mobile", mobileNumber);
		
		if(countryCode.equalsIgnoreCase(PExConstant.PEX_COUNTRY_CODE_KH))
		{
			mobileNumber = mobileNumber.substring(3);
		}
		
		
		params.put("mobilex", mobileNumber);
		
		
		
		
		List<PexAccount> pexAccounts =  new ArrayList();
		//take xxx-yyyyy
		StringDataUtil stringDataUtil = new StringDataUtil();
		mobileNumber = stringDataUtil.numberFiltering(mobileNumber); 
				
		String[] prefix = {"855"};
		String mobileN = ""; 
		log.info("CHECK MOBILE PEX DIRECT :::: "+mobileNumber);
		
		for(int i=0 ; i<prefix.length; i++)
		{
			
			mobileN = prefix[i]+mobileNumber; 
			
			params.put("mobile", mobileN);
			pexAccounts = dao.selectQueryParam(sql,params);
			if(pexAccounts.size()>0)
			{
				return pexAccounts;
			}
		}
		
		
		
		
		
		//List<HlbPexAccount> pexAccounts =  dao.selectQueryParam(sql, params);

		
		return pexAccounts;
	}
	
	
	public List<PexAccount> getDirectPexUserAllAcc(String CIF)
	{
		String sql = "FROM HlbPexAccount WHERE cif = :cif " ;
		Hashtable params = new Hashtable();
		params.put("cif", CIF);
		
		
		List<PexAccount> pexAccounts =  dao.selectQueryParam(sql, params);
		
		return pexAccounts;
	}
	
	
	public List<PexAccount> getDirectPexUserActiveMobile(String mobile)
	{
		String sql = "FROM HlbPexAccount WHERE mobile_no=:mobile AND status = '"+PExConstant.PEX_ACC_STATUS_ACTIVE+"' " ;
		Hashtable params = new Hashtable();
		params.put("mobile", mobile);
		
		
		
		List<PexAccount> pexAccounts =  dao.selectQueryParam(sql,params);
		
		return pexAccounts;
	}
	
	
	public int disableCurrentActivePExAcc(String CIF, String mlebTranxID)
	{
		SimpleDateFormat tranxDTFormat = new SimpleDateFormat(PExConstant.COMMON_DATETIME_FORMAT);
		String sql = "UPDATE HlbPexAccount SET status = '"+PExConstant.PEX_ACC_STATUS_DISABLED+"', update_date = '"+tranxDTFormat.format(new Date())+"', mleb_tranx_id = '"+mlebTranxID+"'  WHERE cif = :cif AND status = '"+PExConstant.PEX_ACC_STATUS_ACTIVE+"' ";
		Hashtable params = new Hashtable();
		params.put("cif", CIF);
		
		return dao.updateSQLParam(sql,params);
	}
	
	
	public int addPexActiveAcc(PexAccount pexAccount)
	{
		if(pexAccount.getStatus().equalsIgnoreCase(PExConstant.PEX_ACC_STATUS_ACTIVE))
		{
			try
			{
				
				dao.insertEntity(pexAccount);
				return 1;
			}
			catch(PersistenceException  ex)
			{
				
				if(ex.getCause()!= null && ex.getCause().getClass().equals(ConstraintViolationException.class) )
				{
					String updateSQL = "DELETE FROM HlbPexAccount WHERE mobile_no = :mobile AND status = '"+PExConstant.PEX_ACC_STATUS_DISABLED+"'";
					Hashtable params = new Hashtable();
					params.put("mobile", pexAccount.getMobileNo());
					int rsUpdate = dao.updateSQLParam(updateSQL, params);
				
					if(rsUpdate == 1)
					{
						addPexActiveAcc(pexAccount);
					}
				}
				else
				{
					log.info("Error Insert PEx Acc ", ex);
				}
			}
			catch(Exception  e)
			{
				log.info("Error Insert PEx Acc ", e);
			}
			
		}
		
		
		return 0;
	}
	
	
	public List<String> getATMDenomination(BigDecimal transactionLimit)
	{
		
		String sql = "FROM HlbPexConf WHERE status = '"+PExConstant.PEX_CONF_STATUS_ACTIVE+"'";
		
		List<PexConf> pexConfs = dao.selectQuery(sql);
		
		
		if(pexConfs.size()>0)
		{
			BigDecimal atmDenomination = pexConfs.get(0).getAtmDenomination();
			BigDecimal tranxLimit = transactionLimit;
			
			int denominationCount = tranxLimit.divide(atmDenomination).toBigInteger().intValue();
			List<String> denominationSet = new ArrayList();
			for(int i=1;i<=denominationCount;i++)
			{
				denominationSet.add(atmDenomination.multiply(new BigDecimal(i)).toPlainString());
			}
			return denominationSet;
		}
		
		
		return null;
	}
	
	
	
	public PexConf getPExConf()
	{
		String sql = "FROM HlbPexConf WHERE status = '"+PExConstant.PEX_CONF_STATUS_ACTIVE+"'";
		
		List<PexConf> pexConfs = dao.selectQuery(sql);
		
		
		if(pexConfs.size()>0)
		{
			return pexConfs.get(0);
		}
		
		return null;
	}
	
	
	public BankRoute getPExBeneficiaryBankCode(String bankCode)
	{
		String sql = "FROM HlbBankRoute WHERE bank_code = :bankCode AND status = '"+PExConstant.PEX_BANK_ROUTE_STATUS_ACTIVE+"'";
		Hashtable param = new Hashtable();
		
		param.put("bankCode", bankCode);
		
		
		List<BankRoute> pexRoute = dao.selectQueryParam(sql,param);
		
		
		if(pexRoute.size()>0)
		{
			return pexRoute.get(0);
		}
		
		return null;
	}
	
	
	
	
	
	
	public String getSocketStatusMessage(String statusCode , String locale)
	{
		String sql = "FROM HlbMessageMap WHERE status_code = :status_code AND locale= :locale AND service = '"+PExConstant.PEX_MSG_SERVICE+"' type = '"+PExConstant.PEX_MSG_SOCKET_TYPE+"' ";
		Hashtable params = new Hashtable();
		params.put("status_code", statusCode);
		params.put("locale", locale);
		
		List<MessageMap>  messages = dao.selectQueryParam(sql,params);
		
		if(messages.size()>0)
		{
			return messages.get(0).getStatusMessage();
		}
		else
		{
			return null;
		}
		
	}
	
	public String getSMSStatusMessage(String statusCode , String locale)
	{
		String sql = "FROM HlbMessageMap WHERE status_code = :status_code AND locale= :locale AND service = '"+PExConstant.PEX_MSG_SERVICE+"' type = '"+PExConstant.PEX_MSG_SMS_TYPE+"' ";
		Hashtable params = new Hashtable();
		params.put("status_code", statusCode);
		params.put("locale", locale);
		List<MessageMap>  messages = dao.selectQueryParam(sql,params);
		
		if(messages.size()>0)
		{
			return messages.get(0).getStatusMessage();
		}
		else
		{
			return null;
		}
		
	}
	
	
	public boolean validateCIFDailyPExLimit(String cif, BigDecimal globalDailyLimit, Date transactionDate, BigDecimal tranxPexAmt)
	{
		SimpleDateFormat dbFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dayFormatFrom = dbFormat.format(transactionDate)+" 00:00:00";
		String dayFormatTo = dbFormat.format(transactionDate)+" 23:59:59";
		String getDailyCIFPExTransaction = "FROM HlbPexProcessTranx WHERE cif = :cif AND creation_date BETWEEN '"+dayFormatFrom+"' AND '"+dayFormatTo+"'";
		Hashtable params = new Hashtable();
		params.put("cif", cif);
		List<PexProcessTranx> dailyPExTrans = dao.selectQueryParam(getDailyCIFPExTransaction,params);
		
		
		BigDecimal PExAmount = tranxPexAmt;
		
		
		for(PexProcessTranx pexTranx : dailyPExTrans)
		{
			if(pexTranx.getStatus().equalsIgnoreCase(PExConstant.PEX_TRANSACTION_STATUS_ACTIVE))
			{
				PExAmount = PExAmount.add(pexTranx.getAmountPex());
			}
			else if(pexTranx.getStatus().equalsIgnoreCase(PExConstant.PEX_TRANSACTION_STATUS_PAID))
			{
				PExAmount = PExAmount.add(pexTranx.getAmountPex());
			}
			else if(pexTranx.getStatus().equalsIgnoreCase(PExConstant.PEX_TRANSACTION_STATUS_PROCESSING))
			{
				PExAmount = PExAmount.add(pexTranx.getAmountPex());
			}
			
			
		}
		
		
		if(globalDailyLimit.compareTo(PExAmount) > 0)
		{
			return true;
		}
		
		
		
		return false;
	}
	
	

	
	
	
	
	public int updateSequenceNum(Date transactionDate)
	{
		int rs = 0;
		SimpleDateFormat refFormat = new SimpleDateFormat("yyyyMMdd");
		String sql = "FROM HlbPexDailySeqNum WHERE type = '"+PExConstant.PEX_SEQ_NUM_TYPE+"_"+refFormat.format(transactionDate)+"'";
		
		List<PexDailySeqNum> sData = dao.selectQueryLimited(sql,1);
		
		PexDailySeqNum dailySeqNum = new PexDailySeqNum();
		if(sData.size() == 0)
		{
			rs = 1;
			dailySeqNum.setSequenceNum(rs);
			dailySeqNum.setUpdateDate(transactionDate);
			dailySeqNum.setType(PExConstant.PEX_SEQ_NUM_TYPE+"_"+refFormat.format(transactionDate));
			try
			{
				dao.insertEntity(dailySeqNum);
				
			}catch(PersistenceException  ex)
			{
				if(ex.getCause()!= null && ex.getCause().getClass().equals(ConstraintViolationException.class))
				{
					return updateSequenceNum(transactionDate);
				}
			}
		}
		else
		{
			
			SimpleDateFormat dbFormat = new SimpleDateFormat(PExConstant.COMMON_DATETIME_FORMAT);
			PexDailySeqNum seqNumData = sData.get(0);
			String dbDate = refFormat.format(seqNumData.getUpdateDate());
			String currentDate = refFormat.format(transactionDate);
			int latestCount = 0;
			if(currentDate.equalsIgnoreCase(dbDate))
			{
				latestCount = seqNumData.getSequenceNum()+1;
			}
			else
			{
				latestCount = 1;
			}
			
			
			
			//String updateSQL = "UPDATE HlbPexDailySeqNum SET sequence_num = "+latestCount+" , update_date = '"+dbFormat.format(transactionDate)+"' WHERE sequence_num = "+seqNumData.getSequenceNum()+" AND update_date = '"+dbFormat.format(seqNumData.getUpdateDate())+ "' AND type = '"+PExConstant.PEX_SEQ_NUM_TYPE+"_"+refFormat.format(transactionDate)+"'" ;
			
			String updateSQL = "UPDATE HlbPexDailySeqNum SET sequence_num = "+latestCount+" , update_date = '"+dbFormat.format(transactionDate)+"' WHERE sequence_num = "+seqNumData.getSequenceNum()+" AND row_id="+seqNumData.getRowId()+"";
			
			//System.out.println(updateSQL);
		
			int rsSQL = dao.updateSQL(updateSQL);
			
			//System.out.println(rsSQL);
			
			if(rsSQL == 0 )
			{
				return updateSequenceNum(transactionDate);
			}
			else
			{
				rs = latestCount;
			}
			//rs = latestCount;
			
		}
		
		
		return rs;
	}
	
	
	
	
	
	
	public String getSystemSequenceNum(Date transactionDate)
	{
		int rs = 0;
		SimpleDateFormat refFormat = new SimpleDateFormat("yyyyMMdd");
		String sql = "FROM HlbPexSystemDailySeqNum WHERE type = '"+PExConstant.PEX_SYS_SEQ_NUM_TYPE+"_"+refFormat.format(transactionDate)+"'";
		
		List<PexSystemDailySeqNum> sData = dao.selectQueryLimited(sql,1);
		
		PexSystemDailySeqNum dailySeqNum = new PexSystemDailySeqNum();
		if(sData.size() == 0)
		{
			rs = 1;
			dailySeqNum.setSequenceNum(rs);
			dailySeqNum.setUpdateDate(transactionDate);
			dailySeqNum.setType(PExConstant.PEX_SYS_SEQ_NUM_TYPE+"_"+refFormat.format(transactionDate));
			try
			{
				dao.insertEntity(dailySeqNum);
				
			}catch(PersistenceException  ex)
			{
				if(ex.getCause()!= null && ex.getCause().getClass().equals(ConstraintViolationException.class))
				{
					return getSystemSequenceNum(transactionDate);
				}
			}
		}
		else
		{
			
			SimpleDateFormat dbFormat = new SimpleDateFormat(PExConstant.COMMON_DATETIME_FORMAT);
			PexSystemDailySeqNum seqNumData = sData.get(0);
			String dbDate = refFormat.format(seqNumData.getUpdateDate());
			String currentDate = refFormat.format(transactionDate);
			int latestCount = 0;
			if(currentDate.equalsIgnoreCase(dbDate))
			{
				latestCount = seqNumData.getSequenceNum()+1;
			}
			else
			{
				latestCount = 1;
			}
			
			
			
			//String updateSQL = "UPDATE HlbPexDailySeqNum SET sequence_num = "+latestCount+" , update_date = '"+dbFormat.format(transactionDate)+"' WHERE sequence_num = "+seqNumData.getSequenceNum()+" AND update_date = '"+dbFormat.format(seqNumData.getUpdateDate())+ "' AND type = '"+PExConstant.PEX_SEQ_NUM_TYPE+"_"+refFormat.format(transactionDate)+"'" ;
			
			String updateSQL = "UPDATE HlbPexSystemDailySeqNum SET sequence_num = "+latestCount+" , update_date = '"+dbFormat.format(transactionDate)+"' WHERE sequence_num = "+seqNumData.getSequenceNum()+" AND row_id="+seqNumData.getRowId()+"";
			
			//System.out.println(updateSQL);
		
			int rsSQL = dao.updateSQL(updateSQL);
			
			//System.out.println(rsSQL);
			
			if(rsSQL == 0 )
			{
				return getSystemSequenceNum(transactionDate);
			}
			else
			{
				rs = latestCount;
			}
			//rs = latestCount;
			
		}
		
		
		return PExConstant.PEX_SYS_SEQ_NUM_TYPE+refFormat.format(transactionDate)+rs;
		
		
	}
	
	
	
	/*public int updateSequenceTypeNum(String type,Date transactionDate)
	{
		int rs = 0;
		//SimpleDateFormat refFormat = new SimpleDateFormat("yyyyMMdd");
		String sql = "FROM HlbPexSeqNum WHERE type = '"+type+"'";
		
		List<HlbPexDailySeqNum> sData = dao.selectQueryLimited(sql,1);
		
		HlbPexDailySeqNum dailySeqNum = new HlbPexDailySeqNum();
		if(sData.size() == 0)
		{
			rs = 1;
			dailySeqNum.setSequenceNum(rs);
			dailySeqNum.setUpdateDate(transactionDate);
			dailySeqNum.setType(type);
			try
			{
				dao.insertEntity(dailySeqNum);
				
			}catch(PersistenceException  ex)
			{
				if(ex.getCause()!= null && ex.getCause().getClass().equals(ConstraintViolationException.class))
				{
					return updateSequenceNum(transactionDate);
				}
			}
		}
		else
		{
			
			SimpleDateFormat dbFormat = new SimpleDateFormat(PExConstant.COMMON_DATETIME_FORMAT);
			HlbPexDailySeqNum seqNumData = sData.get(0);
		
			int latestCount = seqNumData.getSequenceNum()+1;

			//String updateSQL = "UPDATE HlbPexDailySeqNum SET sequence_num = "+latestCount+" , update_date = '"+dbFormat.format(transactionDate)+"' WHERE sequence_num = "+seqNumData.getSequenceNum()+" AND update_date = '"+dbFormat.format(seqNumData.getUpdateDate())+ "' AND type = '"+PExConstant.PEX_SEQ_NUM_TYPE+"_"+refFormat.format(transactionDate)+"'" ;
			
			String updateSQL = "UPDATE HlbPexSeqNum SET sequence_num = "+latestCount+" , update_date = '"+dbFormat.format(transactionDate)+"' WHERE sequence_num = "+seqNumData.getSequenceNum()+" AND row_id="+seqNumData.getRowId()+"";
			
			//System.out.println(updateSQL);
		
			int rsSQL = dao.updateSQL(updateSQL);
			
			//System.out.println(rsSQL);
			
			if(rsSQL == 0 )
			{
				return updateSequenceNum(transactionDate);
			}
			else
			{
				rs = latestCount;
			}
			//rs = latestCount;
			
		}
		
		
		return rs;
	}*/
	
	
	
	
	
	
	public String genCollectionCode(int collectionCode_length)
	{
		Random rnd = new Random();
		StringDataUtil stringUtil = new StringDataUtil();
		int collectionNumber = rnd.nextInt((int) Math.pow(10, collectionCode_length));
		String collectionCode = stringUtil.getFulllength_frontBlank(collectionCode_length, collectionNumber+"", "0");
		return collectionCode;
	}
	
	
	public String genPExRefNO(Date transactionDate,String runningNumber)
	{
		SimpleDateFormat refFormat = new SimpleDateFormat(PExConstant.PEX_REF_DATE_FORMAT);
		//return PExConstant.ref_prefix+refFormat.format(transactionDate)+TimeRefUtil.genTimeRef(2);
		return PExConstant.ref_prefix+refFormat.format(transactionDate)+"-"+runningNumber;
	}
	
	
	public String genPin(int pin_length)
	{
		Random rnd = new Random();
		StringDataUtil stringUtil = new StringDataUtil();
		int collectionNumber = rnd.nextInt((int) Math.pow(10, pin_length));
		String pin = stringUtil.getFulllength_frontBlank(pin_length, collectionNumber+"", "0");
		
		return pin;
	}
	
	
	
	
	public Date getExpiryDate(int expPeriod, String extime, Date creationDate) throws Exception
	{
		
		String ref_date_format = "yyMMdd";
		String ref_dateTime_format = "yyMMdd HH:mm";
		SimpleDateFormat refFormat = new SimpleDateFormat(ref_date_format);
		SimpleDateFormat refDateTImeFormat = new SimpleDateFormat(ref_dateTime_format);
		
		long expCountTime = (expPeriod+1)*1*(24)*(60*60*1000);
		String expDateTime = refFormat.format(creationDate)+" "+extime;
		Date newExpDate = refDateTImeFormat.parse(expDateTime);
		expCountTime = newExpDate.getTime()+expCountTime;
		newExpDate = new Date(expCountTime);
		
		return newExpDate;
		
	}
	
	
	
	public BigDecimal getCharges(int pexChargesID, BigDecimal pexAmount)
	{
		PexCharges charges = (PexCharges) dao.findByID(PexCharges.class, pexChargesID);
		
		if(null!=charges.getFlatCharges())
		{
			return charges.getFlatCharges();
		}
		else
		{
			BigDecimal hundred = new BigDecimal(100);
			BigDecimal percentage = charges.getPercentageCharges();
			BigDecimal percentageFactor = percentage.divide(hundred,2, BigDecimal.ROUND_HALF_UP);
			
			BigDecimal pexAmountCharges = pexAmount.multiply(percentageFactor);
			
			if(pexAmountCharges.compareTo(charges.getMinimumCharges())>0)
			{
				return pexAmountCharges;
			}
			else
			{
				return charges.getMinimumCharges();
			}
			
		}
		
	}
	
	
	
	
	public PexProcessTranx getPExTransactionByRef( String ref_no)
	{
		
		
		String sql = "FROM HlbPexProcessTranx WHERE ref_no = :ref_no ";
		Hashtable params = new Hashtable();
		params.put("ref_no", ref_no);
		List<PexProcessTranx> pexTranxList = dao.selectQueryParam(sql,params);
		
		return pexTranxList.get(0);
	}
	
	
	public List<PexProcessTranx> getPExTransactionByTypes(String[] types, String cif, int lastXday)
	{
		Date dateNow = new Date();
		
		Calendar cal = Calendar.getInstance();
        cal.setTime(dateNow);
        cal.add(Calendar.DATE, -lastXday); 
        dateNow =  cal.getTime();
		
		SimpleDateFormat dbDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		String day = dbDateFormat.format(dateNow);
		
		
		
		
		
		
		String sql = "FROM HlbPexProcessTranx WHERE cif = :cif AND creation_date > '"+day+" 23:59:59' AND (";
		for(String type : types)
		{
			sql = sql + " status = '"+type+"' OR ";
		}
		
		sql = sql.substring(0, sql.length()-3)+")";
		
		//System.out.println("HIST ::::::::: "+sql);		
		Hashtable params = new Hashtable();
		params.put("cif", cif);
		List<PexProcessTranx> pexTranxList = dao.selectQueryParam(sql,params);
		
		return pexTranxList;
	}
	

	public int updatePexExpiration(PexProcessTranx pexTranx)
	{
		
		int expirationDay = pexTranx.getExpiration_period();
		Date expiryDate = pexTranx.getExpiredDate();
		
		
		Date currentDate = new Date();
		currentDate = checkRBSDate(currentDate);
		log.info(pexTranx.getRefNo()+" :: "+(currentDate.getTime() > expiryDate.getTime()));
		
		SimpleDateFormat dbDateFormat = new SimpleDateFormat(PExConstant.COMMON_DATETIME_FORMAT);
		if(currentDate.getTime() > expiryDate.getTime())
		{
			
			
			
			String updateSQL = "UPDATE HlbPexProcessTranx SET status = '"+PExConstant.PEX_TRANSACTION_STATUS_EXPIRED+"' WHERE status = '"+PExConstant.PEX_TRANSACTION_STATUS_ACTIVE+"' AND row_id = "+pexTranx.getRowId()+" ";
			return dao.updateSQL(updateSQL);
		
			
			
		}
		
		return 0;
	}
	
	
	
	
	public void activeEffectiveCharges(Date today)
	{
		//COMMON_DATETIME_FORMAT
		//SimpleDateFormat fm = new SimpleDateFormat(PExConstant.COMMON_DATETIME_FORMAT);
		String sqlUpdateConf = "UPDATE HlbPexConf SET <charges_type> = <charges_value> WHERE status = '"+PExConstant.PEX_CONF_STATUS_ACTIVE+"'";
		String sqlUpdateCharges = "UPDATE HlbPexCharges SET status = '"+PExConstant.PEX_CHARGES_STATUS_DEACTIVATED+"' WHERE pex_type = '<charges_type>' AND status = '"+PExConstant.PEX_CHARGES_STATUS_ACTIVE+"' ";
		String sql = "FROM HlbPexCharges WHERE status ='"+PExConstant.PEX_CHARGES_STATUS_PENDING+"'";
		List<PexCharges> pendingList = dao.selectQuery(sql);
		
		for(PexCharges charges : pendingList)
		{
			if(today.compareTo(charges.getEffectiveDate())>0)
			{
				String updateconfTemp = "";
				String updateChargesTemp = "";
				if(charges.getPexType().equalsIgnoreCase(PExConstant.PEX_COLLECTION_TYPE_ATM))
				{
					updateconfTemp = sqlUpdateConf.replaceAll("<charges_type>", "transaction_atm_charges");
					updateconfTemp = updateconfTemp.replaceAll("<charges_value>", String.valueOf(charges.getRowId()));
					updateChargesTemp = sqlUpdateCharges.replaceAll("<charges_type>", PExConstant.PEX_COLLECTION_TYPE_ATM);
					
				}
				else if(charges.getPexType().equalsIgnoreCase(PExConstant.PEX_COLLECTION_TYPE_INTERNET))
				{
					updateconfTemp = sqlUpdateConf.replaceAll("<charges_type>", "transaction_itn_charges");
					updateconfTemp = updateconfTemp.replaceAll("<charges_value>", String.valueOf(charges.getRowId()));
					updateChargesTemp = sqlUpdateCharges.replaceAll("<charges_type>", PExConstant.PEX_COLLECTION_TYPE_INTERNET);
				}
				else if(charges.getPexType().equalsIgnoreCase(PExConstant.PEX_COLLECTION_TYPE_DIRECT))
				{
					updateconfTemp = sqlUpdateConf.replaceAll("<charges_type>", "transaction_drt_charges");
					updateconfTemp = updateconfTemp.replaceAll("<charges_value>", String.valueOf(charges.getRowId()));
					updateChargesTemp = sqlUpdateCharges.replaceAll("<charges_type>", PExConstant.PEX_COLLECTION_TYPE_DIRECT);
				}
				
				
			
				dao.updateSQL(updateconfTemp);
			
				dao.updateSQL(updateChargesTemp);
				charges.setStatus(PExConstant.PEX_CHARGES_STATUS_ACTIVE);
				dao.updateEntity(charges);
			}
		}
		
		
	}
	
	
	
	
	
	 public Document stringToDom(String xmlSource) throws Exception {
	        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder builder = factory.newDocumentBuilder();
	        return builder.parse(new InputSource(new StringReader(xmlSource)));
	    }
	 
	 
	 public String getStringFromDocument(Document doc) throws TransformerException
	 {
	     
	        DOMSource domSource = new DOMSource(doc);
	        StringWriter writer = new StringWriter();
	        StreamResult result = new StreamResult(writer);
	        TransformerFactory tf = TransformerFactory.newInstance();
	        Transformer transformer = tf.newTransformer();
	        transformer.transform(domSource, result);
	        return writer.toString();
	    
	 }
	 
	 
	 
	 public List<ObAccountBean>  filterAccount(List<ObAccountBean> accountList, String cif, String firstAccount)
	{
		
		
		String sql = "FROM HlbCustomerAccOrder WHERE cif = :cif";
		Hashtable params = new Hashtable();
		params.put("cif", cif);
		List<HlbCustomerAccOrder> accOrderLists = dao.selectQueryParam(sql, params);
		
		
		
		
		
		if(accOrderLists.size()>0)
		{
			
			for(HlbCustomerAccOrder accOrder : accOrderLists)
			{
				for( ObAccountBean obAccount : accountList)
				{
					if(obAccount.getAccountNumber().equalsIgnoreCase(accOrder.getAccountNumber()))
					{
						obAccount.setAccountIndex(getIndexDisplay(Integer.parseInt(accOrder.getAccountOrder())));
						obAccount.setAccountOrder(obAccount.getAccountIndex());
						break;
					}
				}
			}
			
			
			
			
			
			Collections.sort(accountList, COMPARATOR);
			int newIndexOrder = 1;
			for(ObAccountBean obAccount : accountList)
			{
				obAccount.setAccountIndex("ACC"+getIndexDisplay(newIndexOrder));
				newIndexOrder++;
			}
		}
		
		
		if(null!=firstAccount && accountList.size()>0 && accountList.get(0).getAccountOrder()==null && accountList.get(0).getAccountNumber().equalsIgnoreCase(firstAccount))
		{
			accountList.get(0).setAccountOrder("001");
		}
		
		
		return accountList;
	}
	
	 
	 public Comparator<ObAccountBean> COMPARATOR = new Comparator<ObAccountBean>()
    {

		 
		public int compare(ObAccountBean o1, ObAccountBean o2) {
			
			if(null==o1.getAccountIndex())
			{
				o1.setAccountIndex("9999");
			}
			if(null==o2.getAccountIndex())
			{
				o2.setAccountIndex("9999");
			}
			
			return o1.getAccountIndex().compareTo(o2.getAccountIndex());
		}

    };
    
    
	public String  getfirstDisplayAccount(List<AccountVO> listCasaAcc)
	{
		for(AccountVO vo:listCasaAcc)
		{
			if(vo.isAllowDisplay())
			{
				return vo.getAccountNumber();
			}
		}
		
		return null;
	}
			    
    public String getIndexDisplay(int x)
	{
		String index = x+"";
		while(index.length()<3)
		{
			index = "0"+index;
		}
		
		return index;
	}
    
    
    public String formatOCMSeqNo(String x)
	{
		String index = x;
		while(index.length()<6)
		{
			index = "0"+index;
		}
		
		return index;
	}
    
    public String loadMsgx(String code, String locale)
	{
		String msg = "";
		if(null!=locale && locale.equalsIgnoreCase(MiBConstant.LOCALE_KH))
		{
			msg = property.getProperty(code+"."+MiBConstant.LOCALE_KH);
		}
		else
		{
			msg = property.getProperty(code);
		}
		
		return msg;
	}
    
    public String SMSPEx(int smstype, PexProcessTranx pexProcess) throws Exception
    {
    	StringDataUtil stringUtil = new StringDataUtil();
    	HlbCustomerProfile custProfile = ((HlbCustomerProfile)dao.findByID(HlbCustomerProfile.class, pexProcess.getCif()));
    	if(smstype==1)
    	{
    		String sms001 = loadMsgx("sms001",pexProcess.getLocale());
    		String ibRefID = getSystemSequenceNum(new Date());
			String name = stringUtil.getLimitedChac(15, 0, pexProcess.getSenderName());
			String amount = pexProcess.getAmountPex().toPlainString();
			String refNo = pexProcess.getRefNo();
			String message = pexProcess.getRemarkMessage();
			sms001 = sms001.replaceAll("<Name>", name);
			sms001 = sms001.replaceAll("<Amount>", amount);
			sms001 = sms001.replaceAll("<Ref No>", refNo);
			sms001 = sms001.replaceAll("<xdays>", String.valueOf(pexProcess.getExpiration_period()));
			sms001 = sms001.replaceAll("<Message>", message);
			
			String loginID = custProfile.getLoginId();
			
			return sendSMS(loginID,sms001,pexProcess.getRefNo(),ibRefID,pexProcess.getCif(),"PTA_MAKE_PEX_INTERNET",pexProcess.getMobileNo(),"creation");
    	}
    	else if(smstype==2)
    	{
    		String sms002 = loadMsgx("sms002",pexProcess.getLocale());
    		String ibRefID = getSystemSequenceNum(new Date());
			String name = stringUtil.getLimitedChac(15, 0, pexProcess.getSenderName());
			String amount = pexProcess.getAmountPex().toPlainString();
			String refNo = pexProcess.getRefNo();
			String message = pexProcess.getRemarkMessage();
			sms002 = sms002.replaceAll("<Name>", name);
			sms002 = sms002.replaceAll("<Amount>",amount);
			sms002 = sms002.replaceAll("<Ref No>", refNo);
			sms002 = sms002.replaceAll("<Acct No>", pexProcess.getCollectionAccNo());
			sms002 = sms002.replaceAll("<Message>",  message);

			String loginID = custProfile.getLoginId();
			return sendSMS(loginID,sms002,pexProcess.getRefNo(),ibRefID,pexProcess.getCif(),"PTA_MAKE_PEX_DIRECT",pexProcess.getMobileNo(),"creation");
    	}
    	else if(smstype==3)
    	{
    		String sms003 = loadMsgx("sms003",pexProcess.getLocale());
    		String ibRefID = getSystemSequenceNum(new Date());
			String name = stringUtil.getLimitedChac(15, 0, pexProcess.getSenderName());
			String amount = pexProcess.getAmountPex().toPlainString();
			String refNo = pexProcess.getRefNo();
//			String message = pexProcess.getRemarkMessage();
			String dateTime = String.valueOf(pexProcess.getCreationDate()); 
			sms003 = sms003.replaceAll("<Name>", name);
			sms003 = sms003.replaceAll("<Amount>",amount);
			sms003 = sms003.replaceAll("<Ref No>", refNo);
//			sms003 = sms003.replaceAll("<Acct No>", pexProcess.getCollectionAccNo());
//			sms003 = sms003.replaceAll("<Message>", message);
			sms003 = sms003.replaceAll("<datetime>", dateTime);
			
			String loginID = custProfile.getLoginId();
			return sendSMS(loginID,sms003,pexProcess.getRefNo(),ibRefID,pexProcess.getCif(),"PTA_PEX_CANCEL",pexProcess.getMobileNo(),"cancelled");
    	}
    	else if(smstype==4)
    	{
    		String sms004 = loadMsgx("sms004",pexProcess.getLocale());
    		String ibRefID = getSystemSequenceNum(new Date());
//			String name = pexProcess.getSenderName();
			String amount = pexProcess.getAmountPex().toPlainString();
			String refNo = pexProcess.getRefNo();
//			String message = pexProcess.getRemarkMessage();
			String dateTime = String.valueOf(pexProcess.getCreationDate());
			sms004 = sms004.replaceAll("<Amount>",amount);
			sms004 = sms004.replaceAll("<Ref No>", refNo);
			sms004 = sms004.replaceAll("<datetime>", dateTime);
			String loginID = custProfile.getLoginId();
			
			return sendSMS(loginID,sms004,pexProcess.getRefNo(),ibRefID,pexProcess.getCif(),"PTA_PEX_COLLECT_FAILED",custProfile.getMobileNumber(),"suspended");
    	}
    	else if(smstype==5)
    	{
    	
    		char[] hexString = Hex.encodeHex(PerformCreationPex.collectionEncryptionKey.getBytes());
    		EncryptionServices encryptService = new EncryptionServices();
    		byte[] encryptedCNo = encryptService.decrypt(String.valueOf(hexString).getBytes(),Base64.decodeBase64(pexProcess.getPin().getBytes()));
			String atmPin = new String(encryptedCNo);
    		String sms005 = loadMsgx("sms005",pexProcess.getLocale());
    		String ibRefID = getSystemSequenceNum(new Date());
			String name = stringUtil.getLimitedChac(15, 0, pexProcess.getSenderName());
			String amount = pexProcess.getAmountPex().toPlainString();
			String refNo = pexProcess.getRefNo();
			String message = pexProcess.getRemarkMessage();
			sms005 = sms005.replaceAll("<Name>", name);
			sms005 = sms005.replaceAll("<Amount>",amount);
			sms005 = sms005.replaceAll("<Ref No>", refNo);
			sms005 = sms005.replaceAll("<Acct No>", pexProcess.getCollectionAccNo());
			sms005 = sms005.replaceAll("<ATMPIN>",  atmPin);
			sms005 = sms005.replaceAll("<Message>",  message);

			String loginID = custProfile.getLoginId();
			return sendSMS(loginID,sms005,pexProcess.getRefNo(),ibRefID,pexProcess.getCif(),"PTA_MAKE_PEX_DIRECT",pexProcess.getMobileNo(),"creation");
    	}
    	
    	
    	
    	
    	return null;
    }
    
    
    
    public String sendSMS(String loginID,String sms,String refNo,String transactionRefID, String cif, String msgTemp, String mobileNumber,String smsType)
	{
		
		String statusSMS = "";
		try
		{
		
			if(null!=mobileNumber && mobileNumber.length()>0)
			{
				SecuirtyService secService = new SecuirtyService(null);
				SendNotificationForPreLoginRequest notificationRequest = new SendNotificationForPreLoginRequest();
				notificationRequest.setMessageBody(sms);
				notificationRequest.setReferenceId(transactionRefID);
				notificationRequest.setCifId(cif);
				notificationRequest.setMessageTemplateCode(msgTemp);
				notificationRequest.setMobileNumber(mobileNumber);
				ObUserContext userContext = new ObUserContext();
				userContext.setLoginId(loginID);
				userContext.setCountryCode(property.getProperty("pexCollectCountry"));
				userContext.setLocaleCode(property.getProperty("pexCollectLocale"));
				EndpointResponse respEndPoint = secService.sendPreLoginNotification(userContext, notificationRequest,transactionRefID);
				if(respEndPoint.getResponse().getStatusCode()==1)
				{
					statusSMS = String.valueOf(respEndPoint.getResponse().getStatusCode());
					updateSMSFlag(true,refNo,smsType);
				}
				else
				{
					statusSMS = respEndPoint.getResponse().getErrorCode();
					updateSMSFlag(false,refNo,smsType);
				}
				dao.insertEntity(secService.getIbwsLog());
			}
			else
			{
				return "No Mobile Number";
			}
			
			
		}catch(Exception e)
		{
			updateSMSFlag(false,refNo,smsType);
		}
		
		return statusSMS;
		
	}
    
    
    public void updateSMSFlag(boolean success, String refID, String type)
	{
		
		String updateIBTransSQL = "UPDATE HlbPexProcessTranx SET <smsPExType> = '<smsFlagStatus>' WHERE ref_no=:ref_no AND <smsPExType> = '"+PExConstant.PEX_SMS_FLAG_PENDING+"'"; 
		
		
		
		if(type.equalsIgnoreCase("creation"))
		{
			updateIBTransSQL = updateIBTransSQL.replaceAll("<smsPExType>", "sms_creation_flag");
		}
		else if(type.equalsIgnoreCase("cancelled"))
		{
			updateIBTransSQL = updateIBTransSQL.replaceAll("<smsPExType>", "sms_cancel_flag");
		}
		else if(type.equalsIgnoreCase("suspended"))
		{
			updateIBTransSQL = updateIBTransSQL.replaceAll("<smsPExType>", "sms_suspend_flag");
		}
		
		
			
		
		if(success)
		{
			
			Hashtable params = new Hashtable();
			params.put("ref_no", refID);
			updateIBTransSQL = updateIBTransSQL.replaceAll("<smsFlagStatus>", PExConstant.PEX_SMS_FLAG_SENT);
			dao.updateSQLParam(updateIBTransSQL,params);
		}
		
		
		
		
	}
	 
    
    
    public boolean showATM()
    {
    	PropertiesManager propertyRealTime = new PropertiesManager();
    	String atm = propertyRealTime.getProperty("pexAtm");
    	if(null!=atm && atm.equalsIgnoreCase("true"))
    	{
    		return true;
    	}    	
    	
    	return false;
    }
    
	public static void main(String args[])
	{
		BigDecimal a = new BigDecimal("50.00");
		BigDecimal b = new BigDecimal("10.00");
		
		int x = b.divide(a).toBigInteger().intValue();
		
		//System.out.println(checkVNMobileNumber("09123456789"));
		
	}

	
	
	
	public boolean checkVNMobileNumber(String mobileNumber)
	{
		
		
		if(mobileNumber.matches(PExConstant.contactNumberPatternKH1))
		{
			return true;
		}
		else if(mobileNumber.matches(PExConstant.contactNumberPatternKH2))
		{
			return true;
		}
		else if(mobileNumber.matches(PExConstant.contactNumberPatternKH3))
		{
			return true;
		}
		else if(mobileNumber.matches(PExConstant.contactNumberPatternKH4))
		{
			return true;
		}
		else if(mobileNumber.matches(PExConstant.contactNumberPatternKH5))
		{
			return true;
		}
		
		return false;
	}

	
	
	public String loadCCMsg(String errorCode,String locale)
	{
		String statusCode = errorCode;
		locale = null==locale?"en":locale;
		String message = msgPro.getProperty(statusCode,locale);
		String errorMsg = "";
		if(null==message)
		{
			errorMsg = statusCode;
		}
		else
		{
			errorMsg = message;
		}
		
		
		return errorMsg;
	}
	
	
//	public Date checkRBSDate(Date currentDate)
//	{
//		//manual change rbs night mode date
//		// 1 = forward one day
//		// -1 = backward one day
//		int dateCheck = 0;
//		String host_datetime = property.getProperty("ext.host.datetime");
//		if(null==host_datetime)
//		{
//			dateCheck = 0;
//		}
//		else if(host_datetime.length()>0)
//		{
//			dateCheck = Integer.parseInt(host_datetime);
//		}
//
//
//		
//		if(dateCheck!=0)
//		{
//			Calendar c = Calendar.getInstance();
//			c.setTime(currentDate);
//			c.add(Calendar.DATE, dateCheck);
//			Date newDate = c.getTime();
//			return newDate;
//		}
//		
//		
//		return currentDate;
//	}

	//below mehtod for get  ext.host.datetime from database,then no more using above  checkRBSDate() funcion
		public Date checkRBSDate(Date currentDate){
			int dateCheck = 0;
			
			String host_datetime = null;
			
			
			String sql = "FROM HlbGeneralCode WHERE  gncode_type='RBSDATEDIFF' and status='A'";
			List<HlbGeneralCode> dateGenCodeList = null;
			try{
				dateGenCodeList = dao.selectQuery(sql);			
				host_datetime = dateGenCodeList.get(0).getGnCodeDescription();
				log.info("extRbsHostdatevalue == "+host_datetime);			
			}catch(Exception e){
				log.info("exception "+e.getMessage());
			}
			
			if(null==host_datetime)
			{
				dateCheck = 0;
			}
			else if(host_datetime.length()>0)
			{
				dateCheck = Integer.parseInt(host_datetime);
			}


			
			if(dateCheck!=0)
			{
				Calendar c = Calendar.getInstance();
				c.setTime(currentDate);
				c.add(Calendar.DATE, dateCheck);
				Date newDate = c.getTime();
				return newDate;
			}
			
			
			return currentDate;
			
		}
	
	
	
	
	public String getHashPexCollection(String collectionHash)
	{
		String sql = "FROM HlbPexCollectionCode";
		List<PexCollectionCode> collectionDatas = dao.selectQuery(sql);
		
		try
		{
			EncryptionServices encryptService = new EncryptionServices();
			MessageDigest md = MessageDigest.getInstance( "SHA1" );
			for(PexCollectionCode collectionCode:collectionDatas)
			{
				char[] hexString = Hex.encodeHex(PerformCreationPex.collectionEncryptionKey.getBytes());
				byte[] encryptedCNo = encryptService.decrypt(String.valueOf(hexString).getBytes(),Base64.decodeBase64(collectionCode.getCollectionNo().getBytes()));
				
				md.update(encryptedCNo);
			
				String dbCollectionCode  = new BigInteger( 1, md.digest() ).toString(16);
				//log.debug("Collection Code DB : "+new String(encryptedCNo) +" --- ["+collectionHash+"]-["+dbCollectionCode+"]");
				if(dbCollectionCode.equalsIgnoreCase(collectionHash))
				{
					
					return (new String(encryptedCNo));
				}
			}
		
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		
				
		return null;
	}
	
	public BigDecimal getQuickPexLimit(String quick_LoginType, String device_type)
	{
		Date today = new Date();
		String sql = "FROM HlbQuickPexLimit WHERE status = '"+MiBConstant.PRO_ACTIVE+"' and quick_pex_type = :quickLoginType ";
		
		Hashtable param = new Hashtable();

		param.put("quickLoginType", quick_LoginType);


		List<QuickPexLimit> pexLimit = dao.selectQueryParam(sql,param);


		if(pexLimit.size()==1)
		{
			if(today.compareTo(pexLimit.get(0).getEffectiveDate())>0){
				if(device_type.equalsIgnoreCase(PExConstant.PEX_BIOMETRIC_ANDROID_CHECK)){
					return pexLimit.get(0).getAndroidAmountLimit() != null ?pexLimit.get(0).getAndroidAmountLimit() : null;
				}else if(device_type.equalsIgnoreCase(PExConstant.PEX_BIOMETRIC_IOS_CHECK)){
					return pexLimit.get(0).getIosAmountLimit() != null ?pexLimit.get(0).getIosAmountLimit() : null;
				}else{
					return null;
				}
			}
				
		}
		
		return null;
	}
	
	public boolean loginTypeContainBiometric(String loginType, String pexType)
	{
		if(loginType != null)
		{
			String types [] = loginType.split(",");
			
			for(String type : types)
			{
				if(type.equalsIgnoreCase(pexType))
				{
					return true;
				}
			}
		}
	
		return false;
	}
	
}
