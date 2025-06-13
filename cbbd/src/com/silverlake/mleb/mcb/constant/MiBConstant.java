package com.silverlake.mleb.mcb.constant;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MiBConstant
{
	public final static String MODULETYPE_MANAGEBENE_TT = "MBTT";
	
	public final static String CHANNEL_MB = "MB";
	public final static String GENERAL_OPERATIONCODE = "000";
	public final static String EPI_OPERATIONCODE = "010";
	public final static String PAL_OPERATIONCODE = "100";
	
	public final static String OC_000 = "000";	//LLG
	public final static String OC_100 = "000";	//RTGS
	
	public final static String OCBC_BANKNAME = "OCBCNISP";
	public final static String OCBC_BANK_NAME = "OCBC NISP";

	public final static String MiB_TimeFormat = "yyMMddhhmmssSSS";

	public static final String DEVICE_PROFILE_DATE_FORMAT = "dd-MMM yyyy HH:mm:ss";
	
	public static final String FUND_TRANSFER_ACK_DATE_FORMAT = "EEEE dd/MM/yyyy, HH:mm";

	public static final String DDMMMYYYY_FORMAT = "dd-MMM-yyyy";
	public static final String DDMMMYYYY_FORMAT_TIME = "dd-MMM-yyyy HH:mm:ss";
	public static final String HHMMSS_FORMAT = "HH:mm:ss";
	public static final String YYYYMMDD_FORMAT = "yyyy-MM-dd";
	public static final String HHMM_FORMAT = "HH:mm";
	public static final String DATETIME_ISO_OFFSET_DATE_TIME = "yyyy-MM-dd'T'HH:mm:ssXXX";//2019-01-28T00:00:00+07:00

	public static final String MMMYYYY_FORMAT = "MMM-yyyy";
	public static final String DB_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public final static String MAINTENANCE_MESSAGE = "MIB_MAINTENANCE_MESSAGE";
	public final static String ROOTED_MESSAGE = "MIB_ROOTED_MESSAGE";
	public final static int CONNECT_TIMEOUT = 3000;
	public final static int REQUEST_TIMEOUT = 60 * 1000;
	public final static String TIME_TWELVE_MIGNIGHT = "00:00:00.00";
	public final static String TIME_ELEVN_MIDNIGHT = "23:59:59.59";
	public final static String TIME_MY_TIMEZONE = "+08:00";
	public final static String OMNI_DATE_FORMAT = "MMM dd, yyyy hh:mm:ss a";
	public final static String DUKCAPIL_DATE_FORMAT = "yyyy-MM-dd";
	public static final String DD_MMM_YYYY_FORMAT = "dd MMM yyyy";
	public final static String AOFORM_DATE_FORMAT = "ddMMyy";
	public final static String MC_BIT = "N";

	public final static String MIB_FUZION_ERROR_PREFIX = "fz.";
	public final static String MIB_EVENT_PREFIX = "EVENT.";
	public final static String MIB_OMNI_PREFIX = "omni.";

	public final static String MIB_GENERAL_ERROR = "ERROR.MCB.GENERAL";

	public final static String MIB_COMMON_ERROR = "ERROR.MCB.9999999";
	public final static String MIB_INVALID_FUNC_ERROR = "ERROR.MCB.9999000";
	public final static String MIB_INVALID_VERSION_ERROR = "ERROR.MCB.9999001";
	public final static String MIB_SERVER_UNDER_MAINTENANCE = "ERROR.MCB.9999002";
	public final static String MIB_SERVER_UNDER_NIGHTMODE = "ERROR.MCB.9999003";
	public final static String MIB_BLOCK_ROOTED_DEVICE = "ERROR.MCB.9999004";
	public final static String MIB_COMMON_SUCCESS = "0000000";
	public final static String MIB_STATUSCODE_UNTAGGED_DEVICE = "MCB.0000001";
	public final static String MIB_STATUSCODE_TAGGED_XDEVICE = "MCB.0000002";
	public final static String MIB_STATUSCODE_DEVICEID_NOT_FOUND = "MCB.0000003";
	public final static String MIB_COMMON_NO_RESULT = "MCB.0000004";
	public final static String MIB_INVALID_INPUT = "MCB.0000005";
	public final static String MIB_INVALID_ACCOUNT_TYPE = "MCB.0000007";
	public final static String MIB_STATUSCODE_DEVICETAGGED_EXISTED_LIMIT = "MCB.0000008";
	public final static String MIB_STATUSCODE_INACTIVE_DEVICEID = "MCB.0000009";
	public final static String MIB_FILE_NOT_FOUND = "MCB.0000010";
	public final static String MIB_UNABLE_TRANSFER_NEW_BENE = "MCB.0000025";
	
	public final static String MIB_NO_THIRD_PARTY_HLB_ACCOUNT = "MCB.0000011";
	public final static String MIB_TRANSACTION_EXISTED_LIMIT = "MCB.0000012";
	public final static String MIB_TRANSACTION_LIMIT_NOTAVAILABLE = "MCB.0000013";
	public final static String MIB_INVALID_DEVICE_TYPE = "MCB.0000014";
	public final static String MIB_NO_ACCOUNT = "MCB.0000015";
	public final static String MIB_NO_LOAN_ACCOUNT = "MCB.0000016";
	public final static String MIB_NO_CREDIT_CARD = "MCB.0000017";
	public final static String MIB_TO_AND_FROM_SAME_ACCOUNT = "MCB.0000018";
	public final static String MIB_INPUT_INCOMPLETE = "MCB.0000019";
	public final static String MIB_OTH_DETAIL = "MCB.0000021";
	public final static String MIB_INVALID_INPUT_CHARACTER_ELEVEN = "MCB.0000035"; //0-9a-zA-Z ,.:;+()?-
	public final static String MIB_STATUSCODE_RSA_CHALLENGE = "MCB.0000035";
	public final static String MIB_STATUSCODE_RSA_AND_UNTAGGED_DEVICE_TAC = "MCB.0000036";
	public final static String MIB_STATUSCODE_RSA_CHALLENGE_WITH_NO_MOBILENO = "MCB.0000037";
	public final static String MIB_STATUS_RSA_SECURITY_QUESTION_CHALLENGE_LOGIN = "MCB.0000054";
	public final static String MIB_NO_SAVINGS_ACCOUNT = "MCB.0000024";
	public final static String MIB_INVALID_INPUT_ALPHANUMERIC = "MCB.0000038";
	public final static String MIB_INVALID_INPUT_ADDRESS = "MCB.0000039";
	
	public final static String MCB_LIMITED_ACCESS = "MCB.1000100";
	public final static String MCB_UNTAGGED_DEVICE = "MCB.1000101";
	public final static String MCB_UNTAGGED_DEVICE_AND_NO_MOBILE = "MCB.1000102";
	public final static String MCB_UNTAGGED_DEVICE_AND_EXCEED_LIMIT = "MCB.1000103";
	public final static String MCB_UNTAGGED_DEVICE_AND_EXCEED_LIMIT_AND_NO_MOBILE = "MCB.1000104";
	public final static String MCB_BIND_DEVICE_EXCEED_LIMIT = "MCB.1000105";
	public final static String MCB_BIND_DEVICE_BINDED = "MCB.1000106";
	public final static String MCB_BIND_DEVICE_INVALID_DEVICE_TYPE = "MCB.1000107";
	public final static String MCB_RESEND_SMS_OVER_LIMIT = "MCB.1000108";
	public final static String MCB_RESEND_SMS_OVER_INTERVAL = "MCB.1000109";
	public final static String MIB_STATUSCODE_SUCCESS_UNTAGGED_CURRENT_DEVICE = "MCB.1000110";
	public final static String MCB_NO_BINDED_DEVICE= "MCB.1000111";
	public final static String MCB_NO_ACCOUNT_FOUND= "MCB.1000112";
	public final static String MCB_INVALID_TNC_ACTION= "MCB.1000113";
	public final static String MCB_INVALID_BINDED_DEVICE = "MCB.1000114";
	public final static String MCB_NO_FAQ_FOUND="MCB.1000115";
	public final static String MCB_NO_NOTIFICATION_FOUND="MCB.1000116";
	public final static String MCB_EXCHANGE_RATE_MISSING="MCB.1000117";
	public final static String MCB_NO_STATEMENT_HIST_FOUND="MCB.1000118";
	public final static String MCB_BACKDATEDLIST_EMPTY="MCB.1000119";
	public final static String MCB_SWAPDEVICE_OVERLIMIT="MCB.1000120";
	public final static String MCB_NO_TOKEN_FOUND= "MCB.1000121";
	public final static String MCB_NO_AVAILABLE_AMOUNT_OPTION= "MCB.1000122";
	public final static String MCB_ERROR_DOWNLOADING_STATEMENT = "MCB.1000123";
	public final static String MCB_DEVID_PEND_EXPIRED = "MCB.1000125";
	public final static String MCB_UNBIND_PRIMARY_DEVICE = "MCB.1000126";
	public final static String MCB_INVALID_SWITCH = "MCB.1000127";

	// fund transfer
	public final static String MIB_INVALID_TRANX_SERVICE = "MCB.0000107";
	public final static String MIB_NO_SMS_TOKEN = "MCB.0000112";
	public final static String MIB_INVALID_SMS_TOKEN_STATUS = "MCB.0000113";

	// Unit Trust
	public final static String MIB_NO_UNIT_TRUST_ACC = "MCB.0000114";
	public final static String MIB_NO_PRODUCT_LIST = "MCB.0000119";
	public final static String MIB_UT_FAILED_INQUIRY = "MCB.0000146";
	

	// resend token limit
	public final static String MIB_RESEND_TOKEN_LIMIT = "MCB.0000100";

	// Time Deposit
	public final static String MIB_LIST_ACCOUNT_EMPTY = "MCB.0000116";
//	public final static String MIB_FD_NO_SOFT_TOKEN = "MCB.0000117";
	public final static String MIB_EMPTY_TDPRODUCT_LIST = "MCB.0000144";
	
	// Account Maintenance
//	public final static String MIB_AM_NO_SOFT_TOKEN = "MCB.0000118";

	// Internet Transaction History
	public final static String MIB_ITH_NO_ACCOUNT = "MCB.0000124";

	// token length limit
	public final static String MIB_TOKEN_LENGTH_LIMIT = "MCB.0000108";

	// Payment & Purchase
	public final static String MIB_NO_BILLER_GRP = "MCB.0000101";
	public final static String MIB_NO_BILLER = "MCB.0000102";
	public final static String MIB_NO_AVAILABLE_ACCOUNT = "MCB.0000103";
	public final static String MIB_UNDER_PAYMENT_MIN_LIMIT = "MCB.0000104";
	public final static String MIB_UNDER_TRAN_MIN_LIMIT = "MCB.0000150";

	public final static String MIB_NO_BILL_INFO = "MCB.0000106";
	public final static String MIB_PAYMENT = "purchase";

	// Account Transaction History Error Code
	public final static String MIB_COUNT_LAST_TRANSACTIONS = "MCB.0000109";
	public final static String MIB_TRANSACTIONS_DATE_RANGE = "MCB.0000110";
	public final static String MIB_INVALID_TRANSACTIONS_DATE_RANGE = "MCB.0000111";

	// Account Transaction History PDF Error Code
	public final static String MIB_INVALID_FILTERCATEGORY = "MCB.0000125";
	public final static String MIB_INVALID_FILTERRANGE = "MCB.0000126";
	public final static String MIB_NO_PDF_FILE = "MCB.0000128";
	
	// Registration
	public final static String MIB_INVALID_REG_ATM_CARD = "MCB.0000129";
	
	//Account On Boarding
	public final static String MIB_INVALID_POSTAL_CODE = "MCB.0000130";
	public final static String MIB_EMPTY_POSTAL_LIST = "MCB.0000131";
	public final static String MIB_EMPTY_AOPRODUCT_LIST = "MCB.0000132";
	public final static String MIB_EMPTY_AODOCUMENT_LIST = "MCB.0000133";
	public final static String MIB_EMPTY_AOCREDITCARD_LIST = "MCB.0000134";
	public final static String MIB_EMPTY_AONATIONALITY_LIST = "MCB.0000135";
	public final static String MIB_ONBOARD_IS_AML_LIST = "MCB.0000136";
	public final static String MIB_ONBOARD_ID_ALREADY_EXIST = "MCB.0000137";
	public final static String MIB_ONBOARD_SAME_DEVICE_ID_FOUND = "MCB.0000138";
	public final static String MIB_ONBOARD_NO_CACHE_FOUND = "MCB.0000139";
	public final static String MIB_ONBOARD_NO_INTERESTRATE_RECORD_FOUND = "MCB.0000140";
	public final static String MIB_ONBOARD_NO_PARAMS_RECORD_FOUND = "MCB.0000141";
	public final static String MIB_ONBOARD_IMG_CACHE_DELETED = "MCB.0000142"; // OCBC2BUIXU-716
	public final static String MIB_ONA_TAKA_LT_MINAMOUNT = "MCB.0000143"; 
	public final static String MIB_ONA_TAKA_GT_MAXAMOUNT = "MCB.0000144"; 
	public final static String MIB_ONA_TAKA_PRODUCT_MISMATCH = "MCB.0000145"; 
	// Manage Recurring Module
	public final static String MIB_FAILED_DELETE_RECURRING = "MCB.0000120";

	// Manage Future Transfer Module
	public final static String MIB_FAILED_DELETE_FUTURE_TRANSFER = "MCB.0000121";

	// Manage Autodebit Module
	public final static String MIB_FAILED_DELETE_AUTODEBIT = "MCB.0000122";

	// Manage Future Payment Module
	public final static String MIB_FAILED_DELETE_FUTURE_PAYMENT = "MCB.0000123";

	// Manage Payee Module
	public final static String MIB_FAILED_DELETE_PAYEE = "MCB.0000124";
	
	//eagle eye portfolio
	public final static String NO_EAGLE_EYE_LIST ="MCB.0000127";

	public final static String MIB_FOREIGN_X_FOREIGN = "MCB.CCY.0000002";
	
	//not valid value date
	public final static String NOT_VALID_VALUE_DATE = "MCB.1000124";
	
	// Status
	public static final Character STS_ACTIVE = 'A';
	public static final String PRO_ACTIVE = "ACTIVE";
	public static final String PRO_DEACTIVATED = "DEACTIVATED";
	public static final String PRO_SUCCEED = "SUCCEED";
	public static final String PRO_SENT = "SENT";
	public static final Character STS_DEACTIVATED = 'D';
	public static final Character STS_DELETE = 'X';

	// Boolean Character
	public static final Character OPTION_YES = 'Y';
	public static final Character OPTION_NO = 'N';
	public static final String OPTION_YES_KH = "vÃƒÂ¢ng";
	public static final String OPTION_NO_KH = "KhÃƒÂ´ng";

	// Action Drop Down List
	public static final String ACTION_ADD = "ADD";
	public static final String ACTION_VIEW = "VIEW";
	public static final String ACTION_MODIFY = "MODIFY";
	public static final String ACTION_DELETE = "DELETE";

	// Account Type
	public final static String ACCOUNT_TYPE_SAVING = "S";
	public final static String ACCOUNT_TYPE_FIXED_DEPOSIT = "FD";
	public final static String ACCOUNT_TYPE_DEBIT_CARD = "D";
	public final static String ACCOUNT_TYPE_MASTER_CARD = "MC";
	public final static String ACCOUNT_TYPE_LOAN = "L";
	public final static String ACCOUNT_TYPE_MONEYBOX = "MB";
	public final static String ACCOUNT_TYPE_DREAMJAR = "DJ";
	public final static String ACCOUNT_TYPE_CODE_LOAN = "LN"; // references from
																// fuzion
																// account type
																// code
	public final static String ACCOUNT_TYPE_CODE_CASA = "CASA"; // references
																// from fuzion
																// account type
																// code
	public final static String ACCOUNT_TYPE_CODE_CREDITCARD = "CC"; // references
																	// from
																	// fuzion
																	// account
																	// type code
	public final static String ACCOUNT_TYPE_CODE_SMF = "SMF"; // references from
																// fuzion
																// account type
																// code
	public final static String ACCOUNT_TYPE_CODE_ATM_DEBIT_CARD = "DC";
	public final static String ACCOUNT_TYPE_CODE_OTHER = "OT";
	
	public final static String ACCOUNT_ADD_NEW_LIST = "ACCOUNT_ADD_NEW_LIST";
	public final static String ACCOUNT_ADD_NEW_LIST_OWE = "ACCOUNT_ADD_NEW_LIST_OWE";

	// WebServices response status
	public final static String STATUS_SUCCESS = "1";
	public final static String STATUS_FAIL = "0";


	public final static String LOCALE_EN = "en";
	public final static String LOCALE_IN = "IN";
	public final static String LOCALE_ZH = "zh"; //FE android hardcode is zh , IOS hardcode is zh-Hans
	public final static String LOCALE_CN = "cn"; // when used for queries from DB

	public final static String FUNC_HLB_LOCATION = "FUNC_HLB_LOCATION";
	public final static String FUNC_HLB_LOCATION_TYPE = "FUNC_HLB_LOCATION_TYPE";
	public final static String FUNC_HLB_LOAN = "FUNC_HLB_LOAN";
	public final static String FUNC_LOGIN = "FUNC_MBK_LOGIN";
	public final static String FUNC_LOGIN_OMNI_BINDING= "FUNC_LOGIN_OMNI_BINDING";
	public final static String FUNC_LOGOUT = "FUNC_MBK_LOGOUT";
	public final static String FUNC_RETRIEVE_CUSTPROFILE = "FUNC_HLB_CUSTPROFILE";
	public final static String FUNC_PERFORM_DEVICETAGGING = "FUNC_HLB_DEVICETAGGING";
	public final static String FUNC_RETRIEVE_DEVICEPROFILES = "FUNC_HLB_DEVICEPROFILES";
	public final static String FUNC_REQUEST_DEVICETAC = "FUNC_HLB_DEVICETAC";
	public final static String FUNC_REQUEST_INFO = "FUNC_HLB_REQUESTINFO";
	public final static String FUNC_REQUEST_SESSION = "FUNC_REQUEST_SESSION";
	public final static String FUNC_REQUEST_FAQ = "FUNC_HLB_FAQ";

	// soft token modules
	public final static String FUNC_SOFT_TOKEN_STEP1 = "FUNC_SOFT_TOKEN_STEP1";
	public final static String FUNC_SOFT_TOKEN_STEP2 = "FUNC_SOFT_TOKEN_STEP2";
	
	//eagle eye portfolio
	public final static String FUNC_EAGLE_EYE_LIST = "FUNC_OCBC_EAGLE_EYE_LIST";
	public final static String FUNC_EAGLE_EYE_DETAILS = "FUNC_OCBC_EAGLE_EYE_DETAILS";
	
	//account onbaording
	public final static String FUNC_AO_DEVICENIK_CHECK = "FUNC_AO_DEVICENIK_CHECK";
	public final static String FUNC_AO_INFO_INPUT = "FUNC_AO_INFO_INPUT";
	public final static String FUNC_AO_SMS_TOKEN = "FUNC_AO_SMS_TOKEN";
	public final static String FUNC_AO_VAL_TOKEN = "FUNC_AO_VAL_TOKEN";
	public final static String FUNC_AO_TNC = "FUNC_AO_TNC";
	public final static String FUNC_AO_RETRIEVE_DOC_LIST = "FUNC_AO_RETRIEVE_DOC_LIST";
	public final static String FUNC_AO_SUBMIT_OCR_IMAGE = "FUNC_AO_SUBMIT_OCR_IMAGE";
	public final static String FUNC_AO_KTPNIK_INPUT = "FUNC_AO_KTPNIK_INPUT";
	public final static String FUNC_AO_CC_SELECTION = "FUNC_AO_CC_SELECTION";
	public final static String FUNC_AO_BANK_LIST = "FUNC_AO_BANK_LIST";
	public final static String FUNC_AO_BANK_LIST2 = "FUNC_AO_BANK_LIST2";
	public final static String FUNC_AO_BANK_BRANCH = "FUNC_AO_BANK_BRANCH";
	public final static String FUNC_AO_CHECK_POSTAL_CODE = "FUNC_AO_CHECK_POSTAL_CODE";
	public final static String FUNC_AO_SEARCH_POSTAL_CODE = "FUNC_AO_SEARCH_POSTAL_CODE";
	public final static String FUNC_AO_RETRIEVE_PRODUCT_LIST = "FUNC_AO_RETRIEVE_PRODUCT_LIST";
	public final static String FUNC_AO_RETRIEVE_NATIONALITY_LIST = "FUNC_AO_RETR_NATIONALITY_LIST";
	public final static String FUNC_AO_SUBMIT_EDITED_DATA_DUKCAPIL = "FUNC_AO_SUB_EDITED_DUKCAPIL";
	public final static String FUNC_AO_RETRIEVE_PARAM = "FUNC_AO_RETRIEVE_PARAM";
	public final static String FUNC_AO_CREATE_ACCOUNT_FN = "FUNC_AO_CREATE_ACCOUNT_FN";
	public final static String FUNC_AO_CREATE_ACCOUNT_DL = "FUNC_AO_CREATE_ACCOUNT_DL";
	public final static String FUNC_AO_CACHE_USER_INPUT = "FUNC_AO_CACHE_USER_INPUT";
	public final static String FUNC_AO_RETRIEVE_CACHE_USER_INPUT = "FUNC_AO_RETR_CACHE_USER_INPUT";
	public final static String FUNC_AO_REMOVE_CACHE_USER_INPUT = "FUNC_AO_REMOVE_CACHE_USR_INPUT";
	public final static String FUNC_AO_RETRIEVE_PRODUCT_IMAGE = "FUNC_AO_RETRIEVE_PRODUCT_IMAGE";
	public final static String FUNC_AO_INSTALLMENT_CALCULATOR = "FUNC_AO_INSTALLMENT_CALCULATOR";
	public final static String FUNC_AO_SUBMIT_OCR_IMG_TO_OMNI = "FUNC_AO_SUBMIT_OCR_IMG_TO_OMNI";
	public final static String FUNC_AO_RETRIEVE_IMAGE_BY_UUID = "FUNC_AO_RETRIEVE_IMAGE_BY_UUID";
	public final static String FUNC_AO_INQUIRY_DUKCAPIL_TIME_OUT = "FUNC_AO_INQ_DUKCAPIL_TIME_OUT";
	public final static String FUNC_AO_REFRESH_SESSION = "FUNC_AO_REFRESH_SESSION";
	public final static String FUNC_AO_POSTAL_WHITELIST = "FUNC_AO_POSTAL_WHITELIST";
	public final static String FUNC_AO_FILTER_SELECTION = "FUNC_AO_FILTER_SELECTION";
	public final static String FUNC_AO_CHECK_CUT_OFF_TIME = "FUNC_AO_CHECK_CUT_OFF_TIME";
	public final static String FUNC_AO_GET_PUBLIC_HOLIDAY = "FUNC_AO_GET_PUBLIC_HOLIDAY";
	public final static String FUNC_AO_VIDEO_CALL = "FUNC_AO_VIDEO_CALL";
	public final static String FUNC_AO_CHECK_VIDEO_CALL = "FUNC_AO_CHECK_VIDEO_CALL";
	public final static String FUNC_AO_RETRIEVE_INTRO_PAGE = "FUNC_AO_RETRIEVE_INTRO_PAGE";

	// security token
	public final static String FUNC_MBK_SECTOKEN = "FUNC_MBK_SECTOKEN";
	// CUSTOMER PROFILE STATUS
	public final static String MIB_CUSTPROFILE_STATUS_ACTIVE = "ACTIVE";
	public final static String MIB_CUSTPROFILE_STATUS_INACTIVE = "INACTIVE";

	// DEVICE PROFILE STATUS
	public final static String MIB_DEVICEPROFILE_STATUS_ACTIVE = "ACTIVE";
	public final static String MIB_DEVICEPROFILE_STATUS_UNTAGGED = "UNBINDED";
	public final static String MIB_DEVICEPROFILE_STATUS_PENDING = "PENDING";
	public final static String MIB_DEVICEPROFILE_STATUS_REJECTED = "REJECTED";
	public final static String MIB_DEVICEPROFILE_STATUS_EXPIRED = "EXPIRED";
	public final static String MIB_DEVICEPROFILE_STATUS_APPROVE = "APPROVE";
	public final static String MIB_DEVICEPROFILE_STATUS_SYS_APPROVE = "SYS APPROVE";

	// DEVICE PROFILE LEVEL
	public final static String MIB_DEVICEPROFILE_LEVEL_PRIMARY= "PRIMARY";
	public final static String MIB_DEVICEPROFILE_LEVEL_SECONDARY = "SECONDARY";
		
	// UNTAGGED DEVICE PROFILE BY
	public final static String MIB_DEVICEPROFILE_UNTAGGED_TYPE_MOBILE = "MOBILE";
	public final static String MIB_DEVICEPROFILE_UNTAGGED_TYPE_SUPPORTCENTER = "SUPPORTCENTER";

	// /UNTAGGED DEVICE PROFILE FLAG
	public final static int MIB_DEVICEPROFILE_UNTAGGED_FLAG_CLEARED = 0;
	public final static int MIB_DEVICEPROFILE_UNTAGGED_FLAG_PENDDING = 1;

	// MIB SYSTEM STATUS
	public final static String MIB_SYSTEM_STATUS_ACTIVE = "ACTIVE";

	public final static String MIB_CURRENCYCODE_IDR = "IDR";

	public final static String STATUS_TRANSACTION_SUCCESS = "Successful";
	public final static String STATUS_TRANSACTION_FAILED = "Failed";
	public final static String STATUS_TRANSACTION_SUBMIT = "Accepted for Processing";
	public final static String STATUS_TRANSACTION_SCHED = "Scheduled";
	public final static String STATUS_TRANSACTION_CANCEL = "Cancelled";
	public final static String STATUS_TRANSACTION_SKIP = "Skipped";
	public final static String STATUS_TRANSACTION_COMPLETE = "Completed";

	// untag device message
	public final static String UNTAG_INACTIVE_DEVICEID_DESCRIPTION = "Unable to de-register device. Please try again later.";

	// enforce 2 decimal with default big decimal
	public final static BigDecimal ZEROAMOUNT = new BigDecimal(0.00).setScale(2,
			RoundingMode.HALF_UP);

	// DEVICE PROFILE STATUS
	public final static String MIB_MAINTENANCE_STATUS_ACTIVE = "ACTIVE";
	public final static String MIB_MAINTENANCE_STATUS_DELETE = "DELETE";
	// currency code check status
	public final static int CURRENCY_CODE_INVALID = 0;
	public final static int CURRENCY_CODE_LOCAL_X_FOREIGN = 1;
	public final static int CURRENCY_CODE_FOREIGN_X_FOREIGN = 2;
	public final static int CURRENCY_CODE_SUCCESS = 3;
	public final static int CURRENCY_CODE_SUCCESS_X = 4;

	// date in IN
	public final static String MONTH_IN = "Bulan";
	public final static String MINUTE_IN = "Menit";
	public final static String HOUR_IN = "Jam";

	public final static String RESET_ERROR_MSG = "RESET_ERR_MSG";

	// message properties status
	public final static String MSG_ADDED = "ADD";
	public final static String MSG_MODIFY = "MODIFY";

	public final static String MSG_DELETED = "DELETED";
	public final static String MSG_ACTIVE = "ACTIVE";
	public final static String MSG_LANG_EN = "en";
	public final static String MSG_LANG_IN = "in_ID";
	public final static String MSG_LANG_CN = "cn";
	
	
	public final static String REST_FTCONFIRM = "REST_FTCONFIRM";
	public final static String FUNC_APP_STAT_INFO = "FUNC_APP_STAT_INFO";

	
	//mcb
	public static final String FUNC_MCB_CHANGE_PASSWORD = "FUNC_MCB_CHANGE_PASSWORD";
	public final static String FUNC_MCB_APP_STAT_INFO = "FUNC_MCB_APP_STAT_INFO";
	public final static String FUNC_MCB_SECTOKEN = "FUNC_MCB_SECTOKEN";
	public final static String FUNC_MCB_LOGIN = "FUNC_MCB_LOGIN";
	public final static String FUNC_MCB_LOGIN_V2 = "FUNC_MCB_LOGIN_V2";
	public final static String FUNC_MCB_REQUEST_SESSION = "FUNC_MCB_REQUEST_SESSION";
	public final static String FUNC_MCB_LOGOUT = "FUNC_MCB_LOGOUT";
	public final static String FUNC_MCB_DVBINDING = "FUNC_MCB_DVBINDING";
	public final static String FUNC_MCB_DVBINDING_V2 = "FUNC_MCB_DVBINDING_V2";
	public final static String FUNC_MCB_DVUNBIND_OPEN = "FUNC_MCB_DVUNBIND_OPEN";
	public final static String FUNC_MCB_DVUNBIND = "FUNC_MCB_DVUNBIND";
	public final static String FUNC_MCB_DEVICETAC = "FUNC_MCB_DEVICETAC";
	public static final String FUNC_MCB_ACC_OVERVIEW = "FUNC_MCB_ACC_OVERVIEW";
	public final static String FUNC_MCB_DVENQUIRY = "FUNC_MCB_DVENQUIRY";
	public static final String FUNC_MCB_RETRIEVE_TASK_LIST = "FUNC_MCB_RETRIEVE_TASK_LIST";
	public static final String FUNC_MCB_DASHBOARD_INFO = "FUNC_MCB_DASHBOARD_INFO";
	public static final String FUNC_MCB_HOLIDAY_LIST = "FUNC_MCB_HOLIDAY_LIST";
	public static final String FUNC_MCB_RETRIEVE_FUTURE_TRANX = "FUNC_MCB_RETRIEVE_FUTURE_TRANX";
	public static final String FUNC_MCB_RETRIEVE_TASK_TRANX = "FUNC_MCB_RETRIEVE_TASK_TRANX";
	public static final String FUNC_MCB_FAQ_LIST = "FUNC_MCB_FAQ_LIST";
	public static final String FUNC_MCB_FAQ_RESPOND = "FUNC_MCB_FAQ_RESPOND";
	public static final String FUNC_MCB_ACC_DETAILS = "FUNC_MCB_ACC_DETAILS";
	public static final String FUNC_MCB_ACC_HIST = "FUNC_MCB_ACC_HIST";
	public static final String FUNC_MCB_ACC_STATEMENT_FILE = "FUNC_MCB_ACC_STATEMENT_FILE";
	public static final String FUNC_MCB_PUSH_NOTIFICATION_INBOX = "FUNC_MCB_PUSH_NOTF_INBOX";
	public static final String FUNC_MCB_BOARD_NOTIFICATION_INBOX = "FUNC_MCB_BOARD_NOTF_INBOX";
	public static final String FUNC_MCB_EXCHANGE_RATE = "FUNC_MCB_EXCHANGE_RATE";
	public static final String FUNC_MCB_TRANX_SUM = "FUNC_MCB_TRANX_SUM";
	public static final String FUNC_MCB_AUTH_TRANX = "FUNC_MCB_AUTH_TRANX";
	public static final String FUNC_MCB_AUTH_TRANX_CONFIRM = "FUNC_MCB_AUTH_TRANX_CONFIRM";
	public static final String FUNC_MCB_PRE_AUTHORIZE_TRANSAC = "FUNC_MCB_PRE_AUTHORIZE_TRANSAC";
	public final static String FUNC_MCB_AUTHORIZATIONTAC = "FUNC_MCB_AUTHORIZATIONTAC";
	public static final String FUNC_MCB_AUTHORIZE_TRANSAC = "FUNC_MCB_AUTHORIZE_TRANSAC";
	public static final String FUNC_MCB_RESET_PASSWORD = "FUNC_MCB_RESET_PASSWORD";
	public static final String FUNC_MCB_RESETPASS_TNC = "FUNC_MCB_RESETPASS_TNC";
	
	public static final String FUNC_MCB_CUSTOMER_TOKEN_INQUIRY = "FUNC_MCB_CUST_TK_INQ";
	public static final String FUNC_MCB_OTP_REQ = "FUNC_MCB_OTP_REQ";
	public static final String FUNC_MCB_OTP_VERIFY = "FUNC_MCB_OTP_VERIFY";
	public static final String FUNC_MCB_SWITCH_DEFAULT_TOKEN = "FUNC_MCB_SWITCH_DEF_TK";
	public static final String FUNC_MCB_SOFT_TOKEN_PREBIND1 = "FUNC_MCB_ST_TK_PREBIND1";
	public static final String FUNC_MCB_SOFT_TOKEN_PREBIND2 = "FUNC_MCB_ST_TK_PREBIND2";
	public static final String FUNC_MCB_SOFT_TOKEN_POSTBIND = "FUNC_MCB_S_TK_POSTBIND";
	public static final String FUNC_MCB_SOFT_TOKEN_ELIGIBILITY = "FUNC_MCB_S_TK_ELIGIBILITY";
	public static final String FUNC_MCB_SOFT_TOKEN_DELETE = "FUNC_MCB_ST_TK_DELETE";
	public static final String FUNC_MCB_SOFT_TOKEN_TNC = "FUNC_MCB_S_TK_TNC";
	public static final String FUNC_MCB_ENRL_DEF_TK = "FUNC_MCB_ENRL_DEF_TK";
	
	public static final String FUNC_MCB_UPDATE_TNC = "FUNC_MCB_UPDATE_TNC";
	
	public static final String FUNC_MCB_TRANX_STATUS = "FUNC_MCB_TRANX_STATUS";
	
	
	public static final String FUNC_MCB_UPDATE_FAV = "FUNC_MCB_UPDATE_FAV";
	public static final String FUNC_MCB_RETRIEVE_FAV = "FUNC_MCB_RETRIEVE_FAV";
	public static final String FUNC_MCB_RECURRING_FT = "FUNC_MCB_RECURRING_FT";
	public static final String FUNC_MCB_RECURRING_PYMT = "FUNC_MCB_RECURRING_PYMT";
	public static final String FUNC_MCB_UPDATE_RECURRING = "FUNC_MCB_UPDATE_RECURRING";
	
	public static final String FUNC_MCB_USRPRF_AUTH_LIST= "FUNC_MCB_USRPRF_AUTH_LIST";
	public static final String FUNC_MCB_USRPRF_AUTH_SUBMIT = "FUNC_MCB_USRPRF_AUTH_SUBMIT";
	public static final String FUNC_MCB_POLICY_AUTH_LIST= "FUNC_MCB_POLICY_AUTH_LIST";
	public static final String FUNC_MCB_POLICY_AUTH_SUBMIT = "FUNC_MCB_POLICY_AUTH_SUBMIT";
	public static final String FUNC_MCB_ACCESS_AUTH_LIST= "FUNC_MCB_ACCESS_AUTH_LIST";
	public static final String FUNC_MCB_ACCESS_AUTH_SUBMIT = "FUNC_MCB_ACCESS_AUTH_SUBMIT";
	
	
	public static final String FUNC_MCB_PEND_AUTH_NOTI_LIST= "FUNC_MCB_PEND_AUTH_NOTI_LIST";
	public static final String FUNC_MCB_PEND_AUTH_NOTI_MAINT = "FUNC_MCB_PEND_AUTH_NOTI_MAINT";
	
	// biometric
	public final static String MIB_PENDING_STATUS = "PENDING";
	public final static String MIB_DELETED_STATUS = "DELETED";
	public final static String MIB_ADDED_STATUS = "ADDED";
	public final static String MIB_PERFORMLOGIN = "PerformLogin";
	public final static String MIB_BIOMETRIC_UNENROLL = "Un-enroll";
	public final static String MIB_BIOMETRIC_ENROLL = "Enrolled";
	public final static String MIB_BIOMETRIC_TYPE_FINGERPRINT = "Fingerprint";
	public final static String MIB_BIOMETRIC_TYPE_TOUCHID = "Touch ID";
	public final static String MIB_BIOMETRIC_TYPE_FACEID = "Face ID";

	// IB/MB Registration Modules
	public final static String FUNC_REG_START_SESSION = "FUNC_REG_START_SESSION";
	public final static String FUNC_REG_RETRIEVE_TNC = "FUNC_REG_RETRIEVE_TNC";
	public final static String FUNC_REG_RETRIEVE_CAPTCHA = "FUNC_REG_RETRIEVE_CAPTCHA";
	public final static String FUNC_REG_VALIDATE_EGBLT = "FUNC_REG_VALIDATE_EGBLT";
	public final static String FUNC_REG_VALIDATE_EGBLT2 = "FUNC_REG_VALIDATE_EGBLT2";
	public final static String FUNC_REG_USERNAME = "FUNC_REG_USERNAME";
	public final static String FUNC_REG_SUBMIT_PIN = "FUNC_REG_SUBMIT_PIN";
	public final static String FUNC_REG_CONFIRMATION = "FUNC_REG_CONFIRMATION";
	public final static String FUNC_REG_RETRIEVE_REGPIN = "FUNC_REG_RETRIEVE_REGPIN";
	public final static String FUNC_REG_CHANGE_PASSWORD = "FUNC_REG_CHANGE_PASSWORD";
	public final static String FUNC_REG_REFRESH_SESSION = "FUNC_REG_REFRESH_SESSION";
	
	// AccountOverview and AccountTransctionHistory WS Function Code
	public static final String FUNC_ACC_OVERVIEW = "FUNC_ACC_OVERVIEW";
	public static final String FUNC_ACC_DASHBOARD = "FUNC_ACC_DASHBOARD";
	public static final String FUNC_ACC_TRANS_HIST = "FUNC_ACC_TRANS_HIST";
	public static final String FUNC_ACC_TRANS_HIST_2 = "FUNC_ACC_TRANS_HIST_2";
	public static final String FUNC_ACC_TRANS_PDF = "FUNC_ACC_TRANS_PDF";
	public static final String FUNC_ESTATEMENT_CC_PDF = "FUNC_ESTATEMENT_CC_PDF";
	public static final String FUNC_ACC_CCY_COMPOSITION = "FUNC_ACC_CCY_COMPOSITION";
	public static final String FUNC_ACC_AUTO_RENEWAL = "FUNC_ACC_AUTO_RENEWAL";
	public static final String FUNC_ACC_LIST_INSURANCE = "FUNC_ACC_LIST_INSURANCE";
	public static final String FUNC_GET_OWN_UNIT_TRUST = "FUNC_GET_OWN_UNIT_TRUST";
	public static final String FUNC_LIST_UNIT_TRUST = "FUNC_LIST_UNIT_TRUST";
	public static final String FUNC_ESTATEMENT_LIST = "FUNC_ESTATEMENT_LIST";
	public static final String FUNC_RET_ACC_POINT = "FUNC_RET_ACC_POINT";
	public static final String FUNC_RET_ACC_POINT2 = "FUNC_RET_ACC_POINT2";
	public static final String FUNC_ESTATEMENT_PDF = "FUNC_ESTATEMENT_PDF";
	
	// TimeDeposit WS Function Code
	public static final String FUNC_TIME_DEPOSIT_LIST = "FUNC_TIME_DEPOSIT_LIST";
	public static final String FUNC_TIME_DEPOSIT_CONF = "FUNC_TIME_DEPOSIT_CONF";
	public static final String FUNC_TIME_DEPOSIT_SMS = "FUNC_TIME_DEPOSIT_SMS";
	public static final String FUNC_TIME_DEPOSIT_ACKGT = "FUNC_TIME_DEPOSIT_ACKGT";
	
	// TimeDeposit WS Function Code (MB2)
	public static final String FUNC_TIME_DEPOSIT_SOD = "FUNC_TIME_DEPOSIT_SOD";
	public static final String FUNC_TD_REFRESH_SESSION = "FUNC_TD_REFRESH_SESSION";
	public static final String FUNC_TD_INTEREST_PAYMENT = "FUNC_TD_INTEREST_PAYMENT";
	public static final String FUNC_TIME_DEPOSIT_OP_CONF = "FUNC_TIME_DEPOSIT_OP_CONF";
	public static final String FUNC_TIME_DEPOSIT_OP_SMS_TOKEN = "FUNC_TIME_DEPOSIT_OP_SMS_TOKEN";
	public static final String FUNC_TIME_DEPOSIT_OP_ACK = "FUNC_TIME_DEPOSIT_OP_ACK";
	public static final String FUNC_TD_OPEN_NEW_ACC_LIST = "FUNC_TD_OPEN_NEW_ACC_LIST";
	
	public static final String FUNC_TIME_DEPOSIT_COT = "FUNC_TIME_DEPOSIT_COT";
	public static final String FUNC_TIME_DEPOSIT_GET_DETAILS = "FUNC_TIME_DEPOSIT_GET_DETAILS";
	public static final String FUNC_TIME_DEPOSIT_GET_TNC = "FUNC_TIME_DEPOSIT_GET_TNC";
	
	// Account Maintenance Time Deposit WS Function Code
	public static final String FUNC_ACC_MAIN_LIST = "FUNC_ACC_MAIN_LIST";
	public static final String FUNC_ACC_MAIN_CONF = "FUNC_ACC_MAIN_CONF";
	public static final String FUNC_ACC_MAIN_SMS = "FUNC_ACC_MAIN_SMS";
	public static final String FUNC_ACC_MAIN_ACKGT = "FUNC_ACC_MAIN_ACKGT";

	// Internet Transaction History WS Function Code
	public static final String FUNC_INT_TRANS_HIS_LIST = "FUNC_INT_TRANS_HIS_LIST";
	public static final String FUNC_INT_TRANS_HIS_SEARCH = "FUNC_INT_TRANS_HIS_SEARCH";
	public static final String FUNC_INT_TRANS_HIS_DETAIL = "FUNC_INT_TRANS_HIS_DETAIL";

	// User Profile Maintenance
	public static final String FUNC_UPM_CHANGE_PASSWORD = "FUNC_UPM_CHANGE_PASSWORD";
	public static final String FUNC_UPM_UPDATE_USER_PROFILE = "FUNC_UPM_UPDATE_USER_PROFILE";
	public static final String FUNC_UPM_LOAD_USER_PROFILE = "FUNC_UPM_LOAD_USER_PROFILE";

	// Smart Notification Setup
	public static final String FUNC_SMART_NOT_SETUP_LIST = "FUNC_SMART_NOT_SETUP_LIST";
	public static final String FUNC_SMART_NOT_SETUP_SAVE = "FUNC_SMART_NOT_SETUP_SAVE";
                               
	// Manage Beneficiary Modules
	public static final String FUNC_BENEFICIARY_LIST = "FUNC_BENEFICIARY_LIST";
	public static final String FUNC_BENEFICIARY_VIEW = "FUNC_BENEFICIARY_VIEW";
	public static final String FUNC_BENEFICIARY_EDIT = "FUNC_BENEFICIARY_EDIT";
	public static final String FUNC_BENEFICIARY_DELETE = "FUNC_BENEFICIARY_DELETE";

	// Payee Management Modules
	public static final String FUNC_PAYEE_LIST = "FUNC_PAYEE_LIST";
	public static final String FUNC_PAYEE_VIEW = "FUNC_PAYEE_VIEW";
	public static final String FUNC_PAYEE_DELETE = "FUNC_PAYEE_DELETE";
	
	// Manage Beneficiary Modules Revamp
	public static final String FUNC_MANAGE_BENE_LIST = "FUNC_MANAGE_BENE_LIST";
	public static final String FUNC_MANAGE_BENE_VIEW = "FUNC_MANAGE_BENE_VIEW";
	public static final String FUNC_MANAGE_BENE_EDIT = "FUNC_MANAGE_BENE_EDIT";
	public static final String FUNC_MANAGE_BENE_DEL = "FUNC_MANAGE_BENE_DEL";
	public static final String FUNC_MANAGE_BENE_CHECK_BENE = "FUNC_MANAGE_BENE_CHECK_BENE";
	public static final String FUNC_MANAGE_BENE_SAVE_BENE = "FUNC_MANAGE_BENE_SAVE_BENE";
	public static final String FUNC_MANAGE_BENE_CHECK_PAYEE = "FUNC_MANAGE_BENE_CHECK_PAYEE";
	public static final String FUNC_MANAGE_BENE_CHECK_CUSTID = "FUNC_MANAGE_BENE_CHECK_CUSTID";
	public static final String FUNC_MANAGE_BENE_SAVE_PAYEE = "FUNC_MANAGE_BENE_SAVE_PAYEE";
	public static final String FUNC_MANAGE_BENE_VIEW_PAYEE = "FUNC_MANAGE_BENE_VIEW_PAYEE";
	public static final String FUNC_MANAGE_BENE_UPDATE_PAYEE = "FUNC_MANAGE_BENE_UPDATE_PAYEE";
	public static final String FUNC_MANAGE_BENE_DELETE_PAYEE = "FUNC_MANAGE_BENE_DELETE_PAYEE";
	public static final String FUNC_MANAGE_BENE_SMS_TOKEN = "FUNC_MANAGE_BENE_SMS_TOKEN";
	public static final String FUNC_MANAGE_BENE_JUDGE_BANK = "FUNC_MANAGE_BENE_JUDGE_BANK";
	
	// Account Type
	public static final String ACCOUNTTYPE_SAVINGACCOUNT = "S";
	public static final String ACCOUNTTYPE_CURRENTACCOUNT = "D";
	public static final String ACCOUNTTYPE_MULTICURRENCYACCOUNT = "M";
	public static final String ACCOUNTTYPE_SAVINGPLAN = "SP";
	public static final String ACCOUNTTYPE_TIMEDEPOSIT = "T";
	public static final String ACCOUNTTYPE_TIMEDEPOSIT_SYARIAH = "TS";//This is for internal usage only, not one of the account type (TD Syariah still using ACCOUNTTYPE_TIMEDEPOSIT as it's account type).
	public static final String ACCOUNTTYPE_REWARDPOINT = "Y";
	public static final String ACCOUNTTYPE_UNITTRUST = "U";
	public static final String ACCOUNTTYPE_BANCASSURANCE = "B";
	public static final String ACCOUNTTYPE_LOAN = "L";
	public static final String ACCOUNTTYPE_CREDITCARD = "C";
	public static final String ACCOUNTTYPE_OBLIGASI = "O";
	
	// AccountTransactionHistory PDF
	public static final String ATH_RT = "RT"; // recent trx
	public static final String ATH_DT = "DT"; // date range
	public static final String ATH_RD = "RD"; // recent date

	// Product Name
	public static final String ACCOUNTTYPE_UNITTRUST_ORI = "ORI";

	// Smart Notification Setup
	public static final String CATEGORY_CODE_TT = "TTN";	// P3OCBCUAT-113
	public static final String CATEGORY_CODE_FT = "FTN";
	public static final String CATEGORY_CODE_PP = "PPN";
	public static final String CATEGORY_CODE_GN = "GNN";

	// WS
	public static final String OMNI_SUCCESS = "00000";
	public static final String ERRORCODE_SUCCESS = "000";
	public static final String MINUS_SEPARATOR = "-";
	public static final String COMMA_SEPARATOR = ",";
	public static final String VERTICAL_BAR_SEPARATOR = "|";
	public static final String CHANNEL = "MD";
	public static final String OPERATIONCODE = "000";
	public static final String CURRENCY_IDR = "IDR";
	public static final String NO_AVAILABLE = "N/A";
	public static final String NO_AVAILABLE_DATE = "1900";
	public static final String COUNT_PER_PAGE = "10";
	public static final String DAY_START = " 00:00:00";
	public static final String DAY_END = " 23:59:59";
	public static final String ACCOUNT_DEBIT = "D";
	public static final String POINT = "POINT";
	public static final String GENERAL_CODE_COUNTLASTTRANSCTION = "countLastTransaction";
	public static final String GENERAL_CODE_TRNSACTIONDATERANGE = "transactionDateRange";
	public static final String GENERAL_CODE_SERVER_VERSION = "SERVER_VERSION";
	public static final String GENERAL_CODE_ONFX_SETTINGS = "ONFX_SETTINGS";
	public static final String GENERAL_CODE_ONFX_SETTINGS_V2FLAG= "V2FLAG";
	public static final String GENERAL_CODE_HOUSEKEEPING_SETTING = "LOG_HK_SETTING";
	public static final String GENERAL_CODE_HOUSEKEEPING_SETTING_PURGE_TIME_DAYS = "PURGE_TIME_DAY";
	public static final String COTCODE_ACCOUNTOPENING = "ACCOP";
	public static final String WITHIN_COT = "false";
	public static final String WITHOUT_COT = "true";
	public static final String TDRENEWALCOUNTERCODE_N = "N";
	public static final String TDRENEWALCOUNTERCODE_A = "A";
	public static final String TDRENEWALCOUNTERCODE_Y = "Y";
	public static final String MATURITY_DESC_N = "Non - Auto Rollover";
	public static final String MATURITY_DESC_A = "Auto Rollover and Interest Compounded";
	public static final String MATURITY_DESC_Y = "Auto Rollover";

	// OCBC Fund Transfer Function Code
	public final static String FUNC_OCBC_FT_LIST = "FUNC_OCBC_FT_LIST";
	public final static String FUNC_OCBC_FT_LIST2 = "FUNC_OCBC_FT_LIST2";
	public final static String FUNC_OCBC_FT_OWN_LIST = "FUNC_OCBC_FT_OWN_LIST";
	public final static String FUNC_OCBC_FT_OWN_VAL = "FUNC_OCBC_FT_OWN_VAL";
	public final static String FUNC_OCBC_FT_OWN_CONF = "FUNC_OCBC_FT_OWN_CONF";
	public final static String FUNC_OCBC_FT_OWN_ACK = "FUNC_OCBC_FT_OWN_ACK";
	public final static String FUNC_OCBC_FT_INTRA_VAL = "FUNC_OCBC_FT_INTRA_VAL";
	public final static String FUNC_OCBC_FT_INTRA_CONF = "FUNC_OCBC_FT_INTRA_CONF";
	public final static String FUNC_OCBC_FT_INTRA_ACK = "FUNC_OCBC_FT_INTRA_ACK";
	public final static String FUNC_OCBC_FT_INTER_VAL = "FUNC_OCBC_FT_INTER_VAL";
	public final static String FUNC_OCBC_FT_INTER_CONF = "FUNC_OCBC_FT_INTER_CONF";
	public final static String FUNC_OCBC_FT_INTER_ACK = "FUNC_OCBC_FT_INTER_ACK";
	public final static String FUNC_OCBC_FT_LIMIT_EXCHANGE = "FUNC_OCBC_FT_LIMIT_EXCHANGE";
	public final static String FUNC_OCBC_FT_FAV_BENE = "FUNC_OCBC_FT_FAV_BENE";
	
	//Fund Transfer MB2
	public final static String FUNC_FT_SOURCE_FUND = "FUNC_FT_SOURCE_FUND";
	public final static String FUNC_FT_SAVED_RECIPIENTS = "FUNC_FT_SAVED_RECIPIENTS";
	public final static String FUNC_FT_BANK_LIST = "FUNC_FT_BANK_LIST";
	public final static String FUNC_OCBC_FT_VAL = "FUNC_OCBC_FT_VAL";
	public final static String FUNC_OCBC_FT_CONFIRMATION = "FUNC_OCBC_FT_CONFIRMATION";
	public final static String FUNC_OCBC_FT_ACKG = "FUNC_OCBC_FT_ACKG";
	public final static String FUNC_FT_TRX_LIMIT = "FUNC_FT_TRX_LIMIT";
	public final static String FUNC_FT_TRANS_SERVICE_LIST = "FUNC_FT_TRANS_SERVICE_LIST";
	public final static String FUNC_FT_CART_TOTAL_CALCULATOR = "FUNC_FT_CART_TOTAL_CALCULATOR";
	public final static String FUNC_FT_FAV_BENE = "FUNC_FT_FAV_BENE";
	public final static String FUNC_FT_TERM_AND_CONDITION = "FUNC_FT_TERM_AND_CONDITION";
	
	// Manage Scheduled Transaction - Future Schedule and Recurring Schedule Transaction
	public final static String FUNC_FT_SCHEDULE_TRANSFER_LIST = "FUNC_FT_SCHEDULE_TRANSFER_LIST";
	public final static String FUNC_FT_FUTURE_SCHD_TRX_DETAIL = "FUNC_FT_FUTURE_SCHD_TRX_DETAIL";
	public final static String FUNC_FT_CANCEL_SCHEDULE_TRANS = "FUNC_FT_CANCEL_SCHEDULE_TRANS";
	public final static String FUNC_FT_RECURR_SCHD_TRX_DETAIL = "FUNC_FT_RECURR_SCHD_TRX_DETAIL";
	public final static String FUNC_FT_CANCEL_REC_SCHED_TRANS = "FUNC_FT_CANCEL_REC_SCHED_TRANS";

	// OCBC Payment & Purchase Web Service Function Code
	public final static String FUNC_GET_DB_ACC_REG_PAYEE_CC = "FUNC_GET_DB_ACC_REG_PAYEE_CC";
	public final static String FUNC_GET_DB_ACC_REG_PAYEE = "FUNC_GET_DB_ACC_REG_PAYEE";
	public final static String FUNC_GET_CC = "FUNC_GET_CC";
	public final static String FUNC_GET_REG_PAYEE = "FUNC_GET_REG_PAYEE";
	public final static String FUNC_RETRIEVE_PAYEE_TYPE_ORG = "FUNC_RETRIEVE_PAYEE_TYPE_ORG";
	public final static String FUNC_RETRIEVE_BILL_INFO_NON_REG = "FUNC_GET_BILL_INFO_NON_REG";
	public final static String FUNC_RETRIEVE_BILL_INFO_REG = "FUNC_GET_BILL_INFO_REG";
	public final static String FUNC_RETRIEVE_BILL_INFO_CC = "FUNC_GET_BILL_INFO_CC";
	public final static String FUNC_VALIDATE_PAYMENT_INFO = "FUNC_VALIDATE_PAYMENT_INFO";
	public final static String FUNC_SUBMIT_PP_TRXN_NON_REG = "FUNC_SUBMIT_PP_TRXN_NON_REG";
	public final static String FUNC_SUBMIT_PP_TRXN_REG = "FUNC_SUBMIT_PP_TRXN_REG";
	public final static String FUNC_SUBMIT_PP_TRXN_CC = "FUNC_SUBMIT_PP_TRXN_CC";
	public final static String FUNC_REQUEST_TOKEN_PP = "FUNC_REQUEST_TOKEN_PP";
	public final static String FUNC_REQ_PP_TRXN_ACK_NON_REG = "FUNC_REQ_PP_TRXN_ACK_NON_REG";
	public final static String FUNC_REQ_PP_TRXN_ACK_REG = "FUNC_REQ_PP_TRXN_ACK_REG";
	public final static String FUNC_REQ_PP_TRXN_ACK_CC = "FUNC_REQ_PP_TRXN_ACK_CC";
	public final static String FUNC_UPDATE_FAV_PAYEE = "FUNC_UPDATE_FAV_PAYEE";

	public final static String FUNC_OCBC_FT_REQUEST_SMS_TOKEN = "FUNC_OCBC_FT_REQUEST_SMS_TOKEN";
	public final static String FUNC_OCBC_UPLOAD_CUST_PROF = "FUNC_OCBC_UPLOAD_CUST_PROF";
	
	public final static String FUNC_PAYMENT_SOURCE_FUND = "FUNC_PAYMENT_SOURCE_FUND";
	public final static String FUNC_PAYMENT_REGISTERED_BILLER = "FUNC_PAYMENT_REGISTERED_BILLER";
	public final static String FUNC_PAYMENT_UNREGISTERED_BILLER = "FUNC_PAYMENT_UNREGISTER_BILLER";
	public final static String FUNC_PAYMENT_INQUIRY_TRXNLIMIT = "FUNC_PAYMENT_INQUIRY_TRXNLIMIT";
	public final static String FUNC_PAYMENT_INQUIRY_BILL_INFO = "FUNC_PAYMENT_INQUIRY_BILL_INFO";
	public final static String FUNC_PAYMENT_CONFIRMATION = "FUNC_PAYMENT_CONFIRMATION";
	public final static String FUNC_PAYMENT_SMS_TOKEN = "FUNC_PAYMENT_SMS_TOKEN";
	public final static String FUNC_PAYMENT_ACKNOWLEDGEMENT = "FUNC_PAYMENT_ACKNOWLEDGEMENT";
	public final static String FUNC_PAYMENT_UPDATE_FAV_PAYEE = "FUNC_PAYMENT_UPDATE_FAV_PAYEE";

	// OCBC Unit Trust Function Code
	public final static String FUNC_OCBC_UT_SUBS_LIST = "FUNC_OCBC_UT_SUBS_LIST";
	public final static String FUNC_OCBC_UT_SUBS_NEW_LIST = "FUNC_OCBC_UT_SUBS_NEW_LIST";
	public final static String FUNC_OCBC_UT_SUBS_PROD_LIST = "FUNC_OCBC_UT_SUBS_PROD_LIST";
	public final static String FUNC_OCBC_UT_SUBS_NEW_VAL = "FUNC_OCBC_UT_SUBS_NEW_VAL";
	public final static String FUNC_OCBC_UT_SUBS_CONF = "FUNC_OCBC_UT_SUBS_CONF";
	public final static String FUNC_OCBC_UT_SUBS_ACK = "FUNC_OCBC_UT_SUBS_ACK";
	public final static String FUNC_OCBC_UT_SWITCH_LIST = "FUNC_OCBC_UT_SWITCH_LIST";
	public final static String FUNC_OCBC_UT_SWITCH_CONF = "FUNC_OCBC_UT_SWITCH_CONF";
	public final static String FUNC_OCBC_UT_SWITCH_ACK = "FUNC_OCBC_UT_SWITCH_ACK";
	public final static String FUNC_OCBC_UT_REDEMP_LIST = "FUNC_OCBC_UT_REDEMP_LIST";
	public final static String FUNC_OCBC_UT_REDEMP_CONF = "FUNC_OCBC_UT_REDEMP_CONF";
	public final static String FUNC_OCBC_UT_REDEMP_ACK = "FUNC_OCBC_UT_REDEMP_ACK";
	public final static String FUNC_OCBC_UT_PROD_INFO = "FUNC_OCBC_UT_PROD_INFO";
	public final static String FUNC_OCBC_UT_TERM_TRANS = "FUNC_OCBC_UT_TERM_TRANS";
	public final static String FUNC_OCBC_UT_REQUEST_SMS_TOKEN = "FUNC_OCBC_UT_REQUEST_SMS_TOKEN";
	public final static String FUNC_OCBC_UT_TNC = "FUNC_OCBC_UT_TNC";
	public final static String FUNC_OCBC_UT_SWITCH_PROD_LIST = "FUNC_OCBC_UT_SWITCH_PROD_LIST";
	
	//MB2 Unit trust
	public final static String FUNC_UT_SUBSCIRBE_PROD_LIST = "FUNC_UT_SUBSCIRBE_PROD_LIST";
	public final static String FUNC_UT_NEW_PRODUCT_LIST = "FUNC_UT_NEW_PRODUCT_LIST";
	public final static String FUNC_UT_SUBSCIRBE_CONF = "FUNC_UT_SUBSCIRBE_CONF";
	public final static String FUNC_UT_SUBSCIRBE_ACK = "FUNC_UT_SUBSCIRBE_ACK";
	public final static String FUNC_UT_SMS_TOKEN = "FUNC_UT_SMS_TOKEN";
	public final static String FUNC_UT_REDEEM_PROD_LIST = "FUNC_UT_REDEEM_PROD_LIST";
	public final static String FUNC_UT_REDEEM_CONF = "FUNC_UT_REDEEM_CONF";
	public final static String FUNC_UT_REDEEM_ACK = "FUNC_UT_REDEEM_ACK";
	public final static String FUNC_UT_SWITCH_FROM_LIST = "FUNC_UT_SWITCH_FROM_LIST";
	public final static String FUNC_UT_SWITCH_TO_LIST = "FUNC_UT_SWITCH_TO_LIST";
	public final static String FUNC_UT_SWITCH_CONF = "FUNC_UT_SWITCH_CONF";
	public final static String FUNC_UT_SWITCH_ACK = "FUNC_UT_SWITCH_ACK";
	public final static String FUNC_UT_NEW_PRODUCT_DETAIL = "FUNC_UT_NEW_PRODUCT_DETAIL";
	public final static String FUNC_UT_OWN_INVEST_SUMMARY = "FUNC_UT_OWN_INVEST_SUMMARY";
	public final static String FUNC_UT_OTHER_PRODUCT_LIST = "FUNC_UT_OTHER_PRODUCT_LIST";
	public final static String FUNC_UT_OWN_PRODUCT_LIST = "FUNC_UT_OWN_PRODUCT_LIST";
	public final static String FUNC_UT_SOURCE_OF_FUND = "FUNC_UT_SOURCE_OF_FUND";
	public final static String FUNC_UT_GET_EXG_RATE = "FUNC_UT_GET_EXG_RATE";
	public final static String FUNC_UT_QUICK_STEP1 = "FUNC_UT_QUICK_STEP1";
	public final static String FUNC_UT_INSURANCE_QUESTION = "FUNC_UT_INSURANCE_QUESTION";
	public final static String FUNC_UT_INSURANCE_CHECKING = "FUNC_UT_INSURANCE_CHECKING";
	
	// Manage Recurring Modules
	public final static String FUNC_MNG_RECUR_LIST = "FUNC_MNG_RECUR_LIST";
	public final static String FUNC_GET_RECUR_DETAILS = "FUNC_GET_RECUR_DETAILS";
	public final static String FUNC_DELETE_RECUR_TRXN = "FUNC_DELETE_RECUR_TRXN";

	// Manage Future Transfer Modules
	public final static String FUNC_FUTURE_TRANSFER_LIST = "FUNC_FUTURE_TRANSFER_LIST";
	public final static String FUNC_FUTURE_TRANSFER_DETAILS = "FUNC_FUTURE_TRANSFER_DETAILS";
	public final static String FUNC_FUTURE_TRANSFER_DELETE = "FUNC_FUTURE_TRANSFER_DELETE";

	// Manage Recurring Modules
	public final static String FUNC_MNG_AUTODEBIT_LIST = "FUNC_MNG_AUTODEBIT_LIST";
	public final static String FUNC_GET_AUTODEBIT_DETAILS = "FUNC_GET_AUTODEBIT_DETAILS";
	public final static String FUNC_DELETE_AUTODEBIT_TRXN = "FUNC_DELETE_AUTODEBIT_TRXN";

	// Manage Future Payment Modules
	public final static String FUNC_FUTURE_PAYMENT_LIST = "FUNC_FUTURE_PAYMENT_LIST";
	public final static String FUNC_FUTURE_PAYMENT_DETAILS = "FUNC_FUTURE_PAYMENT_DETAILS";
	public final static String FUNC_FUTURE_PAYMENT_DELETE = "FUNC_FUTURE_PAYMENT_DELETE";
	
	// Manage Scheduled Transactions - Payment (MB2)
	public final static String FUNC_PP_SCHEDULED_TRX_LIST = "FUNC_PP_SCHEDULED_TRX_LIST";
	public final static String FUNC_PP_SCHEDULED_TRX_DETAIL = "FUNC_PP_SCHEDULED_TRX_DETAIL";
	public final static String FUNC_PP_CANCEL_FUTURE_TRX = "FUNC_PP_CANCEL_FUTURE_TRX";
	public final static String FUNC_PP_DELETE_RECUR_TRX = "FUNC_PP_DELETE_RECUR_TRX";
	
	// Mapping for Transaction Purpose Type
	public final static String TRANS_PURPOSE_TYPE_BUSINESS = "BUSINESS";
	public final static String TRANS_PURPOSE_TYPE_INDIVIDUAL = "INDIVIDUAL";

	public final static String DEVICE_BINDING_FLAG = "device.binding.flag";
	public final static String ASYNCREST_CONCURRENCY_LIMIT = "asyncrest.concurrency.limit";
	public final static String IS_SCHEDULER_MODE = "is.scheduler.mode";

	// FUND TRANSFER MAPPING
	public final static String FUNC_OCBC_GN_RECURRINGTYPE = "REC";
	public final static String FUNC_OCBC_GN_TRANSSERVICE = "TRANSSERVICE";

	// Transfer Service Type
	public final static String TRANS_SERVICE_TYPE_OAT = "OAT";
	public final static String TRANS_SERVICE_TYPE_OATC = "OATC";
	public final static String TRANS_SERVICE_TYPE_OATS = "OATS";
	public final static String TRANS_SERVICE_TYPE_OLTS = "OLTS";
	public final static String TRANS_SERVICE_TYPE_RTGSS = "RTGSS";
	public final static String TRANS_SERVICE_TYPE_LLGS = "LLGS";
	public final static String TRANS_SERVICE_TYPE_IFTS = "IFTS";
	public final static String TRANS_SERVICE_TYPE_IFTC = "IFTC";
	public final static String TRANS_SERVICE_TYPE_IFT = "IFT";
	public final static String TRANS_SERVICE_TYPE_LLG = "LLG";
	public final static String TRANS_SERVICE_TYPE_RTGS = "RTGS";
	public final static String TRANS_SERVICE_TYPE_OLT = "OLT";
	public final static String TRANS_SERVICE_TYPE_IB = "IB";
	public final static String TRANS_SERVICE_TYPE_OL = "OL";
	public final static String TRANS_SERVICE_TYPE_TT = "TT";
	public final static String TRANS_SERVICE_TYPE_FAST = "FAST";
	
	//Payment type
	public final static String PAYMENT_BILLER_TYPE_BPM = "BPM";
	public final static String PAYMENT_BILLER_TYPE_ETAX = "ETAX";
	
	//Payment Amount option type
	public final static String PAYMENT_BILLER_AMOUNT_OPTION_LAST = "LAST";
	public final static String PAYMENT_BILLER_AMOUNT_OPTION_TOTAL = "TOTAL";
	public final static String PAYMENT_BILLER_AMOUNT_OPTION_MANUAL = "MANUAL";
	
	// OCBC Bank Code
	public final static String BANK_CODE_OCBC = "999";

	// Regex
	public final static String BENE_PICTURE_REGEX = "\\.0";

	// Customer Dto Token Type
	public final static String CUST_DTO_TOKEN_SMS = "SMS";
	public final static String MAXDEVICEBINDKEY = "maxDeviceBinding";

	// Unit Trust
	public final static String ACTIONCODE_SUBSCRIPTION = "A";
	public final static String ACTIONCODE_SWITCH = "S";
	public final static String ACTIONCODE_REDEMPTION = "R";
	public final static String MIB_EMPTY_UNIT_TRUST_LIST = "MCB.0000145";
	public final static String MIB_EMPTY_UNIT_TRUST_ACCOUNT = "MCB.0000147";
	public final static String MIB_EMPTY_UNIT_TRUST = "MCB.0000148";
	public final static String UNITTRUST_INSURANCE_ELIGIBLE = "00";
	public final static String UNITTRUST_INSURANCE_ANSWER_FAILED = "01";
	public final static String UNITTRUST_INSURANCE_LIMIT_FAILED = "02";
	public final static String UNITTRUST_INSURANCE_AGE_FAILED= "01";

	public final static String UNITTRUST_HIGHLY_AGGRESIVE = "Highly Aggressive";
	public final static String UNITTRUST_AGGRESIVE = "Aggressive";
	public final static String UNITTRUST_BALANCE = "Balance";
	public final static String UNITTRUST_GROWTH = "Growth";
	public final static String UNITTRUST_CONSERVATIVE = "Conservative";
	public final static String UNITTRUST_HIGHLY_AGGRESIVE_ID = "Sangat Tinggi";
	public final static String UNITTRUST_AGGRESIVE_ID = "Tinggi";
	public final static String UNITTRUST_BALANCE_ID = "Rendah - Menengah";
	public final static String UNITTRUST_GROWTH_ID = "Menengah - Tinggi";
	public final static String UNITTRUST_CONSERVATIVE_ID = "Rendah";
	public final static String UNITTRUST_TNC = "UT_TNC";
	public final static String UT_PHONE_NUMBER = "UT_PHONE_NUMBER";
	public final static String MODULE_CORE_UNIT_TRUST = "UNIT_TRUST";
	
	//Eagle Eye Portfolio
	public final static String EAGLEEYE_BANCASSURANCE = "BANCASSURANCE";
	public final static String EAGLEEYE_REKSADANA = "REKSADANA";
	public final static String EAGLEEYE_OBLIGASI = "OBLIGASI";
	public final static String EAGLEEYE_DEPOSITO = "DEPOSITO";
	public final static String EAGLEEYE_GIRO = "GIRO";
	public final static String EAGLEEYE_TABUNGAN = "TABUNGAN";
	public final static String EAGLEEYE_TREASURY = "TREASURY PRODUCT";

	public final static String FUNC_DVENQUIRY = "FUNC_DVENQUIRY";
	public final static String FUNC_DVENQUIRY_OPEN = "FUNC_DVENQUIRY_OPEN";
	public final static String FUNC_DVBINDING = "FUNC_DVBINDING";
	public final static String FUNC_DVUNBIND = "FUNC_DVUNBIND";
	public final static String FUNC_DVUNBINDCONF = "FUNC_DVUNBINDCONF";
	public final static String FUNC_EXT_SYSENQUIRY = "FUNC_EXT_SYSENQUIRY";
	public final static String FUNC_EXT_SYSUPDATECONFM = "FUNC_EXT_SYSUPDATECONFM";
	public final static String FUNC_EXT_SYSUPDATEACK = "FUNC_EXT_SYSUPDATEACK";
	public final static String FUNC_EXT_CUSTEVENTENQ = "FUNC_EXT_CUSTEVENTENQ";
	public final static String FUNC_EXT_MSGMAPENQUIRY = "FUNC_EXT_MSGMAPENQUIRY";
	public final static String FUNC_EXT_MSGMAPCONFIRM = "FUNC_EXT_MSGMAPCONFIRM";
	public final static String FUNC_EXT_MSGMAPACK = "FUNC_EXT_MSGMAPACK";

	public final static String MIB_DEVAPI_UNATHORIZED = "MCB.EXT.0000001";
	public final static String MIB_DEVAPI_BINDREJECTED = "MCB.EXT.0000002";
	public final static String MIB_DEVAPI_INVALID_DEV_TYPE = "MCB.EXT.0000003";
	public final static String MIB_INVALID_INPUT_PARAM = "MCB.EXT.0000004";
	public final static String MIB_MSGCODE_EXISTED = "MCB.EXT.0000005";
	public final static String MIB_MSGCODE_INVALID_RECORDID = "MCB.EXT.0000006";
	public final static String MIB_MSGCODE_INVALID_ACTION = "MCB.EXT.0000007";

	public final static String MSG_MAPPING_ACTION_ADD = "ADD";
	public final static String MSG_MAPPING_ACTION_UPDATE = "UPDATE";
	public final static String MSG_MAPPING_ACTION_DELETE = "DELETE";
	
	//Account On Boarding
	public final static String ACCONBOARDING_TNC = "_ACCONBOARDING_TNC";
	public final static String ACCONBOARDING_ACTIONCODE_FN = "FN";
	public final static String ACCONBOARDING_ACTIONCODE_DL = "DL";
	public final static String ACCONBOARDING_DOC_KTP = "KTP";
	public final static String ACCONBOARDING_DOC_SKTP = "SKTP";
	public final static String ACCONBOARDING_DOC_NPWP = "NPWP";
	public final static String ACCONBOARDING_DOC_SIGN = "SIGN";
	public final static String ACCONBOARDING_DOC_PSLIP = "PSLIP";
	public final static String ACCONBOARDING_DOC_SPT = "SPT";
	public final static String ACCONBOARDING_DOC_BSTMT = "BSTMT";
	
	public final static String ACCONBOARDING_KPR = "KPR";
	public final static String ACCONBOARDING_KTA = "KTA";
	public final static String ACCONBOARDING_KPM = "KPM";
	
	public final static String ACCONBOARDING_COTTYPE = "AOB";

	// Payment Purchase Map
	public final static String MAP_PARSE_FROM_ACCOUNT = "From Account";
	public final static String MAP_PARSE_BILLER_NAME = "Biller Name";
	public final static String MAP_PARSE_AMOUNT_DEC = "Amount|dec";

	// Internet Transaction History
	public final static String PARAM_CODE_1001 = "1001";
	public final static String PARAM_CODE_1002 = "1002";
	public final static String PARAM_CODE_1003 = "1003";
	public final static String PARAM_CODE_1004 = "1004";
	public final static String PARAM_CODE_1005 = "1005";
	public final static String PARAM_CODE_2001 = "2001";
	public final static String PARAM_CODE_2002 = "2002";
	public final static String PARAM_CODE_2003 = "2003";
	public final static String PARAM_CODE_2004 = "2004";
	public final static String PARAM_CODE_2005 = "2005";
	public final static String PARAM_CODE_3001 = "3001";
	public final static String PARAM_CODE_3002 = "3002";
	public final static String PARAM_CODE_3003 = "3003";
	public final static String PARAM_CODE_4001 = "4001";
	public final static String PARAM_CODE_4002 = "4002";
	public final static String PARAM_CODE_4003 = "4003";
	public final static String PARAM_CODE_4004 = "4004";	// P3OCBCUAT-150
	public final static String PARAM_CODE_4005 = "4005";	// P3OCBCUAT-150
	public final static String PARAM_CODE_5001 = "5001";
	public final static String PARAM_CODE_5002 = "5002";
	public final static String PARAM_CODE_5003 = "5003";	// P3OCBCUAT-150
	public final static String PARAM_CODE_5004 = "5004";	// P3OCBCUAT-359
	public final static String PARAM_CODE_5005 = "5005";	// P3OCBCUAT-359
	public final static String PARAM_CODE_7001 = "7001";	// P3OCBCUAT-150
	public final static String PARAM_CODE_7002 = "7002";	// P3OCBCUAT-150
	public final static String PARAM_CODE_7003 = "7003";	// P3OCBCUAT-150
	public final static String PARAM_CODE_8001 = "8001";	// P3OCBCUAT-150
	public final static String PARAM_CODE_8002 = "8002";	// P3OCBCUAT-150
	public final static String PARAM_CODE_9001 = "9001";	// P3OCBCUAT-506
	public final static String PARAM_CODE_9002 = "9002";	// P3OCBCUAT-506
	
	public final static String ITH_MONTHS = " months";
	public final static String ITH_PERCENTAGE = "%";

	public final static String ITH_ACTION_A = "A";
	public final static String ITH_ACTION_D = "D";
	public final static String ITH_ACTION_P = "P";
	public final static String ITH_ACTION_S = "S";
	public final static String ITH_ACTION_R = "R";
	public final static String ITH_ACTION_H = "H";
	public final static String ITH_ACTION_E = "E";
	public final static String ITH_ACTION_ADD = "Add";
	public final static String ITH_ACTION_DELETE = "Delete";
	public final static String ITH_ACTION_PENDING = "Pending";
	public final static String ITH_ACTION_COMPLETE = "Complete";
	public final static String ITH_ACTION_REJECT = "Reject";
	public final static String ITH_ACTION_EDIT = "Edit";

	public final static String ITH_STATUS_S = "S";
	public final static String ITH_STATUS_F = "F";
	public final static String ITH_STATUS_P = "P";
	public final static String ITH_STATUS_R = "R";
	public final static String ITH_STATUS_A = "A";
	public final static String ITH_STATUS_SUCCESS = "Success";
	public final static String ITH_STATUS_FAIL = "Fail";
	public final static String ITH_STATUS_PENDING = "Pending";
	public final static String ITH_STATUS_REJECT = "Reject";
	public final static String ITH_STATUS_IN_PROGRESS = "In Progress";
	
	public final static String TT_IFT = "IFT";
	public final static String TT_OAT = "OAT";
	public final static String TT_OL = "OL";
	public final static String TT_LLG = "LLG";
	public final static String TT_OLG = "OLG";
	public final static String TT_OLT = "OLT";
	public final static String TT_IB = "IB";
	public final static String TT_RTGS = "RTGS";
	public final static String TT_OCBCNISP = "OCBCNISP";
	public final static String TT_ONLINE = "Online";

	public final static String ITH_RECINTERVAL_D = "D";
	public final static String ITH_RECINTERVAL_M = "M";
	public final static String ITH_RECINTERVAL_W = "W";
	public final static String ITH_RECINTERVAL_Y = "Y";
	public final static String ITH_RECINTERVAL_DAILY = "Daily";
	public final static String ITH_RECINTERVAL_MONTHLY = "Monthly";
	public final static String ITH_RECINTERVAL_WEEKLY = "Weekly";
	public final static String ITH_RECINTERVAL_YEARLY = "Yearly";
	
	public final static String REGISTRATION_TNC = "RM_TNC";
	
	public final static String IMAGE_DOC_TYPE_KTP = "KTP";
	
	public final static String TNCT1EN = "TNCT1EN";
	public final static String TNCT1ID = "TNCT1ID";
	public final static String TNCT2EN = "TNCT2EN";
	public final static String TNCT2ID = "TNCT2ID";
	public final static String TNCT3EN = "TNCT3EN";
	public final static String TNCT3ID = "TNCT3ID";
	
	public final static String PARAM_TYPE_CARD_DELIVERY_LOCATION = "CDL";
	public final static String PARAM_TYPE_CREDIT_CARD_STATEMENT = "CCS";
	public final static String PARAM_TYPE_EDUCATION = "EDU";
	public final static String PARAM_TYPE_HARDCOPY_DELIVERY_LOCATION = "HDL";
	public final static String PARAM_TYPE_HOME_CURRENT_STATUS = "HCS";
	public final static String PARAM_TYPE_NO_CREDIT_CARD_OWNED = "NCCO";
	public final static String PARAM_TYPE_NUMBER_DEPENDANTS = "NOD";
	public final static String PARAM_TYPE_BUSINESS_INDUSTRY = "PBI";
	public final static String PARAM_TYPE_JABATAN = "PJ";
	public final static String PARAM_TYPE_MONTHLY_SALARY = "PMS";
	public final static String PARAM_TYPE_MONTHLY_SPENDING = "PMSP";
	public final static String PARAM_TYPE_PURPOSE = "PP";
	public final static String PARAM_TYPE_SOURCE_FUND = "PSF";
	public final static String PARAM_TYPE_WORK_TYPE = "PWT";
	public final static String PARAM_TYPE_PURPOSE_KPM = "PKPM";
	public final static String PARAM_TYPE_PURPOSE_KPR = "PKPR";
	public final static String PARAM_TYPE_PURPOSE_KTA = "PKTA";
	public final static String PARAM_TYPE_SUPPLEMENT_LIMIT = "SL";
	public final static String PARAM_TYPE_TENOR_KPM = "TKPM";
	public final static String PARAM_TYPE_TENOR_KPR = "TKPR";
	public final static String PARAM_TYPE_TENOR_KTA = "TKTA";
	public final static String PARAM_TYPE_ADDITIONAL_WORK_INFO = "PAWI";
	
	public final static String ONLINE_STR = "online";
	
	public final static String IPM_DAILY = "Daily";
	public final static String IPM_MONTHLY = "Monthly";
	public final static String IPM_YEARLY = "Yearly";
	
	public final static String CREDIT_CARD_YOYAGE = "VYGREG";
	public final static Integer YEARLY_VOYAGE_INCOME = 1200000000;
	
	public final static String AO_SHARE_EN = "AO_SHARE_EN";
	public final static String AO_SHARE_ID = "AO_SHARE_ID";
	public final static String AO_PHONE_NUMBER= "AO_PHONE_NUMBER";
	
	// Account Group
	public static final String ACCOUNTGRP_SAVINGS = "S";
	public static final String ACCOUNTGRP_CURRENTACC = "C";
	public static final String ACCOUNTGRP_MULTICURRENCY = "M";
	public static final String ACCOUNTGRP_SAVINGSPLAN = "SP";
	public static final String ACCOUNTGRP_TIMEDEPOSIT = "T";
	public static final String ACCOUNTGRP_WEALTHPRODUCTS = "W";
	public static final String ACCOUNTGRP_CREDITCARD = "CC";
	public static final String ACCOUNTGRP_LOAN = "L";
	
	// Investment 01007 flag
	public static final String INVESTMENT_UNITTRUST = "RDN";
	public static final String INVESTMENT_OBLIGATION = "ORI";
	
	// Transfer Limit Scheduled
	public static final String TRANSFER_LIMIT_TASK_SUCCESS = "SUCCESS";
	public static final String TRANSFER_LIMIT_TASK_FAILED = "FAILED";
	public static final String TRANSFER_LIMIT_TASK_STATUS = "TRANSFER_LIMIT_TASK_STATUS";
	
	public static final String STATUS_ACTIVE = "A";
	
	// Intro Page WS
	public static final String FUNC_INTRO_PAGE = "FUNC_INTRO_PAGE";
	public final static String MIB_EMPTY_INTRO_PAGE_LIST = "MCB.0000143";
	
	// Scheduled Job Name
	public static final String SCHEDULED_JOB_BANK_LIST = "BANKLIST";
	public static final String SCHEDULED_JOB_TRXN_LIMIT = "TRXNLIMIT";
	public static final String SCHEDULED_JOB_EXG_RATE = "EXGRATE";
	public static final String SCHEDULED_JOB_UT_PRODUCT_LIST = "UTPRODUCTLIST";
	public static final String SCHEDULED_JOB_TT_BANK_INFO = "TTBANKINFO";
	
	// Payment and Purchase
	public static final String BILLER_TYPE_PAYMENT = "PAYMENT";
	public static final String BILLER_TYPE_PURCHASE = "PURCHASE";
	public static final String BILLER_TYPE_PEMBAYARAN = "PEMBAYARAN";
	public static final String BILLER_TYPE_PEMBELIAN = "PEMBELIAN";
	
	public static final String FUNC_RETREIEVE_UNBILL_CC = "FUNC_RETREIEVE_UNBILL_CC";
	
	public static final String PP_TYPE_REG = "REG";
	public static final String PP_TYPE_CC = "CC";
	public static final String PP_TYPE_UNREG = "UNREG";
	
	public static final String LANG_EN = "EN";
	public static final String LANG_IN = "IN";
	public static final String LANG_CN = "CN"; //FE android hardcode is zh , IOS hardcode is zh-Hans, OMNI/DB code: cn
	
	// Blitz Cinema
	public final static String FUNC_BLITZ_GET_CITY = "FUNC_BLITZ_GET_CITY";
	public final static String FUNC_BLITZ_GET_MOVIE = "FUNC_BLITZ_GET_MOVIE";
	public final static String FUNC_BLITZ_GET_CINEMA = "FUNC_BLITZ_GET_CINEMA";
	public final static String FUNC_BLITZ_GET_SHOWDATE = "FUNC_BLITZ_GET_SHOWDATE";
	public static final String FUNC_BLITZ_GET_AUDI_TYPE = "FUNC_BLITZ_GET_AUDI_TYPE";
	public static final String FUNC_BLITZ_GET_SCHEDULES = "FUNC_BLITZ_GET_SCHEDULES";
	public static final String FUNC_BLITZ_GET_AUDI_NO = "FUNC_BLITZ_GET_AUDI_NO";
	public static final String FUNC_BLITZ_GET_SEAT_INFO = "FUNC_BLITZ_GET_SEAT_INFO";
	public static final String FUNC_BLITZ_SOURCE_OF_FUND = "FUNC_BLITZ_SOURCE_OF_FUND";
	
	// Unit Trust action code - SUBSCRIBE, REDEEM, SWITCH
	public static final String UT_ACTION_SUBSCRIBE = "SUB";
	public static final String UT_ACTION_REDEEM = "RED";
	public static final String UT_ACTION_SWITCH = "SWI";
	
	// Manage Beneficiary Type
	public static final String MB_TRANSFER = "TF";
	public static final String MB_PAYMENT = "PAY";
	public static final String MB_PURCHASE = "PUR";
	public final static String MIB_EMPTY_BENEFICIARY_LIST = "MCB.0000149";
	
	// Tnc Product Key
	public static final String TNC_KEY_UNIT_TRUST = "TNC_KEY_UNIT_TRUST";
	public final static String MIB_EMPTY_TNC_UNIT_TRUST = "MCB.0000151";
	
	public static final String STRING_FALSE = "false";
	public static final String STRING_TRUE = "true";
	
	// Telegraphic Transfer Module
	public final static String FUNC_TT_SOURCE_OF_FUND = "FUNC_TT_SOURCE_OF_FUND";
	public final static String FUNC_TT_SAVED_RECIPIENT = "FUNC_TT_SAVED_RECIPIENT";
	public final static String FUNC_TT_VALIDATE_SWIFT_CODE = "FUNC_TT_VALIDATE_SWIFT_CODE";
	public final static String FUNC_TT_LIST_BANK_COUNTRY = "FUNC_TT_LIST_BANK_COUNTRY";
	public final static String FUNC_TT_LIST_BANK_CITY = "FUNC_TT_LIST_BANK_CITY";
	public final static String FUNC_TT_LIST_BANK = "FUNC_TT_LIST_BANK";
	public final static String FUNC_TT_REQ_RECIPIENT_TRANSFER = "FUNC_TT_REQ_RECIPIENT_TRANSFER";
	public final static String FUNC_TT_VALIDATE_TRXN = "FUNC_TT_VALIDATE_TRXN";
	public final static String FUNC_TT_ACKNOWLEDGEMENT = "FUNC_TT_ACKNOWLEDGEMENT";
	public final static String FUNC_TT_CALC_AMOUNT = "FUNC_TT_CALC_AMOUNT";
	public final static String FUNC_TT_DEL_BENEFICIARY = "FUNC_TT_DEL_BENEFICIARY";
	public final static String FUNC_TT_SAVE_BENEFICIARY = "FUNC_TT_SAVE_BENEFICIARY";
	
	// Opening New Account
	public final static String FUNC_ONA_TNC = "FUNC_ONA_TNC";
	public final static String FUNC_ONA_RETRIEVE_PRODUCT_LIST = "FUNC_ONA_RETRIEVE_PRODUCT_LIST";
	public final static String FUNC_ONA_CCY_SELECTION = "FUNC_ONA_CCY_SELECTION";
	public final static String FUNC_ONA_RETRIEVE_PARAM = "FUNC_ONA_RETRIEVE_PARAM";
	public final static String FUNC_ONA_INFO_INPUT = "FUNC_ONA_INFO_INPUT";
	public final static String FUNC_ONA_RETRIEVE_DOC_LIST = "FUNC_ONA_RETRIEVE_DOC_LIST";
	public final static String FUNC_ONA_KTPNIK_INPUT = "FUNC_ONA_KTPNIK_INPUT";
	public final static String FUNC_ONA_CC_SELECTION = "FUNC_ONA_CC_SELECTION";
	public final static String FUNC_ONA_BANK_LIST = "FUNC_ONA_BANK_LIST";
	public final static String FUNC_ONA_BANK_BRANCH = "FUNC_ONA_BANK_BRANCH";
	public final static String FUNC_ONA_CHECK_POSTAL_CODE = "FUNC_ONA_CHECK_POSTAL_CODE";
	public final static String FUNC_ONA_SEARCH_POSTAL_CODE = "FUNC_ONA_SEARCH_POSTAL_CODE";
	public final static String FUNC_ONA_CREATE_ACCOUNT_DL = "FUNC_ONA_CREATE_ACCOUNT_DL";
	public final static String FUNC_ONA_CACHE_USER_INPUT = "FUNC_ONA_CACHE_USER_INPUT";
	public final static String FUNC_ONA_RETRIEVE_CACHE_USER_INPUT = "FUNC_ONA_RETRIEVE_CACHE_USER_INPUT";
	public final static String FUNC_ONA_REMOVE_CACHE_USER_INPUT = "FUNC_ONA_REMOVE_CACHE_USER_INPUT";
	public final static String FUNC_ONA_RETRIEVE_PRODUCT_IMAGE = "FUNC_ONA_RETRIEVE_PRODUCT_IMAGE";
	public final static String FUNC_ONA_INSTALLMENT_CALCULATOR = "FUNC_ONA_INSTALLMENT_CALCULATOR";
	public final static String FUNC_ONA_RETRIEVE_OCR_IMG = "FUNC_ONA_RETRIEVE_OCR_IMG";
	public final static String FUNC_ONA_SUBMIT_OCR_IMG_TO_OMNI = "FUNC_ONA_SUBMIT_OCR_IMG_TO_OMNI";
	public final static String FUNC_ONA_SUBMIT_OCR_IMG= "FUNC_ONA_SUBMIT_OCR_IMG";
	public final static String FUNC_ONA_INQUIRY_DUKCAPIL_TIME_OUT = "FUNC_ONA_INQUIRY_DUKCAPIL_TIME_OUT";
	public final static String FUNC_ONA_SUBMIT_EDITED_DATA_DUKCAPIL = "FUNC_ONA_SUB_EDITED_DATA_DUKCAPIL";
	public final static String FUNC_ONA_CREATE_ACCOUNT_FN = "FUNC_ONA_CREATE_ACCOUNT_FN";
	public final static String FUNC_ONA_TAKA_STEP1 = "FUNC_ONA_TAKA_STEP1";
	public final static String FUNC_ONA_TAKA_STEP2 = "FUNC_ONA_TAKA_STEP2";
	public final static String FUNC_ONA_TAKA_CALC = "FUNC_ONA_TAKA_CALC";
	public final static String FUNC_ONA_TAKA_CACHE_DATA = "FUNC_ONA_TAKA_CACHE_DATA";
	public final static String FUNC_ONA_TAKA_CONFIRMATION = "FUNC_ONA_TAKA_CONFIRMATION";
	public final static String FUNC_ONA_TAKA_ACKNOWLEDGEMENT = "FUNC_ONA_TAKA_ACKNOWLEDGEMENT";
	public final static String FUNC_ONA_TANDA360_STEP1 = "FUNC_ONA_TANDA360_STEP1";
	public final static String FUNC_ONA_TANDA360_CONFIRMATION = "FUNC_ONA_TANDA360_CONFIRMATION";
	public final static String FUNC_ONA_TANDA360_ACK = "FUNC_ONA_TANDA360_ACK";
	public final static String FUNC_ONA_GIRO_STEP1 = "FUNC_ONA_GIRO_STEP1";
	public final static String FUNC_ONA_GIRO_CONFIRMATION = "FUNC_ONA_GIRO_CONFIRMATION";
	public final static String FUNC_ONA_GIRO_ACKNOWLEDGEMENT = "FUNC_ONA_GIRO_ACKNOWLEDGEMENT";
	public final static String FUNC_ONA_TDS_STEP1 = "FUNC_ONA_TDS_STEP1";
	public static final String FUNC_ONA_TDS_INTEREST_PAYMENT = "FUNC_ONA_TDS_INTEREST_PAYMENT";
	public final static String FUNC_ONA_TDS_CONFIRMATION = "FUNC_ONA_TDS_CONFIRMATION";
	public final static String FUNC_ONA_TDS_ACKNOWLEDGEMENT = "FUNC_ONA_TDS_ACKNOWLEDGEMENT";
	public final static String FUNC_ONA_CC_AND_LOAN = "FUNC_ONA_CC_AND_LOAN";
	public final static String FUNC_ONA_RETRIEVE_CC = "FUNC_ONA_RETRIEVE_CC";
	public final static String FUNC_ONA_OFFLINE_SYARIAH = "FUNC_ONA_OFFLINE_SYARIAH";
	
	//Taka hadiah
	public final static String FUNC_ONA_TAKA_HADIAH_STEP1 = "FUNC_ONA_TAKA_HADIAH_STEP1";
	public final static String FUNC_ONA_TAKA_HADIAH_CONFIRMATION = "FUNC_ONA_TAKA_HADIAH_CONFIRMATION";
	public final static String FUNC_ONA_TAKA_HADIAH_ACKNOWLEDGEMENT = "FUNC_ONA_TAKA_HADIAH_ACKNOWLEDGEMENT";
	
	public final static String FUNC_ONA_TANDA_HOLD_STEP1 = "FUNC_ONA_TANDA_HOLD_STEP1";
	public final static String FUNC_ONA_TANDA_HOLD_ACKNOWLEDGEMENT = "FUNC_ONA_TANDA_HOLD_ACKNOWLEDGEMENT";
	public final static String FUNC_ONA_TANDA_HOLD_CONFIRMATION = "FUNC_ONA_TANDA_HOLD_CONFIRMATION";
	
	public final static String MIB_ONA_EMPTY_TNC = "MCB.0000152";
	public final static String MIB_ONA_EMPTY_PARAM_LIST = "MCB.0000153";
	public final static String MIB_ONA_INVALID_PRODUCT_CODE = "MCB.0000154";
	public final static String MIB_EMPTY_CREDITCARD_LIST = "MCB.0000155";
	
	// Edit Profile Information
	public final static String FUNC_EPI_LOAD_USER_PROFILE = "FUNC_EPI_LOAD_USER_PROFILE";
	public final static String FUNC_EPI_EDIT_USER_PROFILE = "FUNC_EPI_EDIT_USER_PROFILE";
	public final static String FUNC_EPI_GET_PARAM_LIST = "FUNC_EPI_GET_PARAM_LIST";
	public final static String FUNC_EPI_GET_ADDRESS_LIST = "FUNC_EPI_GET_ADDRESS_LIST";
	public final static String FUNC_EPI_FILTER_SELECTION = "FUNC_EPI_FILTER_SELECTION";
	
	// Edit Profile Information (Cache Parameters' key)
	public final static String EPI_MARITALSTATUS = "EPI_MS";
	public final static String EPI_RELIGION = "EPI_R";
	public final static String EPI_NETINCOME = "EPI_NI";
	public final static String EPI_AVERAGETRXN = "EPI_AT";
	public final static String EPI_WORKTYPE = "EPI_WT";
	public final static String EPI_INDUSTRY = "EPI_IND";
	public final static String EPI_JOBSTITLE = "EPI_JT";
	
	public final static String KEY_TENOR_YEAR= "KEY_TENOR_YEAR";
	public final static String KEY_TENOR_YEARS= "KEY_TENOR_YEARS";
	
	//GNCODE
	public final static String MAX_YEAR_TAKABUNGA = "maxYearTB";
	public final static String NUMDAYS_CACHE_TAKADATA = "numDaysCacheTaka";
	
	public final static String BUNGA_WITH_INSURANCE = "BUNGA_WITH_INSURANCE";
	public final static String BUNGA_WITHOUT_INSURANCE = "BUNGA_WITHOUT_INSURANCE";
	public final static String BUNGAPASTI_WITH_INSURANCE = "BUNGAPASTI_WITH_INSURANCE";
	public final static String BUNGAPASTI_WITHOUT_INSURANCE = "BUNGAPASTI_WITHOUT_INSURANCE";
	public final static String TAKA_BUNGA = "TAKA_BUNGA";
	public final static String TAKA_BUNGAPASTI = "TAKA_BUNGAPASTI";
	
	//TD syariah
	public final static String GN_CODE_ONA_TDS = "ONA_TDS";
	public final static String GN_CODE_ONA_TDS_PURPOSE_OPN_CODE = "PURPOSE_OPN_CODE";
	public final static String GN_CODE_ONA_TDS_SOURCE_OF_FUND_CODE = "SOURCE_OF_FUND_CODE";
	
	//MATURITY CATEGORY
	public final static String MATURITY_LISTING_TIME_DEPOSIT = "TD";
	public final static String MATURITY_LISTING_TIME_DEPOSIT_SYARIAH= "TDS";
	
	//Forex Module
	public final static String FOREX_COTTYPE = "FOREX";
	public final static String FUNC_FOREX_IS_ALLOWED_PROCEED = "FUNC_FOREX_IS_ALLOWED_PROCEED";
//	public final static String FUNC_FOREX_SOURCE_OF_FUND = "FUNC_FOREX_SOURCE_OF_FUND";
//	public final static String FUNC_FOREX_SAVED_RECIPIENT_LIST = "FUNC_FOREX_SAVED_RECIPIENT_LIST";
	public final static String FUNC_FOREX_TNC = "FUNC_FOREX_TNC";
	public final static String FUNC_FOREX_GET_CURRENCY_RATE = "FUNC_FOREX_GET_CURRENCY_RATE";
	public final static String FUNC_FOREX_GET_EXCHANGE_RATE = "FUNC_FOREX_GET_EXCHANGE_RATE";
	public final static String FUNC_FOREX_CONFIRMATION = "FUNC_FOREX_CONFIRMATION";
	public final static String FUNC_FOREX_ACKNOWLEDGEMENT = "FUNC_FOREX_ACKNOWLEDGEMENT";
	
	// General Reusable Modules
	public final static String FUNC_GEN_STORE_OCR_IMAGE = "FUNC_GEN_STORE_OCR_IMAGE";
	public final static String FUNC_GEN_RETRIEVE_OCR_IMAGE = "FUNC_GEN_RETRIEVE_OCR_IMAGE";
	public final static String FUNC_GEN_SEARCH_POSTAL_CODE = "FUNC_GEN_SEARCH_POSTAL_CODE";
	public final static String FUNC_GEN_TNC = "FUNC_GEN_TNC";
	public final static String FUNC_GEN_HELP_INFO = "FUNC_GEN_HELP_INFO";
	public final static String FUNC_GEN_RETRIEVE_CACHE = "FUNC_GEN_RETRIEVE_CACHE";
	
	// V2 of General Modules
	public final static String FUNC_GEN_STORE_IMAGE = "FUNC_GEN_STORE_IMAGE";
	public final static String FUNC_GEN_RETRIEVE_IMAGE = "FUNC_GEN_RETRIEVE_IMAGE";
	
	// General Cache Status Codes to be used by any cache modules & Error messages
	public final static String CACHE_STATUS_ACTIVE = "ACTIVE";
	public final static String CACHE_STATUS_DELETED = "DELETED";
	public final static String CACHE_STATUS_SUCCESS = "SUCCESS";
	public final static String CACHE_STATUS_FAILED = "FAILED";
	
	public final static String MIB_GEN_NO_CACHE_FOUND = "MCB.0001001";
	public final static String MIB_GEN_NO_IMAGE_CACHE_FOUND = "MCB.0001002";
	public final static String MIB_GEN_DUPLICATE_IMAGE_CACHE_FOUND = "MCB.0001003";
	public final static String MIB_GEN_NO_HELP_INFO_CONTENT_FOUND = "MCB.0001004";
	
	// Pre Approved Loan (PAL)
	public final static String FUNC_PAL_STEP1 = "FUNC_PAL_STEP1";
	public final static String FUNC_PAL_STEP2 = "FUNC_PAL_STEP2";
	public final static String FUNC_PAL_SUBMIT_OCR_TO_OMNI = "FUNC_PAL_SUBMIT_OCR_TO_OMNI";
	public final static String FUNC_PAL_INQ_NIK = "FUNC_PAL_INQ_NIK";
	public final static String FUNC_PAL_CONFIRMATION = "FUNC_PAL_CONFIRMATION";
	public final static String FUNC_PAL_SUBMIT_EDITED_DATA_DUKCAPIL = "FUNC_PAL_SUBMIT_EDITED_DATA_DUKCAPIL";
	public final static String FUNC_PAL_INSTALLMENT_CALC = "FUNC_PAL_INSTALLMENT_CALC";
	public final static String FUNC_PAL_STORE_CACHE = "FUNC_PAL_STORE_CACHE";
	public final static String FUNC_PAL_COMPARE_ADDRESS = "FUNC_PAL_COMPARE_ADDRESS";
	public final static String CUST_TYPE_PAYROLL = "PAYROLL";
	public final static String CUST_TYPE_REGULAR = "REGULAR";
	public final static String SUBSCRIPTION_TYPE_ONLINE = "Online";
	public final static String LESS_THAN_25MILLION = "LT 25M";
	public final static String MORE_THAN_OR_EQUAL_TO_25MILLION = "MTE 25M";
	public final static String PAL_EXISTING_APPLICATION_CHECK_FLAG = "PAL_EXISTING_APPLICATION_CHECK";
	public final static String PAL_LOWER_LIMIT = "PAL_LOWER_LIMIT";
	
	public final static String MIB_PAL_DUPLICATE_CACHE_FOUND = "MCB.0000801";
	public final static String MIB_PAL_OMNI_IMAGE_SUBMISSION_FAILED = "MCB.0000802";
	public final static String MIB_PAL_MISSING_START_OVER_FLAG = "MCB.0000803";
	public final static String MIB_PAL_NO_SAVINGS_ACCOUNT = "MCB.0000804";
	public final static String MIB_PAL_BYPASS = "MCB.0000805";
	
	// General Status Codes
	public final static String GEN_STATUS_SUCCESS = "S";
	public final static String GEN_STATUS_FAILED = "F";
	public final static String GEN_STATUS_ACTIVE = "A";
	public final static String GEN_STATUS_DELETED = "D";
	
	// Module Codes for General Reusable Modules
	public final static String MODULE_CODE_PRE_APPROVED_LOAN = "PAL";
	public final static String MODULE_CODE_ACC_ON_BOARDING = "AOB";
	public final static String MODULE_CODE_NEW_TO_INVESTMENT = "NTI";
	
	//QR function codes
	public final static String FUNC_QR_PAY_GET_ACC_LIST= "FUNC_QR_PAY_GET_ACC_LIST";
	public final static String FUNC_QR_PAY_GET_ACC= "FUNC_QR_PAY_GET_ACC";
	public final static String FUNC_QR_PAY_SET_ACC= "FUNC_QR_PAY_SET_ACC";
	public final static String FUNC_QR_PAY_INQUIRY= "FUNC_QR_PAY_INQUIRY";
	public final static String FUNC_QR_PAY_CONFIMATION= "FUNC_QR_PAY_CONFIMATION";
	public final static String FUNC_QR_PAY_ACKNOWLEDGEMENT= "FUNC_QR_PAY_ACKNOWLEDGEMENT";

	//QR status
	public final static String MIB_QR_INVALID_QR="MCB.0000156";
	public final static String QR_VALID = "00";
	public final static String QR_INVALID_CRC = "01";
	public final static String QR_INVALID_FORMAT = "02";
	public final static String QR_INSUFFICIENT_FIELD= "03";
	public final static String QR_INVALID_CCY = "04";

	// Entry Point Codes
	public final static String MIB_EP_ACCOUNT_OPENING = "AO";
	public final static String MIB_EP_PUSH_NOTIFICATION = "PNS";
	public final static String MIB_EP_DEEP_LINK = "DL";
	
	//Poin Seru 
	public final static String FUNC_POIN_SERU_STORE= "FUNC_POIN_SERU_STORE";
	
	//QR pay gn code
	public final static String GN_CODE_QR= "QRPY";
	public final static String GN_CODE_QR_MIN_AMOUNT= "MIN_AMOUNT";
	

	//NTI
	public final static String FUNC_NTI_STEP1 = "FUNC_NTI_STEP1";
	public final static String FUNC_NTI_INQ_NIK = "FUNC_NTI_INQ_NIK";
	public final static String FUNC_NTI_LOAD_QUESTION = "FUNC_NTI_LOAD_QUESTION";
	public final static String FUNC_NTI_ACKNOWLEDGEMENT = "FUNC_NTI_ACKNOWLEDGEMENT";
	public final static String FUNC_NTI_SUBMIT_IMAGE_TO_OMNI = "FUNC_NTI_SUBMIT_IMAGE_TO_OMNI";
	public final static String FUNC_NTI_VALIDATE = "FUNC_NTI_VALIDATE";
	public final static String FUNC_NTI_STORE_CACHE = "FUNC_NTI_STORE_CACHE";

	public final static String MIB_NTI_NO_ACCOUNT = "MCB.0000901";
	public final static String MIB_NTI_MISSING_DOC_IMAGE = "MCB.0000902";
	public final static String MIB_NTI_DUPLICATE_DOC_IMAGE = "MCB.0000903";
	public final static String MIB_NTI_OMNI_IMAGE_SUBMISSION_FAILED = "MCB.0000904";
	public final static String MIB_NTI_NO_MATCHING_ACCOUNT = "MCB.0000905";
	public final static String MIB_NTI_DUPLICATE_CACHE_FOUND = "MCB.0000906";


	//Primary bond function codes
	public final static String FUNC_PB_STEP_1= "FUNC_PB_STEP_1";
	public final static String FUNC_PB_STEP_2= "FUNC_PB_STEP_2";
	public final static String FUNC_PB_INVESTOR_INQUIRY = "FUNC_PB_INVESTOR_INQUIRY";
	public final static String FUNC_PB_INVESTOR_SAVE = "FUNC_PB_INVESTOR_SAVE";
	public final static String FUNC_PB_ORDER_CONFIRMATION= "FUNC_PB_ORDER_CONFIRMATION";
	public final static String FUNC_PB_ORDER_ACKNOWLEDGEMENT= "FUNC_PB_ORDER_ACKNOWLEDGEMENT";
	public final static String FUNC_PB_HISTORY_INQUIRY= "FUNC_PB_HISTORY_INQUIRY";
	public final static String FUNC_PB_REDEEM_CONFIRMATION= "FUNC_PB_REDEEM_CONFIRMATION";
	public final static String FUNC_PB_REDEEM_ACKNOWLEDGEMENT= "FUNC_PB_REDEEM_ACKNOWLEDGEMENT";
	
	//MCB - ONFX function code
	public static final String FUNC_MCB_ONFX_STEP1 = "FUNC_MCB_ONFX_STEP1";
	
	//Product Type
	public final static String PRODUCT_CODE_ONFX="ONFX";
	public final static String PRODUCT_CODE_TDAO="TDAO";
	public final static String PRODUCT_CODE_TDAM="TDAM";
	
	public final static String TNC_TYPE = "tnc";
	public final static String TNC_GENERAL_CODE = "tnc_general";
	public final static String CUSTOMER_STATE_TNC_ACCEPT_ACTION= "accept";
	public final static String TNC_SOFTWARE_TOKEN = "softwareToken_Tnc";
	public final static String TNC_RESET_PASSWORD = "tnc_resetPassword";
	
	
	public final static String DEFAULT_STATEMENT_DAYS= "90";
	
	
	public final static String TASKMAPPRODCODE = "TASKMAPPRODCODE";
	public final static String TRANXPRODCODE = "TRANXPRODCODE";
	public final static String TRANXPRODCODE_IN = "TRANXPRODCODE_IN";
	public final static String TRANXPRODCODE_CN = "TRANXPRODCODE_CN";
	public final static String TRANXSTATUSCODE = "TRANXSTATUSCODE";
	public final static String TRANXSTATUSCODE_IN = "TRANXSTATUSCODE_IN";
	public final static String TRANXSTATUSCODE_CN = "TRANXSTATUSCODE_CN";
	public final static String PYMT_GRP_IMG= "PYMT_GRP_IMG";
	public final static String PURPOSE_LIST = "PURPOSE_LIST";
	public final static String FT_RECURRING = "FT_RECURRING";
	public final static String FT_FX_OPTION = "FT_FX_OPTION";
	public final static String FT_TT_CHARGES_METHOD = "FT_TT_CHARGES_METHOD";
	public final static String TRANXUPLOADFORMAT = "TRANXUPLOADFORMAT";
	public final static String ROLLOVERTYPE = "ROLLOVERTYPE";
	public final static String BENE_SHARED_OPTION = "BENE_SHARED_OPTION";
	public final static String PYMT_GRP_IMG_DARK= "PYMT_GRP_IMG_DARK";
	
	
	public final static String BLOCK_STATUS_STATE_TYPE = "blockStatusState";
	
	public final static String TNC_TYPE_FX = "FX";
	
	public final static String FUNC_TRUE = "Y";
	public final static String FUNC_FALSE = "N";
	public final static String FUNC_PRODUCT_CODE_LOAN = "LOANACCT";
	public final static String FUNC_PRODUCT_CODE_UNIT_TRUST = "UNTTRST";
	public final static String FUNC_PRODUCT_CODE_BONDS = "OBLGSRTL";
	public final static String FUNC_PRODUCT_CODE_ESPT = "ESPT";
	public final static String FUNC_PRODUCT_CODE_BANCAS = "BANCAS";
	public final static String MIN_YEAR = "MIN_YEAR";
	public final static String MAX_YEAR = "MAX_YEAR";
	
	public final static String UPDATE = "U";
	public final static String ACTION_CODE_EDIT = "E";
	public final static String USER_PROFILE_CODE = "SUPER";
	
	//Duplicate ktp,phone,email
	public final static String MIB_DUPLICATE = "MCB.0000055";
	
	
	public final static String MIB_NOTFOUND = "MCB.0000056";
	
	//Form
	public final static String DOCUMENT_ID = "FORM_BLOCK_USER";
	public final static String DOCUMENT_TYPE = "BLOCK";
	public final static String DOCUMENT_ID_TT = "FORM_TT";
	public final static String DOCUMENT_TYPE_LLD = "LLD";
	
	//Ext Channel
	public final static String CHANNEL001 = "CHANNEL001";
	public final static String CHANNEL002 = "CHANNEL002";
	public final static String CHANNEL003 = "CHANNEL003";
	public final static String CHANNEL004 = "CHANNEL004";
	public final static String CHANNEL005 = "CHANNEL005";
	public final static String CHANNEL001_DESC = "API Integration";
	public final static String CHANNEL002_DESC = "API H2H Non Bulk";
	public final static String CHANNEL003_DESC = "API H2H Bulk";
	public final static String CHANNEL004_DESC = "API H2H Etax";
	public final static String CHANNEL005_DESC = "Velocity Web";

	//Device binding approval setting
	public static final String GNCODE_PRIMARY_DEV_BINDING= "primaryDevBind";
	public static final String GNCODE_DEV_BINDING_PENDING_TIME_LIMIT= "maxPendingLimit";
	public static final int DEV_BINDING_DEFAULT_PENDING_TIME_LIMIT= 5;
}
