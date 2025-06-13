package com.silverlake.mleb.mcb.module.func.deviceBinding;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.mcb.bean.ObDeviceBindingRequest;
import com.silverlake.mleb.mcb.bean.ObDeviceBindingResponse;
import com.silverlake.mleb.mcb.bean.ObDeviceCifBean;
import com.silverlake.mleb.mcb.bean.ObHeaderResponse;
import com.silverlake.mleb.mcb.bean.ObUserContext;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.entity.CustomerProfile;
import com.silverlake.mleb.mcb.entity.DeviceProfile;
import com.silverlake.mleb.mcb.entity.GeneralCode;
import com.silverlake.mleb.mcb.entity.dao.CustomerDAO;
import com.silverlake.mleb.mcb.entity.dao.DeviceCIFDao;
import com.silverlake.mleb.mcb.entity.dao.GeneralCodeDAO;
import com.silverlake.mleb.mcb.module.common.FuncServices;
import com.silverlake.mleb.mcb.util.PropertiesManager;






@Service
public class DeviceCIFEnquiry extends FuncServices  
{

	private static Logger log = LogManager.getLogger(DeviceCIFEnquiry.class);
	 
	private PropertiesManager pro = new PropertiesManager();
	@Autowired ApplicationContext appContext;
	
	@Autowired
	DeviceCIFDao custDao;
	
	@Autowired
	GeneralCodeDAO gnDao;
	
	@Autowired
	CustomerDAO custProfDao;
	
	public MICBResponseBean process(MICBRequestBean arg0) {
		// TODO Auto-generated method stub
		
		
	
		MICBResponseBean response = new MICBResponseBean();
		ObDeviceBindingResponse loginResponse = new ObDeviceBindingResponse();
		loginResponse.setObHeader(new ObHeaderResponse());
		loginResponse.setUserContext(new ObUserContext());
		
		try {
			boolean checkDeviceMatch = false;
			ObDeviceBindingRequest requestData = (ObDeviceBindingRequest) arg0.getBDObject();			
			
			String locale = arg0.getLocaleCode();
			String cif = requestData.getObUser().getCifNumber();
			String deviceID =  null==arg0.getDeviceBean()?null:arg0.getDeviceBean().getDeviceId();
		
			 
				List<DeviceProfile>  extDevList = custDao.getDeviceByCIF(cif);
				
				if(extDevList!=null)
				{
					
					log.info("CIF SIZE :::::: "+extDevList.size());
					loginResponse.setDeviceListing(new ArrayList());
					SimpleDateFormat sDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					int index = 1;
					for(DeviceProfile ext:extDevList)
					{
						if(deviceID.equalsIgnoreCase(ext.getDeviceId()))
						{
							checkDeviceMatch = true;
						}
						ObDeviceCifBean dev = new ObDeviceCifBean();
						dev.setCif(ext.getCif());
						dev.setDevModel(ext.getDeviceModel());
						dev.setDevId(ext.getDeviceId());
						dev.setDevOs(ext.getOs());
						dev.setDevIsRooted(ext.getIsRooted());
						dev.setDevType(ext.getDeviceType());
						dev.setTransId(ext.getTransId());
						dev.setIndex(String.valueOf(index));
						dev.setDateBind(sDF.format(ext.getDateBind()));
						dev.setDeviceLevel(ext.getDeviceLevel());
						dev.setDeviceStatus(ext.getStatus());
                        if(ext.getAliasName() != null && !ext.getAliasName().isEmpty()){
                        	dev.setAliasName(ext.getAliasName());	
						}else {
							dev.setAliasName("");
						}
                        
                        CustomerProfile profileDB = custProfDao.getCustomerProfileByCif(dev.getCif());
                        if(profileDB != null) {
                        	dev.setLastLoginDate(sDF.format(profileDB.getLastLoginDT()));
                        }
                        
                        GeneralCode primaryGnCode = gnDao.findByGnCode(MiBConstant.GNCODE_PRIMARY_DEV_BINDING);
						if(primaryGnCode != null) {
							String desc = primaryGnCode.getGnCodeDescription();
							if(desc != null && desc.equalsIgnoreCase("true")) {
								if(ext.getDateBind() != null && ext.getDeviceLevel() != null && ext.getDeviceLevel().equalsIgnoreCase(MiBConstant.MIB_DEVICEPROFILE_LEVEL_SECONDARY)
										&& ext.getApprovalAction() != null && ext.getApprovalAction().equalsIgnoreCase(MiBConstant.MIB_DEVICEPROFILE_STATUS_APPROVE) 
										&& ext.getDateSwitch() == null) {
									// cooling period only applicable to newly binded secondary device - exclude switch device process
									log.info("DateBind :: "+ext.getDateBind());
									Calendar cal = Calendar.getInstance();
									cal.setTime(ext.getDateBind());
									cal.add(Calendar.HOUR_OF_DAY, 12);
									Date coolingPeriod = cal.getTime();
									log.info("pluscoolingPeriod :: "+sDF.format(coolingPeriod));
									Date current = new Date();
									log.info("current :: "+sDF.format(current));
									long countdown = coolingPeriod.getTime() - current.getTime();
									dev.setApprovalCountdown(Long.toString(countdown));
									if(Long.signum(countdown) < 0) {
										log.info("countdown ended");
										dev.setApprovalCountdown("0");
									}
								}
							}
						}
						
						loginResponse.getDeviceListing().add(dev);
						index++;
					}
				
				}
				
				
				GeneralCode gnRs = gnDao.findByGnCode(MiBConstant.MAXDEVICEBINDKEY);
				if(null!=gnRs)
				{
					loginResponse.setMaxDevBinding(gnRs.getGnCodeDescription());
				}
				else
				{
					loginResponse.setMaxDevBinding("1");
				}
				
				
				loginResponse.setAllowDevBinding("true");
				int maxDevBind = loginResponse.getMaxDevBinding()==null?0:Integer.parseInt(loginResponse.getMaxDevBinding());
				
				if(extDevList!=null && extDevList.size()>=maxDevBind)
				{
					loginResponse.setAllowDevBinding("false");
					
				}
				

				if(checkDeviceMatch)
				{
					loginResponse.setAllowDevBinding("false");
				}
				
				
				
				
				if(loginResponse.getDeviceListing()==null || loginResponse.getDeviceListing().size()==0)
				{
					loginResponse.getObHeader().setStatusCode(MiBConstant.MCB_NO_BINDED_DEVICE);
				}
				else
				{
					loginResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
				}
				
				
			
		 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//"ERROR.MIB.9999999"
			

			log.info(this.getClass().toString(), e);
			loginResponse = new ObDeviceBindingResponse();
			loginResponse.setObHeader(new ObHeaderResponse());
			loginResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			loginResponse.getObHeader().setStatusMessage(e.getMessage());
			
		}
		
		response.setBDObject(loginResponse);
		
		return response;
	}


	

	 

	
	
	
}