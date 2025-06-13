package com.silverlake.mleb.mcb.task;
//package com.silverlake.mleb.mib.task;
//
//import java.lang.reflect.Field;
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Collection;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//import com.silverlake.mleb.mib.constant.MiBConstant;
//import com.silverlake.mleb.mib.entity.AOBankInfoCache;
//import com.silverlake.mleb.mib.entity.AOBusinessAddressCache;
//import com.silverlake.mleb.mib.entity.AOBusinessInfoCache;
//import com.silverlake.mleb.mib.entity.AOCCAdditionalInfoCache;
//import com.silverlake.mleb.mib.entity.AOCCSupplementInfoCache;
//import com.silverlake.mleb.mib.entity.AOEmergencyContactAddressCache;
//import com.silverlake.mleb.mib.entity.AOEmergencyContactCache;
//import com.silverlake.mleb.mib.entity.AOFinancialInfoCache;
//import com.silverlake.mleb.mib.entity.AOGeneralInfoCache;
//import com.silverlake.mleb.mib.entity.AOKPRAdditionalInfoCache;
//import com.silverlake.mleb.mib.entity.AOKTPInfoCache;
//import com.silverlake.mleb.mib.entity.AOMailingAddressCache;
//import com.silverlake.mleb.mib.entity.AOWorkAddressCache;
//import com.silverlake.mleb.mib.entity.MiBConf;
//import com.silverlake.mleb.mib.entity.dao.AOCacheInputDao;
//import com.silverlake.mleb.mib.entity.dao.AOCacheInputTypeDao;
//import com.silverlake.mleb.mib.entity.dao.MLEBMIBDAO;
//import com.silverlake.mleb.mib.module.common.MiBServices;
//import com.silverlake.mleb.mib.module.ib.aoApi.AOService;
//import com.silverlake.mleb.mib.module.ib.aobean.AccountOnBoardingAddress;
//import com.silverlake.mleb.mib.module.ib.aobean.AccountOnBoardingBankInfo;
//import com.silverlake.mleb.mib.module.ib.aobean.AccountOnBoardingBusinessInfo;
//import com.silverlake.mleb.mib.module.ib.aobean.AccountOnBoardingCCAdditionalInfo;
//import com.silverlake.mleb.mib.module.ib.aobean.AccountOnBoardingCCSupplementInfo;
//import com.silverlake.mleb.mib.module.ib.aobean.AccountOnBoardingEmergencyContact;
//import com.silverlake.mleb.mib.module.ib.aobean.AccountOnBoardingFinancialInfo;
//import com.silverlake.mleb.mib.module.ib.aobean.AccountOnBoardingForm;
//import com.silverlake.mleb.mib.module.ib.aobean.AccountOnBoardingGeneralInfo;
//import com.silverlake.mleb.mib.module.ib.aobean.AccountOnBoardingKPRAdditionalInfo;
//import com.silverlake.mleb.mib.module.ib.aobean.AccountOnBoardingKTPInfo;
//import com.silverlake.mleb.mib.module.ib.aobean.Parameters;
//import com.silverlake.mleb.mib.module.ib.bean.IBRequest;
//import com.silverlake.mleb.mib.module.ib.bean.IBResponse;
//import com.silverlake.mleb.mib.util.MapperUtil;
//import org.apache.commons.collections.CollectionUtils;
//import org.springframework.beans.BeanUtils;
//@Service
//public class CheckAccountOnBoardingCache
//{
//	private static Logger log = LogManager.getLogger(CheckAccountOnBoardingCache.class);
//
//
//	@Autowired
//	MLEBMIBDAO dao;
//
//	@Autowired
//	AOCacheInputDao cacheDao;
//
//	@Autowired TaskRenewSession renewSession;
//
//	@Autowired 
//	ApplicationContext appContext;
//
//	@Autowired
//	AOCacheInputTypeDao cacheTypeDao;
//	
//	@Scheduled(fixedDelay=(60000*25)) //Run every 25 min
//	public void process()
//	{
//		MiBServices mibService = new MiBServices(dao);
//		MiBConf mibConf = mibService.getMiBConf();
//		try{
//			AOService aoService = new AOService(appContext);
//			List<AOGeneralInfoCache> lsGeneralInfoCache = cacheDao.getAOGeneralInfoCacheByPendingStatus();
//			log.info("Account On Boarding Cache Checking Start");
//			if(lsGeneralInfoCache!=null && !lsGeneralInfoCache.isEmpty()){
//				for(AOGeneralInfoCache generalInfo : lsGeneralInfoCache){
//					String deviceId = "";
//					String nik = "";
//					Date currentTime = Calendar.getInstance().getTime();
//					long differenceInMillis = currentTime.getTime() - generalInfo.getCreatedTime().getTime();
//					long differenceInHours = (differenceInMillis) / 1000L / 60L / 60L; // Divide by millis/sec, secs/min, mins/hr
//					log.info("difference In Hours (USER CACHE) :: " + differenceInHours);
//					
//					if(generalInfo.getIsFinal().equalsIgnoreCase(Boolean.toString(false))){
//						if(differenceInHours >= mibService.getNonFinalCacheExpiredHour()){
//							nik = generalInfo.getNik();
//							deviceId = generalInfo.getDeviceId();
//							processSendToDataLead(aoService ,deviceId);
//							cacheDao.deletePendingCacheByDeviceId(deviceId);
//							log.info("REMOVED CACHE NON FINAL INPUT WHERE DEVICE ID :: " + deviceId);
//						}
//					}
//					else{
//						if(differenceInHours >= mibService.getFinalCacheExpiredHour()){
//							nik = generalInfo.getNik();
//							deviceId = generalInfo.getDeviceId();
//							processSendToDataLead(aoService ,deviceId);
//							cacheDao.deletePendingCacheByDeviceId(deviceId);
//							log.info("REMOVED CACHE FINAL INPUT WHERE DEVICE ID :: " + deviceId);
//						}
//					}
//				}
//			}
//			log.info("Account On Boarding Cache Checking End");
//		}
//		catch(Exception e)
//		{
//			log.info("EXCEPTION PROCESSING CACHE LISTS :: "+e);
//			e.printStackTrace();
//		}
//
//
//		//log.info("Cache Checking every 1 minute");
//
//	}
//
//	private void processSendToDataLead(AOService aoService, String deviceId){
//		Parameters parameters = new Parameters();
//		AccountOnBoardingForm accOnBoardingForm = new AccountOnBoardingForm();
//
//		IBRequest aoRequest = new IBRequest();
//		IBResponse aoResponse = new IBResponse();
//
//
//		List<AOGeneralInfoCache> lsGeneralInfoCache = cacheDao.getAOGeneralInfoPendingCacheByDeviceId(deviceId);
//
//		if(lsGeneralInfoCache!=null && !lsGeneralInfoCache.isEmpty()){
//			AOGeneralInfoCache generalInfo = lsGeneralInfoCache.get(0);
//
//			accOnBoardingForm.setActionCode(MiBConstant.ACCONBOARDING_ACTIONCODE_DL);
//			/*if(!generalInfo.getIsBirthInUS().equalsIgnoreCase("false") && !generalInfo.getIsHoldMailInUS().equalsIgnoreCase("false") && !generalInfo.getIsStandingInstructionUS().equalsIgnoreCase("false")
//					&& !generalInfo.getIsUSAttorney().equalsIgnoreCase("false") && !generalInfo.getIsUSGreenCard().equalsIgnoreCase("false") && !generalInfo.getIsUSResidence().equalsIgnoreCase("false")){
//				accOnBoardingForm.setFlagFatca("N");
//			}
//			else{
//				accOnBoardingForm.setFlagFatca("Y");
//			}*/
//			accOnBoardingForm.setFlagFatca("");
//			accOnBoardingForm.setNik(generalInfo.getNik()!= null && generalInfo.getNik().isEmpty()?"":generalInfo.getNik());
//
//			copyBeanProperties(generalInfo, accOnBoardingForm, true);
//			if(generalInfo.getCallAppointmentDate()!=null && generalInfo.getCallAppointmentDate().isEmpty()){
//				accOnBoardingForm.setCallAppointmentDate(null);
//			}
//			else if(generalInfo.getCallAppointmentDate()==null){
//				accOnBoardingForm.setCallAppointmentDate(null);
//			}
//			processAdditionalInfo(accOnBoardingForm, deviceId);
//
//			parameters.setAccountOnBoardingForm(accOnBoardingForm);
//			parameters.setChannel(MiBConstant.CHANNEL_MB);
//			parameters.setOperationCode(MiBConstant.GENERAL_OPERATIONCODE);
//			parameters.setLang(MiBConstant.LOCALE_EN.toUpperCase());
//			aoRequest.setParameters(parameters);
//			aoResponse = aoService.createAccountOnBoarding(aoRequest, "", "");
//
////			if(!aoResponse.getStatus().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS) && !aoResponse.getStatus().equalsIgnoreCase("000")){
////				log.info("OMNI ERROR : " + MiBConstant.MIB_OMNI_PREFIX+aoResponse.getStatus() + "DEVICE ID : " + deviceId);
////			}
//		}
//
//
//	}
//
//	private void processAdditionalInfo(AccountOnBoardingForm accOnBoardingForm, String deviceId){
//
//		List<AOKTPInfoCache> lsKTPInfoCache = cacheDao.getKTPInfoPendingCacheByDeviceId(deviceId);
//		List<AOBankInfoCache> lsBankInfo = cacheDao.getBankPendingCacheInputByDeviceId(deviceId);
//		List<AOBusinessAddressCache> lsBusinessAddressCache = cacheDao.getBusinessAddressPendingCacheByDeviceId(deviceId);
//		List<AOBusinessInfoCache> lsBusinessInfoCache = cacheDao.getBusinessInfoPendingCacheByDeviceId(deviceId);
//		List<AOCCAdditionalInfoCache> lsCCAdditionalInfoCache = cacheDao.getCCAdditionalInfoPendingCacheByDeviceId(deviceId);
//		List<AOCCSupplementInfoCache> lsCCSupplementInfoCache = cacheDao.getCCSupplementInfoPendingCacheByDeviceId(deviceId);
//		List<AOEmergencyContactAddressCache> lsEmergencyContactAddressCache = cacheDao.getEmergencyContactAddressPendingCacheByDeviceId(deviceId);
//		List<AOEmergencyContactCache> lsEmergencyContactCache = cacheDao.getEmergencyContactPendingCacheByDeviceId(deviceId);
//		List<AOKPRAdditionalInfoCache> lsKPRAdditionalInfoCache = cacheDao.getKPRAdditionalInfoPendingCacheByDeviceId(deviceId);
//		List<AOMailingAddressCache> lsMailingAddressCache = cacheDao.getMailingAddressPendingCacheByDeviceId(deviceId);
//		List<AOWorkAddressCache> lsWorkAddressCache = cacheDao.getWorkAddressPendingCacheByDeviceId(deviceId);
//		List<AOFinancialInfoCache> lsFinancialInfoCache = cacheDao.getFinancialInfoPendingCacheByDeviceId(deviceId);
//
//		if(lsKTPInfoCache!=null && !lsKTPInfoCache.isEmpty()){
//			AOKTPInfoCache ktpInfo = lsKTPInfoCache.get(0);
//			accOnBoardingForm.setEditedDataDukcapil(new AccountOnBoardingKTPInfo());
//			copyBeanProperties(ktpInfo, accOnBoardingForm.getEditedDataDukcapil(), false);
//		}
//		if(lsBankInfo!=null && !lsBankInfo.isEmpty()){
//			AOBankInfoCache bankInfo = lsBankInfo.get(0);
//			bankInfo.setNik(null);
//			accOnBoardingForm.setBankInformation(new AccountOnBoardingBankInfo());
//			copyBeanProperties(bankInfo, accOnBoardingForm.getBankInformation(), false);
//		}
//		if(lsBusinessAddressCache!=null && !lsBusinessAddressCache.isEmpty()){
//			AOBusinessAddressCache businessAddressInfo = lsBusinessAddressCache.get(0);
//			businessAddressInfo.setNik(null);
//			accOnBoardingForm.setBusinessAddress(new AccountOnBoardingAddress());
//			copyBeanProperties(businessAddressInfo, accOnBoardingForm.getBusinessAddress(), false);
//		}
//		if(lsBusinessInfoCache!=null && !lsBusinessInfoCache.isEmpty()){
//			AOBusinessInfoCache businessInfo = lsBusinessInfoCache.get(0);
//			businessInfo.setNik(null);
//			accOnBoardingForm.setBusinessInformation(new AccountOnBoardingBusinessInfo());
//			copyBeanProperties(businessInfo, accOnBoardingForm.getBusinessInformation(), false);
//		}
//		if(lsCCAdditionalInfoCache!=null && !lsCCAdditionalInfoCache.isEmpty()){
//			AOCCAdditionalInfoCache ccAdditionalInfo = lsCCAdditionalInfoCache.get(0);
//			ccAdditionalInfo.setNik(null);
//			accOnBoardingForm.setCcAdditionalInformation(new AccountOnBoardingCCAdditionalInfo());
//			copyBeanProperties(ccAdditionalInfo, accOnBoardingForm.getCcAdditionalInformation(), false);
//		}
//		if(lsCCSupplementInfoCache!=null && !lsCCSupplementInfoCache.isEmpty()){
//			AOCCSupplementInfoCache ccSupplementInfo = lsCCSupplementInfoCache.get(0);
//			ccSupplementInfo.setNik(null);
//			accOnBoardingForm.setCcSupplementInformation(new AccountOnBoardingCCSupplementInfo());
//			copyBeanProperties(ccSupplementInfo, accOnBoardingForm.getCcSupplementInformation(), false);
//		}
//		if(lsEmergencyContactAddressCache!=null && !lsEmergencyContactAddressCache.isEmpty()){
//			AOEmergencyContactAddressCache emergencyContactAddress = lsEmergencyContactAddressCache.get(0);
//			emergencyContactAddress.setNik(null);
//			accOnBoardingForm.setEmergencyContactAddress(new AccountOnBoardingAddress());
//			copyBeanProperties(emergencyContactAddress, accOnBoardingForm.getEmergencyContactAddress(), false);
//		}
//		if(lsEmergencyContactCache!=null && !lsEmergencyContactCache.isEmpty()){
//			AOEmergencyContactCache emergencyContact =  lsEmergencyContactCache.get(0);
//			emergencyContact.setNik(null);
//			accOnBoardingForm.setEmergencyContact(new AccountOnBoardingEmergencyContact());
//			copyBeanProperties(emergencyContact, accOnBoardingForm.getEmergencyContact(), false);
//		}
//		if(lsKPRAdditionalInfoCache!=null && !lsKPRAdditionalInfoCache.isEmpty()){
//			AOKPRAdditionalInfoCache kprAdditionalInfo = lsKPRAdditionalInfoCache.get(0);
//			kprAdditionalInfo.setNik(null);
//			accOnBoardingForm.setKprAdditionalInformation(new AccountOnBoardingKPRAdditionalInfo());
//			copyBeanProperties(kprAdditionalInfo, accOnBoardingForm.getKprAdditionalInformation(), false);
//		}
//		if(lsMailingAddressCache!=null && !lsMailingAddressCache.isEmpty()){
//			AOMailingAddressCache mailingAddress = lsMailingAddressCache.get(0);
//			mailingAddress.setNik(null);
//			accOnBoardingForm.setMailingAddress(new AccountOnBoardingAddress());
//			copyBeanProperties(mailingAddress, accOnBoardingForm.getMailingAddress(), false);
//		}
//		if(lsWorkAddressCache!=null && !lsWorkAddressCache.isEmpty()){
//			AOWorkAddressCache workAddress = lsWorkAddressCache.get(0);
//			workAddress.setNik(null);
//			accOnBoardingForm.setWorkAddress(new AccountOnBoardingAddress());
//			copyBeanProperties(workAddress, accOnBoardingForm.getWorkAddress(), false);
//		}
//		if(lsFinancialInfoCache!=null && !lsFinancialInfoCache.isEmpty()){
//			AOFinancialInfoCache financialInfo = lsFinancialInfoCache.get(0);
//			financialInfo.setNik(null);
//			accOnBoardingForm.setFinancialInformation(new AccountOnBoardingFinancialInfo());
//			copyBeanProperties(financialInfo, accOnBoardingForm.getFinancialInformation(), false);
//		}
//	}
//
//	public static void copyBeanProperties(Object source, Object target, boolean isAccForm){
//		try {
//			Collection<String> excludes = new ArrayList<String>();
//			Field[] sourceFieldList = source.getClass().getDeclaredFields();
//			Field[] targetFieldList = target.getClass().getDeclaredFields();
//
//			Map<String, Field> sourceFieldMap = new HashMap<String, Field>();
//			if (sourceFieldList != null) {
//				for (Field sourceField : sourceFieldList) {
//					sourceFieldMap.put(sourceField.getName(), sourceField);
//				}
//			}
//
//			if (targetFieldList != null) {
//				for (Field targetField : targetFieldList) {
//					Field sourceField = sourceFieldMap.get(targetField.getName());
//
//					if (sourceField != null) {
//						if (!sourceField.getType().equals(targetField.getType())) {
//							excludes.add(targetField.getName());
//						}
//					}
//				}
//			}
//
//
//			if (CollectionUtils.isEmpty(excludes)) {
//				BeanUtils.copyProperties(source, target);
//			} else {
//				BeanUtils.copyProperties(source, target, excludes.toArray(new String[excludes.size()]));
//
//				for (String excludedProperty : excludes) {
//					Field fieldSource = source.getClass().getDeclaredField(excludedProperty);
//					Field fieldTarget = target.getClass().getDeclaredField(excludedProperty);
//
//					fieldSource.setAccessible(true);
//					fieldTarget.setAccessible(true);
//					Object fieldSourceObjVal = fieldSource.get(source);
//
//					if (fieldSourceObjVal != null) { 
//						if (fieldTarget.getType().equals(String.class)) {
//							fieldTarget.set(target, fieldSourceObjVal.toString());
//						} else if (fieldTarget.getType().equals(boolean.class)) {
//							if (fieldSourceObjVal instanceof String) {
//								fieldTarget.set(target, Boolean.parseBoolean(fieldSourceObjVal.toString()));
//							} else if (fieldSourceObjVal instanceof Character) {
//								fieldTarget.set(target, Boolean.valueOf(fieldSourceObjVal.toString()));
//							}
//						} else if (fieldTarget.getType().equals(Boolean.class)) {
//							if (fieldSourceObjVal instanceof String) {
//								fieldTarget.set(target, Boolean.valueOf(fieldSourceObjVal.toString()));
//							}
//							else if (fieldSourceObjVal instanceof Boolean) {
//								fieldTarget.set(target, Boolean.valueOf(fieldSourceObjVal.toString()));
//							}	
//						} else if (fieldTarget.getType().equals(BigDecimal.class)) {
//							if ((fieldSourceObjVal instanceof String) || 
//									(fieldSourceObjVal instanceof Integer)){
//								fieldTarget.set(target, new BigDecimal(fieldSourceObjVal.toString()));
//							}
//						} else if (fieldTarget.getType().equals(Character.class)) {
//							fieldTarget.set(target, (fieldSourceObjVal.toString()).charAt(0));
//						} 
//					}
//
//
//				}
//			}
//			
//			if(isAccForm){
//				Field[] targetFieldList2 = target.getClass().getDeclaredFields();
//				if (targetFieldList2 != null)
//				{
//					for (Field targetField : targetFieldList2)
//					{
//						targetField.setAccessible(true);
//						Object fieldSourceObjVal = targetField.get(target);
//						if (fieldSourceObjVal == null)
//						{
//							if (targetField.getType().equals(String.class))
//							{
//								if(!targetField.getName().equalsIgnoreCase("passCode") && !targetField.getName().equalsIgnoreCase("customerID") 
//										&& !targetField.getName().equalsIgnoreCase("imageDocType") && !targetField.getName().equalsIgnoreCase("cif")
//										&& !targetField.getName().equalsIgnoreCase("accountNumber") && !targetField.getName().equalsIgnoreCase("userId")
//										 && !targetField.getName().equalsIgnoreCase("errorCode")  && !targetField.getName().equalsIgnoreCase("errorDesc")){
//									targetField.set(target, "");
//								}
//								
//							}
//						}
//					}
//				}
//			}
//			else{
//				Field[] targetFieldList2 = target.getClass().getDeclaredFields();
//				if (targetFieldList2 != null)
//				{
//					for (Field targetField : targetFieldList2)
//					{
//						targetField.setAccessible(true);
//						Object fieldSourceObjVal = targetField.get(target);
//						if (fieldSourceObjVal == null)
//						{
//							if (targetField.getType().equals(String.class))
//							{
//								targetField.set(target, "");
//								/*if(!targetField.getName().equalsIgnoreCase("passCode") && !targetField.getName().equalsIgnoreCase("customerID") && !targetField.getName().equalsIgnoreCase("imageDocType") 
//										&& !targetField.getName().equalsIgnoreCase("accountNumber")){
//									targetField.set(target, "");
//								}*/
//								
//							}
//						}
//					}
//				}
//			}
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
//}
//
