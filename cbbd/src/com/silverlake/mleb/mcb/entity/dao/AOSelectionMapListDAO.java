package com.silverlake.mleb.mcb.entity.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import comx.silverlake.mleb.mcb.entity.AOParamCache;
import comx.silverlake.mleb.mcb.entity.AOSelectionMapList;

@Service
@Repository
@Transactional
@SuppressWarnings("unchecked") // remove later
public class AOSelectionMapListDAO extends DAO {
	private static Logger log = LogManager.getLogger(AOSelectionMapListDAO.class);

	@PersistenceContext(unitName = "db_mleb")
	EntityManager emx;

	
//	public List<AOSelectionMapList> getSelectionMapList(String selectionKey, String selectionType) {
//		EntityManager em = getEntityManager();
//		String sql = "FROM " + AOSelectionMapList.class.getSimpleName() + " where selection_id1 = some (select rowId from " + AOParamCache.class.getSimpleName() + " where param_key = :selectionKey and PARAM_TYPE = :selectionType )";
//		List<AOSelectionMapList> listSML = em.createQuery(sql).setParameter("selectionKey", selectionKey).setParameter("selectionType", selectionType)
//				.getResultList();
//		return listSML;
//	}
//	
//	public List<AOParamCache> getMatchedValue(int rowId) {
//		EntityManager em = getEntityManager();
//		String sql = "FROM " + AOParamCache.class.getSimpleName() + " WHERE row_id = :rowId";
//		List<AOParamCache> listSML = em.createQuery(sql).setParameter("rowId", rowId)
//				.getResultList();
//		return listSML;
//	}
	
	

	public List<AOParamCache> getSelectionMapListValues(String paramKey, String paramType) {
		EntityManager em = getEntityManager();
		String sql = "FROM AOParamCache WHERE row_id "
				+ "in (SELECT a.selectionId2 from AOSelectionMapList a, AOParamCache b "
				+ "WHERE a.selectionId1 = b.rowId AND b.paramKey =:paramKey AND b.paramType =:paramType)";
		List<AOParamCache> listSML = em.createQuery(sql).setParameter("paramKey", paramKey)
				.setParameter("paramType", paramType).getResultList();
		return listSML;
	}
	
	public List<AOSelectionMapList> getAMLSelectionMapList(String selectionKey1, String selectionType1, String selectionKey2, String selectionType2)
	{
		
		EntityManager em  = getEntityManager();
		String sql = "FROM AOSelectionMapList WHERE selection_id1 "
				+ "in (SELECT a.rowId from AOParamCache a "
				+ "WHERE a.paramKey =:selectionKey1 AND a.paramType =:selectionType1) AND selection_id2 in "
				+ "(SELECT a.rowId from AOParamCache a "
				+ "WHERE a.paramKey =:selectionKey2 AND a.paramType =:selectionType2)";
		
		List<AOSelectionMapList> lsSelectionMapList = em.createQuery(sql).setParameter("selectionKey1", selectionKey1).setParameter("selectionType1", selectionType1)
				.setParameter("selectionKey2", selectionKey2).setParameter("selectionType2", selectionType2).getResultList();
		
		if(lsSelectionMapList!=null && lsSelectionMapList.size()>0){
			return lsSelectionMapList;
		}
		else{
			return null;
		}
	
	}

	@Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return emx;
	}

}