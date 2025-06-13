package com.silverlake.mleb.pns.constant;

public class PNSConstant
{
	public static final String APP_PROPERTIES_PATH = "/apps/my/";
	
	public final static String PNS_COMMON_ERROR_CODE = "ERROR.PNS.9999999";
	public final static String PNS_COMMON_ERROR_MESG = "Internal Error. Failed to push notification.";
	
	
	public final static String PNS_SUCCESS_CODE = "0000000";
	public final static String PNS_SUCCESS_MESG = "Successful.";
	
	public final static String PNS_INVALID_DEVICE_OS_CODE = "0000001";
	public final static String PNS_INVALID_DEVICE_OS_MESG = "Invalid notification type.";
	
	public final static String PNS_INVALID_DEVICE_TOKEN_CODE = "0000002";
	public final static String PNS_INVALID_DEVICE_TOKEN_MESG = "Invalid Device Token.";
	
	public static final String PNS_GCM_FAIL_CODE = "0000102";
	public static final String PNS_GCM_FAIL_MESG = "GCM return failed.";
	
	public static final String PNS_GCM_NOT_REG_CODE = "0000107";
	public static final String PNS_GCM_NOT_REG_MESG = "Device not registered.";
	
	public static final String PNS_APN_FAIL_CODE = "0000003";
	public static final String PNS_APN_FAIL_MESG = "Failed to send to APN";
	
	public static final String PNS_CANT_PUSH_TO_GCM_CODE = "0000004";
	public static final String PNS_CANT_PUSH_TO_GCM_MESG = "Cannot push Notificaion through GCM.";
	
	public static final String PNS_INVALID_REQUEST_CODE = "0000005";
	public static final String PNS_INVALID_REQUEST_MESG = "Invalid Request";
	
	public final static String PNS_INVALID_APP_ID = "0000006";
	public final static String PNS_INVALID_APP_ID_MESG = "Invalid app id/password";
	
	public static final String PNS_INTERNAL_ERROR_CODE = "0000009";
	public static final String PNS_INTERNAL_ERROR_MESG = "Internal Error";
	
	public final static String FUNC_PUSH_NOTIF = "FUNC_PUSH_NOTIF";
	
	
	
	public final static String OS_TYPE_ANDROID = "Android";
	public final static String OS_TYPE_IOS = "iOS";
	public final static String OS_TYPE_IPAD = "iPad";
	
	
	public final static String TRX_STATUS_ACCEPTED = "ACCEPTED";
	public final static String TRX_STATUS_SENT = "SENT";
	public final static String TRX_STATUS_FAILED = "FAILED";
	
	public final static String PROXY_IP_STATUS_ACTIVE = "ACTIVE";
	
	public final static String PNS_TEMPLATE_PEX_REQ_CREATE = "HLB_PNS_PEX_REQ_CREATE";
	public final static String PNS_TEMPLATE_PEX_REQ_REMINDER = "HLB_PNS_PEX_REQ_REMINDER";
	public final static String PNS_TEMPLATE_PEX_REQ_REJECT = "HLB_PNS_PEX_REQ_REJECT";
	public final static String PNS_TEMPLATE_PEX_REQ_APPROVED = "HLB_PNS_PEX_REQ_APPROVED";
	public final static String PNS_TEMPLATE_PEX_REQ_EXPIRED = "HLB_PNS_PEX_REQ_EXPIRED";
	public final static String PNS_TEMPLATE_TEST = "HLB_PNS_TEST";
	public final static String PNS_TEMPLATE_PROMOTION = "HLB_PNS_PROMOTION";
	public final static String PNS_TEMPLATE = "OCBC_PUSHNOTIFICATION";
	
	public final static String PNB_PROMO_STATUS_PENDING = "P";
	public final static String PNB_PROMO_STATUS_FAILED = "F";
	public final static String PNB_PROMO_STATUS_COMPLETED = "CPT";
	public final static String PNB_PROMO_STATUS_CANCELLED = "CCL";
	
	public final static String PNB_PROMO_FLAG_INPROGRESS = "IP";
	public final static String PNB_PROMO_FLAG_RETRY = "R";
	public final static String PNB_PROMO_FLAG_INPROGRESS_RETRY = "IPR";
	
	public final static String PNB_TRX_STATUS_PENDING = "PENDING";
	public final static String PNB_TRX_STATUS_SUCCESS = "SUCCESS";
	public final static String PNB_TRX_STATUS_CANCELLED = "CANCELLED";
	public final static String PNB_TRX_STATUS_RETRY = "RETRY";
	public final static String PNB_TRX_STATUS_FAILED = "FAILED";
	
	
	public final static String FUNC_PNB_RETRIEVE_PUSH_NOTIF_HIST = "FUNC_PNB_RETRIEVE_PUSH_NOTIF_HIST";
	
	public final static String PNB_PUSH_ENABLE_FLAG_YES = "Y";
	public final static String PNB_PUSH_ENABLE_FLAG_NO = "N";
}
