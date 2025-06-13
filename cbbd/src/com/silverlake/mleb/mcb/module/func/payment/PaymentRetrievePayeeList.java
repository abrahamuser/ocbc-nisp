package com.silverlake.mleb.mcb.module.func.payment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.ObPaymentBillerBean;
import com.silverlake.mleb.mcb.bean.ObPaymentBillerGroupBean;
import com.silverlake.mleb.mcb.bean.ObPaymentRequest;
import com.silverlake.mleb.mcb.bean.ObPaymentResponse;
import com.silverlake.mleb.mcb.bean.ObPaymentSessionCache;
import com.silverlake.mleb.mcb.bean.Payee;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.module.common.CacheSessionFuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.transaction.PayeeList;
import com.silverlake.mleb.mcb.module.vc.transaction.PayeeRequestData;
import com.silverlake.mleb.mcb.module.vc.transaction.PayeeResponseData;

/**
 * Purpose: Get biller list including it's category
 * 
 * Omni Web Services:
 * others/billing_organization
 * 
 * @author Alex Loo
 *
 */
@Service
public class PaymentRetrievePayeeList extends CacheSessionFuncServices<ObPaymentRequest, ObPaymentResponse, ObPaymentSessionCache>{
	private static Logger log = LogManager.getLogger(PaymentRetrievePayeeList.class);
	
	@Override
	public void processInternalWithCache(String locale, String sessionId, String trxId, ObPaymentRequest requestBean, ObPaymentResponse responseBean, ObPaymentSessionCache cacheBean, VCGenericService vcService) throws Exception {
		if((requestBean.getGroupName() == null || requestBean.getGroupName().isEmpty()) && 
				(requestBean.getBillerCode() == null || requestBean.getBillerCode().isEmpty())){
			responseBean.getObHeader().setStatusCode(MiBConstant.MIB_INVALID_INPUT);
			log.info("Either groupname or billercode is required");
			return;
		}
		PayeeRequestData requestData = new PayeeRequestData();
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		requestData.setGroup_name(requestBean.getGroupName());
		requestData.setBiller_code(requestBean.getBillerCode());
		requestData.setPage_no(1);
		requestData.setPage_size(Integer.MAX_VALUE);
		PayeeResponseData responseData;
		VCResponse<PayeeResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.TRANSACTION_PAYEES, 
				requestData, 
				PayeeResponseData.class, 
				true);
		if(processVCResponseError(vcResponse, responseBean)){
			return;
		}
		
		responseData = vcResponse.getData();
		
		responseBean.setPayeeList(new ArrayList<Payee>(5));
		if(responseData.getPayee_list() != null){
			for(PayeeList payeeList:responseData.getPayee_list()){
				Payee payeeBean = new Payee();
				payeeBean.setBillerCustNickName(payeeList.getNick_name());
				payeeBean.setBillerCd(payeeList.getBiller_code());
				payeeBean.setBillerId(payeeList.getRecord_id());
				if(locale.equalsIgnoreCase(MiBConstant.LANG_EN)){
					payeeBean.setBillerName(payeeList.getBiller_name_en());
				}else if(locale.toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase())){
					payeeBean.setBillerName(payeeList.getBiller_name_cn());
				}else{
					payeeBean.setBillerName(payeeList.getBiller_name_id());
				}
				payeeBean.setPayeeId(payeeList.getBilling_id());
				payeeBean.setPpType(payeeList.getBiller_type());
				responseBean.getPayeeList().add(payeeBean);
			}
		}
		cacheBean.setPayeeResponseData(responseData);
	}
}
