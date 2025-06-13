package com.silverlake.mleb.pex.constant;


public class PExConstant
{
	public final static int SQLLongTextLength = 1024;
	public final static String IB_ObHeader_TimeFormat = "yyyyMMddhhmmss";
	public final static String MiB_TimeFormat ="yyMMddhhmmssSSS";
	public final static String IB_ChannelID = "MIB";
	public final static String IB_remoteAddress = "172.16.1.126";
	public final static String IB_requestUrl = "";
	public final static String IB_Client_sessionID = "VDC77FD0BD71F9675108643F01FCA815";
	public final static String IB_version = "0.2.0001";
	public final static int CONNECT_TIMEOUT = 3000;
	public final static int REQUEST_TIMEOUT = 30 * 1000;
	
	public final static String PEX_TRANX_DATE = "yyyy-MM-dd'T'HH:mm:ssZ";
	
	public final static String APP_PROPERTIES_PATH = "/apps/vn";
	public static final String  contactNumberPattern = "601\\d{8,9}";
	public static final String  contactNumberPatternKH = "855\\d{8,9}";
	
	public static final String  contactNumberPatternKH1 = "849\\d{8,9}";
	public static final String  contactNumberPatternKH2 = "841\\d{8,9}";
	public static final String  contactNumberPatternKH3 = "09\\d{8,9}";
	public static final String  contactNumberPatternKH4 = "01\\d{8,9}";
	public static final String  contactNumberPatternKH5 = "\\d{20}";
	
	public final static String PEX_SEQ_NUM_TYPE = "RBS";
	public final static String PEX_SYS_SEQ_NUM_TYPE = "SYS";
	
	public static final String ref_prefix = "R-PEx";
	
	public static final String FROM_ACC_LIST_IB_TYPE = "OWN_ACCT_FT";
	
	public final static String PEX_MSG_SERVICE = "PEX";
	public final static String PEX_MSG_SOCKET_TYPE = "1";
	public final static String PEX_MSG_SMS_TYPE = "2";
	
	
	public final static String PEX_SMS_STATUS_ATM_CREATION= "00001";
	public final static String PEX_SMS_STATUS_INTERNET_CREATION = "00002";
	public final static String PEX_SMS_STATUS_DIRECT_CREATION = "00003";
	
	
	public final static String PEX_COUNTRY_CODE_MY = "MY";
	public final static String PEX_COUNTRY_CODE_KH = "KH";
	
	
	public final static String PEX_SMS_TAC_ACTION_CODE = "MAKE_PEX";
	public final static String PEX_SMS_TAC_FUNC_CODE = "PEX_COL";
	
	
	//status display after perform transaction
	public final static String PEX_STATUS_TRANSACTION_SUCCESS = "Successful";
	public final static String PEX_STATUS_TRANSACTION_PENDING_COLLECTION = "Pending Collection";
	public final static String PEX_STATUS_TRANSACTION_IN_PROGRESS= "In Progress";
	public final static String PEX_STATUS_TRANSACTION_FAILED = "Failed";
	
	public final static String PEX_STATUS_TRANSACTION_SUCCESS_KH = "ThÃ nh cÃ´ng";
	public final static String PEX_STATUS_TRANSACTION_PENDING_COLLECTION_KH = "Chá»� nháº­n tiá»�n";
	public final static String PEX_STATUS_TRANSACTION_IN_PROGRESS_KH= "Cháº¥p nháº­n Ä‘á»ƒ tiáº¿n hÃ nh";
	public final static String PEX_STATUS_TRANSACTION_FAILED_KH = "KhÃ´ng thÃ nh cÃ´ng";
	

	public final static String PEX_ERR_COMMON_ERROR = "ERROR_PEX.9999999";
	public final static String PEX_ERR_ACK_ERROR = "ERROR_PEX.9999998";
	public final static String PEX_ERR_INVALID_FUNC_ERROR = "ERROR_PEX.9999000";
	public final static String PEX_ERR_INVALID_WS_REQUEST_HEADER = "ERROR_PEX.9998000";
	public final static String PEX_ERR_COMMON_SUCCESS = "0000000";
	public final static String PEX_ERR_INVALID_COLLECTION= "PEX.0100000";//0100001
	public final static String PEX_ERR_INVALID_COLLECTIONNO = "PEX.0100001";
	public final static String PEX_ERR_INVALID_COLLECTIONDATA = "PEX.0100002";
	public final static String PEX_ERR_COLLECTION_EXPIRED = "PEX.0100003";
	public final static String PEX_ERR_COLLECTION_PROCESSING = "PEX.0100004";
	public final static String PEX_ERR_COLLECTION_PAID = "PEX.0100005";
	public final static String PEX_ERR_COLLECTION_CANCELLED = "PEX.0100006";
	public final static String PEX_ERR_DUPLICATE_MOBILE_FOUND = "PEX.0100007";
	public final static String PEX_ERR_ACTIVE_MOBILE_NOT_FOUND= "PEX.0100008";
	public final static String PEX_ERR_TRANSACTION_LIMIT_EXISTED = "PEX.0100009";
	public final static String PEX_ERR_DAILY_LIMIT_EXISTED = "PEX.0100010";
	public final static String PEX_ERR_INSUFFICIENT_FUNDS = "PEX.0100011";
	public final static String PEX_ERR_RBS_SERVICE_UNAVAILABLE = "PEX.0100012";
	public final static String PEX_ERR_MISSING_FIELD = "PEX.0100013";
	public final static String PEX_ERR_INVALID_COLLECTION_TYPE = "PEX.0100014";
	public final static String PEX_ERR_INVALID_PEX_STATUS_TYPE = "PEX.0100015";
	public final static String PEX_ERR_FROM_ACCOUNT_NOT_FOUND = "PEX.0100016";
	public final static String PEX_ERR_INVALID_TO_NUMBER = "PEX.0100017";
	public final static String PEX_ERR_NO_TRANSACTION_RECORD = "PEX.0100018";
	public final static String PEX_ERR_INVALID_BENEFICIARY_INPUT = "PEX.0100019";
	public final static String PEX_ERR_INTERNET_NOT_ALLOWED = "PEX.0100020";
	public final static String PEX_ERR_COLLECTION_SUSPENDED = "PEX.0100021";
	public final static String PEX_ERR_COLLECTION_FAILED = "PEX.0100022";
	public final static String PEX_ERR_COLLECTION_ACCEPTED = "PEX.0100023";
	public final static String PEX_ERR_MOBILE_NUMBER_NOT_FOUND = "PEX.0100024";
	public final static String PEX_ERR_INVALID_ATM_TERMINALID = "PEX.0100025";
	
	//biometric
	public final static String PEX_QUICK_PEX_LIMIT_EXCEEDED = "PEX.0100042";
	
	public final static String COMMON_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public final static String COMMON_DATETIME_FORMAT_HIST = "dd-MMM-yyyy HH:mm:ss";
	public final static String PEX_TRANSACTION_DATETIME_FORMAT = "dd-MMM-yyyy HH:mm";
	public final static String PEX_TRANSACTION_AMOUNT_FORMAT = "###,###,##0.00";
	public final static String PEX_TRANSACTION_AMOUNT_NORMAL_FORMAT = "##0.00";
	public final static String PEX_RBS_DATE = "ddMMyy";
	public final static String PEX_RBS_EXPDATE = "ddMMyy";
	public final static String PEX_REF_DATE_FORMAT = "yyyyMMdd";
	

	public final static String rbs_date_mock = "201213";
	
	public final static String PEX_TRANSACTION_STATUS_ACTIVE = "ACTIVE";
	public final static String PEX_TRANSACTION_STATUS_SUSPENDED = "SUSPENDED";
	public final static String PEX_TRANSACTION_STATUS_EXPIRED = "EXPIRED";
	public final static String PEX_TRANSACTION_STATUS_CANCELLED= "CANCELLED";
	public final static String PEX_TRANSACTION_STATUS_PROCESSING = "PROCESSING";
	public final static String PEX_TRANSACTION_STATUS_FAILED = "FAILED";
	public final static String PEX_TRANSACTION_STATUS_PAID = "PAID";
	//public final static String PEX_TRANSACTION_STATUS_ERROR = "ERROR";
	public final static String PEX_TRANSACTION_STATUS_ACCEPTED = "ACCEPTED";
	
	
	//biometric quick limit
	public final static String PEX_BIOMETRIC_ANDROID_CHECK = "ANDROID";
	public final static String PEX_BIOMETRIC_IOS_CHECK = "IOS";
	
	
	
	public final static String PEX_TRANSACTION_STATUS_ACCEPTED_NAME = "Collection in Progress";
	public final static String PEX_TRANSACTION_STATUS_ACCEPTED_DIRECT_NAME = "Accepted for Processing";
	
	public final static String PEX_ACC_STATUS_ACTIVE = "ACTIVE";
	public final static String PEX_ACC_STATUS_INACTIVEx = "INACTIVE";
	public final static String PEX_ACC_STATUS_DISABLED = "DISABLED"; 
	public final static String PEX_ACC_STATUS_ACTIVE_KH = "Ä�ang hoáº¡t Ä‘á»™ng";
	public final static String PEX_ACC_STATUS_INACTIVEx_KH = "khÃ´ng hoaÌ£t Ä‘Ã´Ì£ng";
	public final static String PEX_ACC_STATUS_DISABLED_KH = "Há»§y Ä‘Äƒng kÃ½";
	
	public final static String PEX_COLLECTION_FLAG_OPEN = "OPEN";
	public final static String PEX_COLLECTION_FLAG_LOCKED = "LOCKED";

	public final static String FUNC_CREATION_PEX = "FUNC_HLB_CREATIONPEX";
	public final static String FUNC_CONFIRM_CREATION_PEX = "FUNC_HLB_CONFIRMCREATIONPEX";
	public final static String FUNC_PERFORM_CREATION_PEX = "FUNC_HLB_PERFORMCREATIONPEX";
	public final static String FUNC_CANCEL_PEX = "FUNC_HLB_CANCELPEX";
	public final static String FUNC_RETRIEVE_PEX_ACC = "FUNC_HLB_RETRIEVEPEXACC";
	public final static String FUNC_CONFIRM_PEX_ACC = "FUNC_HLB_CONFIRMPEXACC";
	public final static String FUNC_UPDATE_PEX_ACC = "FUNC_HLB_UPDATEPEXACC";
	public final static String FUNC_CREATION_CHECK_DIRECT_PEX = "FUNC_HLB_CHECKDIRECTPEX";
	public final static String FUNC_RETRIEVE_PEX_TRANSACTION_HIS = "FUNC_HLB_RETRIEVEPEXHIS";
	public final static String FUNC_CREATION_RESEND_TAC_PEX = "FUNC_HLB_RESENDTACPEX";
	public final static String FUNC_PEX_PUSH_NOTIFICATION = "FUNC_PEX_PUSH_NOTIF";
	public final static String FUNC_PNB_RETRIEVE_PUSH_NOTIF_HIST = "FUNC_PNB_RETRIEVE_PUSH_NOTIF_HIST";
	public final static String FUNC_APP_STAT_INFO = "FUNC_APP_STAT_INFO_PEX";
	public final static String[] All_FUNCID = {
		FUNC_CREATION_PEX,FUNC_CONFIRM_CREATION_PEX,FUNC_PERFORM_CREATION_PEX,
		FUNC_CANCEL_PEX,FUNC_RETRIEVE_PEX_ACC,FUNC_CONFIRM_PEX_ACC,
		FUNC_UPDATE_PEX_ACC,FUNC_CREATION_CHECK_DIRECT_PEX,FUNC_RETRIEVE_PEX_TRANSACTION_HIS,
		FUNC_CREATION_RESEND_TAC_PEX,FUNC_PEX_PUSH_NOTIFICATION,FUNC_PNB_RETRIEVE_PUSH_NOTIF_HIST,FUNC_APP_STAT_INFO};
	
	public final static String PEX_COLLECTION_TYPE_ATM = "ATM";
	public final static String PEX_COLLECTION_TYPE_INTERNET = "ITN";
	public final static String PEX_COLLECTION_TYPE_DIRECT = "DRT";
	
	
	
	public final static String PEX_COLLECTION_NAME_ATM = "ATM";
	public final static String PEX_COLLECTION_NAME_INTERNET = "Internet";
	public final static String PEX_COLLECTION_NAME_DIRECT = "PEx Direct ";
	
	
	public final static String PEX_COLLECTION_CHANNEL_OCM = "*EBS";
	public final static String PEX_COLLECTION_VERSION_OCM = "1001";
	
	public final static String PEX_COLLECTION_CHANNEL_FUZION = "01";
	public final static String PEX_COLLECTION_VERSION_FUZION = "1.0";

	public final static String PEX_CONF_STATUS_ACTIVE = "ACTIVE";
	public final static String PEX_BANK_ROUTE_STATUS_ACTIVE = "ACTIVE";
	
	
	public final static String PEX_ERROR_CODE_RBS_TIMEOUT = "RBS_TIMEOUT";
	public final static String PEX_ERROR_CODE_RBS_UNAVAILABLE = "RBS_UNAVAILABLE";
	public final static String PEX_ERROR_MSG_RBS_TIMEOUT_RESOLVED = "RBS_RESOLVED";
	
	//beneficiary bank code 
	public final static String FT_BENEFICIARY_BANK_CODE = "001";
	
	
	public final static String PEX_CHARGES_STATUS_ACTIVE = "ACTIVE";
	public final static String PEX_CHARGES_STATUS_PENDING = "PENDING";
	public final static String PEX_CHARGES_STATUS_DEACTIVATED = "DEACTIVATED";
	
	public final static String PEX_SMS_FLAG_SENT = "SENT";
	public final static String PEX_SMS_FLAG_PENDING = "PENDING";
	public final static String PEX_SMS_FLAG_PROCCESSING = "PROCCESSING";
	
	
	
	public final static String PEX_IB_FLAG_UPDATED = "UPDATED";
	public final static String PEX_IB_FLAG_UPDATING = "UPDATING";
	public final static String PEX_IB_FLAG_ADDED = "ADDED";
	public final static String PEX_IB_FLAG_PENDING_ADD = "PENDING_ADD";
	public final static String PEX_IB_FLAG_PENDING_UPDATED = "PENDING_UPDATE";
	
	public final static String[] PEX_TRANX_STATUS_TYPE = {"PENDING","CANCELLED","SUCCESSFUL","FAILED","INPROGRESS"};
	
	//biometric
	public final static String LOGIN_METHOD_NORMAL = "NORMAL";
	
	public final static String FZ_RBS_ERROR_FORMAT =  "ERR_INT_RBS_";
	
	public final static String FZ_EAI_ERROR_FORMAT =  "ERR_INT_EAI_";
	
	public final static String INDV_EN = "Indiv"; 
	public final static String STAFF_EN = "Staff";
	public final static String CORP_EN = "Corp";
	public final static String CURRENT_ACCOUNT_EN = "Current Account"; 
	public final static String SAVINGS_ACCOUNT_EN = "Savings Account"; 
	public final static String OD_EN =  "with OD"; 
	public final static String HISAVER_EN =  "Hi-Saver"; 
	
	public final static String INDV_KH = " CÃ¡ nhÃ¢n"; 
	public final static String STAFF_KH = " NhÃ¢n viÃªn";
	public final static String CORP_KH = " Doanh nghiá»‡p";
	public final static String CURRENT_ACCOUNT_KH = "TK VÃ£ng lai"; 
	public final static String SAVINGS_ACCOUNT_KH = "TK Tiáº¿t kiá»‡m";
	public final static String OD_KH =  "tháº¥u chi"; 
	public final static String HISAVER_KH =  "Hi-Saver";  
	
	public final static String GENERAL_ERR_MSG = "Sorry, we are unable to process your request. Please try again later.";
	public final static String GENERAL_ERR_MSG_KH = "YÃªu cáº§u/giao dá»‹ch cá»§a QuÃ½ khÃ¡ch táº¡m thá»�i khÃ´ng thá»±c hiá»‡n Ä‘Æ°á»£c."; 

    //EAI status code
	public final static String PEX_ERROR_CODE_EAI_TIMEOUT = "EAI_TIMEOUT";
	public final static String PEX_ERROR_CODE_EAI_UNAVAILABLE = "EAI_UNAVAILABLE";


}