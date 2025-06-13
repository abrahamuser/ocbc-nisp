package com.silverlake.mleb.mcb.module.func.transactionMgmt;



import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.mcb.bean.ObHeaderResponse;
import com.silverlake.mleb.mcb.bean.ObRecurringRequest;
import com.silverlake.mleb.mcb.bean.ObRecurringResponse;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.entity.dao.CustomerDAO;
import com.silverlake.mleb.mcb.entity.dao.GeneralCodeDAO;
import com.silverlake.mleb.mcb.module.common.FuncServices;
import com.silverlake.mleb.mcb.module.vc.accountManagement.ResponseData;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.common.VCService;
import com.silverlake.mleb.mcb.module.vc.others.BillingOrganizationList;
import com.silverlake.mleb.mcb.module.vc.others.BillingOrganizationRequestData;
import com.silverlake.mleb.mcb.module.vc.others.BillingOrganizationResponseData;
import com.silverlake.mleb.mcb.module.vc.transaction.management.Recurring;
import com.silverlake.mleb.mcb.module.vc.transaction.management.RecurringRequestData;
import com.silverlake.mleb.mcb.module.vc.transaction.management.RecurringResponseData;







@Service
public class RetrieveRecurringList extends FuncServices  
{

	private static Logger log = LogManager.getLogger(RetrieveRecurringList.class);
	
	@Autowired
	CustomerDAO dao;
	
	@Autowired
	GeneralCodeDAO gnDao;
	
	 
	@Autowired ApplicationContext appContext;
	
	
	public MICBResponseBean process(MICBRequestBean arg0) {
		// TODO Auto-generated method stub
		
		
	
		MICBResponseBean response = new MICBResponseBean();
		ObRecurringResponse obResponse = new ObRecurringResponse();
		obResponse.setObHeader(new ObHeaderResponse());
	
		
		
		try {
			
			ObRecurringRequest requestData = (ObRecurringRequest) arg0.getBDObject();			
			String orgId = requestData.getObUser().getOrgId();
			String usrId = requestData.getObUser().getLoginId();
			String ipAddress = requestData.getObHeader() == null ? null : requestData.getObHeader().getIp();
			
			RecurringRequestData omniReq = new RecurringRequestData();
			omniReq.setOrg_cd(orgId);
			omniReq.setUsr_cd(usrId);
			String pageSize = requestData.getPage_size()==null?"10":requestData.getPage_size();
			pageSize = pageSize.trim().length()==0?"10":pageSize;
			
			String pageNo = (String) (requestData.getPage_no()==null?"1":requestData.getPage_no());
			pageNo = pageNo.trim().length()==0?"1":pageNo;
			omniReq.setPage_no(Integer.parseInt(pageNo));
			omniReq.setPage_size(Integer.parseInt(pageSize));
			omniReq.setShow_newest(requestData.getShow_newest()==null?"Y":requestData.getShow_newest());
		 
			omniReq.setBiller_code(requestData.getBillerCode());
			omniReq.setBilling_id(requestData.getBillingId());
			omniReq.setAmount(requestData.getAmount());
			omniReq.setDebit_acct_no(requestData.getDebit_acct_no());
			omniReq.setBene_acct_no(requestData.getBene_acct_no());
			omniReq.setRecurring_id(requestData.getRecurring_id());
			String[] prod_cd_list = requestData.getList_prod_cd()==null?null:requestData.getList_prod_cd().split(",");
			
		
			
			if(null!=prod_cd_list)
			{
				omniReq.setList_prod_cd(new ArrayList());
				for(String prd_cd:prod_cd_list)
				{
					omniReq.getList_prod_cd().add(prd_cd);
				}
			}
			
			
			
			RecurringResponseData omniResp = new RecurringResponseData();
			VCService vcService = new VCService(appContext);
			VCResponse<com.silverlake.mleb.mcb.module.vc.transaction.management.RecurringResponseData> vcResponse = null;
			if(requestData.getRecurringType()!=null && requestData.getRecurringType().equalsIgnoreCase("FT"))
			{
				vcResponse = 
						vcService.callOmniVC(RecurringRequestData.method_trx_mgmt_listRecurring_ft,omniReq, omniResp, requestData.getUserContext().getSessionId(), arg0.getTranxID(), ipAddress);
				
			}
			else
			{
				vcResponse = 
						vcService.callOmniVC(RecurringRequestData.method_trx_mgmt_listRecurring_Pymt,omniReq, omniResp, requestData.getUserContext().getSessionId(), arg0.getTranxID(), ipAddress);
				
			}
			  
			if(vcResponse.getResponse_code().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS))
			{
			
				if(requestData.getRecurringType()!=null && requestData.getRecurringType().equalsIgnoreCase("FT"))
				{
					obResponse.setRecurringList(vcResponse.getData().getRecurring_list());
					obResponse.setTotalSize(vcResponse.getData().getTotal_rows());
					obResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
				}
				else
				{
					BillingOrganizationRequestData requestDataBilling = new BillingOrganizationRequestData();
					requestDataBilling.setOrg_cd(orgId);
					requestDataBilling.setUsr_cd(usrId);
					requestDataBilling.setLocale(arg0.getLocaleCode());
					VCResponse<BillingOrganizationResponseData> vcResponseBilling = vcService.callOmniVC(
							VCMethodConstant.REST_METHODS.OTHERS_BILLING_ORGANIZATION, 
							requestDataBilling, 
							new BillingOrganizationResponseData(), 
							requestData.getUserContext().getSessionId(), arg0.getTranxID(), ipAddress);
					
					if(vcResponseBilling.getResponse_code().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS))
					{

						obResponse.setRecurringList(vcResponse.getData().getRecurring_list());
						
						
						BillingOrganizationResponseData resultBilling = vcResponseBilling.getData();
						 
						for(Recurring tmp:obResponse.getRecurringList())
						{
							for(BillingOrganizationList checkTmp: resultBilling.getBiller_list())
							{
								if(tmp.getBiller_code().equalsIgnoreCase(checkTmp.getBiller_code()))
								{
									if(arg0.getLocaleCode().equalsIgnoreCase(MiBConstant.LOCALE_IN))
									{
										tmp.setBiller_group(checkTmp.getGroup_name_id());
									}
									else if(arg0 != null && arg0.getLocaleCode() != null && arg0.getLocaleCode().toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase()))
									{
										tmp.setBiller_group(checkTmp.getGroup_name_cn());
									}
									else
									{
										tmp.setBiller_group(checkTmp.getGroup_name_en());
									}
									
									break;
								}
							}
							
						}
						
						
						
						
						obResponse.setTotalSize(vcResponse.getData().getTotal_rows());
						obResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
					}
					else
					{
						obResponse.getObHeader().setStatusCode(MiBConstant.MIB_OMNI_PREFIX+vcResponseBilling.getResponse_code() );
						obResponse.getObHeader().setStatusMessage(vcResponse.getResponse_desc());
						
					}
					
					
				}
				
				 

			}
			else 
			{
				obResponse.getObHeader().setStatusCode(MiBConstant.MIB_OMNI_PREFIX+vcResponse.getResponse_code() );
				obResponse.getObHeader().setStatusMessage(vcResponse.getResponse_desc());
			} 
			 
			
		} catch (Exception e) {
		
			log.info(this.getClass().toString(), e);
			obResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			obResponse.getObHeader().setStatusMessage(e.getMessage());
		}
		
		response.setBDObject(obResponse);
		
		return response;
	}
	
	
	 
	 
}
