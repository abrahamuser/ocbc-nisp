package com.silverlake.mleb.mcb.module.func.administration;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.ObBeneAccountBean;
import com.silverlake.mleb.mcb.bean.administration.ObAdminBeneficiaryListRequest;
import com.silverlake.mleb.mcb.bean.administration.ObAdminBeneficiaryListResponse;
import com.silverlake.mleb.mcb.bean.administration.ObAdminBeneficiaryListSessionCache;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.module.common.CacheSessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.administration.BeneficiaryListRequestData;
import com.silverlake.mleb.mcb.module.vc.administration.BeneficiaryListResponseData;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.transaction.BeneficiaryList;
import com.silverlake.mleb.mcb.util.StringUtil;

/**
 * Purpose: To retrieve the list of beneficiary list for administration purpose
 * Omni Web Services:
 * administration/beneficiary_ft/list
 * 
 * @author Alex Loo
 *
 */
@Service
public class AdministrationBeneficiaryList extends CacheSessionFuncServices<ObAdminBeneficiaryListRequest, ObAdminBeneficiaryListResponse, ObAdminBeneficiaryListSessionCache>{
	private static Logger log = LogManager.getLogger(AdministrationBeneficiaryList.class);
	@Override
	public void processInternalWithCache(String locale, String sessionId, String trxId, ObAdminBeneficiaryListRequest requestBean, ObAdminBeneficiaryListResponse responseBean, ObAdminBeneficiaryListSessionCache cacheBean, VCGenericService vcService) throws Exception {
		BeneficiaryListRequestData requestData = new BeneficiaryListRequestData();
		BeneficiaryListResponseData responseData;
		processRequest(locale, requestData, requestBean, responseBean);
		if(responseBean.getObHeader().getStatusCode() != null){
			return;
		}
		VCResponse<BeneficiaryListResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.ADMINSTRATION_BENEFICIARY_FT_LIST, 
				requestData, 
				BeneficiaryListResponseData.class, 
				true);
		if(processVCResponseError(vcResponse, responseBean)){
			return;
		}
		responseData = vcResponse.getData();
		processResponse(locale, requestData, responseData, requestBean, responseBean, cacheBean);
		cacheBean.setBeneficiaryListResponseData(responseBean);
	}
	
	private void processRequest(String locale, BeneficiaryListRequestData requestData, ObAdminBeneficiaryListRequest requestBean, ObAdminBeneficiaryListResponse responseBean) throws Exception{
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		if(requestBean.getTransferServiceType() != null && !requestBean.getTransferServiceType().isEmpty()) {
			requestData.setSrc_prod_code(requestBean.getTransferServiceType());
		}
		requestData.setPage_no(1);
		requestData.setPage_size(Integer.MAX_VALUE);
	}
	
	private void processResponse(String locale, BeneficiaryListRequestData requestData, BeneficiaryListResponseData responseData, ObAdminBeneficiaryListRequest requestBean, ObAdminBeneficiaryListResponse responseBean, ObAdminBeneficiaryListSessionCache cacheBean) throws Exception{
		responseBean.setBeneficiaryList(new ArrayList<ObBeneAccountBean>());
		if(responseData.getBene_list() != null){
			int i=1;
			for(BeneficiaryList beneficiaryList:responseData.getBene_list()) {
				String uuid = StringUtil.leftPadString(Integer.toString(i), 5, "0");
				beneficiaryList.setUuid("AB"+uuid);
				ObBeneAccountBean obBeneAccountBean = new ObBeneAccountBean();
				obBeneAccountBean.setBeneID(beneficiaryList.getRecord_id());
				obBeneAccountBean.setBeneAccountCcy(beneficiaryList.getAccount_currency());
				obBeneAccountBean.setBeneAccountNo(beneficiaryList.getAccount_number());
				obBeneAccountBean.setBeneAccountName(beneficiaryList.getAccount_name());
				obBeneAccountBean.setBeneNickName(beneficiaryList.getNick_name());
				obBeneAccountBean.setBeneficiaryBankName(beneficiaryList.getBank_name());
				obBeneAccountBean.setBeneficiaryBankCode(beneficiaryList.getBank_code());
				obBeneAccountBean.setBeneBankClearingId(beneficiaryList.getBank_network_clearing_code());
				obBeneAccountBean.setBeneficiaryBankBranch(beneficiaryList.getBank_branch_name());
				obBeneAccountBean.setProvinceCode(beneficiaryList.getProvince_code());
				obBeneAccountBean.setBeneBankCityId(beneficiaryList.getCity_code());
				obBeneAccountBean.setBeneAddress1(beneficiaryList.getBene_address1());
				obBeneAccountBean.setBeneAddress2(beneficiaryList.getBene_address2());
				obBeneAccountBean.setBeneAddress3(beneficiaryList.getBene_address3());
				obBeneAccountBean.setBankCountryCode(beneficiaryList.getBank_country_code());
				obBeneAccountBean.setIsFavFlag(beneficiaryList.getIs_favorite());
				obBeneAccountBean.setAccountId(beneficiaryList.getRecord_id());
				obBeneAccountBean.setAccountUUID(beneficiaryList.getUuid());
				obBeneAccountBean.setBankAddress1(beneficiaryList.getBank_address1());
				obBeneAccountBean.setBankCity(beneficiaryList.getBank_city());
				obBeneAccountBean.setProxy_data(beneficiaryList.getProxy_data());
				obBeneAccountBean.setProxy_type(beneficiaryList.getProxy_type());
				
				responseBean.getBeneficiaryList().add(obBeneAccountBean);
				i++;
			}
		}
		responseBean.setTotalRecords(responseData.getTotal_records());
	}
}
