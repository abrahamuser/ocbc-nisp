package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

import com.silverlake.mleb.mcb.bean.common.ObResultBean;
import com.silverlake.mleb.mcb.bean.common.ObUserDataBean;
import com.silverlake.mleb.mcb.module.vc.authorization.AuthPayment;
import com.silverlake.mleb.mcb.module.vc.authorization.AuthResult;
import com.silverlake.mleb.mcb.module.vc.authorization.Backdate;
import com.silverlake.mleb.mcb.module.vc.transaction.Transaction;
import com.silverlake.mleb.mcb.module.vc.transaction.TransactionSum;

public class ObAuthorizationResponse extends ObResponseCache  implements Serializable
{
	private List<Backdate> list_backdated_info;
	private List<AuthResult> list_auth_result_info;
	private List<Transaction> list_trx;
	private String total_rows;
	private String total_transaction;
	private Integer totalRecords;
	private String idTransaction;
	private List<AuthPayment> list_payment;
	private List<String> list_restriction;
	private List<TransactionSum> transactionSum;
	
	private String request_id;
	private String challenge_code;
	
	private int resendTagCount;
	private int resendTagMax;
	private String resendTagDateTime;
	private int resendTagInterval;
	private String descriptions;
	private List<Transaction> productList;
	
	//Authorization for Administration summary screen
	private List<ObPendingAuthCountBean> pendingAuthCountList;
	
	//Authorization for pending beneficiary maintenance
	private List<ObBeneAccountBean> beneficiaryList;
	
	//Authorization for pending user maintenance
	private List<ObUserDataBean> userDataList;
	
	//Authorization for pending account maintenance
		private List<ObAccountBean> accountList;
	
	//Authorization common fields
	private List<ObResultBean> resultList;
	
	public List<Backdate> getList_backdated_info() {
		return list_backdated_info;
	}
	public void setList_backdated_info(List<Backdate> list_backdated_info) {
		this.list_backdated_info = list_backdated_info;
	}
	public List<AuthResult> getList_auth_result_info() {
		return list_auth_result_info;
	}
	public void setList_auth_result_info(List<AuthResult> list_auth_result_info) {
		this.list_auth_result_info = list_auth_result_info;
	}
	public List<Transaction> getList_trx() {
		return list_trx;
	}
	public void setList_trx(List<Transaction> list_trx) {
		this.list_trx = list_trx;
	}
	public String getTotal_rows() {
		return total_rows;
	}
	public void setTotal_rows(String total_rows) {
		this.total_rows = total_rows;
	}
	public String getTotal_transaction() {
		return total_transaction;
	}
	public void setTotal_transaction(String total_transaction) {
		this.total_transaction = total_transaction;
	}
	public String getIdTransaction() {
		return idTransaction;
	}
	public void setIdTransaction(String idTransaction) {
		this.idTransaction = idTransaction;
	}
	public List<AuthPayment> getList_payment() {
		return list_payment;
	}
	public void setList_payment(List<AuthPayment> list_payment) {
		this.list_payment = list_payment;
	}
	public List<String> getList_restriction() {
		return list_restriction;
	}
	public void setList_restriction(List<String> list_restriction) {
		this.list_restriction = list_restriction;
	}
	public List<TransactionSum> getTransactionSum() {
		return transactionSum;
	}
	public void setTransactionSum(List<TransactionSum> transactionSum) {
		this.transactionSum = transactionSum;
	}
	public String getRequest_id() {
		return request_id;
	}
	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}
	public String getChallenge_code() {
		return challenge_code;
	}
	public void setChallenge_code(String challenge_code) {
		this.challenge_code = challenge_code;
	}
	public int getResendTagCount() {
		return resendTagCount;
	}
	public void setResendTagCount(int resendTagCount) {
		this.resendTagCount = resendTagCount;
	}
	public int getResendTagMax() {
		return resendTagMax;
	}
	public void setResendTagMax(int resendTagMax) {
		this.resendTagMax = resendTagMax;
	}
	public String getResendTagDateTime() {
		return resendTagDateTime;
	}
	public void setResendTagDateTime(String resendTagDateTime) {
		this.resendTagDateTime = resendTagDateTime;
	}
	public int getResendTagInterval() {
		return resendTagInterval;
	}
	public void setResendTagInterval(int resendTagInterval) {
		this.resendTagInterval = resendTagInterval;
	}
	public String getDescriptions() {
		return descriptions;
	}
	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}
	public List<Transaction> getProductList() {
		return productList;
	}
	public void setProductList(List<Transaction> productList) {
		this.productList = productList;
	}
	public Integer getTotalRecords() {
		return totalRecords;
	}
	public List<ObPendingAuthCountBean> getPendingAuthCountList() {
		return pendingAuthCountList;
	}
	public List<ObBeneAccountBean> getBeneficiaryList() {
		return beneficiaryList;
	}
	public List<ObUserDataBean> getUserDataList() {
		return userDataList;
	}
	public List<ObAccountBean> getAccountList() {
		return accountList;
	}
	public List<ObResultBean> getResultList() {
		return resultList;
	}
	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}
	public void setPendingAuthCountList(List<ObPendingAuthCountBean> pendingAuthCountList) {
		this.pendingAuthCountList = pendingAuthCountList;
	}
	public void setBeneficiaryList(List<ObBeneAccountBean> beneficiaryList) {
		this.beneficiaryList = beneficiaryList;
	}
	public void setUserDataList(List<ObUserDataBean> userDataList) {
		this.userDataList = userDataList;
	}
	public void setAccountList(List<ObAccountBean> accountList) {
		this.accountList = accountList;
	}
	public void setResultList(List<ObResultBean> resultList) {
		this.resultList = resultList;
	}
	
	
	
}
