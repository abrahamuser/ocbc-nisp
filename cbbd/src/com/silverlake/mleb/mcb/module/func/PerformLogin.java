package com.silverlake.mleb.mcb.module.func;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.fuzion.ws.security.endpoint.LoginResponse;
import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.mcb.bean.ObAccessRestrictionBean;
import com.silverlake.mleb.mcb.bean.ObAuthenticationRequest;
import com.silverlake.mleb.mcb.bean.ObAuthenticationResponse;
import com.silverlake.mleb.mcb.bean.ObHeaderResponse;
import com.silverlake.mleb.mcb.bean.ObMenuItemListBean;
import com.silverlake.mleb.mcb.bean.ObMenuListBean;
import com.silverlake.mleb.mcb.bean.ObRequest;
import com.silverlake.mleb.mcb.bean.ObUserContext;
import com.silverlake.mleb.mcb.bean.ObUserDetail;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.entity.AccessRestriction;
import com.silverlake.mleb.mcb.entity.CustomerProfile;
import com.silverlake.mleb.mcb.entity.CustomerState;
import com.silverlake.mleb.mcb.entity.DeviceProfile;
import com.silverlake.mleb.mcb.entity.GeneralCode;
import com.silverlake.mleb.mcb.entity.McbConf;
import com.silverlake.mleb.mcb.entity.dao.AccessRestrictionDAO;
import com.silverlake.mleb.mcb.entity.dao.CustomerDAO;
import com.silverlake.mleb.mcb.entity.dao.DeviceCIFDao;
import com.silverlake.mleb.mcb.entity.dao.GeneralCodeDAO;
import com.silverlake.mleb.mcb.module.common.FuncServices;
import com.silverlake.mleb.mcb.module.common.MiBServices;
import com.silverlake.mleb.mcb.module.func.deviceBinding.DeviceCIFUnbindAck;
import com.silverlake.mleb.mcb.module.vc.common.VCAsyncResponseBean;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.common.VCService;
import com.silverlake.mleb.mcb.module.vc.user.List_role;
import com.silverlake.mleb.mcb.module.vc.user.Menu_access_list;
import com.silverlake.mleb.mcb.module.vc.user.Menu_item_list;
import com.silverlake.mleb.mcb.module.vc.user.RequestData;
import com.silverlake.mleb.mcb.module.vc.user.ResponseData;
import com.silverlake.mleb.mcb.util.DateUtil;
import com.silverlake.mleb.mcb.util.MapperUtil;
import com.silverlake.mleb.mcb.util.PropertiesManager;

@Service
public class PerformLogin extends FuncServices {

	private static Logger log = LogManager.getLogger(PerformLogin.class);
	// private MessageManager msgPro = new MessageManager();
	private PropertiesManager pro = new PropertiesManager();
	@Autowired ApplicationContext appContext;

	@Autowired CustomerDAO custDao;

	@Autowired DeviceCIFDao deviceDao;

	@Autowired GeneralCodeDAO gnDao;
	
	@Autowired AccessRestrictionDAO accessRestrictionDAO;

	public MICBResponseBean process(MICBRequestBean arg0) {
		// TODO Auto-generated method stub

		MICBResponseBean response = new MICBResponseBean();
		ObAuthenticationResponse loginResponse = new ObAuthenticationResponse();
		loginResponse.setObHeader(new ObHeaderResponse());
		loginResponse.setUserContext(new ObUserContext());

		try {
			String deviceId = arg0.getDeviceBean() == null ? null : arg0.getDeviceBean().getDeviceId();
			String deviceOS = arg0.getDeviceBean() == null ? null : arg0.getDeviceBean().getOs();
			String deviceType = arg0.getDeviceBean() == null ? null
					: arg0.getDeviceBean().getType();
			String deviceModel = arg0.getDeviceBean() == null ? null : arg0.getDeviceBean().getModel();
			ObRequest obRequest = (ObRequest) arg0.getBDObject();
			String ipAddress = obRequest.getObHeader() == null ? null : obRequest.getObHeader().getIp();
			
			LoginResponse ibResponse = new LoginResponse();
			ObAuthenticationRequest requestData = (ObAuthenticationRequest) arg0.getBDObject();
			MiBServices mibServices = new MiBServices(custDao);
			String locale = arg0.getLocaleCode();
			log.info("DVID:["+(deviceId==null?"":deviceId)+"]");
			McbConf mibConf = mibServices.getMcBConf();
			String authType = "MN";
			if(requestData.getLoginType() == null || requestData.getLoginType().isEmpty()) {
				requestData.setLoginType(authType);
			}

			if(ipAddress == null || ipAddress.isEmpty()) {
				ipAddress = requestData.getIp();
			}
			
			VCService usrService = new VCService(appContext);
			RequestData vcLoginRequest = new RequestData();
			vcLoginRequest.setOrg_cd(requestData.getObUser().getOrgId());
			vcLoginRequest.setParam_c(requestData.getcString());
			vcLoginRequest.setParam_p(requestData.getpString());
			vcLoginRequest.setRandom_number(requestData.getRandomNumber());
			vcLoginRequest.setUsr_cd(requestData.getObUser().getUserId());
			vcLoginRequest.setDevice_id(deviceId);
			vcLoginRequest.setDevice_type(deviceModel);
			vcLoginRequest.setDevice_os(deviceOS);
			vcLoginRequest.setAuth_type(requestData.getLoginType());
			vcLoginRequest.setPassword_data_block(requestData.getPasswordDataBlock());
			
			ResponseData vcResponseData = new ResponseData();
			VCResponse<ResponseData> vcResponse = usrService.callOmniVC(VCMethodConstant.REST_METHODS.USER_AUTHENTICATE,
					vcLoginRequest, vcResponseData, null, arg0.getTranxID(), ipAddress);
			
			//login failed
			if (!vcResponse.getResponse_code().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS)) {
				loginResponse.getObHeader().setStatusCode(MiBConstant.MIB_OMNI_PREFIX + vcResponse.getResponse_code());
				loginResponse.getObHeader().setStatusMessage(vcResponse.getResponse_desc());
				response.setBDObject(loginResponse);
				return response;
			}
			
			vcResponseData = vcResponse.getData();
			String checkStateStatusRS = checkUsrStatus_State(vcResponseData.getUser_status(),
					vcResponseData.getUser_state());
			if (checkStateStatusRS != null) {
				loginResponse.getObHeader().setStatusCode(MiBConstant.MIB_OMNI_PREFIX + checkStateStatusRS);
				loginResponse.getObHeader().setStatusMessage(vcResponse.getResponse_desc());
				response.setBDObject(loginResponse);
				return response;
			}
			
			VCGenericService vcService = new VCGenericService(appContext, arg0.getTranxID(), vcResponseData.getSession_id(), ipAddress);
			RequestData vcProfileImageRequest = new RequestData();
			vcProfileImageRequest.setOrg_cd(requestData.getObUser().getOrgId());
			vcProfileImageRequest.setUsr_cd(requestData.getObUser().getUserId());
			ResponseData profileImageResponse = new com.silverlake.mleb.mcb.module.vc.user.ResponseData();
			VCResponse<com.silverlake.mleb.mcb.module.vc.user.ResponseData> vcProfileImgResponse = vcService
					.callOmniVC(VCMethodConstant.REST_METHODS.USER_IMAGE, vcProfileImageRequest, com.silverlake.mleb.mcb.module.vc.user.ResponseData.class, true);
			
			//Fail to get profile image
			if (!vcProfileImgResponse.getResponse_code().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS)) {
				loginResponse.getObHeader().setStatusCode(MiBConstant.MIB_OMNI_PREFIX + vcProfileImgResponse.getResponse_code());
				loginResponse.getObHeader().setStatusMessage(vcResponse.getResponse_desc());
				response.setBDObject(loginResponse);
				return response;
			}

			profileImageResponse = vcProfileImgResponse.getData();
			loginResponse.setProfileImg(profileImageResponse.getProfile_image());

			String usr_domain = vcResponseData.getUsr_domain();
			String dbLoginAccess = mibConf.getLoginLimitAccess();
			// db should configure as : [SB][PR][SO]
			log.info("DB : " + dbLoginAccess);
			log.info("usr_domain : " + usr_domain);
			if (null != dbLoginAccess && dbLoginAccess.indexOf("[" + usr_domain.trim() + "]") < 0) {
				loginResponse.getObHeader().setStatusCode(MiBConstant.MCB_LIMITED_ACCESS);
				response.setBDObject(loginResponse);
				return response;
			}
			

			ObUserContext usrCtx = new ObUserContext();

			usrCtx = requestData.getUserContext();
			usrCtx.setSessionId(vcResponseData.getSession_id());
			usrCtx.setLoginId(requestData.getObUser().getUserId());
			usrCtx.setSecToken(requestData.getNonce());

			loginResponse.setUserContext(usrCtx);

			loginResponse.setObUser(new ObUserDetail());
			loginResponse.getObUser().setFullName(vcResponseData.getUsr_name());
			loginResponse.getObUser().setCifNumber(vcResponseData.getUsr_id());
			loginResponse.getObUser().setUserId(vcResponseData.getUsr_cd());
			loginResponse.getObUser().setLoginId(requestData.getObUser().getUserId());
			loginResponse.getObUser().setOrgId(vcResponseData.getOrg_cd());
			loginResponse.setLoginID(requestData.getObUser().getUserId());
			loginResponse.getObUser().setMobileNumber(vcResponseData.getPhone());
			loginResponse.getObUser().setUsr_state(vcResponseData.getUser_state());
			loginResponse.getObUser().setUsr_status(vcResponseData.getUser_status());
			loginResponse.getObUser().setUsrName(vcResponseData.getUsr_name());
			loginResponse.getObUser().setOrgName(vcResponseData.getOrg_name());
			loginResponse.getObUser().setAddress1(vcResponseData.getOrg_addr1());
			loginResponse.getObUser().setAddress2(vcResponseData.getOrg_addr2());
			loginResponse.getObUser().setAddress3(vcResponseData.getOrg_addr3());
			loginResponse.setRole_list(vcResponseData.getList_role());
			loginResponse.getObUser().setEmail(vcResponseData.getEmail());
			loginResponse.getObUser().setIsEligiblePurchaseForeignCcy(vcResponseData.getPurchase_fcy());
			log.info("vc mobile : " + loginResponse.getObUser().getMobileNumber());

			boolean checkDeviceProfileBinding = true;

			String checkDeviceBindingFlag = pro.getProperty(MiBConstant.DEVICE_BINDING_FLAG);

			if (checkDeviceBindingFlag == null || checkDeviceBindingFlag.equalsIgnoreCase("false")) {
				// no divice binding checking
				checkDeviceProfileBinding = false;
			}

			CustomerProfile customerProfile = custDao
					.getCustomerProfileByCif(vcResponseData.getUsr_id());
			loginResponse.getObUser().setLastLoginDate(null == customerProfile ? "-"
					: DateUtil.formatLastLoginDateTime(customerProfile.getLastLoginDT()));
			loginResponse.getObUser().setLastLogoutDate(null == customerProfile ? "-"
					: DateUtil.formatLastLoginDateTime(customerProfile.getLastLogoutDT()));

			boolean isBinded = false;
			if (checkDeviceProfileBinding) {
				String pnsToken = requestData.getObHeader() == null ? null
						: requestData.getObHeader().getPnsDeviceToken();
				isBinded = checkDeviceBindingStatus(loginResponse, mibConf, deviceId,
						vcResponseData.getUsr_id(), loginResponse.getObUser().getMobileNumber(),
						pnsToken);
			}

			String accessIdList = getAccessId(vcResponseData.getMenu_access_list());
			List<ObMenuListBean> menuListBeanList = new ArrayList<ObMenuListBean>();
			if(vcResponseData.getMenu_access_list() != null){
				for(Menu_access_list menu_access_list:vcResponseData.getMenu_access_list()){
					ObMenuListBean menuListBean = new ObMenuListBean();
					MapperUtil.copyFields(menu_access_list, menuListBean);
					menuListBean.setMenu_item_list(new ArrayList<ObMenuItemListBean>());
					for(Menu_item_list menu_item_list:menu_access_list.getMenu_item_list()){
						ObMenuItemListBean obMenuItemListBean = new ObMenuItemListBean();
						MapperUtil.copyFields(menu_item_list, obMenuItemListBean);
						menuListBean.getMenu_item_list().add(obMenuItemListBean);
					}
					menuListBeanList.add(menuListBean);
				}
			}
			mapMenuDescription(menuListBeanList, locale);
			loginResponse.setMenu_access_list(menuListBeanList);
			loginResponse.getObUser().setAccessId(accessIdList);
			loginResponse.setUsr_state(vcResponseData.getUser_state());
			loginResponse.setMobileNo(vcResponseData.getPhone());

			mibServices.addCustProfile(loginResponse.getObUser(), new Date());
			
			//Check access restriction
//			if (loginResponse.getObHeader().getStatusCode() == null) {
				//Initialize async rest template with concurrency limit
				vcService.initAsyncRestTemplate();
				List<VCAsyncResponseBean<com.silverlake.mleb.mcb.module.vc.user.RequestData, VCResponse>> futureVCResponse = new ArrayList<VCAsyncResponseBean<com.silverlake.mleb.mcb.module.vc.user.RequestData, VCResponse>>();
				
				ObAccessRestrictionBean accessRestrictionParentNode = new ObAccessRestrictionBean();
				accessRestrictionParentNode.setMenuId("0");
				//Generate menu access set (Exist means allowed access)
				Set<String> menuAccessList = null;
				if(accessIdList != null && !accessIdList.isEmpty()){
					String[] tempList = accessIdList.split(",");
					menuAccessList = new HashSet<String>(Arrays.asList(tempList));
				}else{
					menuAccessList = new HashSet<String>();
				}
				//Generate role set (Exist means allowed access)
				Set<String> roleList = new HashSet<String>();
				if(vcResponseData.getList_role() != null){
					for(List_role list_role:vcResponseData.getList_role()){
						if(list_role.getRole_cd()!=null){
							roleList.add(list_role.getRole_cd().toUpperCase());
						}
						if(list_role.getRole_base()!=null){
							roleList.add(list_role.getRole_base().toUpperCase());
						}
					}
				}
				//Generate blocking list (Exist means allowed access)
				List<GeneralCode> moduleBlockingList = gnDao.getRedirectUrlAndMenuAccess();
				Set<String> blockList = new HashSet<String>();
				if(moduleBlockingList != null){
					for(GeneralCode blockItem:moduleBlockingList){
						blockList.add(blockItem.getGnCode());
					}
				}
				
				initMenuList(requestData, vcService, accessRestrictionParentNode, futureVCResponse, blockList, roleList, menuAccessList);
				
				Set<String> accessProhibitedList = new HashSet<String>();
				for(VCAsyncResponseBean<com.silverlake.mleb.mcb.module.vc.user.RequestData, VCResponse> vcAsyncResponseBean:futureVCResponse){
					VCResponse<com.silverlake.mleb.mcb.module.vc.user.ResponseData> vcAccessRetrictionResponse = vcService.getAscyncResponseData(VCMethodConstant.REST_METHODS.USER_ACCESS_RESTRICTION.getUri(), vcAsyncResponseBean, com.silverlake.mleb.mcb.module.vc.user.ResponseData.class);
					if(!vcAccessRetrictionResponse.getResponse_code().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS)){
						loginResponse.getObHeader().setStatusCode(MiBConstant.MIB_OMNI_PREFIX+vcAccessRetrictionResponse.getResponse_code());
						loginResponse.getObHeader().setStatusMessage(vcAccessRetrictionResponse.getResponse_desc());
						response.setBDObject(loginResponse);
						return response;
					}
					com.silverlake.mleb.mcb.module.vc.user.ResponseData responseData = vcAccessRetrictionResponse.getData();
					if(responseData != null && responseData.getList_restriction() != null){
						for(String ar:responseData.getList_restriction()){
							if(ar.equalsIgnoreCase("mn.act.new")){
								accessProhibitedList.add(vcAsyncResponseBean.getRequestData().getMn_itm_id());
								break;
							}
						}
					}
				}
				processMenuList( accessRestrictionParentNode, accessProhibitedList);
				List<ObAccessRestrictionBean> accessRestrictionList = new ArrayList<ObAccessRestrictionBean>();
				convertToFlatList(accessRestrictionParentNode, accessRestrictionList);
				loginResponse.setAccessRestrictionList(accessRestrictionList);
//			}

			// final success process
			if (loginResponse.getObHeader().getStatusCode() == null) {
				loginResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
				// log.info("img :
				// "+loginResponse.getProfileImg());

				DeviceCIFUnbindAck deviceUnbind = new DeviceCIFUnbindAck();
				String devicePushToken = requestData.getObHeader() == null ? null
						: requestData.getObHeader().getPnsDeviceToken();
				//VELO3UAT-59 - use db push token if FE didn't pass in
				if(devicePushToken == null || devicePushToken.isEmpty()) {
					//Take from db if FE didn't pass the push token.
					DeviceProfile deviceProfile = deviceDao.getDevProfByDeviceIDAndCif(deviceId, vcResponseData.getUsr_id());
					if(deviceProfile != null && deviceProfile.getPnsToken() != null && !deviceProfile.getPnsToken().isEmpty()) {
						devicePushToken = deviceProfile.getPnsToken();
					}
				}
				//VELO2UAT-26
				//When perform device binding, need to pass in A in param action_cd.
				//When normal login without binding, need to pass in U in param action_cd.
				String actionCd = "A";
				if(isBinded){
					actionCd = "U";
				}
				deviceUnbind.sendDeviceInfo(appContext, actionCd, vcResponseData.getOrg_cd(),
						vcResponseData.getUsr_cd(), deviceId, deviceOS, deviceType, deviceModel, devicePushToken,
						requestData.getUserContext().getSessionId(), arg0.getTranxID(), requestData.getUserContext().getLocaleCode(), null, null);
			}

			loginResponse.setSessionDuration(mibConf.getSessionDuration());

			// check tnc flag
			String tncContent = showTNCFlag(vcResponseData.getUsr_id(), arg0.getLocaleCode());
			if (null != tncContent) {
				loginResponse.getObUser().setTncFlag(true);
				loginResponse.setTncContent(tncContent);
			}

			// check password rule
			loginResponse.setPassword_rules(vcResponseData.getPassword_rules());

			// loginResponse =
			// checkSessionAndMaintenance(mibServices,loginResponse,arg0.getLocaleCode(),
			// deviceId,mibConf);

			// mibServices = new MiBServices(custDao);
			// updateDevicePNSToken(mibServices, loginResponse, requestData,
			// arg0.getDeviceBean());

			// loginResponse = checkPNSFlag(arg0.getDeviceBean().getDeviceId(),
			// arg0.getDeviceBean().getType(), loginResponse);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			// "ERROR.MIB.9999999"

			log.info(this.getClass().toString(), e);
			loginResponse = new ObAuthenticationResponse();
			loginResponse.setObHeader(new ObHeaderResponse());
			loginResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			loginResponse.getObHeader().setStatusMessage(e.getMessage());

		}

		response.setBDObject(loginResponse);

		return response;
	}

	/**
	 * Check binding status of device
	 * @param response
	 * @param mibConf
	 * @param deviceId
	 * @param cif
	 * @param mobileNo
	 * @param pnsToken
	 * @return true if device is binded
	 */
	public boolean checkDeviceBindingStatus(ObAuthenticationResponse response, McbConf mibConf,
			String deviceId, String cif, String mobileNo, String pnsToken) {
		boolean isBinded = false;
		GeneralCode gnRs = gnDao.findByGnCode(MiBConstant.MAXDEVICEBINDKEY);

		int maxLimit = gnRs == null ? 1 : Integer.parseInt(gnRs.getGnCodeDescription());

		List<DeviceProfile> deviceList = deviceDao.getDeviceByCIF(cif);
		
		if (deviceList.size() == 0 && (mobileNo == null || mobileNo.trim().length() == 0)) {
			response.getObHeader().setStatusCode(MiBConstant.MCB_UNTAGGED_DEVICE_AND_NO_MOBILE);
		} else if (deviceList.size() == 0) {
			response.getObHeader().setStatusCode(MiBConstant.MCB_UNTAGGED_DEVICE);
		} else {

			boolean deviceBinded = false;
			DeviceProfile bindedDevice = null;
			for (DeviceProfile temp : deviceList) {
				if (temp.getDeviceId().equalsIgnoreCase(deviceId)) {
					bindedDevice = temp;
					deviceBinded = true;
					break;
				}
			}

			if (!deviceBinded && deviceList.size() >= maxLimit && (mobileNo == null || mobileNo.trim().length() == 0)) {
				response.getObHeader().setStatusCode(MiBConstant.MCB_UNTAGGED_DEVICE_AND_EXCEED_LIMIT_AND_NO_MOBILE);
			} else if (!deviceBinded && deviceList.size() >= maxLimit) {
				response.getObHeader().setStatusCode(MiBConstant.MCB_UNTAGGED_DEVICE_AND_EXCEED_LIMIT);
			} else if (!deviceBinded && (mobileNo == null || mobileNo.trim().length() == 0)) {
				response.getObHeader().setStatusCode(MiBConstant.MCB_UNTAGGED_DEVICE_AND_NO_MOBILE);
			} else if (!deviceBinded) {
				response.getObHeader().setStatusCode(MiBConstant.MCB_UNTAGGED_DEVICE);
			} else {
				// binded device
				isBinded = true;
				if (null != pnsToken && pnsToken.trim().length() > 0) {
					bindedDevice.setPnsToken(pnsToken);
					deviceDao.updateEntity(bindedDevice);
				}

			}
		}

		return isBinded;
	}

	public String showTNCFlag(String cif, String locale) throws IOException {

		PropertiesManager proMgr = new PropertiesManager();

		GeneralCode gn = gnDao.getTnC(MiBConstant.TNC_GENERAL_CODE);

		String TnCFile = gn == null ? null : gn.getGnCodeDescription();
		TnCFile = TnCFile == null ? null : (TnCFile.trim().length() == 0 ? null : TnCFile);

		if (null != TnCFile) {

			List<CustomerState> custStateList = custDao.getCustomerStateTnCByAction(cif, TnCFile,
					MiBConstant.CUSTOMER_STATE_TNC_ACCEPT_ACTION);
			if (null == custStateList || custStateList.size() == 0) {

				String appPropertyPath = System.getProperty(PropertiesManager.PROPERTIES_SYSTEM_PATH_NAME);
				locale = locale == null ? MiBConstant.LOCALE_EN
						: (locale.trim().length() == 0 ? MiBConstant.LOCALE_EN : locale);
				String fileLoc = "";
				if (locale.equalsIgnoreCase(MiBConstant.LOCALE_EN)) {
					fileLoc = "file:" + appPropertyPath + "/tnc/" + MiBConstant.TNC_GENERAL_CODE + "/" + TnCFile;
				} else if(locale.equalsIgnoreCase(MiBConstant.LOCALE_CN) || locale.equalsIgnoreCase(MiBConstant.LOCALE_CN_IOS)){
					fileLoc = "file:" + appPropertyPath + "/tnc/" + MiBConstant.TNC_GENERAL_CODE + "/" + TnCFile + "_"
							+ "cn";
				}else {
					fileLoc = "file:" + appPropertyPath + "/tnc/" + MiBConstant.TNC_GENERAL_CODE + "/" + TnCFile + "_"
							+ locale.toLowerCase();
				}

				InputStream inx = appContext.getResource(fileLoc).getInputStream();
				byte[] dataFile = IOUtils.toByteArray(inx);

				String base64String = Base64.encodeBase64String(dataFile);
				String TNCBase64File = base64String;
				inx.close();
				return TNCBase64File;
			}

		}

		return null;
	}

	public ObAuthenticationResponse checkSessionAndMaintenance(MiBServices mibServices,
			ObAuthenticationResponse authenticationResponse, String locale, String deviceId, McbConf mibConf)
			throws ParseException

	{

		// maintenanceNoticeTime = in minutes
		if (authenticationResponse.getObHeader().getStatusCode().equalsIgnoreCase(MiBConstant.MIB_COMMON_SUCCESS)
				|| authenticationResponse.getObHeader().getStatusCode()
						.equalsIgnoreCase(MiBConstant.MIB_STATUSCODE_UNTAGGED_DEVICE)) {

			// McbConf mibConf = mibServices.getMcBConf();
			int sessionDurationMinute = mibConf.getSessionDuration();
			int upfrontNoticeSecond = mibConf.getUpfrontNoticeTime();
			int maintenanceNoticeTime = mibConf.getUpfrontNoticeMaintncTime();
			// login session upfront notice and session duration
			authenticationResponse.setUpfrontNoticeTime(upfrontNoticeSecond);
			authenticationResponse.setSessionDuration(sessionDurationMinute * 60);

			String[] checkServer = mibServices.getMaintenanceNotification(maintenanceNoticeTime, deviceId);

			if (null == checkServer) {
				authenticationResponse.getObHeader().setMaintenanceNotice(null);
			} else {
				boolean checkNotice = checkServer[0].equalsIgnoreCase("true") ? true : false;

				String maintenanceMsg = "";// msgPro.getProperty(MiBConstant.MAINTENANCE_MESSAGE,
											// locale);
				// 2013-12-05 22:00
				// HH:mm to hh:mm a

				SimpleDateFormat formatFrom = new SimpleDateFormat("HH:mm");
				SimpleDateFormat formatto = new SimpleDateFormat("hh:mm a");

				Date startTimex1 = formatFrom.parse(checkServer[1].substring(11));
				String startTimeString1 = formatto.format(startTimex1);

				Date startTimex2 = formatFrom.parse(checkServer[2].substring(11));
				String startTimeString2 = formatto.format(startTimex2);

				maintenanceMsg = maintenanceMsg.replaceAll("\\{0\\}", startTimeString1);
				maintenanceMsg = maintenanceMsg.replaceAll("\\{1\\}", startTimeString2);
				/* temporary disable maintenance notice */
				authenticationResponse.getObHeader().setMaintenanceNoticeFlag(checkNotice);
				authenticationResponse.getObHeader().setMaintenanceNotice(maintenanceMsg);

				authenticationResponse.getObHeader().setMaintenanceUpfrontNoticeTime(maintenanceNoticeTime);
				authenticationResponse.getObHeader().setMaintenanceStartDateTime(checkServer[1]);
				authenticationResponse.getObHeader().setMaintenanceEndDateTime(checkServer[2]);

				// end

			}

			// RESET CC ERROR Message indicator
			/*
			 * if(null!=mibConf.getDescription() &&
			 * mibConf.getDescription().startsWith(MiBConstant.RESET_ERROR_MSG))
			 * { //SimpleDateFormat dbFormat = new SimpleDateFormat(
			 * "yyyy-MM-dd HH:mm:ss");
			 * if(mibServices.updateResetMsgCheck(mibConf.getDescription(),
			 * mibConf.getUpdateDate())) {
			 * authenticationResponse.getObHeader().setServiceMsg(MiBConstant.
			 * RESET_ERROR_MSG); }
			 * 
			 * }
			 */
		}

		return authenticationResponse;
	}

	public String getAccessId(List<Menu_access_list> menuList) {
		String id = "";
		for (Menu_access_list tmp : menuList) {

			id = id + "," + tmp.getMenu_id();

			String subIds = "";
			if (tmp.getMenu_item_list() != null) {
				for (Menu_item_list subTmp : tmp.getMenu_item_list()) {
					subIds = subIds + "," + subTmp.getMenu_item_id();
				}

			}

			id = id + subIds;
		}

		log.info("All menu id : " + id);

		return id;
	}

	public String checkUsrStatus_State(String status, String state) throws Exception {

		List<GeneralCode> blockRs = gnDao.getAllBlockStatusState();

		for (GeneralCode tmp : blockRs) {

			if (null != status && status.equalsIgnoreCase(tmp.getGnCode())) {
				return status;
			} else if (null != state && state.equalsIgnoreCase(tmp.getGnCode())) {
				return state;
			}

		}

		return null;
	}
	
	private void initMenuList(ObAuthenticationRequest requestBean, VCGenericService vcService, ObAccessRestrictionBean accessRestrictionParentNode, List<VCAsyncResponseBean<com.silverlake.mleb.mcb.module.vc.user.RequestData, VCResponse>> futureVCResponse, Set<String> blockList, Set<String> roleList, Set<String> menuAccessList) throws Exception{
		accessRestrictionParentNode.setChilds(new ArrayList<ObAccessRestrictionBean>());
		if(accessRestrictionParentNode.getIsHide() != null && accessRestrictionParentNode.getIsHide()){
			//if parent node is hide, ignore the child (defaulted to hide)
			return;
		}
		List<AccessRestriction> accessRestrictionList = accessRestrictionDAO.getMenuList(accessRestrictionParentNode.getMenuId());
		for(AccessRestriction accessRestriction:accessRestrictionList){
			ObAccessRestrictionBean accessRestrictionNode = new ObAccessRestrictionBean();
			accessRestrictionNode.setMenuId(accessRestriction.getMenuId());
			accessRestrictionNode.setIsHide(false);
			accessRestrictionNode.setMenuDescription(accessRestriction.getMenuDescription());
			if(accessRestriction.getBlockValidate() == 1 && !blockList.contains(accessRestriction.getMenuId())){
				//Module blocking, if module not exist hide the menu
				accessRestrictionNode.setIsHide(true);
			}else if(accessRestriction.getRoleValidate() == 1 && accessRestriction.getRoleType() != null){
				//Role checking, if role not exist hide the menu
				String[] roleTypes = accessRestriction.getRoleType().toUpperCase().split(",");
				boolean isRoleMatched = false;
				for(String roleType:roleTypes) {
					if(roleList.contains(roleType.trim())) {
						isRoleMatched = true;
						break;
					}
				}
				if(!isRoleMatched) {
					accessRestrictionNode.setIsHide(true);
				}
			}else if(accessRestriction.getRoleValidate() == 2 && accessRestriction.getRoleType() != null){
				//Role checking, if role exist hide the menu
				String[] roleTypes = accessRestriction.getRoleType().toUpperCase().split(",");
				boolean isRoleMatched = false;
				for(String roleType:roleTypes) {
					if(roleList.contains(roleType.trim())) {
						isRoleMatched = true;
						break;
					}
				}
				if(isRoleMatched) {
					accessRestrictionNode.setIsHide(true);
				}
			}else if(accessRestriction.getMenuValidate() == 1){
				//Menu access checking, if menu id not exist hide the menu
				//Menu validate id is separated by delimiter of comma. It might contained more than 1 id to check.
				//At least 1 menu id is matched to unhide the menu.
				boolean isHide = true;
				if(accessRestriction.getMenuValidateId() != null && !accessRestriction.getMenuValidateId().isEmpty()){
					String[] menuIds = accessRestriction.getMenuValidateId().split(",");
					for(String menuId:menuIds) {
						if(menuAccessList.contains(menuId)) {
							isHide = false;
							break;
						}
					}
				}else {
					isHide = false;
				}
				accessRestrictionNode.setIsHide(isHide);
			}else if(accessRestriction.getAccessValidate() == 1){
				triggerAccessRestrictionCall(requestBean, accessRestrictionNode.getMenuId(), vcService, futureVCResponse);
			}
			
			if (!accessRestriction.getMenuId().startsWith("VM")) {
				if (!menuAccessList.contains(accessRestriction.getMenuId())) {
					boolean isHide = true;
					accessRestrictionNode.setIsHide(isHide);
				}
			}
			
			initMenuList(requestBean, vcService, accessRestrictionNode, futureVCResponse, blockList, roleList, menuAccessList);
			accessRestrictionParentNode.getChilds().add(accessRestrictionNode);
		}
	}
	
	private void processMenuList(ObAccessRestrictionBean accessRestrictionParentNode, Set<String> prohibitAccessList) throws Exception{
		if(accessRestrictionParentNode.getChilds().size() > 0 ){
			boolean isParentHide = true;
			for(ObAccessRestrictionBean accessRestrictionNode:accessRestrictionParentNode.getChilds()){
				//Ignore futher checking if it is hidden
				if(accessRestrictionNode.getIsHide()){
					continue;
				}
				//Prohibited, set to hide
				if(prohibitAccessList.contains(accessRestrictionNode.getMenuId())){
					accessRestrictionNode.setIsHide(true);
				}else {//Only check it's child when it is unhide
					processMenuList(accessRestrictionNode, prohibitAccessList);
					if(!accessRestrictionNode.getIsHide()){
						isParentHide = false;
					}
				}
			}
			if(isParentHide){
				accessRestrictionParentNode.setIsHide(true);
			}
		}
	}
	
	private void convertToFlatList(ObAccessRestrictionBean accessRestrictionParentNode, List<ObAccessRestrictionBean> accessRestrictionList) throws Exception{
		if(accessRestrictionParentNode.getChilds().size() > 0 ){
			for(ObAccessRestrictionBean accessRestrictionNode:accessRestrictionParentNode.getChilds()){
				convertToFlatList(accessRestrictionNode, accessRestrictionList);
			}
		}
		if(accessRestrictionParentNode.getIsHide() != null && accessRestrictionParentNode.getIsHide()){
			ObAccessRestrictionBean node = new ObAccessRestrictionBean();
			node.setMenuId(accessRestrictionParentNode.getMenuId());
			node.setMenuDescription(accessRestrictionParentNode.getMenuDescription());
			node.setIsHide(accessRestrictionParentNode.getIsHide());
			accessRestrictionList.add(node);
		}
	} 
	
	private void triggerAccessRestrictionCall(ObAuthenticationRequest requestBean, String menuItemId, VCGenericService vcService, List<VCAsyncResponseBean<com.silverlake.mleb.mcb.module.vc.user.RequestData, VCResponse>> futureVCResponse) throws Exception{
		//Check access retriction
		com.silverlake.mleb.mcb.module.vc.user.RequestData vcAccessRetrictionRequest = new com.silverlake.mleb.mcb.module.vc.user.RequestData();
		vcAccessRetrictionRequest.setOrg_cd(requestBean.getObUser().getOrgId());
		vcAccessRetrictionRequest.setUsr_cd(requestBean.getObUser().getLoginId());
		vcAccessRetrictionRequest.setMn_itm_id(menuItemId);
		VCAsyncResponseBean<com.silverlake.mleb.mcb.module.vc.user.RequestData, VCResponse> vcAccessRetrictionResponse = vcService.callOmniVCAsync(
				VCMethodConstant.REST_METHODS.USER_ACCESS_RESTRICTION,
				vcAccessRetrictionRequest,
				true,
				menuItemId);
		futureVCResponse.add(vcAccessRetrictionResponse);
	}
	
	private void mapMenuDescription(List<ObMenuListBean> menuList, String locale){
		if(menuList != null){
			for(ObMenuListBean menu:menuList){
				String title = RetrieveAppStatInfo_mcb.getMessageTable(MiBConstant.LANG_EN).get("MENU_LIST_TITLE_"+menu.getMenu_id());
				String titleId = RetrieveAppStatInfo_mcb.getMessageTable(MiBConstant.LANG_IN).get("MENU_LIST_TITLE_"+menu.getMenu_id());
				String titleCn = RetrieveAppStatInfo_mcb.getMessageTable(MiBConstant.LANG_CN).get("MENU_LIST_TITLE_"+menu.getMenu_id());
				if(title != null){
					String description = RetrieveAppStatInfo_mcb.getMessageTable(MiBConstant.LANG_EN).get("MENU_LIST_DESC_"+menu.getMenu_id());
					String descriptionIN = RetrieveAppStatInfo_mcb.getMessageTable(MiBConstant.LANG_IN).get("MENU_LIST_DESC_"+menu.getMenu_id());
					String descriptionCN = RetrieveAppStatInfo_mcb.getMessageTable(MiBConstant.LANG_CN).get("MENU_LIST_DESC_"+menu.getMenu_id());

					menu.setMenu_title(title);
					menu.setMenu_title_id(titleId);
					menu.setMenu_desc_cn(titleCn);
					menu.setMenu_desc(description); 
					menu.setMenu_desc_id(descriptionIN);
					menu.setMenu_desc_cn(descriptionCN);
				}
				if(menu.getMenu_item_list() != null){
					for(ObMenuItemListBean subMenu:menu.getMenu_item_list()){
						title = RetrieveAppStatInfo_mcb.getMessageTable(MiBConstant.LANG_EN).get("MENU_LIST_TITLE_"+subMenu.getMenu_item_id());
						titleId = RetrieveAppStatInfo_mcb.getMessageTable(MiBConstant.LANG_IN).get("MENU_LIST_TITLE_"+subMenu.getMenu_item_id());
						titleCn = RetrieveAppStatInfo_mcb.getMessageTable(MiBConstant.LANG_CN).get("MENU_LIST_TITLE_"+subMenu.getMenu_item_id());
						if(title != null){
							String description = RetrieveAppStatInfo_mcb.getMessageTable(MiBConstant.LANG_EN).get("MENU_LIST_DESC_"+subMenu.getMenu_item_id());
							String descriptionIN = RetrieveAppStatInfo_mcb.getMessageTable(MiBConstant.LANG_IN).get("MENU_LIST_DESC_"+subMenu.getMenu_item_id());
							String descriptionCN = RetrieveAppStatInfo_mcb.getMessageTable(MiBConstant.LANG_CN).get("MENU_LIST_DESC_"+subMenu.getMenu_item_id());
							subMenu.setMenu_item_title(title);
							subMenu.setMenu_item_title_id(title);
							subMenu.setMenu_item_title_cn(title);
							subMenu.setMenu_item_desc(description);
							subMenu.setMenu_item_desc_id(descriptionIN);
							subMenu.setMenu_item_desc_cn(descriptionCN);
						}
					}
				}
			}
		}
	}
}