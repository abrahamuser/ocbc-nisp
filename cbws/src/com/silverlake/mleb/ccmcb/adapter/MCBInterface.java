package com.silverlake.mleb.ccmcb.adapter;

import java.math.BigDecimal;
import java.util.List;

import javax.activation.DataHandler;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlMimeType;

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
 
 


@WebService
public interface  MCBInterface
{
	public ObAuthenticationResponse performLogin(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="orgId")String orgId, @WebParam(name="usrId")String usrId, @WebParam(name="pString")String pString, @WebParam(name="cString")String cString, @WebParam(name="random_number")String random_number, @WebParam(name="authType")String authType, @WebParam(name="passwordDataBlock")String passwordDataBlock, @WebParam(name="ip")String ip);

	//retrieveCustProfile
	public ObAuthenticationResponse retrieveSecInfo(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="primaryActionFlag") String primaryActionFlag);
	
	public ObAccessRestrictionResponse retrieveAccessRestriction(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="sourceTrx")String sourceTrx, @WebParam(name="productCode")String productCode, @WebParam(name="trxStatus")String trxStatus);

	public ObDeviceBindingResponse retrieveDeviceProfile(@WebParam(name="security", header=true)ObSecurity sec);

	public ObAuthenticationResponse requestDeviceTAC(@WebParam(name="security", header=true)ObSecurity sec);

	public ObAuthenticationResponse performLogout(@WebParam(name="security", header=true)ObSecurity sec);

	public ObAuthenticationResponse retrieveAccessInfo(@WebParam(name="security", header=true)ObSecurity sec);

	public ObAuthenticationResponse retrieveSessionInfo(@WebParam(name="security", header=true)ObSecurity sec);

	public ObAuthenticationResponse performDeviceBinding(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="tac")String tac, @WebParam(name="unbindIndex")String unbindIndex);
	
	public ObAuthenticationResponse performDeviceUnBindOpen(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="pString")String pString, @WebParam(name="cString")String cString, @WebParam(name="random_number")String random_number, @WebParam(name="approvalUnbindFlag")String approvalUnbindFlag, @WebParam(name="passwordDataBlock")String passwordDataBlock);
	public ObAuthenticationResponse performDeviceUnBind(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="unbindIndex")String unbindIndex, @WebParam(name="pString")String pString, @WebParam(name="cString")String cString, @WebParam(name="random_number")String random_number, @WebParam(name="approvalUnbindFlag")String approvalUnbindFlag, @WebParam(name="passwordDataBlock")String passwordDataBlock);
	
	public ObAuthenticationResponse performChangePassword(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="pString")String pString,  @WebParam(name="cString")String cString,@WebParam(name="random_number")String random_number, @WebParam(name="passwordDataBlock") String passwordDataBlock);
	
	public ObAccountOverviewResponse retrieveAccountList(@WebParam(name="security", header=true)ObSecurity sec,@WebParam(name="accountType")String accountType,@WebParam(name="prod_cd")String prod_cd);
	
	public ObAuthorizationResponse retrieveTransactionDataList(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="source_trx")List<String> source_trx, @WebParam(name="pymtMasterId")String pymtMasterId, @WebParam(name="trx_status")String trx_status, @WebParam(name="value_date_from")String value_date_from, @WebParam(name="value_date_to")String value_date_to, @WebParam(name="pageSize")String pageSize, @WebParam(name="page_no")String page_no, @WebParam(name="prodCode")List<String> prodCode);
	
	public ObTaskListResponse retrieveTaskList(@WebParam(name="security", header=true)ObSecurity sec,@WebParam(name="trnxSourcesList")String trnxSourcesList);
	
	public ObForexResponse onfxStep1(@WebParam(name="security", header=true)ObSecurity sec);
	
	public ObForexResponse onfxGetRate(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="currency")String currency, @WebParam(name="isCrossCurrency")Boolean isCrossCurrency, @WebParam(name="currency2")String currency2);
	
	public ObForexResponse onfxConfirmation(@WebParam(name="security", header=true)ObSecurity sec,  
			@WebParam(name="debitAccountUUID")String debitAccountUUID,
			@WebParam(name="beneAccountUUID")String beneAccountUUID,
			@WebParam(name="beneAccountNo")String beneAccountNo,
			@WebParam(name="beneAccountName")String beneAccountName,
			@WebParam(name="currency")String currency,
			@WebParam(name="amount")BigDecimal amount,
			@WebParam(name="amountCcy")String amountCcy,
			@WebParam(name="remark")String remark,
			@WebParam(name="purpose")String purpose,
			@WebParam(name="currency2")String currency2,
			@WebParam(name="isPreValidate")Boolean isPreValidate);
	
	public ObForexResponse onfxPost(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="action")String action);
	
	public ObDashboardInfoResponse retrieveDashBoardInfo(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="tncAction")String tncAction);
	
	public ObHolidayResponse retrieveHolidayList(@WebParam(name="security", header=true)ObSecurity sec,@WebParam(name="year")Integer year);
	
	public ObTransactionCurrencyResponse retrieveTransactionCurrencyList(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="transferServiceType")String transferServiceType);
	
	public ObTransactionSummaryResponse retrieveTransactionAuditTrail(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="pymtMasterId")String pymtMasterId);
	
	public ObTransactionSummaryResponse retrieveTransactionNotes(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="sourceTrx")String sourceTrx, @WebParam(name="pymtMasterId")String pymtMasterId);
	
	public ObTransactionSummaryResponse transactionNoteAdd(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="sourceTrx")String sourceTrx, @WebParam(name="pymtMasterId")String pymtMasterId, @WebParam(name="noteRemark")String noteRemark);
	
	public ObTransactionSummaryResponse transactionNoteDelete(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="sourceTrx")String sourceTrx, @WebParam(name="noteId")String noteId);
	
	public ObTransactionSummaryResponse retrieveTransactionReceipt(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="pymtMasterId")String pymtMasterId, @WebParam(name="isRetrieveDetails")Boolean isRetrieveDetails, @WebParam(name="is_additional_info")String is_additional_info);
	
	public ObTransactionSummaryResponse transactionStatusUpdate(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="sourceTrx")String sourceTrx, @WebParam(name="pymtMasterId")String pymtMasterId, @WebParam(name="index")Integer index, @WebParam(name="trxStatus")String trxStatus, @WebParam(name="validationCheck")String validationCheck);

	public ObTransactionSourceOfFundResponse transactionSourceOfFund(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="transferServiceType")String transferServiceType, @WebParam(name="isSourceOfFund")Boolean isSourceOfFund, @WebParam(name="currency")String currency, @WebParam(name="accountNo")String accountNo);
	
	public ObTransactionBeneficiaryResponse transactionBeneficiaries(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="transferServiceType")String transferServiceType, @WebParam(name="accountNo")String accountNo, @WebParam(name="accountCcy")String accountCcy);
	
	public ObTransactionBeneficiaryResponse transactionBeneficiaryInquiry(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="transferServiceType")String transferServiceType, @WebParam(name="accountNo")String accountNo, @WebParam(name="accountCcy")String accountCcy, @WebParam(name="debitAccountNo")String debitAccountNo, @WebParam(name="bankCode")String bankCode, @WebParam(name="bankName")String bankName, @WebParam(name="isInquiry")Boolean isInquiry, @WebParam(name="proxy_data")String proxy_data, @WebParam(name="proxy_type")String proxy_type);

	public ObTransactionResponse transactionFundTransferMenu(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="transferServiceTypes")List<String> transferServiceTypes);
	
	public ObTransactionResponse transactionFundTransferStep1(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="transferServiceType")String transferServiceType);
	
	public ObTransactionResponse transactionFundTransferValidateDate(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="transferServiceType")String transferServiceType, @WebParam(name="valueDate")String valueDate);

	public ObTransactionResponse transactionFundTransferSubmit(@WebParam(name="security", header=true)ObSecurity sec, 
			@WebParam(name="transferServiceType")String transferServiceType,
			@WebParam(name="debitAccountUUID")String debitAccountUUID,
			@WebParam(name="beneAccountUUID")String beneAccountUUID,
			@WebParam(name="beneAccountNo")String beneAccountNo,
			@WebParam(name="beneAccountName")String beneAccountName,
			@WebParam(name="beneAccountCcy")String beneAccountCcy,
			@WebParam(name="beneAddress1")String beneAddress1,
			@WebParam(name="beneAddress2")String beneAddress2,
			@WebParam(name="beneAddress3")String beneAddress3,
			@WebParam(name="beneBankCode")String beneBankCode,
			@WebParam(name="beneBankBranch")String beneBankBranch,
			@WebParam(name="residentStatus")String residentStatus,
			@WebParam(name="beneCategory")String beneCategory,
			@WebParam(name="senderName")String senderName,
			@WebParam(name="remitterCategory")String remitterCategory,
			@WebParam(name="amount")BigDecimal amount,
			@WebParam(name="amountCcy")String amountCcy,
			@WebParam(name="valueDate")String valueDate,
			@WebParam(name="customerReferenceNo")String customerReferenceNo,
			@WebParam(name="remark")String remark,
			@WebParam(name="isSaveBene")Boolean isSaveBene,
			@WebParam(name="isFavBene")Boolean isFavBene,
			@WebParam(name="beneAliasName")String beneAliasName,
			@WebParam(name="isSendNotificationSender")Boolean isSendNotificationSender,
			@WebParam(name="isNotifySenderCompleted")Boolean isNotifySenderCompleted,
			@WebParam(name="isNotifySenderRejected")Boolean isNotifySenderRejected,
			@WebParam(name="isNotifySenderSuspected")Boolean isNotifySenderSuspected,
			@WebParam(name="emailNotificationSender")String emailNotificationSender,
			@WebParam(name="isSendNotificationBene")Boolean isSendNotificationBene,
			@WebParam(name="emailNotificationBene")String emailNotificationBene,
			@WebParam(name="isRecurring")Boolean isRecurring,
			@WebParam(name="recurringType")String recurringType,
			@WebParam(name="recurringValue")String recurringValue,
			@WebParam(name="recurringStartDate")String recurringStartDate,
			@WebParam(name="recurringEndDate")String recurringEndDate,
			@WebParam(name="fxType")String fxType,
			@WebParam(name="fxDealerName")String fxDealerName,
			@WebParam(name="fxDealerRate")BigDecimal fxDealerRate,
			@WebParam(name="fxContractNo")String fxContractNo,
			@WebParam(name="fxRate")BigDecimal fxRate,
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
			@WebParam(name="beneID") String beneID,
			@WebParam(name="beneBankNetworkClearingCode") String beneBankNetworkClearingCode,
			@WebParam(name="beneBankCity") String beneBankCity,
			@WebParam(name="exec_time_batch_cd")String exec_time_batch_cd,
			@WebParam(name="recurring_exec_time_batch_cd")String recurring_exec_time_batch_cd,
			@WebParam(name="additional_info")String additional_info,
			@WebParam(name="bene_bank_id")String bene_bank_id,
			@WebParam(name="trx_purpose")String trx_purpose,
			@WebParam(name="charges_acct_no")String charges_acct_no,
			@WebParam(name="charges_acct_ccy")String charges_acct_ccy,
			@WebParam(name="sender_address1")String sender_address1,
			@WebParam(name="sender_address2")String sender_address2,
			@WebParam(name="sender_address3")String sender_address3,
			@WebParam(name="ip")String ip
		);
	
	public ObTransactionManageBeneficiaryResponse transactionManageBeneficiaryDetails(@WebParam(name="security", header=true)ObSecurity sec, 
			@WebParam(name="beneId")String beneId);
	
	public ObTransactionManageBeneficiaryResponse transactionManageBeneficiaryStep1(@WebParam(name="security", header=true)ObSecurity sec);
	
	public ObTransactionManageBeneficiaryResponse transactionManageBeneficiaryEdit(@WebParam(name="security", header=true)ObSecurity sec, 
			@WebParam(name="beneId")String beneId, 
			@WebParam(name="nickName")String nickName);
	
	public ObTransactionManageBeneficiaryResponse transactionManageBeneficiaryDelete(@WebParam(name="security", header=true)ObSecurity sec, 
			@WebParam(name="beneId")String beneId);
	
	public ObTransactionManageBeneficiaryResponse transactionManageBeneficiarySetFavorite(@WebParam(name="security", header=true)ObSecurity sec, 
			@WebParam(name="beneId")String beneId,
			@WebParam(name="isFavorite")Boolean isFavorite);
	
	public ObTransactionManageBeneficiaryResponse transactionManageBeneficiaryAdd(@WebParam(name="security", header=true)ObSecurity sec, 
			@WebParam(name="transferServiceType")String transferServiceType,
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
			@WebParam(name="participantBic")String participantBic);
	
	public ObPaymentResponse paymentStep1(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="billerType")String billerType);
	
	public ObPaymentResponse paymentRetrieveBillerList(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="billerType")String billerType);
	
	public ObPaymentResponse paymentRetrievePayeeList(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="groupName")String groupName, @WebParam(name="billerCode")String billerCode);
	
	public ObPaymentResponse paymentInquiryBiller(@WebParam(name="security", header=true)ObSecurity sec, 
			@WebParam(name="flowType")Integer flowType,
			@WebParam(name="billerType")String billerType,
			@WebParam(name="debitAccountUUID")String debitAccountUUID,
			@WebParam(name="billerId")String billerId,
			@WebParam(name="billerCode")String billerCode,
			@WebParam(name="billingId")String billingId,
			@WebParam(name="amount")BigDecimal amount,
			@WebParam(name="valueDate")String valueDate,
			@WebParam(name="customerRef")String customerRef,
			@WebParam(name="ppType")String ppType);
	
	public ObPaymentResponse paymentSubmit(@WebParam(name="security", header=true)ObSecurity sec,
			@WebParam(name="billerType")String billerType,
			@WebParam(name="debitAccountUUID")String debitAccountUUID,
			@WebParam(name="billerName")String billerName,
			@WebParam(name="amountOptionId")String amountOptionId,
			@WebParam(name="amount")BigDecimal amount,
			@WebParam(name="valueDate")String valueDate,
			@WebParam(name="customerReferenceNo")String customerReferenceNo,
			@WebParam(name="isSavePayee")Boolean isSavePayee,
			@WebParam(name="payeeNickName")String payeeNickName,
			@WebParam(name="isSendNotificationSender")Boolean isSendNotificationSender,
			@WebParam(name="isNotifySenderCompleted")Boolean isNotifySenderCompleted,
			@WebParam(name="isNotifySenderRejected")Boolean isNotifySenderRejected,
			@WebParam(name="isNotifySenderSuspected")Boolean isNotifySenderSuspected,
			@WebParam(name="emailNotificationSender")String emailNotificationSender,
			@WebParam(name="isRecurring")Boolean isRecurring,
			@WebParam(name="recurringType")String recurringType,
			@WebParam(name="recurringValue")String recurringValue,
			@WebParam(name="recurringStartDate")String recurringStartDate,
			@WebParam(name="recurringEndDate")String recurringEndDate,
			@WebParam(name="exec_time_batch_cd")String exec_time_batch_cd,
			@WebParam(name="recurring_exec_time_batch_cd")String recurring_exec_time_batch_cd,
			@WebParam(name="ip")String ip,
			@WebParam(name="lang")String lang);
	
	public ObVcTranxResponse retrieveFutureTranx(@WebParam(name="security", header=true)ObSecurity sec,@WebParam(name="ndays")String nDays,@WebParam(name="pageNo")String pageNo);

	public ObTaskListResponse retrieveTaskTranx(@WebParam(name="security", header=true)ObSecurity sec,@WebParam(name="trnxSources")String trnxSources, @WebParam(name="status")String status,@WebParam(name="prdCodeList")String prdCodeList, @WebParam(name="pageNo")String pageNo, @WebParam(name="pageSize")String pageSize,@WebParam(name="valueDateFrom")String valueDateFrom, @WebParam(name="valueDateTo")String valueDateTo, @WebParam(name="sourceFileName")String sourceFileName,@WebParam(name="uploadDateFrom")String uploadDateFrom, @WebParam(name="uploadDateTo")String uploadDateTo, @WebParam(name="module")String module);
	
	public ObRetrieveFaqResponse retrieveFaq(@WebParam(name="security", header=true)ObSecurity sec,@WebParam(name="faq_cat_cd")String faqCatCd);
	
	public ObSendFeedbackFaqResponse sendFeedbackFaq(@WebParam(name="security", header=true)ObSecurity sec,@WebParam(name="faq_id")String faq_id,@WebParam(name="feedback")String feedback,@WebParam(name="org_cd")String org_cd,@WebParam(name="usr_cd")String usr_cd);
	
//	public ObSendFeedbackResponse sendFeedback(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="email") String email, @WebParam(name="phone") String phone, @WebParam(name="category")String category, @WebParam(name="feedback") String feedback, @WebParam(name="imageList") List<ObFile> imageList);
	
	public ObSendFeedbackResponse sendFeedback(@WebParam(name="security", header=true)ObSecurity sec, 
			@WebParam(name="email") String email, 
			@WebParam(name="phone") String phone, 
			@WebParam(name="category") String category, 
			@WebParam(name="feedback") String feedback,
			@WebParam(name="fileName") String fileName, 
			@WebParam(name="dataType") String fileType,
			@WebParam(name="isBase64") Boolean isBase64,
			@WebParam(name="fileData") @XmlMimeType("application/octet-stream")DataHandler fileData);
	
	public ObSendFeedbackResponse sendFeedbackBase64(@WebParam(name="security", header=true)ObSecurity sec, 
			@WebParam(name="email") String email, 
			@WebParam(name="phone") String phone, 
			@WebParam(name="category") String category, 
			@WebParam(name="feedback") String feedback,
			@WebParam(name="fileName") String fileName, 
			@WebParam(name="dataType") String fileType, 
			@WebParam(name="fileData") String fileData);
	
	public ObTNCResponse retrieveTnC(@WebParam(name="security", header=true)ObSecurity sec,@WebParam(name="tncType")String tncType,@WebParam(name="isLocal")Boolean isLocal);
	
	public ObBankListResponse retrieveBankList(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="transferServiceType")String transferServiceType, 
			@WebParam(name="bankCode")String bankCode,
			@WebParam(name="branchName")String branchName,
			@WebParam(name="countryCode")String countryCode,
			@WebParam(name="networkClearingCode")String networkClearingCode);
	
	public ObBankListResponse retrieveBankSwiftDetails(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="networkClearingCode")String networkClearingCode);
	
	public ObCountryListResponse retrieveCountryList(@WebParam(name="security", header=true)ObSecurity sec);
	
	public ObTNCResponse tnCConfirmation(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="tncType")String tncType, @WebParam(name="refNo")String refNo);
	
	public ObAccountOverviewResponse retrieveAccountDetails(@WebParam(name="security", header=true)ObSecurity sec,@WebParam(name="index")String index );

	public ObAccountOverviewResponse retrieveAccountHist(@WebParam(name="security", header=true)ObSecurity sec,@WebParam(name="index")String index ,@WebParam(name="noDays")String noDays,@WebParam(name="fromDate")String fromDate,@WebParam(name="toDate")String toDate,@WebParam(name="pageNo")String pageNo);

	public ObAccountOverviewResponse retrieveAccountStatementFile(@WebParam(name="security", header=true)ObSecurity sec,@WebParam(name="index")String index ,@WebParam(name="noDays")String noDays,@WebParam(name="fromDate")String fromDate,@WebParam(name="toDate")String toDate);

	public ObRetrieveNotificationInboxResponse retrieveBoardNotificationInbox(@WebParam(name="security", header=true)ObSecurity sec,@WebParam(name="notifyType")String notify_type,@WebParam(name="pageNo")String page_no,@WebParam(name="pageSize")String page_size);

	public ObRetrieveNotificationInboxResponse retrievePushNotificationInbox(@WebParam(name="security", header=true)ObSecurity sec,@WebParam(name="pageNo")String page_no,@WebParam(name="pageSize")String page_size);

	public ObRetrieveExchangeRateResponse retrieveExchangeRate(@WebParam(name="security", header=true)ObSecurity sec,@WebParam(name="ccy_code")String ccy_code,@WebParam(name="period_from")String period_from,@WebParam(name="period_to")String period_to);
	
	public ObRetrieveExchangeRateResponse retrieveIndividualExchangeRate(@WebParam(name="security", header=true)ObSecurity sec,@WebParam(name="sellCcy")String sellCcy, @WebParam(name="buyCcy")String buyCcy);
	
	public ObTaskListResponse retrieveTransactionSummary(@WebParam(name="security", header=true)ObSecurity sec,@WebParam(name="trnxSources")String trnxSources, @WebParam(name="masterId")String masterId, @WebParam(name="trxStatus")String trxStatus);
	
	public ObAuthorizationResponse retrieveAuthTransaction(@WebParam(name="security", header=true)ObSecurity sec,@WebParam(name="pageNo")String pageNo,@WebParam(name="pageSize")String pageSize,@WebParam(name="source_trx")List<String> source_trx,@WebParam(name="trx_status")String trx_status,@WebParam(name="value_date_from")String value_date_from,@WebParam(name="value_date_to")String value_date_to,@WebParam(name="pymtMasterId")String pymtMasterId, @WebParam(name="uploadDateFrom")String uploadDateFrom, @WebParam(name="uploadDateTo")String uploadDateTo, @WebParam(name="prodCode")String prodCode);

	public ObAuthorizationResponse performAuthConfirmation(@WebParam(name="security", header=true)ObSecurity sec,@WebParam(name="pageNo")String page_no,@WebParam(name="pageSize")String page_size,@WebParam(name="source_trx")List<String> source_trx,@WebParam(name="trx_status")String trx_status,@WebParam(name="value_date_from")String value_date_from,@WebParam(name="value_date_to")String value_date_to,@WebParam(name="index")String index);

	public ObAuthorizationResponse preAuthorizeTransaction(@WebParam(name="security", header=true)ObSecurity sec,@WebParam(name="idTransaction")String idTransaction,@WebParam(name="action_cd")String action_cd,@WebParam(name="index")String index,@WebParam(name="sourceTrx")String sourceTrx,@WebParam(name="isParentLevel")Boolean isParentLevel);

	public ObAuthorizationResponse requestAuthorizationTAC(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="request_type")String request_type);

	public ObAuthenticationResponse requestChallengeResponse(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="requestType")String requestType);
	
	public ObAuthenticationResponse verifyChallengeResponse(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="requestId")String requestId, @WebParam(name="responseCode")String responseCode);
	
	public ObAuthorizationResponse performAuthorizeTransaction(@WebParam(name="security", header=true)ObSecurity sec,@WebParam(name="idTransaction")String idTransaction,@WebParam(name="responseCode")String responseCode);

	public ObTokenResponse requestCustomerTokenInquiry(@WebParam(name="security", header=true)ObSecurity sec);
	public ObTokenResponse requestSwitchDefaultToken(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="token_type_old")String token_type_old, @WebParam(name="token_type_new")String token_type_new);
	public ObTokenResponse requestSoftwareTokenPreBind1(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="device_finger_print")String device_finger_print);
	public ObTokenResponse requestSoftwareTokenPreBind2(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="device_code")String device_code, @WebParam(name="device_finger_print")String device_finger_print);
	public ObTokenResponse requestSoftwareTokenPostBind(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="device_finger_print")String device_finger_print, @WebParam(name="otip_type")String otip_type, @WebParam(name="appli_name")String appli_name, @WebParam(name="appli_no")Integer appli_no, @WebParam(name="seq_no")Integer seq_no);
	public ObTokenResponse requestSoftwareTokenDelete(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="device_finger_print")String device_finger_print);
	public ObTokenResponse requestSoftwareTokenEligibility(@WebParam(name="security", header=true)ObSecurity sec);
	public ObTokenTnCResponse requestSoftwareTokenTnC(@WebParam(name="security", header=true)ObSecurity sec);
	public ObTokenResponse otpRequest(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="request_type")String request_type);
	public ObTokenResponse otpVerify(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="otp")String otp, @WebParam(name="requestId_stats")String requestId_stats);
	public ObTokenResponse updateEnrolSwToken(@WebParam(name="security", header=true)ObSecurity sec);
	
	public ObAuthenticationResponse performUpdateTNC(@WebParam(name="security", header=true)ObSecurity sec,@WebParam(name="tncAction")String tncAction);

	public ObTaskListResponse retrieveTransactionStatus(@WebParam(name="security", header=true)ObSecurity sec,@WebParam(name="trnxSources")String trnxSources, @WebParam(name="masterId")String masterId );
	
	public ObAuthenticationResponse performResetPassword(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="usr_cd")String usr_cd,@WebParam(name="org_cd")String org_cd, @WebParam(name="email")String email,  @WebParam(name="phone")String phone);
	public ObAuthenticationResponse retrieveResetPassTnC(@WebParam(name="security", header=true)ObSecurity sec);

	
	public ObDashboardInfoResponse retrieveAccountFavList(@WebParam(name="security", header=true)ObSecurity sec );
	public ObFavAccountResponse performUpdateFavAccount(@WebParam(name="security", header=true)ObSecurity sec,@WebParam(name="favIndexList")String favIndexList , @WebParam(name="fav_prd_cd") String fav_prd_cd);
	public ObRecurringResponse retrieveRecurringListFt(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="pageNo")String pageNo, @WebParam(name="list_prod_cd")String list_prod_cd, @WebParam(name="amount")String amount, @WebParam(name="debit_acct_no")String debit_acct_no,@WebParam(name="bene_acct_no")String bene_acct_no ,  @WebParam(name="showNewest")String showNewest, @WebParam(name="recurringId")String recurringId);
	public ObRecurringResponse retrieveRecurringListPymt(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="pageNo")String pageNo, @WebParam(name="biller_code")String billerCode, @WebParam(name="amount")String amount, @WebParam(name="debit_acct_no")String debit_acct_no,@WebParam(name="billing_id")String billingId, @WebParam(name="showNewest")String showNewest, @WebParam(name="recurringId")String recurringId);
	public ObRecurringResponse updateRecurring(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="prod_cd")String prod_cd, @WebParam(name="recurring_id")String recurring_id, @WebParam(name="delete_type")String delete_type );
	
	public ObAuthenticationResponse performBiometricSetup(@WebParam(name="security", header=true)ObSecurity sec,@WebParam(name="action")String action,@WebParam(name="type")String type,@WebParam(name="orgCode")String orgCode,@WebParam(name="usrCode")String usrCode);
	public ObAuthenticationResponse performVerifyPassword(@WebParam(name="security", header=true)ObSecurity sec,@WebParam(name="pString")String pString,@WebParam(name="cString")String cString,@WebParam(name="random_number")String random_number,@WebParam(name="passwordDataBlock")String passwordDataBlock);
	public ObTimeDepositResponse timeDepositAccountOpeningStep1(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="isCheckCutOff")Boolean isCheckCutOff, @WebParam(name="productCode")String productCode);
	public ObTimeDepositResponse timeDepositAccountOpeningProductList(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="currencyCode")String currencyCode);
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
			@WebParam(name="promoCode")String promoCode);
	
	public ObTimeDepositResponse timeDepositAccountOpeningAcknowledgement(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="transactionId")String transactionId, @WebParam(name="ip")String ip);
	public ObTimeDepositResponse timeDepositAccountMaintenanceDetails(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="debitAccountUUID")String debitAccountUUID);
	public ObTimeDepositResponse timeDepositAccountMaintenanceSubmit(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="accountNo")String accountNo, @WebParam(name="rolloverTypeCode")String rolloverTypeCode, @WebParam(name="accountCcy")String accountCcy, @WebParam(name="ip")String ip);
	
	public ObAccountListResponse customerAccountList(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="searchCustNo")String searchCustNo, @WebParam(name="searchCustName")String searchCustName, @WebParam(name="productCode")String productCode, @WebParam(name="pageSize")String pageSize, @WebParam(name="pageNo")String pageNo);
	public ObAccountListResponse customerPortfolioData(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="customerNumber")String customerNumber, @WebParam(name="productCode")String productCode, @WebParam(name="agreementOnly")Boolean agreementOnly);
	public ObAccountListResponse customerPortfolioDownload(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="customerNumber")String customerNumber, @WebParam(name="productCode")String productCode, @WebParam(name="periodFrom")String periodFrom, @WebParam(name="periodTo")String periodTo, @WebParam(name="customer_loan_data")List<CustomerLoanDatav2> customer_loan_data);

	
	
 	public ObAccountAliasListResponse administrationAccountAliasList(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="accountNumber")String accountNumber, @WebParam(name="accountName")String accountName, @WebParam(name="aliasName")String aliasName,@WebParam(name="pageNo")String pageNo,@WebParam(name="pageSize")String pageSize);
 	public ObAccountAliasListResponse administrationAccountAliasMaintenance(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="accountId")String accountId, @WebParam(name="currencyCode")String currencyCode, @WebParam(name="aliasName")String aliasName);
 	
 	public ObAdministrationUserResponse administrationUserManagementDataList(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="userCode")String userCode, @WebParam(name="userName")String userName,@WebParam(name="pageNo")String pageNo,@WebParam(name="pageSize")String pageSize);
 	public ObAdministrationUserResponse administrationUserGeneralMaintenance(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="actionCode")String actionCode, @WebParam(name="userId")String userId,@WebParam(name="userCode")String userCode);
 	public ObAdministrationUserResponse administrationUserProfileList(@WebParam(name="security", header=true)ObSecurity sec);
 	public ObAdministrationUserResponse administrationUserRoleList(@WebParam(name="security", header=true)ObSecurity sec);
 	public ObAdministrationUserResponse administrationUserCreateModify(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="actionCode")String actionCode, @WebParam(name="userId")String userId, @WebParam(name="userCode")String userCode, @WebParam(name="userName")String userName, @WebParam(name="userProfileCode")String userProfile, @WebParam(name="email")String email, @WebParam(name="roleList")List<RoleList> roleList, @WebParam(name="authOwn")String authOwn);
 
	
	public ObAuthorizationResponse authorizationPendingCount(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="category")String category);
	public ObAuthorizationResponse authorizationRetrievePendingAccountList(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="accountNumber")String accountNumber,@WebParam(name="accountName")String accountName);
	public ObAuthorizationResponse authorizationRetrievePendingBeneficiaryList(@WebParam(name="security", header=true)ObSecurity sec);
	public ObAuthorizationResponse authorizationRetrievePendingUserList(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="userName")String userName, @WebParam(name="userCode")String userCode);
	public ObAuthorizationResponse authorizationPerformApprovalPendingAccount(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="recordIds")List<String> recordIds,@WebParam(name="action")String action);
	public ObAuthorizationResponse authorizationPerformApprovalPendingBeneficiary(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="recordIds")List<String> recordIds,@WebParam(name="action")String action);
	public ObAuthorizationResponse authorizationPerformApprovalPendingUser(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="recordIds")List<String> recordIds,@WebParam(name="action")String action);
	
	public ObAdminBeneficiaryListResponse administrationBeneficiaryList(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="productCode")String productCode);
	
	public ObProfileMaintResponse userProfileAuthList(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="pageNo")String page_no,@WebParam(name="pageSize")String page_size);
	public ObProfileMaintResponse userProfileAuthSubmit(@WebParam(name="security", header=true)ObSecurity sec,  @WebParam(name="action")String action , @WebParam(name="record_id")String record_id );
	public ObProfileMaintResponse policyAuthList(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="pageNo")String page_no,@WebParam(name="pageSize")String page_size);
	public ObProfileMaintResponse policyAuthSubmit(@WebParam(name="security", header=true)ObSecurity sec,  @WebParam(name="action")String action , @WebParam(name="record_id")String record_id );
	public ObProfileMaintResponse accessProfileAuthList(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="pageNo")String page_no,@WebParam(name="pageSize")String page_size);
	public ObProfileMaintResponse accessProfileAuthSubmit(@WebParam(name="security", header=true)ObSecurity sec,  @WebParam(name="action")String action , @WebParam(name="record_id")String record_id );

	public ObTransactionSummaryResponse performMT103Inquiry(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="productCode")String productCode, @WebParam(name="paymentMasterId")String paymentMasterId, @WebParam(name="bankRef")String bankRef);
	
	public ObTNCResponse retrieveTnCv2(@WebParam(name="security", header=true)ObSecurity sec,@WebParam(name="tncType")String tncType,@WebParam(name="isLocal")Boolean isLocal, @WebParam(name="ccyCode")String ccyCode);

	public ObTransactionResponse transactionFundTransferConfirmSubmit(@WebParam(name="security", header=true)ObSecurity sec, 
			@WebParam(name="transferServiceType")String transferServiceType,
			@WebParam(name="debitAccountUUID")String debitAccountUUID,
			@WebParam(name="beneAccountUUID")String beneAccountUUID,
			@WebParam(name="beneAccountNo")String beneAccountNo,
			@WebParam(name="beneAccountName")String beneAccountName,
			@WebParam(name="beneAccountCcy")String beneAccountCcy,
			@WebParam(name="beneAddress1")String beneAddress1,
			@WebParam(name="beneAddress2")String beneAddress2,
			@WebParam(name="beneAddress3")String beneAddress3,
			@WebParam(name="beneBankCode")String beneBankCode,
			@WebParam(name="beneBankBranch")String beneBankBranch,
			@WebParam(name="residentStatus")String residentStatus,
			@WebParam(name="beneCategory")String beneCategory,
			@WebParam(name="senderName")String senderName,
			@WebParam(name="remitterCategory")String remitterCategory,
			@WebParam(name="amount")BigDecimal amount,
			@WebParam(name="amountCcy")String amountCcy,
			@WebParam(name="valueDate")String valueDate,
			@WebParam(name="customerReferenceNo")String customerReferenceNo,
			@WebParam(name="remark")String remark,
			@WebParam(name="isSaveBene")Boolean isSaveBene,
			@WebParam(name="isFavBene")Boolean isFavBene,
			@WebParam(name="beneAliasName")String beneAliasName,
			@WebParam(name="isSendNotificationSender")Boolean isSendNotificationSender,
			@WebParam(name="isNotifySenderCompleted")Boolean isNotifySenderCompleted,
			@WebParam(name="isNotifySenderRejected")Boolean isNotifySenderRejected,
			@WebParam(name="isNotifySenderSuspected")Boolean isNotifySenderSuspected,
			@WebParam(name="emailNotificationSender")String emailNotificationSender,
			@WebParam(name="isSendNotificationBene")Boolean isSendNotificationBene,
			@WebParam(name="emailNotificationBene")String emailNotificationBene,
			@WebParam(name="isRecurring")Boolean isRecurring,
			@WebParam(name="recurringType")String recurringType,
			@WebParam(name="recurringValue")String recurringValue,
			@WebParam(name="recurringStartDate")String recurringStartDate,
			@WebParam(name="recurringEndDate")String recurringEndDate,
			@WebParam(name="fxType")String fxType,
			@WebParam(name="fxDealerName")String fxDealerName,
			@WebParam(name="fxDealerRate")BigDecimal fxDealerRate,
			@WebParam(name="fxContractNo")String fxContractNo,
			@WebParam(name="fxRate")BigDecimal fxRate,
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
			@WebParam(name="beneID") String beneID,
			@WebParam(name="beneBankNetworkClearingCode") String beneBankNetworkClearingCode,
			@WebParam(name="beneBankCity") String beneBankCity,
			@WebParam(name="exec_time_batch_cd")String exec_time_batch_cd,
			@WebParam(name="recurring_exec_time_batch_cd")String recurring_exec_time_batch_cd,
			@WebParam(name="additional_info")String additional_info,
			@WebParam(name="bene_bank_id")String bene_bank_id,
			@WebParam(name="trx_purpose")String trx_purpose,
			@WebParam(name="charges_acct_no")String charges_acct_no,
			@WebParam(name="charges_acct_ccy")String charges_acct_ccy,
			@WebParam(name="sender_address1")String sender_address1,
			@WebParam(name="sender_address2")String sender_address2,
			@WebParam(name="sender_address3")String sender_address3,
			@WebParam(name="ip")String ip
		);
	
	public ObPaymentResponse paymentConfirmSubmit(@WebParam(name="security", header=true)ObSecurity sec,
			@WebParam(name="billerType")String billerType,
			@WebParam(name="debitAccountUUID")String debitAccountUUID,
			@WebParam(name="billerName")String billerName,
			@WebParam(name="amountOptionId")String amountOptionId,
			@WebParam(name="amount")BigDecimal amount,
			@WebParam(name="valueDate")String valueDate,
			@WebParam(name="customerReferenceNo")String customerReferenceNo,
			@WebParam(name="isSavePayee")Boolean isSavePayee,
			@WebParam(name="payeeNickName")String payeeNickName,
			@WebParam(name="isSendNotificationSender")Boolean isSendNotificationSender,
			@WebParam(name="isNotifySenderCompleted")Boolean isNotifySenderCompleted,
			@WebParam(name="isNotifySenderRejected")Boolean isNotifySenderRejected,
			@WebParam(name="isNotifySenderSuspected")Boolean isNotifySenderSuspected,
			@WebParam(name="emailNotificationSender")String emailNotificationSender,
			@WebParam(name="isRecurring")Boolean isRecurring,
			@WebParam(name="recurringType")String recurringType,
			@WebParam(name="recurringValue")String recurringValue,
			@WebParam(name="recurringStartDate")String recurringStartDate,
			@WebParam(name="recurringEndDate")String recurringEndDate,
			@WebParam(name="exec_time_batch_cd")String exec_time_batch_cd,
			@WebParam(name="recurring_exec_time_batch_cd")String recurring_exec_time_batch_cd,
			@WebParam(name="ip")String ip,
			@WebParam(name="lang")String lang);
	
	public ObTransNotiAuthResponse pendingAuthNotiList(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="pageNo")String page_no,@WebParam(name="pageSize")String page_size);
	public ObTransNotiAuthResponse pendingAuthNotiMaint(@WebParam(name="security", header=true)ObSecurity sec,  @WebParam(name="action")String action , @WebParam(name="record_id")String record_id );
	
	public ObRegistrationResponse retrieveInquiryRegistration(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="role")String role,@WebParam(name="email")String email,@WebParam(name="mobileNo")String mobileNo,@WebParam(name="ktpNo")String ktpNo,@WebParam(name="registrationNo")String registrationNo,@WebParam(name="accountName")String accountName,@WebParam(name="verificationNo")String verificationNo,@WebParam(name="revisionNo")String revisionNo);
	public ObRegistrationResponse retrieveInputterSignerData(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="user_id")String user_id, @WebParam(name="record_id")String record_id);
	public ObRegistrationResponse addDeleteSignerData(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="action")String action, @WebParam(name="user_id")String user_id, @WebParam(name="name")String name,@WebParam(name="ktp_no")String ktp_no,@WebParam(name="npwp_no")String npwp_no,@WebParam(name="gender")String gender,@WebParam(name="birth_place")String birth_place,@WebParam(name="birth_date")String birth_date,@WebParam(name="address")String address,@WebParam(name="province")String province,@WebParam(name="city")String city,@WebParam(name="email")String email,@WebParam(name="phone")String phone,
			@WebParam(name="ktp_photo") @XmlMimeType("application/octet-stream")DataHandler ktp_photo,
			@WebParam(name="npwp_photo") @XmlMimeType("application/octet-stream")DataHandler npwp_photo,
			@WebParam(name="ktp_file_name")String ktp_file_name, @WebParam(name="ktp_file_ext")String ktp_file_ext,
			@WebParam(name="npwp_file_name")String npwp_file_name, @WebParam(name="npwp_file_ext")String npwp_file_ext, @WebParam(name="record_id")String record_id,
			@WebParam(name="isBase64") Boolean isBase64);
			
	public ObRegistrationResponse registrationRevisionSubmit(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="role")String role, @WebParam(name="record_id")String record_id, @WebParam(name="version_no")Integer version_no,@WebParam(name="registration_no")String registration_no,@WebParam(name="revision_no")String revision_no,@WebParam(name="name")String name,@WebParam(name="ktp_no")String ktp_no,@WebParam(name="address")String address,@WebParam(name="email")String email,@WebParam(name="phone")String phone,@WebParam(name="signature_flow")String signature_flow,@WebParam(name="npwp_no")String npwp_no,@WebParam(name="gender")String gender,@WebParam(name="birth_place")String birth_place,@WebParam(name="birth_date")String birth_date,@WebParam(name="province")String province,@WebParam(name="city")String city,
			@WebParam(name="ktp_photo") @XmlMimeType("application/octet-stream")DataHandler ktp_photo,
			@WebParam(name="npwp_photo") @XmlMimeType("application/octet-stream")DataHandler npwp_photo,
			@WebParam(name="ktp_file_name")String ktp_file_name, @WebParam(name="ktp_file_ext")String ktp_file_ext,
			@WebParam(name="npwp_file_name")String npwp_file_name, @WebParam(name="npwp_file_ext")String npwp_file_ext,
			@WebParam(name="signers_changed")Boolean signers_changed,  @WebParam(name="signerList")List<SignerList> signerList,
			@WebParam(name="isBase64") Boolean isBase64,@WebParam(name="sequence")Integer sequence);
	public ObRegistrationResponse registrationAckSubmit(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="record_id")String record_id, @WebParam(name="version_no")Integer version_no,@WebParam(name="registration_no")String registration_no,@WebParam(name="verification_no")String verification_no,@WebParam(name="name")String name,@WebParam(name="ktp_no")String ktp_no,@WebParam(name="address")String address,@WebParam(name="email")String email,@WebParam(name="phone")String phone,@WebParam(name="npwp_no")String npwp_no,@WebParam(name="gender")String gender,@WebParam(name="birth_place")String birth_place,@WebParam(name="birth_date")String birth_date,@WebParam(name="province")String province,@WebParam(name="city")String city,
			@WebParam(name="ktp_photo") @XmlMimeType("application/octet-stream")DataHandler ktp_photo,
			@WebParam(name="npwp_photo") @XmlMimeType("application/octet-stream")DataHandler npwp_photo,
			@WebParam(name="ktp_file_name")String ktp_file_name, @WebParam(name="ktp_file_ext")String ktp_file_ext,
			@WebParam(name="npwp_file_name")String npwp_file_name, @WebParam(name="npwp_file_ext")String npwp_file_ext, @WebParam(name="sequence")Integer sequence,
			@WebParam(name="isBase64") Boolean isBase64);
	public ObRegistrationResponse registrationCancel(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="role")String role, @WebParam(name="record_id")String record_id, @WebParam(name="version_no")Integer version_no,@WebParam(name="registration_no")String registration_no,@WebParam(name="name")String name,@WebParam(name="ktp_no")String ktp_no,@WebParam(name="address")String address,@WebParam(name="email")String email,@WebParam(name="phone")String phone,@WebParam(name="npwp_no")String npwp_no,@WebParam(name="gender")String gender,@WebParam(name="birth_place")String birth_place,@WebParam(name="birth_date")String birth_date,@WebParam(name="province")String province,@WebParam(name="city")String city,
			@WebParam(name="ktp_photo") @XmlMimeType("application/octet-stream")DataHandler ktp_photo,
			@WebParam(name="npwp_photo") @XmlMimeType("application/octet-stream")DataHandler npwp_photo,
			@WebParam(name="ktp_file_name")String ktp_file_name, @WebParam(name="ktp_file_ext")String ktp_file_ext,
			@WebParam(name="npwp_file_name")String npwp_file_name, @WebParam(name="npwp_file_ext")String npwp_file_ext, @WebParam(name="verification_no")String verification_no,
			@WebParam(name="isBase64") Boolean isBase64);
	
	public ObRegistrationResponse retrieveProvinceList(@WebParam(name="security", header=true)ObSecurity sec);
	
	public ObAccountListResponse customerPortfolioDatav2(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="cifNumber")String cifNumber, @WebParam(name="productCode")String productCode, @WebParam(name="agreementID")String agreementID);
		
	public ObSwiftOutboundTrackingResponse performSWIFTOutboundTracking(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="remittance_no")String remittance_no);
	
	public ObUpdateDeviceAliasResponse updateDeviceAlias(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="device_id")String device_id, @WebParam(name="device_alias")String device_alias);
	
	public ObDeviceUnbindgResponse performDeviceUnBindg(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="device_id")String device_id, @WebParam(name="cif")String cif);
	
	public ObTransactionSourceOfFundResponse retrieveChargeAccountList(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="transferServiceType")String transferServiceType, @WebParam(name="currency")String currency, @WebParam(name="accountNo")String accountNo);
		
	public ObBlockMyUserResponse performBlockMyUser(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="org_cd")String org_cd, @WebParam(name="usr_cd")String usr_cd, @WebParam(name="email")String email, @WebParam(name="phone")String phone);
		
	public ObAccountOverviewResponse estatementDownload(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="acct_no")String acct_no, @WebParam(name="acct_ccy")String acct_ccy, @WebParam(name="periode")String periode);
	
	public ObDocumentDownloadResponse documentDownload(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="documentId")String documentId, @WebParam(name="documentType")String documentType);
	
	public ObTransactionResponse retrieveBeneficiaryCategory(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="purposeCode")String purposeCode);
	public ObTransactionResponse retrieveBeneficiaryCountry(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="purposeCode")String purposeCode);
	public ObTransactionResponse retrieveRemitterCountry(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="purposeCode")String purposeCode, @WebParam(name="beneCategoryCode") String beneCategoryCode, @WebParam(name="beneCountryCode") String beneCountryCode);
	public ObTransactionResponse retrieveRemitterCategory(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="purposeCode")String purposeCode, @WebParam(name="beneCategoryCode") String beneCategoryCode, @WebParam(name="beneCountryCode") String beneCountryCode, @WebParam(name="remitterCountryCode") String remitterCountryCode);
	public ObTransactionResponse retrieveBeneficiaryAffiliation(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="purposeCode")String purposeCode, @WebParam(name="beneCategoryCode") String beneCategoryCode, @WebParam(name="beneCountryCode") String beneCountryCode, @WebParam(name="remitterCountryCode") String remitterCountryCode, @WebParam(name="remitterCategoryCode") String remitterCategoryCode);

	public ObAuthenticationResponse performLoginv2(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="orgId")String orgId, @WebParam(name="usrId")String usrId, @WebParam(name="pString")String pString, @WebParam(name="cString")String cString, @WebParam(name="random_number")String random_number, @WebParam(name="authType")String authType, @WebParam(name="passwordDataBlock")String passwordDataBlock, @WebParam(name="ip")String ip);
	
	public ObAuthenticationResponse performDeviceBindingv2(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="tac")String tac, @WebParam(name="unbindIndex")String unbindIndex);
	
	public ObDeviceBindingResponse performDeviceBindingUpdate(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="device_id")String device_id, @WebParam(name="cif")String cif, @WebParam(name="action")String action);
	
	public ObTransactionLimitEqUSDResponse retrieveTransactionLimitPurchase(@WebParam(name="security", header=true)ObSecurity sec, @WebParam(name="fromCcy") String fromCcy, @WebParam(name="toCcy") String toCcy, @WebParam(name="fromAmount") String fromAmount, @WebParam(name="toAmount") String toAmount, @WebParam(name="accountNo") String accountNo, @WebParam(name="cancelBit") String cancelBit);

}