package com.silverlake.mleb.mcb.entity.dao;


import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.util.DateUtil;

import comx.silverlake.mleb.mcb.entity.LanguageControl;
import comx.silverlake.mleb.mcb.entity.Promotion;
import comx.silverlake.mleb.mcb.entity.PromotionI18n;


@Service
@Repository
@Transactional
public class PromotionDAO
{
	
	@Autowired DozerBeanMapper mapper;
	
	@PersistenceContext(unitName="db_mleb")
	EntityManager em ;

	//private static Logger log = LogManager.getLogger(MicbDAO.class);

	
	public Object findByID(Class objClass, Object obj) {
		Object rsObj = em.find(objClass, obj);
		return rsObj;
	}

	
	
	public List<Promotion> getObPromotion(String promotionType, String localecode) throws Exception 
	{
		List<Promotion> responseList = null;
		responseList = em.createQuery(" select p From HlbPromotion p left join p.hlbPromotionI18nList pl " +
									  " where 1=1 and p.promotionType = '"+ promotionType + "' and p.status = '"+ MiBConstant.PRO_ACTIVE +"'" +
									  " and p.publishTo >= '"+ DateUtil.formatTransactionDateOnly("yyyy-MM-dd", new Date())+"'"+
									  " and pl.languageCode= '" + localecode +"' order by p.position").getResultList();
		
		if(responseList != null && responseList.size() > 0){
			for(Promotion promotion : responseList){
				
				for(PromotionI18n HlbPromotionI18nList : promotion.getPromotionI18nList()){
					if(localecode.equalsIgnoreCase(HlbPromotionI18nList.getLanguageCode())){
						promotion.setPromotionDesc(HlbPromotionI18nList.getPromotionDesc());
						break;
					}
				}
			}
		}
		
		
		return responseList;
	}
	
	public List<Promotion> getAllPromotionIcon() throws Exception 
	{
		List<Promotion> responseList = null;
		responseList = em.createQuery(" select p From HlbPromotion p where p.position = '1' and p.status = '"+ MiBConstant.PRO_ACTIVE +"' "+
				                      " and p.publishTo >= '"+ DateUtil.formatTransactionDateOnly("yyyy-MM-dd", new Date())+"'").getResultList();
				
		/*responseList = em.createQuery(" select p From HlbPromotion p left join p.hlbPromotionI18nList pl " +
				  					  " where p.position = '1' and p.status = '"+ MiBConstant.PRO_ACTIVE +"' " +
				  					  " and pl.languageCode= '"+ localecode +"'").getResultList();
		
		if(responseList != null && responseList.size() > 0){
			for(HlbPromotion promotion : responseList){
				
				for(HlbPromotionI18n HlbPromotionI18nList : promotion.getHlbPromotionI18nList()){
					if(localecode.equalsIgnoreCase(HlbPromotionI18nList.getLanguageCode())){
						promotion.setPromotionDesc(HlbPromotionI18nList.getPromotionDesc());
						break;
					}
				}
			}
		}
		*/
		return responseList;
	}
	
	public List<Promotion> getEndPromotionList(String startDt, String endDt, String localecode) throws Exception 
	{
			
		List<Promotion> responseList = null;
		responseList = em.createQuery(" select p From HlbPromotion p left join p.hlbPromotionI18nList pl " +
									  " where p.publishTo >= '" + startDt + "' and p.publishTo <= '" + endDt +"' " +
									  " and p.status = '"+ MiBConstant.PRO_ACTIVE +"' and pl.languageCode= '"+ localecode +"' "+
									  " order by p.promotionTo asc").getResultList();
		
		if(responseList != null && responseList.size() > 0){
			for(Promotion promotion : responseList){
				
				for(PromotionI18n HlbPromotionI18nList : promotion.getPromotionI18nList()){
					if(localecode.equalsIgnoreCase(HlbPromotionI18nList.getLanguageCode())){
						promotion.setPromotionDesc(HlbPromotionI18nList.getPromotionDesc());
						break;
					}
				}
			}
		}
		return responseList;
	}
	
	public LanguageControl getLangCont(String promotionCode, String localeCode) throws Exception 
	{
		List<LanguageControl> responseList = null;
		LanguageControl entity = null;
		responseList = em.createQuery("From HlbLanguageControl lc where lc.code = '"+ promotionCode + "'  and lc.langCode = '"+ localeCode +"'").getResultList();
		if(responseList!=null && responseList.size()>0)
			entity = responseList.get(0);
		return entity;
	}
	
	public int insertEntity(Object obj) {
		
		em.persist(obj);
		em.flush();
		
		return 0;
	}

	
	public Object updateEntity(Object obj) {
		Object ss = em.merge(obj);
		
		return ss;
	}
	
	
	public List selectQuery(String JQL)
	{
		return em.createQuery(JQL).getResultList();
	}


	public EntityManager getEm() {
		return em;
	}


	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	
	public int updateSQL(String SQL)
	{
		return this.em.createQuery(SQL).executeUpdate();
	}
	

	
}