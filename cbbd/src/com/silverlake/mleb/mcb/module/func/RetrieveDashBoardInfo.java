package com.silverlake.mleb.mcb.module.func;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dozer.util.MappingUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.mcb.bean.ObAccountOverviewRequest;
import com.silverlake.mleb.mcb.bean.ObAccountOverviewResponse;
import com.silverlake.mleb.mcb.bean.ObCorporateAccountOverview;
import com.silverlake.mleb.mcb.bean.ObDashboardInfoRequest;
import com.silverlake.mleb.mcb.bean.ObDashboardInfoResponse;
import com.silverlake.mleb.mcb.bean.ObHeaderResponse;
import com.silverlake.mleb.mcb.bean.ObTaskListResponse;
import com.silverlake.mleb.mcb.bean.ObUserDetail;
import com.silverlake.mleb.mcb.bean.vctransaction.ObVcTranxResponse;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.constant.VCMethodConstant;
import com.silverlake.mleb.mcb.entity.CustomerState;
import com.silverlake.mleb.mcb.entity.GeneralCode;
import com.silverlake.mleb.mcb.entity.dao.CustomerDAO;
import com.silverlake.mleb.mcb.entity.dao.GeneralCodeDAO;
import com.silverlake.mleb.mcb.module.common.FuncServices;
import com.silverlake.mleb.mcb.module.func.deviceBinding.DeviceCIFUnbindAck;
import com.silverlake.mleb.mcb.module.func.task.RetrieveTaskList;
import com.silverlake.mleb.mcb.module.vc.accountManagement.Inquiry;
import com.silverlake.mleb.mcb.module.vc.accountManagement.ListAccount;
import com.silverlake.mleb.mcb.module.vc.accountManagement.RequestData;
import com.silverlake.mleb.mcb.module.vc.accountManagement.ResponseData;
import com.silverlake.mleb.mcb.module.vc.common.VCAsyncResponseBean;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.common.VCService;
import com.silverlake.mleb.mcb.module.vc.task_list.TaskListCountRequestData;
import com.silverlake.mleb.mcb.module.vc.transaction.TranxRequestData;
import com.silverlake.mleb.mcb.module.vc.transaction.TranxResponseData;
import com.silverlake.mleb.mcb.util.PropertiesManager;
import com.silverlake.mleb.mcb.util.StringUtil;







@Service
public class RetrieveDashBoardInfo extends FuncServices  
{

	private static Logger log = LogManager.getLogger(RetrieveDashBoardInfo.class);
	
	@Autowired
	CustomerDAO dao;
	
	@Autowired
	GeneralCodeDAO gnDao;
	
	 
	@Autowired ApplicationContext appContext;
	
	
	public MICBResponseBean process(MICBRequestBean arg0) {
		// TODO Auto-generated method stub
		
		
	
		MICBResponseBean response = new MICBResponseBean();
		ObDashboardInfoResponse obResponse = new ObDashboardInfoResponse();
		obResponse.setObHeader(new ObHeaderResponse());
	
		
		
		try {
			
			ObDashboardInfoRequest requestData = (ObDashboardInfoRequest) arg0.getBDObject();			
			String orgId = requestData.getObUser().getOrgId();
			String usrId = requestData.getObUser().getLoginId();
			String accessId = requestData.getObUser().getAccessId();//all menu id
			String deviceId = arg0.getDeviceBean()==null?null:arg0.getDeviceBean().getDeviceId();
			String deviceOS = arg0.getDeviceBean()==null?null:arg0.getDeviceBean().getOs();
			String deviceType = arg0.getDeviceBean()==null?null:arg0.getDeviceBean().getType();
			String devicePushToken = requestData.getObHeader()==null?null:requestData.getObHeader().getPnsDeviceToken();
			String tncAction = requestData.getTncAction();
			tncAction = tncAction==null?null:(tncAction.trim().length()==0?null:tncAction);
			String ipAddress = requestData.getObHeader() == null ? null : requestData.getObHeader().getIp();
 
			 
			if(!requestData.getObUser().isTncFlag() )
			{
				//move to login
				//DeviceCIFUnbindAck deviceUnbind = new DeviceCIFUnbindAck();
				//deviceUnbind.sendDeviceInfo(appContext,"A",orgId, usrId, deviceId, deviceOS, deviceType, devicePushToken, requestData.getUserContext().getSessionId(),  arg0.getTranxID());
				
				
				RetrieveTaskList taskList = new RetrieveTaskList();
				ObTaskListResponse taskListResponse = new ObTaskListResponse();
				taskListResponse.setObHeader(new ObHeaderResponse());
				VCService userAsyncService = new VCService(appContext); 
				List<String> trnxSourceList = taskList.getTrnxSourc(null);
				RetrieveFutureTranx futureTranx = new RetrieveFutureTranx();
				ObVcTranxResponse futureTranxResponse = new ObVcTranxResponse();
				futureTranxResponse.setObHeader(new ObHeaderResponse());
				VCService userAsyncService_futureTranx = new VCService(appContext); 
				
				GeneralCode futureTrnx_page_size = gnDao.findByGnCode("futureTrnx_page_size");
				String pageSize = futureTrnx_page_size==null?"10":futureTrnx_page_size.getGnCodeDescription();
				
				
				ListenableFuture ListenfutureTranx = futureTranx.callFutureTranx(appContext,null,null,pageSize, userAsyncService_futureTranx, orgId, usrId, requestData.getUserContext().getSessionId(), arg0.getTranxID(), ipAddress);
				VCAsyncResponseBean<TaskListCountRequestData, VCResponse> taskListResultListener = taskList.getTaskListResultAsync(appContext, taskListResponse, requestData.getObUser().getOrgId(), requestData.getObUser().getLoginId(), trnxSourceList, requestData.getUserContext().getSessionId(), arg0.getTranxID(), arg0.getLocaleCode());
				
				
				if(checkAcctSummaryAccessId(accessId))
				{
					obResponse = getFirst5FavAccountInfo(obResponse, orgId, usrId,requestData.getUserContext().getSessionId(), arg0.getTranxID(),appContext, ipAddress);
				}
				else
				{
					obResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
				}
				
				
				if(obResponse.getObHeader().getStatusCode().equalsIgnoreCase(MiBConstant.MIB_COMMON_SUCCESS))
				{
						taskList.getTaskListResultAsync(taskListResultListener, appContext, taskListResponse, requestData.getUserContext().getSessionId(), arg0.getTranxID(), arg0.getLocaleCode(), gnDao);
						obResponse.setTaskInfoList(taskListResponse.getTaskInfoList());
						
						
						futureTranxResponse = futureTranx.getFutureTransactionResult(futureTranxResponse, userAsyncService_futureTranx, ListenfutureTranx, requestData.getUserContext().getSessionId(),  arg0.getTranxID(), arg0.getLocaleCode());
						obResponse.setFutureTransactionCount(futureTranxResponse.getTotalTranx());
						
						//Error message which not start with 's' consider stopper, shall returned back to FE, otherwise ignored. 
						if(taskListResponse.getObHeader().getStatusCode().toLowerCase().startsWith(MiBConstant.MIB_OMNI_PREFIX+"s"))
						{
							obResponse.getObHeader().setStatusCode(taskListResponse.getObHeader().getStatusCode());
						}
						else if(taskListResponse.getObHeader().getStatusCode().equalsIgnoreCase("401"))
						{
							obResponse.getObHeader().setStatusCode(futureTranxResponse.getObHeader().getStatusCode());
						}
						else if(futureTranxResponse.getObHeader().getStatusCode().toLowerCase().startsWith(MiBConstant.MIB_OMNI_PREFIX+"s"))
						{
							obResponse.getObHeader().setStatusCode(futureTranxResponse.getObHeader().getStatusCode());
						}
						else if(futureTranxResponse.getObHeader().getStatusCode().equalsIgnoreCase("401"))
						{
							obResponse.getObHeader().setStatusCode(futureTranxResponse.getObHeader().getStatusCode());
						}
						
				}
				
				
			}
			else
			{
				obResponse.getObHeader().setStatusCode(MiBConstant.MCB_INVALID_TNC_ACTION );
				obResponse.getObHeader().setStatusMessage("MCB_INVALID_TNC_ACTION");
			}
			
			
			
			
			
			
			
			
		} catch (Exception e) {
		
			log.info(this.getClass().toString(), e);
			obResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			obResponse.getObHeader().setStatusMessage(e.getMessage());
		}
		
		response.setBDObject(obResponse);
		
		return response;
	}
	
	
	
	
	
	
	
	public ObDashboardInfoResponse getFirstAccountInfo(ObDashboardInfoResponse obResponse, String orgId,String usrId, String sessionId, String tranxId, String ipAddress) throws Exception
	{
		 
		String pageNo = null;
		pageNo = pageNo == null?"1":pageNo;
		String pageSize = gnDao.getAccountSummaryPageSize();
		pageSize = pageSize==null?"1":pageSize;
		
		try {pageNo = Integer.parseInt(pageNo)+"";}catch(Exception e) {pageNo="1";}
		 
		//Call REQ OMNI Acc Summary
		VCService usrService = new VCService(appContext);
		com.silverlake.mleb.mcb.module.vc.accountManagement.RequestData vcAcctSummaryRequest = new RequestData();
		vcAcctSummaryRequest.setOrg_cd(orgId);
		vcAcctSummaryRequest.setUsr_cd(usrId);
		vcAcctSummaryRequest.setPage_no(pageNo);
		vcAcctSummaryRequest.setPage_size(pageSize);
		//Call RES OMNI Acc Summary 
		com.silverlake.mleb.mcb.module.vc.accountManagement.ResponseData vcAcctSummaryResponseData = new ResponseData();
		//VCResponse vcResponseSummaryList = usrService.callOmniVC(VCService.method_account_management_summary_list,vcAcctSummaryRequest, vcAcctSummaryResponseData, null, arg0.getTranxID());
		VCResponse<com.silverlake.mleb.mcb.module.vc.accountManagement.ResponseData> vcResponse = usrService.callOmniVC(VCMethodConstant.REST_METHODS.ACCOUNT_MANAGEMENT_SUMMARY,vcAcctSummaryRequest, vcAcctSummaryResponseData, sessionId, tranxId, ipAddress);
		
		obResponse.setAccountSize("1");
		if(vcResponse.getResponse_code().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS))
		{
			
			vcAcctSummaryResponseData = vcResponse.getData();
			if(vcAcctSummaryResponseData.getList_account()!=null)
			{
				obResponse.setAccountList(new ArrayList());
				int index = 0;
				for(ListAccount temp:vcAcctSummaryResponseData.getList_account())
				{
					ObCorporateAccountOverview corporateAcct = new ObCorporateAccountOverview();
					log.info("blc check :"+temp.getBalance_ledger());
					BeanUtils.copyProperties(temp, corporateAcct);
					corporateAcct.setIndex(String.valueOf(index));
					obResponse.getAccountList().add(corporateAcct);
					index++;
				}
				
				
				
				if(obResponse.getAccountList().size()>0)
				{
					
					//Call REQ OMNI AccInquiry
					com.silverlake.mleb.mcb.module.vc.accountManagement.RequestData vcAcctInquiryRequest = new RequestData();
					//Retrieve request data Inquiry ONLY on first account
					vcAcctInquiryRequest.setOrg_cd(orgId);
					vcAcctInquiryRequest.setUsr_cd(usrId);
					vcAcctInquiryRequest.setAcct_no(obResponse.getAccountList().get(0).getAcct_no());
					vcAcctInquiryRequest.setAcct_ccy(obResponse.getAccountList().get(0).getAcct_ccy());
					//Call RES OMNI AccInquiry
					com.silverlake.mleb.mcb.module.vc.accountManagement.ResponseData vcAcctInquiryResponseData = new ResponseData();
					VCResponse<com.silverlake.mleb.mcb.module.vc.accountManagement.ResponseData> vcResponseInquiry = usrService.callOmniVC(VCMethodConstant.REST_METHODS.ACCOUNT_MANAGEMENT_INQUIRY,vcAcctInquiryRequest, vcAcctInquiryResponseData, sessionId, tranxId, ipAddress);
					if(vcResponseInquiry.getResponse_code().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS))
					{
						vcAcctInquiryResponseData = vcResponseInquiry.getData();
						BeanUtils.copyProperties(vcAcctInquiryResponseData, obResponse.getAccountList().get(0));
					
						log.info("blc 1 : "+obResponse.getAccountList().get(0).getBalance_available());
						 
						obResponse.getAccountList().get(0).setBalance_available(vcAcctInquiryResponseData.getBalance_available());
						obResponse.getAccountList().get(0).setBalance_hold(vcAcctInquiryResponseData.getBalance_hold());
						obResponse.getAccountList().get(0).setBalance_ledger(vcAcctInquiryResponseData.getBalance_ledger());
						obResponse.getAccountList().get(0).setBalance_overdraft(vcAcctInquiryResponseData.getBalance_overdraft());
						
						log.info("blc 2 : "+obResponse.getAccountList().get(0).getBalance_available());
						
						
						obResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
					}
					else 
					{
						//obResponse.getObHeader().setStatusCode(MiBConstant.MIB_OMNI_PREFIX+vcResponseInquiry.getResponse_code() );
						//obResponse.getObHeader().setStatusMessage(vcResponseInquiry.getResponse_desc());
						if(vcResponse.getResponse_code().toLowerCase().startsWith("s"))
						{
							obResponse.getObHeader().setStatusCode(MiBConstant.MIB_OMNI_PREFIX+vcResponseInquiry.getResponse_code() );
							obResponse.getObHeader().setStatusMessage(vcResponseInquiry.getResponse_desc());
						}
						else
						{
							obResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
						}
						
					}
				
				
				
				}
				else
				{
					obResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
					obResponse.setAccountSize("0");
				}
				
				
			}
			else
			{
				obResponse.getObHeader().setStatusCode(MiBConstant.MCB_NO_ACCOUNT_FOUND);
			}
		}
		else
		{
			//obResponse.getObHeader().setStatusCode(MiBConstant.MIB_OMNI_PREFIX+vcResponse.getResponse_code() );
			//obResponse.getObHeader().setStatusMessage(vcResponse.getResponse_desc());
			if(vcResponse.getResponse_code().toLowerCase().startsWith("s"))
			{
				obResponse.getObHeader().setStatusCode(MiBConstant.MIB_OMNI_PREFIX+vcResponse.getResponse_code() );
				obResponse.getObHeader().setStatusMessage(vcResponse.getResponse_desc());
			}
			else if(vcResponse.getResponse_code().equalsIgnoreCase("401"))
			{
				obResponse.getObHeader().setStatusCode(MiBConstant.MIB_OMNI_PREFIX+vcResponse.getResponse_code() );
				obResponse.getObHeader().setStatusMessage(vcResponse.getResponse_desc());
			}
			else
			{
				obResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
			}
			
		}
		
		
		return obResponse;
	}
	
	
	
	
	
	
	public ObDashboardInfoResponse getFirst5FavAccountInfo(ObDashboardInfoResponse obResponse, String orgId,String usrId, String sessionId, String tranxId, ApplicationContext appContextx, String ipAddress) throws Exception
	{
		 
		String pageNo = null;
		pageNo = pageNo == null?"1":pageNo;
		String pageSize = "10";
		
		try {pageNo = Integer.parseInt(pageNo)+"";}catch(Exception e) {pageNo="1";}
		 
		//Call REQ OMNI Acc Summary
		VCService usrService = new VCService(appContextx);
		com.silverlake.mleb.mcb.module.vc.accountManagement.RequestData vcAcctSummaryRequest = new RequestData();
		vcAcctSummaryRequest.setOrg_cd(orgId);
		vcAcctSummaryRequest.setUsr_cd(usrId);
		vcAcctSummaryRequest.setPage_no(pageNo);
		vcAcctSummaryRequest.setPage_size(pageSize);
		//Call RES OMNI Acc Summary 
		com.silverlake.mleb.mcb.module.vc.accountManagement.ResponseData vcAcctSummaryResponseData = new ResponseData();
		//VCResponse vcResponseSummaryList = usrService.callOmniVC(VCService.method_account_management_summary_list,vcAcctSummaryRequest, vcAcctSummaryResponseData, null, arg0.getTranxID());
		VCResponse<com.silverlake.mleb.mcb.module.vc.accountManagement.ResponseData> vcResponse = usrService.callOmniVC(VCMethodConstant.REST_METHODS.ACCOUNT_MANAGEMENT_SUMMARY,vcAcctSummaryRequest, vcAcctSummaryResponseData, sessionId, tranxId, ipAddress);
		
		
		if(vcResponse.getResponse_code().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS))
		{
			
			vcAcctSummaryResponseData = vcResponse.getData();
			if(vcAcctSummaryResponseData.getList_account()!=null)
			{
				obResponse.setAccountList(new ArrayList());
				int vcResponseAcctCount = vcAcctSummaryResponseData.getList_account().size();
				for(ListAccount temp:vcAcctSummaryResponseData.getList_account())
				{
					ObCorporateAccountOverview corporateAcct = new ObCorporateAccountOverview();
					log.info("blc check :"+temp.getBalance_ledger());
					BeanUtils.copyProperties(temp, corporateAcct);
					 
					obResponse.getAccountList().add(corporateAcct);
					 
				}
				
				log.info("fav size before filter : "+obResponse.getAccountList().size());
				
				//fav_prod_map - STMT -  check fav account 
				Iterator<ObCorporateAccountOverview> favAccList = obResponse.getAccountList().iterator();
				while(favAccList.hasNext())
				{
					ObCorporateAccountOverview temp =  favAccList.next();
					
					if(temp.getFav_prod_map()==null || temp.getFav_prod_map().size()==0)
					{
						log.info("non fav remove "+temp.getAcct_no());
						favAccList.remove();
						continue;
					}
					
					boolean checkSTMT = false;
					for(String favTmp:temp.getFav_prod_map())
					{
						if(favTmp.trim().startsWith("STMT|"))
						{
							temp.setFavOrder(favTmp.trim());
							checkSTMT = true;
							continue;
						}
						
					}
					
					
					if(!checkSTMT)
					{
						log.info("non fav remove "+temp.getAcct_no());
						favAccList.remove();
					}
					
				 
				}
				log.info("fav size after filter : "+obResponse.getAccountList().size());
			
				int maxFavAcc = obResponse.getAccountList().size();
				
				if(maxFavAcc>0)
				{
					//order fav  
					Collections.sort(obResponse.getAccountList(), compareFavOrder);
					
					//limit 5 fav
					for(int i = maxFavAcc ; i>5 ; i--)
					{
						obResponse.getAccountList().remove(i-1);
					}
				
				}
				
				
				
				//if 0 fav but got less than 6 acct
				if(obResponse.getAccountList().size()==0 && vcResponseAcctCount>0 && vcResponseAcctCount<6 )
				{
					
					for(ListAccount temp:vcAcctSummaryResponseData.getList_account())
					{
						ObCorporateAccountOverview corporateAcct = new ObCorporateAccountOverview();
						 
						BeanUtils.copyProperties(temp, corporateAcct);
						 
						obResponse.getAccountList().add(corporateAcct);
						 
					}
					
				}
				
				
				
				
					
				
				
				obResponse.setAccountSize(obResponse.getAccountList().size()+"");
				log.info("fav size inquiry filter : "+obResponse.getAccountList().size());
				if(obResponse.getAccountList().size()>0)
				{
					
					//Call REQ OMNI AccInquiry
					com.silverlake.mleb.mcb.module.vc.accountManagement.RequestData vcAcctInquiryRequest = new RequestData();
					List<ListenableFuture>  futureTranx = new ArrayList();
					int index = 1;
					for(ObCorporateAccountOverview temp:obResponse.getAccountList())
					{
					
						temp.setIndex("fav"+index);
						vcAcctInquiryRequest.setOrg_cd(orgId);
						vcAcctInquiryRequest.setUsr_cd(usrId);
						vcAcctInquiryRequest.setAcct_no(temp.getAcct_no());
						vcAcctInquiryRequest.setAcct_ccy(temp.getAcct_ccy());
						 
						//futureTranx.add(callAsyncAccInquiry(usrService,orgId,usrId,temp,sessionId, tranxId));
						
						
						
					 
						 
						

						index++;
					}
					
					/*int tmpCount = 0;
					for(ObCorporateAccountOverview temp:obResponse.getAccountList())
					{
						  
						try
						{
							VCResponse<com.silverlake.mleb.mcb.module.vc.accountManagement.ResponseData> vcResponseInquiry = getAsyncResponseAccInquiry(usrService,futureTranx.get(tmpCount),sessionId, tranxId);
						
						
							com.silverlake.mleb.mcb.module.vc.accountManagement.ResponseData vcAcctInquiryResponseData = new ResponseData();
							if(vcResponseInquiry.getResponse_code().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS))
							{
								vcAcctInquiryResponseData = vcResponseInquiry.getData();
								BeanUtils.copyProperties(vcAcctInquiryResponseData, temp);
							
							 
								temp.setBalance_available(vcAcctInquiryResponseData.getBalance_available());
								temp.setBalance_hold(vcAcctInquiryResponseData.getBalance_hold());
								temp.setBalance_ledger(vcAcctInquiryResponseData.getBalance_ledger());
								temp.setBalance_overdraft(vcAcctInquiryResponseData.getBalance_overdraft());
								
								 
								
							}
							
							
						}catch(Exception e)
						{
							e.printStackTrace();
							log.info("Inquiry omni hit error "+e.getMessage());
						}
						tmpCount++;
						
					}*/
					
					obResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
					
				
				}
				else
				{
					obResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
					obResponse.setFavAccountSize("0");
				}
				
				
			}
			else
			{
				obResponse.getObHeader().setStatusCode(MiBConstant.MCB_NO_ACCOUNT_FOUND);
			}
		}
		else
		{
			//obResponse.getObHeader().setStatusCode(MiBConstant.MIB_OMNI_PREFIX+vcResponse.getResponse_code() );
			//obResponse.getObHeader().setStatusMessage(vcResponse.getResponse_desc());
			if(vcResponse.getResponse_code().toLowerCase().startsWith("s"))
			{
				obResponse.getObHeader().setStatusCode(MiBConstant.MIB_OMNI_PREFIX+vcResponse.getResponse_code() );
				obResponse.getObHeader().setStatusMessage(vcResponse.getResponse_desc());
			}
			else if(vcResponse.getResponse_code().equalsIgnoreCase("401"))
			{
				obResponse.getObHeader().setStatusCode(MiBConstant.MIB_OMNI_PREFIX+vcResponse.getResponse_code() );
				obResponse.getObHeader().setStatusMessage(vcResponse.getResponse_desc());
			}
			else
			{
				obResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
			}
			
		}
		
		
		return obResponse;
	}
	
	
	public ListenableFuture callAsyncAccInquiry(VCService userAsyncService, String orgId, String usrId, ObCorporateAccountOverview acct, String sessionID, String mlebTrxId, String ipAddress) throws Exception
	{
		
		com.silverlake.mleb.mcb.module.vc.accountManagement.RequestData vcAcctInquiryRequest = new RequestData();
		 
		vcAcctInquiryRequest.setOrg_cd(orgId);
		vcAcctInquiryRequest.setUsr_cd(usrId);
		vcAcctInquiryRequest.setAcct_no(acct.getAcct_no());
		vcAcctInquiryRequest.setAcct_ccy(acct.getAcct_ccy());

		return  userAsyncService.callOmniVCAsync(VCMethodConstant.REST_METHODS.ACCOUNT_MANAGEMENT_INQUIRY,vcAcctInquiryRequest,new ResponseData() , sessionID, mlebTrxId, ipAddress);
		
	}
	
	
	
	public VCResponse<com.silverlake.mleb.mcb.module.vc.accountManagement.ResponseData> getAsyncResponseAccInquiry(VCService vcservice, ListenableFuture futureVCResponse,  String sessionID, String mlebTrxId) throws Exception
	{
		
		com.silverlake.mleb.mcb.module.vc.accountManagement.ResponseData vcAcctInquiryResponseData = new ResponseData();

		VCResponse<com.silverlake.mleb.mcb.module.vc.accountManagement.ResponseData> vcResponseInquiry = vcservice.getAscyncResponseData(VCMethodConstant.REST_METHODS.ACCOUNT_MANAGEMENT_INQUIRY, futureVCResponse, vcAcctInquiryResponseData , sessionID, mlebTrxId);
		
		return vcResponseInquiry;
	}
	
	
	
	 
	 
	public boolean checkAcctSummaryAccessId(String accessIds)
	{
		String[] accessId = accessIds.split(",");
		
		for(String tmp:accessId)
		{
			if(tmp.equalsIgnoreCase("9101"))
			{
				return true;
			}
		}
		
		return false;
	}
	
	
	Comparator<ObCorporateAccountOverview> compareFavOrder = new Comparator<ObCorporateAccountOverview>() {
	    @Override
	    public int compare(ObCorporateAccountOverview o1, ObCorporateAccountOverview o2) {
	        return o1.getFavOrder().compareTo(o2.getFavOrder());
	    }
	};
	
	
}
