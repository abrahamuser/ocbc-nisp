package com.silverlake.mleb.mcb.entity.dao;


import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.entity.DeviceProfile;
import com.silverlake.mleb.mcb.entity.ExtChannelProfile;
import com.silverlake.mleb.mcb.entity.UnBindDeviceProfile;
 

 


@Service
@Repository
@Transactional
public class DeviceCIFDao extends DAO
{
	private static Logger log = LogManager.getLogger(DeviceCIFDao.class);


	@PersistenceContext(unitName="db_mleb")
	EntityManager emx ;
	
	public DeviceProfile getCIFByDeviceID(String deviceID)
	{
		DeviceProfile ext = null;
		EntityManager em  = getEntityManager();
		String sql = "FROM "+DeviceProfile.class.getSimpleName()+" WHERE device_id = :deviceID AND status = '"+MiBConstant.MIB_DEVICEPROFILE_STATUS_ACTIVE+"' ";
		List<DeviceProfile> devProfile = em.createQuery(sql).setParameter("deviceID", deviceID).getResultList();
		
		if(null!=devProfile && devProfile.size()==1)
		{
			return devProfile.get(0);
		}
		
		return ext;
	}
	
	public DeviceProfile getCIFByDeviceIDAndStatus(String deviceID)
	{
		DeviceProfile ext = null;
		EntityManager em  = getEntityManager();
		String sql = "FROM "+DeviceProfile.class.getSimpleName()+" WHERE device_id = :deviceID AND status IN ('" + MiBConstant.MIB_DEVICEPROFILE_STATUS_ACTIVE + "', '"+MiBConstant.MIB_DEVICEPROFILE_STATUS_PENDING+"')";
		List<DeviceProfile> devProfile = em.createQuery(sql).setParameter("deviceID", deviceID).getResultList();
		
		if(null!=devProfile && devProfile.size()==1)
		{
			return devProfile.get(0);
		}
		
		return ext;
	}
	
	public DeviceProfile getDevProfByDeviceIDAndCif(String deviceID,String cif)
	{
		DeviceProfile ext = null;
		EntityManager em  = getEntityManager();
		String sql = "FROM "+DeviceProfile.class.getSimpleName()+" WHERE device_id = :deviceID AND cif = :cif AND status = '"+MiBConstant.MIB_CUSTPROFILE_STATUS_ACTIVE+"' ";
		List<DeviceProfile> devProfile = em.createQuery(sql).setParameter("deviceID", deviceID).setParameter("cif", cif).getResultList();
		
		if(null!=devProfile && devProfile.size()==1)
		{
			return devProfile.get(0);
		}
		
		return ext;
	}
	
	
	
	public List<DeviceProfile> getDeviceByCIF(String cif)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+DeviceProfile.class.getSimpleName()+" WHERE cif = :cif AND status = '"+MiBConstant.MIB_DEVICEPROFILE_STATUS_ACTIVE+"' ";
		List<DeviceProfile> devProfile = em.createQuery(sql).setParameter("cif", cif).getResultList();

		return devProfile;
	}
	
	public List<DeviceProfile> getDeviceByCIFAndStatus(String cif)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+DeviceProfile.class.getSimpleName()+" WHERE cif = :cif AND status IN ('" + MiBConstant.MIB_DEVICEPROFILE_STATUS_ACTIVE + "', '"+MiBConstant.MIB_DEVICEPROFILE_STATUS_PENDING+"')";
		List<DeviceProfile> devProfile = em.createQuery(sql).setParameter("cif", cif).getResultList();

		return devProfile;
	}
	
	public int removeProfileByDevAndCif(String cif, String deviceID)
	{
		EntityManager em  = getEntityManager();
		String sql = "DELETE "+DeviceProfile.class.getSimpleName()+" WHERE  device_id=:devID AND cif = :cif AND status IN ('" + MiBConstant.MIB_DEVICEPROFILE_STATUS_ACTIVE + "', '"+MiBConstant.MIB_DEVICEPROFILE_STATUS_PENDING+"')";
		return em.createQuery(sql).setParameter("cif", cif).setParameter("devID", deviceID).executeUpdate();
		
	}
	
	public UnBindDeviceProfile getRejectedDeviceByDev(String deviceID)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+UnBindDeviceProfile.class.getSimpleName()+" WHERE device_id=:deviceID AND status = '"+MiBConstant.MIB_DEVICEPROFILE_STATUS_REJECTED+"' ORDER BY date_unBind DESC ";
		List<UnBindDeviceProfile> rejectedDevProfile = em.createQuery(sql).setParameter("deviceID", deviceID).getResultList();
		
		if(null!=rejectedDevProfile && rejectedDevProfile.size() > 0)
		{
			return rejectedDevProfile.get(0);
		}
		
		return null;
	}
	
	
	public UnBindDeviceProfile untaggDeviceProfile(String cif, String deviceID)
	{
 
		DeviceProfile deviceProfile = (DeviceProfile) getDevProfByDeviceIDAndCif(deviceID,cif);

		if (null != deviceProfile)
		{
			int deleteRS = removeProfileByDevAndCif(cif, deviceID);
			if (deleteRS == 1)
			{
				UnBindDeviceProfile untaggedDevice = mergedDeviceProfileToUntaggedDeviceProfile(
						deviceProfile);
				untaggedDevice.setStatus(MiBConstant.MIB_DEVICEPROFILE_STATUS_UNTAGGED);
				untaggedDevice.setDateUnBind(new Date());
				insertEntity(untaggedDevice);
				return untaggedDevice;
			}
		}

		return null;
	}
	
	
	public UnBindDeviceProfile mergedDeviceProfileToUntaggedDeviceProfile(
			DeviceProfile deviceProfile)
	{
		UnBindDeviceProfile untaggedDevice = new UnBindDeviceProfile();
		untaggedDevice.setdateBind(deviceProfile.getDateBind());
		untaggedDevice.setDateUnBind(deviceProfile.getDateUnBind());
		untaggedDevice.setDeviceId(deviceProfile.getDeviceId());
		untaggedDevice.setDeviceModel(deviceProfile.getDeviceModel());
		untaggedDevice.setDeviceType(deviceProfile.getDeviceType());
		untaggedDevice.setCif(deviceProfile.getCif());
		untaggedDevice.setOs(deviceProfile.getOs());
		untaggedDevice.setStatus(deviceProfile.getStatus());
		untaggedDevice.setUnBindBy(deviceProfile.getUnBindBy());
		untaggedDevice.setIsRooted(deviceProfile.getIsRooted());
		untaggedDevice.setTokenFingerPrint(deviceProfile.getTokenFingerPrint());
		return untaggedDevice;
	}

	
	
	
	
	
	
	
	
	public ExtChannelProfile validateChannelProfile(String id,String password)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+ExtChannelProfile.class.getSimpleName()+" WHERE  channel_id=:id AND password = :password AND status = '"+MiBConstant.MIB_DEVICEPROFILE_STATUS_ACTIVE+"'  ";
		List<ExtChannelProfile> profiles =  em.createQuery(sql).setParameter("id", id).setParameter("password", password).getResultList();
		if(null!=profiles && profiles.size()==1)
		{
			return profiles.get(0);
		}
		
		return null;
	}
	
	public boolean isDevicePrimaryDevice(String deviceID, String cif)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+DeviceProfile.class.getSimpleName()+" WHERE cif = :cif AND status = '"+MiBConstant.MIB_DEVICEPROFILE_STATUS_ACTIVE+"' ORDER BY date_bind ASC";
		List<DeviceProfile> devProfile = em.createQuery(sql).setParameter("cif", cif).getResultList();
		
		if(null!=devProfile && devProfile.size()>0)
		{
			//get the first binded device
			DeviceProfile primaryDevice = devProfile.get(0); 
			if(primaryDevice.getDeviceId().equals(deviceID)) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	public List<DeviceProfile> getPendingDevProfByCif(String cif)
	{
		EntityManager em  = getEntityManager();
		String sql = "FROM "+DeviceProfile.class.getSimpleName()+" WHERE cif = :cif AND status = '"+MiBConstant.MIB_DEVICEPROFILE_STATUS_PENDING+"' ";
		List<DeviceProfile> devProfile = em.createQuery(sql).setParameter("cif", cif).getResultList();
		
		return devProfile;
	}
	
	public int updateDeviceStatus(String cif, String deviceID, String status, String action) {
		EntityManager em  = getEntityManager();
		String sql = "UPDATE "+ DeviceProfile.class.getSimpleName()+" SET status = :status , approval_action = :action WHERE cif = :cif AND device_id = :deviceID";
		return em.createQuery(sql)
				.setParameter("deviceID", deviceID)
				.setParameter("cif", cif)
				.setParameter("status", status)
				.setParameter("action", action)
				.executeUpdate();
	}
	
	public int updateDeviceLevel(String cif, String deviceID, String deviceLevel) {
		EntityManager em  = getEntityManager();
		String sql = "UPDATE "+ DeviceProfile.class.getSimpleName()+" SET device_level = :deviceLevel WHERE cif = :cif AND device_id = :deviceID";
		return em.createQuery(sql)
				.setParameter("deviceID", deviceID)
				.setParameter("cif", cif)
				.setParameter("deviceLevel", deviceLevel)
				.executeUpdate();
	}
	
	public DeviceProfile getDevProfByDeviceIDAndCifAndStatus(String deviceID,String cif)
	{
		String sql = "FROM "+DeviceProfile.class.getSimpleName()+" WHERE device_id = :deviceID AND cif = :cif AND status IN ('" + MiBConstant.MIB_DEVICEPROFILE_STATUS_ACTIVE + "', '"+MiBConstant.MIB_DEVICEPROFILE_STATUS_PENDING+"')";
		List<DeviceProfile> devProfile = getEntityManager()
				.createQuery(sql, DeviceProfile.class)
				.setParameter("deviceID", deviceID)
				.setParameter("cif", cif)
				.getResultList();
		
		if(null!=devProfile && devProfile.size()==1)
		{
			return devProfile.get(0);
		}
		return null;
	}
	
	public DeviceProfile getPrimaryDevice(String cif)
	{
		DeviceProfile ext = null;
		EntityManager em  = getEntityManager();
		String sql = "FROM "+DeviceProfile.class.getSimpleName()+" WHERE cif = :cif AND device_level = '"+MiBConstant.MIB_DEVICEPROFILE_LEVEL_PRIMARY+"' AND status = '"+MiBConstant.MIB_DEVICEPROFILE_STATUS_ACTIVE+"' ";
		List<DeviceProfile> devProfile = em.createQuery(sql).setParameter("cif", cif).getResultList();
		
		if(null!=devProfile && devProfile.size()==1)
		{
			return devProfile.get(0);
		}
		
		return ext;
	}
	
	@Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return emx;
	}
	
	
	
}