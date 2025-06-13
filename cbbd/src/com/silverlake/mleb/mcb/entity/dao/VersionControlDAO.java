package com.silverlake.mleb.mcb.entity.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.entity.VersionControl;

@Service
@Repository
@Transactional
public class VersionControlDAO {

	@Autowired DozerBeanMapper mapper;

	@PersistenceContext(unitName="db_mleb")
	EntityManager em ;

	//private static Logger log = LogManager.getLogger(MicbDAO.class);

	public Object findByID(Class objClass, Object obj) {
		Object rsObj = em.find(objClass, obj);
		return rsObj;
	}
	
	public VersionControl getBy(String deviceType, String version) {
		String sql = "From "+ VersionControl.class.getSimpleName() +" where appName = :deviceType and version = :version and status=:status";
		
		List<VersionControl> resultlist = em.createQuery(sql)
				.setParameter("deviceType", deviceType)
				.setParameter("version", version)
				.setParameter("status", MiBConstant.STATUS_ACTIVE).getResultList();
		if(resultlist != null && resultlist.size() > 0){
			return resultlist.get(0);
		}else{
			return null;
		}
	}
}
