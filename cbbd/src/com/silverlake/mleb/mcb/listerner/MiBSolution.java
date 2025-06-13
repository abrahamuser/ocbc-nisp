package com.silverlake.mleb.mcb.listerner;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.mcb.bean.ObHeaderResponse;
import com.silverlake.mleb.mcb.bean.ObResponse;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.entity.dao.MLEBMIBDAO;
import com.silverlake.mleb.mcb.module.func.RetrieveSessionInfo;
import com.silverlake.mleb.mcb.module.func.deviceBinding.DeviceCIFBindingAck;
import com.silverlake.mleb.mcb.module.func.deviceBinding.DeviceCIFEnquiry;
//import com.silverlake.mleb.mcb.module.func.deviceBinding.DeviceCIFUnbindAckBak;
import com.silverlake.mleb.mcb.module.func.deviceBinding.DeviceCIFUnbindConf;
import com.silverlake.mleb.mcb.module.func.ext.CustEventEnquiry;
import com.silverlake.mleb.mcb.module.func.ext.MessageMappingEnquiry;
import com.silverlake.mleb.mcb.module.func.ext.MessageMappingUpdateAck;
import com.silverlake.mleb.mcb.module.func.ext.MessageMappingUpdateConfimation;
import com.silverlake.mleb.mcb.module.func.ext.SysConfEnquiry;
import com.silverlake.mleb.mcb.module.func.ext.SysConfUpdateAck;
import com.silverlake.mleb.mcb.module.func.ext.SysConfUpdateConfimation;
 

@Service("MiBSolution")
public class MiBSolution  {

	/*
	private static Logger log =  LogManager.getLogger(MiBSolution.class);
	
	@Autowired
	MLEBMIBDAO dao;

	@Autowired PerformLoginSession performLogin;

	@Autowired RetrieveDeviceProfiles retrieveDeviceProfiles;

	@Autowired RequestDeviceTaggingTAC requestTAC;

	@Autowired PerformLogout_bak performLogout;

	@Autowired RetrieveAccessInfo accessInfo;

	@Autowired RetrieveSessionInfo sessionInfo;

	@Autowired RetrieveAppStatInfo_mib retrieveAppStatInfo;

	@Autowired RetrieveSecurityToken retrieveSecurityToken;

	// Account Overview
	@Autowired RetrieveAccountOverview retrieveAccountOverview;
	@Autowired RetrieveAccountOverview2 retrieveAccountOverview2;

	@Autowired BrowseCurrencyComposition2 browseCurrencyComposition2;
	@Autowired ConfigureAutoRenewal configureAutoRenewal;
	@Autowired BrowseInsuranceList browseInsuranceList;
	@Autowired RetrieveOwnUnitTrustSummary retrieveOwnUnitTrustSummary;
	@Autowired BrowseUnitTrustList browseUnitTrustList;
	@Autowired RetrieveEstatementListService retrieveEstatementListService;
	@Autowired RetrieveAccountPointService retrieveAccountPointService;
	@Autowired RetrieveAccountPointWithoutHistoryService retrieveAccountPointWithoutHistoryService;

	@Autowired RetrieveTransactionHistory retrieveTransactionHistory;	

	@Autowired RetrieveTransactionHistory2 retrieveTransactionHistory2;	

	@Autowired RetrieveTransactionHistoryPDFService retrieveTransactionHistoryPDFService;	

	@Autowired EstatmentCreditCardPDFService estatmentCreditCardPDFService;	

	@Autowired EstatmentPDFService estatmentPDFService;

	//Fund Transfer

	@Autowired
	PerformFundTransferGetListService performFundTransferGetListService;

	@Autowired
	PerformFundTransferOwnGetListService performFundTransferOwnGetListService;

	@Autowired
	PerformFundTransferOwnValidateService performFundTransferOwnValidateService; 

	@Autowired
	PerformFundTransferOwnConfService performFundTransferOwnConfService;

	@Autowired
	PerformFundTransferOwnAckService performFundTransferOwnAckService;

	@Autowired
	PerformFundTransferIntraValidateService performFundTransferIntraValidateService;

	@Autowired
	PerformFundTransferIntraConfService performFundTransferIntraConfService;

	@Autowired
	PerformFundTransferIntraAckService performFundTransferIntraAckService;


	@Autowired
	RequestSMSTokenService requestSMSTokenService;

	@Autowired
	PerformFundTransferInterValidateService performFundTransferInterValidateService;

	@Autowired
	PerformFundTransferInterConfService performFundTransferInterConfService;

	@Autowired
	PerformFundTransferInterAckService performFundTransferInterAckService;

	@Autowired
	PerformFundTransferLimitExchangeRate performFundTransferLimitExchangeRate;

	// Fund Transfer (Revamp)
	@Autowired FundTransferSourceOfFundService fundTransferSourceOfFundService;
	@Autowired FundTransferSavedRecipientListService fundTransferSavedRecipientListService;
	@Autowired FundTransferBankListService fundTransferBankListService;
	@Autowired PerformFundTransferValidateService performFundTransferValidateService;
	@Autowired PerformFTConfirmationService performFTConfirmationService;
	@Autowired PerformFTAckService performFTAckService;
	@Autowired FundTransferTransactionLimitService fundTransferTransactionLimitService;
	@Autowired FundTransferTransferServiceListService fundTransferTransferServiceListService;
	@Autowired FundTransferCartTotalCalculatorService fundTransferCartTotalCalculatorService;
	@Autowired FundTransferUpdateFavBeneficiaryService fundTransferUpdateFavBeneficiaryService;
	@Autowired FundTransferTermAndConditionService fundTransferTermAndConditionService;
	
	// Manage Scheduled Transaction
	@Autowired FTLoadScheduledTrxnListService ftLoadScheduledTrxnListService;
	@Autowired FTGetFutureScheduledTrxnDetailService ftGetFutureScheduledTrxnDetailService;
	@Autowired FTCancelFutureTransferService ftCancelFutureTransferService;
	@Autowired FTGetRecurringScheduledTrxnDetailService ftGetRecurringScheduledTrxnDetailService;
	@Autowired FTDeleteRecurringScheduledTrxnService ftDeleteRecurringScheduledTrxnService;

	//payment & purchase module
	@Autowired RetrieveDebitAcctRegisteredPayeeCC getAvailableAcctRegPayeeList;
	@Autowired RetrieveDebitAcctRegisteredPayee retrieveDebitAcctRegisteredPayee;
	@Autowired RetrieveCreditCard retrieveCreditCard;
	@Autowired RetrieveRegisteredPayee retrieveRegisteredPayee;
	@Autowired RetrievePayeeTypePayeeOrganization retrieveBillersInfo;
	@Autowired RetrieveBillInfoNonRegistered retrieveBillInfoNonRegistered;
	@Autowired RetrieveBillInfoRegistered retrieveBillInfoRegistered;
	@Autowired RetrieveBillInfoCreditCard retrieveBillInfoCreditCard;
	@Autowired ValidatePaymentInformation validatePaymentInformation;
	@Autowired SubmitPaymentPurchaseConfirmNonRegistered submitPaymentPurchaseConfirmNonRegistered;
	@Autowired SubmitPaymentPurchaseConfirmRegistered submitPaymentPurchaseConfirmRegistered;
	@Autowired SubmitPaymentPurchaseConfirmCreditCard submitPaymentPurchaseConfirmCreditCard;
	@Autowired RequestSMSTokenForPaymentPurchase requestSMSTokenForPaymentPurchase;
	@Autowired RequestPaymentPurchaseAckNonRegistered requestPaymentPurchaseAckNonRegistered;	
	@Autowired RequestPaymentPurchaseAckRegistered requestPaymentPurchaseAckRegistered;	
	@Autowired RequestPaymentPurchaseAckCreditCard requestPaymentPurchaseAckCreditCard;	
	@Autowired UpdateFavouriteRegisteredPayee updateFavouriteRegisteredPayee;

	//Time Deposit
	@Autowired PerformTimeDepositListService performTimeDepositListService;
	@Autowired PerformTimeDepositConfService performTimeDepositConfService;
	@Autowired PerformTimeDepositSmsService performTimeDepositSmsService;
	@Autowired PerformTimeDepositAckgtService performTimeDepositAckgtService;
	
	// Time Deposit (MB2)
	@Autowired TimeDepositSourceOfFundService timeDepositSourceOfFundService;
	@Autowired TimeDepositRefreshSessionService timeDepositRefreshSessionService;
	@Autowired TimeDepositGetInterestPaymentService timeDepositGetInterestPaymentService;
	@Autowired TimeDepositOpeningConfirmationService timeDepositOpeningConfirmationService;
	@Autowired TimeDepositOpeningSMSTokenService timeDepositOpeningSMSTokenService;
	@Autowired TimeDepositOpeningAcknowledgementService timeDepositOpeningAcknowledgementService;
	@Autowired TimeDepositOpenNewAccountListService timeDepositOpenNewAccountListService;
	@Autowired TimeDepositCheckCutOfTimeService timeDepositCheckCutOfTimeService;
	@Autowired TimeDepositGetDetailsService timeDepositGetDetailsService;
	@Autowired TimeDepositTermNConditionService timeDepositTermNConditionService;

	//Account Maintenance Time Deposit
	@Autowired PerformAccountMaintenanceListService performAccountMaintenanceListService;
	@Autowired PerformAccountMaintenanceConfService performAccountMaintenanceConfService;
	@Autowired PerformAccountMaintenanceSmsService performAccountMaintenanceSmsService;
	@Autowired PerformAccountMaintenanceAckgtService performAccountMaintenanceAckgtService;

	//Internet Transaction History
	@Autowired InternetTransactionHistoryListService internetTransactionHistoryListService;
	@Autowired InternetTransactionHistorySearchService internetTransactionHistorySearchService;
	@Autowired InternetTransactionHistoryDetailService internetTransactionHistoryDetailService;

	//User Profile Maintenance
	@Autowired PerformChangePasswordService performChangePasswordService;
	@Autowired PerformUpdateUserProfileService performUpdateUserProfileService;
	@Autowired PerformLoadUserProfileService performLoadUserProfileService;

	//Smart Notification Setup
	@Autowired SmartNotificationSetupListService smartNotificationSetupListService;
	@Autowired SmartNotificationSetupSaveService smartNotificationSetupSaveService;

	//Manage Beneficiary Modules
	@Autowired ManageBeneficiaryModulesListService manageBeneficiaryModulesListService;
	@Autowired ManageBeneficiaryModulesViewService manageBeneficiaryModulesViewService;
//	@Autowired ManageBeneficiaryModulesEditService manageBeneficiaryModulesEditService;
	@Autowired ManageBeneficiaryModulesDeleteService manageBeneficiaryModulesDeleteService;

	//Payee Management Modules
	@Autowired LoadPayeeTable loadPayeeTable;
	@Autowired ViewPayeeMgmt viewPayeeMgmt;
	@Autowired DeletePayee deletePayee;

	// Manage Recurring Modules
	@Autowired RetrieveRecurringListService retrieveRecurringListService;
	@Autowired RetrieveRecurringTransferDetailsService retrieveRecurringTransferDetailsService;
	@Autowired DeleteTrxnForSelectedRecurringItemService deleteTrxnForSelectedRecurringItemService;

	// Manage Future Transfer Modules
	@Autowired FutureTransferListService futureTransferListService;
	@Autowired FutureTransferDetailsService futureTransferDetailsService;
	@Autowired FutureTransferDeleteService futureTransferDeleteService;

	// Manage Autodebit Modules
	@Autowired RetrieveAutodebitListService retrieveAutodebitListService;
	@Autowired RetrieveAutodebitDetailsService retrieveAutodebitDetailsService;
	@Autowired DeleteTrxnForSelectedAutodebitItemService deleteTrxnForSelectedAutodebitItemService;

	// Manage Future Payment Modules
	@Autowired FuturePaymentListService futurePaymentListService;
	@Autowired FuturePaymentDetailsService futurePaymentDetailsService;
	@Autowired FuturePaymentDeleteService futurePaymentDeleteService;

	@Autowired UploadCustProfile uploadCustProfile;

	//Unit Trust
	@Autowired PerformUnitTrustSubscriptionListService performUnitTrustSubscriptionList;
	@Autowired RetrieveUnitTrustProductInfoService retrieveUnitTrustProductInfo;
	@Autowired RetrieveUnitTrustTermTransactionService retrieveUnitTrustTermTransaction;
	@Autowired PerformUnitTrustSubscriptionConfService performUnitTrustSubscriptionConf;
	@Autowired PerformUnitTrustSubscriptionAckService performUnitTrustSubscriptionAck;
	@Autowired PerformUnitTrustSwitchingListService performUnitTrustSwitchingList;
	@Autowired RetrieveUnitTrustSwitchingProductListService retrieveUnitTrustSwitchingProductList;
	@Autowired PerformUnitTrustSwitchingConfService performUnitTrustSwitchingConf;
	@Autowired PerformUnitTrustSwitchingAckService performUnitTrustSwitchingAck;
	@Autowired PerformUnitTrustRedemptionListService performUnitTrustRedemptionList;
	@Autowired PerformUnitTrustRedemptionConfService performUnitTrustRedemptionConf;
	@Autowired PerformUnitTrustRedemptionAckService performUnitTrustRedemptionAck;
	@Autowired RequestSMSTokenForUnitTrustService requestSMSTokenForUnitTrust;
	@Autowired PerformUnitTrustSubscriptionValidateNewService performUnitTrustSubscriptionValidateNew;
	@Autowired RetrieveUnitTrustSubscriptionNewListService retrieveUnitTrustSubscriptionNewList;
	@Autowired RetrieveUnitTrustSubscriptionProductListService retrieveUnitTrustSubscriptionProductList;
	@Autowired RequestTnCForUnitTrustService requestTnCForUnitTrustService;
	
	//MB2 Unit Trust
	@Autowired UnitTrustAllProductListService unitTrustAllProductListService;
	@Autowired UnitTrustSubscribeConfirmationService unitTrustSubscribeConfirmationService;
	@Autowired UnitTrustSubscribeAcknowledgementService unitTrustSubscribeAcknowledgementService;
	@Autowired UnitTrustSMSTokenService unitTrustSMSTokenService;
	@Autowired UnitTrustRedeemProductListService unitTrustRedeemProductListService;
	@Autowired UnitTrustRedeemConfirmationService unitTrustRedeemConfirmationService;
	@Autowired UnitTrustRedeemAcknowledgementService unitTrustRedeemAcknowledgementService;
	@Autowired UnitTrustSwitchFromListService unitTrustSwitchFromListService;
	@Autowired UnitTrustSwitchToListService unitTrustSwitchToListService;
	@Autowired UnitTrustSwitchConfirmationService unitTrustSwitchConfirmationService;
	@Autowired UnitTrustSwitchAcknowledgementService unitTrustSwitchAcknowledgementService;
	@Autowired UnitTrustProductDetailService unitTrustProductDetailService;
	@Autowired UnitTrustOwnInvestmentSummaryService unitTrustOwnInvestmentSummaryService;
	@Autowired UnitTrustGetExchangeRateService unitTrustGetExchangeRateService;
	@Autowired UnitTrustQuickStep1Service unitTrustQuickStep1Service;
	@Autowired UnitTrustInsuranceQuestionService unitTrustInsuranceQuestionService;
	@Autowired UnitTrustCheckInsuranceEligibilityService unitTrustCheckInsuranceEligibilityService;
	
	@Autowired UnitTrustOwnProductListService unitTrustOwnProductListService;
	
	@Autowired UnitTrustSourceOfFundService unitTrustSourceOfFundService;
	@Autowired UnitTrustOthersProductListService unitTrustOthersProductListService;
	
	@Autowired DeviceCIFBindingAck devBind;
	@Autowired DeviceCIFEnquiry devEnquiry;
	//@Autowired DeviceCIFUnbindAckBak devUnBind;
	@Autowired DeviceCIFUnbindConf devUnBindConf;
	@Autowired SysConfEnquiry sysConfEnquiry;
	@Autowired SysConfUpdateConfimation SysConfUpdateConfimation;
	@Autowired SysConfUpdateAck sysConfUpdateAck;
	@Autowired CustEventEnquiry custEventEnquiry;
	@Autowired MessageMappingEnquiry msgMapEnquiry;
	@Autowired MessageMappingUpdateConfimation msgMappingUpdateConfimation;
	@Autowired MessageMappingUpdateAck msgMappingUpdateAck;


	@Autowired PerformFundTransferUpdateFavBeneficiaryService performFundTransferUpdateFavBeneficiary;

	// IB/MB Registration Modules
	@Autowired RegistrationRetrieveTnCService registrationRetrieveTnCService;
	@Autowired RegistrationRetrieveCAPTCHAService registrationRetrieveCAPTCHAService;
	@Autowired RegistrationValidateEligibilitiesService registrationValidateEligibilitiesService;
	@Autowired RegistrationValidateEligibilitiesService2 registrationValidateEligibilitiesService2;
	@Autowired RegistrationUsernameService registrationUsernameService;
	@Autowired RegistrationSubmitPINService registrationSubmitPINService;
	@Autowired RegistrationConfirmationService registrationConfirmationService;
	@Autowired RegistrationRetrieveRegisterPinService registrationRetrieveRegisterPinService;
	@Autowired RegistrationChangePasswordService registrationChangePasswordService;
	@Autowired RegistrationStartSessionService registrationStartSessionService;
	@Autowired RegistrationRefreshSessionService registrationRefreshSessionService;
	
	//Soft Token Modules
	@Autowired SoftTokenStep1Service softTokenStep1Service;
	@Autowired SoftTokenStep2Service softTokenStep2Service;

	//Eagle Eye Portfolio
	@Autowired PerformInquiryEagleEyeService performInquiryEagleEyeService;
	@Autowired PerformInquiryEagleEyeDetailsService performInquiryEagleEyeDetailsService;

	//Account OnBoarding
	@Autowired AccountOnBoardingDeviceNIKCheckingService accountOnBoardingDeviceNIKCheckingService;
	@Autowired AccountOnBoardingInfoInputService accountOnBoardingInfoInputService;
	@Autowired AccountOnBoardingResendPasscodeService accountOnBoardingResendPasscodeService;
	@Autowired AccountOnBoardingValidatePasscodeService accountOnBoardingValidatePasscodeService;
	@Autowired AccountOnBoardingTnCService accountOnBoardingTnCService;
	@Autowired AccountOnBoardingRetrieveDocListService accountOnBoardingRetrieveDocListService;
	@Autowired AccountOnBoardingSubmitOCRImageService accountOnBoardingSubmitOCRImageService;
	@Autowired AccountOnBoardingKTPNIKInputService accountOnBoardingKTPNIKInputService;
	@Autowired AccountOnBoardingCreditCardSelectionService accountOnBoardingCreditCardSelectionService;
	@Autowired AccountOnBoardingRetrieveBankListService accountOnBoardingRetrieveBankListService;
	@Autowired AccountOnBoardingRetrieveBankList2Service accountOnBoardingRetrieveBankList2Service;
	@Autowired AccountOnBoardingRetrieveBankBranchService accountOnBoardingRetrieveBankBranchService;
	@Autowired AccountOnBoardingCheckPostalCodeService accountOnBoardingCheckPostalCodeService;
	@Autowired AccountOnBoardingSearchPostalCodeService accountOnBoardingSearchPostalCodeService;
	@Autowired AccountOnBoardingPostalCodeWhitelistService accountOnBoardingPostalCodeWhitelistService;
	@Autowired AccountOnBoardingRetrieveProductListService accountOnBoardingRetrieveProductListService;
	@Autowired AccountOnBoardingRetrieveNationalityService accountOnBoardingRetrieveNationalityService;
	@Autowired AccountOnBoardingSubmitEditedDataDukcapilService accountOnBoardingSubmitEditedDataDukcapilService;
	@Autowired AccountOnBoardingRetrieveParamsService accountOnBoardingRetrieveParamsService;
	@Autowired AccountOnBoardingCreateAccountFNService accountOnBoardingCreateAccountFNService;
	@Autowired AccountOnBoardingCreateAccountDLService accountOnBoardingCreateAccountDLService;
	@Autowired AccountOnBoardingCacheUserInputService accountOnBoardingCacheUserInputService;
	@Autowired AccountOnBoardingRetrieveCacheUserInputService accountOnBoardingRetrieveCacheUserInputService;
	@Autowired AccountOnBoardingRemoveCacheUserInputService accountOnBoardingRemoveCacheUserInputService;
	@Autowired AccountOnBoardingRetrieveProductImageService accountOnBoardingRetrieveProductImageService;
	@Autowired AccountOnBoardingInstallmentCalculatorService accountOnBoardingInstallmentCalculatorService;
	@Autowired AccountOnBoardingSubmitOCRImageToOMNIService accountOnBoardingSubmitOCRImageToOMNIService;
	@Autowired AccountOnBoardingRetrieveOCRImageService accountOnBoardingRetrieveOCRImageService;
	@Autowired AccountOnBoardingInquiryDukcapilTimeOutService accountOnBoardingInquiryDukcapilTimeOutService;
	@Autowired AccountOnBoardingRefreshSessionService accountOnBoardingRefreshSessionService;
	@Autowired AccountOnBoardingFilterSelectionService accountOnBoardingFilterSelectionService;
	@Autowired AccountOnBoardingCheckCutOffTimeService accountOnBoardingCheckCutOffTimeService;
	@Autowired AccountOnBoardingGetListPublicHolidayService accountOnBoardingGetListPublicHolidayService;
	@Autowired AccountOnBoardingVideoCallService accountOnBoardingVideoCallService;
	@Autowired AccountOnBoardingCheckVideoCallService accountOnBoardingCheckVideoCallService;
	@Autowired AccountOnBoardingRetrieveIntroPageService accountOnBoardingRetrieveIntroPageService;
	
	@Autowired PerformFundTransferGetList2Service performFundTransferGetList2Service;
	
	// Intro Page (MB2)
	@Autowired RetrieveIntroPage retrieveIntroPage;
	
	// Payment (MB2)
	@Autowired PaymentSourceOfFundService paymentSourceOfFundService;
	@Autowired PaymentRegisteredBillerListService paymentRegisteredBillerListService;
	@Autowired PaymentUnRegisteredBillerListService paymentUnRegisteredBillerListService;
	@Autowired PaymentInquiryTransactionLimitService paymentInquiryTransactionLimitService;
	@Autowired PaymentInquiryBillerInfoService paymentInquiryBillerInfoService;
	@Autowired PaymentConfirmationService paymentConfirmationService;
	@Autowired PaymentRequestSMSTokenService paymentRequestSMSTokenService;
	@Autowired PaymentAcknowledgementService paymentAcknowledgementService;
	@Autowired PaymentUpdateFavouritePayeeService paymentUpdateFavouritePayeeService;
	
	@Autowired RetrieveUnbilledCreditCardStatementService retrieveUnbilledCreditCardStatementService;
	
	// Manage Scheduled Transactions - Payment (MB2)
	@Autowired PPLoadScheduledTrxnListService ppLoadScheduledTrxnListService;
	@Autowired PPLoadScheduledTrxnDetailService ppLoadScheduledTrxnDetailService;
	@Autowired PPCancelFutureTransferService ppCancelFutureTransferService;
	@Autowired PPDeleteRecurringScheduledTrxService ppDeleteRecurringScheduledTrxService;
	
	// Blitz Cinema
	@Autowired BlitzGetAudiTypesService blitzGetAudiTypesService;
	@Autowired BlitzGetSchedulesService blitzGetSchedulesService;
	@Autowired BlitzGetAudiNoService blitzGetAudiNoService;
	@Autowired BlitzGetCityListService blitzGetCityListService;
	@Autowired BlitzGetCinemaListService blitzGetCinemaListService;
	@Autowired BlitzGetMovieListService blitzGetMovieListService;
	@Autowired BlitzGetShowDateListService blitzGetShowDateListService;
	@Autowired BlitzGetSeatInformationService blitzGetSeatInformationService;
	@Autowired BlitzSourceOfFundService blitzSourceOfFundService;
	
	// Manage Beneficiary Module (MB2)
	@Autowired ManageBeneficiaryGetListService manageBeneficiaryGetListService;
	@Autowired ManageBeneficiaryViewBeneficiaryService manageBeneficiaryViewBeneficiaryService;
	@Autowired ManageBeneficiaryDeleteBeneficiaryService manageBeneficiaryDeleteBeneficiaryService;
	@Autowired ManageBeneficiaryUpdateBeneficiaryService manageBeneficiaryUpdateBeneficiaryService;
	@Autowired ManageBeneficiaryCheckBeneficiaryService manageBeneficiaryCheckBeneficiaryService;
	@Autowired ManageBeneficiaryCheckPayeeService manageBeneficiaryCheckPayeeService;
	@Autowired ManageBeneficiarySaveBeneficiaryService manageBeneficiarySaveBeneficiaryService;
	@Autowired ManageBeneficiaryCheckCustomerIdService manageBeneficiaryCheckCustomerIdService;
	@Autowired ManageBeneficiarySavePayeeService manageBeneficiarySavePayeeService;
	@Autowired ManageBeneficiaryViewPayeeService manageBeneficiaryViewPayeeService;
	@Autowired ManageBeneficiaryUpdatePayeeService manageBeneficiaryUpdatePayeeService;
	@Autowired ManageBeneficiaryDeletePayeeService manageBeneficiaryDeletePayeeService;
	@Autowired ManageBeneficiarySMSTokenService manageBeneficiarySMSTokenService;
	@Autowired ManageBeneficiaryJudgeBankService manageBeneficiaryJudgeBankService;
	
	// Telegraphic Transfer Module
	@Autowired TTRequestSourceOfFundService ttRequestSourceOfFundService;
	@Autowired TTRequestSavedRecipientService ttRequestSavedRecipientService;
	@Autowired TTValidateSwiftCodeService ttValidateSwiftCodeService;
	@Autowired TTRequestBankCountrySelectionService ttRequestBankCountrySelectionService;
	@Autowired TTRequestBankCitySelectionService ttRequestBankCitySelectionService;
	@Autowired TTRequestBankListService ttRequestBankListService;
	@Autowired TTRequestRecipientInfoTransferInfoService ttRequestRecipientInfoTransferInfoService;
	@Autowired TTPerformTrxnValidationService ttPerformTrxnValidationService;
	@Autowired TTPerformAcknowledgementService ttPerformAcknowledgementService;
	@Autowired TTCalculateAmountService ttCalculateAmountService;
	@Autowired TTDeleteBeneficiaryService ttDeleteBeneficiaryService;
	@Autowired TTSaveBeneficiaryService ttSaveBeneficiaryService;
	
	// Opening New Account (MB2)
	@Autowired ONATnCService onaTnCService;
	@Autowired ONARetrieveProductListService onaRetrieveProductListService;
	@Autowired ONARetrieveCurrencySelectionService onaRetrieveCurrencySelectionService;
	@Autowired ONARetrieveParamsService onaRetrieveParamsService;
	@Autowired ONAInfoInputService onaInfoInputService;
	@Autowired ONARetrieveDocListService onaRetrieveDocListService;
	@Autowired ONAKTPNIKInputService onaKTPNIKInputService;
	@Autowired ONACreditCardSelectionService onaCreditCardSelectionService;
	@Autowired ONARetrieveBankListService onaRetrieveBankListService;
	@Autowired ONARetrieveBankBranchService onaRetrieveBankBranchService;
	@Autowired ONACheckPostalCodeService onaCheckPostalCodeService;
	@Autowired ONASearchPostalCodeService onaSearchPostalCodeService;
	@Autowired ONACreateAccountDLService onaCreateAccountDLService;
	@Autowired ONACacheUserInputService onaCacheUserInputService;
	@Autowired ONARetrieveCacheUserInputService onaRetrieveCacheUserInputService;
	@Autowired ONARemoveCacheUserInputService onaRemoveCacheUserInputService;
	@Autowired ONARetrieveProductImageService onaRetrieveProductImageService;
	@Autowired ONAInstallmentCalculatorService onaInstallmentCalculatorService;
	@Autowired ONARetrieveOCRImageService onaRetrieveOCRImageService;
	@Autowired ONASubmitOCRImageToOMNIService onaSubmitOCRImageToOMNIService;
	@Autowired ONASubmitOCRImageService onaSubmitOCRImageService;
	@Autowired ONAInquiryDukcapilTimeOutService onaInquiryDukcapilTimeOutService;
	@Autowired ONASubmitEditedDataDukcapilService onaSubmitEditedDataDukcapilService;
	@Autowired ONACreateAccountFNService onaCreateAccountFNService;
	@Autowired ONATakaStep1Service onaTakaStep1Service;
	@Autowired ONATakaStep2Service onaTakaStep2Service;
	@Autowired ONATakaInstallmentCalculatorService onaTakaInstallmentCalculatorService;
	@Autowired ONATakaCacheDataService onaTakaCacheDataService;
	@Autowired ONATakaConfirmationService onaTakaConfirmationService;
	@Autowired ONATakaAcknowledgementService onaTakaAcknowledgementService;
	@Autowired ONATanda360Step1Service onaTanda360Step1Service;
	@Autowired ONATanda360ConfirmationService onaTanda360ConfirmationService;
	@Autowired ONATanda360AcknowledgementService onaTanda360AcknowledgementService;
	@Autowired ONAGiroStep1Service onaGiroStep1Service;
	@Autowired ONAGiroConfirmationService onaGiroConfirmationService;
	@Autowired ONAGiroAcknowledgementService onaGiroAcknowledgementService;
	@Autowired ONATDSyariahStep1Service onaTDSyariahStep1Service;
	@Autowired ONATDSyariahGetInterestPaymentService onaTDSyariahGetInterestPaymentService;
	@Autowired ONATDSyariahConfirmationService onaTDSyariahConfirmationService;
	@Autowired ONATDSyariahAcknowledgementService onaTDSyariahAcknowledgementService;
	@Autowired ONACreditCardAndLoanService onaCreditCardAndLoanService;
	@Autowired ONARetrieveCreditCardListService onaRetrieveCreditCardListService;
	@Autowired ONATakaHadiahStep1Service onaTakaHadiahStep1Service;
	@Autowired ONATakaHadiahConfirmationService onaTakaHadiahConfirmationService;
	@Autowired ONATakaHadiahAcknowledgementService onaTakaHadiahAcknowledgementService;
	@Autowired ONATandaHoldStep1Service onaTandaHoldStep1Service;
	@Autowired ONATandaHoldConfirmationService onaTandaHoldConfirmationService;
	@Autowired ONATandaHoldAcknowledgementService onaTandaHoldAcknowledgementService;
	@Autowired ONAOfflineSyariahService onaOfflineSyariahService;
	
	// EPI Customer Profile (MB2)
	@Autowired EPIRetrieveCustomerProfileService epiRetrieveCustomerProfileService;
	@Autowired EPIRetrieveSelectionInformationService epiRetrieveSelectionInformationService;
	@Autowired EPIRetrieveAddressInformationService epiRetrieveAddressInformationService;
	@Autowired EPIEditCustomerProfileService epiEditCustomerProfileService;
	@Autowired EPIFilterSelectionService epiFilterSelectionService;
	
	// Forex Module
	@Autowired ForexIsAllowedToProceedService forexIsAllowedToProceedService;
	@Autowired ForexTnCService forexTnCService;
	@Autowired ForexGetCurrencyRateListService forexGetCurrencyRateListService;
	@Autowired ForexGetExchangeRateService forexGetExchangeRateService;
	@Autowired ForexConfirmationService forexConfirmationService;
	@Autowired ForexAcknowledgementService forexAcknowledgementService;
	
	// General Module
	@Autowired StoreOCRImageService storeOCRImageService;
	@Autowired RetrieveOCRImageService retrieveOCRImageService;
	@Autowired SearchPostalCodeService searchPostalCodeService;
	@Autowired RetrieveTnCService retrieveTnCService;
	@Autowired RetrieveCacheService retrieveCacheService;
	@Autowired RetrieveHelpInfoService retrieveHelpInfoService;
	@Autowired StoreImageService storeImageService;
	@Autowired RetrieveImageService retrieveImageService;
	
	
	// Pre Approved Loan (PAL)
	@Autowired PALStep1Service palStep1Service;
	@Autowired PALStep2Service palStep2Service;
	@Autowired PALSubmitOCRImageToOMNIService palSubmitOCRImageToOMNIService;
	@Autowired PALInquiryCheckNIKService palInquiryCheckNIKService;
	@Autowired PALConfirmationService palConfirmationService;
	@Autowired PALInstallmentCalculatorService palInstallmentCalculatorService;
	@Autowired PALSubmitEditedDataDukcapilService palSubmitEditedDataDukcapilService;
	@Autowired PALCompareAddressService palCompareAddressService;
	@Autowired StorePALCacheService storePALCacheService;
	
	//QR Pay
	@Autowired QRPayGetAccountListService qrPayGetAccountListService;
	@Autowired QRPayGetAccountService qrPayGetAccountService;
	@Autowired QRPaySetAccountService qrPaySetAccountService;
	@Autowired QRPayInquiryService qrPayInquiryService;
	@Autowired QRPayConfirmationService qrPayConfirmationService;
	@Autowired QRPayAcknowledgementService qrPayAcknowledgementService;
	
	//Poin Seru
	@Autowired PoinSeruStoreService poinSeruStoreService;
	
	//NTI
	@Autowired NTIStep1Service ntiStep1Service;
	@Autowired NTIAcknowledgementService ntiAcknowledgementService;
	@Autowired NTIInquiryCheckNIKService ntiInquiryCheckNIKService;
	@Autowired NTILoadQuestionService ntiLoadQuestionService;
	@Autowired NTISubmitImageToOmniService ntiSubmitImageToOmniService;
	@Autowired NTIValidateService ntiValidateService;
	@Autowired StoreNTICacheService storeNTICacheService;
	
	//Login Binding
	@Autowired OmniLoginBindingService omniLoginBindingService;
	
	//Primary bond
	@Autowired PrimaryBondStep1Service primaryBondStep1Service;
	@Autowired PrimaryBondStep2Service primaryBondStep2Service;
	@Autowired PrimaryBondInquiryRegisterInvestorService primaryBondInquiryRegisterInvestorService;
	@Autowired PrimaryBondSaveRegisterInvestorService primaryBondSaveRegisterInvestorService;
	@Autowired PrimaryBondOrderConfirmationService primaryBondOrderConfirmationService;
	@Autowired PrimaryBondOrderAcknowledgementService primaryBondOrderAcknowledgementService;
	@Autowired PrimaryBondInquiryOrderHistoryService primaryBondInquiryOrderHistoryService;
	@Autowired PrimaryBondEarlyRedemptionConfirmationService primaryBondEarlyRedemptionConfirmationService;
	@Autowired PrimaryBondEarlyRedemptionAcknowledgementService primaryBondEarlyRedemptionAcknowledgementService;
	
	public MICBResponseBean onCall(MICBRequestBean arg0) {


		MICBResponseBean responseBean = new MICBResponseBean();
		try
		{
			MICBRequestBean micbRequestBean = arg0;

			log.info("MiB Listerner onCall :: [ "+micbRequestBean.getHeaderBean().getServiceID()+" ]");
			
			if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_LOGIN))
			{
				//performLogin = new performLogin(dao);
				responseBean = performLogin.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_RETRIEVE_DEVICEPROFILES))
			{
				responseBean = retrieveDeviceProfiles.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_DEVICETAC))
			{
				responseBean = requestTAC.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_LOGOUT))
			{
				//performLogin = new performLogin(dao);
				responseBean = performLogout.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_REQUEST_INFO))
			{
				responseBean = accessInfo.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_REQUEST_SESSION))
			{
				responseBean = sessionInfo.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_APP_STAT_INFO))
			{
				responseBean = retrieveAppStatInfo.acceptProcess(micbRequestBean);
			}	
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MBK_SECTOKEN))
			{
				responseBean = retrieveSecurityToken.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_RET_ACC_POINT))
			{
				responseBean = 	retrieveAccountPointService.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_RET_ACC_POINT2))
			{
				responseBean = 	retrieveAccountPointWithoutHistoryService.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_INTRO_PAGE))
			{
				responseBean = 	retrieveIntroPage.acceptProcess(micbRequestBean);
			}
			// Registration Module
			else if(micbRequestBean.getHeaderBean().getServiceID().startsWith("FUNC_REG_"))
			{
				responseBean = processRegistrationModule(micbRequestBean);
			}
			// Future Transaction Module
			else if(micbRequestBean.getHeaderBean().getServiceID().startsWith("FUNC_FUTURE_"))
			{
				responseBean = processFutureModule(micbRequestBean);
			}
			// Account Maintenance Module
			else if(micbRequestBean.getHeaderBean().getServiceID().startsWith("FUNC_ACC_MAIN_"))
			{
				responseBean = processAccountMaintenanceModule(micbRequestBean);
			}
			// Account Overview Module
			else if(micbRequestBean.getHeaderBean().getServiceID().startsWith("FUNC_ACC_"))
			{
				responseBean = processAccountOverviewModule(micbRequestBean);
			}
			// Internet Transaction History Module
			else if(micbRequestBean.getHeaderBean().getServiceID().startsWith("FUNC_INT_TRANS_"))
			{
				responseBean = processInternetTransactionHistoryModule(micbRequestBean);
			}
			// eStatement Module
			else if(micbRequestBean.getHeaderBean().getServiceID().startsWith("FUNC_ESTATEMENT_"))
			{
				responseBean = processEstatementModule(micbRequestBean);
			}
			// OCBC Fund Transfer Module
			else if(micbRequestBean.getHeaderBean().getServiceID().startsWith("FUNC_OCBC_FT_"))
			{
				responseBean = processOCBCFTModule(micbRequestBean);
			}
			// Fund Transfer Module
			else if(micbRequestBean.getHeaderBean().getServiceID().startsWith("FUNC_FT_"))
			{
				responseBean = processFundTransferModule(micbRequestBean);
			}
			// Payment & Purchase Module
			else if(micbRequestBean.getHeaderBean().getServiceID().startsWith("FUNC_PAYMENT_"))
			{
				responseBean = processPaymentPurchaseModule(micbRequestBean);
			}
			// Manage Payment & Purchase Module
			else if(micbRequestBean.getHeaderBean().getServiceID().startsWith("FUNC_PP_"))
			{
				responseBean = processManagePaymentPurchaseModule(micbRequestBean);
			}
			// Upload Profile Module
			else if(micbRequestBean.getHeaderBean().getServiceID().startsWith("FUNC_UPM_"))
			{
				responseBean = processUPModule(micbRequestBean);
			}
			// Blitz Module
			else if(micbRequestBean.getHeaderBean().getServiceID().startsWith("FUNC_BLITZ_"))
			{
				responseBean = processBlitzModule(micbRequestBean);
			}
			// Manage Beneficiary Module
			else if(micbRequestBean.getHeaderBean().getServiceID().startsWith("FUNC_MANAGE_BENE_"))
			{
				responseBean = processManageBeneficiaryModule(micbRequestBean);
			}
			// OCBC Unit Trust Module
			else if(micbRequestBean.getHeaderBean().getServiceID().startsWith("FUNC_OCBC_UT_"))
			{
				responseBean = processOCBCUTModule(micbRequestBean);
			}
			// Unit Trust Module
			else if(micbRequestBean.getHeaderBean().getServiceID().startsWith("FUNC_UT_"))
			{
				responseBean = processUnitTrustModule(micbRequestBean);
			}
			// Time Deposit (TD) Module
			else if(micbRequestBean.getHeaderBean().getServiceID().startsWith("FUNC_TIME_DEPOSIT_") ||
					micbRequestBean.getHeaderBean().getServiceID().startsWith("FUNC_TD_"))
			{
				responseBean = processTimeDepositModule(micbRequestBean);
			}
			// Manage Payee Module
			else if(micbRequestBean.getHeaderBean().getServiceID().startsWith("FUNC_PAYEE_"))
			{
				responseBean = processPayeeModule(micbRequestBean);
			}
			// Smart Notification Module
			else if(micbRequestBean.getHeaderBean().getServiceID().startsWith("FUNC_SMART_NOT_"))
			{
				responseBean = processSmartNotificationModule(micbRequestBean);
			}
			// Eagle Eye Module
			else if(micbRequestBean.getHeaderBean().getServiceID().startsWith("FUNC_OCBC_EAGLE_EYE_"))
			{
				responseBean = processEagleEyeModule(micbRequestBean);
			}
			// Telegraphic Transfer (TT) Module
			else if(micbRequestBean.getHeaderBean().getServiceID().startsWith("FUNC_TT_"))
			{
				responseBean = processTTModule(micbRequestBean);
			}
			// Edit Profile Information (EPI) Module
			else if(micbRequestBean.getHeaderBean().getServiceID().startsWith("FUNC_EPI_"))
			{
				responseBean = processEPIModule(micbRequestBean);
			}
			// AOB Module
			else if(micbRequestBean.getHeaderBean().getServiceID().startsWith("FUNC_AO_"))
			{
				responseBean = processAOBModule(micbRequestBean);
			}
			// ONA Module
			else if(micbRequestBean.getHeaderBean().getServiceID().startsWith("FUNC_ONA_"))
			{
				responseBean = processONAModule(micbRequestBean);
			}
			// Forex Module
			else if(micbRequestBean.getHeaderBean().getServiceID().startsWith("FUNC_FOREX_"))
			{
				responseBean = processForexModule(micbRequestBean);
			}
			// Ext Module
			else if(micbRequestBean.getHeaderBean().getServiceID().startsWith("FUNC_EXT_"))
			{
				responseBean = processExtModule(micbRequestBean);
			}
			// Gen Module
			else if(micbRequestBean.getHeaderBean().getServiceID().startsWith("FUNC_GEN_"))
			{
				responseBean = processGeneralModule(micbRequestBean);
			}
			// PAL Module
			else if(micbRequestBean.getHeaderBean().getServiceID().startsWith("FUNC_PAL_"))
			{
				responseBean = processPreApprovedLoanModule(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().startsWith("FUNC_QR_PAY"))
			{
				responseBean = processQRPayModule(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().startsWith("FUNC_NTI"))
			{
				responseBean = processNTIModule(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().startsWith("FUNC_PB"))
			{
				responseBean = processPrimaryBondModule(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_GET_DB_ACC_REG_PAYEE_CC))
			{
				responseBean = getAvailableAcctRegPayeeList.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_GET_DB_ACC_REG_PAYEE))
			{
				responseBean = retrieveDebitAcctRegisteredPayee.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_GET_CC))
			{
				responseBean = retrieveCreditCard.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_GET_REG_PAYEE))
			{
				responseBean = retrieveRegisteredPayee.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_RETRIEVE_PAYEE_TYPE_ORG))
			{
				responseBean = retrieveBillersInfo.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_RETRIEVE_BILL_INFO_NON_REG))
			{
				responseBean = retrieveBillInfoNonRegistered.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_RETRIEVE_BILL_INFO_REG))
			{
				responseBean = retrieveBillInfoRegistered.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_RETRIEVE_BILL_INFO_CC))
			{
				responseBean = retrieveBillInfoCreditCard.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_VALIDATE_PAYMENT_INFO))
			{
				responseBean = validatePaymentInformation.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_SUBMIT_PP_TRXN_NON_REG))
			{
				responseBean = submitPaymentPurchaseConfirmNonRegistered.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_SUBMIT_PP_TRXN_REG))
			{
				responseBean = submitPaymentPurchaseConfirmRegistered.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_SUBMIT_PP_TRXN_CC))
			{
				responseBean = submitPaymentPurchaseConfirmCreditCard.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_REQUEST_TOKEN_PP))
			{
				responseBean = requestSMSTokenForPaymentPurchase.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_REQ_PP_TRXN_ACK_NON_REG))
			{
				responseBean = requestPaymentPurchaseAckNonRegistered.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_REQ_PP_TRXN_ACK_REG))
			{
				responseBean = requestPaymentPurchaseAckRegistered.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_REQ_PP_TRXN_ACK_CC))
			{
				responseBean = requestPaymentPurchaseAckCreditCard.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_RETREIEVE_UNBILL_CC))
			{
				responseBean = 	retrieveUnbilledCreditCardStatementService.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_SOFT_TOKEN_STEP1))
			{
				responseBean = 	softTokenStep1Service.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_SOFT_TOKEN_STEP2))
			{
				responseBean = 	softTokenStep2Service.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_GET_OWN_UNIT_TRUST))
			{
				responseBean = retrieveOwnUnitTrustSummary.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_LIST_UNIT_TRUST))
			{
				responseBean = browseUnitTrustList.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_UPDATE_FAV_PAYEE))
			{
				responseBean = updateFavouriteRegisteredPayee.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_BENEFICIARY_LIST))
			{
				responseBean = manageBeneficiaryModulesListService.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_BENEFICIARY_DELETE))
			{
				responseBean = manageBeneficiaryModulesDeleteService.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_OCBC_UPLOAD_CUST_PROF))
			{
				responseBean = uploadCustProfile.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MNG_RECUR_LIST))
			{
				responseBean = retrieveRecurringListService.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_GET_RECUR_DETAILS))
			{
				responseBean = retrieveRecurringTransferDetailsService.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_DELETE_RECUR_TRXN))
			{
				responseBean = deleteTrxnForSelectedRecurringItemService.acceptProcess(micbRequestBean);
			}	
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MNG_AUTODEBIT_LIST))
			{
				responseBean = retrieveAutodebitListService.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_GET_AUTODEBIT_DETAILS))
			{
				responseBean = retrieveAutodebitDetailsService.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_DELETE_AUTODEBIT_TRXN))
			{
				responseBean = deleteTrxnForSelectedAutodebitItemService.acceptProcess(micbRequestBean);
			}	
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_BENEFICIARY_VIEW))
			{
				responseBean = 	manageBeneficiaryModulesViewService.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_POIN_SERU_STORE))
			{
				responseBean = poinSeruStoreService.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_LOGIN_OMNI_BINDING))
			{
				responseBean = omniLoginBindingService.acceptProcess(micbRequestBean);
			}
			else
			{
				ObResponse obResponse = new ObResponse();
				obResponse.setObHeader(new ObHeaderResponse());
				obResponse.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_FUNC_ERROR);
				responseBean.setBDObject(obResponse);
			}	
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.info(this.getClass().toString(), e);
			ObResponse obResponse = new ObResponse();
			obResponse.setObHeader(new ObHeaderResponse());
			obResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			responseBean.setBDObject(obResponse);
		}
		return responseBean;
	}	
	
	private MICBResponseBean processNTIModule(MICBRequestBean micbRequestBean) {
		MICBResponseBean responseBean = null;

		if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_NTI_STEP1))
			responseBean = ntiStep1Service.acceptProcess(micbRequestBean);
		if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_NTI_ACKNOWLEDGEMENT))
			responseBean = ntiAcknowledgementService.acceptProcess(micbRequestBean);
		if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_NTI_INQ_NIK))
			responseBean = ntiInquiryCheckNIKService.acceptProcess(micbRequestBean);
		if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_NTI_LOAD_QUESTION))
			responseBean = ntiLoadQuestionService.acceptProcess(micbRequestBean);
		if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_NTI_SUBMIT_IMAGE_TO_OMNI))
			responseBean = ntiSubmitImageToOmniService.acceptProcess(micbRequestBean);
		if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_NTI_VALIDATE))
			responseBean = ntiValidateService.acceptProcess(micbRequestBean);
		if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_NTI_STORE_CACHE))
			responseBean = storeNTICacheService.acceptProcess(micbRequestBean);

		return responseBean;
	}

	// Manage Payee Module
	private MICBResponseBean processPayeeModule(MICBRequestBean micbRequestBean)
	{
		MICBResponseBean responseBean = null;
		
		if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_PAYEE_LIST))
		{
			responseBean = loadPayeeTable.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_PAYEE_VIEW))
		{
			responseBean = viewPayeeMgmt.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_PAYEE_DELETE))
		{
			responseBean = deletePayee.acceptProcess(micbRequestBean);
		}
		
		return responseBean;
	}
	
	// Smart Notification Module
	private MICBResponseBean processSmartNotificationModule(MICBRequestBean micbRequestBean)
	{
		MICBResponseBean responseBean = null;
		
		if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_SMART_NOT_SETUP_LIST))
		{
			responseBean = smartNotificationSetupListService.acceptProcess(micbRequestBean);
		}		
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_SMART_NOT_SETUP_SAVE))
		{
			responseBean = smartNotificationSetupSaveService.acceptProcess(micbRequestBean);
		}
		
		return responseBean;
	}
	
	// Eagle Eye Module
	private MICBResponseBean processEagleEyeModule(MICBRequestBean micbRequestBean)
	{
		MICBResponseBean responseBean = null;
		
		if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_EAGLE_EYE_LIST))
		{
			responseBean = 	performInquiryEagleEyeService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_EAGLE_EYE_DETAILS))
		{
			responseBean = 	performInquiryEagleEyeDetailsService.acceptProcess(micbRequestBean);
		}
		
		return responseBean;
	}
	
	// Registration Module
	private MICBResponseBean processRegistrationModule(MICBRequestBean micbRequestBean)
	{
		MICBResponseBean responseBean = null;
		
		if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_REG_RETRIEVE_TNC))
		{
			responseBean = registrationRetrieveTnCService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_REG_RETRIEVE_CAPTCHA))
		{
			responseBean = registrationRetrieveCAPTCHAService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_REG_VALIDATE_EGBLT))
		{
			responseBean = registrationValidateEligibilitiesService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_REG_VALIDATE_EGBLT2))
		{
			responseBean = registrationValidateEligibilitiesService2.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_REG_USERNAME))
		{
			responseBean = registrationUsernameService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_REG_SUBMIT_PIN))
		{
			responseBean = registrationSubmitPINService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_REG_START_SESSION))
		{
			responseBean = 	registrationStartSessionService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_REG_REFRESH_SESSION))
		{
			responseBean = 	registrationRefreshSessionService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_REG_CONFIRMATION))
		{
			responseBean = 	registrationConfirmationService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_REG_RETRIEVE_REGPIN))
		{
			responseBean = 	registrationRetrieveRegisterPinService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_REG_CHANGE_PASSWORD))
		{
			responseBean = 	registrationChangePasswordService.acceptProcess(micbRequestBean);
		}	
		
		return responseBean;
	}
	
	// Future Transaction Module
	private MICBResponseBean processFutureModule(MICBRequestBean micbRequestBean)
	{
		MICBResponseBean responseBean = null;
		
		if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_FUTURE_TRANSFER_LIST))
		{
			responseBean = futureTransferListService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_FUTURE_TRANSFER_DETAILS))
		{
			responseBean = futureTransferDetailsService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_FUTURE_TRANSFER_DELETE))
		{
			responseBean = 	futureTransferDeleteService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_FUTURE_PAYMENT_LIST))
		{
			responseBean = futurePaymentListService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_FUTURE_PAYMENT_DETAILS))
		{
			responseBean = futurePaymentDetailsService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_FUTURE_PAYMENT_DELETE))
		{
			responseBean = 	futurePaymentDeleteService.acceptProcess(micbRequestBean);
		}
		
		return responseBean;
	}
	
	// Account Maintenance Module
	private MICBResponseBean processAccountMaintenanceModule(MICBRequestBean micbRequestBean)
	{
		MICBResponseBean responseBean = null;
		
		if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ACC_MAIN_LIST))
		{
			responseBean = performAccountMaintenanceListService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ACC_MAIN_CONF))
		{
			responseBean = performAccountMaintenanceConfService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ACC_MAIN_SMS))
		{
			responseBean = performAccountMaintenanceSmsService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ACC_MAIN_ACKGT))
		{
			responseBean = performAccountMaintenanceAckgtService.acceptProcess(micbRequestBean);
		}
		
		return responseBean;
	}
	
	// Account Overview Module
	private MICBResponseBean processAccountOverviewModule(MICBRequestBean micbRequestBean)
	{
		MICBResponseBean responseBean = null;
		
		if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ACC_OVERVIEW))
		{
			responseBean = retrieveAccountOverview.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ACC_DASHBOARD))
		{
			responseBean = retrieveAccountOverview2.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ACC_AUTO_RENEWAL))
		{
			responseBean = configureAutoRenewal.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ACC_CCY_COMPOSITION))
		{
			responseBean = browseCurrencyComposition2.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ACC_LIST_INSURANCE))
		{
			responseBean = browseInsuranceList.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ACC_TRANS_HIST))
		{
			responseBean = retrieveTransactionHistory.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ACC_TRANS_HIST_2))
		{
			responseBean = retrieveTransactionHistory2.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ACC_TRANS_PDF))
		{
			responseBean = retrieveTransactionHistoryPDFService.acceptProcess(micbRequestBean);
		}
		
		return responseBean;
	}
	
	// Internet Transaction History Module
	private MICBResponseBean processInternetTransactionHistoryModule(MICBRequestBean micbRequestBean)
	{
		MICBResponseBean responseBean = null;
		
		if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_INT_TRANS_HIS_LIST))
		{
			responseBean = internetTransactionHistoryListService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_INT_TRANS_HIS_SEARCH))
		{
			responseBean = internetTransactionHistorySearchService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_INT_TRANS_HIS_DETAIL))
		{
			responseBean = internetTransactionHistoryDetailService.acceptProcess(micbRequestBean);
		}		
		
		return responseBean;
	}
	
	// Estatement Module
	private MICBResponseBean processEstatementModule(MICBRequestBean micbRequestBean)
	{
		MICBResponseBean responseBean = null;
		
		if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ESTATEMENT_CC_PDF))
		{
			responseBean = estatmentCreditCardPDFService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ESTATEMENT_PDF))
		{
			responseBean = estatmentPDFService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ESTATEMENT_LIST))
		{
			responseBean = retrieveEstatementListService.acceptProcess(micbRequestBean);
		}
		
		return responseBean;
	}

	// OCBC Fund Transfer Module
	private MICBResponseBean processOCBCFTModule(MICBRequestBean micbRequestBean)
	{
		MICBResponseBean responseBean = null;
		
		if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_OCBC_FT_LIST))
		{
			responseBean = performFundTransferGetListService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_OCBC_FT_OWN_LIST))
		{
			responseBean = performFundTransferOwnGetListService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_OCBC_FT_OWN_CONF))
		{
			responseBean = performFundTransferOwnConfService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_OCBC_FT_INTRA_CONF))
		{
			responseBean = performFundTransferIntraConfService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_OCBC_FT_OWN_VAL))
		{
			responseBean = performFundTransferOwnValidateService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_OCBC_FT_REQUEST_SMS_TOKEN))
		{
			responseBean = requestSMSTokenService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_OCBC_FT_OWN_ACK))
		{
			responseBean = performFundTransferOwnAckService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_OCBC_FT_INTRA_VAL))
		{
			responseBean = performFundTransferIntraValidateService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_OCBC_FT_INTRA_ACK))
		{
			responseBean = performFundTransferIntraAckService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_OCBC_FT_INTER_VAL))
		{
			responseBean = performFundTransferInterValidateService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_OCBC_FT_INTER_CONF))
		{
			responseBean = performFundTransferInterConfService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_OCBC_FT_INTER_ACK))
		{
			responseBean = performFundTransferInterAckService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_OCBC_FT_LIMIT_EXCHANGE))
		{
			responseBean = performFundTransferLimitExchangeRate.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_OCBC_FT_FAV_BENE))
		{
			responseBean = 	performFundTransferUpdateFavBeneficiary.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_OCBC_FT_LIST2))
		{
			responseBean = 	performFundTransferGetList2Service.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_OCBC_FT_VAL))
		{
			responseBean = 	performFundTransferValidateService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_OCBC_FT_CONFIRMATION))
		{
			responseBean = 	performFTConfirmationService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_OCBC_FT_ACKG))
		{
			responseBean = 	performFTAckService.acceptProcess(micbRequestBean);
		}
		
		return responseBean;
	}
	
	// Fund Transfer Module
	private MICBResponseBean processFundTransferModule(MICBRequestBean micbRequestBean)
	{
		MICBResponseBean responseBean = null;
		
		if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_FT_TRANS_SERVICE_LIST))
		{
			responseBean = 	fundTransferTransferServiceListService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_FT_CART_TOTAL_CALCULATOR))
		{
			responseBean = 	fundTransferCartTotalCalculatorService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_FT_FAV_BENE))
		{
			responseBean = 	fundTransferUpdateFavBeneficiaryService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_FT_SCHEDULE_TRANSFER_LIST))
		{
			responseBean = 	ftLoadScheduledTrxnListService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_FT_FUTURE_SCHD_TRX_DETAIL))
		{
			responseBean = 	ftGetFutureScheduledTrxnDetailService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_FT_CANCEL_SCHEDULE_TRANS))
		{
			responseBean = 	ftCancelFutureTransferService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_FT_RECURR_SCHD_TRX_DETAIL))
		{
			responseBean = 	ftGetRecurringScheduledTrxnDetailService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_FT_CANCEL_REC_SCHED_TRANS))
		{
			responseBean = 	ftDeleteRecurringScheduledTrxnService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_FT_TERM_AND_CONDITION))
		{
			responseBean = 	fundTransferTermAndConditionService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_FT_SOURCE_FUND))
		{
			responseBean = 	fundTransferSourceOfFundService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_FT_SAVED_RECIPIENTS))
		{
			responseBean = 	fundTransferSavedRecipientListService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_FT_BANK_LIST))
		{
			responseBean = 	fundTransferBankListService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_FT_TRX_LIMIT))
		{
			responseBean = 	fundTransferTransactionLimitService.acceptProcess(micbRequestBean);
		}
		
		return responseBean;
	}
	
	// Payment & Purchase Module
	private MICBResponseBean processPaymentPurchaseModule(MICBRequestBean micbRequestBean)
	{
		MICBResponseBean responseBean = null;
		
		if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_PAYMENT_SOURCE_FUND))
		{
			responseBean = 	paymentSourceOfFundService.acceptProcess(micbRequestBean);
		}else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_PAYMENT_REGISTERED_BILLER))
		{
			responseBean = 	paymentRegisteredBillerListService.acceptProcess(micbRequestBean);
		}else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_PAYMENT_UNREGISTERED_BILLER))
		{
			responseBean = 	paymentUnRegisteredBillerListService.acceptProcess(micbRequestBean);
		}else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_PAYMENT_INQUIRY_TRXNLIMIT))
		{
			responseBean = 	paymentInquiryTransactionLimitService.acceptProcess(micbRequestBean);
		}else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_PAYMENT_INQUIRY_BILL_INFO))
		{
			responseBean = 	paymentInquiryBillerInfoService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_PAYMENT_CONFIRMATION))
		{
			responseBean = paymentConfirmationService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_PAYMENT_SMS_TOKEN))
		{
			responseBean = paymentRequestSMSTokenService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_PAYMENT_ACKNOWLEDGEMENT))
		{
			responseBean = paymentAcknowledgementService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_PAYMENT_UPDATE_FAV_PAYEE))
		{
			responseBean = paymentUpdateFavouritePayeeService.acceptProcess(micbRequestBean);
		}
		
		return responseBean;
	}
	
	// Manage Payment & Purchase Module
	private MICBResponseBean processManagePaymentPurchaseModule(MICBRequestBean micbRequestBean)
	{
		MICBResponseBean responseBean = null;
		
		if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_PP_SCHEDULED_TRX_LIST))
		{
			responseBean = 	ppLoadScheduledTrxnListService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_PP_SCHEDULED_TRX_DETAIL))
		{
			responseBean = 	ppLoadScheduledTrxnDetailService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_PP_CANCEL_FUTURE_TRX))
		{
			responseBean = 	ppCancelFutureTransferService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_PP_DELETE_RECUR_TRX))
		{
			responseBean = 	ppDeleteRecurringScheduledTrxService.acceptProcess(micbRequestBean);
		}
		
		return responseBean;
	}
	
	// Upload Profile Module
	private MICBResponseBean processUPModule(MICBRequestBean micbRequestBean)
	{
		MICBResponseBean responseBean = null;
		
		if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_UPM_CHANGE_PASSWORD))
		{
			responseBean = performChangePasswordService.acceptProcess(micbRequestBean);
		}		
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_UPM_UPDATE_USER_PROFILE))
		{
			responseBean = performUpdateUserProfileService.acceptProcess(micbRequestBean);
		}		
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_UPM_LOAD_USER_PROFILE))
		{
			responseBean = performLoadUserProfileService.acceptProcess(micbRequestBean);
		}	
		
		return responseBean;
	}
	
	// Blitz Module
	private MICBResponseBean processBlitzModule(MICBRequestBean micbRequestBean)
	{
		MICBResponseBean responseBean = null;
		
		if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_BLITZ_GET_AUDI_TYPE))
		{
			responseBean = 	blitzGetAudiTypesService.acceptProcess(micbRequestBean);
		}else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_BLITZ_GET_SCHEDULES))
		{
			responseBean = 	blitzGetSchedulesService.acceptProcess(micbRequestBean);
		}else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_BLITZ_GET_AUDI_NO))
		{
			responseBean = 	blitzGetAudiNoService.acceptProcess(micbRequestBean);
		}else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_BLITZ_GET_CITY))
		{
			responseBean = 	blitzGetCityListService.acceptProcess(micbRequestBean);
		}else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_BLITZ_GET_MOVIE))
		{
			responseBean = 	blitzGetMovieListService.acceptProcess(micbRequestBean);
		}else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_BLITZ_GET_CINEMA))
		{
			responseBean = 	blitzGetCinemaListService.acceptProcess(micbRequestBean);
		}else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_BLITZ_GET_SHOWDATE))
		{
			responseBean = 	blitzGetShowDateListService.acceptProcess(micbRequestBean);
		}else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_BLITZ_GET_SEAT_INFO))
		{
			responseBean = 	blitzGetSeatInformationService.acceptProcess(micbRequestBean);
		}else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_BLITZ_SOURCE_OF_FUND))
		{
			responseBean = 	blitzSourceOfFundService.acceptProcess(micbRequestBean);
		}
		
		return responseBean;
	}
	
	// Manage Beneficiary Module
	private MICBResponseBean processManageBeneficiaryModule(MICBRequestBean micbRequestBean)
	{
		MICBResponseBean responseBean = null;
		
		if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MANAGE_BENE_LIST))
		{
			responseBean = 	manageBeneficiaryGetListService.acceptProcess(micbRequestBean);
		}else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MANAGE_BENE_VIEW))
		{
			responseBean = 	manageBeneficiaryViewBeneficiaryService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MANAGE_BENE_DEL))
		{
			responseBean = 	manageBeneficiaryDeleteBeneficiaryService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MANAGE_BENE_EDIT))
		{
			responseBean = 	manageBeneficiaryUpdateBeneficiaryService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MANAGE_BENE_CHECK_BENE))
		{
			responseBean = 	manageBeneficiaryCheckBeneficiaryService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MANAGE_BENE_SAVE_BENE))
		{
			responseBean = 	manageBeneficiarySaveBeneficiaryService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MANAGE_BENE_CHECK_PAYEE))
		{
			responseBean = 	manageBeneficiaryCheckPayeeService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MANAGE_BENE_CHECK_CUSTID))
		{
			responseBean = 	manageBeneficiaryCheckCustomerIdService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MANAGE_BENE_SAVE_PAYEE))
		{
			responseBean = 	manageBeneficiarySavePayeeService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MANAGE_BENE_VIEW_PAYEE))
		{
			responseBean = 	manageBeneficiaryViewPayeeService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MANAGE_BENE_UPDATE_PAYEE))
		{
			responseBean = 	manageBeneficiaryUpdatePayeeService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MANAGE_BENE_DELETE_PAYEE))
		{
			responseBean = 	manageBeneficiaryDeletePayeeService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MANAGE_BENE_SMS_TOKEN))
		{
			responseBean = 	manageBeneficiarySMSTokenService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MANAGE_BENE_JUDGE_BANK))
		{
			responseBean = 	manageBeneficiaryJudgeBankService.acceptProcess(micbRequestBean);
		}
		
		return responseBean;
	}
	
	// OCBC Unit Trust Module
	private MICBResponseBean processOCBCUTModule(MICBRequestBean micbRequestBean)
	{
		MICBResponseBean responseBean = null;
		
		if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_OCBC_UT_SUBS_LIST))
		{
			responseBean = performUnitTrustSubscriptionList.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_OCBC_UT_PROD_INFO))
		{
			responseBean = retrieveUnitTrustProductInfo.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_OCBC_UT_TERM_TRANS))
		{
			responseBean = retrieveUnitTrustTermTransaction.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_OCBC_UT_SUBS_CONF))
		{
			responseBean = performUnitTrustSubscriptionConf.acceptProcess(micbRequestBean);
		}	
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_OCBC_UT_SUBS_ACK))
		{
			responseBean = performUnitTrustSubscriptionAck.acceptProcess(micbRequestBean);
		}	
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_OCBC_UT_SWITCH_LIST))
		{
			responseBean = performUnitTrustSwitchingList.acceptProcess(micbRequestBean);
		}	
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_OCBC_UT_SWITCH_CONF))
		{
			responseBean = performUnitTrustSwitchingConf.acceptProcess(micbRequestBean);
		}	
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_OCBC_UT_SWITCH_ACK))
		{
			responseBean = performUnitTrustSwitchingAck.acceptProcess(micbRequestBean);
		}	
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_OCBC_UT_REDEMP_LIST))
		{
			responseBean = performUnitTrustRedemptionList.acceptProcess(micbRequestBean);
		}	
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_OCBC_UT_REDEMP_CONF))
		{
			responseBean = performUnitTrustRedemptionConf.acceptProcess(micbRequestBean);
		}	
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_OCBC_UT_REDEMP_ACK))
		{
			responseBean = performUnitTrustRedemptionAck.acceptProcess(micbRequestBean);
		}	
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_OCBC_UT_REQUEST_SMS_TOKEN))
		{
			responseBean = requestSMSTokenForUnitTrust.acceptProcess(micbRequestBean);
		}	
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_OCBC_UT_SUBS_NEW_VAL))
		{
			responseBean = performUnitTrustSubscriptionValidateNew.acceptProcess(micbRequestBean);
		}	
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_OCBC_UT_SUBS_NEW_LIST))
		{
			responseBean = retrieveUnitTrustSubscriptionNewList.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_OCBC_UT_SUBS_PROD_LIST))
		{
			responseBean = retrieveUnitTrustSubscriptionProductList.acceptProcess(micbRequestBean);
		}	
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_OCBC_UT_TNC))
		{
			responseBean = requestTnCForUnitTrustService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_OCBC_UT_SWITCH_PROD_LIST))
		{
			responseBean = retrieveUnitTrustSwitchingProductList.acceptProcess(micbRequestBean);
		}	
		
		return responseBean;
	}
	
	// Unit Trust Module
	private MICBResponseBean processUnitTrustModule(MICBRequestBean micbRequestBean)
	{
		MICBResponseBean responseBean = null;
		
		if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_UT_NEW_PRODUCT_LIST))
		{
			responseBean = 	unitTrustAllProductListService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_UT_SUBSCIRBE_CONF))
		{
			responseBean = 	unitTrustSubscribeConfirmationService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_UT_SUBSCIRBE_ACK))
		{
			responseBean = 	unitTrustSubscribeAcknowledgementService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_UT_SMS_TOKEN))
		{
			responseBean = 	unitTrustSMSTokenService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_UT_REDEEM_PROD_LIST))
		{
			responseBean = 	unitTrustRedeemProductListService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_UT_REDEEM_CONF))
		{
			responseBean = 	unitTrustRedeemConfirmationService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_UT_REDEEM_ACK))
		{
			responseBean = 	unitTrustRedeemAcknowledgementService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_UT_SWITCH_FROM_LIST))
		{
			responseBean = 	unitTrustSwitchFromListService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_UT_SWITCH_TO_LIST))
		{
			responseBean = 	unitTrustSwitchToListService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_UT_SWITCH_CONF))
		{
			responseBean = 	unitTrustSwitchConfirmationService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_UT_SWITCH_ACK))
		{
			responseBean = 	unitTrustSwitchAcknowledgementService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_UT_NEW_PRODUCT_DETAIL))
		{
			responseBean = 	unitTrustProductDetailService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_UT_OWN_INVEST_SUMMARY))
		{
			responseBean = 	unitTrustOwnInvestmentSummaryService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_UT_OWN_PRODUCT_LIST))
		{
			responseBean = 	unitTrustOwnProductListService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_UT_SOURCE_OF_FUND))
		{
			responseBean = 	unitTrustSourceOfFundService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_UT_OTHER_PRODUCT_LIST))
		{
			responseBean = 	unitTrustOthersProductListService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_UT_GET_EXG_RATE))
		{
			responseBean = 	unitTrustGetExchangeRateService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_UT_QUICK_STEP1))
		{
			responseBean = 	unitTrustQuickStep1Service.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_UT_INSURANCE_QUESTION))
		{
			responseBean = 	unitTrustInsuranceQuestionService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_UT_INSURANCE_CHECKING))
		{
			responseBean = 	unitTrustCheckInsuranceEligibilityService.acceptProcess(micbRequestBean);
		}
		
		return responseBean;
	}
	
	// Time Deposit (TD) Module
	private MICBResponseBean processTimeDepositModule(MICBRequestBean micbRequestBean)
	{
		MICBResponseBean responseBean = null;
		
		if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_TIME_DEPOSIT_LIST))
		{
			responseBean = performTimeDepositListService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_TIME_DEPOSIT_CONF))
		{
			responseBean = performTimeDepositConfService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_TIME_DEPOSIT_SMS))
		{
			responseBean = performTimeDepositSmsService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_TIME_DEPOSIT_ACKGT))
		{
			responseBean = performTimeDepositAckgtService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_TIME_DEPOSIT_SOD))
		{
			responseBean = 	timeDepositSourceOfFundService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_TIME_DEPOSIT_COT))
		{
			responseBean = 	timeDepositCheckCutOfTimeService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_TIME_DEPOSIT_GET_DETAILS))
		{
			responseBean = 	timeDepositGetDetailsService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_TIME_DEPOSIT_OP_CONF))
		{
			responseBean = 	timeDepositOpeningConfirmationService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_TIME_DEPOSIT_OP_SMS_TOKEN))
		{
			responseBean = 	timeDepositOpeningSMSTokenService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_TIME_DEPOSIT_OP_ACK))
		{
			responseBean = 	timeDepositOpeningAcknowledgementService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_TIME_DEPOSIT_GET_TNC))
		{
			responseBean = 	timeDepositTermNConditionService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_TD_REFRESH_SESSION))
		{
			responseBean = 	timeDepositRefreshSessionService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_TD_INTEREST_PAYMENT))
		{
			responseBean = 	timeDepositGetInterestPaymentService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_TD_OPEN_NEW_ACC_LIST))
		{
			responseBean = 	timeDepositOpenNewAccountListService.acceptProcess(micbRequestBean);
		}
		
		return responseBean;
	}
	
	// Telegraphic Transfer (TT) Module
	private MICBResponseBean processTTModule(MICBRequestBean micbRequestBean)
	{
		MICBResponseBean responseBean = null;
		
		if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_TT_SOURCE_OF_FUND))
		{
			responseBean = ttRequestSourceOfFundService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_TT_SAVED_RECIPIENT))
		{
			responseBean = ttRequestSavedRecipientService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_TT_VALIDATE_SWIFT_CODE))
		{	
			responseBean = ttValidateSwiftCodeService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_TT_LIST_BANK_COUNTRY))
		{
			responseBean = ttRequestBankCountrySelectionService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_TT_LIST_BANK_CITY))
		{
			responseBean = ttRequestBankCitySelectionService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_TT_LIST_BANK))
		{
			responseBean = ttRequestBankListService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_TT_REQ_RECIPIENT_TRANSFER))
		{
			responseBean = ttRequestRecipientInfoTransferInfoService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_TT_VALIDATE_TRXN))
		{
			responseBean = ttPerformTrxnValidationService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_TT_ACKNOWLEDGEMENT))
		{
			responseBean = ttPerformAcknowledgementService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_TT_CALC_AMOUNT))
		{
			responseBean = ttCalculateAmountService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_TT_DEL_BENEFICIARY))
		{
			responseBean = ttDeleteBeneficiaryService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_TT_SAVE_BENEFICIARY))
		{
			responseBean = ttSaveBeneficiaryService.acceptProcess(micbRequestBean);
		}
		
		return responseBean;
	}
	
	// Edit Profile Information (EPI) Module
	private MICBResponseBean processEPIModule(MICBRequestBean micbRequestBean)
	{
		MICBResponseBean responseBean = null;
		
		if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_EPI_LOAD_USER_PROFILE))
		{
			responseBean = epiRetrieveCustomerProfileService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_EPI_GET_PARAM_LIST))
		{
			responseBean = epiRetrieveSelectionInformationService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_EPI_GET_ADDRESS_LIST))
		{
			responseBean = epiRetrieveAddressInformationService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_EPI_EDIT_USER_PROFILE))
		{
			responseBean = epiEditCustomerProfileService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_EPI_FILTER_SELECTION))
		{
			responseBean = epiFilterSelectionService.acceptProcess(micbRequestBean);
		}
		
		return responseBean;
	}
	
	// Account On Boarding (AOB) Module
	private MICBResponseBean processAOBModule(MICBRequestBean micbRequestBean)
	{
		MICBResponseBean responseBean = null;
		
		if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_AO_INFO_INPUT))
		{
			responseBean = 	accountOnBoardingInfoInputService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_AO_SMS_TOKEN))
		{
			responseBean = 	accountOnBoardingResendPasscodeService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_AO_VAL_TOKEN))
		{
			responseBean = 	accountOnBoardingValidatePasscodeService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_AO_TNC))
		{
			responseBean = 	accountOnBoardingTnCService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_AO_RETRIEVE_DOC_LIST))
		{
			responseBean = 	accountOnBoardingRetrieveDocListService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_AO_SUBMIT_OCR_IMAGE))
		{
			responseBean = 	accountOnBoardingSubmitOCRImageService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_AO_KTPNIK_INPUT))
		{
			responseBean = 	accountOnBoardingKTPNIKInputService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_AO_CC_SELECTION))
		{
			responseBean = 	accountOnBoardingCreditCardSelectionService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_AO_DEVICENIK_CHECK))
		{
			responseBean = 	accountOnBoardingDeviceNIKCheckingService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_AO_BANK_LIST))
		{
			responseBean = 	accountOnBoardingRetrieveBankListService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_AO_BANK_LIST2))
		{
			responseBean = 	accountOnBoardingRetrieveBankList2Service.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_AO_BANK_BRANCH))
		{
			responseBean = 	accountOnBoardingRetrieveBankBranchService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_AO_CHECK_POSTAL_CODE))
		{
			responseBean = 	accountOnBoardingCheckPostalCodeService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_AO_SEARCH_POSTAL_CODE))
		{
			responseBean = 	accountOnBoardingSearchPostalCodeService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_AO_POSTAL_WHITELIST))
		{
			responseBean = accountOnBoardingPostalCodeWhitelistService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_AO_RETRIEVE_PRODUCT_LIST))
		{
			responseBean = 	accountOnBoardingRetrieveProductListService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_AO_RETRIEVE_NATIONALITY_LIST))
		{
			responseBean = 	accountOnBoardingRetrieveNationalityService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_AO_SUBMIT_EDITED_DATA_DUKCAPIL))
		{
			responseBean = 	accountOnBoardingSubmitEditedDataDukcapilService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_AO_RETRIEVE_PARAM))
		{
			responseBean = 	accountOnBoardingRetrieveParamsService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_AO_CREATE_ACCOUNT_FN))
		{
			responseBean = 	accountOnBoardingCreateAccountFNService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_AO_CREATE_ACCOUNT_DL))
		{
			responseBean = 	accountOnBoardingCreateAccountDLService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_AO_CACHE_USER_INPUT))
		{
			responseBean = 	accountOnBoardingCacheUserInputService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_AO_RETRIEVE_CACHE_USER_INPUT))
		{
			responseBean = 	accountOnBoardingRetrieveCacheUserInputService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_AO_REMOVE_CACHE_USER_INPUT))
		{
			responseBean = 	accountOnBoardingRemoveCacheUserInputService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_AO_RETRIEVE_PRODUCT_IMAGE))
		{
			responseBean = 	accountOnBoardingRetrieveProductImageService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_AO_INSTALLMENT_CALCULATOR))
		{
			responseBean = 	accountOnBoardingInstallmentCalculatorService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_AO_SUBMIT_OCR_IMG_TO_OMNI))
		{
			responseBean = 	accountOnBoardingSubmitOCRImageToOMNIService.acceptProcess(micbRequestBean);
		}else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_AO_RETRIEVE_IMAGE_BY_UUID))
		{
			responseBean = 	accountOnBoardingRetrieveOCRImageService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_AO_INQUIRY_DUKCAPIL_TIME_OUT))
		{
			responseBean = 	accountOnBoardingInquiryDukcapilTimeOutService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_AO_REFRESH_SESSION))
		{
			responseBean = 	accountOnBoardingRefreshSessionService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_AO_FILTER_SELECTION))
		{
			responseBean = accountOnBoardingFilterSelectionService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_AO_CHECK_CUT_OFF_TIME))
		{
			responseBean = accountOnBoardingCheckCutOffTimeService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_AO_GET_PUBLIC_HOLIDAY))
		{
			responseBean = accountOnBoardingGetListPublicHolidayService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_AO_VIDEO_CALL))
		{
			responseBean = accountOnBoardingVideoCallService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_AO_CHECK_VIDEO_CALL))
		{
			responseBean = accountOnBoardingCheckVideoCallService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_AO_RETRIEVE_INTRO_PAGE))
		{
			responseBean = accountOnBoardingRetrieveIntroPageService.acceptProcess(micbRequestBean);
		}
		
		return responseBean;
	}
	
	// ONA Module
	private MICBResponseBean processONAModule(MICBRequestBean micbRequestBean)
	{
		MICBResponseBean responseBean = null;
		
		if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ONA_TNC))
		{
			responseBean = onaTnCService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ONA_RETRIEVE_PRODUCT_LIST))
		{
			responseBean = onaRetrieveProductListService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ONA_CCY_SELECTION))
		{
			responseBean = onaRetrieveCurrencySelectionService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ONA_RETRIEVE_PARAM))
		{
			responseBean = onaRetrieveParamsService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ONA_INFO_INPUT))
		{
			responseBean = onaInfoInputService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ONA_RETRIEVE_DOC_LIST))
		{
			responseBean = onaRetrieveDocListService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ONA_KTPNIK_INPUT))
		{
			responseBean = onaKTPNIKInputService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ONA_CC_SELECTION))
		{
			responseBean = onaCreditCardSelectionService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ONA_BANK_LIST))
		{
			responseBean = onaRetrieveBankListService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ONA_BANK_BRANCH))
		{
			responseBean = onaRetrieveBankBranchService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ONA_CHECK_POSTAL_CODE))
		{
			responseBean = onaCheckPostalCodeService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ONA_SEARCH_POSTAL_CODE))
		{
			responseBean = onaSearchPostalCodeService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ONA_CREATE_ACCOUNT_DL))
		{
			responseBean = onaCreateAccountDLService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ONA_CACHE_USER_INPUT))
		{
			responseBean = onaCacheUserInputService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ONA_RETRIEVE_CACHE_USER_INPUT))
		{
			responseBean = onaRetrieveCacheUserInputService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ONA_REMOVE_CACHE_USER_INPUT))
		{
			responseBean = onaRemoveCacheUserInputService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ONA_RETRIEVE_PRODUCT_IMAGE))
		{
			responseBean = onaRetrieveProductImageService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ONA_INSTALLMENT_CALCULATOR))
		{
			responseBean = onaInstallmentCalculatorService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ONA_RETRIEVE_OCR_IMG))
		{
			responseBean = onaRetrieveOCRImageService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ONA_SUBMIT_OCR_IMG_TO_OMNI))
		{
			responseBean = onaSubmitOCRImageToOMNIService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ONA_SUBMIT_OCR_IMG))
		{
			responseBean = onaSubmitOCRImageService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ONA_INQUIRY_DUKCAPIL_TIME_OUT))
		{
			responseBean = onaInquiryDukcapilTimeOutService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ONA_SUBMIT_EDITED_DATA_DUKCAPIL))
		{
			responseBean = onaSubmitEditedDataDukcapilService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ONA_CREATE_ACCOUNT_FN))
		{
			responseBean = onaCreateAccountFNService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ONA_TAKA_STEP1))
		{
			responseBean = onaTakaStep1Service.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ONA_TAKA_STEP2))
		{
			responseBean = onaTakaStep2Service.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ONA_TAKA_CALC))
		{
			responseBean = onaTakaInstallmentCalculatorService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ONA_TAKA_CACHE_DATA))
		{
			responseBean = onaTakaCacheDataService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ONA_TAKA_CONFIRMATION))
		{
			responseBean = onaTakaConfirmationService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ONA_TAKA_ACKNOWLEDGEMENT))
		{
			responseBean = onaTakaAcknowledgementService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ONA_TANDA360_STEP1))
		{
			responseBean = onaTanda360Step1Service.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ONA_TANDA360_CONFIRMATION))
		{
			responseBean = onaTanda360ConfirmationService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ONA_TANDA360_ACK))
		{
			responseBean = onaTanda360AcknowledgementService.acceptProcess(micbRequestBean);
		}else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ONA_GIRO_STEP1))
		{
			responseBean = onaGiroStep1Service.acceptProcess(micbRequestBean);
		}else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ONA_GIRO_CONFIRMATION))
		{
			responseBean = onaGiroConfirmationService.acceptProcess(micbRequestBean);
		}else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ONA_GIRO_ACKNOWLEDGEMENT))
		{
			responseBean = onaGiroAcknowledgementService.acceptProcess(micbRequestBean);
		}else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ONA_TDS_STEP1))
		{
			responseBean = onaTDSyariahStep1Service.acceptProcess(micbRequestBean);
		}else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ONA_TDS_INTEREST_PAYMENT))
		{
			responseBean = onaTDSyariahGetInterestPaymentService.acceptProcess(micbRequestBean);
		}else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ONA_TDS_CONFIRMATION))
		{
			responseBean = onaTDSyariahConfirmationService.acceptProcess(micbRequestBean);
		}else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ONA_TDS_ACKNOWLEDGEMENT))
		{
			responseBean = onaTDSyariahAcknowledgementService.acceptProcess(micbRequestBean);
		}else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ONA_CC_AND_LOAN))
		{
			responseBean = onaCreditCardAndLoanService.acceptProcess(micbRequestBean);
		}else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ONA_RETRIEVE_CC))
		{
			responseBean = onaRetrieveCreditCardListService.acceptProcess(micbRequestBean);
		}else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ONA_OFFLINE_SYARIAH))
		{
			responseBean = onaOfflineSyariahService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ONA_TAKA_HADIAH_STEP1))
		{
			responseBean = onaTakaHadiahStep1Service.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ONA_TAKA_HADIAH_CONFIRMATION))
		{
			responseBean = onaTakaHadiahConfirmationService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ONA_TAKA_HADIAH_ACKNOWLEDGEMENT))
		{
			responseBean = onaTakaHadiahAcknowledgementService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ONA_TANDA_HOLD_STEP1))
		{
			responseBean = onaTandaHoldStep1Service.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ONA_TANDA_HOLD_CONFIRMATION))
		{
			responseBean = onaTandaHoldConfirmationService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_ONA_TANDA_HOLD_ACKNOWLEDGEMENT))
		{
			responseBean = onaTandaHoldAcknowledgementService.acceptProcess(micbRequestBean);
		}
		
		return responseBean;
	}
	
	// Forex Module
	private MICBResponseBean processForexModule(MICBRequestBean micbRequestBean)
	{
		MICBResponseBean responseBean = null;
		
		if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_FOREX_IS_ALLOWED_PROCEED))
		{
			responseBean = forexIsAllowedToProceedService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_FOREX_TNC))
		{
			responseBean = forexTnCService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_FOREX_GET_CURRENCY_RATE))
		{
			responseBean = forexGetCurrencyRateListService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_FOREX_GET_EXCHANGE_RATE))
		{
			responseBean = forexGetExchangeRateService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_FOREX_CONFIRMATION))
		{
			responseBean = forexConfirmationService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_FOREX_ACKNOWLEDGEMENT))
		{
			responseBean = forexAcknowledgementService.acceptProcess(micbRequestBean);
		}
		
		return responseBean;
	}

	// Ext Module
	private MICBResponseBean processExtModule(MICBRequestBean micbRequestBean)
	{
		MICBResponseBean responseBean = null;
		
		if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_DVENQUIRY))
		{
			responseBean = devEnquiry.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_DVBINDING))
		{
			responseBean = devBind.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_DVUNBIND))
		{
			//responseBean = devUnBind.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_DVUNBINDCONF))
		{
			responseBean = devUnBindConf.acceptProcess(micbRequestBean);
		}	
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_EXT_SYSENQUIRY))
		{
			responseBean = sysConfEnquiry.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_EXT_SYSUPDATECONFM))
		{
			responseBean = SysConfUpdateConfimation.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_EXT_SYSUPDATEACK))
		{
			responseBean = sysConfUpdateAck.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_EXT_CUSTEVENTENQ))
		{
			responseBean = custEventEnquiry.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_EXT_MSGMAPENQUIRY))
		{
			responseBean = 	msgMapEnquiry.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_EXT_MSGMAPCONFIRM))
		{
			responseBean = 	msgMappingUpdateConfimation.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_EXT_MSGMAPACK))
		{
			responseBean = 	msgMappingUpdateAck.acceptProcess(micbRequestBean);
		}
		
		return responseBean;
	}
	
	private MICBResponseBean processGeneralModule(MICBRequestBean micbRequestBean)
	{
		MICBResponseBean responseBean = null;
		
		if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_GEN_STORE_OCR_IMAGE))
		{
			responseBean = storeOCRImageService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_GEN_RETRIEVE_OCR_IMAGE))
		{
			responseBean = retrieveOCRImageService.acceptProcess(micbRequestBean);
		}		
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_GEN_SEARCH_POSTAL_CODE))
		{
			responseBean = searchPostalCodeService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_GEN_TNC))
		{
			responseBean = retrieveTnCService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_GEN_RETRIEVE_CACHE))
		{
			responseBean = retrieveCacheService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_GEN_HELP_INFO))
		{
			responseBean = retrieveHelpInfoService.acceptProcess(micbRequestBean);
		}
		//V2
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_GEN_STORE_IMAGE))
		{
			responseBean = storeImageService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_GEN_RETRIEVE_IMAGE))
		{
			responseBean = retrieveImageService.acceptProcess(micbRequestBean);
		}

		return responseBean;
	}
	
	private MICBResponseBean processPreApprovedLoanModule(MICBRequestBean micbRequestBean)
	{
		MICBResponseBean responseBean = null;
		
		if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_PAL_STEP1))
		{
			responseBean = palStep1Service.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_PAL_STEP2))
		{
			responseBean = palStep2Service.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_PAL_SUBMIT_OCR_TO_OMNI))
		{
			responseBean = palSubmitOCRImageToOMNIService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_PAL_INQ_NIK))
		{
			responseBean = palInquiryCheckNIKService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_PAL_SUBMIT_EDITED_DATA_DUKCAPIL))
		{
			responseBean = palSubmitEditedDataDukcapilService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_PAL_CONFIRMATION))
		{
			responseBean = palConfirmationService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_PAL_INSTALLMENT_CALC))
		{
			responseBean = palInstallmentCalculatorService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_PAL_STORE_CACHE))
		{
			responseBean = storePALCacheService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_PAL_COMPARE_ADDRESS))
		{
			responseBean = palCompareAddressService.acceptProcess(micbRequestBean);
		}
		
//		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_GEN_SEARCH_POSTAL_CODE))
//		{
//			responseBean = searchPostalCodeService.acceptProcess(micbRequestBean);
//		}
		
		return responseBean;
	}
	
	private MICBResponseBean processQRPayModule(MICBRequestBean micbRequestBean)
	{
		MICBResponseBean responseBean = null;
		
		if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_QR_PAY_GET_ACC_LIST))
		{
			responseBean = qrPayGetAccountListService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_QR_PAY_GET_ACC))
		{
			responseBean = qrPayGetAccountService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_QR_PAY_SET_ACC))
		{
			responseBean = qrPaySetAccountService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_QR_PAY_INQUIRY))
		{
			responseBean = qrPayInquiryService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_QR_PAY_CONFIMATION))
		{
			responseBean = qrPayConfirmationService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_QR_PAY_ACKNOWLEDGEMENT))
		{
			responseBean = qrPayAcknowledgementService.acceptProcess(micbRequestBean);
		}
		
		return responseBean;
	}
	
	private MICBResponseBean processPrimaryBondModule(MICBRequestBean micbRequestBean)
	{
		MICBResponseBean responseBean = null;
		
		if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_PB_STEP_1))
		{
			responseBean = primaryBondStep1Service.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_PB_STEP_2))
		{
			responseBean = primaryBondStep2Service.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_PB_INVESTOR_INQUIRY))
		{
			responseBean = primaryBondInquiryRegisterInvestorService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_PB_INVESTOR_SAVE))
		{
			responseBean = primaryBondSaveRegisterInvestorService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_PB_ORDER_CONFIRMATION))
		{
			responseBean = primaryBondOrderConfirmationService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_PB_ORDER_ACKNOWLEDGEMENT))
		{
			responseBean = primaryBondOrderAcknowledgementService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_PB_HISTORY_INQUIRY))
		{
			responseBean = primaryBondInquiryOrderHistoryService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_PB_REDEEM_CONFIRMATION))
		{
			responseBean = primaryBondEarlyRedemptionConfirmationService.acceptProcess(micbRequestBean);
		}
		else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_PB_REDEEM_ACKNOWLEDGEMENT))
		{
			responseBean = primaryBondEarlyRedemptionAcknowledgementService.acceptProcess(micbRequestBean);
		}
		
		return responseBean;
	}*/
}

