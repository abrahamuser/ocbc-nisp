package com.silverlake.mleb.mcb.entity.dao;


import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silverlake.mleb.mcb.constant.MiBConstant;

import com.silverlake.mleb.mcb.entity.MessageProperties;
import com.silverlake.mleb.mcb.entity.MessagePropertiesI18n;

@Service
@Repository
@Transactional
public class MessagePropertiesDAO extends DAO
{
	private static Logger log = LogManager.getLogger(MessagePropertiesDAO.class);


	@PersistenceContext(unitName="db_mleb")
	EntityManager emx ;

	
	
	
	@Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return emx;
	}
	
	@SuppressWarnings("unchecked")
	public List<MessagePropertiesI18n> retrieveAllMessageByLocale(String locale)
	{
		
		EntityManager em  = getEntityManager();
		
		String sqlMsg = "SELECT M, k FROM " + MessagePropertiesI18n.class.getSimpleName() + " M, " + MessageProperties.class.getSimpleName() + " k "
				+"WHERE M.errMessageCode=k.errMessageCode AND upper(M.languageCode) =:locale AND k.status = '"+ MiBConstant.MSG_ACTIVE + "' ";

		List<Object[]> selectList = em.createQuery(sqlMsg).setParameter("locale", locale.toUpperCase()).getResultList();
		List<MessagePropertiesI18n> messageList = new ArrayList<MessagePropertiesI18n>();
		
		if(selectList != null && !selectList.isEmpty()){
			for(int i=0; i<selectList.size(); i++){
				MessagePropertiesI18n messageI18n = (MessagePropertiesI18n)selectList.get(i)[0];
				messageI18n.setMessageProperties((MessageProperties)selectList.get(i)[1]);
				messageList.add(messageI18n);
			}
		}

		return messageList;
	}
	
	@SuppressWarnings("unchecked")
	public List<MessageProperties> retrieveAllMessage()
	{
		List<MessageProperties> result = null;
		
		String sql = "FROM "+MessageProperties.class.getSimpleName() + " WHERE status = '" + MiBConstant.MSG_ACTIVE + "' ";
	
		
		result = this.selectQuery(sql);
		
		
		return result;
	}
	
	
	public void updateProMsgToDB(String key, String msg, String msgIn, MessageProperties messageProperties)
	{
		
		if(null==messageProperties)
		{
			MessageProperties msgPro = new MessageProperties();
			msgPro.setCreateDt(new Date());
			msgPro.setCreateBy("system");
			msgPro.setErrMessageCode(key);
			msgPro.setStatus("ACTIVE");
			
			this.insertEntity(msgPro);
			messageProperties = msgPro;
			
			
		}

		if(null!=msg && null!=messageProperties)
		{
			MessagePropertiesI18n msgen = new MessagePropertiesI18n();
			msgen.setCreateDt(new Date());
			msgen.setCreateBy("system");
			msgen.setErrMessage(msg);
			msgen.setErrMessageCode(key);
			msgen.setLanguageCode("en");
			msgen.setMessageProperties(messageProperties);
			
			this.insertEntity(msgen);
		}
		
		if(null!=msgIn && null!=messageProperties)
		{
			MessagePropertiesI18n msgen = new MessagePropertiesI18n();
			msgen.setCreateDt(new Date());
			msgen.setCreateBy("system");
			msgen.setErrMessage(msgIn);
			msgen.setErrMessageCode(key);
			msgen.setLanguageCode("in");
			msgen.setMessageProperties(messageProperties);
			this.insertEntity(msgen);
		}
		
		
	}
	
	public List<MessagePropertiesI18n> getMessageMappingByPage(String errorCode, String errorMsg, String locale, String pageNum, String totalRecord)
	{
		EntityManager em  = getEntityManager();
		//String sql = "FROM "+CustomerEvent.class.getSimpleName()+" WEHRE cif = :cif order by request_datetime DESC";
		
		CriteriaBuilder cb  = em.getCriteriaBuilder();
		CriteriaQuery<MessagePropertiesI18n> cq = cb.createQuery(MessagePropertiesI18n.class);
		Root<MessagePropertiesI18n> from = cq.from(MessagePropertiesI18n.class);
//		CriteriaQuery<MessagePropertiesI18n> select = cq.select(from);
		
		
		
		
		/*if(lastRowId!=null && lastRowId.trim().length()>0)
		{
			CustomerEvent lastEvent = em.find(CustomerEvent.class, Integer.parseInt(lastRowId));
			
			Path<Date> dateCreatedPath = from.get("requestDatetime");
			cq.where(cb.equal(from.get("cif"), cif),cb.lessThan(dateCreatedPath, lastEvent.getRequestDatetime()));
		}
		else
		{
			cq.where(cb.equal(from.get("cif"), cif));
		}*/
		
		int firstRow = 1;
		if(pageNum!=null && pageNum.trim().length()>0)
		{
			firstRow = Integer.parseInt(pageNum);
			firstRow =( firstRow-1)*Integer.parseInt(totalRecord);
		}
		
		
		Predicate  localeCheck = null;
		log.info("Search : "+locale);
		if(null==locale || locale.trim().length()==0)
		{
			localeCheck = cb.or(cb.equal(from.get("languageCode"), "en"),cb.equal(from.get("languageCode"), "id"));
		}
		else
		{
			localeCheck = cb.equal(from.get("languageCode"), locale);
		}
		
		
		if(null!=errorCode && errorCode.trim().length()>0 )
		{
			
			cq.where(cb.equal(from.get("errMessageCode"), errorCode),localeCheck);
		}
		else if(null!=errorMsg && errorMsg.trim().length()>0)
		{
			
			cq.where(cb.like(from.<String>get("errMessage"), "%"+errorMsg+"%"),localeCheck);
		}
		else
		{
			log.info("Search by locale only : "+locale);
			cq.where(localeCheck);
		}
		
		
		
		cq.orderBy(cb.desc(from.get("createDt")));
		return em.createQuery(cq).setFirstResult(firstRow).setMaxResults(Integer.parseInt(totalRecord)).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public MessagePropertiesI18n findByMsgCode(String msgCode, String locale)
	{
		MessagePropertiesI18n msg = null;
		
		String sql = "FROM "+MessagePropertiesI18n.class.getSimpleName()+" WHERE err_message_code = :msgCode and language_code = :langCode ";
		
		Hashtable hash = new Hashtable();
		hash.put("msgCode", msgCode);
		hash.put("langCode", locale);
		List<MessagePropertiesI18n> resultList = selectQueryParam(sql,hash);
		if(resultList!=null && resultList.size()>0)
		{
			return resultList.get(0);
		}
		
		
		return null;
	}
	
	
	public MessageProperties findByMainMsgCode(String msgCode)
	{
		
		String sql = "FROM "+MessageProperties.class.getSimpleName()+" WHERE err_message_code = :msgCode ";
		Hashtable hash = new Hashtable();
		hash.put("msgCode", msgCode);
		List<MessageProperties> resultList = selectQueryParam(sql,hash);
		if(resultList!=null && resultList.size()>0)
		{
			return resultList.get(0);
		}
		
		return null;
	}
	
	
	
	
	public MessagePropertiesI18n findByRecordId(String recordID)
	{
		MessagePropertiesI18n msg = null;
		
		msg  = (MessagePropertiesI18n) this.findByID(MessagePropertiesI18n.class, Integer.parseInt(recordID));
		
		
		return msg;
	}
	
	public void deleteMsgMapping(MessagePropertiesI18n msgMapping)
	{
		EntityManager em  = getEntityManager();
		em.remove(msgMapping);
	}
	
	@SuppressWarnings("unchecked")
	public List<MessagePropertiesI18n> getModifyMessage()
	{
		

		EntityManager em  = getEntityManager();
		
//		String sqlMsg = "SELECT M FROM " + MessagePropertiesI18n.class.getSimpleName() + " M inner join M.messageProperties as k "
//				+ " where k.status = '"
//				+ MiBConstant.MSG_ADDED + "' OR k.status = '" + MiBConstant.MSG_MODIFY
//				+ "' OR  k.status='" + MiBConstant.MSG_DELETED + "' ";
//
//		List<MessagePropertiesI18n> messageList = em.createQuery(sqlMsg).getResultList();
		
		String sqlMsg = "SELECT M, k FROM " + MessagePropertiesI18n.class.getSimpleName() + " M, " + MessageProperties.class.getSimpleName() + " k "
				+"where M.errMessageCode=k.errMessageCode AND (" 
				+"k.status = '"+ MiBConstant.MSG_ADDED + "' OR k.status = '" + MiBConstant.MSG_MODIFY
				+ "' OR  k.status='" + MiBConstant.MSG_DELETED + "') ";

		List<Object[]> selectList = em.createQuery(sqlMsg).getResultList();
		List<MessagePropertiesI18n> messageList = new ArrayList<MessagePropertiesI18n>();
		
		if(selectList != null && !selectList.isEmpty()){
			for(int i=0; i<selectList.size(); i++){
				MessagePropertiesI18n messageI18n = (MessagePropertiesI18n)selectList.get(i)[0];
				messageI18n.setMessageProperties((MessageProperties)selectList.get(i)[1]);
				messageList.add(messageI18n);
			}
		}
		
		log.info("---------- MessagePropertiesDAO::getModifyMessage() size()="+messageList.size());
		
		return messageList;
	}
	
	
 
	
}