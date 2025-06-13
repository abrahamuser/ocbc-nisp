package com.silverlake.mleb.mcb.module.func.accountMgmt;



import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.mcb.bean.ObAccountOverviewRequest;
import com.silverlake.mleb.mcb.bean.ObAccountOverviewResponse;
import com.silverlake.mleb.mcb.bean.ObCorporateAccountOverview;
import com.silverlake.mleb.mcb.bean.ObHeaderResponse;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.entity.GeneralCode;
import com.silverlake.mleb.mcb.entity.dao.CustomerDAO;
import com.silverlake.mleb.mcb.entity.dao.GeneralCodeDAO;
import com.silverlake.mleb.mcb.module.common.FuncServices;
import com.silverlake.mleb.mcb.module.vc.accountManagement.RequestData;
import com.silverlake.mleb.mcb.module.vc.accountManagement.ResponseData;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.common.VCService;
import com.silverlake.mleb.mcb.util.MapperUtil;







@Service
public class RetrieveAccountDetails extends FuncServices  
{

	private static Logger log = LogManager.getLogger(RetrieveAccountDetails.class);
	
	@Autowired
	CustomerDAO dao;
	
	@Autowired
	GeneralCodeDAO gnDao;
	
	 
	@Autowired ApplicationContext appContext;
	
	
	public MICBResponseBean process(MICBRequestBean arg0) {
		// TODO Auto-generated method stub
		
		
	
		MICBResponseBean response = new MICBResponseBean();
		ObAccountOverviewResponse obResponse = new ObAccountOverviewResponse();
		obResponse.setObHeader(new ObHeaderResponse());
	
		
		
		try {

			ObAccountOverviewRequest requestData = (ObAccountOverviewRequest) arg0.getBDObject();			
			String orgId = requestData.getObUser().getOrgId();
			String usrId = requestData.getObUser().getLoginId();
			String ipAddress = requestData.getObHeader() == null ? null : requestData.getObHeader().getIp();
			
			com.silverlake.mleb.mcb.module.vc.accountManagement.RequestData vcAcctInquiryRequest = new RequestData();
			VCService usrService = new VCService(appContext);
			vcAcctInquiryRequest.setOrg_cd(orgId);
			vcAcctInquiryRequest.setUsr_cd(usrId);
			vcAcctInquiryRequest.setAcct_no(requestData.getAccountNo());
			vcAcctInquiryRequest.setAcct_ccy(requestData.getAccountCcy());
			//Call RES OMNI AccInquiry
			com.silverlake.mleb.mcb.module.vc.accountManagement.ResponseData vcAcctInquiryResponseData = new ResponseData();
			VCResponse<com.silverlake.mleb.mcb.module.vc.accountManagement.ResponseData> vcResponseInquiry = usrService.callOmniVC(VCMethodConstant.REST_METHODS.ACCOUNT_MANAGEMENT_INQUIRY,vcAcctInquiryRequest, vcAcctInquiryResponseData, requestData.getUserContext().getSessionId(), arg0.getTranxID(), ipAddress);
			if(vcResponseInquiry.getResponse_code().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS))
			{
				vcAcctInquiryResponseData = vcResponseInquiry.getData();
				ObCorporateAccountOverview accountDetails = new ObCorporateAccountOverview();
				
				MapperUtil.copyFields(vcAcctInquiryResponseData, accountDetails);
				
				accountDetails.setBalance_available(vcAcctInquiryResponseData.getBalance_available());
				accountDetails.setBalance_hold(vcAcctInquiryResponseData.getBalance_hold());
				accountDetails.setBalance_ledger(vcAcctInquiryResponseData.getBalance_ledger());
				accountDetails.setBalance_overdraft(vcAcctInquiryResponseData.getBalance_overdraft());
				accountDetails.setAccrued_interest(vcAcctInquiryResponseData.getAccrued_interest());
				
				//add desc for rollover code
				if(accountDetails.getAuto_renewal() != null) {
					List<GeneralCode> rolloverTypeList = gnDao.findByGnCodeType(MiBConstant.ROLLOVERTYPE, arg0.getLocaleCode());
					for(GeneralCode gnTmp:rolloverTypeList){
						if(accountDetails.getAuto_renewal().equalsIgnoreCase(gnTmp.getGnCode())){
							accountDetails.setAuto_renewal_desc(gnTmp.getGnCodeDescription());
							break;
						}
					}
				}
				
				obResponse.setAccountList(new ArrayList());
				obResponse.getAccountList().add(accountDetails);
				obResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
			}
			else 
			{
				obResponse.getObHeader().setStatusCode(MiBConstant.MIB_OMNI_PREFIX+vcResponseInquiry.getResponse_code() );
				obResponse.getObHeader().setStatusMessage(vcResponseInquiry.getResponse_desc());
			} 
			
			
			
			
		} catch (Exception e) {
		
			log.info(this.getClass().toString(), e);
			obResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			obResponse.getObHeader().setStatusMessage(e.getMessage());
		}
		
		response.setBDObject(obResponse);
		
		return response;
	}
	
	
	
	Comparator<ObCorporateAccountOverview> compareAccountNo = new Comparator<ObCorporateAccountOverview>() {
	    @Override
	    public int compare(ObCorporateAccountOverview o1, ObCorporateAccountOverview o2) {
	        return o1.getAcct_no().compareTo(o2.getAcct_no());
	    }
	};
	
	
	Comparator<ObCorporateAccountOverview> compareCcy = new Comparator<ObCorporateAccountOverview>() {
	    @Override
	    public int compare(ObCorporateAccountOverview o1, ObCorporateAccountOverview o2) {
	    	
	    	if(o2.getAcct_ccy().equalsIgnoreCase("IDR"))
	    	{
	    		return 1;
	    	}
	    	else if(o1.getAcct_ccy().equalsIgnoreCase("IDR"))
	    	{
	    		return -1;
	    	}
	    	
	        return o1.getAcct_ccy().compareTo(o2.getAcct_ccy());
	    }
	};
	
	
	Comparator<ObCorporateAccountOverview> compareAlias = new Comparator<ObCorporateAccountOverview>() {
	    @Override
	    public int compare(ObCorporateAccountOverview o1, ObCorporateAccountOverview o2) {
	    	
	    	 
	    	
	        return o1.getAcct_alias().compareTo(o2.getAcct_alias());
	    }
	};
	
	
	
	
	
	
}
