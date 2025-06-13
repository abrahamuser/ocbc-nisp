package com.silverlake.mleb.mcb.bean;

import java.io.Serializable;
import java.util.List;

public class ObManageBeneficiaryListResponse extends ObResponse implements Serializable {

	
	private static final long serialVersionUID = 6841511757383795001L;
	
	private List<ObManageBeneficiaryBean> listTransferBeneficiary;
	
	private List<ObManageBeneficiaryBean> listPaymentBeneficiary;
	
	private List<ObManageBeneficiaryBean> listPurchaseBeneficiary;
	
	private List<ObPaymentBillerGroupBean> listPaymentBillerGroup;
	
	private List<ObManageBeneficiaryBean> listTelegraphicTransferBeneficiary;

	public List<ObManageBeneficiaryBean> getListTransferBeneficiary() {
		return listTransferBeneficiary;
	}

	public void setListTransferBeneficiary(
			List<ObManageBeneficiaryBean> listTransferBeneficiary) {
		this.listTransferBeneficiary = listTransferBeneficiary;
	}

	public List<ObManageBeneficiaryBean> getListPaymentBeneficiary() {
		return listPaymentBeneficiary;
	}

	public void setListPaymentBeneficiary(
			List<ObManageBeneficiaryBean> listPaymentBeneficiary) {
		this.listPaymentBeneficiary = listPaymentBeneficiary;
	}

	public List<ObManageBeneficiaryBean> getListPurchaseBeneficiary() {
		return listPurchaseBeneficiary;
	}

	public void setListPurchaseBeneficiary(
			List<ObManageBeneficiaryBean> listPurchaseBeneficiary) {
		this.listPurchaseBeneficiary = listPurchaseBeneficiary;
	}

	public List<ObPaymentBillerGroupBean> getListPaymentBillerGroup() {
		return listPaymentBillerGroup;
	}

	public void setListPaymentBillerGroup(
			List<ObPaymentBillerGroupBean> listPaymentBillerGroup) {
		this.listPaymentBillerGroup = listPaymentBillerGroup;
	}

	public List<ObManageBeneficiaryBean> getListTelegraphicTransferBeneficiary() {
		return listTelegraphicTransferBeneficiary;
	}

	public void setListTelegraphicTransferBeneficiary(
			List<ObManageBeneficiaryBean> listTelegraphicTransferBeneficiary) {
		this.listTelegraphicTransferBeneficiary = listTelegraphicTransferBeneficiary;
	}
	
}
