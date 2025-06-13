package com.silverlake.mleb.mcb.module.func;


import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import com.fuzion.ws.security.endpoint.RequestSecurityNonceResponse;
import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.mcb.bean.ObAuthenticationRequest;
import com.silverlake.mleb.mcb.bean.ObAuthenticationResponse;
import com.silverlake.mleb.mcb.bean.ObGeneralCodeBean;
import com.silverlake.mleb.mcb.bean.ObHeaderResponse;
import com.silverlake.mleb.mcb.bean.ObMenuListBean;
import com.silverlake.mleb.mcb.bean.ObRequest;
import com.silverlake.mleb.mcb.bean.ObUserContext;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.entity.CustomerProfile;
import com.silverlake.mleb.mcb.entity.DeviceProfile;
import com.silverlake.mleb.mcb.entity.GeneralCode;
import com.silverlake.mleb.mcb.entity.MaintenanceNotification;
import com.silverlake.mleb.mcb.entity.McbConf;
import com.silverlake.mleb.mcb.entity.UnBindDeviceProfile;
import com.silverlake.mleb.mcb.entity.VersionControl;
import com.silverlake.mleb.mcb.entity.dao.CustomerDAO;
import com.silverlake.mleb.mcb.entity.dao.DeviceCIFDao;
import com.silverlake.mleb.mcb.entity.dao.GeneralCodeDAO;
import com.silverlake.mleb.mcb.entity.dao.MLEBMIBDAO;
import com.silverlake.mleb.mcb.entity.dao.MaintenanceNotificationDAO;
import com.silverlake.mleb.mcb.entity.dao.VersionControlDAO;
import com.silverlake.mleb.mcb.module.common.FuncServices;
import com.silverlake.mleb.mcb.module.common.MiBServices;
import com.silverlake.mleb.mcb.module.vc.common.VCRequest;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.common.VCService;
import com.silverlake.mleb.mcb.module.vc.token.Token;
import com.silverlake.mleb.mcb.module.vc.token.TokenRequestData;
import com.silverlake.mleb.mcb.module.vc.token.TokenResponseData;
import com.silverlake.mleb.mcb.module.vc.user.List_device;
import com.silverlake.mleb.mcb.module.vc.user.ResponseData;
import com.silverlake.mleb.mcb.util.PropertiesManager;


@Service
public class RetrieveSecLoginToken extends FuncServices  
{

	private static Logger log = LogManager.getLogger(RetrieveSecLoginToken.class);
	private PropertiesManager pro = new PropertiesManager();

	@Autowired
	MLEBMIBDAO dao;

	@Autowired
	CustomerDAO custDao;
	
	@Autowired
	GeneralCodeDAO gnDao;

	@Autowired ApplicationContext appContext;
	
	@Autowired
	DeviceCIFDao deviceDao;
	
	@Autowired
	VersionControlDAO versionControlDao;
	
	@Autowired MaintenanceNotificationDAO maintenanceNotificationDAO;

	public MICBResponseBean process(MICBRequestBean arg0) {
		// TODO Auto-generated method stub



		MICBResponseBean response = new MICBResponseBean();
		ObAuthenticationResponse custProfileResponse = new ObAuthenticationResponse();
		custProfileResponse.setObHeader(new ObHeaderResponse());
		custProfileResponse.setUserContext(new ObUserContext());

		try {
			//VELO2UAT-466- To return FE a hardcoded value when launch app
			custProfileResponse.setInitializationVector("AAAAAAAAAAAAAAAAAAAAAA==");
			
			MiBServices mibServices = new MiBServices(dao);
			//return module type flag for Front End
			/*
			  	FTO - Fund Transfer Own 
				FTN - Fund Transfer Intra
				FTI - Fund Transfer Inter
				PPR - Payment Purchase Register
				PPN - Payment Purchase Non Register
				PPC - Payment Purchase Credit Card
				UTS - Unit Trust Subscription
				UTR - Unit Trust Redemption
				UTSW - Unit Trust Switching
				UTI - Unit Trust Product Info
				UTT - Unit Trust Term of Transaction
				FTMR - Manage Recurring
				FTMF - Manage Future Transfer
				FTMB - Manage Beneficiary
				PPMA - Manage Autodebit
				PPMF - Manage Future Payment
				PPMP - Manage Payee
				TDO - Time Deposit Opening
				TDM - Time Deposit Maintenance 
				SSN - Smart Notification
				SEP - User Profile Maintenance
				SEL - Language Setting 
				TKA - Customer Service Token Activation
				ITR - Internet Transaction History/Record
				REG - IB/MB Registration Modules 
				AOB = Account On Boarding Module
			 */
			
			/*
			custProfileResponse.setModuleTypeFlag(gnDao.getModuleTypeFlag());
			McbConf mibConf = mibServices.getMcBConf();
			String generalRegex = mibConf.getGeneralRegex();
			String accOnBoardRegex = mibConf.getAoAlphaRegex(); //alphabet regex
			String accOnBoardRegex2 = mibConf.getAoAlphaNumericRegex(); //alphabet&numeric regex
			String accOnBoardNameRegex = mibConf.getAoNameRegex();
			if(generalRegex!=null && !generalRegex.isEmpty())
				custProfileResponse.setRegexCode(generalRegex);
			else
				custProfileResponse.setRegexCode("^[0-9a-zA-Z ,.:+()?-]+?$");
			

			if(accOnBoardRegex!=null && !accOnBoardRegex.isEmpty() && 
					accOnBoardRegex2!=null && !accOnBoardRegex2.isEmpty() &&
					accOnBoardNameRegex!=null && !accOnBoardNameRegex.isEmpty()){
				custProfileResponse.setAoAlphaRegexCode(accOnBoardRegex);
				custProfileResponse.setAoAlphaNumericRegexCode(accOnBoardRegex2);
				custProfileResponse.setAoNameRegex(accOnBoardNameRegex);
			}	
			else{
				custProfileResponse.setAoAlphaRegexCode("^[a-zA-Z, .:;/\\+()?-]+?$");
				custProfileResponse.setAoAlphaNumericRegexCode("^[a-zA-Z0-9, .:;/\\+()?-]+?$");
				custProfileResponse.setAoNameRegex("^[a-zA-Z, .:;/\\+()?-\\[\\]]+?");
			}
			

			custProfileResponse.setStaffDirFlag(gnDao.getStaffDirFlag());
			custProfileResponse.setOcrFlag(gnDao.getOCRFlag());
			
			*/
			
			VCService userService = new VCService(appContext);
			ResponseData vcResponseData = new ResponseData();
			ResponseData vcResponseData_xstring = new ResponseData();
			VCService userAsyncService = new VCService(appContext);
			
			ObRequest obRequest = (ObRequest) arg0.getBDObject();
			String ipAddress = obRequest.getObHeader() == null ? null : obRequest.getObHeader().getIp();
			
			ListenableFuture<ResponseEntity<VCResponse>> futureVCResponse = userAsyncService.callOmniVCAsync(VCMethodConstant.REST_METHODS.USER_X_STRING,new VCRequest(), vcResponseData_xstring, null, arg0.getTranxID(), ipAddress);
			VCResponse<ResponseData> vcResponse = userService.callOmniVC(VCMethodConstant.REST_METHODS.USER_RANDOM_NUMBER,new VCRequest(),vcResponseData, null, arg0.getTranxID(), ipAddress);
			
			
			if(vcResponse.getResponse_code().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS))
			{
				vcResponseData = vcResponse.getData();
				String randomNumber = vcResponseData.getRandom_number();
				
				
				 vcResponse = userAsyncService.getAscyncResponseData(VCMethodConstant.REST_METHODS.USER_X_STRING,futureVCResponse, vcResponseData_xstring, null, arg0.getTranxID());
				
				if(vcResponse.getResponse_code().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS))
				{
					vcResponseData_xstring = vcResponse.getData();
					String x_string = vcResponseData_xstring.getModulus_string();
					String pbk_string = vcResponseData_xstring.getPbk_string();
					String exponent_string = vcResponseData_xstring.getExponent_string();
				 
					custProfileResponse.setxString(x_string);
					custProfileResponse.setRandomNumber(randomNumber);
					custProfileResponse.setPbkString(pbk_string);
					custProfileResponse.setExponentString(exponent_string);
					
					String deviceId = arg0.getDeviceBean()==null?null:arg0.getDeviceBean().getDeviceId();
					log.info("DVID:["+(deviceId==null?"":deviceId)+"]");
					DeviceProfile devProfile = deviceDao.getCIFByDeviceIDAndStatus(deviceId);
					
					boolean primaryBindingFlag = false;
					GeneralCode primaryGnCode = gnDao.findByGnCode(MiBConstant.GNCODE_PRIMARY_DEV_BINDING);
					if(primaryGnCode != null) {
						String desc = primaryGnCode.getGnCodeDescription();
						if(desc != null && desc.equalsIgnoreCase("true")) {
							primaryBindingFlag = true;
						}
					}
					
					if(null!=devProfile)
					{
						//If biometric key is empty, set a biometric key to it
						if(devProfile.getBiometricKey() == null || devProfile.getBiometricKey().isEmpty()) {
							String biometricKey = RandomStringUtils.random(8, false, true);
							devProfile.setBiometricKey(biometricKey);
							deviceDao.updateEntity(devProfile);
						}
						custProfileResponse.setBiometricKey(devProfile.getBiometricKey());
						String cif = devProfile.getCif();
						CustomerProfile custProfile = custDao.getCustomerProfileByCif(cif);
						if(null!=custProfile)
						{
							custProfileResponse.setBinded(true);
							custProfileResponse.setLoginID(custProfile.getLoginId());
							custProfileResponse.setOrgId(custProfile.getOrgId());
							custProfileResponse.setUserId(custProfile.getLoginId());
							custProfileResponse.setFullName(custProfile.getFullName());
							custProfileResponse.setDisplayName(custProfile.getName());
							custProfileResponse.setOrgName(custProfile.getOrgName());
							custProfileResponse.setUsrName(custProfile.getUsrName());
							custProfileResponse.setBindingStatus(devProfile.getStatus());
							
							if (primaryBindingFlag) {
								if(devProfile.getDeviceLevel() == null || devProfile.getDeviceLevel().isEmpty()) {
									// for first time assign device level to all devices under the same user
									// if device level is empty, set device level for all device 
									List<DeviceProfile> listDP = deviceDao.getDeviceByCIF(cif);
									for(DeviceProfile updateDP : listDP) {
										
										if(updateDP.getDeviceLevel() == null) {
											if(deviceDao.isDevicePrimaryDevice(updateDP.getDeviceId(), cif)) {
												updateDP.setDeviceLevel(MiBConstant.MIB_DEVICEPROFILE_LEVEL_PRIMARY);
											} else {
												updateDP.setDeviceLevel(MiBConstant.MIB_DEVICEPROFILE_LEVEL_SECONDARY);
											}
											deviceDao.updateEntity(updateDP);
										}
										
										if(updateDP.getDeviceId().equals(deviceId)) {
											custProfileResponse.setDeviceLevel(updateDP.getDeviceLevel());
										}
									}
								} else {
									custProfileResponse.setDeviceLevel(devProfile.getDeviceLevel());
								}
	
								SimpleDateFormat sdf = new SimpleDateFormat(MiBConstant.DB_DATETIME_FORMAT);
								custProfileResponse.setDateBind(sdf.format(devProfile.getDateBind()));
								if(custProfileResponse.getDateBind() != null && devProfile.getDeviceLevel() != null
										&& (devProfile.getDeviceLevel().equals(MiBConstant.MIB_DEVICEPROFILE_LEVEL_SECONDARY) && devProfile.getApprovalAction() != null && devProfile.getApprovalAction().equalsIgnoreCase(MiBConstant.MIB_DEVICEPROFILE_STATUS_APPROVE)) 
												|| (devProfile.getDeviceLevel().equals(MiBConstant.MIB_DEVICEPROFILE_LEVEL_PRIMARY) && devProfile.getApprovalAction() != null && devProfile.getApprovalAction().equalsIgnoreCase(MiBConstant.MIB_DEVICEPROFILE_STATUS_SYS_APPROVE))
										&& devProfile.getDateSwitch() == null) {
									// cooling period only applicable to newly binded secondary device - exclude switch device process
									// and system approved binded secondary pending device - become primary active device
									log.info("DateBind :: "+custProfileResponse.getDateBind());
									Calendar cal = Calendar.getInstance();
									cal.setTime(devProfile.getDateBind());
									cal.add(Calendar.HOUR_OF_DAY, 12);
									Date coolingPeriod = cal.getTime();
									log.info("pluscoolingPeriod :: "+sdf.format(coolingPeriod));
									Date current = new Date();
									log.info("current :: "+sdf.format(current));
									long countdown = coolingPeriod.getTime() - current.getTime();
									custProfileResponse.setApprovalCountdown(Long.toString(countdown));
									if(Long.signum(countdown) < 0) {
										log.info("countdown ended");
										custProfileResponse.setApprovalCountdown("0");
									}
								}
								
								if(custProfileResponse.getDeviceLevel() != null && custProfileResponse.getDeviceLevel().equalsIgnoreCase(MiBConstant.MIB_DEVICEPROFILE_LEVEL_SECONDARY)) {
									DeviceProfile primaryDeviceProfile = deviceDao.getPrimaryDevice(cif);
									if (primaryDeviceProfile != null) {
										custProfileResponse.setPrimaryDeviceModel(primaryDeviceProfile.getDeviceModel());
									} else {
										// for first time assign device level to all devices under the same user
										// if primary device level is empty, set device level
										List<DeviceProfile> listDP = deviceDao.getDeviceByCIF(cif);
										for(DeviceProfile updatePrimary : listDP) {
											if(updatePrimary.getDeviceLevel() == null) {
												if(deviceDao.isDevicePrimaryDevice(updatePrimary.getDeviceId(), cif)) {
													updatePrimary.setDeviceLevel(MiBConstant.MIB_DEVICEPROFILE_LEVEL_PRIMARY);
													custProfileResponse.setPrimaryDeviceModel(updatePrimary.getDeviceModel());
													deviceDao.updateEntity(updatePrimary);
													break;
												}
											}
										}
									}
								}
							}
						}
						
					} else {
						log.info("Device not binded :: ");
						if(primaryBindingFlag) {
							//for rejected device usage
							ObAuthenticationRequest requestData = (ObAuthenticationRequest) arg0.getBDObject();
							if(requestData != null && requestData.getPrimaryActionFlag() != null && requestData.getPrimaryActionFlag().equalsIgnoreCase("Y")) { 
								// check for rejected device for secondary device
								UnBindDeviceProfile rejectedDev = deviceDao.getRejectedDeviceByDev(deviceId);
								
								if(rejectedDev != null) {
									log.info("Rejected device :: ");
									String cif = rejectedDev.getCif();
									CustomerProfile custProfile = custDao.getCustomerProfileByCif(cif);
									if(null!=custProfile && rejectedDev.getDeviceLevel().equalsIgnoreCase(MiBConstant.MIB_DEVICEPROFILE_LEVEL_SECONDARY))
									{
										custProfileResponse.setBinded(false);
										custProfileResponse.setPrimaryAction(rejectedDev.getStatus());
										custProfileResponse.setDeviceLevel(rejectedDev.getDeviceLevel());
									}
								}
							}
						}
					}
					
					
					
					List<GeneralCode> redirectUrl = gnDao.getRedirectUrlAndMenuAccess();
					List<ObGeneralCodeBean> redirectUrlBean = new ArrayList<ObGeneralCodeBean>();
					List<ObMenuListBean> menu_access_list = new ArrayList<ObMenuListBean>();
					boolean callTokenInquiry = false;
					for(GeneralCode temp:redirectUrl)
					{
						if(temp.getGnCodeType().equalsIgnoreCase("redirectUrl"))
						{
							ObGeneralCodeBean tempBean = new ObGeneralCodeBean();
							BeanUtils.copyProperties(temp, tempBean);
							redirectUrlBean.add(tempBean);
						}
						else
						{
							ObMenuListBean menuTemp = new ObMenuListBean();
							menuTemp.setMenu_title(temp.getGnCodeDescription());
							menuTemp.setMenu_id(temp.getGnCode());
							menu_access_list.add(menuTemp);
							
							if(temp.getGnCode().equalsIgnoreCase("VM.0025")) {
								callTokenInquiry = true;
							}
						}
					}
					
					if(callTokenInquiry && custProfileResponse.getLoginID() != null && custProfileResponse.getOrgId() != null) {
						TokenRequestData vcTokenRequest = new TokenRequestData();
						TokenResponseData vcTokenResponseData = new TokenResponseData();
						vcTokenRequest.setOrg_cd(custProfileResponse.getOrgId());
						vcTokenRequest.setUsr_cd(custProfileResponse.getUserId());
						
						VCService tokenService = new VCService(appContext);
						VCResponse tokenResp = tokenService.callOmniVC(TokenRequestData.method_customer_token_inquiry,vcTokenRequest, vcTokenResponseData, null, arg0.getTranxID(), ipAddress);

						if(tokenResp.getResponse_code().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS))
						{
							vcTokenResponseData = (TokenResponseData) tokenResp.getData();
							if(vcTokenResponseData.getList_token()!=null)
							{
								custProfileResponse.setList_token(new ArrayList());
								custProfileResponse.setPinIB("N");
								
								for (Token temp:vcTokenResponseData.getList_token()) {
									Token tokenList = new Token();
									
									if(temp.getDevice_information() != null && !temp.getDevice_information().isEmpty()) {
										for(List_device device : temp.getDevice_information()) {
											
											if(device.getDevice_id() != null && device.getDevice_id().equals(deviceId)){
												custProfileResponse.setPinIB("Y");
											}
											
											if(device.getExt_channel_list() != null && !device.getExt_channel_list().isEmpty()) {
												for(int i=0;i<device.getExt_channel_list().size();i++) {
													String extChannel = device.getExt_channel_list().get(i);
													if(extChannel.equalsIgnoreCase(MiBConstant.CHANNEL001)) {
														device.getExtChannelListDesc().put(MiBConstant.CHANNEL001, MiBConstant.CHANNEL001_DESC);
														
													} else if (extChannel.equalsIgnoreCase(MiBConstant.CHANNEL002)) {
														device.getExtChannelListDesc().put(MiBConstant.CHANNEL002, MiBConstant.CHANNEL002_DESC);
														
													} else if (extChannel.equalsIgnoreCase(MiBConstant.CHANNEL003)) {
														device.getExtChannelListDesc().put(MiBConstant.CHANNEL003, MiBConstant.CHANNEL003_DESC);
														
													} else if (extChannel.equalsIgnoreCase(MiBConstant.CHANNEL004)) {
														device.getExtChannelListDesc().put(MiBConstant.CHANNEL004, MiBConstant.CHANNEL004_DESC);
														
													} else if (extChannel.equalsIgnoreCase(MiBConstant.CHANNEL005)) {
														device.getExtChannelListDesc().put(MiBConstant.CHANNEL005, MiBConstant.CHANNEL005_DESC);
													}
												}
											}
											
											if(device.getList_appli_no() != null && !device.getList_appli_no().isEmpty()) {
												
												List<String> listAppliNo = device.getList_appli_no();
											    StringBuilder resultAppliNoBuilder = new StringBuilder();
											    
											    for (int i = 0; i < listAppliNo.size(); i++) {
											        resultAppliNoBuilder.append(listAppliNo.get(i));
											        if (i < listAppliNo.size() - 1) {
											            resultAppliNoBuilder.append(",");
											        }
											    }
											    
											    String resultAppliNo = resultAppliNoBuilder.toString();
											    device.setAppliNo(resultAppliNo);
											    device.setList_appli_no(null);
											    
											    if(device.getDevice_id() != null && device.getDevice_id().equals(deviceId)){
											    	custProfileResponse.setTheAppliNo(resultAppliNo);
												}
												
											}
											
										}
									}
									BeanUtils.copyProperties(temp, tokenList);
									custProfileResponse.getList_token().add(tokenList);
								}
							}
							else 
							{
								custProfileResponse.getObHeader().setStatusCode(MiBConstant.MCB_NO_TOKEN_FOUND);
							}
						}
						else
						{
							custProfileResponse.getObHeader().setStatusCode(MiBConstant.MIB_OMNI_PREFIX+tokenResp.getResponse_code());
							custProfileResponse.getObHeader().setStatusMessage(tokenResp.getResponse_desc());
						}
					}
					
					//Get the version info and it's update date time from version_control table
					String type = arg0.getDeviceBean().getType();
					String version = arg0.getHeaderBean().getRequestInfo();
					VersionControl versionControl = versionControlDao.getBy(type, version);
					ObGeneralCodeBean versionObject = new ObGeneralCodeBean();
					ObGeneralCodeBean updateDateObject = new ObGeneralCodeBean();
					ObGeneralCodeBean updateTimeObject = new ObGeneralCodeBean();
					if(type.equalsIgnoreCase("android")) {
						versionObject.setGnCode("version_android");
						updateDateObject.setGnCode("update_date_android");
						updateTimeObject.setGnCode("update_time_android");
					}else {
						versionObject.setGnCode("version_ios");
						updateDateObject.setGnCode("update_date_ios");
						updateTimeObject.setGnCode("update_time_ios");
					}
					if(versionControl != null) {
						String updateDate = "";
						String updateTime = "";
						//MMM dd, yyyy hh:mm:ss a
						SimpleDateFormat sdf = new SimpleDateFormat(MiBConstant.OMNI_DATE_FORMAT);
						if(versionControl.getUpdateDate() != null) {
							updateDate = sdf.format(versionControl.getUpdateDate());
							updateTime = updateDate.substring(13, updateDate.length());
							updateDate = updateDate.substring(0, 12);
						}
						versionObject.setGnCodeDescription(versionControl.getDisplayVersion());
						updateDateObject.setGnCodeDescription(updateDate);
						updateTimeObject.setGnCodeDescription(updateTime);
					} else {
						versionObject.setGnCodeDescription("unknown");
						updateDateObject.setGnCodeDescription("unknown");
						updateTimeObject.setGnCodeDescription("unknown");
					}
					redirectUrlBean.add(versionObject);
					redirectUrlBean.add(updateDateObject);
					redirectUrlBean.add(updateTimeObject);
					
					custProfileResponse.setMenu_access_list(menu_access_list);
					custProfileResponse.setRedirectURLList(redirectUrlBean);
					
					//Get the server maintenance pre-notification if any
					McbConf mibConf = mibServices.getMcBConf();
					int noticeTimeSecond = mibConf.getUpfrontNoticeTime();
					int preNotifyTimeMinute = mibConf.getUpfrontNoticeMaintncTime();
					Calendar currentDateTime = Calendar.getInstance();
					Date currentDate = currentDateTime.getTime();
					currentDateTime.add(Calendar.MINUTE, preNotifyTimeMinute);
					Date preNotiDate = currentDateTime.getTime();
					List<MaintenanceNotification> notifications = maintenanceNotificationDAO.getPreMaintenanceNotification(preNotiDate, currentDate);
					for(MaintenanceNotification notif : notifications) {
						SimpleDateFormat sdf = new SimpleDateFormat(MiBConstant.DB_DATETIME_FORMAT);
						custProfileResponse.getObHeader().setMaintenanceNoticeFlag(true);
						custProfileResponse.getObHeader().setMaintenanceNotice("");
						custProfileResponse.getObHeader().setMaintenanceUpfrontNoticeTime(noticeTimeSecond);
						custProfileResponse.getObHeader().setMaintenanceStartDateTime(sdf.format(notif.getEffectiveStartDt()));
						custProfileResponse.getObHeader().setMaintenanceEndDateTime(sdf.format(notif.getEffectiveEndDt()));
					}
					
					custProfileResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
				}
				else
				{
					custProfileResponse.getObHeader().setStatusCode(MiBConstant.MIB_OMNI_PREFIX+vcResponse.getResponse_code());
				}
				
			}
			else 
			{
				 custProfileResponse.getObHeader().setStatusCode(MiBConstant.MIB_OMNI_PREFIX+
				 vcResponse.getResponse_code()); 
			 
			}
			 
			
			 
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//"ERROR.MIB.9999999"


			log.info(this.getClass().toString(), e);
			custProfileResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			custProfileResponse.getObHeader().setStatusMessage(e.getMessage());
		}

		response.setBDObject(custProfileResponse);

		return response;
	}



	private String tempByPassMposDeployment(String deviceType, String requestInfo){
		int mobileVersion = 0;
		System.out.println("requestInfo :::: "+requestInfo+" ::::");
		System.out.println("deviceType :::: "+deviceType+" ::::");

		if(requestInfo!=null)
			mobileVersion = Integer.parseInt(requestInfo.split("\\.")[0]);


		if(("android".equalsIgnoreCase(deviceType)) && (2 == mobileVersion)) {
			return "false";
		}else if(("ios".equalsIgnoreCase(deviceType)) && (3 == mobileVersion)) {
			return "false";
		}
		return null;
	}





	public ObAuthenticationResponse getRSAData(boolean sec_pic_check, MiBServices mibServices,ObAuthenticationResponse response, String nonce, RequestSecurityNonceResponse securityNonce)
	{

		if(response.getObHeader().getStatusCode().equalsIgnoreCase(MiBConstant.MIB_COMMON_SUCCESS))
		{
			//HlbMiBConf mibConf = mibServices.getMiBConf();
			String exponent = securityNonce.getExponent();
			String modulus = securityNonce.getModulus();
			response.setModulus(modulus);
			response.setExponent(exponent);
			response.getObHeader().setId(nonce);
		}
		return response;
	}

	private static char[] VALID_CHARACTERS =
			"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456879".toCharArray();

	public static String csRandomAlphaNumericString(int numChars) {
		SecureRandom srand = new SecureRandom();
		Random rand = new Random();
		char[] buff = new char[numChars];

		for (int i = 0; i < numChars; ++i) 
		{
			// reseed rand once you've used up all available entropy bits
			if ((i % 10) == 0) 
			{
				rand.setSeed(srand.nextLong()); // 64 bits of random!
			}
			buff[i] = VALID_CHARACTERS[rand.nextInt(VALID_CHARACTERS.length)];
		}
		return new String(buff);
	}
}