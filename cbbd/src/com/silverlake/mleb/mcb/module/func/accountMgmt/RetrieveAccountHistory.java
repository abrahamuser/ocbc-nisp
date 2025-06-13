package com.silverlake.mleb.mcb.module.func.accountMgmt;



import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import com.silverlake.mleb.mcb.module.common.MiBServices;
import com.silverlake.mleb.mcb.module.vc.accountManagement.AccountStatement;
import com.silverlake.mleb.mcb.module.vc.accountManagement.RequestData;
import com.silverlake.mleb.mcb.module.vc.accountManagement.ResponseData;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.common.VCService;
import com.silverlake.mleb.mcb.util.MapperUtil;







@Service
public class RetrieveAccountHistory extends FuncServices  
{

	private static Logger log = LogManager.getLogger(RetrieveAccountHistory.class);
	
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
			MiBServices mibService  = new MiBServices(dao);
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
			
			boolean callInquiryCheck = false;
			String fromDate = requestData.getFromDate();
			String toDate = requestData.getToDate();
			String noDays = requestData.getNoDays();
			 
			fromDate = fromDate==null?null:(fromDate.trim().length()==0?null:fromDate);
			toDate = toDate==null?null:(toDate.trim().length()==0?null:toDate);
			noDays = noDays==null?null:(noDays.trim().length()==0?null:noDays);
			
			if(fromDate==null && toDate==null && noDays==null)
			{
				callInquiryCheck = true;
			}
			
			
			VCResponse<com.silverlake.mleb.mcb.module.vc.accountManagement.ResponseData> vcResponseInquiry = null;
			if(callInquiryCheck)
			{

				vcResponseInquiry = usrService.callOmniVC(VCMethodConstant.REST_METHODS.ACCOUNT_MANAGEMENT_INQUIRY_DETAILS,vcAcctInquiryRequest, vcAcctInquiryResponseData, requestData.getUserContext().getSessionId(), arg0.getTranxID(), ipAddress);
				
			}
			else
			{
				vcResponseInquiry = new VCResponse();
				vcResponseInquiry.setResponse_code(MiBConstant.OMNI_SUCCESS);
			}
			
			if(vcResponseInquiry.getResponse_code().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS))
			{
				vcAcctInquiryResponseData = vcResponseInquiry.getData();
				ObCorporateAccountOverview accountDetails = new ObCorporateAccountOverview();
				
				if(null!=vcAcctInquiryResponseData)
				{
					MapperUtil.copyFields(vcAcctInquiryResponseData, accountDetails);
					obResponse.setAccountList(new ArrayList());
					obResponse.getAccountList().add(accountDetails);
				}
				
				
				
				
				//call statement for history
				GeneralCode history_page_size = gnDao.findByGnCode("history_page_size");
				String pageSize = history_page_size==null?"10":history_page_size.getGnCodeDescription();
				String pageNo = requestData.getPageNo();
				pageNo = pageNo==null?"1":pageNo;
				pageNo = pageNo.trim().length()==0?"1":pageNo;
				
				if(noDays==null && toDate==null && fromDate==null)
				{
					//default history nodays
					noDays =  MiBConstant.DEFAULT_STATEMENT_DAYS;
				}
				
				if(noDays!=null)
				{
					List<String> calDate = mibService.getStatementDate(noDays);
					fromDate = calDate.get(0);
					toDate = calDate.get(1);
				}

				vcAcctInquiryRequest.setPage_no(pageNo);
				vcAcctInquiryRequest.setPage_size(pageSize);
				vcAcctInquiryRequest.setStart_date(fromDate);
				vcAcctInquiryRequest.setEnd_date(toDate);
				
				
				
				VCResponse<com.silverlake.mleb.mcb.module.vc.accountManagement.ResponseData> vcResponseStatement = usrService.callOmniVC(VCMethodConstant.REST_METHODS.ACCOUNT_MANAGEMENT_STATEMENT,vcAcctInquiryRequest, new ResponseData(), requestData.getUserContext().getSessionId(), arg0.getTranxID(), ipAddress);
				
				if(vcResponseStatement.getResponse_code().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS))
				{
					com.silverlake.mleb.mcb.module.vc.accountManagement.ResponseData vcStatementResponse = new ResponseData();
					vcStatementResponse = vcResponseStatement.getData();
 
					if(vcStatementResponse!=null && vcStatementResponse.getTotal_rows()>0)
					{
						 
						
						
						
						//obResponse.setTotalPage(String.valueOf(totalPage));
						obResponse.setTotalSize(String.valueOf(vcStatementResponse.getTotal_rows()));
						obResponse.setList_statement(vcStatementResponse.getList_statement());
						obResponse.setClosing_balance(vcStatementResponse.getClosing_balance());
						obResponse.setOpening_balance(vcStatementResponse.getOpening_balance());
						obResponse.setCurrentPageNo(pageNo);
						
						for(AccountStatement accHist:obResponse.getList_statement())
						{
							/*if(accHist.getTrx_date()!=null)
							{
								accHist.setTrx_date(accHist.getTrx_date().substring(0, 10));
							}*/
							
							if(accHist.getValue_date()!=null)
							{
								accHist.setValue_date(accHist.getValue_date().substring(0, 10));
							}
							
						}
						
						
						
						
						
						obResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
					}
					else
					{
						obResponse.getObHeader().setStatusCode(MiBConstant.MCB_NO_STATEMENT_HIST_FOUND);
					}
				}
				else
				{
					obResponse.getObHeader().setStatusCode(MiBConstant.MIB_OMNI_PREFIX+vcResponseStatement.getResponse_code() );
					obResponse.getObHeader().setStatusMessage(vcResponseStatement.getResponse_desc());
					
				}
				
				
				
				
			
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
	
	 
	
	
	
	
	 
	
	
	
}
