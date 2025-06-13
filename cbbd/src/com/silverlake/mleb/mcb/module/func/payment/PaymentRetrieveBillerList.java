package com.silverlake.mleb.mcb.module.func.payment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silverlake.mleb.mcb.bean.ObPaymentBillerBean;
import com.silverlake.mleb.mcb.bean.ObPaymentBillerGroupBean;
import com.silverlake.mleb.mcb.bean.ObPaymentRequest;
import com.silverlake.mleb.mcb.bean.ObPaymentResponse;
import com.silverlake.mleb.mcb.bean.ObPaymentSessionCache;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.entity.GeneralCode;
import com.silverlake.mleb.mcb.entity.dao.GeneralCodeDAO;
import com.silverlake.mleb.mcb.module.common.CacheSessionFuncServices;
import com.silverlake.mleb.mcb.module.func.usermanagement.AdministrationUserCreateModify;
import com.silverlake.mleb.mcb.module.vc.common.VCGenericService;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.holiday.ListHolidayCalendar;
import com.silverlake.mleb.mcb.module.vc.others.BillingOrganizationList;
import com.silverlake.mleb.mcb.module.vc.others.BillingOrganizationRequestData;
import com.silverlake.mleb.mcb.module.vc.others.BillingOrganizationResponseData;

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
public class PaymentRetrieveBillerList extends CacheSessionFuncServices<ObPaymentRequest, ObPaymentResponse, ObPaymentSessionCache>{
	private static Logger log = LogManager.getLogger(PaymentRetrieveBillerList.class);
	
	private Comparator<GeneralCode> generalCodeComparator = new Comparator<GeneralCode>(){
		@Override
		public int compare(GeneralCode o1, GeneralCode o2) {
			return o1.getGnCode().compareTo(o2.getGnCode());
		}
	};
	
	@Autowired GeneralCodeDAO gnDao;
	
	@Override
	public void processInternalWithCache(String locale, String sessionId, String trxId, ObPaymentRequest requestBean, ObPaymentResponse responseBean, ObPaymentSessionCache cacheBean, VCGenericService vcService) throws Exception {
		
		BillingOrganizationRequestData requestData = new BillingOrganizationRequestData();
		log.info("---------"+requestBean.getObHeader().getApp_mode());
		requestData.setOrg_cd(requestBean.getObUser().getOrgId());
		requestData.setUsr_cd(requestBean.getObUser().getLoginId());
		if(requestBean.getBillerType() != null && !requestBean.getBillerType().isEmpty())
			requestData.setGrp_name(requestBean.getBillerType());
		BillingOrganizationResponseData responseData;
		VCResponse<BillingOrganizationResponseData> vcResponse = vcService.callOmniVC(
				VCMethodConstant.REST_METHODS.OTHERS_BILLING_ORGANIZATION, 
				requestData, 
				BillingOrganizationResponseData.class, 
				true);
		if(processVCResponseError(vcResponse, responseBean)){
			return;
		}
		
		responseData = vcResponse.getData();
		
		responseBean.setBillerGroupList(new ArrayList<ObPaymentBillerGroupBean>(10));
		List<GeneralCode> imageList = null;
		if(requestBean.getObHeader().getApp_mode() != null && !requestBean.getObHeader().getApp_mode().isEmpty() && requestBean.getObHeader().getApp_mode().equalsIgnoreCase("D")){
		imageList = gnDao.getAllPaymentGroupImageDark();
		}else {
		imageList = gnDao.getAllPaymentGroupImage();	
		}
		if(responseData.getBiller_list() != null){
			for(BillingOrganizationList billingOrgList:responseData.getBiller_list()){
				ObPaymentBillerGroupBean billerGroupItem = new ObPaymentBillerGroupBean();
				billerGroupItem.setBillerGrpId(billingOrgList.getCategory_id());
				if(locale.equalsIgnoreCase(MiBConstant.LANG_EN)){
					billerGroupItem.setBillerGrpName(billingOrgList.getGroup_name_en());
				}else if(locale.toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase())){
					billerGroupItem.setBillerGrpName(billingOrgList.getGroup_name_cn());
				}else{
					billerGroupItem.setBillerGrpName(billingOrgList.getGroup_name_id());
				}
				int result = Collections.binarySearch(responseBean.getBillerGroupList(), billerGroupItem, billerGroupIdComparator);
				//Not found
				if(result < 0){
					//Create the group
					billerGroupItem.setSequence(billingOrgList.getGroup_sequence());
					billerGroupItem.setObPaymentBillerBean(new ArrayList<ObPaymentBillerBean>(20));
					GeneralCode key =new GeneralCode();
					key.setGnCode(billerGroupItem.getBillerGrpId());
					int imageSrchResult = Collections.binarySearch(imageList, key, generalCodeComparator);
					if(imageSrchResult >= 0){
						billerGroupItem.setImage(imageList.get(imageSrchResult).getData());
					}
					
					responseBean.getBillerGroupList().add(billerGroupItem);
					Collections.sort(responseBean.getBillerGroupList(), billerGroupIdComparator);
				}else{
					//Get from existed group
					billerGroupItem = responseBean.getBillerGroupList().get(result);
				}
				
				ObPaymentBillerBean billerItem = new ObPaymentBillerBean();
				if(locale.equalsIgnoreCase(MiBConstant.LANG_EN)){
					billerItem.setBillerName(billingOrgList.getBiller_name_en());
					billerItem.setLabel1(billerItem.getBillerName());
					if(billingOrgList.getBiller_title_en() != null && !billingOrgList.getBiller_title_en().isEmpty()){
						billerItem.setLabel1(billingOrgList.getBiller_title_en());
					}
					billerItem.setLabel2(billingOrgList.getBiller_info_en());
				}else if(locale.toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase())){
					billerItem.setBillerName(billingOrgList.getBiller_name_cn());
					billerItem.setLabel1(billerItem.getBillerName());
					if(billingOrgList.getBiller_title_en() != null && !billingOrgList.getBiller_title_en().isEmpty()){
						billerItem.setLabel1(billingOrgList.getBiller_title_cn());
					}
					billerItem.setLabel2(billingOrgList.getBiller_info_cn());
				}else{
					billerItem.setBillerName(billingOrgList.getBiller_name_id());
					billerItem.setLabel1(billerItem.getBillerName());
					if(billingOrgList.getBiller_title_en() != null && !billingOrgList.getBiller_title_en().isEmpty()){
						billerItem.setLabel1(billingOrgList.getBiller_title_id());
					}
					billerItem.setLabel2(billingOrgList.getBiller_info_id());
				}
				billerItem.setBillerFlowType(billingOrgList.getFlow_type());
				billerItem.setBillerCd(billingOrgList.getBiller_code());
				billerItem.setSequence(billingOrgList.getBiller_sequence());
				billerItem.setBillerType(billingOrgList.getBiller_type());
				billerItem.setBillerId(billingOrgList.getRecord_id());
				billerGroupItem.getObPaymentBillerBean().add(billerItem);
			}
			
			for(ObPaymentBillerGroupBean temp:responseBean.getBillerGroupList()){
				Collections.sort(temp.getObPaymentBillerBean(), billerSeqComparator);
			}
			Collections.sort(responseBean.getBillerGroupList(), billerGroupSeqComparator);
		}
	}
	
	private final Comparator<ObPaymentBillerGroupBean> billerGroupIdComparator = new Comparator<ObPaymentBillerGroupBean>(){
		@Override
		public int compare(ObPaymentBillerGroupBean o1, ObPaymentBillerGroupBean o2) {
			return o1.getBillerGrpId().compareTo(o2.getBillerGrpId());
		}
	};
	
	private final Comparator<ObPaymentBillerGroupBean> billerGroupSeqComparator = new Comparator<ObPaymentBillerGroupBean>(){
		@Override
		public int compare(ObPaymentBillerGroupBean o1, ObPaymentBillerGroupBean o2) {
			return Integer.valueOf(o1.getSequence()).compareTo(Integer.valueOf(o2.getSequence()));
		}
	};
	
	private final Comparator<ObPaymentBillerBean> billerSeqComparator = new Comparator<ObPaymentBillerBean>(){
		@Override
		public int compare(ObPaymentBillerBean o1, ObPaymentBillerBean o2) {
			return Integer.valueOf(o1.getSequence()).compareTo(Integer.valueOf(o2.getSequence()));
		}
	};
}
