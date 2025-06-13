package com.silverlake.mleb.mcb.module.func;



import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import com.silverlake.micb.core.bean.MICBRequestBean;
import com.silverlake.micb.core.bean.MICBResponseBean;
import com.silverlake.mleb.mcb.bean.ObHeaderResponse;
import com.silverlake.mleb.mcb.bean.vctransaction.ObVcTranxRequest;
import com.silverlake.mleb.mcb.bean.vctransaction.ObVcTranxResponse;
import com.silverlake.mleb.mcb.constant.MiBConstant;
import com.silverlake.mleb.mcb.entity.GeneralCode;
import com.silverlake.mleb.mcb.entity.dao.CustomerDAO;
import com.silverlake.mleb.mcb.entity.dao.GeneralCodeDAO;
import com.silverlake.mleb.mcb.module.common.FuncServices;
import com.silverlake.mleb.mcb.module.vc.common.VCResponse;
import com.silverlake.mleb.mcb.module.vc.common.VCService;
import com.silverlake.mleb.mcb.module.vc.task_list.TaskTransaction;
import com.silverlake.mleb.mcb.module.vc.transaction.Transaction;
import com.silverlake.mleb.mcb.module.vc.transaction.TranxRequestData;
import com.silverlake.mleb.mcb.module.vc.transaction.TranxResponseData;
import com.silverlake.mleb.mcb.module.vc.user.ResponseData;






@Service
public class RetrieveFutureTranx extends FuncServices  
{

	private static Logger log = LogManager.getLogger(RetrieveFutureTranx.class);
	
	@Autowired
	CustomerDAO dao;
	
	
	@Autowired
	GeneralCodeDAO gnDao;
	
	 
	@Autowired ApplicationContext appContext;
	
	
	public MICBResponseBean process(MICBRequestBean arg0) {
		// TODO Auto-generated method stub
		
		
	
		MICBResponseBean response = new MICBResponseBean();
		ObVcTranxResponse obFutureTranxResponse = new ObVcTranxResponse();
		obFutureTranxResponse.setObHeader(new ObHeaderResponse());
	
		
		
		try {


			ObVcTranxRequest requestData = (ObVcTranxRequest) arg0.getBDObject();			
			String orgId = requestData.getObUser().getOrgId();
			String usrId = requestData.getObUser().getLoginId();
			String ipAddress = requestData.getObHeader() == null ? null : requestData.getObHeader().getIp();
			VCService userAsyncService = new VCService(appContext); 
			
			GeneralCode futureTrnx_page_size = gnDao.findByGnCode("futureTrnx_page_size");
			String pageSize = futureTrnx_page_size==null?"10":futureTrnx_page_size.getGnCodeDescription();
			
		 
			ListenableFuture futureVCList = callFutureTranx(appContext,requestData.getNdays(),requestData.getPageNo(),pageSize,userAsyncService, orgId, usrId, requestData.getUserContext().getSessionId(), arg0.getTranxID(), ipAddress);
			obFutureTranxResponse = getFutureTransactionResult(obFutureTranxResponse,userAsyncService,futureVCList, requestData.getUserContext().getSessionId(),  arg0.getTranxID(),arg0.getLocaleCode());

		
		} catch (Exception e) {
			
			
		
			log.info(this.getClass().toString(), e);
			obFutureTranxResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_ERROR);
			obFutureTranxResponse.getObHeader().setStatusMessage(e.getMessage());
	
		}
		
		response.setBDObject(obFutureTranxResponse);
		
		return response;
	}

	
	
	 
	public ListenableFuture callFutureTranx(ApplicationContext appContextx,String Ndays,String pageNo, String pageSize, VCService userAsyncService, String orgId,String usrId, String sessionID, String mlebTrxId, String ipAddress) throws Exception
	{
		
		TranxRequestData tranxReqData = new TranxRequestData();
		tranxReqData.setOrg_cd(orgId);
		tranxReqData.setUsr_cd(usrId);
		
		
		
		if(Ndays==null || Ndays.trim().length()==0)
		{
			tranxReqData.setN_days(30);
		}
		else
		{
			tranxReqData.setN_days(Integer.parseInt(Ndays));
		}
		
		
		if(pageNo==null || pageNo.trim().length()==0)
		{
			tranxReqData.setPage_no(1);
		}
		else
		{
			tranxReqData.setPage_no(Integer.parseInt(pageNo));
		}
		
		
		tranxReqData.setPage_size(Integer.parseInt(pageSize));
		ListenableFuture futureVCResponse = userAsyncService.callOmniVCAsync(TranxRequestData.method_transaction_futureDated,tranxReqData,new ResponseData() , sessionID, mlebTrxId, ipAddress);
		
		return futureVCResponse;
		
	}
	
	
	
	public ObVcTranxResponse getFutureTransactionResult(ObVcTranxResponse obResponse,  VCService vcservice, ListenableFuture futureVCResponse, String sessionID, String mlebTrxId, String locale) throws Exception
	{
		obResponse.setTransaction(new ArrayList());
		
	 
		VCResponse vcResponse = new VCResponse();
		
		 
			
		TranxResponseData responseDataTmp = new TranxResponseData();
		VCResponse vcResponsex = vcservice.getAscyncResponseData(TranxRequestData.method_transaction_futureDated, futureVCResponse, responseDataTmp , sessionID, mlebTrxId);
		if(!vcResponsex.getResponse_code().equalsIgnoreCase(MiBConstant.OMNI_SUCCESS)) 
		{
			obResponse.getObHeader().setStatusCode(MiBConstant.MIB_OMNI_PREFIX+vcResponsex.getResponse_code() );
			obResponse.getObHeader().setStatusMessage(vcResponsex.getResponse_desc());
		}
		else
		{
			responseDataTmp = (TranxResponseData) vcResponsex.getData();
			
			if(null!=gnDao)
			{
				List<GeneralCode> productCodeList = new ArrayList();
				if(locale.equalsIgnoreCase(MiBConstant.LOCALE_IN))
				{
					productCodeList = gnDao.getAllTaskTranxProductCode_IN();
				}
				else if(locale != null  && locale.toLowerCase().startsWith(MiBConstant.LOCALE_ZH.toLowerCase()))
				{
					productCodeList = gnDao.getAllTaskTranxProductCode_CN();
				}
				else
				{
					productCodeList = gnDao.getAllTaskTranxProductCode();
				}
				
				
				//map product description
				for(Transaction futureTranx:responseDataTmp.getList_trx())
				{
					log.info("prod code size :"+productCodeList.size());
					for(GeneralCode gnTmp:productCodeList)
					{
						if(null!=futureTranx.getProd_cd() && futureTranx.getProd_cd().equalsIgnoreCase(gnTmp.getGnCode()))
						{
							futureTranx.setProd_desc(gnTmp.getGnCodeDescription());
							break;
						}
					}
					
				}
				
				
				
			}
			
			
			obResponse.setTransaction(responseDataTmp.getList_trx());
			obResponse.setTotalTranx(String.valueOf(responseDataTmp.getTotal_rows()));
			
			
			
			
			obResponse.getObHeader().setStatusCode(MiBConstant.MIB_COMMON_SUCCESS);
		 
		}
			
		 
		
 
		
		
		return obResponse;
	}
	
	
	 

	
	
}