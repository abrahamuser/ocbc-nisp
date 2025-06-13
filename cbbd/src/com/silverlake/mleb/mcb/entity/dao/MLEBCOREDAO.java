package com.silverlake.mleb.mcb.entity.dao;


import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silverlake.mleb.mcb.constant.MiBConstant;

import com.silverlake.mleb.mcb.entity.CustomerProfile;
import com.silverlake.mleb.mcb.entity.DeviceProfile;
import com.silverlake.mleb.mcb.entity.McbConf;

 

@Service
@Repository
@Transactional
public class MLEBCOREDAO extends DAO
{
	private static Logger log = LogManager.getLogger(MLEBCOREDAO.class);


	@PersistenceContext(unitName="db_mleb")
	EntityManager emx ;
	
	
	public void insertMLEBFunction(String functionID, String mcbUrl, String DB)
	{
		//INSERT INTO mleb_core.mleb_function (function_id,core_function,create_by,create_dt,dispatch_url,function_code,function_name,group_id,is_authentication_required,modify_by,modify_dt,status,system_id,vm_url) VALUES (MLEB_CORE.MLEBFUNCTION_ID.nextval,NULL,'admin',SYSDATE,'http://localhost:7001/mcb/remoting/mcb_api','FUNC_MCB_APP_STAT_INFO','stat info',01,'N',NULL,NULL,'A',1,'vm://MCB');
		String sql = "SELECT * FROM mleb_core.mleb_function where function_code = '"+functionID+"' and status = 'A'";
		
		String insertOracle = "INSERT INTO mleb_core.mleb_function (function_id,core_function,create_by,create_dt,dispatch_url,function_code,function_name,group_id,is_authentication_required,modify_by,modify_dt,status,system_id,vm_url) VALUES (MLEB_CORE.MLEBFUNCTION_ID.nextval,NULL,'admin',SYSDATE,'"+mcbUrl+"','"+functionID+"','',01,'N',NULL,NULL,'A',1,'vm://MCB')";
		
		String insertMYSQL = "INSERT INTO mleb_core.mleb_function (core_function,create_by,create_dt,dispatch_url,function_code,function_name,group_id,is_authentication_required,modify_by,modify_dt,status,system_id,vm_url) VALUES ( NULL,'admin',now(),'"+mcbUrl+"','"+functionID+"','',01,'N',NULL,NULL,'A',1,'vm://MCB')";
	
		EntityManager em  = getEntityManager();
		
		List resultSelect = em.createNativeQuery(sql).getResultList();
		
		if(null==resultSelect ||  resultSelect.size()==0)
		{
			log.info("Function ID NOT FOUND ["+functionID+"] : INSERT TO DB ["+DB+"] ");
			
		 
			if(DB.equalsIgnoreCase("oracle"))
			{
				em.createNativeQuery(insertOracle).executeUpdate();
				log.info("INSERT FINISH ["+functionID+"] ["+DB+"] ");
			}
			else if(DB.equalsIgnoreCase("mysql"))
			{
				em.createNativeQuery(insertMYSQL).executeUpdate();
				log.info("INSERT FINISH ["+functionID+"] ["+DB+"] ");
			}
			else
			{
				log.info("DB NOT SUPPORTED ["+DB+"] ");
			}
			
		}
		
		
	
	}
	
	
	

	public int getCIFDeviceProfile(McbConf mibConf, String cif, String deviceID)
	{
		/*-1 = device already tagged
		0 = device not tag yet
		>0 = device tagged limited*/
		
		EntityManager em  = getEntityManager();
		String sql = "FROM "+CustomerProfile.class.getSimpleName()+" WHERE cif = :cif AND status = '"+MiBConstant.MIB_CUSTPROFILE_STATUS_ACTIVE+"' ";
		List<CustomerProfile> custProfiles = em.createQuery(sql).setParameter("cif", cif).getResultList();
		int i = 0;
		
		
		int limit = mibConf.getDeviceLimit();
		if(i >= limit)
		{
			return limit;
		}
		return 0;
	}
	
	public CustomerProfile getCustomerProfileByCif(String cif)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+CustomerProfile.class.getSimpleName()+" WHERE cif = :cif AND status = '"+MiBConstant.MIB_CUSTPROFILE_STATUS_ACTIVE+"' ";
		List<CustomerProfile> custProfiles = em.createQuery(sql).setParameter("cif", cif).getResultList();
		
		if(custProfiles != null && custProfiles.size() == 1)
		{
			return custProfiles.get(0);
		}
		
		return null;
	}
	
	
	public int getDeviceLimit()
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+McbConf.class.getSimpleName()+" where status = '"+MiBConstant.MIB_SYSTEM_STATUS_ACTIVE+"'";
		List<McbConf> hlbMiBConf = em.createQuery(sql).getResultList();
		return hlbMiBConf.get(0).getDeviceLimit();
	}
	
	
	public boolean checkActiveDeviceProfile(String deviceID)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+DeviceProfile.class.getSimpleName()+" WHERE device_id = :device_id AND status = '"+MiBConstant.MIB_DEVICEPROFILE_STATUS_ACTIVE+"'";

		List<DeviceProfile> devicePros = em.createQuery(sql).setParameter("device_id", deviceID).getResultList();
		
		if(devicePros.size()>0)
		{
			return true;
		}
		
		return false;
	}
	
	public boolean checkActiveDeviceProfile(String deviceID, String cif)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+DeviceProfile.class.getSimpleName()+" WHERE device_id = :device_id AND status = '"+MiBConstant.MIB_DEVICEPROFILE_STATUS_ACTIVE+"' AND cif != :cif ";

		List<DeviceProfile> devicePros = em.createQuery(sql).setParameter("device_id", deviceID).setParameter("cif", cif).getResultList();
		
		if(devicePros.size()>0)
		{
			return true;
		}
		
		return false;
	}
	

	
	public List<DeviceProfile> getdeviceProfileByCif(String cif)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+DeviceProfile.class.getSimpleName()+" WHERE cif = :cif AND status = '"+MiBConstant.MIB_DEVICEPROFILE_STATUS_ACTIVE+"' order by date_tagged desc";
		
		List<DeviceProfile> devicePros = em.createQuery(sql).setParameter("cif", cif).getResultList();
		
		return devicePros;
		
	}
	
	public int deleteDeviceProfileByCif(String cif, String deviceID)
	{
		EntityManager em  = getEntityManager();
		String sql = "DELETE "+DeviceProfile.class.getSimpleName()+" WHERE  device_id=:devID AND status = '"+MiBConstant.MIB_DEVICEPROFILE_STATUS_ACTIVE+"' AND cif = :cif ";
		return em.createQuery(sql).setParameter("cif", cif).setParameter("devID", deviceID).executeUpdate();
		
	}
	
	
	/*public List<RedirectURL> getPreLoginURL(String locale)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+RedirectURL.class.getSimpleName()+"L WHERE status = '"+MiBConstant.STS_ACTIVE+"'";
		sql += " and type = '"+ locale +"'"; 

		List<RedirectURL> preLoginURL = em.createQuery(sql).getResultList();

		return preLoginURL;		
	}*/
	
	
	@Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return emx;
	}
	
	public  String getLoginIdByDeviceId(String deviceID)
	{
		String result = null;
		EntityManager em  = getEntityManager();
		String sql = "SELECT hcp.loginId FROM "+CustomerProfile.class.getSimpleName()+" as hcp inner join hcp.hlbDeviceProfiles as hdp where hdp.deviceId = :deviceID ";
		
		Hashtable params = new Hashtable();
		params.put("deviceID", deviceID);

		
		if(deviceID.indexOf("-IMAC-") > 0)
		{
			String deviceIdCon = deviceID.substring(0, deviceID.indexOf("-IMAC-"));
			sql = sql + " OR hdp.deviceId = :deviceIDConcat ";
			params.put("deviceIDConcat", deviceIdCon);
		}
		
		sql = sql + " AND hcp.status =  '"+MiBConstant.MIB_CUSTPROFILE_STATUS_ACTIVE+"' and hdp.status = '"+MiBConstant.MIB_CUSTPROFILE_STATUS_ACTIVE+"' ";
		
		Query query =  em.createQuery(sql);
		
		Enumeration keys = params.keys();
		while( keys.hasMoreElements() ) {
		  Object key = keys.nextElement();
		  Object value = params.get(key);
		  
		  query.setParameter(key.toString(), value.toString());
		  
		}
		
		List<String> custProfilesList = query.getResultList();
		
		log.info(" custProfilesList size is ["+custProfilesList.size()+"]"+custProfilesList);
		if( custProfilesList.size() < 1 ){
			result="-1";
		}else if( custProfilesList.size() == 1 ){
			String  custProfile = custProfilesList.get(0);
			result = custProfile;
		}
		
		return result;
	}

	public String getMobileNumberBasedOnCif(String uniqueId){
		String queryString = "select a.mobileNumber as mobileNo from "+CustomerProfile.class.getSimpleName()+" a where a.cif = :cif";
		String  result = null;
		try {
			EntityManager em  = getEntityManager();
			List<String> mobileNumber = em.createQuery(queryString).setParameter("cif", uniqueId).getResultList();
			
			if (mobileNumber.size()>0)
				result =  mobileNumber.get(0);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}
	
	/*public  String getLoginIdByDeviceId(String deviceID,String deviceType)
	{
		
		String result = null;
		EntityManager em  = getEntityManager();
		String sql = "FROM "+DeviceProfile.class.getSimpleName()+" where (device_id = :deviceID ";
		
		Hashtable params = new Hashtable();
		params.put("deviceID", deviceID);
		
		if(deviceID.indexOf("-IMAC-") > 0)
		{
			String deviceIdCon = deviceID.substring(0, deviceID.indexOf("-IMAC-"));
			sql = sql + " OR device_id = :deviceIDConcat_IMAC ";
			params.put("deviceIDConcat_IMAC", deviceIdCon);
		}
		
		if(deviceType.equalsIgnoreCase("iPad")){
			
			sql = sql + " OR device_id = :deviceIDConcat_IPAD ";
			params.put("deviceIDConcat_IPAD", deviceID+"_iPad");
		}
		
	 
		sql = sql + ") AND device_type = :deviceType AND status = '"+MiBConstant.MIB_CUSTPROFILE_STATUS_ACTIVE+"' ";
		params.put("deviceType", deviceType);
		
		List<DeviceProfile> pexTranxs = selectQueryParam(sql,params);
		List<String> custProfilesList = new ArrayList();
		
		
		CustomerProfile cust;
		for(DeviceProfile deviceProfile : pexTranxs){
			
			cust = deviceProfile.getCustomerProfile();
			if(cust.getStatus().equalsIgnoreCase(MiBConstant.MIB_CUSTPROFILE_STATUS_ACTIVE))
				custProfilesList.add(cust.getLoginId());
		}
		
		log.info(" custProfilesList size is ["+custProfilesList.size()+"]"+custProfilesList);
		if( custProfilesList.size() < 1 ){
			result="-1";
		}else if( custProfilesList.size() == 1 ){
			String  custProfile = custProfilesList.get(0);
			result = custProfile;
		}
		
		return result;
	}*/
	
	public boolean checkBiometricStatus(String deviceId)
	{
		
		EntityManager em  = getEntityManager();
		String sql = "FROM "+DeviceProfile.class.getSimpleName()+" where device_id = :deviceID AND STATUS = '"+MiBConstant.MIB_CUSTPROFILE_STATUS_ACTIVE+"' AND biometric_status = '"+MiBConstant.MIB_CUSTPROFILE_STATUS_ACTIVE+"'";
		
		Hashtable params = new Hashtable();
		params.put("deviceID", deviceId);
		
		List<DeviceProfile> devices = selectQueryParam(sql,params);
		if( devices.size() < 1 )
			return false;
		else if( devices.size() == 1 )
			return true;
		else
			return false;
	}
	
public DeviceProfile getDeviceProfileByOnDeviceId(String deviceID,String deviceType) {
		
		String sql = "FROM "+DeviceProfile.class.getSimpleName()+" where (device_id = :deviceID ";
		
		Hashtable params = new Hashtable();
		params.put("deviceID", deviceID);
		
		if(deviceID.indexOf("-IMAC-") > 0)
		{
			String deviceIdCon = deviceID.substring(0, deviceID.indexOf("-IMAC-"));
			sql = sql + " OR device_id = :deviceIDConcat_IMAC ";
			params.put("deviceIDConcat_IMAC", deviceIdCon);
		}
		
		if(deviceType.equalsIgnoreCase("iPad")){
			
			sql = sql + " OR device_id = :deviceIDConcat_IPAD ";
			params.put("deviceIDConcat_IPAD", deviceID+"_iPad");
		}
		
		sql = sql + ") AND device_type = :deviceType AND status = '"+MiBConstant.MIB_CUSTPROFILE_STATUS_ACTIVE+"' ";
		params.put("deviceType", deviceType);
		
		List<DeviceProfile> pexTranxs = selectQueryParam(sql,params);
		
		if(pexTranxs.size() > 0) {
			return pexTranxs.get(0);
		}
		else { 
			return null;
		}
	}

/*public  CustomerProfile getCIFByDeviceId(String deviceID, String deviceType)
{
	
	CustomerProfile result = null;
	EntityManager em  = getEntityManager();
	String sql = "FROM "+DeviceProfile.class.getSimpleName()+" where (device_id = :deviceID ";
	
	Hashtable params = new Hashtable();
	params.put("deviceID", deviceID);
	
	if(deviceID.indexOf("-IMAC-") > 0)
	{
		String deviceIdCon = deviceID.substring(0, deviceID.indexOf("-IMAC-"));
		sql = sql + " OR device_id = :deviceIDConcat_IMAC ";
		params.put("deviceIDConcat_IMAC", deviceIdCon);
	}
	
	if(deviceType.equalsIgnoreCase("iPad")){
		
		sql = sql + " OR device_id = :deviceIDConcat_IPAD ";
		params.put("deviceIDConcat_IPAD", deviceID+"_iPad");
	}
	
	sql = sql + ") AND device_type = :deviceType AND status = '"+MiBConstant.MIB_CUSTPROFILE_STATUS_ACTIVE+"' ";
	params.put("deviceType", deviceType);
	
	List<DeviceProfile> pexTranxs = selectQueryParam(sql,params);
	List<CustomerProfile> custProfilesList = new ArrayList();
	
	
	CustomerProfile cust;
	for(DeviceProfile deviceProfile : pexTranxs){
		
		cust = deviceProfile.getCustomerProfile();
		if(cust.getStatus().equalsIgnoreCase(MiBConstant.MIB_CUSTPROFILE_STATUS_ACTIVE))
			custProfilesList.add(cust);
	}
	
	
	
	
	
	log.info(" custProfilesList size is ["+custProfilesList.size()+"]"+custProfilesList);
	if( custProfilesList.size() < 1 ){
		result=null;
	}else if( custProfilesList.size() == 1 ){
		CustomerProfile  custProfile = custProfilesList.get(0);
		result = custProfile;
	}
	
	return result;
}
*/


	
	
	
}