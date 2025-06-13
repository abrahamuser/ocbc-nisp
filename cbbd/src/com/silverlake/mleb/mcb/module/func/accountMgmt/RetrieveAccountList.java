package com.silverlake.mleb.mcb.module.func.accountMgmt;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
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
import com.silverlake.mleb.mcb.module.vc.accountManagement.ListAccount;
import com.silverlake.mleb.mcb.module.vc.accountManagement.RequestData;
import com.silverlake.mleb.mcb.module.vc.accountManagement.ResponseData;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.common.VCService;







@Service
public class RetrieveAccountList extends FuncServices  
{

	private static Logger log = LogManager.getLogger(RetrieveAccountList.class);
	
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
			
			//need to get all account in one call;
			String pageNo = "1";
			String pageSize = "9999";
			 
			//Call REQ OMNI Acc Summary
			VCService usrService = new VCService(appContext);
			com.silverlake.mleb.mcb.module.vc.accountManagement.RequestData vcAcctSummaryRequest = new RequestData();
			vcAcctSummaryRequest.setOrg_cd(orgId);
			vcAcctSummaryRequest.setUsr_cd(usrId);
			vcAcctSummaryRequest.setPage_no(pageNo);
			vcAcctSummaryRequest.setPage_size(pageSize);
			vcAcctSummaryRequest.setAcct_type(requestData.getAccountType());
			vcAcctSummaryRequest.setProd_cd(requestData.getProd_cd());
			//Call RES OMNI Acc Summary 
			com.silverlake.mleb.mcb.module.vc.accountManagement.ResponseData vcAcctSummaryResponseData = new ResponseData();
			VCResponse<com.silverlake.mleb.mcb.module.vc.accountManagement.ResponseData> vcResponse = usrService.callOmniVC(VCMethodConstant.REST_METHODS.ACCOUNT_MANAGEMENT_SUMMARY,vcAcctSummaryRequest, vcAcctSummaryResponseData, requestData.getUserContext().getSessionId(), arg0.getTranxID(), ipAddress);
			
			if(vcResponse.getResponse_code().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS))
			{
				
				vcAcctSummaryResponseData = vcResponse.getData();
				if(vcAcctSummaryResponseData.getList_account()!=null)
				{
					obResponse.setAccountList(new ArrayList());
					List<ObCorporateAccountOverview> accountList = new ArrayList();
					for(ListAccount temp:vcAcctSummaryResponseData.getList_account())
					{
						ObCorporateAccountOverview corporateAcct = new ObCorporateAccountOverview();
						BeanUtils.copyProperties(temp, corporateAcct);
						
						if(corporateAcct.getFav_prod_map()!=null)
						{
							for(String favTmp:corporateAcct.getFav_prod_map())
							{
								if(favTmp.trim().startsWith("STMT|"))
								{
									corporateAcct.setFavOrder(favTmp.trim());
									 
									break;
								}
								
							}
						}
						
						
						accountList.add(corporateAcct);
					}
					
					
					
					Collections.sort(accountList, compareAccountNo);
					Collections.sort(accountList, compareCcy);
					Collections.sort(accountList, compareAlias);
					Collections.sort(accountList, compareFav);
					
					List<GeneralCode> rolloverTypeList = gnDao.findByGnCodeType(MiBConstant.ROLLOVERTYPE, arg0.getLocaleCode());
					
					int index = 1;
					for(ObCorporateAccountOverview temp:accountList)
					{
						//VELO2UAT-265 - add desc for rollover code
						if(temp.getAuto_renewal() != null) {
							for(GeneralCode gnTmp:rolloverTypeList){
								if(temp.getAuto_renewal().equalsIgnoreCase(gnTmp.getGnCode())){
									temp.setAuto_renewal_desc(gnTmp.getGnCodeDescription());
									break;
								}
							}
						}
						String preFixAcctType = requestData.getAccountType()==null?"":requestData.getAccountType().trim();
						temp.setIndex(String.valueOf(preFixAcctType+index));
						index++;
					}
					
					
					obResponse.setAccountList(accountList);
					obResponse.setMaxYear(Calendar.getInstance().get(Calendar.YEAR));
					obResponse.setMinYear(Calendar.getInstance().get(Calendar.YEAR)-1);
					obResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
				}
				else
				{
					obResponse.getObHeader().setStatusCode(MiBConstant.MCB_NO_ACCOUNT_FOUND);
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
	    	
	    	o1.setAcct_alias(o1.getAcct_alias()==null?"":o1.getAcct_alias());
	    	o2.setAcct_alias(o2.getAcct_alias()==null?"":o2.getAcct_alias());
	        return o1.getAcct_alias().compareTo(o2.getAcct_alias());
	    }
	};
	
	Comparator<ObCorporateAccountOverview> compareFav = new Comparator<ObCorporateAccountOverview>() {
	    @Override
	    public int compare(ObCorporateAccountOverview o1, ObCorporateAccountOverview o2) {
	    	
	    	String x1 = o1.getFavOrder();
	    	String x2 = o2.getFavOrder();
	    	
	     
	    	
	    	if(x1==null)
	    	{
	    		return 1;
	    	}
	    	else if(x2==null)
	    	{
	    		return 1;
	    	}
	    	
	        return x1.compareTo(x2);
	    }
	};
	
	
	
	
}
