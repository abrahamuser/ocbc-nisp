package com.silverlake.mleb.mcb.entity.dao;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.entity.AccessRestriction;
import com.silverlake.mleb.mcb.entity.McbConf;

@Service
@Repository
@Transactional
public class AccessRestrictionDAO {

	@Autowired DozerBeanMapper mapper;

	@PersistenceContext(unitName="db_mleb")
	EntityManager em ;

	//private static Logger log = LogManager.getLogger(MicbDAO.class);

	public Object findByID(Class objClass, Object obj) {
		Object rsObj = em.find(objClass, obj);
		return rsObj;
	}
	
	public List<AccessRestriction> getMenuList(String parentId){
		String sql = "From "+ AccessRestriction.class.getSimpleName() +" where menuParentId = :parentId and status=:status";
		
		List<AccessRestriction> resultlist = em.createQuery(sql).setParameter("parentId", parentId).setParameter("status", MiBConstant.STATUS_ACTIVE).getResultList();
		if(resultlist != null){
			return resultlist;
		}else{
			return Collections.<AccessRestriction>emptyList();
		}
	}
	
	public List<AccessRestriction> getParentMenuList(){
		return getMenuList("0");
	}
}
