package com.silverlake.mleb.ccmcb.adapter;

import java.math.BigDecimal;
import java.util.List;

import javax.activation.DataHandler;
import javax.annotation.Resource;
import javax.jws.HandlerChain;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.silverlake.mleb.ccmcb.module.*;
import com.silverlake.mleb.ccmcb.util.SecurityUtil;
import com.silverlake.mleb.ccmcb.util.UserLoginSession;
import com.silverlake.mleb.mcb.bean.ObAccessRestrictionResponse;
import com.silverlake.mleb.mcb.bean.ObAccountOverviewResponse;
import com.silverlake.mleb.mcb.bean.ObAuthenticationResponse;
import com.silverlake.mleb.mcb.bean.ObAuthorizationResponse;
import com.silverlake.mleb.mcb.bean.ObBankListResponse;
import com.silverlake.mleb.mcb.bean.ObBlockMyUserResponse;
import com.silverlake.mleb.mcb.bean.ObCountryListResponse;
import com.silverlake.mleb.mcb.bean.ObDashboardInfoResponse;
import com.silverlake.mleb.mcb.bean.ObDeviceBindingResponse;
import com.silverlake.mleb.mcb.bean.ObDeviceUnbindgResponse;
import com.silverlake.mleb.mcb.bean.ObDocumentDownloadResponse;
import com.silverlake.mleb.mcb.bean.ObFavAccountResponse;
import com.silverlake.mleb.mcb.bean.ObForexResponse;
import com.silverlake.mleb.mcb.bean.ObHolidayResponse;
import com.silverlake.mleb.mcb.bean.ObPaymentResponse;
import com.silverlake.mleb.mcb.bean.ObProfileMaintResponse;
import com.silverlake.mleb.mcb.bean.ObRecurringResponse;
import com.silverlake.mleb.mcb.bean.ObRegistrationResponse;
import com.silverlake.mleb.mcb.bean.ObRetrieveExchangeRateResponse;
import com.silverlake.mleb.mcb.bean.ObRetrieveFaqResponse;
import com.silverlake.mleb.mcb.bean.ObRetrieveNotificationInboxResponse;
import com.silverlake.mleb.mcb.bean.ObSecurity;
import com.silverlake.mleb.mcb.bean.ObSendFeedbackFaqResponse;
import com.silverlake.mleb.mcb.bean.ObSendFeedbackResponse;
import com.silverlake.mleb.mcb.bean.ObSwiftOutboundTrackingResponse;
import com.silverlake.mleb.mcb.bean.ObTNCResponse;
import com.silverlake.mleb.mcb.bean.ObTaskListResponse;
import com.silverlake.mleb.mcb.bean.ObTokenResponse;
import com.silverlake.mleb.mcb.bean.ObTokenTnCResponse;
import com.silverlake.mleb.mcb.bean.ObTransNotiAuthResponse;
import com.silverlake.mleb.mcb.bean.ObTransactionBeneficiaryResponse;
import com.silverlake.mleb.mcb.bean.ObTransactionCurrencyResponse;
import com.silverlake.mleb.mcb.bean.ObTransactionLimitEqUSDResponse;
import com.silverlake.mleb.mcb.bean.ObTransactionResponse;
import com.silverlake.mleb.mcb.bean.ObTransactionSourceOfFundResponse;
import com.silverlake.mleb.mcb.bean.ObTransactionSummaryResponse;
import com.silverlake.mleb.mcb.bean.ObUpdateDeviceAliasResponse;
import com.silverlake.mleb.mcb.bean.RoleList;
import com.silverlake.mleb.mcb.bean.SignerList;
import com.silverlake.mleb.mcb.bean.accountaliases.ObAccountAliasListResponse;
import com.silverlake.mleb.mcb.bean.accountlist.ObAccountListResponse;
import com.silverlake.mleb.mcb.bean.administration.ObAdminBeneficiaryListResponse;
import com.silverlake.mleb.mcb.bean.mgmtbene.ObTransactionManageBeneficiaryResponse;
import com.silverlake.mleb.mcb.bean.timedeposit.ObTimeDepositResponse;
import com.silverlake.mleb.mcb.bean.usermanagement.ObAdministrationUserResponse;
import com.silverlake.mleb.mcb.bean.vctransaction.ObVcTranxResponse;
import com.silverlake.mleb.mcb.module.vc.accountlist.CustomerLoanDatav2;

@WebService(endpointInterface = "com.silverlake.mleb.ccmcb.adapter.MCBInterface")
@HandlerChain(file = "/wsHandle.xml")
public class MCBImpl implements MCBInterface
{
	UserLoginSession userloginsession = new UserLoginSession();
	SecurityUtil secUtil = new SecurityUtil();
	private static Logger log = LogManager.getLogger(MCBImpl.class);
	@Resource
	WebServiceContext wsContext;



	@Override
	public ObAuthenticationResponse retrieveSecInfo(ObSecurity sec, @WebParam(name="primaryActionFlag")String primaryActionFlag)
	{
		RetrieveSecToken rCustProfile = new RetrieveSecToken(wsContext);
		if (null == rCustProfile.getObResponse())
		{
			rCustProfile.processData(primaryActionFlag);
		}
		return rCustProfile.processSend(ObAuthenticationResponse.class);
	}
	
	@Override
	public ObAccessRestrictionResponse retrieveAccessRestriction(ObSecurity sec, @WebParam(name="sourceTrx")String sourceTrx, @WebParam(name="productCode")String productCode, @WebParam(name="productCode")String trxStatus)
	{
		RetrieveAccessRestriction service= new RetrieveAccessRestriction(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(sourceTrx, productCode, trxStatus);
		}
		return service.processSend(ObAccessRestrictionResponse.class);
	}
	

	@Override
	public ObAuthenticationResponse performLogin(ObSecurity sec, String OrgId, String usrId, String pString, String cString, String random_number, String authType, String passwordDataBlock, String ip)
	{
		PerformLoginSession pLogin = new PerformLoginSession(wsContext);
		if (null == pLogin.getObResponse())
		{
			pLogin.processData(OrgId,usrId,pString,cString, random_number, authType, passwordDataBlock, ip);
		}
		return pLogin.processSend(ObAuthenticationResponse.class);
	}
	
	
	@Override
	public ObAuthenticationResponse performLogout(ObSecurity sec)
	{
		PerformLogout logout = new PerformLogout(wsContext);
		if (null == logout.getObResponse())
		{
			logout.processData(false);
		}
		return logout.processSend(ObAuthenticationResponse.class);
	}
	

	@Override
	public ObAuthenticationResponse requestDeviceTAC(ObSecurity sec)
	{
		RequestDeviceTAC requestTAC = new RequestDeviceTAC(wsContext);
		if (null == requestTAC.getObResponse())
		{
			requestTAC.processData();
		}
		return requestTAC.processSend(ObAuthenticationResponse.class);
	}

	@Override
	public ObDeviceBindingResponse retrieveDeviceProfile(ObSecurity sec)
	{
		RetrieveDeviceProfiles dvProfile = new RetrieveDeviceProfiles(wsContext);
		if (null == dvProfile.getObResponse())
		{
			dvProfile.processData();
		}
		return dvProfile.processSend(ObDeviceBindingResponse.class);
	}

	

 

	@Override
	public ObAuthenticationResponse retrieveAccessInfo(ObSecurity sec)
	{
		RetrieveAccessInfo accessInfo = new RetrieveAccessInfo(wsContext);
		if (null == accessInfo.getObResponse())
		{
			accessInfo.processData();
		}
		return accessInfo.processSend(ObAuthenticationResponse.class);
	}

	@Override
	public ObAuthenticationResponse retrieveSessionInfo(ObSecurity sec)
	{
		RetrieveSessionInfo accessInfo = new RetrieveSessionInfo(wsContext);
		if (null == accessInfo.getObResponse())
		{
			accessInfo.processData();
		}
		return accessInfo.processSend(ObAuthenticationResponse.class);
	}

	 
	public ObAuthenticationResponse performDeviceBinding(@WebParam(name="security", header=true)ObSecurity sec,String tac,String unbindIndex)
	{
		PerformDeviceBinding accessInfo = new PerformDeviceBinding(wsContext);
		if (null == accessInfo.getObResponse())
		{
			accessInfo.processData(tac,unbindIndex);
		}
		return accessInfo.processSend(ObAuthenticationResponse.class);
	}
	
	public ObAuthenticationResponse performDeviceUnBindOpen(@WebParam(name="security", header=true)ObSecurity sec, String pString, String cString, String random_number, String approvalUnbindFlag, String passwordDataBlock)
	{
		PerformDeviceUnBindOpen unbind = new PerformDeviceUnBindOpen(wsContext);
		if (null == unbind.getObResponse())
		{
			unbind.processData(pString,cString,random_number, approvalUnbindFlag, passwordDataBlock);
		}
		return unbind.processSend(ObAuthenticationResponse.class);
	}
	
	
	public ObAuthenticationResponse performDeviceUnBind(@WebParam(name="security", header=true)ObSecurity sec, String unbindIndex, String pString, String cString, String random_number, String approvalUnbindFlag, String passwordDataBlock)
	{
		PerformDeviceUnBind unbind = new PerformDeviceUnBind(wsContext);
		if (null == unbind.getObResponse())
		{
			unbind.processData(unbindIndex,pString,cString,random_number, approvalUnbindFlag, passwordDataBlock);
		}
		return unbind.processSend(ObAuthenticationResponse.class);
	}
	
	
	public ObAccountOverviewResponse retrieveAccountList(@WebParam(name="security", header=true)ObSecurity sec,@WebParam(name="accountType")String accountType,@WebParam(name="prod_cd")String prod_cd)
	{
		RetrieveAccountList appModule = new RetrieveAccountList(wsContext);
		if (null == appModule.getObResponse())
		{
			appModule.processData(accountType,prod_cd);
		}
		return appModule.processSend(ObAccountOverviewResponse.class);
	}
 
	
	public ObAuthenticationResponse performChangePassword(@WebParam(name="security", header=true)ObSecurity sec, String pString, String cString,
			  String random_number, String passwordDataBlock)
	{
		PerformChangePassword appModule = new PerformChangePassword(wsContext);
		if (null == appModule.getObResponse())
		{
			appModule.processData(random_number,pString,cString,passwordDataBlock);
		}
		return appModule.processSend(ObAuthenticationResponse.class);
	}

	public ObTaskListResponse retrieveTaskList(@WebParam(name="security", header=true)ObSecurity sec,@WebParam(name="trnxSourcesList")String trnxSourcesList)
	{
		RetrieveTaskList appModule = new RetrieveTaskList(wsContext);
		if (null == appModule.getObResponse())
		{
			appModule.processData(trnxSourcesList);
		}
		return appModule.processSend(ObTaskListResponse.class);
	}
	
	
	public ObDashboardInfoResponse retrieveDashBoardInfo(@WebParam(name="security", header=true)ObSecurity sec, String tncAction)
	{
		RetrieveDashBoardInfo appModule = new RetrieveDashBoardInfo(wsContext);
		if (null == appModule.getObResponse())
		{
			appModule.processData(tncAction);
		}
		return appModule.processSend(ObDashboardInfoResponse.class);
	}


	@Override
	public ObForexResponse onfxStep1(ObSecurity sec) {
		ONFXStep1 service= new ONFXStep1(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData();
		}
		return service.processSend(ObForexResponse.class);
	}
	
	@Override
	public ObForexResponse onfxGetRate(ObSecurity sec, String currency, Boolean isCrossCurrency, String currency2) {
		ONFXGetRate service= new ONFXGetRate(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(currency, isCrossCurrency, currency2);
		}
		return service.processSend(ObForexResponse.class);
	}
	
	@Override
	public ObForexResponse onfxConfirmation(ObSecurity sec, String debitAccountUUID,
			String beneAccountUUID,
			String beneAccountNo,
			String beneAccountName,
			String currency,
			BigDecimal amount,
			String amountCcy,
			String remark,
			String purpose,
			String currency2,
			Boolean isPreValidate) {
		ONFXConfirmation service= new ONFXConfirmation(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(debitAccountUUID,
					beneAccountUUID,
					beneAccountNo,
					beneAccountName,
					currency,
					amount,
					amountCcy,
					remark,
					purpose,
					currency2,
					isPreValidate);
		}
		return service.processSend(ObForexResponse.class);
	}
	
	@Override
	public ObForexResponse onfxPost(ObSecurity sec, String action) {
		ONFXPost service= new ONFXPost(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(action);
		}
		return service.processSend(ObForexResponse.class);
	}
	
	public ObHolidayResponse retrieveHolidayList(@WebParam(name="security", header=true)ObSecurity sec,Integer year)
	{
		RetrieveHolidayList appModule = new RetrieveHolidayList(wsContext);
		if (null == appModule.getObResponse())
		{
			appModule.processData(year);
		}
		return appModule.processSend(ObHolidayResponse.class);
	}
	
	@Override
	public ObTransactionCurrencyResponse retrieveTransactionCurrencyList(ObSecurity sec, String transferServiceType) {
		RetrieveTransactionCurrencyList service= new RetrieveTransactionCurrencyList(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(transferServiceType);
		}
		return service.processSend(ObTransactionCurrencyResponse.class);
	}
	
	@Override
	public ObTransactionSummaryResponse retrieveTransactionAuditTrail(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="pymtMasterId")String pymtMasterId){
		RetrieveTransactionAuditTrail service= new RetrieveTransactionAuditTrail(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(pymtMasterId);
		}
		return service.processSend(ObTransactionSummaryResponse.class);
	}
	
	@Override
	public ObTransactionSummaryResponse retrieveTransactionNotes(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="sourceTrx")String sourceTrx, @WebParam(name="pymtMasterId")String pymtMasterId){
		RetrieveTransactionNotes service= new RetrieveTransactionNotes(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(sourceTrx, pymtMasterId);
		}
		return service.processSend(ObTransactionSummaryResponse.class);
	}
	
	@Override
	public ObTransactionSummaryResponse transactionNoteAdd(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="sourceTrx")String sourceTrx, @WebParam(name="pymtMasterId")String pymtMasterId, @WebParam(name="noteRemark")String noteRemark){
		TransactionNoteAdd service= new TransactionNoteAdd(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(sourceTrx, pymtMasterId, noteRemark);
		}
		return service.processSend(ObTransactionSummaryResponse.class);
	}
	
	@Override
	public ObTransactionSummaryResponse transactionNoteDelete(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="sourceTrx")String sourceTrx, @WebParam(name="noteId")String noteId){
		TransactionNoteDelete service= new TransactionNoteDelete(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(sourceTrx, noteId);
		}
		return service.processSend(ObTransactionSummaryResponse.class);
	}
	
	@Override
	public ObTransactionSummaryResponse retrieveTransactionReceipt(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="pymtMasterId")String pymtMasterId, @WebParam(name="isRetrieveDetails")Boolean isRetrieveDetails, @WebParam(name="is_additional_info")String is_additional_info){
		RetrieveTransactionReceipt service= new RetrieveTransactionReceipt(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(pymtMasterId, isRetrieveDetails, is_additional_info);
		}
		return service.processSend(ObTransactionSummaryResponse.class);
	}
	
	@Override
	public ObTransactionSummaryResponse transactionStatusUpdate(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="sourceTrx")String sourceTrx, @WebParam(name="pymtMasterId")String pymtMasterId, @WebParam(name="index")Integer index, @WebParam(name="trxStatus")String trxStatus, @WebParam(name="validationCheck")String validationCheck){
		TransactionStatusUpdate service= new TransactionStatusUpdate(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(sourceTrx, pymtMasterId, index, trxStatus, validationCheck);
		}
		return service.processSend(ObTransactionSummaryResponse.class);
	}
	
	@Override
	public ObTransactionSourceOfFundResponse transactionSourceOfFund(ObSecurity sec, String transferServiceType, Boolean isSourceOfFund, String currency, String accountNo) {
		TransactionSourceOfFund service= new TransactionSourceOfFund(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(transferServiceType, isSourceOfFund, currency, accountNo);
		}
		return service.processSend(ObTransactionSourceOfFundResponse.class);
	}
	
	@Override
	public ObTransactionBeneficiaryResponse transactionBeneficiaries(ObSecurity sec, String transferServiceType, String accountNo, String accountCcy) {
		TransactionBeneficiaries service= new TransactionBeneficiaries(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(transferServiceType, accountNo, accountCcy);
		}
		return service.processSend(ObTransactionBeneficiaryResponse.class);
	}
	
	@Override
	public ObTransactionBeneficiaryResponse transactionBeneficiaryInquiry(ObSecurity sec, String transferServiceType, String accountNo, String accountCcy,
			String debitAccountNo, String bankCode, String bankName, Boolean isInquiry, String proxy_data, String proxy_type) {
		TransactionBeneficiaryInquiry service= new TransactionBeneficiaryInquiry(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(transferServiceType, accountNo, accountCcy, debitAccountNo, bankCode, bankName, isInquiry, proxy_data, proxy_type);
		}
		return service.processSend(ObTransactionBeneficiaryResponse.class);
	}
	
	@Override
	public ObTransactionResponse transactionFundTransferMenu(ObSecurity sec, List<String> transferServiceTypes) {
		TransactionFundTransferMenu service= new TransactionFundTransferMenu(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(transferServiceTypes);
		}
		return service.processSend(ObTransactionResponse.class);
	}
	
	@Override
	public ObTransactionResponse transactionFundTransferStep1(ObSecurity sec, String transferServiceType) {
		TransactionFundTransferStep1 service= new TransactionFundTransferStep1(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(transferServiceType);
		}
		return service.processSend(ObTransactionResponse.class);
	}
	
	@Override
	public ObTransactionResponse transactionFundTransferValidateDate(ObSecurity sec, String transferServiceType, String valueDate) {
		TransactionFundTransferValidateDate service= new TransactionFundTransferValidateDate(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(transferServiceType, valueDate);
		}
		return service.processSend(ObTransactionResponse.class);
	}
	
	@Override
	public ObTransactionResponse transactionFundTransferSubmit(ObSecurity sec, 
			String transferServiceType,
			String debitAccountUUID,
			String beneAccountUUID,
			String beneAccountNo,
			String beneAccountName,
			String beneAccountCcy,
			String beneAddress1,
			String beneAddress2,
			String beneAddress3,
			String beneBankCode,
			String beneBankBranch,
			String residentStatus,
			String beneCategory,
			String senderName,
			String remitterCategory,
			BigDecimal amount,
			String amountCcy,
			String valueDate,
			String customerReferenceNo,
			String remark,
			Boolean isSaveBene,
			Boolean isFavBene,
			String beneAliasName,
			Boolean isSendNotificationSender,
			Boolean isNotifySenderCompleted,
			Boolean isNotifySenderRejected,
			Boolean isNotifySenderSuspected,
			String notificationSenderEmail,
			Boolean isSendNotificationBene,
			String notificationBeneEmail,
			Boolean isRecurring,
			String recurringType,
			String recurringValue,
			String recurringStartDate,
			String recurringEndDate,
			String fxType,
			String fxDealerName,
			BigDecimal fxDealerRate,
			String fxContractNo,
			BigDecimal fxRate,
			@WebParam(name="beneBankName")String beneBankName,
			@WebParam(name="beneBankCountryCode")String beneBankCountryCode,
			@WebParam(name="beneBankAddress1") String beneBankAddress1,
			@WebParam(name="beneBankAddress2") String beneBankAddress2,
			@WebParam(name="beneBankAddress3") String beneBankAddress3,
			@WebParam(name="senderCountryCode") String senderCountryCode,
			@WebParam(name="beneCountryCode") String beneCountryCode,
			@WebParam(name="beneRelationshipStatus") String beneRelationshipStatus,
			@WebParam(name="paymentPurpose") String paymentPurpose,
			@WebParam(name="chargesMethod") String chargesMethod,
			String beneID,
			String beneBankNetworkClearingCode,
			String beneBankCity,
			String exec_time_batch_cd,
			String recurring_exec_time_batch_cd,
			String additional_info,
			String bene_bank_id,
			String trx_purpose,
			String charges_acct_no,
			String charges_acct_ccy,
			String sender_address1,
			String sender_address2,
			String sender_address3,
			String ip) {
		TransactionFundTransferSubmit service= new TransactionFundTransferSubmit(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(transferServiceType,
					debitAccountUUID,
					beneAccountUUID,
					beneAccountNo,
					beneAccountName,
					beneAccountCcy,
					beneAddress1,
					beneAddress2,
					beneAddress3,
					beneBankCode,
					beneBankBranch,
					residentStatus,
					beneCategory,
					senderName,
					remitterCategory,
					amount,
					amountCcy,
					valueDate,
					customerReferenceNo,
					remark,
					isSaveBene,
					isFavBene,
					beneAliasName,
					isSendNotificationSender,
					isNotifySenderCompleted,
					isNotifySenderRejected,
					isNotifySenderSuspected,
					notificationSenderEmail,
					isSendNotificationBene,
					notificationBeneEmail,
					isRecurring,
					recurringType,
					recurringValue,
					recurringStartDate,
					recurringEndDate,
					fxType,
					fxDealerName,
					fxDealerRate,
					fxContractNo,
					fxRate,
					beneBankName,
					beneBankCountryCode,
					beneBankAddress1,
					beneBankAddress2,
					beneBankAddress3,
					senderCountryCode,
					beneCountryCode,
					beneRelationshipStatus,
					paymentPurpose,
					chargesMethod,
					beneID,
					beneBankNetworkClearingCode,
					beneBankCity,
					exec_time_batch_cd,
					recurring_exec_time_batch_cd,
					additional_info,
					bene_bank_id,
					trx_purpose,
					charges_acct_no,
					charges_acct_ccy,
					sender_address1,
					sender_address2,
					sender_address3,
					ip);
		}
		return service.processSend(ObTransactionResponse.class);
	}
	
	@Override
	public ObTransactionManageBeneficiaryResponse transactionManageBeneficiaryStep1(ObSecurity sec) {
		TransactionManageBeneficiaryStep1 service= new TransactionManageBeneficiaryStep1(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData();
		}
		return service.processSend(ObTransactionManageBeneficiaryResponse.class);
	}
	
	@Override
	public ObTransactionManageBeneficiaryResponse transactionManageBeneficiaryDetails(ObSecurity sec, String beneId) {
		TransactionManageBeneficiaryDetails service= new TransactionManageBeneficiaryDetails(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(beneId);
		}
		return service.processSend(ObTransactionManageBeneficiaryResponse.class);
	}
	
	@Override
	public ObTransactionManageBeneficiaryResponse transactionManageBeneficiaryEdit(ObSecurity sec, String beneId, String nickName) {
		TransactionManageBeneficiaryEdit service= new TransactionManageBeneficiaryEdit(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(beneId, nickName);
		}
		return service.processSend(ObTransactionManageBeneficiaryResponse.class);
	}
	
	@Override
	public ObTransactionManageBeneficiaryResponse transactionManageBeneficiaryDelete(ObSecurity sec, String beneId) {
		TransactionManageBeneficiaryDelete service= new TransactionManageBeneficiaryDelete(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(beneId);
		}
		return service.processSend(ObTransactionManageBeneficiaryResponse.class);
	}
	
	@Override
	public ObTransactionManageBeneficiaryResponse transactionManageBeneficiarySetFavorite(ObSecurity sec, String beneId, Boolean isFavorite) {
		TransactionManageBeneficiarySetFavorite service= new TransactionManageBeneficiarySetFavorite(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(beneId, isFavorite);
		}
		return service.processSend(ObTransactionManageBeneficiaryResponse.class);
	}
	
	@Override
	public ObTransactionManageBeneficiaryResponse transactionManageBeneficiaryAdd(ObSecurity sec, @WebParam(name="transferServiceType")String transferServiceType,
			@WebParam(name="accountNo")String accountNo,
			@WebParam(name="accountName")String accountName,
			@WebParam(name="accountCcy")String accountCcy,
			@WebParam(name="nickName")String nickName,
			@WebParam(name="email")String email,
			@WebParam(name="phoneNumber")String phoneNumber,
			@WebParam(name="isShared")String isShared,
			@WebParam(name="beneAddress1")String beneAddress1,
			@WebParam(name="beneAddress2")String beneAddress2,
			@WebParam(name="beneAddress3")String beneAddress3,
			@WebParam(name="bankCountryCode")String bankCountryCode,
			@WebParam(name="bankName")String bankName,
			@WebParam(name="networkCode")String networkCode,
			@WebParam(name="bankCode")String bankCode,
			@WebParam(name="bankBranch")String bankBranch,
			@WebParam(name="rtgsMemberCode")String rtgsMemberCode,
			@WebParam(name="participantBic")String participantBic) {
		TransactionManageBeneficiaryAdd service= new TransactionManageBeneficiaryAdd(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(transferServiceType,
					accountNo,
					accountName,
					accountCcy,
					nickName,
					email,
					phoneNumber,
					isShared,
					beneAddress1,
					beneAddress2,
					beneAddress3,
					bankCountryCode,
					bankName,
					networkCode,
					bankCode,
					bankBranch,
					rtgsMemberCode,
					participantBic);
		}
		return service.processSend(ObTransactionManageBeneficiaryResponse.class);
	}
	
	@Override
	public ObPaymentResponse paymentStep1(ObSecurity sec, String billerType) {
		PaymentStep1 service= new PaymentStep1(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(billerType);
		}
		return service.processSend(ObPaymentResponse.class);
	}
	
	@Override
	public ObPaymentResponse paymentRetrieveBillerList(ObSecurity sec, String billerType) {
		PaymentRetrieveBillerList service= new PaymentRetrieveBillerList(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(billerType);
		}
		return service.processSend(ObPaymentResponse.class);
	}
	
	@Override
	public ObPaymentResponse paymentRetrievePayeeList(ObSecurity sec, String groupName, String billerCode) {
		PaymentRetrievePayeeList service= new PaymentRetrievePayeeList(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(groupName, billerCode);
		}
		return service.processSend(ObPaymentResponse.class);
	}
	
	@Override
	public ObPaymentResponse paymentInquiryBiller(ObSecurity sec,
			Integer flowType,
			String billerType,
			String debitAccountUUID,
			String billerId,
			String billerCode,
			String billingId,
			BigDecimal amount,
			String valueDate,
			String customerRef,
			String ppType) {
		PaymentInquiryBiller service= new PaymentInquiryBiller(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(flowType,
					billerType,
					debitAccountUUID,
					billerId,
					billerCode,
					billingId,
					amount,
					valueDate,
					customerRef,
					ppType);
		}
		return service.processSend(ObPaymentResponse.class);
	}
	
	@Override
	public ObPaymentResponse paymentSubmit(ObSecurity sec,
			String billerType,
			String debitAccountUUID,
			String billerName,
			String amountOptionId,
			BigDecimal amount,
			String valueDate,
			String customerReferenceNo,
			Boolean isSavePayee,
			String payeeNickName,
			Boolean isSendNotificationSender,
			Boolean isNotifySenderCompleted,
			Boolean isNotifySenderRejected,
			Boolean isNotifySenderSuspected,
			String notificationSenderEmail,
			Boolean isRecurring,
			String recurringType,
			String recurringValue,
			String recurringStartDate,
			String recurringEndDate,
			String exec_time_batch_cd,
			String recurring_exec_time_batch_cd,
			String ip,
			String lang
			) {
		PaymentSubmit service= new PaymentSubmit(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(
					billerType,
					debitAccountUUID,
					billerName,
					amountOptionId,
					amount,
					valueDate,
					customerReferenceNo,
					isSavePayee,
					payeeNickName,
					isSendNotificationSender,
					isNotifySenderCompleted,
					isNotifySenderRejected,
					isNotifySenderSuspected,
					notificationSenderEmail,
					isRecurring,
					recurringType,
					recurringValue,
					recurringStartDate,
					recurringEndDate,
					exec_time_batch_cd,
					recurring_exec_time_batch_cd,
					ip,
					lang);
		}
		return service.processSend(ObPaymentResponse.class);
	}
	
	public ObVcTranxResponse retrieveFutureTranx(@WebParam(name="security", header=true)ObSecurity sec, String nDays,@WebParam(name="pageNo")String pageNo)
	{
		RetrieveFutureTranx appModule = new RetrieveFutureTranx(wsContext);
		if (null == appModule.getObResponse())
		{
			appModule.processData(nDays,pageNo);
		}
		return appModule.processSend(ObVcTranxResponse.class);
	}
	
	public ObTaskListResponse retrieveTaskTranx(@WebParam(name="security", header=true)ObSecurity sec,@WebParam(name="trnxSources")String trnxSources,@WebParam(name="status")String status, @WebParam(name="prdCodeList")String prdCodeList, @WebParam(name="pageNo")String pageNo, @WebParam(name="pageSize")String pageSize,@WebParam(name="valueDateFrom")String valueDateFrom, @WebParam(name="valueDateTo")String valueDateTo, @WebParam(name="sourceFileName")String sourceFileName,@WebParam(name="uploadDateFrom")String uploadDateFrom, @WebParam(name="uploadDateTo")String uploadDateTo, @WebParam(name="module")String module)
	{
		RetrieveTaskTransaction appModule = new RetrieveTaskTransaction(wsContext);
		if (null == appModule.getObResponse())
		{
			appModule.processData(trnxSources,status,prdCodeList,pageNo,pageSize, valueDateFrom, valueDateTo, sourceFileName, uploadDateFrom, uploadDateTo, module);
		}
		return appModule.processSend(ObTaskListResponse.class);
	}
	
	public ObRetrieveFaqResponse retrieveFaq(@WebParam(name="security", header=true)ObSecurity sec,String faqCatCd) {
		RetrieveFaq service= new RetrieveFaq(wsContext);
		if (null == service.getObResponse())
		{
			service.processData(faqCatCd);
		}
		return service.processSend(ObRetrieveFaqResponse.class);
	}
	
	public ObSendFeedbackFaqResponse sendFeedbackFaq(@WebParam(name="security", header=true)ObSecurity sec, String faq_id, String feedback, String org_cd, String usr_cd) {
		SendFeedbackFaq service= new SendFeedbackFaq(wsContext);
		if (null == service.getObResponse())
		{
			service.processData(faq_id, feedback, org_cd, usr_cd);
		}
		return service.processSend(ObSendFeedbackFaqResponse.class);
	}
	
	@Override
	public ObSendFeedbackResponse sendFeedbackBase64(ObSecurity sec, 
			String email, 
			String phone, 
			String category, 
			String feedback, 
			String fileName,
			String fileType,
			String fileData)
	{
		SendFeedbackBase64 service= new SendFeedbackBase64(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(email, phone, category, feedback, fileName, fileType, fileData);
		}
		return service.processSend(ObSendFeedbackResponse.class);
	}
	
	@Override
	public ObSendFeedbackResponse sendFeedback(ObSecurity sec, 
			String email, 
			String phone, 
			String category, 
			String feedback, 
			String fileName,
			String fileType,
			Boolean isBase64,
			DataHandler fileData)
	{
		SendFeedback service= new SendFeedback(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(email, phone, category, feedback, fileName, fileType, isBase64, fileData);
		}
		return service.processSend(ObSendFeedbackResponse.class);
	}
	
	public ObAccountOverviewResponse retrieveAccountDetails(@WebParam(name="security", header=true)ObSecurity sec,@WebParam(name="index")String index )
	{
		RetrieveAccountDetails service= new RetrieveAccountDetails(wsContext);
		if (null == service.getObResponse())
		{
			service.processData(index);
		}
		return service.processSend(ObAccountOverviewResponse.class);
	}

	public ObAccountOverviewResponse retrieveAccountHist(@WebParam(name="security", header=true)ObSecurity sec,@WebParam(name="index")String index ,@WebParam(name="noDays")String noDays,@WebParam(name="fromDate")String fromDate,@WebParam(name="toDate")String toDate,@WebParam(name="pageNo")String pageNo)
	{
		RetrieveAccountHist service= new RetrieveAccountHist(wsContext);
		if (null == service.getObResponse())
		{
			service.processData(index,noDays,fromDate,toDate,pageNo);
		}
		return service.processSend(ObAccountOverviewResponse.class);
	}

	public ObAccountOverviewResponse retrieveAccountStatementFile(@WebParam(name="security", header=true)ObSecurity sec,@WebParam(name="index")String index ,@WebParam(name="noDays")String noDays,@WebParam(name="fromDate")String fromDate,@WebParam(name="toDate")String toDate)
	{
		RetrieveAccountStatementFile service= new RetrieveAccountStatementFile(wsContext);
		if (null == service.getObResponse())
		{
			service.processData(index,noDays,fromDate,toDate);
		}
		return service.processSend(ObAccountOverviewResponse.class);
	}
	
	public ObRetrieveNotificationInboxResponse retrieveBoardNotificationInbox(@WebParam(name="security", header=true)ObSecurity sec,@WebParam(name="notifyType")String notify_type,@WebParam(name="pageNo")String page_no,@WebParam(name="pageSize")String page_size)
	{
		RetrieveBoardNotificationInbox service= new RetrieveBoardNotificationInbox(wsContext);
		if (null == service.getObResponse())
		{
			service.processData(notify_type, page_no, page_size);
		}
		return service.processSend(ObRetrieveNotificationInboxResponse.class);
	}
	
	public ObRetrieveNotificationInboxResponse retrievePushNotificationInbox(@WebParam(name="security", header=true)ObSecurity sec,@WebParam(name="pageNo")String page_no,@WebParam(name="pageSize")String page_size)
	{
		RetrievePushNotificationInbox service= new RetrievePushNotificationInbox(wsContext);
		if (null == service.getObResponse())
		{
			service.processData(page_no, page_size);
		}
		return service.processSend(ObRetrieveNotificationInboxResponse.class);
	}

	public ObRetrieveExchangeRateResponse retrieveExchangeRate(@WebParam(name="security", header=true)ObSecurity sec,@WebParam(name="ccy_code")String ccy_code,@WebParam(name="period_from")String period_from,@WebParam(name="period_to")String period_to)
	{
		RetrieveExchangeRate appModule = new RetrieveExchangeRate(wsContext);
		if (null == appModule.getObResponse())
		{
			appModule.processData(ccy_code,period_from,period_to);
		}
		return appModule.processSend(ObRetrieveExchangeRateResponse.class);
	}
	
	public ObRetrieveExchangeRateResponse retrieveIndividualExchangeRate(ObSecurity sec, String sellCcy, String buyCcy) {
		RetrieveIndividualExchangeRate service= new RetrieveIndividualExchangeRate(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(sellCcy, buyCcy);
		}
		return service.processSend(ObRetrieveExchangeRateResponse.class);
	}
	
	@Override
	public ObAuthorizationResponse retrieveTransactionDataList(ObSecurity sec, List<String> source_trx, String pymtMasterId, String trx_status, String value_date_from, String value_date_to, String pageSize, String page_no, List<String> prodCode) {
		RetrieveTransactionDataList service= new RetrieveTransactionDataList(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(source_trx, pymtMasterId, trx_status, value_date_from, value_date_to, pageSize, page_no, prodCode);
		}
		return service.processSend(ObAuthorizationResponse.class);
	}
	
	public ObTaskListResponse retrieveTransactionSummary(@WebParam(name="security", header=true)ObSecurity sec,@WebParam(name="trnxSources")String trnxSources, @WebParam(name="masterId")String masterId, @WebParam(name="trxStatus")String trxStatus)
	{
		RetrieveTransactionSummary appModule = new RetrieveTransactionSummary(wsContext);
		if (null == appModule.getObResponse())
		{
			appModule.processData(trnxSources,masterId, trxStatus);
		}
		return appModule.processSend(ObTaskListResponse.class);
	}
	
	public ObAuthorizationResponse retrieveAuthTransaction(@WebParam(name="security", header=true)ObSecurity sec,@WebParam(name="pageNo")String pageNo,@WebParam(name="pageSize")String pageSize,@WebParam(name="source_trx")List<String> source_trx,@WebParam(name="trx_status")String trx_status,@WebParam(name="value_date_from")String value_date_from,@WebParam(name="value_date_to")String value_date_to, @WebParam(name="pymtMasterId")String pymtMasterId, @WebParam(name="uploadDateFrom")String uploadDateFrom, @WebParam(name="uploadDateTo")String uploadDateTo, @WebParam(name="prodCode")String prodCode)
	{
		RetrieveAuthTransaction service= new RetrieveAuthTransaction(wsContext);
		if (null == service.getObResponse())
		{
			service.processData(pageNo,pageSize,source_trx,trx_status,value_date_from,value_date_to,pymtMasterId, uploadDateFrom, uploadDateTo, prodCode);
		}
		return service.processSend(ObAuthorizationResponse.class);
	}
	
	public ObAuthorizationResponse performAuthConfirmation(@WebParam(name="security", header=true)ObSecurity sec,@WebParam(name="pageNo")String page_no,@WebParam(name="pageSize")String page_size,@WebParam(name="source_trx")List<String> source_trx,@WebParam(name="trx_status")String trx_status,@WebParam(name="value_date_from")String value_date_from,@WebParam(name="value_date_to")String value_date_to,@WebParam(name="index")String index)
	{
		PerformAuthorizeConfirmation service= new PerformAuthorizeConfirmation(wsContext);
		if (null == service.getObResponse())
		{
			service.processData(page_no,page_size,source_trx,trx_status,value_date_from,value_date_to,index);
		}
		return service.processSend(ObAuthorizationResponse.class);
	}
	
	public ObAuthorizationResponse preAuthorizeTransaction(@WebParam(name="security", header=true)ObSecurity sec,@WebParam(name="idTransaction")String idTransaction,@WebParam(name="action_cd")String action_cd,@WebParam(name="index")String index, @WebParam(name="sourceTrx")String sourceTrx,@WebParam(name="isParentLevel")Boolean isParentLevel)
	{
		PreAuthorizeTransaction service= new PreAuthorizeTransaction(wsContext);
		if (null == service.getObResponse())
		{
			service.processData(idTransaction, action_cd, index, sourceTrx, isParentLevel);
		}
		return service.processSend(ObAuthorizationResponse.class);
	}
	
	@Override
	public ObAuthorizationResponse requestAuthorizationTAC(ObSecurity sec, @WebParam(name="request_type")String request_type)
	{
		RequestAuthorizationTAC requestTAC = new RequestAuthorizationTAC(wsContext);
		if (null == requestTAC.getObResponse())
		{
			requestTAC.processData(request_type);
		}
		return requestTAC.processSend(ObAuthorizationResponse.class);
	}
	
	@Override
	public ObAuthenticationResponse requestChallengeResponse(ObSecurity sec, String requestType)
	{
		RequestChallengeResponse service= new RequestChallengeResponse(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(requestType);
		}
		return service.processSend(ObAuthenticationResponse.class);
	}
	
	@Override
	public ObAuthenticationResponse verifyChallengeResponse(ObSecurity sec, String requestId, String responseCode)
	{
		VerifyChallengeResponse service= new VerifyChallengeResponse(wsContext);
		if (null == service.getObResponse())
		{
			service.setCRData(requestId, responseCode);
			service.processGenericData();
		}
		return service.processSend(ObAuthenticationResponse.class);
	}
	
	@Override
	public ObAuthorizationResponse performAuthorizeTransaction(@WebParam(name="security", header=true)ObSecurity sec,@WebParam(name="idTransaction")String idTransaction,@WebParam(name="responseCode")String responseCode)
	{
		PerformAuthorizeTransaction service= new PerformAuthorizeTransaction(wsContext);
		if (null == service.getObResponse())
		{
			service.processData(idTransaction,responseCode);
		}
		return service.processSend(ObAuthorizationResponse.class);
	}
	
	@Override
	public ObTNCResponse retrieveTnC(ObSecurity sec, String tncType, @WebParam(name="isLocal")Boolean isLocal)
	{
		RetrieveTnC service= new RetrieveTnC(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(tncType, isLocal);
		}
		return service.processSend(ObTNCResponse.class);
	}
	
	@Override
	public ObBankListResponse retrieveBankList(ObSecurity sec, 
			String transferServiceType, 
			String bankCode,
			String branchName,
			String countryCode,
			String networkClearingCode)
	{
		RetrieveBankList service= new RetrieveBankList(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(transferServiceType, bankCode, branchName, countryCode, networkClearingCode);
		}
		return service.processSend(ObBankListResponse.class);
	}
	
	@Override
	public ObBankListResponse retrieveBankSwiftDetails(ObSecurity sec,
			String networkClearingCode)
	{
		RetrieveBankSwiftDetails service= new RetrieveBankSwiftDetails(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(networkClearingCode);
		}
		return service.processSend(ObBankListResponse.class);
	}
	
	@Override
	public ObCountryListResponse retrieveCountryList(ObSecurity sec)
	{
		RetrieveCountryList service= new RetrieveCountryList(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData();
		}
		return service.processSend(ObCountryListResponse.class);
	}
	
	@Override
	public ObTNCResponse tnCConfirmation(ObSecurity sec, String tncType, String refNo)
	{
		TnCConfirmation service= new TnCConfirmation(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(tncType, refNo);
		}
		return service.processSend(ObTNCResponse.class);
	}
	
	public ObTokenResponse requestCustomerTokenInquiry(ObSecurity sec)
	{
		CustomerTokenInquiryRequest requestTAC = new CustomerTokenInquiryRequest(wsContext);
		if (null == requestTAC.getObResponse())
		{
			requestTAC.processData();
		}
		return requestTAC.processSend(ObTokenResponse.class);
	}

	@Override
	public ObTokenResponse requestSwitchDefaultToken(ObSecurity sec, String token_type_old, String token_type_new) {
		SwitchDefaultActiveTokenRequest requestTAC = new SwitchDefaultActiveTokenRequest(wsContext);
		if (null == requestTAC.getObResponse())
		{
			requestTAC.processData(token_type_old, token_type_new);
		}
		return requestTAC.processSend(ObTokenResponse.class);
	}


	@Override
	public ObTokenResponse requestSoftwareTokenPreBind1(ObSecurity sec, String device_finger_print) 
	{
		SoftwareTokenPrebind1Request requestTAC = new SoftwareTokenPrebind1Request(wsContext);
		if (null == requestTAC.getObResponse())
		{
			requestTAC.processData(device_finger_print);
		}
		return requestTAC.processSend(ObTokenResponse.class);
	}


	@Override
	public ObTokenResponse requestSoftwareTokenPreBind2(ObSecurity sec, String device_code, String device_finger_print) 
	{
		SoftwareTokenPreBind2Request requestTAC = new SoftwareTokenPreBind2Request(wsContext);
		if (null == requestTAC.getObResponse())
		{
			requestTAC.processData(device_code, device_finger_print);
		}
		return requestTAC.processSend(ObTokenResponse.class);
	}


	@Override
	public ObTokenResponse requestSoftwareTokenPostBind(ObSecurity sec, String device_finger_print, String otip_type,
			String appli_name, Integer appli_no, Integer seq_no) 
	{
		SoftwareTokenPostBindRequest requestTAC = new SoftwareTokenPostBindRequest(wsContext);
		if (null == requestTAC.getObResponse())
		{
			requestTAC.processData(device_finger_print, otip_type, appli_name, appli_no, seq_no);
		}
		return requestTAC.processSend(ObTokenResponse.class);
	}
	
	@Override
	public ObTokenResponse requestSoftwareTokenDelete(ObSecurity sec, String device_finger_print) 
	{
		SoftwareTokenDeleteRequest requestTAC = new SoftwareTokenDeleteRequest(wsContext);
		if (null == requestTAC.getObResponse())
		{
			requestTAC.processData(device_finger_print);
		}
		return requestTAC.processSend(ObTokenResponse.class);
	}
	
	public ObTokenResponse requestSoftwareTokenEligibility(ObSecurity sec)
	{
		SoftwareTokenEligibilityRequest requestTAC = new SoftwareTokenEligibilityRequest(wsContext);
		if (null == requestTAC.getObResponse())
		{
			requestTAC.processData();
		}
		return requestTAC.processSend(ObTokenResponse.class);
	}
	
	public ObTokenTnCResponse requestSoftwareTokenTnC(ObSecurity sec)
	{
		SoftwareTokenTnCRequest requestTAC = new SoftwareTokenTnCRequest(wsContext);
		if (null == requestTAC.getObResponse())
		{
			requestTAC.processData();
		}
		return requestTAC.processSend(ObTokenTnCResponse.class);
	}
	
	@Override
	public ObTokenResponse updateEnrolSwToken(ObSecurity sec) {
		UpdateEnrolSwToken requestTAC = new UpdateEnrolSwToken(wsContext);
		if (null == requestTAC.getObResponse())
		{
			requestTAC.processData();
		}
		return requestTAC.processSend(ObTokenResponse.class);
	}
	
	public ObAuthenticationResponse performUpdateTNC(@WebParam(name="security", header=true)ObSecurity sec,@WebParam(name="tncAction")String tncAction)
	{
		PerformUpdateTNC service= new PerformUpdateTNC(wsContext);
		if (null == service.getObResponse())
		{
			service.processData(tncAction);
		}
		return service.processSend(ObAuthenticationResponse.class);
	}
	
	public ObTokenResponse otpRequest(ObSecurity sec, @WebParam(name="request_type")String request_type)
	{
		OTPRequest requestTAC = new OTPRequest(wsContext);
		if (null == requestTAC.getObResponse())
		{
			requestTAC.processData(request_type);
		}
		return requestTAC.processSend(ObTokenResponse.class);
	}
	
	public ObTokenResponse otpVerify(ObSecurity sec, @WebParam(name="otp")String otp, @WebParam(name="requestId_stats")String requestId_stats)
	{
		OTPVerify requestTAC = new OTPVerify(wsContext);
		if (null == requestTAC.getObResponse())
		{
			requestTAC.processData(otp, requestId_stats);
		}
		return requestTAC.processSend(ObTokenResponse.class);
	}
	
	public ObTaskListResponse retrieveTransactionStatus(@WebParam(name="security", header=true)ObSecurity sec,@WebParam(name="trnxSources")String trnxSources, @WebParam(name="masterId")String masterId )
	{
		RetrieveTransactionStatus service= new RetrieveTransactionStatus(wsContext);
		if (null == service.getObResponse())
		{
			service.processData(trnxSources,masterId);
		}
		return service.processSend(ObTaskListResponse.class);
	}


	@Override
	public ObAuthenticationResponse performResetPassword(ObSecurity sec,String usr_cd,String org_cd,String email, String phone) {
		PerformResetPassword appModule = new PerformResetPassword(wsContext);
		if (null == appModule.getObResponse())
		{
			appModule.processData(org_cd,usr_cd,email,phone);
		}
		return appModule.processSend(ObAuthenticationResponse.class);
	}
	
	public ObAuthenticationResponse retrieveResetPassTnC(ObSecurity sec)
	{
		RetrieveResetPassTnC appModule = new RetrieveResetPassTnC(wsContext);
		if (null == appModule.getObResponse())
		{
			appModule.processData();
		}
		return appModule.processSend(ObAuthenticationResponse.class);
	}
	
	
	
	public ObDashboardInfoResponse retrieveAccountFavList(ObSecurity sec)
	{
		RetrieveAccountFavList appModule = new RetrieveAccountFavList(wsContext);
		if (null == appModule.getObResponse())
		{
			appModule.processData();
		}
		return appModule.processSend(ObDashboardInfoResponse.class);
	}
	public ObFavAccountResponse performUpdateFavAccount(ObSecurity sec,String favIndexList , String fav_prd_cd)
	{
		PerformUpdateFavAccount appModule = new PerformUpdateFavAccount(wsContext);
		if (null == appModule.getObResponse())
		{
			appModule.processData(favIndexList,fav_prd_cd);
		}
		return appModule.processSend(ObFavAccountResponse.class);
	}
	
	public ObRecurringResponse retrieveRecurringListFt( ObSecurity sec,  String pageNo,  String list_prod_cd,  String amount,  String debit_acct_no, String bene_acct_no, String showNewest, String recurringId)
	{
		RetrieveRecurringListFT appModule = new RetrieveRecurringListFT(wsContext);
		if (null == appModule.getObResponse())
		{
			appModule.processData(pageNo,list_prod_cd,amount,debit_acct_no,bene_acct_no,showNewest,recurringId);
		}
		return appModule.processSend(ObRecurringResponse.class);
	}
	
	public ObRecurringResponse retrieveRecurringListPymt( ObSecurity sec,  String pageNo,  String billerCode,  String amount,  String debit_acct_no, String billingId,  String showNewest, String recurringId)
	{
		RetrieveRecurringListPYMT appModule = new RetrieveRecurringListPYMT(wsContext);
		if (null == appModule.getObResponse())
		{
			appModule.processData(pageNo,billerCode,amount,debit_acct_no,billingId,showNewest,recurringId);
		}
		return appModule.processSend(ObRecurringResponse.class);
	}
	
	public ObRecurringResponse updateRecurring(ObSecurity sec, String prod_cd, String recurring_id, String delete_type)
	{
		UpdateRecurring appModule = new UpdateRecurring(wsContext);
		if (null == appModule.getObResponse())
		{
			appModule.processData(prod_cd,recurring_id,delete_type);
		}
		return appModule.processSend(ObRecurringResponse.class);
	}
	
	@Override
	public ObAuthenticationResponse performBiometricSetup(ObSecurity sec, String action, String type, String orgCode, String usrCode)
	{
		PerformBiometricSetup service= new PerformBiometricSetup(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(action, type, orgCode, usrCode);
		}
		return service.processSend(ObAuthenticationResponse.class);
	}
	
	@Override
	public ObAuthenticationResponse performVerifyPassword(ObSecurity sec, String pString, String cString, String randomNumber, String passwordDataBlock)
	{
		PerformVerifyPassword service= new PerformVerifyPassword(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(pString, cString, randomNumber, passwordDataBlock);
		}
		return service.processSend(ObAuthenticationResponse.class);
	}
	
	@Override
	public ObTimeDepositResponse timeDepositAccountOpeningConfirmation(@WebParam(name="security", header=true)ObSecurity sec,
			@WebParam(name="debitAccountUUID")String debitAccountUUID,
			@WebParam(name="purposeCode")String purposeCode,
			@WebParam(name="sourceOfFundCode")String sourceOfFundCode,
			@WebParam(name="rolloverTypeCode")String rolloverTypeCode,
			@WebParam(name="amount")BigDecimal amount,
			@WebParam(name="amountCcy")String amountCcy,
			@WebParam(name="tenor")Integer tenor,
			@WebParam(name="tenorType")String tenorType,
			@WebParam(name="interestTerm")Integer interestTerm,
			@WebParam(name="interestTermCode")String interestTermCode,
			@WebParam(name="promoCode")String promoCode) {
		TimeDepositAccountOpeningConfirmation service= new TimeDepositAccountOpeningConfirmation(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(debitAccountUUID,
					purposeCode,
					sourceOfFundCode,
					rolloverTypeCode,
					amount,
					amountCcy,
					tenor,
					tenorType,
					interestTerm,
					interestTermCode,
					promoCode);
		}
		return service.processSend(ObTimeDepositResponse.class);
	}
	
	@Override
	public ObTimeDepositResponse timeDepositAccountOpeningAcknowledgement(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="transactionId")String transactionId, @WebParam(name="ip")String ip) {
		TimeDepositAccountOpeningAcknowledgement service= new TimeDepositAccountOpeningAcknowledgement(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(transactionId, ip);
		}
		return service.processSend(ObTimeDepositResponse.class);
	}

	@Override
	public ObTimeDepositResponse timeDepositAccountOpeningStep1(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="transactionId")Boolean isCheckCutOff, @WebParam(name="productCode")String productCode) {
		TimeDepositAccountOpeningStep1 service = new TimeDepositAccountOpeningStep1(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(isCheckCutOff, productCode);
		}
		return service.processSend(ObTimeDepositResponse.class);
	}

	@Override
	public ObTimeDepositResponse timeDepositAccountOpeningProductList(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="currencyCode")String currencyCode) {
		TimeDepositAccountOpeningProductList service = new TimeDepositAccountOpeningProductList(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(currencyCode);
		}
		return service.processSend(ObTimeDepositResponse.class);
	}
	
	@Override
	public ObTimeDepositResponse timeDepositAccountMaintenanceDetails(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="debitAccountUUID")String debitAccountUUID) {
		TimeDepositAccountMaintenanceDetails service = new TimeDepositAccountMaintenanceDetails(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(debitAccountUUID);
		}
		return service.processSend(ObTimeDepositResponse.class);
	}
	
	@Override
	public ObTimeDepositResponse timeDepositAccountMaintenanceSubmit(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="accountNo")String accountNo, @WebParam(name="rolloverTypeCode")String rolloverTypeCode, @WebParam(name="accountCcy")String accountCcy, @WebParam(name="ip")String ip) {
		TimeDepositAccountMaintenanceSubmit service = new TimeDepositAccountMaintenanceSubmit(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(accountNo, rolloverTypeCode, accountCcy, ip);
		}
		return service.processSend(ObTimeDepositResponse.class);
	}
	
	
	@Override
	public ObAccountListResponse customerAccountList(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="searchCustNo")String searchCustNo, @WebParam(name="searchCustName")String searchCustName, @WebParam(name="productCode")String productCode, @WebParam(name="pageSize")String pageSize, @WebParam(name="pageNo")String pageNo) {
		CustomerAccountList service = new CustomerAccountList(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(searchCustNo, searchCustName, productCode, pageSize, pageNo);
		}
		return service.processSend(ObAccountListResponse.class);
	}
	
	@Override
	public ObAccountListResponse customerPortfolioData(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="customerNumber")String customerNumber, @WebParam(name="productCode")String productCode, @WebParam(name="agreementOnly")Boolean agreementOnly) {
		CustomerPortfolioData service = new CustomerPortfolioData(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(customerNumber, productCode, agreementOnly);
		}
		return service.processSend(ObAccountListResponse.class);
	}
	
	@Override
	public ObAccountListResponse customerPortfolioDownload(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="customerNumber")String customerNumber, @WebParam(name="productCode")String productCode, @WebParam(name="periodFrom")String periodFrom, @WebParam(name="periodTo")String periodTo, @WebParam(name="customer_loan_data")List<CustomerLoanDatav2> customer_loan_data) {
		CustomerPortfolioDownload service = new CustomerPortfolioDownload(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(customerNumber, productCode, periodFrom, periodTo, customer_loan_data);
		}
		return service.processSend(ObAccountListResponse.class);
	}

	
	@Override
	public ObAccountAliasListResponse administrationAccountAliasList(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="accountNumber")String accountNumber, @WebParam(name="accountName")String accountName, @WebParam(name="aliasName")String aliasName, @WebParam(name="pageNo")String pageNo, @WebParam(name="pageSize")String pageSize) {
		AdministrationAccountAliasList service = new AdministrationAccountAliasList(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(accountNumber, accountName, aliasName, pageNo, pageSize);
		}
		return service.processSend(ObAccountAliasListResponse.class);
	}
	
	@Override
	public ObAccountAliasListResponse administrationAccountAliasMaintenance(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="accountId")String accountId, @WebParam(name="currencyCode")String currencyCode, @WebParam(name="aliasName")String aliasName) {
		AdministrationAccountAliasMaintenance service = new AdministrationAccountAliasMaintenance(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(accountId, currencyCode, aliasName);
		}
		return service.processSend(ObAccountAliasListResponse.class);
	}
	
	@Override
	public ObAdministrationUserResponse administrationUserManagementDataList(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="userCode")String userCode, @WebParam(name="userName")String userName, @WebParam(name="pageNo")String pageNo, @WebParam(name="pageSize")String pageSize) {
		AdministrationUserManagementDataList service = new AdministrationUserManagementDataList(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(userCode, userName, pageNo, pageSize);
		}
		return service.processSend(ObAdministrationUserResponse.class);
	}
	
	@Override
	public ObAdministrationUserResponse administrationUserGeneralMaintenance(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="actionCode")String actionCode, @WebParam(name="userId")String userId, @WebParam(name="userCode")String userCode) {
		AdministrationUserGeneralMaintenance service = new AdministrationUserGeneralMaintenance(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(actionCode, userId, userCode);
		}
		return service.processSend(ObAdministrationUserResponse.class);
	}
	
	@Override
	public ObAdministrationUserResponse administrationUserProfileList(@WebParam(name="security", header=true)ObSecurity sec) {
		AdministrationUserProfileList service = new AdministrationUserProfileList(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData();
		}
		return service.processSend(ObAdministrationUserResponse.class);
	}
	
	@Override
	public ObAdministrationUserResponse administrationUserRoleList(@WebParam(name="security", header=true)ObSecurity sec) {
		AdministrationUserRoleList service = new AdministrationUserRoleList(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData();
		}
		return service.processSend(ObAdministrationUserResponse.class);
	}
	
	@Override
	public ObAdministrationUserResponse administrationUserCreateModify(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="actionCode")String actionCode, @WebParam(name="userId")String userId, @WebParam(name="userCode")String userCode, @WebParam(name="userName")String userName, @WebParam(name="userProfileCode")String userProfileCode, @WebParam(name="email")String email, @WebParam(name="roleList")List<RoleList> roleList, @WebParam(name="authOwn")String authOwn) {
		AdministrationUserCreateModify service = new AdministrationUserCreateModify(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(actionCode,userId, userCode, userName, userProfileCode, email, roleList, authOwn);
		}
		return service.processSend(ObAdministrationUserResponse.class);
	}

	
	@Override
	public ObAuthorizationResponse authorizationPendingCount(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="category")String category) {
		AuthorizationPendingCount service = new AuthorizationPendingCount(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(category);
		}
		return service.processSend(ObAuthorizationResponse.class);
	}
	
	@Override
	public ObAuthorizationResponse authorizationRetrievePendingAccountList(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="accountNumber")String accountNumber,@WebParam(name="accountName")String accountName) {
		AuthorizationRetrievePendingAccountList service = new AuthorizationRetrievePendingAccountList(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(accountNumber, accountName);
		}
		return service.processSend(ObAuthorizationResponse.class);
	}
	
	@Override
	public ObAuthorizationResponse authorizationRetrievePendingBeneficiaryList(@WebParam(name="security", header=true)ObSecurity sec) {
		AuthorizationRetrievePendingBeneficiaryList service = new AuthorizationRetrievePendingBeneficiaryList(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData();
		}
		return service.processSend(ObAuthorizationResponse.class);
	}
	
	@Override
	public ObAuthorizationResponse authorizationRetrievePendingUserList(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="userName")String userName, @WebParam(name="userCode")String userCode) {
		AuthorizationRetrievePendingUserList service = new AuthorizationRetrievePendingUserList(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(userName, userCode);
		}
		return service.processSend(ObAuthorizationResponse.class);
	}
	
	@Override
	public ObAuthorizationResponse authorizationPerformApprovalPendingAccount(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="recordIds")List<String> recordIds,@WebParam(name="action")String action) {
		AuthorizationPerformApprovalPendingAccount service = new AuthorizationPerformApprovalPendingAccount(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(recordIds, action);
		}
		return service.processSend(ObAuthorizationResponse.class);
	}
	
	@Override
	public ObAuthorizationResponse authorizationPerformApprovalPendingBeneficiary(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="recordIds")List<String> recordIds,@WebParam(name="action")String action) {
		AuthorizationPerformApprovalPendingBeneficiary service = new AuthorizationPerformApprovalPendingBeneficiary(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(recordIds, action);
		}
		return service.processSend(ObAuthorizationResponse.class);
	}
	
	@Override
	public ObAuthorizationResponse authorizationPerformApprovalPendingUser(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="recordIds")List<String> recordIds,@WebParam(name="action")String action) {
		AuthorizationPerformApprovalPendingUser service = new AuthorizationPerformApprovalPendingUser(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(recordIds, action);
		}
		return service.processSend(ObAuthorizationResponse.class);
	}
	
	@Override
	public ObAdminBeneficiaryListResponse administrationBeneficiaryList(@WebParam(name="security", header=true)ObSecurity sec, String productCode) {
		AdministrationBeneficiaryList service = new AdministrationBeneficiaryList(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(productCode);
		}
		return service.processSend(ObAdminBeneficiaryListResponse.class);
	}
	
	public ObProfileMaintResponse userProfileAuthList(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="pageNo")String page_no,@WebParam(name="pageSize")String page_size)
	{
		UserProfilePendingAuthList service = new UserProfilePendingAuthList(wsContext);
		if (null == service.getObResponse())
		{
			service.processData(page_no,page_size);
		}
		return service.processSend(ObProfileMaintResponse.class);
	}
	
	public ObProfileMaintResponse userProfileAuthSubmit(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="action")String action , @WebParam(name="record_id")String record_id )
	{
		UserProfileSubmitAuth service = new UserProfileSubmitAuth(wsContext);
		if (null == service.getObResponse())
		{
			service.processData(action,record_id);
		}
		return service.processSend(ObProfileMaintResponse.class);
	}
	
	public ObProfileMaintResponse policyAuthList(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="pageNo")String page_no,@WebParam(name="pageSize")String page_size)
	{
		PolicyPendingAuthList service = new PolicyPendingAuthList(wsContext);
		if (null == service.getObResponse())
		{
			service.processData(page_no,page_size);
		}
		return service.processSend(ObProfileMaintResponse.class);
	}
	
	public ObProfileMaintResponse policyAuthSubmit(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="action")String action , @WebParam(name="record_id")String record_id )
	{
		PolicySubmitAuth service = new PolicySubmitAuth(wsContext);
		if (null == service.getObResponse())
		{
			service.processData(action,record_id);
		}
		return service.processSend(ObProfileMaintResponse.class);
	}
	
	public ObProfileMaintResponse accessProfileAuthList(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="pageNo")String page_no,@WebParam(name="pageSize")String page_size)
	{
		AccessProfilePendingAuthList service = new AccessProfilePendingAuthList(wsContext);
		if (null == service.getObResponse())
		{
			service.processData(page_no,page_size);
		}
		return service.processSend(ObProfileMaintResponse.class);
	}
	
	public ObProfileMaintResponse accessProfileAuthSubmit(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="action")String action , @WebParam(name="record_id")String record_id )
	{
		AccessProfileSubmitAuth service = new AccessProfileSubmitAuth(wsContext);
		if (null == service.getObResponse())
		{
			service.processData(action,record_id);
		}
		return service.processSend(ObProfileMaintResponse.class);
	}
	
	@Override
	public ObTransactionSummaryResponse performMT103Inquiry(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="productCode")String productCode, @WebParam(name="paymentMasterId")String paymentMasterId, @WebParam(name="bankRef")String bankRef){
		PerformMT103Inquiry service= new PerformMT103Inquiry(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(productCode, paymentMasterId, bankRef);
		}
		return service.processSend(ObTransactionSummaryResponse.class);
	}
    
	@Override
	public ObTNCResponse retrieveTnCv2(ObSecurity sec, String tncType, @WebParam(name="isLocal")Boolean isLocal, @WebParam(name="ccyCode")String ccyCode)
	{
		RetrieveTnCv2 service= new RetrieveTnCv2(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(tncType, isLocal, ccyCode);
		}
		return service.processSend(ObTNCResponse.class);
	}
	
	@Override
	public ObTransactionResponse transactionFundTransferConfirmSubmit(ObSecurity sec, 
			String transferServiceType,
			String debitAccountUUID,
			String beneAccountUUID,
			String beneAccountNo,
			String beneAccountName,
			String beneAccountCcy,
			String beneAddress1,
			String beneAddress2,
			String beneAddress3,
			String beneBankCode,
			String beneBankBranch,
			String residentStatus,
			String beneCategory,
			String senderName,
			String remitterCategory,
			BigDecimal amount,
			String amountCcy,
			String valueDate,
			String customerReferenceNo,
			String remark,
			Boolean isSaveBene,
			Boolean isFavBene,
			String beneAliasName,
			Boolean isSendNotificationSender,
			Boolean isNotifySenderCompleted,
			Boolean isNotifySenderRejected,
			Boolean isNotifySenderSuspected,
			String notificationSenderEmail,
			Boolean isSendNotificationBene,
			String notificationBeneEmail,
			Boolean isRecurring,
			String recurringType,
			String recurringValue,
			String recurringStartDate,
			String recurringEndDate,
			String fxType,
			String fxDealerName,
			BigDecimal fxDealerRate,
			String fxContractNo,
			BigDecimal fxRate,
			@WebParam(name="beneBankName")String beneBankName,
			@WebParam(name="beneBankCountryCode")String beneBankCountryCode,
			@WebParam(name="beneBankAddress1") String beneBankAddress1,
			@WebParam(name="beneBankAddress2") String beneBankAddress2,
			@WebParam(name="beneBankAddress3") String beneBankAddress3,
			@WebParam(name="senderCountryCode") String senderCountryCode,
			@WebParam(name="beneCountryCode") String beneCountryCode,
			@WebParam(name="beneRelationshipStatus") String beneRelationshipStatus,
			@WebParam(name="paymentPurpose") String paymentPurpose,
			@WebParam(name="chargesMethod") String chargesMethod,
			String beneID,
			String beneBankNetworkClearingCode,
			String beneBankCity,
			String exec_time_batch_cd,
			String recurring_exec_time_batch_cd,
			String additional_info,
			String bene_bank_id,
			String trx_purpose,
			String charges_acct_no,
			String charges_acct_ccy,
			String sender_address1,
			String sender_address2,
			String sender_address3,
			String ip) {
		TransactionFundTransferConfirmSubmit service= new TransactionFundTransferConfirmSubmit(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(transferServiceType,
					debitAccountUUID,
					beneAccountUUID,
					beneAccountNo,
					beneAccountName,
					beneAccountCcy,
					beneAddress1,
					beneAddress2,
					beneAddress3,
					beneBankCode,
					beneBankBranch,
					residentStatus,
					beneCategory,
					senderName,
					remitterCategory,
					amount,
					amountCcy,
					valueDate,
					customerReferenceNo,
					remark,
					isSaveBene,
					isFavBene,
					beneAliasName,
					isSendNotificationSender,
					isNotifySenderCompleted,
					isNotifySenderRejected,
					isNotifySenderSuspected,
					notificationSenderEmail,
					isSendNotificationBene,
					notificationBeneEmail,
					isRecurring,
					recurringType,
					recurringValue,
					recurringStartDate,
					recurringEndDate,
					fxType,
					fxDealerName,
					fxDealerRate,
					fxContractNo,
					fxRate,
					beneBankName,
					beneBankCountryCode,
					beneBankAddress1,
					beneBankAddress2,
					beneBankAddress3,
					senderCountryCode,
					beneCountryCode,
					beneRelationshipStatus,
					paymentPurpose,
					chargesMethod,
					beneID,
					beneBankNetworkClearingCode,
					beneBankCity,
					exec_time_batch_cd,
					recurring_exec_time_batch_cd,
					additional_info,
					bene_bank_id,
					trx_purpose,
					charges_acct_no,
					charges_acct_ccy,
					sender_address1,
					sender_address2,
					sender_address3,
					ip);
		}
		return service.processSend(ObTransactionResponse.class);
	}
	
	@Override
	public ObPaymentResponse paymentConfirmSubmit(ObSecurity sec,
			String billerType,
			String debitAccountUUID,
			String billerName,
			String amountOptionId,
			BigDecimal amount,
			String valueDate,
			String customerReferenceNo,
			Boolean isSavePayee,
			String payeeNickName,
			Boolean isSendNotificationSender,
			Boolean isNotifySenderCompleted,
			Boolean isNotifySenderRejected,
			Boolean isNotifySenderSuspected,
			String notificationSenderEmail,
			Boolean isRecurring,
			String recurringType,
			String recurringValue,
			String recurringStartDate,
			String recurringEndDate,
			String exec_time_batch_cd,
			String recurring_exec_time_batch_cd,
			String ip,
			String lang
			) {
		PaymentConfirmSubmit service= new PaymentConfirmSubmit(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(
					billerType,
					debitAccountUUID,
					billerName,
					amountOptionId,
					amount,
					valueDate,
					customerReferenceNo,
					isSavePayee,
					payeeNickName,
					isSendNotificationSender,
					isNotifySenderCompleted,
					isNotifySenderRejected,
					isNotifySenderSuspected,
					notificationSenderEmail,
					isRecurring,
					recurringType,
					recurringValue,
					recurringStartDate,
					recurringEndDate,
					exec_time_batch_cd,
					recurring_exec_time_batch_cd,
					ip,
					lang);
		}
		return service.processSend(ObPaymentResponse.class);
	}
	
	public ObTransNotiAuthResponse pendingAuthNotiList(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="pageNo")String page_no,@WebParam(name="pageSize")String page_size)
	{
		PendingAuthNotiList service = new PendingAuthNotiList(wsContext);
		if (null == service.getObResponse())
		{
			service.processData(page_no,page_size);
		}
		return service.processSend(ObTransNotiAuthResponse.class);
	}
	
	public ObTransNotiAuthResponse pendingAuthNotiMaint(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="action")String action , @WebParam(name="record_id")String record_id )
	{
		PendingAuthNotiMaint service = new PendingAuthNotiMaint(wsContext);
		if (null == service.getObResponse())
		{
			service.processData(action,record_id);
		}
		return service.processSend(ObTransNotiAuthResponse.class);
	}
	
	@Override
	public ObRegistrationResponse retrieveInquiryRegistration(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="role")String role , @WebParam(name="email")String email, @WebParam(name="mobileNo")String mobileNo , @WebParam(name="ktpNo")String ktpNo, @WebParam(name="registrationNo")String registrationNo , @WebParam(name="accountName")String accountName, @WebParam(name="verificationNo")String verificationNo , @WebParam(name="revisionNo")String revisionNo )
	{
		RetrieveInquiryRegistration service = new RetrieveInquiryRegistration(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(role,email,mobileNo,ktpNo,registrationNo,accountName,verificationNo,revisionNo);
		}
		return service.processSend(ObRegistrationResponse.class);
	}
	
	@Override
	public ObRegistrationResponse retrieveInputterSignerData(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="user_id")String user_id, @WebParam(name="record_id")String record_id)
	{
		RetrieveInputterSignerData service = new RetrieveInputterSignerData(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(user_id,record_id);
		}
		return service.processSend(ObRegistrationResponse.class);
	}
	
	@Override
	public ObRegistrationResponse addDeleteSignerData(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="action")String action, @WebParam(name="user_id")String user_id, @WebParam(name="name")String name, @WebParam(name="ktp_no")String ktp_no, @WebParam(name="npwp_no")String npwp_no, @WebParam(name="gender")String gender, @WebParam(name="birth_place")String birth_place, @WebParam(name="birth_date")String birth_date, @WebParam(name="address")String address, @WebParam(name="province")String province,@WebParam(name="city")String city, @WebParam(name="email")String email, @WebParam(name="phone")String phone, @WebParam(name="ktp_photo")DataHandler ktp_photo, @WebParam(name="npwp_photo")DataHandler npwp_photo, @WebParam(name="ktp_file_name")String ktp_file_name, @WebParam(name="ktp_file_ext")String ktp_file_ext, @WebParam(name="npwp_file_name")String npwp_file_name, @WebParam(name="npwp_file_ext")String npwp_file_ext, @WebParam(name="record_id")String record_id, @WebParam(name="isBase64") Boolean isBase64)
	{
		AddDeleteSignerData service = new AddDeleteSignerData(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(action,user_id,name,ktp_no,npwp_no,gender,birth_place,birth_date,address,province,city,email,phone,ktp_photo,npwp_photo,ktp_file_name,ktp_file_ext,npwp_file_name,npwp_file_ext,record_id,isBase64);
		}
		return service.processSend(ObRegistrationResponse.class);
	}
	
	@Override
	public ObRegistrationResponse registrationRevisionSubmit(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="role")String role, @WebParam(name="record_id")String record_id, @WebParam(name="version_no")Integer version_no, @WebParam(name="registration_no")String registration_no, @WebParam(name="revision_no")String revision_no, @WebParam(name="name")String name, @WebParam(name="ktp_no")String ktp_no, @WebParam(name="address")String address, @WebParam(name="email")String email, @WebParam(name="phone")String phone, @WebParam(name="signature_flow")String signature_flow, @WebParam(name="npwp_no")String npwp_no, @WebParam(name="gender")String gender, @WebParam(name="birth_place")String birth_place, @WebParam(name="birth_date")String birth_date, @WebParam(name="province")String province, @WebParam(name="city")String city, @WebParam(name="ktp_photo")DataHandler ktp_photo, @WebParam(name="npwp_photo")DataHandler npwp_photo, @WebParam(name="ktp_file_name")String ktp_file_name, @WebParam(name="ktp_file_ext")String ktp_file_ext, @WebParam(name="npwp_file_name")String npwp_file_name, @WebParam(name="npwp_file_ext")String npwp_file_ext, @WebParam(name="signers_changed")Boolean signers_changed,@WebParam(name="signerList")List<SignerList> signerList, @WebParam(name="isBase64") Boolean isBase64, @WebParam(name="sequence")Integer sequence)
	{
		RegistrationRevisionSubmit service = new RegistrationRevisionSubmit(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(role,record_id,version_no,registration_no,revision_no,name,ktp_no,address,email,phone,signature_flow,npwp_no,gender,birth_place,birth_date,province,city,ktp_photo,npwp_photo,ktp_file_name,ktp_file_ext,npwp_file_name,npwp_file_ext,signers_changed,signerList,isBase64,sequence);
		}
		return service.processSend(ObRegistrationResponse.class);
	}
	
	@Override
	public ObRegistrationResponse registrationAckSubmit(@WebParam(name="security", header=true)ObSecurity sec,@WebParam(name="record_id")String record_id, @WebParam(name="version_no")Integer version_no, @WebParam(name="registration_no")String registration_no, @WebParam(name="verification_no")String verification_no, @WebParam(name="name")String name, @WebParam(name="ktp_no")String ktp_no, @WebParam(name="address")String address, @WebParam(name="email")String email, @WebParam(name="phone")String phone,@WebParam(name="npwp_no")String npwp_no, @WebParam(name="gender")String gender, @WebParam(name="birth_place")String birth_place, @WebParam(name="birth_date")String birth_date, @WebParam(name="province")String province, @WebParam(name="city")String city, @WebParam(name="ktp_photo")DataHandler ktp_photo, @WebParam(name="npwp_photo")DataHandler npwp_photo, @WebParam(name="ktp_file_name")String ktp_file_name, @WebParam(name="ktp_file_ext")String ktp_file_ext, @WebParam(name="npwp_file_name")String npwp_file_name, @WebParam(name="npwp_file_ext")String npwp_file_ext, @WebParam(name="sequence")Integer sequence, @WebParam(name="isBase64") Boolean isBase64)
	{
		RegistrationAckSubmit service = new RegistrationAckSubmit(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(record_id,version_no,registration_no,verification_no,name,ktp_no,address,email,phone,npwp_no,gender,birth_place,birth_date,province,city,ktp_photo,npwp_photo,ktp_file_name,ktp_file_ext,npwp_file_name,npwp_file_ext,sequence,isBase64);
		}
		return service.processSend(ObRegistrationResponse.class);
	}
	
	@Override
	public ObRegistrationResponse registrationCancel(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="role")String role,@WebParam(name="record_id")String record_id, @WebParam(name="version_no")Integer version_no, @WebParam(name="registration_no")String registration_no, @WebParam(name="name")String name, @WebParam(name="ktp_no")String ktp_no, @WebParam(name="address")String address, @WebParam(name="email")String email, @WebParam(name="phone")String phone,@WebParam(name="npwp_no")String npwp_no, @WebParam(name="gender")String gender, @WebParam(name="birth_place")String birth_place, @WebParam(name="birth_date")String birth_date, @WebParam(name="province")String province, @WebParam(name="city")String city, @WebParam(name="ktp_photo")DataHandler ktp_photo, @WebParam(name="npwp_photo")DataHandler npwp_photo, @WebParam(name="ktp_file_name")String ktp_file_name, @WebParam(name="ktp_file_ext")String ktp_file_ext, @WebParam(name="npwp_file_name")String npwp_file_name, @WebParam(name="npwp_file_ext")String npwp_file_ext, @WebParam(name="verification_no")String verification_no, @WebParam(name="isBase64") Boolean isBase64)
	{
		RegistrationCancel service = new RegistrationCancel(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(role,record_id,version_no,registration_no,name,ktp_no,address,email,phone,npwp_no,gender,birth_place,birth_date,province,city,ktp_photo,npwp_photo,ktp_file_name,ktp_file_ext,npwp_file_name,npwp_file_ext,verification_no,isBase64);
		}
		return service.processSend(ObRegistrationResponse.class);
	}

	
	@Override
	public ObRegistrationResponse retrieveProvinceList(ObSecurity sec)
	{
		RetrieveProvinceList service= new RetrieveProvinceList(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData();
		}
		return service.processSend(ObRegistrationResponse.class);
	}
	
	@Override
	public ObAccountListResponse customerPortfolioDatav2(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="cifNumber")String cifNumber, @WebParam(name="productCode")String productCode, @WebParam(name="agreementID")String agreementID) {
		CustomerPortfolioDatav2 service = new CustomerPortfolioDatav2(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(cifNumber, productCode, agreementID);
		}
		return service.processSend(ObAccountListResponse.class);
	}
	
	@Override
	public ObSwiftOutboundTrackingResponse performSWIFTOutboundTracking(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="remittance_no")String remittance_no)
	{
		PerformSWIFTOutboundTracking service= new PerformSWIFTOutboundTracking(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(remittance_no);
		}
		return service.processSend(ObSwiftOutboundTrackingResponse.class);
	}
	
	@Override
	public ObUpdateDeviceAliasResponse updateDeviceAlias(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="device_id")String device_id, @WebParam(name="device_alias")String device_alias)
	{
		UpdateDeviceAlias service= new UpdateDeviceAlias(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(device_id, device_alias);
		}
		return service.processSend(ObUpdateDeviceAliasResponse.class);
	}
	
	@Override
	public ObDeviceUnbindgResponse performDeviceUnBindg(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="device_id")String device_id, @WebParam(name="cif")String cif)
	{
		PerformDeviceUnBindg service= new PerformDeviceUnBindg(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(device_id, cif);
		}
		return service.processSend(ObDeviceUnbindgResponse.class);
	}
	
	@Override
	public ObTransactionSourceOfFundResponse retrieveChargeAccountList(ObSecurity sec, String transferServiceType, String currency, String accountNo) {
		RetrieveChargeAccountList service= new RetrieveChargeAccountList(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(transferServiceType, currency, accountNo);
		}
		return service.processSend(ObTransactionSourceOfFundResponse.class);
	}

	@Override
	public ObBlockMyUserResponse performBlockMyUser(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="org_cd")String org_cd, @WebParam(name="usr_cd")String usr_cd, @WebParam(name="email")String email, @WebParam(name="phone")String phone)
	{
		PerformBlockMyUser service= new PerformBlockMyUser(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(org_cd, usr_cd, email, phone);
		}
		return service.processSend(ObBlockMyUserResponse.class);
	}
	
	@Override
	public ObAccountOverviewResponse estatementDownload(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="acct_no")String acct_no, @WebParam(name="acct_ccy")String acct_ccy, @WebParam(name="periode")String periode) {
		EstatementDownload service = new EstatementDownload(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(acct_no, acct_ccy, periode);
		}
		return service.processSend(ObAccountOverviewResponse.class);
	}
	
	@Override
	public ObDocumentDownloadResponse documentDownload(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="documentId")String documentId, @WebParam(name="documentType")String documentType) {
		DocumentDownload service = new DocumentDownload(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(documentId, documentType);
		}
		return service.processSend(ObDocumentDownloadResponse.class);
	}

	@Override
	public ObTransactionResponse retrieveBeneficiaryCategory(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="purposeCode") String purposeCode) {
		RetrieveBeneficiaryCategory service = new RetrieveBeneficiaryCategory(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(purposeCode);
		}
		return service.processSend(ObTransactionResponse.class);
	}

	@Override
	public ObTransactionResponse retrieveBeneficiaryCountry(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="purposeCode") String purposeCode) {
		RetrieveBeneficiaryCountry service = new RetrieveBeneficiaryCountry(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(purposeCode);
		}
		return service.processSend(ObTransactionResponse.class);
	}

	@Override
	public ObTransactionResponse retrieveRemitterCountry(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="purposeCode") String purposeCode, @WebParam(name="beneCategoryCode") String beneCategoryCode,
			@WebParam(name="beneCountryCode") String beneCountryCode) {
		RetrieveRemitterCountry service = new RetrieveRemitterCountry(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(purposeCode, beneCategoryCode, beneCountryCode);
		}
		return service.processSend(ObTransactionResponse.class);
	}

	@Override
	public ObTransactionResponse retrieveRemitterCategory(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="purposeCode") String purposeCode, @WebParam(name="beneCategoryCode") String beneCategoryCode,
			@WebParam(name="beneCountryCode") String beneCountryCode, @WebParam(name="remitterCountryCode") String remitterCountryCode) {
		RetrieveRemitterCategory service = new RetrieveRemitterCategory(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(purposeCode, beneCategoryCode, beneCountryCode, remitterCountryCode);
		}
		return service.processSend(ObTransactionResponse.class);
	}

	@Override
	public ObTransactionResponse retrieveBeneficiaryAffiliation(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="purposeCode") String purposeCode,
			@WebParam(name="beneCategoryCode") String beneCategoryCode, @WebParam(name="beneCountryCode") String beneCountryCode, @WebParam(name="remitterCountryCode") String remitterCountryCode, @WebParam(name="remitterCategoryCode") String remitterCategoryCode) {
		RetrieveBeneficiaryAffiliation service = new RetrieveBeneficiaryAffiliation(wsContext);
		if (null == service.getObResponse())
		{
			service.processGenericData(purposeCode, beneCategoryCode, beneCountryCode, remitterCountryCode, remitterCategoryCode);
		}
		return service.processSend(ObTransactionResponse.class);
	}

	@Override
	public ObAuthenticationResponse performLoginv2(ObSecurity sec, String OrgId, String usrId, String pString, String cString, String random_number, String authType, String passwordDataBlock, String ip)
	{
		PerformLoginSessionv2 pLogin = new PerformLoginSessionv2(wsContext);
		if (null == pLogin.getObResponse())
		{
			pLogin.processData(OrgId,usrId,pString,cString, random_number, authType, passwordDataBlock, ip);
		}
		return pLogin.processSend(ObAuthenticationResponse.class);
	}
	
	@Override
	public ObAuthenticationResponse performDeviceBindingv2(@WebParam(name="security", header=true)ObSecurity sec,String tac,String unbindIndex)
	{
		PerformDeviceBindingv2 accessInfo = new PerformDeviceBindingv2(wsContext);
		if (null == accessInfo.getObResponse())
		{
			accessInfo.processData(tac,unbindIndex);
		}
		return accessInfo.processSend(ObAuthenticationResponse.class);
	}
	
	@Override
	public ObDeviceBindingResponse performDeviceBindingUpdate(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="device_id")String device_id, @WebParam(name="cif")String cif, @WebParam(name="action")String action) {
		PerformDeviceBindingUpdate updateBinding = new PerformDeviceBindingUpdate(wsContext);
		if (null == updateBinding.getObResponse())
		{
			updateBinding.processGenericData(device_id, cif, action);
		}
		return updateBinding.processSend(ObDeviceBindingResponse.class);
	}

	@Override
	public ObTransactionLimitEqUSDResponse retrieveTransactionLimitPurchase(@WebParam(name="security", header=true) ObSecurity sec, @WebParam(name="fromCcy") String fromCcy, @WebParam(name="toCcy") String toCcy, @WebParam(name="fromAmount") String fromAmount, @WebParam(name="toAmount") String toAmount, @WebParam(name="accountNo") String accountNo, @WebParam(name="cancelBit") String cancelBit) {
		RetrieveTransactionLimitPurchase retrieveTransactionLimitPurchase = new RetrieveTransactionLimitPurchase(wsContext);
		if (null == retrieveTransactionLimitPurchase.getObResponse())
		{
			retrieveTransactionLimitPurchase.processGenericData(fromCcy,toCcy,fromAmount,toAmount,accountNo,cancelBit);
		}
		return retrieveTransactionLimitPurchase.processSend(ObTransactionLimitEqUSDResponse.class);
	}
}
