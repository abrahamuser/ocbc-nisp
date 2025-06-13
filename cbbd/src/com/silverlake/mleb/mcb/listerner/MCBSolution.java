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
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.entity.dao.MLEBMIBDAO;
import com.silverlake.mleb.mcb.module.func.PerformBiometricSetup;
import com.silverlake.mleb.mcb.module.func.PerformChangePassword;
import com.silverlake.mleb.mcb.module.func.PerformLogin;
import com.silverlake.mleb.mcb.module.func.PerformLoginv2;
import com.silverlake.mleb.mcb.module.func.PerformLogout;
import com.silverlake.mleb.mcb.module.func.PerformResetPassword;
import com.silverlake.mleb.mcb.module.func.PerformUpdateTNC;
import com.silverlake.mleb.mcb.module.func.PerformVerifyPassword;
import com.silverlake.mleb.mcb.module.func.RetrieveAccessRestriction;
import com.silverlake.mleb.mcb.module.func.RetrieveAppStatInfo_mcb;
import com.silverlake.mleb.mcb.module.func.RetrieveDashBoardInfo;
import com.silverlake.mleb.mcb.module.func.RetrieveFutureTranx;
import com.silverlake.mleb.mcb.module.func.RetrieveResetPassTnC;
import com.silverlake.mleb.mcb.module.func.RetrieveSecLoginToken;
import com.silverlake.mleb.mcb.module.func.RetrieveSessionInfo;
import com.silverlake.mleb.mcb.module.func.SwiftoutboundTracking.PerformSWIFTOutboundTracking;
import com.silverlake.mleb.mcb.module.func.accountMgmt.EstatementDownload;
import com.silverlake.mleb.mcb.module.func.accountMgmt.PerformUpdateFavAcct;
import com.silverlake.mleb.mcb.module.func.accountMgmt.RetrieveAccountDetails;
import com.silverlake.mleb.mcb.module.func.accountMgmt.RetrieveAccountHistory;
import com.silverlake.mleb.mcb.module.func.accountMgmt.RetrieveAccountList;
import com.silverlake.mleb.mcb.module.func.accountMgmt.RetrieveFavAcct;
import com.silverlake.mleb.mcb.module.func.accountMgmt.RetrieveStatementFile;
import com.silverlake.mleb.mcb.module.func.accountaliases.AdministrationAccountAliasList;
import com.silverlake.mleb.mcb.module.func.accountaliases.AdministrationAccountAliasMaintenance;
import com.silverlake.mleb.mcb.module.func.accountlist.CustomerAccountList;
import com.silverlake.mleb.mcb.module.func.accountlist.CustomerPortfolioData;
import com.silverlake.mleb.mcb.module.func.accountlist.CustomerPortfolioDatav2;
import com.silverlake.mleb.mcb.module.func.accountlist.CustomerPortfolioDownload;
import com.silverlake.mleb.mcb.module.func.admin.PendingAuthNotiList;
import com.silverlake.mleb.mcb.module.func.admin.PendingAuthNotiMaint;
import com.silverlake.mleb.mcb.module.func.admin.RetrieveAccessAuth;
import com.silverlake.mleb.mcb.module.func.admin.RetrievePolicyAuth;
import com.silverlake.mleb.mcb.module.func.admin.RetrieveProfileAuth;
import com.silverlake.mleb.mcb.module.func.admin.SubmitAccessAuth;
import com.silverlake.mleb.mcb.module.func.admin.SubmitPolicyAuth;
import com.silverlake.mleb.mcb.module.func.admin.SubmitProfileAuth;
import com.silverlake.mleb.mcb.module.func.administration.AdministrationBeneficiaryList;
import com.silverlake.mleb.mcb.module.func.authorization.AuthorizationPendingCount;
import com.silverlake.mleb.mcb.module.func.authorization.AuthorizationPerformApprovalPendingAccount;
import com.silverlake.mleb.mcb.module.func.authorization.AuthorizationPerformApprovalPendingBeneficiary;
import com.silverlake.mleb.mcb.module.func.authorization.AuthorizationPerformApprovalPendingUser;
import com.silverlake.mleb.mcb.module.func.authorization.AuthorizationRetrievePendingAccountList;
import com.silverlake.mleb.mcb.module.func.authorization.AuthorizationRetrievePendingBeneficiaryList;
import com.silverlake.mleb.mcb.module.func.authorization.AuthorizationRetrievePendingUserList;
import com.silverlake.mleb.mcb.module.func.authorization.AuthorizationTAC;
import com.silverlake.mleb.mcb.module.func.authorization.PerformAuthorizeConfirmation;
import com.silverlake.mleb.mcb.module.func.authorization.PerformAuthorizeTransaction;
import com.silverlake.mleb.mcb.module.func.authorization.PreAuthorizeTransaction;
import com.silverlake.mleb.mcb.module.func.authorization.RetrieveAuthorizeTransaction;
import com.silverlake.mleb.mcb.module.func.blockuser.PerformBlockMyUser;
import com.silverlake.mleb.mcb.module.func.deviceBinding.DeviceCIFBindingAck;
import com.silverlake.mleb.mcb.module.func.deviceBinding.DeviceCIFBindingAckv2;
import com.silverlake.mleb.mcb.module.func.deviceBinding.DeviceCIFBindingTAC;
import com.silverlake.mleb.mcb.module.func.deviceBinding.DeviceCIFBindingUpdate;
import com.silverlake.mleb.mcb.module.func.deviceBinding.DeviceCIFEnquiry;
import com.silverlake.mleb.mcb.module.func.deviceBinding.DeviceCIFUnbindAck;
import com.silverlake.mleb.mcb.module.func.deviceBinding.DeviceCIFUnbindAckOpen;
import com.silverlake.mleb.mcb.module.func.devicealias.UpdateDeviceAlias;
import com.silverlake.mleb.mcb.module.func.deviceunbindg.PerformDeviceUnBindg;
import com.silverlake.mleb.mcb.module.func.exchangeRate.RetrieveExchangeRate;
import com.silverlake.mleb.mcb.module.func.exchangeRate.RetrieveIndividualExchangeRate;
import com.silverlake.mleb.mcb.module.func.holiday.RetrieveHolidayList;
import com.silverlake.mleb.mcb.module.func.notificationInbox.RetrieveBoardNotificationInbox;
import com.silverlake.mleb.mcb.module.func.notificationInbox.RetrievePushNotificationInbox;
import com.silverlake.mleb.mcb.module.func.onfx.ONFXConfirmation;
import com.silverlake.mleb.mcb.module.func.onfx.ONFXGetRate;
import com.silverlake.mleb.mcb.module.func.onfx.ONFXPost;
import com.silverlake.mleb.mcb.module.func.onfx.ONFXStep1;
import com.silverlake.mleb.mcb.module.func.others.DocumentDownload;
import com.silverlake.mleb.mcb.module.func.others.RetrieveBankList;
import com.silverlake.mleb.mcb.module.func.others.RetrieveBankSwiftDetails;
import com.silverlake.mleb.mcb.module.func.others.RetrieveCountryList;
import com.silverlake.mleb.mcb.module.func.others.RetrieveFaq;
import com.silverlake.mleb.mcb.module.func.others.RetrieveTnC;
import com.silverlake.mleb.mcb.module.func.others.RetrieveTnCv2;
import com.silverlake.mleb.mcb.module.func.others.SendFeedback;
import com.silverlake.mleb.mcb.module.func.others.SendFeedbackFaq;
import com.silverlake.mleb.mcb.module.func.others.TnCConfirmation;
import com.silverlake.mleb.mcb.module.func.payment.PaymentConfirmSubmit;
import com.silverlake.mleb.mcb.module.func.payment.PaymentInquiryBiller;
import com.silverlake.mleb.mcb.module.func.payment.PaymentRetrieveBillerList;
import com.silverlake.mleb.mcb.module.func.payment.PaymentRetrievePayeeList;
import com.silverlake.mleb.mcb.module.func.payment.PaymentStep1;
import com.silverlake.mleb.mcb.module.func.payment.PaymentSubmit;
import com.silverlake.mleb.mcb.module.func.registration.AddDeleteSignerData;
import com.silverlake.mleb.mcb.module.func.registration.RegistrationAckSubmit;
import com.silverlake.mleb.mcb.module.func.registration.RegistrationCancel;
import com.silverlake.mleb.mcb.module.func.registration.RegistrationRevisionSubmit;
/*import com.silverlake.mleb.mcb.module.func.registration.RegistrationAckSubmit;
import com.silverlake.mleb.mcb.module.func.registration.RegistrationCancel;
import com.silverlake.mleb.mcb.module.func.registration.RegistrationRevisionSubmit;*/
import com.silverlake.mleb.mcb.module.func.registration.RetrieveInputterSignerData;
import com.silverlake.mleb.mcb.module.func.registration.RetrieveInquiryRegistration;
import com.silverlake.mleb.mcb.module.func.registration.RetrieveProvinceList;
import com.silverlake.mleb.mcb.module.func.task.RetrieveTaskList;
import com.silverlake.mleb.mcb.module.func.task.RetrieveTaskTransaction;
import com.silverlake.mleb.mcb.module.func.timedeposit.TimeDepositAccountMaintenanceDetails;
import com.silverlake.mleb.mcb.module.func.timedeposit.TimeDepositAccountMaintenanceSubmit;
import com.silverlake.mleb.mcb.module.func.timedeposit.TimeDepositAccountOpeningAcknowledgement;
import com.silverlake.mleb.mcb.module.func.timedeposit.TimeDepositAccountOpeningConfirmation;
import com.silverlake.mleb.mcb.module.func.timedeposit.TimeDepositAccountOpeningProductList;
import com.silverlake.mleb.mcb.module.func.timedeposit.TimeDepositAccountOpeningStep1;
import com.silverlake.mleb.mcb.module.func.token.CustomerTokenInquiryRequest;
import com.silverlake.mleb.mcb.module.func.token.OTPRequest;
import com.silverlake.mleb.mcb.module.func.token.OTPVerify;
import com.silverlake.mleb.mcb.module.func.token.RequestChallengeResponse;
import com.silverlake.mleb.mcb.module.func.token.SoftwareTokenDelete;
import com.silverlake.mleb.mcb.module.func.token.SoftwareTokenEligibilityRequest;
import com.silverlake.mleb.mcb.module.func.token.SoftwareTokenPostBindRequest;
import com.silverlake.mleb.mcb.module.func.token.SoftwareTokenPreBind1Request;
import com.silverlake.mleb.mcb.module.func.token.SoftwareTokenPreBind2Request;
import com.silverlake.mleb.mcb.module.func.token.SoftwareTokenTncRetrieve;
import com.silverlake.mleb.mcb.module.func.token.SwitchDefaultActiveTokenRequest;
import com.silverlake.mleb.mcb.module.func.token.UpdateEnrolSwToken;
import com.silverlake.mleb.mcb.module.func.token.VerifyChallengeResponse;
import com.silverlake.mleb.mcb.module.func.transaction.PerformMT103Inquiry;
import com.silverlake.mleb.mcb.module.func.transaction.RetrieveBeneficiaryAffiliation;
import com.silverlake.mleb.mcb.module.func.transaction.RetrieveBeneficiaryCategory;
import com.silverlake.mleb.mcb.module.func.transaction.RetrieveBeneficiaryCountry;
import com.silverlake.mleb.mcb.module.func.transaction.RetrieveChargeAccountList;
import com.silverlake.mleb.mcb.module.func.transaction.RetrieveRemitterCategory;
import com.silverlake.mleb.mcb.module.func.transaction.RetrieveRemitterCountry;
import com.silverlake.mleb.mcb.module.func.transaction.RetrieveTransactionAuditTrail;
import com.silverlake.mleb.mcb.module.func.transaction.RetrieveTransactionCurrencyList;
import com.silverlake.mleb.mcb.module.func.transaction.RetrieveTransactionDataList;
import com.silverlake.mleb.mcb.module.func.transaction.RetrieveTransactionLimitPurchase;
import com.silverlake.mleb.mcb.module.func.transaction.RetrieveTransactionNotes;
import com.silverlake.mleb.mcb.module.func.transaction.RetrieveTransactionReceipt;
import com.silverlake.mleb.mcb.module.func.transaction.RetrieveTransactionStatus;
import com.silverlake.mleb.mcb.module.func.transaction.RetrieveTransactionSummary;
import com.silverlake.mleb.mcb.module.func.transaction.TransactionBeneficiaries;
import com.silverlake.mleb.mcb.module.func.transaction.TransactionBeneficiaryInquiry;
import com.silverlake.mleb.mcb.module.func.transaction.TransactionFundTransferConfirmSubmit;
import com.silverlake.mleb.mcb.module.func.transaction.TransactionFundTransferMenu;
import com.silverlake.mleb.mcb.module.func.transaction.TransactionFundTransferStep1;
import com.silverlake.mleb.mcb.module.func.transaction.TransactionFundTransferSubmit;
import com.silverlake.mleb.mcb.module.func.transaction.TransactionFundTransferValidateDate;
import com.silverlake.mleb.mcb.module.func.transaction.TransactionManageBeneficiaryAdd;
import com.silverlake.mleb.mcb.module.func.transaction.TransactionManageBeneficiaryDelete;
import com.silverlake.mleb.mcb.module.func.transaction.TransactionManageBeneficiaryDetails;
import com.silverlake.mleb.mcb.module.func.transaction.TransactionManageBeneficiaryEdit;
import com.silverlake.mleb.mcb.module.func.transaction.TransactionManageBeneficiarySetFavorite;
import com.silverlake.mleb.mcb.module.func.transaction.TransactionManageBeneficiaryStep1;
import com.silverlake.mleb.mcb.module.func.transaction.TransactionNoteAdd;
import com.silverlake.mleb.mcb.module.func.transaction.TransactionNoteDelete;
import com.silverlake.mleb.mcb.module.func.transaction.TransactionSourceOfFund;
import com.silverlake.mleb.mcb.module.func.transaction.TransactionStatusUpdate;
import com.silverlake.mleb.mcb.module.func.transactionMgmt.RetrieveRecurringList;
import com.silverlake.mleb.mcb.module.func.transactionMgmt.UpdateRecurring;
import com.silverlake.mleb.mcb.module.func.usermanagement.AdministrationUserCreateModify;
import com.silverlake.mleb.mcb.module.func.usermanagement.AdministrationUserGeneralMaintenance;
import com.silverlake.mleb.mcb.module.func.usermanagement.AdministrationUserManagementDataList;
import com.silverlake.mleb.mcb.module.func.usermanagement.AdministrationUserProfileList;
import com.silverlake.mleb.mcb.module.func.usermanagement.AdministrationUserRoleList;




@Service("MCBSolution")
public class MCBSolution  {


	private static Logger log =  LogManager.getLogger(MCBSolution.class);
	
	@Autowired
	MLEBMIBDAO dao;

 
	@Autowired RetrieveAppStatInfo_mcb retrieveAppStatInfo;
	@Autowired RetrieveSecLoginToken retrieveSecurityToken;
	@Autowired RetrieveAccessRestriction retrieveAccessRestriction;
	@Autowired PerformLogin performLoginSession;
	@Autowired PerformLogout performLogout;
	@Autowired DeviceCIFBindingAck deviceCIFBindingAck;
	@Autowired RetrieveSessionInfo retrieveSessionInfo;
	@Autowired DeviceCIFEnquiry deviceCIFEnquiry;
	@Autowired DeviceCIFUnbindAck deviceCIFUnbindAck;
	@Autowired DeviceCIFUnbindAckOpen deviceCIFUnbindAckOpen;
	@Autowired DeviceCIFBindingTAC deviceCIFBindingTAC;
	@Autowired RetrieveAccountList retrieveAccountSummary;
	@Autowired PerformChangePassword performChangePassword;
	@Autowired RetrieveTransactionDataList retrieveTransactionDataList;
	@Autowired RetrieveTaskList retrieveTaskList;
	@Autowired RetrieveDashBoardInfo retrieveDashBoardInfo;
	@Autowired RetrieveHolidayList retrieveHolidayList;
	@Autowired RetrieveFutureTranx retrieveFutureTranx;
	@Autowired RetrieveTaskTransaction retrieveTaskTransactionList;
	@Autowired RetrieveTransactionCurrencyList retrieveTransactionCurrencyList;
	@Autowired RetrieveTransactionAuditTrail retrieveTransactionAuditTrail;
	@Autowired RetrieveTransactionNotes retrieveTransactionNotes;
	@Autowired TransactionNoteAdd transactionNoteAdd;
	@Autowired TransactionNoteDelete transactionNoteDelete;
	@Autowired RetrieveTransactionReceipt retrieveTransactionReceipt;
	@Autowired TransactionStatusUpdate transactionStatusUpdate;
	@Autowired TransactionSourceOfFund transactionSourceOfFund;
	@Autowired TransactionBeneficiaries transactionBeneficiaries;
	@Autowired TransactionBeneficiaryInquiry transactionBeneficiaryInquiry;
	@Autowired TransactionFundTransferMenu transactionFundTransferMenu;
	@Autowired TransactionFundTransferStep1 transactionFundTransferStep1;
	@Autowired TransactionFundTransferValidateDate transactionFundTransferValidateDate;
	@Autowired TransactionFundTransferSubmit transactionFundTransferSubmit;
	@Autowired TransactionManageBeneficiaryStep1 transactionManageBeneficiaryStep1;
	@Autowired TransactionManageBeneficiaryDetails transactionManageBeneficiaryDetails;
	@Autowired TransactionManageBeneficiaryEdit transactionManageBeneficiaryEdit;
	@Autowired TransactionManageBeneficiaryDelete transactionManageBeneficiaryDelete;
	@Autowired TransactionManageBeneficiarySetFavorite transactionManageBeneficiarySetFavorite;
	@Autowired TransactionManageBeneficiaryAdd transactionManageBeneficiaryAdd;
	@Autowired PaymentStep1 paymentStep1;
	@Autowired PaymentRetrieveBillerList paymentRetrieveBillerList;
	@Autowired PaymentRetrievePayeeList paymentRetrievePayeeList;
	@Autowired PaymentInquiryBiller paymentInquiryBiller;
	@Autowired PaymentSubmit paymentSubmit;
	@Autowired ONFXStep1 onfxStep1;
	@Autowired ONFXGetRate onfxGetRate;
	@Autowired ONFXPost onfxPost;
	@Autowired ONFXConfirmation onfxConfirmation;
	@Autowired RetrieveFaq retrieveFaqList;
	@Autowired SendFeedbackFaq sendFeedbackFaq;
	@Autowired SendFeedback sendFeedback;
	@Autowired RetrieveTnC retrieveTnC;
	@Autowired RetrieveBankList retrieveBankList;
	@Autowired RetrieveBankSwiftDetails retrieveBankSwiftDetails;
	@Autowired RetrieveCountryList retrieveCountryList;
	@Autowired TnCConfirmation tnCConfirmation;
	@Autowired RetrieveAccountDetails retrieveAccountDetails;
	@Autowired RetrieveAccountHistory retrieveAccountHistory;
	@Autowired RetrieveStatementFile retrieveStatementFile;
	@Autowired RetrievePushNotificationInbox retrievePushNotificationInbox;
	@Autowired RetrieveBoardNotificationInbox retrieveBoardNotificationInbox;
	@Autowired RetrieveExchangeRate retrieveExchangeRate;
	@Autowired RetrieveIndividualExchangeRate retrieveIndividualExchangeRate;
	@Autowired RetrieveTransactionSummary retrieveTransactionSummary;
	@Autowired RetrieveAuthorizeTransaction retrieveAuthTransaction;
	@Autowired RequestChallengeResponse requestChallengeResponse;
	@Autowired VerifyChallengeResponse verifyChallengeResponse;
	@Autowired PerformAuthorizeConfirmation performAuthorizeConfirmation;
	@Autowired PreAuthorizeTransaction preAuthorizeTransaction;
	@Autowired AuthorizationTAC authorizationTAC;
	@Autowired PerformAuthorizeTransaction performAuthorizeTransaction;
	@Autowired CustomerTokenInquiryRequest customerTokenInquiryRequest;
	@Autowired SwitchDefaultActiveTokenRequest switchDefaultActiveTokenRequest;
	@Autowired SoftwareTokenPreBind1Request softwareTokenPreBind1Request;
	@Autowired SoftwareTokenPreBind2Request softwareTokenPreBind2Request;
	@Autowired SoftwareTokenPostBindRequest softwareTokenPostBindRequest;
	@Autowired SoftwareTokenDelete softwareTokenDeleteRequest;
	@Autowired SoftwareTokenEligibilityRequest softwareTokenEligibilityRequest;
	@Autowired PerformUpdateTNC performUpdateTNC;
	@Autowired OTPRequest otpRequest;
	@Autowired OTPVerify otpVerify;
	@Autowired RetrieveTransactionStatus retrieveTransactionStatus;
	@Autowired SoftwareTokenTncRetrieve retrieveSoftwareTokenTnC;
	@Autowired UpdateEnrolSwToken updateEnrolSwToken;
	@Autowired PerformResetPassword performResetPassword;
	@Autowired RetrieveResetPassTnC retrieveResetPassTnC;
	
	@Autowired PerformUpdateFavAcct performUpdateFavAcctd;
	@Autowired RetrieveFavAcct retrieveFavAcct;
	@Autowired RetrieveRecurringList retrieveRecurringList;
	@Autowired UpdateRecurring updateRecurring;
	@Autowired PerformBiometricSetup performBiometricSetup;
	@Autowired PerformVerifyPassword performVerifyPassword;

	@Autowired TimeDepositAccountOpeningStep1 timeDepositAccountOpeningStep1;
	@Autowired TimeDepositAccountOpeningProductList timeDepositAccountOpeningProductList;
	@Autowired TimeDepositAccountOpeningConfirmation timeDepositAccountOpeningConfirmation;
	@Autowired TimeDepositAccountOpeningAcknowledgement timeDepositAccountOpeningAcknowledgement;
	@Autowired TimeDepositAccountMaintenanceDetails timeDepositAccountMaintenanceDetails;
	@Autowired TimeDepositAccountMaintenanceSubmit timeDepositAccountMaintenanceSubmit;
	
	
	@Autowired CustomerAccountList customerAccountList;
	@Autowired CustomerPortfolioData customerPortfolioData;
	@Autowired CustomerPortfolioDownload customerPortfolioDownload;

	@Autowired AdministrationBeneficiaryList administrationBeneficiaryList;

	

	@Autowired AdministrationAccountAliasList administrationAccountAliasList;	
	@Autowired AdministrationAccountAliasMaintenance administrationAccountAliasMaintenance;
	
	@Autowired AdministrationUserManagementDataList administrationUserManagementDataList;
	@Autowired AdministrationUserGeneralMaintenance administrationUserGeneralMaintenance;
	@Autowired AdministrationUserProfileList administrationUserProfileList;
	@Autowired AdministrationUserRoleList administrationUserRoleList;
	@Autowired AdministrationUserCreateModify administrationUserCreateModify;
	

	@Autowired AuthorizationPendingCount authorizationPendingCount;
	@Autowired AuthorizationRetrievePendingUserList authorizationRetrievePendingUserList;
	@Autowired AuthorizationRetrievePendingAccountList authorizationRetrievePendingAccountList;
	@Autowired AuthorizationRetrievePendingBeneficiaryList authorizationRetrievePendingBeneficiaryList;
	@Autowired AuthorizationPerformApprovalPendingAccount authorizationPerformApprovalPendingAccount;
	@Autowired AuthorizationPerformApprovalPendingUser authorizationPerformApprovalPendingUser;
	@Autowired AuthorizationPerformApprovalPendingBeneficiary authorizationPerformApprovalPendingBeneficiary;
	
	@Autowired RetrieveProfileAuth retrieveProfileAuth;
	@Autowired SubmitProfileAuth submitProfileAuth;
	@Autowired RetrievePolicyAuth retrievePolicyAuth;
	@Autowired SubmitPolicyAuth submitPolicyAuth;
	@Autowired RetrieveAccessAuth retrieveAccessAuth;
	@Autowired SubmitAccessAuth submitAccessAuth;
	
	@Autowired PerformMT103Inquiry performMT103Inquiry;
	
	@Autowired  RetrieveTnCv2 retrieveTnCv2;
	
	@Autowired TransactionFundTransferConfirmSubmit transactionFundTransferConfirmSubmit;
	@Autowired PaymentConfirmSubmit paymentConfirmSubmit;
	
	@Autowired PendingAuthNotiList pendingAuthNotiList;
	@Autowired PendingAuthNotiMaint pendingAuthNotiMaint;
	
	@Autowired RetrieveInquiryRegistration retrieveInquiryRegistration;
	@Autowired RetrieveInputterSignerData retrieveInputterSignerData;
	@Autowired AddDeleteSignerData addDeleteSignerData;
	@Autowired RegistrationRevisionSubmit registrationRevisionSubmit;
	@Autowired RegistrationAckSubmit registrationAckSubmit;
	@Autowired RegistrationCancel registrationCancel;
	@Autowired RetrieveProvinceList retrieveProvinceList;
	
	@Autowired CustomerPortfolioDatav2 customerPortfolioDatav2;
	@Autowired PerformSWIFTOutboundTracking performSWIFTOutboundTracking;
	@Autowired UpdateDeviceAlias updateDeviceAlias;
	@Autowired PerformDeviceUnBindg performDeviceUnBindg;
	@Autowired RetrieveChargeAccountList retrieveChargeAccountList;
	@Autowired PerformBlockMyUser performBlockMyUser;
	@Autowired EstatementDownload estatementDownload;
	@Autowired DocumentDownload documentDownload;
	@Autowired RetrieveBeneficiaryCategory retrieveBeneficiaryCategory;
	@Autowired RetrieveBeneficiaryCountry retrieveBeneficiaryCountry;
	@Autowired RetrieveRemitterCountry retrieveRemitterCountry;
	@Autowired RetrieveRemitterCategory retrieveRemitterCategory;
	@Autowired RetrieveBeneficiaryAffiliation retrieveBeneficiaryAffiliation;
	@Autowired PerformLoginv2 performLoginSessionv2;
	@Autowired DeviceCIFBindingAckv2 deviceCIFBindingAckv2;
	@Autowired DeviceCIFBindingUpdate deviceCIFBindingUpdate;
	@Autowired RetrieveTransactionLimitPurchase retrieveTransactionLimitPurchase;
	
	public MICBResponseBean onCall(MICBRequestBean arg0) {


		MICBResponseBean responseBean = new MICBResponseBean();
		try
		{
			MICBRequestBean micbRequestBean = arg0;

			log.info("MCB Listerner onCall :: [ "+micbRequestBean.getHeaderBean().getServiceID()+" ]");
			
			if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_APP_STAT_INFO))
			{
				responseBean = retrieveAppStatInfo.acceptProcess(micbRequestBean);
			}	
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_SECTOKEN))
			{
				responseBean = retrieveSecurityToken.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_ACCESS_RESTRICTION.getCode()))
			{
				responseBean = retrieveAccessRestriction.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_LOGIN))
			{
				responseBean = performLoginSession.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_REQUEST_SESSION))
			{
				responseBean = retrieveSessionInfo.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_LOGOUT))
			{
				responseBean = performLogout.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_DVBINDING))
			{
				responseBean = deviceCIFBindingAck.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_DVUNBIND_OPEN))
			{
				responseBean = deviceCIFUnbindAckOpen.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_DVUNBIND))
			{
				responseBean = deviceCIFUnbindAck.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_DEVICETAC))
			{
				responseBean = deviceCIFBindingTAC.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_ACC_OVERVIEW))
			{
				responseBean = retrieveAccountSummary.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_DVENQUIRY))
			{
				responseBean = deviceCIFEnquiry.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_CHANGE_PASSWORD))
			{
				responseBean = performChangePassword.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_TRANS_DATA_LIST.getCode()))
			{
				responseBean = retrieveTransactionDataList.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_RETRIEVE_TASK_LIST))
			{
				responseBean = retrieveTaskList.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_DASHBOARD_INFO))
			{
				responseBean = retrieveDashBoardInfo.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_HOLIDAY_LIST))
			{
				responseBean = retrieveHolidayList.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_RETRIEVE_FUTURE_TRANX))
			{
				responseBean = retrieveFutureTranx.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_RETRIEVE_TASK_TRANX))
			{
				responseBean = retrieveTaskTransactionList.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_TRANS_CURRENCY_LIST.getCode()))
			{
				responseBean = retrieveTransactionCurrencyList.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_TRANS_AUDIT_TRAIL.getCode()))
			{
				responseBean = retrieveTransactionAuditTrail.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_TRANS_NOTES_LIST.getCode()))
			{
				responseBean = retrieveTransactionNotes.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_TRANS_NOTES_ADD.getCode()))
			{
				responseBean = transactionNoteAdd.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_TRANS_NOTES_DELETE.getCode()))
			{
				responseBean = transactionNoteDelete.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_TRANS_RECEIPT.getCode()))
			{
				responseBean = retrieveTransactionReceipt.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_TRANS_UPDATE_STATUS.getCode()))
			{
				responseBean = transactionStatusUpdate.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_TRANS_SOURCE_OF_FUND.getCode()))
			{
				responseBean = transactionSourceOfFund.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_TRANS_BENEFICIARIES.getCode()))
			{
				responseBean = transactionBeneficiaries.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_TRANS_BENEFICIARY_INQUIRY.getCode()))
			{
				responseBean = transactionBeneficiaryInquiry.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_TRANS_FUND_TRANS_MENU.getCode()))
			{
				responseBean = transactionFundTransferMenu.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_TRANS_FUND_TRANS_STEP1.getCode()))
			{
				responseBean = transactionFundTransferStep1.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_TRANS_FUND_TRANS_VAL_DATE.getCode()))
			{
				responseBean = transactionFundTransferValidateDate.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_TRANS_FUND_TRANS_SUBMIT.getCode()))
			{
				responseBean = transactionFundTransferSubmit.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_TRANS_MGMT_BENE_STEP_1.getCode()))
			{
				responseBean = transactionManageBeneficiaryStep1.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_TRANS_MGMT_BENE_DETAILS.getCode()))
			{
				responseBean = transactionManageBeneficiaryDetails.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_TRANS_MGMT_BENE_EDIT.getCode()))
			{
				responseBean = transactionManageBeneficiaryEdit.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_TRANS_MGMT_BENE_DELETE.getCode()))
			{
				responseBean = transactionManageBeneficiaryDelete.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_TRANS_MGMT_BENE_SET_FAV.getCode()))
			{
				responseBean = transactionManageBeneficiarySetFavorite.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_TRANS_MGMT_BENE_ADD.getCode()))
			{
				responseBean = transactionManageBeneficiaryAdd.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_PYMT_STEP1.getCode()))
			{
				responseBean = paymentStep1.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_PYMT_BILLER_LIST.getCode()))
			{
				responseBean = paymentRetrieveBillerList.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_PYMT_PAYEE_LIST.getCode()))
			{
				responseBean = paymentRetrievePayeeList.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_PYMT_BILLER_INQUIRY.getCode()))
			{
				responseBean = paymentInquiryBiller.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_PYMT_SUBMIT.getCode()))
			{
				responseBean = paymentSubmit.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_ONFX_STEP1.getCode()))
			{
				responseBean = onfxStep1.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_ONFX_GET_RATE.getCode()))
			{
				responseBean = onfxGetRate.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_ONFX_CONFIRMATION.getCode()))
			{
				responseBean = onfxConfirmation.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_ONFX_POST.getCode()))
			{
				responseBean = onfxPost.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_FAQ_LIST))
			{
				responseBean = retrieveFaqList.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_FAQ_RESPOND))
			{
				responseBean = sendFeedbackFaq.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_SEND_FEEDBACK.getCode()))
			{
				responseBean = sendFeedback.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_TNC_GET.getCode()))
			{
				responseBean = retrieveTnC.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_TNC_CONFIRM.getCode()))
			{
				responseBean = tnCConfirmation.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_ACC_DETAILS))
			{
				responseBean = retrieveAccountDetails.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_ACC_HIST))
			{
				responseBean = retrieveAccountHistory.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_ACC_STATEMENT_FILE))
			{
				responseBean = retrieveStatementFile.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_PUSH_NOTIFICATION_INBOX))
			{
				responseBean = retrievePushNotificationInbox.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_BOARD_NOTIFICATION_INBOX))
			{
				responseBean = retrieveBoardNotificationInbox.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_EXCHANGE_RATE))
			{
				responseBean = retrieveExchangeRate.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_EXCHANGE_RATE_INDIVIDUAL.getCode()))
			{
				responseBean = retrieveIndividualExchangeRate.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_TRANX_SUM))
			{
				responseBean = retrieveTransactionSummary.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_AUTH_TRANX))
			{
				responseBean = retrieveAuthTransaction.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_TOKEN_CR_REQUEST.getCode()))
			{
				responseBean = requestChallengeResponse.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_TOKEN_CR_VERIFY.getCode()))
			{
				responseBean = verifyChallengeResponse.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_AUTH_TRANX_CONFIRM))
			{
				responseBean = performAuthorizeConfirmation.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_PRE_AUTHORIZE_TRANSAC))
			{
				responseBean = preAuthorizeTransaction.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_AUTHORIZATIONTAC))
			{
				responseBean = authorizationTAC.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_AUTHORIZE_TRANSAC))
			{
				responseBean = performAuthorizeTransaction.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_CUSTOMER_TOKEN_INQUIRY))
			{
				responseBean = customerTokenInquiryRequest.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_SWITCH_DEFAULT_TOKEN))
			{
				responseBean = switchDefaultActiveTokenRequest.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_SOFT_TOKEN_PREBIND1))
			{
				responseBean = softwareTokenPreBind1Request.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_SOFT_TOKEN_PREBIND2))
			{
				responseBean = softwareTokenPreBind2Request.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_SOFT_TOKEN_POSTBIND))
			{
				responseBean = softwareTokenPostBindRequest.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_SOFT_TOKEN_DELETE))
			{
				responseBean = softwareTokenDeleteRequest.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_SOFT_TOKEN_ELIGIBILITY))
			{
				responseBean = softwareTokenEligibilityRequest.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_SOFT_TOKEN_TNC))
			{
				responseBean = retrieveSoftwareTokenTnC.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_ENRL_DEF_TK))
			{
				responseBean = updateEnrolSwToken.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_UPDATE_TNC))
			{
				responseBean = performUpdateTNC.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_OTP_REQ))
			{
				responseBean = otpRequest.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_OTP_VERIFY))
			{
				responseBean = otpVerify.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_BANK_LIST.getCode()))
			{
				responseBean = retrieveBankList.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_BANK_SWIFT_DETAILS.getCode()))
			{
				responseBean = retrieveBankSwiftDetails.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_COUNTRY_LIST.getCode()))
			{
				responseBean = retrieveCountryList.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_TRANX_STATUS))
			{
				responseBean = retrieveTransactionStatus.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_RESET_PASSWORD))
			{
				responseBean = performResetPassword.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_RESETPASS_TNC))
			{
				responseBean = retrieveResetPassTnC.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_UPDATE_FAV))
			{
				responseBean = performUpdateFavAcctd.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_RETRIEVE_FAV))
			{
				responseBean = retrieveFavAcct.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_RECURRING_FT))
			{
				responseBean = retrieveRecurringList.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_RECURRING_PYMT))
			{
				responseBean = retrieveRecurringList.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_UPDATE_RECURRING))
			{
				responseBean = updateRecurring.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_USER_BIOMETRIC_SETUP.getCode()))
			{
				responseBean = performBiometricSetup.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_USER_VERIFY_PWD.getCode()))
			{
				responseBean = performVerifyPassword.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_TIME_DEPOSIT_AO_STEP_1.getCode()))
			{
				responseBean = timeDepositAccountOpeningStep1.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_TIME_DEPOSIT_AO_PRODUCT_LIST.getCode()))
			{
				responseBean = timeDepositAccountOpeningProductList.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_TIME_DEPOSIT_AO_CONF.getCode()))
			{
				responseBean = timeDepositAccountOpeningConfirmation.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_TIME_DEPOSIT_AO_ACK.getCode()))
			{
				responseBean = timeDepositAccountOpeningAcknowledgement.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_TIME_DEPOSIT_AM_DETAILS.getCode()))
			{
				responseBean = timeDepositAccountMaintenanceDetails.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_TIME_DEPOSIT_AM_SUBMIT.getCode()))
			{
				responseBean = timeDepositAccountMaintenanceSubmit.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_CUSTOMER_ACCOUNT_LIST.getCode()))
			{
				responseBean = customerAccountList.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_CUSTOMER_PORTFOLIO_DATA.getCode()))
			{
				responseBean = customerPortfolioData.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_CUSTOMER_PORTFOLIO_DOWNLOAD.getCode()))
			{
				responseBean = customerPortfolioDownload.acceptProcess(micbRequestBean);
			}

			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_ACCOUNT_ALIAS_LIST.getCode()))
			{
				responseBean = administrationAccountAliasList.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_ACCOUNT_ALIAS_NAME_UPDATE.getCode()))
			{
				responseBean = administrationAccountAliasMaintenance.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_USER_MANAGEMENT_DATA_LIST.getCode()))
			{
				responseBean = administrationUserManagementDataList.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_USER_MANAGEMENT_DATA_MAINTENANCE.getCode()))
			{
				responseBean = administrationUserGeneralMaintenance.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_USER_PROFILE_LIST.getCode()))
			{
				responseBean = administrationUserProfileList.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_USER_ROLE_LIST.getCode()))
			{
				responseBean = administrationUserRoleList.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_USER_MANAGEMENT_CREATE_MODIFY.getCode()))
			{
				responseBean = administrationUserCreateModify.acceptProcess(micbRequestBean);
			}

			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_AUTH_ADM_ACCT_MTN_AUTH.getCode()))
			{
				responseBean = authorizationPerformApprovalPendingAccount.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_AUTH_ADM_ACCT_PEND_LIST.getCode()))
			{
				responseBean = authorizationRetrievePendingAccountList.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_AUTH_ADM_BENE_MTN_AUTH.getCode()))
			{
				responseBean = authorizationPerformApprovalPendingBeneficiary.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_AUTH_ADM_BENE_PEND_LIST.getCode()))
			{
				responseBean = authorizationRetrievePendingBeneficiaryList.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_AUTH_ADM_USR_AUTH.getCode()))
			{
				responseBean = authorizationPerformApprovalPendingUser.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_AUTH_ADM_USR_PEND_LIST.getCode()))
			{
				responseBean = authorizationRetrievePendingUserList.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_AUTH_COUNT.getCode()))
			{
				responseBean = authorizationPendingCount.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_ADM_BENE_LIST.getCode()))
			{
				responseBean = administrationBeneficiaryList.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_USRPRF_AUTH_LIST ))
			{
				responseBean = retrieveProfileAuth.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_USRPRF_AUTH_SUBMIT))
			{
				responseBean = submitProfileAuth.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_POLICY_AUTH_LIST ))
			{
				responseBean = retrievePolicyAuth.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_POLICY_AUTH_SUBMIT))
			{
				responseBean = submitPolicyAuth.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_ACCESS_AUTH_LIST ))
			{
				responseBean = retrieveAccessAuth.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_ACCESS_AUTH_SUBMIT))
			{
				responseBean = submitAccessAuth.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_MT103_INQUIRY.getCode()))
			{
				responseBean = performMT103Inquiry.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_TNC_V2.getCode()))
			{
				responseBean = retrieveTnCv2.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_TRANS_FUND_CNFRM_TRANS_SUBMIT.getCode()))
			{
				responseBean = transactionFundTransferConfirmSubmit.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_PYMT_CNFM_SUBMIT.getCode()))
			{
				responseBean = paymentConfirmSubmit.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_PEND_AUTH_NOTI_LIST ))
			{
				responseBean = pendingAuthNotiList.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_PEND_AUTH_NOTI_MAINT))
			{
				responseBean = pendingAuthNotiMaint.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_INQUIRY_REGISTRATION.getCode()))
			{
				responseBean = retrieveInquiryRegistration.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_INPUTTER_SIGNER_DATA.getCode()))
			{
				responseBean = retrieveInputterSignerData.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_ADD_DELETE_SIGNER.getCode()))
			{
				responseBean = addDeleteSignerData.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_REG_REVI_SUBMIT.getCode()))
			{
				responseBean = registrationRevisionSubmit.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_REG_ACK_SUBMIT.getCode()))
			{
				responseBean = registrationAckSubmit.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_REG_CANCEL.getCode()))
			{
				responseBean = registrationCancel.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_PROVINCE_LIST.getCode()))
			{
				responseBean = retrieveProvinceList.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_CUSTOMER_PORTFOLIO_DATA_V2.getCode()))
			{
				responseBean = customerPortfolioDatav2.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_SWIFT_OUTBTRKNG.getCode()))
			{
				responseBean = performSWIFTOutboundTracking.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_DEVICE_ALIAS.getCode()))
			{
				responseBean = updateDeviceAlias.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_DEVICE_UNBINDING.getCode()))
			{
				responseBean = performDeviceUnBindg.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_CHARGE_ACCOUNT_LIST.getCode()))
			{
				responseBean = retrieveChargeAccountList.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_BLOCK_USER.getCode()))
			{
				responseBean = performBlockMyUser.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_ESTATEMENT_DOWNLOAD.getCode()))
			{
				responseBean = estatementDownload.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_DOCUMENT_DOWNLOAD.getCode()))
			{
				responseBean = documentDownload.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_BENE_CATEGORY.getCode()))
			{
				responseBean = retrieveBeneficiaryCategory.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_BENE_COUNTRY.getCode()))
			{
				responseBean = retrieveBeneficiaryCountry.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_REMITTER_COUNTRY.getCode()))
			{
				responseBean = retrieveRemitterCountry.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_REMITTER_CATEGORY.getCode()))
			{
				responseBean = retrieveRemitterCategory.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_BENE_AFFILIATION.getCode()))
			{
				responseBean = retrieveBeneficiaryAffiliation.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_LOGIN_V2))
			{
				responseBean = performLoginSessionv2.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(MiBConstant.FUNC_MCB_DVBINDING_V2))
			{
				responseBean = deviceCIFBindingAckv2.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_DVBINDING_UPDATE.getCode()))
			{
				responseBean = deviceCIFBindingUpdate.acceptProcess(micbRequestBean);
			}
			else if(micbRequestBean.getHeaderBean().getServiceID().equalsIgnoreCase(VCMethodConstant.FUNCTION_CODES.FUNC_MCB_LIMIT_PURCHASE.getCode()))
			{
				responseBean = retrieveTransactionLimitPurchase.acceptProcess(micbRequestBean);
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
	
	
}

