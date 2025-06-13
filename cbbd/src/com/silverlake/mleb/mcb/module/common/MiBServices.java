package com.silverlake.mleb.mcb.module.common;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fuzion.ws.account.endpoint.AccountVO;
import com.fuzion.ws.customer.endpoint.CustomerVO;
import com.silverlake.micb.core.bean.DeviceBean;
import com.silverlake.mleb.mcb.bean.ObAccountBean;
import com.silverlake.mleb.mcb.bean.ObAccountMaintenanceConfResponse;
import com.silverlake.mleb.mcb.bean.ObAccountMaintenanceConfResponseBean;
import com.silverlake.mleb.mcb.bean.ObAccountMaintenanceListBean;
import com.silverlake.mleb.mcb.bean.ObAccountMaintenanceListResponse;
import com.silverlake.mleb.mcb.bean.ObAccountOverviewBean;
import com.silverlake.mleb.mcb.bean.ObAccountOverviewResponse;
import com.silverlake.mleb.mcb.bean.ObAccountSpecificBean;
import com.silverlake.mleb.mcb.bean.ObAccountSummaryListResponse;
import com.silverlake.mleb.mcb.bean.ObCreditCardBean;
import com.silverlake.mleb.mcb.bean.ObDeviceProfileBean;
import com.silverlake.mleb.mcb.bean.ObHeaderRequest;
import com.silverlake.mleb.mcb.bean.ObResponse;
import com.silverlake.mleb.mcb.bean.ObTimeDepositConfResponse;
import com.silverlake.mleb.mcb.bean.ObUserContext;
import com.silverlake.mleb.mcb.bean.ObUserDetail;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.entity.CustomerAccOrder;
import com.silverlake.mleb.mcb.entity.CustomerProfile;
import com.silverlake.mleb.mcb.entity.GeneralCode;
import com.silverlake.mleb.mcb.entity.MaintenanceNotification;
import com.silverlake.mleb.mcb.entity.McbConf;
import com.silverlake.mleb.mcb.entity.MessageProperties;
import com.silverlake.mleb.mcb.entity.MessagePropertiesI18n;
import com.silverlake.mleb.mcb.entity.dao.CustomerDAO;
import com.silverlake.mleb.mcb.entity.dao.DAO;
import com.silverlake.mleb.mcb.util.EhcacheSession;
import com.silverlake.mleb.mcb.util.MessageManager;
import com.silverlake.mleb.mcb.util.PropertiesManager;

import comx.silverlake.mleb.mcb.entity.DeviceProfile;
import comx.silverlake.mleb.mcb.entity.RootedDevices;
import net.sf.ehcache.Element;

public class MiBServices
{
	private static Logger log = LogManager.getLogger(MiBServices.class);
	private PropertiesManager pro = new PropertiesManager();
	EhcacheSession ehcahce = new EhcacheSession();

	DAO dao;

	public MiBServices(DAO dao)
	{
		this.dao = dao;
	}

	public ObUserDetail mergedCustmerVo2UserDetails(String loginID, CustomerVO custVO)
	{
		ObUserDetail obUserDetail = new ObUserDetail();

		obUserDetail.setCifNumber(custVO.getCifId());
		obUserDetail.setFullName(custVO.getFullName());
		obUserDetail.setIdentificationNumber(custVO.getIdNumber());
		obUserDetail.setIdentificationType(custVO.getIdTypeCode());
		obUserDetail.setMach(custVO.isIsMach());
		obUserDetail.setMobileNumber(custVO.getMobileNumber());
		obUserDetail.setTncFlag(custVO.isTncFlag());
		obUserDetail.setLoginId(loginID);

		return obUserDetail;
	}

	public boolean updateDeviceTagging(ObUserDetail userBean, DeviceBean deviceBean,
			ObHeaderRequest obHeader)
	{

		String sql = "FROM " + CustomerProfile.class.getSimpleName()
				+ " WHERE cif = :cif AND status = '" + MiBConstant.MIB_CUSTPROFILE_STATUS_ACTIVE
				+ "' ";
		Hashtable params = new Hashtable();
		params.put("cif", userBean.getCifNumber());

		String deviceIdFull = deviceBean.getDeviceId();
		String deviceIdConc = "";

		String didSql = "FROM " + DeviceProfile.class.getSimpleName() + " WHERE deviceId = :didf";
		Hashtable params1 = new Hashtable();
		params1.put("didf", deviceIdFull);
		if (deviceIdFull.indexOf("-IMAC-") > 0)
		{
			deviceIdConc = deviceIdFull.substring(0, deviceIdFull.indexOf("-IMAC-"));
			didSql = didSql + " OR deviceId = :didc";
			params1.put("didc", deviceIdConc);
		}

		List<DeviceProfile> deviceProfiles = dao.selectQueryParam(didSql, params1);

		if (deviceProfiles != null && deviceProfiles.size() > 0)
		{
			return false;
		}

		CustomerProfile custProfile = (CustomerProfile) dao.findByID(CustomerProfile.class,
				userBean.getCifNumber());

		DeviceProfile deviceProfile = new DeviceProfile();
		deviceProfile.setDateTagged(new Date());
		deviceProfile.setDeviceId(deviceBean.getDeviceId());
		deviceProfile.setDeviceModel(deviceBean.getModel());
		deviceProfile.setDeviceType(deviceBean.getType());
		deviceProfile.setCustomerProfile(custProfile);
		deviceProfile.setOs(deviceBean.getOs());
		deviceProfile.setStatus(MiBConstant.MIB_DEVICEPROFILE_STATUS_ACTIVE);

		String pnsDeviceTypes = pro.getProperty("pns.device.types");
		if (pnsDeviceTypes != null && !pnsDeviceTypes.equals(""))
		{
			log.info("---------UPDATE PNS TOKEN TAGGING-------");
			if (pnsDeviceTypes.contains(deviceBean.getType().toLowerCase()))
			{

				log.info("is device token = null?" + obHeader.getPnsDeviceToken());
				deviceProfile.setIsPushNotifEnabled("Y");
				if (obHeader != null && obHeader.getPnsDeviceToken() != null
						&& !obHeader.getPnsDeviceToken().equals(""))
				{

					deviceProfile.setPnsToken(obHeader.getPnsDeviceToken());

				}

			} else
			{
				deviceProfile.setIsPushNotifEnabled("N");
			}
		}

		try
		{
			dao.insertEntity(deviceProfile);
			return true;
		} catch (Exception ex)
		{

		}

		return false;

	}

	public void addCustProfile(ObUserDetail userBean, Date lastLoginDate)
	{
		CustomerProfile custProfile = (CustomerProfile) dao.findByID(CustomerProfile.class,
				userBean.getCifNumber());

		if (null == custProfile)
		{
			CustomerProfile custProfilex = new CustomerProfile();
			custProfilex.setCif(userBean.getCifNumber());
			custProfilex.setCreateBy(userBean.getCifNumber());
			custProfilex.setCreateDt(new Date());
			custProfilex.setFullName(userBean.getFullName());
			custProfilex.setIdNo(userBean.getIdentificationNumber());
			custProfilex.setIdType(userBean.getIdentificationType());
			custProfilex.setLoginId(userBean.getLoginId());
			custProfilex.setOrgId(userBean.getOrgId());
			custProfilex.setOrgName(userBean.getOrgName());
			custProfilex.setUsrName(userBean.getUsrName());
			custProfilex.setName(userBean.getDisplayName());
			custProfilex.setStatus(MiBConstant.MIB_CUSTPROFILE_STATUS_ACTIVE);
			custProfilex.setLastLoginDT(lastLoginDate);
			custProfilex.setMobileNumber(userBean.getMobileNumber());
			// custProfilex.setLastLogoutDT(lastLogoutDate);

			dao.insertEntity(custProfilex);
		} 
		else if (userBean.getOrgName()!=null && ( custProfile.getOrgName()==null || !custProfile.getOrgName().equalsIgnoreCase( userBean.getOrgName())))
		{
			custProfile.setUsrName(userBean.getUsrName());
			custProfile.setOrgName(userBean.getOrgName());

			dao.updateEntity(custProfile);
		}
		else if (userBean.getUsrName()!=null && ( custProfile.getUsrName()==null || !custProfile.getUsrName().equalsIgnoreCase( userBean.getUsrName())))
		{
			
			custProfile.setUsrName(userBean.getUsrName());
			custProfile.setOrgName(userBean.getOrgName());

			dao.updateEntity(custProfile);
		}
		else if (null == custProfile.getLoginId()
				|| !custProfile.getLoginId().equalsIgnoreCase(userBean.getLoginId()))
		{

			custProfile.setLastLoginDT(lastLoginDate);
			// custProfile.setLastLogoutDT(lastLogoutDate);

			custProfile.setCreateBy(userBean.getCifNumber());
			custProfile.setCreateDt(new Date());
			custProfile.setFullName(userBean.getFullName());
			custProfile.setIdNo(userBean.getIdentificationNumber());
			custProfile.setIdType(userBean.getIdentificationType());
			custProfile.setLoginId(userBean.getLoginId());
			custProfile.setName(userBean.getDisplayName());
			custProfile.setStatus(MiBConstant.MIB_CUSTPROFILE_STATUS_ACTIVE);
			custProfile.setMobileNumber(userBean.getMobileNumber());

			dao.updateEntity(custProfile);
		} else if (null == custProfile.getMobileNumber()
				|| !custProfile.getMobileNumber().equalsIgnoreCase(userBean.getMobileNumber())
				|| updateFullName(custProfile,userBean))
		{

			custProfile.setLastLoginDT(lastLoginDate);
			// custProfile.setLastLogoutDT(lastLogoutDate);

			custProfile.setCreateBy(userBean.getCifNumber());
			custProfile.setCreateDt(new Date());
			custProfile.setFullName(userBean.getFullName());
			custProfile.setIdNo(userBean.getIdentificationNumber());
			custProfile.setIdType(userBean.getIdentificationType());
			custProfile.setLoginId(userBean.getLoginId());
			custProfile.setName(userBean.getDisplayName());
			custProfile.setStatus(MiBConstant.MIB_CUSTPROFILE_STATUS_ACTIVE);
			custProfile.setMobileNumber(userBean.getMobileNumber());
			custProfile.setUsrName(userBean.getUsrName());

			dao.updateEntity(custProfile);
		} else
		{
			log.info("update last login date");
			custProfile.setLastLoginDT(lastLoginDate);
			dao.updateEntity(custProfile);
			log.info("update last login date done");
		}
	}
	
	
	
	public boolean updateFullName(CustomerProfile cust,ObUserDetail userBean )
	{
		
		if(userBean.getFullName()!=null)
		{
			
			if(cust.getFullName()==null || !userBean.getFullName().equalsIgnoreCase(cust.getFullName()))
			{
				return true;
			}
			
			
			
		}
		
		
		
		return false;
		
	}

	public void updateLogoutDate(String cifNumber, Date lastLogoutDate)
	{
		CustomerProfile custProfile = (CustomerProfile) dao.findByID(CustomerProfile.class,
				cifNumber);

		if (custProfile != null)
		{
			custProfile.setLastLogoutDT(lastLogoutDate);

			dao.updateEntity(custProfile);
		}
	}

	

	public ObDeviceProfileBean getDeviceProfile(String deviceID)
	{
		CustomerDAO custDAO = (CustomerDAO) dao;
		DeviceProfile deviceProfile = (DeviceProfile) dao.findByID(DeviceProfile.class, deviceID);

		if (null != deviceProfile)
		{
			ObDeviceProfileBean dpb = new ObDeviceProfileBean();
			dpb.setDeviceId(deviceProfile.getDeviceId());
			dpb.setModel(deviceProfile.getDeviceModel());
			dpb.setOs(deviceProfile.getOs());
			dpb.setType(deviceProfile.getDeviceType());
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					MiBConstant.DEVICE_PROFILE_DATE_FORMAT);
			dpb.setDateTagged(dateFormat.format(deviceProfile.getDateTagged()));
			dpb.setPnsFlagStatus(deviceProfile.getIsPushNotifEnabled()); // newly
																			// added
			dpb.setBiometricStatus(deviceProfile.getBiometricStatus()); // newly
																		// added

			return dpb;
		} else
			return null;
	}

	 

	public McbConf getMcBConf()
	{
		String sql = "FROM " + McbConf.class.getSimpleName() + " where status = 'ACTIVE'";
		List<McbConf> mibConf = dao.selectQuery(sql);

		return mibConf.get(0);
	}

	public String getIndexDisplay(int x)
	{
		String index = x + "";
		while (index.length() < 3)
		{
			index = "0" + index;
		}

		return index;
	}

	public ObResponse filterAccountSummarySorting(ObResponse responseData, String cif)
	{

		if (responseData instanceof ObAccountSummaryListResponse)
		{
			ObAccountSummaryListResponse summaryList = (ObAccountSummaryListResponse) responseData;
			int indexOrder = 1;
			for (ObAccountSpecificBean obAccount : summaryList.getObAccountSpecificBean())
			{
				obAccount.setAccountIndex("ACC" + getIndexDisplay(indexOrder));
				indexOrder++;
			}

			String sql = "FROM " + CustomerAccOrder.class.getSimpleName() + " WHERE cif = :cif";
			Hashtable params = new Hashtable();
			params.put("cif", cif);
			List<CustomerAccOrder> accOrderLists = dao.selectQueryParam(sql, params);

			if (accOrderLists.size() > 0)
			{

				for (CustomerAccOrder accOrder : accOrderLists)
				{
					for (ObAccountSpecificBean obAccount : summaryList.getObAccountSpecificBean())
					{
						if (obAccount.getAccountNumber().equalsIgnoreCase(
								accOrder.getAccountNumber()))
						{
							obAccount.setAccountIndex(getIndexDisplay(Integer.parseInt(accOrder
									.getAccountOrder())));
							break;
						}
					}
				}

				Collections.sort(summaryList.getObAccountSpecificBean(), COMPARATOR);
				int newIndexOrder = 1;
				for (ObAccountSpecificBean obAccount : summaryList.getObAccountSpecificBean())
				{
					obAccount.setAccountIndex("ACC" + getIndexDisplay(newIndexOrder));
					newIndexOrder++;
				}
			}

		}

		return responseData;
	}

	public List<ObAccountBean> filterAccount(List<ObAccountBean> accountList, String cif,
			String firstAccount)
	{

		String sql = "FROM " + CustomerAccOrder.class.getSimpleName() + " WHERE cif = :cif";
		Hashtable params = new Hashtable();
		params.put("cif", cif);
		List<CustomerAccOrder> accOrderLists = dao.selectQueryParam(sql, params);

		if (accOrderLists.size() > 0)
		{

			for (CustomerAccOrder accOrder : accOrderLists)
			{
				for (ObAccountBean obAccount : accountList)
				{
					if (obAccount.getAccountNumber().equalsIgnoreCase(accOrder.getAccountNumber()))
					{
						obAccount.setAccountIndex(getIndexDisplay(Integer.parseInt(accOrder
								.getAccountOrder())));
						obAccount.setAccountOrder(obAccount.getAccountIndex());
						break;
					}
				}
			}

			Collections.sort(accountList, COMPARATOR);
			int newIndexOrder = 1;
			for (ObAccountBean obAccount : accountList)
			{
				obAccount.setAccountIndex("ACC" + getIndexDisplay(newIndexOrder));
				newIndexOrder++;
			}
		}

		if (null != firstAccount && accountList.size() > 0
				&& accountList.get(0).getAccountOrder() == null
				&& accountList.get(0).getAccountNumber().equalsIgnoreCase(firstAccount))
		{
			accountList.get(0).setAccountOrder("001");
		}

		return accountList;
	}

	public List<ObCreditCardBean> filterCardAccount(List<ObCreditCardBean> accountList, String cif)
	{

		String sql = "FROM " + CustomerAccOrder.class.getSimpleName() + " WHERE cif = :cif";
		Hashtable params = new Hashtable();
		params.put("cif", cif);
		List<CustomerAccOrder> accOrderLists = dao.selectQueryParam(sql, params);

		if (accOrderLists.size() > 0)
		{

			for (CustomerAccOrder accOrder : accOrderLists)
			{
				for (ObAccountBean obAccount : accountList)
				{
					if (obAccount.getAccountNumber().equalsIgnoreCase(accOrder.getAccountNumber()))
					{
						obAccount.setAccountIndex(getIndexDisplay(Integer.parseInt(accOrder
								.getAccountOrder())));
						obAccount.setAccountOrder(obAccount.getAccountIndex());
						break;
					}
				}
			}

			Collections.sort(accountList, COMPARATOR);
			int newIndexOrder = 1;
			for (ObAccountBean obAccount : accountList)
			{
				obAccount.setAccountIndex("ACC" + getIndexDisplay(newIndexOrder));
				newIndexOrder++;
			}
		}

		return accountList;
	}

	public Comparator<ObAccountBean> COMPARATOR = new Comparator<ObAccountBean>()
	{

		public int compare(ObAccountBean o1, ObAccountBean o2)
		{

			if (null == o1.getAccountIndex())
			{
				o1.setAccountIndex("9999");
			}
			if (null == o2.getAccountIndex())
			{
				o2.setAccountIndex("9999");
			}

			return o1.getAccountIndex().compareTo(o2.getAccountIndex());
		}

	};

	public Comparator<ObCreditCardBean> TransHistSort = new Comparator<ObCreditCardBean>()
	{

		public int compare(ObCreditCardBean o1, ObCreditCardBean o2)
		{

			return o2.getAccountIndex().compareTo(o1.getAccountIndex());
		}

	};

	public void updateAccountSorting(List<ObAccountSpecificBean> obAccountSpecificBean, String cif)
	{
		String sql = "DELETE FROM " + CustomerAccOrder.class.getSimpleName() + " WHERE cif = :cif";
		Hashtable params = new Hashtable();
		params.put("cif", cif);
		dao.updateSQLParam(sql, params);
		Date insertDate = new Date();
		List<Object> accList = new ArrayList();
		for (ObAccountSpecificBean obAcc : obAccountSpecificBean)
		{
			CustomerAccOrder cust = new CustomerAccOrder();
			cust.setAccountNumber(obAcc.getAccountNumber());
			cust.setAccountOrder(getIndexDisplay(Integer.parseInt(obAcc.getAccountIndex())));
			cust.setUpdateDate(insertDate);
			cust.setCustomerProfile(new CustomerProfile());
			cust.getCustomerProfile().setCif(cif);
			accList.add(cust);
		}

		dao.insertMultiEntity(accList);

	}

	public static void main(String args[])
	{

		SimpleDateFormat sft = new SimpleDateFormat("E");

		// System.out.println(sft.format(new Date()));
	}

	public boolean checkDay(Date checkDay, String dbDay)
	{
		if (null == dbDay || !dbDay.matches("^[0-9]{1}"))
		{
			return true;
		}

		String checkDayString = "0";
		String[] week =
		{ "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun" };
		SimpleDateFormat sft = new SimpleDateFormat("E");
		String dayWeek = sft.format(checkDay);
		for (int i = 0; i < 7; i++)
		{
			if (dayWeek.equalsIgnoreCase(week[i]))
			{
				checkDayString = String.valueOf(i + 1);
			}
		}

		if (checkDayString.equalsIgnoreCase(dbDay))
		{
			return true;
		}

		return false;
	}

	@Deprecated
	public String[] checkMaintenanceNotification(String deviceID)
	{
		// [0]true/false , [1]Start Datetime, [2] End Datetime
		String[] result = new String[3];

		// allow device access under maintenance
		String allowServerMaintenanceService = pro.getProperty("deviceAccessUnderMaintenance");
		String allowDeviceList[] = null == allowServerMaintenanceService ? new String[0]
				: allowServerMaintenanceService.split(",");
		for (String allowDevice : allowDeviceList)
		{
			if (null != deviceID && deviceID.equalsIgnoreCase(allowDevice))
			{
				result[0] = "false";
				return result;
			}
		}
		// end

		Date currentDate = new Date();
		SimpleDateFormat sft = new SimpleDateFormat(MiBConstant.DB_DATETIME_FORMAT);

		String currentDT = sft.format(currentDate);
		String currentTime = currentDT.substring(11, 16);
		String dateMaintenance = currentDT.substring(0, 11);
		String sqlx = "FROM " + MaintenanceNotification.class.getSimpleName()
				+ " WHERE effective_start_dt <= '" + currentDT + "' AND effective_end_dt >= '"
				+ currentDT + "' AND status='" + MiBConstant.MIB_MAINTENANCE_STATUS_ACTIVE
				+ "'  order by maintenance_id desc";

		log.info(sqlx);
		List<MaintenanceNotification> notifications = dao.selectQuery(sqlx);

		for (MaintenanceNotification notif : notifications)
		{

			String maintenanceStartTime = notif.getStartTime();
			String maintenanceEndTime = notif.getEndTime();
			if (currentTime.compareTo(maintenanceStartTime) >= 0
					&& currentTime.compareTo(maintenanceEndTime) <= 0
					&& checkDay(currentDate, notif.getDayInWeek()))
			{
				// is under maintenance

				if (maintenanceEndTime.equalsIgnoreCase("23:59"))
				{

					Calendar c = Calendar.getInstance();
					c.setTime(currentDate);
					c.add(Calendar.DATE, 1);
					Date checkNextDay = new Date(c.getTimeInMillis());

					String nextCurrentDay = sft.format(checkNextDay);
					String nextMainTenance = nextCurrentDay.substring(0, 11);
					String sqlxx = "FROM " + MaintenanceNotification.class.getSimpleName()
							+ " WHERE effective_start_dt <= '" + nextCurrentDay
							+ "' AND effective_end_dt >= '" + nextCurrentDay + "' AND status='"
							+ MiBConstant.MIB_MAINTENANCE_STATUS_ACTIVE
							+ "'  order by maintenance_id desc";
					log.info(sqlxx);

					List<MaintenanceNotification> nextNotifications = dao.selectQuery(sqlxx);
					if (nextNotifications != null && nextNotifications.size() > 0)
					{
						for (MaintenanceNotification nextNotify : nextNotifications)
						{
							if (nextNotify.getStartTime().equalsIgnoreCase("00:00")
									&& checkDay(checkNextDay, nextNotify.getDayInWeek()))
							{
								String nextMaintenanceEndTime = nextNotify.getEndTime();
								result[0] = "true";
								result[1] = dateMaintenance + maintenanceStartTime;
								result[2] = nextMainTenance + nextMaintenanceEndTime;

								if (nextMaintenanceEndTime.endsWith(":59"))
								{
									log.info("----- 1 ------");
									try
									{
										int endHour = Integer.parseInt(nextMaintenanceEndTime
												.substring(0, 2)) + 1;
										if (endHour == 24)
											endHour = 00;

										String endHourMin = "";
										if (endHour <= 9)
											endHourMin = "0" + endHour + ":00";
										else
											endHourMin = endHour + ":00";

										boolean isContinue = true;

										String result2 = dateMaintenance + endHourMin;
										DateFormat df = new SimpleDateFormat(
												MiBConstant.DB_DATETIME_FORMAT);
										String effDate = df
												.format(nextNotify.getEffectiveStartDt());
										while (isContinue)
										{
											nextCurrentDay = sft.format(nextNotify
													.getEffectiveStartDt());
											nextMainTenance = nextCurrentDay.substring(0, 11);
											String sqlxxx = "FROM "
													+ MaintenanceNotification.class.getSimpleName()
													+ " WHERE effective_start_dt = '" + effDate
													+ "' AND start_time = '" + endHourMin
													+ "' AND status='"
													+ MiBConstant.MIB_MAINTENANCE_STATUS_ACTIVE
													+ "'  order by maintenance_id desc";
											log.info(sqlxxx);
											List<MaintenanceNotification> nextNotifications1 = dao
													.selectQuery(sqlxxx);
											log.info("nextNotifications1 :: "
													+ nextNotifications1.size());
											if (nextNotifications1 != null
													&& nextNotifications1.size() > 0)
											{
												MaintenanceNotification hmn = nextNotifications1
														.get(0);
												String mEndTime = hmn.getEndTime();
												log.info("END TIME :: " + mEndTime);

												if (!mEndTime.endsWith(":59"))
												{
													Date mEndDate = hmn.getEffectiveStartDt();

													String nextCurrentDay1 = sft.format(mEndDate);
													String nextMainTenance1 = nextCurrentDay1
															.substring(0, 11);

													result2 = nextMainTenance1 + mEndTime;
													isContinue = false;
												} else
												{
													if (mEndTime.equalsIgnoreCase("23:59"))
													{
														Calendar c1 = Calendar.getInstance();
														c1.setTime(hmn.getEffectiveStartDt());
														c1.add(Calendar.DATE, 1);
														Date checkNextDay1 = new Date(
																c1.getTimeInMillis());

														effDate = sft.format(checkNextDay1);
													} else
													{
														effDate = df.format(hmn
																.getEffectiveStartDt());
													}

													endHourMin = "";

													endHour = Integer.parseInt(hmn.getEndTime()
															.substring(0, 2)) + 1;
													if (endHour == 24)
														endHour = 00;

													if (endHour <= 9)
														endHourMin = "0" + endHour + ":00";
													else
														endHourMin = endHour + ":00";
												}
											} else
											{
												nextMainTenance = effDate.substring(0, 11);
												result2 = nextMainTenance + endHourMin; // requested
																						// from
																						// Jacky
																						// to
																						// increase
																						// the
																						// end
																						// time
																						// from
																						// 59
																						// to
																						// next
																						// hour
																						// 00
												isContinue = false;
											}
										}

										result[1] = dateMaintenance + maintenanceStartTime;
										result[2] = result2;

									} catch (Exception e)
									{
										e.printStackTrace();
										result[1] = dateMaintenance + maintenanceStartTime;
										result[2] = dateMaintenance + nextMaintenanceEndTime;
									}

								}

								return result;
							} else
							{
								result[1] = dateMaintenance + maintenanceStartTime;
								result[2] = dateMaintenance + maintenanceEndTime;
							}
						}
					} else
					{
						result[1] = dateMaintenance + maintenanceStartTime;
						result[2] = dateMaintenance + maintenanceEndTime;
					}

				} else if (maintenanceEndTime.endsWith(":59"))
				{
					try
					{
						int endHour = Integer.parseInt(maintenanceEndTime.substring(0, 2)) + 1;
						String endHourMin = "";
						if (endHour <= 9)
							endHourMin = "0" + endHour + ":00";
						else
							endHourMin = endHour + ":00";

						boolean isContinue = true;

						String result2 = dateMaintenance + endHourMin;
						DateFormat df = new SimpleDateFormat(MiBConstant.DB_DATETIME_FORMAT);
						String effDate = df.format(notif.getEffectiveStartDt());
						while (isContinue)
						{
							String sqlxx = "FROM " + MaintenanceNotification.class.getSimpleName()
									+ " WHERE effective_start_dt = '" + effDate
									+ "' AND start_time = '" + endHourMin + "' AND status='"
									+ MiBConstant.MIB_MAINTENANCE_STATUS_ACTIVE
									+ "'  order by maintenance_id desc";
							log.info(sqlxx);
							List<MaintenanceNotification> nextNotifications = dao
									.selectQuery(sqlxx);
							log.info("nextNotifications : " + nextNotifications.size());

							if (nextNotifications != null && nextNotifications.size() > 0)
							{
								MaintenanceNotification hmn = nextNotifications.get(0);
								String mEndTime = hmn.getEndTime();

								if (!mEndTime.endsWith(":59"))
								{
									Date mEndDate = hmn.getEffectiveStartDt();

									String nextCurrentDay = sft.format(mEndDate);
									String nextMainTenance = nextCurrentDay.substring(0, 11);

									result2 = nextMainTenance + mEndTime;
									isContinue = false;
								} else
								{

									if (mEndTime.equalsIgnoreCase("23:59"))
									{
										Calendar c1 = Calendar.getInstance();
										c1.setTime(hmn.getEffectiveStartDt());
										c1.add(Calendar.DATE, 1);
										Date checkNextDay1 = new Date(c1.getTimeInMillis());

										effDate = sft.format(checkNextDay1);
									} else
									{
										effDate = df.format(hmn.getEffectiveStartDt());
									}

									endHourMin = "";

									endHour = Integer.parseInt(hmn.getEndTime().substring(0, 2)) + 1;
									if (endHour == 24)
										endHour = 00;

									if (endHour <= 9)
										endHourMin = "0" + endHour + ":00";
									else
										endHourMin = endHour + ":00";

								}
							} else
							{
								dateMaintenance = effDate.substring(0, 11);
								result2 = dateMaintenance + endHourMin; // requested
																		// from
																		// Jacky
																		// to
																		// increase
																		// the
																		// end
																		// time
																		// from
																		// 59 to
																		// next
																		// hour
																		// 00
								isContinue = false;
							}
						}

						result[1] = dateMaintenance + maintenanceStartTime;
						result[2] = result2;

					} catch (Exception e)
					{
						e.printStackTrace();
						result[1] = dateMaintenance + maintenanceStartTime;
						result[2] = dateMaintenance + maintenanceEndTime;
					}

				} else
				{
					result[1] = dateMaintenance + maintenanceStartTime;
					result[2] = dateMaintenance + maintenanceEndTime;
				}

				result[0] = "true";
				return result;
			}
		}

		result[0] = "false";
		return result;

	}

	@Deprecated
	public String[] getMaintenanceNotification(int directNotice, String deviceID)

	{
		// login show direct
		// [0]true/false , [1]Start Datetime, [2] End Datetime
		String[] result = new String[3];

		// allow device access under maintenance
		String allowServerMaintenanceService = pro.getProperty("deviceAccessUnderMaintenance");
		String allowDeviceList[] = null == allowServerMaintenanceService ? new String[0]
				: allowServerMaintenanceService.split(",");
		for (String allowDevice : allowDeviceList)
		{
			if (null != deviceID && deviceID.equalsIgnoreCase(allowDevice))
			{
				result = null;
				return result;
			}
		}
		// end

		Date currentDate = new Date();
		SimpleDateFormat sft = new SimpleDateFormat(MiBConstant.DB_DATETIME_FORMAT);
		String realCurrentDate = sft.format(currentDate);
		realCurrentDate = realCurrentDate.substring(11, 16);
		directNotice = directNotice * 1000 * 60;
		long noticeDateTime = currentDate.getTime() + directNotice;
		currentDate = new Date(noticeDateTime);

		String currentDT = sft.format(currentDate);
		String currentTime = currentDT.substring(11, 16);
		String dateMaintenance = currentDT.substring(0, 11);
		String sqlx = "FROM " + MaintenanceNotification.class.getSimpleName()
				+ " WHERE effective_start_dt <= '" + currentDT + "' AND effective_end_dt >= '"
				+ currentDT + "' AND status='" + MiBConstant.MIB_MAINTENANCE_STATUS_ACTIVE
				+ "'  order by start_time asc";

		List<MaintenanceNotification> notifications = dao.selectQuery(sqlx);

		for (MaintenanceNotification notif : notifications)
		{

			String maintenanceStartTime = notif.getStartTime();
			String maintenanceEndTime = notif.getEndTime();
			String nextMaintenanceEndTime = null;
			if (!checkDay(currentDate, notif.getDayInWeek()))
			{
				continue;
			} else if (currentTime.compareTo(maintenanceStartTime) >= 0
					&& realCurrentDate.compareTo(maintenanceEndTime) <= 0
					&& checkDay(currentDate, notif.getDayInWeek()))
			{
				result[0] = "true";
			} else if (currentTime.compareTo(maintenanceEndTime) > 0)
			{

				continue;
			} else
			{
				result[0] = "false";
			}

			if (maintenanceEndTime.equalsIgnoreCase("23:59")
					&& checkDay(currentDate, notif.getDayInWeek()))
			{

				Calendar c = Calendar.getInstance();
				c.setTime(currentDate);
				c.add(Calendar.DATE, 1);
				Date checkNextDay = new Date(c.getTimeInMillis());

				String nextCurrentDay = sft.format(checkNextDay);
				String nextMainTenance = nextCurrentDay.substring(0, 11);
				String sqlxx = "FROM " + MaintenanceNotification.class.getSimpleName()
						+ " WHERE effective_start_dt <= '" + nextCurrentDay
						+ "' AND effective_end_dt >= '" + nextCurrentDay + "' AND status='"
						+ MiBConstant.MIB_MAINTENANCE_STATUS_ACTIVE + "'";

				List<MaintenanceNotification> nextNotifications = dao.selectQuery(sqlxx);
				for (MaintenanceNotification nextNotify : nextNotifications)
				{
					if (nextNotify.getStartTime().equalsIgnoreCase("00:00")
							&& checkDay(checkNextDay, nextNotify.getDayInWeek()))
					{
						nextMaintenanceEndTime = nextNotify.getEndTime();
						result[1] = dateMaintenance + maintenanceStartTime;
						result[2] = nextMainTenance + nextMaintenanceEndTime;
						return result;
					} else
					{
						result[1] = dateMaintenance + maintenanceStartTime;
						result[2] = dateMaintenance + maintenanceEndTime;
					}
				}
			} else if (checkDay(currentDate, notif.getDayInWeek()))
			{
				result[1] = dateMaintenance + maintenanceStartTime;
				result[2] = dateMaintenance + maintenanceEndTime;
			} else
			{
				result = null;
			}

			return result;

		}

		return null;

	}

	public String getfirstDisplayAccount(List<AccountVO> listCasaAcc)
	{
		for (AccountVO vo : listCasaAcc)
		{
			if (vo.isAllowDisplay())
			{
				return vo.getAccountNumber();
			}
		}

		return null;
	}

	public String checkAccessVersion(String deviceType, String requestInfo, boolean rooted)
	{

		PropertiesManager realTimePro = new PropertiesManager();

		// null = invalid device type
		// false = invalid version (major update)
		// true = pass all
		// rooted = block access
		
		String rootedBlock = realTimePro.getProperty("rootedBlock");
		if(rootedBlock!=null && rootedBlock.trim().equalsIgnoreCase("true") && rooted)
		{
			 return "rooted";
		}
		
		Hashtable<String, String> sqlParams = new Hashtable<String, String>(); 
		sqlParams.put("gnCodeType", MiBConstant.GENERAL_CODE_SERVER_VERSION);
		List<GeneralCode> versionList  = dao.selectQueryParam("FROM "+GeneralCode.class.getSimpleName() + " WHERE gnCodeType=:gnCodeType", sqlParams);
		
		String serverVersion = null;
		if(versionList != null) {
			//osType may contains "android", "ios", "blackberry", "window" 
			for(GeneralCode appVersion:versionList) {
				String osType = appVersion.getGnCode();
				if(deviceType.equalsIgnoreCase(osType)) {
					serverVersion = appVersion.getGnCodeDescription();
					break;
				}
			}
		}
		//No setting in database yet, default to 0
		if(serverVersion == null) {
			String[] osTypes = new String[]{"android", "ios", "blackberry", "window"};
			for(String osType:osTypes) {
				if(osType.equalsIgnoreCase(deviceType)) {
					serverVersion = "0";
					break;
				}
			}
		}
		if (null == serverVersion)
		{
			return null;
		} else
		{
			try
			{

				int iServerVersion = Integer.parseInt(serverVersion);
				int mobileVersion = Integer.parseInt(requestInfo.split("\\.")[0]);

				if (mobileVersion >= iServerVersion)
				{
					return "true";
				} else
				{
					return "false";
				}

			} catch (Exception e)
			{
				return "false";
			}

		}

	}

	public List<MessagePropertiesI18n> getModifyMessage()
	{
		List<MessagePropertiesI18n> messageList = null;

		String sqlMsg = "FROM " + MessagePropertiesI18n.class.getSimpleName() + " M join "
				+ MessageProperties.class.getSimpleName() + " k on M.message_id=k.message_id where k.status = '"
				+ MiBConstant.MSG_ADDED + "' OR k.status = '" + MiBConstant.MSG_MODIFY
				+ "' OR  k.status='" + MiBConstant.MSG_DELETED + "' ";

		messageList = dao.selectQuery(sqlMsg);

		return messageList;
	}

	public boolean updateResetMsgCheck(String desciption, Date update_date)
	{
		int clusterServer = 2;
		boolean check = false;
		String hostname = "";
		try
		{
			String ip = InetAddress.getLocalHost().getHostAddress().toString();

			hostname = InetAddress.getLocalHost().getHostName();

		} catch (UnknownHostException e)
		{
			// TODO Auto-generated catch block

			log.info("Update message hostname exception ", e);
		}

		String[] descUpdate = desciption.split(",");
		List<String> descList = new ArrayList();
		for (String desc : descUpdate)
		{
			descList.add(desc);
		}

		if (!descList.contains(hostname))
		{
			// update properties file
			log.info("RESET MSG : Update Properties");

			// ADD , MODIFY , DELETE

			/*
			 * MessageManager ccMsg = new MessageManager();
			 * ccMsg.getDefaultProperties().setProperty("0000000", new
			 * Date().toString());
			 * 
			 * 
			 * ccMsg.updateDefaultPropertiesFile();
			 */
			check = true;
			String updateSql = "";

			if (descList.size() == (clusterServer))
			{
				Hashtable tblMap = new Hashtable();
				tblMap.put("update_dt__timestamp", update_date);
				updateSql = "UPDATE " + McbConf.class.getSimpleName()
						+ " set description = 'UPDATED' where description = '" + desciption
						+ "' AND status = 'ACTIVE' AND update_date = :update_dt__timestamp";
				int rs = dao.updateSQLParam_timestamp(updateSql, tblMap);
				if (rs > 0)
				{
					updatePropertiesMessage(true);
				}
			} else
			{
				Hashtable tblMap = new Hashtable();
				tblMap.put("update_dt__timestamp", update_date);
				updateSql = "UPDATE " + McbConf.class.getSimpleName() + " set description = '"
						+ desciption + "," + hostname + "' where description = '" + desciption
						+ "' AND status = 'ACTIVE' AND update_date = :update_dt__timestamp";
				int rs = dao.updateSQLParam_timestamp(updateSql, tblMap);
				if (rs > 0)
				{
					updatePropertiesMessage(false);
				}

			}

		}

		return check;

	}

	

	public void updatePropertiesMessage(boolean update)
	{

		List<MessagePropertiesI18n> messageList = getModifyMessage();
		MessageManager ccMsg = new MessageManager();
		boolean modify_en = false;
		boolean modify_vn = false;
		boolean modify_cn = false;
		log.info("RESET MSG Size :" + messageList.size());
		for (MessagePropertiesI18n s : messageList)
		{
			String keyMsg = s.getErrMessageCode();
			String valueMsg = s.getErrMessage();

			log.info("RESET MSG data : " + s.getMessageId() + " - " + s.getErrMessageCode() + " - "
					+ s.getMessageProperties().getStatus());
			if (s.getMessageProperties().getStatus().equalsIgnoreCase(MiBConstant.MSG_ADDED)
					|| s.getMessageProperties().getStatus()
							.equalsIgnoreCase(MiBConstant.MSG_MODIFY))
			{
				// added & modify

				String newline = System.getProperty("line.separator");
				valueMsg = valueMsg.replace("\\n", newline);
				valueMsg = valueMsg.replace("\\", "");

				if (s.getLanguageCode().equalsIgnoreCase(MiBConstant.MSG_LANG_EN))
				{
					ccMsg.getDefaultProperties().setProperty(keyMsg, valueMsg);
					modify_en = true;
				} else if (s.getLanguageCode().equalsIgnoreCase(MiBConstant.MSG_LANG_IN))
				{
					ccMsg.getINProperties().setProperty(keyMsg, valueMsg);
					modify_vn = true;
				}
				else if (s.getLanguageCode().equalsIgnoreCase(MiBConstant.MSG_LANG_CN))
				{
					ccMsg.getINProperties().setProperty(keyMsg, valueMsg);
					modify_cn = true;
				}

			} else
			{
				// delete
				if (s.getLanguageCode().equalsIgnoreCase(MiBConstant.MSG_LANG_EN))
				{
					ccMsg.getDefaultProperties().remove(keyMsg);
					modify_en = true;
				} else if (s.getLanguageCode().equalsIgnoreCase(MiBConstant.MSG_LANG_IN))
				{
					ccMsg.getINProperties().remove(keyMsg);
					modify_vn = true;
				}
				else if (s.getLanguageCode().equalsIgnoreCase(MiBConstant.MSG_LANG_CN))
				{
					ccMsg.getINProperties().setProperty(keyMsg, valueMsg);
					modify_cn = true;
				}
			}

		}

		// update to properties file
		if (modify_en)
		{
			ccMsg.updateDefaultPropertiesFile();
		}

		if (modify_vn)
		{
			ccMsg.updateINPropertiesFile();
		}

		if (modify_cn)
		{
			ccMsg.updateCNPropertiesFile();
		}

		// update message database
		if (update)
		{
			updateDBMessageStatus(messageList);
		}

	}

	public void updateDBMessageStatus(List<MessagePropertiesI18n> messageList)
	{
		// SimpleDateFormat dbFormat = new
		// SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sqlUpdate = "Update " + MessageProperties.class.getSimpleName() + " ";
		List<String> updatedList = new ArrayList();
		for (MessagePropertiesI18n m18n : messageList)
		{
			if (!updatedList.contains(m18n.getMessageId().toString()))
			{
				String newSqlUpdate = "";
				Hashtable tblMap = new Hashtable();
				if (m18n.getMessageProperties().getStatus()
						.equalsIgnoreCase(MiBConstant.MSG_MODIFY))
				{
					newSqlUpdate = sqlUpdate + " set status = '" + MiBConstant.MSG_ACTIVE
							+ "' WHERE message_id = '" + m18n.getMessageId()
							+ "' AND modify_dt = :dt__timestamp";
					tblMap.put("dt__timestamp", m18n.getMessageProperties().getModifyDt());
				} else if (m18n.getMessageProperties().getStatus()
						.equalsIgnoreCase(MiBConstant.MSG_ADDED))
				{
					newSqlUpdate = sqlUpdate + " set status = '" + MiBConstant.MSG_ACTIVE
							+ "' WHERE message_id = '" + m18n.getMessageId()
							+ "' AND create_dt = :dt__timestamp";
					tblMap.put("dt__timestamp", m18n.getMessageProperties().getCreateDt());
				} else
				{
					// delete
					newSqlUpdate = sqlUpdate + " set status = '" + MiBConstant.MSG_DELETED
							+ "' WHERE message_id = '" + m18n.getMessageId()
							+ "' AND modify_dt = :dt__timestamp";
					tblMap.put("dt__timestamp", m18n.getMessageProperties().getModifyDt());
				}

				dao.updateSQLParam_timestamp(newSqlUpdate, tblMap);
				updatedList.add(m18n.getMessageId().toString());
			}
		}

	}

	public String checkModuleMaintenance()
	{

		String moduleMaintenance = pro.getProperty("moduleMaintenance");
		String moduleMaintenanceList[] = null == moduleMaintenance ? new String[0]
				: moduleMaintenance.split(",");

		String maintenanceResult = "";

		boolean hideAndroidBiometric = false;
		boolean hideIosBiometric = false;
		for (String module : moduleMaintenanceList)
		{
			String showModule = pro.getProperty(module);
			if (showModule != null && showModule.equalsIgnoreCase("true"))
			{
				// maintenanceResult= maintenanceResult+"Y";
				if (module.indexOf("biometric") == 0 && hideAndroidBiometric)
				{
					maintenanceResult = maintenanceResult + "N";
				} else if (module.indexOf("iosBiometric") == 0 && hideIosBiometric)
				{
					maintenanceResult = maintenanceResult + "N";
				} else
				{
					maintenanceResult = maintenanceResult + "Y";
				}
			} else
			{
				// maintenanceResult = maintenanceResult+"N";
				maintenanceResult = maintenanceResult + "N";
				if (module.equalsIgnoreCase("showBiometric"))
				{
					hideAndroidBiometric = true;
				} else if (module.equalsIgnoreCase("showIosBiometric"))
				{
					hideIosBiometric = true;
				}
			}
		}

		return maintenanceResult;
	}

	public Date checkRBSDateValue(Date currentDate)
	{
		int dateCheck = 0;

		String host_datetime = null;

		String sql = "FROM " + GeneralCode.class.getSimpleName()
				+ " WHERE  gncode_type='RBSDATEDIFF' and status='A'";
		List<GeneralCode> dateGenCodeList = null;
		try
		{
			dateGenCodeList = dao.selectQuery(sql);
			host_datetime = dateGenCodeList.get(0).getGnCodeDescription();
			log.info("extRbsHostdatevalue == " + host_datetime);
		} catch (Exception e)
		{
			log.info("exception " + e.getMessage());
		}

		if (null == host_datetime)
		{
			dateCheck = 0;
		} else if (host_datetime.length() > 0)
		{
			dateCheck = Integer.parseInt(host_datetime);
		}

		if (dateCheck != 0)
		{
			Calendar c = Calendar.getInstance();
			c.setTime(currentDate);
			c.add(Calendar.DATE, dateCheck);
			Date newDate = c.getTime();
			return newDate;
		}

		return currentDate;

	}

	public boolean checkBiometricDeviceSupported(DeviceBean deviceBean)
	{

		// String supportedDeviceModel =
		// pro.getProperty("supportedDeviceModel");

		if (deviceBean.getType().equalsIgnoreCase("Android"))
		{
			try
			{
				String notSupportedDeviceModel = getAccessInfo(true, "notSupportedDeviceModel");
				String notSupportedModel[] = null == notSupportedDeviceModel ? new String[0]
						: notSupportedDeviceModel.split(",");
				String androidDeviceOsModel = getAccessInfo(true, "os.android");
				String deviceOs[] = null == androidDeviceOsModel ? new String[0]
						: androidDeviceOsModel.split(",");

				String model = deviceBean.getModel();
				// only applicable for android
				int os = Integer.parseInt(modifyAndroidVersion(deviceBean.getOs().substring(8)));

				for (String osVersion : deviceOs)
				{
					int intOs = Integer.parseInt(osVersion.replace(".", ""));

					if (intOs > os)
					{
						return false;
					} else
					{
						for (String sDeviceModel : notSupportedModel)
						{

							if (model.contains(sDeviceModel))
							{
								return false;
							}
						}
					}
				}
			} catch (Exception ex)
			{
				ex.printStackTrace();
				return false;
			}
		} else
		{
			return true;
		}

		return true;
	}

	public boolean insertUpdateCustomerBiometric(ObUserDetail userBean, DeviceBean deviceBean,
			String biometricStatus, String biometricDeregFuzionFlag)
	{
		String sql = "FROM " + DeviceProfile.class.getSimpleName()
				+ " WHERE cif = :cif AND device_id = :deviceId AND status = '"
				+ MiBConstant.MIB_CUSTPROFILE_STATUS_ACTIVE + "' ";
		Hashtable params = new Hashtable();
		params.put("cif", userBean.getCifNumber());
		params.put("deviceId", deviceBean.getDeviceId());

		List<DeviceProfile> deviceProfiles = dao.selectQueryParam(sql, params);

		if (deviceProfiles != null && deviceProfiles.size() == 1)
		{
			for (DeviceProfile devPro : deviceProfiles)
			{
				// hlb.setBiometricFailedLoginCount(0);
				devPro.setBiometricStatus(biometricStatus);
				devPro.setBiometricDateUpdated(new Date());
				devPro.setBiometricDeregisterIbFlag(biometricDeregFuzionFlag);
				dao.updateEntity(devPro);
				return true;
			}
		}

		return false;
	}

	public DeviceProfile checkBiometricRegistered(String deviceId)
	{

		String sql = "FROM " + DeviceProfile.class.getSimpleName()
				+ " WHERE device_id = :deviceID AND status = '"
				+ MiBConstant.MIB_CUSTPROFILE_STATUS_ACTIVE + "' AND biometric_status = '"
				+ MiBConstant.MIB_CUSTPROFILE_STATUS_ACTIVE + "'";
		Hashtable params = new Hashtable();
		params.put("deviceID", deviceId);
		List<DeviceProfile> deviceProfiles = dao.selectQueryParam(sql, params);
		if (deviceProfiles.size() == 1)
		{
			return deviceProfiles.get(0);
		} else
		{
			return null;
		}
	}

	 

	public String mapBiometricStatus(String biometricStatus)
	{

		if (biometricStatus != null)
		{
			if (biometricStatus.equalsIgnoreCase(MiBConstant.PRO_ACTIVE))
			{
				return MiBConstant.MIB_BIOMETRIC_ENROLL;
			} else
			{
				return MiBConstant.MIB_BIOMETRIC_UNENROLL;
			}
		}

		return MiBConstant.MIB_BIOMETRIC_UNENROLL;
	}

	public DeviceProfile getDeactivatedBiometric(String deviceId)
	{

		DeviceProfile deviceProfile = (DeviceProfile) dao.findByID(DeviceProfile.class, deviceId);
		if (deviceProfile != null && deviceProfile.getBiometricStatus() != null
				&& deviceProfile.getBiometricStatus().equalsIgnoreCase(MiBConstant.PRO_DEACTIVATED))
		{
			return deviceProfile;
		} else
		{
			return null;
		}
	}

	public boolean updateCustomerDevicePNSToken(ObUserDetail userBean, DeviceBean deviceBean,
			String pnsToken)
	{
		String sql = "FROM "
				+ DeviceProfile.class.getSimpleName()
				+ " WHERE cif = :cif AND (device_id = :deviceId OR device_id = :deviceIdCon) AND status = '"
				+ MiBConstant.MIB_CUSTPROFILE_STATUS_ACTIVE + "' ";
		Hashtable params = new Hashtable();
		params.put("cif", userBean.getCifNumber());

		String deviceId = deviceBean.getDeviceId();
		String deviceIdCon = "";

		if (deviceId.indexOf("-IMAC-") > 0)
		{
			deviceIdCon = deviceId.substring(0, deviceId.indexOf("-IMAC-"));
		}

		if (deviceBean.getType().equalsIgnoreCase("iPad"))
		{
			if (!(deviceId.indexOf("_iPad") > 0))
				deviceId = deviceId + "_iPad";
		}

		params.put("deviceId", deviceId);
		params.put("deviceIdCon", deviceIdCon);

		List<DeviceProfile> deviceProfiles = dao.selectQueryParam(sql, params);
		if (deviceProfiles != null && deviceProfiles.size() == 1)
		{
			DeviceProfile devPro = deviceProfiles.get(0);

			if (pnsToken != null
					&& !pnsToken.equalsIgnoreCase("")
					&& (devPro.getPnsToken() == null || devPro.getPnsToken().equals("") || !pnsToken
							.equalsIgnoreCase(devPro.getPnsToken())))
			{
				log.info("Updating PNS Token from :: " + devPro.getPnsToken() + ", TO :: "
						+ pnsToken);
				devPro.setPnsToken(pnsToken);
				dao.updateEntity(devPro);
				return true;
			}
		}

		return false;
	}

	public boolean updateCustomerDevicePNSFlag(ObUserDetail userBean, DeviceBean deviceBean,
			int pnsFlag)
	{
		String sql = "FROM " + DeviceProfile.class.getSimpleName()
				+ " WHERE cif = :cif AND device_id = :deviceId AND status = '"
				+ MiBConstant.MIB_CUSTPROFILE_STATUS_ACTIVE + "' ";
		Hashtable params = new Hashtable();
		params.put("cif", userBean.getCifNumber());

		if (deviceBean.getType().equalsIgnoreCase("iPad"))
		{
			String deviceIdIpad = deviceBean.getDeviceId() + "_iPad";
			params.put("deviceId", deviceIdIpad);
		} else
		{
			params.put("deviceId", deviceBean.getDeviceId());
		}

		List<DeviceProfile> deviceProfiles = dao.selectQueryParam(sql, params);

		if (deviceProfiles != null && deviceProfiles.size() == 1)
		{
			DeviceProfile devPfl = deviceProfiles.get(0);

			log.info("Updating PNS Flag Status From :: " + devPfl.getIsPushNotifEnabled()
					+ ", TO :: " + (pnsFlag == 1 ? "Y" : "N"));

			if (pnsFlag == 1)
			{
				devPfl.setIsPushNotifEnabled("Y");
			} else
			{
				devPfl.setIsPushNotifEnabled("N");
			}

			dao.updateEntity(devPfl);
			return true;

		}

		return false;
	}

	public String getAccessInfo(boolean cache, String deviceData)
	{
		// boolean cache true = use back cache data

		List<GeneralCode> rs = new ArrayList();
		if (!cache)
		{

			String selectGenericDeviceID = "FROM " + GeneralCode.class.getSimpleName()
					+ " WHERE gncode_type = 'ACCESSINFO'";

			rs = dao.selectQuery(selectGenericDeviceID);
			Element elm = new Element("accessData", rs);
			ehcahce.getAccessInfoData().put(elm);

		} else
		{
			Object accessData = ehcahce.getAccessInfoData().get("accessData");
			if (null == accessData)
			{
				return getAccessInfo(false, deviceData);
			} else
			{
				Element elm = (Element) accessData;
				rs = (List<GeneralCode>) elm.getObjectValue();

			}

		}

		for (GeneralCode gnCode : rs)
		{

			if (gnCode.getGnCode().equalsIgnoreCase(deviceData))
			{
				return gnCode.getGnCodeDescription();
			}

		}

		return null;

	}

	public String modifyAndroidVersion(String version)
	{

		String androidVersion = version.replace(".", "");
		int num = androidVersion.length();
		if (num < 3)
		{
			androidVersion = androidVersion + "0";
		}
		return androidVersion;
	}

	public boolean loginTypeContains(String loginTypes, String type)
	{
		if (loginTypes != null)
		{
			String types[] = loginTypes.split(",");

			for (String t : types)
			{
				if (t.equalsIgnoreCase(type))
				{
					return true;
				}
			}
		}

		return false;
	}

	public String checkWhitelistedRootedDevice(boolean cache, String deviceID)
	{

		String result = "false";

		// allow rooted device access
		// String allowServerMaintenanceService =
		// pro.getProperty("deviceAccessUnderMaintenance")
		String allowServerMaintenanceService = getAccessInfo(cache, "deviceRootedWhitelist");
		String allowDeviceList[] = null == allowServerMaintenanceService ? new String[0]
				: allowServerMaintenanceService.split(",");
		for (String allowDevice : allowDeviceList)
		{
			if (null != deviceID && deviceID.equalsIgnoreCase(allowDevice))
			{
				result = "true";
			}
		}

		return result;

	}

	public boolean checkExistsUpdateRootedDevice(DeviceBean device, ObUserContext user,
			CustomerProfile custProfile)
	{
		boolean allow = false;

		String sql = "FROM " + RootedDevices.class.getSimpleName() + " WHERE deviceId = :device";
		Hashtable params = new Hashtable();
		params.put("device", device.getDeviceId());

		List<RootedDevices> rootedDevice = dao.selectQueryParam(sql, params);

		if (rootedDevice.size() > 0)
		{

			for (RootedDevices rD : rootedDevice)
			{
				rD.setUpdateDate(new Date());
				rD.setLogin_id(user.getLoginId());
				dao.updateEntity(rD);
				allow = true;
			}

		} else
		{
			RootedDevices rootDevice = new RootedDevices();
			rootDevice.setDeviceId(device.getDeviceId());
			rootDevice.setLogin_id(user.getLoginId());
			rootDevice.setMake(device.getMake());
			rootDevice.setModel(device.getModel());
			rootDevice.setOs(device.getOs());
			rootDevice.setDeviceType(device.getType());
			rootDevice.setUpdateDate(new Date());
			rootDevice.setIdNo(custProfile != null ? custProfile.getIdNo() : "");
			rootDevice.setCif(custProfile != null ? custProfile.getCif() : "");
			dao.insertEntity(rootDevice);
			allow = true;
		}
		return allow;
	}

	public String checkModuleMaintenanceForRooted()
	{

		String moduleMaintenance = pro.getProperty("androidRootedFlags");
		String moduleMaintenanceList[] = null == moduleMaintenance ? new String[0]
				: moduleMaintenance.split(",");

		String maintenanceResult = "";

		for (String module : moduleMaintenanceList)
		{
			String showModule = pro.getProperty(module);
			if (showModule != null && showModule.equalsIgnoreCase("true"))
			{
				maintenanceResult = maintenanceResult + "Y";

			} else
			{
				maintenanceResult = maintenanceResult + "N";

			}
		}

		return maintenanceResult;
	}

	public String checkModuleMaintenanceForRootedMethod()
	{

		String moduleMaintenance = pro.getProperty("androidRootedMethods");
		String moduleMaintenanceList[] = null == moduleMaintenance ? new String[0]
				: moduleMaintenance.split(",");

		String maintenanceResult = "";

		for (String module : moduleMaintenanceList)
		{
			String showModule = pro.getProperty(module);
			if (showModule != null && showModule.equalsIgnoreCase("true"))
			{
				maintenanceResult = maintenanceResult + "Y";

			} else
			{
				maintenanceResult = maintenanceResult + "N";

			}
		}

		return maintenanceResult;
	}

	public ObResponse filterAccountOverview(ObResponse responseData)
	{
		if (responseData instanceof ObAccountOverviewResponse)
		{
			ObAccountOverviewResponse accountOverviewResponse = (ObAccountOverviewResponse) responseData;
			List<ObAccountOverviewBean> accountOverviewBeanList = accountOverviewResponse
					.getAccountListing();
			if (accountOverviewBeanList != null && !accountOverviewBeanList.isEmpty())
			{
				Integer index = 1;
				for (ObAccountOverviewBean bean : accountOverviewBeanList)
				{
					bean.setIndex("ACC" + String.format("%04d", index));
					index++;
				}
			}
		}

		return responseData;
	}

	public ObResponse filterTimeDepositConf(ObResponse responseData)
	{
		if (responseData instanceof ObTimeDepositConfResponse)
		{
			ObTimeDepositConfResponse obTimeDepositConfResponse = (ObTimeDepositConfResponse) responseData;
			obTimeDepositConfResponse.setIndex("FDC" + String.format("%04d", 1));
		}

		return responseData;
	}

	public ObResponse filterAccountMaintenanceList(ObResponse responseData)
	{
		if (responseData instanceof ObAccountMaintenanceListResponse)
		{
			ObAccountMaintenanceListResponse obAccountMaintenanceListResponse = (ObAccountMaintenanceListResponse) responseData;
			List<ObAccountMaintenanceListBean> obAccountMaintenanceListBeanList = obAccountMaintenanceListResponse
					.getObAccountMaintenanceListBeanList();
			if (obAccountMaintenanceListBeanList != null
					&& !obAccountMaintenanceListBeanList.isEmpty())
			{
				Integer index = 1;
				for (ObAccountMaintenanceListBean bean : obAccountMaintenanceListBeanList)
				{
					bean.setIndex("AML" + String.format("%04d", index));
					index++;
				}
			}
		}

		return responseData;
	}

	public ObResponse filterAccountMaintenanceConf(ObResponse responseData)
	{
		if (responseData instanceof ObAccountMaintenanceConfResponse)
		{
			ObAccountMaintenanceConfResponse obAccountMaintenanceConfResponse = (ObAccountMaintenanceConfResponse) responseData;
			List<ObAccountMaintenanceConfResponseBean> obAccountMaintenanceConfResponseBeanList = obAccountMaintenanceConfResponse
					.getObAccountMaintenanceConfResponseBeanList();
			if (obAccountMaintenanceConfResponseBeanList != null
					&& !obAccountMaintenanceConfResponseBeanList.isEmpty())
			{
				Integer index = 1;
				for (ObAccountMaintenanceConfResponseBean bean : obAccountMaintenanceConfResponseBeanList)
				{
					bean.setIndex("AMC" + String.format("%04d", index));
					index++;
				}
			}
		}

		return responseData;
	}

	public int getResendTokenLimit()
	{

		EntityManager em = dao.getEntityManager();
		String sql = "FROM " + McbConf.class.getSimpleName() + " where status = '"
				+ MiBConstant.MIB_SYSTEM_STATUS_ACTIVE + "'";
		List<McbConf> hlbMiBConf = em.createQuery(sql).getResultList();

		if (hlbMiBConf.get(0).getResendTokenLimit() == null)
			return 0;
		else
			return hlbMiBConf.get(0).getResendTokenLimit();
	}

	public int getInputTokenSize()
	{

		EntityManager em = dao.getEntityManager();
		String sql = "FROM " + McbConf.class.getSimpleName() + " where status = '"
				+ MiBConstant.MIB_SYSTEM_STATUS_ACTIVE + "'";
		List<McbConf> hlbMiBConf = em.createQuery(sql).getResultList();

		if (hlbMiBConf.get(0).getInputTokenSize() == null)
			return 0;
		else
			return hlbMiBConf.get(0).getInputTokenSize();
	}
	
	public int getNonFinalCacheExpiredHour(){
		EntityManager em = dao.getEntityManager();
		String sql = "FROM " + McbConf.class.getSimpleName() + " where status = '"
				+ MiBConstant.MIB_SYSTEM_STATUS_ACTIVE + "'";
		List<McbConf> hlbMiBConf = em.createQuery(sql).getResultList();

		if (hlbMiBConf.get(0).getCacheExpiredHour() == null)
			return 0;
		else
			return hlbMiBConf.get(0).getCacheExpiredHour();
	}
	
	public int getFinalCacheExpiredHour(){
		EntityManager em = dao.getEntityManager();
		String sql = "FROM " + McbConf.class.getSimpleName() + " where status = '"
				+ MiBConstant.MIB_SYSTEM_STATUS_ACTIVE + "'";
		List<McbConf> hlbMiBConf = em.createQuery(sql).getResultList();

		if (hlbMiBConf.get(0).getFinalCacheExpiredHour() == null)
			return 0;
		else
			return hlbMiBConf.get(0).getFinalCacheExpiredHour();
	}
	
//	public int getTakaCacheExpiredDays(){
//		EntityManager em = dao.getEntityManager();
//		String sql = "FROM " + MiBConf.class.getSimpleName() + " where status = '"
//				+ MiBConstant.MIB_SYSTEM_STATUS_ACTIVE + "'";
//		List<MiBConf> lsMiBConf = em.createQuery(sql).getResultList();
//
//		if (lsMiBConf.get(0).getTakaCacheExpiredDays() == null)
//			return 0;
//		else
//			return lsMiBConf.get(0).getTakaCacheExpiredDays();
//	}
	
	
	
	public List<String> getStatementDate(String noDays) throws Exception
	{
		//2019-05-20
		//3 month only
		
		Date fromDateTime = null;
		Date toDateTime = null; 
		SimpleDateFormat df = new SimpleDateFormat(MiBConstant.YYYYMMDD_FORMAT);
		 
		String toDate = df.format(new Date());
		
		Date today = df.parse(toDate);
		 
		int numberOfDays = Integer.parseInt(noDays);
		
		long oneDay = 1000*60*60*24;
		
		Date calDay = new Date(today.getTime()-(oneDay*numberOfDays));
	 
		String fromDate = df.format(calDay);
		
		List<String> result = new ArrayList();
		
		result.add(fromDate);
		result.add(toDate);
		
		return result;
	}
}
