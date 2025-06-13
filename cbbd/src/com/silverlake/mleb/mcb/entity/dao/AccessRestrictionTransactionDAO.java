package com.silverlake.mleb.mcb.entity.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silverlake.mleb.mcb.entity.AccessRestrictionTransaction;

@Service
@Repository
@Transactional
public class AccessRestrictionTransactionDAO {

	@Autowired DozerBeanMapper mapper;

	@PersistenceContext(unitName="db_mleb")
	EntityManager em ;

	//private static Logger log = LogManager.getLogger(MicbDAO.class);

	public Object findByID(Class objClass, Object obj) {
		Object rsObj = em.find(objClass, obj);
		return rsObj;
	}
	
	public AccessRestrictionTransaction get(String sourceCode, String productCode, String status){
		String sql = "From "+ AccessRestrictionTransaction.class.getSimpleName() +" where sourceCode = :sourceCode and productCode=:productCode and status=:status";
		
		List<AccessRestrictionTransaction> result = em.createQuery(sql)
				.setParameter("sourceCode", sourceCode)
				.setParameter("productCode", productCode)
				.setParameter("status", status)
				.getResultList();
		if(result != null && !result.isEmpty()){
			return result.get(0);
		}else{
			return null;
		}
	}
}
